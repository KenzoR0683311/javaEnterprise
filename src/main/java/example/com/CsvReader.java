package example.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import example.com.entities.Todo;
import example.com.service.TodoService;

import org.slf4j.*;

@Component
public class CsvReader implements CommandLineRunner {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  TodoService todoService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello");
    
    if (args.length >= 1) {
      String csvFilePath = args[0];
      
      try {
        readCSV(csvFilePath);
      } catch (Exception e) {
        logger.error("Error reading CSV file: " + e.getMessage());
        System.exit(1);
      }
    }
  }

  private void readCSV(String csvFilePath) throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
        String headerLine = reader.readLine();
        String line;


        while ((line = reader.readLine()) != null) {
          String[] parts = line.split(",");
          String title = parts[0];
          String comment = parts[1];
          Date expiryDate = new SimpleDateFormat("dd-MM-yyyy").parse(parts[2]);
          String email = parts[3];
          Todo todo = new Todo(title, comment, expiryDate);
          
          try {
            todoService.save(email, todo);
          } catch(UsernameNotFoundException e) {
            logger.error("An error occurred", e);
          }
        } 
      }
    }
  }


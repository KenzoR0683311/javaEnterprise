package example.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import example.com.entities.Todo;
import example.com.service.TodoService;

@Component
public class CsvReader implements CommandLineRunner {

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
        System.err.println("Error reading CSV file: " + e.getMessage());
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
        Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[2]);
        String email = parts[3];

        Todo todo = new Todo(title, comment, expiryDate);
        todoService.save(email, todo);
      }
    }
  }
}

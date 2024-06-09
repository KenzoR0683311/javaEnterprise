package example.com.soap.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import example.com.entities.Todo;
import example.com.service.TodoService;
import example.com.soap.model.AddTodoRequest;
import example.com.soap.model.AddTodoResponse;
import example.com.soap.model.STypeProcessOutcome;

@Endpoint
public class TodoEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(TodoEndpoint.class);
    private static final Logger msgLogger = LoggerFactory.getLogger("messagelogger");
    
    @Autowired
    TodoService todoService;

    @PayloadRoot(namespace = "http://ucll.be/java/com/example/todo", localPart = "addTodoRequest")
    @ResponsePayload
    public AddTodoResponse processMessage(@RequestPayload AddTodoRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        AddTodoResponse response = new AddTodoResponse();

        String email = request.getEmail();

        
        try {
            Todo todo = new Todo(request.getTitle(), request.getComment(), formatter.parse(request.getExpirydate()));

            // 2. Log the incoming event and the data
            // logger.debug("Received message '" + cm.getMessage() + "' from " + cm.getSender());
            // msgLogger.info("SOAP | " + cm.getSender() + " | " + cm.getMessage());

            // 3. Process the message (call Business Logic layer and pass the DTO on)
            Optional<Todo> t = todoService.save(todo);
            
            // 4a. Prepare the JAXB generated Response. All processing went OK
            response.setCode(0);
            response.setType(STypeProcessOutcome.INFO);
        } catch (IllegalArgumentException e) {
            // 4b. Prepare the JAXB generated Response. Something went wrong. Inform the caller of the error
            response.setCode(1);
            response.setType(STypeProcessOutcome.ERROR);
            response.setFeedback(e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected exception occured", e);

            // 4c. Prepare the JAXB generated Response. Something went wrong. Inform the caller of the error
            response.setCode(1);
            response.setType(STypeProcessOutcome.ERROR);
            response.setFeedback("An unexpected exception occured");
        }

        // 5. Effectively return the SOAP Web-Service response
        return response;
    }
}


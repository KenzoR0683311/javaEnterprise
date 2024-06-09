package example.com.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import example.com.entities.Todo;
import example.com.service.TodoService;
import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;

@Route("addTodoView")
@PermitAll
public class AddTodoView extends FormLayout {
    private TodoService todoService;

    @Autowired
    public AddTodoView(TodoService todoService) {
        this.todoService = todoService;

        TextField titleField = new TextField("Title");
        TextField commentField = new TextField("Comment");
        DatePicker expiryDateField = new DatePicker("Expiry Date");

        Button addButton = new Button("Add");
        addButton.addClickListener(event -> {
            // Create new todo with form data
            Todo newTodo = new Todo(
                    titleField.getValue(),
                    commentField.getValue(),
                    java.sql.Date.valueOf(expiryDateField.getValue())
            );

            // Save the new todo
            todoService.save(newTodo);

            // Show notification
            Notification.show("Todo added successfully");

            // Clear form fields
            titleField.clear();
            commentField.clear();
            expiryDateField.clear();
            getUI().ifPresent(ui -> ui.navigate("/"));
        });

        add(titleField, commentField, expiryDateField, addButton);
    }
}

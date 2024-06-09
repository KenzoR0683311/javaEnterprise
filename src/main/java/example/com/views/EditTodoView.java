package example.com.views;

import java.time.ZoneId;
import java.util.Date;
import java.util.EnumSet;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import example.com.entities.Todo;
import example.com.enums.TodoStatus;
import example.com.service.TodoService;
import jakarta.annotation.security.PermitAll;

@Route("editTodoPage")
@PermitAll
public class EditTodoView extends FormLayout implements HasUrlParameter<Long> {
  private TodoService todoService;
  private Todo todo;
  
  private TextField idField = new TextField("id");
  private TextField titleField = new TextField("Title");
  private TextField commentField = new TextField("Comment");
  private DatePicker expiryDateField = new DatePicker("Expiry Date");
	private Select<TodoStatus> select = new Select<>();
  private Button editButton = new Button("Edit", event -> editTodo());

  @Autowired
  public EditTodoView(TodoService todoService) {
    this.todoService = todoService;
    idField.setEnabled(false);

    select.setLabel("Todo Status");
    select.setItems(EnumSet.allOf(TodoStatus.class));

    add(idField, titleField, commentField, expiryDateField, select, editButton);
  }

	private void editTodo() {
    todo.setTitle(titleField.getValue());
    todo.setComment(commentField.getValue());
    todo.setExpiryDate(Date.from(expiryDateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    todo.setCurrentStatus(select.getValue());

    todoService.save(todo);
    getUI().ifPresent(ui -> ui.navigate("/"));
  }

	@Override
  public void setParameter(BeforeEvent arg0, Long todoId) {
    todo = todoService.findById(todoId).get();
    idField.setValue(todoId.toString());
    titleField.setValue(todo.getTitle());
    commentField.setValue(todo.getComment());
    expiryDateField.setValue(todo.getExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    select.setValue(todo.getCurrentStatus());
  }
}


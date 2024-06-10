package example.com.views;

import example.com.service.TodoService;
import jakarta.annotation.security.PermitAll;
import example.com.entities.Todo;
import example.com.enums.TodoStatus;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Route("/")
@PermitAll
public class MainView extends VerticalLayout {
  private TodoService todoService;

  @Autowired
  public MainView(TodoService todoService) {
    this.todoService = todoService;
    List<Todo> todos = todoService
                        .findAll()
                        .stream()
                        .filter(todo -> todo.getCurrentStatus() == TodoStatus.TODO || todo.getCurrentStatus() == TodoStatus.DOING)
                        .collect(Collectors.toList());

    Grid<Todo> grid = new Grid<>(Todo.class);
    grid.setItems(todos);

    // Define columns to display in the grid
    grid.addColumn(Todo::getTitle).setHeader("Title");
    grid.addColumn(Todo::getComment).setHeader("Comment");
    grid.addColumn(Todo::getExpiryDate).setHeader("Expiry Date");
    grid.addColumn(Todo::getCurrentStatus).setHeader("Status");

    // Add edit and delete buttons next to each todo
    grid.addComponentColumn(todo -> {
      Button editButton = new Button("Edit");
      editButton.addClickListener(event -> {
        getUI().ifPresent(ui -> ui.navigate("editTodoPage/" + todo.getId()));
      });
      return editButton;
    }).setHeader("Edit");

    grid.addComponentColumn(todo -> {
      Button deleteButton = new Button("Delete");
        deleteButton.addClickListener(event -> {
          todoService.remove(todo.getId());
          todos.remove(todo); 
          grid.setItems(todos); 
        });
        return deleteButton;
      }).setHeader("Delete");

      add(grid, createAddButton());
    }

    private Button createAddButton() {
        Button addButton = new Button("Add");
        addButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("addTodoView"));
        });
        return addButton;
    }
}

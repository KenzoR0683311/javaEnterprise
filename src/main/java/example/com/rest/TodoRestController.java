package example.com.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.com.entities.Todo;
import example.com.service.TodoService;

@RestController
@RequestMapping("api/todo")
class TodoRestController {
  @Autowired
  private TodoService todoService;
  
  @GetMapping()
  public ResponseEntity<List<Todo>> getAll() {
    List<Todo> todoList = todoService.findAll(); 
    return new ResponseEntity<>(todoList, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> get(@PathVariable("id") long id) {
    Optional<Todo> optionalTodo = todoService.findById(id);
    
    Todo todo = optionalTodo.get();
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }

  @PostMapping()
  public void add(@RequestBody Todo todo) {
    todoService.save(todo);
  }

  @PutMapping()
  public void update(@RequestBody Todo todo) {
    todoService.save(todo);
  }


  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") long id) {
    todoService.remove(id);
  }
}


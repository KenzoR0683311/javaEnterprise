package example.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import example.com.entities.Todo;
import example.com.entities.User;
import example.com.repository.TodoRepository;
import example.com.repository.UserRepository;

import org.slf4j.*;

@Service
public class TodoService {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private TodoRepository todoRepository;
  private UserRepository userRepository;
  
  @Autowired
  public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
  }

  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  public Optional<Todo> findById(long id) {
    return todoRepository.findById(id);
  }

  public Optional<Todo> save(@RequestBody Todo todo) {
    Todo savedTodo = todoRepository.save(todo);
    return Optional.of(savedTodo);
  }

  public void save(String email, Todo todo) {
    User user = userRepository.findByEmail(email);
    todo.setUser(user);
    todoRepository.save(todo);
  }

  public void remove(long id) {
    todoRepository.deleteById(id);
  }

}


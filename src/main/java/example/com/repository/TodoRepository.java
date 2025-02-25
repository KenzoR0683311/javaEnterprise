package example.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {}


package toyproject.todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.todolist.todo.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

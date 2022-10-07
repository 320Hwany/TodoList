package toyproject.todoCalculator.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.todoCalculator.todo.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}

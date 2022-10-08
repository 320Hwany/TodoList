package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todoCalculator.todo.domain.Todo;
import toyproject.todoCalculator.todo.repository.TodoRepository;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void join(Todo todo) {
        todoRepository.save(todo);
    }
}

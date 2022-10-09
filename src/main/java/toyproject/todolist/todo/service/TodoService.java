package toyproject.todolist.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todolist.todo.domain.Todo;
import toyproject.todolist.todo.repository.TodoRepository;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void join(Todo todo) {
        todoRepository.save(todo);
    }
}

package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todoCalculator.todo.domain.Todo;
import toyproject.todoCalculator.todo.dto.TodoDto;
import toyproject.todoCalculator.todo.repository.TodoRepository;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void join(TodoDto todoDto) {

        todoRepository.save(Todo.builder()
                .work(todoDto.getWork())
                .build());
    }
}

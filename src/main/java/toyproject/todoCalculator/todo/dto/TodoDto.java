package toyproject.todoCalculator.todo.dto;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class TodoDto {

    @Size(min = 2)
    private String work;
}

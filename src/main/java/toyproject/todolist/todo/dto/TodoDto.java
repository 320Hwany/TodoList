package toyproject.todolist.todo.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.todolist.todo.domain.Member;
import toyproject.todolist.todo.domain.Todo;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoDto {

    @NotEmpty
    private String title;

    private Member member;

    @Builder
    public TodoDto(String title) {
        this.title = title;
    }

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .member(member)
                .build();
    }
}

package toyproject.todolist.todo.dto;

import lombok.*;
import toyproject.todolist.todo.domain.Member;
import toyproject.todolist.todo.domain.Todo;

import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @Size(min = 2, max = 12, message = "아이디는 2글자 이상 20글자 이하로 작성해주세요")
    // 메세지 출력하는법 알아보자
    private String username;

    @Size(min = 2, max = 12)
    private String password;

    @Builder
    public MemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private List<Todo> todos;

    public Member toEntity() {

        return Member.builder()
                .name(username)
                .password(password)
                .auth("ROLE_USER")
                .todos(todos)
                .build();
    }
}

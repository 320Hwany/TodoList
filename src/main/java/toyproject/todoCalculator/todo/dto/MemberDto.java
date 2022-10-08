package toyproject.todoCalculator.todo.dto;

import lombok.Getter;
import lombok.Setter;
import toyproject.todoCalculator.todo.domain.Todo;

import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
public class MemberDto {

    @Size(min = 2, max = 12, message = "아이디는 2글자 이상 20글자 이하로 작성해주세요")
    // 메세지 출력하는법 알아보자
    private String username;

    @Size(min = 2, max = 12)
    private String password;

    private String auth;

    private List<Todo> todos;
}

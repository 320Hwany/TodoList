package toyproject.todoCalculator.todo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
public class MemberDto {

    @Size(min = 2, max = 12, message = "아이디는 2글자 이상 20글자 이하로 작성해주세요")
    private String username;

    @Size(min = 2, max = 12)
    private String password;

    private String auth;
}

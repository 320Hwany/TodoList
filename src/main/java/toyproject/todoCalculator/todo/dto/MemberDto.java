package toyproject.todoCalculator.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String auth;
}

package toyproject.todoCalculator.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import toyproject.todoCalculator.todo.domain.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
@AllArgsConstructor
public class MemberDto {

    @Size(min = 4, max = 12, message = "아이디는 4글자 이상 12글자 이하로 작성해 주세요")
    @NotBlank
    private String name;

    @Size(min = 4, max = 12, message = "비밀번호는 4글자 이상 12글자 이하로 작성해 주세요")
    @NotBlank
    private String password;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .build();
    }
}

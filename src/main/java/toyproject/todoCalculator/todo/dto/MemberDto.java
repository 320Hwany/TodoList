package toyproject.todoCalculator.todo.dto;

import lombok.Getter;
import lombok.Setter;
import toyproject.todoCalculator.todo.domain.Member;

@Getter @Setter // @Setter 넣어주지 않아서 @ModelAttribute 가 parameter 가져올 때 인식하지 못했다..
public class MemberDto {

    private String name;
    private String password;

    public MemberDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .build();
    }
}

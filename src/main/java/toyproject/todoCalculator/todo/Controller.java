package toyproject.todoCalculator.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.service.MemberService;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final MemberService memberService;

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/memberForm")
    public String memberForm() {
        return "memberForm";
    }

    @PostMapping("/memberForm")
    public String joinMember(@ModelAttribute MemberDto memberDto) {
        memberService.join(memberDto.toEntity());

        return "redirect:/";
    }

    @PostMapping("/")
    public String login(@ModelAttribute MemberDto memberDto) {
        if (memberService.login(memberDto) == true) {
            return "hello";
        }
        return "redirect:/";
    }
}

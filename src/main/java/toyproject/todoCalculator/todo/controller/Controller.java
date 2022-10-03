package toyproject.todoCalculator.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.service.MemberService;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/signup")
    public String memberForm() {
        return "signup";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @PostMapping("/signup")  // @Size 적용하려면 @Valid 꼭 있어야 한다.
    public String joinMember(@Valid MemberDto memberDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/signup?error1";
        }
        if (memberService.join(memberDto)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup?error2";
        }
    }
}

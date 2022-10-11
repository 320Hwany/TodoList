package toyproject.todolist.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.todolist.todo.domain.Member;
import toyproject.todolist.todo.dto.MemberDto;
import toyproject.todolist.todo.service.MemberService;

import javax.validation.Valid;
import java.security.Principal;

@Controller // 축약 하는법! 클래스 이름이 Controller 이면 안된다!
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/signup")
    public String memberForm() {
        return "signup";
    }

    @GetMapping("/menu") // 로그인 정보를 ModelAttribute 로 받아오려고 했는데 잘 안되었다.
    //Security 를 이용하니 Principal 을 이용해서 멤버를 찾았다.
    public String menu(Principal principal, RedirectAttributes redirectAttributes) {

        String name = principal.getName();
        Member member = memberService.findMemberByName(name);
        Long id = member.getId();
        redirectAttributes.addAttribute("id", id); // redirect 로 파라미터 넘기려면 필요하다
        return "redirect:/menu/{id}";
    }

    @GetMapping("/menu/{id}")
    public String redirectMenu(@PathVariable Long id, Model model) {

        Member member = memberService.findMemberById(id);
        model.addAttribute("member", member);
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

package toyproject.todoCalculator.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.service.MemberService;

import javax.validation.Valid;

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

    @GetMapping("/{id}")
    public String memberLogin(@PathVariable Long id, Model model) {

        Member member = memberService.findMember(id).get();
        model.addAttribute(member);
        return "hello";
    }


    @PostMapping("/memberForm")  // @Size 적용하려면 @Valid 꼭 있어야 한다.
    public String joinMember(@ModelAttribute @Valid MemberDto memberDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/memberForm";
        }
        else {
            memberService.join(memberDto);
        }
        return "redirect:/";
    }

    @PostMapping("/")
    public String login(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {

        Member member = memberService.findByName(memberDto.getName()).get();
        redirectAttributes.addAttribute("id", member.getId());
        if (memberService.login(memberDto) == true) {
            return "redirect:/{id}";
        } else {
            return "redirect:/";
        }
    }
}

package toyproject.todoCalculator.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.service.MemberService;

@RequiredArgsConstructor
@Controller
public class TodoController {

    private final MemberService memberService;

    @GetMapping("/TodoList/{id}")
    public String todoList(@PathVariable Long id, Model model) {
        Member member = memberService.findMemberById(id);
        model.addAttribute("member", member);

        return "todo";
    }
}

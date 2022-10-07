package toyproject.todoCalculator.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.TodoDto;
import toyproject.todoCalculator.todo.service.MemberService;
import toyproject.todoCalculator.todo.service.TodoService;

@RequiredArgsConstructor
@Controller
public class TodoController {

    private final MemberService memberService;
    private final TodoService todoService;

    @GetMapping("/TodoList/{id}")
    public String todoList(@PathVariable Long id, Model model) {
        Member member = memberService.findMemberById(id);
        model.addAttribute("member", member);

        return "todo";
    }

    @PostMapping("/makeTodo")
    public void makeTodo(@ModelAttribute TodoDto todoDto) {

        todoService.join(todoDto);
    }
}

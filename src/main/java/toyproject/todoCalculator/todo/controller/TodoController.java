package toyproject.todoCalculator.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.domain.Todo;
import toyproject.todoCalculator.todo.service.MemberService;
import toyproject.todoCalculator.todo.service.TodoService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TodoController {

    private final MemberService memberService;
    private final TodoService todoService;

    @GetMapping("/TodoList/{id}")
    public String todoList(@PathVariable Long id, Model model) {

        Member member = memberService.findMemberById(id);
        List<Todo> todos = member.getTodos();

        model.addAttribute("member", member);
        model.addAttribute("todos", todos);

        return "todo";
    }

    @PostMapping("/makeTodo")
    public String makeTodo(@RequestParam String username, String work, RedirectAttributes redirectAttributes) {

        Member member = memberService.findMemberByName(username);
        Todo todo = new Todo(work);
        todo.setMember(member);
        todoService.join(todo);
        redirectAttributes.addAttribute("id", member.getId());

        return "redirect:TodoList/{id}";
    }
}

package toyproject.todolist.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.todolist.todo.domain.Member;
import toyproject.todolist.todo.domain.Todo;
import toyproject.todolist.todo.dto.TodoDto;
import toyproject.todolist.todo.service.MemberService;
import toyproject.todolist.todo.service.TodoService;

import javax.validation.Valid;
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

        return "makeTodo";
    }

    @GetMapping("/ShowList/{id}")
    public String showList(@PathVariable Long id, Model model) {

        Member member = memberService.findMemberById(id);
        List<Todo> todos = member.getTodos();

        model.addAttribute("member", member);
        model.addAttribute("todos", todos);

        return "showTodo";
    }

    @PostMapping("/makeTodo")
    public String makeTodo(@RequestParam @Valid String username, String work, RedirectAttributes redirectAttributes) {

        Member member = memberService.findMemberByName(username);
        TodoDto todoDto = new TodoDto(work);
        Todo todo = todoDto.toEntity();
        todo.setMember(member);
        todoService.join(todo);
        redirectAttributes.addAttribute("id", member.getId());

        return "redirect:menu/{id}";
    }
}

package toyproject.todolist.todo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todolist.todo.domain.Member;
import toyproject.todolist.todo.domain.Todo;
import toyproject.todolist.todo.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest // 스프링 통합 테스트로 해야 memberService 가져올 수 있다. 없으면 @Autowired 안된다.
    // 데이터베이스에 연결안하고 테스트해서 fail 떴었다.
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    TodoService todoService;

    private List<Todo> todos;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("hello");
        member.setPassword("1234");
        Todo todo = new Todo("hi");
        member.setTodos(todos);

        //when
        memberService.join(member);
        todoService.join(todo);
        Member findMember = memberService.findMemberByName(member.getUsername());

        //then
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(findMember.getAuth()).isEqualTo("ROLE_USER");
        assertThat(findMember.getTodos()).isEqualTo(member.getTodos());
    }
}
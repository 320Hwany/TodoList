package toyproject.todolist.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    public Todo(String title) {
        this.title = title;
    }

    // 연관 관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getTodos().add(this);
    }
}

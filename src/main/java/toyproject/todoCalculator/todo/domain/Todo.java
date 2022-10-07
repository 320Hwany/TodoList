package toyproject.todoCalculator.todo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false)
    private String work;

    @OneToOne(mappedBy = "todo", fetch = LAZY)
    private Member member;

    @Builder
    public Todo(String work) {
        this.work = work;
    }
}

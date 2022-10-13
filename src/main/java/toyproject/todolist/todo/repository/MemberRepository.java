package toyproject.todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.todolist.todo.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findById(Long id);

    void deleteById(Long id);
}

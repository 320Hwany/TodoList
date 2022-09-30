package toyproject.todoCalculator.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.todoCalculator.todo.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Optional<Member> findByPassword(String password);
}

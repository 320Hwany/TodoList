package toyproject.todoCalculator.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.MemberDto;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
}

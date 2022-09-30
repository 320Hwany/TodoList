package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id).get();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public boolean login(MemberDto memberDto) {
        Optional<Member> findByName = memberRepository.findByName(memberDto.getName());
        Optional<Member> findByPassword = memberRepository.findByPassword(memberDto.getPassword());

        if (findByName.isPresent() && findByPassword.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}

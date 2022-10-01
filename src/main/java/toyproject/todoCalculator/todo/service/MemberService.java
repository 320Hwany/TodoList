package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional // 이거 왜 쓰징?
    public void join(MemberDto memberDto) {
        memberRepository.save(Member.builder()
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .build());
    }

    @Transactional
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    @Transactional
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
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

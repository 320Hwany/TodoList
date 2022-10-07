package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.domain.Todo;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.repository.MemberRepository;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    @Transactional // 이거 왜 쓰징?
    public Boolean join(MemberDto memberDto) {

        if (memberRepository.findByUsername(memberDto.getUsername()).isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));

        Todo todo = new Todo("Hello");

        memberRepository.save(Member.builder()
                .name(memberDto.getUsername())
                .password(memberDto.getPassword())
                .auth("ROLE_USER")
                .todo(todo)
                .build());

        return true;
    }

    @Transactional
    public Member findMemberByName(String username) {

        Member member = memberRepository.findByUsername(username).get();
        return member;
    }

    @Transactional
    public Member findMemberById(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }
}

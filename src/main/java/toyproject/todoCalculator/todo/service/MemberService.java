package toyproject.todoCalculator.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.todoCalculator.todo.domain.Member;
import toyproject.todoCalculator.todo.dto.MemberDto;
import toyproject.todoCalculator.todo.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    @Transactional // 이거 왜 쓰징?
    public Boolean join(Member member) {

        if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));

        memberRepository.save(Member.builder()
                .name(member.getUsername())
                .password(member.getPassword())
                .auth("ROLE_USER")
                .todos(member.getTodos())
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

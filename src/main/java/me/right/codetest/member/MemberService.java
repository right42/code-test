package me.right.codetest.member;

import me.right.codetest.study.Study;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {

    Optional<Member> findById(Long memberId);

    void notify(Study study);

    void validate(Long memberId);

    void notify(Member member);
}

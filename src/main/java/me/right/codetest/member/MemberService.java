package me.right.codetest.member;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);
}

package me.right.codetest.study;

import me.right.codetest.member.Member;
import me.right.codetest.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudyService {

    private final StudyRepository studyRepository;

    private final MemberService memberService;

    public StudyService(StudyRepository studyRepository, MemberService memberService) {
        assert memberService != null;
        assert studyRepository != null;
        this.studyRepository = studyRepository;
        this.memberService = memberService;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : " + memberId)));

        Study newStudy = studyRepository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member.get());
        return newStudy;
    }
}

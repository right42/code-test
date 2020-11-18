package me.right.codetest;

import me.right.codetest.member.Member;
import me.right.codetest.member.MemberService;
import me.right.codetest.study.Study;
import me.right.codetest.study.StudyRepository;
import me.right.codetest.study.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    void createStudyService(@Mock StudyRepository studyRepository,
                            @Mock MemberService memberService ){

        StudyService studyService = new StudyService(studyRepository, memberService);
        assertThat(studyService).isNotNull();

        Member member = new Member(1L, "name");
        when(memberService.findById(any())).thenReturn(Optional.of(member));

        Optional<Member> optional = memberService.findById(1L);

        assertThat(optional.get()).isEqualTo(member);

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());
        ;

        Optional<Member> optionalMember = memberService.findById(1L);
        assertThat(optionalMember).isNotEmpty();

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        Optional<Member> optionalMember2 = memberService.findById(3L);
        assertThat(optionalMember2).isEmpty();
    }

    @Test
    void stubbingTest(@Mock MemberService memberService,
                      @Mock StudyRepository studyRepository
                      ){
        StudyService studyService = new StudyService(studyRepository, memberService);
        assertThat(studyService).isNotNull();

        Study study = new Study();
        study.setName("test");
        Member member = new Member(1L, "name");

        when(studyRepository.save(study)).thenReturn(study);
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Study newStudy = studyService.createNewStudy(1L, study);

        assertThat(newStudy.getName()).isNotNull();
        assertThat(newStudy.getOwner()).isEqualTo(member);
    }
}

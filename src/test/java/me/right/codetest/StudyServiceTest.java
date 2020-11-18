package me.right.codetest;

import me.right.codetest.member.Member;
import me.right.codetest.member.MemberService;
import me.right.codetest.study.Study;
import me.right.codetest.study.StudyRepository;
import me.right.codetest.study.StudyService;
import me.right.codetest.study.StudyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    StudyRepository studyRepository;

    @Mock
    MemberService memberService;

    @Test
    void createStudyService(){

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
    void stubbingTest(){
        StudyService studyService = new StudyService(studyRepository, memberService);
        assertThat(studyService).isNotNull();

        Study study = new Study();
        study.setName("test");
        Member member = new Member(1L, "name");

//        when(studyRepository.save(study)).thenReturn(study);
//        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        Study newStudy = studyService.createNewStudy(1L, study);

        assertThat(newStudy.getName()).isNotNull();
        assertThat(newStudy.getOwner()).isEqualTo(member);

        then(memberService).should(times(1)).notify(newStudy);
        then(memberService).shouldHaveNoMoreInteractions();
//        verify(memberService, times(1)).notify(newStudy);
//        verifyNoMoreInteractions(memberService);

    }

    @Test
    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    void openStudy(){
        // given
        StudyService studyService = new StudyService(studyRepository, memberService);

        Study study = new Study();
        study.setName("테스트 스터디");
        assertNull(study.getOpenedDateTime());

        given(studyRepository.save(study)).willReturn(study);

        // when
        Study openStudy = studyService.openStudy(study);

        // then
        assertThat(openStudy.getStudyStatus()).isEqualTo(StudyStatus.OPENED);
        assertThat(openStudy.getOpenedDateTime()).isNotNull();
        then(memberService).should().notify(study);

    }

}


package me.right.codetest;

import me.right.codetest.member.MemberService;
import me.right.codetest.study.StudyRepository;
import me.right.codetest.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {


    @Test
    void createStudyService(@Mock StudyRepository studyRepository,
                            @Mock MemberService memberService ){
        StudyService studyService = new StudyService(studyRepository, memberService);

        assertThat(studyService).isNotNull();
    }
}

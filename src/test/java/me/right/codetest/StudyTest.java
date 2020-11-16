package me.right.codetest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest {

    int value = 0;

    @BeforeAll
    void beforeAll(){
        System.out.println("before all");
    }

    @Test
    @DisplayName("카운트 1증가")
    void value_plus(){
        value++;

        assertThat(value).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("카운트 2증가")
    void value_plus_second(){
        value += 2;

        assertThat(value).isGreaterThanOrEqualTo(2);
    }
}

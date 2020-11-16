package me.right.codetest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInstanceStudy {

    int value = 0;

    @BeforeAll
    void beforeAll(){
        System.out.println("before all");
    }

    @Order(2)
    @Test
    @DisplayName("카운트 1증가")
    void value_plus(){
        value++;

        assertThat(value).isGreaterThanOrEqualTo(1);
    }

    @Order(1)
    @Test
    @DisplayName("카운트 2증가")
    void value_plus_second(){
        value += 2;

        assertThat(value).isGreaterThanOrEqualTo(2);
    }
}

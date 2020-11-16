package me.right.codetest;

import me.right.codetest.modules.FindSlowTestExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(FindSlowTestExtension.class)
public class StudyTest {

    @Test
    void slowTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);

    }

}

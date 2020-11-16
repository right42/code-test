package me.right.codetest;

import me.right.codetest.annotation.SlowTest;
import me.right.codetest.modules.FindSlowTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.concurrent.TimeUnit;

public class StudyTest {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension();

    @SlowTest
    void slowTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2L);

    }

}

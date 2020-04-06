package by.pva.testing.testExamples.timeouts;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TimeOuts_ex {
    @BeforeEach
    @Timeout(5)
    void setUp() throws InterruptedException {
        // fails if execution time exceeds 5 seconds
    	// Thread.sleep(6000);
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds100Milliseconds() throws InterruptedException {
        // fails if execution time exceeds 100 milliseconds
    	// Thread.sleep(200);
    }
}

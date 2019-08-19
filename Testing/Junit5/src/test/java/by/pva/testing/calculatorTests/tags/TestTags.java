package by.pva.testing.calculatorTests.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
public class TestTags {
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }
}

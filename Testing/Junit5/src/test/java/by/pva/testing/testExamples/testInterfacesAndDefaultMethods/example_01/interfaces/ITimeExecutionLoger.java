package by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.interfaces;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import by.pva.testing.testExamples.testInterfacesAndDefaultMethods.example_01.extension.TimingExtension;

@Tag("timed")
@ExtendWith(TimingExtension.class)
public interface ITimeExecutionLoger {
}

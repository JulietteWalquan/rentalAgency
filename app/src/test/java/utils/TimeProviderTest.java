package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("utils")
class TimeProviderTest {

    @DisplayName("Get current year")
    @Test
    void test_currentYearValue() {
        int currentYear = TimeProvider.currentYearValue();
        assertEquals(2024, currentYear);
    }
}
package utils;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("utils")
class TimeProviderTest {

    @Test
    void test_create_timeProvider() {
        TimeProvider timeProvider = new TimeProvider();
        assertNotNull(timeProvider);
    }

    @Test
    void test_currentYearValue() {
        int currentYear = TimeProvider.currentYearValue();
        assertEquals(2024, currentYear);
    }
}
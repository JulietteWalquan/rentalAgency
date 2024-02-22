package utils;

import agency.Car;
import agency.Motorbike;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("utils")
class MaxPriceCriterionTest {

    @Test
    void test_create_positive_maxPriceCriterion() {
        // Given
        double maxPrice = 100.0;
        MaxPriceCriterion maxPriceCriterion = new MaxPriceCriterion(maxPrice);

        // Then
        assertNotNull(maxPriceCriterion);
        assertThat(maxPriceCriterion.getMaxPrice())
                .isNotNaN()
                .isNotNull()
                .isNotNegative()
                .isEqualTo(maxPrice);
    }


    @Test
    void test_successful_test() {
        // Given
        double maxPrice = 250.0;
        MaxPriceCriterion maxPriceCriterion = new MaxPriceCriterion(maxPrice);

        // When
        Car car = mock(Car.class);
        when(car.dailyRentalPrice()).thenReturn(200.0);

        // Then
        assertTrue(maxPriceCriterion.test(car));
    }

    @Test
    void test_unsuccessful_test() {
        // Given
        double maxPrice = 100.0;
        MaxPriceCriterion maxPriceCriterion = new MaxPriceCriterion(maxPrice);

        // When
        Motorbike motorbike = mock(Motorbike.class);
        when(motorbike.dailyRentalPrice()).thenReturn(222.5);

        // Then
        assertFalse(maxPriceCriterion.test(motorbike));
    }
}
package utils;

import agency.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("utils")
class BrandCriterionTest {

    String brand = "Toyota";
    BrandCriterion brandCriterion;

    @BeforeEach
    void setUp() {
        brandCriterion = new BrandCriterion(brand);
    }

    @DisplayName("Test to create a brandCriterion")
    @Test
    void test_create_brandCriterion() {
        // Given
        BrandCriterion brandCriterionTest = new BrandCriterion(brand);

        // Then
        assertNotNull(brandCriterionTest);
        assertThat(brandCriterionTest.getBrand())
                .isNotBlank()
                .isEqualTo(brand)
                .isNotEqualTo("Renault");
    }

    @DisplayName("Test to check if the brandCriterion match")
    @Test
    void test_successful_test() {
        // When
        Car car = mock(Car.class);
        when(car.getBrand()).thenReturn(brand);

        // Then
        assertTrue(brandCriterion.test(car));
    }

    @DisplayName("Test to check if the brandCriterion doesn't match")
    @Test
    void test_unsuccessful_test() {
        // When
        Car car = mock(Car.class);
        when(car.getBrand()).thenReturn("Renault");

        // Then
        assertFalse(brandCriterion.test(car));
    }
}
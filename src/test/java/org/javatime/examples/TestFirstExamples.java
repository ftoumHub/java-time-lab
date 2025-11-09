package org.javatime.examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFirstExamples {

    private final FirstExamples firstExamples = new FirstExamples();

    @Test
    @Order(0)
    public void fail_on_wrong_value_for_days() {
        DateTimeException exception = assertThrows(DateTimeException.class, () -> {
            LocalDate.of(2025, Month.JUNE, 45);
        });

        // Vérifie le message exact ou une partie du message
        String expectedMessagePart = "Invalid value for DayOfMonth (valid values 1 - 28/31): 45";
        assertTrue(exception.getMessage().contains(expectedMessagePart),
                "Le message devrait contenir : " + expectedMessagePart);
    }

    @Test
    @Order(1)
    public void verify_einstein_date_of_birth_is_correct() {
        final LocalDate einstein_date_of_birth = firstExamples.getEinsteinBirthday();
        assertEquals(1879, einstein_date_of_birth.getYear());
        assertEquals(14, einstein_date_of_birth.getDayOfMonth());
        assertEquals(Month.MARCH, einstein_date_of_birth.getMonth());
    }

    @Test
    @Order(2)
    public void test_time_is_11_30() {
        final LocalTime localTime = firstExamples.getSampleLocalTime();
        assertEquals(11, localTime.getHour());
        assertEquals(30, localTime.getMinute());
        assertEquals(0, localTime.getSecond());
    }

    @Test
    @Order(3)
    public void test_einstein_birth_and_time() {
        final LocalDateTime combined = firstExamples.getSampleLocalDateTime();
        verifyCombinedDateTime(combined);
    }

    @Test
    @Order(4)
    public void test_combined_date_time() {
        verifyCombinedDateTime(firstExamples.getComponentDateTime());
    }

    @Test
    @Order(5)
    public void test_date_from_time() {
        assertEquals(LocalDate.now(), firstExamples.getTodayFromLocalDateTime());
    }

    @Test
    @Order(6)
    public void test_time_difference_between_london_and_paris() {
        assertEquals(1, firstExamples.getDifferenceBetweenParisAndLondon());
    }

    private void verifyCombinedDateTime(LocalDateTime combined) {
        assertEquals(1879, combined.getYear());
        assertEquals(14, combined.getDayOfMonth());
        assertEquals(Month.MARCH, combined.getMonth());
        assertEquals(11, combined.getHour());
        assertEquals(30, combined.getMinute());
        assertEquals(0, combined.getSecond());
    }
}
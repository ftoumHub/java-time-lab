package org.javatime.examples;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAdvancedExamples {

    private final AdvancedExamples examples = new AdvancedExamples();

    @Test
    @Order(1)
    public void testFunctionalQuery() {
        LocalDate firstQuarterDate = LocalDate.now().with(Month.MARCH).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate secondQuarterDate = LocalDate.now().with(Month.JUNE).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate thirdQuarterDate = LocalDate.now().with(Month.SEPTEMBER).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate fourthQuarterDate = LocalDate.now().with(Month.NOVEMBER).with(TemporalAdjusters.lastDayOfMonth());

        assertEquals(Quarter.FIRST, examples.getQuarterOfYear(firstQuarterDate));
        assertEquals(Quarter.SECOND, examples.getQuarterOfYear(secondQuarterDate));
        assertEquals(Quarter.THIRD, examples.getQuarterOfYear(thirdQuarterDate));
        assertEquals(Quarter.FOURTH, examples.getQuarterOfYear(fourthQuarterDate));
    }

    @Test
    @Order(2)
    public void test_next_mlk_day() {
        LocalDate firstJan = LocalDate.of(2014, 1, 1);
        LocalDate onMLKDay = firstJan.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        LocalDate afterMLKDay = onMLKDay.plusDays(1);

        LocalDate mlkDay2014 = LocalDate.of(2014, 1, 20);
        LocalDate mlkDay2015 = LocalDate.of(2015, 1, 19);

        assertEquals(mlkDay2014, examples.getNextMartinLutherKingDay(firstJan));
        assertEquals(mlkDay2015, examples.getNextMartinLutherKingDay(onMLKDay));
        assertEquals(mlkDay2015, examples.getNextMartinLutherKingDay(afterMLKDay));
    }
}

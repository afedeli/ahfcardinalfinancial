package com.cardinalfinancial.checkout;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class DateChecker {
    public DateChecker() {
    }

    //Holidays
    public boolean isLaborDay(LocalDate checkDate){
        return checkDate.isEqual(getLaborDay(checkDate.getYear()));
    }

    public boolean isIndependenceDay(LocalDate checkDate){
        return checkDate.isEqual(getIndependenceDay(checkDate.getYear()));
    }

    //Weekends
    public boolean isWeekendDay(LocalDate checkDate){
        DayOfWeek currentDOW = checkDate.getDayOfWeek();
        return ((currentDOW == DayOfWeek.SATURDAY) || (currentDOW == DayOfWeek.SUNDAY));
    }


    //Helper Functions
    private LocalDate getIndependenceDay(int year){
        return LocalDate.of(year, 7, 4);
    }

    private  LocalDate getLaborDay(int year){
        LocalDate temp = LocalDate.of(year, 9, 1);
        LocalDate laborDay = temp.with(firstInMonth(DayOfWeek.MONDAY));
        return laborDay;
    }
}

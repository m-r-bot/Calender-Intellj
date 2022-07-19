package de.itemis.calender;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Locale;

public class Locator {

    private final YearMonth month;
    private final int xCoordinate;
    private final boolean sameYear;
    private LocalDate date;

    int day = date.getDayOfMonth();
    int month = sameYear ? date.getMonthValue() : date.getMonthValue() + 12;

    int x = xCoordinate; // ((month-1) * 75) + 12;
    int y = ((day-1) * 15) - 264;
    int width = 75;
    int height = 15;


    int xDateRectangle = (int) (x + 0.1*width); //0.714289 is added to x coordinate when looking up the flowRoot element - oder doch nicht - intially 0.33 //TODO figure out accurate number and not just make one up, look at transform function and or widgh and height and or x and y coordinate of flowROOOt element
    int yDateRectangle = (int) (y + 0.77502*height); //494.77502, initially 0.66
    double xDisplayedDay = x + 0.4*width;
    double yDisplayedDay = y + 0.77502*height;

}

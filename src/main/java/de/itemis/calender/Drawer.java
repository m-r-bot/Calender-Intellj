package de.itemis.calender;

import java.time.LocalDate;
import java.time.YearMonth;

public class Drawer {

    private final YearMonth month;
    private final int xCoordinate;
    private final boolean sameYear;
    private LocalDate date;


    String whiteHex = "#ffffff";
    String mediumBlueHex = "#009ff3";
    String lightBlueHex = "#b6e6ff";
    String darkBlueText = "#00457c";

    String fillColor = !holidayManager.isFeiertag(date)  ? whiteHex : mediumBlueHex;
    fillColor = (date.getDayOfWeek().getValue() == 6 ||  date.getDayOfWeek().getValue() == 7) ? mediumBlueHex : fillColor;

}

package de.itemis.calender;

import java.time.LocalDate;
import java.time.YearMonth;

public class Month {

    int x, y;
    LocalDate day;
    private YearMonth monthField;
    private final int currentX; //apparently those fields aren't actually used
    private final boolean sameYear;
    static private SVGFormatter svgFormatter = new SVGFormatter();

    public Month( YearMonth month, int currentX, boolean sameYear) {
        this.monthField = month;
        this.currentX = currentX;
        this.sameYear = sameYear;
    }

    private String generateMonthHeader(LocalDate date){
        int yMHeader = -265;
        String darkBlueText = "#00457c";
        var label = new StringBuilder();

        String monthHeader = String.valueOf(monthField.getMonth()).substring(0,3);
        label.append("<text x=\"").append(x).append("\" y=\"" + yMHeader + "\" font-family=\"sans-serif\" font-size=\"26,6px\" line-height=\"1.25\" fill=\"" + darkBlueText + "\">").append(monthHeader).append("</text>");
        return label.toString();
    }

    public String drawSVGForMonth () {
        var result = new StringBuilder();
        for(int day = 1; day<=monthField.lengthOfMonth(); day++){
            LocalDate currentDay = LocalDate.of(monthField.getYear(), monthField.getMonth(), day);
            Day dayObject = new Day(currentDay);
            result.append(this.generateMonthHeader(currentDay));
            result.append(dayObject.drawSVGForDay());
        }
        return result.toString();
    }

}

package de.itemis.calender;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthColumnPainter {
    private final YearMonth month;
    private final int currentX;
    private final boolean sameYear;
    private Holiday holidayManager = new Holiday();

    public MonthColumnPainter
    (YearMonth month, int currentX, boolean sameYear) {
        this.month = month;
        this.currentX = currentX;
        this.sameYear = sameYear;
    }

    private String createRectanglesForDay(LocalDate date, boolean sameYear) {
        String whiteHex = "#ffffff";
        String mediumBlueHex = "#009ff3";
        String lightBlueHex = "#b6e6ff";
        String darkBlueText = "#00457c";

        String fillColor = !holidayManager.isFeiertag(date)  ? whiteHex : mediumBlueHex;
        fillColor = (date.getDayOfWeek().getValue() == 6 ||  date.getDayOfWeek().getValue() == 7) ? mediumBlueHex : fillColor;

        int day = date.getDayOfMonth();

        int x = currentX;
        int y = ((day-1) * 15) - 264;
        int width = 75;
        int height = 15;

        String displayedDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMANY);
        displayedDay = displayedDay.substring(0,2);
        displayedDay = displayedDay.toUpperCase();

        int displayedDate = date.getDayOfMonth();

        int xDate = (int) (x + 0.1*width); //0.714289 is added to x coordinate when looking up the flowRoot element - oder doch nicht - intially 0.33 //TODO figure out accurate number and not just make one up, look at transform function and or widgh and height and or x and y coordinate of flowROOOt element
        int yDate = (int) (y + 0.77502*height); //494.77502, initially 0.66
        double xDisplayedDay = x + 0.4*width;
        double yDisplayedDay = y + 0.77502*height;

        StringBuilder sb = new StringBuilder();

        sb.append("<g>");
        sb.append("<rect ");
        sb.append("style=\" fill:").append(fillColor).append(";fill-opacity:1;stroke:#000000;stroke-width:0.30000001;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"");
        sb.append(" x=\"").append(x).append("\"");
        sb.append(" y=\"").append(y).append("\"");
        sb.append(" width=\"75\"");
        sb.append(" height=\"15\"");
        sb.append("/>");

        //not the prettiest structure but it works, maybe ask for a better way to do this tho
        if (displayedDate<10) {
            String dateSmallerTen = "0" + displayedDate;
            sb.append("<text x=\"").append(xDate).append("\" y=\"").append(yDate).append("\" font-family=\"Verdana;-inkscape-font-specification:'Verdana, Bold'\" font-size=\"26,6px\" font-weight=\"bold\" fill=\"").append(darkBlueText).append("\">").append(dateSmallerTen).append("</text>");
        }
        else{
            sb.append("<text x=\"").append(xDate).append("\" y=\"").append(yDate).append("\" font-family=\"Verdana;-inkscape-font-specification:'Verdana, Bold'\" font-size=\"26,6px\" font-weight=\"bold\" fill=\"").append(darkBlueText).append("\">").append(displayedDate).append("</text>");
        }

        sb.append("<text x=\"" + xDisplayedDay +"\" y=\""+ yDisplayedDay +"\" font-family=\"sans-serif\" font-size=\"26,6px\" line-height=\"1.25\" fill=\"" + darkBlueText + "\">" + displayedDay + "</text>");
        sb.append("</g>");
        return sb.toString();
    }

    private String createTextForMonth() {
        var label = new StringBuilder();
        String monthHeader = String.valueOf(month.getMonth()).substring(0,3);
        label.append("<text x=\"").append(currentX).append("\" y=\"-265\" font-family=\"sans-serif\" font-size=\"26,6px\" line-height=\"1.25\" fill=\"#00457c\">").append(monthHeader).append("</text>");
        return label.toString();
    }

    public String createRectanglesForMonth(){
        var result = new StringBuilder();
        for(int day = 1; day<=month.lengthOfMonth(); day++){
            LocalDate tag = LocalDate.of(month.getYear(), month.getMonth(), day);
            result.append(this.createTextForMonth());
            result.append(this.createRectanglesForDay(tag, sameYear));
        }
        return result.toString();
    }

}

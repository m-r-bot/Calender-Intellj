package de.itemis.calender;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Day {

    int day, month, year, x, y;
    static int width = 75, height = 15;
    public String Label;
    public String Colour; //should prob be private, change after constructor bug is fixed

    private Holidays holidayManager = new Holidays (year);
    static private SVGFormatter svgFormatter = new SVGFormatter();

    public Day (LocalDate date) {

        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();

        this.x = (month) * 75 + 12;
        this.y = (day) * 15 - 264;

        this.Label = generateLabel(date);
        this.Colour = generateColour(date);
    }

    public String generateColour(LocalDate date){

        //define colours used in calender
        String whiteHex = "#ffffff";
        String mediumBlueHex = "#009ff3";
        String lightBlueHex = "#b6e6ff";
        String darkBlueText = "#00457c";

        //determines if it is a holiday and a weekend day
        String fillColour = !holidayManager.isHoliday(date)  ? whiteHex : mediumBlueHex;
        fillColour = holidayManager.feiertage.contains(date) ? lightBlueHex : fillColour;
        fillColour = (date.getDayOfWeek().getValue() == 6 ||  date.getDayOfWeek().getValue() == 7) ? mediumBlueHex : fillColour;
        return fillColour;
    }

    private String generateLabel (LocalDate date){
        String darkBlueText = "#00457c";

        //style for day Month and weekday/-end name
        String displayedDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMANY);
        displayedDay = displayedDay.substring(0,2);
        displayedDay = displayedDay.toUpperCase();

        int displayedDate = date.getDayOfMonth();

        //coordinate for text elements in each day
        int xDate = (int) (this.x + 0.1*width); //0.714289 is added to x coordinate when looking up the flowRoot element - oder doch nicht - intially 0.33 //TODO figure out accurate number and not just make one up, look at transform function and or widgh and height and or x and y coordinate of flowROOOt element
        int yDate = (int) (this.y + 0.77502*height); //494.77502, initially 0.66
        double xDisplayedDay = this.x + 0.4*width;
        double yDisplayedDay = this.y + 0.77502*height;

        StringBuilder sb = new StringBuilder();

        //adds a 0 before number if it is smaller than 10 for consistency
        if (displayedDate<10) {
            String dateSmallerTen = "0" + displayedDate;
            sb.append("<text x=\"").append(xDate).append("\" y=\"").append(yDate).append("\" font-family=" + svgFormatter.Verdana + "font-size=\"26,6px\" font-weight=\"bold\" fill=\"").append(darkBlueText).append("\">").append(dateSmallerTen).append("</text>");
        }
        else{
            sb.append("<text x=\"").append(xDate).append("\" y=\"").append(yDate).append("\" font-family=\"Verdana;-inkscape-font-specification:'Verdana, Bold'\" font-size=\"26,6px\" font-weight=\"bold\" fill=\"").append(darkBlueText).append("\">").append(displayedDate).append("</text>");
        }

        //paint content to stringbuilder
        sb.append("<text x=\"").append(xDisplayedDay).append("\" y=\"").append(yDisplayedDay).append("\" font-family=\"sans-serif\" font-size=\"26,6px\" line-height=\"1.25\" fill=\"").append(darkBlueText).append("\">").append(displayedDay).append("</text>");
        return sb.toString();
    }

    public String drawSVGForDay (){
        StringBuilder sb = new StringBuilder();

        sb.append("<g>");
        sb.append("<rect ");
        sb.append("style=\" fill:").append(this.Colour).append(svgFormatter.fillElements);
        sb.append(" x=\"").append(x).append("\"");
        sb.append(" y=\"").append(y).append("\"");
        sb.append(" width=\"").append(width).append("\"");
        sb.append(" height=\"").append(height).append("\"");
        sb.append("/>");
        sb.append(this.Label);
        sb.append("</g>");

        return sb.toString();
    }
}

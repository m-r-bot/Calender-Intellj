import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Painter {
    public static Holiday holidayManager = new Holiday();
    public static String appendProps(String tagStart) {
        StringBuilder sb = new StringBuilder(tagStart);
        sb.append("   xmlns:ns=\"&amp;#38;#38;ns_ai;\"\r\n");
        sb.append("   xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\r\n");
        sb.append("   xmlns:cc=\"http://creativecommons.org/ns#\"\r\n");
        sb.append("   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n");
        sb.append("   xmlns:svg=\"http://www.w3.org/2000/svg\"\r\n");
        sb.append("   xmlns=\"http://www.w3.org/2000/svg\"\r\n");
        sb.append("   xmlns:xlink=\"http://www.w3.org/1999/xlink\"\r\n");
        sb.append("   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n");
        sb.append("   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n");
        sb.append("   width=\"1006mm\"\r\n");
        sb.append("   height=\"706mm\"\r\n");
        sb.append("   viewBox=\"-3 -3 1006 706\"\r\n");
        sb.append("   version=\"1.1\"\r\n");
        sb.append("   id=\"svg10724\"\r\n");
        sb.append("   sodipodi:docname=\"Kalender 2022.svg\"\r\n");
        sb.append("   inkscape:version=\"0.92.5 (2060ec1f9f, 2020-04-08)\">");
        return sb.toString();
    }
    // properties taken from calender doc

    public String appendTransform() {
        StringBuilder sb = new StringBuilder();
        sb.append("<g\r\n");
        sb.append("     inkscape:label=\"Kalender\"\r\n");
        sb.append("     inkscape:groupmode=\"layer\"\r\n");
        sb.append("     id=\"layer1\"\r\n");
        sb.append("     transform=\"translate(0,409)\"\r\n");
        sb.append("     style=\"display:inline\">");
        return sb.toString();
    }
    // transform translation function used to transform y coordinate of all
    // following g elements
    // if no value given, assume 0
    // (0,409) -> x-value position is changed by 0, y-value position is changed by
    // 409

    public String appendHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rect\r\n");
        sb.append(
                "       style=\"fill:#00457c;fill-opacity:0.99227798;stroke:#000000;stroke-width:0.30000001;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:0\"\r\n");
        sb.append("       id=\"rect4897\"\r\n");
        sb.append("       width=\"975\"\r\n");
        sb.append("       height=\"98\"\r\n");
        sb.append("       x=\"12\"\r\n");
        sb.append("       y=\"-397\" />");
        return sb.toString();
    }
    // ids are visible when clicking on the object in the svg file in inkspace

    public String appendRect(LocalDate date,boolean sameYear) {
        String whiteHex = "#ffffff";
        String lightRedHex =  "#ed5c5c";
        String fillColor = !holidayManager.isFeiertag(date) ? whiteHex : lightRedHex;
        int month = sameYear ? date.getMonthValue() : date.getMonthValue() + 12;
        int day = date.getDayOfMonth();
        int x = ((month-1) * 75) + 12;
        int y = ((day-1) * 15) - 264;
        int width = 75;
        int height = 15;
        String tagBez = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMANY) + " " + date.getDayOfMonth();
        int x_bez = (int) (x + 0.13*width);
        int y_bez = (int) (y + 0.66*height);
        StringBuilder sb = new StringBuilder();
        sb.append("<g>");
        sb.append("<rect ");

        sb.append(
                " style=\"fill:"+fillColor+ ";fill-opacity:1;stroke:#000000;stroke-width:0.30000001;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"");
        sb.append(" x=\"" + x + "\"");
        sb.append(" y=\"" + y + "\"");
        sb.append(" width=\"75\"");
        sb.append(" height=\"15\"");
        sb.append("/>");
        sb.append("<text x=\"" + x_bez +"\" y=\""+ y_bez+"\" font-family=\"Verdana\" font-size=\"11\" fill=\"black\">"+ tagBez + "</text>");
        sb.append("</g>");
        return sb.toString();
    }

    public  String appendFooter() {
        StringBuilder sb = new StringBuilder();
        sb.append("<rect\r\n");
        sb.append(
                "style=\"fill:#00457c;fill-opacity:0.99227798;stroke:#000000;stroke-width:0.30000001;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:0\"\r\n");
        sb.append("id=\"rect4911\"\r\n");
        sb.append("width=\"975\"\r\n");
        sb.append("height=\"61.999996\"\r\n");
        sb.append("x=\"12\"\r\n");
        sb.append("y=\"215.99997\" /> ");
        return sb.toString();
    }
    public String getWeekendRectangle(int day, int month) {
        int x = (month * 75) + 12;
        int y = (day * 15) - 264;

        StringBuilder sb = new StringBuilder();
        sb.append("<rect ");
        sb.append(
                " fill:#009ff3;fill-opacity:1;stroke:#000000;stroke-width:0.30000001;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"");
        sb.append(" x=\"" + x + "\"");
        sb.append(" y=\"" + y + "\"");
        sb.append(" width=\"75\"");
        sb.append(" height=\"15\"");
        sb.append("/>");
        return sb.toString();
    }

}

package de.itemis.calender;

import java.io.File;
import java.time.YearMonth;
import java.io.IOException; //Import to handle errors
import java.io.PrintWriter;

public class Calender {

    public static void main(String[] args) {

        //read user input
        int year;
        //var scan = new Scanner(System.in);
        //System.out.println("F�r welches Jahr soll der Kalender erstellt werden? "); //side quest: figure out "encoding utf8 module"
        //year = scan.nextInt();
        year = 2023;

        GeneralPainter generalPainter = new GeneralPainter();

        try {
            File file = new File("calender.Calender.svg");

            file.createNewFile();
            // create new file granted no one exists

            PrintWriter pw = new PrintWriter(file); //
            // pw = new PrintWriter(System.out);

            pw.println(generalPainter.appendProps("<svg"));

            pw.println(generalPainter.appendTransform());
            pw.println(generalPainter.appendHeader());


            final YearMonth firstMonth = YearMonth.of(year, 1);
            for (int month = 0; month < 13; month++) {
                // Map months of value above 12 to next year
                YearMonth currentMonth = firstMonth.plusMonths(month);
                int currentX = ((month) * 75) + 12;
                var currentMonthObject = new Month(currentMonth, currentX, currentMonth.getYear() == year); // var = lokale Typinferenz
                pw.println(currentMonthObject.drawSVGForMonth());

            }

            pw.println(generalPainter.appendFooter());
            pw.println("</svg>");
            pw.close();
            System.out.println("File was successfully created at " + file.getCanonicalPath());
        }



        catch (IOException e) {
            e.printStackTrace();
        }

    }

}

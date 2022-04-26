
import java.io.File;
import java.util.Scanner; //Import File class
import java.io.IOException; //Import to handle errors
import java.io.PrintWriter;
import java.time.LocalDate;

public class Calender {

    public static void main(String[] args) {
        Painter painter = new Painter();
        int year = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("F�r welches Jahr soll der Kalender erstellt werden? ");
        year = scan.nextInt();
        try {
            File file = new File("Calender.svg");

            file.createNewFile();
            // create new file granted no one exists

            PrintWriter pw = new PrintWriter(file); //
            // pw = new PrintWriter(System.out);

            pw.println(painter.appendProps("<svg"));

            pw.println(painter.appendTransform());
            pw.println(painter.appendHeader());



            for (int month = 1; month < 14; month++) {
                // Map months of value above 12 to next year
                LocalDate firstOfMonth = month <= 12 ? LocalDate.of(year,month,1): LocalDate.of(year+1,month-12,1);
                for(int day = 0; day<firstOfMonth.lengthOfMonth(); day++){
                    LocalDate tag = LocalDate.of(firstOfMonth.getYear(),firstOfMonth.getMonth(),day+1);
                    pw.println(painter.appendRect(tag,year==tag.getYear()));
                }
            }

            pw.println(painter.appendFooter());
            pw.println("</svg>");
            pw.close();
            System.out.println("File was successfully created at " + file.getCanonicalPath());
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

}

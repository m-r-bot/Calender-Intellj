package de.itemis.calender;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Holiday {
    private HashMap<LocalDate,String> holidays;

    public Holiday(){
        this.holidays = new HashMap<>();
        this.readFromCSV();
    }
    public void readFromCSV(){
        try {
            Scanner scanner = new Scanner(new File("src/feiertage.csv"));
            // skip .csv header
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String[] keyValPair = scanner.nextLine().split(",");
                LocalDate date = LocalDate.parse(keyValPair[0], DateTimeFormatter.ISO_DATE);
                String holiday = keyValPair[1];
                holidays.put(date,holiday);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isFeiertag(LocalDate date){
        return this.holidays.containsKey(date);
    }
    public String getFeiertag(LocalDate date){
        return this.holidays.get(date);
    }

}

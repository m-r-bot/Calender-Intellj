package de.itemis.calender;

import java.time.LocalDate;

public class Feiertag {
    private String name;
    private boolean belongsToSachsen;
    LocalDate date;

    public Feiertag(String name, boolean belongsToSachsen, LocalDate date){
        this.name = name;
        this.belongsToSachsen = belongsToSachsen;
        this.date = date;
    }
}

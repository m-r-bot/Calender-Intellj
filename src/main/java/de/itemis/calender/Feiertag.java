package de.itemis.calender;

import java.time.LocalDate;
import java.util.*;

public class Feiertag {
    private String name;
    private boolean belongsToSachsen;
    LocalDate date;

    public Feiertag(String name, boolean belongsToSachsen, LocalDate date){
        this.name = name;
        this.belongsToSachsen = belongsToSachsen;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isBelongsToSachsen(LocalDate date) {
        List sachsenHolidays = new ArrayList();

        final String BW = "Baden-Württemberg";
        final String BY = "Bayern";
        final String ST = "Sachsen-Anhalt";
        final String BB = "Brandenburg";
        final String HE = "Hessen";
        final String NW = "Nordrhein-Westfalen";
        final String RP = "Rheinland-Pfalz";
        final String SL = "Saarland";
        final String BE = "Berlin";
        final String HB = "Bremen";
        final String HH = "Hamburg";
        final String SN = "Sachsen";
        final String TH = "Thüringen";
        final String SH = "Schleswig-Holstein";
        final String MV = "Mecklenburg-Vorpommern";
        final String NV = "Nordrhein-Westfalen";
        final String NI = "Niedersachsen";

        // Regions of local holidays.
        Map<String, List<String>> locHolidaysRegions = new HashMap<>();
        locHolidaysRegions.put("Heilige Drei Könige", Arrays.asList(BW, BY, ST));
        locHolidaysRegions.put("Frauen Tag", Arrays.asList(BE));
        locHolidaysRegions.put("Buß- und Bettag", Arrays.asList(SN));
        locHolidaysRegions.put("Weltkindertag", Arrays.asList(TH));
        locHolidaysRegions.put("Ostersonntag", Arrays.asList(BB));
        locHolidaysRegions.put("Pfingstsonntag", Arrays.asList(BB));
        locHolidaysRegions.put("Fronleichnam", Arrays.asList(BW, BY, HE, ST, NW, RP, SL));
        locHolidaysRegions.put("Mariä Himmelfahrt", Arrays.asList(SL));
        locHolidaysRegions.put("Reformationstag", Arrays.asList(BB, HE, HB, HH, MV, NI, SN, ST, SH, TH));
        locHolidaysRegions.put("Allerheiligen", Arrays.asList(BW, BY, NV, RP, SL));

       //sachsenHolidays.forEach(LocalDate date);
       if (locHolidaysRegions.containsValue(SN))
           belongsToSachsen = true;
       else
           belongsToSachsen = false;
        return belongsToSachsen;
    }
}

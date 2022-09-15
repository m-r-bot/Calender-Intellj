package de.itemis.calender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
// given year
// calculate the holidays only as such -> it's only of interest that x days is calender.Feiertag and it is called Y
// holidys is either sachsen sp. or non-sachsen sp.

public class Holidays {

    //initializes an ArrayList that stores values of type Feiertag as defined in class Feiertag
    List<Feiertag> holidays = new ArrayList<>();

    public Holidays(int year){
        //adds statically stored German holidays to holiday list for the current and following year
        holidays.addAll(determineGermanHolidays(year));
        holidays.addAll(determineGermanHolidays(year+1));
    }

        //determines if given date is a holiday
    public boolean isHoliday(LocalDate date){
        Optional<Feiertag> optionalHoliday = this.holidays.stream().filter( (item) -> item.date.equals(date)).findAny();
        return optionalHoliday.isPresent();
    }

    public boolean isSachsenHoliday(LocalDate date){
        Optional<Feiertag> optionalHoliday = this.holidays.stream().filter( (item) -> item.date.equals(date)).findAny();
        //als ob es keinen einfacheren Weg gibt als da eine doppelkonditon einzubauen
        //optionalSachsenHoliday = this.determineGermanHolidays(date.getYear()).stream().filter( (item) -> item.)
        Optional <Feiertag> optionalSachsenHoliday = optionalHoliday.filter(new Feiertag(get))
        return optionalSachsenHoliday.isPresent();
    }

        //TODO create boolean belongs to sachsen with locHolidays Hashmap
        public boolean isNotSachsenHoliday(LocalDate date){
            return false;
        }



        public static LocalDate calculateEasterDate(int year) {
            // Gaußsche Osterformel
            final int a = year % 19;
            final int b = year % 4;
            final int c = year % 7;
            final int k = year / 100;
            final int p = k / 3;
            final int q = k / 4;
            final int m = (15 + k - p - q) % 30;
            final int d = (19 * a + m) % 30;
            final int n = (4 + k - q) % 7;
            final int e = (2 * b + 4 * c + 6 * d + n) % 7;
            final int easter = (22 + d + e);
            Month month = easter > 31 ? Month.APRIL : Month.MARCH;
            int day = easter > 31 ? easter - 31 : easter;
            return LocalDate.of(year, month, day);
        }

        /**
         *
         * @param year
         * @return a list of all German Public (legal) holidays for a specific year
         * @implNote If a holiday is cancelled in the future, you can safely delete it
         *           from the function, this won't affect older, already persisted
         *           holidays
         *           however, if a new holiday is declared, it should be handeled
         *           differently (in a new function) where it's explicitly programmed
         *           not to be added
         *           to the past (Before it was declared)
         */
        public List<Feiertag> determineGermanHolidays(int year) {
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

            // Fixed calender.Holidays
            final LocalDate NEUJAHR = LocalDate.of(year, Month.JANUARY, 1);
            final LocalDate HEILIGE_DREI_KOENIGE = LocalDate.of(year, Month.JANUARY, 6);
            final LocalDate FRAUEN_TAG = LocalDate.of(year, Month.MARCH, 8);
            final LocalDate TAG_DER_ARBEIT = LocalDate.of(year, Month.MAY, 1);
            final LocalDate MARIA_HIMMELFAHRT = LocalDate.of(year, Month.AUGUST, 15);
            final LocalDate WELT_KINDER_TAG = LocalDate.of(year, Month.SEPTEMBER, 20);
            final LocalDate TAG_DER_DEUTSCHEN_EINHEIT = LocalDate.of(year, Month.OCTOBER, 3);
            final LocalDate REFORMATIONSTAG = LocalDate.of(year, Month.OCTOBER, 31);
            final LocalDate ALLERHEILIGEN = LocalDate.of(year, Month.NOVEMBER, 1);
            final LocalDate ERSTER_WEIHNACHTSTAG = LocalDate.of(year, Month.DECEMBER, 25);
            final LocalDate ZWEITER_WEIHNACHTSTAG = ERSTER_WEIHNACHTSTAG.plusDays(1);

            // Easter-based calender.Holidays
            final LocalDate OSTER_SONNTAG = calculateEasterDate(year);
            final LocalDate OSTER_MONTAG = OSTER_SONNTAG.plusDays(1);
            final LocalDate KAR_FREITAG = OSTER_SONNTAG.minusDays(2);
            final LocalDate CHRISTI_HIMMEL_FAHRT = OSTER_SONNTAG.plusDays(39);
            final LocalDate PFINGST_SONNTAG = OSTER_SONNTAG.plusDays(49);
            final LocalDate PFINGST_MONTAG = PFINGST_SONNTAG.plusDays(1);
            final LocalDate FRONLEICHNAM = OSTER_SONNTAG.plusDays(60);

            final LocalDate BUSS_UND_BETTAG = LocalDate.of(year, Month.NOVEMBER, 23)
                    .with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));

            // National (federal) holidays, as in 2022
            Map<String, LocalDate> fedHolidays = new HashMap<>();
            fedHolidays.put("Neujahr", NEUJAHR);
            fedHolidays.put("Karfreitag", KAR_FREITAG);
            fedHolidays.put("Ostermontag", OSTER_MONTAG);
            fedHolidays.put("Christi Himmelfahrt", CHRISTI_HIMMEL_FAHRT);
            fedHolidays.put("Pfingstmontag", PFINGST_MONTAG);
            fedHolidays.put("Tag der Arbeit", TAG_DER_ARBEIT);
            fedHolidays.put("Tag der Deutschen Einheit", TAG_DER_DEUTSCHEN_EINHEIT);
            fedHolidays.put("Erster Weihnachtstag", ERSTER_WEIHNACHTSTAG);
            fedHolidays.put("Zweiter Weihnachtstag", ZWEITER_WEIHNACHTSTAG);

            // Date of local holidays.
            Map<String, LocalDate> locHolidays = new HashMap<>();
            locHolidays.put("Heilige Drei Könige", HEILIGE_DREI_KOENIGE);
            locHolidays.put("Frauen Tag", FRAUEN_TAG);
            locHolidays.put("Buß- und Bettag", BUSS_UND_BETTAG);
            locHolidays.put("Weltkindertag", WELT_KINDER_TAG);
            locHolidays.put("Ostersonntag", OSTER_SONNTAG);
            locHolidays.put("Pfingstsonntag", PFINGST_SONNTAG);
            locHolidays.put("Fronleichnam", FRONLEICHNAM);
            locHolidays.put("Mariä Himmelfahrt", MARIA_HIMMELFAHRT);
            locHolidays.put("Reformationstag", REFORMATIONSTAG);
            locHolidays.put("Allerheiligen", ALLERHEILIGEN);

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

            //adds federal holidays to holidays list
            fedHolidays.forEach((feiertag, datum) -> {
                holidays.add(new Feiertag(feiertag,true,datum));
            });

            // TODO : handle Buß- und Bettag // Reformationstag using if statement
            locHolidays.forEach((feiertag, datum) -> {
                holidays.add(new Feiertag(feiertag,false,datum));
            });

            return holidays;
        }

    }


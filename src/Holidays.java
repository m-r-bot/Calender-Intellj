import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
// given year
// calculate the holidays only as such -> it's only of interest that x days is Feiertag and it is called Y
// holidys is either sachsen sp. or non-sachsen sp.

public class Holidays {

    List<Feiertag> feiertage;

    public Holidays(int year){
        List<Feiertag> feiertage = new ArrayList<>();

        feiertage = determineGermanHolidays(year);
        feiertage.addAll(determineGermanHolidays(year+1));
    }

        public boolean isHoliday(LocalDate date){
            Optional<Feiertag> optionalHoliday = this.feiertage.stream().filter( (ft) -> ft.date == date).findFirst();
            return optionalHoliday.isPresent();
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
            final String DE = "Deutschland";
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

            // Fixed Holidays
            final LocalDate NEUJAHR = LocalDate.of(year, Month.JANUARY, 1);
            final LocalDate HEILIGE_DER_DREI_KOENIGE = LocalDate.of(year, Month.JANUARY, 6);
            final LocalDate FRAUEN_TAG = LocalDate.of(year, Month.MARCH, 8);
            final LocalDate TAG_DER_ARBEIT = LocalDate.of(year, Month.MAY, 1);
            final LocalDate MARIA_HIMMELFAHRT = LocalDate.of(year, Month.AUGUST, 15);
            final LocalDate WELT_KINDER_TAG = LocalDate.of(year, Month.SEPTEMBER, 20);
            final LocalDate TAG_DER_DEUTSCHEN_EINHEIT = LocalDate.of(year, Month.OCTOBER, 3);
            final LocalDate REFORMATIONSTAG = LocalDate.of(year, Month.OCTOBER, 31);
            final LocalDate ALLERHEILIGEN = LocalDate.of(year, Month.NOVEMBER, 1);
            final LocalDate ERSTER_WEIHNACHTSTAG = LocalDate.of(year, Month.DECEMBER, 25);
            final LocalDate ZWEITER_WEIHNACHTSTAG = ERSTER_WEIHNACHTSTAG.plusDays(1);

            // Easter-based Holidays
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
            Map<String, LocalDate> bundeseinheitlicheFeiertage = new HashMap<>();
            bundeseinheitlicheFeiertage.put("Neujahr", NEUJAHR);
            bundeseinheitlicheFeiertage.put("Karfreitag", KAR_FREITAG);
            bundeseinheitlicheFeiertage.put("Ostermontag", OSTER_MONTAG);
            bundeseinheitlicheFeiertage.put("Christi Himmelfahrt", CHRISTI_HIMMEL_FAHRT);
            bundeseinheitlicheFeiertage.put("Pfingstmontag", PFINGST_MONTAG);
            bundeseinheitlicheFeiertage.put("Tag der Arbeit", TAG_DER_ARBEIT);
            bundeseinheitlicheFeiertage.put("Tag der Deutschen Einheit", TAG_DER_DEUTSCHEN_EINHEIT);
            bundeseinheitlicheFeiertage.put("Erster Weihnachtstag", ERSTER_WEIHNACHTSTAG);
            bundeseinheitlicheFeiertage.put("Zweiter Weihnachtstag", ZWEITER_WEIHNACHTSTAG);

            // Date of local holidays.
            Map<String, LocalDate> laenderspezifischeFeiertage = new HashMap<>();
            laenderspezifischeFeiertage.put("Heilige Drei Könige", HEILIGE_DER_DREI_KOENIGE);
            laenderspezifischeFeiertage.put("Frauen Tag", FRAUEN_TAG);
            laenderspezifischeFeiertage.put("Buß- und Bettag", BUSS_UND_BETTAG);
            laenderspezifischeFeiertage.put("Weltkindertag", WELT_KINDER_TAG);
            laenderspezifischeFeiertage.put("Ostersonntag", OSTER_SONNTAG);
            laenderspezifischeFeiertage.put("Pfingstsonntag", PFINGST_SONNTAG);
            laenderspezifischeFeiertage.put("Fronleichnam", FRONLEICHNAM);
            laenderspezifischeFeiertage.put("Mariä Himmelfahrt", MARIA_HIMMELFAHRT);
            laenderspezifischeFeiertage.put("Reformationstag", REFORMATIONSTAG);
            laenderspezifischeFeiertage.put("Allerheiligen", ALLERHEILIGEN);

            // Regions of local holidays.
            Map<String, List<String>> laenderspezifischeFeiertageRegionen = new HashMap<>();
            laenderspezifischeFeiertageRegionen.put("Heilige Drei Könige", Arrays.asList(BW, BY, ST));
            laenderspezifischeFeiertageRegionen.put("Frauen Tag", Arrays.asList(BE));
            laenderspezifischeFeiertageRegionen.put("Buß- und Bettag", Arrays.asList(SN));
            laenderspezifischeFeiertageRegionen.put("Weltkindertag", Arrays.asList(TH));
            laenderspezifischeFeiertageRegionen.put("Ostersonntag", Arrays.asList(BB));
            laenderspezifischeFeiertageRegionen.put("Pfingstsonntag", Arrays.asList(BB));
            laenderspezifischeFeiertageRegionen.put("Fronleichnam", Arrays.asList(BW, BY, HE, ST, NW, RP, SL));
            laenderspezifischeFeiertageRegionen.put("Mariä Himmelfahrt", Arrays.asList(SL));
            laenderspezifischeFeiertageRegionen.put("Reformationstag",
                    Arrays.asList(BB, HE, HB, HH, MV, NI, SN, ST, SH, TH));
            laenderspezifischeFeiertageRegionen.put("Allerheiligen", Arrays.asList(BW, BY, NV, RP, SL));

            List<Feiertag> feiertage = new ArrayList<>();

            bundeseinheitlicheFeiertage.forEach((feiertag, datum) -> {
                feiertage.add(new Feiertag(feiertag,true,datum));
            });

            // TODO : handle Buß- und Bettag // Reformationstag
            laenderspezifischeFeiertage.forEach((feiertag, datum) -> {
                feiertage.add(new Feiertag(feiertag,false,datum));
            });

            return feiertage;
        }
    }


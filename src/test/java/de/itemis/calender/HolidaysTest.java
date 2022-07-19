package de.itemis.calender;

import de.itemis.calender.Holidays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//JUnit4 easier for learning but here structure better

public class HolidaysTest {
    @Test
    public void testcalculateEasterDate2022(){
        var easterDate = Holidays.calculateEasterDate(2022);

        Assertions.assertEquals(17, easterDate.getDayOfMonth());
        Assertions.assertEquals(4, easterDate.getMonthValue());
    }

    @Test
    public void testcalculateEasterDate2021(){
        var easterDate = Holidays.calculateEasterDate(2021);

        Assertions.assertEquals(4, easterDate.getDayOfMonth());
        Assertions.assertEquals(4, easterDate.getMonthValue());
    }
}

import static org.junit.jupiter.api.Assertions.*;

import calender.Holidays;
import org.junit.jupiter.api.Test;
//JUnit4 easier for learning but here structure better

public class HolidaysTest {
    @Test
    public void testcalculateEasterDate2022(){
        var easterDate = Holidays.calculateEasterDate(2022);

        assertEquals(17, easterDate.getDayOfMonth());
        assertEquals(4, easterDate.getMonthValue());
    }

    @Test
    public void testcalculateEasterDate2021(){
        var easterDate = Holidays.calculateEasterDate(2021);

        assertEquals(4, easterDate.getDayOfMonth());
        assertEquals(4, easterDate.getMonthValue());
    }
}

import calender.HolidayStripes;
import org.junit.jupiter.api.Test;

public class HolidayStripesTest {

    @Test
    public void testColour() {
        var String = HolidayStripes.ColourStripes(2022);

        assertEquals(yellow, HolidayStripes.ColourStripes(2022));
    }

    @Test
    public void testFederalState() {

        assertEquals(SN, HolidayStripes.getFederalState);
    }
}

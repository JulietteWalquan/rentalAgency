package util;

import java.util.Calendar;

public class TimeProvider {

    /**
     * Get the current year from the java.util.Calendar class
     *
     * @return the current year
     */
    public static int currentYearValue() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}

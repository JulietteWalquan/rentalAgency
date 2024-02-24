package utils;

import java.util.Calendar;

public class TimeProvider {

    private TimeProvider() {
        // private constructor to hide the implicit public one
    }

    /**
     * Get the current year from the java.util.Calendar class
     *
     * @return the current year
     */
    public static int currentYearValue() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}

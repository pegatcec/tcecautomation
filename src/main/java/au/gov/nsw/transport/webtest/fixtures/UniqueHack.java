package au.gov.nsw.transport.webtest.fixtures;

public class UniqueHack {

    private static String unique = null;


    public static String getUnqiue() {
        if (unique == null) {
            unique = String.valueOf(System.currentTimeMillis());
        }
        return unique;
    }

    private UniqueHack() {
    }
}

package co.edu.udea.certificacion.caso13.caso13.utils;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static String uniqueEmail(String prefix) {
        long timestamp = System.currentTimeMillis();
        return prefix + "." + timestamp + "@testmail.com";
    }
}

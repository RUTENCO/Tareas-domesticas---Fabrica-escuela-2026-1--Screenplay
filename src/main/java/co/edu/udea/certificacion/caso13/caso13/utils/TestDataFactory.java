package co.edu.udea.certificacion.caso13.caso13.utils;

import co.edu.udea.certificacion.caso13.caso13.models.AuthUserData;

public final class TestDataFactory {

    // Business field constants
    public static final String DEFAULT_USER_NAME = "QA Automation";
    public static final String SEED_USER_NAME = "Seed User";
    public static final String DEFAULT_PASSWORD = "Abcd1234!";
    public static final String TEST_EMAIL_DOMAIN = "@testmail.com";

    private TestDataFactory() {
    }

    public static String uniqueEmail(String prefix) {
        long timestamp = System.currentTimeMillis();
        return prefix + "." + timestamp + TEST_EMAIL_DOMAIN;
    }

    public static AuthUserData createDefaultUserData(String email) {
        return new AuthUserData(DEFAULT_USER_NAME, email, DEFAULT_PASSWORD);
    }

    public static AuthUserData createSeedUserData(String email) {
        return new AuthUserData(SEED_USER_NAME, email, DEFAULT_PASSWORD);
    }

    public static AuthUserData createUserWithData(String name, String email, String password) {
        return new AuthUserData(name, email, password);
    }

    public static String getDefaultPassword() {
        return DEFAULT_PASSWORD;
    }

    public static String getDefaultUserName() {
        return DEFAULT_USER_NAME;
    }
}

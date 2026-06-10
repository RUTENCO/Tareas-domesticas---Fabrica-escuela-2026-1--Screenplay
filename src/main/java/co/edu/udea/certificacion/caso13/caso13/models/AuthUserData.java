package co.edu.udea.certificacion.caso13.caso13.models;

public class AuthUserData {

    private final String name;
    private final String email;
    private final String password;

    public AuthUserData(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

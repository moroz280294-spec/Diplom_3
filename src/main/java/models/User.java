package models;

public class User {
    private String email;
    private String password;
    private String firstName;
    private String accessToken;

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public User withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}

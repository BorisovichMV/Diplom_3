package entities;

public class UserLoginModel {
    private String password;
    private String email;

    public UserLoginModel(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public static UserLoginModel fromUser(User user) {
        return new UserLoginModel(user.getPassword(), user.getEmail());
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

import com.google.gson.Gson;

public class LoginRequest {
    private String email;
    private String password;
    private String name;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public java.lang.String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

import com.google.gson.Gson;

public class ChangeUserRequest {
    private String email;
    private String name;

    public ChangeUserRequest() {
    }

    public ChangeUserRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

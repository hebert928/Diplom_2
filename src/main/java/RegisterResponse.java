public class RegisterResponse {
    private boolean success;
    private String accessToken;

    public RegisterResponse(boolean success, String accessToken) {
        this.success = success;
        this.accessToken = accessToken;
    }

    public RegisterResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

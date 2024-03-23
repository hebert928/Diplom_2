import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class UserLoginTest extends BaseTest{

    @Test
    public void checkUserLoginSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        createUser(registerRequest);
        Response response = loginUser(new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getName()));
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
        String accessToken = response.as(RegisterResponse.class).getAccessToken();
        deleteUser(accessToken);
    }

    @Test
    public void checkUserLoginWithWrongData() {
        LoginRequest loginRequest = new LoginRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response response = loginUser(loginRequest);
        response.then().assertThat().body("success", is(false)).and().statusCode(401);
    }
}

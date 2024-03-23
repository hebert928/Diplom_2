import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class ChangeUserDataTest extends BaseTest{

    @Test
    public void checkChangeAuthorizedUserDataSuccess(){
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response createUserResponse = createUser(registerRequest);
        String accessToken = createUserResponse.as(RegisterResponse.class).getAccessToken();
        ChangeUserRequest changeUserRequest = new ChangeUserRequest("stellars2025@gmail.com", "BBurger");
        Response response = changeUserData(changeUserRequest, accessToken);
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
        deleteUser(accessToken);
    }

    @Test
    public void checkChangeUnauthorizedUserDataError() {
        ChangeUserRequest changeUserRequest = new ChangeUserRequest("stellars2025@gmail.com", "BBurger");
        Response response = changeUserData(changeUserRequest, "fakeToken");
        response.then().assertThat().body("success", is(false)).and().statusCode(401);
    }
}

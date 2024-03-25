import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class UserCreateTest extends BaseTest{

    @Test
    public void checkUserCreateSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response response = createUser(registerRequest);
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
        setAccessToken(response.as(RegisterResponse.class).getAccessToken());
    }

    @Test
    public void checkCreateTheSameUserForbidden() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response response = createUser(registerRequest);
        setAccessToken(response.as(RegisterResponse.class).getAccessToken());

        createUser(registerRequest)
                .then()
                .assertThat()
                .body("success", is(false))
                .and()
                .statusCode(403);
    }

    @Test
    public void checkCreateUserWithoutPasswordFieldForbidden() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", null, "everBurger");
        Response response = createUser(registerRequest);

        response
                .then()
                .assertThat()
                .body("success", is(false))
                .and()
                .statusCode(403);
    }
}

import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class GetUserOrdersTest extends BaseTest{

    @Test
    public void checkGetAuthorizedUserOrdersSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response createUserResponse = createUser(registerRequest);
        String accessToken = createUserResponse.as(RegisterResponse.class).getAccessToken();
        IngredientsRequest ingredientsRequest = new IngredientsRequest(new String[] {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa73"});
        createOrder(ingredientsRequest, accessToken);
        Response response = getOrders(accessToken);
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
        deleteUser(accessToken);
    }

    @Test
    public void checkGetUnauthorizedUserOrdersUnauthorized() {
        IngredientsRequest ingredientsRequest = new IngredientsRequest(new String[] {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa73"});
        createOrder(ingredientsRequest, "fakeToken");
        Response response = getOrders("fakeToken");
        response.then().assertThat().body("success", is(false)).and().statusCode(401);
    }
}

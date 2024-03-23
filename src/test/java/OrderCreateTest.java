import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class OrderCreateTest extends BaseTest{

    @Test
    public void checkCreateOrderAuthorizedUserWithIngredientsSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response createUserResponse = createUser(registerRequest);
        String accessToken = createUserResponse.as(RegisterResponse.class).getAccessToken();
        IngredientsRequest ingredientsRequest = new IngredientsRequest(new String[] {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa73"});
        Response response = createOrder(ingredientsRequest, accessToken);
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
        deleteUser(accessToken);
    }

    @Test
    public void checkCreateOrderUnauthorizedUserWithIngredientsSuccess() {
        IngredientsRequest ingredientsRequest = new IngredientsRequest(new String[] {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f", "61c0c5a71d1f82001bdaaa73"});
        Response response = createOrder(ingredientsRequest, "fakeToken");
        response.then().assertThat().body("success", is(true)).and().statusCode(200);
    }

    @Test
    public void checkCreateOrderWithoutIngredientsBadRequest() {
        IngredientsRequest ingredientsRequest = new IngredientsRequest();
        Response response = createOrder(ingredientsRequest, "fakeToken");
        response.then().assertThat().body("success", is(false)).and().statusCode(400);
    }

    @Test
    public void checkCreateOrderWithWrongIngredientsHashError() {
        IngredientsRequest ingredientsRequest = new IngredientsRequest(new String[] {"61c0c5a71d1f82001bdaa", "61c0c5a71d1f82001bdaaa7"});
        Response response = createOrder(ingredientsRequest, "fakeToken");
        response.then().assertThat().statusCode(500);
    }
}

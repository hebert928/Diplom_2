import io.restassured.response.Response;
import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.is;

public class GetUserOrdersTest extends BaseTest{

    @Test
    public void checkGetAuthorizedUserOrdersSuccess() {
        RegisterRequest registerRequest = new RegisterRequest("stellars2024@gmail.com", "tuman1&", "everBurger");
        Response createUserResponse = createUser(registerRequest);
        setAccessToken(createUserResponse.as(RegisterResponse.class).getAccessToken());
        IngredientsResponse ingredientsResponse = getIngredientList().as(IngredientsResponse.class);
        IngredientsRequest ingredientsRequest = new IngredientsRequest(Arrays.stream(ingredientsResponse.getIdList()).limit(3).toArray(String[]::new));
        createOrder(ingredientsRequest, getAccessToken());
        Response response = getOrders(getAccessToken());

        response.then().assertThat().body("success", is(true)).and().statusCode(200);
    }

    @Test
    public void checkGetUnauthorizedUserOrdersUnauthorized() {
        IngredientsResponse ingredientsResponse = getIngredientList().as(IngredientsResponse.class);
        IngredientsRequest ingredientsRequest = new IngredientsRequest(Arrays.stream(ingredientsResponse.getIdList()).limit(3).toArray(String[]::new));
        createOrder(ingredientsRequest, "fakeToken");
        Response response = getOrders("fakeToken");

        response.then().assertThat().body("success", is(false)).and().statusCode(401);
    }
}

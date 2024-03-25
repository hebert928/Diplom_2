import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import static io.restassured.RestAssured.given;

public class BaseTest {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isAccessToken(){
        return accessToken != null;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    protected void printResponse(Response response) {
        System.out.println(response.body().asString());
    }

    @Step("Send POST request to api/auth/register")
    public Response createUser(RegisterRequest registerRequest){
        Response response = given()
                .header("Content-type", "application/json")
                .body(registerRequest)
                .post(Endpoints.REGISTER);

        printResponse(response);

        return response;
    }

    @Step("Send POST request to /api/auth/login")
    public Response loginUser(LoginRequest loginRequest) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post(Endpoints.LOGIN);
        return response;
    }

    @Step("Send DELETE request to /api/auth/user")
    public void deleteUser(String accessToken) {
        given().header("Authorization", accessToken).delete(Endpoints.USER);
    }

    @Step("Send PATCH request to /api/auth/user")
    public Response changeUserData(ChangeUserRequest changeUserRequest, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(changeUserRequest)
                .patch(Endpoints.USER);
        printResponse(response);
        return response;
    }

    @Step("Send POST request to /api/orders")
    public Response createOrder(IngredientsRequest ingredientsRequest, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(ingredientsRequest)
                .post(Endpoints.ORDERS);

        printResponse(response);

        return response;
    }

    @Step("Send GET request to /api/orders")
    public Response getOrders(String accessToken) {
        Response response = given()
                .header("Authorization", accessToken)
                .get(Endpoints.ORDERS);

        printResponse(response);

        return response;
    }

    @Step("Send GET request to /api/ingredients")
    public Response getIngredientList() {
        Response response = given()
                .header("Content-type", "application/json")
                .get(Endpoints.INGREDIENTS);
        return response;
    }

    @After
    public void clear() {
        if(isAccessToken()) {
            deleteUser(getAccessToken());
        }
    }
}




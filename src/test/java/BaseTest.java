import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import static io.restassured.RestAssured.given;

public class BaseTest {

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
                .post("api/auth/register");

        printResponse(response);

        return response;
    }

    @Step("Send POST request to /api/auth/login")
    public Response loginUser(LoginRequest loginRequest) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginRequest)
                .post("/api/auth/login");
        return response;
    }

    @Step("Send DELETE request to /api/auth/user")
    public void deleteUser(String accessToken) {
        given().header("Authorization", accessToken).delete("/api/auth/user");
    }

    @Step("Send PATCH request to /api/auth/user")
    public Response changeUserData(ChangeUserRequest changeUserRequest, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(changeUserRequest)
                .patch("/api/auth/user");
        printResponse(response);
        return response;
    }

    @Step("Send POST request to /api/orders")
    public Response createOrder(IngredientsRequest ingredientsRequest, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(ingredientsRequest)
                .post("/api/orders");

        printResponse(response);

        return response;
    }

    @Step("Send GET request to /api/orders")
    public Response getOrders(String accessToken) {
        Response response = given()
                .header("Authorization", accessToken)
                .get("/api/orders");

        printResponse(response);

        return response;
    }
}




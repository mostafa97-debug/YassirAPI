package  Tests;
import Pojo.Product;
import Util.AuthUtil;
import Util.BaseTest;
import Util.ConfigReader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTests extends BaseTest {

    @Test(priority = 1, description = "Verify 'Get auth user' endpoint with a valid token")
    public void getAuthUser_withValidToken_verifyStatusCodeAndBody() {
        String token = AuthUtil.getAuthToken();
        String expectedUsername = ConfigReader.getProperty("auth.username");
        String expectedEmail = ConfigReader.getProperty("auth.email");

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("username", equalTo(expectedUsername))
                .body("email", equalTo(expectedEmail));
    }

    @Test(priority = 2, description = "Verify 'Get auth user' endpoint without a token")
    public void getAuthUser_withoutToken_verifyStatusCodeAndBody() {
        given()
                .when()
                .get("/auth/me")
                .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Access Token is required"));
    }

    @Test(priority = 3, description = "Verify 'Get auth user' endpoint with an invalid token")
    public void getAuthUser_withInvalidToken_verifyStatusCodeAndBody() {
        String invalidToken = "invalid_token_123";

        given()
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Invalid/Expired Token!"));
    }

    @Test(priority = 4, description = "Verify 'Add a new product' endpoint with a valid token")
    public void addProduct_withValidToken_verifyStatusCodeAndBody() {
        String token = AuthUtil.getAuthToken();
        Product newProduct = new Product("BMW Pencil");

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(newProduct)
                .when()
                .post("/auth/products/add")
                .then()
                .statusCode(201)
                .body("title", equalTo("BMW Pencil"));
    }

}
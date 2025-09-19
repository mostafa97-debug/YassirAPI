package Tests;

import Util.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTests extends BaseTest {

    @Test(priority = 1, description = "Verify status code of all products endpoint")
    public void getAllProducts_verifyStatusCode() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2, description = "Verify response body contains products array and correct fields")
    public void getAllProducts_verifyResponseBody() {
        given()
                .when()
                .get("/products")
                .then()
                .contentType(ContentType.JSON)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0))
                .body("total", equalTo(194)); // Updated based on your request
    }

    @Test(priority = 3, description = "Verify successful request for a single product by ID")
    public void getProductById_verifyStatusCodeAndBody() {
        int productId = 1;
        given()
                .pathParam("id", productId)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(productId))
                .body("title", equalTo("Essence Mascara Lash Princess"))
                .body("price", equalTo(9.99F));
    }

    @Test(priority = 4, description = "Verify request with an invalid product ID returns 404")
    public void getProductById_withInvalidId_verifyStatusCodeAndBody() {
        int invalidId = 9999;
        given()
                .pathParam("id", invalidId)
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Product with id '9999' not found"));
    }

    @Test(priority = 5, description = "Verify successful search for products")
    public void searchProducts_verifyStatusCodeAndResults() {
        String query = "phone";
        given()
                .queryParam("q", query)
                .when()
                .get("/products/search")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("products.size()", greaterThan(0))
                .body("products.title", everyItem(isA(String.class)));
    }
}
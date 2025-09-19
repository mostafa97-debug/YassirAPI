package Util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class AuthUtil {

    private static String authToken;

    public static String getAuthToken() {
        if (authToken == null) {
            loginAndGetToken();
        }
        return authToken;
    }

    private static void loginAndGetToken() {
        Map<String, String> body = new HashMap<>();
        body.put("username", "emilys");
        body.put("password", "emilyspass");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(body)
                .post("/auth/login");

        if (response.statusCode() == 200) {
            authToken = response.jsonPath().getString("accessToken");
            System.out.println("Authentication successful. Token retrieved: " + authToken);
        } else {
            System.err.println("Authentication failed. Status code: " + response.statusCode());
            System.err.println("Response body: " + response.asString());
            throw new RuntimeException("Failed to retrieve authentication token.");
        }
    }
}
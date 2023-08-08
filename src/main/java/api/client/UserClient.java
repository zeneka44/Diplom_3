package api.client;

import com.google.gson.Gson;
import api.data.User;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserClient {
    public static final String AUTH_ENDPOINT = "/api/auth";
    public static final String CREATE_USER_ENDPOINT = AUTH_ENDPOINT + "/register";
    public static final String LOGIN_USER_ENDPOINT = AUTH_ENDPOINT + "/login";
    public static final String GET_USER_ENDPOINT = AUTH_ENDPOINT + "/user";


    public static Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT);
    }

    public static Response login(String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(new Gson().toJson(map))
                .when()
                .post(LOGIN_USER_ENDPOINT);
    }

    public static String getAccessToken(String email, String password) {
        return login(email, password)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract().body().path("accessToken");
    }

    public static Response delete(String email, String password) {
        return given()
                .header("Authorization", getAccessToken(email, password))
                .and()
                .when()
                .delete(GET_USER_ENDPOINT);
    }
}

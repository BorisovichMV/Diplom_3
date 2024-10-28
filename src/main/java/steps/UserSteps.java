package steps;

import entities.User;
import entities.UserLoginModel;
import entities.UserRegistrationModel;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static steps.RequestSteps.sendAuthorizedRequest;
import static steps.RequestSteps.sendRequest;

public class UserSteps {
    @Step("Отправляем запрос на создание пользователя")
    public static void createUser(User user) {
        Response response = sendRequest("POST", UserRegistrationModel.fromUser(user), "/auth/register", 200);
        rememberTokens(user, response.jsonPath());
    }

    @Step("Отправляем запрос на вход пользователя")
    public static void loginUser(User user) {
        UserLoginModel model = UserLoginModel.fromUser(user);
        Response response = sendRequest("POST", model, "/auth/login", 200);
        rememberTokens(user, response.jsonPath());
    }

    @Step("Запоминаем токены")
    public static void rememberTokens(User user, JsonPath jsonPath) {
        String accessToken = jsonPath.getString("accessToken");
        String refreshToken = jsonPath.getString("refreshToken");
        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshToken);
    }

    @Step("Удаляем пользователя")
    public static void deleteUser(User user) {
        sendAuthorizedRequest(user, "DELETE", user, "/auth/user", 202);
    }

}

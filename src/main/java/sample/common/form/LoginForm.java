package sample.common.form;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank(message = "ユーザIDを入力してください")
    private String userId;

    @NotBlank(message = "パスワードを入力してください")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
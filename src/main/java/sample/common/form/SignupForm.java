package sample.common.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupForm {

    @NotBlank(message = "ユーザIDを入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ユーザIDは半角英数字で入力してください")
    private String userId;

    @NotBlank(message = "パスワードを入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字で入力してください")
    @Size(min = 8, message = "パスワードは8桁以上で入力してください")
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
package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sample.common.service.LoginService;

@Controller
public class HomePageController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String top() {
        return "homePage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            Model model) {

        if (userId == null || userId.isBlank()) {
            model.addAttribute("message", "ユーザIDを入力してください");
            return "signup";
        }

        if (password == null || password.isBlank()) {
            model.addAttribute("message", "パスワードを入力してください");
            return "signup";
        }

        if (!userId.matches("^[a-zA-Z0-9]+$")) {
            model.addAttribute("message", "ユーザIDは半角英数字で入力してください");
            return "signup";
        }

        if (!password.matches("^[a-zA-Z0-9]+$")) {
            model.addAttribute("message", "パスワードは半角英数字で入力してください");
            return "signup";
        }

        if (password.length() < 8) {
            model.addAttribute("message", "パスワードは8桁以上で入力してください");
            return "signup";
        }

        boolean result = loginService.signup(userId, password);

        if (!result) {
            model.addAttribute("message", "このユーザ名はすでに使われています");
            return "signup";
        }

        return "menu";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        if (userId == null || userId.isBlank()) {
            model.addAttribute("message", "ユーザIDを入力してください");
            return "login";
        }

        if (password == null || password.isBlank()) {
            model.addAttribute("message", "パスワードを入力してください");
            return "login";
        }

        boolean result = loginService.login(userId, password);

        if (result) {
            session.setAttribute("loginUsername", userId);
            return "redirect:/tasks";
        } else {
            model.addAttribute("message", "ユーザー名またはパスワードが違います");
            return "login";
        }
    }
}
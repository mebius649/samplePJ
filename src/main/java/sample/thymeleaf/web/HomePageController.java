package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sample.common.form.LoginForm;
import sample.common.form.SignupForm;
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
            @Valid SignupForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        boolean result = loginService.signup(form.getUserId(), form.getPassword());

        if (!result) {
            model.addAttribute("message", "このユーザ名はすでに使われています");
            return "signup";
        }

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(
            @Valid LoginForm form,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        boolean result = loginService.login(form.getUserId(), form.getPassword());

        if (result) {
            session.setAttribute("loginUsername", form.getUserId());
            return "redirect:/tasks";
        } else {
            model.addAttribute("message", "ユーザー名またはパスワードが違います");
            return "login";
        }
    }
    }

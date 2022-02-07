package ch.bbw.pr.sospri;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginLogoutController {
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        return "logout";
    }




}

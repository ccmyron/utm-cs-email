package md.utm.security.controller;

import lombok.RequiredArgsConstructor;
import md.utm.security.model.User;
import md.utm.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userService.findAllUsers();

        model.addAttribute("users", users);
        return "users";
    }
}

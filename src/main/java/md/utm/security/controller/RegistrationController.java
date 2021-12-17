package md.utm.security.controller;

import lombok.RequiredArgsConstructor;
import md.utm.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           RedirectAttributes attributes) {

        // Send confirmation email
        userService.registerUser(name, email);

        // Prepare data to show on the screen
        attributes.addFlashAttribute("name", name);
        attributes.addFlashAttribute("email", email);
        return "redirect:/register/success";
    }

    @GetMapping("/success")
    public String registrationSuccessful(Model model) {
        model.addAttribute("name", model.getAttribute("name"));
        model.addAttribute("email", model.getAttribute("email"));
        return "registrationSuccessful";
    }

    @GetMapping("/confirm")
    public String confirmRegistration(@RequestParam("confirmation-token") String token) {
        try {
            // Confirm registration
            userService.confirmUserRegistration(token);

            // Show to the user that registration confirmation was successful
            return "redirect:/register/confirm/success";
        } catch (Exception ex) {
            return "redirect:/register/confirm/error";
        }
    }

    @GetMapping("/confirm/success")
    public String confirmationSuccessful() {
        return "confirmationSuccessful";
    }

    @GetMapping("/confirm/error")
    public String confirmationUnsuccessful() {
        return "confirmationUnsuccessful";
    }
}

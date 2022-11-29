package com.example.stackoverflow.api.v1;

import com.example.stackoverflow.dto.AddNewUserRequest;
import com.example.stackoverflow.dto.UserDtoToAdmin;
import com.example.stackoverflow.entity.UserEntity;
import com.example.stackoverflow.repository.UserRepository;
import com.example.stackoverflow.service.ServiceImpl.UserNotFoundException;
import com.example.stackoverflow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewHomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String signUp(final Model model) {
        model.addAttribute("user", new UserEntity());

        return "sign_up";
    }

    @GetMapping("/ask_question")
    public String askQuestion() {
        return "question";
    }

    @GetMapping("/logged_in")
    public String signIn() {
        return "success_login";
    }

    @GetMapping("users")
    public String getUsers(Model model) {
        List<UserDtoToAdmin> userEntityList = userService.findAllUsers();
        model.addAttribute("listUsers", userEntityList);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("pageTittle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(AddNewUserRequest request, RedirectAttributes attributes) {
        userService.addUser(request);
        attributes.addFlashAttribute("message", "The user has been saved successfully!");
        return "redirect:/users";
    }

    @PostMapping("/register")
    public String register(AddNewUserRequest request, RedirectAttributes attributes) {
        userService.addUser(request);
        attributes.addFlashAttribute("message", "You have successfully registered!!!");
        return "redirect:/";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        try {
            UserEntity dto = userService.findById(id);
            model.addAttribute("user", dto);
            model.addAttribute("pageTittle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            attributes.addFlashAttribute("message", "Could not find any user with ID " + id);
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            userService.delete(id);
            attributes.addFlashAttribute("message", "The user ID " + id + " has been deleted successfully!");
        } catch (UserNotFoundException e) {
            attributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/admin/login")
    public String login() {
        return "login";
    }


}

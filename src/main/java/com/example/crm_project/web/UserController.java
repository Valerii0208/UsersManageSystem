package com.example.crm_project.web;

import com.example.crm_project.entity.User;
import com.example.crm_project.service.UserService;
import com.example.crm_project.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
    public String showUserList(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String postUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "The user has been saved successful");
        return "redirect:/manageUsers";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User + (ID: " + id + ")");
            return "user_form";

        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The user has been edited successful");
            return "redirect:/manageUsers";
        }
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return "redirect:/manageUsers";
    }
}

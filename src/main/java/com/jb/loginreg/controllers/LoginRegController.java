package com.jb.loginreg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jb.loginreg.models.User;
import com.jb.loginreg.services.UserService;
import com.jb.loginreg.validators.UserValidator;

@Controller
public class LoginRegController {
	private final UserService userService;
	private final UserValidator userValidator;
	public LoginRegController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@RequestMapping("/")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "loginReg.jsp";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirect) {
		userValidator.validate(user, result);
		if(userService.isEmailAlreadyInUse(user.getEmail())) {
			ObjectError emailError = new ObjectError("email", "That email is already being used");
			result.addError(emailError);
		}
		if(userService.isUsernameAlreadyInUse(user.getUsername())) {
			ObjectError usernameError = new ObjectError("username", "That username is already being used");
			result.addError(usernameError);
		}
		if (result.hasErrors()) {
			return "loginReg.jsp";
		}
		else {
			User u = userService.registerUser(user);
			session.setAttribute("userId", u.getId());
			redirect.addFlashAttribute("success", "You have registered and are now on the home page!");
			return "redirect:/home";
		}
	}
	
    @RequestMapping("/login")
    public String loginPage() {
        return "login.jsp";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirect, HttpSession session) {
    	if (userService.authenticateUser(email, password)) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("userId", u.getId());
    		redirect.addFlashAttribute("success", "You have logged in and are now on the home page!");
    		return "redirect:/home";
    	}
    	else {
    		redirect.addFlashAttribute("error", "Invalid Log In. Incorrect Email/Password.");
    		return "redirect:/login";
    	}
    }
    
    @RequestMapping("/home")
    public String home(Model model, HttpSession session, RedirectAttributes redirect) {
    	Long id = (Long) session.getAttribute("userId");
    	if (id == null) {
    		redirect.addFlashAttribute("warning", "Please register or log in before trying to access our site.");
    		return "redirect:/";
    	}
    	else {
    		User user = userService.findUserById(id);
    		model.addAttribute("user", user);
    	}
    	return "home.jsp";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}

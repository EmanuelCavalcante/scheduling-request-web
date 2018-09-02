package com.taskrequestapi.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskrequestapi.models.User;

@Controller
public class UserController {

	
	@RequestMapping("/user/new")
	public String newUser() {
		return "user/RegisterUser";
	}
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String cadastrar(@Valid User cerv,BindingResult result, Model model,RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			model.addAttribute("menssage", "Form error");
			return "user/RegisterUser";
		}
		
		attributes.addFlashAttribute("menssage", "Saved successfully!");
		return "redirect:/user/new";
	}
}

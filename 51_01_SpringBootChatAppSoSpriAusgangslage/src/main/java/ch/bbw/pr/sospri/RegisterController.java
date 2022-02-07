package ch.bbw.pr.sospri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.pr.sospri.member.MemberService;
import ch.bbw.pr.sospri.member.RegisterMember;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * RegisterController
 * @author Peter Rutschmann
 * @version 26.03.2020
 */
@Controller
public class RegisterController {
	@Autowired
	MemberService memberservice;


	@GetMapping("/get-register")
	public String getRequestRegistMembers(Model model) {
		System.out.println("getRequestRegistMembers");
		model.addAttribute("registerMember", new RegisterMember());
		return "register";
	}
	
	@PostMapping("/get-register")
	public String postRequestRegistMembers(@Valid RegisterMember registerMember, BindingResult bindingResult, Model model) {
		System.out.println("postRequestRegistMembers: registerMember");
		if (bindingResult.hasErrors()) {
			if (!registerMember.getPassword().equals(registerMember.getConfirmation())) {
				System.out.println("Password and Confirmation not the same!");
				registerMember.setMessage("Password and Confirmation not the same!");
				return "register";
			}
		}
		if(memberservice.getByUserName(registerMember.getPrename().toLowerCase()
				+"."+registerMember.getLastname().toLowerCase()) != null) {
			System.out.println("User allready exists, choose other first- or lastname.");
			registerMember.setMessage("Username " +
					registerMember.getPrename().toLowerCase()+"."+registerMember.getLastname().toLowerCase()
					+ " allready exists");
			return "register";
		} else if(!bindingResult.hasErrors() && registerMember.getPassword().equals(registerMember.getConfirmation())){
			System.out.println(registerMember);

			//Hier ergänzen
			memberservice.add(registerMember.toMember());
			model.addAttribute("Username", memberservice.getByUserName(registerMember.toMember().getUsername()));
			return "registerconfirmed";
		}
		else{
			return "register";
		}


	}
}
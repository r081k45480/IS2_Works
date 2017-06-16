package app.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.repository.ProjRepo;
import app.repository.UserRepo;
import model.Projekat;
import model.User;

@Controller
@RequestMapping(value={"/users", "/"})
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProjRepo projRepo;
	
	@RequestMapping(value="admin/init", method=RequestMethod.GET)
	public String getuloge(Model m, HttpServletRequest request){
		
		List<String> uloge = Arrays.asList(User.uloge());
		
		request.setAttribute("uloge", uloge);
		m.addAttribute("user", new User());
		return "admin/registracija";
	}
	
	@RequestMapping(value="admin/save", method=RequestMethod.POST)
	public String saveClan(Model m, @ModelAttribute("user") User user, HttpServletRequest r){		
		User manager = Common.getUlogovan(r);
		if(!user.getUloga().equals(User.ulogaManager()))
			user.setManager(manager);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		m.addAttribute("poruka","User je uspesno dodat");
		
		return getuloge(m, r);
	}
	
	@RequestMapping(value={"initIndex", ""}, method=RequestMethod.GET)
	public String initIndex(Model m, HttpServletRequest req,
			@AuthenticationPrincipal org.springframework.security.core.userdetails.User uu){
		String username = uu.getUsername();
		User u = null;
		if(username!=null){
			u = userRepo.findOne(username);
			Common.setUlogovan(req, u);
		} else
			u = Common.getUlogovan(req);
		
		
		List<Projekat> projs = null;
		List<User> radnici = new LinkedList<>();
		
		if(u.getUloga().equals(User.ulogaManager())){
			projs = projRepo.findByManager(u);
			radnici = userRepo.findByManager(u);
		} else {
			projs = projRepo.findByRadnik(u);
		}
		
		m.addAttribute("radnici", radnici);
		m.addAttribute("projs", projs);
		
		return "initIndex";
	}
	/*
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		model.setViewName("login");

		return model;

	}*/
	
	@RequestMapping(value = {"logout"}, method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    Common.setUlogovan(request, null);
	    
		return "index";

	}

}

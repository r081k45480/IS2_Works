package app.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.ProjRepo;
import app.repository.UserRepo;
import model.Projekat;
import model.User;

@Controller
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProjRepo projRepo;
	
	@RequestMapping(value="init", method=RequestMethod.GET)
	public String getuloge(Model m, HttpServletRequest request){
		
		List<String> uloge = Arrays.asList(User.uloge());
		
		request.setAttribute("uloge", uloge);
		m.addAttribute("user", new User());
		return "registracija";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveClan(Model m, @ModelAttribute("user") User user, HttpServletRequest r){		
		User manager = Common.getUlogovan(r);
		if(!user.getUloga().equals(User.ulogaManager()))
			user.setManager(manager);
		
		userRepo.save(user);
		m.addAttribute("poruka","User je uspesno dodat");
		
		return getuloge(m, r);
	}
	
	@RequestMapping(value="initIndex", method=RequestMethod.GET)
	public String initIndex(Model m, HttpServletRequest req, String username){
		User u;
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
}

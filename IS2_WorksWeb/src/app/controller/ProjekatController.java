package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.ProjRepo;
import model.Projekat;
import model.User;

@Controller
@RequestMapping(value="/proj")
public class ProjekatController {
	
	@Autowired
	ProjRepo projRepo;
	
	@RequestMapping(value="init", method=RequestMethod.GET)
	public String getuloge(Model m, HttpServletRequest request){
		m.addAttribute("proj", new Projekat());
		return "newProj";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveClan(Model m, @ModelAttribute("proj") Projekat proj, HttpServletRequest req){
		System.out.println("Saving new user...");
		
		User user = Common.getUlogovan(req);
		
		proj.setManager(user);
		projRepo.save(proj);
		m.addAttribute("poruka","Projekat je uspesno dodat");
		return getuloge(m, req);
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	@RequestMapping(value="showproj", method=RequestMethod.GET)
	public String showproj(Model m, Integer id){
		Projekat p = projRepo.findOne(id);
		
		
		
		m.addAttribute("proj", p);
		
		return "showProj";
	}
}

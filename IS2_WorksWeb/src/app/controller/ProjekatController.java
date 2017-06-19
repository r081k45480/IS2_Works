package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.repository.ProjRepo;
import app.repository.RadRepo;
import app.repository.TaskRepo;
import app.repository.UserRepo;
import model.Projekat;
import model.Rad;
import model.Task;
import model.User;

@Controller
@RequestMapping(value="/proj")
public class ProjekatController {
	
	@Autowired
	ProjRepo projRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RadRepo radRepo;
	
	
	@Autowired
	TaskRepo taskRepo;
	
	@RequestMapping(value="admin/init", method=RequestMethod.GET)
	public String getuloge(Model m, HttpServletRequest request){
		m.addAttribute("proj", new Projekat());
		return "admin/newProj";
	}
	
	@RequestMapping(value="admin/save", method=RequestMethod.POST)
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
	public String showproj(Model m, Integer id, HttpServletRequest r){
		User user = Common.getUlogovan(r);
		Projekat p = projRepo.findOne(id);
		
		List<User> workers = userRepo.findWorkers(p);
		List<User> oworkers = userRepo.findMyWorkersNotOnProject(p,user);
		
		
		
		if(!p.getManager().getUsername().equals(user.getUsername())){
			m.addAttribute("manager", p.getManager());
		}
		
		if(user.getUloga().equals(User.ulogaRadnik())){
			List<Task> tasks = taskRepo.findByUsernameAndProjectId(user.getUsername(), p.getId());
			m.addAttribute("mojitaskovi", tasks);
		} else {
			Map<String, List<Task>> map = new HashMap<>();
			for(User w : workers){
				List<Task> t = taskRepo.findByUsernameAndProjectId(w.getUsername(), p.getId());
				map.put(w.getUsername(), t);
			}
			m.addAttribute("map", map);
		}
		
		m.addAttribute("radnici", workers);
		m.addAttribute("ostaliradnici", oworkers);
		m.addAttribute("proj", p);
		
		return "showProj";
	}
	
	
	@RequestMapping(value="admin/dodajradnika", method=RequestMethod.POST)
	public String dodajRadnika(Model m, Integer p, String r, HttpServletRequest request){
		Rad rad = new Rad();
		rad.setProjekat(projRepo.findOne(p));
		rad.setUser(userRepo.findOne(r));
		
		radRepo.save(rad);
		
		return showproj(m, p, request);
	}
	
	@RequestMapping(value="admin/addtask", method=RequestMethod.POST)
	public String addTask(Model m, String opis, Integer p, String u, HttpServletRequest request){
		Task t = new Task();
		t.setOpis(opis);
		t.setRad(radRepo.findByUsernameAndProj(u, p));
		taskRepo.save(t);
		
		return showproj(m, p, request);
	}
	
	@RequestMapping(value="admin/zavrsiproj", method=RequestMethod.POST)
	public String addTask(Model m, Integer p, HttpServletRequest request){
		Projekat proj = projRepo.findOne(p);
		proj.setZavrseno(new Date());
		projRepo.save(proj);
		return showproj(m, p, request);
	}
	@RequestMapping(value="utrosio", method=RequestMethod.POST)
	public String utrosio(Model m, String utrosio, Integer tid, HttpServletRequest request){
		Task t = taskRepo.findOne(tid);
		t.setUtroseno(Double.parseDouble(utrosio)); 	
		taskRepo.save(t);
		
		return showproj(m, t.getRad().getProjekat().getId(), request);
	}
}

package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.reports.ReportProjekta;
import app.reports.ReportSviProjekti;
import app.repository.ProjRepo;
import app.repository.TaskRepo;
import model.Projekat;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value="/reports")
public class ReportContoller {
	
	private JRDataSource jrds;
	
	@Autowired
	ProjRepo pr;
	@Autowired
	TaskRepo tr;
	
	public ReportContoller() throws JRException{
	}

	@RequestMapping(value="/SviProjekti.pdf", method=RequestMethod.GET)
	public String showReport(Model m){
		ReportSviProjekti rsp=new ReportSviProjekti(pr);
		try {
			jrds=rsp.create(null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("datasource", jrds);
		m.addAttribute("format", "pdf");
		return "rpt_sviProjekti";
	}
	
	@RequestMapping(value="/Projekat.pdf", method=RequestMethod.GET)
	public String showReportProj(Model m, @RequestParam("pid")Integer pid){
		Projekat p = pr.findOne(pid);
		
		ReportProjekta rsp=new ReportProjekta(tr, p);
		try {
			jrds=rsp.create(null);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("datasource", jrds);
		m.addAttribute("format", "pdf");
		m.addAttribute("naziv", p.getNaziv());
		m.addAttribute("opis", p.getOpis());
		m.addAttribute("pocetak", p.getPocetak());
		m.addAttribute("zavrseno", p.getZavrseno());
		m.addAttribute("planZavrsetka", p.getplanZavrsetka());
		
		return "rpt_TaskoviProjekta";
	}
	
}

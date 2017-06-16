package app.reports;

import java.util.List;

import app.repository.TaskRepo;
import model.Projekat;
import model.Task;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportProjekta extends JRAbstractBeanDataSourceProvider{
	private List<Task> lis;
	
	private TaskRepo pr;
	private Projekat p;
	
	public ReportProjekta(TaskRepo pr, Projekat p){
		super(Task.class);
		this.pr=pr;
		this.p = p;
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		lis=pr.findByPojectWithUser(p);
		return new JRBeanCollectionDataSource(lis);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		lis=null;
	}
}

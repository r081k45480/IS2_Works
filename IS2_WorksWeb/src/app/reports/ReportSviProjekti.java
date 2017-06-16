package app.reports;

import java.util.List;

import app.repository.ProjRepo;
import model.Projekat;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportSviProjekti extends JRAbstractBeanDataSourceProvider{
	private List<Projekat> projekti;
	
	ProjRepo pr;
	
	public ReportSviProjekti(ProjRepo pr){
		super(Projekat.class);
		this.pr=pr;
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		// TODO Auto-generated method stub
		projekti=pr.findAllOrderByManager();
		return new JRBeanCollectionDataSource(projekti);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		// TODO Auto-generated method stub
		projekti=null;
		
	}
}

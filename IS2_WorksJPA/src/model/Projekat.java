package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the projekat database table.
 * 
 */
@Entity
@NamedQuery(name="Projekat.findAll", query="SELECT p FROM Projekat p")
public class Projekat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	private String opis;

	@Temporal(TemporalType.DATE)
	@Column(name="plan_zavrsetka")
	private Date planZavrsetka;

	@Temporal(TemporalType.DATE)
	private Date pocetak;

	@Temporal(TemporalType.DATE)
	private Date zavrseno;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="manager")
	private User manager;

	//bi-directional many-to-one association to Rad
	@OneToMany(mappedBy="projekat")
	private List<Rad> rads;

	public Projekat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getplanZavrsetka() {
		return this.planZavrsetka;
	}

	public void setplanZavrsetka(Date planZavretka) {
		this.planZavrsetka = planZavretka;
	}

	public Date getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getZavrseno() {
		return this.zavrseno;
	}

	public void setZavrseno(Date zavrseno) {
		this.zavrseno = zavrseno;
	}

	public User getManager() {
		return this.manager;
	}

	public void setManager(User user) {
		this.manager = user;
	}

	public List<Rad> getRads() {
		return this.rads;
	}

	public void setRads(List<Rad> rads) {
		this.rads = rads;
	}

	public Rad addRad(Rad rad) {
		getRads().add(rad);
		rad.setProjekat(this);

		return rad;
	}

	public Rad removeRad(Rad rad) {
		getRads().remove(rad);
		rad.setProjekat(null);

		return rad;
	}

}
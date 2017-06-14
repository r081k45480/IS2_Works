package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@Column(name="ime_prezime")
	private String imePrezime;

	private String uloga;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="user")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Projekat
	@OneToMany(mappedBy="manager")
	private List<Projekat> projekats;

	//bi-directional many-to-one association to Rad
	@OneToMany(mappedBy="user")
	private List<Rad> rads;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="nadredjeni")
	private User manager;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="manager")
	private List<User> users;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImePrezime() {
		return this.imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getUloga() {
		return this.uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga.toUpperCase();
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setUser(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setUser(null);

		return komentar;
	}

	public List<Projekat> getProjekats() {
		return this.projekats;
	}

	public void setProjekats(List<Projekat> projekats) {
		this.projekats = projekats;
	}

	public Projekat addProjekat(Projekat projekat) {
		getProjekats().add(projekat);
		projekat.setManager(this);

		return projekat;
	}

	public Projekat removeProjekat(Projekat projekat) {
		getProjekats().remove(projekat);
		projekat.setManager(null);

		return projekat;
	}

	public List<Rad> getRads() {
		return this.rads;
	}

	public void setRads(List<Rad> rads) {
		this.rads = rads;
	}

	public Rad addRad(Rad rad) {
		getRads().add(rad);
		rad.setUser(this);

		return rad;
	}

	public Rad removeRad(Rad rad) {
		getRads().remove(rad);
		rad.setUser(null);

		return rad;
	}

	public User getManager() {
		return this.manager;
	}

	public void setManager(User user) {
		this.manager = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setManager(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setManager(null);

		return user;
	}

	private static String[] uloge = {
										"Radnik",
										"Manager"
									};
	
	public static String[] uloge() {
		return uloge;
	}
	
	public static String ulogaRadnik(){
		return uloge[0].toUpperCase();
	}
	public static String ulogaManager(){
		return uloge[1].toUpperCase();
	}
	

}
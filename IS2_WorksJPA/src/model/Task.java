package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String opis;

	private Double utroseno;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="task")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Rad
	@ManyToOne
	private Rad rad;

	public Task() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getUtroseno() {
		return this.utroseno;
	}

	public void setUtroseno(Double utroseno) {
		this.utroseno = utroseno;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setTask(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setTask(null);

		return komentar;
	}

	public Rad getRad() {
		return this.rad;
	}

	public void setRad(Rad rad) {
		this.rad = rad;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rad database table.
 * 
 */
@Entity
@NamedQuery(name="Rad.findAll", query="SELECT r FROM Rad r")
public class Rad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Projekat
	@ManyToOne
	@JoinColumn(name="proj")
	private Projekat projekat;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="radnik")
	private User user;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="rad")
	private List<Task> tasks;

	public Rad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Projekat getProjekat() {
		return this.projekat;
	}

	public void setProjekat(Projekat projekat) {
		this.projekat = projekat;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setRad(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setRad(null);

		return task;
	}

}
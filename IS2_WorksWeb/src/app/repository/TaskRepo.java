package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{

	@Query("SELECT t FROM Task t WHERE t.rad.projekat.id = :p AND t.rad.user.username = :u")
	public List<Task> findByUsernameAndProjectId(@Param("u") String u, @Param("p") Integer p);
}

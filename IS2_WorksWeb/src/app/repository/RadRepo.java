package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Rad;

@Repository
public interface RadRepo extends JpaRepository<Rad, Integer>{
	
	@Query("SELECT r FROM Rad r WHERE r.user.username = :u AND r.projekat.id = :p")
	public Rad findByUsernameAndProj(@Param("u") String u, @Param("p") Integer p);
}

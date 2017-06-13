package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Projekat;
import model.User;

@Repository
public interface ProjRepo extends JpaRepository<Projekat, Integer>{

	public List<Projekat> findByManager(User u);
	
	
	@Query(value="SELECT p FROM Projekat p JOIN FETCH p.rads r WHERE r.user = :u")
	public List<Projekat> findByRadnik(@Param("u") User u);
}

package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Projekat;
import model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

	public List<User> findByManager(User u);

	@Query("SELECT u FROM User u JOIN FETCH u.rads r WHERE r.projekat = :p")
	public List<User> findWorkers(@Param("p")Projekat p);
	
	
	@Query("SELECT u FROM User u WHERE u.manager=:m and u.id NOT IN"
			+ " (SELECT r.user.id FROM Rad r WHERE r.projekat =:p)")
	public List<User> findMyWorkersNotOnProject(@Param("p")Projekat p, @Param("m")User m);
	
}

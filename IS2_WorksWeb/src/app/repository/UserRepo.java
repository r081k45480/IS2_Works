package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

	public List<User> findByManager(User u);
}

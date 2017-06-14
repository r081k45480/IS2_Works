package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Rad;

@Repository
public interface RadRepo extends JpaRepository<Rad, Integer>{

}

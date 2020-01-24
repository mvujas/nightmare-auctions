package com.github.mvujas.nightmareauctionsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	@Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
	boolean doesUserWithUsernameExist(@Param("username") String username);
	
}

package com.github.mvujas.nightmareauctionsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Grade;
import com.github.mvujas.nightmareauctionsbackend.model.User;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

	@Query(
			"SELECT g "
			+ "FROM Grade g "
			+ "WHERE g.bid.author = :user OR g.item.author = : user")
	List<Grade> getUsersGrades(@Param("user") User user);
	
}

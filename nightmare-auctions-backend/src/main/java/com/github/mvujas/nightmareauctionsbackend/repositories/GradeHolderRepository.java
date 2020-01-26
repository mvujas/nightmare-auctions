package com.github.mvujas.nightmareauctionsbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.GradeHolder;
import com.github.mvujas.nightmareauctionsbackend.model.User;

@Repository
public interface GradeHolderRepository extends JpaRepository<GradeHolder, Integer> {

	@Query("SELECT gh FROM GradeHolder gh WHERE gh.givingGrade = :user AND gh.value is null")
	List<GradeHolder> getWaitingGradesForGivingUser(@Param("user") User user);
	
	@Query("SELECT gh FROM GradeHolder gh WHERE gh.receivingGrade = :user AND gh.value is not null")
	List<GradeHolder> getFinishedGradesForUser(@Param("user") User user);

}

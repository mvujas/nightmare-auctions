package com.github.mvujas.nightmareauctionsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

}

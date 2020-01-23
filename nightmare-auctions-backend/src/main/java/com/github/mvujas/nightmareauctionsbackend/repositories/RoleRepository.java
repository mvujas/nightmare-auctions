package com.github.mvujas.nightmareauctionsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
	
}

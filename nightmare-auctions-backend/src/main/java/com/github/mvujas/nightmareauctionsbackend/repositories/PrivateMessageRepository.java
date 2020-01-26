package com.github.mvujas.nightmareauctionsbackend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.PrivateMessage;
import com.github.mvujas.nightmareauctionsbackend.model.User;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Integer> {

	@Query(
			"SELECT DISTINCT u "
			+ "FROM User u, PrivateMessage pm "
			+ "WHERE (pm.sender = :user AND pm.receiver = u) OR (pm.receiver = :user AND pm.sender = u) "
			+ "GROUP BY pm.id "
			+ "ORDER BY MAX(pm.sendingTime) DESC")
	List<User> getChattersOrderedByDateForUser(@Param("user") User user);
	
	@Query(
			"SELECT pm "
			+ "FROM PrivateMessage pm "
			+ "WHERE pm.sendingTime >= :since AND "
			+ "	((pm.sender = :user1 AND pm.receiver = :user2) OR (pm.sender = :user2 AND pm.receiver = :user1)) "
			+ "ORDER BY pm.sendingTime ASC")
	List<PrivateMessage> getMessagesBetweenUsersSinceDate(
			@Param("user1") User user1,
			@Param("user2") User user2,
			@Param("since") Date since);
	
}

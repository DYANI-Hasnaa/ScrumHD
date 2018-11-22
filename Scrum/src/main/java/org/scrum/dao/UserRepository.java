package org.scrum.dao;

import org.scrum.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<user, Long>{
	
	@Query("select u from user u where u.username=:username")
	public user FindByUsername(@Param("username")String username);

	
}

package org.scrum.dao;

import java.util.Set;

import javax.validation.Valid;

import org.scrum.entities.backlog;
import org.scrum.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<user, Long>{
	
	@Query("select u from user u where u.username=:username")
	public user FindByUsername(@Param("username")String username);

	@Query("select u from user u where u.username=:username")
	public Set<user> FindByUsernameBacklogs(@Param("username")String username);

	@Query("select u from user u where u.email=:email")
	public user FindByEmail(@Param("email")String email);
	

	@Query("select u from user u join u.backlog b where u.role='productOwner'")
	public user FindPo();
}

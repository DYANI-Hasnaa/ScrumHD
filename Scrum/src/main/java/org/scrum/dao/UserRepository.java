package org.scrum.dao;

import java.util.List;
import java.util.Set;

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

	@Query("select u from user u join u.backlog b where u.role='teamMember' and u.username is not null and b.projectname=:projectname")
	public List<user> FindTeam(@Param("projectname")String projectname);

	@Query("select u from user u join u.backlog b where u.role='productOwner' and b.projectname=:projectname")
	public user FindPo(@Param("projectname")String projectname);
	
	@Query("select u from user u join u.backlog b where u.role='client' and b.projectname=:projectname")
	public user FindC(@Param("projectname")String projectname);
	
	@Query("select u from user u where u.role='teamMember' and u.username is not null")
	public List<user> FindMembers();
	
	@Query("select u from user u where u.role='productOwner' and u.username is not null")
	public List<user> FindPO();
	
	@Query("select u from user u where u.role='client' and u.username is not null")
	public List<user> Findclients();
	
	@Query("select u from user u where u.role='scrumMaster' and u.username is not null")
	public List<user> FindSM();
	
	@Query("select u from user u join u.backlog b where u.role='scrumMaster' and u.username is not null and b.projectname=:projectname")
	public List<user> FindS(@Param("projectname")String projectname);
	
	@Query("select u from user u join u.backlog b where u.role='productOwner' and u.username is not null and b.projectname=:projectname")
	public List<user> FindP(@Param("projectname")String projectname);
	
	@Query("select u from user u join u.backlog b where u.role='client' and u.username is not null and b.projectname=:projectname")
	public List<user> Findclient(@Param("projectname")String projectname);
}

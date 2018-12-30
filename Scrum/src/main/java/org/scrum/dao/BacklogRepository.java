package org.scrum.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.scrum.entities.backlog;
import org.scrum.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BacklogRepository extends JpaRepository<backlog, Long>{
	
	@Query("select b from backlog b where b.projectname=:projectname")
	public backlog FindByProjectname(@Param("projectname")String projectname);

	@Query("select b from backlog b where b.projectname=:projectname")
	public Set<backlog> FindByProjectnameBacklogs(@Param("projectname")String projectname);
	
	@Query("select b from backlog b join b.user u where u.username=:username")
	public Set<backlog> FindByUser(@Param("username")String username);

}

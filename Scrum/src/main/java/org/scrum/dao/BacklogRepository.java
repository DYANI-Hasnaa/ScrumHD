package org.scrum.dao;

import org.scrum.entities.backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BacklogRepository extends JpaRepository<backlog, Long>{
	
	@Query("select b from backlog b where b.projectname=:projectname")
	public backlog FindByProjectname(@Param("projectname")String projectname);

	

}

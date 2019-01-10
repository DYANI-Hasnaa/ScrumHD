package org.scrum.dao;

import java.util.List;

import org.scrum.entities.backlog;
import org.scrum.entities.sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SprintRepository extends JpaRepository<sprint, Long>{

	@Query("select s from sprint s where s.namesprint=:namesprint")
	public sprint FindBySprintName(@Param("namesprint")String namesprint); 
	
	@Query("select s from sprint s where s.backlog=:backlog")
	public List<sprint> FindSprintByBacklog(@Param("backlog")backlog backlog);
	
	@Query("select s.namesprint, s.point from sprint s")
	public List<sprint> FindSprint();
}

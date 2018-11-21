package org.scrum.dao;

import java.util.List;

import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.scrum.entities.sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("select i from Item i where i.name=:name")
	public Item FindByItemName(@Param("name")String name); 
	
	@Query("select i from Item i where i.backlog=:backlog")
	public List<Item> FindItemByBacklog(@Param("backlog")backlog backlog); 
	
	
	@Query("select i from Item i where i.sprint=null and i.status='To do' and i.backlog=:backlog")
	public List<Item> FindItemBacklog(@Param("backlog")backlog backlog);
	
	// @Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and s.statussprint='To do'")
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='To do'")
	public List<Item> findAllSprintTodo(@Param("sprint")sprint s);
	
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='In progress'")
	public List<Item> findAllSprintIn(@Param("sprint")sprint s);
	
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='Done'")
	public List<Item> findAllSprintDone(@Param("sprint")sprint s);
	
	

}

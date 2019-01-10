package org.scrum.dao;

import java.util.List;

import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.scrum.entities.sprint;
import org.scrum.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("select i from Item i where i.name=:name")
	public Item FindByItemName(@Param("name")String name); 
	
	@Query("select i from Item i where i.backlog=:backlog")
	public List<Item> FindItemByBacklog(@Param("backlog")backlog backlog);
	
	@Query("select i from Item i where i.sprint=null and i.backlog=:backlog")
	public List<Item> FindItemBacklog(@Param("backlog")backlog backlog);
	
	// @Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and s.statussprint='To do'")
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='To do' and it.backlog=:backlog")
	public List<Item> findAllSprintTodo(@Param("sprint")sprint s, @Param("backlog")backlog backlog);
	
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='In progress' and it.backlog=:backlog")
	public List<Item> findAllSprintIn(@Param("sprint")sprint s, @Param("backlog")backlog backlog);
	
	@Query("select it from Item it inner join it.sprint s where it.sprint=:sprint and it.status='Done' and it.backlog=:backlog")
	public List<Item> findAllSprintDone(@Param("sprint")sprint s, @Param("backlog")backlog backlog);
	
	@Query("select i from Item i where i.sprint=:sprint")
	public List<Item> FindSum(@Param("sprint")sprint sprint);
	
	@Query("select it from Item it where it.user=:user and it.status='To do'")
	public List<Item> findAllitemTodo(@Param("user")user user);
	
	@Query("select it from Item it where it.user=:user and it.status='In progress'")
	public List<Item> findAllitemIn(@Param("user")user user);
	
	@Query("select it from Item it where it.user=:user and it.status='Done'")
	public List<Item> findAllitemDone(@Param("user")user user);
	
	@Query("select it from Item it where it.backlog=:backlog and it.status='To do'")
	public List<Item> findBacklogTodo(@Param("backlog")backlog backlog);
	
	@Query("select it from Item it where it.backlog=:backlog and it.status='In progress'")
	public List<Item> findBacklogIn(@Param("backlog")backlog backlog);
	
	@Query("select it from Item it where it.backlog=:backlog and it.status='Done'")
	public List<Item> findBacklogDone(@Param("backlog")backlog backlog);
}

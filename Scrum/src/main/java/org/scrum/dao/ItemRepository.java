package org.scrum.dao;

import java.util.List;

import org.scrum.entities.Item;
import org.scrum.entities.backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Query("select i from Item i where i.name=:name")
	public Item FindByItemName(@Param("name")String name); 
	
	@Query("select i from Item i where i.backlog=:backlog")
	public List<Item> FindItemByBacklog(@Param("backlog")backlog backlog); 
	
	

	
	

}

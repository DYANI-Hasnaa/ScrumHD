package org.scrum.dao;

import java.util.Set;
import org.scrum.entities.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<message, Long>{

	@Query("select m from message m where id_user=:id_user order by date_send asc")
	public Set<message> FindMessageById(@Param("id_user")Long id_user);
	
	@Query("select m from message m where id_backlog=:id_backlog order by date_send asc")
	public Set<message> FindMessageByPO(@Param("id_backlog")Long id_backlog);
	
	
}

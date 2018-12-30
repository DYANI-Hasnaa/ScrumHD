package org.scrum.dao;


import java.util.Set;
import org.scrum.entities.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<message, Long>{

	
	@Query("select m from message m where id_user=:id_user")
	public Set<message> FindMessageById(@Param("id_user")Long id_user);
	
	
}

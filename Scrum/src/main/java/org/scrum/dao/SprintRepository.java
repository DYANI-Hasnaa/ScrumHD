package org.scrum.dao;

import org.scrum.entities.sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<sprint, Long>{

}

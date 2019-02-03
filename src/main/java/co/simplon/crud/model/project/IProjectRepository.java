package co.simplon.crud.model.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {
	
	Project findByName(String name);
    
}

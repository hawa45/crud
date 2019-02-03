package co.simplon.crud.model.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.crud.model.project.Project;

public interface ITaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByProject(Project project);
	List<Task> findByProjectAndFinishedIsFalse(Project project);
	List<Task> findByProjectAndFinishedIsTrue(Project project);
	List<Task> findByProjectAndPriorityAndFinishedIsFalse(Project project, int priority);
	List<Task> findByProjectAndPriorityAndFinishedIsTrue(Project project, int priority);

}

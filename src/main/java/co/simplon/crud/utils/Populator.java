package co.simplon.crud.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.simplon.crud.model.project.IProjectRepository;
import co.simplon.crud.model.project.Project;
import co.simplon.crud.model.task.ITaskRepository;
import co.simplon.crud.model.task.Task;

@Component
public class Populator {

	@Autowired
	private IProjectRepository projects;
	
	@Autowired
	private ITaskRepository tasks;

	public void clean() {
			
		projects.deleteAll();//cascade delete qui delete les tasks avec !!
	}

	public void demoData() {
		Project back = new Project("Back");
		projects.save(back);
		
		Task sql = new Task(back,"SQL", 5);
		tasks.save(sql);
		Task model = new Task(back,"Model", 1);
		tasks.save(model);
		Task usecase = new Task(back,"UseCase", 1);
		tasks.save(usecase);
		
		back.addTask(sql);	
		back.addTask(model);	
		back.addTask(usecase);				

		projects.save(back);
	
	}

}

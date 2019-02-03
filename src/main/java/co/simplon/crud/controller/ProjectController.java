package co.simplon.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.crud.model.project.IProjectRepository;
import co.simplon.crud.model.project.Project;
import co.simplon.crud.model.task.ITaskRepository;
import co.simplon.crud.model.task.Task;

@RestController
@RequestMapping("/project")
public class ProjectController {

	//export du projet en excel, pdf ou xml ?
	
	@Autowired
	IProjectRepository projects;
	
	@Autowired
	ITaskRepository tasks;

	@PostMapping
	public Project addProject(@RequestBody Project project) {
		return projects.save(project);
	}

	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		projects.deleteById(id);
	}

	@PutMapping
	public void updateProject(@RequestBody Project project) {
		//attention a verifier qu'il y ait bien un id sinon çà cree un autre project
		projects.save(project);
	}

	@GetMapping("/{id}")
	public Project getproject(@PathVariable Long id) {
		return projects.findById(id).get();
	}
	
	@GetMapping("/all")
	public List<Project> getAllProjects() {
		return projects.findAll();
	}
	

	@GetMapping("/name")
	public Project getProjectByName(@RequestBody String name) {
		return projects.findByName(name);
	}

	///////////////TASKS
	
	@GetMapping("/{id}/tasks")
	public List<Task> getTasks(@PathVariable Long id) {
		Project p = projects.findById(id).get();
		return tasks.findByProject(p);
	}
	
	@PostMapping("/{id}/task")
	public Task addTask(@RequestBody Task task, @PathVariable Long id)
	{
		Project p = projects.findById(id).get();
		task.setProject(p);
		tasks.save(task);
		p.addTask(task);
		projects.save(p);
		return task;
	}
	
	@DeleteMapping("/{idProject}/{idTask}")
	public void deleteTask(@PathVariable Long idProject,@PathVariable Long idTask )
	{
		Project p = projects.findById(idProject).get();
		Task task = tasks.findById(idTask).get();
		p.removeTask(task);
		projects.save(p);
	}
	
	@PutMapping("/{idProject}/task")
	public void updateTask(@RequestBody Task task, @PathVariable Long idProject)
	{
		Project p = projects.findById(idProject).get();
		task.setProject(p);
		tasks.save(task);
	}
	
}

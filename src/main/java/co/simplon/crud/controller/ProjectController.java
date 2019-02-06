package co.simplon.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.models.Response;

@RestController
@RequestMapping("/project")
public class ProjectController {

	// export du projet en excel, pdf ou xml ?

	// gerer les excpetions ds des tryc catch et retourner une erreur 500
	@Autowired
	IProjectRepository projects;

	@Autowired
	ITaskRepository tasks;

	@PostMapping
	public ResponseEntity<?> addProject(@RequestBody Project project) {

		if (projects.findByName(project.getName()) != null) {
			return new ResponseEntity<String>("A project with the same name already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Project>(projects.save(project), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (projects.findById(id) == null) {
			return new ResponseEntity<String>("No project with this id", HttpStatus.CONFLICT);
		}

		try {
			projects.deleteById(id);
			result = new ResponseEntity<>(HttpStatus.OK);
		}

		catch (Exception ex) {

			result = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;

	}

	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody Project project) {
		ResponseEntity<?> result = null;
		if (projects.findById(project.getId()) != null) {
			result = new ResponseEntity<String>("No project with id " + project.getId(), HttpStatus.NO_CONTENT);
		} else {
			result = new ResponseEntity<Project>(projects.save(project), HttpStatus.OK);
		}

		return result;

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getproject(@PathVariable Long id) {
		ResponseEntity<?> result = null;
		if (projects.findById(id).isPresent()) {
			result = new ResponseEntity<Project>(projects.findById(id).get(), HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("No project with id " + id, HttpStatus.NO_CONTENT);
		}
		return result;
	}

	@GetMapping("/all")
	public List<Project> getAllProjects() {
		return projects.findAll();
	}

	@GetMapping("/name")
	public ResponseEntity<?> getProjectByName(@RequestBody String name) {

		ResponseEntity<?> result = null;
		if (projects.findByName(name) != null) {
			result = new ResponseEntity<Project>(projects.findByName(name), HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("No project with name " + name, HttpStatus.NO_CONTENT);
		}
		return result;

	}

	/////////////// TASKS

	@GetMapping("/{id}/tasks")
	public ResponseEntity<?> getTasks(@PathVariable Long id) {

		ResponseEntity<?> result = null;

		if (projects.findById(id).isPresent()) {
			result = new ResponseEntity<List<Task>>(projects.findById(id).get().getTasks(), HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("No project with id " + id, HttpStatus.NOT_FOUND);
		}
		return result;
	}

	@PostMapping("/{id}/task")
	public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable Long id) {
		ResponseEntity<?> result = null;

		if (projects.findById(id).isPresent()) {
			Project p = projects.findById(id).get();
			task.setProject(p);
			tasks.save(task);
			p.addTask(task);
			projects.save(p);
			result = new ResponseEntity<Task>(task, HttpStatus.OK);
		} else {
			result = new ResponseEntity<String>("Cannot create task because there is no project with id " + id,
					HttpStatus.NOT_FOUND);
		}
		return result;

	}

	@DeleteMapping("/{idProject}/{idTask}")
	public ResponseEntity<?> deleteTask(@PathVariable Long idProject, @PathVariable Long idTask) {
		ResponseEntity<?> result = null;
		if (!projects.findById(idProject).isPresent()) {

			result = new ResponseEntity<String>("No project with id " + idProject, HttpStatus.NO_CONTENT);

		} else if (tasks.findById(idTask) == null) {
			result = new ResponseEntity<String>("No task with id " + idTask, HttpStatus.NO_CONTENT);
		}

		else {

			Project p = projects.findById(idProject).get();
			Task task = tasks.findById(idTask).get();
			p.removeTask(task);
			projects.save(p);

			result = new ResponseEntity<String>("", HttpStatus.OK);
		}

		return result;

	}

	@PutMapping("/{idProject}/task")
	public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long idProject) {

		ResponseEntity<?> result = null;
		if (!projects.findById(idProject).isPresent()) {
			result = new ResponseEntity<String>("No project with id " + idProject, HttpStatus.NO_CONTENT);

		} else if (tasks.findById(task.getId()) == null) {
			result = new ResponseEntity<String>("No task with id " + task.getId(), HttpStatus.NO_CONTENT);
		}

		else {
			Project p = projects.findById(idProject).get();
			task.setProject(p);
			tasks.save(task);
			result = new ResponseEntity<String>("", HttpStatus.OK);
		}

		return result;
	}
}

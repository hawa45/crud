package co.simplon.crud.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.simplon.crud.model.project.IProjectRepository;
import co.simplon.crud.model.project.Project;
import co.simplon.crud.model.role.IRoleRepository;
import co.simplon.crud.model.role.Role;
import co.simplon.crud.model.task.ITaskRepository;
import co.simplon.crud.model.task.Task;
import co.simplon.crud.model.user.IUserRepository;
import co.simplon.crud.model.user.User;

@Component
public class Populator {

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private IProjectRepository projects;

	@Autowired
	private ITaskRepository tasks;

	@Autowired
	private IUserRepository users;
	
	@Autowired
	private IRoleRepository roles;

	public void clean() {

		projects.deleteAll();// cascade delete qui delete les tasks avec !!
		
		users.deleteAll();
		
		roles.deleteAll();
	}

	public void demoData() {
		
		Role adminRole = new Role("admin");
		Role regularRole = new Role("regular");
		
		roles.save(adminRole);
		roles.save(regularRole);
		
		User admin = new User("admin",encoder.encode("password"));
		admin.addRole(adminRole);
		User bob = new User("bob",encoder.encode("password"));
		bob.addRole(regularRole);
		users.save(admin);
		users.save(bob);
		
		Project back = new Project("Back");
		projects.save(back);

		Task sql = new Task(back, "SQL", 5);
		tasks.save(sql);
		Task model = new Task(back, "Model", 1);
		tasks.save(model);
		Task usecase = new Task(back, "UseCase", 1);
		tasks.save(usecase);

		back.addTask(sql);
		back.addTask(model);
		back.addTask(usecase);

		projects.save(back);

	}

}

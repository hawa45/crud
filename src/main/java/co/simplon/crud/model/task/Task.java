package co.simplon.crud.model.task;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.crud.model.common.AbstractEntity;
import co.simplon.crud.model.project.Project;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Task extends AbstractEntity{

	@Getter	@Setter
	@NotNull
	@ManyToOne
	@JsonIgnore
	private Project project;
	
	@Getter	@Setter
	private boolean finished;
	
	@NotEmpty
	@Getter @Setter
	private String name;
	
	@Min(1)	@Max(10)
	@Getter @Setter
	private int priority;
	
	protected Task() {super();}
	
	public Task(Project project, String name, int priority)
	{
		this();
		this.setProject(project);
		this.setName(name);
		this.setPriority(priority);
	}

}

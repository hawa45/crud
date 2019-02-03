package co.simplon.crud.model.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.simplon.crud.model.common.AbstractEntity;
import co.simplon.crud.model.task.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(callSuper=true)
public class Project extends AbstractEntity {
	
	@NotEmpty
	@Column(unique=true)
	@Getter
	@Setter
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonIgnore
	private Set<Task> tasks = new HashSet<Task>();	
	
	protected Project() {super();}
	
	public Project(String name)
	{
		this();
		this.setName(name);
		this.setCreationDate(LocalDateTime.now());
	}
	public void addTask(Task task)
	{
		this.tasks.add(task);
	}
	
	public void removeTask(Task task)
	{
		this.tasks.remove(task);
	}
	
	public List<Task> getTasks()
	{
		return new ArrayList<Task>(tasks);
	}
}

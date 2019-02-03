package co.simplon.crud.model.common;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private LocalDateTime creationDate;	
	
	private LocalDateTime lastModificationDate;	
	
	protected AbstractEntity()
	{
		this.setCreationDate(LocalDateTime.now());
	}

}

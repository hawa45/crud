package co.simplon.crud.model.role;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import co.simplon.crud.model.common.AbstractEntity;
import co.simplon.crud.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Role extends AbstractEntity {

	private String name;

	@ManyToOne
	private User user;
	protected Role()
	{}
	public Role(String name)
	{
		this.setName(name);
	}

}

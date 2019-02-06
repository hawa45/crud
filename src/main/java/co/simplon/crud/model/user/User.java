package co.simplon.crud.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import co.simplon.crud.model.common.AbstractEntity;
import co.simplon.crud.model.role.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends AbstractEntity {

	private String login;
	private String passwordHash;

	@Setter(AccessLevel.PROTECTED)
	@OneToMany
	private Set<Role> roles = new HashSet<Role>();

	protected User() {//sinon pas possible de deleteALLusers
	}

	public User(String login, String passwordHash) {
		this.setLogin(login);
		this.setPasswordHash(passwordHash);
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public List<Role> GetRoles() {
		return new ArrayList<Role>(roles);
	}
}

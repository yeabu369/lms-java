package dataaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;
	public static final List<User> defaultUsers = new ArrayList<>() {
		{
			add(new User("101", "xyz", Auth.LIBRARIAN));
			add(new User("102", "abc", Auth.ADMIN));
			add(new User("103", "111", Auth.BOTH));
		}
	};

	private String id;
	
	private String password;
	private Auth authorization;
	User(String id, String pass, Auth  auth) {
		this.id = id;
		this.password = pass;
		this.authorization = auth;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Auth getAuthorization() {
		return authorization;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + authorization.toString() + "]";
	}
	
}

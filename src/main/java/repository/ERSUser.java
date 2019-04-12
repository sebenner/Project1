package repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ERSUser {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
}

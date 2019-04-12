package repository;

import java.util.List;

public interface UserRepository {
	public ERSUser loginUser(String uName, String pssword);
	public ERSUser registerUser(String uName, String pssword, String firstName,
			String lastName, String email, String uRole);
	//public boolean usernameExists(String uName);
	//public boolean emailExists(String email);
	public void submitRequest(float amount, String description, int author,
								String type);
	public List<EmpRequests> getEmployeesRequests(int uId);
	public List<AllRequests> getAllRequestsByStatus(String status);
	public Request getRequest(int rId);
	public void updateRequest(int rId, int resolver, String status);
}

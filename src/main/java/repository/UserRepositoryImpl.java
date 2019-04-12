package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import exception.DatabaseException;
import util.DBUtil;

public class UserRepositoryImpl implements UserRepository{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public ERSUser loginUser(String email, String pssword) {
		final Logger logger = Logger.getLogger("log");
		logger.info("get Employees Requests 1");
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st = con.prepareStatement("select * from ers_users where usrEmail=? and usrPassword=?");
			st.setString(1, email);
			st.setString(2, pssword);
			ResultSet output = st.executeQuery();
			con.commit();
			if (output.next()) {
				return new ERSUser(output.getInt("usrId"), output.getString("usrUsername"),
						output.getString("usrFirstName"), output.getString("usrLastName"),
						output.getString("usrEmail"), output.getString("usrRole"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ERSUser registerUser(String uName, String pssword, String firstName,
								String lastName, String email, String uRole) {
		try (Connection con = DBUtil.getConnection()) {
			CallableStatement cs = con.prepareCall("call proc1(?,?,?,?,?,?)");
			cs.setString(1, uName);
			cs.setString(2, pssword);
			cs.setString(3, firstName);
			cs.setString(4, lastName);
			cs.setString(5, email);
			cs.setString(6, uRole);
			cs.executeQuery();
			con.commit();
			return loginUser(email, pssword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//con.close();
		return null;
	}

	/*
	@Override
	public boolean usernameExists(String uName) {
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st = con.prepareStatement("select * from ers_users where usrUsername=?");
			st.setString(1, uName);
			ResultSet output = st.executeQuery();
			con.commit();
			return output.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean emailExists(String email) {
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st = con.prepareStatement("select * from ers_users where usrEmail=?");
			st.setString(1, email);
			ResultSet output = st.executeQuery();
			con.commit();
			return output.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
*/
	@Override
	public void submitRequest(float amount, String description, int author, String type) {
		try (Connection con = DBUtil.getConnection()) {
			CallableStatement cs = con.prepareCall("call proc2(?,?,?,?)");
			cs.setFloat(1, amount);
			cs.setString(2, description);
			cs.setInt(3, author);
			cs.setString(4, type);
			cs.executeQuery();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EmpRequests> getEmployeesRequests(int uId) {
		final Logger logger = Logger.getLogger("log");
		logger.info("get Employees Requests 1");
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st = con.prepareStatement("select reimbSubmitted, reimbAmount, reimbType, reimbStatus from ers_reimbursement where reimbAuthor=?");
			st.setInt(1, uId);
			ResultSet output = st.executeQuery();
			con.commit();
			List<EmpRequests> empReqs = new ArrayList<>();
			while(output.next()) {
				logger.info("output.getDate(1) = " + output.getTimestamp(1));
				logger.info("output.getFloat(2) = " + output.getFloat(2));
				empReqs.add(new EmpRequests(dateFormat.format(output.getDate(1)),output.getFloat(2),
											output.getString(3), output.getString(4)));
				logger.info("get Employees Requests 2");
			}
			logger.info("get Employees Requests 3");
			return empReqs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRequest(int rId, int resolver, String status) {
		try (Connection con = DBUtil.getConnection()) {
			CallableStatement cs = con.prepareCall("call proc3(?,?,?)");
			cs.setInt(1, rId);
			cs.setInt(2, resolver);
			cs.setString(3, status);
			cs.executeQuery();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<AllRequests> getAllRequestsByStatus(String status) {
		final Logger logger = Logger.getLogger("log");
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st;
			logger.info("status = " + status);
			if (status.equals("All")) {
				logger.info(1);
				st = con.prepareStatement("select r.reimbid, u.usrUsername, r.reimbamount, r.reimbsubmitted, r.reimbtype, r.reimbstatus\r\n" + 
						"from ers_users u JOIN ers_reimbursement r ON u.usrId = r.reimbauthor");
			}
			else {
				logger.info(2);
				st = con.prepareStatement("select r.reimbid, u.usrUsername, r.reimbamount, r.reimbsubmitted, r.reimbtype, r.reimbstatus\r\n" + 
						"from ers_users u JOIN ers_reimbursement r ON u.usrId = r.reimbauthor where r.reimbstatus=?");
				st.setString(1, status);
			}
			//"select reimbSubmitted, reimbAmount, reimbType, reimbStatus from ers_reimbursement where reimbAuthor=?");
			ResultSet output = st.executeQuery();
			con.commit();
			List<AllRequests> reqs = new ArrayList<>();
			while(output.next()) {
				reqs.add(new AllRequests(output.getInt(1), output.getString(2), output.getFloat(3),
										dateFormat.format(output.getDate(4)), output.getString(5), output.getString(6)));
			}
			return reqs;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Request getRequest(int rId) {
		final Logger logger = Logger.getLogger("log");
		logger.info("get Employees Requests 1");
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement st;
				st = con.prepareStatement("select r.reimbid, u.usrUsername, r.reimbamount, r.reimbsubmitted, r.reimbdescription, r.reimbtype, r.reimbstatus\r\n" + 
						"from ers_users u JOIN ers_reimbursement r ON u.usrId = r.reimbauthor where r.reimbid=?");
				st.setInt(1, rId);
			ResultSet output = st.executeQuery();
			con.commit();
			Request req = new Request(output.getInt(1), output.getString(2), output.getFloat(3),
						dateFormat.format(output.getDate(4)), output.getString(5), output.getString(6), output.getString(7));
			return req;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return null;
	}
}

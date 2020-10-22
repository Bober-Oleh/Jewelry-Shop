package com.epam.jewelry_shop.dao;

import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.util.CloserConnectionDB;
import com.epam.jewelry_shop.util.HashPasswordAuthentication;
import com.epam.jewelry_shop.util.UserResultSetExtracter;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.epam.jewelry_shop.database.DBHelper;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_QUERY = "INSERT INTO jewelry_shop.users(firstName, lastName, email, password, avatar,role) VALUES (?,?,?,?,?,?)";
	private static final String SELECT_QUERY = "SELECT * FROM users WHERE email = ?";
	private static final String SELECT_QUERY_ALL = "SELECT * FROM users";

	Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	private UserResultSetExtracter userResultSetExtracter;
	private DBHelper DBHelper;

	public UserDAOImpl(DBHelper dbHelper) {
		this.DBHelper = dbHelper;
		this.userResultSetExtracter = new UserResultSetExtracter();
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}

	@Override
	public boolean createUser(User user) {
		Connection connection = DBHelper.getConnection();
		boolean result = false;
		PreparedStatement ps = null;

		try {

			User checkUser = getUserByEmail(user.getEmail());
			if (checkUser == null) {
				ps = getPreparedStatement(connection, INSERT_QUERY);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, new HashPasswordAuthentication().hash(user.getPassword().toCharArray()));
				ps.setString(5, user.getAvatar());

				if (ps.executeUpdate() > 0) {
					ResultSet resultSet = ps.getGeneratedKeys();
					if (resultSet.next()) {
						user.setIdUser(resultSet.getInt(1));
						result = true;
					}
				}
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
		} finally {

			try {
				new CloserConnectionDB().closeConnection(ps, connection);

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return result;
	}

	@Override
	public User getUserByEmail(String email) {
		Connection connection = DBHelper.getConnection();
		User user = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedStatement(connection, SELECT_QUERY);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {

				int idUser = rs.getInt("idUser");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String password = rs.getString("password");
				String role=rs.getString("role");
				user = new User(idUser, firstName, lastName, email, password);
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
		} finally {

			try {
				new CloserConnectionDB().closeConnection(ps, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User getUserById(int idUser) {
		Connection connection = DBHelper.getConnection();
		User user = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = getPreparedStatement(connection, SELECT_QUERY);
			ps.setString(1, String.valueOf(idUser));
			rs = ps.executeQuery();

			while (rs.next()) {
				user = userResultSetExtracter.createUserFromResultSet(rs);
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
		} finally {
			try {
				new CloserConnectionDB().closeConnection(ps, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		Connection connection = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getPreparedStatement(connection, SELECT_QUERY_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				users.add(userResultSetExtracter.createUserFromResultSet(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				new CloserConnectionDB().closeConnection(ps, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
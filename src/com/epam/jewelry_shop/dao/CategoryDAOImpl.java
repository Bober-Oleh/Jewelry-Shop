package com.epam.jewelry_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import com.epam.jewelry_shop.database.DBHelper;
import com.epam.jewelry_shop.model.Category;

public class CategoryDAOImpl {
	private static final String COUNT_CATEGORIES = "SELECT * FROM categories";
	private Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	private DBHelper DBHelper;

	public CategoryDAOImpl(com.epam.jewelry_shop.database.DBHelper dBHelper) {

		DBHelper = dBHelper;
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	public List<Category> findAllCategory() {
		List<Category> categories = new ArrayList<>();
		Connection connection = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = COUNT_CATEGORIES;
			ps = getPreparedStatement(connection, query);
			rs = ps.executeQuery();

			while (rs.next()) {

				int idCategory = rs.getInt("idCategory");
				String nameCategory = rs.getString("nameCategory");

				Category category = new Category(idCategory, nameCategory);
				categories.add(category);
			}

		} catch (SQLException e) {
			log.info(e.getMessage());
		} finally {

			try {
				closeConnection(ps, connection);
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return categories;
	}

	public void closeConnection(PreparedStatement ps, Connection connection) throws SQLException {
		DbUtils.close(ps);
		DbUtils.close(connection);
	}

}

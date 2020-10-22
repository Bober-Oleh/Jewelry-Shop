package com.epam.jewelry_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import com.epam.jewelry_shop.database.DBHelper;
import com.epam.jewelry_shop.model.Producer;

public class ProducerDAOImpl implements ProducerDAO {

	private static final String COUNT_PRODUCERS = "SELECT * FROM producers";
	private DBHelper DBHelper;

	public ProducerDAOImpl(DBHelper dBHelper) {
		DBHelper = dBHelper;
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	@Override
	public List<Producer> findAllProducer() {
		Connection connection = DBHelper.getConnection();
		List<Producer> producers = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = COUNT_PRODUCERS;
			ps = getPreparedStatement(connection, query);
			rs = ps.executeQuery();

			while (rs.next()) {

				int idProducer = rs.getInt("idProducer");
				String nameProducer = rs.getString("nameProducer");
				Producer producer = new Producer(idProducer, nameProducer);
				producers.add(producer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				closeConnection(ps, connection);
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return producers;
	}

	public void closeConnection(PreparedStatement ps, Connection connection) throws SQLException {
		DbUtils.close(ps);
		DbUtils.close(connection);
	}

}

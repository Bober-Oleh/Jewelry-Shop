package com.epam.jewelry_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import com.epam.jewelry_shop.database.DBHelper;
import com.epam.jewelry_shop.model.Order;
import com.epam.jewelry_shop.model.ProductInformation;
import com.mysql.jdbc.Statement;

public class OrderDAOImpl {
	private static final String MAINQUERY = "SELECT od.idOrder, orderStatus, detailedOrderStatus, date, idUser,pi.idProduct, price, quantity "
			+ "FROM jewelry_shop.orders od join jewelry_shop.product_informations pi on pi.idOrder=od.idOrder";
	private static final String ADD_ORDER = "insert into jewelry_shop.orders values (?, ?, ?,?,?)";
	private static final String ADD_ORDER_ITEM = "insert into jewelry_shop.product_informations values (?, ?, ?,?,?)";

	Logger log = Logger.getLogger(OrderDAOImpl.class.getName());
	private DBHelper DBHelper;

	public OrderDAOImpl() {
		DBHelper = new DBHelper();
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}

	public boolean addOrder(Order order) {
		boolean result = true;

		if (!saveOrder(order)) {
			return false;
		}
		for (ProductInformation product : order.getListProduct()) {
			if (!saveItemOrder(product, order.getIdOrder())) {
				return false;
			}

		}

		return result;
	}

	public boolean saveOrder(Order order) {
		Connection connection = DBHelper.getConnection();
		boolean result = false;
		PreparedStatement ps = null;

		try {

			ps = getPreparedStatement(connection, ADD_ORDER);
			ps.setInt(1, 0);
			ps.setString(2, order.getOrderStatus());
			ps.setString(3, order.getDetailedOrderStatus());
			ps.setString(4, order.getDate());
			ps.setInt(5, (int) order.getUser().getIdUser());

			if (ps.executeUpdate() > 0) {
				ResultSet resultSet = ps.getGeneratedKeys();
				if (resultSet.next()) {
					order.setIdOrder(resultSet.getInt(1));
					result = true;
				}
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

		return result;

	}

	public boolean saveItemOrder(ProductInformation product, int idOrder) {
		Connection connection = DBHelper.getConnection();
		boolean result = false;
		PreparedStatement ps = null;

		try {

			ps = getPreparedStatement(connection, ADD_ORDER_ITEM);
			ps.setInt(1, 0);
			ps.setInt(2, product.getIdProduct());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getNumberOfProducts());
			ps.setInt(5, idOrder);

			if (ps.executeUpdate() > 0) {
				result = true;
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

		return result;

	}

	public void closeConnection(PreparedStatement ps, Connection connection) throws SQLException {
		DbUtils.close(ps);
		DbUtils.close(connection);
	}
}

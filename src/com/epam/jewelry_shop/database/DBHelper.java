package com.epam.jewelry_shop.database;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DBHelper {

	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	public Connection getConnection() {
		Connection connection = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/pool");
			connection = ds.getConnection();

		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
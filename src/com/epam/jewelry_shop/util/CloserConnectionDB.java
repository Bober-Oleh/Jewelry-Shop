package com.epam.jewelry_shop.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;

public class CloserConnectionDB {
	public void closeConnection(PreparedStatement ps, Connection connection) throws SQLException {
		DbUtils.close(ps);
		DbUtils.close(connection);
	}
}

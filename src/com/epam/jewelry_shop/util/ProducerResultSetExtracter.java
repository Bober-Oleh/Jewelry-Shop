package com.epam.jewelry_shop.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.epam.jewelry_shop.model.Producer;

public class ProducerResultSetExtracter {

	public Producer createProducerFromResultSet(ResultSet rs) throws SQLException {

		int idProducer = rs.getInt("idProducer");
		String nameProducer = rs.getString("nameProducer");
		Producer producer = new Producer(idProducer, nameProducer);

		return producer;
	}

}

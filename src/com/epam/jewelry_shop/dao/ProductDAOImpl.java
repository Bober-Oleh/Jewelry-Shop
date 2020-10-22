package com.epam.jewelry_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import com.epam.jewelry_shop.beans.FilterFormBean;
import com.epam.jewelry_shop.database.DBHelper;
import com.epam.jewelry_shop.model.Product;
import com.epam.jewelry_shop.util.CategoryResultSetExtracter;
import com.epam.jewelry_shop.util.ProducerResultSetExtracter;
import com.epam.jewelry_shop.util.ProductResultSetExtracter;

public class ProductDAOImpl implements ProductDAO {
	private Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	private DBHelper DBHelper;

	private static final String PRODUCT_QUERY = "SELECT idProduct,price,name,image,pr.idProducer,nameProducer,"
			+ "c.idCategory,nameCategory " + "FROM jewelry_shop.products prod join jewelry_shop.categories c "
			+ "on c.idCategory=prod.idCategory join jewelry_shop.producers pr on pr.idProducer=prod.idProducer ";
	private static final String COUNT_QUERY = "select count(idProduct)from products prod";

	private static final String MIN_PRICE = "SELECT MIN(PRICE) FROM jewelry_shop.products";
	private static final String MAX_PRICE = "SELECT MAX(PRICE) FROM jewelry_shop.products";
	ProductResultSetExtracter productResult;
	ProducerResultSetExtracter producerResult;
	CategoryResultSetExtracter categoryResult;

	public ProductDAOImpl(DBHelper dbHelper) {
		DBHelper = dbHelper;
		this.productResult = new ProductResultSetExtracter();
		this.categoryResult = new CategoryResultSetExtracter();
		this.producerResult = new ProducerResultSetExtracter();
	}

	public PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	public List<Product> findProducts(int countProduct, int numberProducts, int numberPage, String filters,
			String sortForQuery) {
		String myQuery = PRODUCT_QUERY + filters + " ";
		int numberPages = (int) (Math.ceil(countProduct / (double) numberPage));
		int min = (numberPage - 1) * numberProducts + 1;
		int max = min + numberProducts - 1;
		if (numberPage == numberPages) {
			max = countProduct - ((numberPages - 1) * numberProducts);
		}
		String pagination = " LIMIT " + (min - 1) + "," + numberProducts;
		myQuery += sortForQuery + " " + pagination;
		return findAllProductsWithFilters(myQuery);
	}

	@Override
	public String createFiltersForQuery(FilterFormBean filterBean) {
		int filterCategoryId = filterBean.getCategoryId();
		int filterProduserId = filterBean.getProducerId();
		String name = filterBean.getName();
		double filterMaxPrice = filterBean.getMaxPrice();
		double filterMinPrice = filterBean.getMinPrice();
		String filter = "where price <= " + filterMaxPrice + " and price >=" + filterMinPrice;
		List<String> filters = new ArrayList();
		if (filterCategoryId != 0) {
			filters.add("prod.idCategory=" + filterCategoryId);
		}
		if (name != null && !name.equals("")) {
			filters.add("prod.name LIKE '%" + name + "%'");
		}
		if (filterProduserId != 0) {
			filters.add("prod.idProducer=" + filterProduserId);
		}
		for (String str : filters) {
			filter += " and " + str;
		}

		return filter;
	}

	@Override
	public String createSortForQuery(String fieldSort, int typeSort) {
		String sortForQuery = " order by " + fieldSort;
		if (typeSort == 2) {
			sortForQuery += " desc";
		}

		return sortForQuery;
	}

	public int countProducts(String filters) {
		Connection connection = DBHelper.getConnection();

		int countProducts = 0;
		String query = COUNT_QUERY + " " + filters;
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {

			ps = getPreparedStatement(connection, query);
			rs = ps.executeQuery();

			while (rs.next()) {
				int numberStrings = rs.getInt("count(idProduct)");
				countProducts = numberStrings;
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

		return countProducts;
	}

	@Override
	public List<Product> findAllProductsWithFilters(String myQuery) {
		Connection connection = DBHelper.getConnection();
		List<Product> products = new ArrayList<>();
		String query = null;
		if (myQuery != null) {
			query = myQuery;
		} else {
			query = PRODUCT_QUERY;

		}

		PreparedStatement ps = null;

		ResultSet rs = null;
		try {

			ps = getPreparedStatement(connection, query);

			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = productResult.createProductFromResultSet(rs);
				product.setProducer(producerResult.createProducerFromResultSet(rs));
				product.setCategory(categoryResult.createCategoryFromResultSet(rs));
				products.add(product);
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
		return products;
	}

	public Product getProductById(int idProduct) {
		Connection connection = DBHelper.getConnection();
		Product product = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = PRODUCT_QUERY + " WHERE idProduct = ?";
			ps = getPreparedStatement(connection, query);

			ps.setString(1, String.valueOf(idProduct));

			rs = ps.executeQuery();

			while (rs.next()) {
				product = productResult.createProductFromResultSet(rs);
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
		return product;

	}

	public double getMinPrice() {
		Connection connection = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		double minPrice = 0;
		try {

			String query = MIN_PRICE;
			ps = getPreparedStatement(connection, query);
			rs = ps.executeQuery();

			while (rs.next()) {
				minPrice = rs.getDouble("min(price)");
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
		return minPrice;

	}

	public double getMaxPrice() {
		Connection connection = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		double maxPrice = 0;
		try {

			String query = MAX_PRICE;
			ps = getPreparedStatement(connection, query);
			rs = ps.executeQuery();

			while (rs.next()) {
				maxPrice = rs.getDouble("max(price)");
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
		return maxPrice;

	}

	public void closeConnection(PreparedStatement ps, Connection connection) throws SQLException {
		DbUtils.close(ps);
		DbUtils.close(connection);
	}
}
package com.epam.jewelry_shop.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.epam.jewelry_shop.dao.CategoryDAOImpl;
import com.epam.jewelry_shop.dao.ProducerDAOImpl;
import com.epam.jewelry_shop.dao.ProductDAO;
import com.epam.jewelry_shop.dao.ProductDAOImpl;
import com.epam.jewelry_shop.dao.UserDAOImpl;
import com.epam.jewelry_shop.database.DBHelper;
import com.epam.jewelry_shop.service.UserService;
import com.epam.jewelry_shop.service.UserServiceImpl;

public class ServletContextListenerImpl implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {

		ServletContext context = event.getServletContext();

		DBHelper dbHelper = new DBHelper();
		UserDAOImpl userDao = new UserDAOImpl(dbHelper);
		ProductDAO productDAO = new ProductDAOImpl(dbHelper);
		UserService userService = new UserServiceImpl(userDao);
		ProducerDAOImpl producerDAO = new ProducerDAOImpl(dbHelper);
		CategoryDAOImpl categoryDAO = new CategoryDAOImpl(dbHelper);

		context.setAttribute("userService", userService);
		context.setAttribute("userDAO", userDao);
		context.setAttribute("productDAO", productDAO);
		context.setAttribute("producerDAO", producerDAO);
		context.setAttribute("categoryDAO", categoryDAO);
	}

}

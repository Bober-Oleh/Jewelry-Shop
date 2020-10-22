package com.epam.jewelry_shop.servlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class RegistrationTest {
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	RequestDispatcher requestDispatcher;
	@Mock
	HttpSession session;

	@Before
	public void initialize() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		requestDispatcher = mock(RequestDispatcher.class);
		session = mock(HttpSession.class);
		when(request.getRequestDispatcher("registration.jsp")).thenReturn(requestDispatcher);
		when(session.getAttribute("capcha")).thenReturn("1235");
		when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher("home.jsp")).thenReturn(requestDispatcher);
		when(request.getParameter("email")).thenReturn("zxcvb@mail.ru");

	}

	@Test
	public void RegistrationServletValid() throws ServletException, IOException {

		when(request.getParameter("capcha")).thenReturn("1235");
		StringWriter stringWriter = new StringWriter();
		new RegistrationServlet().doPost(request, response);
		verify(request).getRequestDispatcher("home.jsp");
	}

	@Test
	public void RegistrationServletNotValid() throws ServletException, IOException {

		when(request.getParameter("capcha")).thenReturn("1231");
		StringWriter stringWriter = new StringWriter();
		new RegistrationServlet().doPost(request, response);
		verify(request).getRequestDispatcher("registration.jsp");
	}
}
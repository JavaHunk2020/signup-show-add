package p1.pykube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signups")
public class SignupsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 List<SignupDTO> dtos=SignupDaoService.findAll();
		 //Adding my data inside request scope
		 req.setAttribute("signups", dtos);
		//I have to go to next JSP
		 req.getRequestDispatcher("showSignup.jsp").forward(req, resp);
	}

}

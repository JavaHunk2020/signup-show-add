package p1.pykube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seachRecords")
public class SearchSignupServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String searchText=req.getParameter("searchText");
		 if(searchText!=null) {
			 searchText=searchText.trim();
		 }
		 List<SignupDTO> dtos=SignupDaoService.searchData(searchText);
		 //Adding my data inside request scope
		 req.setAttribute("signups", dtos);
		//I have to go to next JSP
		 req.getRequestDispatcher("showSignup.jsp").forward(req, resp);
	}

}

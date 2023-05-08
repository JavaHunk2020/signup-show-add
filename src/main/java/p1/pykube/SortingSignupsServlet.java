package p1.pykube;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sortData")
public class SortingSignupsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		  String sort=req.getParameter("sort");
		  String orderBy=req.getParameter("orderBy");
    	 //FETCHING REMAINING DATA
		 List<SignupDTO> dtos=SignupDaoService.findAll();
		 if("email".equalsIgnoreCase(sort) && "asc".equalsIgnoreCase(orderBy)) {
			 //This is an example of anonymous class  - class with out name
			 Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					//Ascending order
					return o1.getEmail().compareTo(o2.getEmail());
				}
			});
		 }else if("email".equalsIgnoreCase(sort) && "desc".equalsIgnoreCase(orderBy)) {
			 //This is an example of anonymous class  - class with out name
			 Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					//Ascending order
					return o2.getEmail().compareTo(o1.getEmail());
				}
			});
		 }else if("username".equalsIgnoreCase(sort)) {
			 //This is an example of anonymous class  - class with out name
			 Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					//Ascending order
					return o1.getUsername().compareTo(o2.getUsername());
				}
			});
		 }
		 //Adding my data inside request scope
		 req.setAttribute("signups", dtos);
		//I have to go to next JSP
		 req.getRequestDispatcher("showSignup.jsp").forward(req, resp);
	}

}

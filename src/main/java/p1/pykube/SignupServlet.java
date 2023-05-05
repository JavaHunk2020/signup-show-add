package p1.pykube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/csignup")
public class SignupServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		
		
		//Show on some other page
		SignupDTO signupDTO=new SignupDTO(username,password,mobile,email,address);
		
		SignupDaoService.save(signupDTO);
		
		//I have adding signupDTO inside request scope against pkdata key
		req.setAttribute("message", "Hey record is saved in database successfully!");
		
		 List<SignupDTO> dtos=SignupDaoService.findAll();
		 req.setAttribute("signups", dtos);
		//I have to go to next JSP
		req.getRequestDispatcher("showSignup.jsp").forward(req, resp);
	}

}

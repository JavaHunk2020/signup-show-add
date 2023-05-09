package p1.pykube;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/esignup")
public class ESignupServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid=req.getParameter("pid");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		
		
		//Show on some other page
		SignupDTO signupDTO=new SignupDTO(username,password,mobile,email,address);
		signupDTO.setPid(Integer.parseInt(pid));
		SignupDaoService.update(signupDTO);
		
		//I have adding signupDTO inside request scope against pkdata key
		req.setAttribute("message", "Hey record is update in database successfully!");
		
		 List<SignupDTO> dtos=SignupDaoService.findAll();
		 req.setAttribute("signups", dtos);
		//I have to go to next JSP
		 req.getRequestDispatcher("showSignup.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String pid=req.getParameter("pid");
		 int pidNum= Integer.parseInt(pid);
		 SignupDTO dto=SignupDaoService.findByPid(pidNum);
		 //Adding my data inside request scope
		 req.setAttribute("dto", dto);
		//I have to go to next JSP
		 req.getRequestDispatcher("esignup.jsp").forward(req, resp);
	}

}

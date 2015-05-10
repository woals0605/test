package kr.ac.shinhan.csp; 

import java.io.IOException; 
 
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class SignUpServlet  extends HttpServlet { 
 	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
 		resp.setCharacterEncoding("UTF-8"); 
 		resp.setContentType("text/html");
 		
 		String id = req.getParameter("id");
 		String name = req.getParameter("name");
 		String password = req.getParameter("password");
 		boolean success = false; 
 		
 		PersistenceManager pm = MyPersistenceManager.getManager();
 		UserAccount ua = new UserAccount(id,password,name);
 		Query qry = MyPersistenceManager.getManager().newQuery(UserAccount.class);
 		List<UserAccount> userList = (List<UserAccount>) qry.execute();
 		
 		for(UserAccount ul : userList){
	 		if(id.equals(ul.getUserID()))
	 		{
	 			success=false;
	 		}
	 		else
	 		{
	 			success=true;
	 		}
 		}
 		if(!success){
 			resp.getWriter().println("<html><body>"); 
 			resp.getWriter().println("Can't use ID");
 			resp.getWriter().println("<a href='signup.html'>Sign Up Again</a>");
 			resp.getWriter().println("</body></html>");
 		}
 		if(success){
 			pm.makePersistent(ua);
 			resp.sendRedirect("login.html");
 		}

 	} 
 
} 

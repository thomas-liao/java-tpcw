import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TPCW_order_inquiry_servlet extends HttpServlet {
    
  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
      HttpSession session = req.getSession(false);

      PrintWriter out = res.getWriter();
      // Set the content type of this servlet's result.
      res.setContentType("text/html");
      String username = "";
      String url;
      String C_ID = req.getParameter("C_ID");
      String SHOPPING_ID=req.getParameter("SHOPPING_ID");

      
      out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD W3 HTML//EN\">\n");
      out.print("<HTML><HEAD><TITLE>Laptop Guru Order</TITLE>\n");
      out.print("</HEAD><BODY BGCOLOR=\"#ffffff\">\n"); 
      out.print("<H1 ALIGN=\"center\">Laptop Guru Platform (TPC-W)</H1>\n"); 
      out.print("<H2 ALIGN=\"center\">Order Inquiry</H2>\n"); 

      out.print("<FORM ACTION=\"TPCW_order_display_servlet;@sessionIdString@"+
		req.getRequestedSessionId()+"\" METHOD=\"get\">\n");
      out.print("<TABLE ALIGN=\"CENTER\">\n"); 
      out.print("<TR> <TD> <H4>Username:</H4></TD>\n"); 
      out.print("<TD><INPUT NAME=\"UNAME\" VALUE=\"" + username +
		"\" SIZE=\"23\"></TD></TR>\n"); 
      out.print("<TR><TD> <H4>Password:</H4></TD>\n"); 
      out.print("<TD> <INPUT NAME=\"PASSWD\" SIZE=\"14\" " +
		"TYPE=\"password\"></TD>\n");
      out.print("</TR></TABLE> <P ALIGN=\"CENTER\"><CENTER>\n");

      out.print("<INPUT TYPE=\"IMAGE\" NAME=\"Display Last Order\" "
		+ "SRC=\"../tpcw/Images/display_last_order_B.gif\">\n");
      if(SHOPPING_ID != null)
	  out.print("<INPUT TYPE=HIDDEN NAME=\"SHOPPING_ID\" value = \"" + 
		    SHOPPING_ID + "\">\n");
      if(C_ID!=null)
	  out.print("<INPUT TYPE=HIDDEN NAME=\"C_ID\" value = \"" + 
		    C_ID + "\">\n");
      url = "TPCW_search_request_servlet";
      if(SHOPPING_ID != null){
	  url = url+"?SHOPPING_ID="+SHOPPING_ID;
	  if(C_ID!=null)
	      url = url + "&C_ID=" + C_ID;
      }
      else if(C_ID!=null)
	  url = url + "?C_ID=" + C_ID;
      
      out.print("<A HREF=\"" + res.encodeUrl(url));
      out.print("\"><IMG SRC=\"../tpcw/Images/search_B.gif\" "
		+ "ALT=\"Search\"></A>\n");

      url = "TPCW_home_interaction";
      if(SHOPPING_ID != null){
	  url = url+"?SHOPPING_ID="+SHOPPING_ID;
	  if(C_ID!=null)
	      url = url + "&C_ID=" + C_ID;
      }
      else if(C_ID!=null)
	  url = url + "?C_ID=" + C_ID; 
      
      out.print("<A HREF=\"" + res.encodeUrl(url));
      out.print("\"><IMG SRC=\"../tpcw/Images/home_B.gif\" " 
		+ "ALT=\"Home\"></A></P></CENTER>\n");
      out.print("</CENTER></FORM></BODY></HTML>");		
      out.close();
      return;
    }
}

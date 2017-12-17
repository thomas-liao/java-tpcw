import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TPCW_home_interaction extends HttpServlet {
    
  public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
      int i;	
      String url;
      Vector column1 = new Vector();
      Vector column2 = new Vector();

      column1.addElement(new String("ARTS"));//1
      column2.addElement(new String("NON-FICTION"));
      column1.addElement(new String("BIOGRAPHIES"));//2
      column2.addElement(new String("PARENTING"));
      column1.addElement(new String("BUSINESS"));//3
      column2.addElement(new String("POLITICS"));
      column1.addElement(new String("CHILDREN"));//4
      column2.addElement(new String("REFERENCE"));
      column1.addElement(new String("COMPUTERS"));//5
      column2.addElement(new String("RELIGION"));
      column1.addElement(new String("COOKING"));//6
      column2.addElement(new String("ROMANCE"));
      column1.addElement(new String("HEALTH"));//7
      column2.addElement(new String("SELF-HELP"));
      column1.addElement(new String("HISTORY"));//8
      column2.addElement(new String("SCIENCE-NATURE"));
      column1.addElement(new String("HOME"));//9
      column2.addElement(new String("SCIENCE-FICTION"));
      column1.addElement(new String("HUMOR"));//10
      column2.addElement(new String("SPORTS"));
      column1.addElement(new String("LITERATURE"));//11
      column2.addElement(new String("MYSTERY"));

      /*SERVLET SETUP*/
      HttpSession session = req.getSession(false);
      if(session == null)
	  session = req.getSession(true);
      // This must be after the getSession() call.
      PrintWriter out = res.getWriter();
      // Set the content type of this servlet's result.
      res.setContentType("text/html");

      //int C_ID = -1;
      //int SHOPPING_ID = -1;
      String C_ID = req.getParameter("C_ID");
      String SHOPPING_ID = req.getParameter("SHOPPING_ID");

      //Generate Home Page Head
      out.print("<HTML> <HEAD> <TITLE>Laptop Guru Platform</TITLE></HEAD>\n");
      out.print("<BODY BGCOLOR=\"#ffffff\">\n"); 
      out.print("<H1 ALIGN=\"center\">Laptop Guru Platform</H1>\n");
      out.print("<P ALIGN=\"CENTER\">\n");
      out.print("<IMG SRC=\"../tpcw/Images/tpclogo.gif\" ALIGN=\"BOTTOM\""+ 
		"BORDER=\"0\" WIDTH=\"288\" HEIGHT=\"67\"></P>\n");
      out.print("<H2 ALIGN=\"center\">Home Page</H2>\n");
      

      //Say Hello!
      TPCW_say_hello.print_hello(session, req, out);

      //Insert the promotional processing
      TPCW_promotional_processing.DisplayPromotions(out, req, res,-1);

      //Generate Table of What's New and BestSellers
      //Table headings
      out.print("<TABLE ALIGN=\"center\" BGCOLOR=\"#c0c0c0\" BORDER=\"0\""+
		" CELLPADDING=\"6\" CELLSPACING=\"0\" WIDTH=\"700\">\n");
      out.print("<TR ALIGN=\"CENTER\" BGCOLOR=\"#ffffff\" VALIGN=\"top\">\n");
      out.print("<TD COLSPAN=\"2\" VALIGN=\"MIDDLE\" WIDTH=\"300\">\n");
      out.print("<IMG SRC=\"../tpcw/Images/whats_new.jpeg\""+ 
		" ALT=\"New Product\">\n");
      out.print("</TD>\n");
      out.print("<TD BGCOLOR=\"#ffffff\" WIDTH=\"100\"></TD>\n");
      out.print("<TD COLSPAN=\"2\" WIDTH=\"300\">\n");
      out.print("<IMG SRC=\"../tpcw/Images/best_seller.png\""+
		" ALT=\"Best Seller\"></TD></TR>\n");
      
      for(i = 0; i < column1.size(); i++){
	  out.print("<TR><TD><P ALIGN=\"center\">");
	  url = "TPCW_new_products_servlet";
	  url = url+"?subject=" + column1.elementAt(i);
	  if(SHOPPING_ID != null)
	      url = url + "&SHOPPING_ID=" + SHOPPING_ID;
	  if(C_ID != null)
	      url = url+"&C_ID=" + C_ID;
	  out.print("<A HREF=\"" +res.encodeUrl(url));
	  
	  out.print("\">" + column1.elementAt(i) + "</A></P></TD>\n");
	  url = "TPCW_new_products_servlet";
	  url = url+"?subject=" +column2.elementAt(i);
	  if(SHOPPING_ID != null)
	      url = url + "&SHOPPING_ID=" + SHOPPING_ID;
	  if(C_ID != null)
	      url = url+"&C_ID=" + C_ID;
	  
	  out.print("<TD><P ALIGN=\"center\"><A HREF=\""+ 
		    res.encodeUrl(url));
	  
	  
	  out.print("\">" + column2.elementAt(i)+"</A></P></TD>\n");
	  out.print("<TD BGCOLOR=\"#ffffff\" WIDTH=\"50\"></TD>\n");
	  out.print("<TD> <P ALIGN=\"center\">");
	  url = "TPCW_best_sellers_servlet";
	  url = url + "?subject=" + column1.elementAt(i);
	  if(SHOPPING_ID != null)
	      url = url + "&SHOPPING_ID=" + SHOPPING_ID;
	  if(C_ID != null)
	      url = url+"&C_ID=" + C_ID;
	  
	  out.print("<A HREF=\"" + res.encodeUrl(url));
	  
	  out.print("\">" + column1.elementAt(i) + "</A></P></TD>\n");
	  
	  url = "TPCW_best_sellers_servlet";
	  url = url + "?subject=" + column2.elementAt(i);
	  if(SHOPPING_ID != null)
	      url = url + "&SHOPPING_ID=" + SHOPPING_ID;
	  if(C_ID != null)
	      url = url+"&C_ID=" + C_ID;
	  
	  out.print("<TD><P ALIGN=\"center\"><A HREF=\"" + 
		    res.encodeUrl(url));
	  out.print("\">" + column2.elementAt(i)+"</A></P></TD>\n");
	  out.print("</TR>\n");
      }
      out.print("</TABLE>\n");
      
      //Generate shopping cart, search, and order status buttons.
      out.print("<P ALIGN=\"CENTER\">\n");
      url = "TPCW_shopping_cart_interaction";
      url = url + "?ADD_FLAG=N";
      if(SHOPPING_ID != null)
	  url = url + "&SHOPPING_ID=" + SHOPPING_ID;
      if(C_ID != null)
	  url = url+"&C_ID=" + C_ID;
      
      out.print("<A HREF=\"" + res.encodeUrl(url)); 

      out.print("\"><IMG SRC=\"../tpcw/Images/shopping_cart.png\"" +
		" ALT=\"Shopping Cart\"></A>\n");
      
      url = "TPCW_search_request_servlet";
      if(SHOPPING_ID != null){
	  url = url+"?SHOPPING_ID="+SHOPPING_ID;
	  if(C_ID!=null)
	      url = url + "&C_ID=" + C_ID;
      }
      else if(C_ID!=null)
	  url = url + "?C_ID=" + C_ID;
      
      out.print("<A HREF=\"" + res.encodeUrl(url));
      out.print("\"><IMG SRC=\"../tpcw/Images/search_B.gif\"" +
		" ALT=\"Search\"></A>\n");
      
      url = "TPCW_order_inquiry_servlet";
      if(SHOPPING_ID != null){
	  url = url+"?SHOPPING_ID="+SHOPPING_ID;
	  if(C_ID!=null)
	      url = url + "&C_ID=" + C_ID;
      }
      else if(C_ID!=null)
	  url = url + "?C_ID=" + C_ID;
      
      out.print("<A HREF=\"" + res.encodeUrl(url));
      
      out.print("\"><IMG SRC=\"../tpcw/Images/order_status.jpg\"" +
		" ALT=\"Order Status\"></A>\n");
      out.close();
      return;
  }
    
}

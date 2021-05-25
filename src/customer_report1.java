import java.sql.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

class customer_report1 extends JFrame
 {
  Connection cn=null;
  PreparedStatement st=null;
  ResultSet rs=null;
  customer_report1()
   {
    setVisible(true);
    setSize(500,500);
    Runtime r=Runtime.getRuntime();
    Process p=null;
    try
     { 
      Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
      st=cn.prepareStatement("select cno,cname,address,email_id,phno from customer_master"); 
      rs=st.executeQuery();
      //
      BufferedWriter br=new BufferedWriter(new FileWriter("/home/ramkrishna/Desktop/project/customer_report1.html"));
      br.write("<html>");
      br.write("<head>");
      br.write("<title>customer_report1</title>");
      br.write("</head>");
      br.write("<body bgcolor=#FFB6C1>");
      br.write("<h1><b><center><i>CUSTOMER MASTER REPORT</i></center></b></h1>");
      br.write("<center><table border=1></center>"); 
      br.write("<tr><th><b>CNO</b></th><th><b>CUSTOMER NAME</b></th><th><b>ADDRESS</b></th><th><b>EMAIL_ID</b></th><th><b>PHONE_NO</b></th></tr>");
      while(rs.next())
       {
        br.write("<tr>");
        br.write("<td>"+rs.getInt(1)+"</td>");
        br.write("<td>"+rs.getString(2)+"</td>");
        br.write("<td>"+rs.getString(3)+"</td>");
        br.write("<td>"+rs.getString(4)+"</td>");
        br.write("<td>"+rs.getString(5)+"</td>");
        br.write("</tr>");
       }
      br.write("</table>");
      br.write("</body>");
      br.write("</html>");
      br.close();
      p=r.exec("/usr/bin/google-chrome /home/ramkrishna/Desktop/project/customer_report1.html");
     }
    catch(Exception e)
     {
      System.out.println("exp="+e);
     }
   }
  public static void main(String argw[])
   {
    customer_report1 ct=new customer_report1();
   } 
 }  
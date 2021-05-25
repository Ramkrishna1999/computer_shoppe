import java.sql.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

 class stock_report //extends JFrame
  {
   Connection cn=null;
   PreparedStatement st=null;
   ResultSet rs=null;
   stock_report()
    {
     //setVisible(true);
     //setSize(500,500);
     Runtime r=Runtime.getRuntime();
     Process p=null;
     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st=cn.prepareStatement("select icode,iname,modelno,qty,rate from stock");
       rs=st.executeQuery();
      
       BufferedWriter br=new BufferedWriter(new FileWriter("/home/ramkrishna/Desktop/project/stock_report.html"));
       br.write("<html>");
       br.write("<head>");
       br.write("<title>stock_report</title>");
       br.write("</head>"); 
       br.write("<body bgcolor=#FFB6C1>");
       br.write("<h1><b><center><i>STOCK REPORT</i></center></b></h1>");
       br.write("<center><table border=1></center>");
       br.write("<tr><th><b>ICODE</b></th><th><b>ITEM NAME</b></th><th><b>MODEL NO</b></th><th><b>QUANTITY</b></th><th><b>RATE</b></th></tr>");
       while(rs.next())
        {
         br.write("<tr>");
         br.write("<td>"+rs.getInt(1)+"</td>");
         br.write("<td>"+rs.getString(2)+"</td>");
         br.write("<td>"+rs.getString(3)+"</td>");
         br.write("<td>"+rs.getInt(4)+"</td>");
         br.write("<td>"+rs.getInt(5)+"</td>");
         br.write("</tr>");
        }
       br.write("</table>");
       br.write("</body>");
       br.write("</html>");
       br.close();
       //C:/Program Files/Internet Explorer/iexplore
       p=r.exec("/usr/bin/google-chrome /home/ramkrishna/Desktop/project/stock_report.html");
      }
     catch(Exception e)
      {
        System.out.println("exp="+e);
      }
    }
   public static void main(String argw[])
    {
     stock_report vt=new stock_report ();
    }
  } 
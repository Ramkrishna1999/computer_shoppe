import java.sql.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

 class bill_report extends JFrame implements ActionListener
  {  
   JLabel  l1=new JLabel("Bill Number");
   JComboBox txbno=new JComboBox();
   JButton  b=new JButton("OK");
   Connection cn=null;
   PreparedStatement st=null;
   PreparedStatement st1=null;
   PreparedStatement st2=null;
   PreparedStatement st3=null;
   ResultSet rs=null;
   ResultSet rs2=null;
   ResultSet rs3=null;
   bill_report()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l1.setBounds( 200,250,150,30);
     jp.add(l1);
     txbno.setBounds(360,250,200,30);
     jp.add(txbno);
     b.setBounds(360,300,100,30);
     jp.add(b);
   
     setVisible(true);
     setSize(600,600);
     
     try
      {
      Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st=cn.prepareStatement("select  distinct bno from bill_order");
       rs=st.executeQuery();
       
       txbno.addItem("please select bill no");
       txbno.addItem("5");
       while(rs.next())
        {
            
         txbno.addItem(""+rs.getInt(1));
        
        }
      }
     catch(Exception ex)
      {
       JOptionPane.showMessageDialog(this,ex,"Error",JOptionPane.ERROR_MESSAGE);
      }
     txbno.addActionListener(this);
     b.addActionListener(this);
   }
  Runtime r=Runtime.getRuntime();
  Process p=null;
  public void actionPerformed(ActionEvent e)
   {  
    if(e.getSource()==b)
     { 
      try
       {   
        st1=cn.prepareStatement("select bill_order.bno,cname,bill_order.amt,bdate from bill_order,customer_master,bill_details,stock where customer_master.cno=bill_order.cno and bill_order.bno=bill_details.bno and stock.icode=bill_details.icode and bill_order.bno=?");
        String str=(String)txbno.getSelectedItem();   
        int vbno=Integer.parseInt(str);
        st1.setInt(1,vbno);
        rs2=st1.executeQuery();
        if(rs2.next())
         { 
              
          BufferedWriter br=new BufferedWriter(new FileWriter("/home/ramkrishna/Desktop/project/bill.html"));
   	  br.write("<html>");
  	  br.write("<head>");
   	  br.write("<title>Bill Report</title>");
  	  br.write("</head>");
   	  br.write("<body");
   	  //br.write("<Img src='f:/project/sony.jpg' align='left' />");
 	  br.write("<h1><b><center>BILL</center></b></h1>");
  	  br.write( "<center><b>Bill No&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b><input type='text'  value='"+rs2.getInt(1)+"'></center><br><br>");
  	  br.write( "<center><b>Customer name&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b><input type='text'  value='"+rs2.getString(2)+"'></center><br><br>");
	  br.write(" <center><b>Amount&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b><input type='text'  value='"+rs2.getInt(3)+"'></center><br><br>");
	  br.write(" <center><b>Date&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b><input type='text'  value='"+rs2.getString(4)+"'></center><br><br>");
 	  st3=cn.prepareStatement("select iname,modelno,bill_details.qty,rate from stock,bill_details,bill_order where stock.icode=bill_details.icode and bill_order.bno=bill_details.bno and bill_order.bno=?");
    	  st3.setInt(1,vbno);     
 	  rs3=st3.executeQuery();
 	  br.write("<h1><b><center>PURCHASE DETAILS</center></b></h1>");
  	  br.write("<center><table border=1></center>");
 	  br.write("<tr><th><b>ITEM NAME</b></th><th><b>MODEL NO</B></th><th><B>QUANTITY</B></th><th><B>RATE</B></th></tr>");
	  while(rs3.next())
	   {
	    br.write("<tr>");
  	    br.write("<td>"+rs3.getString(1)+"</td>");
  	    br.write("<td>"+rs3.getString(2)+"</td>");
 	    br.write("<td>"+rs3.getInt(3)+"</td>");
  	    br.write("<td>"+rs3.getInt(4)+"</td>");
  	    br.write("</tr>");
	   }
	  br.write("</table>");
 	  br.write("</body>");
  	  br.write("</html>");
 	  br.close();
	  p=r.exec("/usr/bin/google-chrome /home/ramkrishna/Desktop/project/bill.html");
	 }//if
	else
	 {
 	  BufferedWriter br1=new BufferedWriter(new FileWriter("/home/ramkrishna/Desktop/project/bill2.html"));
	  System.out.println("Record is not Available");
	  br1.write("Record is not Available");
	  br1.close();
	  p=r.exec("/usr/bin/google-chrome /home/ramkrishna/Desktop/project/bill2.html");
	 }
       }//try close();
      catch(Exception e1)
       {
  	System.out.println("exp="+e1);
       }
     }
   }
  public static void main(String args[])
   {
    bill_report b=new bill_report();
   }
 }
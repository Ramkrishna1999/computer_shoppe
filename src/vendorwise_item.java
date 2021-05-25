import java.sql.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

class vendorwise_item extends JFrame implements ActionListener
 {  
  JLabel  l1=new JLabel("Vendor Name");
  JComboBox txvname=new JComboBox();
  JButton  b=new JButton("OK");
  Connection cn=null;
  PreparedStatement st=null;
  PreparedStatement st1=null;
  PreparedStatement st2=null;
  ResultSet rs=null;
  ResultSet rs2=null;

  vendorwise_item()
   {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l1.setBounds( 200,250,100,30);
     jp.add(l1);
     txvname.setBounds(310,250,200,30);
     jp.add(txvname);
     b.setBounds(310,300,100,30);
     jp.add(b);   
     setVisible(true);
     setSize(500,500);
     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st=cn.prepareStatement("select  distinct vname  from vendor_master");
       rs=st.executeQuery();
       txvname.addItem("Please select vendor name");
       while(rs.next()) 
        {
         txvname.addItem(rs.getString(1));
        }
      }
     catch(Exception ex)
      {
       JOptionPane.showMessageDialog(this,ex,"Error",JOptionPane.ERROR_MESSAGE);
      }
     txvname.addActionListener(this);
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
         st1=cn.prepareStatement("select vname,stock.iname,purchase_details.qty,purchase_details.rate,purchase_order.amt from vendor_master,stock,purchase_details,purchase_order where vendor_master.vno=purchase_order.vno  and stock.icode=purchase_details.icode and vendor_master.vname=?");
         st1.setString(1,(String)txvname.getSelectedItem());
         rs2=st1.executeQuery();
         if(rs2.next())
          {
           BufferedWriter br=new BufferedWriter(new FileWriter("/home/ramkrishna/Desktop/Java/purchase.html"));
           br.write("<html>");
           br.write("<head>");
           br.write("<title>Purchase Report</title>");
           br.write("</head>");
           br.write("<body bgcolor=#FFB6C1>");
           br.write("<h1><b><center>VENDORWISE  REPORT</center></b></h1>");
           br.write("<center><b>Vendor name&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b><input type='text'  value='"+rs2.getString(1)+"'></center><br><br>");
           br.write("<center>");
           br.write("<table  border=1>");
           br.write("<tr>");
           br.write("<th>Item Name</th>");
           br.write("<th>Qty</th>");
           br.write("<th>Rate</th>");
           br.write("<th>Amount</th>");
           br.write("</tr>");
           while(rs2.next())
            {
             br.write("<tr>");
             br.write("<td>'"+rs2.getString(2)+"'</td>");
             br.write("<td>'"+rs2.getInt(3)+"'</td>");
             br.write("<td>'"+rs2.getInt(4)+"'</td>");
             br.write("<td>'"+rs2.getInt(5)+"'</td>");
             br.write("</tr>");
            }
           br.write("</center>");
           br.write("</body>");
           br.write("</html>");
           br.close();
           p=r.exec("usr/bin/google-chrome /home/ramkrishna/Desktop/purchase.html");
         }//if
        else
         {
          BufferedWriter br1=new BufferedWriter(new FileWriter("home/ramkrishna/Desktop/Java/purchase.html"));
          System.out.println("Record is not Available");
          br1.write("Record is not Available");
          br1.close();
          p=r.exec("usr/bin/google-chrome /home/ramkrishna/Desktop/Java/purchase.html");
         }
       }
      catch(Exception e1)
       {
        System.out.println("exp="+e1);
       }
     }
   }

  public static void main(String args[])
   {
    vendorwise_item vi=new vendorwise_item();
   }
 } 
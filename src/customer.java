import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

 class customer extends JFrame implements ActionListener
  {
   JLabel l8=new JLabel("CUSTOMER MASTER");
   JLabel l1=new JLabel("Customer number");
   JLabel l2=new JLabel("Customer Name");
   JLabel l3=new JLabel("Enquiry number");
   JLabel l4=new JLabel("Address");
   JLabel l5=new JLabel("City");
   JLabel l6=new JLabel("Email_id");
   JLabel l7=new JLabel("Phone no");
   JTextField txcno=new JTextField(20);
   JComboBox cbname=new JComboBox();
   JTextField txeno=new JTextField(20);
   	  JTextField txcname=new JTextField(20);
   JTextField txcadd=new JTextField(20);
   JTextField txcity=new JTextField(20);
   JTextField txemail=new JTextField(20);
   JTextField txcpn=new JTextField(30);
   JButton bnew=new JButton("New");
   JButton bsave=new JButton("Save");
   JButton bupdate=new JButton("Update");
   JButton bdelete=new JButton("Delete");
   JButton bsearch=new JButton("Search");
   JButton bexit=new JButton("Exit");
   Connection cn=null;
   PreparedStatement st=null;
   PreparedStatement st1=null;
   PreparedStatement st2=null;
   ResultSet rs2=null;
   ResultSet rs=null;
   ResultSet rs1=null;
   Statement str1=null;
   customer()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l8.setBounds(210,30,250,30);
     jp.add(l8);
     l1.setBounds(110,100,170,30);
     jp.add(l1);
     txcno.setBounds(320,100,180,30);
     jp.add(txcno);
     l2.setBounds(110,150,150,30);
     jp.add(l2);
     cbname.setBounds(320,150,180,30);
     jp.add(cbname);
     l3.setBounds(110,200,150,30);
     jp.add(l3);
     txeno.setBounds(320,200,180,30);
     jp.add(txeno);
     l4.setBounds(110,250,100,30);
     jp.add(l4);
     txcadd.setBounds(320,250,180,30);
     jp.add(txcadd);
     l5.setBounds(110,300,100,30);
     jp.add(l5);
     txcity.setBounds(320,300,180,30);
     jp.add(txcity);
     l6.setBounds(110,350,100,30);
     jp.add(l6);
     txemail.setBounds(320,350,180,30);
     jp.add(txemail);
     l7.setBounds(110,400,100,30);
     jp.add(l7);
     txcpn.setBounds(320,400,180,30);
     jp.add(txcpn);
     jp.setBackground(Color.pink);
     JPanel p1=new JPanel();
     p1.setLayout(new GridLayout(1,5));
     p1.add(bnew);
     p1.add(bsave);
     p1.add(bsearch);
     p1.add(bupdate);
     p1.add(bdelete);
     p1.setBounds(40,470,550,30);
     bexit.setBounds(270,500,90,30);
     jp.add(bexit);
     jp.add(p1);
     setSize(600,600);
     setVisible(true);
     l8.setFont(new Font("Times New Roman",Font.BOLD,20));
     l1.setFont(new Font("Times New Roman",Font.BOLD,16));
     l2.setFont(new Font("Times New Roman",Font.BOLD,16));
     l3.setFont(new Font("Times New Roman",Font.BOLD,16));
     l4.setFont(new Font("Times New Roman",Font.BOLD,16));
     l5.setFont(new Font("Times New Roman",Font.BOLD,16));
     l6.setFont(new Font("Times New Roman",Font.BOLD,16));
     l7.setFont(new Font("Times New Roman",Font.BOLD,16));
     txcno.setFont(new Font("Times New Roman",Font.BOLD,16));
     cbname.setFont(new Font("Times New Roman",Font.BOLD,16));
     txeno.setFont(new Font("Times New Roman",Font.BOLD,16));
     txcadd.setFont(new Font("Times New Roman",Font.BOLD,16));
     txcity.setFont(new Font("Times New Roman",Font.BOLD,16));
     txemail.setFont(new Font("Times New Roman",Font.BOLD,16));
     txcpn.setFont(new Font("Times New Roman",Font.BOLD,16));
     bsave.setFont(new Font("Times New Roman",Font.BOLD,18));
     bnew.setFont(new Font("Times New Roman",Font.BOLD,18));
     bdelete.setFont(new Font("Times New Roman",Font.BOLD,18));
     bupdate.setFont(new Font("Times New Roman",Font.BOLD,18));
     bexit.setFont(new Font("Times New Roman",Font.BOLD,18));
     bsearch.setFont(new Font("Times New Roman",Font.BOLD,18));
     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st=cn.prepareStatement("select  ename  from enquiry");
       rs=st.executeQuery();
       while(rs.next())
        {
         cbname.addItem(rs.getString(1));
        }
      }//try
     catch(Exception e)
      {
       JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
     bnew.addActionListener(this);
     bsave.addActionListener(this);
     bsearch.addActionListener(this);
     bupdate.addActionListener(this);
     bdelete.addActionListener(this);
     bexit.addActionListener(this);
     cbname.addActionListener(this);
     try
      {   
       st1=cn.prepareStatement("select enq_no,address,city,email_id ,phno from enquiry where ename=?");
       st1.setString(1,(String)cbname.getSelectedItem());
       rs=st1.executeQuery();
       rs.next();
      }//try
     catch(Exception e6)
      {
       System.out.println("exp="+e6);
      }//catch
    }//constructor
   public void actionPerformed(ActionEvent e1)
    {
     if (e1.getSource()==bnew)
       New();
     else if(e1.getSource()==bupdate)
       Update();
     else if(e1.getSource()==bsave)
       Save();
     else if(e1.getSource()==bexit)
       dispose();
     else if(e1.getSource()==cbname)
       Name();
     else if(e1.getSource()==bdelete)
       Delete();
     else if(e1.getSource()==bsearch)
       Search();
    }//action performed
   void New()
    {
     int no;
     txcno.setText("");
     txeno.setText("");
     txcadd.setText("");
     txcity.setText("");
     txemail.setText("");
     txcpn.setText("");
     txeno.requestFocus();
     try
      {
       str1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       rs=str1.executeQuery("select * from customer_master");
       if (!rs.next())
   	 no=1;
       else
        {
         rs.last();
         no=rs.getInt(1)+1;
        }//else
       txcno.setText(""+no);
      }//try
     catch(Exception e4)
      {
  	JOptionPane.showMessageDialog(this,e4,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
    }//new
   void Save()
    {
     int vno,veno;
     String vadd,vcity,vemail,vcbname,vphno;
     vno=Integer.parseInt(txcno.getText());
     vcbname=(String)cbname.getSelectedItem();
     veno=Integer.parseInt(txeno.getText());
     vadd=txcadd.getText();
     vcity=txcity.getText();
     vemail=txemail.getText();
     //vphno=Integer.parseInt(txcpn.getText());
     vphno=txcpn.getText();
     try
      {
       String sql="Insert into customer_master(cno,cname,enq_no,address,city,email_id,phno)values(?,?,?,?,?,?,?)";
       st=cn.prepareStatement(sql);
       st.setInt(1,vno);
       st.setString(2,vcbname);
       st.setInt(3,veno);
       st.setString(4,vadd);
       st.setString(5,vcity);
       st.setString(6,vemail);
       st.setString(7,vphno);
       st.executeUpdate();
       JOptionPane.showMessageDialog(this,"Record is save","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e5)
      {
       JOptionPane.showMessageDialog(this,e5,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Update()
    {
     int vno,veno;
     String vadd,vcity,vemail,vphno;
     vno=Integer.parseInt(txcno.getText());
     veno=Integer.parseInt(txeno.getText());
     vadd=txcadd.getText();
     vcity=txcity.getText();
     vemail=txemail.getText();
     vphno=txcpn.getText();
     try
      {
       String sql="Update customer_master set enq_no=?,address=?,city=?,email_id=?,phno=? where cno=?";
       st=cn.prepareStatement(sql);
       st.setInt(1,veno);
       st.setString(2,vadd);
       st.setString(3,vcity);
       st.setString(4,vemail); 
       st.setString(5,vphno);
       st.setInt(6,vno);
       st.executeUpdate();
       JOptionPane.showMessageDialog(this,"Record is modify","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e6)
      {
       JOptionPane.showMessageDialog(this,e6,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Name()
    {
     String vname;  
     vname=(String)cbname.getSelectedItem();
     try
      {
       String sql="select enq_no ,address, city,email_id,phno from enquiry where ename =?";
       st2=cn.prepareStatement(sql);   
       st2.setString(1,vname);
       rs2=st2.executeQuery();
       rs2.next();
       txeno.setText(Integer.toString(rs2.getInt(1)));
       txcadd.setText(rs2.getString(2));
       txcity.setText(rs2.getString(3));
       txemail.setText(rs2.getString(4));
       txcpn.setText(rs2.getString(5));
      }
     catch(Exception e)
      {
       JOptionPane.showMessageDialog(this,e,"error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Delete()
    {
     int vno,veno;
     String vadd,vcity,vemail,vphno;
     vno=Integer.parseInt(txcno.getText());
     try
      {
       String sql="Delete from customer_master where cno=?";
       st=cn.prepareStatement(sql);
       st.setInt(1,vno);
       st.executeUpdate();
       JOptionPane.showMessageDialog(this,"Record is Deleted","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e7)
      {
       JOptionPane.showMessageDialog(this,e7,"Error",JOptionPane.ERROR_MESSAGE);
       txcno.setText("");
       txeno.setText("");
       txcadd.setText("");
       txcity.setText("");
       txemail.setText("");
       txcpn.setText("");
      }
    }
   void Search()
    {
     try
      {
       int vno=Integer.parseInt(txcno.getText());
       st=cn.prepareStatement("select * from customer_master where cno=?");
       st.setInt(1,vno);
       rs=st.executeQuery();
       rs.next();
       txcno.setText(Integer.toString(rs.getInt(1)));
       cbname.setSelectedItem(rs.getString(2));
       txeno.setText(Integer.toString(rs.getInt(2)));
       txcadd.setText(rs.getString(3));
       txcity.setText(""+rs.getString(4));
       txemail.setText(""+rs.getString(5));
       txcpn.setText(""+rs.getString(6));
      }
     catch(Exception e8)
      {
       JOptionPane.showMessageDialog(this,e8,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   public static void main(String args[])
    {
     customer c=new customer();
    }
  }      
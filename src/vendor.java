import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

 class vendor extends JFrame implements ActionListener
  {
   JLabel l7=new JLabel("VENDOR MASTER"); 
   JLabel l1=new JLabel("Vendor number");
   JLabel l2=new JLabel("Vendor Name");
   JLabel l3=new JLabel("Vendor address");
   JLabel l4=new JLabel("City");
   JLabel l5=new JLabel("Email_id");
   JLabel l6=new JLabel("Phone no");
   JTextField txvno=new JTextField(20);
   JTextField txvname=new JTextField(20);
   JTextField txvadd=new JTextField(20);
   JTextField txcity=new JTextField(20);
   JTextField txemail=new JTextField(20);
   JTextField txvpn=new JTextField(20);
   JButton bnew=new JButton("New");
   JButton bsave=new JButton("Save");
   JButton bupdate=new JButton("Update");
   JButton bdelete=new JButton("Delete");
   JButton bsearch=new JButton("Search");
   JButton bexit=new JButton("Exit");
   JButton bfirst=new JButton("First");
   JButton bprev=new JButton("Prev"); 
   JButton bnext=new JButton("Next");
   JButton blast=new JButton("Last");
   Connection cn=null;
   PreparedStatement st=null;
   Statement st1=null;
   ResultSet rs1=null;
   ResultSet rs=null;
   vendor()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l7.setBounds(210,30,250,30);
     jp.add(l7);
     l1.setBounds(110,100,180,30);
     jp.add(l1);
     txvno.setBounds(320,100,190,30);
     jp.add(txvno);
     l2.setBounds(110,150,180,30);
     jp.add(l2);
     txvname.setBounds(320,150,190,30);
     jp.add(txvname);
     l3.setBounds(110,200,180,30);
     jp.add(l3);
     txvadd.setBounds(320,200,190,30);
     jp.add(txvadd);
     l4.setBounds(110,250,180,30);
     jp.add(l4);
     txcity.setBounds(320,250,190,30);
     jp.add(txcity);
     l5.setBounds(110,300,180,30);
     jp.add(l5);
     txemail.setBounds(320,300,190,30);
     jp.add(txemail);
     l6.setBounds(110,350,180,30);
     jp.add(l6);
     txvpn.setBounds(320,350,190,30);
     jp.add(txvpn);
     jp.setBackground(Color.pink);
     JPanel p1=new JPanel();
     p1.setLayout(new GridLayout(2,5));
     p1.add(bnew);
     p1.add(bsave);
     p1.add(bsearch);
     p1.add(bupdate);
     p1.add(bdelete);
     p1.add(bexit);
     p1.add(bfirst);
     p1.add(bnext);
     p1.add(bprev);
     p1.add(blast);
     p1.setBounds(50,420,550,60);
     jp.add(p1);
     setSize(650,650);
     setVisible(true);
     l7.setFont(new Font("Times New Roman",Font.BOLD,22));
     l1.setFont(new Font("Times New Roman",Font.BOLD,16));
     l2.setFont(new Font("Times New Roman",Font.BOLD,16));
     l3.setFont(new Font("Times New Roman",Font.BOLD,16));
     l4.setFont(new Font("Times New Roman",Font.BOLD,16));
     l5.setFont(new Font("Times New Roman",Font.BOLD,16));
     l6.setFont(new Font("Times New Roman",Font.BOLD,16));
     txvno.setFont(new Font("Times New Roman",Font.BOLD,16));
     txvname.setFont(new Font("Times New Roman",Font.BOLD,16));
     txvadd.setFont(new Font("Times New Roman",Font.BOLD,16));
     txcity.setFont(new Font("Times New Roman",Font.BOLD,16));
     txemail.setFont(new Font("Times New Roman",Font.BOLD,16));
     txvpn.setFont(new Font("Times New Roman",Font.BOLD,16));
     bsave.setFont(new Font("Times New Roman",Font.BOLD,18));
     bnew.setFont(new Font("Times New Roman",Font.BOLD,18));
     bdelete.setFont(new Font("Times New Roman",Font.BOLD,18));
     bfirst.setFont(new Font("Times New Roman",Font.BOLD,18));
     bnext.setFont(new Font("Times New Roman",Font.BOLD,18));
     bupdate.setFont(new Font("Times New Roman",Font.BOLD,18));
     bprev.setFont(new Font("Times New Roman",Font.BOLD,18));
     blast.setFont(new Font("Times New Roman",Font.BOLD,18));
     bexit.setFont(new Font("Times New Roman",Font.BOLD,18));
     bsearch.setFont(new Font("Times New Roman",Font.BOLD,18));
     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
       rs1=st1.executeQuery("select * from vendor_master");
      }
     catch(Exception e1)
      {
       JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
      }
     bnew.addActionListener(this);
     bsave.addActionListener(this);
     bsearch.addActionListener(this);
     bupdate.addActionListener(this);
     bdelete.addActionListener(this);
     bexit.addActionListener(this);
     bfirst.addActionListener(this);
     bprev.addActionListener(this);
     bnext.addActionListener(this);
     blast.addActionListener(this);
     addWindowListener(new W());
    }
   class W extends WindowAdapter
    {
     public void windowClosing(WindowEvent e)
      {
       dispose();
       //System.exit(0);
      }
    }
   public void actionPerformed(ActionEvent e)
    {
     try
      {
       try
        {
         if((e.getSource()==bfirst)||(e.getSource()==bnext)||(e.getSource()==bprev)||(e.getSource()==blast))
          {
  	   if(e.getSource()==bfirst)
	    {
	     rs1.first();
	    }
 	   else if(e.getSource()==bnext)
	    {
	     rs1.next();
	     if(rs1.isAfterLast())
		rs1.last();
	    }
	   else if(e.getSource()==blast)
	    {
	     rs1.last();
	    }
	   else if(e.getSource()==bprev)
	    {
	     rs1.previous();
	     if(rs1.isBeforeFirst())
	 	rs1.first();
            }
	   txvno.setText(Integer.toString(rs1.getInt(1)));
	   txvname.setText(rs1.getString(2));
	   txvadd.setText(rs1.getString(3));
	   txcity.setText(rs1.getString(4));
	   txemail.setText(rs1.getString(5));
	   txvpn.setText(rs1.getString(6));
	  }
	}
       catch(Exception e2)
        {
	  JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
	}
       if(e.getSource()==bnew)
	 New();
       else if(e.getSource()==bsave)
	 Save();
       else if(e.getSource()==bupdate)
	 Update();
       else if(e.getSource()==bdelete)
	 Delete();
       else if(e.getSource()==bsearch)
	 Search();
       else if(e.getSource()==bexit)
	 dispose();
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void New()
    {
     int no;
     txvname.setText("");
     txvadd.setText("");
     txcity.setText("");
     txemail.setText("");
     txvpn.setText("");
     txvname.requestFocus();
     try
      {
       st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       rs=st1.executeQuery("select * from vendor_master");
       if(rs==null)
	 no=1;
       else
 	{
         rs.last();
	 no=rs.getInt(1)+1;
	}
       txvno.setText(""+no);
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Save()
    {
     int vvno;
     String vname,vadd,vcity,vemail,vpno;
     vvno=Integer.parseInt(txvno.getText());
     vname=txvname.getText();
     vadd=txvadd.getText();
     vcity=txcity.getText();
     vemail=txemail.getText();
     vpno=txvpn.getText();
     try
      {
       String sql="Insert into vendor_master(vno,vname,address,city,email_id,phno)values(?,?,?,?,?,?)";
       st=cn.prepareStatement(sql);
       st.setInt(1,vvno);
       st.setString(2,vname);
       st.setString(3,vadd);
       st.setString(4,vcity);
       st.setString(5,vemail);
       st.setString(6,vpno);
       st.executeUpdate();
       JOptionPane.showMessageDialog(this,"Record is save","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Search()
    {
     try
      {
       int vno=Integer.parseInt(txvno.getText());
       st=cn.prepareStatement("select * from vendor_master where vno=?");
       st.setInt(1,vno);
       rs=st.executeQuery();
       rs.next();
       txvno.setText(Integer.toString(rs.getInt(1)));
       txvname.setText(rs.getString(2)); 
       txvadd.setText(rs.getString(3));
       txcity.setText(""+rs.getString(4));
       txemail.setText(""+rs.getString(5));
       txvpn.setText(""+rs.getString(6));
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Update()
    {
     int vvno;
     String vname,vadd,vcity,vemail,vpno;
     vvno=Integer.parseInt(txvno.getText());
     vname=txvname.getText();
     vadd=txvadd.getText();
     vcity=txcity.getText();
     vemail=txemail.getText();
     vpno=txvpn.getText();
     try
      {
	String sql="Update vendor_master set vname=?,address=?,city=?,email_id=?,phno=? where vno=?";
	st=cn.prepareStatement(sql);
	st.setString(1,vname);
	st.setString(2,vadd);
	st.setString(3,vcity);
	st.setString(4,vemail);
	st.setString(5,vpno);
	st.setInt(6,vvno);
	st.executeUpdate();
	JOptionPane.showMessageDialog(this,"Record is modify","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   void Delete()
    {
     int vno;
     String vname,vadd,vcity,vemail,vpno;
     vno=Integer.parseInt(txvno.getText());
     try
      {
       String sql="Delete from vendor_master where vno=?";
       st=cn.prepareStatement(sql);
       st.setInt(1,vno);
       st.executeUpdate();
       JOptionPane.showMessageDialog(this,"Record is Deleted","Msg",JOptionPane.INFORMATION_MESSAGE);
      }
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }
   public static void main(String args[])
    {
     vendor v=new vendor();
    }
  }
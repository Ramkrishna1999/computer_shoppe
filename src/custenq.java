import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

 class custenq extends JFrame implements ActionListener
  {
   JLabel l7=new JLabel("CUSTOMER ENQUIRY");
   JLabel l1=new JLabel("Enquiry number");
   JLabel l2=new JLabel("Enquirer Name");
   JLabel l3=new JLabel("Address");
   JLabel l4=new JLabel("City");
   JLabel l5=new JLabel("Email_id");
   JLabel l6=new JLabel("Phone no");

   JTextField txeno=new JTextField(20);
   JTextField txename=new JTextField(20);
   JTextField txeadd=new JTextField(20);
   JTextField txecity=new JTextField(20);
   JTextField txemail=new JTextField(20);
   JTextField txepn=new JTextField(30);

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
   ResultSet rs=null;
   ResultSet rs1=null;
   Statement st1=null;

   custenq()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l7.setBounds(170,20,200,30);
     jp.add(l7);
     l1.setBounds(110,80,150,30);
     jp.add(l1);
     txeno.setBounds(280,80,180,30); 
     jp.add(txeno);
     l2.setBounds(110,120,150,30);
     jp.add(l2);
     txename.setBounds(280,120,180,30);
     jp.add(txename);
     l3.setBounds(110,160,150,30);
     jp.add(l3);
     txeadd.setBounds(280,160,180,30);
     jp.add(txeadd);
     l4.setBounds(110,200,150,30);
     jp.add(l4);
     txecity.setBounds(280,200,180,30);
     jp.add(txecity);
     l5.setBounds(110,240,150,30);
     jp.add(l5);
     txemail.setBounds(280,240,180,30);
     jp.add(txemail);
     l6.setBounds(110,280,150,30);
     jp.add(l6);
     txepn.setBounds(280,280,180,30);
     jp.add(txepn);

     JPanel p1=new JPanel();
     p1.setLayout(new GridLayout(2,4,1,10));
     p1.setBackground(Color.PINK);
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
     p1.setBounds(40,350,450,60);
     jp.add(p1);
     jp.setBackground(Color.pink);
     setSize(550,550);
     setTitle("Customer Enquiry");
     setVisible(true);
     l1.setFont(new Font("Times New Roman",Font.BOLD,16));
     l2.setFont(new Font("Times New Roman",Font.BOLD,16));
     l3.setFont(new Font("Times New Roman",Font.BOLD,16));
     l4.setFont(new Font("Times New Roman",Font.BOLD,16));
     l5.setFont(new Font("Times New Roman",Font.BOLD,16));
     l6.setFont(new Font("Times New Roman",Font.BOLD,16));
     l7.setFont(new Font("Times New Roman",Font.BOLD,16));
     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
       rs1=st1.executeQuery("select * from enquiry");
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
//       System.exit(0);
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
	    }
	   txeno.setText(Integer.toString(rs1.getInt(1)));
	   txename.setText(rs1.getString(2));
	   txeadd.setText(rs1.getString(3));
	   txecity.setText(""+rs1.getString(4));
	   txemail.setText(""+rs1.getString(5));
	   txepn.setText(""+rs1.getString(6));
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
     txeno.setText("");
     txename.setText("");
     txeadd.setText("");
     txecity.setText("");
     txemail.setText("");
     txepn.setText("");
     txename.requestFocus();
     try
      {
       st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       rs=st1.executeQuery("select * from enquiry");
       if(rs==null)
         no=1;
       else
	{
	 rs.last();
	 no=rs.getInt(1)+1;
	}
       txeno.setText(""+no);
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }

   void Save()
    {
     int veno;
     String vname,vadd,vcity,vemail,vphno;
     veno=Integer.parseInt(txeno.getText());
     vname=txename.getText();
     vadd=txeadd.getText();
     vcity=txecity.getText();
     vemail=txemail.getText();
     vphno=txepn.getText();
     try
      {
       String sql="Insert into enquiry(enq_no,ename,address,city,email_id,phno)values(?,?,?,?,?,?)";
       st=cn.prepareStatement(sql);
       st.setInt(1,veno);
       st.setString(2,vname);
       st.setString(3,vadd);
       st.setString(4,vcity);
       st.setString(5,vemail);
       st.setString(6,vphno);
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
       int veno=Integer.parseInt(txeno.getText());
       st=cn.prepareStatement("select * from enquiry where enq_no=?");
       st.setInt(1,veno);
       rs=st.executeQuery();
       rs.next();
       txeno.setText(Integer.toString(rs.getInt(1)));
       txename.setText(rs.getString(2));
       txeadd.setText(rs.getString(3));
       txecity.setText(""+rs.getString(4));
       txemail.setText(""+rs.getString(5));
       txepn.setText(""+rs.getString(6));
      }
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }

   void Update()
    {
     int veno;
     String vname,vadd,vcity,vemail,vphno;
     veno=Integer.parseInt(txeno.getText()); 
     vname=txename.getText();
     vadd=txeadd.getText();
     vcity=txecity.getText();
     vemail=txemail.getText();
     vphno=txepn.getText();
     try
      {
       String sql="Update enquiry set ename=?,address=?,city=?,email_id=?,phno=? where enq_no=?";
       st=cn.prepareStatement(sql);
       st.setInt(1,veno);
       st.setString(2,vname);
       st.setString(3,vadd);
       st.setString(4,vcity);
       st.setString(5,vemail);
       st.setString(6,vphno);
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
     int veno;
     String vname,vadd,vcity,vemail,vphno;
     veno=Integer.parseInt(txeno.getText());
     try
      {
	String sql="Delete from enquiry where enq_no=?";
	st=cn.prepareStatement(sql);
	st.setInt(1,veno);
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
     custenq enq=new custenq();
    }

  }
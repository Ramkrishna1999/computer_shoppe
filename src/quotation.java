import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.io.*;
import java.util.*;

 class quotation extends JFrame implements ActionListener
  {
   JLabel l1=new JLabel("Quotation");
   JLabel l2=new JLabel("Quotation No");
   JLabel l3=new JLabel("Quotation Date");
   JLabel l4=new JLabel("Enquirer Name"); 
   JLabel l5=new JLabel("Item name");
   JLabel l6=new JLabel("Model no");
   JLabel l7=new JLabel("Rate");
   JLabel l8=new JLabel("Amount"); 
   JTextField txqno=new JTextField(20);
   JTextField txqdate=new JTextField(20);
   JComboBox cmben =new JComboBox();
   JComboBox cmbin =new JComboBox();
   JComboBox cmbmno =new JComboBox();
   JTextField txrate=new JTextField(20);
   JTextField txamt=new JTextField(20);
   PreparedStatement st=null;
   ResultSet rs=null;
   Connection cn=null;
   JButton bnew=new JButton("New");
   JButton bsave=new JButton("Save");
   JButton bdelete=new JButton("Delete");
   JButton bsearch=new JButton("Search");
   JButton bexit=new JButton("Exit");
   JButton bclr=new JButton("Clear");
   JButton ba=new JButton("Add");
   Vector data=new Vector();
   Vector head=new Vector();
   Vector hd=new Vector();
   JTable tb;
 
   public quotation()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     jp.setBackground(Color.pink);
     l1.setBounds(110,30,250,30);
     jp.add(l1);
     l2.setBounds(110,100,180,30);
     jp.add(l2);
     txqno.setBounds(270,100,140,30);
     jp.add(txqno);
     l3.setBounds(110,150,180,30);
     jp.add(l3);
     txqdate.setBounds(270,150,140,30);
     jp.add(txqdate);
     l4.setBounds(110,200,180,30);
     jp.add(l4);
     cmben.setBounds(270,200,140,30);
     jp.add(cmben);
     l5.setBounds(110,250,100,30);
     jp.add(l5);
     cmbin.setBounds(270,250,140,30);
     jp.add(cmbin);
     l6.setBounds(110,300,100,30);
     jp.add(l6);
     cmbmno.setBounds(270,300,140,30);
     jp.add(cmbmno);
     l7.setBounds(110,350,100,30);
     jp.add(l7);
     txrate.setBounds(270,350,140,30);
     jp.add(txrate);
     l8.setBounds(110,400,100,30);
     jp.add(l8);
     txamt.setBounds(270,400,140,30);
     jp.add(txamt);

     try
      {
       Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st=cn.prepareStatement("select  iname  from stock");
       rs=st.executeQuery();
       while(rs.next())
        {
    	 cmbin.addItem(rs.getString(1));
        }//while
       st=cn.prepareStatement("select  ename  from enquiry");
       rs=st.executeQuery();
       while(rs.next())
        {
     	 cmben.addItem(rs.getString(1));
    	}//while
      }//try
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch

     JPanel p1=new JPanel();
     p1.setLayout(new GridLayout(1,3));
     p1.add(bnew);
     p1.add(bsave);
     p1.add(bsearch);
     p1.setBounds(130,500,340,30);
     jp.add(p1);
     JPanel p2=new JPanel();
     p2.setLayout(new GridLayout(1,2));
     p2.add(bdelete);
     p2.add(bexit);
     p2.setBounds(180,530,220,30);
     jp.add(p2);

     l1.setFont(new Font("Times New Roman",Font.BOLD,20));
     l2.setFont(new Font("Times New Roman",Font.BOLD,16));
     l3.setFont(new Font("Times New Roman",Font.BOLD,16));
     l4.setFont(new Font("Times New Roman",Font.BOLD,16));
     l5.setFont(new Font("Times New Roman",Font.BOLD,16));
     l6.setFont(new Font("Times New Roman",Font.BOLD,16));
     l7.setFont(new Font("Times New Roman",Font.BOLD,16));
     l8.setFont(new Font("Times New Roman",Font.BOLD,16));
     txqno.setFont(new Font("Times New Roman",Font.BOLD,16));
     txqdate.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmben.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmbin.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmbmno.setFont(new Font("Times New Roman",Font.BOLD,16));
     txrate.setFont(new Font("Times New Roman",Font.BOLD,16));
     txamt.setFont(new Font("Times New Roman",Font.BOLD,16));

     bsave.setFont(new Font("Times New Roman",Font.BOLD,18)); 
     bnew.setFont(new Font("Times New Roman",Font.BOLD,18));
     bdelete.setFont(new Font("Times New Roman",Font.BOLD,18));
     bexit.setFont(new Font("Times New Roman",Font.BOLD,18));
     bsearch.setFont(new Font("Times New Roman",Font.BOLD,18));
     bclr.setFont(new Font("Times New Roman",Font.BOLD,18));
     ba.setFont(new Font("Times New Roman",Font.BOLD,18));

     setVisible(true);
     setSize(1200,1200);
     ba.setBounds(630,420,90,30);
     bclr.setBounds(750,420,90,30);
     jp.add(ba);
     jp.add(bclr);
     head.add("Quotation no");
     head.add("Item Name");
     head.add("Model no");
     head.add("Rate");

     tb=new JTable(data,head);
     JScrollPane js=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     JPanel j1=new JPanel();
     j1.setLayout(new FlowLayout());
     j1.setBounds(480,100,500,300);
     j1.add(js);
     jp.add(j1);

     cmbin.addActionListener(this);
     bnew.addActionListener(this);
     bsave.addActionListener(this);
     bsearch.addActionListener(this);
     bdelete.addActionListener(this);
     bexit.addActionListener(this);
     bclr.addActionListener(this);
     ba.addActionListener(this);
     addWindowListener(new W());
    }//constructor
   class W extends WindowAdapter
    {
     public void windowClosing(WindowEvent e)
      {
       dispose();
       System.exit(0);
      }
    }
   public void actionPerformed(ActionEvent e)
    {
     if(e.getSource()==cmbin)
       ss();
     else if(e.getSource()==bexit)
       dispose();
     else if(e.getSource()==bnew)
       New();
     else if(e.getSource()==ba)
       add();
     else if(e.getSource()==bclr)
       Clear();
     else if(e.getSource()==bsave)
       Save();
     else if(e.getSource()==bsearch)
       Search();
     else if(e.getSource()==bdelete)
       Delete();
    }//actionPerformed

   void ss()
    {
     String in=((String)cmbin.getSelectedItem());
     try
      {
       PreparedStatement st=null;
       ResultSet rs=null;
       st=cn.prepareStatement("select modelno from stock where iname=?");
       st.setString(1,in);
       rs=st.executeQuery();
       while(rs.next())
	{
 	 String mn=rs.getString(1);
	 cmbmno.addItem(mn);
	}//while
      }//try
     catch(Exception e7)
      {
       JOptionPane.showMessageDialog(this,e7,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
    }//fun

   void New()
    {
     int no;
     Statement st=null;
     ResultSet rs=null;
     txqno.setText("");
     txqdate.setText("");
     txrate.setText("");
     txamt.setText("");
     txqdate.requestFocus();
     try
      {
	st=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	rs=st.executeQuery("select * from quotation_header");
	if(!rs.next())
	  no=1;
	else
	  {
	   rs.last();
	   no=rs.getInt(1)+1;
	  }
	 txqno.setText(""+no);
      }
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }

   void add()
    {
     Vector temp=new Vector();
     temp.add(txqno.getText());
     temp.add((String)cmbin.getSelectedItem());
     temp.add((String)cmbmno.getSelectedItem());
     temp.add(txrate.getText());
     data.add(temp);
     DefaultTableModel
     model=(DefaultTableModel)tb.getModel();
     model.newDataAvailable(null);
    }//add

   void Clear()
    {
     data.removeAllElements();
     DefaultTableModel model=(DefaultTableModel)tb.getModel();
     model.newDataAvailable(null);
    }

   void Save()
    {
     try
      {
       PreparedStatement st1=null;
       PreparedStatement st2=null;
       ResultSet rs2=null;
       int eno;
       st=cn.prepareStatement("select enq_no from enquiry where ename = ?");
       st.setString(1,((String)cmben.getSelectedItem()));
       rs=st.executeQuery();
       rs.next();
       eno=(rs.getInt(1));
       st=cn.prepareStatement("Insert into quotation_header values(?,?,?,?)");
    
       st.setInt(1,Integer.parseInt(txqno.getText()));
       st.setInt(2,eno);
       st.setString(3,(txqdate.getText()));
       st.setInt(4,Integer.parseInt(txamt.getText()));
       st.executeUpdate();
       st1=cn.prepareStatement("Insert into quotation_details values(?,?,?)");
    
       for(int i=0;i<tb.getRowCount();i++)
        {
         st1.setInt(1,Integer.parseInt(""+ tb.getValueAt(i,0)));
         int vicode;
         String vstrin=(String)tb.getValueAt(i,1);
         String vstrmno=(String)tb.getValueAt(i,2);
         st2=cn.prepareStatement("select icode from stock where iname=? and modelno=?");
         st2.setString(1,vstrin);
         st2.setString(2,vstrmno);
         rs2=st2.executeQuery();
         rs2.next();  
         vicode=rs2.getInt(1);
         st1.setInt(2,vicode);
         st1.setInt(3,Integer.parseInt(""+tb.getValueAt(i,3)));
         st1.executeUpdate();
        }//for
      }//try
     catch(Exception e1)
      {
        JOptionPane.showMessageDialog(this,e1,"Msg",JOptionPane.INFORMATION_MESSAGE);
      }//catch
     JOptionPane.showMessageDialog(this,"record is saved","Msg",JOptionPane.INFORMATION_MESSAGE);
    }//save

   void Search()
    {
     try
      {
        PreparedStatement st7=null;
	ResultSet rs7=null;
	st7=cn.prepareStatement("select * from  quotation_header where qno=?");
	st7.setInt(1,Integer.parseInt(txqno.getText()));
	rs7=st7.executeQuery();
	rs7.next();
	txqno.setText(Integer.toString(rs7.getInt(1)));
	int eno;
	PreparedStatement st12=cn.prepareStatement("select ename from enquiry where enq_no=?");
	st12.setInt(1,rs7.getInt(2));
	ResultSet rs12=null;
	rs12=st12.executeQuery();
	rs12.next();
	cmben.setSelectedItem(rs12.getString(1));
	txqdate.setText(""+rs7.getString(3));
	txamt.setText(""+rs7.getInt(4));
	PreparedStatement st6=null;
        ResultSet rs6=null;
	st6=cn.prepareStatement("select * from quotation_details where qno=?");
	st6.setInt(1,Integer.parseInt(txqno.getText()));
	rs6=st6.executeQuery();
	while(rs6.next())
	 {
	  Vector temp=new Vector();
	  temp.add(""+rs6.getInt(1));
	  st12=cn.prepareStatement("select iname,modelno from stock where icode=?");
	  st12.setInt(1,rs6.getInt(2));
 	  rs12=st12.executeQuery();
	  rs12.next();
	  temp.add(""+rs12.getString(1));
	  temp.add(""+rs12.getString(2));
	  temp.add(""+rs6.getInt(3));
 	  data.add(temp);
	  DefaultTableModel model=(DefaultTableModel)tb.getModel();
	  model.newDataAvailable(null);
	 }//while
      }//try
     catch(Exception e1)
      {   
  
      }//catch
    }//search

   void Delete()
    {
     try
      {
       PreparedStatement s11=cn.prepareStatement("delete from quotation_details where qno=?");
       s11.setInt(1,Integer.parseInt(txqno.getText()));
       s11.executeUpdate();
      }	
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
     try
      {
        PreparedStatement s11=cn.prepareStatement("delete from quotation_header where qno=?");
	s11.setInt(1,Integer.parseInt(txqno.getText()));
	s11.executeUpdate();	
	JOptionPane.showMessageDialog(this,"Record is cancelled","Msg",JOptionPane.INFORMATION_MESSAGE);
	txqno.setText("");
	txqdate.setText("");
	txrate.setText("");
	txamt.setText("");
      }	
     catch(Exception e2)
      {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }
    }

   public static void main(String args[])
    {
     quotation q=new quotation();
    }//main
  }//class

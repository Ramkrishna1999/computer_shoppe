import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;

class bill extends JFrame implements ActionListener
 {
  JLabel l1=new JLabel("BILL");
  JLabel l2=new JLabel("Bill no");
  JLabel l3=new JLabel("Bill Date");
  JLabel l4=new JLabel("Customer Name");
  JLabel l5=new JLabel("Item Name");
  JLabel l6=new JLabel("Model No");
  JLabel l7=new JLabel("Quantity");
  JLabel l8=new JLabel("Rate");
  JLabel l9=new JLabel("Amount");

  JTextField txbno=new JTextField(20);
  JTextField txbdate=new JTextField(20);

  JComboBox cmbcn=new JComboBox();
  JComboBox cmbiname=new JComboBox();
  JComboBox cmbmn=new JComboBox();

  JTextField txqty=new JTextField(20);
  JTextField txrate=new JTextField(20);
  JTextField txamt=new JTextField(20);

  JButton bnew=new JButton("New");
  JButton bsave=new JButton("Save");
  JButton bdelete=new JButton("Delete");
  JButton bsearch=new JButton("Search");
  JButton bexit=new JButton("Exit");
  JButton bclr=new JButton("Clear");
  JButton ba=new JButton("Add");

  JTable tb;

  Connection cn=null;

  PreparedStatement st=null;

  ResultSet rs=null;

  Vector data=new Vector();
  Vector head=new Vector();
  Vector item=new Vector();

  bill()
   {
    JPanel jp=(JPanel)getContentPane();
    jp.setLayout(null);

    l1.setBounds(240,30,250,30);
    jp.add(l1);

    l2.setBounds(110,100,180,30);
    jp.add(l2);

    txbno.setBounds(270,100,140,30);
    jp.add(txbno);

    l3.setBounds(110,150,180,30);
    jp.add(l3);

    txbdate.setBounds(270,150,140,30);
    jp.add(txbdate);

    l4.setBounds(110,200,180,30);
    jp.add(l4);

    cmbcn.setBounds(270,200,140,30);
    jp.add(cmbcn);

    l5.setBounds(110,250,180,30);
    jp.add(l5);

    cmbiname.setBounds(270,250,140,30);
    jp.add(cmbiname);

    l6.setBounds(110,300,180,30);
    jp.add(l6);

    cmbmn.setBounds(270,300,140,30);
    jp.add(cmbmn);

    l7.setBounds(110,350,180,30);
    jp.add(l7);

    txqty.setBounds(270,350,140,30);
    jp.add(txqty);

    l8.setBounds(110,400,100,30);
    jp.add(l8);

    txrate.setBounds(270,400,140,30);
    jp.add(txrate);

    l9.setBounds(110,450,100,30);
    jp.add(l9);

    txamt.setBounds(270,450,140,30);
    jp.add(txamt);
    jp.setBackground(Color.pink);

    JPanel p1=new JPanel();
    p1.setLayout(new GridLayout(1,3));
    p1.add(bnew);
    p1.add(bsave);
    p1.add(bsearch);
    p1.add(bdelete);
    p1.setBounds(130,530,320,30);

    jp.add(p1);
    JPanel p2=new JPanel();
    p2.setLayout(new GridLayout(1,2));
    p2.add(bdelete);
    p2.add(bexit);
    p2.setBounds(180,560,220,30);

    jp.add(p2);
    l1.setFont(new Font("Times New Roman",Font.BOLD,20));
    l2.setFont(new Font("Times New Roman",Font.BOLD,16));
    l3.setFont(new Font("Times New Roman",Font.BOLD,16));
    l4.setFont(new Font("Times New Roman",Font.BOLD,16));
    l5.setFont(new Font("Times New Roman",Font.BOLD,16));
    l6.setFont(new Font("Times New Roman",Font.BOLD,16));
    l7.setFont(new Font("Times New Roman",Font.BOLD,16));
    l8.setFont(new Font("Times New Roman",Font.BOLD,16));
    l9.setFont(new Font("Times New Roman",Font.BOLD,16));

    txbno.setFont(new Font("Times New Roman",Font.BOLD,16));
    txbdate.setFont(new Font("Times New Roman",Font.BOLD,16));
    cmbcn.setFont(new Font("Times New Roman",Font.BOLD,16));
    cmbiname.setFont(new Font("Times New Roman",Font.BOLD,16));
    cmbmn.setFont(new Font("Times New Roman",Font.BOLD,16));
    txqty.setFont(new Font("Times New Roman",Font.BOLD,16));
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

    head.add("Bill no");
    head.add("Item Name");
    head.add("Model No");
    head.add("Quantity");
    head.add("Rate");

    tb=new JTable(data,head);
    JScrollPane js=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JPanel j1=new JPanel();
    j1.setLayout(new FlowLayout());
    j1.setBounds(480,100,500,300);
    j1.add(js);
    jp.add(j1);

    bnew.addActionListener(this);
    bsave.addActionListener(this);
    ba.addActionListener(this);
    bclr.addActionListener(this);
    cmbiname.addActionListener(this);
    bdelete.addActionListener(this);
    bexit.addActionListener(this);
    bsearch.addActionListener(this);
    addWindowListener(new W());


    try
     {
      try
       {
         Class.forName("com.mysql.jdbc.Driver");
         cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
        st=cn.prepareStatement("select  cname  from customer_master");
        rs=st.executeQuery();
        while(rs.next())
         {
          cmbcn.addItem(rs.getString(1));
         }//while
       }//try

      catch(Exception e1)
       {
        JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
       }//catch

     st=cn.prepareStatement("select  iname  from stock");
     rs=st.executeQuery();
      while(rs.next())
       {
         cmbiname.addItem(rs.getString(1));
       }//while
    }//try
    
   catch(Exception e1)
    {
     JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
    }//catch

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
   if(e.getSource()==cmbiname)
   inm();

   if(e.getSource()==ba)
   add();

   if(e.getSource()==bclr)
   clear();

   if(e.getSource()==bsearch)
   Search();

   if(e.getSource()==bdelete)
   Delete();

   if(e.getSource()==bsave)
   Save();

   if(e.getSource()==bnew)
   New();

   if(e.getSource()==bexit)
   dispose();

  }//actionperformed

 void add()
  { 
    int amt=0;
       Vector temp=new Vector();
       temp.add(txbno.getText());
       temp.add((String)cmbiname.getSelectedItem());
       temp.add((String)cmbmn.getSelectedItem());
       temp.add(txqty.getText());
       temp.add(txrate.getText());

       data.add(temp);
       int vqty=Integer.parseInt(txqty.getText());
       int vrate=Integer.parseInt(txrate.getText());

       amt=(vqty*vrate);
       txamt.setText(""+amt);
       DefaultTableModel
       model=(DefaultTableModel)tb.getModel();
       model.newDataAvailable(null);
       txqty.setText("");
       txrate.setText("");
       txamt.requestFocus();
    }//add

 void clear()
  { 
   data.removeAllElements();
   DefaultTableModel model=(DefaultTableModel)tb.getModel();
   model.newDataAvailable(null);
  }

 void inm()
  {
   String in=((String)cmbiname.getSelectedItem());
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
       cmbmn.addItem(mn);
      }//while
     }//try
   catch(Exception e)
    {
     JOptionPane.showMessageDialog(this,e,"Error",JOptionPane.ERROR_MESSAGE);
    }//catch
  }//fun

 void New()
  {
   int no;
   Statement st4=null;
   ResultSet rs4=null;

   txbno.setText("");
   txbdate.setText("");
   txqty.setText("");
   txrate.setText("");
   txamt.setText("");
   txbdate.requestFocus();
   try 
    {
      st4=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
      rs4=st4.executeQuery("select * from bill_order");
      if(!rs4.next())
        no=1;
      else
       {
        rs4.last();
        no=rs4.getInt(1)+1; 
       }
      txbno.setText(""+no);
    }
   catch(Exception e2)
    {
     JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
    }
  }

 void Save()
  {
   try
    {
      PreparedStatement st1=null;
      ResultSet rs1=null;

      PreparedStatement st2=null;
      ResultSet rs2=null;

      PreparedStatement st3=null;
      ResultSet rs3=null;

      PreparedStatement st5=null;
      ResultSet rs5=null;

      int cno;
      st1=cn.prepareStatement("select cno from customer_master where cname=?");
      st1.setString(1,((String)cmbcn.getSelectedItem()));

      rs1=st1.executeQuery();
      rs1.next();

      cno=(rs1.getInt(1));
      st1=cn.prepareStatement("Insert into bill_order values(?,?,?,?)");
    
      st1.setInt(1,Integer.parseInt(txbno.getText()));
      st1.setInt(2,cno);
      st1.setString(3,(txbdate.getText()));
      st1.setInt(4,Integer.parseInt(txamt.getText()));
      st1.executeUpdate();

      st2=cn.prepareStatement("Insert into bill_details values(?,?,?)");
    
      for(int i=0;i<tb.getRowCount();i++)
       {
        st2.setInt(1,Integer.parseInt(""+ tb.getValueAt(i,0)));
        int vicode;
	String vstrin=(String)tb.getValueAt(i,1);
	String vstrmno=(String)tb.getValueAt(i,2);

	st3=cn.prepareStatement("select icode,qty from stock where iname=? and modelno=?");
	st3.setString(1,vstrin);
	st3.setString(2,vstrmno);
	rs3=st3.executeQuery();
	rs3.next();  
	vicode=rs3.getInt(1);

	st2.setInt(2,vicode);
	st2.setInt(3,Integer.parseInt(""+tb.getValueAt(i,3)));
	st2.executeUpdate();

	st5=cn.prepareStatement("update stock set qty=? where icode=?");
	st5.setInt(1,rs3.getInt(2)-Integer.parseInt(""+tb.getValueAt(i,3)));
	st5.setInt(2,vicode);
	st5.executeUpdate();
      }//for
   }//try
  catch(Exception e1)
   {
    JOptionPane.showMessageDialog(this,e1,"Msg",JOptionPane.INFORMATION_MESSAGE);
   }//catch
  JOptionPane.showMessageDialog(this,"record is saved","Msg",JOptionPane.INFORMATION_MESSAGE);
 }//save

 void Delete()
  {
   try
    {
      PreparedStatement st1=cn.prepareStatement("delete from bill_details where bno=?");
      st1.setInt(1,Integer.parseInt(txbno.getText()));
      st1.executeUpdate();
    }	
   catch(Exception e2)
    {
     JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
    }
   try
    {
     PreparedStatement st1=cn.prepareStatement("delete from bill_order where bno=?");
     st1.setInt(1,Integer.parseInt(txbno.getText()));
     st1.executeUpdate();	
     JOptionPane.showMessageDialog(this,"Record is cancelled","Msg",JOptionPane.INFORMATION_MESSAGE);

     txbno.setText("");
     txbdate.setText("");
     txqty.setText("");
     txrate.setText("");
     txamt.setText("");
    }	
   catch(Exception e2)
    {
     JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);  
    }
  }//delete

 void Search()
  {
   try
    {
     PreparedStatement st7=null;
     ResultSet rs7=null;
     st7=cn.prepareStatement("select * from  bill_order where bno=?");
     st7.setInt(1,Integer.parseInt(txbno.getText()));
     rs7=st7.executeQuery();
     rs7.next();
     txbno.setText(Integer.toString(rs7.getInt(1)));

     int cno;

     PreparedStatement st12=cn.prepareStatement("select cname from customer_master where cno=?");
     st12.setInt(1,rs7.getInt(2));
     ResultSet rs12=null;
     rs12=st12.executeQuery();
     rs12.next();

     cmbcn.setSelectedItem(rs12.getString(1));
     txbdate.setText(""+rs7.getString(3));
     txamt.setText(""+rs7.getInt(4));

     PreparedStatement st6=null;
     ResultSet rs6=null;
     st6=cn.prepareStatement("select * from bill_details where bno=?");
     st6.setInt(1,Integer.parseInt(txbno.getText()));
     rs6=st6.executeQuery();

     while(rs6.next())
      {
       Vector temp=new Vector();
       temp.add(""+rs6.getInt(1));

       st12=cn.prepareStatement("select iname,modelno,rate from stock where icode=?");
       st12.setInt(1,rs6.getInt(2));
       rs12=st12.executeQuery();
       rs12.next();

       temp.add(""+rs12.getString(1));
       temp.add(""+rs12.getString(2));
       temp.add(""+rs6.getInt(3));
       temp.add(""+rs12.getInt(3));

       data.add(temp);
       temp.add(""+rs6.getInt(3));
       JOptionPane.showMessageDialog(this,"Record updated","Msg",JOptionPane.INFORMATION_MESSAGE);
       DefaultTableModel model=(DefaultTableModel)tb.getModel();
       model.newDataAvailable(null);
      }//while
    }//try
   catch(Exception e1)
    {   
     JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
    }//catch
  }//search

 public static void main(String args[])
  {
    bill bf=new bill();
  }//main
}
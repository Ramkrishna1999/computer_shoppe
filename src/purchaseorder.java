import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.io.*;
import java.util.*;

 class purchaseorder extends JFrame implements ActionListener
  {
   JLabel l1=new JLabel("PURCHASE ORDER");
   JLabel l2=new JLabel("Purchase order no");
   JLabel l3=new JLabel("Purchase Date");
   JLabel l4=new JLabel("Vendor name");
   JLabel l5=new JLabel("Item name");
   JLabel l6=new JLabel("Model no");
   JLabel l7=new JLabel("Quantity");
   JLabel l8=new JLabel("Rate");
   JLabel l9=new JLabel("Amount");
   JTextField txpono=new JTextField(20);
   JTextField txpdate=new JTextField(20);
   JComboBox cmbvn =new JComboBox();
   JComboBox cmbin =new JComboBox();
   JComboBox cmbmno =new JComboBox();
   JTextField txqty=new JTextField(20);
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
 
   public purchaseorder()
    {
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     jp.setBackground(Color.pink);
     l1.setBounds(180,30,250,30);
     jp.add(l1);
     l2.setBounds(100,100,180,30);
     jp.add(l2);
     txpono.setBounds(270,100,120,30);
     jp.add(txpono);
     l3.setBounds(100,150,180,30);
     jp.add(l3);
     txpdate.setBounds(270,150,120,30);
     jp.add(txpdate);
     l4.setBounds(100,200,180,30);
     jp.add(l4);
     cmbvn.setBounds(270,200,120,30);
     jp.add(cmbvn);
     l5.setBounds(100,250,180,30);
     jp.add(l5);
     cmbin.setBounds(270,250,120,30);
     jp.add(cmbin);
     l6.setBounds(100,300,180,30);
     jp.add(l6);
     cmbmno.setBounds(270,300,120,30);
     jp.add(cmbmno);
     l7.setBounds(100,350,180,30);
     jp.add(l7);
     txqty.setBounds(270,350,120,30);
     jp.add(txqty);
     l8.setBounds(100,400,180,30);
     jp.add(l8);
     txrate.setBounds(270,400,120,30);
     jp.add(txrate);
     l9.setBounds(100,450,180,30);
     jp.add(l9);
     txamt.setBounds(270,450,120,30);
     jp.add(txamt);
     PreparedStatement st4=null;
     ResultSet rs4=null;
     PreparedStatement st5=null;
     ResultSet rs5=null;
     //PreparedStatement st=null;
     ResultSet rs1=null;
     try
      {
        Class.forName("com.mysql.jdbc.Driver");
       cn=DriverManager.getConnection("jdbc:mysql://localhost/computer shopee","root","");
       st4=cn.prepareStatement("select  iname  from stock");
       rs4=st4.executeQuery();
       while(rs4.next())
        {
         cmbin.addItem(rs4.getString(1));
        }//while
      }//try
     catch(Exception e1)
      {
       JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
     try
      {
       st5=cn.prepareStatement("select  vname  from vendor_master");
       rs5=st5.executeQuery();
       while(rs5.next())
        {
         cmbvn.addItem(rs5.getString(1));
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
     p1.setBounds(130,530,320,30);
     jp.add(p1);
     JPanel p2=new JPanel();
     p2.setLayout(new GridLayout(1,2));
     p2.add(bdelete);
     p2.add(bexit);
     p2.setBounds(180,560,200,30);
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
     txpono.setFont(new Font("Times New Roman",Font.BOLD,16));
     txpdate.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmbvn.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmbin.setFont(new Font("Times New Roman",Font.BOLD,16));
     cmbmno.setFont(new Font("Times New Roman",Font.BOLD,16));
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
     head.add("purchase order no");
     head.add("Item Name");
     head.add("Model no");
     head.add("Quantity");
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
     if(e.getSource()==ba)
       add();
     else if(e.getSource()==bclr)
       clear();
     else if(e.getSource()==bnew)
       New();
     else if(e.getSource()==bsave)
       Save();
     else if(e.getSource()==bsearch)
       Search();
     else if(e.getSource()==bdelete) 
       Delete();
     else if(e.getSource()==bexit)
       dispose();
     else if(e.getSource()==cmbin)
       ss();
    }

   void add()
    {
     int amt=0;
     Vector temp=new Vector();
     temp.add(txpono.getText());
     temp.add((String)cmbin.getSelectedItem());
     temp.add((String)cmbmno.getSelectedItem());
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
    }

   void clear()
    {
     data.removeAllElements();
     DefaultTableModel model=(DefaultTableModel)tb.getModel();
     model.newDataAvailable(null);
    }

   void New()
    {
     int no;
     Statement st9=null;
     ResultSet rs9=null;
     txpono.setText("");
     txpdate.setText("");
     txqty.setText("");
     txrate.setText("");
     txamt.setText("");
     txpdate.requestFocus();
     try
      {
       st9=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       rs9=st9.executeQuery("select * from purchase_order");
       if(!rs9.next())
         no=1;
       else
        {
         rs9.last();
         no=rs9.getInt(1)+1;
        }//else
       txpono.setText(""+no);
      }//try 
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch 
    }//new

   void Save()
    {
     try
      {
       PreparedStatement st1=null;
       PreparedStatement st2=null;
       PreparedStatement st3=null;
       PreparedStatement pstat=null;
       ResultSet rs2=null;
       int vno;
       pstat=cn.prepareStatement("select vno from vendor_master where vname = ?");
       pstat.setString(1,((String)cmbvn.getSelectedItem()));
       rs2=pstat.executeQuery();
       rs2.next();
       vno=(rs2.getInt(1));
       pstat=cn.prepareStatement("Insert into purchase_order values(?,?,?,?)");
    
       pstat.setInt(1,Integer.parseInt(txpono.getText()));
       pstat.setInt(2,vno);
       pstat.setString(3,(txpdate.getText()));
       pstat.setInt(4,Integer.parseInt(txamt.getText()));
       pstat.executeUpdate();
       st1=cn.prepareStatement("Insert into purchase_details values(?,?,?,?)");
    
       for(int i=0;i<tb.getRowCount();i++)
        {
  	 st1.setInt(1,Integer.parseInt(""+ tb.getValueAt(i,0)));
	 int vicode;
	 String vstrin=(String)tb.getValueAt(i,1);
 	 String vstrmno=(String)tb.getValueAt(i,2);
	 st2=cn.prepareStatement("select icode,qty from stock where iname=? and modelno=?");
	 st2.setString(1,vstrin);
	 st2.setString(2,vstrmno);
	 rs2=st2.executeQuery();
	 rs2.next();  
	 vicode=rs2.getInt(1);
	 st1.setInt(2,vicode);
	 st1.setInt(3,Integer.parseInt(""+tb.getValueAt(i,3)));
	 st1.setInt(4,Integer.parseInt(""+ tb.getValueAt(i,4)));
	 st1.executeUpdate();
	 st3=cn.prepareStatement("update stock set qty=? where icode=?");
	 st3.setInt(1,rs2.getInt(2)+Integer.parseInt(""+ tb.getValueAt(i,3)));
	 st3.setInt(2,vicode);
	 st3.executeUpdate();
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
       PreparedStatement s11=cn.prepareStatement("delete from purchase_details where pono=?");
       s11.setInt(1,Integer.parseInt(txpono.getText()));
       s11.executeUpdate();
      }//try	
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
     try
      {
       PreparedStatement s11=cn.prepareStatement("delete from purchase_order where pono=?");
       s11.setInt(1,Integer.parseInt(txpono.getText()));
       s11.executeUpdate();	
       JOptionPane.showMessageDialog(this,"Record is cancelled","Msg",JOptionPane.INFORMATION_MESSAGE);
       txpono.setText("");
       txpdate.setText("");
       txqty.setText("");
       txrate.setText("");
       txamt.setText("");
      }//try	
     catch(Exception e2)
      {
       JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
    }//delete

   void Search()
    {
     try
      {
       PreparedStatement st7=null;
       ResultSet rs7=null;
       st7=cn.prepareStatement("select * from  purchase_order where pono=?");
       st7.setInt(1,Integer.parseInt(txpono.getText()));
       rs7=st7.executeQuery();
       rs7.next();
       txpono.setText(Integer.toString(rs7.getInt(1)));
       int vno;
       PreparedStatement st12=cn.prepareStatement("select vname from vendor_master where vno=?");
       st12.setInt(1,rs7.getInt(2));
       ResultSet rs12=null;
       rs12=st12.executeQuery();
       rs12.next();
       cmbvn.setSelectedItem(rs12.getString(1));
       txpdate.setText(""+rs7.getString(3));
       txamt.setText(""+rs7.getInt(4));
       PreparedStatement st6=null;
       ResultSet rs6=null;
       st6=cn.prepareStatement("select * from purchase_details where pono=?");
       st6.setInt(1,Integer.parseInt(txpono.getText()));
       rs6=st6.executeQuery();
       while(rs6.next())
        {
         Vector temp=new Vector();
         temp.add(""+rs6.getInt(1));
         st12=cn.prepareStatement("select iname,modelno,qty from stock where icode=?");
         st12.setInt(1,rs6.getInt(2));
         rs12=st12.executeQuery();
         rs12.next();
	 temp.add(""+rs12.getString(1));
	 temp.add(""+rs12.getString(2));
	 temp.add(""+rs6.getInt(3));
	 temp.add(""+rs6.getInt(4));
	 data.add(temp);
	 DefaultTableModel model=(DefaultTableModel)tb.getModel();
	 model.newDataAvailable(null);
 	}//while
      }//try
     catch(Exception e1)
      {   
       JOptionPane.showMessageDialog(this,e1,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
    }//search

   void ss()
    {
     String in=((String)cmbin.getSelectedItem());
     try
      {
       PreparedStatement st8=null;
       ResultSet rs8=null;
       st8=cn.prepareStatement("select modelno from stock where iname=?");
       st8.setString(1,in);
       rs8=st8.executeQuery();
       while(rs8.next())
	{
	 String mn=rs8.getString(1);
	 cmbmno.addItem(mn);
	}//while
      }//try
     catch(Exception e7)
      {
       JOptionPane.showMessageDialog(this,e7,"Error",JOptionPane.ERROR_MESSAGE);
      }//catch
   }//fun
  public static void main(String args[])
   {
    purchaseorder p=new purchaseorder();
   }//main
  }//class
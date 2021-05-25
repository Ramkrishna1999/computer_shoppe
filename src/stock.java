import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

 class stock extends JFrame implements ActionListener
  {
   JLabel l7=new JLabel("STOCK");
   JLabel l1=new JLabel("Item Code");
   JLabel l2=new JLabel("Item Name");
   JLabel l3=new JLabel("Model Number");
   JLabel l4=new JLabel("ROL");
   JLabel l5=new JLabel("Quantity");
   JLabel l6=new JLabel("Rate");
   JTextField txcode=new JTextField(20);
   JTextField txname=new JTextField(20);
   JTextField txmono=new JTextField(20);
   JTextField txrol=new JTextField(20);
   JTextField txqty=new JTextField(20);
   JTextField txrate=new JTextField(20);
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
   ResultSet rs=null;
   ResultSet rs1=null;
   stock() 
    { 
     JPanel jp=(JPanel)getContentPane();
     jp.setLayout(null);
     l7.setBounds(250,30,120,30);
     jp.add(l7);
     l1.setBounds(110,100,150,30);
     jp.add(l1);
     txcode.setBounds(300,100,150,30);
     jp.add(txcode);
     l2.setBounds(110,150,105,30); 
     jp.add(l2);
     txname.setBounds(300,150,150,30);
     jp.add(txname);
     l3.setBounds(110,200,150,30);
     jp.add(l3);
     txmono.setBounds(300,200,150,30);
     jp.add(txmono);
     l4.setBounds(110,250,150,30);
     jp.add(l4);
     txrol.setBounds(300,250,150,30); 
     jp.add(txrol);
     l5.setBounds(110,300,100,30);
     jp.add(l5);
     txqty.setBounds(300,300,150,30);
     jp.add(txqty);
     l6.setBounds(110,350,100,30);
     jp.add(l6);
     txrate.setBounds(300,350,150,30);
     jp.add(txrate);
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
     p1.setBounds(50,430,550,60);
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
     txcode.setFont(new Font("Times New Roman",Font.BOLD,16));
     txname.setFont(new Font("Times New Roman",Font.BOLD,16));
     txmono.setFont(new Font("Times New Roman",Font.BOLD,16));
     txrol.setFont(new Font("Times New Roman",Font.BOLD,16));
     txqty.setFont(new Font("Times New Roman",Font.BOLD,16));
     txrate.setFont(new Font("Times New Roman",Font.BOLD,16));
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
       rs1=st1.executeQuery("select * from stock");
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
       // System.exit(0);
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
            txcode.setText(Integer.toString(rs1.getInt(1)));
            txname.setText(rs1.getString(2));
            txmono.setText(rs1.getString(3));
            txrol.setText(Integer.toString(rs1.getInt(4))); 
            txqty.setText(Integer.toString(rs1.getInt(5)));
            txrate.setText(Integer.toString(rs1.getInt(6)));
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
      txcode.setText("");
      txname.setText("");
      txmono.setText("");
      txrol.setText("");
      txqty.setText("");
      txrate.setText("");
      txname.requestFocus();
      try
       {
        st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs=st1.executeQuery("select * from stock");
        if(rs==null)
          no=1;
        else
         {
          rs.last();
          no=rs.getInt(1)+1;
         }
        txcode.setText(""+no);
       }
      catch(Exception e2)
       {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
       }
     }
    void Save()
     {
      int vcode,vrol,vqty,vrate;
      String vname,vmono;
      vcode=Integer.parseInt(txcode.getText());
      vname=txname.getText();
      vmono=txmono.getText();
      vrol=Integer.parseInt(txrol.getText());
      vqty=Integer.parseInt(txqty.getText());
      vrate=Integer.parseInt(txrate.getText());
      try
       {
        String sql="Insert into stock(icode,iname,modelno,rol,qty,rate)values(?,?,?,?,?,?)";
        st=cn.prepareStatement(sql);
        st.setInt(1,vcode);
        st.setString(2,vname);
        st.setString(3,vmono);
        st.setInt(4,vrol);
        st.setInt(5,vqty);
        st.setInt(6,vrate);
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
        int vcode=Integer.parseInt(txcode.getText());
        st=cn.prepareStatement("select * from stock where icode=?");
        st.setInt(1,vcode);
        rs=st.executeQuery();
        rs.next();
        txcode.setText(Integer.toString(rs.getInt(1)));
        txname.setText(rs.getString(2));
        txmono.setText(rs.getString(3));
        txrol.setText(""+rs.getInt(4));
        txqty.setText(""+rs.getInt(5));
        txrate.setText(""+rs.getInt(6));
       }
      catch(Exception e2)
       {
	JOptionPane.showMessageDialog(this,e2,"Error",JOptionPane.ERROR_MESSAGE);
       }
     }
    void Update()
     {
      int vcode,vrol,vqty,vrate;
      String vname,vmono;
      vcode=Integer.parseInt(txcode.getText());
      vname=txname.getText();
      vmono=txmono.getText();
      vrol=Integer.parseInt(txrol.getText());
      vqty=Integer.parseInt(txqty.getText());
      vrate=Integer.parseInt(txrate.getText());
      try
       {
	String sql="Update stock set iname=?,modelno=?,rol=?,qty=?,rate=? where icode=?";
	st=cn.prepareStatement(sql);
	st.setString(1,vname);
	st.setString(2,vmono);
	st.setInt(3,vrol);
	st.setInt(4,vqty);
	st.setInt(5,vrate);
	st.setInt(6,vcode);
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
      int vcode,vrol,vqty,vrate;
      String vname,vmono;
      vcode=Integer.parseInt(txcode.getText());
      try
       {
	String sql="Delete from stock where icode=?";
	st=cn.prepareStatement(sql);
	st.setInt(1,vcode);
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
      stock s=new stock();
     }
  }
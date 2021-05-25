import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

  class Menudemo extends JFrame implements ActionListener
     {
      JMenuBar mb=new JMenuBar();  

      JMenu m1=new JMenu("Master form");
      JMenu m2=new JMenu("Transation form");
      JMenu m3=new JMenu("Report");
      JMenu m4=new JMenu("Exit");

      JMenuItem mi1=new JMenuItem("Enquiry");
      JMenuItem mi2=new JMenuItem("Customer_Form");
      JMenuItem mi3=new JMenuItem("Stock");
      JMenuItem mi4=new JMenuItem("Purchase_Order");
      JMenuItem mi5=new JMenuItem("Vendor_Form");
      JMenuItem mi6=new JMenuItem("Quotation");
      JMenuItem mi7=new JMenuItem("Bill");
      JMenuItem mi8=new JMenuItem("Customer_Report");
      JMenuItem mi9=new JMenuItem("Vendor_Report");
      JMenuItem mi10=new JMenuItem("Stock_Report");
      JMenuItem mi11=new JMenuItem("PurchaseOrder_Report");
      JMenuItem mi12=new JMenuItem("Bill_Report");
      JMenuItem mi13=new JMenuItem("Close");

     // JLabel img=new JLabel(new ImageIcon("D:\\Report\\Images\\shop1.jpg"));
 
      Menudemo()
       {
        JPanel jp=(JPanel)getContentPane();
        
        jp.setLayout(new BorderLayout());
        jp.add(mb,"North");
        jp.setBackground(Color.pink);

        m2.add(mi3);
        m2.add(mi4);
        m2.add(mi7);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi5);
        m1.add(mi6);

        m3.add(mi8);
        m3.add(mi9);
        m3.add(mi10);
        m3.add(mi11); 
        m3.add(mi12);

        m4.add(mi13);  

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);

        mi11.addActionListener(this);

        mi12.addActionListener(this);

        mi13.addActionListener(this);


        setJMenuBar(mb);
       // img.setBounds(0,0,1500,1500);
        //jp.add(img);
         ImageIcon bg =new ImageIcon("E:\\Report\\Images\\shenzhen-china-circa-april-2019-600w-1545919016.jpg");
		Image img=bg.getImage();
		Image temp=img.getScaledInstance(1400,750,Image.SCALE_SMOOTH);
		bg=new ImageIcon(temp);
		JLabel back=new JLabel("",bg,JLabel.CENTER);
		back.setBounds(0, 0, 600, 400);
	    add(back);
        setVisible(true);
        setTitle("Welcome");
        setSize(1500,1500);
       }
      public  void  actionPerformed(ActionEvent e)
       {      
        if(e.getSource()==mi1)
         {
          custenq enq=new custenq();    		
         }

        else if(e.getSource()==mi2)
         {
          customer  c=new  customer();
         }

        else if(e.getSource()==mi3)
         {
          stock s=new stock();
         }

        else if(e.getSource()==mi4)
         {   
          purchaseorder p=new purchaseorder();
         }

        else  if(e.getSource()==mi5)
         {
          vendor v=new vendor();
         }

        else  if(e.getSource()==mi6)
         {
          quotation q=new quotation();
         }

        else  if(e.getSource()==mi7)
         {
          bill bf=new bill();
         }

        else  if(e.getSource()==mi8)
         {
          customer_report1 ct=new customer_report1();
         }

        else  if(e.getSource()==mi9)
         {
          vendor_report vr=new vendor_report(); 
         }

        else  if(e.getSource()==mi10)
         {
          stock_report vt=new stock_report(); 
         }

        else  if(e.getSource()==mi11)
         {
          vendorwise_item vi=new vendorwise_item(); 
         }

        else  if(e.getSource()==mi12)
         {
          bill_report b=new bill_report(); 
         }

        else  if(e.getSource()==mi13)
         {
           try {
           Startxampp st=new Startxampp();
           st.stopxampp();
       } catch (IOException ex) {
           Logger.getLogger(login1.class.getName()).log(Level.SEVERE, null, ex);
       } 
          dispose();
         }    
      }

  public static void main(String args[])
   {
    Menudemo m=new Menudemo();
   }
 }           
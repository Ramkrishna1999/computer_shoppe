import java .awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class login1 extends JFrame  implements ActionListener
  {
   
   JLabel l=new JLabel("Please  Login here :-");
   JLabel l1=new JLabel("Username");
   JLabel l2=new JLabel("Password");

   JTextField txun=new JTextField(20);
   JPasswordField txup=new JPasswordField(20);
    
   JButton b1=new JButton("Login");   
   JButton b2=new JButton("Exit"); 


		
   login1()
    {
        
     JPanel jp=(JPanel)getContentPane();
     setSize(600,400);
     jp.setLayout(null);
     jp.setBackground(Color.pink);
   
     l.setBounds(150,70,500,35);
     jp.add(l);
     
     l1.setBounds(110,140,130,30);
     jp.add(l1);
     txun.setBounds(300,140,150,30);
     jp.add(txun);
     l2.setBounds(110,190,100,30);
     jp.add(l2);
     txup.setBounds(300,190,150,30);
     jp.add(txup);
     b1.setBounds(160,240,100,30);
     jp.add(b1);
     b2.setBounds(280,240,100,30);
     jp.add(b2);
     ImageIcon bg =new ImageIcon("E:\\Report\\Images\\padlock_with_keys_185543.jpg");
		Image img=bg.getImage();
		Image temp=img.getScaledInstance(600,400,Image.SCALE_SMOOTH);
		bg=new ImageIcon(temp);
		JLabel back=new JLabel("",bg,JLabel.CENTER);
		back.setBounds(0, 0, 600, 400);
	    add(back);
   
     setVisible(true);
     //setSize(600,400);
     setTitle("Login");

     b1.addActionListener(this);
     b2.addActionListener(this);

     Font f = new Font("Arial",Font.BOLD,30);
     l.setFont(f);
     Font f1 = new Font("Arial",Font.BOLD,18);
    
     l1.setFont(f1);
     l2.setFont(f1);
     //l.setForeground(Color.green);
     //l1.setForeground(Color.green);
     //l2.setForeground(Color.green);
     
     b1.setFont(f1);
     b2.setFont(f1);
    }//constructor

   public void actionPerformed(ActionEvent e)
    {
     if(e.getSource()==b1)
      {
       if((txun.getText().equals("Ramkrishna"))&& (txup.getText().equals("8380029541")))
        {
         JOptionPane.showMessageDialog( this,"Login successfully"," ",JOptionPane.INFORMATION_MESSAGE);
          try {
           Startxampp st=new Startxampp();
           st.startxampp();
       } catch (IOException ex) {
           Logger.getLogger(login1.class.getName()).log(Level.SEVERE, null, ex);
       }
         Menudemo m=new Menudemo();
         dispose();
        }
       else
        {
         JOptionPane.showMessageDialog(this,"Login failed"," ",JOptionPane.INFORMATION_MESSAGE);
        }
      }
     else 
      System.exit(0);
    }

   public static void main(String args[])
    {
       
     login1 l=new login1();
    }
   
  }         
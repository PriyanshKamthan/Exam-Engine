import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JPanel p1,p2,p3;
    JLabel l,l1,l2,l3;
    JTextField t1,t2;
    static String name;
    JComboBox<String> t3;
    Main()
    {
        f=new JFrame("Login Page");
        
        p1=new JPanel();
        l=new JLabel("Exam Engine");
        l.setFont(new Font("lucida bright",1,40));
        p1.add(l);
        
        p2=new JPanel();
        p2.setLayout(new GridLayout(1,2));
        b1=new JButton("Login");
        b2=new JButton("Exit");
        b1.setFont(new Font("lucida bright",1,15));
        b2.setFont(new Font("lucida bright",1,15));
        p2.add(b2);
        p2.add(b1);

        String[] category={" ","Student","Admin"};
        p3=new JPanel();
        p3.setLayout(null);
        l1=new JLabel("Enter ID:");
        l2=new JLabel("Enter Password:");
        l3=new JLabel("Enter Category:");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JComboBox<String>(category);
        l1.setFont(new Font("lucida bright",0,15));
        l2.setFont(new Font("lucida bright",0,15));
        l3.setFont(new Font("lucida bright",0,15));
        t1.setFont(new Font("lucida bright",0,15));
        t2.setFont(new Font("lucida bright",0,15));
        t3.setFont(new Font("lucida bright",0,15));
        l1.setBounds(50,60,150,30);
        l2.setBounds(50,100,150,30);
        l3.setBounds(50,140,150,30);
        t1.setBounds(200,60,150,30);
        t2.setBounds(200,100,150,30);
        t3.setBounds(200,140,150,30);
        p3.add(l1);
        p3.add(l2);
        p3.add(l3);
        p3.add(t1);
        p3.add(t2);
        p3.add(t3);

        b1.addActionListener(this);
        b2.addActionListener(this);

        f.add(p1,BorderLayout.NORTH);
        f.add(p3);
        f.add(p2,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }    

    public void actionPerformed(ActionEvent e)
    {
        JButton bt=(JButton)e.getSource();
        if(bt==b1)
        {
            if(new DataHandler().check(Integer.parseInt(t1.getText()),t2.getText(),t3.getSelectedItem().toString()))
            {
                if(t3.getSelectedItem().toString().equals("Admin"))
                {new Admin(name);}
                else if(t3.getSelectedItem().toString().equals("Student"))
                {new StudentPage(name,Integer.parseInt(t1.getText()));}
            }
            else JOptionPane.showMessageDialog(f,"Invalid Entries");
        }
        else if(bt==b2)
        {
            f.dispose();
        }
    }
    public static void main(String ar[])
    {
        new Main();
    }
}
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Admin implements ActionListener
{
    JFrame f;
    JButton b,b1,b2,b3,b4;
    JLabel l;
    JPanel p1,p2;
    Admin(String name)
    {
        f=new JFrame("Admin Page");
        
        p1=new JPanel();
        l=new JLabel("Hello "+name);
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);

        b=new JButton("Exit");
        b.setFont(new Font("lucida bright",1,15));
        b.addActionListener(this);

        p2=new JPanel();
        p2.setLayout(null);
        b1=new JButton("Add New Student");
        b2=new JButton("Create New Quiz");
        b3=new JButton("View Scores");
        b4=new JButton("View Students");
        b1.setFont(new Font("lucida bright",0,15));
        b2.setFont(new Font("lucida bright",0,15));
        b3.setFont(new Font("lucida bright",0,15));
        b4.setFont(new Font("lucida bright",0,15));
        b1.setBounds(100,50,200,30);
        b2.setBounds(100,90,200,30);
        b3.setBounds(100,130,200,30);
        b4.setBounds(100,170,200,30);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        f.add(p1,BorderLayout.NORTH);
        f.add(p2);
        f.add(b,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton bt=(JButton)e.getSource();
        if(bt==b)
        {
            int a=JOptionPane.showConfirmDialog(f,"Are you sure you want to exit?");
            if(a==0)
            f.dispose();
        }
        if(bt==b1)
        {
            new AddStudent();
        }
        if(bt==b2)
        {
            String quiz_name=JOptionPane.showInputDialog(f,"Enter quiz name: ");
            if(new DataHandler().createQuiz(quiz_name))
            {
                JOptionPane.showMessageDialog(f,quiz_name+" created");
                new CreateQuiz(quiz_name);
            }
            else JOptionPane.showMessageDialog(f,"Something went wrong");    
        }
        if(bt==b3)
        {
            new DisplayAllMarks();
        }
        if(bt==b4)
        {
            new DisplayStudentName();
        }
    }
    public static void main(String[] args)
    {
        new Admin("priyiansh");        
    }
}

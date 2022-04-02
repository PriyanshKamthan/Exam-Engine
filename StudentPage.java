import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StudentPage implements ActionListener
{
    JFrame f;
    JButton b,b1,b2,b3,b4;
    JLabel l;
    JPanel p1,p2;
    String s_name;int s_id;
    StudentPage(String name,int id)
    {
        this.s_name=name;
        this.s_id=id;
        f=new JFrame("Student Page");
        
        p1=new JPanel();
        l=new JLabel("Hello "+name);
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);

        b=new JButton("Exit");
        b.setFont(new Font("lucida bright",1,15));
        b.addActionListener(this);

        p2=new JPanel();
        p2.setLayout(null);
        b1=new JButton("Attempt Quiz");
        b2=new JButton("View My scores");
        b1.setFont(new Font("lucida bright",0,15));
        b2.setFont(new Font("lucida bright",0,15));
        b1.setBounds(100,50,200,30);
        b2.setBounds(100,90,200,30);
        p2.add(b1);
        p2.add(b2);
       
        b1.addActionListener(this);
        b2.addActionListener(this);

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
            new QuizList(s_id,s_name);
        }
        if(bt==b2)
        {
            new DisplayScore(s_id, s_name);
        }
    }

    public static void main(String ar[])
    {
        new StudentPage("Priyansh",123);
    }
}

//CREATE TABLE Score(S_Name varchar(100),Quiz int NOT NULL,PersonID int FOREIGN KEY REFERENCES Persons(PersonID));
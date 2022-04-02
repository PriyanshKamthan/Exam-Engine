import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CreateQuiz implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JLabel L,l;
    JPanel p1,p2,p3;
    JTextArea t;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4;
    JComboBox<String> t5;
    int q_id=1;
    String quiz_name;
    CreateQuiz(String quiz_name)
    {
        this.quiz_name=quiz_name;
        f=new JFrame("Create quiz");
        
        p1=new JPanel();
        L=new JLabel(quiz_name);
        L.setFont(new Font("lucida bright",0,40));
        p1.add(L);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,2));
        b1=new JButton("Save Question");
        b2=new JButton("Exit");
        b1.setFont(new Font("lucida bright",1,15));
        b2.setFont(new Font("lucida bright",1,15));
        p2.add(b2);
        p2.add(b1);

        b1.addActionListener(this);
        b2.addActionListener(this);

        p3=new JPanel();
        p3.setLayout(null);
        l=new JLabel("Que "+q_id);
        t=new JTextArea();
        l1=new JLabel("Option1:");
        l2=new JLabel("Option2:");
        l3=new JLabel("Option3:");
        l4=new JLabel("Option4:");
        l5=new JLabel("Answer:");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        String[] options={"","1","2","3","4"};
        t5=new JComboBox<String>(options);

        l.setBounds(20,10,55,50);
        t.setBounds(80,10,700,50);
        l1.setBounds(50,90,75,30);
        t1.setBounds(125,90,250,30);
        l2.setBounds(430,90,75,30);
        t2.setBounds(505,90,250,30);
        l3.setBounds(50,130,75,30);
        t3.setBounds(125,130,250,30);
        l4.setBounds(430,130,75,30);
        t4.setBounds(505,130,250,30);
        l5.setBounds(300,190,100,30);
        t5.setBounds(400,190,100,30);

        l.setFont(new Font("lucida bright",1,15));
        t.setFont(new Font("lucida bright",0,15));
        l1.setFont(new Font("lucida bright",1,15));
        l2.setFont(new Font("lucida bright",1,15));
        l3.setFont(new Font("lucida bright",1,15));
        l4.setFont(new Font("lucida bright",1,15));
        l5.setFont(new Font("lucida bright",1,15));
        t1.setFont(new Font("lucida bright",0,15));
        t2.setFont(new Font("lucida bright",0,15));
        t3.setFont(new Font("lucida bright",0,15));
        t4.setFont(new Font("lucida bright",0,15));
        t5.setFont(new Font("lucida bright",0,15));
        
        p3.add(l);
        p3.add(t);
        p3.add(l1);
        p3.add(t1);
        p3.add(l2);
        p3.add(t2);
        p3.add(l3);
        p3.add(t3);
        p3.add(l4);
        p3.add(t4);
        p3.add(l5);
        p3.add(t5);

        f.add(p1,BorderLayout.NORTH);
        f.add(p3);
        f.add(p2,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(800,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton bt=(JButton)e.getSource();
        if(bt==b1)
        {
            String que=t.getText();
            String op1=t1.getText();
            String op2=t3.getText();
            String op3=t3.getText();
            String op4=t4.getText();
            int ans=Integer.parseInt(t5.getSelectedItem().toString());
            new DataHandler().addQueToQuizz(quiz_name, q_id++, que, op1, op2, op3, op4, ans);
            t.setText("");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setSelectedIndex(0);
            l.setText("Que "+q_id);
        }
        else if(bt==b2)
        {
            int a=JOptionPane.showConfirmDialog(f,"Do you want to exit without saving?");
            if(a==0)
            f.dispose();
        }
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddStudent implements ActionListener
{
    JFrame f;
    JButton b;
    JLabel l,l1,l2,l3;
    JTextField t1,t2,t3;
    JPanel p1,p2;
    AddStudent()
    {
        f=new JFrame("Add Student Page");
        
        p1=new JPanel();
        l=new JLabel("Add Student");
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);

        p2=new JPanel();
        p2.setLayout(null);
        l1=new JLabel("Enter ID:");
        l2=new JLabel("Enter Name:");
        l3=new JLabel("Enter Password:");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
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
        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        p2.add(t1);
        p2.add(t2);
        p2.add(t3);

        b=new JButton("Save Details");
        b.setFont(new Font("lucida bright",1,15));
        b.addActionListener(this);

        f.add(p1,BorderLayout.NORTH);
        f.add(p2);
        f.add(b,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals(""))
            throw new ArithmeticException("Please fill all the fields");
            int s_id=Integer.parseInt(t1.getText());
            String s_name=t2.getText();
            String s_pswd=t3.getText();
            if(new DataHandler().addStudent(s_id, s_name, s_pswd))
            {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                JOptionPane.showMessageDialog(f,"Student data saved");
            }
            else JOptionPane.showMessageDialog(f,"Something went wrong");
            f.dispose();
        }
        catch(ArithmeticException e1){JOptionPane.showMessageDialog(f,e1.getMessage());}
        catch(NumberFormatException e1){JOptionPane.showMessageDialog(f,"ID must be an Integer");}
        catch(Exception e1){JOptionPane.showMessageDialog(f,e1.getMessage());}
    } 
}

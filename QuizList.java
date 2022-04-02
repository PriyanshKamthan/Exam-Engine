import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class QuizList implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JLabel l;
    JPanel p1,p2,p3;
    DefaultListModel<String> l1 = new DefaultListModel<>();
    JList<String> lis=new JList<>(l1);
    int s_id;
    String quiz_name,s_name;

    QuizList(int s_id,String s_name)
    {
        this.s_id=s_id;
        this.s_name=s_name;

        f=new JFrame("Student Page");        
        p1=new JPanel();
        l=new JLabel("Hello "+s_name);
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);

        getTableName();
        p2=new JPanel();
        p2.setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(lis);
        lis.setBounds(20,20,350,240);
        lis.setFont(new Font("lucida bright",1,15));
        p2.add(lis);

        p3=new JPanel();
        p3.setLayout(new GridLayout(1,2));
        b1=new JButton("Open");
        b1.setFont(new Font("lucida bright",1,15));
        b2=new JButton("Exit");
        b2.setFont(new Font("lucida bright",1,15));
        p3.add(b2);
        p3.add(b1);
        b1.addActionListener(this);
        b2.addActionListener(this);

        f.add(p1,BorderLayout.NORTH);
        f.add(p2);
        f.add(p3,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton bt=(JButton)e.getSource();
        if(bt==b1)
        {
            String quiz_name=lis.getSelectedValue();
            new AttemptQuiz(quiz_name,s_id,s_name);
            f.dispose();
        }
        else if(bt==b2)
        {
            f.dispose();
        }
    }
    
    void getTableName()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/exam?user=root&password=pk1234");
            String query="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='exam'";
            PreparedStatement st=cn.prepareStatement(query);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            l1.addElement(rs.getString(1));
            cn.close();
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
    public static void main(String[] args) {
        new QuizList(123,"Priyansh");
    }
}

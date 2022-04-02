import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayScore implements ActionListener
{
    JFrame f;
    JButton b1;
    JLabel l;
    JPanel p1,p2;
    DefaultListModel<String> l1 = new DefaultListModel<>();
    JList<String> lis=new JList<>(l1);
    int s_id;
    String quiz_name,s_name;

    DisplayScore(int s_id,String s_name)
    {
        this.s_id=s_id;
        this.s_name=s_name;

        f=new JFrame(s_name+"'scores");        
        p1=new JPanel();
        l=new JLabel("Hello "+s_name);
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);

        display();
        p2=new JPanel();
        p2.setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(lis);
        lis.setBounds(20,20,350,240);
        lis.setFont(new Font("lucida bright",1,15));
        p2.add(lis);

        b1=new JButton("Exit");
        b1.addActionListener(this);

        f.add(p1,BorderLayout.NORTH);
        f.add(p2);
        f.add(b1,BorderLayout.SOUTH);
        f.setVisible(true);
        f.setSize(400,400);
        f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){f.dispose();}});
    }    
    public void actionPerformed(ActionEvent e)
    {
        f.dispose();
    }
    void display()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/user_data?user=root&password=pk1234");
            String query="select quiz_name,score from scores where s_id=?;";
            PreparedStatement st=cn.prepareStatement(query);
            st.setInt(1,s_id);
            ResultSet rs=st.executeQuery(); 
            while(rs.next())
            l1.addElement(rs.getString(1)+"---------------"+rs.getString(2));
            cn.close();
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
    
}
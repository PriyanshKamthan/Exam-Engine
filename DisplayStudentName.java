import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayStudentName implements ActionListener
{
    JFrame f;
    JButton b1;
    JLabel l;
    JPanel p1,p2;
    DefaultListModel<String> l1 = new DefaultListModel<>();
    JList<String> lis=new JList<>(l1);
    DisplayStudentName()
    {
        f=new JFrame("All Students");        
        p1=new JPanel();
        l=new JLabel("Students");
        l.setFont(new Font("lucida bright",0,40));
        p1.add(l);
        
        displayStudents();
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
    void displayStudents()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/user_data?user=root&password=pk1234");
            String query="select s_name,s_id from student order by s_name;";
            PreparedStatement st=cn.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
                l1.addElement(rs.getString(2)+"........."+rs.getString(1));
            }
            cn.close();
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AttemptQuiz implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JLabel L,L1;
    JPanel p1,p2,p3;
    JLabel l,que;
    ButtonGroup group = new ButtonGroup();
    JRadioButton op[]=new JRadioButton[4];
    JLabel t1,t2,t3,t4;
    int q_id=1,score,ans;

    int s_id;
    String quiz_name,s_name;

    AttemptQuiz(String quiz_name,int s_id,String s_name)
    {
        this.quiz_name=quiz_name;
        this.s_id=s_id;
        this.s_name=s_name;

        f=new JFrame("Attempt quiz");       
        p1=new JPanel();
        L=new JLabel(quiz_name);
        L.setFont(new Font("lucida bright",0,40));
        p1.add(L);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,2));
        b1=new JButton("Next question");
        b2=new JButton("Finish Attempt");
        b1.setFont(new Font("lucida bright",1,15));
        b2.setFont(new Font("lucida bright",1,15));
        p2.add(b2);
        p2.add(b1);
        b1.addActionListener(this);
        b2.addActionListener(this);

        p3=new JPanel();
        p3.setLayout(null);
        l=new JLabel("Que "+q_id);
        que=new JLabel("");
        op[0]=new JRadioButton("1");
        op[1]=new JRadioButton("2");
        op[2]=new JRadioButton("3");
        op[3]=new JRadioButton("4");
        L1=new JLabel("Score: "+score);
        L1.setFont(new Font("lucida bright",0,20));
        t1=new JLabel();
        t2=new JLabel();
        t3=new JLabel();
        t4=new JLabel();

        l.setBounds(20,10,55,50);
        que.setBounds(80,10,700,50);
        op[0].setBounds(50,90,75,30);
        t1.setBounds(125,90,250,30);
        op[1].setBounds(430,90,75,30);
        t2.setBounds(505,90,250,30);
        op[2].setBounds(50,130,75,30);
        t3.setBounds(125,130,250,30);
        op[3].setBounds(430,130,75,30);
        t4.setBounds(505,130,250,30);
        L1.setBounds(350,190,100,30);

        l.setFont(new Font("lucida bright",1,15));
        que.setFont(new Font("lucida bright",1,15));
        op[0].setFont(new Font("lucida bright",1,15));
        op[1].setFont(new Font("lucida bright",1,15));
        op[2].setFont(new Font("lucida bright",1,15));
        op[3].setFont(new Font("lucida bright",1,15));
        t1.setFont(new Font("lucida bright",0,15));
        t2.setFont(new Font("lucida bright",0,15));
        t3.setFont(new Font("lucida bright",0,15));
        t4.setFont(new Font("lucida bright",0,15));
      
        p3.add(l);
        p3.add(que);
        group.add(op[0]);
        group.add(op[1]);
        group.add(op[2]);
        group.add(op[3]);
        p3.add(op[0]);
        p3.add(op[1]);
        p3.add(op[2]);
        p3.add(op[3]);
        p3.add(t1);
        p3.add(t2);
        p3.add(t3);
        p3.add(t4);
        p3.add(L1);
        attemptQuiz(quiz_name);
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
            check();
            group.clearSelection();
            for(int i=0;i<=3;i++)
            op[i].setEnabled(true);
            attemptQuiz(quiz_name);
        }

        if(bt==b2)
        {
            String str=s_name+" have scored: "+score+" in "+quiz_name;
            JOptionPane.showMessageDialog(f,str);
            new DataHandler().saveScore(s_id, s_name, quiz_name, score);
            f.dispose();
        }
    }

    void attemptQuiz(String quiz_name)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/exam?user=root&password=pk1234");
            String query="select * from "+quiz_name+" where q_id="+q_id+";";
            PreparedStatement st=cn.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                l.setText("Que: "+rs.getString(1));
                que.setText(rs.getString(2));
                t1.setText(rs.getString(3));
                t2.setText(rs.getString(4));
                t3.setText(rs.getString(5));
                t4.setText(rs.getString(6));
                ans=rs.getInt(7);
            }
            else
            {
                for(int i=0;i<=3;i++)
                op[i].setEnabled(false);
                JOptionPane.showMessageDialog(f,"Questions are over");
                //saveScore();
            }
            q_id++;
            cn.close();
        }
        catch(Exception e1)
        {
            JOptionPane.showMessageDialog(f,e1.getMessage());
            f.dispose();
        }
    }

    void check()
    {
        if(op[--ans].isSelected())
        {
            score++;
            L1.setText("Score: "+score);
        }
        for(int i=0;i<=3;i++)
        op[i].setEnabled(false);
    }
}
import java.sql.*;

public class DataHandler
{
    boolean check(int id,String pswd,String category)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/user_data?user=root&password=pk1234");
            String query=null;
            if(category.equalsIgnoreCase("student"))
            query="select S_NAME from student where S_ID=? and S_PSWD=?;";
            else if(category.equalsIgnoreCase("admin"))
            query="select A_NAME from admin where A_ID=? and A_PSWD=?;";

            PreparedStatement st=cn.prepareStatement(query);
            st.setInt(1,id);
            st.setString(2,pswd);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                Main.name=rs.getString(1);
                cn.close();
                return true;
            }
            else{cn.close();return false;}
        }
        catch(Exception e1){e1.getMessage();return false;}
    }

    boolean addStudent(int s_id,String s_name,String s_pswd)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/user_data?user=root&password=pk1234");
            String query="insert into student values(?,?,?);";
            PreparedStatement st=cn.prepareStatement(query);
            st.setInt(1,s_id);
            st.setString(2,s_name);
            st.setString(3,s_pswd);
            st.executeUpdate();
            cn.close();           
            return true;
        }
        catch(Exception e){System.out.println(e.getMessage());return false;}
    }
    
    boolean createQuiz(String quiz_name)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/exam?user=root&password=pk1234");
            String query="create table "+quiz_name+" (Q_ID INT,Que VARCHAR(150),Option1 VARCHAR(20),Option2 VARCHAR(20),Option3 VARCHAR(20),Option4 VARCHAR(20),Answer VARCHAR(20),PRIMARY KEY (Q_ID));";
            PreparedStatement st=cn.prepareStatement(query);
            st.executeUpdate();
            cn.close();
            return true;
        }
        catch(Exception e){System.out.println(e.getMessage());return false;}
    }

    void addQueToQuizz(String quiz_name,int q_id,String que,String op1,String op2,String op3,String op4,int ans)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/exam?user=root&password=pk1234");
            String query="insert into "+quiz_name+" values(?,?,?,?,?,?,?);";
            PreparedStatement st=cn.prepareStatement(query);
            st.setInt(1, q_id);
            st.setString(2,que);
            st.setString(3,op1);
            st.setString(4,op2);
            st.setString(5,op3);
            st.setString(6,op4);
            st.setInt(7,ans);
            st.executeUpdate();
            cn.close();
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }

    boolean saveScore(int s_id,String s_name,String quiz_name,int score)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/user_data?user=root&password=pk1234");
            String query="insert into scores values(?,?,?,?);";
            PreparedStatement st=cn.prepareStatement(query);
            st.setInt(1,s_id);
            st.setString(2,s_name);
            st.setString(3,quiz_name);
            st.setInt(4,score);
            st.executeUpdate();
            cn.close();
            return true;
        }
        catch(Exception e){System.out.println(e.getMessage());return false;}
    }

    
}
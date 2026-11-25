package JDBC_Oracle_Project;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class frameScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Login");
        frame.setSize(300,200);
        JPanel panel = new JPanel();
        JTextField Name = new JTextField(20);
        JTextField RollNo = new JTextField(20);
        JTextField Dept = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(Name);
        panel.add(new JLabel("Roll No:"));
        panel.add(RollNo);
        panel.add(new JLabel("Department:"));
        panel.add(Dept);

        JButton button = new JButton("Login");
        button.addActionListener(e -> {
            // Database connection details
            String url = "jdbc:oracle:thin:@//127.0.0.1:1521/FREEPDB1"; // "jdbc:oracle:thin:@//localhost:1521/orcl";
            String username = "STUDENT_APP"; // "SCOTT";
            String password = "Indresh123#"; // "THEIR_SCOTT_PASSWORD";

            String name = Name.getText();
            String rollNo = RollNo.getText();
            String dept = Dept.getText();

            String query = "INSERT INTO STUDENT_DETAILS_CS100 (name,rollno,dept) VALUES(?,?,?)";

            try(Connection con = DriverManager.getConnection(url,username,password);
                PreparedStatement ps = con.prepareStatement(query); )
            {
                ps.setString(1,name);
                ps.setString(2,rollNo);
                ps.setString(3,dept);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Thankyou, your data is saved");
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

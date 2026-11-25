package JDBC_Oracle_Project;

import javax.swing.*;
import java.awt.*;

public class JdbcConnectionTest {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Frame");

        // Heading
        JPanel heading = new JPanel(new FlowLayout(FlowLayout.CENTER));
        heading.add(new JLabel("Student Login"));
        heading.setBorder(BorderFactory.createEmptyBorder(10,5,20,5));

        // Form: Name, RollNo, Dept
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        form.add(new JLabel("Name:"));
        form.add(new JTextField(20));

        form.add(new JLabel("Roll No:"));
        form.add(new JTextField(20));

        form.add(new JLabel("Department:"));
        form.add(new JTextField(20));

        // MainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // SubmitButton
        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainPanel, "Data is loaded!");
        });

        // Add Heading,From,SubmitButton to the MainPanel
        mainPanel.add(heading);
        mainPanel.add(form);
        mainPanel.add(Box.createVerticalStrut(15));// Add some space above the button
        mainPanel.add(submitButton);

        frame.add(mainPanel);
        // Automatically fit the window to the components
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
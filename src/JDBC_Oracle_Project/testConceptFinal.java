package JDBC_Oracle_Project;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testConceptFinal {

    // Oracle Database running inside the VirtualBox VM.
    private static final String URL =
            "jdbc:oracle:thin:@//127.0.0.1:1521/FREEPDB1";
    private static final String USERNAME = "STUDENT_APP";

    // Set this in Windows Command Prompt before running the application:
    // set "ORACLE_DB_PASSWORD=your_STUDENT_APP_password"
    private static String getDatabasePassword() throws SQLException {
        String password = System.getenv("ORACLE_DB_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new SQLException(
                    "Environment variable ORACLE_DB_PASSWORD is not set.\n"
                            + "In Command Prompt, run: set \"ORACLE_DB_PASSWORD=your_password\""
            );
        }
        return password;
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException(
                    "Oracle JDBC driver not found. Check that ojdbc17.jar is included in the classpath.",
                    ex
            );
        }

        return DriverManager.getConnection(URL, USERNAME, getDatabasePassword());
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Marks");

        // Heading
        JPanel heading = new JPanel(new FlowLayout(FlowLayout.CENTER));
        heading.add(new JLabel("Student Marks"));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 5, 20, 5));

        // Form: Name, RollNo, Department
        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));

        JTextField nameField = new JTextField(20);
        JTextField rollNoField = new JTextField(20);
        JTextField deptField = new JTextField(20);

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Roll No:"));
        form.add(rollNoField);
        form.add(new JLabel("Department:"));
        form.add(deptField);

        // MainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Temporary panel for entering all 8 SGPAs
        JPanel sgpaInputPanel = new JPanel(new BorderLayout(5, 5));
        sgpaInputPanel.setBorder(
                BorderFactory.createTitledBorder("Enter SGPA for All 8 Semesters")
        );

        // Four rows, with two semester inputs per row
        JPanel sgpaFieldsPanel = new JPanel(new GridLayout(4, 4, 8, 8));
        JTextField[] semSGPAFields = new JTextField[8];

        for (int i = 0; i < 8; i++) {
            sgpaFieldsPanel.add(new JLabel("Sem " + (i + 1) + " SGPA:"));
            semSGPAFields[i] = new JTextField(6);
            sgpaFieldsPanel.add(semSGPAFields[i]);
        }

        sgpaInputPanel.add(sgpaFieldsPanel, BorderLayout.CENTER);
        sgpaInputPanel.add(
                new JLabel("Enter values from 0.0 to 10.0."),
                BorderLayout.SOUTH
        );
        sgpaInputPanel.setVisible(false);

        // Progress Report panel
        JPanel reportPanel = new JPanel(new BorderLayout(5, 5));
        reportPanel.setVisible(false);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);

        // enteringSGPA = true means the user is entering semester results
        boolean[] enteringSGPA = {false};

        // Execute this code when the Submit button is clicked
        submitButton.addActionListener(e -> {

            // CASE 1: User is entering the 8 SGPAs
            if (enteringSGPA[0]) {

                double[] semSGPAs = new double[8];
                double totalSGPA = 0.0;

                // Read and validate all 8 SGPAs
                for (int i = 0; i < 8; i++) {

                    String value = semSGPAFields[i].getText().trim();

                    if (value.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Please enter SGPA for Semester " + (i + 1)
                        );
                        return;
                    }

                    try {
                        double sgpa = Double.parseDouble(value);

                        if (!Double.isFinite(sgpa) || sgpa < 0.0 || sgpa > 10.0) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "SGPA must be between 0.0 and 10.0."
                            );
                            return;
                        }

                        // Store with two decimal places
                        semSGPAs[i] = Math.round(sgpa * 100.0) / 100.0;
                        totalSGPA += semSGPAs[i];

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Enter a valid numeric SGPA for Semester " + (i + 1)
                        );
                        return;
                    }
                }

                // Calculate CGPA using the simple average
                double cgpa = Math.round((totalSGPA / 8.0) * 100.0) / 100.0;

                // Calculate Grade from CGPA
                String grade;
                if (cgpa >= 9.0) {
                    grade = "A";
                } else if (cgpa >= 8.0) {
                    grade = "B";
                } else if (cgpa >= 7.0) {
                    grade = "C";
                } else if (cgpa >= 6.0) {
                    grade = "D";
                } else if (cgpa >= 5.0) {
                    grade = "E";
                } else {
                    grade = "F";
                }

                // Calculate Pass / Fail
                String result = cgpa < 5.0 ? "Fail" : "Pass";

                String name = nameField.getText().trim();
                String rollNo = rollNoField.getText().trim();
                String department = deptField.getText().trim();

                // Insert the complete student record into Oracle Database
                String insertSql = """
                        INSERT INTO students
                        (name, roll_no, department,
                        sem1_sgpa, sem2_sgpa, sem3_sgpa, sem4_sgpa,
                        sem5_sgpa, sem6_sgpa, sem7_sgpa, sem8_sgpa,
                        cgpa, grade)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;

                try (
                        Connection connection = getConnection();
                        PreparedStatement statement = connection.prepareStatement(insertSql)
                ) {
                    int index = 1;
                    statement.setString(index++, name);
                    statement.setString(index++, rollNo);
                    statement.setString(index++, department);

                    for (double sgpa : semSGPAs) {
                        statement.setDouble(index++, sgpa);
                    }

                    statement.setDouble(index++, cgpa);
                    statement.setString(index, grade);

                    int rowsSaved = statement.executeUpdate();

                    if (rowsSaved > 0) {
                        // Remove the temporary SGPA fields
                        sgpaInputPanel.setVisible(false);

                        // Restore the original button and form fields
                        submitButton.setText("Submit");
                        nameField.setEnabled(true);
                        rollNoField.setEnabled(true);
                        deptField.setEnabled(true);
                        enteringSGPA[0] = false;

                        // Display the saved Progress Report
                        displayProgressReport(
                                reportPanel,
                                name,
                                rollNo,
                                department,
                                semSGPAs,
                                cgpa,
                                grade,
                                result
                        );

                        mainPanel.revalidate();
                        mainPanel.repaint();
                        frame.pack();
                        frame.setLocationRelativeTo(null);

                        JOptionPane.showMessageDialog(frame, "Data stored successfully!");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Failed to store data:\n" + ex.getMessage(),
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }

                return;
            }

            // CASE 2: Initial Submit - Check the Roll Number
            String rollNo = rollNoField.getText().trim();

            if (rollNo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Roll No.");
                return;
            }

            // Hide any previously displayed report or SGPA fields
            sgpaInputPanel.setVisible(false);
            reportPanel.setVisible(false);

            // Search for the student using Roll No
            String selectSql = """
                    SELECT name, roll_no, department,
                    sem1_sgpa, sem2_sgpa, sem3_sgpa, sem4_sgpa,
                    sem5_sgpa, sem6_sgpa, sem7_sgpa, sem8_sgpa,
                    cgpa, grade
                    FROM students
                    WHERE roll_no = ?
                    """;

            try (
                    Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(selectSql)
            ) {
                statement.setString(1, rollNo);

                try (ResultSet rs = statement.executeQuery()) {

                    // STUDENT EXISTS
                    if (rs.next()) {
                        String dbName = rs.getString("name");
                        String dbRollNo = rs.getString("roll_no");
                        String dbDepartment = rs.getString("department");

                        double[] semSGPAs = new double[8];

                        // Fetch all 8 semester SGPAs
                        for (int i = 0; i < 8; i++) {
                            semSGPAs[i] = rs.getDouble("sem" + (i + 1) + "_sgpa");
                        }

                        double cgpa = rs.getDouble("cgpa");
                        String grade = rs.getString("grade");
                        String result = cgpa < 5.0 ? "Fail" : "Pass";

                        // Display the details fetched from Oracle Database
                        nameField.setText(dbName);
                        rollNoField.setText(dbRollNo);
                        deptField.setText(dbDepartment);
                        enteringSGPA[0] = false;

                        nameField.setEnabled(true);
                        rollNoField.setEnabled(true);
                        deptField.setEnabled(true);

                        displayProgressReport(
                                reportPanel,
                                dbName,
                                dbRollNo,
                                dbDepartment,
                                semSGPAs,
                                cgpa,
                                grade,
                                result
                        );

                    } else {
                        // STUDENT DOES NOT EXIST
                        String name = nameField.getText().trim();
                        String department = deptField.getText().trim();

                        if (name.isEmpty() || department.isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "For a new student, please enter Name, Roll No and Department."
                            );
                            return;
                        }

                        // Do not save yet. First ask the user for all 8 SGPAs.
                        enteringSGPA[0] = true;

                        for (JTextField field : semSGPAFields) {
                            field.setText("");
                        }

                        nameField.setEnabled(false);
                        rollNoField.setEnabled(false);
                        deptField.setEnabled(false);
                        sgpaInputPanel.setVisible(true);
                        submitButton.setText("Save Progress Report");

                        JOptionPane.showMessageDialog(
                                frame,
                                "New student detected. Enter the SGPA for all 8 semesters, "
                                        + "then click 'Save Progress Report'."
                        );
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Database error:\n" + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }

            // Refresh the GUI after showing the report or SGPA fields
            mainPanel.revalidate();
            mainPanel.repaint();
            frame.pack();
            frame.setLocationRelativeTo(null);
        });

        // Add Heading, Form, SGPA fields, Button and Report
        mainPanel.add(heading);
        mainPanel.add(form);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(sgpaInputPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(submitButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(reportPanel);

        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Display the same report for existing and new students
    private static void displayProgressReport(
            JPanel reportPanel,
            String name,
            String rollNo,
            String department,
            double[] semSGPAs,
            double cgpa,
            String grade,
            String result
    ) {
        reportPanel.removeAll();
        reportPanel.setLayout(new BorderLayout(8, 8));
        reportPanel.setBorder(BorderFactory.createTitledBorder("Progress Report"));

        JLabel reportHeading = new JLabel(
                "Student Progress Report",
                SwingConstants.CENTER
        );
        reportHeading.setFont(new Font("Arial", Font.BOLD, 16));

        // 7 rows, 4 columns
        JPanel details = new JPanel(new GridLayout(7, 4, 8, 6));
        details.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 0));

        details.add(new JLabel("Name:"));
        details.add(new JLabel(name));
        details.add(new JLabel("Roll No:"));
        details.add(new JLabel(rollNo));
        details.add(new JLabel("Department:"));
        details.add(new JLabel(department));
        details.add(new JLabel("CGPA:"));
        details.add(new JLabel(String.format("%.2f", cgpa)));

        // Display two semesters per row
        for (int i = 0; i < 8; i += 2) {
            details.add(new JLabel("Semester " + (i + 1) + " SGPA:"));
            details.add(new JLabel(String.format("%.2f", semSGPAs[i])));
            details.add(new JLabel("Semester " + (i + 2) + " SGPA:"));
            details.add(new JLabel(String.format("%.2f", semSGPAs[i + 1])));
        }

        details.add(new JLabel("Grade:"));
        details.add(new JLabel(grade));
        details.add(new JLabel("Result:"));
        details.add(new JLabel(result));

        reportPanel.add(reportHeading, BorderLayout.NORTH);
        reportPanel.add(details, BorderLayout.CENTER);
        reportPanel.setVisible(true);
    }
}

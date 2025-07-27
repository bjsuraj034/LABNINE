import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudent extends JFrame {
    JTextField nameField, ageField;
    JButton addButton;

    public AddStudent() {
        setTitle("Add Student");
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        addButton = new JButton("Add");
        add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name, age) VALUES (?, ?)");
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}

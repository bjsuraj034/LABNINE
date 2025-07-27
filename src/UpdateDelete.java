import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateDelete extends JFrame {
    JTextField idField, nameField, ageField;
    JButton updateBtn, deleteBtn;

    public UpdateDelete() {
        setTitle("Update/Delete Student");
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("New Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("New Age:"));
        ageField = new JTextField();
        add(ageField);

        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        add(updateBtn);
        add(deleteBtn);

        updateBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("UPDATE students SET name=?, age=? WHERE id=?");
                ps.setString(1, nameField.getText());
                ps.setInt(2, Integer.parseInt(ageField.getText()));
                ps.setInt(3, Integer.parseInt(idField.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student Updated!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE id=?");
                ps.setInt(1, Integer.parseInt(idField.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student Deleted!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new UpdateDelete();
    }
}

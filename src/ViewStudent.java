import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewStudent extends JFrame {
    JTextArea textArea;

    public ViewStudent() {
        setTitle("View Students");
        textArea = new JTextArea(20, 40);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                textArea.append("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ViewStudent();
    }
}

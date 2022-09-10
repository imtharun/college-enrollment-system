import javax.swing.*;
import java.sql.*;

public class ViewStudDetails extends JFrame{
    JLabel name, dob, gender, email, phone, mark10, mark12, cutoff, degree, course;
    JLabel forName, forDOB, forGender, forEmail, forPhone, forMark10, forMark12, forCutoff, forDegree, forCourse, empty;

    public ViewStudDetails(int username){
        name = new JLabel("Name: ");
        dob = new JLabel("DOB: ");
        gender = new JLabel("Gender: ");
        email = new JLabel("Email: ");
        degree = new JLabel("Degree");
        course = new JLabel("Course");
        phone = new JLabel("Phone: ");
        mark10 = new JLabel("10th Mark: ");
        mark12 = new JLabel("12th Mark: ");
        cutoff = new JLabel("Cutoff: ");

        empty = new JLabel();

        forName = new JLabel("");
        forDOB = new JLabel("");
        forGender = new JLabel("");
        forPhone = new JLabel("");
        forEmail = new JLabel("");
        forDegree = new JLabel("");
        forCourse = new JLabel("");
        forMark10 = new JLabel("");
        forMark12 = new JLabel("");
        forCutoff = new JLabel("");

        name.setBounds(30, 30, 80, 30);forName.setBounds(120, 30, 150, 30);
        dob.setBounds(30, 60, 80, 30); forDOB.setBounds(120, 60, 80, 30);
        gender.setBounds(30, 90, 80, 30);forGender.setBounds(120, 90, 80, 30);
        email.setBounds(30, 120, 80, 30);forEmail.setBounds(120, 120, 180, 30);
        degree.setBounds(30, 150, 80, 30);forDegree.setBounds(120, 150, 80, 30);
        course.setBounds(30, 180, 80, 30);forCourse.setBounds(120, 180, 180, 30);
        phone.setBounds(30, 210, 80, 30);forPhone.setBounds(120, 210, 80, 30);
        mark10.setBounds(30, 240, 80, 30);forMark10.setBounds(120, 240, 80, 30);
        mark12.setBounds(30, 270, 80, 30);forMark12.setBounds(120, 270, 80, 30);
        cutoff.setBounds(30, 300, 80, 30);forCutoff.setBounds(120, 300, 80, 30);

        add(name);add(forName);
        add(dob);add(forDOB);
        add(gender);add(forGender);
        add(email);add(forEmail);
        add(degree);add(forDegree);
        add(course);add(forCourse);
        add(phone);add(forPhone);
        add(mark10);add(forMark10);
        add(mark12);add(forMark12);
        add(cutoff);add(forCutoff);add(empty);

        try {
            System.out.println(username);
            String sql = "SELECT * FROM STUD WHERE USERNAME=?";

            Connection con = DriverManager.getConnection("jdbc:derby:studdb;create=true");
            // Statement st = con.createStatement();

            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1, username);

            ResultSet rs = psmt.executeQuery();

            while(rs.next()){
                forName.setText(rs.getString(1));
                forDOB.setText(rs.getString(2));
                forGender.setText(rs.getString(3));
                forEmail.setText(rs.getString(4));
                forPhone.setText(rs.getString(5));
                forDegree.setText(rs.getString(6));
                forCourse.setText(rs.getString(7));
                forMark10.setText(rs.getString(8));
                forMark12.setText(rs.getString(9));
                forCutoff.setText(rs.getString(10));
            }
            System.out.println("Success");

        } catch (Exception exp) {
            exp.printStackTrace();
        }

        setVisible(true);
        setSize(500, 500);
    }
}

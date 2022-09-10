import javax.swing.*;
import java.sql.*;

public class ViewStaffDetails extends JFrame{
    JLabel name, dob, gender, email, phone, educ, addrs;
    JLabel forName, forDOB, forGender, forEmail, forPhone, forEduc, forAddr, empty;

    public ViewStaffDetails(int username){
        name = new JLabel("Name: ");
        dob = new JLabel("DOB: ");
        gender = new JLabel("Gender: ");
        email = new JLabel("Email: ");
        educ = new JLabel("Education:");
        phone = new JLabel("Phone: ");
        addrs = new JLabel("Address: ");
        empty = new JLabel();

        forName = new JLabel("");
        forDOB = new JLabel("");
        forGender = new JLabel("");
        forPhone = new JLabel("");
        forEmail = new JLabel("");
        forEduc = new JLabel("");
        forAddr = new JLabel("");


        name.setBounds(30, 30, 80, 30);forName.setBounds(120, 30, 130, 30);
        dob.setBounds(30, 60, 80, 30); forDOB.setBounds(120, 60, 80, 30);
        gender.setBounds(30, 90, 80, 30);forGender.setBounds(120, 90, 80, 30);
        email.setBounds(30, 120, 80, 30);forEmail.setBounds(120, 120, 130, 30);
        phone.setBounds(30, 150, 80, 30);forPhone.setBounds(120, 150, 100, 30);
        educ.setBounds(30, 180, 80, 30);forEduc.setBounds(120, 180, 160, 30);
        addrs.setBounds(30, 210, 80, 30);forAddr.setBounds(120, 210, 160, 30);

        add(name);add(forName);
        add(dob);add(forDOB);
        add(gender);add(forGender);
        add(email);add(forEmail);
        add(phone);add(forPhone);
        add(educ);add(forEduc);
        add(addrs);add(forAddr);
        add(empty);

        try {
            System.out.println(username);
            String sql = "SELECT * FROM STAFF WHERE USERNAME=?";

            Connection con = DriverManager.getConnection("jdbc:derby:staffdb;create=true");
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
                forEduc.setText(rs.getString(6));
                forAddr.setText(rs.getString(7));
            }
            System.out.println("Success");

        } catch (Exception exp) {
            exp.printStackTrace();
        }

        setVisible(true);
        setSize(500, 500);
    }
}

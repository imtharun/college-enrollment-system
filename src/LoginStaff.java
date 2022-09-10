import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class LoginStaff extends JFrame implements ActionListener{
    JLabel header, username, pass;
    JPasswordField forPass;
    JTextField forUsername;
    JButton login, reset, empty;
    JCheckBox show;

    public LoginStaff(){
        header = new JLabel("Log in");
        username = new JLabel("Username: ");
        pass = new JLabel("Password: ");
        login = new JButton("Log in");
        reset = new JButton("Reset");
        show = new JCheckBox("Show password");
        empty = new JButton();

        forUsername = new JTextField();
        forPass = new JPasswordField();

        header.setBounds(120, 20, 50, 30);
        header.setFont(new Font("Sans", Font.BOLD, 16));
        username.setBounds(20, 60, 80, 30);
        forUsername.setBounds(120, 60, 150, 30);
        pass.setBounds(20, 100, 80, 30);
        forPass.setBounds(120, 100, 150, 30);
        login.setBounds(50, 170, 80, 30);
        reset.setBounds(140, 170, 80, 30);
        show.setBounds(120, 130, 120, 20);

        add(header);
        add(username);
        add(pass);
        add(forUsername);
        add(forPass);
        add(login);
        add(reset);
        add(show);
        add(empty);

        login.addActionListener(this);
        reset.addActionListener(this);
        show.addActionListener(this);
        
        forPass.setEchoChar('*');
        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setSize(330, 300);
        setTitle("Login Page");
        // pack();
    }

    public void actionPerformed(ActionEvent ae){
        if (show.isSelected()) {
            forPass.setEchoChar((char)0); //password = JPasswordField
         } else {
            forPass.setEchoChar('*');
         }
        if(ae.getSource() == login){
            try{
                HashMap<Integer, String> hm = new HashMap<>();

                Connection con = DriverManager.getConnection("jdbc:derby:staffdb;create=true");

                String sql = "SELECT USERNAME, PASSWORD FROM STAFF";
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery(sql);

                while(rs.next()){
                    int s1 = rs.getInt(1);
                    String s2 = rs.getString(2);
                    hm.put(s1, s2);
                }

                int user = Integer.parseInt(forUsername.getText());
                String pass = String.valueOf(forPass.getPassword());

                System.out.println(hm);

                String checkPass = "";
                int checkUser = -1;

                if(hm.containsKey(user)){
                    checkUser = user;
                    checkPass = hm.get(user);
                }
                if(user == checkUser && pass.toLowerCase().equals(checkPass.toLowerCase())){
                    System.out.println("Logged in successfully");
                    setVisible(false);
                    new ViewStaffDetails(user);
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }

            }catch(Exception exp){
                exp.printStackTrace();
            }
            
        }

        
        if(ae.getSource() == reset){
            forPass.setText("");
            forUsername.setText("");
        }

    }
}

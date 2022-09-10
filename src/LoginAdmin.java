import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoginAdmin extends JFrame implements ActionListener{
    JLabel header, username, pass;
    JPasswordField forPass;
    JTextField forUsername;
    JButton login, reset, empty;
    JCheckBox show;

    public LoginAdmin(){
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

        login.setBackground(Color.GREEN);
        reset.setBackground(new Color(255, 102, 102));

        login.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login.setBackground(new Color(0, 204, 0));
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login.setBackground(Color.GREEN);
            }
        });

        // show.setForeground(Color.ORANGE);

        reset.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reset.setBackground(new Color(255, 0, 0));
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reset.setBackground(new Color(255, 102, 102));
            }
        });

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
            String pass = String.valueOf(forPass.getPassword());
            String user = forUsername.getText().toString();
            if(pass.equalsIgnoreCase("1234") && user.equalsIgnoreCase("admin")){
                System.out.println("Logged in successfully");
                setVisible(false);
                new DB();
            }else{
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        
        if(ae.getSource() == reset){
            forPass.setText("");
            forUsername.setText("");
        }

    }
}

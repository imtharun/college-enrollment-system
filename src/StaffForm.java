import java.awt.*;
import java.awt.event.*;
import com.sun.mail.smtp.SMTPTransport;
import java.io.*;
import java.security.Security;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.*;
import java.sql.*;

public class StaffForm extends JFrame implements ActionListener{
    JLabel rform, name, dob, gender, address, email, phoneNumber, education;
    JRadioButton male, female;
    JTextField forName, forEmail, forPhoneNumber;
    JComboBox<String[]> forDate, forMonth, forYears;
    JTextArea forAddress, forEducation;
    ButtonGroup forGender;
    JButton forSubmit, forRefresh;
    
    String[] dates = new String[31];
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"}; 
    String[] years = new String[10];
    int start = 1996;

    public StaffForm(){
        for(int i = 1; i <= dates.length; i++){
            dates[i - 1] = Integer.toString(i);
        }
        for (int i = 0; i < years.length; i++) {
            years[i] = Integer.toString(start);
            start++;
        }
        rform = new JLabel("Staff Registration Form");
        name = new JLabel("Name: ");
        dob = new JLabel("DOB: ");
        gender = new JLabel("Gender: ");
        email = new JLabel("Email: ");
        phoneNumber = new JLabel("Phone: ");
        education = new JLabel("Education: ");
        address = new JLabel("Address: ");
        forSubmit = new JButton("Submit");
        forRefresh = new JButton("Refresh");
        forAddress = new JTextArea();
        forEducation = new JTextArea();
        forName = new JTextField();
        forEmail = new JTextField();
        forPhoneNumber = new JTextField();
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        forDate = new JComboBox(dates);
        forMonth = new JComboBox(months);
        forYears = new JComboBox(years);
        forDate.setSelectedItem(null);
        forMonth.setSelectedItem(null);
        forYears.setSelectedItem(null);
        forGender = new ButtonGroup();
        
        
        rform.setBounds(150, 10, 300, 50);
        rform.setFont(new Font("Sans", Font.BOLD, 20));
        name.setBounds(30, 50, 60, 50);forName.setBounds(100, 65, 170, 30);
        email.setBounds(31,85, 60, 50);forEmail.setBounds(100, 100, 170, 30);
        gender.setBounds(30, 125, 60, 50);
        male.setBounds(95, 140, 60, 20);female.setBounds(170, 140, 70, 20);
        dob.setBounds(30, 170, 60, 50);forDate.setBounds(100, 185, 50, 30);
        forMonth.setBounds(150, 185, 50, 30);forYears.setBounds(200, 185, 70, 30);
        phoneNumber.setBounds(30, 225, 50, 50);forPhoneNumber.setBounds(100, 230, 170 , 30);
        education.setBounds(30, 285, 80, 50);forEducation.setBounds(100, 300, 170, 80);
        address.setBounds(30, 400, 80, 30);forAddress.setBounds(100, 400, 170, 80);
        forSubmit.setBounds(80, 500, 80, 30); forRefresh.setBounds(180, 500, 80, 30);

        forSubmit.addActionListener(this);
        forRefresh.addActionListener(this);
        
        add(rform);
        add(name);add(forName);
        add(email);add(forEmail);
        add(gender);add(male);add(female);
        forGender.add(male);
        forGender.add(female);
        add(dob);add(forDate);add(forMonth);add(forYears);
        add(education);add(forEducation);
        add(phoneNumber);add(forPhoneNumber);
        add(address);add(forAddress);add(forSubmit);add(forRefresh);

        setBackground(Color.GRAY);
        setLayout(null);
        setSize(650, 650);
        setVisible(true);
        setTitle("College Registration form");
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == forSubmit){
            String gen;
            if(male.isSelected()){
                gen = "Male";
            }else{
                gen = "Female";
            }
            
            int i1 = forDate.getSelectedIndex(), i2 = forMonth.getSelectedIndex(), i3 = forYears.getSelectedIndex();
            String dat = dates[i1], mnth = months[i2], yar = years[i3];
            String name = forName.getText();
            String dob = dat + "-" + mnth + "-" + yar;
            String mail = forEmail.getText();
            String phnumber = forPhoneNumber.getText();
            String edu = forEducation.getText();
            String addrss = forAddress.getText();
            database db = new database();

            try{
                if(true){
                    System.out.println("New");
                    db.initStaff();
                    // db.createStaff();
                    db.insertStaff(name, dob, gen, mail, phnumber, edu, addrss);
                }

                Connection con = DriverManager.getConnection("jdbc:derby:staffdb;create=true");
                String query = "SELECT EMAIL, USERNAME, PASSWORD FROM STAFF WHERE PHONE=?";
                PreparedStatement psmt = con.prepareStatement(query);
                psmt.setString(1, phnumber);

                ResultSet rs = psmt.executeQuery();
                
                String to = "", user = "", pass = "";
                while(rs.next()){
                    to = rs.getString(1);
                    user = rs.getString(2);
                    pass = rs.getString(3);
                }

                String from = "aj.tharun5@gmail.com";
                String host = "127.0.0.1";//or IP address

                //Get the session object, SMTP -> SIMPLE MAIL TRANSFER PROTOCOL
                Properties properties = System.getProperties();
                properties.setProperty("mail.smtp.host", host);
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                String RealPass =  "{5Jr=c%0d3j6e]*>}";

                Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, RealPass);
                    }
                });

                //compose the message
                try{
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                    message.setSubject("Reg: username and password");
                    message.setText("Hello, this is example of sending email  ");

                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText("Username and password");
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    File f = new File("C:\\Users\\ajtha\\Coding\\JavaPresentation\\CollegeEnrollementSystem\\file\\Docs.txt");
                    if(f.createNewFile()){
                        System.out.println("file created");
                    }else{
                        System.out.println("file exist");
                    }

                    String msg = "Hello, This is from PSG COLLEGE OF TECHNOLOGY. This is your username and password: " + user + " " + pass;

                    BufferedWriter bf = new BufferedWriter(new FileWriter(f));
                    bf.write(msg);
                    bf.flush();
                    attachmentPart.attachFile(f);
                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    multipart.addBodyPart(attachmentPart);

                    message.setContent(multipart);
                    Transport.send(message);
                    System.out.println("Email is sent successfully");
                }catch (Exception exp) {exp.printStackTrace();}

            }catch(Exception exp){
                System.out.println("In stack tree of Staffform");
                exp.printStackTrace();
            }
            System.out.println("Submitted");
        }

        if(ae.getSource() == forRefresh){
            forGender.clearSelection();
            forName.setText("");
            forPhoneNumber.setText("");
            forEmail.setText("");
            forDate.setSelectedItem(null);
            forMonth.setSelectedItem(null);
            forYears.setSelectedItem(null);
            forAddress.setText("");
            forEducation.setText("");
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    JMenu admin, menuStudent, menuStaff, hostel;
    JMenuBar mb;
    JMenuItem adminLogin, miStudentNew, miStudentExis, miStaffNew, miStaffExis, fee;
    JLabel header, infos, empty, back, forContactAddress, forContactNumber;
    ImageIcon favicon;
    JScrollPane sp;
    public Main() {
        mb = new JMenuBar();
        
        header = new JLabel("Welcome to PSG college of Technology");
        infos = new JLabel("<html>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp PSG College of Technology, an ISO 9001:2015 certified institution is one of the foremost institutions founded by the PSG & Sons' Charities Trust (1926). The College was established in the year 1951 and the Founders wisely decided to locate it in the same campus as the PSG Industrial Institute for effective industry-institute interaction.Provide world-class Engineering Education, Foster Research and Development. Evolve innovative applications of Technology. Encourage Entrepreneurship. Ultimately mould young men and women capable of assuming leadership of the society for the betterment of the Country.</html>");
        empty = new JLabel();
        admin = new JMenu("Admin");
        menuStudent = new JMenu("Student");
        menuStaff = new JMenu("Staff");
        miStudentNew = new JMenuItem("New student");
        miStudentExis = new JMenuItem("Existing Student");
        miStaffNew = new JMenuItem("New staff");
        miStaffExis = new JMenuItem("Existing Staff");
        adminLogin = new JMenuItem("Login");
        hostel = new JMenu("Hostel");
        fee = new JMenuItem("Fee");
        forContactAddress = new JLabel("");
        forContactAddress.setText("<html> For Contact: <br> Post Box No. 1611 <br> Peelamedu <br> Coimbatore- 641004</html>");
        forContactNumber = new JLabel("");
        forContactNumber.setText("<html> Phone number: </br>  0422-2572177, 2572477, 4344777 <br> 0422-2573833</html>");

        admin.add(adminLogin);
        miStaffExis.addActionListener(this);
        miStaffNew.addActionListener(this);
        miStudentExis.addActionListener(this);
        miStudentNew.addActionListener(this);
        adminLogin.addActionListener(this);
        fee.addActionListener(this);

        header.setBounds(140, 10, 380, 30);
        infos.setBounds(40, 40, 500, 300);
        header.setFont(new Font("Sans", Font.BOLD,16));
        forContactAddress.setBounds(420, 430, 120, 100);
        forContactNumber.setBounds(30, 430, 120, 100);
        
        add(header);
        add(infos);
        add(forContactAddress);add(forContactNumber);
        ImageIcon bg = new ImageIcon("C:\\Users\\ajtha\\Coding\\JavaPresentation\\CollegeEnrollementSystem\\images\\bg.jpg");
        Image img = bg.getImage();
        Image temp = img.getScaledInstance(700, 650, Image.SCALE_SMOOTH);
        bg = new ImageIcon(temp);
        back = new JLabel(bg);
        back.setLayout(null);
        back.setBounds(0, 0, 700, 650);
        add(back);
        add(empty);
        menuStaff.add(miStaffNew);
        menuStaff.add(miStaffExis);
        menuStudent.add(miStudentNew);
        menuStudent.add(miStudentExis);
        hostel.add(fee);
        mb.add(admin);
        mb.add(menuStudent);
        mb.add(menuStaff);
        mb.add(hostel);

        favicon = new ImageIcon("C:\\Users\\ajtha\\Coding\\JavaPresentation\\CollegeEnrollementSystem\\images\\PSGlogo.jpeg");
        setIconImage(favicon.getImage());
        setJMenuBar(mb);
        setVisible(true);
        // setLayout(null);
        setTitle("College Enrollement System");
        setSize(700, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == adminLogin){
            new LoginAdmin();
        }

        if(ae.getSource() == miStaffExis){
            System.out.println("Existing staff");
            new LoginStaff();
        }

        if(ae.getSource() == miStaffNew){
            new StaffForm();
        }

        if(ae.getSource() == miStudentNew){
            new StudentForm();
        }

        if(ae.getSource() == miStudentExis){
            new LoginStud();
        }

        if(ae.getSource() == fee){
            new Fee();
        }

    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}

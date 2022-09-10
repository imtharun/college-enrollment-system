import java.sql.*;
import java.util.*;

public class database {
    Connection conStaff, conStud;
    void initStaff() throws Exception{
        conStaff = DriverManager.getConnection("jdbc:derby:staffdb;create=true");
    }

    void initStud() throws Exception{
        conStud = DriverManager.getConnection("jdbc:derby:studdb;create=true");
    }

    void createStaff() throws Exception{
        initStaff();
        String query = "CREATE TABLE STAFF (NAME VARCHAR(100), DOB VARCHAR(50), GENDER VARCHAR(50), EMAIL VARCHAR(50), PHONE VARCHAR(50), EDUCATION VARCHAR(100), ADDR VARCHAR(100), USERNAME INTEGER, PASSWORD VARCHAR(30))";
        Statement sStaff = conStaff.createStatement();
        sStaff.executeUpdate(query);
    }

    void createStud() throws Exception{
        initStud();
        String query = "CREATE TABLE STUD (NAME VARCHAR(100), DOB VARCHAR(50), GENDER VARCHAR(50), EMAIL VARCHAR(50), PHONE VARCHAR(50), DEGREE VARCHAR(50) , COURSE VARCHAR(50), MARKTEN VARCHAR(30), MARKTW VARCHAR(30), CUTOFF VARCHAR(30), USERNAME INTEGER, PASSWORD VARCHAR(30))";
        Statement sStud = conStud.createStatement();
        sStud.executeUpdate(query);
    }

    
    void insertStaff(String name, String dob, String gender, String email, String phone, String edu, String addr) throws Exception{
        Random rand = new Random();
        int random  = rand.nextInt(10000, 99999);
        
        String pass;


        if(phone.length() > 6){
             pass = phone.substring(0, 6);
        }else{
            pass = phone;
        }
        String sql = "INSERT INTO STAFF VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conStaff.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dob);
        ps.setString(3, gender);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, edu);
        ps.setString(7, addr);
        ps.setInt(8, random);ps.setString(9, pass);
        ps.executeUpdate();
    }

    void insertStud(String name, String dob, String gender, String email, String phone, String degree, String course, String mark10, String mark12, String cutoff) throws Exception{
        Random rand = new Random();
        int random  = rand.nextInt(10000, 99999);
        
        String pass;

        if(phone.length() > 6){
             pass = phone.substring(0, 6);
        }else{
            pass = phone;
        }

        String sql = "INSERT INTO STUD VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conStud.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dob);
        ps.setString(3, gender);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, degree);
        ps.setString(7, course);
        ps.setString(8, mark10);
        ps.setString(9, mark12);
        ps.setString(10, cutoff);
        ps.setInt(11, random);
        ps.setString(12, pass);
        ps.executeUpdate();
    }
    
    void destroyStaff() throws Exception{
        conStaff.close();
    }

    void destroyStud() throws Exception{
        conStud.close();
    }
}

import javax.swing.*;

public class Fee extends JFrame {
	JTable hf;
	JScrollPane shf;
	String[] col ={ "Sno", "Room Type", "Room rent/Month", "Mess fee/Month","Total(In Rupees)" };
	String[][] row = { { "1","Single room", "3500", "5000", "8500" }, {"2","Double room", "2500", "5000", "7500" }, { "3", "Single room(Bath attached)", "6000", "5000","11000"}, { "4", "Double room(Bath attached)", "5000", "5000","10000" }};
	//JTable hf = new JTable(row, col);
	
	public Fee() {
		hf= new JTable(row,col);
		shf=new JScrollPane(hf);
		shf.setBounds(100, 60, 500,500);
		add(shf);

        setVisible(true);
        setSize(800, 300);
        setTitle("Hostel fee Structure");
	}
}
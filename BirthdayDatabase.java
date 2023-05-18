import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class BirthdayDatabase extends JFrame{

	private JLabel familyNameL, firstNameL, monthL, dayL, yearL, fileNameL, messageL;
	private JTextField familyNameTF, firstNameTF, dayTF, yearTF, fileNameTF;
	private JComboBox<Month> monthCB;
	private JScrollPane messageSP;
	private JTextArea messagesTA;
	private JButton enterB, searchB, deleteB, sortAlphaB, sortChronB, displayB, loadB, saveB, okayB, cancelB;
	
	private PersonList pL;
	private String fileName;
	private Person currentPerson, found;
	
	private boolean processingSave, duplicateRecord, processingDelete;
	
	private Container myCP;
	
	private String errorMsg,fName, lName, month, day, year;
	
	public BirthdayDatabase() {
		super("The Birthday Database");
		setSize(560, 670);
		setLocation(100,100);
		myCP = getContentPane();
		myCP.setLayout(new FlowLayout());
		
		pL = new PersonList();
		pL.loadFromFile("birthdays.txt");
		processingSave = false;
		duplicateRecord = false;
		processingDelete = false;
		
		firstNameL = new JLabel("First Name:");
		firstNameL.setFont(new Font("Arial", Font.PLAIN, 30));
		myCP.add(firstNameL);
		
		firstNameTF = new JTextField(20);
		firstNameTF.setFont(new Font("Arial", Font.PLAIN, 20));
		firstNameTF.setText("");
		myCP.add(firstNameTF);
		
		familyNameL = new JLabel("Last Name:");
		familyNameL.setFont(new Font("Arial", Font.PLAIN, 30));
		myCP.add(familyNameL);
		
		familyNameTF = new JTextField(20);
		familyNameTF.setFont(new Font("Arial", Font.PLAIN, 20));
		familyNameTF.setText("");
		myCP.add(familyNameTF);

		monthL = new JLabel("Month of birth: ");
		monthL.setFont(new Font("Arial", Font.PLAIN, 25));
		myCP.add(monthL);
		
		monthCB = new JComboBox<Month>(Month.values());
		monthCB.setFont(new Font("Arial", Font.PLAIN, 20));
		monthCB.setPreferredSize(new Dimension(330,40));
		myCP.add(monthCB);
		
		dayL = new JLabel("Day of birth:");
		dayL.setFont(new Font("Arial", Font.PLAIN, 25));
		myCP.add(dayL);
		
		dayTF = new JTextField(20);
		dayTF.setFont(new Font("Arial", Font.PLAIN, 20));
		dayTF.setText("");
		myCP.add(dayTF);
		
		yearL = new JLabel("Year of birth:");
		yearL.setFont(new Font("Arial", Font.PLAIN, 25));
		myCP.add(yearL);
		
		yearTF = new JTextField(20);
		yearTF.setFont(new Font("Arial", Font.PLAIN, 20));
		yearTF.setText("");
		myCP.add(yearTF);
		
		enterB = new JButton("Enter");
		enterB.setFont(new Font("Arial", Font.PLAIN, 30));
		enterB.addActionListener(new EnterBHandler());
		myCP.add(enterB);
		
		displayB = new JButton("Display");
		displayB.setFont(new Font("Arial", Font.PLAIN, 30));
		displayB.addActionListener(new DisplayBHandler());
		myCP.add(displayB);
		
		searchB = new JButton("Search");
		searchB.setFont(new Font("Arial", Font.PLAIN, 30));
		searchB.addActionListener(new SearchBHandler());
		myCP.add(searchB);
		
		deleteB = new JButton("Delete");
		deleteB.setFont(new Font("Arial", Font.PLAIN, 30));
		deleteB.addActionListener(new DeleteBHandler());
		myCP.add(deleteB);
		
		sortAlphaB = new JButton("Sort Alphabetically");
		sortAlphaB.setFont(new Font("Arial", Font.PLAIN, 25));
		sortAlphaB.addActionListener(new SortAlphaBHandler());
		myCP.add(sortAlphaB);
		
		sortChronB = new JButton("Sort Chronologically");
		sortChronB.setFont(new Font("Arial", Font.PLAIN, 25));
		sortChronB.addActionListener(new SortChronBHandler());
		myCP.add(sortChronB);
		
		
		fileNameL = new JLabel("File Name:");
		fileNameL.setFont(new Font("Arial", Font.PLAIN, 25));
		myCP.add(fileNameL);
		
		fileNameTF = new JTextField(20);
		fileNameTF.setFont(new Font("Arial", Font.PLAIN, 20));
		fileNameTF.setText("birthdays.txt");
		myCP.add(fileNameTF);
		
		loadB = new JButton("Load");
		loadB.setFont(new Font("Arial", Font.PLAIN, 30));
		loadB.addActionListener(new LoadBHandler());
		myCP.add(loadB);
		
		saveB = new JButton("Save");
		saveB.setFont(new Font("Arial", Font.PLAIN, 30));
		saveB.addActionListener(new SaveBHandler());
		myCP.add(saveB);
		
		okayB = new JButton("OK");
		okayB.setFont(new Font("Arial", Font.PLAIN, 30));
		okayB.addActionListener(new OkayBHandler());
		myCP.add(okayB);
		
		cancelB = new JButton("Cancel");
		cancelB.setFont(new Font("Arial", Font.PLAIN, 30));
		cancelB.addActionListener(new CancelBHandler());
		myCP.add(cancelB);
		
		messageL = new JLabel("Messages:");
		messageL.setFont(new Font("Arial", Font.PLAIN, 25));
		myCP.add(messageL);
		
		messagesTA = new JTextArea("Welcome to the Birthday Database.", 4, 40);
			messageSP = new JScrollPane(messagesTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			messageSP.setPreferredSize(new Dimension(500,200));
			messagesTA.setFont(new Font("Arial", Font.PLAIN, 20));
			myCP.add(messageSP);
		
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		adjustButtons(true);
	}

	private void clearInputFields() {
		familyNameTF.setText("");
		firstNameTF.setText("");
		dayTF.setText("");
		yearTF.setText("");
	}
	
	private void adjustButtons(boolean tFValue) {
		saveB.setEnabled(tFValue);
		enterB.setEnabled(tFValue);
		displayB.setEnabled(tFValue);
		searchB.setEnabled(tFValue);
		deleteB.setEnabled(tFValue);
		loadB.setEnabled(tFValue);
		okayB.setEnabled(!tFValue);
		cancelB.setEnabled(!tFValue);
	}
	
	private void reset() {
		adjustButtons(true);
		clearInputFields();
	}
	
	private String getUserInputName(JTextField theTF, String theText) {
		String userInput = theTF.getText();
		if(userInput.equals("")) {
			errorMsg += "You need to enter a " + theText + " name.\n";
		}
		return userInput;
	}
	
	private String getUserInputDate() {
		Month selection = (Month)monthCB.getSelectedItem();
		try{
			int dayInt = Integer.parseInt(dayTF.getText());
			if(dayInt <= selection.getDay()) {
				day = dayTF.getText();
				return dayTF.getText();
			}else {
				errorMsg += "That number is invalid for this month.";
				return dayTF.getText();
			}
		}catch(NumberFormatException e) {
			errorMsg += "You must enter a valid number for the day.\n";
			return dayTF.getText();
		}
	}
	
	private String getUserInputYear() {
		try{
			int yearInt = Integer.parseInt(yearTF.getText());
			if(yearInt < 0) {
				errorMsg += "You cannot enter a negative number for the year.";
			}else {
				year = yearTF.getText();
			}
		}catch(NumberFormatException e) {
			errorMsg += "You must enter a valid number for the year.\n";
		}
		return yearTF.getText();	
	}
	
	private boolean validInput() {
		errorMsg = "";
		fName = getUserInputName(firstNameTF, "first");
		lName = getUserInputName(familyNameTF, "family");
		month = ((Month)monthCB.getSelectedItem()).getName();
		day = getUserInputDate();
		year = getUserInputYear();
		return errorMsg.equals("");
	}
	
	public class EnterBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validInput()) {
				currentPerson = new Person(fName, lName, Month.valueOf(month.toUpperCase()), Integer.parseInt(day), Integer.parseInt(year));
				found = pL.search(currentPerson);
				if (found != null) {
					duplicateRecord = true;
					adjustButtons(false);
					messagesTA.setText("\nRecord already exists:\n" + found.toString()
					+ "\nPress OK to replace old record " +
							"or Cancel to cancel new entry.\n");
				}else {
					if(pL.addAlpha(currentPerson)){
						messagesTA.setText("\n" + currentPerson + " added.\n" + 
												" To see current list, press Display.\n" + 
												" To save this list to file, press Save.\n");
					}else {
						messagesTA.setText("\nFailed to add " + currentPerson + " to the DB.\n");
					}
				}
				clearInputFields();
			}else {
				messagesTA.setText(errorMsg + "\n");
			}
		}
	}
	
	public class SaveBHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileName = fileNameTF.getText();
			String message = "";
			if(fileName.compareTo("") > 0) {
				File theFile = new File(fileName);
				if(!theFile.exists()) {
					message = pL.saveToFile(fileName);
					messagesTA.setText("Data saved to file "+ fileName+".\n"
							+ message + "\n");
				}else if(theFile.isDirectory()){
					messagesTA.setText("Error: " + fileName + " is a directory. \n");
				}else if(!theFile.canWrite()) {
					messagesTA.setText("Cannot write data to " + fileName + "\n.");
				}else {
					adjustButtons(false);
					processingSave = true;
					messagesTA.setText("\nPress OK to overwrite file "+
					fileName + " or press Cancel to cancel save request.\n");
				}
			}else {
				messagesTA.setText("You must enter a file name in order to save a file.");
			}
		}
	}
	
	public class CancelBHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(processingSave) {
				 messagesTA.setText("Save request cancelled. "+
						 fileName + " unchanged.\n");
				 processingSave = false;
			}else if(duplicateRecord) {
				messagesTA.setText("Information about " + fName + " " + lName + " unchanged.\n");
				duplicateRecord = false;
			}else if (processingDelete) {
				messagesTA.setText("Delete request cancelled. \n");
				processingDelete = false;
			}else {
				System.out.println("Cancel Button being handled at inappropriate time " + e.toString());
			}
			reset();
		}
	}
	
	public class OkayBHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(processingSave) {
				String errmsg = pL.saveToFile(fileName);
				messagesTA.setText(fileName + " overwritten.\n"
						+ errmsg + "\n");
				processingSave = false;
			}else if (duplicateRecord) {
				if(pL.delete(found.getName())) {
					if(pL.addAlpha(currentPerson)) {
						messagesTA.setText("\nRecord for " + currentPerson + " changed.\n");
					}else {
						messagesTA.setText("\nError in adding new record. " +
					fName + " " + lName + " deleted from DB.\n");
					}
				}else {
					messagesTA.setText("\nError in deleting old record. No change in DB.\n");
				}
				duplicateRecord = false;
			}else if(processingDelete) {
				if(pL.delete(found.getName())) {
					messagesTA.setText("The record for " + found + " was deleted.");
				}else {
					messagesTA.setText("Failure occurred in deleting " + found + ".\n");
				}
				processingDelete = false;
			}else {
				System.out.println("OK button being handled at inappropriate time " + e.toString());
			}
			reset();
		}
	}
	
	public class LoadBHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileName = fileNameTF.getText();
			if(fileName.compareTo("") > 0) {
				File theFile = new File(fileName);
				if(!theFile.exists()) {
					messagesTA.setText(fileName + " does not exist: cannot load data.\n");
				}else if(!theFile.canRead()) {
					messagesTA.setText("Cannot read from " + fileName + "\n");
				}else {
					String fromLoad = pL.loadFromFile(fileName);
					messagesTA.setText("Data loaded from " + fileName+ "\n"
							+ fromLoad + "\n");
				}
				clearInputFields();
			}else {
				messagesTA.setText("You must enter a file name " +
						"in order to load a file.");
			}
		}
	}
	
	public class DisplayBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String entries = pL.printList();
			messagesTA.setText(entries);
		}
	}
	
	public class SearchBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String fn = firstNameTF.getText();
			String ln = familyNameTF.getText();
			if(!fn.equals("") && !ln.equals("")) {
				Name n = new Name(fn, ln);
				String thename = pL.findPersonByName(n);
				messagesTA.setText("\nFound in DB: \n" + thename);
				return;
			}else {
				Month mo = (Month)monthCB.getSelectedItem();
				int dayInt = 0;
				int yr = 0;
				try{
					dayInt = Integer.parseInt(dayTF.getText());
					yr = Integer.parseInt(yearTF.getText());
					if(dayInt <= mo.getDay() && yr > 0) {
						Date d = new Date(mo, dayInt, yr);
						String theperson = pL.hasBirthdayOn(d);
						messagesTA.setText("\nFound in DB: \n" + theperson);
					}else {
						messagesTA.setText(" Invalid entries for day and/or year.");
						return;
					}
				}catch(NumberFormatException i) {
					messagesTA.setText(" Invalid entries for day and/or year.");
				}
			}
		}
	}
	
	public class DeleteBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String fn = firstNameTF.getText();
			String ln = familyNameTF.getText();
			if(!fn.equals("") && !ln.equals("")) {
				Name n = new Name(fn, ln);
				boolean response = pL.delete(n);
				messagesTA.setText(response? n + " deleted." : n + " not deleted.");
				return;
			}else {
				messagesTA.setText("You must enter a name to delete.");
			}
		}
	}
	
	public class SortAlphaBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String entries = pL.sortAlphab();
			messagesTA.setText(entries);
		}
	}
	
	public class SortChronBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String entries = pL.sortChronol();
			messagesTA.setText(entries);
		}
	}
	
	public static void main(String[] args){
		BirthdayDatabase myApp = new BirthdayDatabase();
	}
}

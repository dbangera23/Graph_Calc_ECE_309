import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.Double;
import java.math.BigDecimal;
import java.math.MathContext;

public class AccumulatingCalculator implements ActionListener, Accumulator {
	
	//******************//
	// Global Variables //
	//******************//
	String newLine = System.lineSeparator(); //Line separator for GUI outputs
	
	//*******************//
	//*//*************//*/
	//*// GUI Objects //*//
	//*//*************//*//
	//*******************//
	JLabel errorLabelField = new JLabel("Error Messages Here"); //Error Label for GUIs
	JPanel errorPanel = new JPanel(); //Panel for error messages
	//******************//
	// Mode GUI Objects //
	//******************//
	JLabel accumLabel = new JLabel("Accumulator"); //Top label for Accumulator
	JLabel accumInputLabel = new JLabel("Input"); //Input Label for Accumulator
	JLabel accumOutputLabel = new JLabel("Output"); //Output Label for Accumulator
	JLabel accumLogLabel = new JLabel("Log"); //Log Label for Accumulator
	JFrame accumWindow = new JFrame("Accumulator"); //Window for Accumulator
	JButton accumReset = new JButton("Clear"); //Reset button for accumulator
	//*************************//
	// Mode GUI Objects //
	//*************************//
	JFrame modeWindow = new JFrame("Select a Mode"); //Main window
	JRadioButton accumulator = new JRadioButton("Accumulator"); //Button for accumulator mode
	JRadioButton calculator = new JRadioButton("Calculator"); //Button for calculator mode
	JRadioButton test = new JRadioButton("Test"); //Button for test mode
	JRadioButton graph = new JRadioButton("Graphing Calculator"); // Button for Graphing Calculator mode
	ButtonGroup modeButtons = new ButtonGroup(); //Selection group for modes
	JPanel modePanel = new JPanel(); //Panel for mode buttons
	JPanel accumCenter = new JPanel(); //Panel in middle of accumulator GUI
	JPanel accumLeftCenter = new JPanel(); //Panel for input and output
	JPanel accumRightCenter = new JPanel(); //Panel for log
	JPanel accumBottom = new JPanel(); //Panel at bottom of accumulator GUI
	JPanel accumClearPanel = new JPanel(); //Panel for clear Button
	JPanel accumInputLabelPanel = new JPanel(); //Panel for Input Label
	JPanel accumOutputLabelPanel = new JPanel(); //Panel for  Output Label
	JTextField accumInput = new JTextField(); //Input text area for accumulator
	JTextArea accumOutput = new JTextArea(); //Output text are for accumulator
	JTextArea accumLog = new JTextArea(); //Log text are for accumulator
	JScrollPane accumLogPane = new JScrollPane(accumLog);
	
	//************************//
	// Future Updates Objects //
	//************************//
	JFrame comingSoonWindow = new JFrame(); //Window for future modes
	JTextArea comingSoon = new JTextArea(); //Text for coming soon
	
	public AccumulatingCalculator(){
		System.out.println("Dean Bangera, Morgan Mona, Joseph T Richards");
		//**************************//
		// Building Mode Window GUI //
		//**************************//
		modeWindow.setSize(600,70); //Set size of mode window
		modeWindow.setLocation(0,0); //Set location to be upper left corner of screen
		modeWindow.setVisible(true); //Makes sure mode window is always visible
		modeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminates program on close
		modeButtons.add(accumulator); //Adds Accumulator button to ButtonGroup
		modeButtons.add(calculator); //Adds Calculator button to ButtonGroup
		modeButtons.add(test); //Adds Test button to ButtonGroup
		modeButtons.add(graph); //Adds Graph button to ButtonGroup
		modePanel.add(accumulator); //Adds Accumulator button to Panel
		modePanel.add(calculator); //Adds Calculator button to Panel
		modePanel.add(test); //Adds Test button to Panel
		modePanel.add(graph); //Adds Graph button to Panel
		modeWindow.getContentPane().add(modePanel, "North"); //Place modePanel at top of window
		//Colors for different Buttons and Panels
		accumulator.setBackground(Color.white); //Set background of accumulator button to white
		calculator.setBackground(Color.white); //Set background of calculator button to white
		test.setBackground(Color.white); //Set background of test button to white
		graph.setBackground(Color.white); //Set background of graph button to white
		modePanel.setBackground(Color.white); //Set background of mode button panel to white
		comingSoon.setBackground(Color.blue); //Set background of comingSoon JTextArea to blue
		comingSoon.setForeground(Color.white); //Set foreground (text) of comingSoon JTextArea to white
		accumCenter.setBackground(Color.black); //Set border of accumCenter to black
		accumLeftCenter.setBackground(Color.black); //Set center break to black
		accumLabel.setForeground(Color.white);
		accumInputLabelPanel.setBackground(new Color(0x4FD5D6));
		accumOutputLabelPanel.setBackground(new Color(0xCDFFFF));
		accumRightCenter.setBackground(new Color(0x0099CC));
		//Random GUI formatting code
		comingSoon.setFont(new Font("default", Font.BOLD, 20));
		comingSoon.setLineWrap(true);
		comingSoon.setWrapStyleWord(true);
		//Actions for different buttons
		accumulator.addActionListener(this); //Action for accumulator button
		calculator.addActionListener(this); //Action for calculator button
		test.addActionListener(this); //Action for test button
		graph.addActionListener(this); //Action for graph button
		accumReset.addActionListener(this); //Action for Reset Button in Accumulator
	}
	
	//*****************************//
	// GUI for FutureUpdate Window //
	//*****************************//
	private void futureUpdate(){
		if(comingSoonWindow.isShowing()){
			return; //If comingSoon is already showing do nothing and return
		}
		if(accumWindow.isShowing()){
			accumWindow.dispose(); //If accumWindow is open, close it
		}
		comingSoonWindow.setSize(600,400); //Set the window size
		comingSoonWindow.setLocation(0,70); //Set the location of the window
		comingSoonWindow.setVisible(true); //Make sure window is always visible
		comingSoonWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nothing happens if user tries to close
		comingSoonWindow.add(comingSoon, "Center"); //Add comingSoon to window
		comingSoon.setEditable(false); //Make Coming Soon un-editable
		String notReady = "Coming in a future update. Check back later.";
		comingSoon.append(notReady); //Print notReady to GUI window
	}
	
	//*****************//
	// Accumulator GUI //
	//*****************//
	private void accumulateGUI(){
		if(accumWindow.isShowing()){
			return; //If accumWindow is already showing do nothing and return
		}
		if(comingSoonWindow.isShowing()){
			comingSoonWindow.dispose(); //If comingSoon window is open, close it
		}

		accumWindow.setSize(600,250); //Set the window size
		accumWindow.setLocation(0,70); //Set the location of the window
		accumWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nothing happens if user tries to close

		//**************//
		// Bottom Panel //
		//**************//
		accumClearPanel.add(accumReset); //Adds Reset Button to Reset Panel
		errorPanel.add(errorLabelField); //Adds Error Label to Error Panel
		accumBottom.setLayout(new GridLayout(1,2)); //Set Layout for bottom panel of accumulator
		accumBottom.add(accumClearPanel); //Adds clear button panel to bottom panel
		accumBottom.add(errorPanel); //Adds error message panel to bottom panel
		accumWindow.getContentPane().add(accumBottom, "South"); //Adds bottom panel to Accumulator GUI
		errorLabelField.setForeground(Color.red); //Set error message color.
		//**************//
		// Center Panel //
		//**************//
		
		//Left Side
		accumOutput.setEditable(false); //Make accumOutput un-editable
		accumLog.setEditable(false); //Make accumLog un-editable
		accumLeftCenter.setLayout(new GridBagLayout()); //Set Layout for the input/output panel
		GridBagConstraints l = new GridBagConstraints();	//Left side constraints
		l.fill = GridBagConstraints.BOTH;			//Fill in spaces vertically and horizontally
		l.weightx = 1;							//Will stack vertically so all will have weightx of 1
		l.gridx = 0;						//All will be stacked vertically so all with a gridx position of 0
		l.weighty = 0.1;					//Labels have weight .1 vertically
		l.gridy = 1;						//Increment position vertically 
		accumInputLabelPanel.add(accumInputLabel); //Add input Label to input label panel
		accumInputLabel.setFont(new Font("default", Font.BOLD, 20));
		accumLeftCenter.add(accumInputLabelPanel, l); //Add input label panel to left center panel
		l.weighty = 1.5;					//input fields have weight of 1.5
		l.gridy = 2;
		accumLeftCenter.add(accumInput, l); //Add input text area to left center panel
		accumInput.addActionListener(this);//Subscribe to input for user and wait for an enter
		accumOutputLabelPanel.add(accumOutputLabel); //Add output label to output label panel
		l.weighty = 0.1;
		l.gridy = 3;
		accumOutputLabel.setFont(new Font("default", Font.BOLD, 20));
		accumLeftCenter.add(accumOutputLabelPanel, l); //Add output label panel to left center panel
		l.weighty = 1.5;
		l.gridy = 4;
		accumLeftCenter.add(accumOutput, l); //Add output text area to left center panel
		accumCenter.add(accumLeftCenter); //Add left center panel to center panel
		
		//Right Side
		accumRightCenter.setLayout(new GridBagLayout()); //Set layout for center right panel
		accumRightCenter.add(accumLogLabel); //Add log label panel to right center panel
		accumLogLabel.setFont(new Font("default", Font.BOLD, 20));
		GridBagConstraints r = new GridBagConstraints();		//Right side grid layout constraints
		r.fill = GridBagConstraints.BOTH;
		r.weightx = 1.0;
		r.gridx = 0;
		r.weighty = 1;
		r.gridy = 1;
		accumRightCenter.add(accumLogPane,r); //Add log text area to right center panel
		accumCenter.setLayout(new GridLayout(1,2)); //Set Layout for center panel
		accumCenter.add(accumRightCenter); //Add right center panel to center panel
		accumWindow.getContentPane().add(accumCenter, "Center"); //Add center panel to Accumulator GUI
		
		//Reveal everything
		accumWindow.setVisible(true); //Make sure the window is always visible
	}
	
	//***********************//
	// Accumulator Interface //
	//***********************//
	public String accumulate(String total, String amount) {
		try {
			if(total.trim().length()==0) total = "0";
			if(amount.startsWith("$")) amount = amount.substring(1);		//Check if a dollar sign is present
			
			boolean containsDec = false;	//Check if a decimal is present
			if(amount.contains(".") || total.contains("."))
				containsDec = true;
		
			double retval = Double.parseDouble(total);		//Actual accumulator math
			retval += Double.parseDouble(amount);
		
			if(retval==0)					//Check if return value is a 0
				containsDec = false;
		
			String retstring = Double.toString(retval);		//Convert finished value back to string
		
			//Return as it came in unless its a zero
			if(containsDec){				//Value came in with a decimal. Return with appropriate number of decimals
				BigDecimal  totalBD = new BigDecimal(retstring,MathContext.DECIMAL64);//set precision to 16 digits
				totalBD = totalBD.setScale(2,BigDecimal.ROUND_UP);//scale (2) is # of digits to right of decimal point.
				retstring = totalBD.toPlainString();// no exponents	
			} else {				//Value didn't come in with a decimal or answer is 0 return without a decimal
				if(retstring.contains("."))	retstring = retstring.substring(0,retstring.indexOf("."));
			}
			return retstring;
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Amount not numeric");
		}
	}
	
	//*************//
	// Main Method //
	//*************//
	public static void main(String[] args) {
		if(args.length != 0){
			System.out.println("No command line parameters accepted"); //Prints error message to console that arguments are not accepted
		}
		AccumulatingCalculator ac = new AccumulatingCalculator(); //Creation of the GUI
	}
	
	//************************//
	// actionPerformed Method //
	//************************//
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == accumulator){
			setModeWinColors(Color.blue ,Color.black,Color.black,Color.black);
			accumulateGUI(); //Calls method for accumulator GUI to be created
			return;
		}
		if(ae.getSource() == calculator){
			setModeWinColors(Color.black ,Color.blue,Color.black,Color.black);
			futureUpdate(); //Set Coming Soon to appear
			return;
		}
		if(ae.getSource() == test){
			setModeWinColors(Color.black ,Color.black,Color.blue,Color.black);
			futureUpdate(); //Set Coming Soon to appear
			return;
		}
		if(ae.getSource() == graph){
			setModeWinColors(Color.black ,Color.black,Color.black,Color.blue);
			futureUpdate(); //Set Coming Soon to appear
			return;
		}
		if(ae.getSource() == accumInput){
			errorLabelField.setText("");		//Reset errors
			String inputText = accumInput.getText().trim();
			String outputText = "0";
			if(accumOutput.getText().trim().length()!=0){
				outputText = accumOutput.getText().trim();
			}
			if(inputText.trim().length()==0) errorLabelField.setText("No number inputed");			//Check if anything was inputed
			String answer = "";
			try{
				answer = accumulate(outputText, inputText);			//Try to accumulate
				if(inputText.contains("-")){
					if(inputText.contains("$"))
							inputText = inputText.substring(1);
					inputText = inputText.substring(1);
					accumLog.append(newLine + outputText + " - " + inputText + " = " + answer);
				}
				else{
					if(inputText.contains("+") && inputText.contains("$"))
						inputText = inputText.substring(2);
					else if(inputText.contains("$") || inputText.contains("+"))
						inputText = inputText.substring(1);
					accumLog.append(newLine + outputText + " + " + inputText + " = " + answer);
				}
				accumOutput.setText(answer);											//Output answer
				//accumLog.append(newLine + answer);										//Append to the log
			}
			catch(IllegalArgumentException e){										//Something went wrong. Show error in error field
				 errorLabelField.setText(e.getMessage());
			}
			accumInput.setText("");													//Regardless of if everything worked or not reset the input
			return;
		}
		if(ae.getSource() == accumReset){
			errorLabelField.setText(""); //clear error message to blank
			accumInput.setText("");
			accumOutput.setText("");
			return;
		}
	}
	
	private void setModeWinColors(Color a, Color b, Color c, Color d){
		accumulator.setForeground(a); //Set text color of accumulator button to blue
		calculator.setForeground(b); //Set text color of calculator button to black
		test.setForeground(c); //Set text color of test button to black
		graph.setForeground(d); //Set text color of graph button to black
	}
	
}

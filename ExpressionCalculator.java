import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.lang.Double;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ExpressionCalculator implements ActionListener, Calculator {
	
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
	//*************************//
	// Accumulator GUI Objects //
	//*************************//
	JLabel accumLabel = new JLabel("Accumulator"); //Top label for Accumulator
	JLabel accumInputLabel = new JLabel("Input"); //Input Label for Accumulator
	JLabel accumOutputLabel = new JLabel("Output"); //Output Label for Accumulator
	JLabel accumLogLabel = new JLabel("Log"); //Log Label for Accumulator
	JFrame accumWindow = new JFrame("Accumulator"); //Window for Accumulator
	JButton accumReset = new JButton("Clear"); //Reset button for accumulator
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
	// Expression GUI Objects //
	//************************//
	JFrame ExpWindow = new JFrame("Expression Calculator"); //Window for Expression Calculator
	JLabel expLabel = new JLabel("Expression Calculator");
	JLabel expInputLabel = new JLabel("Input"); //Input Label for Expression
	JLabel expOutputLabel = new JLabel("Output"); //Output Label for Expression
	JLabel expLogLabel = new JLabel("Log"); //Log Label for Expression
	JButton expclear = new JButton("Clear"); //Reset button for expression
	JTextField expInput = new JTextField(); //Input text area for expression
	JTextArea expOutput = new JTextArea(); //Output text are for expression
	JTextArea expLog = new JTextArea(); //Log text are for expression
	JTextField expXArea = new JTextField(); // Area to enter x value
	JLabel expXLabel = new JLabel("X ="); //Label for x =
	JScrollPane expLogPane = new JScrollPane(expLog);
	JPanel expCenter = new JPanel(); //Panel in middle of expression GUI
	JPanel expLeftCenter = new JPanel(); //Panel for input and output
	JPanel expRightCenter = new JPanel(); //Panel for log
	JPanel expBottom = new JPanel(); //Panel at bottom of expression GUI
	JPanel expClearPanel = new JPanel(); //Panel for clear button
	JPanel expInputLabelPanel = new JPanel(); //Panel for input label
	JPanel expOutputLabelPanel = new JPanel(); //Panel for output label
	JPanel expXLabelPanel = new JPanel();
	//******************//
	// Test GUI Objects //
	//******************//
	JFrame testWindow = new JFrame("Test Calculator"); //Window for test Calculator
	JLabel testLabel = new JLabel("Expression Calculator");
	JLabel testInputLabel = new JLabel("Input"); //Input Label for test
	JLabel testCorrectLabel = new JLabel("Correct"); //Output Label for correct
	JLabel testWrongLabel = new JLabel("Wrong"); //Output Label for correct
	JLabel testPercentLabel = new JLabel("Percent Correct"); //Output Label for percent correct
	JPanel testCorrectPanel = new JPanel();
	JPanel testWrongPanel = new JPanel();
	JPanel testPercentPanel = new JPanel();
	JPanel testXPanel = new JPanel();
	JLabel testLogLabel = new JLabel("Log"); //Log Label for test
	JButton testclear = new JButton("Clear"); //Reset button for test
	JTextField testInput = new JTextField(); //Input text area for test
	JTextArea testOutputCorrect = new JTextArea("0"); //Output text area for test correct
	JTextArea testOutputWrong = new JTextArea("0"); //Output text area for test wrong
	JTextArea testOutputPercent = new JTextArea("0.00"); //Output text area for percent
	JTextArea testLog = new JTextArea(); //Log text are for test
	JTextField testXArea = new JTextField(); // Area to enter x value
	JLabel testXLabel = new JLabel("X ="); //Label for x =
	JScrollPane testLogPane = new JScrollPane(testLog);
	JPanel testCenter = new JPanel(); //Panel in middle of test GUI
	JPanel testLeftCenter = new JPanel(); //Panel for input and output
	JPanel testRightCenter = new JPanel(); //Panel for log
	JPanel testBottom = new JPanel(); //Panel at bottom of test GUI
	JPanel testClearPanel = new JPanel(); //Panel for clear button
	JPanel testInputLabelPanel = new JPanel(); //Panel for input label
	JPanel testStatsPanel = new JPanel(); //Panel for statistics
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
	//************************//
	// Future Updates Objects //
	//************************//
	JFrame comingSoonWindow = new JFrame(); //Window for future modes
	JTextArea comingSoon = new JTextArea(); //Text for coming soon
	
	public ExpressionCalculator(){
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
		
		expXLabelPanel.setBackground(new Color(0xCDFFFF));
		expInputLabelPanel.setBackground(new Color(0x4FD5D6));
		expOutputLabelPanel.setBackground(new Color(0xCDFFFF));
		expRightCenter.setBackground(new Color(0x0099CC));
		
		testXPanel.setBackground(new Color(0xCDFFFF));
		testInputLabelPanel.setBackground(new Color(0x4FD5D6));
		testRightCenter.setBackground(new Color(0x0099CC));
		testCorrectPanel.setBackground(new Color(0xCDFFFF));
		testWrongPanel.setBackground(new Color(0x0099CC));
		testPercentPanel.setBackground(new Color(0xCDFFFF));
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
		if(ExpWindow.isShowing()){
			ExpWindow.dispose(); //If ExpWindow is open, close it
		}
		if(testWindow.isShowing()){
			testWindow.dispose();
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
	
	//**********//
	// Test GUI //
	//**********//
	private void testGUI(){
		if(testWindow.isShowing()){
			return; //If accumWindow is already showing do nothing and return
		}
		if(accumWindow.isShowing()){
			accumWindow.dispose(); //If accumWindow is open, close it
		}
		if(ExpWindow.isShowing()){
			ExpWindow.dispose(); // If 
		}
		if(comingSoonWindow.isShowing()){
			comingSoonWindow.dispose(); //If ExpWindow is open, close it
		}
		testWindow.setSize(900,350); //Set the window size
		testWindow.setLocation(0,70); // Set the location of the window
		testWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nothing happens if user tries to close
		//**************//
		// Bottom Panel //
		//**************//
		testClearPanel.add(testclear); //Adds Reset Button to Reset Panel
		errorPanel.add(errorLabelField); //Adds Error Label to Error Panel
		testBottom.setLayout(new GridLayout(1,2)); //Set Layout for bottom panel of accumulator
		testBottom.add(testClearPanel); //Adds clear button panel to bottom panel
		testBottom.add(errorPanel); //Adds error message panel to bottom panel
		testWindow.getContentPane().add(testBottom, "South"); //Adds bottom panel to Accumulator GUI
		errorLabelField.setForeground(Color.red); //Set error message color.
		//**************//
		// Center Panel //
		//**************//
		//Left Side
		testOutputCorrect.setEditable(false); //Make accumOutput un-editable
		testOutputWrong.setEditable(false); //Make accumOutput un-editable
		testOutputPercent.setEditable(false); //Make accumOutput un-editable
		testLog.setEditable(false); //Make accumLog un-editable
		testStatsPanel.setLayout(new GridBagLayout());
		GridBagConstraints v = new GridBagConstraints();
		v.fill = GridBagConstraints.BOTH;
		v.weightx = .5;
		v.gridx = 0;
		v.weighty = .1;
		v.gridy = 1;
		testCorrectPanel.add(testCorrectLabel);
		testStatsPanel.add(testCorrectPanel, v);
		testCorrectLabel.setFont(new Font("default", Font.BOLD, 20));
		v.gridx = 1;
		testWrongPanel.add(testWrongLabel);
		testStatsPanel.add(testWrongPanel, v);
		testWrongLabel.setFont(new Font("default", Font.BOLD, 20));
		v.gridx = 2;
		testPercentPanel.add(testPercentLabel);
		testStatsPanel.add(testPercentPanel, v);
		testPercentLabel.setFont(new Font("default", Font.BOLD, 20));
		v.gridy = 2;
		v.gridx = 0;
		testStatsPanel.add(testOutputCorrect, v);
		v.gridx = 1;
		testStatsPanel.add(testOutputWrong, v);
		v.gridx = 2;
		testStatsPanel.add(testOutputPercent, v);
		testLeftCenter.setLayout(new GridBagLayout()); //Set Layout for the input/output panel
		GridBagConstraints l = new GridBagConstraints();	//Left side constraints
		l.fill = GridBagConstraints.BOTH;			//Fill in spaces vertically and horizontally
		l.weightx = .5;							//Will stack vertically so all will have weightx of 1
		l.gridx = 0;						//All will be stacked vertically so all with a gridx position of 0
		l.weighty = 0.1;					//Labels have weight .1 vertically
		l.gridy = 1;						//Increment position vertically 
		testXPanel.add(testXLabel);
		testLeftCenter.add(testXPanel, l);
		testXLabel.setFont(new Font("default", Font.BOLD, 20));
		l.weighty = 1.5;					//input fields have weight of 1.5
		l.gridy = 2;
		testLeftCenter.add(testXArea, l);
		testXArea.addActionListener(this); //Subscribe to input for user and wait for enter
		l.weighty = 0.1;
		l.gridy = 3;
		testInputLabelPanel.add(testInputLabel); //Add input Label to input label panel
		testInputLabel.setFont(new Font("default", Font.BOLD, 20));
		testLeftCenter.add(testInputLabelPanel, l); //Add input label panel to left center panel
		l.weighty = 1.5;					//input fields have weight of 1.5
		l.gridy = 4;
		testLeftCenter.add(testInput, l); //Add input text area to left center panel
		testInput.addActionListener(this);//Subscribe to input for user and wait for an enter
		l.weighty = 1.6;
		l.gridy = 5;
		testLeftCenter.add(testStatsPanel, l);
		testCenter.add(testLeftCenter); //Add left center panel to center panel
		//Right Side
		testRightCenter.setLayout(new GridBagLayout()); //Set layout for center right panel
		testRightCenter.add(testLogLabel); //Add log label panel to right center panel
		testLogLabel.setFont(new Font("default", Font.BOLD, 20));
		GridBagConstraints r = new GridBagConstraints();		//Right side grid layout constraints
		r.fill = GridBagConstraints.BOTH;
		r.weightx = 1.0;
		r.gridx = 0;
		r.weighty = 1;
		r.gridy = 1;
		testRightCenter.add(testLogPane,r); //Add log text area to right center panel
		testCenter.setLayout(new GridLayout(1,2)); //Set Layout for center panel
		testCenter.add(testRightCenter); //Add right center panel to center panel
		testWindow.getContentPane().add(testCenter, "Center"); //Add center panel to Accumulator GUI
		//Reveal everything
		testWindow.setVisible(true);
		testclear.addActionListener(this);
	}
	
	//****************//
	// Expression GUI //
	//****************//
	private void expressionGUI(){
		if(ExpWindow.isShowing()){
			return; //If accumWindow is already showing do nothing and return
		}
		if(accumWindow.isShowing()){
			accumWindow.dispose(); //If accumWindow is open, close it
		}
		if(comingSoonWindow.isShowing()){
			comingSoonWindow.dispose(); //If ExpWindow is open, close it
		}
		if(testWindow.isShowing()){
			testWindow.dispose();
		}
		ExpWindow.setSize(600,350); //Set the window size
		ExpWindow.setLocation(0,70); // Set the location of the window
		ExpWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Nothing happens if user tries to close
		//**************//
		// Bottom Panel //
		//**************//
		expClearPanel.add(expclear); //Adds Reset Button to Reset Panel
		errorPanel.add(errorLabelField); //Adds Error Label to Error Panel
		expBottom.setLayout(new GridLayout(1,2)); //Set Layout for bottom panel of accumulator
		expBottom.add(expClearPanel); //Adds clear button panel to bottom panel
		expBottom.add(errorPanel); //Adds error message panel to bottom panel
		ExpWindow.getContentPane().add(expBottom, "South"); //Adds bottom panel to Accumulator GUI
		errorLabelField.setForeground(Color.red); //Set error message color.
		//**************//
		// Center Panel //
		//**************//
		//Left Side
		expOutput.setEditable(false); //Make accumOutput un-editable
		expLog.setEditable(false); //Make accumLog un-editable
		
		expLeftCenter.setLayout(new GridBagLayout()); //Set Layout for the input/output panel
		GridBagConstraints l = new GridBagConstraints();	//Left side constraints
		l.fill = GridBagConstraints.BOTH;			//Fill in spaces vertically and horizontally
		l.weightx = .5;							//Will stack vertically so all will have weightx of 1
		l.gridx = 0;						//All will be stacked vertically so all with a gridx position of 0
		l.weighty = 0.1;					//Labels have weight .1 vertically
		l.gridy = 1;						//Increment position vertically 
		expXLabelPanel.add(expXLabel);
		expLeftCenter.add(expXLabelPanel, l);
		expXLabel.setFont(new Font("default", Font.BOLD, 20));
		l.weighty = 1.5;					//input fields have weight of 1.5
		l.gridy = 2;
		expLeftCenter.add(expXArea, l);
		expXArea.addActionListener(this); //Subscribe to input for user and wait for enter
		l.weighty = 0.1;
		l.gridy = 3;
		expInputLabelPanel.add(expInputLabel); //Add input Label to input label panel
		expInputLabel.setFont(new Font("default", Font.BOLD, 20));
		expLeftCenter.add(expInputLabelPanel, l); //Add input label panel to left center panel
		l.weighty = 1.5;					//input fields have weight of 1.5
		l.gridy = 4;
		expLeftCenter.add(expInput, l); //Add input text area to left center panel
		expInput.addActionListener(this);//Subscribe to input for user and wait for an enter
		expOutputLabelPanel.add(expOutputLabel); //Add output label to output label panel
		l.weighty = 0.1;
		l.gridy = 5;
		expOutputLabel.setFont(new Font("default", Font.BOLD, 20));
		expLeftCenter.add(expOutputLabelPanel, l); //Add output label panel to left center panel
		l.weighty = 1.5;
		l.gridy = 6;
		expLeftCenter.add(expOutput, l); //Add output text area to left center panel
		expCenter.add(expLeftCenter); //Add left center panel to center panel
		//Right Side
		expRightCenter.setLayout(new GridBagLayout()); //Set layout for center right panel
		expRightCenter.add(expLogLabel); //Add log label panel to right center panel
		expLogLabel.setFont(new Font("default", Font.BOLD, 20));
		GridBagConstraints r = new GridBagConstraints();		//Right side grid layout constraints
		r.fill = GridBagConstraints.BOTH;
		r.weightx = 1.0;
		r.gridx = 0;
		r.weighty = 1;
		r.gridy = 1;
		expRightCenter.add(expLogPane,r); //Add log text area to right center panel
		expCenter.setLayout(new GridLayout(1,2)); //Set Layout for center panel
		expCenter.add(expRightCenter); //Add right center panel to center panel
		ExpWindow.getContentPane().add(expCenter, "Center"); //Add center panel to Accumulator GUI
		expclear.addActionListener(this);
		//Reveal everything
		ExpWindow.setVisible(true);
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
		if(ExpWindow.isShowing()){
			ExpWindow.dispose(); //If ExpWindow is open, close it
		}
		if(testWindow.isShowing()){
			testWindow.dispose();
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
			if(amount.contains(".") || total.contains(".")){
				containsDec = true;
				if(amount.contains(".")){
					if((amount.substring(amount.indexOf(".")+1).length()!=2))
						throw new IllegalArgumentException("Decimal amounts of 2 units only allowed. Required format: '0.00'");
				}
			}
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
	
	//***********************//
	// Expression Interface //
	//***********************//
	public String calculate(String Expression, String x)
			throws IllegalArgumentException {
		Expression = Expression.replace("pi", Double.toString(Math.PI));
		Expression = Expression.replace("e", Double.toString(Math.E));
		Expression = Expression.replace("PI", Double.toString(Math.PI));
		Expression = Expression.replace("E", Double.toString(Math.E));
		if(Expression.contains("x")){
			if(x.trim().isEmpty() || x==null)
				throw new IllegalArgumentException("X found in expression but no x was passed in");
			else{
				Expression = Expression.replace("x", x);
			}
		}
		if(Expression.trim().contains(")("))
			throw new IllegalArgumentException("Implied expressions not allowed");
		while(Expression.contains("(") || Expression.contains(")")){
			boolean found = false;
			int walk = 0;
			for(walk = Expression.lastIndexOf("(")+1;walk<=Expression.length();walk++){
				if(walk==-1)
					throw new IllegalArgumentException("Expression does not having opening parenthesis");
				if(Expression.charAt(walk)==')'){
					found = true;
					break;
				}
			}
			if(found==false)
				throw new IllegalArgumentException("Close parenthesis not found in expression");
			String InnerExp = Expression.substring(Expression.lastIndexOf("("),walk+1);
			String exp1 = Expression.substring(0,Expression.lastIndexOf("("));
			String exp2 = Expression.substring(walk+1);
			Expression = exp1 + " " + calculate(InnerExp.substring(1, InnerExp.indexOf(")")),null) + " " + exp2;
		}
		while(Expression.contains("r") || Expression.contains("^") || Expression.contains("*") || Expression.contains("/")){
			boolean em;
			if(Expression.contains("r") || Expression.contains("^"))
				em = true;
			else{
				em = false;
			}
			String operatorExp = "";
			int operator = -2;
			if(em){
				if(Expression.indexOf("r")!=-1){
					operator = Expression.indexOf("r");
					operatorExp = "root";
				}
				if(Expression.indexOf("^") != -1){
					if(Expression.indexOf("^")<operator || (Expression.indexOf("^")>operator && operator==-2)){
						operator = Expression.indexOf("^");
						operatorExp = "power";
					}
				}
			}
			else{
				if(Expression.indexOf("*")!=-1){
					operator = Expression.indexOf("*");
					operatorExp = "multiplication";
				}
				if(Expression.indexOf("/") != -1)
					if(Expression.indexOf("/")<operator || (Expression.indexOf("/")>operator && operator==-2)){
						operator = Expression.indexOf("/");
						operatorExp = "division";
				}
			}
			double base = 0.00;
			double power = 0.00;
			int basestart = operator-1;
			boolean num = false;
			boolean neg = false;
			boolean dot = false;
			for(boolean go = true; go && basestart>=0; basestart--){
				char match = Expression.charAt(basestart);
				if(Character.isDigit(match))
					num = true;
				if(match == '.' && dot == true)
					throw new IllegalArgumentException("Two dots found before " + operatorExp + " at index: " + operator);
				if(match == '.')
					dot = true;
				if(match == '-' && num == true){
					neg = true;
					break;
				}
				else if(match == '-' && num != true)
					throw new IllegalArgumentException("double operator error at index: " + operator);
				if(match == ' '){
					if(num)
						go = false;
				}
				if(!Character.isDigit(match) && (match != '-' && match != ' ' && match!='.')){
					if(num)
						break;
					else
						throw new IllegalArgumentException("invalid number found before " + operatorExp + " at index: " + operator);
				}
			}
			base = Double.parseDouble(Expression.substring(basestart+1, operator));
			int powerstart = operator+1;
			num = false;
			neg = false;
			dot = false;
			for(boolean go = true; go && powerstart<=Expression.length()-1; powerstart++){
				char match = Expression.charAt(powerstart);
				if(Character.isDigit(match))
					num = true;
				if(match == '.' && dot == true)
					throw new IllegalArgumentException("Two dots found after " + operatorExp + " at index: " + operator);
				if(match == '.')
					dot = true;
				if(match == '-' && neg == true)
					throw new IllegalArgumentException("double operator error at index: " + operator);
				if(match == '-')
					if(!num)
						neg = true;
					else
						break;
				if(match == ' '){
					if(num)
						go = false;
				}
				if(!Character.isDigit(match) && (match != '-' && match != ' ' && match != '.')){
					if(num)
						break;
					else{
						throw new IllegalArgumentException("invalid number found after " + operatorExp + " at index: " + operator);
					}
				}
			}
			power = Double.parseDouble(Expression.substring(operator+1,powerstart));
			double ans = 0.00;
			if(em){
				if(operatorExp.equals("power")){
					if(power<0)
						ans = 1/Math.pow(base, -power);
					else{
						ans = Math.pow(base, power);
					}
				}
				else{
					if(power<0)
						if(power%2==0)
							ans = Math.pow(-base,1/power);
						else{
							ans = Math.pow(-base, 1/power);
							ans = -ans;
						}
					else{
						ans = Math.pow(base, 1/power);
					}
				}
			}
			else{
				if(operatorExp.equals("multiplication")){
					ans = base * power;
				}
				else{
					ans = base / power;
					if(Double.isInfinite(ans))
						throw new IllegalArgumentException("Divide by zero exception");
				}
			}
			String exp1 = Expression.substring(0,Expression.indexOf(Expression.substring(basestart+1,powerstart)));
			String exp2 = Expression.substring(Expression.substring(basestart+1,powerstart).length() + exp1.length());
			Expression = exp1 + Double.toString(ans) + exp2;
		}
		while(Expression.contains("+") || Expression.contains("-")){
			
			String addOrDiv = "";
			int operator = -2;
			if(Expression.indexOf("+")!=-1){
				operator = Expression.indexOf("+");
				addOrDiv = "add";
			}
			if(Expression.indexOf("-") != -1 && Expression.indexOf("-") != 0){
				if(Expression.indexOf("-")<operator || (Expression.indexOf("-")>operator && operator==-2)){
				operator = Expression.indexOf("-");
				addOrDiv = "subtract";
				}
			}
			if(Expression.indexOf("-")==0)
				if(!Expression.substring(1).contains("+") && !Expression.substring(1).contains("-"))
					return Expression;
			double num1 = 0.00;
			double num2 = 0.00;
			int num1start = operator-1;
			boolean num = false;
			boolean dot = false;
			for(boolean go = true; go && num1start>=0; num1start--){
				char match = Expression.charAt(num1start);
				if(Character.isDigit(match))
					num = true;
				else if((match == '-' || match == '.') && num != true)
					throw new IllegalArgumentException("double operator error at index: " + operator);
				if(match == '.' && dot == true)
					throw new IllegalArgumentException("double dot found before " + addOrDiv + " at index:" + operator);
				if(match == '.')
					dot = true;
				if(match == ' '){
					if(num)
						go = false;
				}
				if(!Character.isDigit(match) && (match != '-' && match != ' ' && match != '.'))
					if(num)
						break;
					else{
						throw new IllegalArgumentException("invalid number found before " + addOrDiv + " at index: " + operator);
					}
			}
			num1 = Double.parseDouble(Expression.substring(num1start+1,operator));
			boolean neg = false;
			num = false;
			dot = false;
			neg = false;
			int num2start = operator+1;
			for(boolean go = true; go && num2start<=Expression.length()-1; num2start++){
				char match = Expression.charAt(num2start);
				if(Character.isDigit(match))
					num = true;
				if(match == '-'){
					if(num)
						break;
					if(addOrDiv.equals("subtract") && neg == false){
						String exp1 = Expression.substring(0,operator);
						String exp2 = Expression.substring(num2start+1);
						Expression = exp1 + "+" + exp2;
						addOrDiv = "add";
						num2start = exp1.length();
					}
					if(neg==true)
						throw new IllegalArgumentException("Double negative after an operator found at index:" + operator);
					neg = true;
				}
				if(match == '.'){
					if(dot == true)
						throw new IllegalArgumentException("Two dots found after " + addOrDiv + " at index:" + operator);
					dot = true;
				}
				if(match == ' '){
					if(num){
						go = false;
					}
				}
				if(!Character.isDigit(match) && (match != '-' && match != ' ' && match!='.'))
					if(num)
						break;
					else{
						throw new IllegalArgumentException("invalid number found after " + addOrDiv + " at index: " + operator);	
					}
			}
			num2 = Double.parseDouble(Expression.substring(operator+1,num2start));
			double ans = 0.00;
			if(addOrDiv.equals("add"))
				ans = num1+num2;
			else{
				ans = num1-num2;
			}
			String exp1 = Expression.substring(0,Expression.indexOf(Expression.substring(num1start+1,num2start)));
			String exp2 = Expression.substring(Expression.substring(num1start+1,num2start).length() + exp1.length());
			Expression = exp1 + Double.toString(ans) + exp2;
		}
		try{
			Double.parseDouble(Expression);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Invalid input. Unable to calculate");
		}
		String answer = Expression;
		return answer;
	}
	
	//*************//
	// Main Method //
	//*************//
	public static void main(String[] args) {
		if(args.length != 0){
			System.out.println("No command line parameters accepted"); //Prints error message to console that arguments are not accepted
		}
		ExpressionCalculator ac = new ExpressionCalculator(); //Creation of the GUI
	}
	
	//************************//
	// actionPerformed Method //
	//************************//
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == testInput){
			String inputText = testInput.getText();
			if(inputText.contains("=")){
				String In1 = inputText.substring(0, inputText.indexOf("="));
				String In2 = inputText.substring(inputText.indexOf("=")+1);
				String Correct = testOutputCorrect.getText();
				String Wrong = testOutputWrong.getText();
				int CorrectNum = Integer.parseInt(Correct);
				int WrongNum = Integer.parseInt(Wrong);
				String x = testXArea.getText();
				String first = "";
				String second = "";
				try{
					first = calculate(In1,x);
					second = calculate(In2,x);
				}
				catch(Exception e ){
					errorLabelField.setText(e.getMessage());
				}
				double firstdo = Double.parseDouble(first);
				double secondo = Double.parseDouble(second);
				float percent = 0;
				if(firstdo==secondo){
					if(x.trim()!=""){
						CorrectNum++;
						percent = (CorrectNum * 100.0f) / (WrongNum+CorrectNum);
						testLog.append(newLine + inputText + " is correct");
					}
					else{
						CorrectNum++;
						percent = (CorrectNum * 100.0f) / (WrongNum+CorrectNum);
						testLog.append(newLine + inputText + " evaluated with x=" + x + " is correct");
					}
				}
				else{
					if(x.trim()!=""){
						WrongNum++;
						percent = (CorrectNum * 100.0f) / (WrongNum+CorrectNum);
						testLog.append(newLine + inputText + " is wrong");
					}
					else{
						WrongNum++;
						percent = (CorrectNum * 100.0f) / (WrongNum+CorrectNum);
						testLog.append(newLine + inputText + " evaluated with x=" + x + " is wrong");
					}
				}
				testOutputCorrect.setText(Integer.toString(CorrectNum));
				testOutputWrong.setText(Integer.toString(WrongNum));
				testOutputPercent.setText(Float.toString(percent));
			}
			else{
				 errorLabelField.setText("no = found. Nothing to compare");
			}
		}
		if(ae.getSource() == testclear){
			testInput.setText("");
		}
		if(ae.getSource() == expInput){
			errorLabelField.setText("");		//Reset errors
			String inputText = expInput.getText();
			String x = expXArea.getText();
			
			if(inputText.trim().length()==0) errorLabelField.setText("No number inputed");			//Check if anything was inputed
			String answer = "";
			try{
				answer = calculate(inputText, x);			//Try to accumulate
					
				if(x.trim().isEmpty()){
					expLog.append(newLine + inputText + " = " + answer);
				}
				else{
					expLog.append(newLine + inputText + " where x= " + x + " = " + answer);
				}
				
				expOutput.setText(answer);											//Output answer
				//accumLog.append(newLine + answer);										//Append to the log
			}
			catch(IllegalArgumentException e){										//Something went wrong. Show error in error field
				 errorLabelField.setText(e.getMessage());
			}
			expInput.setText("");													//Regardless of if everything worked or not reset the input
			return;
		}
		if(ae.getSource() == expclear){
			errorLabelField.setText("");
			expInput.setText("");
			expOutput.setText("");
			expXArea.setText("");
			return;
		}
		if(ae.getSource() == accumulator){
			setModeWinColors(Color.blue ,Color.black,Color.black,Color.black);
			accumulateGUI(); //Calls method for accumulator GUI to be created
			return;
		}
		if(ae.getSource() == calculator){
			setModeWinColors(Color.black ,Color.blue,Color.black,Color.black);
			expressionGUI(); //Calls method for expression GUI to be created
			return;
		}
		if(ae.getSource() == test){
			setModeWinColors(Color.black ,Color.black,Color.blue,Color.black);
			testGUI(); //Calls method for test GUI to be created
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

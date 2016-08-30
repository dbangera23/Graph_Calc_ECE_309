import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GraphPanel extends JPanel implements MouseListener
  {
	String expression;
	GraphingCalculator address;
	JFrame miniXYdisplay = new JFrame();
	JPanel miniXYdisplayWindow = new JPanel();
	JTextField xTextField = new JTextField();
	JTextField yTextField = new JTextField();
	double xPixelsToValueConversionFactor;
	double yPixelsToValueConversionFactor;
	JTextArea inputArea = new JTextArea("x= ");
	JTextArea outputArea = new JTextArea("y= ");
	double[] xValues;
	double[] yValues;
	boolean negX=false;
	boolean negY=false;
	double xRange;
	double yRange;
	int xSize;
	int ySize;
	double yScale;
	double xMin;
	double xMax;
	double yMin;
	double yMax;
	double xScale;
	
  public GraphPanel (String     expression, // CONSTRUCTOR
                     double[]   xValues,
                     double[]   yValues,
                     GraphingCalculator calculatorProgram)
                     throws IllegalArgumentException
    {
	  // To-dos for this constructor method:
	    // 1 Verify arrays are same size
		
		  xSize = xValues.length;
		  ySize = yValues.length;
		  if(xSize != ySize){
			  throw new IllegalArgumentException("Error: x and y coordinate sizes do not match");
		  }

		  this.xValues = xValues;
		  this.yValues = yValues;
		// Find x values range
		  xMin=xValues[0];
		  xMax=xValues[0];
		  
		  for(int i=0;i<xSize;i++){
			  if(xValues[i]<0)
				  negX=true;
			  if(xValues[i]<xMin)
				  xMin = xValues[i];
			  else if(xValues[i]>xMax)
				  xMax = xValues[i];
		  }
		  xRange = (int) (xMax-xMin);
		  
		// Find y values range
		  yMin=yValues[0];
		  yMax=yValues[0];
		  
		  for(int j=0;j<ySize;j++){
			  if(yValues[j]<0)
				  negY=true;
			  if(yValues[j]<yMin)
				  yMin = yValues[j];
			  else if(yValues[j]>yMax)
				  yMax = yValues[j];
		  }
		  yRange = (int) (yMax-yMin);
		  
	    // 2 Verify x increment is positive
		  if(calculatorProgram.getxIncrement()<=0)
			  throw new IllegalArgumentException("Error: x-Increment is not positive");
		  
	    // 3 Save Calculator address for call back
		  address = calculatorProgram;
		  
	    // 4 Save expression for call back
		  this.expression = expression;
		  address.GraphFrame.setTitle(this.expression);
		  
	    // 5 Register with the panel as MouseListener
		  address.Graph.addMouseListener(this);
		  
	    // 6 Calculate Y scale values (and save them) 
		  yScale = scaleValues(yMin, yMax, yRange);
		  yScale = Math.round(yScale);
		  
	    // 7 Build miniXYdisplayWindow (reuse for each mouse click!)
		  miniXYdisplayWindow.setLayout(new GridLayout(2,2));
		  miniXYdisplayWindow.add(inputArea);
		  miniXYdisplayWindow.add(xTextField);
		  miniXYdisplayWindow.add(outputArea);
	      miniXYdisplayWindow.add(yTextField);
	      miniXYdisplay.add(miniXYdisplayWindow);
	      miniXYdisplay.setSize(100, 100);
    }
  

@Override
  public void paint(Graphics g) // overrides paint() in JPanel!
    {
	
	// 1 Calculate x and y pixels-to-value conversion factors	 
    // 2 Do ALL drawing here in paint()
	  Rectangle r = address.Graph.getBounds();
	  g.clearRect(address.Graph.getX(), address.Graph.getY(), r.width, r.height);
	  int xAxisSize = (r.width-25);
	  int yAxisSize = r.height-20;
	  if(negY)
		  g.drawLine(25, r.height/2, r.width-25, r.height/2);
	  else
		  g.drawLine(25, r.height-20, r.width-25, r.height-20);
	  if(negX)
		  g.drawLine(r.width/2, r.height-20, r.width/2, 20);
	  else
		  g.drawLine(25, r.height-20, 25, 20);
	  xScale = xAxisSize/(xSize+1);
	  yScale = yAxisSize/(ySize+1);
	  xPixelsToValueConversionFactor  = xScale/(xRange/(double)xSize);
	  yPixelsToValueConversionFactor = yScale/(yRange/(double)ySize);
	  for(int i = 0;i<(xSize+1);i++){
		  if(negY){
			  g.drawLine(25+i*(int)xScale, r.height/2, 25+i*(int)xScale, (r.height/2)-5);
			  g.drawString(new DecimalFormat("#.##").format(xMin+(xRange/(double)xSize)*i),28+(int)xScale*i	,(r.height/2)-5);
		  }
		  else{
			  g.drawLine(25+i*(int)xScale, r.height-20, 25+i*(int)xScale, r.height-5);
			  g.drawString(new DecimalFormat("#.##").format(xMin+(xRange/(double)xSize)*i),28+(int)xScale*i	,r.height-5);
		  }
		  if(negX){
			  g.drawLine((r.width/2)-5, r.height-20-(int)yScale*i, r.width/2, r.height-20-(int)yScale*i);
			  g.drawString(new DecimalFormat("#.##").format(yMin+(yRange/(double)ySize)*i), r.width/2+5, r.height-12-(int)yScale*i);
		  }
		  else{
			  g.drawLine(15, r.height-20-(int)yScale*i, 25, r.height-20-(int)yScale*i);
			  g.drawString(new DecimalFormat("#.##").format(yMin+(yRange/(double)ySize)*i), 0, r.height-12-(int)yScale*i);
		  }
		  
		  if(i<xSize){
			  if(negX){
				  if(negY){
					  if(i>0){
						  g.drawLine((int)(xPixelsToValueConversionFactor*xValues[i])+(r.width/2), (r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i]), (int)(xPixelsToValueConversionFactor*xValues[i-1])+(r.width/2),  (r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i-1]));
					  }
					  g.drawOval((int)(xPixelsToValueConversionFactor*xValues[i])+(r.width/2),(r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i]), 4, 4);
				  }
				  else{
					  if(i>0){
						  g.drawLine((int)(xPixelsToValueConversionFactor*xValues[i])+(r.width/2),r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i]), (int)(xPixelsToValueConversionFactor*xValues[i-1])+(r.width/2),r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i-1]));
					  }
					  g.drawOval((int)(xPixelsToValueConversionFactor*xValues[i])+(r.width/2),r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i]), 4, 4);
				  }
			  }
			  else{
				  if(negY){
					  if(i>0){
						  g.drawLine((int)(xPixelsToValueConversionFactor*xValues[i])+23,(r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i]), (int)(xPixelsToValueConversionFactor*xValues[i-1])+23, (r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i-1]));
					  }
					  g.drawOval((int)(xPixelsToValueConversionFactor*xValues[i])+23,(r.height/2)-(int)(yPixelsToValueConversionFactor*yValues[i]), 4, 4);
				  }
				  else{
					  if(i>0){
						  g.drawLine((int)(xPixelsToValueConversionFactor*xValues[i])+23,r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i]), (int)(xPixelsToValueConversionFactor*xValues[i-1])+23, r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i-1]));
					  }
					  g.drawOval((int)(xPixelsToValueConversionFactor*xValues[i])+23,r.height-22-(int)(yPixelsToValueConversionFactor*yValues[i]), 4, 4);
				  }
			  }
		  }
	  }
    }

@Override
public void mouseClicked(MouseEvent arg0) {}
public void mouseEntered(MouseEvent arg0) {}
public void mouseExited(MouseEvent arg0) {}

@Override
public void mousePressed(MouseEvent me) {
	  // xTextField and yTextField are in the miniXYdisplayWindow
	if(address.dim!=address.graphPanel.getSize()){
		address.graphics = address.Graph.getGraphics();
		paint(address.graphics);
		address.dim = address.graphPanel.getSize();
	}
    int xInPixels =me.getX();
    double xValue = 0.00;
    if(negX){
    	int x = address.Graph.getBounds().width;
    	xValue = xInPixels-(x/2);
    	xValue /= xPixelsToValueConversionFactor;
    }
    else{
    	xValue = xInPixels-23;
    	xValue /= xPixelsToValueConversionFactor;
    }
    String xValueString = String.valueOf(xValue);
    if(xValueString.contains("."))
    	xValueString = xValueString.substring(0, xValueString.indexOf(".")+3);
    xTextField.setText(xValueString);
  
    String yValueString = address.calculate(expression,xValueString); 
    if(yValueString.contains("."))
    	yValueString = yValueString.substring(0, yValueString.indexOf(".")+3);
    yTextField.setText(yValueString);

    // show mini x,y display window
    miniXYdisplay.setLocation(me.getXOnScreen(), me.getYOnScreen());
    miniXYdisplay.setVisible(true); 
}

@Override
public void mouseReleased(MouseEvent arg0) {
    // "erase" mini x,y display window	
	miniXYdisplay.setVisible(false);
}

private double scaleValues(double min, double max, double yRange2) {
		int initialIncrement;
		int upperIncrement;
		int lowerIncrement;
		int selectedIncrement;
		String zeros = "0000000000";
		
		// 1) Determine the RANGE to be plotted.
		// already done

	// 2) Determine an initial increment value.
	if (yRange2 >= 10)
	   {
		 yRange2 = (int)yRange2;
	   }
	else
	   {
		 System.out.println("Add handling of small plot range!");
		 throw new IllegalArgumentException("Error: Small plot range handling not created");
	   }
	/*ASSUME*/ // 10 scale values as a starting assumption.
	initialIncrement = (int) (yRange2/10);
	// Please excuse this clumsy "math"!
	String initialIncrementString = String.valueOf(initialIncrement);
	//System.out.println("InitialIncrementString = " + initialIncrementString + " (length = " + initialIncrementString.length() + ")");

	// 3) Find even numbers above and below the initial increment. 
	String leadingDigit = initialIncrementString.substring(0,1);
	int leadingNumber = Integer.parseInt(leadingDigit);
	int bumpedLeadingNumber = leadingNumber + 1;
	String bumpedLeadingDigit = String.valueOf(bumpedLeadingNumber);
	String upperIncrementString = bumpedLeadingDigit + zeros.substring(0,initialIncrementString.length()-1);
	String lowerIncrementString = leadingDigit       + zeros.substring(0,initialIncrementString.length()-1);
	upperIncrement = Integer.parseInt(upperIncrementString);
	lowerIncrement = Integer.parseInt(lowerIncrementString);

	// 4) Pick the upper or lower even increment depending on which is closest.
	int distanceToUpper = upperIncrement - initialIncrement;
	int distanceToLower = initialIncrement - lowerIncrement;
	if (distanceToUpper > distanceToLower)
		  selectedIncrement = lowerIncrement;
	  else
	    selectedIncrement = upperIncrement;

	// 5) Determine lowest Y scale value
	int numberOfScaleValues = 0;
	int lowestScaleValue    = 0;
	int highestScaleValue;
	if (min < 0)
	   {
	   for (; lowestScaleValue > min; lowestScaleValue-=selectedIncrement)
	        numberOfScaleValues++;
	   }
	if (min > 0)
	   {
		 for (; lowestScaleValue < min; lowestScaleValue+=selectedIncrement)
		      numberOfScaleValues++;
	   numberOfScaleValues--;
	   lowestScaleValue -= selectedIncrement;
	   }


	// 6) Determine upper Y scale value
	numberOfScaleValues = 1;
	for (highestScaleValue = lowestScaleValue; highestScaleValue < max; highestScaleValue+=selectedIncrement)
		  numberOfScaleValues++;
	if ((numberOfScaleValues < 5) || (numberOfScaleValues > 20))
	   {
		 throw new IllegalArgumentException("Error: Number of scale click marks is too few or too many!");
	   }

	// 7) Determine if Y scale will be extended to include the 0 point.
	if (!((lowestScaleValue < 0) && (highestScaleValue > 0))) // Y scale does not include 0.
	   {   //	Should it be extended to include the 0 point?
	   if ((lowestScaleValue > 0) && (lowestScaleValue/selectedIncrement <= 3))
	      {
	  	lowestScaleValue = 0;
	      }
	   if ((highestScaleValue < 0) && (highestScaleValue/selectedIncrement <= 3))
	      {
	   	highestScaleValue = 0;
	      }
	   }
	int scaleValue = lowestScaleValue;
	while(scaleValue < highestScaleValue)
	     {
		   scaleValue += selectedIncrement;
	     }
	return scaleValue;
}
}
import presentationLayer.*;
import dataLayer.book.*;
import dataLayer.referenceBook.*;

import javax.swing.*;
import java.awt.event.*;

public class TotalBookFrame extends JFrame implements CrossButtonListener
{
private JLabel totalAllBookLabel,totalAvailableBookLabel,totalReferenceBookLabel,referenceBookLabel,magzineLabel,communicationBookLabel;
private JLabel totalLibraryBookLabel,issuedLibraryBookLabel,availableLibraryBookLabel;
private JLabel totalAllBookValueLabel,totalAvailableBookValueLabel,totalReferenceBookValueLabel,referenceBookValueLabel,magzineValueLabel,communicationBookValueLabel;
private JLabel totalLibraryBookValueLabel,issuedLibraryBookValueLabel,availableLibraryBookValueLabel;

public TotalBookFrame()
{
initComponent();
setTitle("Books");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(500,250);
setSize(370,280);
setResizable(false);
setVisible(true);
}
 
private void initComponent()
{
totalAllBookLabel=new JLabel("Total Books:");
totalAvailableBookLabel=new JLabel("Available Books:");
totalReferenceBookLabel=new JLabel("Total Reference:");
referenceBookLabel=new JLabel("Reference Book:");
magzineLabel=new JLabel("Magzine:");
communicationBookLabel=new JLabel("Communication:");
totalLibraryBookLabel=new JLabel("Library Book:");
issuedLibraryBookLabel=new JLabel("Issued Book:");
availableLibraryBookLabel=new JLabel("Available Book:");

totalAllBookValueLabel=new JLabel("");
totalAvailableBookValueLabel=new JLabel("");
totalReferenceBookValueLabel=new JLabel("");
referenceBookValueLabel=new JLabel("");
magzineValueLabel=new JLabel("");
communicationBookValueLabel=new JLabel("");
totalLibraryBookValueLabel=new JLabel("");
issuedLibraryBookValueLabel=new JLabel("");
availableLibraryBookValueLabel=new JLabel("");

setLayout(null);
totalAllBookLabel.setBounds(40,20,100,60);
totalAvailableBookLabel.setBounds(200,20,100,60);
referenceBookLabel.setBounds(40,50,100,60);
magzineLabel.setBounds(40,80,100,60);
totalReferenceBookLabel.setBounds(200,80,100,60);
communicationBookLabel.setBounds(40,110,100,60);
totalLibraryBookLabel.setBounds(40,140,100,60);
issuedLibraryBookLabel.setBounds(200,140,100,60);
availableLibraryBookLabel.setBounds(40,170,100,60);

totalAllBookValueLabel.setBounds(140,20,30,60);
totalAvailableBookValueLabel.setBounds(300,20,30,60);
referenceBookValueLabel.setBounds(140,50,30,60);
magzineValueLabel.setBounds(140,80,30,60);
totalReferenceBookValueLabel.setBounds(300,80,30,60);
communicationBookValueLabel.setBounds(140,110,30,60);
totalLibraryBookValueLabel.setBounds(140,140,30,60);
issuedLibraryBookValueLabel.setBounds(300,140,30,60);
availableLibraryBookValueLabel.setBounds(140,170,30,60);

add(totalAllBookLabel);
add(totalAvailableBookLabel);
add(referenceBookLabel);
add(magzineLabel);
add(totalReferenceBookLabel);
add(communicationBookLabel);
add(totalLibraryBookLabel);
add(issuedLibraryBookLabel);
add(availableLibraryBookLabel);
add(totalAllBookValueLabel);
add(totalAvailableBookValueLabel);
add(referenceBookValueLabel);
add(magzineValueLabel);
add(totalReferenceBookValueLabel);
add(communicationBookValueLabel);
add(totalLibraryBookValueLabel);
add(issuedLibraryBookValueLabel);
add(availableLibraryBookValueLabel);

setValues();
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private void setValues()
{
int totalLibraryBook=0,libraryBookIssued=0,availableLibraryBook=0;
int communicationBook=0,magzine=0,referenceBook=0;
int totalBook=0,totalAvailableBook=0,totalReferenceBook=0;

BookDLInterface bookDL=new BookDL();
ReferenceBookDLInterface rb=new ReferenceBookDL();

try
{
communicationBook=rb.getCount("Communication Book");
magzine=rb.getCount("Magzine");
referenceBook=rb.getCount("Reference Book");

libraryBookIssued=bookDL.getBookIssued();
availableLibraryBook=bookDL.getAvailableStock();
totalLibraryBook=libraryBookIssued+availableLibraryBook;

totalReferenceBook=communicationBook+magzine+referenceBook;
totalBook=communicationBook+magzine+referenceBook+totalLibraryBook;
totalAvailableBook=communicationBook+magzine+referenceBook+availableLibraryBook;

}catch(Exception exception)
{
System.out.println(""+exception);
}

totalAllBookValueLabel.setText(""+totalBook);
totalAvailableBookValueLabel.setText(""+totalAvailableBook);
totalReferenceBookValueLabel.setText(""+totalReferenceBook);
communicationBookValueLabel.setText(""+communicationBook);
referenceBookValueLabel.setText(""+referenceBook);
magzineValueLabel.setText(""+magzine);
issuedLibraryBookValueLabel.setText(""+libraryBookIssued);
availableLibraryBookValueLabel.setText(""+availableLibraryBook);
totalLibraryBookValueLabel.setText(""+totalLibraryBook);

}
 
}
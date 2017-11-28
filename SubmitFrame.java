import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

import dataLayer.issue.*;
import presentationLayer.*;

public class SubmitFrame extends JFrame implements ActionListener, CrossButtonListener
{

JTextField bookIdTextField,memberIdTextField;
JLabel bookIdLabel,memberIdLabel;
JButton submitButton;

SubmitFrame()
{
initComponent();
setTitle("Submit Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(250,130);
setSize(700,500);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
bookIdLabel=new JLabel("Book ID");
memberIdLabel=new JLabel("Member ID");
bookIdTextField=new JTextField(10);
memberIdTextField=new JTextField(10);

submitButton=new JButton("Submit");

setLayout(null);
bookIdLabel.setBounds(170,230,100,30);
memberIdLabel.setBounds(170,180,100,30);
bookIdTextField.setBounds(370,230,150,30);
memberIdTextField.setBounds(370,180,150,30);

submitButton.setBounds(300,340,80,30);

add(bookIdLabel);
add(memberIdLabel);
add(bookIdTextField);
add(memberIdTextField);
add(submitButton);

submitButton.addActionListener(this);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private void requestFocusOnMemberId()
{
memberIdTextField.requestFocus();
}

private void reset()
{
bookIdTextField.setText("");
memberIdTextField.setText("");
}

private String getMemberId()
{
return memberIdTextField.getText().trim();
}
private int getBookId()
{
return Integer.parseInt(bookIdTextField.getText().trim());
}

private boolean calculateFine()
{
if(InputValidator.isTextFieldEmpty(this,memberIdTextField,"Member ID. required"))return false;
if(InputValidator.isTextFieldEmpty(this,bookIdTextField,"Book ID. required"))return false;

if(!InputValidator.isInteger(this,bookIdTextField," Book ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,bookIdTextField,1,99999," Book ID. should be in between (1-99999)"))return false;

IssueDL issueDL=new IssueDL();
IssueInterface issue=new Issue();

try
{
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
Calendar cal = Calendar.getInstance();
issue=issueDL.getDate(getMemberId(),getBookId());

int differenceInDays = (int) ((cal.getTime().getTime()-issue.getSubmissionDate().getTime()) / (1000 * 60 * 60 * 24));

if(differenceInDays>0)
{
int fine=differenceInDays*2;
int selected=JOptionPane.showConfirmDialog(this,"Fine is Rs."+fine+" Do you want to pay it now?","Confirming Fine Payment",JOptionPane.YES_NO_OPTION);
if(selected==JOptionPane.YES_OPTION)submit();
}
else
{
submit();
}
}catch(IssueException issueException)
{
JOptionPane.showMessageDialog(this,issueException.getMessage());
}

return true;
}

private void submit()
{
IssueDL issueDL=new IssueDL();
try
{
issueDL.delete(getMemberId(),getBookId());
JOptionPane.showMessageDialog(this,"Book Submit");
reset();
requestFocusOnMemberId();
}catch(IssueException issueException)
{
JOptionPane.showMessageDialog(this,issueException.getMessage());
}

}

public void actionPerformed(ActionEvent ev)
{
calculateFine();
}

}

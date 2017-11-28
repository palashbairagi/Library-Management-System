package presentationLayer.member;

import presentationLayer.*;
import dataLayer.member.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class EditMemberFrame extends JFrame implements CrossButtonListener, ActionListener
{
private JLabel idLabel,firstNameLabel,lastNameLabel,emailIdLabel,phoneNumberLabel,sexLabel,addressLabel,dateOfBirthLabel;
private JTextField idTextField,firstNameTextField,lastNameTextField,emailIdTextField,phoneNumberTextField,dateOfBirthTextField,addressTextField;
private JRadioButton maleRadioButton,femaleRadioButton;
private ButtonGroup sex;
private JButton updateButton,cancelButton;
private JPanel sexPanel;
private SimpleDateFormat simpleDateFormat;
String mode="edit";

public EditMemberFrame()
{
initComponent();
setTitle("Edit Member");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(400,150);
setSize(650,450);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

idLabel=new JLabel("Member Id");
firstNameLabel=new JLabel("First Name");
lastNameLabel=new JLabel("Last Name");
emailIdLabel=new JLabel("Email Id");
phoneNumberLabel=new JLabel("Phone Number");
sexLabel=new JLabel("Sex");
addressLabel=new JLabel("Address");
dateOfBirthLabel=new JLabel("Date of Birth");

idTextField=new JTextField(10);
firstNameTextField=new JTextField(10);
lastNameTextField=new JTextField(10);
emailIdTextField=new JTextField(10);
phoneNumberTextField=new JTextField(10);
dateOfBirthTextField=new JTextField(10);
addressTextField=new JTextField(10);

sex=new ButtonGroup();
maleRadioButton=new JRadioButton("Male");
femaleRadioButton=new JRadioButton("Female");
maleRadioButton.setSelected(true);
sex.add(maleRadioButton);
sex.add(femaleRadioButton);

updateButton=new JButton("Edit");
cancelButton=new JButton("Cancel");

sexPanel=new JPanel();
sexPanel.setLayout(new GridLayout(1,2));
sexPanel.add(maleRadioButton);
sexPanel.add(femaleRadioButton);

setLayout(null);
idLabel.setBounds(60,40,100,25);
firstNameLabel.setBounds(60,70,100,25);
lastNameLabel.setBounds(60,100,100,25);
sexLabel.setBounds(60,130,100,25);
phoneNumberLabel.setBounds(60,160,100,25);
dateOfBirthLabel.setBounds(60,190,100,25);
emailIdLabel.setBounds(60,220,100,25);
addressLabel.setBounds(60,250,100,25);

idTextField.setBounds(160,40,150,25);
firstNameTextField.setBounds(160,70,150,25);
lastNameTextField.setBounds(160,100,150,25);
sexPanel.setBounds(160,130,150,25);
phoneNumberTextField.setBounds(160,160,150,25);
dateOfBirthTextField.setBounds(160,190,150,25);
emailIdTextField.setBounds(160,220,400,25);
addressTextField.setBounds(160,250,400,25);

updateButton.setBounds(185,330,80,30);
cancelButton.setBounds(360,330,80,30);

add(idLabel);
add(firstNameLabel);
add(lastNameLabel);
add(emailIdLabel);
add(phoneNumberLabel);
add(sexLabel);
add(addressLabel);
add(dateOfBirthLabel);
add(idTextField);
add(firstNameTextField);
add(lastNameTextField);
add(emailIdTextField);
add(phoneNumberTextField);
add(dateOfBirthTextField);
add(addressTextField);
add(sexPanel);
add(updateButton);
add(cancelButton);

updateButton.addActionListener(this);
cancelButton.addActionListener(this);
disableAll();
enableId();
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private String getId()
{
return idTextField.getText().trim();
}
private void setId(String id)
{
this.idTextField.setText(id);
}
private String getFirstName()
{
return firstNameTextField.getText().trim();
}
private void setFirstName(String firstName)
{
this.firstNameTextField.setText(firstName);
}
private String getLastName()
{
return lastNameTextField.getText().trim();
}
private void setLastName(String lastName)
{
this.lastNameTextField.setText(lastName);
}
private String getEmailId()
{
return emailIdTextField.getText().trim();
}
private void setEmailId(String emailId)
{
this.emailIdTextField.setText(emailId);
}
private String getPhoneNumber()
{
return phoneNumberTextField.getText().trim();
}
private void setPhoneNumber(String phoneNumber)
{
this.phoneNumberTextField.setText(phoneNumber);
}
private String getAddress()
{
return addressTextField.getText().trim();
}
private void setAddress(String address)
{
this.addressTextField.setText(address);
}
private String getSex()
{
return (maleRadioButton.isSelected())?"Male":"Female";
}
private void setSex(String sex)
{
sex=sex.toUpperCase();
if(sex.equals("MALE"))
{
maleRadioButton.setSelected(true);
femaleRadioButton.setSelected(false);
}
else
{
maleRadioButton.setSelected(false);
femaleRadioButton.setSelected(true);
}
}
private Date getDateOfBirth() throws ParseException
{
java.util.Date dateOfBirth=simpleDateFormat.parse(this.dateOfBirthTextField.getText().trim());
if(isConvertedDateValid(dateOfBirth,this.dateOfBirthTextField.getText().trim()))
{
return dateOfBirth;
}
else
{
throw new ParseException("Invalid date of birth",0);
}
}
private void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirthTextField.setText(dateOfBirth.getDate()+"-"+(dateOfBirth.getMonth()+1)+"-"+(dateOfBirth.getYear()+1900));
}
private boolean isConvertedDateValid(java.util.Date date,String string)
{
String dt=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
return string.equals(dt);
}

public void reset()
{
idTextField.setText("");
firstNameTextField.setText("");
lastNameTextField.setText("");
emailIdTextField.setText("");
phoneNumberTextField.setText("");
dateOfBirthTextField.setText("");
maleRadioButton.setSelected(true);
addressTextField.setText("");
}

void disableAll()
{
idTextField.setEditable(false);
firstNameTextField.setEditable(false);
lastNameTextField.setEditable(false);
emailIdTextField.setEditable(false);
phoneNumberTextField.setEditable(false);
dateOfBirthTextField.setEditable(false);
sexPanel.setVisible(false);
addressTextField.setEditable(false);
}

void enableAll()
{
idTextField.setEditable(true);
firstNameTextField.setEditable(true);
lastNameTextField.setEditable(true);
emailIdTextField.setEditable(true);
phoneNumberTextField.setEditable(true);
dateOfBirthTextField.setEditable(true);
sexPanel.setVisible(true);
addressTextField.setEditable(true);
}

void disableId()
{
idTextField.setEditable(false);
}
void enableId()
{
idTextField.setEditable(true);
}

public void requestFocusOnId()
{
idTextField.requestFocus();
}


private boolean updateMember()
{
if(InputValidator.isTextFieldEmpty(this,firstNameTextField,"First Name required"))return false;
if(InputValidator.isTextFieldEmpty(this,lastNameTextField,"Last Name required"))return false;
if(InputValidator.isTextFieldEmpty(this,phoneNumberTextField,"Phone Number required"))return false;
if(InputValidator.isTextFieldEmpty(this,dateOfBirthTextField,"Date Of Birth required"))return false;
if(InputValidator.isTextFieldEmpty(this,emailIdTextField,"Email ID required"))return false;
if(InputValidator.isTextFieldEmpty(this,addressTextField,"Address required"))return false;

if(!InputValidator.isValidDDMMYYYYDate(this,dateOfBirthTextField,"Date of Birth Format should be dd-MM-yyyy"))return false;
if(!InputValidator.isValidEmailId(this,emailIdTextField,"Invalid Email Id"))return false;

MemberDL memberDL=new MemberDL();
MemberInterface member=new Member();
member.setId(getId());
member.setFirstName(getFirstName());
member.setLastName(getLastName());
member.setEmailId(getEmailId());
member.setPhoneNumber(getPhoneNumber());
member.setAddress(getAddress());
member.setSex(getSex());

try{
member.setDateOfBirth(getDateOfBirth());
}catch(ParseException parseException)
{
System.out.println(parseException);
}

try
{
memberDL.update(member);
JOptionPane.showMessageDialog(this,"Member Updated");
disableAll();
enableId();
updateButton.setText("Edit");
reset();
requestFocusOnId();
mode="edit";
}catch(MemberException memberException)
{
JOptionPane.showMessageDialog(this,memberException.getMessage());
requestFocusOnId();
}
return true;
}

private boolean edit()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;

MemberDLInterface memberDL=new MemberDL();
MemberInterface member=new Member();
try
{
member=memberDL.getById(getId());
setId(getId());
setFirstName(member.getFirstName());
setLastName(member.getLastName());
setEmailId(member.getEmailId());
setPhoneNumber(member.getPhoneNumber());
setAddress(member.getAddress());
setDateOfBirth(member.getDateOfBirth());
setSex(member.getSex());
enableAll();
disableId();
updateButton.setText("Update");
mode="update";
}catch(MemberException memberException)
{
JOptionPane.showMessageDialog(this,memberException.getMessage());
requestFocusOnId();
}
return true;
}

public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==updateButton)
{

if(mode=="edit")
{
edit();
}
else
{
updateMember();
}

}

if(ev.getSource()==cancelButton)
{

if(mode=="edit")
{
idTextField.setText("");
requestFocusOnId();
}
else
{
disableAll();
enableId();
reset();
requestFocusOnId();
mode="edit";
updateButton.setText("Edit");
}

}
}

}
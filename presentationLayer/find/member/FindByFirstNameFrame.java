package presentationLayer.find.member;

import presentationLayer.*;
import dataLayer.member.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class FindByFirstNameFrame extends JFrame implements CrossButtonListener, ListSelectionListener
{
private JLabel idLabel,nameLabel,emailIdLabel,phoneNumberLabel,sexLabel,addressLabel,dateOfBirthLabel;
private JTextField idTextField,nameTextField,emailIdTextField,phoneNumberTextField,dateOfBirthTextField,sexTextField,addressTextField;
private FindByFirstNameModel findByFirstNameModel;
private JTable memberTable;
private JScrollPane memberScrollPane;

public FindByFirstNameFrame(String firstName)
{
initComponent(firstName);

setTitle("Member By First Name");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(400,150);
setSize(650,450);
setResizable(false);
setVisible(true);
}

private void initTable(String firstName)
{
findByFirstNameModel=new FindByFirstNameModel(firstName);
memberTable=new JTable(findByFirstNameModel);
memberTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
memberTable.getColumnModel().getColumn(0).setPreferredWidth(30);
memberTable.getColumnModel().getColumn(1).setPreferredWidth(30);
memberTable.getColumnModel().getColumn(2).setPreferredWidth(30);
memberTable.getColumnModel().getColumn(3).setPreferredWidth(30);
memberTable.getColumnModel().getColumn(4).setPreferredWidth(30);
memberTable.getSelectionModel().addListSelectionListener(this);
memberScrollPane=new JScrollPane(memberTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
}

private void initComponent(String firstName)
{
initTable(firstName);

idLabel=new JLabel("Member Id");
nameLabel=new JLabel("Name");
emailIdLabel=new JLabel("Email Id");
phoneNumberLabel=new JLabel("Phone Number");
sexLabel=new JLabel("Sex");
addressLabel=new JLabel("Address");
dateOfBirthLabel=new JLabel("Date of Birth");

idTextField=new JTextField(10);
nameTextField=new JTextField(10);
emailIdTextField=new JTextField(10);
phoneNumberTextField=new JTextField(10);
sexTextField=new JTextField(10);
dateOfBirthTextField=new JTextField(10);
addressTextField=new JTextField(10);

setLayout(null);
idLabel.setBounds(60,250,100,25);
nameLabel.setBounds(60,280,100,25);
addressLabel.setBounds(60,310,100,25);
emailIdLabel.setBounds(60,340,100,25);
phoneNumberLabel.setBounds(60,370,100,25);
sexLabel.setBounds(420,280,40,25);
dateOfBirthLabel.setBounds(340,370,100,25);

idTextField.setBounds(160,250,150,25);
nameTextField.setBounds(160,280,230,25);
addressTextField.setBounds(160,310,430,25);
emailIdTextField.setBounds(160,340,430,25);
phoneNumberTextField.setBounds(160,370,150,25);
sexTextField.setBounds(460,280,130,25);
dateOfBirthTextField.setBounds(440,370,150,25);
memberScrollPane.setBounds(60,20,530,200);

add(idLabel);
add(nameLabel);
add(emailIdLabel);
add(phoneNumberLabel);
add(sexLabel);
add(addressLabel);
add(dateOfBirthLabel);
add(idTextField);
add(nameTextField);
add(emailIdTextField);
add(phoneNumberTextField);
add(dateOfBirthTextField);
add(addressTextField);
add(sexTextField);
add(memberScrollPane);

disableAll();
memberTable.setRowSelectionInterval(0,0);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

void disableAll()
{
idTextField.setEditable(false);
nameTextField.setEditable(false);
phoneNumberTextField.setEditable(false);
sexTextField.setEditable(false);
addressTextField.setEditable(false);
dateOfBirthTextField.setEditable(false);
emailIdTextField.setEditable(false);
}

private void setId(String id)
{
this.idTextField.setText(id);
}
private void setMemberName(String name)
{
this.nameTextField.setText(name);
}
private void setEmailId(String emailId)
{
this.emailIdTextField.setText(emailId);
}
private void setPhoneNumber(String phoneNumber)
{
this.phoneNumberTextField.setText(phoneNumber);
}
private void setAddress(String address)
{
this.addressTextField.setText(address);
}
private void setSex(String sex)
{
this.sexTextField.setText(sex);
}
private void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirthTextField.setText(dateOfBirth.getDate()+"-"+(dateOfBirth.getMonth()+1)+"-"+(dateOfBirth.getYear()+1900));
}

public void valueChanged(ListSelectionEvent event)
{
int selectedRowIndex=memberTable.getSelectedRow();
MemberInterface member=findByFirstNameModel.get(selectedRowIndex);
setId(member.getId());
setMemberName(member.getFirstName()+" "+member.getLastName());
setEmailId(member.getEmailId());
setPhoneNumber(member.getPhoneNumber());
setAddress(member.getAddress());
setDateOfBirth(member.getDateOfBirth());
setSex(member.getSex());
}

}
package presentationLayer.communicationBook;

import presentationLayer.*;
import dataLayer.referenceBook.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddCommunicationBookFrame extends JFrame implements CrossButtonListener, ActionListener
{
private JLabel idLabel,titleLabel,authorLabel,publisherLabel,editionLabel,totalStockLabel;
private JTextField idTextField,titleTextField,authorTextField,publisherTextField,totalStockTextField;
private JComboBox editionComboBox;
private JButton addButton,cancelButton;

public AddCommunicationBookFrame()
{
initComponent();
setTitle("Add Communication Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,180);
setSize(400,320);
setResizable(false);
setVisible(true);
}
private void initComponent()
{

idLabel=new JLabel("Book Id");
titleLabel=new JLabel("Title");
authorLabel=new JLabel("Author");
publisherLabel=new JLabel("Publisher");
editionLabel=new JLabel("Edition");
totalStockLabel=new JLabel("No. of Books");

idTextField=new JTextField(10);
titleTextField=new JTextField(10);
authorTextField=new JTextField(10);
publisherTextField=new JTextField(10);
totalStockTextField=new JTextField(10);

editionComboBox=new JComboBox();
editionComboBox.addItem("1st");
editionComboBox.addItem("2nd");
editionComboBox.addItem("3rd");
editionComboBox.addItem("4th");
editionComboBox.addItem("5th");
editionComboBox.addItem("6th");
editionComboBox.addItem("7th");
editionComboBox.addItem("8th");
editionComboBox.addItem("9th");
editionComboBox.addItem("10th");

addButton=new JButton("Add");
cancelButton=new JButton("Cancel");

setLayout(null);
idLabel.setBounds(60,20,100,25);
titleLabel.setBounds(60,50,100,25);
authorLabel.setBounds(60,80,100,25);
publisherLabel.setBounds(60,110,100,25);
editionLabel.setBounds(60,140,100,25);
totalStockLabel.setBounds(60,170,100,25);

idTextField.setBounds(160,20,150,25);
titleTextField.setBounds(160,50,150,25);
authorTextField.setBounds(160,80,150,25);
publisherTextField.setBounds(160,110,150,25);
totalStockTextField.setBounds(160,170,150,25);
editionComboBox.setBounds(160,140,150,25);

addButton.setBounds(80,230,80,30);
cancelButton.setBounds(220,230,80,30);

add(idLabel);
add(titleLabel);
add(authorLabel);
add(publisherLabel);
add(editionLabel);
add(totalStockLabel);
add(idTextField);
add(titleTextField);
add(authorTextField);
add(publisherTextField);
add(totalStockTextField);
add(editionComboBox);
add(addButton);
add(cancelButton);

addButton.addActionListener(this);
cancelButton.addActionListener(this);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private int getId()
{
return Integer.parseInt(idTextField.getText().trim());
}
private String getBookTitle()
{
return titleTextField.getText().trim();
}
private String getAuthor()
{
return authorTextField.getText().trim();
}
private String getPublisher()
{
return publisherTextField.getText().trim();
}
private String getEdition()
{
return editionComboBox.getSelectedItem().toString();
}
private int getTotalStock()
{
return Integer.parseInt(totalStockTextField.getText().trim());
}

private void requestFocusOnId()
{
idTextField.requestFocus();
}

private void reset()
{
idTextField.setText("");
titleTextField.setText("");
authorTextField.setText("");
publisherTextField.setText("");
editionComboBox.setSelectedIndex(0);
totalStockTextField.setText("");
}

private boolean addCommunicationBook()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;
if(InputValidator.isTextFieldEmpty(this,titleTextField,"Title required"))return false;
if(InputValidator.isTextFieldEmpty(this,authorTextField,"Author required"))return false;
if(InputValidator.isTextFieldEmpty(this,publisherTextField,"Publisher required"))return false;
if(InputValidator.isTextFieldEmpty(this,totalStockTextField,"Number of Book required"))return false;

if(!InputValidator.isInteger(this,idTextField,"ID. should be a number"))return false;
if(!InputValidator.isInteger(this,totalStockTextField,"Number of Book should be a number"))return false;

if(!InputValidator.isInValidRange(this,idTextField,1,99999," Member ID. should be in between (1-99999)"))return false;
if(!InputValidator.isInValidRange(this,totalStockTextField,1,99999,"Number of Book should be in between (1-99999)"))return false;

ReferenceBookDLInterface referenceBookDL=new ReferenceBookDL();
ReferenceBookInterface referenceBook=new ReferenceBook();
referenceBook.setId(getId());
referenceBook.setTitle(getBookTitle());
referenceBook.setAuthorName(getAuthor());
referenceBook.setPublisher(getPublisher());
referenceBook.setCategory("Communication Book");
referenceBook.setEdition(getEdition());
referenceBook.setTotalStock(getTotalStock());
try
{
referenceBookDL.add(referenceBook);
JOptionPane.showMessageDialog(this,"Book Added");
reset();
requestFocusOnId();
}catch(ReferenceBookException referenceBookException)
{
JOptionPane.showMessageDialog(this,referenceBookException.getMessage());
}

return true;
}

public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addButton)
{
addCommunicationBook();
}
if(ev.getSource()==cancelButton)
{
reset();
requestFocusOnId();
}
}

}

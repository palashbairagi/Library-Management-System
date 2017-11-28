package presentationLayer.referenceBook;

import presentationLayer.*;
import dataLayer.referenceBook.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditReferenceBookFrame extends JFrame implements CrossButtonListener, ActionListener
{
private JLabel idLabel,titleLabel,authorLabel,publisherLabel,editionLabel,totalStockLabel;
private JTextField idTextField,titleTextField,authorTextField,publisherTextField,totalStockTextField;
private JComboBox editionComboBox;
private JButton updateButton,cancelButton;
String mode="edit";

public EditReferenceBookFrame()
{
initComponent();
setTitle("Edit Reference Book");
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

updateButton=new JButton("Edit");
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

updateButton.setBounds(80,230,80,30);
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

private void setId(int id)
{
this.idTextField.setText(String.valueOf(id));
}
private void setBookTitle(String title)
{
this.titleTextField.setText(title);
}
private void setAuthor(String author)
{
this.authorTextField.setText(author);
}
private void setPublisher(String publisher)
{
this.publisherTextField.setText(publisher);
}
private void setEdition(String Edition)
{
this.editionComboBox.setSelectedItem(Edition);
}
private void setTotalStock(int totalStock)
{
this.totalStockTextField.setText(String.valueOf(totalStock));
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

private void disableAll()
{
idTextField.setEditable(false);
titleTextField.setEditable(false);
authorTextField.setEditable(false);
publisherTextField.setEditable(false);
editionComboBox.setVisible(false);
totalStockTextField.setEditable(false);
}

private void enableAll()
{
idTextField.setEditable(true);
titleTextField.setEditable(true);
authorTextField.setEditable(true);
publisherTextField.setEditable(true);
editionComboBox.setVisible(true);
totalStockTextField.setEditable(true);
}

private void disableId()
{
idTextField.setEditable(false);
}
private void enableId()
{
idTextField.setEditable(true);
}

private boolean updateBook()
{
if(InputValidator.isTextFieldEmpty(this,titleTextField,"Title required"))return false;
if(InputValidator.isTextFieldEmpty(this,authorTextField,"Author required"))return false;
if(InputValidator.isTextFieldEmpty(this,publisherTextField,"Publisher required"))return false;
if(InputValidator.isTextFieldEmpty(this,totalStockTextField,"Number of Book required"))return false;

if(!InputValidator.isInteger(this,totalStockTextField,"Number of Book should be a number"))return false;

if(!InputValidator.isInValidRange(this,totalStockTextField,1,99999,"Number of Book should be in between (1-99999)"))return false;

ReferenceBookDLInterface referenceBookDL=new ReferenceBookDL();
ReferenceBookInterface referenceBook=new ReferenceBook();
referenceBook.setId(getId());
referenceBook.setTitle(getBookTitle());
referenceBook.setAuthorName(getAuthor());
referenceBook.setPublisher(getPublisher());
referenceBook.setCategory("Reference Book");
referenceBook.setEdition(getEdition());
referenceBook.setTotalStock(getTotalStock());

try
{
referenceBookDL.update(referenceBook);
JOptionPane.showMessageDialog(this,"Book Updated");
disableAll();
enableId();
updateButton.setText("Edit");
reset();
requestFocusOnId();
mode="edit";
}catch(ReferenceBookException referenceBookException)
{
JOptionPane.showMessageDialog(this,referenceBookException.getMessage());
}

return true;
}

private boolean edit()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;
if(!InputValidator.isInteger(this,idTextField,"ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,idTextField,1,99999," Member ID. should be in between (1-99999)"))return false;

ReferenceBookDLInterface referenceBookDL=new ReferenceBookDL();
ReferenceBookInterface referenceBook=new ReferenceBook();

try
{
referenceBook=referenceBookDL.getById(getId());
setId(getId());
setBookTitle(referenceBook.getTitle());
setAuthor(referenceBook.getAuthorName());
setPublisher(referenceBook.getPublisher());
setEdition(referenceBook.getEdition());
setTotalStock(referenceBook.getTotalStock());
enableAll();
disableId();
updateButton.setText("Update");
mode="update";
}catch(ReferenceBookException referenceBookException)
{
JOptionPane.showMessageDialog(this,referenceBookException.getMessage());
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
updateBook();
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
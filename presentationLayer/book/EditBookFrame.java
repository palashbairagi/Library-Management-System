package presentationLayer.book;

import presentationLayer.*;
import dataLayer.book.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditBookFrame extends JFrame implements CrossButtonListener, ActionListener
{
private JLabel idLabel,titleLabel,authorLabel,publisherLabel,subjectLabel,categoryLabel,editionLabel,totalStockLabel;
private JTextField idTextField,titleTextField,authorTextField,publisherTextField,subjectTextField,categoryTextField,totalStockTextField;
private JComboBox editionComboBox;
private JButton updateButton,cancelButton;
String mode="edit";

public EditBookFrame()
{
initComponent();
setTitle("Edit Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,150);
setSize(400,360);
setResizable(false);
setVisible(true);
}

private void initComponent()
{

idLabel=new JLabel("Book Id");
titleLabel=new JLabel("Title");
authorLabel=new JLabel("Author");
publisherLabel=new JLabel("Publisher");
subjectLabel=new JLabel("Subject");
categoryLabel=new JLabel("Category");
editionLabel=new JLabel("Edition");
totalStockLabel=new JLabel("No. of Books");

idTextField=new JTextField(10);
titleTextField=new JTextField(10);
authorTextField=new JTextField(10);
publisherTextField=new JTextField(10);
subjectTextField=new JTextField(10);
categoryTextField=new JTextField(10);
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
subjectLabel.setBounds(60,140,100,25);
categoryLabel.setBounds(60,170,100,25);
editionLabel.setBounds(60,200,100,25);
totalStockLabel.setBounds(60,230,100,25);

idTextField.setBounds(160,20,150,25);
titleTextField.setBounds(160,50,150,25);
authorTextField.setBounds(160,80,150,25);
publisherTextField.setBounds(160,110,150,25);
subjectTextField.setBounds(160,140,150,25);
categoryTextField.setBounds(160,170,150,25);
totalStockTextField.setBounds(160,230,150,25);
editionComboBox.setBounds(160,200,150,25);

updateButton.setBounds(80,280,80,30);
cancelButton.setBounds(220,280,80,30);

add(idLabel);
add(titleLabel);
add(authorLabel);
add(publisherLabel);
add(subjectLabel);
add(categoryLabel);
add(editionLabel);
add(totalStockLabel);
add(idTextField);
add(titleTextField);
add(authorTextField);
add(publisherTextField);
add(subjectTextField);
add(categoryTextField);
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
private String getSubject()
{
return subjectTextField.getText().trim();
}
private String getCategory()
{
return categoryTextField.getText().trim();
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
private void setSubject(String subject)
{
this.subjectTextField.setText(subject);
}
private void setCategory(String category)
{
this.categoryTextField.setText(category);
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
subjectTextField.setText("");
categoryTextField.setText("");
editionComboBox.setSelectedIndex(0);
totalStockTextField.setText("");
}

private void disableAll()
{
idTextField.setEditable(false);
titleTextField.setEditable(false);
authorTextField.setEditable(false);
publisherTextField.setEditable(false);
subjectTextField.setEditable(false);
categoryTextField.setEditable(false);
editionComboBox.setVisible(false);
totalStockTextField.setEditable(false);
}

private void enableAll()
{
idTextField.setEditable(true);
titleTextField.setEditable(true);
authorTextField.setEditable(true);
publisherTextField.setEditable(true);
subjectTextField.setEditable(true);
categoryTextField.setEditable(true);
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
if(InputValidator.isTextFieldEmpty(this,subjectTextField,"Subject required"))return false;
if(InputValidator.isTextFieldEmpty(this,categoryTextField,"Category required"))return false;
if(InputValidator.isTextFieldEmpty(this,totalStockTextField,"Number of Book required"))return false;

if(!InputValidator.isInteger(this,totalStockTextField,"Number of Book should be a number"))return false;

if(!InputValidator.isInValidRange(this,totalStockTextField,1,99999,"Number of Book should be in between (1-99999)"))return false;

BookDL bookDL=new BookDL();
BookInterface book=new Book();
book.setId(getId());
book.setTitle(getBookTitle());
book.setAuthorName(getAuthor());
book.setPublisher(getPublisher());
book.setSubject(getSubject());
book.setCategory(getCategory());
book.setEdition(getEdition());
book.setAvailableStock(getTotalStock()-book.getCurrentHolder());

try
{
bookDL.update(book);
JOptionPane.showMessageDialog(this,"Book Updated");
disableAll();
enableId();
updateButton.setText("Edit");
reset();
requestFocusOnId();
mode="edit";
}catch(BookException bookException)
{
JOptionPane.showMessageDialog(this,bookException.getMessage());
}
return true;
}

private boolean edit()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;
if(!InputValidator.isInteger(this,idTextField,"ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,idTextField,1,99999," Member ID. should be in between (1-99999)"))return false;

BookDLInterface bookDL=new BookDL();
BookInterface book=new Book();
try
{
book=bookDL.getById(getId());
setId(getId());
setBookTitle(book.getTitle());
setAuthor(book.getAuthorName());
setPublisher(book.getPublisher());
setSubject(book.getSubject());
setCategory(book.getCategory());
setEdition(book.getEdition());
setTotalStock(book.getAvailableStock()+book.getCurrentHolder());
enableAll();
disableId();
updateButton.setText("Update");
mode="update";
}catch(BookException bookException)
{
JOptionPane.showMessageDialog(this,bookException.getMessage());
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
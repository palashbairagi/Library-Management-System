package presentationLayer.book;

import presentationLayer.*;
import dataLayer.book.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteBookFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel idLabel;
private JTextField idTextField;
private JButton deleteButton;

public DeleteBookFrame()
{
initComponent();
setTitle("Delete Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,300);
setSize(450,150);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
idLabel=new JLabel("Book Id");

idTextField=new JTextField(10);

deleteButton=new JButton("Delete");

setLayout(null);
idLabel.setBounds(60,40,80,25);
idTextField.setBounds(140,40,150,25);
deleteButton.setBounds(320,40,80,25);

add(idLabel);
add(idTextField);
add(deleteButton);

deleteButton.addActionListener(this);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private int getId()
{
return Integer.parseInt(idTextField.getText().trim());
}

private void requestFocusOnId()
{
idTextField.requestFocus();
}

private boolean deleteBook()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;
if(!InputValidator.isInteger(this,idTextField,"ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,idTextField,1,99999," Member ID. should be in between (1-99999)"))return false;

BookDL bookDL=new BookDL();
try
{
bookDL.delete(getId());
JOptionPane.showMessageDialog(this,"Book Deleted");
idTextField.setText("");
requestFocusOnId();
}catch(BookException bookException)
{
JOptionPane.showMessageDialog(this,bookException.getMessage());
requestFocusOnId();
}
return true;
}

public void actionPerformed(ActionEvent ev)
{
int selected=JOptionPane.showConfirmDialog(this,"Are you sure ?","Confirming delete operation",JOptionPane.YES_NO_OPTION);
if(selected==JOptionPane.YES_OPTION)deleteBook();
}

}
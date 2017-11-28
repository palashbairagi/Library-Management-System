package presentationLayer.issue;
import presentationLayer.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookIdFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel idLabel;
private JTextField idTextField;
private JButton viewButton;

public BookIdFrame()
{
initComponent();
setTitle("View Book");
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

viewButton=new JButton("View");

setLayout(null);
idLabel.setBounds(60,40,100,25);
idTextField.setBounds(140,40,150,25);
viewButton.setBounds(320,40,80,25);

add(idLabel);
add(idTextField);
add(viewButton);

viewButton.addActionListener(this);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private int getId()
{
return Integer.parseInt(idTextField.getText().trim());
}

private boolean openViewBookFrame()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;
if(!InputValidator.isInteger(this,idTextField,"ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,idTextField,1,99999," ID. should be in between (1-99999)"))return false;

try
{
ViewByBookFrame viewByBookFrame=new ViewByBookFrame(getId());
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"Book Not Issued");
requestFocusId();
}

return true;
}

private void requestFocusId()
{
idTextField.requestFocus();
}

public void actionPerformed(ActionEvent ev)
{
openViewBookFrame();
}

}
package presentationLayer.member;

import presentationLayer.*;
import dataLayer.member.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteMemberFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel idLabel;
private JTextField idTextField;
private JButton deleteButton;

public DeleteMemberFrame()
{
initComponent();
setTitle("Delete Member");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,300);
setSize(450,150);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
idLabel=new JLabel("Member Id");

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

private String getId()
{
return idTextField.getText().trim();
}

private void requestFocusOnId()
{
idTextField.requestFocus();
}

private boolean deleteMember()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;

MemberDL memberDL=new MemberDL();
try
{
memberDL.delete(getId());
JOptionPane.showMessageDialog(this,"Member Deleted");
idTextField.setText("");
requestFocusOnId();
}catch(MemberException memberException)
{
JOptionPane.showMessageDialog(this,memberException.getMessage());
requestFocusOnId();
}
return true;
}

public void actionPerformed(ActionEvent ev)
{
int selected=JOptionPane.showConfirmDialog(this,"Are you sure ?","Confirming delete operation",JOptionPane.YES_NO_OPTION);
if(selected==JOptionPane.YES_OPTION)deleteMember();
}

}
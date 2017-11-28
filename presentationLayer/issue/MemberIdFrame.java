package presentationLayer.issue;
import presentationLayer.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemberIdFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel idLabel;
private JTextField idTextField;
private JButton viewButton;

public MemberIdFrame()
{
initComponent();
setTitle("View Member");
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

private String getId()
{
return idTextField.getText().trim();
}

private void requestFocusOnId()
{
idTextField.requestFocus();
}

private boolean openViewMemberFrame()
{
if(InputValidator.isTextFieldEmpty(this,idTextField,"ID. required"))return false;

try
{
ViewByMemberFrame viewByMemberFrame=new ViewByMemberFrame(getId());
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"Book Not Issued To Member");
requestFocusOnId();
}

return true;
}

public void actionPerformed(ActionEvent ev)
{
openViewMemberFrame();
}

}
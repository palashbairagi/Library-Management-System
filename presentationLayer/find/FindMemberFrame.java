package presentationLayer.find;

import presentationLayer.*;
import presentationLayer.find.member.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class FindMemberFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel searchLabel;
private JTextField searchTextField;
private JRadioButton byFirstName,byLastName;
private ButtonGroup searchGroup;
private JButton findButton;
private JPanel radioButtonPanel;

public FindMemberFrame()
{
initComponent();
setTitle("Find Member");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,230);
setSize(450,170);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
searchLabel=new JLabel("Search Field: ");
searchTextField=new JTextField(10);

searchGroup=new ButtonGroup();
byFirstName=new JRadioButton("By First Name");
byLastName=new JRadioButton("By Last Name");
byFirstName.setSelected(true);

searchGroup.add(byFirstName);
searchGroup.add(byLastName);

findButton=new JButton("Find ");

radioButtonPanel=new JPanel();
radioButtonPanel.setLayout(new GridLayout(2,1));
radioButtonPanel.add(byFirstName);
radioButtonPanel.add(byLastName);

setLayout(null);
searchLabel.setBounds(60,20,100,25);
searchTextField.setBounds(160,20,150,25);
radioButtonPanel.setBounds(60,60,150,60);

findButton.setBounds(320,20,80,25);

findButton.addActionListener(this);

add(searchLabel);
add(searchTextField);
add(radioButtonPanel);
add(findButton);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private boolean search()
{
String text=searchTextField.getText();

if(InputValidator.isTextFieldEmpty(this,searchTextField,"Field required"))return false;

searchTextField.requestFocus();

if(byFirstName.isSelected()==true)
{
try
{
FindByFirstNameFrame findByFirstNameFrame=new FindByFirstNameFrame(text);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"No Member");
}
}

if(byLastName.isSelected()==true)
{
try
{
FindByLastNameFrame findByLastNameFrame=new FindByLastNameFrame(text);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"No Member");
}
}

return true;
}

public void actionPerformed(ActionEvent ev)
{
search();
}

}
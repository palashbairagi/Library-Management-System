package presentationLayer.find;

import presentationLayer.*;
import presentationLayer.find.book.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FindBookFrame extends JFrame implements CrossButtonListener,ActionListener
{
private JLabel searchLabel;
private JTextField searchTextField;
private JRadioButton byAuthor,byCategory,bySubject;
private ButtonGroup searchGroup;
private JButton findButton;
private JPanel radioButtonPanel;

public FindBookFrame()
{
initComponent();
setTitle("Find Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,230);
setSize(450,200);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
searchLabel=new JLabel("Search Field: ");
searchTextField=new JTextField(10);

searchGroup=new ButtonGroup();
bySubject=new JRadioButton("By Subject");
byAuthor=new JRadioButton("By Author");
byCategory=new JRadioButton("By Category");
bySubject.setSelected(true);

searchGroup.add(bySubject);
searchGroup.add(byAuthor);
searchGroup.add(byCategory);

findButton=new JButton("Find ");

radioButtonPanel=new JPanel();
radioButtonPanel.setLayout(new GridLayout(3,1));
radioButtonPanel.add(bySubject);
radioButtonPanel.add(byAuthor);
radioButtonPanel.add(byCategory);

setLayout(null);
searchLabel.setBounds(60,20,100,25);
searchTextField.setBounds(160,20,150,25);
radioButtonPanel.setBounds(60,60,100,100);

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

if(bySubject.isSelected()==true)
{
try
{
FindBySubjectFrame findBySubjectFrame=new FindBySubjectFrame(text);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"No Book");
}
}

if(byAuthor.isSelected()==true)
{
try
{
FindByAuthorFrame findByAuthorFrame=new FindByAuthorFrame(text);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"No Book");
}
}


if(byCategory.isSelected()==true)
{
try
{
FindByCategoryFrame findByCategoryFrame=new FindByCategoryFrame(text);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,"No Book");
}
}


return true;
}

public void actionPerformed(ActionEvent ev)
{
search();
}

}
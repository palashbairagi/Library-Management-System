import presentationLayer.*;
import dataLayer.member.*;

import javax.swing.*;
import java.awt.event.*;

public class TotalMemberFrame extends JFrame implements CrossButtonListener
{
private JLabel totalMemberLabel, totalMemberValueLabel;

public TotalMemberFrame()
{
initComponent();
setTitle("Members");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(450,250);
setSize(250,130);
setResizable(false);
setVisible(true);
}

private void initComponent()
{
totalMemberLabel=new JLabel("Total Member:");
totalMemberValueLabel=new JLabel("");

setLayout(null);
totalMemberLabel.setBounds(50,30,100,30);
totalMemberValueLabel.setBounds(150,30,100,30);

add(totalMemberLabel);
add(totalMemberValueLabel);

getTotalMember();
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private void getTotalMember()
{
int  totalMember=0;
MemberDLInterface memberDL=new MemberDL();

try
{
totalMember=memberDL.getTotalMember();
}catch(MemberException memberException)
{

}

totalMemberValueLabel.setText(""+totalMember);
}

}
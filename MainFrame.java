import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

import presentationLayer.*;
import presentationLayer.book.*;
import presentationLayer.member.*;
import presentationLayer.issue.*;
import presentationLayer.find.*;
import presentationLayer.referenceBook.*;
import presentationLayer.communicationBook.*;
import presentationLayer.magzines.*;
import dataLayer.issue.*;

public class MainFrame extends JFrame implements ActionListener, CrossButtonListener
{
JMenuBar menuBar;
JMenu viewMenu,memberMenu,bookMenu,referenceMenu,referenceBook,magzines,communicationBook,findMenu;
JMenuItem viewByMember,viewByBook;
JMenuItem addMember,viewMember,editMember,deleteMember;
JMenuItem addBook,viewBook,editBook,deleteBook; 
JMenuItem addReferenceBook,viewReferenceBook,editReferenceBook,deleteReferenceBook;
JMenuItem addMagzines,viewMagzines,editMagzines,deleteMagzines;
JMenuItem addCommunicationBook,viewCommunicationBook,editCommunicationBook,deleteCommunicationBook;
JMenuItem findBook,findMember;

JToolBar toolbar;

JTextField bookIdTextField,memberIdTextField;
JLabel bookIdLabel,memberIdLabel;
JButton issueButton;
JButton totalBookButton,totalMemberButton,submitButton;

MainFrame()
{
initComponent();
setTitle("Library Management System");
setJMenuBar(menuBar);
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(50,10);
setSize(1250,700);
setResizable(false);
setVisible(true);
}

public void initMenuComponent()
{

viewMenu=new JMenu("View");
memberMenu=new JMenu("Member");
bookMenu=new JMenu("Book");
findMenu=new JMenu("Find");

viewByBook=new JMenuItem("Book");
viewByMember=new JMenuItem("Member");

viewByBook.addActionListener(this);
viewByMember.addActionListener(this);

viewMenu.add(viewByBook);
viewMenu.add(viewByMember);

addMember=new JMenuItem("Add");
viewMember=new JMenuItem("View");
editMember=new JMenuItem("Update");
deleteMember=new JMenuItem("Delete");

addMember.addActionListener(this);
viewMember.addActionListener(this);
editMember.addActionListener(this);
deleteMember.addActionListener(this);

memberMenu.add(addMember);
memberMenu.add(viewMember);
memberMenu.add(editMember);
memberMenu.add(deleteMember);

addBook=new JMenuItem("Add");
viewBook=new JMenuItem("View");
editBook=new JMenuItem("Update");
deleteBook=new JMenuItem("Delete");

addBook.addActionListener(this);
viewBook.addActionListener(this);
editBook.addActionListener(this);
deleteBook.addActionListener(this);

bookMenu.add(addBook);
bookMenu.add(viewBook);
bookMenu.add(editBook);
bookMenu.add(deleteBook);

referenceBook=new JMenu("Book");
addReferenceBook=new JMenuItem("Add");
viewReferenceBook=new JMenuItem("View");
editReferenceBook=new JMenuItem("Update");
deleteReferenceBook=new JMenuItem("Delete");

addReferenceBook.addActionListener(this);
viewReferenceBook.addActionListener(this);
editReferenceBook.addActionListener(this);
deleteReferenceBook.addActionListener(this);

referenceBook.add(addReferenceBook);
referenceBook.add(viewReferenceBook);
referenceBook.add(editReferenceBook);
referenceBook.add(deleteReferenceBook);

magzines=new JMenu("Magzines");
addMagzines=new JMenuItem("Add");
viewMagzines=new JMenuItem("View");
editMagzines=new JMenuItem("Update");
deleteMagzines=new JMenuItem("Delete");

addMagzines.addActionListener(this);
viewMagzines.addActionListener(this);
editMagzines.addActionListener(this);
deleteMagzines.addActionListener(this);

magzines.add(addMagzines);
magzines.add(viewMagzines);
magzines.add(editMagzines);
magzines.add(deleteMagzines);

communicationBook=new JMenu("Commnication Book");
addCommunicationBook=new JMenuItem("Add");
viewCommunicationBook=new JMenuItem("View");
editCommunicationBook=new JMenuItem("Update");
deleteCommunicationBook=new JMenuItem("Delete");

communicationBook.add(addCommunicationBook);
communicationBook.add(viewCommunicationBook);
communicationBook.add(editCommunicationBook);
communicationBook.add(deleteCommunicationBook);

addCommunicationBook.addActionListener(this);
viewCommunicationBook.addActionListener(this);
editCommunicationBook.addActionListener(this);
deleteCommunicationBook.addActionListener(this);

referenceMenu= new JMenu("Reference");
referenceMenu.add(referenceBook);
referenceMenu.add(magzines);
referenceMenu.add(communicationBook);

findBook=new JMenuItem("Book");
findMember=new JMenuItem("Member");

findBook.addActionListener(this);
findMember.addActionListener(this);

findMenu.add(findBook);
findMenu.add(findMember);

menuBar=new JMenuBar();
menuBar.add(viewMenu);
menuBar.add(memberMenu);
menuBar.add(bookMenu);
menuBar.add(referenceMenu);
menuBar.add(findMenu);

}

private void initComponent()
{
initMenuComponent();

bookIdLabel=new JLabel("Book ID");
memberIdLabel=new JLabel("Member ID");
bookIdTextField=new JTextField(10);
memberIdTextField=new JTextField(10);

ImageIcon submitBook=new ImageIcon("submitBook.png");
ImageIcon totalMember = new ImageIcon("totalMember.png");
ImageIcon totalBook=new ImageIcon("totalBook.png");

issueButton=new JButton("Issue");
submitButton=new JButton(submitBook);
totalMemberButton=new JButton(totalMember);
totalBookButton=new JButton(totalBook);

setLayout(null);
bookIdLabel.setBounds(440,280,100,30);
memberIdLabel.setBounds(440,230,100,30);
bookIdTextField.setBounds(640,280,150,30);
memberIdTextField.setBounds(640,230,150,30);

issueButton.setBounds(580,400,80,30);

toolbar=new JToolBar();
toolbar.add(submitButton);
toolbar.add(totalMemberButton);
toolbar.add(totalBookButton);

add(bookIdLabel);
add(memberIdLabel);
add(bookIdTextField);
add(memberIdTextField);
add(issueButton);
setLayout(new BorderLayout());
add(toolbar,BorderLayout.NORTH);

issueButton.addActionListener(this);
submitButton.addActionListener(this);
totalBookButton.addActionListener(this);
totalMemberButton.addActionListener(this);
}

public void windowClosing(WindowEvent ev)
{
System.exit(0);
}

private void requestFocusOnMemberId()
{
memberIdTextField.requestFocus();
}
private void requestFocusOnBookId()
{
bookIdTextField.requestFocus();
}

private void reset()
{
bookIdTextField.setText("");
memberIdTextField.setText("");
}

private String getMemberId()
{
return memberIdTextField.getText().trim();
}
private int getBookId()
{
return Integer.parseInt(bookIdTextField.getText().trim());
}

private boolean issue()
{
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
Calendar cal = Calendar.getInstance();

if(InputValidator.isTextFieldEmpty(this,memberIdTextField,"Member ID. required"))return false;
if(InputValidator.isTextFieldEmpty(this,bookIdTextField,"Book ID. required"))return false;

if(!InputValidator.isInteger(this,bookIdTextField," Book ID. should be a number"))return false;
if(!InputValidator.isInValidRange(this,bookIdTextField,1,99999," Book ID. should be in between (1-99999)"))return false;

IssueDL issueDL=new IssueDL();
Issue issue=new Issue();
issue.setMemberId(getMemberId());
issue.setBookId(getBookId());
issue.setIssueDate(cal.getTime());
cal.add(Calendar.DATE, 7);
issue.setSubmissionDate(cal.getTime());

try
{
issueDL.add(issue);
JOptionPane.showMessageDialog(this,"Book Issued");
reset();
requestFocusOnMemberId();
}catch(IssueException issueException)
{
JOptionPane.showMessageDialog(this,issueException.getMessage());
requestFocusOnBookId();
}

return true;
}

public void actionPerformed(ActionEvent ev)
{

if(ev.getSource()==viewByMember)
{
MemberIdFrame memberIdFrame=new MemberIdFrame();
}

if(ev.getSource()==viewByBook)
{
BookIdFrame bookIdFrame=new BookIdFrame();
}

if(ev.getSource()==addMember)
{
AddMemberFrame addMemberFrame=new AddMemberFrame();
}

if(ev.getSource()==viewMember)
{
ViewMemberFrame viewMemberFrame=new ViewMemberFrame();
}

if(ev.getSource()==editMember)
{
EditMemberFrame editMemberFrame=new EditMemberFrame();
}

if(ev.getSource()==deleteMember)
{
DeleteMemberFrame deleteMemberFrame=new DeleteMemberFrame();
}

if(ev.getSource()==addBook)
{
AddBookFrame addBookFrame=new AddBookFrame();
}

if(ev.getSource()==viewBook)
{
ViewBookFrame viewBookFrame=new ViewBookFrame();
}

if(ev.getSource()==editBook)
{
EditBookFrame editBookFrame=new EditBookFrame();
}

if(ev.getSource()==addReferenceBook)
{
AddReferenceBookFrame addReferenceBook=new AddReferenceBookFrame();
}

if(ev.getSource()==viewReferenceBook)
{
ViewReferenceBookFrame viewReferenceBookFrame=new ViewReferenceBookFrame();
}

if(ev.getSource()==editReferenceBook)
{
EditReferenceBookFrame editReferenceBookFrame=new EditReferenceBookFrame();
}

if(ev.getSource()==deleteReferenceBook)
{
DeleteReferenceBookFrame deleteReferenceBookFrame=new DeleteReferenceBookFrame();
}

if(ev.getSource()==addMagzines)
{
AddMagzineFrame addMagzineFrame=new AddMagzineFrame();
}

if(ev.getSource()==viewMagzines)
{
ViewMagzineFrame viewMagzineFrame=new ViewMagzineFrame();
}

if(ev.getSource()==editMagzines)
{
EditMagzineFrame editMagzineFrame=new EditMagzineFrame();
}

if(ev.getSource()==deleteMagzines)
{
DeleteMagzineFrame deleteMagzineFrame=new DeleteMagzineFrame();
}

if(ev.getSource()==addCommunicationBook)
{
AddCommunicationBookFrame addCommunicationBookFrame=new AddCommunicationBookFrame();
}

if(ev.getSource()==viewCommunicationBook)
{
ViewCommunicationBookFrame viewCommunicationBookFrame=new ViewCommunicationBookFrame();
}

if(ev.getSource()==editCommunicationBook)
{
EditCommunicationBookFrame editCommunicationBookFrame=new EditCommunicationBookFrame();
}

if(ev.getSource()==deleteCommunicationBook)
{
DeleteCommunicationBookFrame deleteCommunicationBookFrame=new DeleteCommunicationBookFrame();
}

if(ev.getSource()==deleteBook)
{
DeleteBookFrame deleteBookFrame=new DeleteBookFrame();
}

if(ev.getSource()==findBook)
{
FindBookFrame findBookFrame=new FindBookFrame();
}

if(ev.getSource()==findMember)
{
FindMemberFrame findMemberFrame=new FindMemberFrame();
}

if(ev.getSource()==issueButton)
{
issue();
}

if(ev.getSource()==submitButton)
{
SubmitFrame submitFrame=new SubmitFrame();
}

if(ev.getSource()==totalBookButton)
{
TotalBookFrame totalBookFrame=new TotalBookFrame();
}

if(ev.getSource()==totalMemberButton)
{
TotalMemberFrame totalMemberFrame=new TotalMemberFrame();
}

}

}

class LibraryManagement
{
public static void main(String gg[])
{
MainFrame mainFrame=new MainFrame();
}
}
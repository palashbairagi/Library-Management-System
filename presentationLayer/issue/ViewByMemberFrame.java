package presentationLayer.issue;

import presentationLayer.*;
import dataLayer.issue.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class ViewByMemberFrame extends JFrame implements CrossButtonListener,ListSelectionListener
{
private JLabel bookIdLabel,bookTitleLabel,bookAuthorLabel,bookEditionLabel;
private JLabel memberIdLabel,memberNameLabel,memberPhoneNumberLabel;
private JLabel issueDateLabel,submissionDateLabel;

private JTextField bookIdTextField,bookTitleTextField,bookAuthorTextField,bookEditionTextField;
private JTextField memberIdTextField,memberNameTextField,memberPhoneNumberTextField;
private JTextField issueDateTextField,submissionDateTextField;

private ViewByMemberModel viewByMemberModel;
private JTable issueTable;
private JScrollPane issueScrollPane;

public ViewByMemberFrame(String memberId)
{
initComponent(memberId);
setTitle("Books Issued to a Member");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(350,150);
setSize(650,500);
setResizable(false);
setVisible(true);
}

private void initTable(String memberId)
{
viewByMemberModel=new ViewByMemberModel(memberId);
issueTable=new JTable(viewByMemberModel);
issueTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
issueTable.getColumnModel().getColumn(0).setPreferredWidth(20);
issueTable.getColumnModel().getColumn(1).setPreferredWidth(40);
issueTable.getColumnModel().getColumn(2).setPreferredWidth(40);
issueTable.getSelectionModel().addListSelectionListener(this);
issueScrollPane=new JScrollPane(issueTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
}

private void initComponent(String memberId)
{
initTable(memberId);

bookIdLabel=new JLabel("Book ID");
bookTitleLabel=new JLabel("Title");
bookAuthorLabel=new JLabel("Author");
bookEditionLabel=new JLabel("Edition");
memberIdLabel=new JLabel("Member Id");
memberNameLabel=new JLabel("Name");
memberPhoneNumberLabel=new JLabel("Phone Number");
issueDateLabel=new JLabel("Issue Date");
submissionDateLabel=new JLabel("Submission Date");

bookIdTextField=new JTextField(10);
bookTitleTextField=new JTextField(10);
bookAuthorTextField=new JTextField(10);
bookEditionTextField=new JTextField(10);
memberIdTextField=new JTextField(10);
memberNameTextField=new JTextField(10);
memberPhoneNumberTextField=new JTextField(10);
issueDateTextField=new JTextField(10);
submissionDateTextField=new JTextField(10);

setLayout(null);
issueScrollPane.setBounds(60,110,530,200);
bookIdLabel.setBounds(60,330,100,25);
bookTitleLabel.setBounds(60,360,100,25);
bookAuthorLabel.setBounds(340,330,100,25);
bookEditionLabel.setBounds(340,360,100,25);
issueDateLabel.setBounds(60,390,100,25);
submissionDateLabel.setBounds(340,390,100,25);

memberIdLabel.setBounds(60,30,100,25);
memberNameLabel.setBounds(60,60,100,25);
memberPhoneNumberLabel.setBounds(340,30,100,25);

bookIdTextField.setBounds(160,330,150,25);
bookTitleTextField.setBounds(160,360,150,25);
bookAuthorTextField.setBounds(440,330,150,25);
bookEditionTextField.setBounds(440,360,150,25);
issueDateTextField.setBounds(160,390,150,25);
submissionDateTextField.setBounds(440,390,150,25);

memberIdTextField.setBounds(160,30,150,25);
memberNameTextField.setBounds(160,60,150,25);
memberPhoneNumberTextField.setBounds(440,30,150,25);

add(bookIdLabel);
add(bookTitleLabel);
add(bookAuthorLabel);
add(bookEditionLabel);
add(memberIdLabel);
add(memberNameLabel);
add(memberPhoneNumberLabel);
add(issueDateLabel);
add(submissionDateLabel);

add(bookIdTextField);
add(bookTitleTextField);
add(bookAuthorTextField);
add(bookEditionTextField);
add(memberIdTextField);
add(memberNameTextField);
add(memberPhoneNumberTextField);
add(issueDateTextField);
add(submissionDateTextField);
add(issueScrollPane);

disableAll();
issueTable.setRowSelectionInterval(0,0);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

private void disableAll()
{
bookIdTextField.setEditable(false);
bookTitleTextField.setEditable(false);
bookAuthorTextField.setEditable(false);
bookEditionTextField.setEditable(false);
memberIdTextField.setEditable(false);
memberNameTextField.setEditable(false);
memberPhoneNumberTextField.setEditable(false);
issueDateTextField.setEditable(false);
submissionDateTextField.setEditable(false);
}

private void setMemberId(String id)
{
this.memberIdTextField.setText(id);
}
private void setMemberName(String name)
{
this.memberNameTextField.setText(name);
}
private void setPhoneNumber(String phoneNumber)
{
this.memberPhoneNumberTextField.setText(phoneNumber);
}

private void setBookId(int id)
{
this.bookIdTextField.setText(String.valueOf(id));
}
private void setBookTitle(String title)
{
this.bookTitleTextField.setText(title);
}
private void setAuthor(String author)
{
this.bookAuthorTextField.setText(author);
}
private void setEdition(String Edition)
{
this.bookEditionTextField.setText(Edition);
}
private void setSubmissionDate(java.util.Date submissionDate)
{
this.submissionDateTextField.setText(submissionDate.getDate()+"-"+(submissionDate.getMonth()+1)+"-"+(submissionDate.getYear()+1900));
}
private void setIssueDate(java.util.Date issueDate)
{
this.issueDateTextField.setText(issueDate.getDate()+"-"+(issueDate.getMonth()+1)+"-"+(issueDate.getYear()+1900));
}

public void valueChanged(ListSelectionEvent event)
{
int selectedRowIndex=issueTable.getSelectedRow();
IssueInterface issue=viewByMemberModel.get(selectedRowIndex);
setMemberId(issue.getMemberId());
setMemberName(issue.getFirstName()+" "+issue.getLastName());
setPhoneNumber(issue.getPhoneNumber());
setBookId(issue.getBookId());
setBookTitle(issue.getTitle());
setAuthor(issue.getAuthorName());
setEdition(issue.getEdition());
setIssueDate(issue.getIssueDate());
setSubmissionDate(issue.getSubmissionDate());
}

}
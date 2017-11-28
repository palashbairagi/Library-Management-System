package presentationLayer.issue;

import presentationLayer.*;
import dataLayer.issue.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class ViewByBookFrame extends JFrame implements CrossButtonListener, ListSelectionListener
{
private JLabel bookIdLabel,bookTitleLabel,bookAuthorLabel,bookEditionLabel,bookAvailableLabel,bookStockLabel,currentHolderLabel;
private JLabel memberIdLabel,memberNameLabel;
private JLabel issueDateLabel,submissionDateLabel;
private JTextField bookIdTextField,bookTitleTextField,bookAuthorTextField,bookEditionTextField,bookAvailableTextField,bookStockTextField,currentHolderTextField;
private JTextField memberIdTextField,memberNameTextField,memberPhoneNumberTextField;
private JTextField issueDateTextField,submissionDateTextField;

private ViewByBookModel viewByBookModel;
private JTable issueTable;
private JScrollPane issueScrollPane;

public ViewByBookFrame(int bookId)
{
initComponent(bookId);
setTitle("Book Issued To Members");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(350,150);
setSize(650,450);
setResizable(false);
setVisible(true);
}

private void initTable(int bookId)
{
viewByBookModel=new ViewByBookModel(bookId);
issueTable=new JTable(viewByBookModel);
issueTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
issueTable.getColumnModel().getColumn(0).setPreferredWidth(30);
issueTable.getColumnModel().getColumn(1).setPreferredWidth(30);
issueTable.getColumnModel().getColumn(2).setPreferredWidth(30);
issueTable.getSelectionModel().addListSelectionListener(this);
issueScrollPane=new JScrollPane(issueTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
}

public void initComponent(int bookId)
{
initTable(bookId);
bookIdLabel=new JLabel("Book ID");
bookTitleLabel=new JLabel("Title");
bookAuthorLabel=new JLabel("Author");
bookEditionLabel=new JLabel("Edition");
bookStockLabel=new JLabel("Total Book");
bookAvailableLabel=new JLabel("Book Available");
currentHolderLabel=new JLabel("Current Holder");

memberIdLabel=new JLabel("Member Id");
memberNameLabel=new JLabel("Name");
issueDateLabel=new JLabel("Issue Date");
submissionDateLabel=new JLabel("Submission Date");

bookIdTextField=new JTextField(10);
bookTitleTextField=new JTextField(10);
bookAuthorTextField=new JTextField(10);
bookEditionTextField=new JTextField(10);
bookStockTextField=new JTextField(10);
bookAvailableTextField=new JTextField(10);
currentHolderTextField=new JTextField(10);

memberIdTextField=new JTextField(10);
memberNameTextField=new JTextField(10);
issueDateTextField=new JTextField(10);
submissionDateTextField=new JTextField(10);

setLayout(null);
issueScrollPane.setBounds(60,160,530,150);
bookIdLabel.setBounds(60,20,100,25);
bookTitleLabel.setBounds(60,50,100,25);
bookAuthorLabel.setBounds(340,20,100,25);
bookEditionLabel.setBounds(340,50,100,25);
bookStockLabel.setBounds(60,80,100,25);
bookAvailableLabel.setBounds(60,110,100,25);
currentHolderLabel.setBounds(340,80,100,25);

memberIdLabel.setBounds(60,330,100,25);
memberNameLabel.setBounds(60,360,100,25);
issueDateLabel.setBounds(340,330,100,25);
submissionDateLabel.setBounds(340,360,100,25);

bookIdTextField.setBounds(160,20,150,25);
bookTitleTextField.setBounds(160,50,150,25);
bookAuthorTextField.setBounds(440,20,150,25);
bookEditionTextField.setBounds(440,50,150,25);
bookStockTextField.setBounds(160,80,150,25);
bookAvailableTextField.setBounds(160,110,150,25);
currentHolderTextField.setBounds(440,80,150,25);

memberIdTextField.setBounds(160,330,150,25);
memberNameTextField.setBounds(160,360,150,25);
issueDateTextField.setBounds(440,330,150,25);
submissionDateTextField.setBounds(440,360,150,25);

add(bookIdLabel);
add(bookTitleLabel);
add(bookAuthorLabel);
add(bookEditionLabel);
add(bookStockLabel);
add(bookAvailableLabel);
add(currentHolderLabel);
add(memberIdLabel);
add(memberNameLabel);
add(issueDateLabel);
add(submissionDateLabel);

add(bookIdTextField);
add(bookTitleTextField);
add(bookAuthorTextField);
add(bookEditionTextField);
add(bookStockTextField);
add(bookAvailableTextField);
add(currentHolderTextField);
add(memberIdTextField);
add(memberNameTextField);
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
bookStockTextField.setEditable(false);
bookAvailableTextField.setEditable(false);
currentHolderTextField.setEditable(false);
memberIdTextField.setEditable(false);
memberNameTextField.setEditable(false);
issueDateTextField.setEditable(false);
submissionDateTextField.setEditable(false);
}

private void setMemberId(String id)
{
this.memberIdTextField.setText(String.valueOf(id));
}
private void setMemberName(String name)
{
this.memberNameTextField.setText(name);
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
private void setStock(int stock)
{
this.bookStockTextField.setText(String.valueOf(stock));
}
private void setAvailableStock(int stock)
{
this.bookAvailableTextField.setText(String.valueOf(stock));
}
private void setCurrentHolder(int holders)
{
this.currentHolderTextField.setText(String.valueOf(holders));
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
IssueInterface issue=viewByBookModel.get(selectedRowIndex);
setBookId(issue.getBookId());
setBookTitle(issue.getTitle());
setAuthor(issue.getAuthorName());
setEdition(issue.getEdition());
setStock(issue.getStock()+issue.getCurrentHolder());
setAvailableStock(issue.getStock());
setCurrentHolder(issue.getCurrentHolder());
setSubmissionDate(issue.getSubmissionDate());
setMemberId(issue.getMemberId());
setMemberName(issue.getFirstName()+" "+issue.getLastName());
setIssueDate(issue.getIssueDate());
}

}
package presentationLayer.find.book;

import presentationLayer.*;
import dataLayer.book.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class FindByAuthorFrame extends JFrame implements CrossButtonListener, ListSelectionListener
{
private JLabel idLabel,titleLabel,authorLabel,publisherLabel,subjectLabel,categoryLabel,editionLabel,totalStockLabel,availableStockLabel,currentHolderLabel;
private JTextField idTextField,titleTextField,authorTextField,publisherTextField,subjectTextField,categoryTextField,totalStockTextField,editionTextField,availableStockTextField,currentHolderTextField;
private JTable bookTable;
private FindByAuthorModel findByAuthorModel;
private JScrollPane bookScrollPane;

public FindByAuthorFrame(String author)
{
initComponent(author);
setTitle("Book By Author");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(400,150);
setSize(650,450);
setResizable(false);
setVisible(true);
}

private void initTable(String author)
{
findByAuthorModel=new FindByAuthorModel(author);
bookTable=new JTable(findByAuthorModel);
bookTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
bookTable.getColumnModel().getColumn(0).setPreferredWidth(20);
bookTable.getColumnModel().getColumn(1).setPreferredWidth(30);
bookTable.getColumnModel().getColumn(2).setPreferredWidth(30);
bookTable.getColumnModel().getColumn(3).setPreferredWidth(30);
bookTable.getColumnModel().getColumn(4).setPreferredWidth(30);
bookTable.getSelectionModel().addListSelectionListener(this);
bookScrollPane=new JScrollPane(bookTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
}

private void initComponent(String author)
{
initTable(author);
idLabel=new JLabel("Book Id");
titleLabel=new JLabel("Title");
authorLabel=new JLabel("Author");
publisherLabel=new JLabel("Publisher");
subjectLabel=new JLabel("Subject");
categoryLabel=new JLabel("Category");
editionLabel=new JLabel("Edition");
totalStockLabel=new JLabel("No. of Books");
availableStockLabel=new JLabel("Books Available");
currentHolderLabel=new JLabel("Current Holders");

idTextField=new JTextField(10);
titleTextField=new JTextField(10);
authorTextField=new JTextField(10);
publisherTextField=new JTextField(10);
subjectTextField=new JTextField(10);
categoryTextField=new JTextField(10);
totalStockTextField=new JTextField(10);
availableStockTextField=new JTextField(10);
currentHolderTextField=new JTextField(10);
editionTextField=new JTextField(10);

setLayout(null);
idLabel.setBounds(60,250,100,25);
titleLabel.setBounds(60,280,100,25);
authorLabel.setBounds(60,310,100,25);
publisherLabel.setBounds(60,340,100,25);
subjectLabel.setBounds(60,370,100,25);
categoryLabel.setBounds(340,250,100,25);
editionLabel.setBounds(340,280,100,25);
totalStockLabel.setBounds(340,310,100,25);
availableStockLabel.setBounds(340,340,100,25);
currentHolderLabel.setBounds(340,370,100,25);

idTextField.setBounds(160,250,150,25);
titleTextField.setBounds(160,280,150,25);
authorTextField.setBounds(160,310,150,25);
publisherTextField.setBounds(160,340,150,25);
subjectTextField.setBounds(160,370,150,25);
categoryTextField.setBounds(440,250,150,25);
editionTextField.setBounds(440,280,150,25);
totalStockTextField.setBounds(440,310,150,25);
availableStockTextField.setBounds(440,340,150,25);
currentHolderTextField.setBounds(440,370,150,25);
bookScrollPane.setBounds(60,20,530,200);

add(idLabel);
add(titleLabel);
add(authorLabel);
add(publisherLabel);
add(subjectLabel);
add(categoryLabel);
add(editionLabel);
add(totalStockLabel);
add(availableStockLabel);
add(currentHolderLabel);
add(idTextField);
add(titleTextField);
add(authorTextField);
add(publisherTextField);
add(subjectTextField);
add(categoryTextField);
add(totalStockTextField);
add(availableStockTextField);
add(currentHolderTextField);
add(editionTextField);
add(bookScrollPane);

disableAll();
bookTable.setRowSelectionInterval(0,0);
}

public void windowClosing(WindowEvent ev)
{
dispose();
}

void disableAll()
{
idTextField.setEditable(false);
titleTextField.setEditable(false);
authorTextField.setEditable(false);
publisherTextField.setEditable(false);
subjectTextField.setEditable(false);
categoryTextField.setEditable(false);
editionTextField.setEditable(false);
totalStockTextField.setEditable(false);
availableStockTextField.setEditable(false);
currentHolderTextField.setEditable(false);
}

private void setId(int id)
{
this.idTextField.setText(String.valueOf(id));
}
private void setBookTitle(String title)
{
this.titleTextField.setText(title);
}
private void setAuthor(String author)
{
this.authorTextField.setText(author);
}
private void setPublisher(String publisher)
{
this.publisherTextField.setText(publisher);
}
private void setSubject(String subject)
{
this.subjectTextField.setText(subject);
}
private void setCategory(String category)
{
this.categoryTextField.setText(category);
}
private void setEdition(String Edition)
{
this.editionTextField.setText(Edition);
}
private void setTotalStock(int stock)
{
this.totalStockTextField.setText(String.valueOf(stock));
}
private void setAvailableStock(int availableStock)
{
this.availableStockTextField.setText(String.valueOf(availableStock));
}
private void setCurrentHolder(int currentHolder)
{
this.currentHolderTextField.setText(String.valueOf(currentHolder));
}

public void valueChanged(ListSelectionEvent event)
{
int selectedRowIndex=bookTable.getSelectedRow();
BookInterface book=findByAuthorModel.get(selectedRowIndex);
setId(book.getId());
setBookTitle(book.getTitle());
setAuthor(book.getAuthorName());
setPublisher(book.getPublisher());
setCategory(book.getCategory());
setSubject(book.getSubject());
setEdition(book.getEdition());
setAvailableStock(book.getAvailableStock());
setCurrentHolder(book.getCurrentHolder());
setTotalStock(book.getAvailableStock()+book.getCurrentHolder());
}

}
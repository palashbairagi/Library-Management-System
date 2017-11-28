package presentationLayer.magzines;

import presentationLayer.*;
import dataLayer.referenceBook.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class ViewMagzineFrame extends JFrame implements CrossButtonListener, ListSelectionListener
{
private JLabel idLabel,titleLabel,authorLabel,publisherLabel,editionLabel,totalStockLabel;
private JTextField idTextField,titleTextField,authorTextField,publisherTextField,totalStockTextField,editionTextField;
private MagzineModel magzineModel;
private JTable magzineTable;
private JScrollPane magzineScrollPane;

public ViewMagzineFrame()
{
initComponent();
setTitle("View Reference Book");
CrossButtonHandler cbh=new CrossButtonHandler(this);
addWindowListener(cbh);
setLocation(400,150);
setSize(650,400);
setResizable(false);
setVisible(true);
}

private void initTable()
{
magzineModel=new MagzineModel();
magzineTable=new JTable(magzineModel);
magzineTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
magzineTable.getColumnModel().getColumn(0).setPreferredWidth(20);
magzineTable.getColumnModel().getColumn(1).setPreferredWidth(30);
magzineTable.getColumnModel().getColumn(2).setPreferredWidth(30);
magzineTable.getColumnModel().getColumn(3).setPreferredWidth(30);
magzineTable.getColumnModel().getColumn(4).setPreferredWidth(30);
magzineTable.getSelectionModel().addListSelectionListener(this);
magzineScrollPane=new JScrollPane(magzineTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
}

private void initComponent()
{
initTable();

idLabel=new JLabel("Book Id");
titleLabel=new JLabel("Title");
authorLabel=new JLabel("Author");
publisherLabel=new JLabel("Publisher");
editionLabel=new JLabel("Edition");
totalStockLabel=new JLabel("No. of Books");

idTextField=new JTextField(10);
titleTextField=new JTextField(10);
authorTextField=new JTextField(10);
publisherTextField=new JTextField(10);
totalStockTextField=new JTextField(10);
editionTextField=new JTextField(10);

setLayout(null);
idLabel.setBounds(60,250,100,25);
titleLabel.setBounds(60,280,100,25);
authorLabel.setBounds(60,310,100,25);
publisherLabel.setBounds(340,250,100,25);
editionLabel.setBounds(340,280,100,25);
totalStockLabel.setBounds(340,310,100,25);

idTextField.setBounds(160,250,150,25);
titleTextField.setBounds(160,280,150,25);
authorTextField.setBounds(160,310,150,25);
publisherTextField.setBounds(440,250,150,25);
editionTextField.setBounds(440,280,150,25);
totalStockTextField.setBounds(440,310,150,25);

magzineScrollPane.setBounds(60,20,530,200);

add(idLabel);
add(titleLabel);
add(authorLabel);
add(publisherLabel);
add(editionLabel);
add(totalStockLabel);
add(idTextField);
add(titleTextField);
add(authorTextField);
add(publisherTextField);
add(totalStockTextField);
add(editionTextField);
add(magzineScrollPane);

disableAll();
magzineTable.setRowSelectionInterval(0,0);
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
editionTextField.setEditable(false);
totalStockTextField.setEditable(false);
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
private void setEdition(String Edition)
{
this.editionTextField.setText(Edition);
}
private void setTotalStock(int stock)
{
this.totalStockTextField.setText(String.valueOf(stock));
}

public void valueChanged(ListSelectionEvent event)
{
int selectedRowIndex=magzineTable.getSelectedRow();
ReferenceBookInterface referenceBook=magzineModel.get(selectedRowIndex);
setId(referenceBook.getId());
setBookTitle(referenceBook.getTitle());
setAuthor(referenceBook.getAuthorName());
setPublisher(referenceBook.getPublisher());
setEdition(referenceBook.getEdition());
setTotalStock(referenceBook.getTotalStock());
}

}
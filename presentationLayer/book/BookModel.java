package presentationLayer.book;

import javax.swing.table.*;
import java.util.*;

import dataLayer.book.*;

class BookModel extends AbstractTableModel
{
private ArrayList<BookInterface>books;
private BookDL bookDL;
private String title[]={"Id.","Title","Author","Publisher","Subject"};

BookModel()
{
bookDL=new BookDL();
try
{
books=bookDL.getAll();
}catch(BookException bookException)
{
books=new ArrayList<BookInterface>();
}
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(rowIndex>=0 && rowIndex<=books.size())
{
BookInterface book=books.get(rowIndex);
if(columnIndex==0)return book.getId();
if(columnIndex==1)return book.getTitle();
if(columnIndex==2)return book.getAuthorName();
if(columnIndex==3)return book.getPublisher();
if(columnIndex==4)return book.getSubject();
}
return "";
}

public int getColumnCount()
{
return title.length;
}

public int getRowCount()
{
return books.size();
}

public boolean isCellEditable()
{
return false;
}

public String getColumnName(int columnIndex)
{
return title[columnIndex];
}

public BookInterface get(int index)
{
if(index>=0 && index<books.size())
{
return books.get(index);
}
else
{
throw new ArrayIndexOutOfBoundsException("Invalid book index");
}
}

}
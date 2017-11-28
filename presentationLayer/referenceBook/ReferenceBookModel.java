package presentationLayer.referenceBook;

import javax.swing.table.*;
import java.util.*;

import dataLayer.referenceBook.*;

class ReferenceBookModel extends AbstractTableModel
{
private ArrayList<ReferenceBookInterface>referenceBooks;
private ReferenceBookDLInterface referenceBookDL;
private String title[]={"Id.","Title","Author","Publisher","Edition"};

ReferenceBookModel()
{
referenceBookDL=new ReferenceBookDL();
try
{
referenceBooks=referenceBookDL.get("Reference Book");
}catch(ReferenceBookException referenceBookException)
{
referenceBooks=new ArrayList<ReferenceBookInterface>();
}
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(rowIndex>=0 && rowIndex<=referenceBooks.size())
{
ReferenceBookInterface referenceBook=referenceBooks.get(rowIndex);
if(columnIndex==0)return referenceBook.getId();
if(columnIndex==1)return referenceBook.getTitle();
if(columnIndex==2)return referenceBook.getAuthorName();
if(columnIndex==3)return referenceBook.getPublisher();
if(columnIndex==4)return referenceBook.getEdition();
}
return "";
}

public int getColumnCount()
{
return title.length;
}

public int getRowCount()
{
return referenceBooks.size();
}

public boolean isCellEditable()
{
return false;
}

public String getColumnName(int columnIndex)
{
return title[columnIndex];
}

public ReferenceBookInterface get(int index)
{
if(index>=0 && index<referenceBooks.size())
{
return referenceBooks.get(index);
}
else
{
throw new ArrayIndexOutOfBoundsException("Invalid book index");
}
}

}
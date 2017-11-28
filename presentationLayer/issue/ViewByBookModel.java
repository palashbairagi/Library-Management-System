package presentationLayer.issue;

import javax.swing.table.*;
import java.util.*;

import dataLayer.issue.*;

class ViewByBookModel extends AbstractTableModel
{
private ArrayList<IssueInterface>issues;
private IssueDLInterface issueDL;
private String title[]={"Member ID","Name","Phone Number"};

ViewByBookModel(int bookId)
{
issueDL=new IssueDL();
try
{
issues=issueDL.getByBookId(bookId);
}catch(IssueException issueException)
{
issues=new ArrayList<IssueInterface>();
}
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(rowIndex>=0 && rowIndex<=issues.size())
{
IssueInterface issue=issues.get(rowIndex);
if(columnIndex==0)return issue.getMemberId();
if(columnIndex==1)return issue.getFirstName()+" "+issue.getLastName();
if(columnIndex==2)return issue.getPhoneNumber();
}
return "";
}

public int getColumnCount()
{
return title.length;
}

public int getRowCount()
{
return issues.size();
}

public boolean isCellEditable()
{
return false;
}

public String getColumnName(int columnIndex)
{
return title[columnIndex];
}

public IssueInterface get(int index)
{
if(index>=0 && index<issues.size())
{
return issues.get(index);
}
else
{
throw new ArrayIndexOutOfBoundsException("Invalid issue index");
}
}

}
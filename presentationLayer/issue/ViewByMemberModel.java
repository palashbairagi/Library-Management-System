package presentationLayer.issue;

import javax.swing.table.*;
import java.util.*;

import dataLayer.issue.*;

class ViewByMemberModel extends AbstractTableModel
{
private ArrayList<IssueInterface>issues;
private IssueDLInterface issueDL;
private String title[]={"Book ID","Title","Author"};

ViewByMemberModel(String memberId)
{
issueDL=new IssueDL();
try
{
issues=issueDL.getByMemberId(memberId);
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
if(columnIndex==0)return issue.getBookId();
if(columnIndex==1)return issue.getTitle();
if(columnIndex==2)return issue.getAuthorName();
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
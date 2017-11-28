package presentationLayer.find.member;

import javax.swing.table.*;
import java.util.*;

import dataLayer.member.*;

class FindByLastNameModel extends AbstractTableModel
{
private ArrayList<MemberInterface>members;
private MemberDL memberDL;
private String title[]={"Id.","First Name","Last Name","Contact Number","E-Mail ID"};

FindByLastNameModel(String lastName)
{
memberDL=new MemberDL();
try
{
members=memberDL.getByLastName(lastName);
}catch(MemberException memberException)
{
members=new ArrayList<MemberInterface>();
}
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(rowIndex>=0 && rowIndex<=members.size())
{
MemberInterface member=members.get(rowIndex);
if(columnIndex==0)return member.getId();
if(columnIndex==1)return member.getFirstName();
if(columnIndex==2)return member.getLastName();
if(columnIndex==3)return member.getPhoneNumber();
if(columnIndex==4)return member.getEmailId();
}
return "";
}

public int getColumnCount()
{
return title.length;
}

public int getRowCount()
{
return members.size();
}

public boolean isCellEditable()
{
return false;
}

public String getColumnName(int columnIndex)
{
return title[columnIndex];
}

public MemberInterface get(int index)
{
if(index>=0 && index<members.size())
{
return members.get(index);
}
else
{
throw new ArrayIndexOutOfBoundsException("Invalid member index");
}
}

}
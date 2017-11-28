package dataLayer.member;

import java.io.*;
import java.sql.*;
import java.util.*;

public class MemberDL implements MemberDLInterface
{

private static Connection getConnection() throws MemberException
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("MemberDL[Connection getConnection()]:"+classNotFoundException);//remove after testing
throw new MemberException("JdbcOdbcDriver not found.");
}

Connection connection=null;

try
{
connection=DriverManager.getConnection("jdbc:odbc:librarydsn","library","library");
}catch(SQLException sqlException)
{
System.out.println("MemberDL[Connection getConnection()]:"+sqlException);//remove after testing
throw new MemberException("Unable to connect using DSN:librarydsn");
}

return connection;
}

private java.util.Date convertSQLDateToUtilDate(java.sql.Date sqlDate)
{
return new java.util.Date(sqlDate.getYear(),sqlDate.getMonth(),sqlDate.getDate());
}

private java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate)
{
return new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
}

public int getTotalMember() throws MemberException
{
int count=0;
Connection connection;

try
{
connection= getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[int getCount()]:"+memberException);
throw memberException;
}

Statement statement=null;
ResultSet resultSet=null;

try
{
statement=connection.createStatement();
resultSet=statement.executeQuery("select count(*) as totalRecords from Member");
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("MemberDL[int getCount()]:"+sqlException);
throw new MemberException("Unable to get record count.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[int getCount()]:"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

return count;
}

public boolean exists(String id) throws MemberException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[boolean exists(int id)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select count(*) as totalRecords from Member where id=?");
preparedStatement.setString(1,id);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("MemberDL[boolean exists(int id)]:"+sqlException);
throw new MemberException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[boolean exists(int id)]"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

return count!=0;
}

public MemberInterface getById(String id) throws MemberException
{
MemberInterface member=null;
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[MemberInterface getById(int id)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from member where id=?");
preparedStatement.setString(1,id);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
member=new Member();
member.setId(id);
member.setFirstName(resultSet.getString("firstName").trim());
member.setLastName(resultSet.getString("lastName").trim());
member.setEmailId(resultSet.getString("emailId").trim());
member.setPhoneNumber(resultSet.getString("phoneNumber").trim());
member.setSex(resultSet.getString("sex").trim());
member.setAddress(resultSet.getString("address").trim());
member.setDateOfBirth(convertUtilDateToSqlDate(resultSet.getDate("dateOfBirth")));
}
}catch(SQLException sqlException)
{
System.out.println("MemberDL[MemberInterface getById(int id)]:"+sqlException);
throw new MemberException("Unable to get record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[MemberInterface getById(int id)]"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

if(member==null) throw new MemberException("Invalid Id.");

return member;
}

public java.util.ArrayList<MemberInterface>getByFirstName(String firstName) throws MemberException
{
ArrayList<MemberInterface> members=new ArrayList<MemberInterface>();
MemberInterface member;
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByFirstName(string firstName)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from member where firstName=?");
preparedStatement.setString(1,firstName);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
member=new Member();
member.setId(resultSet.getString("id"));
member.setFirstName(resultSet.getString("firstName").trim());
member.setLastName(resultSet.getString("lastName").trim());
member.setEmailId(resultSet.getString("emailId").trim());
member.setPhoneNumber(resultSet.getString("phoneNumber").trim());
member.setSex(resultSet.getString("sex").trim());
member.setAddress(resultSet.getString("address").trim());
member.setDateOfBirth(convertUtilDateToSqlDate(resultSet.getDate("dateOfBirth")));
members.add(member);
}
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByFirstName(string firstName)]: "+sqlException);
throw new MemberException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByFirstName(string firstName)]: "+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

if(members.size()==0)throw new MemberException("No member");

return members;
}

public java.util.ArrayList<MemberInterface>getByLastName(String lastName) throws MemberException
{
ArrayList<MemberInterface> members=new ArrayList<MemberInterface>();
MemberInterface member;
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByLastName(String lastName)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from member where lastName=?");
preparedStatement.setString(1,lastName);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
member=new Member();
member.setId(resultSet.getString("id"));
member.setFirstName(resultSet.getString("firstName").trim());
member.setLastName(resultSet.getString("lastName").trim());
member.setEmailId(resultSet.getString("emailId").trim());
member.setPhoneNumber(resultSet.getString("phoneNumber").trim());
member.setSex(resultSet.getString("sex").trim());
member.setAddress(resultSet.getString("address").trim());
member.setDateOfBirth(convertUtilDateToSqlDate(resultSet.getDate("dateOfBirth")));
members.add(member);
}
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByLastName(String lastName)]: "+sqlException);
throw new MemberException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getByLastName(Sring lastName)]: "+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

if(members.size()==0) throw new MemberException("No member");
return members;
}

public java.util.ArrayList<MemberInterface>getAll() throws MemberException
{
ArrayList<MemberInterface> members=new ArrayList<MemberInterface>();
MemberInterface member;
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getAll()]:"+memberException);
throw memberException;
}

Statement statement=null;
ResultSet resultSet=null;

try
{
statement=connection.createStatement();
resultSet=statement.executeQuery("select * from member");
while(resultSet.next())
{
member=new Member();
member.setId(resultSet.getString("id"));
member.setFirstName(resultSet.getString("firstName").trim());
member.setLastName(resultSet.getString("lastName").trim());
member.setEmailId(resultSet.getString("emailId").trim());
member.setPhoneNumber(resultSet.getString("phoneNumber").trim());
member.setSex(resultSet.getString("sex").trim());
member.setAddress(resultSet.getString("address").trim());
member.setDateOfBirth(resultSet.getDate("dateOfBirth"));
members.add(member);
}
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getAll()]: "+sqlException);
throw new MemberException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[ArrayList<MemberInterface>getAll()]: "+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

if(members.size()==0) throw new MemberException("No member.");

return members;
}

public void add(MemberInterface member)throws MemberException
{

if(exists(member.getId())) throw new MemberException("ID. exists");

Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[void add(MemberInterface member)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("insert into member values(?,?,?,?,?,?,?,?)");
preparedStatement.setString(1,member.getId());
preparedStatement.setString(2,member.getFirstName());
preparedStatement.setString(3,member.getLastName());
preparedStatement.setString(4,member.getEmailId());
preparedStatement.setString(5,member.getPhoneNumber());
preparedStatement.setString(6,member.getSex());
preparedStatement.setString(7,member.getAddress());
preparedStatement.setDate(8,convertUtilDateToSqlDate(member.getDateOfBirth()));
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void add(MemberInterface member)]:"+sqlException);
throw new MemberException("Unable to insert record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void add(MemberInterface member)]:"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

}

public void update(MemberInterface member) throws MemberException
{
Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[void update(Member member)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("update member set firstName=?,lastName=?,emailId=?,phoneNumber=?,sex=?,address=?,dateOfBirth=? where id=?");
preparedStatement.setString(1,member.getFirstName());
preparedStatement.setString(2,member.getLastName());
preparedStatement.setString(3,member.getEmailId());
preparedStatement.setString(4,member.getPhoneNumber());
preparedStatement.setString(5,member.getSex());
preparedStatement.setString(6,member.getAddress());
preparedStatement.setDate(7,convertUtilDateToSqlDate(member.getDateOfBirth()));
preparedStatement.setString(8,member.getId());
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void update(MemberInterface member)]:"+sqlException);
throw new MemberException("Unable to update record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void update(MemberInterface member)]"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

}

public void delete(String id) throws MemberException
{
if(exists(id)==false) throw new MemberException("ID. not exists.");

Connection connection;

try
{
connection=getConnection();
}catch(MemberException memberException)
{
System.out.println("MemberDL[void delete(int id)]:"+memberException);
throw memberException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("delete from member where id=?");
preparedStatement.setString(1,id);
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void delete(int id)]:"+sqlException);
throw new MemberException("Unable to delete record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("MemberDL[void delete(int id)]"+sqlException);
throw new MemberException("Unable to close connection to database.");
}
}

}

}
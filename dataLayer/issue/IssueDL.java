package dataLayer.issue;

import java.util.*;
import java.sql.*;
			
public class IssueDL implements IssueDLInterface
{

private static Connection getConnection() throws IssueException
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("IssueDL[Connection getConnection()]:"+classNotFoundException);//remove after testing
throw new IssueException("JdbcOdbcDriver not found.");
}

Connection connection=null;

try
{
connection=DriverManager.getConnection("jdbc:odbc:librarydsn","library","library");
}catch(SQLException sqlException)
{
System.out.println("IssueDL[Connection getConnection()]:"+sqlException);//remove after testing
throw new IssueException("Unable to connect using DSN:librarydsn");
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

public int getCount(String memberId,int bookId) throws IssueException
{
int count=0;
Connection connection;

try
{
connection =getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[int getCount()]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select count(*) as totalRecords from IssueView where MemberId=? and BookId=?");
preparedStatement.setString(1,memberId);
preparedStatement.setInt(2,bookId);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("IssueDL[int getCount()]:"+sqlException);
throw new IssueException("Unable to get record count.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[int getCount()]:"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return count;
}

public int bookCount(int id) throws IssueException
{

int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[boolean bookExists(int id)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select count(*) as totalRecords from Book where id=?");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean bookExists(int id)]:"+sqlException);
throw new IssueException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean bookExists(int id)]"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return count;
}

public int memberCount(String id) throws IssueException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[boolean memberExists(int id)]:"+issueException);
throw issueException;
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
System.out.println("IssueDL[boolean MemberExists(int id)]:"+sqlException);
throw new IssueException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean memberExists(int id)]"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return count;
}

public int bookLimit(String id) throws IssueException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[boolean memberExists(int id)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select count(*) as totalRecords from issueView where memberId=?");
preparedStatement.setString(1,id);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean MemberExists(int id)]:"+sqlException);
throw new IssueException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean memberExists(int id)]"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return count;
}

public int getAvailableStock(int id) throws IssueException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[getAvailableStock(int id)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select availableStock from Book where Id=?");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("availableStock");
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean MemberExists(int id)]:"+sqlException);
throw new IssueException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[boolean memberExists(int id)]"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return count;
}

public java.util.ArrayList<IssueInterface>getByBookId(int id) throws IssueException
{
ArrayList<IssueInterface> issues=new ArrayList<IssueInterface>();
IssueInterface issue;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByBookId(int id)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from issueView where bookId=? ");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
issue=new Issue();
issue.setBookId(resultSet.getInt("bookId"));
issue.setTitle(resultSet.getString("title").trim());
issue.setAuthorName(resultSet.getString("authorName").trim());
issue.setEdition(resultSet.getString("edition").trim());
issue.setCurrentHolder(resultSet.getInt("currentHolder"));
issue.setStock(resultSet.getInt("availableStock"));
issue.setMemberId(resultSet.getString("memberId"));
issue.setFirstName(resultSet.getString("firstName").trim());
issue.setLastName(resultSet.getString("lastName").trim());
issue.setPhoneNumber(resultSet.getString("phoneNumber").trim());
issue.setSubmissionDate(convertSQLDateToUtilDate(resultSet.getDate("submissionDate")));
issue.setIssueDate(convertSQLDateToUtilDate(resultSet.getDate("issueDate")));
issues.add(issue);
}
}catch(SQLException sqlException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByBookId(int id)]: "+sqlException);
throw new IssueException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByBookId(int id)]: "+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

return issues;
}

public IssueInterface getDate(String memberId,int bookId)throws IssueException
{
if(memberCount(memberId)==0) throw new IssueException("Invalid Member ID");
if(bookCount(bookId)==0) throw new IssueException("Invalid Book ID");
if(getCount(memberId,bookId)==0)throw new IssueException("Book is not Issued to Member");

IssueInterface issue=new Issue();
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[java.util.Date getDate(int memberId,int BookId)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select submissionDate from issueView where memberId=? and bookId=?");
preparedStatement.setString(1,memberId);
preparedStatement.setInt(2,bookId);
resultSet=preparedStatement.executeQuery();
resultSet.next();
issue.setSubmissionDate(resultSet.getDate("submissionDate"));
}catch(SQLException sqlException)
{
System.out.println("IssueDL[java.util.Date getDate(int memberId,int BookId)]:"+sqlException);
throw new IssueException("Unable to get record");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[java.util.Date getDate(int memberId,int BookId)]:"+sqlException);
throw new IssueException("Unable to close connection to database.");
}

}

return issue;
}

public java.util.ArrayList<IssueInterface>getByMemberId(String id) throws IssueException
{
ArrayList<IssueInterface> issues=new ArrayList<IssueInterface>();
IssueInterface issue;
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByMemberId(int id)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from issueView where memberId=?");
preparedStatement.setString(1,id);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
issue=new Issue();
issue.setBookId(resultSet.getInt("bookId"));
issue.setTitle(resultSet.getString("title").trim());
issue.setAuthorName(resultSet.getString("authorName").trim());
issue.setEdition(resultSet.getString("edition").trim());
issue.setMemberId(resultSet.getString("memberId"));
issue.setFirstName(resultSet.getString("firstName").trim());
issue.setLastName(resultSet.getString("lastName").trim());
issue.setPhoneNumber(resultSet.getString("phoneNumber").trim());
issue.setSubmissionDate(convertSQLDateToUtilDate(resultSet.getDate("submissionDate")));
issue.setIssueDate(convertSQLDateToUtilDate(resultSet.getDate("issueDate")));
issues.add(issue);
}
}catch(SQLException sqlException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByMemberId(int id)]: "+sqlException);
throw new IssueException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[ArrayList<IssueInterface>getByMemberId(int id)]: "+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}
return issues;
}
 
public void add(IssueInterface issue)throws IssueException
{
if(memberCount(issue.getMemberId())==0) throw new IssueException("Invalid Member ID");
if(bookCount(issue.getBookId())==0) throw new IssueException("Invalid Book ID");
if(getCount(issue.getMemberId(),issue.getBookId())>0)throw new IssueException("Book already Issued");
if(bookLimit(issue.getMemberId())==6)throw new IssueException("Member has already Issued 6 Books");
if(getAvailableStock(issue.getBookId())==0)throw new IssueException("No Book Available");

Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[void add(IssueInterface issue)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("insert into issue values(?,?,?,?)");
preparedStatement.setString(1,issue.getMemberId());
preparedStatement.setInt(2,issue.getBookId());
preparedStatement.setDate(3,convertUtilDateToSqlDate(issue.getIssueDate()));
preparedStatement.setDate(4,convertUtilDateToSqlDate(issue.getSubmissionDate()));
preparedStatement.executeUpdate();
afterIssue(issue.getBookId());
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void add(IssueInterface issue)]:"+sqlException);
throw new IssueException("Unable to insert record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void add(IssueInterface issue)]:"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

}

public void delete(String memberId,int  bookId) throws IssueException
{
Connection connection;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[void delete(int memberId,int bookId)]:"+issueException);
throw issueException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("delete from issue where memberId=? and bookId=?");
preparedStatement.setString(1,memberId);
preparedStatement.setInt(2,bookId);
preparedStatement.executeUpdate();
afterSubmit(bookId);
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void delete(int id)]:"+sqlException);
throw new IssueException("Unable to delete record");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void delete(int id)]"+sqlException);
throw new IssueException("Unable to close connection to database.");
}
}

}

public void afterIssue(int bookId)
{

Connection connection=null;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[void afterIssue(Book book)]:"+issueException);
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("update book set currentHolder=currentHolder+1,availableStock=availableStock-1 where id=?");
preparedStatement.setInt(1,bookId);
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void afterIssue(int bookId)]:"+sqlException);
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void afterIssue(int bookId)]"+sqlException);
}
}

}

public void afterSubmit(int bookId)
{

Connection connection=null;

try
{
connection=getConnection();
}catch(IssueException issueException)
{
System.out.println("IssueDL[void afterSubmit(Book book)]:"+issueException);
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("update book set currentHolder=currentHolder-1,availableStock=availableStock+1 where id=?");
preparedStatement.setInt(1,bookId);
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void afterSubmit(int bookId)]:"+sqlException);
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("IssueDL[void afterSubmit(int bookId)]"+sqlException);
}
}

}

}
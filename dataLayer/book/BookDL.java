package dataLayer.book;

import java.util.*;
import java.sql.*;
			
public class BookDL implements BookDLInterface
{

private static Connection getConnection() throws BookException
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("BookDL[Connection getConnection()]:"+classNotFoundException);
throw new BookException("JdbcOdbcDriver not found.");
}

Connection connection=null;

try
{
connection=DriverManager.getConnection("jdbc:odbc:librarydsn","library","library");
}catch(SQLException sqlException)
{
System.out.println("BookDL[Connection getConnection()]:"+sqlException);//remove after testing
throw new BookException("Unable to connect using DSN:librarydsn");
}

return connection;
}

public int getAvailableStock() throws BookException
{

int count=0;
Connection connection;

try
{
connection =getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[int getCount()]:"+bookException);
throw bookException;
}

Statement statement=null;
ResultSet resultSet=null;

try
{
statement=connection.createStatement();
resultSet=statement.executeQuery("select sum(availableStock) as totalRecords from Book");
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("BookDL[int getCount()]:"+sqlException);
throw new BookException("Unable to get record count.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[int getCount()]:"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

return count;
}

public int getBookIssued() throws BookException
{

int count=0;
Connection connection;

try
{
connection =getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[int getCount()]:"+bookException);
throw bookException;
}

Statement statement=null;
ResultSet resultSet=null;

try
{
statement=connection.createStatement();
resultSet=statement.executeQuery("select sum(currentHolder) as totalRecords from Book");
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("BookDL[int getCount()]:"+sqlException);
throw new BookException("Unable to get record count.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[int getCount()]:"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

return count;
}

public boolean exists(int id) throws BookException
{

int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[boolean exists(int id)]:"+bookException);
throw bookException;
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
System.out.println("BookDL[boolean exists(int id)]:"+sqlException);
throw new BookException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[boolean exists(int id)]"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

return count!=0;
}

public BookInterface getById(int id) throws BookException
{

BookInterface book=null;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[BookInterface getByID(int id)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from book where id=?");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
book=new Book();
book.setId(id);
book.setTitle(resultSet.getString("title").trim());
book.setAuthorName(resultSet.getString("authorName").trim());
book.setPublisher(resultSet.getString("publisher").trim());
book.setSubject(resultSet.getString("subject").trim());
book.setCategory(resultSet.getString("category").trim());
book.setEdition(resultSet.getString("edition").trim());
book.setCurrentHolder(resultSet.getInt("currentHolder"));
book.setAvailableStock(resultSet.getInt("availableStock"));
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[boolean exists(int id)]:"+sqlException);
throw new BookException("Unable to get record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[BookInterface getByID(int id)]"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

if(book==null) throw new BookException("Invalid Id.");

return book;
}

public java.util.ArrayList<BookInterface>getBySubject(String subject) throws BookException
{

ArrayList<BookInterface> books=new ArrayList<BookInterface>();
BookInterface book;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[ArrayList<BookInterface>getBySubject(String subject)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from book where subject=?");
preparedStatement.setString(1,subject);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
book=new Book();
book.setId(resultSet.getInt("id"));
book.setTitle(resultSet.getString("title").trim());
book.setAuthorName(resultSet.getString("authorName").trim());
book.setPublisher(resultSet.getString("publisher").trim());
book.setSubject(resultSet.getString("subject").trim());
book.setCategory(resultSet.getString("category").trim());
book.setEdition(resultSet.getString("edition").trim());
book.setCurrentHolder(resultSet.getInt("currentHolder"));
book.setAvailableStock(resultSet.getInt("availableStock"));
books.add(book);
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getBySubject(String subject)]: "+sqlException);
throw new BookException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getBySubject(string subject)]: "+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

if(books.size()==0) throw new BookException("No book");

return books;
}

public java.util.ArrayList<BookInterface>getByAuthor(String author) throws BookException
{

ArrayList<BookInterface> books=new ArrayList<BookInterface>();
BookInterface book;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByAuthor(String author)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from book where authorName=?");
preparedStatement.setString(1,author);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
book=new Book();
book.setId(resultSet.getInt("id"));
book.setTitle(resultSet.getString("title").trim());
book.setAuthorName(resultSet.getString("authorName").trim());
book.setPublisher(resultSet.getString("publisher").trim());
book.setSubject(resultSet.getString("subject").trim());
book.setCategory(resultSet.getString("category").trim());
book.setEdition(resultSet.getString("edition").trim());
book.setCurrentHolder(resultSet.getInt("currentHolder"));
book.setAvailableStock(resultSet.getInt("availableStock"));
books.add(book);
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByAuthor(String author)]: "+sqlException);
throw new BookException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByAuthor(String author)]: "+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

if(books.size()==0) throw new BookException("No book");

return books;
}

public java.util.ArrayList<BookInterface>getByCategory(String category) throws BookException
{

ArrayList<BookInterface> books=new ArrayList<BookInterface>();
BookInterface book;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByCategory(string category)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from book where category=?");
preparedStatement.setString(1,category);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
book=new Book();
book.setId(resultSet.getInt("id"));
book.setTitle(resultSet.getString("title").trim());
book.setAuthorName(resultSet.getString("authorName").trim());
book.setPublisher(resultSet.getString("publisher").trim());
book.setSubject(resultSet.getString("subject").trim());
book.setCategory(resultSet.getString("category").trim());
book.setEdition(resultSet.getString("edition").trim());
book.setCurrentHolder(resultSet.getInt("currentHolder"));
book.setAvailableStock(resultSet.getInt("availableStock"));
books.add(book);
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByCategory(String category)]: "+sqlException);
throw new BookException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getByCategory(string category)]: "+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

if(books.size()==0) throw new BookException("No book");

return books;
}

public java.util.ArrayList<BookInterface>getAll() throws BookException
{

ArrayList<BookInterface> books=new ArrayList<BookInterface>();
BookInterface book;
Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[ArrayList<BookInterface>getAll()]:"+bookException);
throw bookException;
}

Statement statement=null;
ResultSet resultSet=null;

try
{
statement=connection.createStatement();
resultSet=statement.executeQuery("select * from book");
while(resultSet.next())
{
book=new Book();
book.setId(resultSet.getInt("id"));
book.setTitle(resultSet.getString("title").trim());
book.setAuthorName(resultSet.getString("authorName").trim());
book.setPublisher(resultSet.getString("publisher").trim());
book.setSubject(resultSet.getString("subject").trim());
book.setCategory(resultSet.getString("category").trim());
book.setEdition(resultSet.getString("edition").trim());
book.setCurrentHolder(resultSet.getInt("currentHolder"));
book.setAvailableStock(resultSet.getInt("availableStock"));
books.add(book);
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>getAll()]: "+sqlException);
throw new BookException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[ArrayList<BookInterface>get()]: "+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

if(books.size()==0) throw new BookException("No book");

return books;
}

public void add(BookInterface book)throws BookException
{
if(exists(book.getId())) throw new BookException("ID. exists.");

Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[void add(Book book)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
preparedStatement.setInt(1,book.getId());
preparedStatement.setString(2,book.getTitle());
preparedStatement.setString(3,book.getAuthorName());
preparedStatement.setString(4,book.getPublisher());
preparedStatement.setString(5,book.getSubject());
preparedStatement.setString(6,book.getCategory());
preparedStatement.setString(7,book.getEdition());
preparedStatement.setInt(8,0);
preparedStatement.setInt(9,book.getAvailableStock());
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void add(Book book)]:"+sqlException);
throw new BookException("Unable to insert record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void add(Book book)]:"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

}

public void update(BookInterface book) throws BookException
{

Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[void update(Book book)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("update book set title=?,authorName=?,publisher=?,subject=?,category=?,edition=?,currentHolder=?,availableStock=? where id=?");
preparedStatement.setString(1,book.getTitle());
preparedStatement.setString(2,book.getAuthorName());
preparedStatement.setString(3,book.getPublisher());
preparedStatement.setString(4,book.getSubject());
preparedStatement.setString(5,book.getCategory());
preparedStatement.setString(6,book.getEdition());
preparedStatement.setInt(7,book.getCurrentHolder());
preparedStatement.setInt(8,book.getAvailableStock());
preparedStatement.setInt(9,book.getId());
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void update(Book book)]:"+sqlException);
throw new BookException("Unable to update record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void update(Book book)]"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

}

public void delete(int id) throws BookException
{
if(!exists(id)) throw new BookException("ID.  not exists.");

Connection connection;

try
{
connection=getConnection();
}catch(BookException bookException)
{
System.out.println("BookDL[void delete(Book book)]:"+bookException);
throw bookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("delete from book where id=?");
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void delete(int id)]:"+sqlException);
throw new BookException("Unable to delete record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[void delete(int id)]"+sqlException);
throw new BookException("Unable to close connection to database.");
}
}

}

}
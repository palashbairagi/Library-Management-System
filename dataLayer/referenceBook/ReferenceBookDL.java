package dataLayer.referenceBook;

import java.sql.*;
import java.util.*;

public class ReferenceBookDL implements ReferenceBookDLInterface
{

private static Connection getConnection() throws ReferenceBookException
{

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println("ReferenceBookDL[Connection getConnection()]:"+classNotFoundException);//remove after testing
throw new ReferenceBookException("JdbcOdbcDriver not found.");
}

Connection connection=null;

try
{
connection=DriverManager.getConnection("jdbc:odbc:librarydsn","library","library");
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[Connection getConnection()]:"+sqlException);//remove after testing
throw new ReferenceBookException("Unable to connect using DSN:librarydsn");
}

return connection;
}

public int getCount(String category) throws ReferenceBookException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[int getCount()]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select sum(totalStock) as totalRecords from ReferenceBook where category=?");
preparedStatement.setString(1,category);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[int getCount()]:"+sqlException);
throw new ReferenceBookException("Unable to get record count");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[int getCount()]:"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}

return count;
}

public boolean exists(int id) throws ReferenceBookException
{
int count=0;
Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[boolean exists(String category)]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select count(*) as totalRecords from ReferenceBook where id=?");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("totalRecords");
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[boolean exists(String category)]:"+sqlException);
throw new ReferenceBookException("Unable to check existence.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[boolean exists(String category)]"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}

return count!=0;
}

public ReferenceBookInterface getById(int id) throws ReferenceBookException
{

ReferenceBookInterface referenceBook=null;
Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("BookDL[BookInterface getByID(int id)]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from ReferenceBook where id=?");
preparedStatement.setInt(1,id);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
referenceBook=new ReferenceBook();
referenceBook.setId(resultSet.getInt("id"));
referenceBook.setTitle(resultSet.getString("title").trim());
referenceBook.setAuthorName(resultSet.getString("authorName").trim());
referenceBook.setPublisher(resultSet.getString("publisher").trim());
referenceBook.setCategory(resultSet.getString("category").trim());
referenceBook.setEdition(resultSet.getString("edition").trim());
referenceBook.setTotalStock(resultSet.getInt("TotalStock"));
}
}catch(SQLException sqlException)
{
System.out.println("BookDL[boolean exists(int id)]:"+sqlException);
throw new ReferenceBookException("Unable to get record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("BookDL[BookInterface getByID(int id)]"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}

if(referenceBook==null) throw new ReferenceBookException("Invalid Id.");

return referenceBook;
}


public java.util.ArrayList<ReferenceBookInterface>get(String category) throws ReferenceBookException
{

ArrayList<ReferenceBookInterface> referenceBooks=new ArrayList<ReferenceBookInterface>();
ReferenceBookInterface referenceBook;
Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[ArrayList<ReferenceBookInterface>get()]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;
ResultSet resultSet=null;

try
{
preparedStatement=connection.prepareStatement("select * from referenceBook where category=?");
preparedStatement.setString(1,category);
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
referenceBook=new ReferenceBook();
referenceBook.setId(resultSet.getInt("id"));
referenceBook.setTitle(resultSet.getString("title").trim());
referenceBook.setAuthorName(resultSet.getString("authorName").trim());
referenceBook.setPublisher(resultSet.getString("publisher").trim());
referenceBook.setCategory(resultSet.getString("category").trim());
referenceBook.setEdition(resultSet.getString("edition").trim());
referenceBook.setTotalStock(resultSet.getInt("totalStock"));
referenceBooks.add(referenceBook);
}
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[ArrayList<ReferenceBookInterface>get()]: "+sqlException);
throw new ReferenceBookException("Unable to get records.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[ArrayList<ReferenceBookInterface>get()]: "+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}

if(referenceBooks.size()==0) throw new ReferenceBookException("No Book");

return referenceBooks;
}

public void add(ReferenceBookInterface referenceBook)throws ReferenceBookException
{
if(exists(referenceBook.getId())) throw new ReferenceBookException("ID. exists.");

Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[void add(ReferenceBook referenceBook)]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("insert into referenceBook values(?,?,?,?,?,?,?)");
preparedStatement.setInt(1,referenceBook.getId());
preparedStatement.setString(2,referenceBook.getTitle());
preparedStatement.setString(3,referenceBook.getAuthorName());
preparedStatement.setString(4,referenceBook.getPublisher());
preparedStatement.setString(5,referenceBook.getCategory());
preparedStatement.setString(6,referenceBook.getEdition());
preparedStatement.setInt(7,referenceBook.getTotalStock());
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void add(ReferenceBook referenceBook)]:"+sqlException);
throw new ReferenceBookException("Unable to insert record.");
}
finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void add(ReferenceBook referenceBook)]:"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}
}

public void update(ReferenceBookInterface referenceBook) throws ReferenceBookException
{

Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[void update(ReferenceBook referenceBook)]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("update referenceBook set title=?,authorName=?,publisher=?,edition=?,totalStock=? where id=?");
preparedStatement.setString(1,referenceBook.getTitle());
preparedStatement.setString(2,referenceBook.getAuthorName());
preparedStatement.setString(3,referenceBook.getPublisher());
preparedStatement.setString(4,referenceBook.getEdition());
preparedStatement.setInt(5,referenceBook.getTotalStock());
preparedStatement.setInt(6,referenceBook.getId());
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void update(ReferenceBook referenceBook)]:"+sqlException);
throw new ReferenceBookException("Unable to update record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void update(ReferenceBook referenceBook)]"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}

}

public void delete(int id) throws ReferenceBookException
{
if(exists(id)==false) throw new ReferenceBookException("ID.  not exists.");

Connection connection;

try
{
connection=getConnection();
}catch(ReferenceBookException referenceReferenceBookException)
{
System.out.println("ReferenceBookDL[void delete(ReferenceBook ReferenceBook)]:"+referenceReferenceBookException);
throw referenceReferenceBookException;
}

PreparedStatement preparedStatement=null;

try
{
preparedStatement=connection.prepareStatement("delete from referenceBook where id=?");
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void delete(String title,String authorName,String category,String edition)]:"+sqlException);
throw new ReferenceBookException("Unable to delete record.");
}

finally
{
try
{
if(!connection.isClosed()) connection.close();
}catch(SQLException sqlException)
{
System.out.println("ReferenceBookDL[void delete(String title,String authorName,String category,String edition)]"+sqlException);
throw new ReferenceBookException("Unable to close connection to database.");
}
}
}

}
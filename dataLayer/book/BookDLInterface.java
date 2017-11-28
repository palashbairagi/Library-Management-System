package dataLayer.book;

public interface BookDLInterface
{
public int getAvailableStock() throws BookException;
public int getBookIssued() throws BookException;
public boolean exists(int id) throws BookException;
public BookInterface getById(int id) throws BookException;
public java.util.ArrayList<BookInterface>getBySubject(String subject) throws BookException;
public java.util.ArrayList<BookInterface>getByAuthor(String author) throws BookException;
public java.util.ArrayList<BookInterface>getByCategory(String category) throws BookException;
public java.util.ArrayList<BookInterface> getAll() throws BookException;
public void add(BookInterface book)throws BookException;
public void update(BookInterface book)throws BookException;
public void delete(int id)throws BookException;
}
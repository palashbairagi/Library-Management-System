package dataLayer.referenceBook;

public interface ReferenceBookDLInterface
{
public void add(ReferenceBookInterface referencebook)throws ReferenceBookException;
public void update(ReferenceBookInterface referencebook)throws ReferenceBookException;
public void delete(int id)throws ReferenceBookException;
public ReferenceBookInterface getById(int id)throws ReferenceBookException;
public java.util.ArrayList<ReferenceBookInterface> get(String category) throws ReferenceBookException;
public int getCount(String category) throws ReferenceBookException;
public boolean exists(int id) throws ReferenceBookException;
}
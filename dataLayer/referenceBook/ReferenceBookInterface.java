package dataLayer.referenceBook;

public interface ReferenceBookInterface extends java.io.Serializable
{
public void setId(int id);
public int getId();
public void setTitle(String title);
public String getTitle();
public void setAuthorName(String authorName);
public String getAuthorName();
public void setPublisher(String publisher);
public String getPublisher();
public void setCategory(String category);
public String getCategory();
public void setEdition(String edition);
public String getEdition();
public void setTotalStock(int totalStock);
public int getTotalStock();
}
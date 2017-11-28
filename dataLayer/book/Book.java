package dataLayer.book;

public class Book implements BookInterface
{
private int id;
private String title;
private String authorName;
private String publisher;
private String subject;
private String category;
private String edition;
private int currentHolder;
private int availableStock;

public void setId(int id)
{
this.id=id;
}
public int getId()
{
return this.id;
}

public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}

public void setAuthorName(String authorName)
{
this.authorName=authorName;
}
public String getAuthorName()
{
return this.authorName;
}

public void setPublisher(String publisher)
{
this.publisher=publisher;
}
public String getPublisher()
{
return this.publisher;
}

public void setSubject(String subject)
{
this.subject=subject;
}
public String getSubject()
{
return this.subject;
}

public void setCategory(String category)
{
this.category=category;
}
public String getCategory()
{
return this.category;
}

public void setEdition(String edition)
{
this.edition=edition;
}
public String getEdition()
{
return this.edition;
}

public void setCurrentHolder(int currentHolder)
{
this.currentHolder=currentHolder;
}
public int getCurrentHolder()
{
return this.currentHolder;
}

public void setAvailableStock(int availableStock)
{
this.availableStock=availableStock;
} 
public int getAvailableStock()
{
return this.availableStock;
}

}
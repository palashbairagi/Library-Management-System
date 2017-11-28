package dataLayer.referenceBook;

import java.util.*;

public class ReferenceBook implements ReferenceBookInterface
{
private int id;
private String title;
private String authorName;
private String publisher;
private String category;
private String edition;
private int totalStock;

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

public void setTotalStock(int totalStock)
{
this.totalStock=totalStock;
} 
public int getTotalStock()
{
return this.totalStock;
}

}
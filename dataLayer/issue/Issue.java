package dataLayer.issue;

import java.util.*;

public class Issue implements IssueInterface
{
private int bookId;
private String title;
private String authorName;
private String edition;
private int currentHolder;
private int stock;

private String memberId; 
private String firstName;
private String lastName;
private String phoneNumber;

private Date submissionDate;
private Date issueDate;

public void setMemberId(String memberId)
{
this.memberId=memberId;
}
public String getMemberId()
{
return this.memberId;
}

public void setFirstName(String firstName)
{
this.firstName=firstName;
}
public String getFirstName()
{
return this.firstName;
}

public void setLastName(String lastName)
{
this.lastName=lastName;
}
public String getLastName()
{
return this.lastName;
}

public void setPhoneNumber(String phoneNumber)
{
this.phoneNumber=phoneNumber;
}
public String getPhoneNumber()
{
return this.phoneNumber;
}

public void setBookId(int bookId)
{
this.bookId=bookId;
}
public int getBookId()
{
return this.bookId;
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

public void setStock(int stock)
{
this.stock=stock;
} 
public int getStock()
{
return this.stock;
}

public void setSubmissionDate(Date submissionDate)
{
this.submissionDate=submissionDate;
}
public Date getSubmissionDate()
{
return this.submissionDate;
}
public void setIssueDate(Date issueDate)
{
this.issueDate=issueDate;
}
public Date getIssueDate()
{
return this.issueDate;
}

}
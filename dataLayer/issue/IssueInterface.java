package dataLayer.issue;

import java.util.*;

public interface IssueInterface extends java.io.Serializable
{
public void setMemberId(String memberId);
public String getMemberId();
public void setFirstName(String firstName);
public String getFirstName();
public void setLastName(String lastName);
public String getLastName();
public void setPhoneNumber(String phoneNumber);
public String getPhoneNumber();
public void setBookId(int bookId);
public int getBookId();
public void setTitle(String title);
public String getTitle();
public void setAuthorName(String authorName);
public String getAuthorName();
public void setEdition(String edition);
public String getEdition();
public void setCurrentHolder(int currentHolder);
public int getCurrentHolder();
public void setStock(int stock);
public int getStock();
public void setIssueDate(Date issueDate);
public Date getIssueDate();
public void setSubmissionDate(Date submissionDate);
public Date getSubmissionDate();
}
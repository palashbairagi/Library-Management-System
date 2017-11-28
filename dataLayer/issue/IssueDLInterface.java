package dataLayer.issue;

import java.util.*;

public interface IssueDLInterface extends java.io.Serializable
{
public int getCount(String memberId,int bookId) throws IssueException;
public IssueInterface getDate(String memberId,int bookId)throws IssueException;
public java.util.ArrayList<IssueInterface>getByBookId(int id) throws IssueException;
public java.util.ArrayList<IssueInterface>getByMemberId(String id) throws IssueException;
public void add(IssueInterface issue)throws IssueException;
public void delete(String memberId,int  bookId) throws IssueException;
}
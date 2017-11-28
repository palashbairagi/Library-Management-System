package dataLayer.issue;

import java.io.*;

public class IssueException extends Exception implements Serializable
{
private String message;

public IssueException(String message)
{
this.message=message;
}

public String getMessage()
{
return message;
}

public String toString()
{
return this.message;
}

}
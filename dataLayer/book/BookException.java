package dataLayer.book;

import java.io.*;

public class BookException extends Exception implements Serializable
{

private String message;

public BookException(String message)
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
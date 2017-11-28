package dataLayer.referenceBook;

import java.io.*;

public class ReferenceBookException extends Exception implements Serializable
{
private String message;

public ReferenceBookException(String message)
{
this.message=message;
}

public String getMessage()
{
return this.message;
}

public String toString()
{
return this.message;
}

}
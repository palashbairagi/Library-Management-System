package dataLayer.member;

import java.io.*;

public class MemberException extends Exception implements Serializable
{
private String message;

public MemberException(String message)
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
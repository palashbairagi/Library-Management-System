package dataLayer.member;

import java.util.*;

public interface MemberInterface extends java.io.Serializable
{
public void setId(String id);
public String getId();
public void setFirstName(String firstName);
public String getFirstName();
public void setLastName(String lastName);
public String getLastName();
public void setEmailId(String emailId);
public String getEmailId();
public void setPhoneNumber(String phoneNumber);
public String getPhoneNumber();
public void setSex(String sex);
public String getSex();
public void setAddress(String address);
public String getAddress();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
}
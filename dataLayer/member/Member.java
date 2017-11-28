package dataLayer.member;

import java.util.*;

public class Member implements MemberInterface
{
private String id;
private String firstName;
private String lastName;
private String emailId;
private String phoneNumber;
private String sex;
private String address;
private Date dateOfBirth;

public void setId(String id)
{
this.id=id;
}
public String getId()
{
return this.id;
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

public void setEmailId(String emailId)
{
this.emailId=emailId;
}
public String getEmailId()
{
return this.emailId;
}

public void setPhoneNumber(String phoneNumber)
{
this.phoneNumber=phoneNumber;
}
public String getPhoneNumber()
{
return this.phoneNumber;
}

public void setSex(String sex)
{
this.sex=sex;
}
public String getSex()
{
return this.sex;
}

public void setAddress(String address)
{
this.address=address;
}
public String getAddress()
{
return this.address;
}

public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public Date getDateOfBirth()
{
return this.dateOfBirth;
}

}
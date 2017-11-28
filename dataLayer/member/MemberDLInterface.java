package dataLayer.member;

public interface MemberDLInterface
{
public int getTotalMember() throws MemberException;
public boolean exists(String id) throws MemberException;
public MemberInterface getById(String id)throws MemberException;
public java.util.ArrayList<MemberInterface>getByFirstName(String firstName) throws MemberException;
public java.util.ArrayList<MemberInterface>getByLastName(String lastName) throws MemberException;
public java.util.ArrayList<MemberInterface> getAll() throws MemberException;
public void add(MemberInterface member)throws MemberException;
public void update(MemberInterface member)throws MemberException;
public void delete(String id)throws MemberException;
}
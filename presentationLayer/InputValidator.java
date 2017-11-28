package presentationLayer;

import javax.swing.*;
import java.text.*;

public class InputValidator
{

public static boolean isTextFieldEmpty(JFrame container,JTextField
textField, String message)
{
boolean empty=false;
if(textField.getText().trim().length()==0)
{
empty=true;
JOptionPane.showMessageDialog(container,message);
textField.requestFocus();
}
return empty;
}

public static boolean isTextFieldNotEmpty(JFrame container,JTextField
textField, String message)
{
boolean empty=false;
if(textField.getText().trim().length()!=0)
{
empty=true;
JOptionPane.showMessageDialog(container,message);
textField.requestFocus();
}
return empty;
}

public static boolean isInteger(JFrame container,JTextField textField
,String message)
{
boolean integer=true;
try
{
Integer.parseInt(textField.getText().trim());
}catch(NumberFormatException numberFormatException)
{
integer=false;
JOptionPane.showMessageDialog(container,message);
textField.requestFocus();
}
return integer;
}

public static boolean isValidEmailId(JFrame container,JTextField textField,String message)
{
int p;
boolean isValid=true;
String emailId=textField.getText().trim();

if(emailId.indexOf("@")==-1)
{
isValid=false;
JOptionPane.showMessageDialog(container,message);
return isValid;
}
else
{
p=emailId.indexOf("@");

if(emailId.indexOf(".",p+1)==-1)
{
isValid=false;
JOptionPane.showMessageDialog(container,message);
}
}

return isValid;
}

public static boolean isValidDDMMYYYYDate(JFrame container,JTextField
textField,String message)
{
boolean valid=true;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
try
{
java.util.Date date=simpleDateFormat.parse(textField.getText().trim());
String string=date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
valid=string.equals(textField.getText().trim());
}catch(ParseException parseException)
{
valid=false;
}
if(!valid)
{
JOptionPane.showMessageDialog(container,message);
textField.requestFocus();
}
return valid;
}

public static boolean isInValidRange(JFrame container,JTextField textField,long 
minimum, long maximum,String message)
{
boolean valid=true;
try
{
long x=Long.parseLong(textField.getText().trim());
if(x<minimum || x>maximum)valid=false;
}catch(NumberFormatException numberFormatException)
{
valid=false;
}
if(!valid)
{
JOptionPane.showMessageDialog(container,message);
textField.requestFocus();
}
return valid;
}

}
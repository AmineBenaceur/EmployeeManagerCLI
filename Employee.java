import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Employee{

private String firstName;
private String lastName;
private String department;
private String shift;
private String salary;
private String contact;


public Employee(String first, String last, String dept, String sh, String sal,  String cont ){
	firstName = first;
	lastName = last;
	shift = sh;
	salary = sal;
	department = dept;
	contact = cont;
}

public String toString(){
	return firstName + "\t " + lastName + "\t " + department + "\t " + shift + "\t " +  salary + "\t " + contact ;

}

public String getShift(){
	return shift.trim();
}

public static Employee fromString(String input ){
	String[] parts= input.split("\t");
	String first = parts[0];
	String last = parts[1];
	String dept = parts[2];
	String sh = parts[3];
	String sal = parts[4];
	String cont = parts[5];

	Employee e = new Employee(first,last,dept,sh,sal,cont);
	return e;

}

public String getFirstName(){
	return firstName.trim();
}
public String getLastName(){
	return lastName.trim();
}

public static void main(String[] args) {
	//test constructor
	Employee moody = new Employee("moody", "elsahar" , "debauchery", "m", "10000000", "4032836668");
	// System.out.println(moody.toString());
	// //test fromString
	// System.out.println("enter a new employee in one string");
	//   Scanner scanner = new Scanner(new InputStreamReader(System.in));
 //      String input = scanner.nextLine();
 //      Employee entered = fromString(input);
 //      System.out.println(entered.toString());




}
//https://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html
//https://www.journaldev.com/791/java-string-split
// https://javarevisited.blogspot.com/2012/12/how-to-read-input-from-command-line-in-java.html#ixzz5i6pxVwQT


}
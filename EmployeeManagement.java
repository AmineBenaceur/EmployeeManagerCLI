import java.io.*;
import java.util.*;

public class EmployeeManagement{
	
	public DataIO data;
	public LoginManager loginmanager;

	ArrayList<Employee> employees;
	Scanner scanner;
	String line = "";
	boolean validLogin;
	String fileName = "";




	public EmployeeManagement(){
		scanner = new Scanner(new InputStreamReader(System.in));
		employees = new ArrayList<Employee>();
		validLogin =false;
		loginmanager = new LoginManager();
	}
	// connect to DATAIO object to be able to store and retrieve data from .txt file
	public void connectToFile(){
		data = new DataIO(fileName,employees);
		data.populateEmployees();
		System.out.println("IMPORTING EMPLOYEES FROM .TXT FILE! ");

	}



	public void manageLogin(){
		validLogin = false;
		while(!validLogin){
		System.out.println("Enter an admin username and password seperated by a space. Or enter 'R' to register ");
		line =  scanner.nextLine();
		
		if (line.split(" ").length > 2 )
			System.out.println("That entry is too long! ");
			
		
		if (line.split(" ").length == 2){
			//System.out.println("TWO WORDS");
			String[] parts = line.split(" ");
			if(loginmanager.checkAdmin(parts[0], parts[1])){
				validLogin = true;
				fileName = parts[0].trim() + ".txt";
				System.out.println("LOGGING ON! ");
				
			}
		}

		if ( (line.split(" ").length == 1) && (line.trim().equalsIgnoreCase("R"))){
			boolean validAdmin = false;
			String adminUser = "";
			String adminPass = "";
			String[] newParts = { "",""};
			validLogin = true;

			while (!validAdmin){
				System.out.println("Enter your NEW admin username and password seperated by a space ");
				line = scanner.nextLine();
				newParts = line.split(" ");
				if (newParts.length != 2 ){
					System.out.println(" only enter a username and password seperated by a space. No more, no less :)");
				}
				else{
					validAdmin = true;
					fileName = newParts[0].trim() + ".txt";
					loginmanager.addAdmin(newParts[0],newParts[1]);
					System.out.println("LOGGING ON! ");
				}
			}

			
			
		}
		
	}
			System.out.println("LOGGING ON! ");
	}
	public void addNewEmployee(){
		System.out.println("enter FIRST name With no spaces. ");
		String first = scanner.nextLine().trim();
		
		System.out.println("enter LAST name With no spaces. ");
		String last = scanner.nextLine().trim();

		System.out.println("enter DEPARTMENT of employee with no spaces. ");
		String dept = scanner.nextLine().trim();

		String sh = "";
		boolean correct = false;
		while(!correct ){
			System.out.println("enter SCHEDULED shift times of employee... ");	
			System.out.println(" 'm -> morning shift");
			System.out.println(" 'e' -> evening shift" );
			System.out.println(" 'o' -> overnight shift");
			sh = scanner.nextLine().trim();

			if( sh.equalsIgnoreCase("m") || sh.equalsIgnoreCase("e")|| sh.equalsIgnoreCase("o")){
				correct = true;
			}
		}
		System.out.println("Enter monthly salary of employee ");
		String sal = scanner.nextLine().trim();

		System.out.println("Enter contact phone # of employee ");
		String con = scanner.nextLine().trim();

		Employee emp = new Employee(first, last, dept, sh, sal,con);
		employees.add(emp);
	}
	public void endSession(){
		data.saveEmployees();
		System.out.println("SAVING EMPLOYEES TO .TXT FILE");
		System.out.println("LOGGING OFF! ");
		scanner.close();
	}

	public void printAllEmployees(){
		
		for(int i=0; i < employees.size() ; i++ ){
			System.out.println(employees.get(i).toString());
		}
	}

	public static void displayOptions(){
		System.out.println("-------------OPTIONS------------");
		System.out.println("type....");
		System.out.println("'COUNT'		-> to display number of employees under your administration.");
		System.out.println("'VIEW'		-> to display ALL employees info under your administration.");
		System.out.println("'ADD'		-> to add an employee under your administration.");
		System.out.println("'SEARCH'		-> to add an employee under your administration.");
		System.out.println("'REMOVE'	-> to Remove an employee from your administration.");
		System.out.println("'AVAIL'		-> to check if an employee is currently working");
		System.out.println("'QUIT'		-> EXIT");
		System.out.println("--------------------------------");
	}

	//MAIN MENU FUNCTION
	public void run(){
		boolean stop = false;

		while(!stop){
			displayOptions();
			line = scanner.nextLine();

			System.out.println();
			if (line == null ){
				System.out.println("please enter valid command!");
			}

			if (line.equalsIgnoreCase("COUNT")){
				if(employees == null || employees.size() == 0 ){
					System.out.println("LIST IS EMPTY");
				}else{
					
					System.out.println("There are: " + employees.size() + "  employees under your admin. ");
					
				}

			}
			if (line.equalsIgnoreCase("VIEW")){
				printAllEmployees();
				
			}
			if (line.equalsIgnoreCase("ADD")){
				addNewEmployee();
				
			} 
			if (line.equalsIgnoreCase("REMOVE")){
				Employee found;	
				
					System.out.println("enter an employees first name and last name - seperated by a space ");
					line = scanner.nextLine();
					String[] parts = line.split(" ");

					if(parts.length == 2){
						found = searchEmployee(parts[0],parts[1]);
						employees.remove(found);
					}
				
			} 

			if (line.equalsIgnoreCase("SEARCH")){
				System.out.println("enter an employees first name and last name - seperated by a space ");
				line = scanner.nextLine();
			    String[] parts = line.split(" ");
				Employee em = searchEmployee(parts[0],parts[1]);
				if(em != null ){
					System.out.println("FOUND SUCCESSFULLY! : ");
					System.out.println(em.toString());
					System.out.println();
				}
				
			} 

			if (line.equalsIgnoreCase("AVAIL")){
				Schudule s = new Schudule();
				System.out.println("Enter the first and last name of the employee seperated by a space");
				line = scanner.nextLine();
				String[] parts = line.split(" ");

				Employee emp = searchEmployee(parts[0],parts[1]);

				if(emp == null){
					System.out.println("EMPLOYEE NOT FOUND");
				}else{
				boolean isworking = s.isWorking(emp );

					if(isworking == true ){
						System.out.println("YES! " + parts[0] + " is On the Clock");
					}
					else{
						System.out.println("NO! " + parts[0] + " is not at work. ");
					}
				}
				
			} 

			if (line.equalsIgnoreCase("QUIT")){
				stop = true;
				
			} 

			System.out.println();

			}


	}

	Employee searchEmployee(String f, String l){
		Employee e;
		if (employees == null){
			System.out.println("list is empty!");
		}
		for(int i=0 ; i< employees.size() ; i++ ){
			e = employees.get(i);
			if( (f.equalsIgnoreCase(e.getFirstName())) && l.equalsIgnoreCase(e.getLastName()) ){
				return e;
			}
		}
		
		System.out.println("EMPLOYEE NOT FOUND!");
		return null;
	}



	public static void main(String[] args) {
	// System.out.println(moody.toString());
	// //test fromString
	// System.out.println("enter a new employee in one string");
	//   Scanner scanner = new Scanner(new InputStreamReader(System.in));
 //      String input = scanner.nextLine();
 //      Employee entered = fromString(input);
 //      System.out.println(entered.toString());

		EmployeeManagement manager = new EmployeeManagement();

		manager.manageLogin();

		manager.connectToFile();

		manager.run();

		manager.endSession();
		
		System.out.println("hooray!");

	}



}


//https://javarevisited.blogspot.com/2012/12/how-to-read-input-from-command-line-in-java.html
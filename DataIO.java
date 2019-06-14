import java.io.*;
import java.util.ArrayList;

public class DataIO{


private ArrayList employees;
public String fileName;

public FileReader fileReader;
public BufferedReader bufferedReader;
FileWriter fileWriter;
BufferedWriter bufferedWriter;

public DataIO(String file, ArrayList list){
	fileName = file;
	employees = list;
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

// public static void addEmployee(Employee e){
//     employees.add(e);
// }

	public static void main(String[] args) {
		ArrayList<Employee> myList = new ArrayList<Employee>();
		
		// data.populateEmployees();
        Employee john = new Employee("John", "Long", "HR", "m", "500", "403-988-6681");
         Employee dick = new Employee("dick", "short", "accounting", "n", "788", "403-888-7777");
         Employee roger = new Employee("roger", "that", "engineering", "m", "19349", "403-908-0031");
         
         myList.add(john);
         myList.add(dick);
         myList.add(roger);

         DataIO data = new DataIO("Bank.txt", myList);
         data.saveEmployees();
	}


    // public static void printList(ArrayList list){
    //    Employee e; 
    //     for(int i = 0 ; i < list.size() ; i++ ){
    //         e = (Employee) list.get(i);
    //         System.out.println(e.toString());
    //     }
    // }

public void populateEmployees(){
    		 // The name of the file to open.
            System.out.println("WERE READING FROM: " + fileName);
            // This will reference one line at a time
            String line = null;
             try {
                // FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader(fileName);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                	fromString(line);
                    //System.out.println(line);
                    //System.out.println(fromString(line));
                    if (employees == null){
                        System.out.println("EMPLOYEEES ARE NULL D:");
                    }
                    employees.add(fromString(line));
                }   

                Employee e; 
                for(int i = 0 ; i < employees.size() ; i++ ){
                e = (Employee) employees.get(i);
                System.out.println(e.toString());
            }


                // Always close files.
                bufferedReader.close();         
            }
            catch(FileNotFoundException ex) {
                System.out.println(  "Unable to open file '" + fileName + "'");                
            }
            
            catch(IOException ex) {
                System.out.println( "Error reading file '"  + fileName + "'");                   
                ex.printStackTrace();
         }
            catch(NullPointerException npe){
                System.out.println("oops.");
            }

            
}

    public void saveEmployees(){


        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            //write out each employee line by line
            for(int i =0; i < employees.size() ; i++ ){
            bufferedWriter.write(employees.get(i).toString());
            bufferedWriter.newLine();
            }
            
            

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }





    }




}

//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
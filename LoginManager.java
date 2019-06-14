import java.io.*;

public class LoginManager{

private String fileName = "admins.txt";



	// EmployeeManager(){

	// }

	void addAdmin(String user, String pass){
	
		try{
			File file = new File(fileName);
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write( "\n" + user + "\t" + pass + "");
			br.close();
			fr.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	boolean checkAdmin(String user, String pass){
		user = user.trim();
		pass = pass.trim();
		   // The name of the file to open.

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts = line.split("\t");
                if ( (user.equals( parts[0].trim()) ) && (pass.equals(parts[1].trim())) ){
                System.out.println("LOGGED IN " + user + " SUCCESSFULY! ");
                return true;
            	}
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }

       return false;
	}

	public static void main(String[] args) {
		LoginManager man = new LoginManager();
		man.addAdmin("Amine", "1017");
		man.addAdmin("adam", "5958");

		System.out.println("SHOULD BE FALSE ---- " + man.checkAdmin("doesnt", "work"));
		System.out.println("SHOULD BE TRUE ---- " + man.checkAdmin("Amine", "1017"));


	}




}
//https://www.journaldev.com/881/java-append-to-file#java-append-content-to-existing-file-using-bufferedwriter
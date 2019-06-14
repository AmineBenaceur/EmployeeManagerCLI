import java.time.LocalTime;




public class Schudule{

String morningShift = "m";// MORING 
String eveningShift = "e";// EVENING
String overnightShift = "o";// OVERNIGHT

LocalTime startMorning = LocalTime.parse("08:00");
LocalTime startEvening = LocalTime.parse("16:00");
LocalTime startOvernight = LocalTime.parse("00:00");

		
	// public static void main(String[] args) {
	//   LocalTime time = LocalTime.parse("09:22");
	//   System.out.println(isBetween(time, LocalTime.of(10, 0), LocalTime.of(18, 0)));

	//   Employee kyrie = new Employee("Kyrie", "Irving" , "NBA", "o", "6777777777", "888-777-9999");
	//   Employee mkyrie = new Employee("Kyrie", "Irving" , "NBA", "m", "6777777777", "888-777-9999");

	//   System.out.println("SHOULD BE TRUE--- " + isWorking(kyrie));
	// 	System.out.println("SHOULD BE False--- " + isWorking(mkyrie));
	// }


	public  boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
	  return !candidate.isBefore(start) && !candidate.isAfter(end);  // Inclusive.
	}

	public   boolean isWorking(Employee e){


		LocalTime local = LocalTime.now(); // currentTime

		if (e.getShift().equalsIgnoreCase("m")){
			if (isBetween(local,startMorning,startEvening ) ){
				return true;
			}

		}
		if (e.getShift().equalsIgnoreCase("e")){
			if(isBetween(local,startEvening,startOvernight )){
				return true;
			}
		}
		if (e.getShift().equalsIgnoreCase("o")){
			if(isBetween(local,startOvernight,startMorning )){
				return true;
			}
		}

		return false;
	}


}

//https://stackoverflow.com/questions/2309558/time-comparison
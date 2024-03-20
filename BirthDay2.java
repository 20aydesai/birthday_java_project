
//Ayan Desai
//MidTermProject
//10/29/2021
package MidTermProject;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BirthDay2 {

	public static void main(String[] args) throws IOException, ParseException {

		int numberofClasses = 0;
		int numberofStudents = 0;
		int lineNo = 0;
		int numberOfQueries = 0;
		String studentDetail[][] = new String[numberofStudents][5];
		String queryDetail[][] = new String[numberOfQueries][2];
		int getIndex = -1;
		int z = -1;
		int queryMon = 0;
		int queryDate = 0;
		int studentMon = 0;
		int studentDate = 0;
		LocalDate studentBirthDate = null;
		long difference = 0L;
		long diff = 0L;
		int classNo = 0;
		List<StudentBirthday> birthdays = new ArrayList<StudentBirthday>();
		MonthDay mdStudent = null;
		MonthDay mdQuery = null;
		LocalDate dtQuery = null;
		LocalDate dtStudent = null;
		String closestStudent = null;
		//String queryMonDate = null;
		//LocalDate queryBirthDate = null;
		//int queryLast = 0;
		//int currentStudentLast = 0;
		int prevStudentLast = 0;
		long lDifference = 0L;
		long lDiff = 0L;
		String qLname = null;
		String qFname = null;
		int queryLast = 0;
		int currentStudentLast = 0;

		try {
			// open file to read
			Scanner scanner = new Scanner(new File("C:\\Users\\rupes\\Downloads\\birthdayInputFile.txt"));
			lineNo++;

			if (lineNo == 1) {
				numberofClasses = Integer.valueOf(scanner.nextLine());
				lineNo++;

			System.out.println("numberofClasses " + numberofClasses);

			}

			// while ((classNo <=numberofClasses) ) {
			while (classNo <=5) {
			    classNo++;
				
				System.out.println();
				System.out.println("Class # " + classNo+":");
				System.out.println();
				numberofStudents = Integer.valueOf(scanner.nextLine());
				lineNo++;

				// System.out.println("numberofStudents " + numberofStudents);

				// populate StudentDetail array
				int a = 0; // record number for student detail array
				studentDetail = new String[numberofStudents][5];

				while (numberofStudents != 0) {

					String currentLine = null;
					currentLine = scanner.nextLine();
					lineNo++;
					studentDetail[a] = currentLine.split(" ");
					numberofStudents--;
					a++;

				} // while numberofstudents <>0

				// System.out.println("StudentDetail length "+studentDetail.length);

				// Sorted StudentDetail Array using Insertion sort

				int key, key1, j;

				for (int i = 1; i < studentDetail.length; i++) {
					studentMon = Month.valueOf(studentDetail[i][2]).getValue();// month
					studentDate = Integer.parseInt(studentDetail[i][3]);
					studentBirthDate = LocalDate.parse("2020" + "-"
							+ (studentMon < 10 ? "0" + studentMon : String.valueOf(studentMon)) + "-"
							+ (studentDate < 10 ? "0" + studentDate : String.valueOf(studentDate)));
							
					// System.out.println("studentBirthdatei" + studentBirthDate);


					key = studentBirthDate.getMonthValue() * 100 + studentBirthDate.getDayOfMonth();
					// System.out.println("KEYI:" + key);

					String[] keyRow = studentDetail[i];
					j = i - 1;
					studentMon = Month.valueOf(studentDetail[j][2]).getValue();// month
					studentDate = Integer.parseInt(studentDetail[j][3]);
					studentBirthDate = LocalDate.parse("2020" + "-"
							+ (studentMon < 10 ? "0" + studentMon : String.valueOf(studentMon)) + "-"
							+ (studentDate < 10 ? "0" + studentDate : String.valueOf(studentDate)));
					// System.out.println("studentBirthdateJ" + studentBirthDate);
					key1 = studentBirthDate.getMonthValue() * 100 + studentBirthDate.getDayOfMonth();

					while ((j >= 0) && (key1 > key)) {

						studentDetail[j + 1] = studentDetail[j];
						j = j - 1;
					}

					studentDetail[j + 1] = keyRow;
				}

				
				 //printing a student detail 
				for (int i = 0; i < studentDetail.length; ++i) {
				  
				  for(int k = 0; k < studentDetail[i].length; ++k) {
				  
				  
				  System.out.println("studentDetail "+studentDetail[i][k]); } }
				 

				numberOfQueries = Integer.valueOf(scanner.nextLine());

				// System.out.println("numberOfQueries " + numberOfQueries);

				// populate a queryDetail array
				int b = 0; // record number for query detail array
				queryDetail = new String[numberOfQueries][2];
				while (numberOfQueries != 0) {

					String currentLine1 = null;
					currentLine1 = scanner.nextLine();
					// lineNo++;
					queryDetail[b] = currentLine1.split(" ");
					numberOfQueries--;

					b++;
				} // while number of queries <>0

				/*
				 * //printing query detail; for (int x =0; x < queryDetail.length; ++x) {
				 * for(int y = 0; y < queryDetail[x].length; ++y) {
				 * System.out.println("queriesDetail "+ queryDetail[x][y]); } }
				 */

				// For each query student find the closest student
				for (int q = 0; q < queryDetail.length; q++) {
				    difference = 9223372036854775807L;
					diff = 9223372036854775807L;
					z = -1;
					closestStudent = null;
					getIndex = -1;
					mdQuery = null;
					dtQuery = null;
					mdStudent = null;
					dtStudent = null;
					//queryBirthDate = null;
					// studentBirthDate = new Date();
					lDifference = 9223372036854775807L;
					lDiff = 9223372036854775807L;
					queryLast = 0;
					currentStudentLast = 0;
					prevStudentLast = 0;
					qFname = null;
					qLname = null;
				

					// System.out.println("querydetail " + queryDetail[q][0].toString());
				
					birthdays = new ArrayList<StudentBirthday>();

					for (int s = 0; s < studentDetail.length; s++) {

						StudentBirthday s1 = new StudentBirthday(
								(MonthDay.of(Month.valueOf(studentDetail[s][2]).getValue(),
										Integer.parseInt(studentDetail[s][3]))),
								studentDetail[s][0], studentDetail[s][1]);
						birthdays.add(s1); //Add each student information into birthdays list

						getIndex = Arrays.asList(studentDetail[s][0]).indexOf(queryDetail[q][0]);
						// System.out.println("S"+s);
						if (getIndex == 0) {
							z = s;
						}

					}
					// if (getIndex == 0) {

					// System.out.println("Z "+z);
					queryMon = Month.valueOf(studentDetail[z][2]).getValue();// month
					queryDate = Integer.parseInt(studentDetail[z][3]);
					// queryMonDate = (String.valueOf(queryMon) +""+ String.valueOf(queryDate));

					mdQuery = (MonthDay.of(queryMon,queryDate));
					dtQuery = mdQuery.atYear(2021);
					qFname = studentDetail[z][0].toString();
					qLname = studentDetail[z][1].toString();

					// System.out.println("queryMonDate" + mdQuery);//monthDay

					// queryBirthDate = LocalDate.parse(studentDetail[z][4] +"-"+(queryMon < 10 ?
					// "0"+queryMon : String.valueOf(queryMon))+ "-"+ (queryDate < 10 ?
					// "0"+queryDate : String.valueOf(queryDate)));
					// System.out.println("queryBirthdate" + queryBirthDate);
					
					//create s2 of type StudentBirthday for a query student
					StudentBirthday s2 = new StudentBirthday(mdQuery, studentDetail[z][0].toString(),
							studentDetail[z][1].toString());

			
					/*
					 * System.out.println("First"+s2.firstName);
					 * System.out.println("Last"+s2.lastName); System.out.println(s2.monDay);
					 */
					// }

					// System.out.println("QueryDate:"+dtQuery);

					for (int i = 0; i < birthdays.size(); i++) {

						if (birthdays.get(i).firstName.equals(s2.firstName)
								&& birthdays.get(i).lastName.equals(s2.lastName)
								&& birthdays.get(i).monDay.equals(s2.monDay)) 
						{
							birthdays.remove(i);
							// System.out.println("ReMOVED");
						}
					}

					//sort the birthdays list by monthday, lastname and firstname
					Collections.sort(birthdays, new Comparestudents());
				/*	
					for (int i = 0; i < birthdays.size(); i++) {

						if (birthdays.get(i).firstName.equals(s2.firstName)
								&& birthdays.get(i).lastName.equals(s2.lastName)
								&& birthdays.get(i).monDay.equals(s2.monDay)) 
						{
							z = i; //query record is in brithday list
							// System.out.println("ReMOVED");
						}
					}
			*/	
					// System.out.println("After sorting");
					// for (StudentBirthday studentbirthday : birthdays) {
					// System.out.println(studentbirthday.firstName+ " "+ studentbirthday.lastName);
					// }

				/*	
					 //printing birthdays list 
					for (int i = 0; i < birthdays.size(); i++) {
					 
					  System.out.println("BDs: "+ birthdays.get(i).monDay );
					  System.out.println("BDs: "+ birthdays.get(i).lastName );
					  System.out.println("BDs: "+ birthdays.get(i).firstName );
					  
					  }
				*/	

					
					for (int i = 0; i < birthdays.size(); i++) {

						mdStudent = MonthDay.parse(birthdays.get(i).monDay.toString());
						// System.out.println ("md:"+mdStudent);

						dtStudent = mdStudent.atYear(2021);

						// System.out.println(dtQuery.compareTo(dtStudent));
								
						diff = ChronoUnit.DAYS.between(dtQuery, dtStudent);//difference in days between querystudent and studentdate
					
						//	System.out.println("Difference:"+ difference);
						//	System.out.println("Diff**"+diff);

						if (Math.abs(diff) < Math.abs(difference)) {
							difference = diff;
							closestStudent = (birthdays.get(i).firstName.toString() + ' '
									+ birthdays.get(i).lastName.toString());
						//	System.out.println("closeststudent: "+ closestStudent);

						} else if (Math.abs(diff) > Math.abs(difference)) {
							// Not to do anything

						}

						else if (Math.abs(diff) == Math.abs(difference)) {
			
							 difference = diff;
							 closestStudent = (birthdays.get(i-1).firstName.toString() + ' '+ birthdays.get(i-1).lastName.toString());
												
							  //check the last name
							  
							//  System.out.println("CHECK Lastname");
							/*  
							  
							  char[] query = qLname.toCharArray(); 
							  char[] current = birthdays.get(i).lastName.toCharArray(); 
							  char[] previous = birthdays.get(i-1).lastName.toCharArray();
							  
							  int minLength = Math.min(Math.min(current.length, previous.length),query.length);
							  
							  for(int n = 0; n < minLength; n++) {
								  if (current[n] != previous[n]) {
							  queryLast = Integer.valueOf(qLname.charAt(n)); 
							  currentStudentLast = Integer.valueOf(birthdays.get(i).lastName.charAt(n));
							  prevStudentLast =  Integer.valueOf(birthdays.get(i-1).lastName.charAt(n));
							  break; 
							  } 
							  }
							  
							  
							  //System.out.println(queryLast); //System.out.println(currentStudentLast);
							  //System.out.println(prevStudentLast);
							  
							  lDiff = (queryLast - currentStudentLast);
							  
							  if (Math.abs(lDiff) < Math.abs(queryLast - prevStudentLast)) { 
								  lDifference =	lDiff; 
								  }
							 
							  closestStudent = (birthdays.get(i).firstName.toString()+' '+birthdays.get(i).lastName.toString()); 
							  
							 */

						} // elseif

					} // for each brithdays record

					System.out.println(closestStudent + " has the closest birthday to " + queryDetail[q][0].toString()
							+ " " + queryDetail[q][1].toString());
				} // end q query loop

				numberofClasses++;
			//	classNo++;
			} // number of classes loop
		}


		catch (FileNotFoundException ex) {
			ex.printStackTrace();

		}

	}
}

class StudentBirthday {
	MonthDay monDay;
	String firstName;
	String lastName;

	// The studentbirthday constructor
	public StudentBirthday(MonthDay MonDay, String First, String Last) {
		this.monDay = MonDay;
		this.firstName = First;
		this.lastName = Last;
	}
}

class Comparestudents implements Comparator<StudentBirthday> {
	public int compare(StudentBirthday o1, StudentBirthday o2) {
		int value1 = o1.monDay.compareTo(o2.monDay);
		if (value1 == 0) {
			int value2 = o1.lastName.compareTo(o2.lastName);
			if (value2 == 0) {
				return o1.firstName.compareTo(o2.firstName);
			} else {
				return value2;
			}
		}
		return value1;
	}

}

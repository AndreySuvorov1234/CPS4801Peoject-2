package domain;
import java.util.ArrayList;

public class Course 
{
	private String number = null;
	private String name = null;
	private int maxNumbOfStudents;
	private ArrayList<Instructor> instructors;
	public Course(String number, String name, ArrayList<Instructor> instructors, int maxNumbOfStudents)
	{
		this.name = name;
		this.number =number;
		this.instructors = instructors;
		this.maxNumbOfStudents = maxNumbOfStudents;
	}
	public String getNumber() {return number;	}
	public String getName() {return name;}
	public int getMaxNumbOfStudents() {return maxNumbOfStudents;}	
	public ArrayList<Instructor> getInstructors() {return instructors;	}
	public String toString(){return name;}
}

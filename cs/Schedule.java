package cs;
import java.util.ArrayList;
import domain.Department;
import domain.Class;

public class Schedule 
{
	private ArrayList<Class> classes;
	private boolean isFitnessChanged = true;
	private double fitness = -1;
	private Data data;
	private int classNumb =0;
	private int numbOfConflicts = 0;
	public Schedule(Data data)
	{
		this.data=data;
		classes = new ArrayList<Class>(data.getNumberOfClasses());
	}
	
	public Schedule initialize()
	{
		new ArrayList<Department>(data.getDepts()).forEach(dept -> 
		{
			dept.getCourses().forEach(course->
			{
				Class newClass = new Class(classNumb++, dept, course);
				newClass.setMeetingTime(data.getMeetingTimes().get( (int) (data.getMeetingTimes().size() * Math.random())));
				newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
				newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size()* Math.random())));
				classes.add(newClass);
			});
		});
		return this;
	}
	private double calculateFitness()
	{
		numbOfConflicts = 0;
		classes.forEach(x->
		{
			if(x.getRoom().getSeatingCapacity() <x.getCourse().getMaxNumbOfStudents())
				numbOfConflicts++;
			classes.stream().filter(y->classes.indexOf(y) >= classes.indexOf(x)).forEach(y ->
			{
				if(x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId())
				{
					if(x.getRoom() == y.getRoom()) 
						numbOfConflicts++;
					if(x.getInstructor() == y.getInstructor()) 
						numbOfConflicts++;
				}
			});
		});
		return 1/(double)(numbOfConflicts+1);
	}
	
	public Data getData()  {return data;}
	public int getNumbOfConflicts(){return numbOfConflicts;}
	public double getFitness()
	{
		if(isFitnessChanged == true)
		{
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}
	public ArrayList<Class> getClasses()
	{
		isFitnessChanged = true;
		return classes;
	}
	public String toString()
	{
		String returnVal = new String();
		for(int i=0; i<classes.size()-1; i++ )
			returnVal += classes.get(i) + ",";
		returnVal +=classes.get(classes.size()-1);
		return returnVal;
	}
	
}

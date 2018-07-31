package cs;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population 
{
	private ArrayList<Schedule> schedules;
	
	public Population(){};
	public Population(int size, Data data)
	{
		schedules = new ArrayList<Schedule>(size);
		IntStream.range(0,  size).forEach(x -> schedules.add(new Schedule(data).initialize()));	
	}
	public Population sortByFitness()
	{
		schedules.sort((schedule1, schedule2) ->
		{
			int returnVal=0;
			if(schedule1.getFitness() > schedule2.getFitness()) 
				returnVal = -1;
			else if (schedule1.getFitness() < schedule2.getFitness()) 
				returnVal=1;
			return returnVal;
		});
		return this;
	}
	
	
	
	public ArrayList<Schedule> getSchedules() {return this.schedules;}
}

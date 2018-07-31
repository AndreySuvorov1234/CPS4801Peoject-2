package cs;

public class Driver 
{
	public static final int POPULATION_SIZE =9;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.9;
	public static final int TOURNAMENT_SELECTION_SIZE= 3;
	public static final int NUMB_OF_ELITE_SCHEDULES = 1;
	
	private int scheduleNumb =0;
	private Data data;
	
	public static void main(String[] args) 
	{
		Driver driver = new Driver();
		driver.data = new Data();
		int generationNumber = 0;
		driver.printAvailableData();
		System.out.println("> Generation # "+generationNumber);
		System.out.print("Schedule #   |							");
		System.out.print("Classes [dept,class,roo,instructor,meeting-time]	");
		System.out.println("				   | Fitness | Conflicts");
		System.out.print("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------");
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
		boolean keepgoing = true;
		/*Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
		population.getSchedules().forEach(s -> System.out.println("       "+driver.scheduleNumb++ +
				"     | "+ s + "| " + String.format("%.5f", s.getFitness()) +
				" | "+ s.getNumbOfConflicts()));
		*/
		Population population = new Population();
		while(keepgoing)
		{
		
			population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
			if(population.getSchedules().get(0).getNumbOfConflicts() ==0)
			{
				keepgoing = false;
			}
		}
		population.getSchedules().forEach(schedule -> System.out.println("       "+driver.scheduleNumb++ +
				"     | "+ schedule + "| " + String.format("%.5f", schedule.getFitness()) +
				" | "+ schedule.getNumbOfConflicts()));
	}
	
	
	private void printAvailableData()
	{
		System.out.println("Availale Departments =>");
		data.getDepts().forEach(x -> System.out.println("name: "+x.getName()+", courses: "+x.getCourses()));
		
		System.out.println("\nAvailable Courses =>");
		data.getCourses().forEach(x -> System.out.println("course #: "+x.getNumber()+", name: "+x.getName()
			+", max # of students: "+ x.getMaxNumbOfStudents()+", instructors:" +x.getInstructors()));
		
		System.out.println("\nAvailable Rooms =>");
		data.getRooms().forEach(x -> System.out.println("room # "+x.getNumber()+", max seating capacity: "+x.getSeatingCapacity()));
		
		System.out.println("\nAvailable Instructors =>");
		data.getInstructors().forEach(x-> System.out.println("id: "+ x.getId()+", name: "+x.getName()));
		
		System.out.println("\nAvalaible Meeting Times =>");
		data.getMeetingTimes().forEach(x-> System.out.println("id: "+x.getId()+", Meeting Time: "+x.getTime()));
		System.out.print("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------");
	}

}

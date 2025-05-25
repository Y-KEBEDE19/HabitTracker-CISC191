/**
* Lead Author(s):
* @author yididk; student ID
* @author Yididya Kebede: 5550169151
* <<Add additional lead authors here>>
* 
* Github Link: https://github.com/Y-KEBEDE19/HabitTracker-CISC191
*
* Other Contributors:
* Full name; student ID or contact information if not in class
* <<Add additional contributors (mentors, tutors, friends) here, with contact information>>
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
* 
* Resources: 
* 
* Splitting CSV Files: https://labex.io/tutorials/java-how-to-split-csv-lines-correctly-421487
* 
* 
*
* <<Add more references here>>
*
* Version: 2025-05-12
*/
package com.habitTracker.cisc191;

/**
 * Purpose: The responsibility of Habit is to define the functionality of a Habit is
 *
 * Habit is-a Object
 * Habit is ...
 */
public class Habit
{
	private String name; // Habit Has-A name 
	private int streakCount; // Habit Has-A Streak Account
	private boolean completedToday; // Habit Has-A completedToday boolean ? 
	
	public Habit(String name, boolean completedToday)
	{
		this.name = name; 
		this.streakCount = 0; 
		this.completedToday = completedToday; 
		
		
	}
	
	// Setting up Setters & Getters for the different variables 
	
	// Purpose: get the name of the Habit
	public String getName()
	{
		return name;
	}
	
	// Purpose: set the name of the Habit
	public void setName(String name)
	{
		this.name = name;  
	}
	
	// Purpose get the streak count
	public int getStreakCount()
	{
		return streakCount; 
	}
	
	// Purpose: set the Streak Count from the loaded File
	private void setStreakCount(int streakCount)
	{
		this.streakCount = streakCount; 
	}
	
	
	// Purpose: Increment streak count if HabitIsCompleted
	public void isCompleted(boolean isHabitCompleted)
	{
		if (isHabitCompleted)
		{
			streakCount++;
		}
	}
	
	
	// Purpose: Determines Format for Habit being saved to File
	@Override
	public String toString()
	{
		return name + "," + streakCount + completedToday; 
		
	}
	
	// Purpose: Matches Info From File back into Habit 
	public static Habit fromString(String csv)
	{
		String [] parts = csv.split(",");
		Habit habit = new Habit( parts[0], Boolean.parseBoolean(parts[2]));
		habit.setStreakCount(Integer.parseInt(parts[1]));
		return habit;
		
	}
	
	
	
	
	
}

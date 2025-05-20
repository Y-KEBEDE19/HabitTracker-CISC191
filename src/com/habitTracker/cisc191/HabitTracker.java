/**
* Lead Author(s):
* @author yididk; student ID
* @author Full name; student ID
* <<Add additional lead authors here>>
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
* <<Add more references here>>
*
* Version: 2025-05-12
*/
package com.habitTracker.cisc191;
import java.util.ArrayList;

/**
 * Purpose: The responsibility of HabitTracker is to keep track of Habits
 *
 * HabitTracker is-a class
 * HabitTracker is 
 */
public class HabitTracker
{
	private ArrayList<Habit> habits = new ArrayList<>(); // HabitTracker Has-A array of Habits
	
	// Purpose: Add a Habit to the Habit Tracker 
	
	public void addHabit(Habit newHabit)
	{
		// Adding a habit
		habits.add(newHabit);
	}
	
	// Purpose: remove Habit from Habit Tracker
	public void removeHabit(int index)
	{
		habits.remove(index);
	}
	
	// Purpose: return the List from Habit Tracker for File Management 
	
	public ArrayList<Habit> getHabits()
	{
		return habits;
	}
	
	
}

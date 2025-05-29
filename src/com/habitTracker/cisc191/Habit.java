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
* Learning How to Track Date-Time: 
* 
* https://docs.oracle.com/javase/8/docs/api/java/util/NavigableSet.html
* https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html
* https://www.geeksforgeeks.org/treeset-in-java-with-examples/
* https://www.w3schools.com/java/java_date.asp
* https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
* 
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

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Purpose: The responsibility of Habit is to define the functionality of a Habit is
 *
 * Habit is-a Object
 * Habit is ...
 */
public class Habit
{
	private String name; // Habit Has-A name 
	private final NavigableSet<LocalDate> completedDates = new TreeSet<>(); // Keep Track of Time -> Helps with Maintaining streak
	
	
	
	public Habit(String name)
	{
		this.name = name; // name of habit
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
		
	// Purpose: Increment streak count if HabitIsCompleted
	public void markCompletedToday()
	{
		completedDates.add(LocalDate.now()); // Add today's date to the set
	}
	
	// Purpose: Not Completed For Today
	public void unmarkCompleteToday()
	{
		completedDates.remove(LocalDate.now()); // remove today's date
	}
	
	// Purpose: Determines Format for Habit being saved to File
	@Override
    public String toString() {
        String joinedDates = completedDates.stream() // all dates
            .map(LocalDate::toString) // change format of each date to something readable
            .collect(Collectors.joining(";")); // join string with semi-colons
        return name + "|" + joinedDates; 
    }
	
	/**
	 * Purpose: Amount of Days Tracked 
	 * @return
	 */
	public int getDaysTracked()
	{
		// TODO Auto-generated method stub
		return completedDates.size(); // the size of the set is the # of days tracked
	}
	
	// Purpose: Matches Info From File back into Habit 
	public static Habit fromString(String line) {
        String[] parts = line.split("\\|", 2);
        Habit habit = new Habit(parts[0]);
        if (parts.length > 1 && !parts[1].isEmpty()) {
            String[] dates = parts[1].split(";");
            for (String date : dates) {
                habit.completedDates.add(LocalDate.parse(date));
            }
        }
        return habit;
    }
	
	/**
	 * Purpose: returns the longest streak
	 * @return
	 */
	public int getLongestStreak() 
	{
        int longest = 0, current = 1;
        LocalDate prev = null;
        for (LocalDate date : completedDates) {
            if (prev != null && date.equals(prev.plusDays(1))) {
                current++;
            } else if (prev != null) {
                longest = Math.max(longest, current);
                current = 1;
            }
            prev = date;
        }
        return Math.max(longest, current); // Whatever is higher either current streak or longest streak before
    }

	/**
	 * Purpose: Find the current streak up to today
	 * @return
	 */
	 public int getCurrentStreak() 
	 {
	        if (completedDates.isEmpty()) return 0;
	        int streak = 0;
	        LocalDate cursor = LocalDate.now();
	        
	        // Calculates Current Streak
	        while (completedDates.contains(cursor)) {
	            streak++;
	            cursor = cursor.minusDays(1);
	        }
	        return streak;
	 }
	
}

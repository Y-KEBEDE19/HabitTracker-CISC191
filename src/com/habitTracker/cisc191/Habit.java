/**
 * Lead Author(s):
 * 
 * @author Yididya Kebede: 5550169151
 *         <<Add additional lead authors here>>
 * 
 *         Github Link: https://github.com/Y-KEBEDE19/HabitTracker-CISC191
 *
 *         References:
 *         Morelli, R., & Walde, R. (2016).
 *         Java, Java, Java: Object-Oriented Problem Solving
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *         Resources:
 * 
 *         Learning How to Track Date-Time:
 * 
 * 
 *         References:
 * 
 *         LocalDate:
 *         Oracle. (2023, October 4). LocalDate (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
 * 
 *         TreeSet:
 *         Oracle. (2025, April 5). TreeSet (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html
 * 
 *         NavigableSet:
 *         Oracle. (2025, April 5). NavigableSet (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/NavigableSet.html
 * 
 *         Stream:
 *         Oracle. (2025, April 5). Stream (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * 
 *         Oracle. (2025, April 5). Collectors (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
 * 
 *         Oracle. (2025, April 5). Function (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
 * 
 *         Set:
 *         Oracle. (2025, April 5). Set (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
 * 
 *         String Methods:
 * 
 *         Oracle. (2024, January 8). String (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 * 
 *         <<Add more references here>>
 *
 *         Version: 2025-05-12
 */
package com.habitTracker.cisc191;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Purpose: The responsibility of Habit is to define the functionality of a
 * Habit is
 *
 * Habit is-a Object
 * Habit is ...
 */
public class Habit
{
	private String name; // Habit Has-A name
	private final NavigableSet<LocalDate> completedDates = new TreeSet<>(); // Keep
																			// Track
																			// of
																			// Time
																			// ->
																			// Helps
																			// with
																			// Maintaining
																			// streak

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
	public String toString()
	{
		String joinedDates = completedDates.stream() // all dates
				.map(LocalDate::toString) // change format of each date to
											// something readable
				.collect(Collectors.joining(";")); // join string with
													// semi-colons
		return name + "|" + joinedDates;
	}

	/**
	 * Purpose: Amount of Days Tracked
	 * 
	 * @return
	 */
	public int getDaysTracked()
	{
		// TODO Auto-generated method stub
		return completedDates.size(); // the size of the set is the # of days
										// tracked
	}

	// Purpose: Matches Info From File back into Habit
	public static Habit fromString(String line)
	{
		String[] parts = line.split("\\|", 2);
		Habit habit = new Habit(parts[0]);
		if (parts.length > 1 && !parts[1].isEmpty())
		{
			String[] dates = parts[1].split(";");
			for (String date : dates)
			{
				habit.completedDates.add(LocalDate.parse(date));
			}
		}
		return habit;
	}

	/**
	 * Purpose: returns the longest streak
	 * 
	 * @return
	 */
	public int getLongestStreak()
	{
		int longest = 0, current = 1;
		LocalDate prev = null;
		for (LocalDate date : completedDates)
		{
			if (prev != null && date.equals(prev.plusDays(1)))
			{
				current++;
			}
			else if (prev != null)
			{
				longest = Math.max(longest, current);
				current = 1;
			}
			prev = date;
		}
		return Math.max(longest, current); // Whatever is higher either current
											// streak or longest streak before
	}

	/**
	 * Purpose: Find the current streak up to today
	 * 
	 * @return
	 */
	public int getCurrentStreak()
	{
		if (completedDates.isEmpty()) return 0;
		int streak = 0;
		LocalDate cursor = LocalDate.now();

		// Calculates Current Streak
		while (completedDates.contains(cursor))
		{
			streak++;
			cursor = cursor.minusDays(1);
		}
		return streak;
	}

}

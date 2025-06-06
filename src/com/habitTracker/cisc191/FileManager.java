/**
 * Lead Author(s):
 * 
 * @author yididk; student ID
 * @author Full name; student ID
 *         <<Add additional lead authors here>>
 *
 *         Other Contributors:
 *         Full name; student ID or contact information if not in class
 *         <<Add additional contributors (mentors, tutors, friends) here, with
 *         contact information>>
 *
 *         References:
 *         Morelli, R., & Walde, R. (2016).
 *         Java, Java, Java: Object-Oriented Problem Solving
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         <<Add more references here>>
 *
 *         Version: 2025-05-12
 */
package com.habitTracker.cisc191;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Purpose: The responsibility of FileManagment is to Manage the Habits with a
 * Efficient File System
 *
 * FileManagment is-a class
 * FileManagment is ...
 */
public class FileManager
{

	private HabitTracker tracker = new HabitTracker(); // File Manager Has-A
														// HabitTracker object

	private File myFile; // File Manager Has-A File

	public FileManager(HabitTracker habitTracker, String fileName)
	{
		// Receiving Habit Tracker Object
		tracker = habitTracker;

		// Naming the File
		myFile = new File(fileName);
	}

	// Store habits into File -> Each Individual Habit is in one line
	public void save() throws IOException
	{
		try (PrintWriter out = new PrintWriter(new FileWriter(myFile)))
		{
			for (Habit habit : tracker.getAllHabits())
			{
				out.println(habit.toString());
			}
		}
	}

	// Purpose: Load Info from File back into Habits
	public void load() throws IOException, FileNotFoundException
	{
		tracker.clearAllHabits(); // resetting the model

		if (!myFile.exists())
		{
			throw new FileNotFoundException(myFile.getName());
		}
		try (Scanner sc = new Scanner(myFile))
		{
			while (sc.hasNextLine())
			{
				String line = sc.nextLine().trim();
				if (!line.isEmpty())
				{
					Habit h = Habit.fromString(line);
					tracker.addHabit(h);
				}
			}
		}
	}
}
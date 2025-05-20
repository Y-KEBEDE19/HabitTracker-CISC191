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
* Version: 2025-05-14
*/
package com.habitTracker.cisc191;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Purpose: The responsibility of HabitTrackerGUI is ...
 *
 * HabitTrackerGUI is-a ...
 * HabitTrackerGUI is ...
 */
public class HabitTrackerGUI extends JFrame
{

	private HabitTracker trackerModel = new HabitTracker(); // HabitTrackerGUI Has-A model -> trackerModel
	
	// Title & Instructions 
	private JPanel instructions; // GUI Has-A Title Panel
	private JLabel instructionsLabel; // GUI Has-A Title Label
	
	// Add Habits Button
	private JPanel addHabitPanel; // GUI Has-A addHabit Panel
	private JButton addHabit; // GUI Has-A addHabit Button
	
	// Save & Load From FileManager Button:
	private JPanel saveAndLoadPanel; // GUI Has-A save&LoadPanel
	private JButton saveHabits; // GUI Has- A saveHabits Button
	private JButton loadHabits; // GUI Has- A loadHabits Button

	
	public  HabitTrackerGUI(HabitTracker trackerModel)
	{
		this.trackerModel = trackerModel; 
		
		
		initFrame(); // Initial Frame Setup
		initComponenets(); // Initial Components 
		layoutComponents(); // Layout Components
		
		
	}
	
	private void initFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void initComponenets()
	{
		// Adding Instructions / Title
		instructions = new JPanel();
		instructionsLabel = new JLabel("Welcome to Habity");
		
		instructions.add(instructionsLabel);
		
		
		// Add Habit's Button: 
		addHabit = new JButton();
		addHabitPanel = new JPanel(); 
		
		
		addHabit.setSize(50, 50);
		addHabit.setText("Add Habit"); // Giving Button a Title
		addHabitPanel.add(addHabit); // Adding Button to Panel
		
		// Save & Load Habits Buttons: 
		
		saveAndLoadPanel = new JPanel(); 
		saveHabits = new JButton(); 
		loadHabits = new JButton(); 
		
		saveHabits.setText("Save Habits"); // Titling Button
		loadHabits.setText("Load Habits"); // Titling Button
		saveAndLoadPanel.add(saveHabits); // Adding Button to Panel
		saveAndLoadPanel.add(loadHabits); // Adding Button to Panel
		
		
	}
	
	private void layoutComponents()
	{
		setLayout(new BorderLayout());
		
		add(instructions, BorderLayout.NORTH); // Adding Title in Position NORTH
		
		// Layout of AddHabits 
				
		addHabitPanel.setBorder(BorderFactory.createEmptyBorder(20,200,20,20)); // Specifying the position
		add(addHabitPanel, BorderLayout.WEST); // Adding addHabit Button to Position WEST
		
		
		// Layout of Save & Load Buttons: 
		
		saveAndLoadPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,200));
		add(saveAndLoadPanel, BorderLayout.EAST); // Adding Save & Load Habits to Position EAST

		
	}
	
	
	/**
	 * Starts the game
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		new HabitTrackerGUI(new HabitTracker());
			    
	}
}

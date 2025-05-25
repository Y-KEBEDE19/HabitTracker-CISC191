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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


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
	
	// When AddHabit Button is clicked: 
	
	private final JPanel habitListPanel = new JPanel(); //GUI Has-A habitsAdded Panel -> Displays all current Habits
	private final JScrollPane listScroll = new JScrollPane(habitListPanel); // Scrolling Panel -> Contains List of Habits
	

	
	public  HabitTrackerGUI(HabitTracker trackerModel)
	{
		this.trackerModel = trackerModel; 
				
		initComponenets(); // Initial Components 
		initListiners(); // Action listener components 
		layoutComponents(); // Layout Components
		
		
		// Initial Setup: 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	    setLocationRelativeTo(null); // center on screen (optional)
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
		
		// Changing Orientation of Habit List From Horizontal -> Vertical: 
		
		habitListPanel.setLayout(new BoxLayout(habitListPanel, BoxLayout.Y_AXIS));
		
		// Do the Same with the Scrolling: 
	
		listScroll.setViewportView(habitListPanel);
		listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Setting Size
		listScroll.setPreferredSize(new Dimension(200, 400));		
		
	}
	
	private void initListiners()
	{
		// Checking if AddHabit Button is clicked
		addHabit.addActionListener(e -> openAddHabitForm());
		
		
	}
	
	/**
	 * Purpose: To open a form to fill out Details about Habit being Added. 
	 * @return
	 */
	private void openAddHabitForm()
	{
		JPanel form = new JPanel(new GridLayout(2,2,5,5)); // Create A Form
		JTextField nameFld = new JTextField(); // Create a Name Field
		JTextField freqFld = new JTextField();  // Delete Later -> Asks Frequency 
		
		form.add(new JLabel("Habit Name")); // Asks for Habit Name
		form.add(nameFld); // Stores the name in nameFld
		form.add(new JLabel("Frequency"));
		form.add(freqFld); 
		
		int result = JOptionPane.showConfirmDialog(
				this,
				form,
				"New Habit",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE
				);
		
		if (result == JOptionPane.OK_OPTION)
		{
			String name = nameFld.getText().trim();
			Boolean isCompletedToday = false; 
			
			trackerModel.addHabit(new Habit(name, isCompletedToday)); // Adding Habit to the HabitTracker 
			
			System.out.println(name);
			addHabit(name); 	
		}
	}

	/**
	 * Purpose: Add Habits Created to Frame
	 * @param name
	 */
	private void addHabit(String name)
	{
		
		  JPanel card = new JPanel(new BorderLayout(5,5)); // Create a Habit Storing Card
		  card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Set Color
		  card.setMaximumSize(new Dimension(200, 60)); // Set Maximum Size

		  JLabel title = new JLabel(name); // Set the Name of the Habit to User Input
		  title.setFont(title.getFont().deriveFont(Font.BOLD)); 
		  card.add(title, BorderLayout.NORTH);

		  habitListPanel.add(card);
		  
		  // only these two lines here:
		  habitListPanel.revalidate();
		  habitListPanel.repaint();
	}

	private void layoutComponents()
	{
		setLayout(new BorderLayout());
		
		// Title of Application: 
		
		add(instructions, BorderLayout.NORTH); // Adding Title in Position NORTH
		
		// Layout of Adding Habits 
		
		JPanel westContainer = new JPanel(new BorderLayout()); // Everything that is going to be on the West Part of the Layout
				
		westContainer.setBorder(BorderFactory.createEmptyBorder(20,200,20,20)); // Specifying the Position
		westContainer.add(addHabitPanel, BorderLayout.NORTH); // Adding HabitButton Panel to the North part of West Container 
		westContainer.add(listScroll, BorderLayout.CENTER); // Adding List of Habits to Center of West Container 
				
		add(westContainer, BorderLayout.WEST); // Adding addHabit Button to Position WEST
		
		
		// Layout of Save & Load Buttons: 
		
		saveAndLoadPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,200)); // Specifying the Position
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

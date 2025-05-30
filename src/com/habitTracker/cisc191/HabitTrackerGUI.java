/**
 * Lead Author(s):
 * 
 * @author Yididya Kebede; 5550169151
 *
 *         References:
 *         Morelli, R., & Walde, R. (2016).
 *         Java, Java, Java: Object-Oriented Problem Solving
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *         Additional References:
 *
 *         References
 *         Oracle. (2025, April 5). Color (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html
 * 
 *         Oracle. (2025, April 5). Component (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/Component.html
 * 
 *         Oracle. (2025, April 5). Dimension (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/Dimension.html
 * 
 *         Oracle. (2025, April 5). GridLayout (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/GridLayout.html
 * 
 *         Oracle. (2025, April 5). ItemEvent (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/event/ItemEvent.html
 * 
 *         Oracle. (2025, April 5). MouseAdapter (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseAdapter.html
 * 
 *         Oracle. (2025, April 5). MouseEvent (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseEvent.html
 * 
 *         Oracle. (2025, April 5). BorderFactory (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/BorderFactory.html
 * 
 *         Oracle. (2025, April 5). Box (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/Box.html
 * 
 *         Oracle. (2025, April 5). ImageIcon (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/ImageIcon.html
 * 
 *         Oracle. (2025, April 5). JCheckBox (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/JCheckBox.html
 * 
 *         Oracle. (2025, April 5). JOptionPane (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
 * 
 *         Oracle. (2025, April 5). JScrollPane (Java platform SE 8 ).
 *         https://docs.oracle.com/javase/8/docs/api/javax/swing/JScrollPane.html
 *
 * 
 * 
 *
 *
 *         <<Add more references here>>
 *
 *         Version: 2025-05-14
 */
package com.habitTracker.cisc191;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.border.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Purpose: The responsibility of HabitTrackerGUI is ...
 *
 * HabitTrackerGUI is-a ...
 * HabitTrackerGUI is ...
 */
public class HabitTrackerGUI extends JFrame
{

	private HabitTracker trackerModel = new HabitTracker(); // HabitTrackerGUI
															// Has-A model ->
															// trackerModel
	private FileManager fileManager; // GUI Has-A fileManager
	private String lastFileName = null; // Name of Last File Saved

	// Title & Instructions
	private JPanel title; // GUI Has-A Title Panel
	private ImageIcon logo = new ImageIcon(
			getClass().getResource("/Images/habity.png")); // Logo Image
	private JLabel titleLabel; // GUI Has-A Title Label

	// Add Habits Button
	private JPanel addHabitPanel; // GUI Has-A addHabit Panel
	private JButton addHabit; // GUI Has-A addHabit Button
	private ImageIcon addHabitImg = new ImageIcon(
			getClass().getResource("/Images/addButton2.png")); // habitButton
																// Image
	private JPanel detailPanel = new JPanel(); // When clicking on a habit it
												// brings up a detail panel
	private Habit currentlyShowingHabit; // Current Habit whose details are
											// being shown

	// Save & Load From FileManager Button:
	private JPanel saveAndLoadPanel; // GUI Has-A save&LoadPanel
	private JButton saveHabits; // GUI Has- A saveHabits Button
	private ImageIcon saveIcon = new ImageIcon(
			getClass().getResource("/Images/saveButton.png")); // Save Button
																// Image
	private JButton loadHabits; // GUI Has- A loadHabits Button
	private ImageIcon loadIcon = new ImageIcon(
			getClass().getResource("/Images/loadButton.png")); // load Button
																// Image

	// When AddHabit Button is clicked:

	private final JPanel habitListPanel = new JPanel(); // GUI Has-A habitsAdded
														// Panel -> Displays all
														// current Habits
	private final JScrollPane listScroll = new JScrollPane(habitListPanel); // Scrolling
																			// Panel
																			// ->
																			// Contains
																			// List
																			// of
																			// Habits

	public HabitTrackerGUI(HabitTracker trackerModel)
	{
		this.trackerModel = trackerModel;

		initComponenets(); // Initial Components
		initListiners(); // Action listener components
		layoutComponents(); // Layout Components

		// Initial Setup:
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null); // centering
		// setSize(1000,1000);
		setVisible(true);
	}

	private void initComponenets()
	{
		// Adding Instructions / Title
		title = new JPanel(); //
		titleLabel = new JLabel(logo);
		title.add(titleLabel);
		// instructions.setPreferredSize(new Dimension(50,50));

		// Add Habit's Button:
		addHabit = new JButton(addHabitImg);
		addHabitPanel = new JPanel();

		addHabit.setSize(50, 50);
		addHabitPanel.add(addHabit); // Adding Button to Panel

		// Save & Load Habits Buttons:

		saveAndLoadPanel = new JPanel();
		saveHabits = new JButton(saveIcon); // Insert Image of Save Icon into
											// Save Habits
		loadHabits = new JButton(loadIcon); // Insert Image of Load Icon into
											// Load Habits

		saveAndLoadPanel.add(saveHabits); // Adding Button to Panel
		saveAndLoadPanel.add(loadHabits); // Adding Button to Panel

		// Changing Orientation of Habit List From Horizontal -> Vertical:

		habitListPanel
				.setLayout(new BoxLayout(habitListPanel, BoxLayout.Y_AXIS));

		// Adding the Analytics Panel
		detailPanel = new JPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

		// Do the Same with the Scrolling:

		listScroll.setViewportView(habitListPanel);
		listScroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		listScroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Setting Size
		listScroll.setPreferredSize(new Dimension(200, 400));

	}

	private void initListiners()
	{
		// Checking if AddHabit Button is clicked
		addHabit.addActionListener(e -> openAddHabitForm());

		// Checking if saveHabit Button is clicked
		saveHabits.addActionListener(e -> saveHabit());

		// Checking if loadHabit Button is clicked:

		loadHabits.addActionListener(e -> loadHabitForm());
	}

	/**
	 * Purpose: To open a form to fill out Details about Habit being Added.
	 * 
	 * @return
	 */
	private void openAddHabitForm()
	{
		JPanel form = new JPanel(new GridLayout(2, 2, 5, 5)); // Create A Form
		JTextField nameFld = new JTextField(); // Create a Name Field

		form.add(new JLabel("Habit Name")); // Asks for Habit Name
		form.add(nameFld); // Stores the name in nameFld

		int result = JOptionPane.showConfirmDialog(this, form, "New Habit",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			String name = nameFld.getText().trim();
			Habit addedHabit = new Habit(name);
			trackerModel.addHabit(addedHabit); // Adding Habit to Tracker
			addHabitCard(addedHabit);
		}
	}

	/**
	 * Purpose: Add Habits Created to Frame
	 * 
	 * @param name
	 */
	private void addHabitCard(Habit habit)
	{

		JCheckBox check = new JCheckBox(); // CheckBox
		JButton removeBtn = new JButton("x"); // removeBtn
		currentlyShowingHabit = habit; 

		JPanel card = new JPanel(new BorderLayout(5, 5)); // Create a Habit
															// Storing Card
		card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Set
																			// Color
		card.setMaximumSize(new Dimension(200, 60)); // Set Maximum Size

		JLabel title = new JLabel(habit.getName()); // Set the Name of the Habit
													// to User Input
		title.setFont(title.getFont().deriveFont(Font.BOLD));
		card.add(title, BorderLayout.NORTH);

		// Action Listeners:

		removeBtn.addActionListener(evt -> {
			// remove from model
			trackerModel.removeHabit(habit); // remove habit from tracker
			
			clearDetails(); // Details removed since Habit is removed 
			
			// remove from UI
			habitListPanel.remove(card);
			habitListPanel.revalidate();
			habitListPanel.repaint();
			// saving changes to most recent save
			try
			{
				fileManager.save();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"Failed to save habits:\n" + e.getMessage(),
						"Save Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		check.addItemListener(evt -> // if CheckBox is clicked mar & Unmark
										// habit completion
		{
			if (evt.getStateChange() == ItemEvent.SELECTED)
			{
				habit.markCompletedToday();
			}
			else
			{
				habit.unmarkCompleteToday();
			}
		});

		card.addMouseListener(new MouseAdapter() // If card is clicked show
													// details
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				showHabitAnalytics(habit);
			}
		});

		card.add(check, BorderLayout.EAST); // add the CheckBox
		card.add(removeBtn, BorderLayout.WEST); // add the RemoveButton

		habitListPanel.add(card);

		// getting it to appear correctly:
		habitListPanel.revalidate();
		habitListPanel.repaint();
	}

	// Clearing Analytics when Habit is no longer selected
	private void clearDetails()
	{
		detailPanel.removeAll();
		detailPanel.revalidate();
		detailPanel.repaint();
		currentlyShowingHabit = null;
	}
	
	private Habit showHabitAnalytics(Habit habit)
	{
		currentlyShowingHabit = habit;
		detailPanel.removeAll();

		// Header with the name

		JLabel name = new JLabel(habit.getName(), SwingConstants.CENTER);
		name.setFont(name.getFont().deriveFont(Font.BOLD, 16f));
		detailPanel.add(name);
		detailPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Three stats:

		detailPanel
				.add(new JLabel("Days Tracked:   " + habit.getDaysTracked()));
		detailPanel
				.add(new JLabel("Longest Streak: " + habit.getLongestStreak()));
		detailPanel
				.add(new JLabel("Current Streak: " + habit.getCurrentStreak()));

		detailPanel.add(Box.createVerticalGlue());
		detailPanel.revalidate();
		detailPanel.repaint();
		
		return currentlyShowingHabit;
	}

	/**
	 * Purpose: Save Habits to File
	 * 
	 * @param name
	 */
	private void saveHabit()
	{
		JPanel form = new JPanel(new GridLayout(2, 2, 5, 5)); // Create A Form
																// to get Title
																// of FileName
		JTextField nameFld = new JTextField(); // Create a Name Field
		form.add(new JLabel("File Name")); // Asks for Habit Name
		form.add(nameFld);

		int result = JOptionPane.showConfirmDialog(this, form, "Save Habits",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			String name = nameFld.getText().trim();
			fileManager = new FileManager(trackerModel, name);

			try
			{
				fileManager.save();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"Failed to save habits:\n" + e.getMessage(),
						"Save Error", JOptionPane.ERROR_MESSAGE);
			}
			// Save name of file:
			lastFileName = name;
		}
	}

	/**
	 * Purpose: To open a form to fill out Details about Habit being Added.
	 * 
	 * @return
	 */
	private void loadHabitForm()
	{
		// build your form
		JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
		JTextField fileNameField = new JTextField();
		JButton recentButton = new JButton("Most Recent");

		// when clicked, shove the lastFileName into the text field
		recentButton
				.addActionListener(e -> fileNameField.setText(lastFileName));

		Component[] component = { form.add(new JLabel("File Name:")),
				form.add(fileNameField), form.add(recentButton),
				form.add(new JLabel()), // filler
		};

		// implementing an array
		for (Component comp : component)
		{
			form.add(comp);
		}

		// show user ok & cancel options
		int result = JOptionPane.showConfirmDialog(this, form, "Load Habits",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			String chosen = fileNameField.getText().trim();
			if (!chosen.isEmpty())
			{
				loadHabit(chosen);
			}
		}
	}

	/**
	 * Purpose: Load Habits From File back to Screen
	 * 
	 * @param name
	 */
	private void loadHabit(String fileName)
	{
		// clearing all the habits from the panel
		habitListPanel.removeAll();

		// clearing the model
		trackerModel.clearAllHabits();

		// Loading the file from the file manager
		try
		{
			fileManager = new FileManager(trackerModel, fileName);
			fileManager.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Failed to load habits:\n" + e.getMessage(), "Load Error",
					JOptionPane.ERROR_MESSAGE);
		}

		// Adding Everything to UI
		for (Habit h : trackerModel.getAllHabits())
		{
			addHabitCard(h);
		}

		// Refreshing the Label
		habitListPanel.revalidate();
		habitListPanel.repaint();

	}

	private void layoutComponents()
	{
		setLayout(new BorderLayout());

		// Title of Application:

		add(title, BorderLayout.NORTH); // Adding Title in Position NORTH

		// Layout of Adding Habits
		JPanel westContainer = new JPanel(new BorderLayout()); // Everything
																// that is going
																// to be on the
																// West Part of
																// the Layout

		westContainer
				.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 20)); // Specifying
																				// the
																				// Position
		westContainer.add(addHabitPanel, BorderLayout.NORTH); // Adding
																// HabitButton
																// Panel to the
																// North part of
																// West
																// Container
		westContainer.add(listScroll, BorderLayout.CENTER); // Adding List of
															// Habits to Center
															// of West Container

		add(westContainer, BorderLayout.WEST); // Adding addHabit Button to
												// Position WEST

		// Adjusting the Detail Panel:

		JPanel eastContainer = new JPanel();
		eastContainer.setLayout(new BoxLayout(eastContainer, BoxLayout.Y_AXIS));
		saveAndLoadPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastContainer.add(saveAndLoadPanel);
		eastContainer.add(Box.createVerticalStrut(20)); // spacing
		detailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastContainer.add(detailPanel);
		eastContainer
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		eastContainer.add(saveAndLoadPanel);
		eastContainer.add(Box.createVerticalStrut(20));

		// Layout of Save & Load Buttons:
		saveAndLoadPanel
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 200)); // Specifying
																				// the
																				// Position
		// add(saveAndLoadPanel, BorderLayout.EAST); // Adding Save & Load
		// Habits to Position EAST

		add(eastContainer, BorderLayout.EAST);

	}

	/**
	 * Starts the game
	 * 
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		new HabitTrackerGUI(new HabitTracker());

	}
}

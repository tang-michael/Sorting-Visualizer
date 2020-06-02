import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Main extends JFrame{
	
	JButton startButton, stopButton, newDataButton;
	JTextField data;
	JComboBox sorts;
	RectangleBars label;
	private SpringLayout layout;
	private final int FRAME_PADDING = 15;
	private final int BUTTON_SPACING = 10;
	private boolean done = true;
	
	private String[] sortNames = { "Insertion Sort", "Bubble Sort", "Quick Sort", "Merge Sort"};

	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
	
	public Main() {
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new SpringLayout();
		getContentPane().setLayout(layout);
		
		setTitle("Sorting Visualizer");
		setLocationRelativeTo(null);
		
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		newDataButton = new JButton("New Data");
		data = new JTextField("50", 5);
		label = new RectangleBars(50);
		sorts = new JComboBox(sortNames);
		
		
		layout.putConstraint(SpringLayout.EAST, sorts, -BUTTON_SPACING,
				SpringLayout.WEST, startButton);
		layout.putConstraint(SpringLayout.SOUTH, sorts, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());
		
		layout.putConstraint(SpringLayout.SOUTH, stopButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, stopButton, 0,
				SpringLayout.HORIZONTAL_CENTER, getContentPane());

		layout.putConstraint(SpringLayout.EAST, startButton, -BUTTON_SPACING,
				SpringLayout.WEST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, startButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());

		layout.putConstraint(SpringLayout.WEST, newDataButton, BUTTON_SPACING,
				SpringLayout.EAST, stopButton);
		layout.putConstraint(SpringLayout.SOUTH, newDataButton, -FRAME_PADDING,
				SpringLayout.SOUTH, getContentPane());

		layout.putConstraint(SpringLayout.NORTH, label, FRAME_PADDING,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, label, FRAME_PADDING,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, label, FRAME_PADDING,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, label, -FRAME_PADDING,
				SpringLayout.NORTH, stopButton);
		
		layout.putConstraint(SpringLayout.SOUTH, data, -FRAME_PADDING, SpringLayout.SOUTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, data, BUTTON_SPACING, SpringLayout.EAST, newDataButton);
		
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		newDataButton.setEnabled(true);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				startButtonPressed(sorts.getSelectedItem().toString());
			}

		});
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopButtonPressed(sorts.getSelectedItem().toString());
			}

		});
		
		newDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newDataButtonPressed();
				
			}
			
			
		});
		
		
		
		add(sorts);
		add(startButton);
		add(stopButton);
		add(newDataButton);
		add(label);
		add(data);
		
		
		
	}
	
	public void newDataButtonPressed() {
		remove(label);
		int size;
		try{
			size = Integer.parseInt(data.getText());
		} catch (NumberFormatException e){
			size = 50;
		}
		System.out.println("Random array of " + size + " created");
		label = new RectangleBars(size);
		layout.putConstraint(SpringLayout.NORTH, label, FRAME_PADDING,
				SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.EAST, label, FRAME_PADDING,
				SpringLayout.EAST, getContentPane());
		layout.putConstraint(SpringLayout.WEST, label, FRAME_PADDING,
				SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.SOUTH, label, -FRAME_PADDING,
				SpringLayout.NORTH, stopButton);
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		add(label);
		revalidate();
		repaint();
	}

	public void startButtonPressed(String sort) {
			label.startSorting(sort);
//		if (done == false) {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
//			newDataButton.setEnabled(false);
//		}else if(done == true) {
//			startButton.setEnabled(true);
//			stopButton.setEnabled(false);
//			newDataButton.setEnabled(true);	
//		}
	}

	/**
	 * Stops sorting and puts the buttons back to enabled
	 */
	public void stopButtonPressed(String sort) {
		label.stopSorting(sort);
		stopButton.setEnabled(false);
		startButton.setEnabled(true);
		newDataButton.setEnabled(true);
	}
	
	
	

}

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class RectangleBars extends JLabel
		implements InsertionSort.ArrayUpdater, BubbleSort.ArrayUpdater, QuickSort.ArrayUpdater, MergeSort.ArrayUpdater {
	private int[] currentArray;
	QuickSort quickSort;
	BubbleSort bubbleSort;
	InsertionSort insertionSort;
	MergeSort mergeSort;
	private Thread insertionThread;
	private Thread bubbleThread;
	private Thread quickThread;
	private Thread mergeThread;
	private boolean isFinished = false;

	public RectangleBars(int size) {
		insertionSort = new InsertionSort(size, this);
		bubbleSort = new BubbleSort(size, this);
		quickSort = new QuickSort(size, this);
		mergeSort = new MergeSort(size, this);
		currentArray = insertionSort.getArray();
		insertionThread = new Thread() {
			@Override
			public void run() {
				currentArray = insertionSort.getArray();
				insertionSort.sort();
				isFinished = true;
			}

		};
		bubbleThread = new Thread() {

			@Override
			public void run() {
				currentArray = bubbleSort.getArray();
				bubbleSort.sort();
				isFinished = true;
			}

		};
		quickThread = new Thread() {

			@Override
			public void run() {
				currentArray = quickSort.getArray();
				quickSort.sort();
				isFinished = true;
			}

		};
		mergeThread = new Thread() {
			@Override
			public void run() {
				currentArray = mergeSort.getArray();
				mergeSort.sort(0, currentArray.length);
				isFinished = true;
			}

		};

	}

	@Override
	public void update(int[] array) {
		currentArray = array;
		repaint();
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		double size = insertionSort.getArray().length;
		int width = (int) (getWidth() / size);
		for (int i = 0; i < currentArray.length; i++) {
			if (i % 2 == 0)
				g.setColor(new Color(39, 70, 143));
			else
				g.setColor(new Color(52, 171, 235));
			int x = i * width;
			int height = (int) (currentArray[i] / size * getHeight());
			int y = getHeight() - height;
			g.fillRect(x, y, width, height);
		}
	}

	public boolean startSorting(String sort) {
		if (isFinished)
			return false;
		if (sort == "Insertion Sort") {
			System.out.println("Insertion Sort is starting...");
			if (!insertionThread.isAlive()) {
				insertionThread = new Thread() {
					@Override
					public void run() {
						currentArray = insertionSort.getArray();
						insertionSort.sort();
						isFinished = true;
						System.out.println("Insertion Sort is finished...");
					}

				};
				insertionThread.start();
			} else
				insertionThread.resume();
		}
		if (sort == "Bubble Sort") {
			System.out.println("Bubble Sort is starting...");
			if (!bubbleThread.isAlive()) {
				bubbleThread = new Thread() {
					@Override
					public void run() {
						currentArray = bubbleSort.getArray();
						bubbleSort.sort();
						isFinished = true;
						System.out.println("Bubble Sort is finished...");
					}

				};

				bubbleThread.start();
			} else
				bubbleThread.resume();
		}
		if (sort == "Quick Sort") {
			System.out.println("Quick Sort is starting...");
			if (!quickThread.isAlive()) {
				quickThread = new Thread() {
					@Override
					public void run() {
						currentArray = quickSort.getArray();
						quickSort.sort();
						isFinished = true;
						System.out.println("Quick Sort is finished...");
					}

				};
				quickThread.start();
			} else
				quickThread.resume();
		}
		if (sort == "Merge Sort") {
			System.out.println("Merge Sort is starting...");
			if (!mergeThread.isAlive()) {
				mergeThread = new Thread() {
					@Override
					public void run() {
						currentArray = mergeSort.getArray();
						mergeSort.sort(0, currentArray.length-1);
						isFinished = true;
						System.out.println("Merge Sort is finished...");
					}

				};
				mergeThread.start();
			} else
				mergeThread.resume();
		}
		return true;
	}

	public void stopSorting(String sort) {
		if (sort == "Insertion Sort") {
			insertionThread.suspend();
		} else if (sort == "Bubble Sort") {
			bubbleThread.suspend();
		} else if (sort == "Quick Sort") {
			quickThread.suspend();
		}

	}

}

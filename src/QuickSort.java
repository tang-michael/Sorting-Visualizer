
public class QuickSort {
	private int[] arr;
	private ArrayUpdater update;

	public QuickSort(int size, ArrayUpdater update) {
		arr = new int[size];
		this.update = update;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * size) + 1;
		}
	}

	public int[] getArray() {
		return arr;
	}

	public void sort() {
		quickSort(0, arr.length - 1);
	}

	private void quickSort(int left, int right) {
		if (left < right) {
			int pi = partition(left, right);
			quickSort(left, pi - 1);
			quickSort(pi + 1, right);
		}
		

	}

	private int partition(int start, int end) {
		int pivot = arr[start]; // Selects the first number as the pivot (it can be anything)

		while (start < end) { // We don't want overlap

			while (arr[start] < pivot) // Moves the left pointer to a point where the number not suppose to be on that
										// side, ie bigger than the pivot
				start++;

			while (arr[end] > pivot) // Moves the right pointer to a point where the number not suppose to be on that
										// side, ie smaller than the pivot
				end--;

			if (arr[start] == arr[end]) // Do nothing if they are the same
				end--;

			else if (start < end) // Swap the values at the pointers
				swapValues(start, end);

		}

		return start;

	}

	public void swapValues(int a, int b) {
		int temp = arr[a];				// Simple array swap
		arr[a] = arr[b];
		arr[b] = temp;

		try {
			Thread.sleep(100);				// Sleeps so update isn't "instant"
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		update.update(arr);				// Ultimately Prints 
	}

	public interface ArrayUpdater {
		public void update(int[] array);
	}
}

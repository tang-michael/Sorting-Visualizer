
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
		int pivot = arr[start]; 
		while (start < end) { 

			while (arr[start] < pivot)
				start++;

			while (arr[end] > pivot) 
				end--;

			if (arr[start] == arr[end]) 
				end--;

			else if (start < end) 
				swapValues(start, end);

		}

		return start;

	}

	public void swapValues(int a, int b) {
		int temp = arr[a];				
		arr[a] = arr[b];
		arr[b] = temp;

		try {
			Thread.sleep(100);				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		update.update(arr);				
	}

	public interface ArrayUpdater {
		public void update(int[] array);
	}
}


public class InsertionSort {
	private int[] arr;
	ArrayUpdater update;

	public InsertionSort(int size, ArrayUpdater update) {
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
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[min_idx])
					min_idx = j;

			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
			try {
				Thread.sleep(100); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update.update(arr);
		}
	}

	public interface ArrayUpdater {
		public void update(int[] array);
	}
}

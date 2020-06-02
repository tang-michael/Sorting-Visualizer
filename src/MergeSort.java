
public class MergeSort {
	private int[] arr;
	private ArrayUpdater update;

	public MergeSort(int size, ArrayUpdater update) {
		arr = new int[size];
		this.update = update;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * size) + 1;
		}
	}
	
	public int[] getArray() {
		return arr;
	}

	void merge(int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;

		int L[] = new int[n1];
		int R[] = new int[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				try {
					Thread.sleep(100);				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				update.update(arr);				
				i++;
			} else {
				arr[k] = R[j];
				try {
					Thread.sleep(100);				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				update.update(arr);			
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			try {
				Thread.sleep(100);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update.update(arr);				
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			try {
				Thread.sleep(100);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update.update(arr);				
			j++;
			k++;
		}
	}

	
	void sort(int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;

			sort(l, m);
			sort(m + 1, r);

			merge(l, m, r);
		}
	}

	public interface ArrayUpdater {
		public void update(int[] array);
	}
}

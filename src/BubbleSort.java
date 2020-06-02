public class BubbleSort {
	private int[] arr;
	private ArrayUpdater update;
	
	public BubbleSort(int size, ArrayUpdater update) {
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
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < n-i-1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];				
					arr[j] = arr[j+1];
					arr[j+1] = temp;

					try {
						Thread.sleep(100);				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					update.update(arr);		
				}
				
			}
		}
	}
	
	
	public interface ArrayUpdater {
		public void update(int[] array);
	}
}

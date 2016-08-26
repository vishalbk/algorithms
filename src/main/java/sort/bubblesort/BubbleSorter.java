package sort.bubblesort;

import sort.Sorter;
import sort.utils.SortUtils;

public class BubbleSorter implements Sorter {

	@Override
	public int[] doSort(int[] arr, boolean asc) {
		for (int i = 0; i < arr.length; i++) {
			boolean swap = false;
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j - 1] > arr[j] && asc) {
					SortUtils.swap(arr, j - 1, j);
					swap = true;
				} else if (arr[j - 1] < arr[j] && !asc) {
					SortUtils.swap(arr, j - 1, j);
					swap = true;
				}
			}
			if (!swap) {
				break;
			}
		}
		return arr;
	}
}

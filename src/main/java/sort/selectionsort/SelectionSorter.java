package sort.selectionsort;

import sort.Sorter;
import sort.utils.SortUtils;

public class SelectionSorter implements Sorter {

	@Override
	public int[] doSort(int[] arr, boolean asc) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j] && asc) {
					SortUtils.swap(arr, i, j);
				} else if (arr[i] < arr[j] && !asc) {
					SortUtils.swap(arr, i, j);
				}
			}
		}
		return arr;
	}
}

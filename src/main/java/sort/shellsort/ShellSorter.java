package sort.shellsort;

import sort.insertionsort.InsertionSorter;

public class ShellSorter extends InsertionSorter {
	@Override
	public int[] doSort(int[] arr, boolean asc) {
		int step = arr.length / 2;
		while (step > 0) {
			for (int i = 0; i < arr.length - step; i += step) {
				arr = doInsertionSort(arr, i, step, asc);
			}
			step = step / 2;
		}
		return arr;
	}

}

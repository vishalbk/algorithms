package sort.insertionsort;

import sort.Sorter;
import sort.utils.SortUtils;

public class InsertionSorter implements Sorter {

	@Override
	public int[] doSort(int[] arr, boolean asc) {
		return doInsertionSort(arr, 0, 1, asc);
	}

	public static int[] doInsertionSort(int arr[], int startIndex, int step,
			boolean asc) {

		for (int i = startIndex; i < arr.length - step; i += step) {
			if (arr[i] > arr[i + step] && asc) {
				doBubbling(arr, i + step, step, asc);
			} else if (arr[i] < arr[i + step] && !asc) {
				doBubbling(arr, i + step, step, asc);
			}
		}

		return arr;
	}

	public static int[] doBubbling(int[] arr, int lastIndex, int step,
			boolean asc) {
		for (int i = lastIndex; i >= step; i-=step) {
			boolean swapped = false;
			if (arr[i] < arr[i - step] && asc) {
				SortUtils.swap(arr, i, i - step);
				swapped = true;
			} else if (arr[i] > arr[i - step] && !asc) {
				SortUtils.swap(arr, i, i - step);
				swapped = true;
			}
			if (!swapped) {
				break;
			}
		}
		return arr;
	}
}

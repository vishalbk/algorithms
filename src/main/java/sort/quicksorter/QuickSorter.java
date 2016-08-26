package sort.quicksorter;

import sort.Sorter;
import sort.utils.SortUtils;

public class QuickSorter implements Sorter {

	@Override
	public int[] doSort(int[] arr, boolean asc) {
		doQuickSort(arr, 0, arr.length - 1, asc);
		return arr;
	}

	private void doQuickSort(int[] arr, int low, int high, boolean asc) {
		if (low >= high) {
			return;
		} else {
			int pivotIndex = partitionAroundPivot(arr, low, high, asc);
			doQuickSort(arr, low, pivotIndex - 1, asc);
			doQuickSort(arr, pivotIndex + 1, high, asc);
		}
	}

	private int partitionAroundPivot(int[] arr, int low, int high, boolean asc) {
		int pivot = arr[low];
		int pivotIndex = low;
		while (low < high) {
			if (asc) {
				while (pivot > arr[low]) {
					low++;
				}
				while (pivot < arr[high]) {
					high--;
				}
			} else {
				while (pivot < arr[low]) {
					low++;
				}
				while (pivot > arr[high]) {
					high--;
				}
			}
			SortUtils.swap(arr, low, high);
		}
		pivotIndex = high;
		return pivotIndex;
	}
}

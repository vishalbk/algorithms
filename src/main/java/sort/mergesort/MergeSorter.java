package sort.mergesort;

import sort.Sorter;

public class MergeSorter implements Sorter {

	@Override
	public int[] doSort(int[] arr, boolean asc) {
		doMergeSort(arr, asc);
		return arr;
	}

	public static int[] doMergeSort(int[] arr, boolean asc) {
		if (arr.length == 1) {
			return arr;
		} else {
			int midIndex = arr.length / 2 + arr.length % 2;
			int[] firstHalf = new int[midIndex];
			int[] secondHalf = new int[arr.length - midIndex];

			split(arr, firstHalf, secondHalf);

			doMergeSort(firstHalf, asc);
			doMergeSort(secondHalf, asc);

			sortAndMerge(arr, firstHalf, secondHalf, asc);
		}

		return arr;
	}

	private static void sortAndMerge(int[] arr, int[] firstHalf,
			int[] secondHalf, boolean asc) {

		int fHIndex = 0;
		int sHIndex = 0;
		int parAIndex = 0;

		while (fHIndex < firstHalf.length && sHIndex < secondHalf.length && parAIndex < arr.length) {
			if (firstHalf[fHIndex] <= secondHalf[sHIndex] && asc) {
				arr[parAIndex++] = firstHalf[fHIndex++];
			} else if (firstHalf[fHIndex] > secondHalf[sHIndex] && !asc) {
				arr[parAIndex++] = firstHalf[fHIndex++];
			} else if (firstHalf[fHIndex] > secondHalf[sHIndex] && asc) {
				arr[parAIndex++] = secondHalf[sHIndex++];
			} else if (firstHalf[fHIndex] <= secondHalf[sHIndex] && !asc) {
				arr[parAIndex++] = secondHalf[sHIndex++];
			}
		}
		while(fHIndex<firstHalf.length && parAIndex < arr.length) {
			arr[parAIndex++] = firstHalf[fHIndex++];
		}
		while(sHIndex<secondHalf.length && parAIndex < arr.length) {
			arr[parAIndex++] = secondHalf[sHIndex++];
		}
	}

	public static void split(int[] parArr, int firstHalf[], int[] secondHalf) {
		for (int i = 0, midIndex = firstHalf.length; i < firstHalf.length
				+ secondHalf.length; i++) {
			if (i < midIndex) {
				firstHalf[i] = parArr[i];
			} else {
				secondHalf[i - midIndex] = parArr[i];
			}
		}
	}
}

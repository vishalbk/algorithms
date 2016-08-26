package sort.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SortUtils {
	public static final String AVERAGE_TEST_TIME_CONSTANT = "average_test_time";

	private static final Random rand = new Random();

	/** Converts from nanos to millis. */
	private static final BigDecimal MILLION = new BigDecimal("1000000");

	public static int[] swap(int[] arr, int i, int j) {
		if (i != j) {
			arr[j] = arr[i] + arr[j];
			arr[i] = arr[j] - arr[i];
			arr[j] = arr[j] - arr[i];
		}
		return arr;
	}

	public static String printArrayAsLine(int[] arr, String spaceChar) {
		StringBuffer sb = new StringBuffer();
		for (int i : arr) {
			sb.append(i + spaceChar);
		}
		return sb.toString();
	}

	public static int[] getRandomIntArray(int size, int maxElement,
			int stepSize, boolean duplicates) {

		int[] arr = new int[size];
		Set<Integer> duSet = new HashSet<Integer>();

		for (int i = 0; i < size;) {
			int number = rand.nextInt(maxElement) + stepSize;
			if (!duSet.contains(number)) {
				arr[i++] = number;
				if (!duplicates)
					duSet.add(number);
			}
		}

		return arr;
	}

	public static int[] getWorstCaseScenarioArray(int size, boolean arrayOrder) {
		int[] arr = new int[size];
		if (arrayOrder) {
			for (int i = 1; i <= size; i++) {
				arr[i - 1] = i;
			}
		} else {
			for (int i = 0, ele = size; i < size; i++, ele--) {
				arr[i] = ele;
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		System.out.println(printArrayAsLine(
				getWorstCaseScenarioArray(10, false), "\t"));
	}

	public static boolean isArraySorted(int arr[], boolean asc) {
		boolean isSorted = true;
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1] && asc) {
				isSorted = false;
				break;
			} else if (arr[i] < arr[i + 1] && !asc) {
				isSorted = false;
				break;
			}
		}

		return isSorted;
	}

	public static BigDecimal getMillisecondFromNanoSecondAsBigDecimal(
			BigDecimal nanoseconds, int precision) {

		nanoseconds = nanoseconds.divide(MILLION, precision,
				BigDecimal.ROUND_HALF_EVEN);

		return nanoseconds;
	}

	public static BigDecimal getAverageTimeInMillsecs(BigDecimal totalTime,
			int iterations, int precision) {
		return getMillisecondFromNanoSecondAsBigDecimal(totalTime.divide(
				new BigDecimal(iterations), precision,
				BigDecimal.ROUND_HALF_EVEN), precision);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}

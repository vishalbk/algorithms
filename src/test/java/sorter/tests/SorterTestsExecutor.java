package sorter.tests;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import sort.Sorter;
import sort.bubblesort.BubbleSorter;
import sort.insertionsort.InsertionSorter;
import sort.mergesort.MergeSorter;
import sort.quicksorter.QuickSorter;
import sort.selectionsort.SelectionSorter;
import sort.shellsort.ShellSorter;
import sort.utils.SortUtils;

public class SorterTestsExecutor {

	public static int MIN_ITERARIONS = 10;
	public static int RESULT_PRECISION = 5;
	public static int MAX_ARRAY_SIZE = 100;
	public static boolean RUN_WORST_CASE_TESTS = true;
	public static boolean PRINT_EACH_ITERATION_ARRAY = false;
	public static boolean PRINT_EACH_ITERATION_TIME = false;
	public static boolean SORT_ORDER = true;
	public static boolean ALLOW_DUPLICATES = false;
	public static boolean PRINT_TEST_SUMMARY_LINE = false;
	public static int WARM_UP_ITERATIONS = 10;
	public static boolean PRINT_WARMUP_STATUS_LINES = false;

	private static final LinkedList<Class<? extends Sorter>> sorterClasses = new LinkedList<Class<? extends Sorter>>() {
		private static final long serialVersionUID = 9001363330167957480L;
		{
			add(QuickSorter.class);
			add(SelectionSorter.class);
			add(BubbleSorter.class);
			add(InsertionSorter.class);
			add(ShellSorter.class);
			add(MergeSorter.class);
		}
	};
	private static Map<Class<? extends Sorter>, BigDecimal> resultTimeMap = new HashMap<Class<? extends Sorter>, BigDecimal>();
	private static Map<Class<? extends Sorter>, BigDecimal> sortedResultTimeMap = null;

	public static void main(String[] args) {
		setCommonSorterTestsParams();

		executeTests(WARM_UP_ITERATIONS, PRINT_WARMUP_STATUS_LINES);

		System.out.println("\n \nFinal Iteration data : ");
		
		printMap(sortedResultTimeMap);
	}

	private static void executeTests(int repeatCount,
			boolean printIterationResult) {
		for (int i = 0; i < repeatCount; i++) {
			resultTimeMap.clear();
			for (Class<? extends Sorter> sorterClass : sorterClasses) {
				BigDecimal averageTime = runSorterTestForClass(sorterClass);
				resultTimeMap.put(sorterClass, averageTime);
			}
			sortedResultTimeMap = SortUtils.sortByValue(resultTimeMap);
			if (printIterationResult) {
				printMap(sortedResultTimeMap);
			}
		}
	}

	private static void printMap(Map<?, ?> map) {
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue()
					+ " ms");
		}
	}

	private static BigDecimal runSorterTestForClass(
			Class<? extends Sorter> sorterClass) {
		BigDecimal averageTimeMS = null;
		SorterTests.SORTER_CLASS = sorterClass;

		Result result = JUnitCore.runClasses(SorterTests.class);

		if (!result.wasSuccessful()) {
			System.out.println("Tests for class : " + sorterClass
					+ " resulted in failure, reasons : ");
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			averageTimeMS = new BigDecimal("0");
		}
		averageTimeMS = new BigDecimal(System.getProperty(
				SortUtils.AVERAGE_TEST_TIME_CONSTANT, "0"));

		return averageTimeMS;
	}

	private static void setCommonSorterTestsParams() {
		SorterTests.MIN_ITERARIONS = MIN_ITERARIONS;
		SorterTests.RESULT_PRECISION = RESULT_PRECISION;
		SorterTests.MAX_ARRAY_SIZE = MAX_ARRAY_SIZE;
		SorterTests.RUN_WORST_CASE_TESTS = RUN_WORST_CASE_TESTS;
		SorterTests.PRINT_EACH_ITERATION_ARRAY = PRINT_EACH_ITERATION_ARRAY;
		SorterTests.PRINT_EACH_ITERATION_TIME = PRINT_EACH_ITERATION_TIME;
		SorterTests.ALLOW_DUPLICATES = ALLOW_DUPLICATES;
		SorterTests.SORT_ORDER = SORT_ORDER;
		SorterTests.PRINT_TEST_SUMMARY_LINE = PRINT_TEST_SUMMARY_LINE;
	}
}

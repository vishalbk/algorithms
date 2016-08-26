package sorter.tests;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import sort.Sorter;
import sort.insertionsort.InsertionSorter;
import sort.timer.StopWatch;
import sort.utils.SortUtils;

@RunWith(Parameterized.class)
public class SorterTests {

	public static int MIN_ITERARIONS = 10000;
	public static Class<? extends Sorter> SORTER_CLASS = InsertionSorter.class;
	public static int RESULT_PRECISION = 3;
	public static int MAX_ARRAY_SIZE = 100;
	public static boolean RUN_WORST_CASE_TESTS = true;
	public static boolean PRINT_EACH_ITERATION_ARRAY = false;
	public static boolean PRINT_EACH_ITERATION_TIME = false;
	public static boolean PRINT_TEST_SUMMARY_LINE = true;
	public static boolean SORT_ORDER = true;
	public static boolean ALLOW_DUPLICATES = false;

	public static BigDecimal totalTime = new BigDecimal(0);
	public static BigDecimal averageTime;

	private Sorter sorter = null;

	private int[] arr = null;
	private StopWatch stopWatch = null;

	private int maxArraElement = 200 + MAX_ARRAY_SIZE + 1;
	private int stepSize = 1;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[MIN_ITERARIONS][0]);
	}

	@BeforeClass
	public static void setUpBeforeTests() {
		totalTime = new BigDecimal(0);
	}

	@Before
	public void setUp() throws Exception {
		if (!RUN_WORST_CASE_TESTS) {
			arr = SortUtils.getRandomIntArray(MAX_ARRAY_SIZE, maxArraElement,
					stepSize, ALLOW_DUPLICATES);
		} else {
			arr = SortUtils.getWorstCaseScenarioArray(MAX_ARRAY_SIZE,
					!SORT_ORDER);
		}
		if (PRINT_EACH_ITERATION_ARRAY || PRINT_EACH_ITERATION_TIME) {
			System.out
					.println("==================================================================\n");
		}
		if (PRINT_EACH_ITERATION_ARRAY) {
			System.out.println(SortUtils.printArrayAsLine(arr, "\t"));
		}
		stopWatch = new StopWatch();
	}

	@After
	public void tearDown() throws Exception {
		if (PRINT_EACH_ITERATION_TIME) {
			System.out.println("Time taken for running selelction sort on "
					+ MAX_ARRAY_SIZE + " is : " + stopWatch.toString());
		}
		if (PRINT_EACH_ITERATION_ARRAY) {
			System.out.println(SortUtils.printArrayAsLine(arr, "\t"));
		}
		if (PRINT_EACH_ITERATION_ARRAY || PRINT_EACH_ITERATION_TIME) {
			System.out
					.println("==================================================================\n");
		}
		arr = null;
		totalTime = totalTime.add(new BigDecimal(stopWatch.toValue()));
	}

	@AfterClass
	public static void afterTests() {
		averageTime = SortUtils.getAverageTimeInMillsecs(totalTime,
				MIN_ITERARIONS, RESULT_PRECISION);
		if (PRINT_TEST_SUMMARY_LINE) {
			System.out.println("\n ******************************* \n");
			System.out.println("Average Time taken for sorting "
					+ MAX_ARRAY_SIZE + " elements over " + MIN_ITERARIONS
					+ " for class " + SORTER_CLASS.getName() + " is "
					+ averageTime + " ms");
			System.out.println("\n ******************************* \n");
		}
		System.setProperty(SortUtils.AVERAGE_TEST_TIME_CONSTANT,
				averageTime.toString());
	}

	@Test
	public void testDoSort() throws Exception {
		sorter = getInstance(SORTER_CLASS);
		stopWatch.start();
		arr = sorter.doSort(arr, SORT_ORDER);
		stopWatch.stop();
		assertTrue("Array Should be Sorted",
				SortUtils.isArraySorted(arr, SORT_ORDER));
	}

	private <T> T getInstance(Class<T> classT) throws Exception {
		T instance = null;
		try {
			Constructor<T> ctor = classT.getConstructor();
			instance = ctor.newInstance();
		} catch (Exception ex) {
			throw ex;
		}
		return instance;
	}

}

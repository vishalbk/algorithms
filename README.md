# Sorting Algorithms and Tests to compare performance

This project contains the  algorithms and a test class which runs and measures performance of each algorithm and shows the output. See SorterTestsExecutor.java. The idea of the project was to learn the algorithms and develop it myself and pit each one of them against one another and notice the difference in times.

The SorterTestsExecutor uses the SorterTests to run tests on all classes one by one over multiple iterations(-DDWARM_UP_ITERATIONS=10) and finds the average time for each iteration and prints out the average time of the last iteration. 

The reamining iterations warm up the caches and ensure consistent average time towards the ending iteration.
The SorterTests runs multiple iterations(configurable -DMIN_ITERARIONS=10) of tests on the same class and averages the time taken for sorting the test Array .

By default the SorterTestsExecutor generates the worst case scenario array i.e if the sort order is ascending then the array generated will be in descending order.

## Sorting Algortihms coverd :
1. Selction Sorting
2. Bubble Sorting
3. Insertion Sorting
4. Shell Sorting
5. Merge Sorting
6. Quick Sorting 

Algorithms in progress : Tim Sort, Double Pivot Quick sort... 

## Running the tests
1. Checkout the project
2. Build the project using the command : mvn clean package
3. Run the SorterTestsExecutor using the command : mvn exec:java -Dexec.id=runSorterBenchmark
4. Below options are availble to customize the tests : 
    1. -DMIN_ITERARIONS=40 (Number of iterations of tests to be run per sorting class to take the average time of each)
    2. -DRESULT_PRECISION=4 (Precision of average time in ms)
    3. -DMAX_ARRAY_SIZE=100 (Test array size)
    4. -DRUN_WORST_CASE_TESTS=true (Use descending array if sort order is ascending or vice versa) if false then generate a random array
    5. -DPRINT_EACH_ITERATION_ARRAY=false (print the array used for each iteration of each class)
    6. -DPRINT_EACH_ITERATION_TIME=false (print the the time taken for each iteration of each class) 
    7. -DSORT_ORDER=true (Sorting order expected true - ascending/ false -descending)
    8. -DALLOW_DUPLICATES=false (Whether to allow duplicates in the test array or not)
    9. -DPRINT_TEST_SUMMARY_LINE=false (print the test summary line of each class)
    10. -DWARM_UP_ITERATIONS=10 (number of iterations of SorterTests to be repeated on all classes)
    11. -DPRINT_WARMUP_STATUS_LINES=false (Wehther to print the status of each warm up iteration).


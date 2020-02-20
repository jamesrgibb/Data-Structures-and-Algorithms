package assignment09;

import java.util.Arrays;
import java.util.Random;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;

public class Timing {

	private static <E> void test(int size) {
		int numOfTimesRun = 5;
		double averageTime = 0;
		Stopwatch timer = new Stopwatch1();
		Integer[] rep = new Integer[size+1];
		Random rand = new Random(1000);
		Heap<Integer> h = new Heap<Integer>();
		for (int i = 0; i < numOfTimesRun; i++) {
			for (int j = 0; j < size; j++) {
				rep[j] = rand.nextInt();
				h.add(rep[j]);
			}
	
			// h.sort();
			int insert = rand.nextInt();
			timer.start();
			h.add(insert);
			timer.stop();
			// divide by 1000 to get in miliseconds
			double time = timer.elapsed() / 1000;
			averageTime += time;
		}
		averageTime /= numOfTimesRun;

		System.out.println();
		System.out.println("lengthOfRep: " + size);
		System.out.println("averageTime: " + averageTime);

	}

	public static void main(String[] args) {
		// run test method with different number of buckets
		test(10);
//		test(50000000);
//		test(75);
//		test(125);
//		test(150);
//		test(175);

	}

}

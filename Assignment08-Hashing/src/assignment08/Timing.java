package assignment08;

import java.util.Random;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;

/**
 * Timing file.
 * 
 * @author Jacob Morrison and James Giobb
 *
 */

public class Timing {

	public static void main(String[] args) {
		int numOfRandInts = 10;
		int numOfTimesRun = 5;
		double averageTime = 0;

		Stopwatch timer = new Stopwatch1();
		HashTableSet<Integer> hashTable1 = new HashTableSet<>(5);
		Random rand = new Random();

		for (int i = 0; i < numOfTimesRun; i++) {
			int randomNumToCheckFor = rand.nextInt();
			hashTable1.add(randomNumToCheckFor);

			for (int j = 0; j < numOfRandInts; j++) {
				int randomNum = rand.nextInt();
				if (hashTable1.contains(randomNum)) {
					hashTable1.add(randomNum);
				}
			}
			timer.start();
			hashTable1.contains(randomNumToCheckFor);
			timer.stop();
			// divide by 1000 to get in miliseconds
			double time = timer.elapsed() / 1000;
			averageTime += time;
		}
		averageTime /= numOfTimesRun;

		System.out.println(numOfRandInts);
		System.out.println(averageTime);
	}

}
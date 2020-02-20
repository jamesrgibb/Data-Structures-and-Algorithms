package assignment08;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class HashAnalysis {
	public static void main(String[] args) {
		SimpleWriter out = new SimpleWriter1L();
		final int numBuckets = 101;
		HashTableSet<A08String> hash = new HashTableSet<>(numBuckets);

//	 SimpleReader file = new SimpleReader1L("data/a08-random.txt");
//	 SimpleReader file = new SimpleReader1L("data/a08-length8.txt");
  SimpleReader file = new SimpleReader1L("data/a08-mod30.txt");
//   SimpleReader file = new SimpleReader1L("data/a08-startend.txt");

		while (!file.atEOS()) {
			A08String element = new A08String(file.nextLine());
			if (!hash.contains(element))
				hash.add(element);
		}
		file.close();

		out.println(file.name());
		out.println("Bucket\tHits\tCount");
		out.println("------\t----\t-----");
		for (int i = 0; i < hash.numBuckets(); i++) {
			out.println(i + "\t" + hash.bucketSize(i) + "\t" + stars(hash.bucketSize(i)));
		}
		out.close();
	}

	/**
	 * Returns a string with the given number of asterisks.
	 * 
	 * @param count number of asterisks
	 * @return strings with the given number of asterisks
	 */
	private static String stars(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++)
			sb.append("*");
		return sb.toString();
	}
}

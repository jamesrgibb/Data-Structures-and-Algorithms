package assignment08;

public class A08String {
	private String str;

	public A08String() {
		this.str = "";
	}

	public A08String(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof A08String))
			return false;
		A08String that = (A08String) other;
		return this.str.equals(that.str);
	}

	@Override
	public int hashCode() {
		//return str.length();
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)%3==0) {
			sum += 29 * str.charAt(i);
			sum += 13; 
			}
			else if (str.charAt(i)%2==0) {
			sum += 53*str.charAt(i);
			sum += 29;
			}
			else if (str.charAt(i)%5==0) {
				sum+= 97*str.charAt(i);
				sum += 107;
			}else if(str.charAt(i)%7==0){
				sum+= 113*str.charAt(i);
				sum+= 103;
			}else {
				sum+= 191*str.charAt(i);
				sum+= 59;
			}
		}
		return sum;
	}
}

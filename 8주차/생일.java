import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Person implements Comparable<Person> {
		String name;
		int year, month, day;

		public Person(String name, int day, int month, int year) {
			this.name = name;
			this.year = year;
			this.month = month;
			this.day = day;
		}

		@Override
		public int compareTo(Person o) {
			if (this.year == o.year) {
				if (this.month == o.month) {
					return this.day - o.day;
				}
				return this.month - o.month;
			}
			return this.year - o.year;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Person[] persons = new Person[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			persons[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(persons);
		
		System.out.println(persons[N - 1].name);
		System.out.println(persons[0].name);
	}
}

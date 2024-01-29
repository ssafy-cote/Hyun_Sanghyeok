import java.util.Scanner;

public class 설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weight = sc.nextInt();
		sc.close();
		
		for(int count5 = weight / 5 ; count5 >= 0 ; count5--) {
			if ((weight - count5 * 5) % 3 == 0) {
				System.out.println(count5 + (weight - count5 * 5) / 3);
				return;
			}
		}
		
		System.out.println(-1); // weight 구성 불가능
	}
}

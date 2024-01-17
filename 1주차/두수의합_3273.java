import java.util.Arrays;
import java.util.Scanner;

public class 두수의_합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		int x = sc.nextInt();
		sc.close();
		
		Arrays.sort(arr);
		
		int matchCount = 0;
		int left = 0;
		int right = n - 1;
		
		while(left < right) {
			int sum = arr[left] + arr[right];
			if (sum == x) {
				matchCount++;
				left++;
			} else if (sum < x) {
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(matchCount);
	}
}

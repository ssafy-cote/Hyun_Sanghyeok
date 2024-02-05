import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		for(int i = 1 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		int[] dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = arr[1];
		if (N >= 2) {
			dp[2] = arr[1] + arr[2];  
		}
		
		System.out.println(find(N, dp, arr));
	}
	
	static int find(int index, int[] dp, int[] arr) {
		if (index == 0 || dp[index] != 0) {
			return dp[index];
		}
		
		return dp[index] = Math.max(find(index - 2, dp, arr), find(index - 3, dp, arr) + arr[index - 1]) + arr[index];
	}
}

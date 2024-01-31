import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 메모리 : 11,868kb, 실행시간 : 84ms

public class 로또 {
	static StringBuilder sb = new StringBuilder();
	static int[] numbers;
	static int[] inputs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		numbers = new int[6];

		int k = Integer.parseInt(st.nextToken());
		while (k != 0) {
			inputs = new int[k];
			for (int i = 0; i < k; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(inputs);

			comb(0, 0);

			sb.append("\n");
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}

	static void comb(int index, int start) {
		if (index == 6) {
			for(int i = 0 ; i < 6 ; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < inputs.length; i++) {
			numbers[index] = inputs[i];
			comb(index + 1, i + 1);
		}
	}
}

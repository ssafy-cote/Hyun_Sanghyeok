// 22,348kb / 224ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 옥상_정원_꾸미기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long answer = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0 ; i < N ; i++) {
			int current = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty() && stack.peek() <= current) {
				stack.pop();
			}
			
			answer += stack.size();
			stack.push(current);
		}
		
		System.out.println(answer);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int time, cost;
      
        public Node(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N]; // 0 ~ N-1일까지의 상담 정보
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        br.close();

        // 풀이 시작
        int[] dp = new int[N + 1]; // dp[i] = i + 1일의 최대 수익 (i일까지의 상담 진행)

        for (int i = 0; i < N; i++) {
            if (i + nodes[i].time <= N) {
                // 현재 상담으로 인해 변경되는 최대값 갱신
                dp[i + nodes[i].time] = Math.max(dp[i + nodes[i].time], dp[i] + nodes[i].cost);
            }

            // 현재까지 상담 중 최대 경로를 선택
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N]);
    }
}

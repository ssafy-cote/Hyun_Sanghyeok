import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12,048kb / 84ms

public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        dp = new int[30][30];
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            sb.append(comb(M, N)).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    // MCN의 결과를 반환
    static int comb(int M, int N) {
        if (dp[M][N] != 0) {
            return dp[M][N];
        }

        if (M == N) {
            return dp[M][N] = 1;
        }

        if (N == 1) {
            return dp[M][N] = M;
        }

        return dp[M][N] = comb(M - 1, N) + comb(M - 1, N - 1);
    }

}

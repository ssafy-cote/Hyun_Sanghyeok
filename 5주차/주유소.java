import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 40,612kb / 372ms

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] lengths = new int[N - 1]; // ~10억
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            lengths[i] = Integer.parseInt(st.nextToken());
        }

        int[] prices = new int[N]; // ~10억
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        // 풀이 시작
        long minTotalPrice = 0; // 모든 도시를 방문하는 최소 가격
        int minPrice = Integer.MAX_VALUE; // 지금까지 주유소 중 최소 가격
        for (int i = 0; i < N - 1; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            minTotalPrice += (long) minPrice * lengths[i];
        }

        System.out.println(minTotalPrice);
    }

}

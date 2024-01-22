import java.util.Scanner;

public class 등수구하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 기존 점수 개수
        int newScore = sc.nextInt(); // 태수의 새로운 점수
        int P = sc.nextInt(); // 랭킹 최대 개수

        int[] scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = sc.nextInt();
        }
        sc.close();

        // 풀이 시작
        if (N == 0) {
            System.out.println(1);
            return;
        }

        if (N == P && scores[scores.length - 1] >= newScore) {
            System.out.println(-1);
            return;
        }

        int rank = 1;
        for (; rank <= N; rank++) {
            if (scores[rank - 1] <= newScore) {
                break;
            }
        }
        System.out.println(rank);
    }
}

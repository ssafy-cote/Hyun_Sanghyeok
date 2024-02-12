// 11,560kb / 80ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L;
    static int C;
    static char[] candidates;
    static char[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // C에서 L개를 순서/중복 없이 뽑는 경우의 수
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        candidates = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            candidates[i] = st.nextToken().charAt(0);
        }
        br.close();

        Arrays.sort(candidates);
        selected = new char[L];
        comb(0, 0, 0);

        System.out.println(sb);
    }

    static void comb(int index, int start, int vowelCount) {
        // 두 개 이상의 자음을 포함할 수 없어진 경우 가지치기
        if (L - vowelCount < 2) {
            return;
        }

        if (index == L) {
            // 하나 이상의 모음을 포함하지 않으면 패스
            if (vowelCount == 0) {
                return;
            }

            for (int i = 0; i < L; i++) {
                sb.append(selected[i]);
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < C; i++) {
            selected[index] = candidates[i];
            comb(index + 1, i + 1, vowelCount + isVowel(selected[index]));
        }
    }

    static int isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return 1;
        }
        return 0;
    }

}

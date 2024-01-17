import java.util.Arrays;
import java.util.Scanner;

public class 방번호_1475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] roomNumbers = sc.nextLine().toCharArray();
        sc.close();

        int[] count = new int[10];
        for (char roomNumber : roomNumbers) {
            count[roomNumber - '0']++;
        }

        // 6 || 9 조정
        int sixAndNine = (count[6] + count[9]) / 2 + (count[6] + count[9]) % 2;
        count[6] = sixAndNine;
        count[9] = sixAndNine;

        System.out.println(Arrays.stream(count).max().getAsInt());
    }
}

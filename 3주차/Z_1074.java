import java.util.Scanner;

/**
 * 계속해서 전체를 4등분하고 그때마다의 x, y 좌표 절반 지점을 구해 R, C와 비교,
 * 위치가 결정될 때마다 각 분면별로 더해지는 count 값을 누적해준다 
 * 
 */
public class Z {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int R = sc.nextInt();
		int C = sc.nextInt();
		sc.close();

		// 한 depth 별로 상/하, 좌/우 여부를 판단
		int count = 0;
		// half부터 하/우
		int halfX = (int) Math.pow(2, N - 1);
		int halfY = halfX;
		for (int depth = N; depth > 0; depth--) {
			// 상/하 판단
			if (R >= halfX) {
				count += (int) Math.pow(4, depth - 1) * 2;
				halfX += (int) Math.pow(2, Math.max(depth - 2, 0));
			} else {
				halfX -= (int) Math.pow(2, Math.max(depth - 2, 0));
			}
			// 좌/우 판단
			if (C >= halfY) {
				count += (int) Math.pow(4, depth - 1);
				halfY += (int) Math.pow(2, Math.max(depth - 2, 0));
			} else {
				halfY -= (int) Math.pow(2, Math.max(depth - 2, 0));
			}
		}

		System.out.println(count);
	}

}

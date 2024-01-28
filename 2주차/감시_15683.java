import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final int EMPTY = 0; // 빈공간
    static final int WALL = 6; // 벽

    // 상, 우, 하, 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] rorateCounts = {0, 4, 2, 4, 4, 1};

    static int minZeroCount = Integer.MAX_VALUE;

    static List<Camera> cameras;

    static class Camera {
        int x, y;
        int type;
        int direction;

        public Camera(int x, int y, int type, int direction) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int M = sc.nextInt();
        int[][] arr = new int[N][M];
        cameras = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] >= 1 && arr[i][j] <= 5) {
                    cameras.add(new Camera(i, j, arr[i][j], 0));
                }
            }
        }
        sc.close();

        find(0, arr);
        System.out.println(minZeroCount);
    }

    static void find(int currentCameraIndex, int[][] arr) {
        if (currentCameraIndex == cameras.size()) {
            // 개수 확인
            int zeroCount = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] == EMPTY) {
                        zeroCount++;
                    }
                }
            }
            minZeroCount = Math.min(minZeroCount, zeroCount);
            return;
        }

        // 현재 카메라를 4방향으로 돌리면서 맵 변경
        Camera current = cameras.get(currentCameraIndex);
        int rotateCount = rorateCounts[current.type];
        for (int i = 0; i < rotateCount; i++) {
            // 맵 복제
            int[][] copiedArr = deepCopy(arr);
            // 각 카메라의 방향에 따라 맵 탐색 진행
            if (current.type == 1) {
                apply(copiedArr, current.x, current.y, dx[current.direction], dy[current.direction]);
            } else if (current.type == 2) {
                apply(copiedArr, current.x, current.y, dx[current.direction], dy[current.direction]);
                apply(copiedArr, current.x, current.y, dx[current.direction + 2], dy[current.direction + 2]);
            } else if (current.type == 3) {
                apply(copiedArr, current.x, current.y, dx[current.direction], dy[current.direction]);
                apply(copiedArr, current.x, current.y, dx[(current.direction + 1) % 4], dy[(current.direction + 1) % 4]);
            } else if (current.type == 4) {
                apply(copiedArr, current.x, current.y, dx[current.direction], dy[current.direction]);
                apply(copiedArr, current.x, current.y, dx[(current.direction + 1) % 4], dy[(current.direction + 1) % 4]);
                apply(copiedArr, current.x, current.y, dx[(current.direction + 2) % 4], dy[(current.direction + 2) % 4]);
            } else if (current.type == 5) {
                apply(copiedArr, current.x, current.y, dx[current.direction], dy[current.direction]);
                apply(copiedArr, current.x, current.y, dx[current.direction + 1], dy[current.direction + 1]);
                apply(copiedArr, current.x, current.y, dx[current.direction + 2], dy[current.direction + 2]);
                apply(copiedArr, current.x, current.y, dx[current.direction + 3], dy[current.direction + 3]);
            }

            // 탐색된 후 다음번 카메라 호출
            find(currentCameraIndex + 1, copiedArr);

            // 카메라 방향 돌리기
            cameras.get(currentCameraIndex).direction += 1;
        }
        // 모든 방향 탐색 종료 시, 현재 카메라 위치 원복 (이전 호출에 대비)
        cameras.get(currentCameraIndex).direction = 0;
    }

    static void apply(int[][] arr, int curX, int curY, int dx, int dy) {
        int nX = curX + dx;
        int nY = curY + dy;

        while (nX >= 0 && nX < arr.length && nY >= 0 && nY < arr[0].length) {
            if (arr[nX][nY] == WALL) {
                break;
            }
            if (arr[nX][nY] == 0) {
                arr[nX][nY] = -1;
            }
            nX += dx;
            nY += dy;
        }
    }

    static int[][] deepCopy(int[][] arr) {
        int[][] copiedArr = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            copiedArr[i] = arr[i].clone();
        }
        return copiedArr;
    }
}

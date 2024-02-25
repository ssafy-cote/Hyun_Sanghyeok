import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Position {
        int x, y, dir;

        public Position(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    // 우 -> 하 -> 좌 -> 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 보드 크기

        char[][] board = new char[N][N]; // 'a' - 사과, 's' - 뱀
        int K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 'a';
        }

        int L = Integer.parseInt(br.readLine()); // 방향 전환 횟수
        int nextL = 0;

        // 방향 전환 정보
        int nextTurnTime = 0;
        char nextTurnDir = 'I'; // dummy

        board[0][0] = 's';

        Deque<Position> snake = new ArrayDeque<>();
        snake.offer(new Position(0, 0, 0)); // 뱀 init

        int time = 0;
        while (true) {
            Position front = snake.peekFirst();
            if (nextTurnTime == time) {
                // 방향 전환
                if (nextTurnDir == 'L') {
                    // 좌회전
                    front.dir = (front.dir + 3) % 4;
                } else if (nextTurnDir == 'D') {
                    // 우회전
                    front.dir = (front.dir + 1) % 4;
                }

                // 다음 방향 갱신
                if (nextL++ < L) {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    nextTurnTime = Integer.parseInt(st.nextToken());
                    nextTurnDir = st.nextToken().charAt(0);
                }
            }

            // 이동
            int nX = front.x + dx[front.dir];
            int nY = front.y + dy[front.dir];

            if (nX < 0 || nX >= N || nY < 0 || nY >= N || board[nX][nY] == 's') {
                // 벽 || 몸통 충돌
                break;
            }

            if (board[nX][nY] != 'a') {
                // 사과가 아니면 꼬리 삭제
                Position rear = snake.pollLast();
                board[rear.x][rear.y] = 0;
            }

            // 머리 이동
            snake.addFirst(new Position(nX, nY, front.dir));
            board[nX][nY] = 's';
            time++;
        }

        System.out.println(time + 1);
    }
}

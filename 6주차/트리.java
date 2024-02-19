import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 63,576kb / 460ms

public class 트리 {

    static class Node {
        int prev, current;

        public Node(int prev, int current) {
            this.prev = prev;
            this.current = current;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            // 간선 입력
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            boolean[] visited = new boolean[N + 1];

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && bfs(graph, visited, i)) {
                    count++;
                }
            }

            // 출력 형식
            sb.append("Case ").append(tc).append(": ");

            if (count == 0) {
                sb.append("No trees.");
            } else if (count == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(count).append(" trees.");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean bfs(List<List<Integer>> graph, boolean[] visited, int start) {
        Queue<Node> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.offer(new Node(-1, start));

        boolean result = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int neighbor : graph.get(node.current)) {
                if (neighbor == node.prev) {
                    // 자신을 넣은 노드는 괜찮음
                    continue;
                }

                if (visited[neighbor]) {
                    // 싸이클 존재, 나머지도 다 방문해서 지워주기
                    result = false;
                    continue;
                }

                visited[neighbor] = true;
                queue.offer(new Node(node.current, neighbor));
            }
        }
        return result;
    }
}

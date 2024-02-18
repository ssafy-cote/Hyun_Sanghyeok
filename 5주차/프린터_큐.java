import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 12,276kb / 100ms

public class Main {

    static class Document implements Comparable<Document> {
        int index, priority;

        public Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }

        @Override
        public int compareTo(Document o) {
            return o.priority - this.priority;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) { // TC
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            PriorityQueue<Document> pq = new PriorityQueue<>();
            Queue<Document> queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Document current = new Document(i, Integer.parseInt(st.nextToken()));
                pq.offer(current);
                queue.offer(current);
            }

            int count = 1;
            while (!queue.isEmpty()) {
                Document current = queue.poll();
                if (current.priority == pq.peek().priority) {
                    if (current.index == target) {
                        break;
                    }
                    pq.poll();
                    count++;
                } else {
                    queue.offer(current);
                }
            }
            sb.append(count).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

}

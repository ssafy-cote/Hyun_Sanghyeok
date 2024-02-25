import java.util.*;

class Solution {
    static class City {
        int index, weight;
        
        public City(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        List<List<City>> graph = new ArrayList<>();
        for(int i = 0 ; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0 ; i < road.length ; i++) {
            graph.get(road[i][0]).add(new City(road[i][1], road[i][2]));
            graph.get(road[i][1]).add(new City(road[i][0], road[i][2]));
        }
        
        int[] distances = new int[N + 1]; // 1 ~ 각 도시의 최단 거리
        Arrays.fill(distances, 1_000_000_000);
        
        PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> c1.weight - c2.weight);
        pq.offer(new City(1, 0));
        distances[1] = 0;
        
        int count = 0;
        while(!pq.isEmpty()) {
            City current = pq.poll();
            
            if (current.weight > distances[current.index]) {
                // 이미 최단 거리가 확정됨
                continue;
            }
            
            if (distances[current.index] <= K) {
                // current.index의 최단 거리 확정
                count++;
            } else {
                // 다익스트라에서 최단 거리는 증가하는 형태로 확정됨
                break;
            }
            
            for(City neighbor : graph.get(current.index)) {
                int newDistance = distances[current.index] + neighbor.weight;
                
                if (distances[neighbor.index] > newDistance) {
                    distances[neighbor.index] = newDistance;
                    pq.offer(new City(neighbor.index, newDistance));
                }
            }
        }
        
        return count;
    }
}

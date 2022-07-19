package kakao;

import java.util.Objects;
import java.util.PriorityQueue;

public class Cache {
	public static void main(String[] args) {
		Cache c = new Cache();
		System.out.println(c.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
		System.out.println(c.solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"} ));
		System.out.println(c.solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
		System.out.println(c.solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
		System.out.println(c.solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
		System.out.println(c.solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
		System.out.println(c.solution(0, new String[]{"Jeju", "Jeju" }));
		
	}
	
	static final int CACHE_MISS = 5;
	static final int CACHE_HIT = 1;
	
	public int solution(int cacheSize, String[] cities) {
		//cacheSize가 0일 때, 같은 도시가 있다하더라도 캐시 미스 발생하기 때문
		if(cacheSize == 0) return CACHE_MISS * cities.length;
		
		int answer = 0;
		int size = 0;
		PriorityQueue<City> pq = new PriorityQueue<>();
		
		for(int i=0; i< cities.length; i++){
			City c = new City(cities[i].toUpperCase(), i);
			
			if(pq.contains(c)){
				answer += CACHE_HIT;
				pq.remove(c);
			}else {
				if(size < cacheSize) size++;
				else if(size == cacheSize) pq.poll();
				answer += CACHE_MISS;
			}
			pq.add(c);
		}
		
		return answer;
	}
	
	static class City implements Comparable<City>{
		String city;
		int time;
		
		public City(String city, int time) {
			this.city = city;
			this.time = time;
		}
		
		@Override
		public int compareTo(City o) {
			return this.time - o.time;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			City city1 = (City) o;
			return Objects.equals(city, city1.city);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(city);
		}
	}
}

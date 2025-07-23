package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7568 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Person> list = new ArrayList<>();
        int[] place = new int[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            list.add(new Person(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        place[list.get(0).idx] = 1;
        for (int i = 1; i < n; i++) {
            int num = 1;
            Person p = list.get(i);
            for (int j = 0; j < i; j++) {
                if(list.get(j).weight > p.weight && list.get(j).height > p.height){
                    num++;
                }
            }
            place[p.idx] = num;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : place){
            sb.append(i + " ");
        }

        System.out.println(sb.toString().trim());
    }
}

class Person implements Comparable<Person> {

    int idx;
    int weight;
    int height;

    Person() {
    }

    Person(int idx, int weight, int height) {
        this.idx = idx;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(Person person) {
        if (person.weight == this.weight) return Integer.compare(person.height, this.height);
        else return Integer.compare(person.weight, this.weight);
    }

    @Override
    public String toString() {
        return "{ " + weight + ", " + height + " }";
    }
}

package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17837 {

    static int n, k, INF = 1001;
    static int[][] board;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static Marker[] markerInfo;
    static LinkedList<Integer>[][] markers;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        markerInfo = new Marker[k+1];
        markers = new LinkedList[n][n];
        
        String s;
        for(int i=0; i<n; i++){
            s = br.readLine();
            for(int j=0; j<n; j++){
                board[i][j] = s.charAt(j*2) - '0';
                markers[i][j] = new LinkedList<>();
            }
        }
        
        for(int i=1; i<=k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1,
                y = Integer.parseInt(st.nextToken()) - 1,
                d = Integer.parseInt(st.nextToken()) - 1;
            markerInfo[i] = new Marker(i, x, y, d);
            markers[x][y].add(i);
        }
        
        int turn = 1, idx, size;
        LinkedList<Integer> list;
        
        outer :
        while(turn < INF){
            for(int i=1; i<=k; i++){
                Marker m = markerInfo[i];
                list = markers[m.x][m.y];
                
                int r = m.x + dir[m.d][0], c = m.y + dir[m.d][1];
                //이동하려는 칸이 체스판을 벗어나는 경우 & 파란칸인 경우
                if((r < 0 || r >= n || c < 0 || c >= n) || board[r][c] == 2) {
                    //이동방향을 반대로 바꿔줌
                    markerInfo[m.no].d = changeDir(m.d);
    
                    //한 칸 이동을 위해 새로운 위치 계산
                    r = m.x + dir[m.d][0];
                    c = m.y + dir[m.d][1];
    
                    //다시 이동하려는 칸이 체스판을 벗어나는 경우 & 파란칸인 경우 방향만 바꿔주고 가만히 있음
                    if ((r < 0 || r >= n || c < 0 || c >= n) || board[r][c] == 2) continue;
                }
                
                if(board[r][c] == 0){ //여러 개의 말이 흰칸으로 움직일 때
                    idx = list.indexOf(m.no);
                    size = list.size();
                    
                    for(int j=idx; j<size; j++){
                        int no = list.get(j);
                        markers[r][c].add(no);
                        markerInfo[no].x = r;
                        markerInfo[no].y = c;
                    }
                    
                    while(size - (idx++) > 0) list.pollLast();

                }else if(board[r][c] == 1){ //여러개의 말이 빨간 칸으로 움직일 때
                    
                    while(true){
                        int no = list.pollLast();
                        markers[r][c].add(no);
                        markerInfo[no].x = r;
                        markerInfo[no].y = c;
                        if(no == m.no) break;
                    }
                }
    
                if(markers[r][c].size() >= 4) break outer;
            }
            turn++;
        }
    
        System.out.println(turn == INF ? -1 : turn);
    }
    
    public static int changeDir(int d){
        return d % 2 == 0 ? d+1 : d-1;
    }
}

class Marker{
    int no, x, y, d;
    
    public Marker(int no, int x, int y, int d) {
        this.no = no;
        this.x = x;
        this.y = y;
        this.d = d;
    }
    
    @Override
    public String toString() {
        return "Marker{" +
          "no=" + no +
          ", x=" + x +
          ", y=" + y +
          ", d=" + d +
          '}';
    }
}

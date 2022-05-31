package kakao;

public class ColoringBook {

    static int cnt;
    static boolean[][] visited;
    static int[][] coloring;
    static int ml , nl;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
    
    public static void main(String[] args) {
        ColoringBook coloringBook = new ColoringBook();
        int[][] picture = {{1, 1, 1, 0},{1, 2, 2, 0},{1, 0, 0, 1},{0, 0, 0, 1},{0, 0, 0, 3},{0, 0, 0, 3}};
        int[] res = coloringBook.solution(6, 4, picture );
    }

    public int[] solution(int m, int n, int[][] picture){
        visited = new boolean[m][n];
        coloring = picture;
        ml = m; nl = n;

        int[] res = new int[2];
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(coloring[i][j] != 0 && !visited[i][j]){
                    visited[i][j] = true;
                    dfs(i, j, coloring[i][j], m , n);
                    res[0]++;
                    res[1] = Math.max(res[1], cnt);
                    cnt = 0;
                }
            }

        }

        return res;
    }

    public void dfs(int x, int y, int color, int m, int n){
        cnt++;
    
        for (int[] ints : dir) {
            int r = x + ints[0], c = y + ints[1];
            if (r < m && r >= 0 && c < n && c >= 0) {
                if (!visited[r][c] && coloring[r][c] == color) {
                    visited[r][c] = true;
                    dfs(r, c, color, m, n);
                }
            }
        }
    }
}

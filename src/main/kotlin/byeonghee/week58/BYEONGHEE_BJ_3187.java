package byeonghee.week58;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 양치기 꿍
public class BYEONGHEE_BJ_3187 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;

    static void newline() throws Exception { stk = new StringTokenizer(br.readLine()); }
    static int input() { return Integer.parseInt(stk.nextToken()); }

    static int R, C;
    static char[][] farm;
    static boolean[][] visited;
    static ArrayDeque<int[]> q = new ArrayDeque<>(R * C);
    static int[] top;
    static int r, c, nr, nc;
    static int ansS, ansW, curS, curW;

    public static final char SHEEP = 'k';
    public static final char WOLF = 'v';
    public static final char FENSE = '#';
    public static final char EMPTY = '.';
    public static final int[] dr = { 1, 0, -1, 0 };
    public static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        newline();
        R = input();
        C = input();
        farm = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            farm[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < R; i++) for(int j = 0; j < C; j++) {
            if (farm[i][j] == FENSE) continue;
            if (visited[i][j]) continue;

            visited[i][j] = true;
            curS = farm[i][j] == SHEEP ? 1 : 0;
            curW = farm[i][j] == WOLF ? 1 : 0;
            q.add(new int[] {i, j});
            while(!q.isEmpty()) {
                top = q.poll(); r = top[0]; c = top[1];
                for(int d = 0; d < 4; d++) {
                    nr = r + dr[d]; nc = c + dc[d];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (visited[nr][nc] || farm[nr][nc] == FENSE) continue;
                    visited[nr][nc] = true;
                    if (farm[nr][nc] == SHEEP) curS++;
                    else if (farm[nr][nc] == WOLF) curW++;
                    q.add(new int[] { nr, nc });
                }
            }
            if (curS > curW) ansS += curS;
            else ansW += curW;
        }

        System.out.println(ansS + " " + ansW);
    }
}
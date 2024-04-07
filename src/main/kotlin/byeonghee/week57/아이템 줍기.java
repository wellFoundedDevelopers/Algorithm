package byeonghee.week57;

class GETITEM {

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int[][] map = new int[52][52];
    static int[][] visited = new int[52][52];

    static int recId = 1;
    static int answer = 0;
    static int around = 0;
    static int r = 0;
    static int c = 0;
    static int d = 0;

    static int x = 0;
    static int xbm = 0;
    static int nr = 0;
    static int nc = 0;
    static int nd = 0;

    public static int solution(int[][] rectangle, int cc, int rr, int itemC, int itemR) {
        for(int[] rect : rectangle) {
            for(int i = rect[1]; i <= rect[3]; i++) {
                for(int j = rect[0]; j <= rect[2]; j++) {
                    map[i][j] = 1 << recId;  //110
                }
            }
            recId++;
        }

        r = rr; c = cc; visited[r][c]++;
        x = getNextRecId(r, c, 0); xbm = 1 << x;
        while(visited[r][c] < 2) {
            nr = r + dr[d]; nc = c + dc[d];

            if ((map[nr][nc] & map[r][c]) == 0 || !isOuter(nr, nc)) {
                nd = (d + 1) % 4;
                d = isOuter(r + dr[nd], c + dc[nd]) ? nd : (nd + 2) % 4;
                nr = r + dr[d]; nc = c + dc[d];
            }

            r = nr; c = nc;
            visited[r][c]++;
            around++;
            if(r == itemR && c == itemC) answer = around;

            if (map[r][c] - xbm > 0) {
                x = getNextRecId(r, c, x);
                xbm = 1 << x;
            }
        }

        return answer < (around - answer) ? answer : around - answer;
    }

    public static boolean isOuter(int r, int c) {
        if (map[r][c] == 0) return false;

        int outer = 0;
        int nr = r;
        int nc = c;

        for(int d = 0; d < 4; d++) {
            if (nr < 0 || nc < 0 || nr > 51 || nc > 51) return false;
            nr = r + dr[d];
            nc = c + dc[d];
            if((map[nr][nc] & map[r][c]) == 0) outer++;
        }

        return outer > 0 && outer < 4;
    }

    public static int getNextRecId(int r, int c, int v) {
        int ret = 1;
        int bm = 2;
        while((map[r][c] & bm) == 0) {
            bm <<= 1;
            ret++;
            if (ret == v) {
                bm <<= 1;
                ret++;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[][]tc = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        System.out.println("start");
        System.out.println(solution(tc, 1, 3, 7, 8));
    }
}
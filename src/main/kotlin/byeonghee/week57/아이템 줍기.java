package byeonghee.week57;

class BYEONGHEE_GETITEM {

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int[][] map = new int[52][52];
    static int[][] visited = new int[52][52];
    static int[][] rectangles;

    static int recId = 1;
    static int answer = 0;
    static int around = 0;
    static int r = 0;
    static int c = 0;
    static int x = 0;
    static int xbm = 0;
    static int d = 0;
    static int nr = 0;
    static int nc = 0;
    static int nd = 0;

    public static int solution(int[][] rectangle, int cc, int rr, int itemC, int itemR) {
        rectangles = rectangle;

        for(int[] rect : rectangles) {
            for(int i = rect[1]; i <= rect[3]; i++) {
                map[i][rect[0]] += 1 << recId;
                map[i][rect[2]] += 1 << recId;
            }
            for(int j = rect[0]+1; j < rect[2]; j++) {
                map[rect[1]][j] += 1 << recId;
                map[rect[3]][j] += 1 << recId;
            }
            recId++;
        }

        r = rr; c = cc; visited[r][c]++;
        while(visited[r][c] < 2) {
            nr = r + dr[d]; nc = c + dc[d];

            if ((map[nr][nc] & map[r][c]) == 0 || isInner((r + nr) / 2f, (c + nc) / 2f)) {
                nd = (d + 1) % 4;
                if ((map[r + dr[nd]][c + dc[nd]] & map[r][c]) == 0 || isInner(r + 0.5f * dr[nd], c + 0.5f * dc[nd])) {
                    nd = (nd + 2) % 4;
                }
                d = nd; nr = r + dr[d]; nc = c + dc[d];
            }

            r = nr; c = nc;
            visited[r][c]++;
            around++;
            if(r == itemR && c == itemC) answer = around;
        }

        return answer < (around - answer) ? answer : around - answer;
    }

    public static boolean isInner(float midR, float midC) {
        for(int[] rec : rectangles) {
            if (midR > rec[1] && midR < rec[3] && midC > rec[0] && midC < rec[2]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][]tc = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        System.out.println("start");
        System.out.println(solution(tc, 1, 3, 7, 8));
    }
}
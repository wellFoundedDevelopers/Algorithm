package byeonghee.week58;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BYEONGHEE_CODETREE_DESTROY_THE_TURRET {

    public static final int[] dr = { 0, 1, 0, -1 };
    public static final int[] dc = { 1, 0, -1, 0 };

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer stk;

    public static void readLine() throws Exception { stk = new StringTokenizer(br.readLine()); }
    public static int input() { return Integer.parseInt(stk.nextToken()); }

    public static int N, M, K, T;

    public static class Po {
        public int r, c, attack, lastTurn;

        public Po(int r, int c, int attack, int lastTurn) {
            this.r = r;
            this.c = c;
            this.attack = attack;
            this.lastTurn = lastTurn;
        }
    }

    public static class Laser {
        public int r, c;
        public HashSet<Integer> attacked;

        public Laser(int r, int c) {
            this.r = r;
            this.c = c;
            attacked = new HashSet<>(N * M);
        }
    }

    public static Comparator<Map.Entry<Integer, Po>> cmp = (a, b) -> {
        Po p1 = a.getValue(); Po p2 = b.getValue();

        if (p1.attack == p2.attack) {
            if (p1.lastTurn == p2.lastTurn) {
                if (p1.r + p1.c == p2.r + p2.c) {
                    return p2.c - p1.c;
                }
                else return p2.r + p2.c - p1.r - p1.c;
            }
            else return p2.lastTurn - p1.lastTurn;
        }
        else return p1.attack - p2.attack;
    };

    public static HashMap<Integer, Po> pos;
    public static List<Map.Entry<Integer, Po>> list;
    public static Po offense, defense;
    public static ArrayDeque<Laser> q;
    public static Laser tmp, nxt;
    public static HashSet<Integer> attacked;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        readLine();
        N = input(); M = input(); K = input();
        pos = new HashMap<>(N * M);
        q = new ArrayDeque<>(N * M);
        attacked = new HashSet<>(N * M);

        for(int i = 0; i < N; i++) {
            readLine();
            for(int j = 0; j < M; j++) {
                int v = input();
                if (v > 0) pos.put(i * M + j, new Po(i, j, v, 0));
            }
        }

        for(T = 1; T <= K; T++) {
            list = pos.entrySet().stream().sorted(cmp).collect(Collectors.toList());
            offense = list.get(0).getValue();
            if (list.size() < 2) break;
            defense = list.get(list.size()-1).getValue();

            offense.attack += N + M;
            offense.lastTurn = T;
            if (!tryLaser()) {
                tryBomb(defense.r, defense.c);
            }

            getReady();
        }

        list = pos.entrySet().stream().sorted(cmp).collect(Collectors.toList());
        System.out.println(list.get(list.size()-1).getValue().attack);
    }

    public static boolean tryLaser() {
        visited = new boolean[N][M];
        q.clear();
        q.add(new Laser(offense.r, offense.c));
        visited[offense.r][offense.c] = true;

        while(!q.isEmpty()) {
            tmp = q.removeFirst();
            for(int d = 0; d < 4; d++) {
                int nr = (tmp.r + dr[d] + N) % N;
                int nc = (tmp.c + dc[d] + M) % M;
                if (!pos.containsKey(nr * M + nc)) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                nxt = new Laser(nr, nc);
                nxt.attacked.addAll(tmp.attacked);
                nxt.attacked.add(nr * M + nc);
                if (nr == defense.r && nc == defense.c) {
                    attacked.addAll(nxt.attacked);
                    return true;
                }
                q.add(nxt);
            }
        }
        return false;
    }

    public static void tryBomb(int r, int c) {
        for(int i = r-1; i <= r+1; i++) for(int j = c-1; j <= c+1; j++) {
            int rr = (i + N) % N;
            int cc = (j + M) % M;
            if (rr == offense.r && cc == offense.c) continue;
            if (!pos.containsKey(rr * M + cc)) continue;
            attacked.add(rr * M + cc);
        }
    }

    public static void getReady() {
        int k; Po v;
        for(Map.Entry<Integer, Po> entry : list) {
            k = entry.getKey(); v = entry.getValue();
            if (attacked.contains(k)) {
                v.attack -= ((defense.r * M + defense.c == k) ? offense.attack : (offense.attack / 2));
                if (v.attack <= 0) pos.remove(k);
            }
            else if (offense.r != v.r || offense.c != v.c) {
                v.attack++;
            }
        }
        attacked.clear();
    }
}
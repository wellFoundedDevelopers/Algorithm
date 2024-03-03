package jimin.week52;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

class 풀링 {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] numbers = new int[n][n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (n > 1) {
            for (int i = 0; i < n; i += 2) {
                for (int j = 0; j < n; j += 2) {
                    int [] polling = {numbers[i][j], numbers[i + 1][j], numbers[i][j + 1], numbers[i + 1][j + 1]};
                    Arrays.sort(polling);
                    numbers[i / 2][j / 2] = polling[2];
                }
            }

            n /= 2;
        }

        System.out.println(numbers[0][0]);
    }
}

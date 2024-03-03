package jimin.week52;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N번째_큰_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] numbers = new ArrayList[n];
        for (int i = 0 ; i<n;i++) {
            numbers[i] = new ArrayList<Integer>();
        }
        StringTokenizer st;

        for (int i = n - 1; i >= 0; i--) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                numbers[j].add(Integer.parseInt(st.nextToken()));
            }
        }

        int x = 0;
        int y = 0;
        int cnt = 0;
        int result = 0;

        while (cnt < n) {
            Arrays.sort(numbers, Comparator.comparing((ArrayList<Integer> num1) -> num1.get(num1.size() -1)).reversed());

            result = numbers[0].remove(numbers[0].size() - 1);

            cnt += 1;
        }

        System.out.println(result);

    }
}

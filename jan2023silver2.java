package USACO;

import java.io.IOException;
import java.util.*;
import java.io.*;

public class jan2023silver2 {
    static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = Integer.parseInt(st.nextToken());
        long[] right = new long[N];
        long[] bot = new long[N];
        char[][] direction = new char[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            for (int c = 0; c < row.length(); c++) {
                direction[r][c] = row.charAt(c);
            }
            int price = Integer.parseInt(st.nextToken());
            right[r] = price;
        }
        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < N; c++) {
            int price = Integer.parseInt(st.nextToken());
            bot[c] = price;
        }
        int[][] counts = new int[N + 1][N + 1];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                counts[r][c] = 1;
            }
        }
        long totalCost = initialize(N, counts, direction, bot, right);
        pr.println(totalCost);

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken()) - 1;
            int fc = Integer.parseInt(st.nextToken()) - 1;
            totalCost = flip(fr, fc, totalCost, N, counts, direction, bot, right);
            pr.println(totalCost);
        }
        br.close();
        pr.close();
    }

    private static long flip(int fr, int fc, long totalCost, int n, int[][] counts, char[][] direction, long[] bot, long[] right) {
        char curDir = direction[fr][fc];
        char nextDir = 'D';
        int r = fr;
        int c = fc;
        int countChanges = -counts[fr][fc];
        if (curDir == 'R') {
            c++;
            nextDir = 'D';
        } else {
            r++;
            nextDir = 'R';
        }
        totalCost = grassEaten(totalCost, n, counts, direction, bot, right, countChanges, r, c);
        direction[fr][fc] = nextDir;
        countChanges = counts[fr][fc];
        r = fr;
        c = fc;
        if (nextDir == 'R') {
            c++;
        } else {
            r++;
        }
        totalCost = grassEaten(totalCost, n, counts, direction, bot, right, countChanges, r, c);
        return totalCost;
    }

    private static long grassEaten(long totalCost, int n, int[][] counts, char[][] direction, long[] bot, long[] right, int countChanges, int r, int c) {
        while (r < n && c < n) {
            counts[r][c] += countChanges;
            if (direction[r][c] == 'R') {
                c++;
            } else {
                r++;
            }
        }
        if (r == n) {
            totalCost += countChanges * bot[c];
        } else {
            totalCost += countChanges * right[r];
        }
        return totalCost;
    }

    private static long initialize(int n, int[][] counts, char[][] direction, long[] bot, long[] right) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (direction[r][c] == 'R') {
                    counts[r][c + 1] += counts[r][c];
                } else {
                    counts[r + 1][c] += counts[r][c];
                }
            }
        }
        long totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalCost += right[i] * counts[i][n];
        }
        for (int i = 0; i < n; i++) {
            totalCost += bot[i] * counts[n][i];
        }
        return totalCost;
    }
}

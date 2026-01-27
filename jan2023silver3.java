package USACO;

import java.io.*;
import java.util.*;

public class jan2023silver3 {
    static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(st.nextToken());
        int[] counts = new int[n];
        st = new StringTokenizer(br.readLine());
        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            counts[i] = Integer.parseInt(st.nextToken());
            totalCount += counts[i];
        }
        br.close();
        calculate(n, totalCount, counts, pr);
        pr.close();
    }

    private static void calculate(int n, int totalCount, int[] counts, PrintWriter pr) {
        char dir = 'R';
        int pos = 0;
        int moves = 0;
        while (moves < totalCount) {
            moves++;
            counts[pos]--;
            if (counts[pos] < 0) {
                break;
            }
            if (dir == 'L') {
                pr.print('L');
                if (pos == 0) {
                    dir = 'R';
                } else if (counts[pos - 1] < counts[pos]) {
                    dir = 'R';
                } else {
                    pos--;
                }
            } else {
                pr.print('R');
                if (pos == n - 1) {
                    dir = 'L';
                } else if (counts[pos + 1] < counts[pos]) {
                    dir = 'L';
                } else {
                    pos++;
                }
            }
        }
    }
}

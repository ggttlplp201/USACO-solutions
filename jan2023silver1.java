package USACO;

import java.io.IOException;
import java.util.*;
import java.io.*;

public class jan2023silver1 {
    static int pathLen = 0;
    static boolean isCycle = false;

    static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String start = br.readLine();
            String end = br.readLine();
            int ans = getAns(start, end);
            pr.println(ans);
        }
        br.close();
        pr.close();
    }

    private static int getAns(String start, String end) {
        if (start.equals(end)) {
            return 0;
        }
        HashSet<Character> uniqueChars = new HashSet<>();
        char[] fromLetters = start.toCharArray();
        char[] toLetters = end.toCharArray();
        boolean hasExtraChar = true;
        for (char fromLetter : fromLetters) {
            uniqueChars.add(fromLetter);
        }
        if (uniqueChars.size() == 52) {
            hasExtraChar = false;
        }
        int[] adj = new int[128];
        Arrays.fill(adj, -1);
        for (int i = 0; i < fromLetters.length; i++) {
            if (adj[fromLetters[i]] != -1 && adj[fromLetters[i]] != toLetters[i]) {
                return -1;
            } else {
                adj[fromLetters[i]] = toLetters[i];
            }
        }
        int sameNumCount = 0;
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != -1 && adj[i] == i) {
                sameNumCount++;
                adj[i] = -1;
            }
        }
        if (sameNumCount == uniqueChars.size()) {
            return 0;
        }
        int[] incomingDegree = getIncomingDegree(adj);
        int res = 0;
        boolean isFullCircle = true;
        boolean[] marked = new boolean[128];
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != -1 && !marked[i]) {
                pathLen = 0;
                isCycle = false;
                int[] edges = new int[128];
                boolean[] paths = new boolean[128];
                dfs(adj, incomingDegree, marked, paths, edges, i, 0);
                if (isCycle) {
                    res += pathLen + 1;
                } else {
                    isFullCircle = false;
                    res += pathLen;
                }
            }
        }
        if (isFullCircle && !hasExtraChar) {
            return -1;
        }
        return res;
    }

    private static void dfs(int[] adj, int[] incomingDegree, boolean[] marked, boolean[] paths, int[] edges, int i, int steps) {
        marked[i] = true;
        int w = adj[i];
        if (w == -1) {
            pathLen = steps;
            return;
        }
        if (!marked[w]) {
            paths[i] = true;
            edges[w] = i;
            dfs(adj, incomingDegree, marked, paths, edges, w, steps + 1);
            paths[i] = false;
        } else {
            if (paths[w]) {
                isCycle = isCycleA(incomingDegree, edges, i, w);
            }
            pathLen = steps + 1;
        }
    }

    private static boolean isCycleA(int[] incomingDegree, int[] edges, int i, int w) {
        for (int j = i; j != w; j = edges[j]) {
            if (incomingDegree[j] > 1) {
                return false;
            }
        }
        return incomingDegree[w] <= 1;
    }

    private static int[] getIncomingDegree(int[] adj) {
        int[] incomingDegree = new int[128];
        for (int j : adj) {
            if (j != -1) {
                incomingDegree[j]++;
            }
        }
        return incomingDegree;
    }
}

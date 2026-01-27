package USACO;

import java.util.*;

public class dec2022bronze2 {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int K = scanner.nextInt();
            String cowsStr = scanner.next();
            char[] cows = cowsStr.toCharArray();
            char[] patches = new char[cows.length];
            int min = findMinPatches(N, K, cows, patches);
            System.out.println(min);
            String patchesStr = new String(patches);
            System.out.println(patchesStr);
        }
    }

    private static int findMinPatches(int n, int k, char[] cows, char[] patches) {
        if (k == 0) {
            for (int i = 0; i < cows.length; i++) {
                patches[i] = cows[i];
            }
            return n;
        }

        Arrays.fill(patches, '.');

        int patchCount = 0;
        int furthestG = -1;
        int furthestH = -1;
        for (int i = 0; i < cows.length; i++) {
            if (cows[i] == 'G') {
                if (furthestG == -1 || i > furthestG + k) {
                    // Need a new G
                    furthestG = i + k;
                    if (furthestG >= patches.length) {
                        furthestG = patches.length - 1;
                    }
                    patchCount++;

                    while (patches[furthestG] != '.') {
                        furthestG--;
                    }
                    patches[furthestG] = 'G';
                }
            } else {
                if (furthestH == -1 || i > furthestH + k) {

                    furthestH = i + k;
                    if (furthestH >= patches.length) {
                        furthestH = patches.length - 1;
                    }
                    patchCount++;

                    while (patches[furthestH] != '.') {
                        furthestH--;
                    }
                    patches[furthestH] = 'H';
                }
            }
        }
        return patchCount;
    }
}


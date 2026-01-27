package USACO;

import java.util.*;

public class dec2022bronze1 {
    static void main() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = in.nextInt();
        }
        Arrays.sort(cows);
        long maxMoney = Long.MIN_VALUE;
        long maxTuition = -1;
        for (int i = 0; i < cows.length; i++) {
            long tuition = cows[i];
            long numOfCow = cows.length - i;
            long money = tuition * numOfCow;
            if (money > maxMoney) {
                maxMoney = money;
                maxTuition = tuition;
            }
        }
        System.out.println(maxMoney + " " + maxTuition);
    }
}

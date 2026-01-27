package USACO;

import java.util.*;

public class dec2022bronze3 {
    static void main() {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            in.nextLine();
            int N = in.nextInt();
            int M = in.nextInt();
            ArrayList<String> var = new ArrayList<>();
            ArrayList<Integer> out = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                var.add(in.next());
                out.add(in.nextInt());
            }
            boolean result = checkConf(M, N, var, out);
            if (!result) {
                System.out.println("LIE");
            } else {
                System.out.println("OK");
            }
        }
    }

    private static boolean checkConf(int m, int n, ArrayList<String> var, ArrayList<Integer> out) {
        for (int i = 0; i < var.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (var.get(i).equals(var.get(j)) && out.get(i).equals(out.get(j))) {
                    var.remove(i);
                    out.remove(i);
                    i--;
                    break;
                } else if (var.get(i).equals(var.get(j)) && !out.get(i).equals(out.get(j))) {
                    return false;
                }
            }
        }
        m = var.size();
        if (n == 1) {
            return true;
        } else if (m == 1) {
            return true;
        } else if (m == 2) {
            return true;
        } else if (n == 2 && m < 4) {
            return true;
        } else if (n == 2 && m == 4) {
            return checkBadCase(var, out);
        }
        return false;
    }

    private static boolean checkBadCase(ArrayList<String> var, ArrayList<Integer> out) {
        ArrayList<HashSet<String>> badCasesList = new ArrayList<>();
        HashSet<String> badCase = new HashSet<>();
        badCase.add("000");
        badCase.add("011");
        badCase.add("101");
        badCase.add("110");
        badCasesList.add(badCase);
        badCase = new HashSet<>();
        badCase.add("001");
        badCase.add("010");
        badCase.add("100");
        badCase.add("111");
        badCasesList.add(badCase);
        for (HashSet<String> bad : badCasesList) {
            boolean match = true;
            for (int i = 0; i < var.size(); i++) {
                String vars = var.get(i);
                int res = out.get(i);
                String varRes = vars + res;
                if (!bad.contains(varRes)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return false;
            }
        }
        return true;
    }
}

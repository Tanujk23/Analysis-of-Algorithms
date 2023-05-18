

import java.util.*;

public class Task5 {

    /**
     * @param stocks input stocks array
     * @param m      number of stocks
     * @param n      number of days
     * @param k
     */
    static void getProfit(int[][] stocks, int m, int n, int k) {

        // intialize two dimensional opt array 
        int[][] opt = new int[k + 1][n];
        ArrayList<ArrayList<Integer>> profitList = new ArrayList<ArrayList<Integer>>();
        // Populate default value as 0
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(opt[i], 0);
        }

        for (int kIterator = 1; kIterator <= k; kIterator++) {
            for (int sellDay = 1; sellDay < n; sellDay++) {
                for (int buyDay = 0; buyDay < sellDay; ++buyDay) {
                    for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
                        opt[kIterator][sellDay] = Math.max(opt[kIterator][sellDay],
                                Math.max(opt[kIterator][sellDay - 1],
                                        opt[kIterator - 1][buyDay] + stocks[stockIdx][sellDay]
                                                - stocks[stockIdx][buyDay]));
                    }
                }
            }
        }

        int kItr = k, nItr = n - 1;
        while (kItr > 0 && nItr > 0) {
            boolean isValid = false;
            for (int buyDay = 0; buyDay < nItr; ++buyDay) {
                for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
                    if (opt[kItr][nItr] == opt[kItr - 1][buyDay] + stocks[stockIdx][nItr]
                            - stocks[stockIdx][buyDay]) {
                        ArrayList<Integer> profitObj = new ArrayList<Integer>();
                        profitObj.add(stockIdx + 1);
                        profitObj.add(buyDay + 1);
                        profitObj.add(nItr + 1);
                        profitList.add(profitObj);
                        kItr--;
                        nItr = buyDay;
                        isValid = true;
                        break;
                    }
                }
                if (isValid) {
                    break;
                }
            }
            if (isValid)
                continue;

            if (opt[kItr][nItr] == opt[kItr][nItr - 1]) {
                nItr--;
                continue;
            }

        }

        if (profitList.size() == 0) {
            System.out.println(1);
            System.out.println(1 + " " + " " + 1);
            return;
        }

        for (int i = profitList.size() - 1; i >= 0; --i) {
            System.out.println(
                    profitList.get(i).get(0) + " " + profitList.get(i).get(1) + " " + profitList.get(i).get(2));
        }

    }

}

import java.util.*;

public class Task8 {
    /**
     * @param stocks input stocks list
     * @param m      number of stocks
     * @param n      number of days
     * @param c      limitter
     */
    static void getProfit(int[][] stocks, int m, int n, int c) {

        int[][] output = new int[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(output[i], 0);
        }
        ArrayList<ArrayList<Integer>> ansList = new ArrayList<ArrayList<Integer>>();
        for (int sellDayIndex = 1; sellDayIndex < n; ++sellDayIndex) {
            for (int buyDayIndex = 0; buyDayIndex <= sellDayIndex; ++buyDayIndex) {
                int temporaryMaximumProfit = Integer.MIN_VALUE;
                int temporaryMaximumProfitIndex = -1;
                for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
                    if ((stocks[stockIdx][sellDayIndex] - stocks[stockIdx][buyDayIndex]) > temporaryMaximumProfit) {
                        temporaryMaximumProfit = stocks[stockIdx][sellDayIndex] - stocks[stockIdx][buyDayIndex];
                        temporaryMaximumProfitIndex = stockIdx;
                    }
                }

                if (buyDayIndex - c - 1 > 0) {
                    if ((output[buyDayIndex - c - 1][0] + temporaryMaximumProfit) > output[sellDayIndex][0]) {
                        output[sellDayIndex][0] = output[buyDayIndex - c - 1][0] + temporaryMaximumProfit;
                        output[sellDayIndex][1] = buyDayIndex;
                        output[sellDayIndex][2] = temporaryMaximumProfitIndex;
                        output[sellDayIndex][3] = sellDayIndex;
                    }
                } else {
                    if (temporaryMaximumProfit > output[sellDayIndex][0]) {
                        output[sellDayIndex][0] = temporaryMaximumProfit;
                        output[sellDayIndex][1] = buyDayIndex;
                        output[sellDayIndex][2] = temporaryMaximumProfitIndex;
                        output[sellDayIndex][3] = sellDayIndex;
                    }
                }

            }
            if (output[sellDayIndex - 1][0] > output[sellDayIndex][0]) {
                output[sellDayIndex][0] = output[sellDayIndex - 1][0];
                output[sellDayIndex][1] = output[sellDayIndex - 1][1];
                output[sellDayIndex][2] = output[sellDayIndex - 1][2];
                output[sellDayIndex][3] = output[sellDayIndex - 1][3];
            }
        }

        int nIterator = n - 1;
        while (nIterator > 0) {
            ArrayList<Integer> profitObj = new ArrayList<Integer>();
            profitObj.add(output[nIterator][2]);
            profitObj.add(output[nIterator][1]);
            profitObj.add(output[nIterator][3]);
            ansList.add(profitObj);
            nIterator = output[nIterator][1] - c - 1;
        }

        if (ansList.size() == 0) {
            System.out.println(1);
            System.out.println(1 + " " + 1 + " " + 1);
            return;
        }

        for (int i = ansList.size() - 1; i >= 0; --i) {
            System.out.println((ansList.get(i).get(0) + 1) + " " + (ansList.get(i).get(1) + 1) + " "
                    + (ansList.get(i).get(2) + 1));
        }

    }
}

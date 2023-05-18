

import java.util.Arrays;

public class Task3a {

    static int[][] M;
    static int[][] stocks;
    static int[] minimumList;
    static int sellingIndex = 0;
    static int buyingIndex = 0;
    static int tempBuyIndex = 0;
    static int tempSellingIndex = 0;
    static int globalBuyingIndex = 0;

    /**
     * @param stock
     * @param n number of days
     */
    static void changeMinList(int stock, int n) {
        if (minimumList[n - 1] > stocks[stock][n]) {
            tempBuyIndex = n;
            minimumList[n] = stocks[stock][n];
        } else {
            minimumList[n] = minimumList[n - 1];
        }
    }

    /**
     * @param stock
     * @param n numnber of days
     * @return
     */
    static int getStockProfit(int stock, int n) {
        if (n == 0) {
            return 0;
        }

        if (M[stock][n - 1] == -1) {
            M[stock][n - 1] = getStockProfit(stock, n - 1);
        }

        if (stocks[stock][n] <= stocks[stock][n - 1]) {
            changeMinList(stock, n);
            return M[stock][n - 1];
        }

        changeMinList(stock, n);

        if (M[stock][n - 1] >= (stocks[stock][n] - minimumList[n])) {
            return M[stock][n - 1];
        }

        tempSellingIndex = n;
        buyingIndex = tempBuyIndex;
        return (stocks[stock][n] - minimumList[n]);
    }
    


    /**
     * @param inputStocksList
     * @param m number of stocks
     * @param n number of days
     * @return
     */
    public static int[] getProfit(int[][] inputStocksList, int m, int n) {

        stocks = inputStocksList;
        M = new int[m][n];
        minimumList = new int[n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(M[i], -1);
        }

        int maximumProfit = 0;
        int stockIndex = 0;

        for (int i = 0; i < m; i++) {
            minimumList[0] = stocks[i][0];
            tempBuyIndex = 0;
            M[i][n - 1] = getStockProfit(i, n - 1);
            if (M[i][n - 1] > maximumProfit) {
                maximumProfit = M[i][n - 1];
                stockIndex = i;
                sellingIndex = tempSellingIndex;
                globalBuyingIndex = buyingIndex;
            }
        }

        return new int[] { stockIndex, globalBuyingIndex, sellingIndex };

    }
}

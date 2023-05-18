

public class Task1 {
    /**
     * @param stocks input stocks array
     * @param m number of stocks
     * @param n number of days
     * @return
     */
    static int[] getProfit(int[][] stocks, int m, int n) {
        int maxProfit = 0;
        int buyingIndex = 0;
        int sellingIndex = 0;
        int stockIndex = 0;
        for (int stock = 0; stock < m; stock++) {
            for (int j = 0; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // calulating profit
                    int profit = stocks[stock][k] - stocks[stock][j];
                    if (profit < 0)
                        continue;
                    if (profit > maxProfit) {
                        // update maximum profit to profit and store selling index, buying index, stock index
                        maxProfit = profit;
                        sellingIndex = k;
                        buyingIndex = j;
                        stockIndex = stock;
                    }
                }
            }
        }

        return new int[] { stockIndex, buyingIndex, sellingIndex };
    }
}



public class Task2 {
    /**
     * @param stocks input stocks array
     * @param m number of stocks
     * @param n number of days
     * @return
     */
    static int[] getProfit(int[][] stocks, int m, int n) {

        int currentMax = 0;
        int maxProfit = 0;
        int minBuyingValue = 0;
        int buyingIndex = 0;
        int sellingIndex = 0;
        int stockIndex = 0;
        int tempBuyingIndex = 0;
        int tempSellingIndex = 0;

        for (int stock = 0; stock < m; stock++) {
            // initialize minimum buying value
            minBuyingValue = stocks[stock][0];
            currentMax = 0;
            tempSellingIndex = 0;
            tempBuyingIndex = 0;
            for (int sell_day = 0; sell_day < n; sell_day++) {
                // compare minimum buying value to sellvalue and update temporary buying index
                if (minBuyingValue > stocks[stock][sell_day]) {
                    minBuyingValue = stocks[stock][sell_day];
                    tempBuyingIndex = sell_day;
                } else {
                    // calculate current maximum
                    currentMax = stocks[stock][sell_day] - minBuyingValue;
                    // compare current maximum to maximum profit, update maximum profit and store indexes
                    if (currentMax > maxProfit) {
                        maxProfit = currentMax;
                        sellingIndex = sell_day;
                        buyingIndex = tempBuyingIndex;
                        stockIndex = stock;
                    }
                }
            }
        }

        return new int[] { stockIndex, buyingIndex, sellingIndex };

    }
}



public class Task3b {
    int[][] M;

    /**
     * @param stocks input stocks array
     * @param m number of stocks
     * @param n number of days
     * @return
     */
    public static int[] getProfit( int[][] stocks, int m, int n ) {

        int minimumBuyingValue = 0;
        int buyingIndex = 0;
        int sellingIndex = -1;
        int tempSellingIndex = 0;
        int tempBuyingIndex = 0;
        int maximumProfit = Integer.MIN_VALUE;
        int stockIndex = 0;
        int gloablBuyingIndex = 0;

        int[][] M = new int[m][n];

        for ( int stock = 0; stock < m; stock++ ) {
            minimumBuyingValue = stocks[stock][0];
            M[stock][0] = 0;
            tempSellingIndex = 0;
            tempBuyingIndex = 0;
            for ( int j = 1; j < n; j++ ) {
                if ( stocks[stock][j] < minimumBuyingValue ) {
                    minimumBuyingValue = stocks[stock][j];
                    tempBuyingIndex = j;
                }

                if (M[stock][j - 1] < (stocks[stock][j] - minimumBuyingValue)) {
                    M[stock][j] = (stocks[stock][j] - minimumBuyingValue);
                    tempSellingIndex = j;
                    buyingIndex = tempBuyingIndex;
                } else {
                    M[stock][j] = M[stock][j - 1];
                }

            }
            if ( maximumProfit < M[stock][n - 1] ) {
                maximumProfit = M[stock][n - 1];
                sellingIndex = tempSellingIndex;
                gloablBuyingIndex = buyingIndex;
                stockIndex = stock;
            }
        }

        return new int[] { stockIndex, gloablBuyingIndex, sellingIndex };
    }
}

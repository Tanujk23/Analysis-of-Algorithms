

import java.util.*;

public class Task4 {

    public ProfitSet tempProfitSet;
    public ProfitSet defaultList;

    public Task4(ProfitSet tempProfitSet, ProfitSet defaultList) {
        this.tempProfitSet = tempProfitSet;
        this.defaultList = defaultList;
    }

    /**
     * @param kIterator
     * @param k
     * @param lastEndDay
     * @param m number of stocks
     * @param n number of days
     * @param stocks input stocks array
     */
    public void getProfit(int kIterator, int k, int lastEndDay, int m, int n, int[][] stocks) {

        if (kIterator == 0) {

            if (tempProfitSet.outputDates.size() <= k && tempProfitSet.maximumProfit > defaultList.maximumProfit) {

                defaultList.maximumProfit = tempProfitSet.maximumProfit;
                defaultList.outputDates.clear();

                for (Triplet<Integer, Integer, Integer> t : tempProfitSet.outputDates) {
                    defaultList.outputDates.add(t);
                }

            }
        } else {
            for (int buyDay = lastEndDay; buyDay <= n; ++buyDay) {
                for (int sellDay = buyDay; sellDay <= n; ++sellDay) {

                    getProfit(kIterator - 1, k, lastEndDay, m, n, stocks);

                    int tempMaxProfit = Integer.MIN_VALUE;
                    int maxProfitStockIndex = 0;
                    
                    for (int stockIndex = 1; stockIndex <= m; ++stockIndex) {
                        if (stocks[stockIndex - 1][sellDay - 1] - stocks[stockIndex - 1][buyDay - 1] > tempMaxProfit) {
                            tempMaxProfit = stocks[stockIndex - 1][sellDay - 1] - stocks[stockIndex - 1][buyDay - 1];
                            maxProfitStockIndex = stockIndex;
                        }
                    }

                    int initialMaxProfit = tempProfitSet.maximumProfit;
                    tempProfitSet.maximumProfit += tempMaxProfit;
                    Triplet trplt = new Triplet(maxProfitStockIndex, buyDay, sellDay);
                    tempProfitSet.outputDates.add(trplt);

                    getProfit(kIterator - 1, k, sellDay, m, n, stocks);
                    
                    tempProfitSet.maximumProfit = initialMaxProfit;
                    tempProfitSet.outputDates.remove(tempProfitSet.outputDates.size() - 1);
                }

            }
        }

    }

}

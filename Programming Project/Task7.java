import java.sql.SQLOutput;
import java.util.*;

public class Task7 {

    static void getProfit(int nIterator, int[][] stocks, int m, int n, int c, Transaction tempProfit,
            Transaction profitAns) {
        if (nIterator == -1) {

            ArrayList<Integer> lastElementList;
            lastElementList = tempProfit.transaction.get(tempProfit.transaction.size() - 1);
            int lastElement = lastElementList.get(1);

            if (tempProfit.transaction.size() != 0 && lastElement != Integer.MIN_VALUE) {
                if (tempProfit.maximumProfit > profitAns.maximumProfit) {
                    profitAns.maximumProfit = tempProfit.maximumProfit;
                    profitAns.transaction = tempProfit.transaction;
                }
            }
        } else {

            getProfit(nIterator-1, stocks, m, n, c,tempProfit, profitAns);

            ArrayList<Integer> lastElementList;
            lastElementList = tempProfit.transaction.get(tempProfit.transaction.size() - 1);
            int lastElement = lastElementList.get(1);
            int lastButOneElement = lastElementList.get(2);

            if ((tempProfit.transaction.size() != 0) && (lastElement == Integer.MIN_VALUE)) {

                int sellDay = lastButOneElement;
                int temporaryMaximumProfit = Integer.MIN_VALUE;
                int selectedStock = -1;
                int resetMaximumProfit = tempProfit.maximumProfit;

                for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
                    if ((stocks[stockIdx][sellDay] - stocks[stockIdx][nIterator]) > temporaryMaximumProfit) {
                        temporaryMaximumProfit = stocks[stockIdx][sellDay] - stocks[stockIdx][nIterator];
                        selectedStock = stockIdx;
                    }
                }

                int tempProfitListSize = tempProfit.transaction.size();

                tempProfit.maximumProfit += temporaryMaximumProfit;
                tempProfit.transaction.get(tempProfitListSize - 1).set(0, selectedStock);
                tempProfit.transaction.get(tempProfitListSize - 1).set(1, nIterator);

                getProfit(nIterator - 1, stocks, m, n, c, tempProfit, profitAns);
                tempProfit.maximumProfit = resetMaximumProfit;
                tempProfit.transaction.get(tempProfitListSize - 1).set(0, -1);
                tempProfit.transaction.get(tempProfitListSize - 1).set(1, Integer.MIN_VALUE);

            } else {
                ArrayList<Integer> lElementList;
                lElementList = tempProfit.transaction.get(tempProfit.transaction.size() - 1);
                int lElement = lElementList.get(1);

                if (tempProfit.transaction.size() == 0 || (lElement >= nIterator + c + 1)) {
                    ArrayList<Integer> profitObj = new ArrayList<Integer>();
                    profitObj.add(-1);
                    profitObj.add(Integer.MIN_VALUE);
                    profitObj.add(nIterator);

                    tempProfit.transaction.add(profitObj);

                    getProfit(nIterator - 1, stocks, m, n, c, tempProfit, profitAns);

                    tempProfit.transaction.remove(tempProfit.transaction.size() - 1);
                }
            }

        }

    }

}

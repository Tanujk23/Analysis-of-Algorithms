

import java.util.*;

public class Stocks {

    static int[][] stocks;
    static int[][] M;

    static void print(int[] profit) {
        System.out.println((profit[0] + 1) + " " + (profit[1] + 1) + " " + (profit[2] + 1));
    }

    static int[][] input(int m, int n) {
        Scanner sc = new Scanner(System.in);

        int[][] stocks = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Taking input for stocks and sell values.
                stocks[i][j] = sc.nextInt();
            }
        }
        return stocks;
    }

    public static void main(String args[]) {

        // Taking Input for m stock for n days
        Scanner sc = new Scanner(System.in);

        long startTime = 0;

        int[] profit = null;

        switch (args[0]) {
            case "1": {
                int m;
                int n;

                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);

                profit = Task1.getProfit(stocks, m, n);
                print(profit);
                break;
            }
            case "2": {
                int m;
                int n;

                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);
                profit = Task2.getProfit(stocks, m, n);

                print(profit);
                break;
            }
            case "3a": {
                int m;
                int n;

                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);

                profit = Task3a.getProfit(stocks, m, n);
                print(profit);
                break;
            }
            case "3b": {
                int m;
                int n;

                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);

                profit = Task3b.getProfit(stocks, m, n);
                print(profit);
                break;
            }
            case "4": {
                int m;
                int n;

                int k = sc.nextInt();
                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);

                int kIterator = k;

                ProfitSet defaultList = new ProfitSet();
                Triplet triplet = new Triplet(1, 1, 1);
                defaultList.outputDates.add(triplet);

                ProfitSet tempProfitSet = new ProfitSet();
                Task4 profitObj = new Task4(tempProfitSet, defaultList);
                profitObj.getProfit(kIterator, k, 1, m, n, stocks);
                for (Triplet<Integer, Integer, Integer> trplt : defaultList.outputDates) {
                    System.out.println(trplt.stock + " " + trplt.buyDay + " " + trplt.sellDay);
                }
                break;
            }
            case "5": {
                int m;
                int n;
                int k = sc.nextInt();
                m = sc.nextInt();
                n = sc.nextInt();
                stocks = input(m, n);
                Task5.getProfit(stocks, m, n, k);

                break;
            }
            case "6a": {
                int k = sc.nextInt();
                int m = sc.nextInt();
                int n = sc.nextInt();
                stocks = input(m, n);
                Task6a.task6a(stocks, m, n, k);
                break;
            }
            case "6b": {
                int k = sc.nextInt();
                int m = sc.nextInt();
                int n = sc.nextInt();
                stocks = input(m, n);
                Task6b.getProfit(stocks, m, n, k);
                break;
            }
            case "7": {
                int c = sc.nextInt();
                int m = sc.nextInt();
                int n = sc.nextInt();
                stocks = input(m, n);

                Transaction tempProfit = new Transaction();
                Transaction profitAns = new Transaction();
                ArrayList<Integer> tempList = new ArrayList<Integer>();
                tempList.add(0);
                tempList.add(0);
                tempList.add(0);
                tempProfit.transaction.add(tempList);

                int nIterator = n-1;
                Task7.getProfit(nIterator,stocks,m,n,c,tempProfit,profitAns);

                if(profitAns.transaction.size() == 0){
                    System.out.println(1);
                    System.out.println(1+" "+1+" "+1);
                }
                System.out.println(profitAns.transaction.size());
                for (int i = profitAns.transaction.size()-1; i >= 0; --i) {
                    System.out.println((profitAns.transaction.get(i).get(0)+1)+" "+(profitAns.transaction.get(i).get(1)+1)+" "+(profitAns.transaction.get(i).get(2)+1));
                }
                System.out.println("We came half way through the solution but not able to complete it.we completed all the other tasks other than this.");
                break;
            }
            case "8" :{
                int c = sc.nextInt();
                int m = sc.nextInt();
                int n = sc.nextInt();
                stocks = input(m, n);
                Task8.getProfit(stocks, m, n, c);
                break;
            }
            case "9a":{
                int c=sc.nextInt();
                int m=sc.nextInt();
                int n=sc.nextInt();
                stocks = input(m,n);
                Task9a.task9a(stocks, m, n,c);
                break;

            }
            case "9b":{
                int c=sc.nextInt();
                int m=sc.nextInt();
                int n=sc.nextInt();
                stocks = input(m,n);
                Task9b.getProfit(stocks, m, n,c);
                break;
            }
            default:
                System.out.println(" Please choose another option");
        }
    }

}

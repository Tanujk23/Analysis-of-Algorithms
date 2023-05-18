

import java.util.ArrayList;
import java.util.Arrays;

public class Task6a {

	static int[][] opt;
	static int[][] globalStocksList;
	static int[][][][] tempProfit;

	/**
	 * @param kIterator
	 * @param sellDay
	 * @param m
	 */
	static void getProfit(int kIterator, int sellDay, int m) {
		if (opt[kIterator][sellDay] != Integer.MIN_VALUE) {
			return;
		}
		if (kIterator == 0 || sellDay == 0) {
			opt[kIterator][sellDay] = 0;
			return;
		}

		for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
			getProfit(kIterator - 1, sellDay - 1, m);
			if (opt[kIterator - 1][sellDay - 1] - globalStocksList[stockIdx][sellDay - 1] > tempProfit[kIterator][sellDay][stockIdx][0]) {
				tempProfit[kIterator][sellDay][stockIdx][0] = opt[kIterator - 1][sellDay - 1] - globalStocksList[stockIdx][sellDay - 1];
				tempProfit[kIterator][sellDay][stockIdx][1] = sellDay - 1;
			}
			getProfit(kIterator, sellDay - 1, m);
			if (tempProfit[kIterator][sellDay - 1][stockIdx][0] > tempProfit[kIterator][sellDay][stockIdx][0]) {
				tempProfit[kIterator][sellDay][stockIdx][0] = tempProfit[kIterator][sellDay - 1][stockIdx][0];
				tempProfit[kIterator][sellDay][stockIdx][1] = tempProfit[kIterator][sellDay - 1][stockIdx][1];
			}

			opt[kIterator][sellDay] = Math.max(opt[kIterator][sellDay], Math.max(opt[kIterator][sellDay - 1],
					globalStocksList[stockIdx][sellDay] + tempProfit[kIterator][sellDay][stockIdx][0]));
		}
	}

	/**
	 * @param stocks input stocks list
	 * @param m number of stocks
	 * @param n number of days
	 * @param k
	 */

	static void task6a(int[][] stocks, int m, int n, int k) {
		globalStocksList = stocks;
		opt = new int[k + 1][n];
		for (int i = 0; i < k + 1; i++) {
			Arrays.fill(opt[i], Integer.MIN_VALUE);
		}
		tempProfit = new int[k + 1][n][m][2];
		for (int i = 0; i < k + 1; i++) {
			for (int j = 0; j < n; j++) {
				for (int r = 0; r < m; r++) {
					Arrays.fill(tempProfit[i][j][r], Integer.MIN_VALUE);
				}
			}
		}

		getProfit(k, n - 1, m);

		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
		int kIterator = k, nIterator = n - 1;
		
		while (kIterator > 0 && nIterator > 0) {
			boolean isValid = false;
			for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
				if (opt[kIterator][nIterator] == stocks[stockIdx][nIterator] + tempProfit[kIterator][nIterator][stockIdx][0]) {
					ArrayList<Integer> profitObj = new ArrayList<Integer>();
					profitObj.add(stockIdx + 1);
					profitObj.add(tempProfit[kIterator][nIterator][stockIdx][1] + 1);
					profitObj.add(nIterator + 1);
					ans.add(profitObj);
					nIterator = tempProfit[kIterator][nIterator][stockIdx][1];
					kIterator--;
					isValid = true;
					break;
				}
			}
			if (isValid)
				continue;

			if (opt[kIterator][nIterator] == opt[kIterator][nIterator - 1]) {
				nIterator--;
				continue;
			}

		}

		if (ans.size() == 0) {
			System.out.println(1);
			System.out.println(1 + " " + 1 + " " + 1);
			return;
		}

		for (int i = ans.size() - 1; i >= 0; --i) {
			System.out.println(ans.get(i).get(0) + " " + ans.get(i).get(1) + " " + ans.get(i).get(2));
		}

	}
}

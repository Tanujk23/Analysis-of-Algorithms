

import java.util.*;

public class Task6b {

	/**
	 * @param stocks input stocks list
	 * @param m      number of stocks
	 * @param n      number of days
	 * @param k
	 */
	static void getProfit(int[][] stocks, int m, int n, int k) {

		int[][] opt = new int[k + 1][n];

		// Populate default value
		for (int i = 0; i < k + 1; i++) {
			Arrays.fill(opt[i], 0);
		}

		int[][][][] tempProfit = new int[k + 1][n][m][2];
		// Fill deault value
		for (int i = 0; i < k + 1; i++) {
			for (int j = 0; j < n; j++) {
				for (int r = 0; r < m; r++) {
					Arrays.fill(tempProfit[i][j][r], Integer.MIN_VALUE);
				}
			}
		}

		for (int kIterator = 1; kIterator <= k; kIterator++) {
			for (int sellDay = 1; sellDay < n; sellDay++) {
				for (int stockIdx = 0; stockIdx < m; ++stockIdx) {
					if (opt[kIterator - 1][sellDay - 1] - stocks[stockIdx][sellDay - 1] > tempProfit[kIterator][sellDay][stockIdx][0]) {
						tempProfit[kIterator][sellDay][stockIdx][0] = opt[kIterator - 1][sellDay - 1] - stocks[stockIdx][sellDay - 1];
						tempProfit[kIterator][sellDay][stockIdx][1] = sellDay - 1;
					}
					if (tempProfit[kIterator][sellDay - 1][stockIdx][0] > tempProfit[kIterator][sellDay][stockIdx][0]) {
						tempProfit[kIterator][sellDay][stockIdx][0] = tempProfit[kIterator][sellDay - 1][stockIdx][0];
						tempProfit[kIterator][sellDay][stockIdx][1] = tempProfit[kIterator][sellDay - 1][stockIdx][1];
					}
					opt[kIterator][sellDay] = Math.max(opt[kIterator][sellDay], Math.max(opt[kIterator][sellDay - 1],
							stocks[stockIdx][sellDay] + tempProfit[kIterator][sellDay][stockIdx][0]));
				}
			}
		}

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

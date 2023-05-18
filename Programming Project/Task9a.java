

import java.util.*;

public class Task9a {
	
	static int[] output;
	static int[][] globalStocksList;
	static int[][][] tempProfitList;
	
	/**
	 * @param m number of stocks
	 * @param sellDayIndex index
	 * @param c limitter
	 */
	static void getProfit(int m,int sellDayIndex,int c) {
		if (output[sellDayIndex] != Integer.MIN_VALUE) {
	        return;
	    }
	    if (sellDayIndex == 0) {
	        output[sellDayIndex] = 0;
	        return;
	    }

	    for (int stockIdx = 0; stockIdx < m; ++stockIdx) {                   
	       
	        int peekStockValue;
	        if (sellDayIndex-1-c-1 >= 0) {
	            getProfit(m,sellDayIndex-1-c-1,c);
	                
	            peekStockValue = output[sellDayIndex-1-c-1];
	        }
	        else {
	            peekStockValue = 0;
	        }
	        if (peekStockValue - globalStocksList[stockIdx][sellDayIndex-1] > tempProfitList[sellDayIndex][stockIdx][0]) {
	            tempProfitList[sellDayIndex][stockIdx][0] = peekStockValue - globalStocksList[stockIdx][sellDayIndex-1];
	            tempProfitList[sellDayIndex][stockIdx][1] = sellDayIndex-1;
	        }
	        getProfit(m,sellDayIndex-1, c);                
	        if (tempProfitList[sellDayIndex-1][stockIdx][0] > tempProfitList[sellDayIndex][stockIdx][0]) {
	            tempProfitList[sellDayIndex][stockIdx][0] = tempProfitList[sellDayIndex-1][stockIdx][0];
	            tempProfitList[sellDayIndex][stockIdx][1] = tempProfitList[sellDayIndex-1][stockIdx][1];
	        }
	        output[sellDayIndex] = Math.max(output[sellDayIndex], Math.max(output[sellDayIndex - 1], 
	                globalStocksList[stockIdx][sellDayIndex] + tempProfitList[sellDayIndex][stockIdx][0]));
	    } 
	}
	
	static void task9a(int[][] stocks,int m,int n,int c) {
		 output = new int[n];
		 globalStocksList = stocks;
		 tempProfitList = new int[n][m][2];
		 
		 for(int i=0;i<n;i++) {
			 Arrays.fill(output, Integer.MIN_VALUE);
		 }
		 
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<m;j++) {
				 Arrays.fill(tempProfitList[i][j], Integer.MIN_VALUE);
			 }
		 }
	    getProfit(m,n-1,c);

	    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
	    int nIterator = n-1;
	    while (nIterator > 0) {
	        boolean isValid = false;
	        for (int stockIdx = 0; stockIdx < m; ++stockIdx) {            
	            if (output[nIterator] == globalStocksList[stockIdx][nIterator] + tempProfitList[nIterator][stockIdx][0]) {
	            	ArrayList<Integer> profitObj = new ArrayList<Integer>();
	            	profitObj.add(stockIdx+1);
	            	profitObj.add( tempProfitList[nIterator][stockIdx][1]+1);
	            	profitObj.add(nIterator+1);
	                ans.add(profitObj);
	                nIterator = tempProfitList[nIterator][stockIdx][1]-c-1;
	                isValid = true;
	                break;      
	            }
	        }
	        if (isValid) continue;

	        if (output[nIterator] == output[nIterator-1]) {
	            nIterator--;
	            continue;
	        }

	    }
	    
	      
	    if (ans.size() == 0) {
	        System.out.println(1);
	        System.out.println(1+" "+1+" "+1);
	        return;
	    } 

	    
	    
	    for (int i = ans.size()-1; i>=0; --i) {
	        System.out.println(ans.get(i).get(0) + " " + ans.get(i).get(1) + " " + ans.get(i).get(2));
	    }  
	   
	}
}

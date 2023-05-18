
import java.util.*;

public class Task9b {
	
	/**
	 * @param stocks input stocks list
	 * @param m number of stocks
	 * @param n number of days
	 * @param c limitter
	 */
	static void getProfit(int[][] stocks,int m,int n,int c) {
		
	int[] output = new int[n];  
    int[][][] tempProfitList = new int[n][m][2];
    
    for (int i = 0; i < n ; i++) {
		Arrays.fill(output, 0);
	}
    
    for(int i=0;i<n;i++) {
    	for(int j=0;j<m;j++) {
    		Arrays.fill(tempProfitList[i][j], Integer.MIN_VALUE);
    	}
    }
    
    
    for (int sellDayIndex = 1; sellDayIndex < n; sellDayIndex++) {
        for (int stockIdx = 0; stockIdx < m; ++stockIdx) {                   
            int peekStockValue;
            if (sellDayIndex-1-c-1 >= 0) {
                peekStockValue = output[sellDayIndex-1-c-1];
            }
            else {
                peekStockValue = 0;
            }
            if (peekStockValue - stocks[stockIdx][sellDayIndex-1] > tempProfitList[sellDayIndex][stockIdx][0]) {
                tempProfitList[sellDayIndex][stockIdx][0] = peekStockValue - stocks[stockIdx][sellDayIndex-1];
                tempProfitList[sellDayIndex][stockIdx][1] = sellDayIndex-1;
            }
            if (tempProfitList[sellDayIndex-1][stockIdx][0] > tempProfitList[sellDayIndex][stockIdx][0]) {
                tempProfitList[sellDayIndex][stockIdx][0] = tempProfitList[sellDayIndex-1][stockIdx][0];
                tempProfitList[sellDayIndex][stockIdx][1] = tempProfitList[sellDayIndex-1][stockIdx][1];
            }
            output[sellDayIndex] = Math.max(output[sellDayIndex], Math.max(output[sellDayIndex - 1], 
                    stocks[stockIdx][sellDayIndex] + tempProfitList[sellDayIndex][stockIdx][0]));
        } 
    }
    
    
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    int nIterator = n-1;
    while (nIterator > 0) {
        boolean isValid = false;
        for (int stockIdx = 0; stockIdx < m; ++stockIdx) {            
            if (output[nIterator] == stocks[stockIdx][nIterator] + tempProfitList[nIterator][stockIdx][0]) {
            	ArrayList<Integer> profitObj = new ArrayList<Integer>();
            	profitObj.add(stockIdx+1);
            	profitObj.add(tempProfitList[nIterator][stockIdx][1]+1);
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

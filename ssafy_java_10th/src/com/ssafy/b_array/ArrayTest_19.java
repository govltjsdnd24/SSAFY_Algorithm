package com.ssafy.b_array;

public class ArrayTest_19 {

    public static void main(String[] args) {

        char[][] grid = { { '2', '3', '1', '4' },
                { '1', 'X', '3', '2' },
                { '3', '4', 'X', 'X' },
                { 'X', '4', '1', '5' } };

        int sum = 0;
        // TODO: X로 표시된 항목의 좌우 숫자의 합을 구하시오.
        for(int i=0;i<grid.length;i++) {
        	for(int j=0;j<grid[i].length;j++) {
        		if(grid[i][j]=='X') {
        			if(j>0 && grid[i][j-1]!='X')
        				sum+=grid[i][j-1]-'0';
        			if(j<grid[i].length-1&& grid[i][j+1]!='X')
        				sum+=grid[i][j+1]-'0';
        		}
        	}
        }
        // END
        System.out.println(sum);
    }
}

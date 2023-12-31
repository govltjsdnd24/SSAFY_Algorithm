package h_august_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Country{
	private int r,c;
	public Country(int r,int c){
		this.r=r; this.c=c;
	}
	public int getR() {return r;}
	public int getC() {return c;}
	@Override
	public String toString() {
		return "Country [r=" + r + ", c=" + c + "]";
	}
	
}

public class BAEKJOON_16234_인구이동 {

	static int [][]countries;
	static boolean [][]visited;
	
	static int N,L,R;
	
	static int[]dr= {1,-1,0,0};
	static int[]dc= {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		N=scan.nextInt();
		L=scan.nextInt();
		R=scan.nextInt();
		
		countries=new int[N][N];
		
		
		int i,j;
		
		int counter=0;
		int tracker;
		
		for(i=0;i<N;i++)
			for(j=0;j<N;j++)
				countries[i][j]=scan.nextInt();

		 do {
			visited=new boolean[N][N];
			tracker=0;
			for(i=0;i<N;i++) {
				for(j=0;j<N;j++) {
					if(!visited[i][j])
						tracker+=bfs(i,j);
				}
			}
			if(tracker>0)
				counter++;
		} while(tracker>0);
		
		System.out.println(counter);
	}
	
	public static int bfs(int x,int y) {
		Queue<Country> queue=new LinkedList<Country>();
		queue.offer(new Country(x,y));
		
		int r,c,nr,nc,i,diff;
		int sum=countries[x][y];
		List<Country> union=new ArrayList<Country>();
		int counter=0;
		visited[x][y]=true;
		union.add(queue.peek());
		
		while(!queue.isEmpty()) {
			Country country=queue.poll();
			r=country.getR();
			c=country.getC();
			
			
			for(i=0;i<4;i++) {
				nr=r+dr[i];
				nc=c+dc[i];
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					diff=Math.abs(countries[nr][nc]-countries[r][c]);
					if(!visited[nr][nc] && diff<=R && diff>=L) {
						visited[nr][nc]=true;
						Country temp=new Country(nr,nc);
						queue.offer(temp);
						union.add(temp);
						sum+=countries[nr][nc];
						counter++;
						
					}
				}
				
			}
		}
		
		int avg=sum/union.size();
		
		//set them to avg
		for(i=0;i<union.size();i++) {
			countries[union.get(i).getR()][union.get(i).getC()]=avg;
		}

		return counter;
	}

}

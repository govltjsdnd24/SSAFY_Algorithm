package h_august_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Block{
	int r, c,size;
	Block(int r, int c,int size){
		this.r=r; this.c=c; this.size=size;
	}
	@Override
	public String toString() {
		return "Block [r=" + r + ", c=" + c + ", size="+ size+"]";
	}
	
}

public class SWEA_5656_벽돌깨기 {

	static int N,W,H;
	static int[][]screen;
	 
	static Block[] combination;
	static boolean[][]visited;
	
	static int []dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	
	static int output;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int T=Integer.parseInt(st.nextToken());
		
		for(int testcase=1;testcase<=T;testcase++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			output=Integer.MAX_VALUE;
			
			screen= new int[H][W];
			Block[] array= new Block[W];
			combination=new Block[N];
			
			for (int i = 0; i < H; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					screen[i][j]=Integer.parseInt(st.nextToken());
					if(array[j]==null && screen[i][j]!=0) {
						array[j]=new Block(i,j,screen[i][j]);
					}
				}
			}
			findCombination(screen,array, 0,0);
			
			System.out.println("#"+testcase+" "+output);
		}

	}
	
	public static void findCombination(int [][]screen, Block[] array,int oIndex,int cIndex) {
		
		if(cIndex==combination.length) {
			System.out.println("Outcome: "+Arrays.toString(combination));
			System.out.println();
			System.out.println();
			output=Math.min(output, countnonZeros(screen));
			return;
		}
		
		for(int i=oIndex;i<array.length;i++) {
			combination[cIndex]=array[i];
			int[][]tScreen=copyScreen(screen);
			System.out.println("before explosion: on row "+i+ " ,"+(cIndex+1) + "th explosion.\n Removing "+ array[i]);
			print(tScreen);
			if(array[i]==null)
				continue;
			explodeBlocks(tScreen,array[i]);
			System.out.println("after explosion: on row "+i+ " ,"+(cIndex+1) + "th explosion");
			print(tScreen);
			Block[] tArray=assignArray(tScreen,array);
			findCombination(tScreen,tArray,oIndex,cIndex+1);
		}
		
		if(combination[combination.length-1]==null)
			output=Math.min(output, countnonZeros(screen));
	}
	
	public static void explodeBlocks(int[][]screen,Block block){
		screen[block.r][block.c]=0;
		if(block.size==1) {
			return;
		}
		Queue<Block> queue=new LinkedList<Block>();
		
		visited=new boolean[H][W];
		
		queue.offer(block);
		visited[block.r][block.c]=true;
		
		while(!queue.isEmpty()) {
			Block current=queue.poll();
			int r=current.r;
			int c=current.c;
			int size=current.size-1;
			
			for (int i = 0; i < dr.length; i++) {
				int nr=r+dr[i];
				int nc=c+dc[i];
				for(int j=0;j<size &&nr>=0 && nr<H && nc>=0 && nc<W ;j++) {
					if(!visited[nr][nc] && screen[nr][nc]!=0) {
						if(screen[nr][nc]>1) {
							queue.offer(new Block(nr,nc,screen[nr][nc]));
						}
						visited[nr][nc]=true;
						screen[nr][nc]=0;
					}
					
					nr+=dr[i];
					nc+=dc[i];
				
				}
			}
		}
		
		dropBlocks(screen);
	}
	
	public static void dropBlocks(int [][]screen){
		boolean[][]visited=new boolean[H][W];
		for (int i = H-1; i >=0; i--) {
			for (int j = 0; j < W; j++) {
				if(screen[i][j]!=0 && !visited[i][j]) {
					visited[i][j]=true;
					int k=i+1;
					while(k<H&&screen[k][j]==0) {
						visited[k][j]=true;
						k++;
					}
					screen[k-1][j]=screen[i][j];
					if(k-1!=i)
						screen[i][j]=0;
				}
			}
		}
	}
	
	public static Block[] assignArray(int[][] screen, Block[] array) {
		Block[] tArray=new Block[W];
		
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if(screen[j][i]!=0) {
					tArray[i]=new Block(j,i,screen[j][i]);
					break;
				}
			}
		}
//		System.out.println("next combination: "+Arrays.toString(tArray));
//		System.out.println();
		return tArray;
	}
	
	public static int[][] copyScreen(int [][] array){
		int[][]tScreen=new int[H][W];
		for (int i = 0; i < tScreen.length; i++) {
			for (int j = 0; j < tScreen[i].length; j++) {
				tScreen[i][j]=array[i][j];
			}
		}
		return tScreen;
	}
	
	public static int countnonZeros(int [][]screen) {
		int nonzeros=0;
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				if(screen[i][j]!=0)
					nonzeros++;
			}
		}
		return nonzeros;
	}
	
	public static void print(int[][] screen) {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				System.out.print(screen[i][j]);
			}
			System.out.println();
		}
	}

}

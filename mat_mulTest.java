package strassen;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
public class mat_mulTest {

	@Test
	public void test1() {
		mat_mul test = new mat_mul();
		int [][] A= {{1,2},{1,2}};
		int [][] B= {{1,2,3},{1,2,3}};
		int [][] C={{3,6,9},{3,6,9}};
		int[][] result = test.mul(A,B,2,2,2,3);
		
		
		assertArrayEquals(result,C);
		
	}
	@Test
	public void test2() {
	mat_mul test = new mat_mul();	
	int [][] D= new int [50][50];
	int [][] E=new int [50][50];
	int [][] F=new int [50][50];
	
	for (int i =0;i<50;i++)
		for (int j=0;j<50;j++)
		{			D[i][j]=1;
					E[i][j]=1;
					F[i][j]=50;
		}
	int [][] result2 = test.mul(D, E, 50, 50, 50, 50);
	
	assertArrayEquals(result2,F);
	}
	
	@Test
	public void test3() {
	mat_mul test = new mat_mul();	
	int [][] D= new int [2][2];
	int [][] E=new int [2][100];
	int [][] F=new int [2][100];
	
	for (int i =0;i<2;i++)
		for (int j=0;j<100;j++)
		{		
					E[i][j]=1;
					F[i][j]=2;
		}
	for (int i =0;i<2;i++)
		for (int j=0;j<2;j++)
		{		
					D[i][j]=1;
					
		}
	
	int [][] result2 = test.mul(D, E, 2, 2, 2, 100);
	
	assertArrayEquals(result2,F);
	}
	
	@Test
	public void test4() {
	mat_mul test = new mat_mul();	
	int [][] D= new int [2][1000];
	int [][] E=new int [1000][1000];
	int [][] F=new int [2][1000];
	
	for (int i =0;i<2;i++)
		for (int j=0;j<1000;j++)
		{		
					D[i][j]=1;
					F[i][j]=1000;
		}
	for (int i =0;i<1000;i++)
		for (int j=0;j<1000;j++)
		{		
					E[i][j]=1;
					
		}
	
	int [][] result2 = test.mul(D, E, 2, 1000, 1000, 1000);
	
	assertArrayEquals(result2,F);
	}
}

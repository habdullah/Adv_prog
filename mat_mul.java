package strassen;


import java.util.Scanner;


public class mat_mul
{
	
	public static int log2(float f)
	{
		return (int)(Math.log(f)/Math.log(2.0));
	}
	
	public int[][] multiply(int[][] A, int[][] B)
	{        
		
		int n = A.length;
		
		int[][] R = new int[n][n];

		
		
		if (n == 1)
			R[0][0] = A[0][0] * B[0][0];
		else
		{
			int[][] A11 = new int[n/2][n/2];
			int[][] A12 = new int[n/2][n/2];
			int[][] A21 = new int[n/2][n/2];
			int[][] A22 = new int[n/2][n/2];
			int[][] B11 = new int[n/2][n/2];
			int[][] B12 = new int[n/2][n/2];
			int[][] B21 = new int[n/2][n/2];
			int[][] B22 = new int[n/2][n/2];
			
            /** Dividing matrix A into 4 halves **/
			split(A, A11, 0 , 0);
			split(A, A12, 0 , n/2);
			split(A, A21, n/2, 0);
			split(A, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
			split(B, B11, 0 , 0);
			split(B, B12, 0 , n/2);
			split(B, B21, n/2, 0);
			split(B, B22, n/2, n/2);
			
            /** 
              M1 = (A11 + A22)(B11 + B22)
              M2 = (A21 + A22) B11
              M3 = A11 (B12 - B22)
              M4 = A22 (B21 - B11)
              M5 = (A11 + A12) B22
              M6 = (A21 - A11) (B11 + B12)
              M7 = (A12 - A22) (B21 + B22)
            **/
              
              int [][] M1 = multiply(add(A11, A22), add(B11, B22));
              int [][] M2 = multiply(add(A21, A22), B11);
              int [][] M3 = multiply(A11, sub(B12, B22));
              int [][] M4 = multiply(A22, sub(B21, B11));
              int [][] M5 = multiply(add(A11, A12), B22);
              int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
              int [][] M7 = multiply(sub(A12, A22), add(B21, B22));
              
            /**
              C11 = M1 + M4 - M5 + M7
              C12 = M3 + M5
              C21 = M2 + M4
              C22 = M1 - M2 + M3 + M6
            **/
              int [][] C11 = add(sub(add(M1, M4), M5), M7);
              int [][] C12 = add(M3, M5);
              int [][] C21 = add(M2, M4);
              int [][] C22 = add(sub(add(M1, M3), M2), M6);
              
            /** join 4 halves into one result matrix **/
              join(C11, R, 0 , 0);
              join(C12, R, 0 , n/2);
              join(C21, R, n/2, 0);
              join(C22, R, n/2, n/2);
          }
        /** return result **/    
          return R;
      }
    /** Funtion to sub two matrices **/
      public int[][] sub(int[][] A, int[][] B)
      {
      	int n = A.length;
      	int[][] C = new int[n][n];
      	for (int i = 0; i < n; i++)
      		for (int j = 0; j < n; j++)
      			C[i][j] = A[i][j] - B[i][j];
      		return C;
      	}
    /** Funtion to add two matrices **/
      	public int[][] add(int[][] A, int[][] B)
      	{
      		int n = A.length;
      		int[][] C = new int[n][n];
      		for (int i = 0; i < n; i++)
      			for (int j = 0; j < n; j++)
      				C[i][j] = A[i][j] + B[i][j];
      			return C;
      		}
    /** Funtion to split parent matrix into child matrices **/
      		public void split(int[][] P, int[][] C, int iB, int jB) 
      		{
      			for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
      				for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
      					C[i1][j1] = P[i2][j2];
      			}
    /** Funtion to join child matrices intp parent matrix **/
      			public void join(int[][] C, int[][] P, int iB, int jB) 
      			{
      				for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
      					for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
      						P[i2][j2] = C[i1][j1];
      				}    
      				
      				
      				
      				
      				
      				
      				
      				
      				
      				
      				
      				
   
      		public int[][] mul(int[][] matA,int[][] matB,int m,int n,int m2,int n2){
      			
      			
						int[] a = new int [4];
						a[0]=m;
						a[1]=n;
						a[2]=m2;
						a[3]=n2;
						int max=0;
						for (int i = 1; i < a.length; i++) {
							if (a[i] > max) {
								max = a[i];
							}
							
						}
//	System.out.print(max+"testing\n");
						max=log2(max)+1;
//	System.out.print(max+"testing\n");
						max=(int) Math.pow(2,max);
//	System.out.print(max+"testing\n");
						
	//add padding
						int[][] temp1 = new int [max][max];
						int[][] temp2 = new int [max][max];
						
						
						for (int i=0;i<m;i++){
							for (int j=0;j<n;j++){
								temp1[i][j]=matA[i][j];
							}
						}
						
						for (int i=0;i<m2;i++){
							for (int j=0;j<n2;j++){
								temp2[i][j]=matB[i][j];
							}
						}
						
						mat_mul s = new mat_mul();	
						System.out.print("\nAdding padding to Matrix A\n");
						for (int i=0;i<max;i++){
							for (int j=0;j<max;j++){
								System.out.print(temp1[i][j]);
							}
							System.out.print("\n");
						}
						System.out.print("\nAdding padding to Matrix B\n");
						for (int i=0;i<max;i++){
							for (int j=0;j<max;j++){
								System.out.print(temp2[i][j]);
							}
							System.out.print("\n");
						}
						int[][] C = s.multiply(temp1, temp2);
						
						int[][] result= new int [m][n2];
						
						
						for (int i=0;i<m;i++)
							for(int j=0;j<n2;j++)
								result[i][j]=C[i][j];
						
							
							System.out.println("Answer matrix with padding ");
							for (int i = 0; i < max; i++)
							{
								for (int j = 0; j < max; j++)
									System.out.print(C[i][j] +" ");
								System.out.println();
							}
							
							System.out.println("\nProduct of matrices A and  B : ");
							for (int i = 0; i < m; i++)
							{
								for (int j = 0; j < n2; j++)
									System.out.print(result[i][j] +" ");
								System.out.println();
							}
							
							
						
						
				return result;		
      		} 
      		
      		
      		
      		
      		
      		
      		
      		
      		
//      		static int m,n,m2,n2;
//      				public static void main (String[] args) 
//      				{
//      					
//      					
//      					System.out.println("Enter the dimensions of the First matrix");
//      					Scanner in = new Scanner(System.in);
//      					m = in.nextInt();
//      					n = in.nextInt();
//
//      					System.out.println("\nEnter the dimensions of the Second matrix");
//      					m2 = in.nextInt();
//      					n2 = in.nextInt();
//
//      					if (n!=m2) {
//      						
//      						System.out.println("Matrices with the entered dimensions cannot be multiplied \n");
//      						
//      					}
//      					else {
//	//turn them into square matrices
//	//pad them
//	//implement recursive strassen algo  
//      						System.out.println("Enter First matrix \n");		
//      						
//	//Matrices initializations 
//      						int first[][]   = new int[m][n];
//      						int second[][]  = new int[m2][n2];
//	//matrix A elements added 
//      						for ( int c = 0 ; c < m ; c++ )
//      							for ( int d = 0 ; d < n ; d++ )
//      								{System.out.printf("\nEnter matrix element a%d%d",c,d);
//      							first[c][d] = in.nextInt();
//      						}
//	//matrix A printed
//      						System.out.print("\nfirst matrix\n");
//      						for (int  c = 0 ; c < m ; c++ )
//      						{
//      							for ( int d = 0 ; d < n ; d++ )
//      								System.out.printf(" %d",first[c][d]);
//
//
//      							System.out.print("\n");
//      						}	
//	//matrix B elements added 
//      						System.out.println("Enter second matrix \n");
//      						
//      						for ( int c = 0 ; c < m2 ; c++ )
//      							for ( int d = 0 ; d < n2 ; d++ )
//      								{System.out.printf("\nEnter matrix element b%d%d",c,d);
//      							second[c][d] = in.nextInt();
//      						}
//	//matrix B printed
//      						System.out.print("\nsecond matrix\n");
//      						for (int  c = 0 ; c < m2 ; c++ )
//      						{
//      							for ( int d = 0 ; d < n2 ; d++ )
//      								System.out.printf(" %d",second[c][d]);
//
//
//      							System.out.print("\n");
//      						}	
//      						
////      						{
////      							int[] a= new int [4];
////      							a[0]=m;
////      							a[1]=n;
////      							a[2]=m2;
////      							a[3]=n2;
////      							int max=0;
////      							for (int i = 1; i < a.length; i++) {
////      								if (a[i] > max) {
////      									max = a[i];
////      								}
////      								
////      							}
//////				System.out.print(max+"testing\n");
////      							max=log2(max)+1;
//////				System.out.print(max+"testing\n");
////      							max=(int) Math.pow(2,max);
//////				System.out.print(max+"testing\n");
////      							
////				//add padding
////      							int[][] temp1 = new int [max][max];
////      							int[][] temp2 = new int [max][max];
////      							
////      							
////      							for (int i=0;i<m;i++){
////      								for (int j=0;j<n;j++){
////      									temp1[i][j]=first[i][j];
////      								}
////      							}
////      							
////      							for (int i=0;i<m2;i++){
////      								for (int j=0;j<n2;j++){
////      									temp2[i][j]=second[i][j];
////      								}
////      							}
////      							
////      							mat_mul s = new mat_mul();	
////      							System.out.print("\nAdding padding to Matrix A\n");
////      							for (int i=0;i<max;i++){
////      								for (int j=0;j<max;j++){
////      									System.out.print(temp1[i][j]);
////      								}
////      								System.out.print("\n");
////      							}
////      							System.out.print("\nAdding padding to Matrix B\n");
////      							for (int i=0;i<max;i++){
////      								for (int j=0;j<max;j++){
////      									System.out.print(temp2[i][j]);
////      								}
////      								System.out.print("\n");
////      							}
////      							int[][] C = s.multiply(temp1, temp2);
////      							
////      							int[][] result= new int [m][n2];
////      							
////      							
////      							for (int i=0;i<m;i++)
////      								for(int j=0;j<n2;j++)
////      									result[i][j]=C[i][j];
////      								
////      								System.out.println("Answer matrix with padding ");
////      								for (int i = 0; i < max; i++)
////      								{
////      									for (int j = 0; j < max; j++)
////      										System.out.print(C[i][j] +" ");
////      									System.out.println();
////      								}
////      								
////      								System.out.println("\nProduct of matrices A and  B : ");
////      								for (int i = 0; i < m; i++)
////      								{
////      									for (int j = 0; j < n2; j++)
////      										System.out.print(result[i][j] +" ");
////      									System.out.println();
////      								}
////      								
////      								
////      							}
//      							int[][] fnl=mul(first,second);
//      							System.out.println("\nProduct of matrices A and  B : ");
//								for (int i = 0; i < m; i++)
//								{
//									for (int j = 0; j < n2; j++)
//										System.out.print(fnl[i][j] +" ");
//									System.out.println();
//								}
//      							
//      							
//      						}
//      					}
      				}

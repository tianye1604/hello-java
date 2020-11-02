package com.study.anything;

/**
 * Created by tianshujian
 * Create Date: 2020/10/30 20:09
 * Description: ${DESCRIPTION}
 */
public class TestClockwisePrint {

	private static int l,r,t,b;

	private static int[][] matrix;
	public static void main(String[] args) {

		//[[1,2,3],[4,5,6],[7,8,9]];
		matrix = getMatix(4);
		printMatrix(matrix);
		System.out.println("----------------------");
		l = 1;
		t = 1;
		r = matrix.length;
		b = matrix.length;

		try {
			while (true){
				printL2R();
				printT2B();
				printR2L();
				printB2T();
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println();
			System.out.println(e.getMessage());
		}


	}

	public static void printMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int i1 = 0; i1 < matrix[i].length; i1++) {
				System.out.print(matrix[i][i1]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static int[][] getMatix(int size) {
		int[][] matrix = new int[size][size];
		if (size<=0) throw new RuntimeException("异常数据");
		for (int x=0;x < size; x++) {
			for (int y=0; y < size; y++) {
				matrix[x][y] = x*size + y + 1;
			}
		}
		return matrix;
	}
	public static void printL2R() {
		if(l > r || t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = l;i<=r; i++){
			System.out.print(matrix[t-1][i-1] + ",");
		}
		t++;
	}
	public static void printT2B() {
		if(l > r || t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = t; i<=b; i++){
			System.out.print(matrix[i-1][r-1] + ",");
		}
		r--;
	}
	public static void printR2L() {
		if(l > r || t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = r; i >= l; i-- ){
			System.out.print(matrix[b-1][i-1] + ",");
		}
		b--;

	}
	public static void printB2T() {
		if(l > r || t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = b; i >= t; i-- ){
			System.out.print(matrix[i-1][l-1] + ",");
		}
		l++;
	}
}

package com.study.anything;

import java.util.Arrays;

/**
 * Created by tianshujian
 * Create Date: 2020/10/30 21:09
 * Description: ${DESCRIPTION}
 */
public class Solution {

	static int l,t,r,b,index;

	public static void printMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int i1 = 0; i1 < matrix[i].length; i1++) {
				System.out.print(matrix[i][i1]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static int[][] getMatix(int xLen, int yLen) {
		int[][] matrix = new int[xLen][yLen];
		if (xLen<=0) throw new RuntimeException("异常数据");
		for (int x=0;x < xLen; x++) {
			for (int y=0; y < yLen; y++) {
				matrix[x][y] = x*yLen + y + 1;
			}
		}
		return matrix;
	}
	public static void main(String[] args) {
		int xLen = 6,ylen = 5;
		int[][] matrix = getMatix(xLen,ylen);
		printMatrix(matrix);
		System.out.println("----------------------");
		int[] result = spiralOrder(matrix);
		System.out.println(Arrays.toString(result));
	}

	public static int[] spiralOrder(int[][] matrix) {

		if(matrix.length == 0) {
			return new int[0];
		}
		l=1;t=1;r=matrix[0].length;b=matrix.length;
		int[] result = new int[r*b];
		try {
			while (true){
				printL2R(matrix,result);
				printT2B(matrix,result);
				printR2L(matrix,result);
				printB2T(matrix,result);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return  result;
	}

	public static void printL2R(int[][] matrix, int[] result) {
		if(l > r) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = l;i<=r; i++){
			result[index] = matrix[t-1][i-1];
			index++;
		}
		t++;
	}
	public static void printT2B(int[][] matrix, int[] result) {
		if(t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = t; i<=b; i++){
			result[index] = matrix[i-1][r-1];
			index++;
		}
		r--;
	}
	public static void printR2L(int[][] matrix, int[] result) {
		if(l > r ) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = r; i >= l; i-- ){
			result[index] =matrix[b-1][i-1];
			index++;
		}
		b--;

	}
	public static void printB2T(int[][] matrix, int[] result) {
		if(t > b) {
			throw new RuntimeException("结束，" + "l="+l  + ",r=" + r + ",t="+t  + ",b=" + b );
		}
		for (int i = b; i >= t; i-- ){
			result[index] = matrix[i-1][l-1];
			index++;
		}
		l++;
	}
}

package test0513;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		long A[] = new long[100001];
		long S[] = new long[100001];
		for(int i = 1; i < 100000; i++) {
			A[i] = (long)(Math.random() * Integer.MAX_VALUE);
			S[i] = S[i-1] + A[i];
		}
		for(int t = 1; t < testcase; t++) {
			long answer = 0;
			int query = sc.nextInt();
			for(int i = 0; i < query; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				answer += S[end] - S[start - 1];
				System.out.println(t + " " + answer);
			}
		}
	}
}

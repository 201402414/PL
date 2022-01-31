package H01;
import java.math.*;
import java.util.Scanner;
class DP { 
	
    static BigInteger df(BigInteger n){ 
    	BigInteger a = BigInteger.ONE;
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) { 
            return a; }
        else {
        	return n.multiply(df(n.subtract(BigInteger.valueOf(2))));
        }
    } 
static public void main (String[] args){ 
	BigInteger a = BigInteger.ONE;
	Scanner b = new Scanner(System.in);
	System.out.print("input : ");
	int temp = b.nextInt();
   	System.out.println("output : "+df(BigInteger.valueOf(temp))); 
    } 
} 


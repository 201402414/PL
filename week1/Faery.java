package H01;

import java.util.Scanner; 
public class Faery { 
	static void farey(int num1, int den1, int num2, int den2, int input){ //num == 분자  den == 분모
		int newnumerator = num1 + num2; 
		int newdenominator = den1 + den2; 
		if(newdenominator > input) {
			return;
		}
		else {
			farey(num1, den1, newnumerator, newdenominator, input);
		}
		
		System.out.print(" "+ newnumerator + "/" + newdenominator+" "); 
		farey(newnumerator, newdenominator, num2, den2, input);
	} 
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in); 
		System.out.print("input : "); 
		int input = sc.nextInt(); 
		System.out.println("output : ");
		for(int i = input; i >= 1; i--){
			int aa =input-i+1;
			System.out.print("f"+aa+" : "); 
			System.out.print("["+ 0 + "/" + 1+" ");  
			Faery.farey(0, 1, 1, 1, aa); 
			System.out.println(" "+ 1 + "/" + 1+"]"); 
		}	
	} 
}
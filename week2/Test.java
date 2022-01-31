package H02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		RecursionLinkedList list = new RecursionLinkedList();
		RecursionLinkedList list2 = new RecursionLinkedList();
		FileReader fr;
		try {
			fr = new FileReader("D:\\Eclipse\\PL\\src\\H02\\hw01.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++)
				list.add(inputString.charAt(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fr = new FileReader("D:\\Eclipse\\PL\\src\\H02\\hw02.txt");
			BufferedReader br = new BufferedReader(fr);
			String inputString = br.readLine();
			for (int i = 0; i < inputString.length(); i++)
				list2.add(inputString.charAt(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("노드1 : "+list.toString());
		System.out.println("노드의 사이즈는 :"+list.size());
		System.out.print("0번 인덱스에 w추가 : ");
		list.add(0, 'w');
		System.out.println(list.toString());
		System.out.println("2번째 노드의 값 :"+list.get(2));
		System.out.println("3번째 인덱스"+list.remove(3)+" 삭제 : "+list.toString());
		System.out.println("노드2 : "+list2.toString());
		list.addAll(list2);
		System.out.println("노드1+노드2 : "+list.toString());
		list.reverse();
		System.out.println("리버스 : "+list.toString());
		// 등등 구현한 기능 추가해서 사용*/
	}

}

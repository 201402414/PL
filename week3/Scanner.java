package H03;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Scanner {
	public enum TokenType {
		ID(3), INT(2);

		private final int finalState;

		TokenType(int finalState) {
			this.finalState = finalState;
		}
	}

	public static class Token {
		public final TokenType type;
		public final String lexme;

		Token(TokenType type, String lexme) {
			this.type = type;
			this.lexme = lexme;
		}

		public String toString() {
			return String.format("[%s: %s]", type.toString(), lexme);
		}
	}

	private int transM[][];
	private String source;
	private StringTokenizer st;

	public Scanner(String source) {
		this.transM = new int[4][128];
		this.source = source == null ? "" : source;
		this.st = new StringTokenizer(this.source, " ");
		initTM();
	}

	private void initTM() {
		for (int i = 0; i < transM.length; i++) {
			for (int j = 0; j < transM[i].length; j++) {
				transM[i][j] = -1;
			}
		} // -1 �� �ʱ�ȭ
		int i;

		for (i = '0'; i <= '9'; i++) // digit
		{
			transM[0][i] = 2; // int
			transM[1][i] = 2; // int
			transM[2][i] = 2; // int
			transM[3][i] = 3; // id
		}
		for (i = 'a'; i <= 'z'; i++)// alpha
		{
			transM[0][i] = 3; // id
			transM[3][i] = 3; // id
		}
		for (i = 'A'; i <= 'Z'; i++) {// alpha
			transM[0][i] = 3; // id
			transM[3][i] = 3; // id
		}

		transM[0]['-'] = 1; // int

	}

	private Token nextToken() {
		int stateOld = 0, stateNew;
		//��ū�� �� �ִ��� �˻�
		if(!st.hasMoreTokens()) return null;
		//�� ���� ��ū�� ����
		String temp = st.nextToken();
		Token result = null;
		for(int i = 0; i<temp.length();i++){
		 //���ڿ��� ���ڸ� �ϳ��� ������ ������¿� TransM�� �̿��Ͽ� ����
		//���¸� �Ǻ�
		 //���� �Էµ� ������ ���°� reject �̸� �����޼��� ��� �� return��
		 //���� ���� ���¸� ���� ���·� ����
			stateNew = transM[stateOld][temp.charAt(i)];
			// �Է¹��ڷ� ���ο� ���� �Ǻ�
			if(stateNew==-1){
				System.out.println("���ǵ�������.");
				return result;
			}

			stateOld = stateNew;
		}
		for (TokenType t : TokenType.values()){//TokenType ���� 0,1,2,3
			if(t.finalState == stateOld){
				result = new Token(t, temp);
				break;
			}
		}
		return result;
	}

	public List<Token> tokenize() {
		List<Token> tokens = new ArrayList<Token>();
		Token t = null;

		// ��ū List�� ��ȯ�ϵ��� �ۼ�
		t = this.nextToken();

		while (t != null) {
			tokens.add(t);
			t = this.nextToken();
		}

		return tokens;
	}

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("D:\\Eclipse\\PL\\src\\H03\\hw01.txt");
			BufferedReader br = new BufferedReader(fr);
			String source = br.readLine();
			Scanner s = new Scanner(source);
			List<Token> tokens = s.tokenize();
			System.out.println(tokens);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

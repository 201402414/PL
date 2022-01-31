package H06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import H06.BinaryOpNode.BinType;

public class CuteParser {
	private Iterator<Token> tokens;

	public CuteParser(File file) {
		try {
			tokens = Scanner.scan(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Token getNextToken() {
		if (!tokens.hasNext())
			return null;
		return tokens.next();
	}

	public Node parseExpr() {
		Token t = getNextToken();
		if (t == null) {
			System.out.println("No more token");
			return null;
		}
		TokenType tType = t.type();
		String tLexeme = t.lexme();
		switch (tType) {
		case ID:
			IdNode idNode = new IdNode();
			idNode.value = tLexeme;
			return idNode;
		case INT:
			IntNode intNode = new IntNode();
			if (tLexeme == null)
				System.out.println("???");
			intNode.value = new Integer(tLexeme);
			return intNode;
		// BinaryOpNode�� ���Ͽ� �ۼ�
		// +, -, /, * �� �ش�
		case DIV: {
			BinaryOpNode div = new BinaryOpNode();
			div.value = BinType.DIV;
			return div;
		}
		case EQ: {
			BinaryOpNode eq = new BinaryOpNode();
			eq.value = BinType.EQ;
			return eq;
		}
		case MINUS: {
			BinaryOpNode minus = new BinaryOpNode();
			minus.value = BinType.MINUS;
			return minus;
		}
		case GT: {
			BinaryOpNode gt = new BinaryOpNode();
			gt.value = BinType.GT;
			return gt;
		}
		case PLUS: {
			BinaryOpNode plus = new BinaryOpNode();
			plus.value = BinType.PLUS;

			return plus;
		}
		case TIMES: {
			BinaryOpNode times = new BinaryOpNode();
			times.value = BinType.TIMES;
			return times;
		}
		case LT: {
			BinaryOpNode lt = new BinaryOpNode();
			lt.value = BinType.LT;
			return lt;
		}
		// ���� ä���
		// FunctionNode�� ���Ͽ� �ۼ�
		// Ű���尡 FunctionNode�� �ش�
		case ATOM_Q: {
			FunctionNode atom = new FunctionNode();
			atom.value = FunctionNode.FunctionType.ATOM_Q;
			return atom;
		}
		case CAR: {
			FunctionNode car = new FunctionNode();
			car.value = FunctionNode.FunctionType.CAR;
			return car;
		}
		case CDR: {
			FunctionNode cdr = new FunctionNode();
			cdr.value = FunctionNode.FunctionType.CDR;
			return cdr;
		}
		case COND: {
			FunctionNode cond = new FunctionNode();
			cond.value = FunctionNode.FunctionType.COND;
			return cond;
		}
		case CONS: {
			FunctionNode cons = new FunctionNode();
			cons.value = FunctionNode.FunctionType.CONS;
			return cons;
		}
		case DEFINE: {
			FunctionNode define = new FunctionNode();
			define.value = FunctionNode.FunctionType.DEFINE;
			return define;
		}
		case EQ_Q: {
			FunctionNode eq = new FunctionNode();
			eq.value = FunctionNode.FunctionType.EQ_Q;
			return eq;
		}
		case LAMBDA: {
			FunctionNode lambda = new FunctionNode();
			lambda.value = FunctionNode.FunctionType.LAMBDA;
			return lambda;
		}
		case NOT: {
			FunctionNode not = new FunctionNode();
			not.value = FunctionNode.FunctionType.NOT;
			return not;
		}
		case NULL_Q: {
			FunctionNode nq = new FunctionNode();
			nq.value = FunctionNode.FunctionType.NULL_Q;
			return nq;
		}
		// ���� ä���
		// BooleanNode �� ���Ͽ� �ۼ�
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode;
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;
		// case L_PAREN �� ���� case R_PAREN �� ��쿡 ���ؼ� �ۼ�
		// L_PAREN �� ��� parseExprList()�� ȣ���Ͽ� ó��
		case L_PAREN: {
			ListNode listNode = new ListNode();
			listNode.value = parseExprList();
			return listNode;
		}
		// ���� ä���
		case R_PAREN:
			return null;
		default:
			// head �� next �� ����� head �� ��ȯ�ϵ��� �ۼ�
			System.out.println("Parsing Error!");
			return null;
		}
	}

	// List �� value �� �����ϴ� �޼ҵ�
	private Node parseExprList() {
		Node head = parseExpr();
		// head �� next ��带 set �Ͻÿ�.
		if (head == null) // if next token is RPAREN
			return null;
		head.setNext(parseExprList());
		return head;
	}
}
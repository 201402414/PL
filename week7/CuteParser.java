package H07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import H07.BinaryOpNode.BinType;

public class CuteParser {
	private Iterator<Token> tokens;
	private static Node END_OF_LIST = new Node() {
	}; // ���� �߰��� �κ�

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
			return new IdNode(tLexeme);
		case INT:
			if (tLexeme == null)
				System.out.println("???");
			return new IntNode(tLexeme);
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
			return BooleanNode.FALSE_NODE;
		case TRUE:
			return BooleanNode.TRUE_NODE;
		// case L_PAREN �� ���� case R_PAREN �� ��쿡 ���ؼ� �ۼ�
		// L_PAREN �� ��� parseExprList()�� ȣ���Ͽ� ó��
		case L_PAREN: {
			return parseExprList();
		}
		// ���� ä���
		case R_PAREN:
			return END_OF_LIST;
		case APOSTROPHE:
			QuoteNode quoteNode = new QuoteNode(parseExpr());
			ListNode listNode = ListNode.cons(quoteNode, ListNode.ENDLIST);
			return listNode;
		case QUOTE:
			return new QuoteNode(parseExpr());
		default:
			System.out.println("Parsing Error!");
			return null;
		}

	}

	// List �� value �� �����ϴ� �޼ҵ�
	private ListNode parseExprList() {
		Node head = parseExpr();
		if (head == null)
			return null;
		if (head == END_OF_LIST) // if next token is RPAREN
			return ListNode.ENDLIST;
		ListNode tail = parseExprList();
		if (tail == null)
			return null;
		return ListNode.cons(head, tail);
	}
}
package H08;

import static H08.TransitionOutput.GOTO_ACCEPT_ID;
import static H08.TransitionOutput.GOTO_ACCEPT_INT;
import static H08.TransitionOutput.GOTO_EOS;
import static H08.TransitionOutput.GOTO_FAILED;
import static H08.TransitionOutput.GOTO_MATCHED;
import static H08.TransitionOutput.GOTO_SHARP;
import static H08.TransitionOutput.GOTO_SIGN;
import static H08.TransitionOutput.GOTO_START;

import ast.Node.Type;

import static H08.TokenType.FALSE;
import static H08.TokenType.INT;
import static H08.TokenType.MINUS;
import static H08.TokenType.PLUS;
import static H08.TokenType.TRUE;

enum State {
	START {
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch (ch.type()) {
			case LETTER:
				context.append(v);
				return GOTO_ACCEPT_ID;
			case DIGIT:
				context.append(v);
				return GOTO_ACCEPT_INT;
			case SPECIAL_CHAR: // special character가 들어온 경우
				if (v == '+' || v == '-') { // 부호인 경우에 상태 반환
					context.append(v);
					return GOTO_SIGN;
				} else if (v == '#') { // boolean인 경우에 상태 반환
					context.append(v);
					return GOTO_SHARP;
				} else { // 그 외에는 type을 알아내서 알맞은 상태로
					switch (v) {
					case '(':
						context.append(v);
						return GOTO_MATCHED(TokenType.L_PAREN, context.getLexime());
					case ')':
						context.append(v);
						return GOTO_MATCHED(TokenType.R_PAREN, context.getLexime());
					case '*':
						context.append(v);
						return GOTO_MATCHED(TokenType.TIMES, context.getLexime());
					case '/':
						context.append(v);
						return GOTO_MATCHED(TokenType.DIV, context.getLexime());
					case '=':
						context.append(v);
						return GOTO_MATCHED(TokenType.EQ, context.getLexime());
					case '\'':
						context.append(v);
						return GOTO_MATCHED(TokenType.APOSTROPHE, context.getLexime());
					case '?':
						context.append(v);
						return GOTO_MATCHED(TokenType.QUESTION, context.getLexime());
					case '<':
						context.append(v);
						return GOTO_MATCHED(TokenType.LT, context.getLexime());
					case '>':
						context.append(v);
						return GOTO_MATCHED(TokenType.GT, context.getLexime());
					}
				}
			case WS:
				return GOTO_START;
			case END_OF_STREAM:
				return GOTO_EOS;
			default:
				throw new AssertionError();
			}
		}
	},
	ACCEPT_ID {
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch (ch.type()) {
			case LETTER:
			case DIGIT:
				context.append(v);
				return GOTO_ACCEPT_ID;
			case SPECIAL_CHAR:
				return GOTO_FAILED;
			case WS:
			case END_OF_STREAM:
				return GOTO_MATCHED(Token.ofName(context.getLexime()));
			default:
				throw new AssertionError();
			}
		}
	},
	ACCEPT_INT {
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			switch (ch.type()) {
			case LETTER:
				return GOTO_FAILED;
			case DIGIT:
				context.append(ch.value());
				return GOTO_ACCEPT_INT;
			case SPECIAL_CHAR:
				return GOTO_FAILED;
			case WS:
				return GOTO_MATCHED(INT, context.getLexime());
			case END_OF_STREAM:
				return GOTO_MATCHED(INT, context.getLexime());
			default:
				throw new AssertionError();
			}
		}
	},
	SHARP {
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch (ch.type()) {
			case LETTER:
				switch (v) {
				case 'T':
					context.append(v);
					return GOTO_MATCHED(TRUE, context.getLexime());
				case 'F':
					context.append(v);
					return GOTO_MATCHED(FALSE, context.getLexime());
				default:
					return GOTO_FAILED;
				}
			default:
				return GOTO_FAILED;
			}
		}
	},
	SIGN {
		@Override
		public TransitionOutput transit(ScanContext context) {
			Char ch = context.getCharStream().nextChar();
			char v = ch.value();
			switch (ch.type()) {
			case LETTER:
				return GOTO_FAILED;
			case DIGIT:
				context.append(v);
				return GOTO_ACCEPT_INT;
			case SPECIAL_CHAR:
				return GOTO_FAILED;
			case WS:
				String lexme = context.getLexime();
				switch (lexme) {
				case "+":
					return GOTO_MATCHED(PLUS, lexme);
				case "-":
					return GOTO_MATCHED(MINUS, lexme);
				default:
					throw new AssertionError();
				}
			case END_OF_STREAM:
				return GOTO_FAILED;
			default:
				throw new AssertionError();
			}
		}
	},
	MATCHED {
		@Override
		public TransitionOutput transit(ScanContext context) {
			throw new IllegalStateException("at final state");
		}
	},
	FAILED {
		@Override
		public TransitionOutput transit(ScanContext context) {
			throw new IllegalStateException("at final state");
		}
	},
	EOS {
		@Override
		public TransitionOutput transit(ScanContext context) {
			return GOTO_EOS;
		}
	};

	abstract TransitionOutput transit(ScanContext context);
}

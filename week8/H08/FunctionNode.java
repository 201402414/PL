package H08;

import java.util.HashMap;
import java.util.Map;

public class FunctionNode implements Node {
	// binaryOpNodeŬ������ ���� �����ؼ� �ۼ�
	public enum FunctionType {
		ATOM_Q {
			TokenType tokenType() {
				return TokenType.ATOM_Q;
			}
		},
		CAR {
			TokenType tokenType() {
				return TokenType.CAR;
			}
		},
		CDR {
			TokenType tokenType() {
				return TokenType.CDR;
			}
		},
		COND {
			TokenType tokenType() {
				return TokenType.COND;
			}
		},
		CONS {
			TokenType tokenType() {
				return TokenType.CONS;
			}
		},
		DEFINE {
			TokenType tokenType() {
				return TokenType.DEFINE;
			}
		},
		EQ_Q {
			TokenType tokenType() {
				return TokenType.EQ_Q;
			}
		},
		LAMBDA {
			TokenType tokenType() {
				return TokenType.LAMBDA;
			}
		},
		NOT {
			TokenType tokenType() {
				return TokenType.NOT;
			}
		},
		NULL_Q {
			TokenType tokenType() {
				return TokenType.NULL_Q;
			}
		};
		
		private static Map<TokenType, FunctionType> fromTokenType = new HashMap<TokenType, FunctionType>();

		static {
			for (FunctionType fType : FunctionType.values()) {
				fromTokenType.put(fType.tokenType(), fType);
			}
		}

		static FunctionType getFunctionType(TokenType fType) {
			return fromTokenType.get(fType);
		}
		abstract TokenType tokenType();
		
	}

	public FunctionType funcType;
	@Override
	public String toString() {
		// ���� ä���
		return funcType.name();
	}

	public void setValue(TokenType tType) {
		// ���� ä���
		FunctionType fType = FunctionType.getFunctionType(tType);
		funcType = fType;
	}
}
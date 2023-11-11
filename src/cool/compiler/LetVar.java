package cool.compiler;

import org.antlr.v4.runtime.Token;

public class LetVar extends Expression {
	Token name;
	Token type;
	Expression init;

	LetVar(Token name,
		   Token type,
		   Expression init,
		   Token start) {
		super(start);
		this.name = name;
		this.type = type;
		this.init = init;
	}

	LetVar(Token name,
		   Token type,
		   Token start) {
		super(start);
		this.name = name;
		this.type = type;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
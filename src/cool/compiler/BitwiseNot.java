package cool.compiler;

import org.antlr.v4.runtime.Token;

public class BitwiseNot extends Expression {
	Expression e;

	BitwiseNot(Expression e,
			   Token start) {
		super(start);
		this.e = e;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
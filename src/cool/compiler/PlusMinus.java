package cool.compiler;

import org.antlr.v4.runtime.Token;

public class PlusMinus extends Expression {
	private Expression left;
	private Expression right;

	public PlusMinus(Expression left,
			  Token op,
			  Expression right) {
		super(op);
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}
	
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

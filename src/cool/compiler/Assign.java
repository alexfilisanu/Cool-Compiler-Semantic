package cool.compiler;

import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
	Expression value;

	Assign(Token name,
		   Expression value) {
		super(name);
		this.value = value;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

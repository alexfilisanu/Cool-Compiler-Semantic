package cool.compiler;

import cool.structures.IdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.Token;

public class Id extends Expression {
	private IdSymbol symbol;
	private Scope scope;

	public Id(Token token) {
		super(token);
	}

	public IdSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(IdSymbol symbol) {
		this.symbol = symbol;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

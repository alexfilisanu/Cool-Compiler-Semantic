package cool.compiler;

import cool.parser.CoolParser;

import java.util.List;

public class Classs extends Expression {
	private List<Expression> definitions;
	private CoolParser.ClassContext ctx;

	public Classs(CoolParser.ClassContext ctx,
		   List<Expression> definitions) {
		super(ctx.start);
		this.ctx = ctx;
		this.definitions = definitions;
	}

	public List<Expression> getDefinitions() {
		return definitions;
	}

	public CoolParser.ClassContext getCtx() {
		return ctx;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

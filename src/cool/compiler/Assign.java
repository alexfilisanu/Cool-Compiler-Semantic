package cool.compiler;

import cool.parser.CoolParser;

public class Assign extends Expression {
	private CoolParser.AssignContext ctx;
	private Expression value;

	public Assign(CoolParser.AssignContext ctx,
		   Expression value) {
		super(ctx.start);
		this.ctx = ctx;
		this.value = value;
	}

	public CoolParser.AssignContext getCtx() {
		return ctx;
	}

	public Expression getValue() {
		return value;
	}

	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

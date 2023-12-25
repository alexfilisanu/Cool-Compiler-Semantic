package cool.compiler;

import java.util.Objects;

public class PrintVisitor implements ASTVisitor<Void> {
	int indent = 0;

	@Override
	public Void visit(Assign assign) {
		var ctx = assign.getCtx();
		printIndent(ctx.ASSIGN().getSymbol().getText());

		indent++;
		printIndent(ctx.name.getText());
		assign.getValue().accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(BitwiseNot bitwiseNot) {
		printIndent(bitwiseNot.getToken().getText());

		indent++;
		bitwiseNot.e.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Block block) {
		printIndent("block");

		indent++;
		block.exprs.forEach(x -> x.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(Bool bool) {
		printIndent(bool.getToken().getText());

		return null;
	}

	@Override
	public Void visit(Call call) {
		printIndent("implicit dispatch");

		indent++;
		printIndent(call.getToken().getText());
		call.args.forEach(x -> x.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(Case casee) {
		printIndent("case");

		indent++;
		casee.e.accept(this);
		casee.caseStatements.forEach(caseStatement -> caseStatement.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(CaseStatement caseStatement) {
		printIndent("case branch");

		indent++;
		printIndent(caseStatement.name.getText());
		printIndent(caseStatement.type.getText());
		caseStatement.init.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Classs classs) {
		var ctx = classs.getCtx();
		printIndent("class");

		indent++;
		printIndent(ctx.type.getText());
		if (ctx.inherit != null) {
			printIndent(ctx.inherit.getText());
		}
		classs.getDefinitions().forEach(definition -> definition.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(Dispatch dispatch) {
		printIndent(".");

		indent++;
		dispatch.e.accept(this);
		if (Objects.nonNull(dispatch.type)) {
			printIndent(dispatch.type.getText());
		}
		printIndent(dispatch.getToken().getText());
		// sublist(1,) because e(first element of args) is already accepted before
		dispatch.args.subList(1, dispatch.args.size())
				.forEach(x -> x.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(Formal formal) {
		printIndent("formal");

		indent++;
		printIndent(formal.name.getText());
		printIndent(formal.type.getText());
		indent--;

		return null;
	}

	@Override
	public Void visit(FuncDef funcDef) {
		printIndent("method");

		indent++;
		printIndent(funcDef.name.getText());
		funcDef.params.forEach(definition -> definition.accept(this));
		printIndent(funcDef.type.getText());
		if (Objects.nonNull(funcDef.body)) {
			funcDef.body.accept(this);
		}
		indent--;

		return null;
	}

	@Override
	public Void visit(Id id) {
		printIndent(id.getToken().getText());

		return null;
	}

	@Override
	public Void visit(If iff) {
		printIndent("if");

		indent++;
		iff.cond.accept(this);
		iff.thenBranch.accept(this);
		iff.elseBranch.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Int intt) {
		printIndent(intt.getToken().getText());

		return null;
	}

	@Override
	public Void visit(IsVoid isVoid) {
		printIndent(isVoid.getToken().getText());

		indent++;
		isVoid.e.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Let let) {
		printIndent("let");

		indent++;
		let.letVars.forEach(varDef -> varDef.accept(this));
		let.body.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(LetVar letVar) {
		printIndent("local");

		indent++;
		printIndent(letVar.name.getText());
		printIndent(letVar.type.getText());
		if (letVar.init != null) {
			letVar.init.accept(this);
		}
		indent--;

		return null;
	}

	@Override
	public Void visit(MultDiv multDiv) {
		printIndent(multDiv.getToken().getText());

		indent++;
		multDiv.left.accept(this);
		multDiv.right.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(New neww) {
		printIndent(neww.getToken().getText());

		indent++;
		printIndent(neww.name.getText());
		indent--;

		return null;
	}

	@Override
	public Void visit(Not not) {
		printIndent(not.getToken().getText());

		indent++;
		not.e.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Paren paren) {
		paren.e.accept(this);

		return null;
	}

	@Override
	public Void visit(PlusMinus plusMinus) {
		printIndent(plusMinus.getToken().getText());

		indent++;
		plusMinus.left.accept(this);
		plusMinus.right.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Program program) {
		printIndent("program");

		indent++;
		program.classes.forEach(x -> x.accept(this));
		indent--;

		return null;
	}

	@Override
	public Void visit(Relational relational) {
		printIndent(relational.getToken().getText());

		indent++;
		relational.left.accept(this);
		relational.right.accept(this);
		indent--;

		return null;
	}

	@Override
	public Void visit(Stringg stringg) {
		// replace for special characters
		String interpretedString = stringg.getToken().getText()
				.replace("\\r", "")
				.replace("\\t", "\t")
				.replace("\\n", "\n")
				.replace("\\b", "\b")
				.replace("\\f", "\f")
				.replace("\\\\", "#")  // Use a temporary placeholder
				.replace("\\", "")
				.replace("#", "\\");   // Restore double backslashes

		int len = interpretedString.length() - 1;
		// remove quotes from string
		printIndent(interpretedString.substring(1, len));

		return null;
	}

	@Override
	public Void visit(Type type) {
		printIndent(type.getToken().getText());

		return null;
	}

	@Override
	public Void visit(VarDef varDef) {
		var ctx = varDef.getCtx();
		printIndent("attribute");

		indent++;
		printIndent(ctx.name.getText());
		printIndent(ctx.type.getText());
		if (varDef.getInit() != null) {
			varDef.getInit().accept(this);
		}
		indent--;

		return null;
	}

	@Override
	public Void visit(While whilee) {
		printIndent("while");

		indent++;
		whilee.cond.accept(this);
		whilee.body.accept(this);
		indent--;

		return null;
	}

	private void printIndent(String str) {
		for (int i = 0; i < indent; i++)
			System.out.print("  ");
		System.out.println(str);
	}
}

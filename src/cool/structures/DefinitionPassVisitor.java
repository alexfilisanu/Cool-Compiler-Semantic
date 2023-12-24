package cool.structures;

import cool.compiler.*;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
	Scope currentScope = null;

	@Override
	public Void visit(Program program) {
		currentScope = new DefaultScope(null);
		currentScope.add(TypeSymbol.INT);
		currentScope.add(TypeSymbol.BOOL);
		currentScope.add(TypeSymbol.STRING);

		for (var stmt: program.getClasses()) {
			stmt.accept(this);
		}

		return null;
	}

	@Override
	public Void visit(Relational relational) {
		return null;
	}

	@Override
	public Void visit(Self self) {
		return null;
	}

	@Override
	public Void visit(Int intt) {
		return null;
	}

	@Override
	public Void visit(IsVoid isVoid) {
		return null;
	}

	@Override
	public Void visit(Let let) {
		return null;
	}

	@Override
	public Void visit(LetVar letVar) {
		return null;
	}

	@Override
	public Void visit(MultDiv multDiv) {
		return null;
	}

	@Override
	public Void visit(New neww) {
		return null;
	}

	@Override
	public Void visit(Not not) {
		return null;
	}

	@Override
	public Void visit(Paren paren) {
		return null;
	}

	@Override
	public Void visit(PlusMinus plusMinus) {
		return null;
	}

	@Override
	public Void visit(Assign assign) {
		return null;
	}

	@Override
	public Void visit(BitwiseNot bitwiseNot) {
		return null;
	}

	@Override
	public Void visit(Block block) {
		return null;
	}

	@Override
	public Void visit(Bool bool) {
		return null;
	}

	@Override
	public Void visit(Call call) {
		return null;
	}

	@Override
	public Void visit(Case casee) {
		return null;
	}

	@Override
	public Void visit(Dispatch dispatch) {
		return null;
	}

	@Override
	public Void visit(CaseStatement caseStatement) {
		return null;
	}

	@Override
	public Void visit(Classs classs) {
		var ctx = classs.getCtx();
		var typeSymbol = new TypeSymbol(ctx.type.getText());

		if (ctx.type.getText().equals("SELF_TYPE")) {
			SymbolTable.error(ctx, ctx.type, "Class has illegal name SELF_TYPE");
			return null;
		}

		if (!SymbolTable.globals.add(typeSymbol)) {
			SymbolTable.error(ctx, ctx.type, "Class " + ctx.type.getText() + " is redefined");
			return null;
		}


		var classSymbol = new ClassSymbol(ctx.type.getText(), currentScope);
		currentScope = classSymbol;

		Id classId = new Id(ctx.type);
		classId.setScope(currentScope);
		classId.setSymbol(classSymbol);

//		if (ctx.inherit != null) {
			SymbolTable.globals.add(classSymbol);
//		} else {
//			SymbolTable.classesAndParents.put(classNode.getName().getText(), null);
//		}

		var definitions = classs.getDefinitions();
		for (var def : definitions) {
			def.accept(this);
		}

		currentScope = currentScope.getParent();
		return null;
	}

	@Override
	public Void visit(Formal formal) {
		return null;
	}

	@Override
	public Void visit(FuncDef funcDef) {
		return null;
	}

	@Override
	public Void visit(Id id) {
		return null;
	}

	@Override
	public Void visit(If iff) {
		return null;
	}

	@Override
	public Void visit(Stringg stringg) {
		return null;
	}

	@Override
	public Void visit(VarDef varDef) {
		return null;
	}

	@Override
	public Void visit(While whilee) {
		return null;
	}

}
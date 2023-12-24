package cool.structures;

import cool.compiler.*;

public class ResolutionPassVisitor implements ASTVisitor<TypeSymbol> {

	@Override
	public TypeSymbol visit(Program program) {
		for (var stmt: program.getClasses()) {
			stmt.accept(this);
		}

		return null;
	}

	@Override
	public TypeSymbol visit(Self self) {
		return null;
	}

	@Override
	public TypeSymbol visit(Relational relational) {
		return null;
	}

	@Override
	public TypeSymbol visit(Int intt) {
		return TypeSymbol.INT;
	}

	@Override
	public TypeSymbol visit(LetVar letVar) {
		return null;
	}

	@Override
	public TypeSymbol visit(PlusMinus plusMinus) {
		return null;
	}

	@Override
	public TypeSymbol visit(Paren paren) {
		return null;
	}

	@Override
	public TypeSymbol visit(Not not) {
		return null;
	}

	@Override
	public TypeSymbol visit(New neww) {
		return null;
	}

	@Override
	public TypeSymbol visit(MultDiv multDiv) {
		return null;
	}

	@Override
	public TypeSymbol visit(Let let) {
		return null;
	}

	@Override
	public TypeSymbol visit(IsVoid isVoid) {
		return null;
	}

	@Override
	public TypeSymbol visit(Assign assign) {
		return null;
	}

	@Override
	public TypeSymbol visit(BitwiseNot bitwiseNot) {
		return null;
	}

	@Override
	public TypeSymbol visit(Block block) {
		return null;
	}

	@Override
	public TypeSymbol visit(Bool bool) {
		return TypeSymbol.BOOL;
	}

	@Override
	public TypeSymbol visit(CaseStatement caseStatement) {
		return null;
	}

	@Override
	public TypeSymbol visit(Classs classs) {
		var ctx = classs.getCtx();
		var child = ctx.type;
		var parent = ctx.inherit;

		if (parent != null) {
			if (SymbolTable.isIllegalParent(parent)) {
				SymbolTable.error(ctx, parent,
						"Class " + child.getText() + " has illegal parent " + parent.getText());
				return null;
			}

			if (SymbolTable.isUndefinedParent(parent)) {
				SymbolTable.error(ctx, parent,
						"Class " + child.getText() + " has undefined parent " + parent.getText());
				return null;
			}

			if (SymbolTable.isInheritanceCycle(child)) {
				SymbolTable.error(ctx, child,
						"Inheritance cycle for class " + child.getText());
				return null;
			}
		}

		return null;
	}

	@Override
	public TypeSymbol visit(Formal formal) {
		return null;
	}

	@Override
	public TypeSymbol visit(If iff) {
		return null;
	}

	@Override
	public TypeSymbol visit(Id id) {
		return null;
	}

	@Override
	public TypeSymbol visit(FuncDef funcDef) {
		return null;
	}

	@Override
	public TypeSymbol visit(Dispatch dispatch) {
		return null;
	}

	@Override
	public TypeSymbol visit(Case casee) {
		return null;
	}

	@Override
	public TypeSymbol visit(Call call) {
		return null;
	}

	@Override
	public TypeSymbol visit(Stringg stringg) {
		return TypeSymbol.STRING;
	}

	@Override
	public TypeSymbol visit(While whilee) {
		return null;
	}

	@Override
	public TypeSymbol visit(VarDef varDef) {
		return null;
	}

}
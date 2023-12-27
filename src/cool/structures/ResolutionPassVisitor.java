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
	public TypeSymbol visit(Relational relational) {
		return null;
	}

	@Override
	public TypeSymbol visit(Int intt) {
		return TypeSymbol.INT;
	}

	@Override
	public TypeSymbol visit(LetVar letVar) {
		if (letVar.getInit() != null) {
			letVar.getInit().accept(this);
		}

		return null;
	}

	@Override
	public TypeSymbol visit(PlusMinus plusMinus) {
		var exprTypeLeft = plusMinus.getLeft().accept(this);
		var exprTypeRight = plusMinus.getRight().accept(this);

		if (exprTypeLeft == null || exprTypeRight == null) {
			return null;
		}

		if (exprTypeLeft != TypeSymbol.INT) {
			SymbolTable.error(plusMinus.getCtx(), plusMinus.getLeft().getToken(),
					"Operand of " + plusMinus.getToken().getText() + " has type " + exprTypeLeft.getName() + " instead of Int");
//			return null;
		}

		if (exprTypeRight != TypeSymbol.INT) {
			SymbolTable.error(plusMinus.getCtx(), plusMinus.getRight().getToken(),
					"Operand of " + plusMinus.getToken().getText() + " has type " + exprTypeRight.getName() + " instead of Int");
//			return null;
		}

		return TypeSymbol.INT;
	}

	@Override
	public TypeSymbol visit(Paren paren) {
		var type = paren.getE().accept(this);
		return type;
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
		var exprTypeLeft = multDiv.getLeft().accept(this);
		var exprTypeRight = multDiv.getRight().accept(this);

		if (exprTypeLeft == null || exprTypeRight == null) {
			return null;
		}

		if (exprTypeLeft != TypeSymbol.INT) {
			SymbolTable.error(multDiv.getCtx(), multDiv.getLeft().getToken(),
					"Operand of " + multDiv.getToken().getText() + " has type " + exprTypeLeft.getName() + " instead of Int");
//			return null;
		}

		if (exprTypeRight != TypeSymbol.INT) {
			SymbolTable.error(multDiv.getCtx(), multDiv.getRight().getToken(),
					"Operand of " + multDiv.getToken().getText() + " has type " + exprTypeRight.getName() + " instead of Int");
//			return null;
		}

		return TypeSymbol.INT;
	}

	@Override
	public TypeSymbol visit(Let let) {
		for(var letVar : let.getLetVars()) {
			letVar.accept(this);
		}

		let.getBody().accept(this);

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
		var exprType = bitwiseNot.getE().accept(this);

		if (exprType == null) {
			return null;
		}

		if (exprType != TypeSymbol.INT) {
			SymbolTable.error(bitwiseNot.getCtx(), bitwiseNot.getE().getToken(),
					"Operand of " + bitwiseNot.getToken().getText() + " has type " + exprType.getName() + " instead of Int");
//			return null;
		}

		return TypeSymbol.INT;
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

		var definitions = classs.getDefinitions();
		for (var def : definitions) {
			def.accept(this);
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
		// Verificăm dacă într-adevăr avem de-a face cu o variabilă
		// sau cu o funcție, al doilea caz constituind eroare.
		// Puteți folosi instanceof.
		if (id.getScope() == null || id.getToken().getText().equals("self")) {
			return null;
		}

		if (id.getSymbol().getName().equals("true") || id.getSymbol().getName().equals("false")) {
			return TypeSymbol.BOOL;
		}

		var symbol = id.getScope().lookup(id.getToken().getText());
		if (id.getSymbol() != null) {
			id.getScope().add(id.getSymbol());
		}

		if (symbol == null) {
			SymbolTable.error(id.getCtx(), id.getToken(), "Undefined identifier " + id.getToken().getText());
			return null;
		}

		// TODO 2: Întoarcem informația de tip salvată deja în simbol încă de la
		// definirea variabilei.
		if (id.getSymbol() != null) {
			return id.getSymbol().getType();
		}

		return null;
	}

	@Override
	public TypeSymbol visit(FuncDef funcDef) {
		var ctx = funcDef.getCtx();
		var id = funcDef.getId();
		var type = funcDef.getType();

		if (SymbolTable.isOverridingMethodWithDifferentNumberOfFormals(id)) {
			SymbolTable.error(ctx, id.getToken(),
					"Class " + id.getScope().getParent().toString() + " overrides method "
							+ id.getToken().getText() + " with different number of formal parameters");
			return null;
		}

		Object[] result = SymbolTable.isOverridingMethodWithDifferentTypeOfFormals(id);
		if ((boolean)result[0]) {
			var idSymbolName = ((IdSymbol) result[2]).getName();
			var token = ctx.formals.stream()
					.filter(x -> x.name.getText().equals(idSymbolName))
					.findFirst()
					.get();
			SymbolTable.error(ctx, token.type,
					"Class " + id.getScope().getParent().toString() + " overrides method "
							+ id.getToken().getText() + " but changes type of formal parameter "
							+ ((IdSymbol)result[2]).getName() + " from " + ((IdSymbol)result[1]).getType().getName()
							+ " to " + ((IdSymbol)result[2]).getType().getName());
			return null;
		}

		result = SymbolTable.isOverridingMethodWithDifferentReturnType(id);
		if ((boolean)result[0]) {
			SymbolTable.error(ctx, ctx.type,
					"Class " + id.getScope().getParent().toString() + " overrides method "
							+ id.getToken().getText() + " but changes return type from "
							+ ((TypeSymbol)result[1]).getName() + " to "
							+ ((TypeSymbol)result[2]).getName());
			return null;
		}

		for (var param : funcDef.getParams()) {
			param.accept(this);
		}

		funcDef.getBody().accept(this);

		return null;
	}

	@Override
	public TypeSymbol visit(Dispatch dispatch) {
		return null;
	}

	@Override
	public TypeSymbol visit(Case casee) {
		for (var caseStatement : casee.getCaseStatements()) {
			caseStatement.accept(this);
		}

		casee.getE().accept(this);

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
		var ctx = varDef.getCtx();
		var type = varDef.getType();
		var id = varDef.getId();

		if (SymbolTable.isRedefinedInheritedAttribute(id)) {
			SymbolTable.error(ctx, id.getToken(),
					"Class " + id.getScope().toString() + " redefines inherited attribute " + id.getToken().getText());
			return null;
		}

		if (varDef.getInit() != null) {
			varDef.getInit().accept(this);
		}

		return null;
	}

	@Override
	public TypeSymbol visit(Type type) {
		return null;
	}
}
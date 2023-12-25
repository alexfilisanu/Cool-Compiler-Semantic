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
		var symbol = id.getScope().lookup(id.getToken().getText());

		if (symbol instanceof FunctionSymbol) {
//			ASTVisitor.error(id.getToken(),
//					id.getToken().getText() + " is not a variable");
			return null;
		}

		// TODO 2: Întoarcem informația de tip salvată deja în simbol încă de la
		// definirea variabilei.
		return id.getSymbol().getType();
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
		var ctx = varDef.getCtx();
		var type = varDef.getType();
		var id = varDef.getId();

		if (SymbolTable.isRedefinedInheritedAttribute(id)) {
			SymbolTable.error(ctx, id.getToken(),
					"Class " + id.getScope().toString() + " redefines inherited attribute " + id.getToken().getText());
		}
		return null;
	}

	@Override
	public TypeSymbol visit(Type type) {
		return null;
	}
}
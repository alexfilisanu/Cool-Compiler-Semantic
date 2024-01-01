package cool.structures;

import cool.compiler.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
		var exprTypeLeft = relational.getLeft().accept(this);
		var exprTypeRight = relational.getRight().accept(this);

		if (exprTypeLeft == null || exprTypeRight == null) {
			return null;
		}

		if (exprTypeLeft != exprTypeRight) {
			if (relational.getToken().getText().equals("=")) {
				if ((exprTypeLeft == TypeSymbol.INT || exprTypeLeft == TypeSymbol.STRING
						|| exprTypeLeft == TypeSymbol.BOOL) && (exprTypeRight == TypeSymbol.INT
						|| exprTypeRight == TypeSymbol.STRING || exprTypeRight == TypeSymbol.BOOL)) {
					SymbolTable.error(relational.getCtx(), relational.getToken(),
							"Cannot compare " + exprTypeLeft.getName() + " with " + exprTypeRight.getName());
				}
			} else {
				SymbolTable.error(relational.getCtx(), relational.getRight().getToken(),
						"Operand of " + relational.getToken().getText() + " has type " + exprTypeRight.getName() + " instead of " + exprTypeLeft.getName());
			}
		}

		return TypeSymbol.BOOL;
	}

	@Override
	public TypeSymbol visit(Int intt) {
		return TypeSymbol.INT;
	}

	@Override
	public TypeSymbol visit(LetVar letVar) {
		var ctx = letVar.getCtx();
		var id = letVar.getId();
		var type = letVar.getType();

		if (id.getScope() != null) {
			if (SymbolTable.globals.lookup(type.getToken().getText()) == null && !type.getToken().getText().equals("SELF_TYPE")) {
				SymbolTable.error(ctx, type.getToken(),
						"Let variable " + ctx.name.getText() + " has undefined type " + type.getToken().getText());
				return null;
			}

			id.getSymbol().setType((TypeSymbol) id.getScope().lookup(type.getToken().getText()));
//			id.getScope().add(id.getSymbol());

		}

		TypeSymbol idType = null;
		var typeA = SymbolTable.globals.lookup(letVar.getType().getToken().getText());
		if (typeA instanceof ClassSymbol) {
			idType = ((ClassSymbol) typeA).getType();
		} else if (typeA instanceof TypeSymbol) {
			idType = (TypeSymbol) typeA;
		}

		if (letVar.getInit() == null) {
			return idType;
		}

		// hack2
		var initType = letVar.getInit().accept(this);

		// TODO 5: Verificăm dacă expresia cu care se realizează atribuirea
		// are tipul potrivit cu cel declarat pentru variabilă.
		if (initType == null) {
//			ASTVisitor.error(assign.expr.getToken(),
//					" Assignment with incompatible types " + idType.getName() + " NULL");
			return null;
		}

		if (idType != null && (!idType.getName().equals(initType.getName()) && !SymbolTable.isInheritedClass(initType, idType))) {
			SymbolTable.error(letVar.getCtx(), letVar.getInit().getToken(),
					"Type " + initType.getName() + " of initialization expression of identifier "
							+ letVar.getId().getToken().getText() + " is incompatible with declared type " + idType.getName());
//			return null;
		}



		return idType;
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
		var exprType = not.getE().accept(this);

		if (exprType == null) {
			return null;
		}

		if (exprType != TypeSymbol.BOOL) {
			SymbolTable.error(not.getCtx(), not.getE().getToken(),
					"Operand of " + not.getToken().getText() + " has type " + exprType.getName() + " instead of Bool");
//			return null;
		}

		return TypeSymbol.BOOL;
	}

	@Override
	public TypeSymbol visit(New neww) {
		var ctx = neww.getCtx();
		var type = neww.getType();

		if (SymbolTable.globals.lookup(type.getToken().getText()) == null) {
			SymbolTable.error(ctx, type.getToken(), "new is used with undefined type " + type.getToken().getText());
			return null;
		}

		return ((IdSymbol) SymbolTable.globals.lookup(type.getToken().getText())).getType();
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
		for (var letVar : let.getLetVars()) {
			// hack
//			if (letVar.getId().getScope() != null) {
//				letVar.getId().setScope(letVar.getId().getScope().getParent());
//			}
			letVar.accept(this);
		}

		return let.getBody().accept(this);

//		return null;
	}

	@Override
	public TypeSymbol visit(IsVoid isVoid) {
		return null;
	}

	@Override
	public TypeSymbol visit(Assign assign) {
		var idType   = assign.getId().accept(this);
		var exprType = assign.getValue().accept(this);

		// TODO 5: Verificăm dacă expresia cu care se realizează atribuirea
		// are tipul potrivit cu cel declarat pentru variabilă.
		if (exprType == null) {
//			ASTVisitor.error(assign.expr.getToken(),
//					" Assignment with incompatible types " + idType.getName() + " NULL");
			return null;
		}


		if (!idType.getName().equals(exprType.getName()) && !SymbolTable.isInheritedClass(exprType, idType)) {
			if (assign.getValue() instanceof Dispatch) {
				SymbolTable.error(assign.getCtx(), ((Dispatch) assign.getValue()).getCtx().start,
						"Type " + exprType.getName() + " of assigned expression is incompatible with declared type "
								+ idType.getName() + " of identifier " + assign.getId().getToken().getText());
			} else {
				SymbolTable.error(assign.getCtx(), assign.getValue().getToken(),
						"Type " + exprType.getName() + " of assigned expression is incompatible with declared type "
								+ idType.getName() + " of identifier " + assign.getId().getToken().getText());
			}
		}

		return idType;
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
		TypeSymbol type = null;
		for (var expr : block.getExprs()) {
			type = expr.accept(this);
			if (expr instanceof Assign) {
				type = null;
			}
		}

		return type;
	}

	@Override
	public TypeSymbol visit(Bool bool) {
		return TypeSymbol.BOOL;
	}

	@Override
	public TypeSymbol visit(CaseStatement caseStatement) {
		if (caseStatement.getId().getScope() != null && caseStatement.getInit().getToken() != null) {
			if (caseStatement.getInit() instanceof Int) {
				return TypeSymbol.INT;
			}

			if (caseStatement.getInit() instanceof Bool) {
				return TypeSymbol.BOOL;
			}

			if (caseStatement.getInit() instanceof Stringg) {
				return TypeSymbol.STRING;
			}

			if (caseStatement.getId().getScope().lookup(caseStatement.getInit().getToken().getText()) != null) {
				return ((IdSymbol) caseStatement.getId().getScope().lookup(caseStatement.getInit().getToken().getText())).getType();
			}
//			return caseStatement.getInit().accept(this);
		}
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
		var ctx = formal.getCtx();
		var name = ctx.name;
		var id = formal.getId();
		var type = formal.getType();

		if (id.getScope() != null) {
			if (Objects.isNull(id.getScope().lookup(type.getToken().getText()))) {
				SymbolTable.error(ctx, type.getToken(),
						"Method " + id.getScope().toString() + " of class " + id.getScope().getParent().toString()
							+ " has formal parameter " + name.getText() + " with undefined type " + type.getToken().getText());
				return null;
			}

			id.getSymbol().setType((TypeSymbol) id.getScope().lookup(type.getToken().getText()));
		}

		if (id.getSymbol() != null) {
			return id.getSymbol().getType();
		} else {
			return null;
		}
	}

	@Override
	public TypeSymbol visit(If iff) {
		var ctx = iff.getCtx();
		var condType = iff.getCond().accept(this);
		var thenType = iff.getThenBranch().accept(this);
		var elseType = iff.getElseBranch().accept(this);

		if (condType != TypeSymbol.BOOL) {
			SymbolTable.error(ctx, iff.getCond().getToken(), "If condition has type " + condType.getName() + " instead of Bool");
		}

		if (thenType != null
				&& (thenType.getName().equals(elseType.getName())
				|| iff.getCond().getToken().getText().equals("true"))) {
			return thenType;
		} else {
			return TypeSymbol.OBJECT; //elseType;
		}
	}

	@Override
	public TypeSymbol visit(Id id) {
		// Verificăm dacă într-adevăr avem de-a face cu o variabilă
		// sau cu o funcție, al doilea caz constituind eroare.
		// Puteți folosi instanceof.
		if (id.getScope() == null || id.getToken().getText().equals("self")) {
			return null;
		}

		if (id.getToken().getText().equals("true") || id.getToken().getText().equals("false")) {
			return TypeSymbol.BOOL;
		}

		var	symbol = id.getScope().lookup(id.getToken().getText());
		if (symbol == null) {
//				|| ((DefaultScope) id.getScope().getParent()).getSymbols().isEmpty()) {
			if (id.getCtx() != null) {
				SymbolTable.error(id.getCtx(), id.getToken(), "Undefined identifier " + id.getToken().getText());
			}
			return null;
		}
//		id.getScope().add(symbol);

		// TODO 2: Întoarcem informația de tip salvată deja în simbol încă de la
		// definirea variabilei.
//		if (id.getSymbol() != null) {
//			return id.getSymbol().getType();
//		}

		if (symbol == null) {
			return null;
		} else {
			return ((IdSymbol) symbol).getType();
		}
//		return null;
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

		var bodyType = funcDef.getBody().accept(this);

		TypeSymbol idType = null;
		var typeA = SymbolTable.globals.lookup(type.getToken().getText());
		if (typeA instanceof ClassSymbol) {
			idType = ((ClassSymbol) typeA).getType();
		} else if (typeA instanceof TypeSymbol) {
			idType = (TypeSymbol) typeA;
		}

		if (type != null && !type.getToken().getText().equals("Object") && bodyType != null && !type.getToken().getText().equals(bodyType.getName())
		&& idType != null && !SymbolTable.isInheritedClass(bodyType, idType)) {
			SymbolTable.error(ctx, funcDef.getBody().getToken(),
					"Type " + bodyType.getName() + " of the body of method " + id.getToken().getText()
							+ " is incompatible with declared return type " + type.getToken().getText());
		}

		return null;
	}

	@Override
	public TypeSymbol visit(Dispatch dispatch) {
		var ctx = dispatch.getCtx();

		if (dispatch.getType().getToken() != null) {
			if ("SELF_TYPE".equals(dispatch.getType().getToken().getText())) {
				SymbolTable.error(ctx, dispatch.getType().getToken(),
						"Type of static dispatch cannot be SELF_TYPE");
				return null;
			}

			if (SymbolTable.globals.lookup(dispatch.getType().getToken().getText()) == null) {
				SymbolTable.error(ctx, dispatch.getType().getToken(),
						"Type " + dispatch.getType().getToken().getText() + " of static dispatch is undefined");
				return null;
			}

			TypeSymbol idType = null;
			var typeA = SymbolTable.globals.lookup(dispatch.getType().getToken().getText());
			if (typeA instanceof ClassSymbol) {
				idType = ((ClassSymbol) typeA).getType();
			} else if (typeA instanceof TypeSymbol) {
				idType = (TypeSymbol) typeA;
			}

			if (!SymbolTable.isInheritedClass(((Id) dispatch.getE()).getSymbol().getType(), idType)) {
				SymbolTable.error(ctx, dispatch.getType().getToken(),
					"Type " + idType + " of static dispatch is not a superclass of type "
							+ ((Id) dispatch.getE()).getSymbol().getType().getName());
			}

			if (SymbolTable.isUndefinedMethod(idType, dispatch.getToken().getText())) {
				SymbolTable.error(ctx, dispatch.getToken(),
						"Undefined method " + dispatch.getToken().getText() + " in class " + idType.getName());
				return null;
			}
		}

		if (dispatch.getToken().getText().equals("copy")) {
			return TypeSymbol.SELF_TYPE;
		}

		if (dispatch.getToken().getText().equals("out_string")) {
			return TypeSymbol.STRING;
		}

		if (dispatch.getToken().getText().equals("out_int")) {
			return TypeSymbol.INT;
		}

		if (dispatch.getToken().getText().equals("in_string")) {
			return TypeSymbol.STRING;
		}

		if (dispatch.getToken().getText().equals("in_int")) {
			return TypeSymbol.INT;
		}

		if (dispatch.getToken().getText().equals("length")) {
			return TypeSymbol.INT;
		}

		if (dispatch.getToken().getText().equals("concat")) {
			for (var arg : dispatch.getArgs()) {
				var typeArg = ((IdSymbol)(((Id) dispatch.getE()).getScope().lookup(arg.getToken().getText()))).getType();
				if (typeArg != TypeSymbol.STRING) {
					SymbolTable.error(ctx, arg.getToken(),
							"In call to method concat of class String, actual type " + typeArg.getName()
									+ " of formal parameter s is incompatible with declared type String");
				}
			}
			return TypeSymbol.STRING;
		}

		if (dispatch.getToken().getText().equals("substr")) {
			var typeArg = ((IdSymbol)(((Id) dispatch.getE()).getScope().lookup(dispatch.getArgs().getFirst().getToken().getText()))).getType();
			if (typeArg != TypeSymbol.STRING) {
				SymbolTable.error(ctx, dispatch.getArgs().getFirst().getToken(),
						"In call to method substr of class String, actual type " + typeArg.getName()
							+ " of formal parameter s is incompatible with declared type String");
			}

			for (var arg : dispatch.getArgs().subList(1, dispatch.getArgs().size())) {
				typeArg = ((IdSymbol)(((Id) dispatch.getE()).getScope().lookup(arg.getToken().getText()))).getType();
				if (typeArg != TypeSymbol.INT) {
					SymbolTable.error(ctx, arg.getToken(),
							"In call to method substr of class String, actual type " + typeArg.getName()
								+ " of formal parameter l is incompatible with declared type Int");
				}
			}
			return TypeSymbol.STRING;
		}

		var eType = dispatch.getE().accept(this);
		if (eType == null && (dispatch.getE().getToken() != null && "self".equals(dispatch.getE().getToken().getText()))) {
			if (((Id) dispatch.getE()).getScope().getParent() instanceof ClassSymbol) {
				eType = ((ClassSymbol) ((Id) dispatch.getE()).getScope().getParent()).getType();
			}
		}

		if (eType != null) {
			if (SymbolTable.globals.lookup(eType.getName()) == null) {
				SymbolTable.error(ctx, dispatch.getE().getToken(),
						"Dispatch on undefined class " + eType.getName());
				return null;
			}

			if (SymbolTable.isUndefinedMethod(eType, dispatch.getToken().getText())) {
				SymbolTable.error(ctx, dispatch.getToken(),
						"Undefined method " + dispatch.getToken().getText() + " in class " + eType.getName());
				return null;
			}

			if (!((FunctionSymbol) SymbolTable.globals.lookup(eType.getName())).symbols.isEmpty()
					&& ((ClassSymbol) SymbolTable.globals.lookup(eType.getName())).symbols.get(dispatch.getToken().getText()) != null
					&& dispatch.getArgs().size() - 1 != ((FunctionSymbol)((ClassSymbol) SymbolTable.globals.lookup(eType.getName())).symbols.get(dispatch.getToken().getText())).symbols.size()) {
				SymbolTable.error(ctx, dispatch.getToken(),
						"Method " + dispatch.getToken().getText() + " of class " + SymbolTable.globals.lookup(eType.getName())
							+ " is applied to wrong number of arguments");
				return null;
			}

			List<TypeSymbol> list1 = dispatch.getArgs().subList(1, dispatch.getArgs().size()).stream()
					.map(x -> x.accept(this))
					.toList();
			Object[] result = SymbolTable.isCallingMethodWithDifferentTypeOfFormals(list1, eType.getName(), dispatch.getToken().getText());
			if ((boolean)result[0]) {
				var pos = (int)result[3];
				SymbolTable.error(ctx, dispatch.getArgs().get(pos + 1).getToken(),
					"In call to method " + dispatch.getToken().getText() + " of class " + SymbolTable.globals.lookup(eType.getName())
						+ ", actual type " + list1.get(pos).getName() + " of formal parameter " + result[1]
						+ " is incompatible with declared type " + result[2]);
			}

			if (dispatch.getE() instanceof Id) {
				if (((Id) dispatch.getE()).getScope() instanceof FunctionSymbol) {
					return ((FunctionSymbol) ((Id) dispatch.getE()).getScope()).getType();
				}
			}
		}

		return null;
	}

	@Override
	public TypeSymbol visit(Case casee) {
		var eType = casee.getE().accept(this);

//		if (eType != TypeSymbol.INT && eType != TypeSymbol.BOOL && eType != TypeSymbol.STRING) {
//			return eType;
//		}

		Set<TypeSymbol> caseTypesPrimitives = new HashSet<>();
		ArrayList<TypeSymbol> caseTypesList = new ArrayList<>();
		for (var caseStatement : casee.getCaseStatements()) {
			var caseType = caseStatement.accept(this);
			if (eType == TypeSymbol.INT || eType == TypeSymbol.BOOL || eType == TypeSymbol.STRING) {
				caseTypesPrimitives.add(caseType);
				if (caseTypesPrimitives.size() > 1) {
					return TypeSymbol.OBJECT;
				}
			} else {
				caseTypesList.add(caseType);
			}
		}

		if (caseTypesPrimitives.size() == 1) {
			return eType;
		} else {
			return SymbolTable.getMostInheritedClass(caseTypesList);
		}
	}

	@Override
	public TypeSymbol visit(Call call) {
        if ("abort".equals(call.getToken().getText())) {
			return TypeSymbol.OBJECT;
		} else {
			List<TypeSymbol> list1 = call.getArgs().stream()
					.map(x -> x.accept(this))
					.toList();

			if (!call.getArgs().isEmpty()) {
				Object[] result = SymbolTable.isCallingMethodWithDifferentTypeOfFormals(list1, ((ClassSymbol) (FunctionSymbol) ((Id) call.getArgs().getFirst()).getScope().getParent()).getType().getName(), call.getToken().getText());
				if ((boolean) result[0]) {
					var pos = (int) result[3];
					SymbolTable.error(call.getCtx(), call.getArgs().get(pos).getToken(),
							"In call to method " + call.getToken().getText() + " of class " + ((ClassSymbol) (FunctionSymbol) ((Id) call.getArgs().getFirst()).getScope().getParent()).getType().getName()
									+ ", actual type " + list1.get(pos).getName() + " of formal parameter " + result[1]
									+ " is incompatible with declared type " + result[2]);
				}

				if (call.getArgs().getFirst() instanceof Id) {
					if (((Id) call.getArgs().getFirst()).getScope() instanceof FunctionSymbol) {
						return ((FunctionSymbol) ((Id) call.getArgs().getFirst()).getScope()).getType();
					}
				}
			}
			return null;
		}
	}

	@Override
	public TypeSymbol visit(Stringg stringg) {
		return TypeSymbol.STRING;
	}

	@Override
	public TypeSymbol visit(While whilee) {
		var condType = whilee.getCond().accept(this);

		if (condType != TypeSymbol.BOOL) {
			SymbolTable.error(whilee.getCtx(), whilee.getCond().getToken(),
					"While condition has type " + condType.getName() + " instead of Bool");
		}

		return TypeSymbol.OBJECT;
	}

	@Override
	public TypeSymbol visit(VarDef varDef) {
		var ctx = varDef.getCtx();
		var type = varDef.getType();
		var id = varDef.getId();

		if (id.getScope() != null) {
			if (id.getScope().lookup(type.getToken().getText()) == null) {
				SymbolTable.error(ctx, type.getToken(),
						"Class " + id.getScope().toString() + " has attribute " + ctx.name.getText()
								+ " with undefined type " + type.getToken().getText());
				return null;
			}
		}

		if (SymbolTable.isRedefinedInheritedAttribute(id)) {
			SymbolTable.error(ctx, id.getToken(),
					"Class " + id.getScope().toString() + " redefines inherited attribute " + id.getToken().getText());
			return null;
		}

		if (id.getScope() != null) {
			id.getSymbol().setType((TypeSymbol) id.getScope().lookup(type.getToken().getText()));
		}
		if (varDef.getInit() != null) {
			var varType = varDef.getInit().accept(this);
			TypeSymbol idType = null;

			var typeA = SymbolTable.globals.lookup(type.getToken().getText());
			if (typeA instanceof ClassSymbol) {
				idType = ((ClassSymbol) typeA).getType();
			} else if (typeA instanceof TypeSymbol) {
				idType = (TypeSymbol) typeA;
			}

			if (type != null && !type.getToken().getText().equals("Object") && varType != null && !type.getToken().getText().equals(varType.getName())
					&& idType != null && !SymbolTable.isInheritedClass(varType, idType)) {
				if (varDef.getInit() instanceof Dispatch) {
					SymbolTable.error(ctx, ((Dispatch) varDef.getInit()).getCtx().start,
							"Type " + varType.getName() + " of initialization expression of attribute " + id.getToken().getText()
									+ " is incompatible with declared type " + type.getToken().getText());
				} else {
					SymbolTable.error(ctx, varDef.getInit().getToken(),
							"Type " + varType.getName() + " of initialization expression of attribute " + id.getToken().getText()
									+ " is incompatible with declared type " + type.getToken().getText());
				}
			}
		}
		return null;
	}

	@Override
	public TypeSymbol visit(Type type) {
		return null;
	}
}
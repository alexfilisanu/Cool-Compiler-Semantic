package cool.structures;

import cool.compiler.*;

import java.util.Objects;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
	Scope currentScope = null;

	@Override
	public Void visit(Program program) {
		currentScope = new DefaultScope(null);
		currentScope.add(TypeSymbol.OBJECT);
		currentScope.add(TypeSymbol.IO);
		currentScope.add(TypeSymbol.INT);
		currentScope.add(TypeSymbol.STRING);
		currentScope.add(TypeSymbol.BOOL);
		currentScope.add(TypeSymbol.SELF_TYPE);

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
		var child = ctx.type;
		var parent = ctx.inherit;

		if (child.getText().equals("SELF_TYPE")) {
			SymbolTable.error(ctx, child, "Class has illegal name SELF_TYPE");
			return null;
		}

		var childClassSymbol = new ClassSymbol(child.getText(), currentScope);
		currentScope = childClassSymbol;

		if (!SymbolTable.globals.add(childClassSymbol)) {
			SymbolTable.error(ctx, child, "Class " + child.getText() + " is redefined");
			return null;
		}

		classs.getId().setScope(currentScope);
		classs.getId().setSymbol(childClassSymbol);

		SymbolTable.globals.add(childClassSymbol);
		if (parent != null) {
			SymbolTable.childToParentMap.put(child.getText(), parent.getText());
		} else {
			SymbolTable.childToParentMap.put(child.getText(), null);
		}

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
		// La definirea unei variabile, creăm un nou simbol.
		// Adăugăm simbolul în domeniul de vizibilitate curent.
		var ctx = varDef.getCtx();
		var name = ctx.name;
		var id = varDef.getId();
		var type = varDef.getType();

		var varDefSymbol = new IdSymbol(name.getText());

		if (name.getText().equals("self")) {
			SymbolTable.error(ctx, name,
					"Class " + currentScope.toString() + " has attribute with illegal name " + name.getText());
			return null;
		}

		// Semnalăm eroare dacă există deja variabila în scope-ul curent.
//		!((ClassSymbol)currentScope).addVar(sym)
		if (!currentScope.add(varDefSymbol)) {
			SymbolTable.error(ctx, name,
					"Class " + currentScope.toString() + " redefines attribute " + name.getText());
			return null;
		}

		// Atașăm simbolul nodului din arbore.
		id.setSymbol(varDefSymbol);
		id.setScope(currentScope);

		// TODO 1: Reținem informația de tip în simbolul nou creat.
		// Căutăm tipul variabilei.
		// Semnalăm eroare dacă nu există.
		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
			SymbolTable.error(ctx, type.getToken(),
					"Class " + currentScope.toString() + " has attribute " + name.getText()
							+ " with undefined type " + type.getToken().getText());
			return null;
		}

		// Reținem informația de tip în cadrul simbolului aferent
		// variabilei
		varDefSymbol.setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));

		if (varDef.getInit() != null) {
			varDef.getInit().accept(this);
		}

		// Tipul unei definiții ca instrucțiune în sine nu este relevant.
		return null;
	}

	@Override
	public Void visit(Type type) {
		return null;
	}

	@Override
	public Void visit(While whilee) {
		return null;
	}

}
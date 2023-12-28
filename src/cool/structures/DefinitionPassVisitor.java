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
		relational.getLeft().accept(this);
		relational.getRight().accept(this);
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
		Scope oldScope = currentScope;
		currentScope = new DefaultScope(currentScope);

		for(var letVar : let.getLetVars()) {
			letVar.accept(this);
		}

		let.getBody().accept(this);

		currentScope = oldScope;
		return null;
	}

	@Override
	public Void visit(LetVar letVar) {
		var ctx = letVar.getCtx();
		var name = ctx.name;
		var type = letVar.getType();

		if (name.getText().equals("self")) {
			SymbolTable.error(ctx, name,
					"Let variable has illegal name self");
			return null;
		}

		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
			SymbolTable.error(ctx, type.getToken(),
					"Let variable " + name.getText() + " has undefined type " + type.getToken().getText());
			return null;
		}

		if (letVar.getInit() != null) {
			letVar.getInit().accept(this);
		}

		return null;
	}

	@Override
	public Void visit(MultDiv multDiv) {
		multDiv.getLeft().accept(this);
		multDiv.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(New neww) {
		return null;
	}

	@Override
	public Void visit(Not not) {
		not.getE().accept(this);
		return null;
	}

	@Override
	public Void visit(Paren paren) {
		paren.getE().accept(this);
		return null;
	}

	@Override
	public Void visit(PlusMinus plusMinus) {
		plusMinus.getLeft().accept(this);
		plusMinus.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Assign assign) {
		if (assign.getId().getToken().getText().equals("self")) {
			SymbolTable.error(assign.getCtx(), assign.getId().getToken(),
					"Cannot assign to self");
			return null;
		}

		assign.getId().accept(this);
		assign.getValue().accept(this);
		return null;
	}

	@Override
	public Void visit(BitwiseNot bitwiseNot) {
		bitwiseNot.getE().accept(this);
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
//		Scope oldScope = currentScope;
//		currentScope = new DefaultScope(currentScope);

		for (var caseStatement : casee.getCaseStatements()) {
			caseStatement.accept(this);
		}

		casee.getE().accept(this);

//		currentScope = oldScope;
		return null;
	}

	@Override
	public Void visit(Dispatch dispatch) {
		return null;
	}

	@Override
	public Void visit(CaseStatement caseStatement) {
		var ctx = caseStatement.getCtx();
		var name = ctx.name;
		var id = caseStatement.getId();
		var type = caseStatement.getType();

		if (name.getText().equals("self")) {
			SymbolTable.error(ctx, name,
					"Case variable has illegal name self");
			return null;
		}

		if (type.getToken().getText().equals("SELF_TYPE")) {
			SymbolTable.error(ctx, type.getToken(),
					"Case variable " + name.getText() + " has illegal type SELF_TYPE");
			return null;
		}

		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
			SymbolTable.error(ctx, type.getToken(),
					"Case variable " + name.getText() + " has undefined type " + type.getToken().getText());
			return null;
		}

		var caseSymbol = new IdSymbol(id.getToken().getText());
		caseSymbol.setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));
		id.setSymbol(caseSymbol);
		id.setScope(currentScope);

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
		childClassSymbol.setType(new TypeSymbol(ctx.type.getText()));
		classs.getId().setSymbol(childClassSymbol);

		if (parent != null) {
			SymbolTable.childToParentMap.put(child.getText(), parent.getText());
		} else {
			SymbolTable.childToParentMap.put(child.getText(), null);
		}
		currentScope.getParent().add(new TypeSymbol(childClassSymbol.name));

		var definitions = classs.getDefinitions();
		for (var def : definitions) {
			def.accept(this);
		}

		currentScope = currentScope.getParent();
		return null;
	}

	@Override
	public Void visit(Formal formal) {
		// La definirea unei variabile, creăm un nou simbol.
		// Adăugăm simbolul în domeniul de vizibilitate curent.
		// Atașăm simbolul nodului din arbore si setăm scope-ul
		// pe variabila de tip Id, pentru a îl putea obține cu
		// getScope() în a doua trecere.
		var ctx = formal.getCtx();
		var name = ctx.name;
		var id   = formal.getId();
		var type = formal.getType();

		var symbol = new IdSymbol(name.getText());

		if (name.getText().equals("self")) {
			SymbolTable.error(ctx, name,
					"Method self of class " + currentScope.getParent().toString() + " has formal parameter with illegal name self");
			return null;
		}

		if (type.getToken().getText().equals("SELF_TYPE")) {
			SymbolTable.error(ctx, type.getToken(),
					"Method " + currentScope.toString() + " of class " + currentScope.getParent().toString() + " has formal parameter " + name.getText() + " with illegal type SELF_TYPE");
		}

//		if (SymbolTable.globals.lookup(n.type) == null) {
//			SymbolTable.error(n.ctx, n.ctx.typeName, "Method " + ((MethodSymbol) currentScope).getName() + " of class " + ((ClassSymbol) ((MethodSymbol) currentScope).parent).getName() + " has formal parameter " + n.id + " with undefined type " + n.type);
//			return;
//		}

		// Verificăm dacă parametrul deja există în scope-ul curent.
		if (! currentScope.add(symbol)) {
			SymbolTable.error(ctx, name,
					"Method " + currentScope.toString() + " of class " + currentScope.getParent().toString() + " redefines formal parameter " + name.getText());
			return null;
		}

		symbol.setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));
		id.setSymbol(symbol);
		id.setScope(currentScope);

		// TODO 1: Reținem informația de tip în simbolul nou creat.
		// Căutăm tipul variabilei.
		// Semnalăm eroare dacă nu există.
//		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
//			ASTVisitor.error(type.getToken(),
//					id.getToken().getText() + " does not exist");
//			return null;
//		}

		// Reținem informația de tip în cadrul simbolului aferent
		// variabilei.
		symbol.setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));

		// Tipul unei definiții ca instrucțiune în sine nu este relevant.
		return null;
	}

	@Override
	public Void visit(FuncDef funcDef) {
		// Asemeni variabilelor globale, vom defini un nou simbol
		// pentru funcții. Acest nou FunctionSymbol va avea că părinte scope-ul
		// curent currentScope și va avea numele funcției.
		//
		// Nu uitați să updatati scope-ul curent înainte să fie parcurs corpul funcției,
		// și să îl restaurati la loc după ce acesta a fost parcurs.
		var ctx = funcDef.getCtx();
		var name = ctx.name;
		var id = funcDef.getId();
		var type = funcDef.getType();

		var functionSymbol = new FunctionSymbol(name.getText(), currentScope);
		currentScope = functionSymbol;

		// Verificăm faptul că o funcție cu același nume nu a mai fost
		// definită până acum.
		if (!currentScope.getParent().add(functionSymbol)) {
			SymbolTable.error(ctx, name,
					"Class " + currentScope.getParent().toString() + " redefines method " + name.getText());
			return null;
		}

		functionSymbol.setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));
		id.setSymbol(functionSymbol);
		id.setScope(currentScope);

		// TODO 1: Reținem informația de tip în simbolul nou creat.
		// Căutăm tipul funcției.
		// Semnalăm eroare dacă nu există.
		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
//			ASTVisitor.error(type.getToken(),
//					id.getToken().getText() + " does not exist");

			return null;
		}

		// Reținem informația de tip în cadrul simbolului aferent funcției.
		id.getSymbol().setType((TypeSymbol) currentScope.lookup(type.getToken().getText()));

		for (var param: funcDef.getParams()) {
			param.accept(this);
		}

		funcDef.getBody().accept(this);

		currentScope = currentScope.getParent();
		return null;
	}

	@Override
	public Void visit(Id id) {
//		var symbol = (IdSymbol)currentScope.lookup(id.getToken().getText());
		var symbol = new IdSymbol(id.getToken().getText());
		if (currentScope.lookup(id.getToken().getText()) != null) {
			symbol.setType(((IdSymbol) currentScope.lookup(id.getToken().getText())).getType());
		}
		id.setScope(currentScope);

		// Semnalăm eroare dacă nu există.

		// Atașăm simbolul nodului din arbore.
		id.setSymbol(symbol);
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
//		if (Objects.isNull(currentScope.lookup(type.getToken().getText()))) {
//			SymbolTable.error(ctx, type.getToken(),
//					"Class " + currentScope.toString() + " has attribute " + name.getText()
//							+ " with undefined type " + type.getToken().getText());
//			return null;
//		}

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
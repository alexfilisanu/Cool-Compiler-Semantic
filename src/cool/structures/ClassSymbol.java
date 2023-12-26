package cool.structures;

// O clasa este asemanatoare unei functii, dar contine propriul sau scope
public class ClassSymbol extends FunctionSymbol {
	public DefaultScope vars;

	public ClassSymbol(String name, Scope parent) {
		super(name, parent);
		this.vars = new DefaultScope(parent);
	}

//	@Override
//	public Symbol lookup(String s) {
//		var sym = symbols.get(s);
//
//		if (sym != null)
//			return sym;
//
//		// Dacă nu găsim simbolul în domeniul de vizibilitate curent, îl căutăm
//		// în domeniul de deasupra.
//		if (parent != null && parent != SymbolTable.globals)
//			return parent.lookup(s);
//
//		return null;
//	}

//	public boolean addVar(Symbol sym) {
//		return vars.add(sym);
//	}
	@Override
	public boolean add(Symbol sym) {
		// Ne asiguram ca simbolul nu exista deja în domeniul de vizibilitate curent
		if (symbols.containsKey(sym.getName())) {
			var symbol = symbols.get(sym.getName());
			if (symbol.getClass().equals(sym.getClass())) {
				return false;
			}
		}

		symbols.put(sym.getName(), sym);

		return true;
	}
}

package cool.structures;

// O clasa este asemanatoare unei functii, dar contine propriul sau scope
public class ClassSymbol extends FunctionSymbol {
	public DefaultScope vars;

	public ClassSymbol(String name, Scope parent) {
		super(name, parent);
		this.vars = new DefaultScope(parent);
	}

	public boolean addVar(Symbol sym) {
		return vars.add(sym);
	}
}

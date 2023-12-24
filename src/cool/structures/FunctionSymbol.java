package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

// O functie este atat simbol, cat si domeniu de vizibilitate pentru parametrii
// sai formali
public class FunctionSymbol extends IdSymbol implements Scope {
	protected Map<String, Symbol> symbols = new LinkedHashMap<>();
	protected Scope parent;

	public FunctionSymbol(String name, Scope parent) {
		super(name);
		this.parent = parent;
	}

	@Override
	public boolean add(Symbol sym) {
		// Ne asiguram ca simbolul nu exista deja în domeniul de vizibilitate curent
		if (symbols.containsKey(sym.getName())) {
			return false;
		}

		symbols.put(sym.getName(), sym);

		return true;
	}

	@Override
	public Symbol lookup(String s) {
		var sym = symbols.get(s);

		if (sym != null) {
			return sym;
		}

		// Daca nu gasim simbolul in domeniul de vizibilitate curent, il cautam
		// in domeniul de deasupra.
		if (parent != null) {
			return parent.lookup(s);
		}

		return null;
	}

	@Override
	public Scope getParent() {
		return parent;
	}

	public Map<String, Symbol> getFormals() {
		return symbols;
	}
}

package cool.structures;

public class TypeSymbol extends Symbol {
	public TypeSymbol(String name) {
		super(name);
	}

	public static final TypeSymbol INT = new TypeSymbol("Int");
	public static final TypeSymbol STRING = new TypeSymbol("String");
	public static final TypeSymbol BOOL = new TypeSymbol("Bool");
}

package cool.compiler;

public interface ASTVisitor<T> {
	T visit(Id id);
	T visit(Int intt);
	T visit(If iff);
	T visit(Bool bool);
	T visit(Stringg string);
	T visit(Call call);
	T visit(Dispatch dispatch);
	T visit(BitwiseNot bitwiseNot);
	T visit(Not not);
	T visit(MultDiv multDiv);
	T visit(PlusMinus plusMinus);
	T visit(Relational relational);
	T visit(Assign assign);
	T visit(Classs classs);
	T visit(Program program);
	T visit(VarDef varDef);
	T visit(FuncDef funcDef);
	T visit(Formal formal);
	T visit(Paren paren);
	T visit(IsVoid isVoid);
	T visit(New neww);
	T visit(Self self);
}


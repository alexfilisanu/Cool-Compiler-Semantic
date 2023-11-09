package cool.compiler;

public interface ASTVisitor<T> {
	T visit(Id id);
	T visit(Int intt);
	T visit(If iff);
	T visit(Float floatt);
	T visit(Bool bool);
	T visit(Call call);
	T visit(BitwiseNot bitwiseNot);
	T visit(MultDiv multDiv);
	T visit(PlusMinus plusMinus);
	T visit(Relational relational);
	T visit(Assign assign);
	T visit(Classs classs);
	T visit(Program program);
	T visit(VarDef varDef);
}


package cool.structures;

import cool.compiler.Compiler;
import cool.parser.CoolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;
        
        globals.add(TypeSymbol.INT);
        globals.add(TypeSymbol.BOOL);
        globals.add(TypeSymbol.STRING);
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }

    public static boolean isIllegalParent(Token parent) {
        return parent.getText().equals("Int")
                || parent.getText().equals("String")
                || parent.getText().equals("Bool")
                || parent.getText().equals("SELF_TYPE");
    }

    public static boolean isUndefinedParent(Token parent) {
        return globals.lookup(parent.getText()) == null;
    }

    public static boolean isInheritanceCycle(Token child) {
        Scope scope = globals;
        while (scope != null) {
            if (scope.lookup(child.getText()) != null) {
                return true;
            }
            scope = scope.getParent();
        }

        return false;
    }

    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}

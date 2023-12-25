package cool.structures;

import cool.compiler.Compiler;
import cool.compiler.Id;
import cool.parser.CoolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {
    public static Scope globals;
    public static Map<String, String> childToParentMap = new LinkedHashMap<>();

    private static boolean semanticErrors;

    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;

        globals.add(TypeSymbol.OBJECT);
        globals.add(TypeSymbol.IO);
        globals.add(TypeSymbol.INT);
        globals.add(TypeSymbol.STRING);
        globals.add(TypeSymbol.BOOL);
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
        Set<String> visited = new HashSet<>();
        var current = child.getText();

        while (current != null) {
            if (!visited.add(current)) {
                return true;
            }

            var next = childToParentMap.get(current);
            if (next == null) {
                return false;
            }
            if (next.equals(child.getText())) {
                return true;
            }

            current = next;
        }

        return false;
    }

    public static boolean isRedefinedInheritedAttribute(Id id) {
        // check if var is redefined in inherited attributes
        var scope = id.getScope();

        while (scope != null) {
            var next = childToParentMap.get(scope.toString());
//            var scoala = ((ClassSymbol)globals.lookup(next)).symbols.containsKey(id.getToken().getText());

//            scope = globals.lookup(next);
            if (next == null || globals.lookup(next) instanceof TypeSymbol) {
                return false;
            }

            if (((ClassSymbol)globals.lookup(next)).symbols.containsKey(id.getToken().getText())) {
                return true;
            }

            scope = (ClassSymbol)globals.lookup(next);
        }

//        if (globals.lookup(id.getText()) != null) {
//            return true;
//        }

//        if (((ClassSymbol)globals.getParent()).vars.lookup(token.getText()) != null) {
//            return true;
//        }

//        var current = token.getText();
//
//
//        while (current != null) {
//            var next = childToParentMap.get(current);
//            if (next == null) {
//                return false;
//            }
//
//            if (next.equals(token.getText())) {
//                return true;
//            }
//
//            current = next;
//        }
//
        return false;
    }

    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}

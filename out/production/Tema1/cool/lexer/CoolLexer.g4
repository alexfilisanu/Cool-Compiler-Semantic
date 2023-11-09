lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

IF : 'if';
THEN : 'then';
ELSE : 'else';
FI: 'fi';

WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';

CASE : 'case';
OF : 'of';
ESAC : 'esac';

NEW : 'new';
CLASS : 'class';
INHERITS : 'inherits';

NOT : 'not';
IS_VOID : 'isvoid';

fragment SELF_TYPE : 'SELF_TYPE';

fragment LOWERCASE : [a-z];
fragment UPPERCASE : [A-Z];
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;

TYPE : 'Int' | 'Float' | 'Bool' | 'String' | 'Object' | SELF_TYPE;
ID : (LETTER | '_')(LETTER | '_' | DIGIT)*;

INT : DIGIT+;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;
BOOL : 'true' | 'false';
STRING : QUOTATION ('\\"' | .)*? QUOTATION;

SEMI : ';';
COMMA : ',';
COLON : ':';
QUOTATION : '"';
ASSIGN : '<-';
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
TILDE : '~';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
EQUAL : '=';
LT : '<';
LE : '<=';

fragment NEW_LINE : '\r'? '\n';

LINE_COMMENT : '--' .*? (NEW_LINE | EOF) -> skip;
BLOCK_COMMENT : '/*' (BLOCK_COMMENT | .)*? '*/' -> skip;

WS : [ \n\f\r\t]+ -> skip;

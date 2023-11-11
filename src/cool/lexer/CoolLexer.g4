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
SELF : 'self';

fragment SELF_TYPE : 'SELF_TYPE';

fragment LOWERCASE : [a-z];
fragment UPPERCASE : [A-Z];
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];

TYPE : UPPERCASE(LOWERCASE)* | SELF_TYPE;
ID : (LOWERCASE | '_')(LETTER | '_' | DIGIT)*;

INT : DIGIT+;
BOOL : 'true' | 'false';
STRING : QUOTATION ('\\"' | .)*? QUOTATION;

SEMI : ';';
COMMA : ',';
DOT : '.';
COLON : ':';
QUOTATION : '"';
ASSIGN : '<-';
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
AT_SIGN : '@';
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

parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program : (classes+=class SEMI)+ EOF;

class : CLASS name=ID (INHERITS inherit=ID)?
            LBRACE (definitions+=definition SEMI)* RBRACE;

definition
    :   name=ID COLON type=TYPE (ASSIGN init=expr)?                 # varDef
    |   name=ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN
            COLON type=TYPE LBRACE body=expr RBRACE                 # funcDef
    ;

formal : name=ID COLON type=TYPE;

expr
    :   name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN     # call
    |   TILDE e=expr                                                # bitwiseNot
    |   NEW name=ID                                                 # new
    |   left=expr op=(MULT | DIV) right=expr                        # multDiv
    |   left=expr op=(PLUS | MINUS) right=expr                      # plusMinus
    |   left=expr op=(LT | LE | EQUAL) right=expr                   # relational
    |   IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI   # if
    |   WHILE cond=expr LOOP body=expr POOL                         # while
    |   name=ID ASSIGN value=expr                                   # assign
    |   IS_VOID e=expr                                              # isvoid
    |   NOT e=expr                                                  # not
    |   LPAREN e=expr RPAREN                                        # paren
    |   ID                                                          # id
    |   INT                                                         # int
    |   FLOAT                                                       # float
    |   BOOL                                                        # bool
    |   STRING                                                      # string
    ;
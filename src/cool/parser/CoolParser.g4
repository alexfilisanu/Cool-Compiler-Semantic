parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program : (classes+=class SEMI)+ EOF;

class : CLASS name=ID (INHERITS inherit=ID)? LBRACE (definitions+=definition SEMI)* RBRACE;

definition
    :   declare (ASSIGN init=expr)?                                  # varDef
//    |   type=TYPE name=ID LPAREN (declares+=declare (COMMA declares+=declare)*)?
//            RPAREN LBRACE body=expr RBRACE      # funcDef
    ;

declare : name=ID COLON type=TYPE;

expr
    :   name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN     # call
    |   TILDE e=expr                                                # bitwiseNot
    |   left=expr op=(MULT | DIV) right=expr                        # multDiv
    |   left=expr op=(PLUS | MINUS) right=expr                      # plusMinus
    |   left=expr op=(LT | LE | EQUAL) right=expr                   # relational
    |   IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI   # if
    |   WHILE cond=expr LOOP body=expr POOL                         # while
    |   name=ID ASSIGN value=expr                                   # assign
    |   LPAREN e=expr RPAREN                                        # paren
    |   ID                                                          # id
    |   INT                                                         # int
    |   FLOAT                                                       # float
    |   BOOL                                                        # bool
    |   STRING                                                      # string
    ;
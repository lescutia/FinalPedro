package scanner;
import java_cup.runtime.*; // defines the Symbol class

// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.

class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
}

class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int line, int ch, int val) {
        super(line, ch);
        intVal = val;
    }
}

class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    idVal = val;
    }
}

class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int line, int ch, String val) {
        super(line, ch);
        strVal = val;
    }
}

// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}
%%

DIGIT=        [0-9]
WHITESPACE=   [\040\t]
LETTER=       [a-zA-Z]
ESCAPEDCHAR=   [nt'\"?\\]
NOTNEWLINEORESCAPEDCHAR=   [^\nnt'\"?\\]
NOTNEWLINEORQUOTE= [^\n\"]
NOTNEWLINEORQUOTEORESCAPE= [^\n\"\\]

%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol

%eofval{
return new Symbol(sym.EOF);
%eofval}

%line

%%

"bool"    {return new Symbol(sym.BOOL, yychar, yyline);}

"main"    {return new Symbol(sym.MAIN, yychar, yyline);}
          
"int"     {return new Symbol(sym.INT, yychar, yyline);}

"float"     {return new Symbol(sym.FLOAT, yychar, yyline);}
          
"void"    {return new Symbol(sym.VOID, yychar, yyline);}
          
"true"    {return new Symbol(sym.TRUE, yychar, yyline);}
          
"false"   {return new Symbol(sym.FALSE, yychar, yyline);}
          
"struct"  {return new Symbol(sym.STRUCT, yychar, yyline);}

"cin"     {return new Symbol(sym.CIN, yychar, yyline);}

"for"     {return new Symbol(sym.FOR, yychar, yyline);}
          
"cout"   {return new Symbol(sym.COUT, yychar, yyline);}

"continue"   {return new Symbol(sym.CONTINUE, yychar, yyline);}
          
"if"      {return new Symbol(sym.IF, yychar, yyline);}
          
"else"    {return new Symbol(sym.ELSE, yychar, yyline);}
          
"while"   {return new Symbol(sym.WHILE, yychar, yyline);}
          
"return"  {return new Symbol(sym.RETURN, yychar, yyline);}

"break"  {return new Symbol(sym.BREAK, yychar, yyline);}
          
({LETTER}|"_")({LETTER}|{DIGIT}|"_")* {
            {return new Symbol(sym.ID, yychar, yyline, new String(yytext()));}
          }

{DIGIT}+  {return new Symbol(sym.INTLITERAL, yychar, yyline, new String(yytext()));}

          
\"({NOTNEWLINEORQUOTEORESCAPE}|\\{ESCAPEDCHAR})*\" {return new Symbol(sym.STRINGLITERAL, yychar, yyline, new String(yytext()));}
          
\"({NOTNEWLINEORQUOTEORESCAPE}|\\{ESCAPEDCHAR})* {
            // unterminated string

          }
          
\"({NOTNEWLINEORQUOTEORESCAPE}|\\{ESCAPEDCHAR})*\\{NOTNEWLINEORESCAPEDCHAR}({NOTNEWLINEORQUOTE})*\" {
            // bad escape character

            CharNum.num += yytext().length();
          }
          
\"({NOTNEWLINEORQUOTEORESCAPE}|\\{ESCAPEDCHAR})*(\\{NOTNEWLINEORESCAPEDCHAR})?({NOTNEWLINEORQUOTEORESCAPE}|\\{ESCAPEDCHAR})*\\? {

          }          
          
\n        { CharNum.num = 1; }

{WHITESPACE}+  { CharNum.num += yytext().length(); }

("//"|"#")[^\n]*  { // comment - ignore. Note: don't need to update char num 
            // since everything to end of line will be ignored
          }

"{"       {return new Symbol(sym.LCURLY, yychar, yyline);}

"}"       {return new Symbol(sym.RCURLY, yychar, yyline);}

"("       {return new Symbol(sym.LPAREN, yychar, yyline);}

")"       {return new Symbol(sym.RPAREN, yychar, yyline);}

";"       {return new Symbol(sym.SEMICOLON, yychar, yyline);}
          
","       {return new Symbol(sym.COMA, yychar, yyline);}     
          
"."      {return new Symbol(sym.DOT, yychar, yyline);}        
          
"<<"      {return new Symbol(sym.WRITE, yychar, yyline);}

">>"      {return new Symbol(sym.READ, yychar, yyline);}
          
"++"      {return new Symbol(sym.PLUSPLUS, yychar, yyline);}

"--"     {return new Symbol(sym.MINUSMINUS, yychar, yyline);}

"+"       {return new Symbol(sym.PLUS, yychar, yyline);}
          
"-"       {return new Symbol(sym.MINUS ,yychar, yyline);}       
          
"*"      {return new Symbol(sym.TIMES, yychar, yyline);}             
          
"/"      {return new Symbol(sym.DIVIDE, yychar, yyline);}

"!"      {return new Symbol(sym.NOT, yychar, yyline);}
          
"&&"     {return new Symbol(sym.AND, yychar, yyline);}

"||"      {return new Symbol(sym.OR, yychar, yyline);}

"=="      {return new Symbol(sym.EQUALS, yychar, yyline);}
          
"!="      {return new Symbol(sym.NOTEQUALS, yychar, yyline);}         
          
"<"       {return new Symbol(sym.LESS, yychar, yyline);}           
          
">"       {return new Symbol(sym.GREATER, yychar, yyline);}

"<="      {return new Symbol(sym.LESSEQ, yychar, yyline);}

">="      {return new Symbol(sym.GREATEREQ, yychar, yyline);}       

"="       {return new Symbol(sym.ASSIGN, yychar, yyline);}  

.         { 
            return new Symbol(sym.errorlex, yychar, yyline, new String (yytext()));
            
          }
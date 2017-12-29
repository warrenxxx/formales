package ejemplo;
import java_cup.runtime.Symbol;

/*
Directivas:
public: clase publica
cup: compatibilidad con cup
full: extender el alfabeto con todos los valores de 8 bits
line: agrega la variable int yyline, para indicar la fila del lexema
char: agrega la variable int yychar, indica el indice del primer caracter del lexema
ignorecase: validar, indistitntamente si la letra es mayuscula o minuscula
eofval: especifica un valor de retorno al final del archivo
*/

%%


%cup
%full
%line
%{

	
	
	private int countLines(String str){
		int count = 0;
		for(int i = 0; i < str.length(); ++i){
			if(str.charAt(i) == '\n'){
				++count;
			}
		}
		return count;
	}
%}
%char
%ignorecase
%eofval{
	return new Symbol(sym.EOF,new String("Fin del archivo"));
%eofval}

digito = [0-9]
letra = [a-zA-Z]
id = {letra}({letra}|{digito}|"_")*
espacio = \t|\f|" "|\r|\n
COMMENT=  \<\!\-\-({letra})*\-\-\>
%%

"<>"		{return new Symbol(sym.DIFERENTE, yychar, yyline, yytext());}
">="		{return new Symbol(sym.MAYORIGUAL, yychar, yyline, yytext());}
"<="		{return new Symbol(sym.MENORIGUAL, yychar, yyline, yytext());}
"-="		{return new Symbol(sym.MENOSNUM, yychar, yyline, yytext());}
"--"		{return new Symbol(sym.MENOSUNO, yychar, yyline, yytext());}
"+="		{return new Symbol(sym.MASNUM, yychar, yyline, yytext());}
"++"		{return new Symbol(sym.MASUNO, yychar, yyline, yytext());}
"="			{return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
">"			{return new Symbol(sym.MAYOR, yychar, yyline, yytext());}
"<"			{return new Symbol(sym.MENOR, yychar, yyline, yytext());}
";"			{return new Symbol(sym.PYCOMA, yychar, yyline, yytext());}
","			{return new Symbol(sym.COMA, yychar, yyline, yytext());}
"("			{return new Symbol(sym.PARIZQ, yychar, yyline, yytext());}
")"			{return new Symbol(sym.PARDER, yychar, yyline, yytext());}
"{"			{return new Symbol(sym.LLAVEIZQ, yychar, yyline, yytext());}
"}"			{return new Symbol(sym.LLAVEDER, yychar, yyline, yytext());}
"for"		{return new Symbol(sym.FOR, yychar, yyline, yytext());}

"<html>"		{System.out.println("Sentencia analizada para <html>. Sin errores."); return new Symbol(sym.htmla, yychar, yyline, yytext());}
"</html>"		{System.out.println("Sentencia analizada para </html>. Sin errores.");return new Symbol(sym.htmlb, yychar, yyline, yytext());}
"<head>"		{System.out.println("Sentencia analizada para <head>. Sin errores.");return new Symbol(sym.heada, yychar, yyline, yytext());}
"</head>"		{System.out.println("Sentencia analizada para </head>. Sin errores.");return new Symbol(sym.headb, yychar, yyline, yytext());}
"<title>"		{System.out.println("Sentencia analizada para <title>. Sin errores.");return new Symbol(sym.titlea, yychar, yyline, yytext());}
"</title>"		{System.out.println("Sentencia analizada para </title>. Sin errores.");return new Symbol(sym.titleb, yychar, yyline, yytext());}
"<body>"		{System.out.println("Sentencia analizada para <body>. Sin errores.");return new Symbol(sym.bodya, yychar, yyline, yytext());}
"</body>"		{System.out.println("Sentencia analizada para </body>. Sin errores.");return new Symbol(sym.bodyb, yychar, yyline, yytext());}
"<p>"		{System.out.println("Sentencia analizada para <p>. Sin errores.");return new Symbol(sym.pa, yychar, yyline, yytext());}
"</p>"		{System.out.println("Sentencia analizada para </p>. Sin errores.");return new Symbol(sym.pb, yychar, yyline, yytext());}
"<hr>"		{System.out.println("Sentencia analizada para <hr>. Sin errores.");return new Symbol(sym.hr, yychar, yyline, yytext());}
"<h1>"		{System.out.println("Sentencia analizada para <h1>. Sin errores.");return new Symbol(sym.h1a, yychar, yyline, yytext());}
"</h1>"		{System.out.println("Sentencia analizada para </h1>. Sin errores.");return new Symbol(sym.h1b, yychar, yyline, yytext());}
"<ul>"		{System.out.println("Sentencia analizada para <ul>. Sin errores.");return new Symbol(sym.ula, yychar, yyline, yytext());}
"</ul>"		{System.out.println("Sentencia analizada para </ul>. Sin errores.");return new Symbol(sym.ulb, yychar, yyline, yytext());}
"<il>"		{System.out.println("Sentencia analizada para <il>. Sin errores.");return new Symbol(sym.ila, yychar, yyline, yytext());}
"</il>"		{System.out.println("Sentencia analizada para </il>. Sin errores.");return new Symbol(sym.ilb, yychar, yyline, yytext());}
{COMMENT}				{ }		
{id}		{return new Symbol(sym.ID, yychar, yyline, yytext());}
{digito}+	{return new Symbol(sym.NUMERO, yychar, yyline, new Integer(yytext()));}
{espacio}	{}
.			{ System.out.println("Caracter ilegal: " + yytext()); }
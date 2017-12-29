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


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;

	private int countLines(String str){
		int count = 0;
		for(int i = 0; i < str.length(); ++i){
			if(str.charAt(i) == '\n'){
				++count;
			}
		}
		return count;
	}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,258,
"34:9,33:2,34,33:2,34:18,33,29,34:6,8,9,34,5,7,4,34,19,31,27,31:8,34,6,1,3,2" +
",34:2,21,24,30,22,20,12,30,15,23,30:2,18,17,30,13,26,30,14,30,16,28,30:3,25" +
",30,34:4,32,34,21,24,30,22,20,12,30,15,23,30:2,18,17,30,13,26,30,14,30,16,2" +
"8,30:3,25,30,10,34,11,34:130,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,97,
"0,1,2,3,1,4,1:6,5,6,1:9,7,1:17,8,9,10,11,1,7,12,13,14,15,16,17,18,19,20,21," +
"22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46," +
"47,48,49,7,50,51,52,53,54,55,56,57,58,59,60")[0];

	private int yy_nxt[][] = unpackFromString(61,35,
"1,2,3,4,5,42,6,7,8,9,10,11,12,85:6,45,85:7,13,85,45,85,13,45,14,45,-1:37,15" +
",16,-1:11,41,44,-1:2,47,-1:3,48,49,-1,50,-1,86,51,-1:8,17,-1:34,18,19,-1:42" +
",85,43,85:5,-1,85:7,46,85,-1,85,46:2,-1:29,13,-1:3,13,-1:15,85:7,-1,85:7,46" +
",85,-1,85,46:2,-1:16,52,-1,53,-1:3,54,-1:6,55,-1:10,20,-1,21,-1:41,85:2,23," +
"85:4,-1,85:7,46,85,-1,85,46:2,-1:25,56,-1:26,57,58,-1:6,92,96,-1,59,-1,94,-" +
"1:24,60,-1:29,61,-1:23,22,-1:36,87,-1:32,24,-1:49,63,-1:38,88,-1:15,25,-1:4" +
"8,89,-1:34,64,-1:3,65,-1:6,66,-1:30,67,-1:13,26,-1:34,27,-1:54,70,-1:14,28," +
"-1:50,72,-1:33,75,-1:38,76,-1:15,29,-1:48,91,-1:20,30,-1:34,31,-1:57,77,-1:" +
"13,93,-1:7,71:7,-1,71:7,-1,71,-1,71,-1:6,32,-1:34,33,-1:52,78,-1:32,79,-1:3" +
"8,80,-1:14,34,-1:34,35,-1:34,36,-1:34,37,-1:52,84,-1:16,38,-1:34,39,-1:34,4" +
"0,-1:50,62,-1:20,71,-1:52,73,-1:30,74,-1:41,82,-1:27,81,-1:34,68,-1:20,83,-" +
"1:48,69,-1:38,90,-1:25,95,-1:21");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF,new String("Fin del archivo"));
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.MENOR, yychar, yyline, yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym.MAYOR, yychar, yyline, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.IGUAL, yychar, yyline, yytext());}
					case -5:
						break;
					case 5:
						{ System.out.println("Caracter ilegal: " + yytext()); }
					case -6:
						break;
					case 6:
						{return new Symbol(sym.PYCOMA, yychar, yyline, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.COMA, yychar, yyline, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.PARIZQ, yychar, yyline, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.PARDER, yychar, yyline, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.LLAVEIZQ, yychar, yyline, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.LLAVEDER, yychar, yyline, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.ID, yychar, yyline, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.NUMERO, yychar, yyline, new Integer(yytext()));}
					case -14:
						break;
					case 14:
						{}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.DIFERENTE, yychar, yyline, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.MENORIGUAL, yychar, yyline, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.MAYORIGUAL, yychar, yyline, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.MENOSNUM, yychar, yyline, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.MENOSUNO, yychar, yyline, yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.MASNUM, yychar, yyline, yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.MASUNO, yychar, yyline, yytext());}
					case -22:
						break;
					case 22:
						{System.out.println("Sentencia analizada para <p>. Sin errores.");return new Symbol(sym.pa, yychar, yyline, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.FOR, yychar, yyline, yytext());}
					case -24:
						break;
					case 24:
						{System.out.println("Sentencia analizada para <hr>. Sin errores.");return new Symbol(sym.hr, yychar, yyline, yytext());}
					case -25:
						break;
					case 25:
						{System.out.println("Sentencia analizada para <h1>. Sin errores.");return new Symbol(sym.h1a, yychar, yyline, yytext());}
					case -26:
						break;
					case 26:
						{System.out.println("Sentencia analizada para </p>. Sin errores.");return new Symbol(sym.pb, yychar, yyline, yytext());}
					case -27:
						break;
					case 27:
						{System.out.println("Sentencia analizada para <il>. Sin errores.");return new Symbol(sym.ila, yychar, yyline, yytext());}
					case -28:
						break;
					case 28:
						{System.out.println("Sentencia analizada para <ul>. Sin errores.");return new Symbol(sym.ula, yychar, yyline, yytext());}
					case -29:
						break;
					case 29:
						{System.out.println("Sentencia analizada para </h1>. Sin errores.");return new Symbol(sym.h1b, yychar, yyline, yytext());}
					case -30:
						break;
					case 30:
						{System.out.println("Sentencia analizada para </il>. Sin errores.");return new Symbol(sym.ilb, yychar, yyline, yytext());}
					case -31:
						break;
					case 31:
						{System.out.println("Sentencia analizada para </ul>. Sin errores.");return new Symbol(sym.ulb, yychar, yyline, yytext());}
					case -32:
						break;
					case 32:
						{System.out.println("Sentencia analizada para <html>. Sin errores."); return new Symbol(sym.htmla, yychar, yyline, yytext());}
					case -33:
						break;
					case 33:
						{System.out.println("Sentencia analizada para <head>. Sin errores.");return new Symbol(sym.heada, yychar, yyline, yytext());}
					case -34:
						break;
					case 34:
						{System.out.println("Sentencia analizada para <body>. Sin errores.");return new Symbol(sym.bodya, yychar, yyline, yytext());}
					case -35:
						break;
					case 35:
						{System.out.println("Sentencia analizada para <title>. Sin errores.");return new Symbol(sym.titlea, yychar, yyline, yytext());}
					case -36:
						break;
					case 36:
						{System.out.println("Sentencia analizada para </html>. Sin errores.");return new Symbol(sym.htmlb, yychar, yyline, yytext());}
					case -37:
						break;
					case 37:
						{System.out.println("Sentencia analizada para </head>. Sin errores.");return new Symbol(sym.headb, yychar, yyline, yytext());}
					case -38:
						break;
					case 38:
						{System.out.println("Sentencia analizada para </body>. Sin errores.");return new Symbol(sym.bodyb, yychar, yyline, yytext());}
					case -39:
						break;
					case 39:
						{ }
					case -40:
						break;
					case 40:
						{System.out.println("Sentencia analizada para </title>. Sin errores.");return new Symbol(sym.titleb, yychar, yyline, yytext());}
					case -41:
						break;
					case 42:
						{ System.out.println("Caracter ilegal: " + yytext()); }
					case -42:
						break;
					case 43:
						{return new Symbol(sym.ID, yychar, yyline, yytext());}
					case -43:
						break;
					case 45:
						{ System.out.println("Caracter ilegal: " + yytext()); }
					case -44:
						break;
					case 46:
						{return new Symbol(sym.ID, yychar, yyline, yytext());}
					case -45:
						break;
					case 85:
						{return new Symbol(sym.ID, yychar, yyline, yytext());}
					case -46:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}

// Generated from C:/Users/mihai/Downloads/CPL/Tema1/src/cool/lexer/CoolLexer.g4 by ANTLR 4.13.1

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, WHILE=6, LOOP=7, POOL=8, LET=9, IN=10, 
		CASE=11, OF=12, ESAC=13, NEW=14, CLASS=15, INHERITS=16, NOT=17, IS_VOID=18, 
		SELF=19, TYPE=20, ID=21, INT=22, BOOL=23, STRING=24, SEMI=25, COMMA=26, 
		DOT=27, COLON=28, QUOTATION=29, ASSIGN=30, LPAREN=31, RPAREN=32, LBRACE=33, 
		RBRACE=34, AT_SIGN=35, TILDE=36, PLUS=37, MINUS=38, MULT=39, DIV=40, EQUAL=41, 
		LT=42, LE=43, RESULT=44, LINE_COMMENT=45, BLOCK_COMMENT=46, EOF_IN_COMMENT=47, 
		UNMATCHED_END_COMMENT=48, HASHTAG=49, WS=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "WHILE", "LOOP", "POOL", "LET", "IN", "CASE", 
			"OF", "ESAC", "NEW", "CLASS", "INHERITS", "NOT", "IS_VOID", "SELF", "LOWERCASE", 
			"UPPERCASE", "LETTER", "DIGIT", "NULLCHAR", "SPECIALCHARS", "NEW_LINE", 
			"TYPE", "ID", "INT", "BOOL", "STRING", "SEMI", "COMMA", "DOT", "COLON", 
			"QUOTATION", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "AT_SIGN", 
			"TILDE", "PLUS", "MINUS", "MULT", "DIV", "EQUAL", "LT", "LE", "RESULT", 
			"LINE_COMMENT", "BLOCK_COMMENT", "EOF_IN_COMMENT", "UNMATCHED_END_COMMENT", 
			"HASHTAG", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", "'while'", "'loop'", 
			"'pool'", "'let'", "'in'", "'case'", "'of'", "'esac'", "'new'", "'class'", 
			"'inherits'", "'not'", "'isvoid'", "'self'", null, null, null, null, 
			null, "';'", "','", "'.'", "':'", "'\"'", "'<-'", "'('", "')'", "'{'", 
			"'}'", "'@'", "'~'", "'+'", "'-'", "'*'", "'/'", "'='", "'<'", "'<='", 
			"'=>'", null, null, null, "'*)'", "'#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "WHILE", "LOOP", "POOL", "LET", 
			"IN", "CASE", "OF", "ESAC", "NEW", "CLASS", "INHERITS", "NOT", "IS_VOID", 
			"SELF", "TYPE", "ID", "INT", "BOOL", "STRING", "SEMI", "COMMA", "DOT", 
			"COLON", "QUOTATION", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"AT_SIGN", "TILDE", "PLUS", "MINUS", "MULT", "DIV", "EQUAL", "LT", "LE", 
			"RESULT", "LINE_COMMENT", "BLOCK_COMMENT", "EOF_IN_COMMENT", "UNMATCHED_END_COMMENT", 
			"HASHTAG", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    int stringSize = 0;

	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 29:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			EOF_IN_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			UNMATCHED_END_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 54:
			HASHTAG_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 stringSize = 0; 
			break;
		case 1:
			 raiseError("String contains null character"); 
			break;
		case 2:
			 stringSize++; 
			break;
		case 3:
			 stringSize++; 
			break;
		case 4:
			 if (stringSize > 1024) { raiseError("String constant too long"); } 
			break;
		case 5:
			 raiseError("Unterminated string constant"); 
			break;
		case 6:
			 raiseError("EOF in string constant"); 
			break;
		}
	}
	private void EOF_IN_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNMATCHED_END_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void HASHTAG_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			 raiseError("Invalid character: #"); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00002\u0185\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u00db"+
		"\b\u0017\u0001\u0018\u0003\u0018\u00de\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u00e6\b\u0019"+
		"\n\u0019\f\u0019\u00e9\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0005\u001a\u00ef\b\u001a\n\u001a\f\u001a\u00f2\t\u001a\u0001\u001b"+
		"\u0004\u001b\u00f5\b\u001b\u000b\u001b\f\u001b\u00f6\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u0102\b\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u0111"+
		"\b\u001d\n\u001d\f\u001d\u0114\t\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u011e\b\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 "+
		"\u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001"+
		".\u0001/\u0001/\u00010\u00010\u00010\u00011\u00011\u00011\u00012\u0001"+
		"2\u00012\u00012\u00052\u014f\b2\n2\f2\u0152\t2\u00012\u00012\u00032\u0156"+
		"\b2\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u00053\u015f\b3\n"+
		"3\f3\u0162\t3\u00013\u00013\u00013\u00013\u00013\u00014\u00014\u00014"+
		"\u00014\u00014\u00014\u00054\u016f\b4\n4\f4\u0172\t4\u00014\u00014\u0001"+
		"4\u00015\u00015\u00015\u00015\u00015\u00016\u00016\u00016\u00017\u0004"+
		"7\u0180\b7\u000b7\f7\u0181\u00017\u00017\u0004\u0112\u0150\u0160\u0170"+
		"\u00008\u0001\u0002\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b"+
		"\u0007\r\b\u000f\t\u0011\n\u0013\u000b\u0015\f\u0017\r\u0019\u000e\u001b"+
		"\u000f\u001d\u0010\u001f\u0011!\u0012#\u0013%\u0000\'\u0000)\u0000+\u0000"+
		"-\u0000/\u00001\u00003\u00145\u00157\u00169\u0017;\u0018=\u0019?\u001a"+
		"A\u001bC\u001cE\u001dG\u001eI\u001fK M!O\"Q#S$U%W&Y\'[(])_*a+c,e-g.i/"+
		"k0m1o2\u0001\u0000\u0006\u0001\u0000az\u0001\u0000AZ\u0002\u0000AZaz\u0001"+
		"\u000009\u0001\u0000)*\u0003\u0000\t\n\f\r  \u0196\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000"+
		"\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000"+
		"?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001"+
		"\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000"+
		"\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000"+
		"M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001"+
		"\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000"+
		"\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000"+
		"[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001"+
		"\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000"+
		"\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000"+
		"i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001"+
		"\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0001q\u0001\u0000\u0000"+
		"\u0000\u0003t\u0001\u0000\u0000\u0000\u0005y\u0001\u0000\u0000\u0000\u0007"+
		"~\u0001\u0000\u0000\u0000\t\u0081\u0001\u0000\u0000\u0000\u000b\u0087"+
		"\u0001\u0000\u0000\u0000\r\u008c\u0001\u0000\u0000\u0000\u000f\u0091\u0001"+
		"\u0000\u0000\u0000\u0011\u0095\u0001\u0000\u0000\u0000\u0013\u0098\u0001"+
		"\u0000\u0000\u0000\u0015\u009d\u0001\u0000\u0000\u0000\u0017\u00a0\u0001"+
		"\u0000\u0000\u0000\u0019\u00a5\u0001\u0000\u0000\u0000\u001b\u00a9\u0001"+
		"\u0000\u0000\u0000\u001d\u00af\u0001\u0000\u0000\u0000\u001f\u00b8\u0001"+
		"\u0000\u0000\u0000!\u00bc\u0001\u0000\u0000\u0000#\u00c3\u0001\u0000\u0000"+
		"\u0000%\u00c8\u0001\u0000\u0000\u0000\'\u00ca\u0001\u0000\u0000\u0000"+
		")\u00cc\u0001\u0000\u0000\u0000+\u00ce\u0001\u0000\u0000\u0000-\u00d0"+
		"\u0001\u0000\u0000\u0000/\u00da\u0001\u0000\u0000\u00001\u00dd\u0001\u0000"+
		"\u0000\u00003\u00e1\u0001\u0000\u0000\u00005\u00ea\u0001\u0000\u0000\u0000"+
		"7\u00f4\u0001\u0000\u0000\u00009\u0101\u0001\u0000\u0000\u0000;\u0103"+
		"\u0001\u0000\u0000\u0000=\u011f\u0001\u0000\u0000\u0000?\u0121\u0001\u0000"+
		"\u0000\u0000A\u0123\u0001\u0000\u0000\u0000C\u0125\u0001\u0000\u0000\u0000"+
		"E\u0127\u0001\u0000\u0000\u0000G\u0129\u0001\u0000\u0000\u0000I\u012c"+
		"\u0001\u0000\u0000\u0000K\u012e\u0001\u0000\u0000\u0000M\u0130\u0001\u0000"+
		"\u0000\u0000O\u0132\u0001\u0000\u0000\u0000Q\u0134\u0001\u0000\u0000\u0000"+
		"S\u0136\u0001\u0000\u0000\u0000U\u0138\u0001\u0000\u0000\u0000W\u013a"+
		"\u0001\u0000\u0000\u0000Y\u013c\u0001\u0000\u0000\u0000[\u013e\u0001\u0000"+
		"\u0000\u0000]\u0140\u0001\u0000\u0000\u0000_\u0142\u0001\u0000\u0000\u0000"+
		"a\u0144\u0001\u0000\u0000\u0000c\u0147\u0001\u0000\u0000\u0000e\u014a"+
		"\u0001\u0000\u0000\u0000g\u0159\u0001\u0000\u0000\u0000i\u0168\u0001\u0000"+
		"\u0000\u0000k\u0176\u0001\u0000\u0000\u0000m\u017b\u0001\u0000\u0000\u0000"+
		"o\u017f\u0001\u0000\u0000\u0000qr\u0005i\u0000\u0000rs\u0005f\u0000\u0000"+
		"s\u0002\u0001\u0000\u0000\u0000tu\u0005t\u0000\u0000uv\u0005h\u0000\u0000"+
		"vw\u0005e\u0000\u0000wx\u0005n\u0000\u0000x\u0004\u0001\u0000\u0000\u0000"+
		"yz\u0005e\u0000\u0000z{\u0005l\u0000\u0000{|\u0005s\u0000\u0000|}\u0005"+
		"e\u0000\u0000}\u0006\u0001\u0000\u0000\u0000~\u007f\u0005f\u0000\u0000"+
		"\u007f\u0080\u0005i\u0000\u0000\u0080\b\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0005w\u0000\u0000\u0082\u0083\u0005h\u0000\u0000\u0083\u0084\u0005"+
		"i\u0000\u0000\u0084\u0085\u0005l\u0000\u0000\u0085\u0086\u0005e\u0000"+
		"\u0000\u0086\n\u0001\u0000\u0000\u0000\u0087\u0088\u0005l\u0000\u0000"+
		"\u0088\u0089\u0005o\u0000\u0000\u0089\u008a\u0005o\u0000\u0000\u008a\u008b"+
		"\u0005p\u0000\u0000\u008b\f\u0001\u0000\u0000\u0000\u008c\u008d\u0005"+
		"p\u0000\u0000\u008d\u008e\u0005o\u0000\u0000\u008e\u008f\u0005o\u0000"+
		"\u0000\u008f\u0090\u0005l\u0000\u0000\u0090\u000e\u0001\u0000\u0000\u0000"+
		"\u0091\u0092\u0005l\u0000\u0000\u0092\u0093\u0005e\u0000\u0000\u0093\u0094"+
		"\u0005t\u0000\u0000\u0094\u0010\u0001\u0000\u0000\u0000\u0095\u0096\u0005"+
		"i\u0000\u0000\u0096\u0097\u0005n\u0000\u0000\u0097\u0012\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0005c\u0000\u0000\u0099\u009a\u0005a\u0000\u0000\u009a"+
		"\u009b\u0005s\u0000\u0000\u009b\u009c\u0005e\u0000\u0000\u009c\u0014\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0005o\u0000\u0000\u009e\u009f\u0005f\u0000"+
		"\u0000\u009f\u0016\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005e\u0000\u0000"+
		"\u00a1\u00a2\u0005s\u0000\u0000\u00a2\u00a3\u0005a\u0000\u0000\u00a3\u00a4"+
		"\u0005c\u0000\u0000\u00a4\u0018\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005"+
		"n\u0000\u0000\u00a6\u00a7\u0005e\u0000\u0000\u00a7\u00a8\u0005w\u0000"+
		"\u0000\u00a8\u001a\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005c\u0000\u0000"+
		"\u00aa\u00ab\u0005l\u0000\u0000\u00ab\u00ac\u0005a\u0000\u0000\u00ac\u00ad"+
		"\u0005s\u0000\u0000\u00ad\u00ae\u0005s\u0000\u0000\u00ae\u001c\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0005i\u0000\u0000\u00b0\u00b1\u0005n\u0000\u0000"+
		"\u00b1\u00b2\u0005h\u0000\u0000\u00b2\u00b3\u0005e\u0000\u0000\u00b3\u00b4"+
		"\u0005r\u0000\u0000\u00b4\u00b5\u0005i\u0000\u0000\u00b5\u00b6\u0005t"+
		"\u0000\u0000\u00b6\u00b7\u0005s\u0000\u0000\u00b7\u001e\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0005n\u0000\u0000\u00b9\u00ba\u0005o\u0000\u0000\u00ba"+
		"\u00bb\u0005t\u0000\u0000\u00bb \u0001\u0000\u0000\u0000\u00bc\u00bd\u0005"+
		"i\u0000\u0000\u00bd\u00be\u0005s\u0000\u0000\u00be\u00bf\u0005v\u0000"+
		"\u0000\u00bf\u00c0\u0005o\u0000\u0000\u00c0\u00c1\u0005i\u0000\u0000\u00c1"+
		"\u00c2\u0005d\u0000\u0000\u00c2\"\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0005s\u0000\u0000\u00c4\u00c5\u0005e\u0000\u0000\u00c5\u00c6\u0005l"+
		"\u0000\u0000\u00c6\u00c7\u0005f\u0000\u0000\u00c7$\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c9\u0007\u0000\u0000\u0000\u00c9&\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0007\u0001\u0000\u0000\u00cb(\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0007\u0002\u0000\u0000\u00cd*\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007"+
		"\u0003\u0000\u0000\u00cf,\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u0000"+
		"\u0000\u0000\u00d1.\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\\\u0000"+
		"\u0000\u00d3\u00db\u0005t\u0000\u0000\u00d4\u00d5\u0005\\\u0000\u0000"+
		"\u00d5\u00db\u0005n\u0000\u0000\u00d6\u00d7\u0005\\\u0000\u0000\u00d7"+
		"\u00db\u0005b\u0000\u0000\u00d8\u00d9\u0005\\\u0000\u0000\u00d9\u00db"+
		"\u0005f\u0000\u0000\u00da\u00d2\u0001\u0000\u0000\u0000\u00da\u00d4\u0001"+
		"\u0000\u0000\u0000\u00da\u00d6\u0001\u0000\u0000\u0000\u00da\u00d8\u0001"+
		"\u0000\u0000\u0000\u00db0\u0001\u0000\u0000\u0000\u00dc\u00de\u0005\r"+
		"\u0000\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e0\u0005\n\u0000"+
		"\u0000\u00e02\u0001\u0000\u0000\u0000\u00e1\u00e7\u0003\'\u0013\u0000"+
		"\u00e2\u00e6\u0003)\u0014\u0000\u00e3\u00e6\u0005_\u0000\u0000\u00e4\u00e6"+
		"\u0003+\u0015\u0000\u00e5\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e9\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001"+
		"\u0000\u0000\u0000\u00e84\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000"+
		"\u0000\u0000\u00ea\u00f0\u0003%\u0012\u0000\u00eb\u00ef\u0003)\u0014\u0000"+
		"\u00ec\u00ef\u0005_\u0000\u0000\u00ed\u00ef\u0003+\u0015\u0000\u00ee\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f16\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f5\u0003"+
		"+\u0015\u0000\u00f4\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f78\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005t\u0000\u0000"+
		"\u00f9\u00fa\u0005r\u0000\u0000\u00fa\u00fb\u0005u\u0000\u0000\u00fb\u0102"+
		"\u0005e\u0000\u0000\u00fc\u00fd\u0005f\u0000\u0000\u00fd\u00fe\u0005a"+
		"\u0000\u0000\u00fe\u00ff\u0005l\u0000\u0000\u00ff\u0100\u0005s\u0000\u0000"+
		"\u0100\u0102\u0005e\u0000\u0000\u0101\u00f8\u0001\u0000\u0000\u0000\u0101"+
		"\u00fc\u0001\u0000\u0000\u0000\u0102:\u0001\u0000\u0000\u0000\u0103\u0104"+
		"\u0003E\"\u0000\u0104\u0112\u0006\u001d\u0000\u0000\u0105\u0106\u0003"+
		"-\u0016\u0000\u0106\u0107\u0006\u001d\u0001\u0000\u0107\u0111\u0001\u0000"+
		"\u0000\u0000\u0108\u0109\u0005\\\u0000\u0000\u0109\u010a\u0005\r\u0000"+
		"\u0000\u010a\u0111\u0005\n\u0000\u0000\u010b\u010c\u0003/\u0017\u0000"+
		"\u010c\u010d\u0006\u001d\u0002\u0000\u010d\u0111\u0001\u0000\u0000\u0000"+
		"\u010e\u010f\t\u0000\u0000\u0000\u010f\u0111\u0006\u001d\u0003\u0000\u0110"+
		"\u0105\u0001\u0000\u0000\u0000\u0110\u0108\u0001\u0000\u0000\u0000\u0110"+
		"\u010b\u0001\u0000\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0111"+
		"\u0114\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000\u0112"+
		"\u0110\u0001\u0000\u0000\u0000\u0113\u011d\u0001\u0000\u0000\u0000\u0114"+
		"\u0112\u0001\u0000\u0000\u0000\u0115\u0116\u0003E\"\u0000\u0116\u0117"+
		"\u0006\u001d\u0004\u0000\u0117\u011e\u0001\u0000\u0000\u0000\u0118\u0119"+
		"\u00031\u0018\u0000\u0119\u011a\u0006\u001d\u0005\u0000\u011a\u011e\u0001"+
		"\u0000\u0000\u0000\u011b\u011c\u0005\u0000\u0000\u0001\u011c\u011e\u0006"+
		"\u001d\u0006\u0000\u011d\u0115\u0001\u0000\u0000\u0000\u011d\u0118\u0001"+
		"\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011e<\u0001\u0000"+
		"\u0000\u0000\u011f\u0120\u0005;\u0000\u0000\u0120>\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0005,\u0000\u0000\u0122@\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0005.\u0000\u0000\u0124B\u0001\u0000\u0000\u0000\u0125\u0126\u0005:"+
		"\u0000\u0000\u0126D\u0001\u0000\u0000\u0000\u0127\u0128\u0005\"\u0000"+
		"\u0000\u0128F\u0001\u0000\u0000\u0000\u0129\u012a\u0005<\u0000\u0000\u012a"+
		"\u012b\u0005-\u0000\u0000\u012bH\u0001\u0000\u0000\u0000\u012c\u012d\u0005"+
		"(\u0000\u0000\u012dJ\u0001\u0000\u0000\u0000\u012e\u012f\u0005)\u0000"+
		"\u0000\u012fL\u0001\u0000\u0000\u0000\u0130\u0131\u0005{\u0000\u0000\u0131"+
		"N\u0001\u0000\u0000\u0000\u0132\u0133\u0005}\u0000\u0000\u0133P\u0001"+
		"\u0000\u0000\u0000\u0134\u0135\u0005@\u0000\u0000\u0135R\u0001\u0000\u0000"+
		"\u0000\u0136\u0137\u0005~\u0000\u0000\u0137T\u0001\u0000\u0000\u0000\u0138"+
		"\u0139\u0005+\u0000\u0000\u0139V\u0001\u0000\u0000\u0000\u013a\u013b\u0005"+
		"-\u0000\u0000\u013bX\u0001\u0000\u0000\u0000\u013c\u013d\u0005*\u0000"+
		"\u0000\u013dZ\u0001\u0000\u0000\u0000\u013e\u013f\u0005/\u0000\u0000\u013f"+
		"\\\u0001\u0000\u0000\u0000\u0140\u0141\u0005=\u0000\u0000\u0141^\u0001"+
		"\u0000\u0000\u0000\u0142\u0143\u0005<\u0000\u0000\u0143`\u0001\u0000\u0000"+
		"\u0000\u0144\u0145\u0005<\u0000\u0000\u0145\u0146\u0005=\u0000\u0000\u0146"+
		"b\u0001\u0000\u0000\u0000\u0147\u0148\u0005=\u0000\u0000\u0148\u0149\u0005"+
		">\u0000\u0000\u0149d\u0001\u0000\u0000\u0000\u014a\u014b\u0005-\u0000"+
		"\u0000\u014b\u014c\u0005-\u0000\u0000\u014c\u0150\u0001\u0000\u0000\u0000"+
		"\u014d\u014f\t\u0000\u0000\u0000\u014e\u014d\u0001\u0000\u0000\u0000\u014f"+
		"\u0152\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000\u0150"+
		"\u014e\u0001\u0000\u0000\u0000\u0151\u0155\u0001\u0000\u0000\u0000\u0152"+
		"\u0150\u0001\u0000\u0000\u0000\u0153\u0156\u00031\u0018\u0000\u0154\u0156"+
		"\u0005\u0000\u0000\u0001\u0155\u0153\u0001\u0000\u0000\u0000\u0155\u0154"+
		"\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0158"+
		"\u00062\u0007\u0000\u0158f\u0001\u0000\u0000\u0000\u0159\u015a\u0005("+
		"\u0000\u0000\u015a\u015b\u0005*\u0000\u0000\u015b\u0160\u0001\u0000\u0000"+
		"\u0000\u015c\u015f\u0003g3\u0000\u015d\u015f\t\u0000\u0000\u0000\u015e"+
		"\u015c\u0001\u0000\u0000\u0000\u015e\u015d\u0001\u0000\u0000\u0000\u015f"+
		"\u0162\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0160"+
		"\u015e\u0001\u0000\u0000\u0000\u0161\u0163\u0001\u0000\u0000\u0000\u0162"+
		"\u0160\u0001\u0000\u0000\u0000\u0163\u0164\u0005*\u0000\u0000\u0164\u0165"+
		"\u0005)\u0000\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167\u0006"+
		"3\u0007\u0000\u0167h\u0001\u0000\u0000\u0000\u0168\u0169\u0005(\u0000"+
		"\u0000\u0169\u016a\u0005*\u0000\u0000\u016a\u0170\u0001\u0000\u0000\u0000"+
		"\u016b\u016f\u0003g3\u0000\u016c\u016d\b\u0004\u0000\u0000\u016d\u016f"+
		"\t\u0000\u0000\u0000\u016e\u016b\u0001\u0000\u0000\u0000\u016e\u016c\u0001"+
		"\u0000\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u0171\u0001"+
		"\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0173\u0001"+
		"\u0000\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0173\u0174\u0005"+
		"\u0000\u0000\u0001\u0174\u0175\u00064\b\u0000\u0175j\u0001\u0000\u0000"+
		"\u0000\u0176\u0177\u0005*\u0000\u0000\u0177\u0178\u0005)\u0000\u0000\u0178"+
		"\u0179\u0001\u0000\u0000\u0000\u0179\u017a\u00065\t\u0000\u017al\u0001"+
		"\u0000\u0000\u0000\u017b\u017c\u0005#\u0000\u0000\u017c\u017d\u00066\n"+
		"\u0000\u017dn\u0001\u0000\u0000\u0000\u017e\u0180\u0007\u0005\u0000\u0000"+
		"\u017f\u017e\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000"+
		"\u0181\u017f\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000\u0000"+
		"\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u0184\u00067\u0007\u0000\u0184"+
		"p\u0001\u0000\u0000\u0000\u0013\u0000\u00da\u00dd\u00e5\u00e7\u00ee\u00f0"+
		"\u00f6\u0101\u0110\u0112\u011d\u0150\u0155\u015e\u0160\u016e\u0170\u0181"+
		"\u000b\u0001\u001d\u0000\u0001\u001d\u0001\u0001\u001d\u0002\u0001\u001d"+
		"\u0003\u0001\u001d\u0004\u0001\u001d\u0005\u0001\u001d\u0006\u0006\u0000"+
		"\u0000\u00014\u0007\u00015\b\u00016\t";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
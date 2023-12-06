//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.parser;

import java.util.List;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SQLiteParser extends Parser {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache;
    public static final int SCOL = 1;
    public static final int DOT = 2;
    public static final int OPEN_PAR = 3;
    public static final int CLOSE_PAR = 4;
    public static final int COMMA = 5;
    public static final int ASSIGN = 6;
    public static final int STAR = 7;
    public static final int PLUS = 8;
    public static final int MINUS = 9;
    public static final int TILDE = 10;
    public static final int PIPE2 = 11;
    public static final int DIV = 12;
    public static final int MOD = 13;
    public static final int LT2 = 14;
    public static final int GT2 = 15;
    public static final int AMP = 16;
    public static final int PIPE = 17;
    public static final int LT = 18;
    public static final int LT_EQ = 19;
    public static final int GT = 20;
    public static final int GT_EQ = 21;
    public static final int EQ = 22;
    public static final int NOT_EQ1 = 23;
    public static final int NOT_EQ2 = 24;
    public static final int K_ABORT = 25;
    public static final int K_ACTION = 26;
    public static final int K_ADD = 27;
    public static final int K_AFTER = 28;
    public static final int K_ALL = 29;
    public static final int K_ALTER = 30;
    public static final int K_ANALYZE = 31;
    public static final int K_AND = 32;
    public static final int K_AS = 33;
    public static final int K_ASC = 34;
    public static final int K_ATTACH = 35;
    public static final int K_AUTOINCREMENT = 36;
    public static final int K_BEFORE = 37;
    public static final int K_BEGIN = 38;
    public static final int K_BETWEEN = 39;
    public static final int K_BY = 40;
    public static final int K_CASCADE = 41;
    public static final int K_CASE = 42;
    public static final int K_CAST = 43;
    public static final int K_CHECK = 44;
    public static final int K_COLLATE = 45;
    public static final int K_COLUMN = 46;
    public static final int K_COMMIT = 47;
    public static final int K_CONFLICT = 48;
    public static final int K_CONSTRAINT = 49;
    public static final int K_CREATE = 50;
    public static final int K_CROSS = 51;
    public static final int K_CURRENT_DATE = 52;
    public static final int K_CURRENT_TIME = 53;
    public static final int K_CURRENT_TIMESTAMP = 54;
    public static final int K_DATABASE = 55;
    public static final int K_DEFAULT = 56;
    public static final int K_DEFERRABLE = 57;
    public static final int K_DEFERRED = 58;
    public static final int K_DELETE = 59;
    public static final int K_DESC = 60;
    public static final int K_DETACH = 61;
    public static final int K_DISTINCT = 62;
    public static final int K_DROP = 63;
    public static final int K_EACH = 64;
    public static final int K_ELSE = 65;
    public static final int K_END = 66;
    public static final int K_ESCAPE = 67;
    public static final int K_EXCEPT = 68;
    public static final int K_EXCLUSIVE = 69;
    public static final int K_EXISTS = 70;
    public static final int K_EXPLAIN = 71;
    public static final int K_FAIL = 72;
    public static final int K_FOR = 73;
    public static final int K_FOREIGN = 74;
    public static final int K_FROM = 75;
    public static final int K_FULL = 76;
    public static final int K_GLOB = 77;
    public static final int K_GROUP = 78;
    public static final int K_HAVING = 79;
    public static final int K_IF = 80;
    public static final int K_IGNORE = 81;
    public static final int K_IMMEDIATE = 82;
    public static final int K_IN = 83;
    public static final int K_INDEX = 84;
    public static final int K_INDEXED = 85;
    public static final int K_INITIALLY = 86;
    public static final int K_INNER = 87;
    public static final int K_INSERT = 88;
    public static final int K_INSTEAD = 89;
    public static final int K_INTERSECT = 90;
    public static final int K_INTO = 91;
    public static final int K_IS = 92;
    public static final int K_ISNULL = 93;
    public static final int K_JOIN = 94;
    public static final int K_KEY = 95;
    public static final int K_LEFT = 96;
    public static final int K_LIKE = 97;
    public static final int K_LIMIT = 98;
    public static final int K_MATCH = 99;
    public static final int K_NATURAL = 100;
    public static final int K_NO = 101;
    public static final int K_NOT = 102;
    public static final int K_NOTNULL = 103;
    public static final int K_NULL = 104;
    public static final int K_OF = 105;
    public static final int K_OFFSET = 106;
    public static final int K_ON = 107;
    public static final int K_OR = 108;
    public static final int K_ORDER = 109;
    public static final int K_OUTER = 110;
    public static final int K_PLAN = 111;
    public static final int K_PRAGMA = 112;
    public static final int K_PRIMARY = 113;
    public static final int K_QUERY = 114;
    public static final int K_RAISE = 115;
    public static final int K_RECURSIVE = 116;
    public static final int K_REFERENCES = 117;
    public static final int K_REGEXP = 118;
    public static final int K_REINDEX = 119;
    public static final int K_RELEASE = 120;
    public static final int K_RENAME = 121;
    public static final int K_REPLACE = 122;
    public static final int K_RESTRICT = 123;
    public static final int K_RIGHT = 124;
    public static final int K_ROLLBACK = 125;
    public static final int K_ROW = 126;
    public static final int K_SAVEPOINT = 127;
    public static final int K_SELECT = 128;
    public static final int K_SET = 129;
    public static final int K_TABLE = 130;
    public static final int K_TEMP = 131;
    public static final int K_TEMPORARY = 132;
    public static final int K_THEN = 133;
    public static final int K_TO = 134;
    public static final int K_TRANSACTION = 135;
    public static final int K_TRIGGER = 136;
    public static final int K_UNION = 137;
    public static final int K_UNIQUE = 138;
    public static final int K_UPDATE = 139;
    public static final int K_USING = 140;
    public static final int K_VACUUM = 141;
    public static final int K_VALUES = 142;
    public static final int K_VIEW = 143;
    public static final int K_VIRTUAL = 144;
    public static final int K_WHEN = 145;
    public static final int K_WHERE = 146;
    public static final int K_WITH = 147;
    public static final int K_WITHOUT = 148;
    public static final int IDENTIFIER = 149;
    public static final int NUMERIC_LITERAL = 150;
    public static final int BIND_PARAMETER = 151;
    public static final int STRING_LITERAL = 152;
    public static final int BLOB_LITERAL = 153;
    public static final int SINGLE_LINE_COMMENT = 154;
    public static final int MULTILINE_COMMENT = 155;
    public static final int SPACES = 156;
    public static final int UNEXPECTED_CHAR = 157;
    public static final int RULE_parse = 0;
    public static final int RULE_error = 1;
    public static final int RULE_sql_stmt_list = 2;
    public static final int RULE_sql_stmt = 3;
    public static final int RULE_alter_table_stmt = 4;
    public static final int RULE_analyze_stmt = 5;
    public static final int RULE_attach_stmt = 6;
    public static final int RULE_begin_stmt = 7;
    public static final int RULE_commit_stmt = 8;
    public static final int RULE_compound_select_stmt = 9;
    public static final int RULE_create_index_stmt = 10;
    public static final int RULE_create_table_stmt = 11;
    public static final int RULE_create_trigger_stmt = 12;
    public static final int RULE_create_view_stmt = 13;
    public static final int RULE_create_virtual_table_stmt = 14;
    public static final int RULE_delete_stmt = 15;
    public static final int RULE_delete_stmt_limited = 16;
    public static final int RULE_detach_stmt = 17;
    public static final int RULE_drop_index_stmt = 18;
    public static final int RULE_drop_table_stmt = 19;
    public static final int RULE_drop_trigger_stmt = 20;
    public static final int RULE_drop_view_stmt = 21;
    public static final int RULE_factored_select_stmt = 22;
    public static final int RULE_insert_stmt = 23;
    public static final int RULE_pragma_stmt = 24;
    public static final int RULE_reindex_stmt = 25;
    public static final int RULE_release_stmt = 26;
    public static final int RULE_rollback_stmt = 27;
    public static final int RULE_savepoint_stmt = 28;
    public static final int RULE_simple_select_stmt = 29;
    public static final int RULE_select_stmt = 30;
    public static final int RULE_select_or_values = 31;
    public static final int RULE_update_stmt = 32;
    public static final int RULE_update_stmt_limited = 33;
    public static final int RULE_vacuum_stmt = 34;
    public static final int RULE_column_def = 35;
    public static final int RULE_type_name = 36;
    public static final int RULE_column_constraint = 37;
    public static final int RULE_conflict_clause = 38;
    public static final int RULE_expr = 39;
    public static final int RULE_foreign_key_clause = 40;
    public static final int RULE_raise_function = 41;
    public static final int RULE_indexed_column = 42;
    public static final int RULE_table_constraint = 43;
    public static final int RULE_with_clause = 44;
    public static final int RULE_qualified_table_name = 45;
    public static final int RULE_ordering_term = 46;
    public static final int RULE_pragma_value = 47;
    public static final int RULE_common_table_expression = 48;
    public static final int RULE_result_column = 49;
    public static final int RULE_table_or_subquery = 50;
    public static final int RULE_join_clause = 51;
    public static final int RULE_join_operator = 52;
    public static final int RULE_join_constraint = 53;
    public static final int RULE_select_core = 54;
    public static final int RULE_compound_operator = 55;
    public static final int RULE_signed_number = 56;
    public static final int RULE_literal_value = 57;
    public static final int RULE_unary_operator = 58;
    public static final int RULE_error_message = 59;
    public static final int RULE_module_argument = 60;
    public static final int RULE_column_alias = 61;
    public static final int RULE_keyword = 62;
    public static final int RULE_name = 63;
    public static final int RULE_function_name = 64;
    public static final int RULE_database_name = 65;
    public static final int RULE_schema_name = 66;
    public static final int RULE_table_function_name = 67;
    public static final int RULE_table_name = 68;
    public static final int RULE_table_or_index_name = 69;
    public static final int RULE_new_table_name = 70;
    public static final int RULE_column_name = 71;
    public static final int RULE_collation_name = 72;
    public static final int RULE_foreign_table = 73;
    public static final int RULE_index_name = 74;
    public static final int RULE_trigger_name = 75;
    public static final int RULE_view_name = 76;
    public static final int RULE_module_name = 77;
    public static final int RULE_pragma_name = 78;
    public static final int RULE_savepoint_name = 79;
    public static final int RULE_table_alias = 80;
    public static final int RULE_transaction_name = 81;
    public static final int RULE_any_name = 82;
    public static final String[] ruleNames;
    private static final String[] _LITERAL_NAMES;
    private static final String[] _SYMBOLIC_NAMES;
    public static final Vocabulary VOCABULARY;
    /** @deprecated */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN = "\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003\u009fڕ\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0003\u0002\u0003\u0002\u0007\u0002«\n\u0002\f\u0002\u000e\u0002®\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¶\n\u0004\f\u0004\u000e\u0004¹\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004½\n\u0004\r\u0004\u000e\u0004¾\u0003\u0004\u0007\u0004Â\n\u0004\f\u0004\u000e\u0004Å\u000b\u0004\u0003\u0004\u0007\u0004È\n\u0004\f\u0004\u000e\u0004Ë\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ð\n\u0005\u0005\u0005Ò\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ò\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ù\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ā\n\u0006\u0003\u0006\u0005\u0006Ą\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007č\n\u0007\u0003\b\u0003\b\u0005\bđ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tę\n\t\u0003\t\u0003\t\u0005\tĝ\n\t\u0005\tğ\n\t\u0003\n\u0003\n\u0003\n\u0005\nĤ\n\n\u0005\nĦ\n\n\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĮ\n\u000b\u0003\u000b\u0003\u000b\u0005\u000bĲ\n\u000b\u0003\u000b\u0006\u000bĵ\n\u000b\r\u000b\u000e\u000bĶ\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bľ\n\u000b\f\u000b\u000e\u000bŁ\u000b\u000b\u0005\u000bŃ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŉ\n\u000b\u0005\u000bŋ\n\u000b\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŕ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŚ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fţ\n\f\f\f\u000e\fŦ\u000b\f\u0003\f\u0003\f\u0003\f\u0005\fū\n\f\u0003\r\u0003\r\u0005\rů\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŵ\n\r\u0003\r\u0003\r\u0003\r\u0005\rź\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƁ\n\r\f\r\u000e\rƄ\u000b\r\u0003\r\u0003\r\u0007\rƈ\n\r\f\r\u000e\rƋ\u000b\r\u0003\r\u0003\r\u0003\r\u0005\rƐ\n\r\u0003\r\u0003\r\u0005\rƔ\n\r\u0003\u000e\u0003\u000e\u0005\u000eƘ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƞ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƣ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƪ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eƳ\n\u000e\f\u000e\u000e\u000eƶ\u000b\u000e\u0005\u000eƸ\n\u000e\u0005\u000eƺ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǀ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǆ\n\u000e\u0003\u000e\u0003\u000e\u0005\u000eǊ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǑ\n\u000e\u0003\u000e\u0003\u000e\u0006\u000eǕ\n\u000e\r\u000e\u000e\u000eǖ\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0005\u000fǝ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǣ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǨ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ǵ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010ǹ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010Ȃ\n\u0010\f\u0010\u000e\u0010ȅ\u000b\u0010\u0003\u0010\u0003\u0010\u0005\u0010ȉ\n\u0010\u0003\u0011\u0005\u0011Ȍ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011ȓ\n\u0011\u0003\u0012\u0005\u0012Ȗ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȝ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0007\u0012Ȥ\n\u0012\f\u0012\u000e\u0012ȧ\u000b\u0012\u0005\u0012ȩ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȯ\n\u0012\u0005\u0012ȱ\n\u0012\u0003\u0013\u0003\u0013\u0005\u0013ȵ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ƚ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ɂ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ɋ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ɏ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɗ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɜ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɤ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɩ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0018\u0005\u0018ɮ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɴ\n\u0018\f\u0018\u000e\u0018ɷ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɾ\n\u0018\f\u0018\u000e\u0018ʁ\u000b\u0018\u0005\u0018ʃ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʉ\n\u0018\u0005\u0018ʋ\n\u0018\u0003\u0019\u0005\u0019ʎ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʡ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʧ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʮ\n\u0019\f\u0019\u000e\u0019ʱ\u000b\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʵ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʼ\n\u0019\f\u0019\u000e\u0019ʿ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˇ\n\u0019\f\u0019\u000e\u0019ˊ\u000b\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˎ\n\u0019\f\u0019\u000e\u0019ˑ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019˖\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˜\n\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˥\n\u001a\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0005\u001bˬ\n\u001b\u0003\u001b\u0003\u001b\u0005\u001b˰\n\u001b\u0005\u001b˲\n\u001b\u0003\u001c\u0003\u001c\u0005\u001c˶\n\u001c\u0003\u001c\u0003\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0005\u001d˽\n\u001d\u0005\u001d˿\n\u001d\u0003\u001d\u0003\u001d\u0005\u001d̃\n\u001d\u0003\u001d\u0005\u001d̆\n\u001d\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001f\u0005\u001f̌\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̔\n\u001f\f\u001f\u000e\u001f̗\u000b\u001f\u0005\u001f̙\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0005\u001f̟\n\u001f\u0005\u001f̡\n\u001f\u0003 \u0005 ̤\n \u0003 \u0003 \u0003 \u0003 \u0007 ̪\n \f \u000e ̭\u000b \u0003 \u0003 \u0003 \u0003 \u0003 \u0007 ̴\n \f \u000e ̷\u000b \u0005 ̹\n \u0003 \u0003 \u0003 \u0003 \u0005 ̿\n \u0005 ́\n \u0003!\u0003!\u0005!ͅ\n!\u0003!\u0003!\u0003!\u0007!͊\n!\f!\u000e!͍\u000b!\u0003!\u0003!\u0003!\u0003!\u0007!͓\n!\f!\u000e!͖\u000b!\u0003!\u0005!͙\n!\u0005!͛\n!\u0003!\u0003!\u0005!͟\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!ͦ\n!\f!\u000e!ͩ\u000b!\u0003!\u0003!\u0005!ͭ\n!\u0005!ͯ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!Ͷ\n!\f!\u000e!\u0379\u000b!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!\u0381\n!\f!\u000e!΄\u000b!\u0003!\u0003!\u0007!Έ\n!\f!\u000e!\u038b\u000b!\u0005!\u038d\n!\u0003\"\u0005\"ΐ\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0005\"Ν\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0007\"Ω\n\"\f\"\u000e\"ά\u000b\"\u0003\"\u0003\"\u0005\"ΰ\n\"\u0003#\u0005#γ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0005#π\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#ό\n#\f#\u000e#Ϗ\u000b#\u0003#\u0003#\u0005#ϓ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#Ϛ\n#\f#\u000e#ϝ\u000b#\u0005#ϟ\n#\u0003#\u0003#\u0003#\u0003#\u0005#ϥ\n#\u0005#ϧ\n#\u0003$\u0003$\u0003%\u0003%\u0005%ϭ\n%\u0003%\u0007%ϰ\n%\f%\u000e%ϳ\u000b%\u0003&\u0006&϶\n&\r&\u000e&Ϸ\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0005&Є\n&\u0003'\u0003'\u0005'Ј\n'\u0003'\u0003'\u0003'\u0005'Ѝ\n'\u0003'\u0003'\u0005'Б\n'\u0003'\u0005'Д\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ц\n'\u0003'\u0003'\u0003'\u0005'Ы\n'\u0003(\u0003(\u0003(\u0005(а\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)и\n)\u0003)\u0003)\u0003)\u0005)н\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ц\n)\u0003)\u0003)\u0003)\u0007)ы\n)\f)\u000e)ю\u000b)\u0003)\u0005)ё\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ѡ\n)\u0003)\u0005)Ѥ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ѭ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0006)ѳ\n)\r)\u000e)Ѵ\u0003)\u0003)\u0005)ѹ\n)\u0003)\u0003)\u0003)\u0005)Ѿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)қ\n)\u0003)\u0003)\u0003)\u0005)Ҡ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ҩ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0007)ұ\n)\f)\u000e)Ҵ\u000b)\u0005)Ҷ\n)\u0003)\u0003)\u0003)\u0003)\u0005)Ҽ\n)\u0003)\u0005)ҿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӆ\n)\u0003)\u0003)\u0003)\u0003)\u0005)ӌ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӓ\n)\u0007)ӕ\n)\f)\u000e)Ә\u000b)\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0007*Ӡ\n*\f*\u000e*ӣ\u000b*\u0003*\u0003*\u0005*ӧ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*ӳ\n*\u0003*\u0003*\u0005*ӷ\n*\u0007*ӹ\n*\f*\u000e*Ӽ\u000b*\u0003*\u0005*ӿ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*Ԇ\n*\u0005*Ԉ\n*\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0005+Ԑ\n+\u0003+\u0003+\u0003,\u0003,\u0003,\u0005,ԗ\n,\u0003,\u0005,Ԛ\n,\u0003-\u0003-\u0005-Ԟ\n-\u0003-\u0003-\u0003-\u0005-ԣ\n-\u0003-\u0003-\u0003-\u0003-\u0007-ԩ\n-\f-\u000e-Ԭ\u000b-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Լ\n-\f-\u000e-Կ\u000b-\u0003-\u0003-\u0003-\u0005-Մ\n-\u0003.\u0003.\u0005.Ո\n.\u0003.\u0003.\u0003.\u0007.Ս\n.\f.\u000e.Ր\u000b.\u0003/\u0003/\u0003/\u0005/Օ\n/\u0003/\u0003/\u0003/\u0003/\u0003/\u0003/\u0005/՝\n/\u00030\u00030\u00030\u00050բ\n0\u00030\u00050ե\n0\u00031\u00031\u00031\u00051ժ\n1\u00032\u00032\u00032\u00032\u00032\u00072ձ\n2\f2\u000e2մ\u000b2\u00032\u00032\u00052ո\n2\u00032\u00032\u00032\u00032\u00032\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ֆ\n3\u00033\u00053։\n3\u00053\u058b\n3\u00034\u00034\u00034\u00054\u0590\n4\u00034\u00034\u00054֔\n4\u00034\u00054֗\n4\u00034\u00034\u00034\u00034\u00034\u00054֞\n4\u00034\u00034\u00034\u00054֣\n4\u00034\u00034\u00034\u00034\u00034\u00074֪\n4\f4\u000e4֭\u000b4\u00054֯\n4\u00034\u00034\u00054ֳ\n4\u00034\u00054ֶ\n4\u00034\u00034\u00034\u00034\u00074ּ\n4\f4\u000e4ֿ\u000b4\u00034\u00054ׂ\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054\u05ca\n4\u00034\u00054\u05cd\n4\u00054\u05cf\n4\u00035\u00035\u00035\u00035\u00035\u00075ז\n5\f5\u000e5י\u000b5\u00036\u00036\u00056ם\n6\u00036\u00036\u00056ס\n6\u00036\u00036\u00056ץ\n6\u00036\u00056ר\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ױ\n7\f7\u000e7״\u000b7\u00037\u00037\u00057\u05f8\n7\u00038\u00038\u00058\u05fc\n8\u00038\u00038\u00038\u00078\u0601\n8\f8\u000e8\u0604\u000b8\u00038\u00038\u00038\u00038\u00078؊\n8\f8\u000e8؍\u000b8\u00038\u00058ؐ\n8\u00058ؒ\n8\u00038\u00038\u00058ؖ\n8\u00038\u00038\u00038\u00038\u00038\u00078\u061d\n8\f8\u000e8ؠ\u000b8\u00038\u00038\u00058ؤ\n8\u00058ئ\n8\u00038\u00038\u00038\u00038\u00038\u00078ح\n8\f8\u000e8ذ\u000b8\u00038\u00038\u00038\u00038\u00038\u00038\u00078ظ\n8\f8\u000e8ػ\u000b8\u00038\u00038\u00078ؿ\n8\f8\u000e8ق\u000b8\u00058ل\n8\u00039\u00039\u00039\u00039\u00039\u00059ً\n9\u0003:\u0005:َ\n:\u0003:\u0003:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003>\u0003>\u0005>ٚ\n>\u0003?\u0003?\u0003@\u0003@\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003R\u0003R\u0003R\u0003R\u0005Rڈ\nR\u0003S\u0003S\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0005Tړ\nT\u0003T\u0004ƂϷ\u0003PU\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦\u0002\u0015\u0005\u0002<<GGTT\u0004\u000211DD\u0004\u0002\u0007\u0007ll\u0003\u0002\u0085\u0086\u0004\u0002\u001f\u001f@@\u0004\u0002$$>>\u0007\u0002\u001b\u001bJJSS||\u007f\u007f\u0004\u0002\t\t\u000e\u000f\u0003\u0002\n\u000b\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0006\u0002OOcceexx\u0004\u0002==\u008d\u008d\u0005\u0002\u001b\u001bJJ\u007f\u007f\u0006\u000268jj\u0098\u0098\u009a\u009b\u0004\u0002\n\fhh\u0004\u0002\u0097\u0097\u009a\u009a\u0003\u0002\u001b\u0096\u0002ޓ\u0002¬\u0003\u0002\u0002\u0002\u0004±\u0003\u0002\u0002\u0002\u0006·\u0003\u0002\u0002\u0002\bÑ\u0003\u0002\u0002\u0002\nó\u0003\u0002\u0002\u0002\fą\u0003\u0002\u0002\u0002\u000eĎ\u0003\u0002\u0002\u0002\u0010Ė\u0003\u0002\u0002\u0002\u0012Ġ\u0003\u0002\u0002\u0002\u0014Ĩ\u0003\u0002\u0002\u0002\u0016Ō\u0003\u0002\u0002\u0002\u0018Ŭ\u0003\u0002\u0002\u0002\u001aƕ\u0003\u0002\u0002\u0002\u001cǚ\u0003\u0002\u0002\u0002\u001eǭ\u0003\u0002\u0002\u0002 ȋ\u0003\u0002\u0002\u0002\"ȕ\u0003\u0002\u0002\u0002$Ȳ\u0003\u0002\u0002\u0002&ȸ\u0003\u0002\u0002\u0002(Ʌ\u0003\u0002\u0002\u0002*ɒ\u0003\u0002\u0002\u0002,ɟ\u0003\u0002\u0002\u0002.ɭ\u0003\u0002\u0002\u00020ʍ\u0003\u0002\u0002\u00022˗\u0003\u0002\u0002\u00024˦\u0003\u0002\u0002\u00026˳\u0003\u0002\u0002\u00028˹\u0003\u0002\u0002\u0002:̇\u0003\u0002\u0002\u0002<̋\u0003\u0002\u0002\u0002>̣\u0003\u0002\u0002\u0002@Ό\u0003\u0002\u0002\u0002BΏ\u0003\u0002\u0002\u0002Dβ\u0003\u0002\u0002\u0002FϨ\u0003\u0002\u0002\u0002HϪ\u0003\u0002\u0002\u0002Jϵ\u0003\u0002\u0002\u0002LЇ\u0003\u0002\u0002\u0002NЯ\u0003\u0002\u0002\u0002Pѽ\u0003\u0002\u0002\u0002Rә\u0003\u0002\u0002\u0002Tԉ\u0003\u0002\u0002\u0002Vԓ\u0003\u0002\u0002\u0002Xԝ\u0003\u0002\u0002\u0002ZՅ\u0003\u0002\u0002\u0002\\Ք\u0003\u0002\u0002\u0002^՞\u0003\u0002\u0002\u0002`թ\u0003\u0002\u0002\u0002bի\u0003\u0002\u0002\u0002d֊\u0003\u0002\u0002\u0002f\u05ce\u0003\u0002\u0002\u0002hא\u0003\u0002\u0002\u0002jק\u0003\u0002\u0002\u0002l\u05f7\u0003\u0002\u0002\u0002nك\u0003\u0002\u0002\u0002pي\u0003\u0002\u0002\u0002rٍ\u0003\u0002\u0002\u0002tّ\u0003\u0002\u0002\u0002vٓ\u0003\u0002\u0002\u0002xٕ\u0003\u0002\u0002\u0002zٙ\u0003\u0002\u0002\u0002|ٛ\u0003\u0002\u0002\u0002~ٝ\u0003\u0002\u0002\u0002\u0080ٟ\u0003\u0002\u0002\u0002\u0082١\u0003\u0002\u0002\u0002\u0084٣\u0003\u0002\u0002\u0002\u0086٥\u0003\u0002\u0002\u0002\u0088٧\u0003\u0002\u0002\u0002\u008a٩\u0003\u0002\u0002\u0002\u008c٫\u0003\u0002\u0002\u0002\u008e٭\u0003\u0002\u0002\u0002\u0090ٯ\u0003\u0002\u0002\u0002\u0092ٱ\u0003\u0002\u0002\u0002\u0094ٳ\u0003\u0002\u0002\u0002\u0096ٵ\u0003\u0002\u0002\u0002\u0098ٷ\u0003\u0002\u0002\u0002\u009aٹ\u0003\u0002\u0002\u0002\u009cٻ\u0003\u0002\u0002\u0002\u009eٽ\u0003\u0002\u0002\u0002 ٿ\u0003\u0002\u0002\u0002¢ڇ\u0003\u0002\u0002\u0002¤ډ\u0003\u0002\u0002\u0002¦ڒ\u0003\u0002\u0002\u0002¨«\u0005\u0006\u0004\u0002©«\u0005\u0004\u0003\u0002ª¨\u0003\u0002\u0002\u0002ª©\u0003\u0002\u0002\u0002«®\u0003\u0002\u0002\u0002¬ª\u0003\u0002\u0002\u0002¬\u00ad\u0003\u0002\u0002\u0002\u00ad¯\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002¯°\u0007\u0002\u0002\u0003°\u0003\u0003\u0002\u0002\u0002±²\u0007\u009f\u0002\u0002²³\b\u0003\u0001\u0002³\u0005\u0003\u0002\u0002\u0002´¶\u0007\u0003\u0002\u0002µ´\u0003\u0002\u0002\u0002¶¹\u0003\u0002\u0002\u0002·µ\u0003\u0002\u0002\u0002·¸\u0003\u0002\u0002\u0002¸º\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002ºÃ\u0005\b\u0005\u0002»½\u0007\u0003\u0002\u0002¼»\u0003\u0002\u0002\u0002½¾\u0003\u0002\u0002\u0002¾¼\u0003\u0002\u0002\u0002¾¿\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002ÀÂ\u0005\b\u0005\u0002Á¼\u0003\u0002\u0002\u0002ÂÅ\u0003\u0002\u0002\u0002ÃÁ\u0003\u0002\u0002\u0002ÃÄ\u0003\u0002\u0002\u0002ÄÉ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÆÈ\u0007\u0003\u0002\u0002ÇÆ\u0003\u0002\u0002\u0002ÈË\u0003\u0002\u0002\u0002ÉÇ\u0003\u0002\u0002\u0002ÉÊ\u0003\u0002\u0002\u0002Ê\u0007\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ÌÏ\u0007I\u0002\u0002ÍÎ\u0007t\u0002\u0002ÎÐ\u0007q\u0002\u0002ÏÍ\u0003\u0002\u0002\u0002ÏÐ\u0003\u0002\u0002\u0002ÐÒ\u0003\u0002\u0002\u0002ÑÌ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002Òñ\u0003\u0002\u0002\u0002Óò\u0005\n\u0006\u0002Ôò\u0005\f\u0007\u0002Õò\u0005\u000e\b\u0002Öò\u0005\u0010\t\u0002×ò\u0005\u0012\n\u0002Øò\u0005\u0014\u000b\u0002Ùò\u0005\u0016\f\u0002Úò\u0005\u0018\r\u0002Ûò\u0005\u001a\u000e\u0002Üò\u0005\u001c\u000f\u0002Ýò\u0005\u001e\u0010\u0002Þò\u0005 \u0011\u0002ßò\u0005\"\u0012\u0002àò\u0005$\u0013\u0002áò\u0005&\u0014\u0002âò\u0005(\u0015\u0002ãò\u0005*\u0016\u0002äò\u0005,\u0017\u0002åò\u0005.\u0018\u0002æò\u00050\u0019\u0002çò\u00052\u001a\u0002èò\u00054\u001b\u0002éò\u00056\u001c\u0002êò\u00058\u001d\u0002ëò\u0005:\u001e\u0002ìò\u0005<\u001f\u0002íò\u0005> \u0002îò\u0005B\"\u0002ïò\u0005D#\u0002ðò\u0005F$\u0002ñÓ\u0003\u0002\u0002\u0002ñÔ\u0003\u0002\u0002\u0002ñÕ\u0003\u0002\u0002\u0002ñÖ\u0003\u0002\u0002\u0002ñ×\u0003\u0002\u0002\u0002ñØ\u0003\u0002\u0002\u0002ñÙ\u0003\u0002\u0002\u0002ñÚ\u0003\u0002\u0002\u0002ñÛ\u0003\u0002\u0002\u0002ñÜ\u0003\u0002\u0002\u0002ñÝ\u0003\u0002\u0002\u0002ñÞ\u0003\u0002\u0002\u0002ñß\u0003\u0002\u0002\u0002ñà\u0003\u0002\u0002\u0002ñá\u0003\u0002\u0002\u0002ñâ\u0003\u0002\u0002\u0002ñã\u0003\u0002\u0002\u0002ñä\u0003\u0002\u0002\u0002ñå\u0003\u0002\u0002\u0002ñæ\u0003\u0002\u0002\u0002ñç\u0003\u0002\u0002\u0002ñè\u0003\u0002\u0002\u0002ñé\u0003\u0002\u0002\u0002ñê\u0003\u0002\u0002\u0002ñë\u0003\u0002\u0002\u0002ñì\u0003\u0002\u0002\u0002ñí\u0003\u0002\u0002\u0002ñî\u0003\u0002\u0002\u0002ñï\u0003\u0002\u0002\u0002ñð\u0003\u0002\u0002\u0002ò\t\u0003\u0002\u0002\u0002óô\u0007 \u0002\u0002ôø\u0007\u0084\u0002\u0002õö\u0005\u0084C\u0002ö÷\u0007\u0004\u0002\u0002÷ù\u0003\u0002\u0002\u0002øõ\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùú\u0003\u0002\u0002\u0002úă\u0005\u008aF\u0002ûü\u0007{\u0002\u0002üý\u0007\u0088\u0002\u0002ýĄ\u0005\u008eH\u0002þĀ\u0007\u001d\u0002\u0002ÿā\u00070\u0002\u0002Āÿ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āĂ\u0003\u0002\u0002\u0002ĂĄ\u0005H%\u0002ăû\u0003\u0002\u0002\u0002ăþ\u0003\u0002\u0002\u0002Ą\u000b\u0003\u0002\u0002\u0002ąČ\u0007!\u0002\u0002Ćč\u0005\u0084C\u0002ćč\u0005\u008cG\u0002Ĉĉ\u0005\u0084C\u0002ĉĊ\u0007\u0004\u0002\u0002Ċċ\u0005\u008cG\u0002ċč\u0003\u0002\u0002\u0002ČĆ\u0003\u0002\u0002\u0002Čć\u0003\u0002\u0002\u0002ČĈ\u0003\u0002\u0002\u0002Čč\u0003\u0002\u0002\u0002č\r\u0003\u0002\u0002\u0002ĎĐ\u0007%\u0002\u0002ďđ\u00079\u0002\u0002Đď\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0003\u0002\u0002\u0002Ēē\u0005P)\u0002ēĔ\u0007#\u0002\u0002Ĕĕ\u0005\u0084C\u0002ĕ\u000f\u0003\u0002\u0002\u0002ĖĘ\u0007(\u0002\u0002ėę\t\u0002\u0002\u0002Ęė\u0003\u0002\u0002\u0002Ęę\u0003\u0002\u0002\u0002ęĞ\u0003\u0002\u0002\u0002ĚĜ\u0007\u0089\u0002\u0002ěĝ\u0005¤S\u0002Ĝě\u0003\u0002\u0002\u0002Ĝĝ\u0003\u0002\u0002\u0002ĝğ\u0003\u0002\u0002\u0002ĞĚ\u0003\u0002\u0002\u0002Ğğ\u0003\u0002\u0002\u0002ğ\u0011\u0003\u0002\u0002\u0002Ġĥ\t\u0003\u0002\u0002ġģ\u0007\u0089\u0002\u0002ĢĤ\u0005¤S\u0002ģĢ\u0003\u0002\u0002\u0002ģĤ\u0003\u0002\u0002\u0002ĤĦ\u0003\u0002\u0002\u0002ĥġ\u0003\u0002\u0002\u0002ĥĦ\u0003\u0002\u0002\u0002Ħ\u0013\u0003\u0002\u0002\u0002ħĩ\u0005Z.\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĴ\u0005n8\u0002īĭ\u0007\u008b\u0002\u0002ĬĮ\u0007\u001f\u0002\u0002ĭĬ\u0003\u0002\u0002\u0002ĭĮ\u0003\u0002\u0002\u0002ĮĲ\u0003\u0002\u0002\u0002įĲ\u0007\\\u0002\u0002İĲ\u0007F\u0002\u0002ıī\u0003\u0002\u0002\u0002ıį\u0003\u0002\u0002\u0002ıİ\u0003\u0002\u0002\u0002Ĳĳ\u0003\u0002\u0002\u0002ĳĵ\u0005n8\u0002Ĵı\u0003\u0002\u0002\u0002ĵĶ\u0003\u0002\u0002\u0002ĶĴ\u0003\u0002\u0002\u0002Ķķ\u0003\u0002\u0002\u0002ķł\u0003\u0002\u0002\u0002ĸĹ\u0007o\u0002\u0002Ĺĺ\u0007*\u0002\u0002ĺĿ\u0005^0\u0002Ļļ\u0007\u0007\u0002\u0002ļľ\u0005^0\u0002ĽĻ\u0003\u0002\u0002\u0002ľŁ\u0003\u0002\u0002\u0002ĿĽ\u0003\u0002\u0002\u0002Ŀŀ\u0003\u0002\u0002\u0002ŀŃ\u0003\u0002\u0002\u0002ŁĿ\u0003\u0002\u0002\u0002łĸ\u0003\u0002\u0002\u0002łŃ\u0003\u0002\u0002\u0002ŃŊ\u0003\u0002\u0002\u0002ńŅ\u0007d\u0002\u0002Ņň\u0005P)\u0002ņŇ\t\u0004\u0002\u0002Ňŉ\u0005P)\u0002ňņ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŋ\u0003\u0002\u0002\u0002Ŋń\u0003\u0002\u0002\u0002Ŋŋ\u0003\u0002\u0002\u0002ŋ\u0015\u0003\u0002\u0002\u0002ŌŎ\u00074\u0002\u0002ōŏ\u0007\u008c\u0002\u0002Ŏō\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏŐ\u0003\u0002\u0002\u0002ŐŔ\u0007V\u0002\u0002őŒ\u0007R\u0002\u0002Œœ\u0007h\u0002\u0002œŕ\u0007H\u0002\u0002Ŕő\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕř\u0003\u0002\u0002\u0002Ŗŗ\u0005\u0084C\u0002ŗŘ\u0007\u0004\u0002\u0002ŘŚ\u0003\u0002\u0002\u0002řŖ\u0003\u0002\u0002\u0002řŚ\u0003\u0002\u0002\u0002Śś\u0003\u0002\u0002\u0002śŜ\u0005\u0096L\u0002Ŝŝ\u0007m\u0002\u0002ŝŞ\u0005\u008aF\u0002Şş\u0007\u0005\u0002\u0002şŤ\u0005V,\u0002Šš\u0007\u0007\u0002\u0002šţ\u0005V,\u0002ŢŠ\u0003\u0002\u0002\u0002ţŦ\u0003\u0002\u0002\u0002ŤŢ\u0003\u0002\u0002\u0002Ťť\u0003\u0002\u0002\u0002ťŧ\u0003\u0002\u0002\u0002ŦŤ\u0003\u0002\u0002\u0002ŧŪ\u0007\u0006\u0002\u0002Ũũ\u0007\u0094\u0002\u0002ũū\u0005P)\u0002ŪŨ\u0003\u0002\u0002\u0002Ūū\u0003\u0002\u0002\u0002ū\u0017\u0003\u0002\u0002\u0002ŬŮ\u00074\u0002\u0002ŭů\t\u0005\u0002\u0002Ůŭ\u0003\u0002\u0002\u0002Ůů\u0003\u0002\u0002\u0002ůŰ\u0003\u0002\u0002\u0002ŰŴ\u0007\u0084\u0002\u0002űŲ\u0007R\u0002\u0002Ųų\u0007h\u0002\u0002ųŵ\u0007H\u0002\u0002Ŵű\u0003\u0002\u0002\u0002Ŵŵ\u0003\u0002\u0002\u0002ŵŹ\u0003\u0002\u0002\u0002Ŷŷ\u0005\u0084C\u0002ŷŸ\u0007\u0004\u0002\u0002Ÿź\u0003\u0002\u0002\u0002ŹŶ\u0003\u0002\u0002\u0002Źź\u0003\u0002\u0002\u0002źŻ\u0003\u0002\u0002\u0002ŻƓ\u0005\u008aF\u0002żŽ\u0007\u0005\u0002\u0002ŽƂ\u0005H%\u0002žſ\u0007\u0007\u0002\u0002ſƁ\u0005H%\u0002ƀž\u0003\u0002\u0002\u0002ƁƄ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002ƃƉ\u0003\u0002\u0002\u0002ƄƂ\u0003\u0002\u0002\u0002ƅƆ\u0007\u0007\u0002\u0002Ɔƈ\u0005X-\u0002Ƈƅ\u0003\u0002\u0002\u0002ƈƋ\u0003\u0002\u0002\u0002ƉƇ\u0003\u0002\u0002\u0002ƉƊ\u0003\u0002\u0002\u0002Ɗƌ\u0003\u0002\u0002\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0007\u0006\u0002\u0002ƍƎ\u0007\u0096\u0002\u0002ƎƐ\u0007\u0097\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƏƐ\u0003\u0002\u0002\u0002ƐƔ\u0003\u0002\u0002\u0002Ƒƒ\u0007#\u0002\u0002ƒƔ\u0005> \u0002Ɠż\u0003\u0002\u0002\u0002ƓƑ\u0003\u0002\u0002\u0002Ɣ\u0019\u0003\u0002\u0002\u0002ƕƗ\u00074\u0002\u0002ƖƘ\t\u0005\u0002\u0002ƗƖ\u0003\u0002\u0002\u0002ƗƘ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƝ\u0007\u008a\u0002\u0002ƚƛ\u0007R\u0002\u0002ƛƜ\u0007h\u0002\u0002Ɯƞ\u0007H\u0002\u0002Ɲƚ\u0003\u0002\u0002\u0002Ɲƞ\u0003\u0002\u0002\u0002ƞƢ\u0003\u0002\u0002\u0002ƟƠ\u0005\u0084C\u0002Ơơ\u0007\u0004\u0002\u0002ơƣ\u0003\u0002\u0002\u0002ƢƟ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002ƤƩ\u0005\u0098M\u0002ƥƪ\u0007'\u0002\u0002Ʀƪ\u0007\u001e\u0002\u0002Ƨƨ\u0007[\u0002\u0002ƨƪ\u0007k\u0002\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƪ\u0003\u0002\u0002\u0002ƪƹ\u0003\u0002\u0002\u0002ƫƺ\u0007=\u0002\u0002Ƭƺ\u0007Z\u0002\u0002ƭƷ\u0007\u008d\u0002\u0002ƮƯ\u0007k\u0002\u0002Ưƴ\u0005\u0090I\u0002ưƱ\u0007\u0007\u0002\u0002ƱƳ\u0005\u0090I\u0002Ʋư\u0003\u0002\u0002\u0002Ƴƶ\u0003\u0002\u0002\u0002ƴƲ\u0003\u0002\u0002\u0002ƴƵ\u0003\u0002\u0002\u0002ƵƸ\u0003\u0002\u0002\u0002ƶƴ\u0003\u0002\u0002\u0002ƷƮ\u0003\u0002\u0002\u0002ƷƸ\u0003\u0002\u0002\u0002Ƹƺ\u0003\u0002\u0002\u0002ƹƫ\u0003\u0002\u0002\u0002ƹƬ\u0003\u0002\u0002\u0002ƹƭ\u0003\u0002\u0002\u0002ƺƻ\u0003\u0002\u0002\u0002ƻƿ\u0007m\u0002\u0002Ƽƽ\u0005\u0084C\u0002ƽƾ\u0007\u0004\u0002\u0002ƾǀ\u0003\u0002\u0002\u0002ƿƼ\u0003\u0002\u0002\u0002ƿǀ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǅ\u0005\u008aF\u0002ǂǃ\u0007K\u0002\u0002ǃǄ\u0007B\u0002\u0002Ǆǆ\u0007\u0080\u0002\u0002ǅǂ\u0003\u0002\u0002\u0002ǅǆ\u0003\u0002\u0002\u0002ǆǉ\u0003\u0002\u0002\u0002Ǉǈ\u0007\u0093\u0002\u0002ǈǊ\u0005P)\u0002ǉǇ\u0003\u0002\u0002\u0002ǉǊ\u0003\u0002\u0002\u0002Ǌǋ\u0003\u0002\u0002\u0002ǋǔ\u0007(\u0002\u0002ǌǑ\u0005B\"\u0002ǍǑ\u00050\u0019\u0002ǎǑ\u0005 \u0011\u0002ǏǑ\u0005> \u0002ǐǌ\u0003\u0002\u0002\u0002ǐǍ\u0003\u0002\u0002\u0002ǐǎ\u0003\u0002\u0002\u0002ǐǏ\u0003\u0002\u0002\u0002Ǒǒ\u0003\u0002\u0002\u0002ǒǓ\u0007\u0003\u0002\u0002ǓǕ\u0003\u0002\u0002\u0002ǔǐ\u0003\u0002\u0002\u0002Ǖǖ\u0003\u0002\u0002\u0002ǖǔ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002Ǘǘ\u0003\u0002\u0002\u0002ǘǙ\u0007D\u0002\u0002Ǚ\u001b\u0003\u0002\u0002\u0002ǚǜ\u00074\u0002\u0002Ǜǝ\t\u0005\u0002\u0002ǜǛ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0003\u0002\u0002\u0002ǞǢ\u0007\u0091\u0002\u0002ǟǠ\u0007R\u0002\u0002Ǡǡ\u0007h\u0002\u0002ǡǣ\u0007H\u0002\u0002Ǣǟ\u0003\u0002\u0002\u0002Ǣǣ\u0003\u0002\u0002\u0002ǣǧ\u0003\u0002\u0002\u0002Ǥǥ\u0005\u0084C\u0002ǥǦ\u0007\u0004\u0002\u0002ǦǨ\u0003\u0002\u0002\u0002ǧǤ\u0003\u0002\u0002\u0002ǧǨ\u0003\u0002\u0002\u0002Ǩǩ\u0003\u0002\u0002\u0002ǩǪ\u0005\u009aN\u0002Ǫǫ\u0007#\u0002\u0002ǫǬ\u0005> \u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǮ\u00074\u0002\u0002Ǯǯ\u0007\u0092\u0002\u0002ǯǳ\u0007\u0084\u0002\u0002ǰǱ\u0007R\u0002\u0002Ǳǲ\u0007h\u0002\u0002ǲǴ\u0007H\u0002\u0002ǳǰ\u0003\u0002\u0002\u0002ǳǴ\u0003\u0002\u0002\u0002ǴǸ\u0003\u0002\u0002\u0002ǵǶ\u0005\u0084C\u0002ǶǷ\u0007\u0004\u0002\u0002Ƿǹ\u0003\u0002\u0002\u0002Ǹǵ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0005\u008aF\u0002ǻǼ\u0007\u008e\u0002\u0002ǼȈ\u0005\u009cO\u0002ǽǾ\u0007\u0005\u0002\u0002Ǿȃ\u0005z>\u0002ǿȀ\u0007\u0007\u0002\u0002ȀȂ\u0005z>\u0002ȁǿ\u0003\u0002\u0002\u0002Ȃȅ\u0003\u0002\u0002\u0002ȃȁ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0003\u0002\u0002\u0002ȅȃ\u0003\u0002\u0002\u0002Ȇȇ\u0007\u0006\u0002\u0002ȇȉ\u0003\u0002\u0002\u0002Ȉǽ\u0003\u0002\u0002\u0002Ȉȉ\u0003\u0002\u0002\u0002ȉ\u001f\u0003\u0002\u0002\u0002ȊȌ\u0005Z.\u0002ȋȊ\u0003\u0002\u0002\u0002ȋȌ\u0003\u0002\u0002\u0002Ȍȍ\u0003\u0002\u0002\u0002ȍȎ\u0007=\u0002\u0002Ȏȏ\u0007M\u0002\u0002ȏȒ\u0005\\/\u0002Ȑȑ\u0007\u0094\u0002\u0002ȑȓ\u0005P)\u0002ȒȐ\u0003\u0002\u0002\u0002Ȓȓ\u0003\u0002\u0002\u0002ȓ!\u0003\u0002\u0002\u0002ȔȖ\u0005Z.\u0002ȕȔ\u0003\u0002\u0002\u0002ȕȖ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0007=\u0002\u0002Șș\u0007M\u0002\u0002șȜ\u0005\\/\u0002Țț\u0007\u0094\u0002\u0002țȝ\u0005P)\u0002ȜȚ\u0003\u0002\u0002\u0002Ȝȝ\u0003\u0002\u0002\u0002ȝȰ\u0003\u0002\u0002\u0002Ȟȟ\u0007o\u0002\u0002ȟȠ\u0007*\u0002\u0002Ƞȥ\u0005^0\u0002ȡȢ\u0007\u0007\u0002\u0002ȢȤ\u0005^0\u0002ȣȡ\u0003\u0002\u0002\u0002Ȥȧ\u0003\u0002\u0002\u0002ȥȣ\u0003\u0002\u0002\u0002ȥȦ\u0003\u0002\u0002\u0002Ȧȩ\u0003\u0002\u0002\u0002ȧȥ\u0003\u0002\u0002\u0002ȨȞ\u0003\u0002\u0002\u0002Ȩȩ\u0003\u0002\u0002\u0002ȩȪ\u0003\u0002\u0002\u0002Ȫȫ\u0007d\u0002\u0002ȫȮ\u0005P)\u0002Ȭȭ\t\u0004\u0002\u0002ȭȯ\u0005P)\u0002ȮȬ\u0003\u0002\u0002\u0002Ȯȯ\u0003\u0002\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002ȰȨ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱ#\u0003\u0002\u0002\u0002Ȳȴ\u0007?\u0002\u0002ȳȵ\u00079\u0002\u0002ȴȳ\u0003\u0002\u0002\u0002ȴȵ\u0003\u0002\u0002\u0002ȵȶ\u0003\u0002\u0002\u0002ȶȷ\u0005\u0084C\u0002ȷ%\u0003\u0002\u0002\u0002ȸȹ\u0007A\u0002\u0002ȹȼ\u0007V\u0002\u0002ȺȻ\u0007R\u0002\u0002ȻȽ\u0007H\u0002\u0002ȼȺ\u0003\u0002\u0002\u0002ȼȽ\u0003\u0002\u0002\u0002ȽɁ\u0003\u0002\u0002\u0002Ⱦȿ\u0005\u0084C\u0002ȿɀ\u0007\u0004\u0002\u0002ɀɂ\u0003\u0002\u0002\u0002ɁȾ\u0003\u0002\u0002\u0002Ɂɂ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002ɃɄ\u0005\u0096L\u0002Ʉ'\u0003\u0002\u0002\u0002ɅɆ\u0007A\u0002\u0002Ɇɉ\u0007\u0084\u0002\u0002ɇɈ\u0007R\u0002\u0002ɈɊ\u0007H\u0002\u0002ɉɇ\u0003\u0002\u0002\u0002ɉɊ\u0003\u0002\u0002\u0002ɊɎ\u0003\u0002\u0002\u0002ɋɌ\u0005\u0084C\u0002Ɍɍ\u0007\u0004\u0002\u0002ɍɏ\u0003\u0002\u0002\u0002Ɏɋ\u0003\u0002\u0002\u0002Ɏɏ\u0003\u0002\u0002\u0002ɏɐ\u0003\u0002\u0002\u0002ɐɑ\u0005\u008aF\u0002ɑ)\u0003\u0002\u0002\u0002ɒɓ\u0007A\u0002\u0002ɓɖ\u0007\u008a\u0002\u0002ɔɕ\u0007R\u0002\u0002ɕɗ\u0007H\u0002\u0002ɖɔ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0003\u0002\u0002\u0002ɘə\u0005\u0084C\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɞ\u0005\u0098M\u0002ɞ+\u0003\u0002\u0002\u0002ɟɠ\u0007A\u0002\u0002ɠɣ\u0007\u0091\u0002\u0002ɡɢ\u0007R\u0002\u0002ɢɤ\u0007H\u0002\u0002ɣɡ\u0003\u0002\u0002\u0002ɣɤ\u0003\u0002\u0002\u0002ɤɨ\u0003\u0002\u0002\u0002ɥɦ\u0005\u0084C\u0002ɦɧ\u0007\u0004\u0002\u0002ɧɩ\u0003\u0002\u0002\u0002ɨɥ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɪ\u0003\u0002\u0002\u0002ɪɫ\u0005\u009aN\u0002ɫ-\u0003\u0002\u0002\u0002ɬɮ\u0005Z.\u0002ɭɬ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮɯ\u0003\u0002\u0002\u0002ɯɵ\u0005n8\u0002ɰɱ\u0005p9\u0002ɱɲ\u0005n8\u0002ɲɴ\u0003\u0002\u0002\u0002ɳɰ\u0003\u0002\u0002\u0002ɴɷ\u0003\u0002\u0002\u0002ɵɳ\u0003\u0002\u0002\u0002ɵɶ\u0003\u0002\u0002\u0002ɶʂ\u0003\u0002\u0002\u0002ɷɵ\u0003\u0002\u0002\u0002ɸɹ\u0007o\u0002\u0002ɹɺ\u0007*\u0002\u0002ɺɿ\u0005^0\u0002ɻɼ\u0007\u0007\u0002\u0002ɼɾ\u0005^0\u0002ɽɻ\u0003\u0002\u0002\u0002ɾʁ\u0003\u0002\u0002\u0002ɿɽ\u0003\u0002\u0002\u0002ɿʀ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʂɸ\u0003\u0002\u0002\u0002ʂʃ\u0003\u0002\u0002\u0002ʃʊ\u0003\u0002\u0002\u0002ʄʅ\u0007d\u0002\u0002ʅʈ\u0005P)\u0002ʆʇ\t\u0004\u0002\u0002ʇʉ\u0005P)\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʋ\u0003\u0002\u0002\u0002ʊʄ\u0003\u0002\u0002\u0002ʊʋ\u0003\u0002\u0002\u0002ʋ/\u0003\u0002\u0002\u0002ʌʎ\u0005Z.\u0002ʍʌ\u0003\u0002\u0002\u0002ʍʎ\u0003\u0002\u0002\u0002ʎʠ\u0003\u0002\u0002\u0002ʏʡ\u0007Z\u0002\u0002ʐʡ\u0007|\u0002\u0002ʑʒ\u0007Z\u0002\u0002ʒʓ\u0007n\u0002\u0002ʓʡ\u0007|\u0002\u0002ʔʕ\u0007Z\u0002\u0002ʕʖ\u0007n\u0002\u0002ʖʡ\u0007\u007f\u0002\u0002ʗʘ\u0007Z\u0002\u0002ʘʙ\u0007n\u0002\u0002ʙʡ\u0007\u001b\u0002\u0002ʚʛ\u0007Z\u0002\u0002ʛʜ\u0007n\u0002\u0002ʜʡ\u0007J\u0002\u0002ʝʞ\u0007Z\u0002\u0002ʞʟ\u0007n\u0002\u0002ʟʡ\u0007S\u0002\u0002ʠʏ\u0003\u0002\u0002\u0002ʠʐ\u0003\u0002\u0002\u0002ʠʑ\u0003\u0002\u0002\u0002ʠʔ\u0003\u0002\u0002\u0002ʠʗ\u0003\u0002\u0002\u0002ʠʚ\u0003\u0002\u0002\u0002ʠʝ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʦ\u0007]\u0002\u0002ʣʤ\u0005\u0084C\u0002ʤʥ\u0007\u0004\u0002\u0002ʥʧ\u0003\u0002\u0002\u0002ʦʣ\u0003\u0002\u0002\u0002ʦʧ\u0003\u0002\u0002\u0002ʧʨ\u0003\u0002\u0002\u0002ʨʴ\u0005\u008aF\u0002ʩʪ\u0007\u0005\u0002\u0002ʪʯ\u0005\u0090I\u0002ʫʬ\u0007\u0007\u0002\u0002ʬʮ\u0005\u0090I\u0002ʭʫ\u0003\u0002\u0002\u0002ʮʱ\u0003\u0002\u0002\u0002ʯʭ\u0003\u0002\u0002\u0002ʯʰ\u0003\u0002\u0002\u0002ʰʲ\u0003\u0002\u0002\u0002ʱʯ\u0003\u0002\u0002\u0002ʲʳ\u0007\u0006\u0002\u0002ʳʵ\u0003\u0002\u0002\u0002ʴʩ\u0003\u0002\u0002\u0002ʴʵ\u0003\u0002\u0002\u0002ʵ˕\u0003\u0002\u0002\u0002ʶʷ\u0007\u0090\u0002\u0002ʷʸ\u0007\u0005\u0002\u0002ʸʽ\u0005P)\u0002ʹʺ\u0007\u0007\u0002\u0002ʺʼ\u0005P)\u0002ʻʹ\u0003\u0002\u0002\u0002ʼʿ\u0003\u0002\u0002\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʽ\u0003\u0002\u0002\u0002ˀˏ\u0007\u0006\u0002\u0002ˁ˂\u0007\u0007\u0002\u0002˂˃\u0007\u0005\u0002\u0002˃ˈ\u0005P)\u0002˄˅\u0007\u0007\u0002\u0002˅ˇ\u0005P)\u0002ˆ˄\u0003\u0002\u0002\u0002ˇˊ\u0003\u0002\u0002\u0002ˈˆ\u0003\u0002\u0002\u0002ˈˉ\u0003\u0002\u0002\u0002ˉˋ\u0003\u0002\u0002\u0002ˊˈ\u0003\u0002\u0002\u0002ˋˌ\u0007\u0006\u0002\u0002ˌˎ\u0003\u0002\u0002\u0002ˍˁ\u0003\u0002\u0002\u0002ˎˑ\u0003\u0002\u0002\u0002ˏˍ\u0003\u0002\u0002\u0002ˏː\u0003\u0002\u0002\u0002ː˖\u0003\u0002\u0002\u0002ˑˏ\u0003\u0002\u0002\u0002˒˖\u0005> \u0002˓˔\u0007:\u0002\u0002˔˖\u0007\u0090\u0002\u0002˕ʶ\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˓\u0003\u0002\u0002\u0002˖1\u0003\u0002\u0002\u0002˗˛\u0007r\u0002\u0002˘˙\u0005\u0084C\u0002˙˚\u0007\u0004\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛˘\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜˝\u0003\u0002\u0002\u0002˝ˤ\u0005\u009eP\u0002˞˟\u0007\b\u0002\u0002˟˥\u0005`1\u0002ˠˡ\u0007\u0005\u0002\u0002ˡˢ\u0005`1\u0002ˢˣ\u0007\u0006\u0002\u0002ˣ˥\u0003\u0002\u0002\u0002ˤ˞\u0003\u0002\u0002\u0002ˤˠ\u0003\u0002\u0002\u0002ˤ˥\u0003\u0002\u0002\u0002˥3\u0003\u0002\u0002\u0002˦˱\u0007y\u0002\u0002˧˲\u0005\u0092J\u0002˨˩\u0005\u0084C\u0002˩˪\u0007\u0004\u0002\u0002˪ˬ\u0003\u0002\u0002\u0002˫˨\u0003\u0002\u0002\u0002˫ˬ\u0003\u0002\u0002\u0002ˬ˯\u0003\u0002\u0002\u0002˭˰\u0005\u008aF\u0002ˮ˰\u0005\u0096L\u0002˯˭\u0003\u0002\u0002\u0002˯ˮ\u0003\u0002\u0002\u0002˰˲\u0003\u0002\u0002\u0002˱˧\u0003\u0002\u0002\u0002˱˫\u0003\u0002\u0002\u0002˱˲\u0003\u0002\u0002\u0002˲5\u0003\u0002\u0002\u0002˳˵\u0007z\u0002\u0002˴˶\u0007\u0081\u0002\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˸\u0005 Q\u0002˸7\u0003\u0002\u0002\u0002˹˾\u0007\u007f\u0002\u0002˺˼\u0007\u0089\u0002\u0002˻˽\u0005¤S\u0002˼˻\u0003\u0002\u0002\u0002˼˽\u0003\u0002\u0002\u0002˽˿\u0003\u0002\u0002\u0002˾˺\u0003\u0002\u0002\u0002˾˿\u0003\u0002\u0002\u0002˿̅\u0003\u0002\u0002\u0002̀̂\u0007\u0088\u0002\u0002́̃\u0007\u0081\u0002\u0002̂́\u0003\u0002\u0002\u0002̂̃\u0003\u0002\u0002\u0002̃̄\u0003\u0002\u0002\u0002̄̆\u0005 Q\u0002̅̀\u0003\u0002\u0002\u0002̅̆\u0003\u0002\u0002\u0002̆9\u0003\u0002\u0002\u0002̇̈\u0007\u0081\u0002\u0002̈̉\u0005 Q\u0002̉;\u0003\u0002\u0002\u0002̊̌\u0005Z.\u0002̋̊\u0003\u0002\u0002\u0002̋̌\u0003\u0002\u0002\u0002̌̍\u0003\u0002\u0002\u0002̘̍\u0005n8\u0002̎̏\u0007o\u0002\u0002̏̐\u0007*\u0002\u0002̐̕\u0005^0\u0002̑̒\u0007\u0007\u0002\u0002̒̔\u0005^0\u0002̓̑\u0003\u0002\u0002\u0002̗̔\u0003\u0002\u0002\u0002̓̕\u0003\u0002\u0002\u0002̖̕\u0003\u0002\u0002\u0002̖̙\u0003\u0002\u0002\u0002̗̕\u0003\u0002\u0002\u0002̘̎\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̠\u0003\u0002\u0002\u0002̛̚\u0007d\u0002\u0002̛̞\u0005P)\u0002̜̝\t\u0004\u0002\u0002̝̟\u0005P)\u0002̞̜\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̡̟\u0003\u0002\u0002\u0002̠̚\u0003\u0002\u0002\u0002̡̠\u0003\u0002\u0002\u0002̡=\u0003\u0002\u0002\u0002̢̤\u0005Z.\u0002̢̣\u0003\u0002\u0002\u0002̣̤\u0003\u0002\u0002\u0002̤̥\u0003\u0002\u0002\u0002̥̫\u0005@!\u0002̧̦\u0005p9\u0002̧̨\u0005@!\u0002̨̪\u0003\u0002\u0002\u0002̩̦\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̸̬\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007o\u0002\u0002̯̰\u0007*\u0002\u0002̵̰\u0005^0\u0002̱̲\u0007\u0007\u0002\u0002̴̲\u0005^0\u0002̳̱\u0003\u0002\u0002\u0002̴̷\u0003\u0002\u0002\u0002̵̳\u0003\u0002\u0002\u0002̵̶\u0003\u0002\u0002\u0002̶̹\u0003\u0002\u0002\u0002̷̵\u0003\u0002\u0002\u0002̸̮\u0003\u0002\u0002\u0002̸̹\u0003\u0002\u0002\u0002̹̀\u0003\u0002\u0002\u0002̺̻\u0007d\u0002\u0002̻̾\u0005P)\u0002̼̽\t\u0004\u0002\u0002̽̿\u0005P)\u0002̼̾\u0003\u0002\u0002\u0002̾̿\u0003\u0002\u0002\u0002̿́\u0003\u0002\u0002\u0002̺̀\u0003\u0002\u0002\u0002̀́\u0003\u0002\u0002\u0002́?\u0003\u0002\u0002\u0002͂̈́\u0007\u0082\u0002\u0002̓ͅ\t\u0006\u0002\u0002̈́̓\u0003\u0002\u0002\u0002̈́ͅ\u0003\u0002\u0002\u0002͆ͅ\u0003\u0002\u0002\u0002͆͋\u0005d3\u0002͇͈\u0007\u0007\u0002\u0002͈͊\u0005d3\u0002͉͇\u0003\u0002\u0002\u0002͍͊\u0003\u0002\u0002\u0002͉͋\u0003\u0002\u0002\u0002͋͌\u0003\u0002\u0002\u0002͚͌\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͎͘\u0007M\u0002\u0002͏͔\u0005f4\u0002͐͑\u0007\u0007\u0002\u0002͓͑\u0005f4\u0002͒͐\u0003\u0002\u0002\u0002͓͖\u0003\u0002\u0002\u0002͔͒\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͙\u0003\u0002\u0002\u0002͖͔\u0003\u0002\u0002\u0002͙͗\u0005h5\u0002͘͏\u0003\u0002\u0002\u0002͗͘\u0003\u0002\u0002\u0002͙͛\u0003\u0002\u0002\u0002͚͎\u0003\u0002\u0002\u0002͚͛\u0003\u0002\u0002\u0002͛͞\u0003\u0002\u0002\u0002͜͝\u0007\u0094\u0002\u0002͟͝\u0005P)\u0002͜͞\u0003\u0002\u0002\u0002͟͞\u0003\u0002\u0002\u0002ͮ͟\u0003\u0002\u0002\u0002͠͡\u0007P\u0002\u0002͢͡\u0007*\u0002\u0002ͧ͢\u0005P)\u0002ͣͤ\u0007\u0007\u0002\u0002ͤͦ\u0005P)\u0002ͥͣ\u0003\u0002\u0002\u0002ͦͩ\u0003\u0002\u0002\u0002ͧͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͬ\u0003\u0002\u0002\u0002ͩͧ\u0003\u0002\u0002\u0002ͪͫ\u0007Q\u0002\u0002ͫͭ\u0005P)\u0002ͬͪ\u0003\u0002\u0002\u0002ͬͭ\u0003\u0002\u0002\u0002ͭͯ\u0003\u0002\u0002\u0002ͮ͠\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯ\u038d\u0003\u0002\u0002\u0002Ͱͱ\u0007\u0090\u0002\u0002ͱͲ\u0007\u0005\u0002\u0002Ͳͷ\u0005P)\u0002ͳʹ\u0007\u0007\u0002\u0002ʹͶ\u0005P)\u0002͵ͳ\u0003\u0002\u0002\u0002Ͷ\u0379\u0003\u0002\u0002\u0002ͷ͵\u0003\u0002\u0002\u0002ͷ\u0378\u0003\u0002\u0002\u0002\u0378ͺ\u0003\u0002\u0002\u0002\u0379ͷ\u0003\u0002\u0002\u0002ͺΉ\u0007\u0006\u0002\u0002ͻͼ\u0007\u0007\u0002\u0002ͼͽ\u0007\u0005\u0002\u0002ͽ\u0382\u0005P)\u0002;Ϳ\u0007\u0007\u0002\u0002Ϳ\u0381\u0005P)\u0002\u0380;\u0003\u0002\u0002\u0002\u0381΄\u0003\u0002\u0002\u0002\u0382\u0380\u0003\u0002\u0002\u0002\u0382\u0383\u0003\u0002\u0002\u0002\u0383΅\u0003\u0002\u0002\u0002΄\u0382\u0003\u0002\u0002\u0002΅Ά\u0007\u0006\u0002\u0002ΆΈ\u0003\u0002\u0002\u0002·ͻ\u0003\u0002\u0002\u0002Έ\u038b\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038d\u0003\u0002\u0002\u0002\u038bΉ\u0003\u0002\u0002\u0002Ό͂\u0003\u0002\u0002\u0002ΌͰ\u0003\u0002\u0002\u0002\u038dA\u0003\u0002\u0002\u0002Ύΐ\u0005Z.\u0002ΏΎ\u0003\u0002\u0002\u0002Ώΐ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΜ\u0007\u008d\u0002\u0002ΒΓ\u0007n\u0002\u0002ΓΝ\u0007\u007f\u0002\u0002ΔΕ\u0007n\u0002\u0002ΕΝ\u0007\u001b\u0002\u0002ΖΗ\u0007n\u0002\u0002ΗΝ\u0007|\u0002\u0002ΘΙ\u0007n\u0002\u0002ΙΝ\u0007J\u0002\u0002ΚΛ\u0007n\u0002\u0002ΛΝ\u0007S\u0002\u0002ΜΒ\u0003\u0002\u0002\u0002ΜΔ\u0003\u0002\u0002\u0002ΜΖ\u0003\u0002\u0002\u0002ΜΘ\u0003\u0002\u0002\u0002ΜΚ\u0003\u0002\u0002\u0002ΜΝ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002ΞΟ\u0005\\/\u0002ΟΠ\u0007\u0083\u0002\u0002ΠΡ\u0005\u0090I\u0002Ρ\u03a2\u0007\b\u0002\u0002\u03a2Ϊ\u0005P)\u0002ΣΤ\u0007\u0007\u0002\u0002ΤΥ\u0005\u0090I\u0002ΥΦ\u0007\b\u0002\u0002ΦΧ\u0005P)\u0002ΧΩ\u0003\u0002\u0002\u0002ΨΣ\u0003\u0002\u0002\u0002Ωά\u0003\u0002\u0002\u0002ΪΨ\u0003\u0002\u0002\u0002ΪΫ\u0003\u0002\u0002\u0002Ϋί\u0003\u0002\u0002\u0002άΪ\u0003\u0002\u0002\u0002έή\u0007\u0094\u0002\u0002ήΰ\u0005P)\u0002ίέ\u0003\u0002\u0002\u0002ίΰ\u0003\u0002\u0002\u0002ΰC\u0003\u0002\u0002\u0002αγ\u0005Z.\u0002βα\u0003\u0002\u0002\u0002βγ\u0003\u0002\u0002\u0002γδ\u0003\u0002\u0002\u0002δο\u0007\u008d\u0002\u0002εζ\u0007n\u0002\u0002ζπ\u0007\u007f\u0002\u0002ηθ\u0007n\u0002\u0002θπ\u0007\u001b\u0002\u0002ικ\u0007n\u0002\u0002κπ\u0007|\u0002\u0002λμ\u0007n\u0002\u0002μπ\u0007J\u0002\u0002νξ\u0007n\u0002\u0002ξπ\u0007S\u0002\u0002οε\u0003\u0002\u0002\u0002οη\u0003\u0002\u0002\u0002οι\u0003\u0002\u0002\u0002ολ\u0003\u0002\u0002\u0002ον\u0003\u0002\u0002\u0002οπ\u0003\u0002\u0002\u0002πρ\u0003\u0002\u0002\u0002ρς\u0005\\/\u0002ςσ\u0007\u0083\u0002\u0002στ\u0005\u0090I\u0002τυ\u0007\b\u0002\u0002υύ\u0005P)\u0002φχ\u0007\u0007\u0002\u0002χψ\u0005\u0090I\u0002ψω\u0007\b\u0002\u0002ωϊ\u0005P)\u0002ϊό\u0003\u0002\u0002\u0002ϋφ\u0003\u0002\u0002\u0002όϏ\u0003\u0002\u0002\u0002ύϋ\u0003\u0002\u0002\u0002ύώ\u0003\u0002\u0002\u0002ώϒ\u0003\u0002\u0002\u0002Ϗύ\u0003\u0002\u0002\u0002ϐϑ\u0007\u0094\u0002\u0002ϑϓ\u0005P)\u0002ϒϐ\u0003\u0002\u0002\u0002ϒϓ\u0003\u0002\u0002\u0002ϓϦ\u0003\u0002\u0002\u0002ϔϕ\u0007o\u0002\u0002ϕϖ\u0007*\u0002\u0002ϖϛ\u0005^0\u0002ϗϘ\u0007\u0007\u0002\u0002ϘϚ\u0005^0\u0002ϙϗ\u0003\u0002\u0002\u0002Ϛϝ\u0003\u0002\u0002\u0002ϛϙ\u0003\u0002\u0002\u0002ϛϜ\u0003\u0002\u0002\u0002Ϝϟ\u0003\u0002\u0002\u0002ϝϛ\u0003\u0002\u0002\u0002Ϟϔ\u0003\u0002\u0002\u0002Ϟϟ\u0003\u0002\u0002\u0002ϟϠ\u0003\u0002\u0002\u0002Ϡϡ\u0007d\u0002\u0002ϡϤ\u0005P)\u0002Ϣϣ\t\u0004\u0002\u0002ϣϥ\u0005P)\u0002ϤϢ\u0003\u0002\u0002\u0002Ϥϥ\u0003\u0002\u0002\u0002ϥϧ\u0003\u0002\u0002\u0002ϦϞ\u0003\u0002\u0002\u0002Ϧϧ\u0003\u0002\u0002\u0002ϧE\u0003\u0002\u0002\u0002Ϩϩ\u0007\u008f\u0002\u0002ϩG\u0003\u0002\u0002\u0002ϪϬ\u0005\u0090I\u0002ϫϭ\u0005J&\u0002Ϭϫ\u0003\u0002\u0002\u0002Ϭϭ\u0003\u0002\u0002\u0002ϭϱ\u0003\u0002\u0002\u0002Ϯϰ\u0005L'\u0002ϯϮ\u0003\u0002\u0002\u0002ϰϳ\u0003\u0002\u0002\u0002ϱϯ\u0003\u0002\u0002\u0002ϱϲ\u0003\u0002\u0002\u0002ϲI\u0003\u0002\u0002\u0002ϳϱ\u0003\u0002\u0002\u0002ϴ϶\u0005\u0080A\u0002ϵϴ\u0003\u0002\u0002\u0002϶Ϸ\u0003\u0002\u0002\u0002Ϸϸ\u0003\u0002\u0002\u0002Ϸϵ\u0003\u0002\u0002\u0002ϸЃ\u0003\u0002\u0002\u0002ϹϺ\u0007\u0005\u0002\u0002Ϻϻ\u0005r:\u0002ϻϼ\u0007\u0006\u0002\u0002ϼЄ\u0003\u0002\u0002\u0002ϽϾ\u0007\u0005\u0002\u0002ϾϿ\u0005r:\u0002ϿЀ\u0007\u0007\u0002\u0002ЀЁ\u0005r:\u0002ЁЂ\u0007\u0006\u0002\u0002ЂЄ\u0003\u0002\u0002\u0002ЃϹ\u0003\u0002\u0002\u0002ЃϽ\u0003\u0002\u0002\u0002ЃЄ\u0003\u0002\u0002\u0002ЄK\u0003\u0002\u0002\u0002ЅІ\u00073\u0002\u0002ІЈ\u0005\u0080A\u0002ЇЅ\u0003\u0002\u0002\u0002ЇЈ\u0003\u0002\u0002\u0002ЈЪ\u0003\u0002\u0002\u0002ЉЊ\u0007s\u0002\u0002ЊЌ\u0007a\u0002\u0002ЋЍ\t\u0007\u0002\u0002ЌЋ\u0003\u0002\u0002\u0002ЌЍ\u0003\u0002\u0002\u0002ЍЎ\u0003\u0002\u0002\u0002ЎА\u0005N(\u0002ЏБ\u0007&\u0002\u0002АЏ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЫ\u0003\u0002\u0002\u0002ВД\u0007h\u0002\u0002ГВ\u0003\u0002\u0002\u0002ГД\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0007j\u0002\u0002ЖЫ\u0005N(\u0002ЗИ\u0007\u008c\u0002\u0002ИЫ\u0005N(\u0002ЙК\u0007.\u0002\u0002КЛ\u0007\u0005\u0002\u0002ЛМ\u0005P)\u0002МН\u0007\u0006\u0002\u0002НЫ\u0003\u0002\u0002\u0002ОХ\u0007:\u0002\u0002ПЦ\u0005r:\u0002РЦ\u0005t;\u0002СТ\u0007\u0005\u0002\u0002ТУ\u0005P)\u0002УФ\u0007\u0006\u0002\u0002ФЦ\u0003\u0002\u0002\u0002ХП\u0003\u0002\u0002\u0002ХР\u0003\u0002\u0002\u0002ХС\u0003\u0002\u0002\u0002ЦЫ\u0003\u0002\u0002\u0002ЧШ\u0007/\u0002\u0002ШЫ\u0005\u0092J\u0002ЩЫ\u0005R*\u0002ЪЉ\u0003\u0002\u0002\u0002ЪГ\u0003\u0002\u0002\u0002ЪЗ\u0003\u0002\u0002\u0002ЪЙ\u0003\u0002\u0002\u0002ЪО\u0003\u0002\u0002\u0002ЪЧ\u0003\u0002\u0002\u0002ЪЩ\u0003\u0002\u0002\u0002ЫM\u0003\u0002\u0002\u0002ЬЭ\u0007m\u0002\u0002ЭЮ\u00072\u0002\u0002Юа\t\b\u0002\u0002ЯЬ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аO\u0003\u0002\u0002\u0002бв\b)\u0001\u0002вѾ\u0005t;\u0002гѾ\u0007\u0099\u0002\u0002де\u0005\u0084C\u0002еж\u0007\u0004\u0002\u0002жи\u0003\u0002\u0002\u0002зд\u0003\u0002\u0002\u0002зи\u0003\u0002\u0002\u0002ий\u0003\u0002\u0002\u0002йк\u0005\u008aF\u0002кл\u0007\u0004\u0002\u0002лн\u0003\u0002\u0002\u0002мз\u0003\u0002\u0002\u0002мн\u0003\u0002\u0002\u0002но\u0003\u0002\u0002\u0002оѾ\u0005\u0090I\u0002пр\u0005v<\u0002рс\u0005P)\u0017сѾ\u0003\u0002\u0002\u0002ту\u0005\u0082B\u0002уѐ\u0007\u0005\u0002\u0002фц\u0007@\u0002\u0002хф\u0003\u0002\u0002\u0002хц\u0003\u0002\u0002\u0002цч\u0003\u0002\u0002\u0002чь\u0005P)\u0002шщ\u0007\u0007\u0002\u0002щы\u0005P)\u0002ъш\u0003\u0002\u0002\u0002ыю\u0003\u0002\u0002\u0002ьъ\u0003\u0002\u0002\u0002ьэ\u0003\u0002\u0002\u0002эё\u0003\u0002\u0002\u0002юь\u0003\u0002\u0002\u0002яё\u0007\t\u0002\u0002ѐх\u0003\u0002\u0002\u0002ѐя\u0003\u0002\u0002\u0002ѐё\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0007\u0006\u0002\u0002ѓѾ\u0003\u0002\u0002\u0002єѕ\u0007\u0005\u0002\u0002ѕі\u0005P)\u0002ії\u0007\u0006\u0002\u0002їѾ\u0003\u0002\u0002\u0002јљ\u0007-\u0002\u0002љњ\u0007\u0005\u0002\u0002њћ\u0005P)\u0002ћќ\u0007#\u0002\u0002ќѝ\u0005J&\u0002ѝў\u0007\u0006\u0002\u0002ўѾ\u0003\u0002\u0002\u0002џѡ\u0007h\u0002\u0002Ѡџ\u0003\u0002\u0002\u0002Ѡѡ\u0003\u0002\u0002\u0002ѡѢ\u0003\u0002\u0002\u0002ѢѤ\u0007H\u0002\u0002ѣѠ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\u0003\u0002\u0002\u0002ѥѦ\u0007\u0005\u0002\u0002Ѧѧ\u0005> \u0002ѧѨ\u0007\u0006\u0002\u0002ѨѾ\u0003\u0002\u0002\u0002ѩѫ\u0007,\u0002\u0002ѪѬ\u0005P)\u0002ѫѪ\u0003\u0002\u0002\u0002ѫѬ\u0003\u0002\u0002\u0002ѬѲ\u0003\u0002\u0002\u0002ѭѮ\u0007\u0093\u0002\u0002Ѯѯ\u0005P)\u0002ѯѰ\u0007\u0087\u0002\u0002Ѱѱ\u0005P)\u0002ѱѳ\u0003\u0002\u0002\u0002Ѳѭ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002ѴѲ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵѸ\u0003\u0002\u0002\u0002Ѷѷ\u0007C\u0002\u0002ѷѹ\u0005P)\u0002ѸѶ\u0003\u0002\u0002\u0002Ѹѹ\u0003\u0002\u0002\u0002ѹѺ\u0003\u0002\u0002\u0002Ѻѻ\u0007D\u0002\u0002ѻѾ\u0003\u0002\u0002\u0002ѼѾ\u0005T+\u0002ѽб\u0003\u0002\u0002\u0002ѽг\u0003\u0002\u0002\u0002ѽм\u0003\u0002\u0002\u0002ѽп\u0003\u0002\u0002\u0002ѽт\u0003\u0002\u0002\u0002ѽє\u0003\u0002\u0002\u0002ѽј\u0003\u0002\u0002\u0002ѽѣ\u0003\u0002\u0002\u0002ѽѩ\u0003\u0002\u0002\u0002ѽѼ\u0003\u0002\u0002\u0002ѾӖ\u0003\u0002\u0002\u0002ѿҀ\f\u0016\u0002\u0002Ҁҁ\u0007\r\u0002\u0002ҁӕ\u0005P)\u0017҂҃\f\u0015\u0002\u0002҃҄\t\t\u0002\u0002҄ӕ\u0005P)\u0016҅҆\f\u0014\u0002\u0002҆҇\t\n\u0002\u0002҇ӕ\u0005P)\u0015҈҉\f\u0013\u0002\u0002҉Ҋ\t\u000b\u0002\u0002Ҋӕ\u0005P)\u0014ҋҌ\f\u0012\u0002\u0002Ҍҍ\t\f\u0002\u0002ҍӕ\u0005P)\u0013Ҏҏ\f\u0011\u0002\u0002ҏҐ\t\r\u0002\u0002Ґӕ\u0005P)\u0012ґҒ\f\u000f\u0002\u0002Ғғ\u0007\"\u0002\u0002ғӕ\u0005P)\u0010Ҕҕ\f\u000e\u0002\u0002ҕҖ\u0007n\u0002\u0002Җӕ\u0005P)\u000fҗҘ\f\u0007\u0002\u0002ҘҚ\u0007^\u0002\u0002ҙқ\u0007h\u0002\u0002Қҙ\u0003\u0002\u0002\u0002Ққ\u0003\u0002\u0002\u0002қҜ\u0003\u0002\u0002\u0002Ҝӕ\u0005P)\bҝҟ\f\u0006\u0002\u0002ҞҠ\u0007h\u0002\u0002ҟҞ\u0003\u0002\u0002\u0002ҟҠ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡҢ\u0007)\u0002\u0002Ңң\u0005P)\u0002ңҤ\u0007\"\u0002\u0002Ҥҥ\u0005P)\u0007ҥӕ\u0003\u0002\u0002\u0002ҦҨ\f\u0010\u0002\u0002ҧҩ\u0007h\u0002\u0002Ҩҧ\u0003\u0002\u0002\u0002Ҩҩ\u0003\u0002\u0002\u0002ҩҪ\u0003\u0002\u0002\u0002ҪҾ\u0007U\u0002\u0002ҫҵ\u0007\u0005\u0002\u0002ҬҶ\u0005> \u0002ҭҲ\u0005P)\u0002Үү\u0007\u0007\u0002\u0002үұ\u0005P)\u0002ҰҮ\u0003\u0002\u0002\u0002ұҴ\u0003\u0002\u0002\u0002ҲҰ\u0003\u0002\u0002\u0002Ҳҳ\u0003\u0002\u0002\u0002ҳҶ\u0003\u0002\u0002\u0002ҴҲ\u0003\u0002\u0002\u0002ҵҬ\u0003\u0002\u0002\u0002ҵҭ\u0003\u0002\u0002\u0002ҵҶ\u0003\u0002\u0002\u0002Ҷҷ\u0003\u0002\u0002\u0002ҷҿ\u0007\u0006\u0002\u0002Ҹҹ\u0005\u0084C\u0002ҹҺ\u0007\u0004\u0002\u0002ҺҼ\u0003\u0002\u0002\u0002һҸ\u0003\u0002\u0002\u0002һҼ\u0003\u0002\u0002\u0002Ҽҽ\u0003\u0002\u0002\u0002ҽҿ\u0005\u008aF\u0002Ҿҫ\u0003\u0002\u0002\u0002Ҿһ\u0003\u0002\u0002\u0002ҿӕ\u0003\u0002\u0002\u0002ӀӁ\f\n\u0002\u0002Ӂӂ\u0007/\u0002\u0002ӂӕ\u0005\u0092J\u0002ӃӅ\f\t\u0002\u0002ӄӆ\u0007h\u0002\u0002Ӆӄ\u0003\u0002\u0002\u0002Ӆӆ\u0003\u0002\u0002\u0002ӆӇ\u0003\u0002\u0002\u0002Ӈӈ\t\u000e\u0002\u0002ӈӋ\u0005P)\u0002Ӊӊ\u0007E\u0002\u0002ӊӌ\u0005P)\u0002ӋӉ\u0003\u0002\u0002\u0002Ӌӌ\u0003\u0002\u0002\u0002ӌӕ\u0003\u0002\u0002\u0002ӍӒ\f\b\u0002\u0002ӎӓ\u0007_\u0002\u0002ӏӓ\u0007i\u0002\u0002Ӑӑ\u0007h\u0002\u0002ӑӓ\u0007j\u0002\u0002Ӓӎ\u0003\u0002\u0002\u0002Ӓӏ\u0003\u0002\u0002\u0002ӒӐ\u0003\u0002\u0002\u0002ӓӕ\u0003\u0002\u0002\u0002Ӕѿ\u0003\u0002\u0002\u0002Ӕ҂\u0003\u0002\u0002\u0002Ӕ҅\u0003\u0002\u0002\u0002Ӕ҈\u0003\u0002\u0002\u0002Ӕҋ\u0003\u0002\u0002\u0002ӔҎ\u0003\u0002\u0002\u0002Ӕґ\u0003\u0002\u0002\u0002ӔҔ\u0003\u0002\u0002\u0002Ӕҗ\u0003\u0002\u0002\u0002Ӕҝ\u0003\u0002\u0002\u0002ӔҦ\u0003\u0002\u0002\u0002ӔӀ\u0003\u0002\u0002\u0002ӔӃ\u0003\u0002\u0002\u0002ӔӍ\u0003\u0002\u0002\u0002ӕӘ\u0003\u0002\u0002\u0002ӖӔ\u0003\u0002\u0002\u0002Ӗӗ\u0003\u0002\u0002\u0002ӗQ\u0003\u0002\u0002\u0002ӘӖ\u0003\u0002\u0002\u0002әӚ\u0007w\u0002\u0002ӚӦ\u0005\u0094K\u0002ӛӜ\u0007\u0005\u0002\u0002Ӝӡ\u0005\u0090I\u0002ӝӞ\u0007\u0007\u0002\u0002ӞӠ\u0005\u0090I\u0002ӟӝ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӟ\u0003\u0002\u0002\u0002ӡӢ\u0003\u0002\u0002\u0002ӢӤ\u0003\u0002\u0002\u0002ӣӡ\u0003\u0002\u0002\u0002Ӥӥ\u0007\u0006\u0002\u0002ӥӧ\u0003\u0002\u0002\u0002Ӧӛ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧӺ\u0003\u0002\u0002\u0002Өө\u0007m\u0002\u0002өӲ\t\u000f\u0002\u0002Ӫӫ\u0007\u0083\u0002\u0002ӫӳ\u0007j\u0002\u0002Ӭӭ\u0007\u0083\u0002\u0002ӭӳ\u0007:\u0002\u0002Ӯӳ\u0007+\u0002\u0002ӯӳ\u0007}\u0002\u0002Ӱӱ\u0007g\u0002\u0002ӱӳ\u0007\u001c\u0002\u0002ӲӪ\u0003\u0002\u0002\u0002ӲӬ\u0003\u0002\u0002\u0002ӲӮ\u0003\u0002\u0002\u0002Ӳӯ\u0003\u0002\u0002\u0002ӲӰ\u0003\u0002\u0002\u0002ӳӷ\u0003\u0002\u0002\u0002Ӵӵ\u0007e\u0002\u0002ӵӷ\u0005\u0080A\u0002ӶӨ\u0003\u0002\u0002\u0002ӶӴ\u0003\u0002\u0002\u0002ӷӹ\u0003\u0002\u0002\u0002ӸӶ\u0003\u0002\u0002\u0002ӹӼ\u0003\u0002\u0002\u0002ӺӸ\u0003\u0002\u0002\u0002Ӻӻ\u0003\u0002\u0002\u0002ӻԇ\u0003\u0002\u0002\u0002ӼӺ\u0003\u0002\u0002\u0002ӽӿ\u0007h\u0002\u0002Ӿӽ\u0003\u0002\u0002\u0002Ӿӿ\u0003\u0002\u0002\u0002ӿԀ\u0003\u0002\u0002\u0002Ԁԅ\u0007;\u0002\u0002ԁԂ\u0007X\u0002\u0002ԂԆ\u0007<\u0002\u0002ԃԄ\u0007X\u0002\u0002ԄԆ\u0007T\u0002\u0002ԅԁ\u0003\u0002\u0002\u0002ԅԃ\u0003\u0002\u0002\u0002ԅԆ\u0003\u0002\u0002\u0002ԆԈ\u0003\u0002\u0002\u0002ԇӾ\u0003\u0002\u0002\u0002ԇԈ\u0003\u0002\u0002\u0002ԈS\u0003\u0002\u0002\u0002ԉԊ\u0007u\u0002\u0002Ԋԏ\u0007\u0005\u0002\u0002ԋԐ\u0007S\u0002\u0002Ԍԍ\t\u0010\u0002\u0002ԍԎ\u0007\u0007\u0002\u0002ԎԐ\u0005x=\u0002ԏԋ\u0003\u0002\u0002\u0002ԏԌ\u0003\u0002\u0002\u0002Ԑԑ\u0003\u0002\u0002\u0002ԑԒ\u0007\u0006\u0002\u0002ԒU\u0003\u0002\u0002\u0002ԓԖ\u0005\u0090I\u0002Ԕԕ\u0007/\u0002\u0002ԕԗ\u0005\u0092J\u0002ԖԔ\u0003\u0002\u0002\u0002Ԗԗ\u0003\u0002\u0002\u0002ԗԙ\u0003\u0002\u0002\u0002ԘԚ\t\u0007\u0002\u0002ԙԘ\u0003\u0002\u0002\u0002ԙԚ\u0003\u0002\u0002\u0002ԚW\u0003\u0002\u0002\u0002ԛԜ\u00073\u0002\u0002ԜԞ\u0005\u0080A\u0002ԝԛ\u0003\u0002\u0002\u0002ԝԞ\u0003\u0002\u0002\u0002ԞՃ\u0003\u0002\u0002\u0002ԟԠ\u0007s\u0002\u0002Ԡԣ\u0007a\u0002\u0002ԡԣ\u0007\u008c\u0002\u0002Ԣԟ\u0003\u0002\u0002\u0002Ԣԡ\u0003\u0002\u0002\u0002ԣԤ\u0003\u0002\u0002\u0002Ԥԥ\u0007\u0005\u0002\u0002ԥԪ\u0005V,\u0002Ԧԧ\u0007\u0007\u0002\u0002ԧԩ\u0005V,\u0002ԨԦ\u0003\u0002\u0002\u0002ԩԬ\u0003\u0002\u0002\u0002ԪԨ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԭ\u0003\u0002\u0002\u0002ԬԪ\u0003\u0002\u0002\u0002ԭԮ\u0007\u0006\u0002\u0002Ԯԯ\u0005N(\u0002ԯՄ\u0003\u0002\u0002\u0002\u0530Ա\u0007.\u0002\u0002ԱԲ\u0007\u0005\u0002\u0002ԲԳ\u0005P)\u0002ԳԴ\u0007\u0006\u0002\u0002ԴՄ\u0003\u0002\u0002\u0002ԵԶ\u0007L\u0002\u0002ԶԷ\u0007a\u0002\u0002ԷԸ\u0007\u0005\u0002\u0002ԸԽ\u0005\u0090I\u0002ԹԺ\u0007\u0007\u0002\u0002ԺԼ\u0005\u0090I\u0002ԻԹ\u0003\u0002\u0002\u0002ԼԿ\u0003\u0002\u0002\u0002ԽԻ\u0003\u0002\u0002\u0002ԽԾ\u0003\u0002\u0002\u0002ԾՀ\u0003\u0002\u0002\u0002ԿԽ\u0003\u0002\u0002\u0002ՀՁ\u0007\u0006\u0002\u0002ՁՂ\u0005R*\u0002ՂՄ\u0003\u0002\u0002\u0002ՃԢ\u0003\u0002\u0002\u0002Ճ\u0530\u0003\u0002\u0002\u0002ՃԵ\u0003\u0002\u0002\u0002ՄY\u0003\u0002\u0002\u0002ՅՇ\u0007\u0095\u0002\u0002ՆՈ\u0007v\u0002\u0002ՇՆ\u0003\u0002\u0002\u0002ՇՈ\u0003\u0002\u0002\u0002ՈՉ\u0003\u0002\u0002\u0002ՉՎ\u0005b2\u0002ՊՋ\u0007\u0007\u0002\u0002ՋՍ\u0005b2\u0002ՌՊ\u0003\u0002\u0002\u0002ՍՐ\u0003\u0002\u0002\u0002ՎՌ\u0003\u0002\u0002\u0002ՎՏ\u0003\u0002\u0002\u0002Տ[\u0003\u0002\u0002\u0002ՐՎ\u0003\u0002\u0002\u0002ՑՒ\u0005\u0084C\u0002ՒՓ\u0007\u0004\u0002\u0002ՓՕ\u0003\u0002\u0002\u0002ՔՑ\u0003\u0002\u0002\u0002ՔՕ\u0003\u0002\u0002\u0002ՕՖ\u0003\u0002\u0002\u0002Ֆ՜\u0005\u008aF\u0002\u0557\u0558\u0007W\u0002\u0002\u0558ՙ\u0007*\u0002\u0002ՙ՝\u0005\u0096L\u0002՚՛\u0007h\u0002\u0002՛՝\u0007W\u0002\u0002՜\u0557\u0003\u0002\u0002\u0002՜՚\u0003\u0002\u0002\u0002՜՝\u0003\u0002\u0002\u0002՝]\u0003\u0002\u0002\u0002՞ա\u0005P)\u0002՟ՠ\u0007/\u0002\u0002ՠբ\u0005\u0092J\u0002ա՟\u0003\u0002\u0002\u0002աբ\u0003\u0002\u0002\u0002բդ\u0003\u0002\u0002\u0002գե\t\u0007\u0002\u0002դգ\u0003\u0002\u0002\u0002դե\u0003\u0002\u0002\u0002ե_\u0003\u0002\u0002\u0002զժ\u0005r:\u0002էժ\u0005\u0080A\u0002ըժ\u0007\u009a\u0002\u0002թզ\u0003\u0002\u0002\u0002թէ\u0003\u0002\u0002\u0002թը\u0003\u0002\u0002\u0002ժa\u0003\u0002\u0002\u0002իշ\u0005\u008aF\u0002լխ\u0007\u0005\u0002\u0002խղ\u0005\u0090I\u0002ծկ\u0007\u0007\u0002\u0002կձ\u0005\u0090I\u0002հծ\u0003\u0002\u0002\u0002ձմ\u0003\u0002\u0002\u0002ղհ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճյ\u0003\u0002\u0002\u0002մղ\u0003\u0002\u0002\u0002յն\u0007\u0006\u0002\u0002նո\u0003\u0002\u0002\u0002շլ\u0003\u0002\u0002\u0002շո\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չպ\u0007#\u0002\u0002պջ\u0007\u0005\u0002\u0002ջռ\u0005> \u0002ռս\u0007\u0006\u0002\u0002սc\u0003\u0002\u0002\u0002վ\u058b\u0007\t\u0002\u0002տր\u0005\u008aF\u0002րց\u0007\u0004\u0002\u0002ցւ\u0007\t\u0002\u0002ւ\u058b\u0003\u0002\u0002\u0002փֈ\u0005P)\u0002քֆ\u0007#\u0002\u0002օք\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0003\u0002\u0002\u0002և։\u0005|?\u0002ֈօ\u0003\u0002\u0002\u0002ֈ։\u0003\u0002\u0002\u0002։\u058b\u0003\u0002\u0002\u0002֊վ\u0003\u0002\u0002\u0002֊տ\u0003\u0002\u0002\u0002֊փ\u0003\u0002\u0002\u0002\u058be\u0003\u0002\u0002\u0002\u058c֍\u0005\u0086D\u0002֍֎\u0007\u0004\u0002\u0002֎\u0590\u0003\u0002\u0002\u0002֏\u058c\u0003\u0002\u0002\u0002֏\u0590\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֖\u0005\u008aF\u0002֒֔\u0007#\u0002\u0002֓֒\u0003\u0002\u0002\u0002֓֔\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֕֗\u0005¢R\u0002֖֓\u0003\u0002\u0002\u0002֖֗\u0003\u0002\u0002\u0002֗֝\u0003\u0002\u0002\u0002֘֙\u0007W\u0002\u0002֚֙\u0007*\u0002\u0002֚֞\u0005\u0096L\u0002֛֜\u0007h\u0002\u0002֜֞\u0007W\u0002\u0002֝֘\u0003\u0002\u0002\u0002֛֝\u0003\u0002\u0002\u0002֝֞\u0003\u0002\u0002\u0002֞\u05cf\u0003\u0002\u0002\u0002֟֠\u0005\u0086D\u0002֠֡\u0007\u0004\u0002\u0002֣֡\u0003\u0002\u0002\u0002֢֟\u0003\u0002\u0002\u0002֢֣\u0003\u0002\u0002\u0002֣֤\u0003\u0002\u0002\u0002֤֥\u0005\u0088E\u0002֥֮\u0007\u0005\u0002\u0002֦֫\u0005P)\u0002֧֨\u0007\u0007\u0002\u0002֪֨\u0005P)\u0002֧֩\u0003\u0002\u0002\u0002֪֭\u0003\u0002\u0002\u0002֫֩\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֬֯\u0003\u0002\u0002\u0002֭֫\u0003\u0002\u0002\u0002֦֮\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ְ֯\u0003\u0002\u0002\u0002ְֵ\u0007\u0006\u0002\u0002ֱֳ\u0007#\u0002\u0002ֱֲ\u0003\u0002\u0002\u0002ֲֳ\u0003\u0002\u0002\u0002ֳִ\u0003\u0002\u0002\u0002ִֶ\u0005¢R\u0002ֲֵ\u0003\u0002\u0002\u0002ֵֶ\u0003\u0002\u0002\u0002ֶ\u05cf\u0003\u0002\u0002\u0002ַׁ\u0007\u0005\u0002\u0002ָֽ\u0005f4\u0002ֹֺ\u0007\u0007\u0002\u0002ֺּ\u0005f4\u0002ֹֻ\u0003\u0002\u0002\u0002ּֿ\u0003\u0002\u0002\u0002ֻֽ\u0003\u0002\u0002\u0002ֽ־\u0003\u0002\u0002\u0002־ׂ\u0003\u0002\u0002\u0002ֽֿ\u0003\u0002\u0002\u0002׀ׂ\u0005h5\u0002ָׁ\u0003\u0002\u0002\u0002ׁ׀\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׄ\u0007\u0006\u0002\u0002ׄ\u05cf\u0003\u0002\u0002\u0002ׅ׆\u0007\u0005\u0002\u0002׆ׇ\u0005> \u0002ׇ\u05cc\u0007\u0006\u0002\u0002\u05c8\u05ca\u0007#\u0002\u0002\u05c9\u05c8\u0003\u0002\u0002\u0002\u05c9\u05ca\u0003\u0002\u0002\u0002\u05ca\u05cb\u0003\u0002\u0002\u0002\u05cb\u05cd\u0005¢R\u0002\u05cc\u05c9\u0003\u0002\u0002\u0002\u05cc\u05cd\u0003\u0002\u0002\u0002\u05cd\u05cf\u0003\u0002\u0002\u0002\u05ce֏\u0003\u0002\u0002\u0002\u05ce֢\u0003\u0002\u0002\u0002\u05ceַ\u0003\u0002\u0002\u0002\u05ceׅ\u0003\u0002\u0002\u0002\u05cfg\u0003\u0002\u0002\u0002אח\u0005f4\u0002בג\u0005j6\u0002גד\u0005f4\u0002דה\u0005l7\u0002הז\u0003\u0002\u0002\u0002וב\u0003\u0002\u0002\u0002זי\u0003\u0002\u0002\u0002חו\u0003\u0002\u0002\u0002חט\u0003\u0002\u0002\u0002טi\u0003\u0002\u0002\u0002יח\u0003\u0002\u0002\u0002ךר\u0007\u0007\u0002\u0002כם\u0007f\u0002\u0002לכ\u0003\u0002\u0002\u0002לם\u0003\u0002\u0002\u0002םפ\u0003\u0002\u0002\u0002מנ\u0007b\u0002\u0002ןס\u0007p\u0002\u0002נן\u0003\u0002\u0002\u0002נס\u0003\u0002\u0002\u0002סץ\u0003\u0002\u0002\u0002עץ\u0007Y\u0002\u0002ףץ\u00075\u0002\u0002פמ\u0003\u0002\u0002\u0002פע\u0003\u0002\u0002\u0002פף\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0003\u0002\u0002\u0002צר\u0007`\u0002\u0002קך\u0003\u0002\u0002\u0002קל\u0003\u0002\u0002\u0002רk\u0003\u0002\u0002\u0002שת\u0007m\u0002\u0002ת\u05f8\u0005P)\u0002\u05eb\u05ec\u0007\u008e\u0002\u0002\u05ec\u05ed\u0007\u0005\u0002\u0002\u05edײ\u0005\u0090I\u0002\u05eeׯ\u0007\u0007\u0002\u0002ׯױ\u0005\u0090I\u0002װ\u05ee\u0003\u0002\u0002\u0002ױ״\u0003\u0002\u0002\u0002ײװ\u0003\u0002\u0002\u0002ײ׳\u0003\u0002\u0002\u0002׳\u05f5\u0003\u0002\u0002\u0002״ײ\u0003\u0002\u0002\u0002\u05f5\u05f6\u0007\u0006\u0002\u0002\u05f6\u05f8\u0003\u0002\u0002\u0002\u05f7ש\u0003\u0002\u0002\u0002\u05f7\u05eb\u0003\u0002\u0002\u0002\u05f7\u05f8\u0003\u0002\u0002\u0002\u05f8m\u0003\u0002\u0002\u0002\u05f9\u05fb\u0007\u0082\u0002\u0002\u05fa\u05fc\t\u0006\u0002\u0002\u05fb\u05fa\u0003\u0002\u0002\u0002\u05fb\u05fc\u0003\u0002\u0002\u0002\u05fc\u05fd\u0003\u0002\u0002\u0002\u05fd\u0602\u0005d3\u0002\u05fe\u05ff\u0007\u0007\u0002\u0002\u05ff\u0601\u0005d3\u0002\u0600\u05fe\u0003\u0002\u0002\u0002\u0601\u0604\u0003\u0002\u0002\u0002\u0602\u0600\u0003\u0002\u0002\u0002\u0602\u0603\u0003\u0002\u0002\u0002\u0603ؑ\u0003\u0002\u0002\u0002\u0604\u0602\u0003\u0002\u0002\u0002\u0605؏\u0007M\u0002\u0002؆؋\u0005f4\u0002؇؈\u0007\u0007\u0002\u0002؈؊\u0005f4\u0002؉؇\u0003\u0002\u0002\u0002؊؍\u0003\u0002\u0002\u0002؋؉\u0003\u0002\u0002\u0002؋،\u0003\u0002\u0002\u0002،ؐ\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؎ؐ\u0005h5\u0002؏؆\u0003\u0002\u0002\u0002؏؎\u0003\u0002\u0002\u0002ؐؒ\u0003\u0002\u0002\u0002ؑ\u0605\u0003\u0002\u0002\u0002ؑؒ\u0003\u0002\u0002\u0002ؒؕ\u0003\u0002\u0002\u0002ؓؔ\u0007\u0094\u0002\u0002ؔؖ\u0005P)\u0002ؕؓ\u0003\u0002\u0002\u0002ؕؖ\u0003\u0002\u0002\u0002ؖإ\u0003\u0002\u0002\u0002ؘؗ\u0007P\u0002\u0002ؘؙ\u0007*\u0002\u0002ؙ؞\u0005P)\u0002ؚ؛\u0007\u0007\u0002\u0002؛\u061d\u0005P)\u0002\u061cؚ\u0003\u0002\u0002\u0002\u061dؠ\u0003\u0002\u0002\u0002؞\u061c\u0003\u0002\u0002\u0002؞؟\u0003\u0002\u0002\u0002؟أ\u0003\u0002\u0002\u0002ؠ؞\u0003\u0002\u0002\u0002ءآ\u0007Q\u0002\u0002آؤ\u0005P)\u0002أء\u0003\u0002\u0002\u0002أؤ\u0003\u0002\u0002\u0002ؤئ\u0003\u0002\u0002\u0002إؗ\u0003\u0002\u0002\u0002إئ\u0003\u0002\u0002\u0002ئل\u0003\u0002\u0002\u0002اب\u0007\u0090\u0002\u0002بة\u0007\u0005\u0002\u0002ةخ\u0005P)\u0002تث\u0007\u0007\u0002\u0002ثح\u0005P)\u0002جت\u0003\u0002\u0002\u0002حذ\u0003\u0002\u0002\u0002خج\u0003\u0002\u0002\u0002خد\u0003\u0002\u0002\u0002در\u0003\u0002\u0002\u0002ذخ\u0003\u0002\u0002\u0002رـ\u0007\u0006\u0002\u0002زس\u0007\u0007\u0002\u0002سش\u0007\u0005\u0002\u0002شع\u0005P)\u0002صض\u0007\u0007\u0002\u0002ضظ\u0005P)\u0002طص\u0003\u0002\u0002\u0002ظػ\u0003\u0002\u0002\u0002عط\u0003\u0002\u0002\u0002عغ\u0003\u0002\u0002\u0002غؼ\u0003\u0002\u0002\u0002ػع\u0003\u0002\u0002\u0002ؼؽ\u0007\u0006\u0002\u0002ؽؿ\u0003\u0002\u0002\u0002ؾز\u0003\u0002\u0002\u0002ؿق\u0003\u0002\u0002\u0002ـؾ\u0003\u0002\u0002\u0002ـف\u0003\u0002\u0002\u0002فل\u0003\u0002\u0002\u0002قـ\u0003\u0002\u0002\u0002ك\u05f9\u0003\u0002\u0002\u0002كا\u0003\u0002\u0002\u0002لo\u0003\u0002\u0002\u0002مً\u0007\u008b\u0002\u0002نه\u0007\u008b\u0002\u0002هً\u0007\u001f\u0002\u0002وً\u0007\\\u0002\u0002ىً\u0007F\u0002\u0002يم\u0003\u0002\u0002\u0002ين\u0003\u0002\u0002\u0002يو\u0003\u0002\u0002\u0002يى\u0003\u0002\u0002\u0002ًq\u0003\u0002\u0002\u0002ٌَ\t\n\u0002\u0002ٌٍ\u0003\u0002\u0002\u0002ٍَ\u0003\u0002\u0002\u0002َُ\u0003\u0002\u0002\u0002ُِ\u0007\u0098\u0002\u0002ِs\u0003\u0002\u0002\u0002ّْ\t\u0011\u0002\u0002ْu\u0003\u0002\u0002\u0002ٓٔ\t\u0012\u0002\u0002ٔw\u0003\u0002\u0002\u0002ٕٖ\u0007\u009a\u0002\u0002ٖy\u0003\u0002\u0002\u0002ٗٚ\u0005P)\u0002٘ٚ\u0005H%\u0002ٙٗ\u0003\u0002\u0002\u0002ٙ٘\u0003\u0002\u0002\u0002ٚ{\u0003\u0002\u0002\u0002ٜٛ\t\u0013\u0002\u0002ٜ}\u0003\u0002\u0002\u0002ٝٞ\t\u0014\u0002\u0002ٞ\u007f\u0003\u0002\u0002\u0002ٟ٠\u0005¦T\u0002٠\u0081\u0003\u0002\u0002\u0002١٢\u0005¦T\u0002٢\u0083\u0003\u0002\u0002\u0002٣٤\u0005¦T\u0002٤\u0085\u0003\u0002\u0002\u0002٥٦\u0005¦T\u0002٦\u0087\u0003\u0002\u0002\u0002٧٨\u0005¦T\u0002٨\u0089\u0003\u0002\u0002\u0002٩٪\u0005¦T\u0002٪\u008b\u0003\u0002\u0002\u0002٫٬\u0005¦T\u0002٬\u008d\u0003\u0002\u0002\u0002٭ٮ\u0005¦T\u0002ٮ\u008f\u0003\u0002\u0002\u0002ٯٰ\u0005¦T\u0002ٰ\u0091\u0003\u0002\u0002\u0002ٱٲ\u0005¦T\u0002ٲ\u0093\u0003\u0002\u0002\u0002ٳٴ\u0005¦T\u0002ٴ\u0095\u0003\u0002\u0002\u0002ٵٶ\u0005¦T\u0002ٶ\u0097\u0003\u0002\u0002\u0002ٷٸ\u0005¦T\u0002ٸ\u0099\u0003\u0002\u0002\u0002ٹٺ\u0005¦T\u0002ٺ\u009b\u0003\u0002\u0002\u0002ٻټ\u0005¦T\u0002ټ\u009d\u0003\u0002\u0002\u0002ٽپ\u0005¦T\u0002پ\u009f\u0003\u0002\u0002\u0002ٿڀ\u0005¦T\u0002ڀ¡\u0003\u0002\u0002\u0002ځڈ\u0007\u0097\u0002\u0002ڂڈ\u0007\u009a\u0002\u0002ڃڄ\u0007\u0005\u0002\u0002ڄڅ\u0005¢R\u0002څچ\u0007\u0006\u0002\u0002چڈ\u0003\u0002\u0002\u0002ڇځ\u0003\u0002\u0002\u0002ڇڂ\u0003\u0002\u0002\u0002ڇڃ\u0003\u0002\u0002\u0002ڈ£\u0003\u0002\u0002\u0002ډڊ\u0005¦T\u0002ڊ¥\u0003\u0002\u0002\u0002ڋړ\u0007\u0097\u0002\u0002ڌړ\u0005~@\u0002ڍړ\u0007\u009a\u0002\u0002ڎڏ\u0007\u0005\u0002\u0002ڏڐ\u0005¦T\u0002ڐڑ\u0007\u0006\u0002\u0002ڑړ\u0003\u0002\u0002\u0002ڒڋ\u0003\u0002\u0002\u0002ڒڌ\u0003\u0002\u0002\u0002ڒڍ\u0003\u0002\u0002\u0002ڒڎ\u0003\u0002\u0002\u0002ړ§\u0003\u0002\u0002\u0002ïª¬·¾ÃÉÏÑñøĀăČĐĘĜĞģĥĨĭıĶĿłňŊŎŔřŤŪŮŴŹƂƉƏƓƗƝƢƩƴƷƹƿǅǉǐǖǜǢǧǳǸȃȈȋȒȕȜȥȨȮȰȴȼɁɉɎɖɛɣɨɭɵɿʂʈʊʍʠʦʯʴʽˈˏ˕˛ˤ˫˯˱˵˼˾̵̸̘̞̠̣̫͔͚̂̅̋̾̀̈́͋ͧͬͮ̕͘͞ͷ\u0382ΉΌΏΜΪίβούϒϛϞϤϦϬϱϷЃЇЌАГХЪЯзмхьѐѠѣѫѴѸѽҚҟҨҲҵһҾӅӋӒӔӖӡӦӲӶӺӾԅԇԏԖԙԝԢԪԽՃՇՎՔ՜ադթղշօֈ֊֏ֲֵֽׁ֖֢֮֓֝֫\u05c9\u05cc\u05ceחלנפקײ\u05f7\u05fb\u0602؋؏ؑؕ؞أإخعـكيٍٙڇڒ";
    public static final ATN _ATN;

    static {
        RuntimeMetaData.checkVersion("4.8", "4.8");
        _sharedContextCache = new PredictionContextCache();
        ruleNames = makeRuleNames();
        _LITERAL_NAMES = makeLiteralNames();
        _SYMBOLIC_NAMES = makeSymbolicNames();
        VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
        tokenNames = new String[_SYMBOLIC_NAMES.length];

        int i;
        for(i = 0; i < tokenNames.length; ++i) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }

        _ATN = (new ATNDeserializer()).deserialize("\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003\u009fڕ\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0003\u0002\u0003\u0002\u0007\u0002«\n\u0002\f\u0002\u000e\u0002®\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¶\n\u0004\f\u0004\u000e\u0004¹\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004½\n\u0004\r\u0004\u000e\u0004¾\u0003\u0004\u0007\u0004Â\n\u0004\f\u0004\u000e\u0004Å\u000b\u0004\u0003\u0004\u0007\u0004È\n\u0004\f\u0004\u000e\u0004Ë\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ð\n\u0005\u0005\u0005Ò\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ò\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ù\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ā\n\u0006\u0003\u0006\u0005\u0006Ą\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007č\n\u0007\u0003\b\u0003\b\u0005\bđ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tę\n\t\u0003\t\u0003\t\u0005\tĝ\n\t\u0005\tğ\n\t\u0003\n\u0003\n\u0003\n\u0005\nĤ\n\n\u0005\nĦ\n\n\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĮ\n\u000b\u0003\u000b\u0003\u000b\u0005\u000bĲ\n\u000b\u0003\u000b\u0006\u000bĵ\n\u000b\r\u000b\u000e\u000bĶ\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bľ\n\u000b\f\u000b\u000e\u000bŁ\u000b\u000b\u0005\u000bŃ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŉ\n\u000b\u0005\u000bŋ\n\u000b\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŕ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŚ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fţ\n\f\f\f\u000e\fŦ\u000b\f\u0003\f\u0003\f\u0003\f\u0005\fū\n\f\u0003\r\u0003\r\u0005\rů\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŵ\n\r\u0003\r\u0003\r\u0003\r\u0005\rź\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƁ\n\r\f\r\u000e\rƄ\u000b\r\u0003\r\u0003\r\u0007\rƈ\n\r\f\r\u000e\rƋ\u000b\r\u0003\r\u0003\r\u0003\r\u0005\rƐ\n\r\u0003\r\u0003\r\u0005\rƔ\n\r\u0003\u000e\u0003\u000e\u0005\u000eƘ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƞ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƣ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƪ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eƳ\n\u000e\f\u000e\u000e\u000eƶ\u000b\u000e\u0005\u000eƸ\n\u000e\u0005\u000eƺ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǀ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǆ\n\u000e\u0003\u000e\u0003\u000e\u0005\u000eǊ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǑ\n\u000e\u0003\u000e\u0003\u000e\u0006\u000eǕ\n\u000e\r\u000e\u000e\u000eǖ\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0005\u000fǝ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǣ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǨ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ǵ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010ǹ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010Ȃ\n\u0010\f\u0010\u000e\u0010ȅ\u000b\u0010\u0003\u0010\u0003\u0010\u0005\u0010ȉ\n\u0010\u0003\u0011\u0005\u0011Ȍ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011ȓ\n\u0011\u0003\u0012\u0005\u0012Ȗ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȝ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0007\u0012Ȥ\n\u0012\f\u0012\u000e\u0012ȧ\u000b\u0012\u0005\u0012ȩ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȯ\n\u0012\u0005\u0012ȱ\n\u0012\u0003\u0013\u0003\u0013\u0005\u0013ȵ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ƚ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ɂ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ɋ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ɏ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɗ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɜ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɤ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɩ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0018\u0005\u0018ɮ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɴ\n\u0018\f\u0018\u000e\u0018ɷ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɾ\n\u0018\f\u0018\u000e\u0018ʁ\u000b\u0018\u0005\u0018ʃ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʉ\n\u0018\u0005\u0018ʋ\n\u0018\u0003\u0019\u0005\u0019ʎ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʡ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʧ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʮ\n\u0019\f\u0019\u000e\u0019ʱ\u000b\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʵ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʼ\n\u0019\f\u0019\u000e\u0019ʿ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˇ\n\u0019\f\u0019\u000e\u0019ˊ\u000b\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˎ\n\u0019\f\u0019\u000e\u0019ˑ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019˖\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˜\n\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˥\n\u001a\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0005\u001bˬ\n\u001b\u0003\u001b\u0003\u001b\u0005\u001b˰\n\u001b\u0005\u001b˲\n\u001b\u0003\u001c\u0003\u001c\u0005\u001c˶\n\u001c\u0003\u001c\u0003\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0005\u001d˽\n\u001d\u0005\u001d˿\n\u001d\u0003\u001d\u0003\u001d\u0005\u001d̃\n\u001d\u0003\u001d\u0005\u001d̆\n\u001d\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001f\u0005\u001f̌\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̔\n\u001f\f\u001f\u000e\u001f̗\u000b\u001f\u0005\u001f̙\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0005\u001f̟\n\u001f\u0005\u001f̡\n\u001f\u0003 \u0005 ̤\n \u0003 \u0003 \u0003 \u0003 \u0007 ̪\n \f \u000e ̭\u000b \u0003 \u0003 \u0003 \u0003 \u0003 \u0007 ̴\n \f \u000e ̷\u000b \u0005 ̹\n \u0003 \u0003 \u0003 \u0003 \u0005 ̿\n \u0005 ́\n \u0003!\u0003!\u0005!ͅ\n!\u0003!\u0003!\u0003!\u0007!͊\n!\f!\u000e!͍\u000b!\u0003!\u0003!\u0003!\u0003!\u0007!͓\n!\f!\u000e!͖\u000b!\u0003!\u0005!͙\n!\u0005!͛\n!\u0003!\u0003!\u0005!͟\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!ͦ\n!\f!\u000e!ͩ\u000b!\u0003!\u0003!\u0005!ͭ\n!\u0005!ͯ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!Ͷ\n!\f!\u000e!\u0379\u000b!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!\u0381\n!\f!\u000e!΄\u000b!\u0003!\u0003!\u0007!Έ\n!\f!\u000e!\u038b\u000b!\u0005!\u038d\n!\u0003\"\u0005\"ΐ\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0005\"Ν\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0007\"Ω\n\"\f\"\u000e\"ά\u000b\"\u0003\"\u0003\"\u0005\"ΰ\n\"\u0003#\u0005#γ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0005#π\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#ό\n#\f#\u000e#Ϗ\u000b#\u0003#\u0003#\u0005#ϓ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#Ϛ\n#\f#\u000e#ϝ\u000b#\u0005#ϟ\n#\u0003#\u0003#\u0003#\u0003#\u0005#ϥ\n#\u0005#ϧ\n#\u0003$\u0003$\u0003%\u0003%\u0005%ϭ\n%\u0003%\u0007%ϰ\n%\f%\u000e%ϳ\u000b%\u0003&\u0006&϶\n&\r&\u000e&Ϸ\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0005&Є\n&\u0003'\u0003'\u0005'Ј\n'\u0003'\u0003'\u0003'\u0005'Ѝ\n'\u0003'\u0003'\u0005'Б\n'\u0003'\u0005'Д\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ц\n'\u0003'\u0003'\u0003'\u0005'Ы\n'\u0003(\u0003(\u0003(\u0005(а\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)и\n)\u0003)\u0003)\u0003)\u0005)н\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ц\n)\u0003)\u0003)\u0003)\u0007)ы\n)\f)\u000e)ю\u000b)\u0003)\u0005)ё\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ѡ\n)\u0003)\u0005)Ѥ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ѭ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0006)ѳ\n)\r)\u000e)Ѵ\u0003)\u0003)\u0005)ѹ\n)\u0003)\u0003)\u0003)\u0005)Ѿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)қ\n)\u0003)\u0003)\u0003)\u0005)Ҡ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ҩ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0007)ұ\n)\f)\u000e)Ҵ\u000b)\u0005)Ҷ\n)\u0003)\u0003)\u0003)\u0003)\u0005)Ҽ\n)\u0003)\u0005)ҿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӆ\n)\u0003)\u0003)\u0003)\u0003)\u0005)ӌ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӓ\n)\u0007)ӕ\n)\f)\u000e)Ә\u000b)\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0007*Ӡ\n*\f*\u000e*ӣ\u000b*\u0003*\u0003*\u0005*ӧ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*ӳ\n*\u0003*\u0003*\u0005*ӷ\n*\u0007*ӹ\n*\f*\u000e*Ӽ\u000b*\u0003*\u0005*ӿ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*Ԇ\n*\u0005*Ԉ\n*\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0005+Ԑ\n+\u0003+\u0003+\u0003,\u0003,\u0003,\u0005,ԗ\n,\u0003,\u0005,Ԛ\n,\u0003-\u0003-\u0005-Ԟ\n-\u0003-\u0003-\u0003-\u0005-ԣ\n-\u0003-\u0003-\u0003-\u0003-\u0007-ԩ\n-\f-\u000e-Ԭ\u000b-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Լ\n-\f-\u000e-Կ\u000b-\u0003-\u0003-\u0003-\u0005-Մ\n-\u0003.\u0003.\u0005.Ո\n.\u0003.\u0003.\u0003.\u0007.Ս\n.\f.\u000e.Ր\u000b.\u0003/\u0003/\u0003/\u0005/Օ\n/\u0003/\u0003/\u0003/\u0003/\u0003/\u0003/\u0005/՝\n/\u00030\u00030\u00030\u00050բ\n0\u00030\u00050ե\n0\u00031\u00031\u00031\u00051ժ\n1\u00032\u00032\u00032\u00032\u00032\u00072ձ\n2\f2\u000e2մ\u000b2\u00032\u00032\u00052ո\n2\u00032\u00032\u00032\u00032\u00032\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ֆ\n3\u00033\u00053։\n3\u00053\u058b\n3\u00034\u00034\u00034\u00054\u0590\n4\u00034\u00034\u00054֔\n4\u00034\u00054֗\n4\u00034\u00034\u00034\u00034\u00034\u00054֞\n4\u00034\u00034\u00034\u00054֣\n4\u00034\u00034\u00034\u00034\u00034\u00074֪\n4\f4\u000e4֭\u000b4\u00054֯\n4\u00034\u00034\u00054ֳ\n4\u00034\u00054ֶ\n4\u00034\u00034\u00034\u00034\u00074ּ\n4\f4\u000e4ֿ\u000b4\u00034\u00054ׂ\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054\u05ca\n4\u00034\u00054\u05cd\n4\u00054\u05cf\n4\u00035\u00035\u00035\u00035\u00035\u00075ז\n5\f5\u000e5י\u000b5\u00036\u00036\u00056ם\n6\u00036\u00036\u00056ס\n6\u00036\u00036\u00056ץ\n6\u00036\u00056ר\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ױ\n7\f7\u000e7״\u000b7\u00037\u00037\u00057\u05f8\n7\u00038\u00038\u00058\u05fc\n8\u00038\u00038\u00038\u00078\u0601\n8\f8\u000e8\u0604\u000b8\u00038\u00038\u00038\u00038\u00078؊\n8\f8\u000e8؍\u000b8\u00038\u00058ؐ\n8\u00058ؒ\n8\u00038\u00038\u00058ؖ\n8\u00038\u00038\u00038\u00038\u00038\u00078\u061d\n8\f8\u000e8ؠ\u000b8\u00038\u00038\u00058ؤ\n8\u00058ئ\n8\u00038\u00038\u00038\u00038\u00038\u00078ح\n8\f8\u000e8ذ\u000b8\u00038\u00038\u00038\u00038\u00038\u00038\u00078ظ\n8\f8\u000e8ػ\u000b8\u00038\u00038\u00078ؿ\n8\f8\u000e8ق\u000b8\u00058ل\n8\u00039\u00039\u00039\u00039\u00039\u00059ً\n9\u0003:\u0005:َ\n:\u0003:\u0003:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003>\u0003>\u0005>ٚ\n>\u0003?\u0003?\u0003@\u0003@\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003R\u0003R\u0003R\u0003R\u0005Rڈ\nR\u0003S\u0003S\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0005Tړ\nT\u0003T\u0004ƂϷ\u0003PU\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦\u0002\u0015\u0005\u0002<<GGTT\u0004\u000211DD\u0004\u0002\u0007\u0007ll\u0003\u0002\u0085\u0086\u0004\u0002\u001f\u001f@@\u0004\u0002$$>>\u0007\u0002\u001b\u001bJJSS||\u007f\u007f\u0004\u0002\t\t\u000e\u000f\u0003\u0002\n\u000b\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0006\u0002OOcceexx\u0004\u0002==\u008d\u008d\u0005\u0002\u001b\u001bJJ\u007f\u007f\u0006\u000268jj\u0098\u0098\u009a\u009b\u0004\u0002\n\fhh\u0004\u0002\u0097\u0097\u009a\u009a\u0003\u0002\u001b\u0096\u0002ޓ\u0002¬\u0003\u0002\u0002\u0002\u0004±\u0003\u0002\u0002\u0002\u0006·\u0003\u0002\u0002\u0002\bÑ\u0003\u0002\u0002\u0002\nó\u0003\u0002\u0002\u0002\fą\u0003\u0002\u0002\u0002\u000eĎ\u0003\u0002\u0002\u0002\u0010Ė\u0003\u0002\u0002\u0002\u0012Ġ\u0003\u0002\u0002\u0002\u0014Ĩ\u0003\u0002\u0002\u0002\u0016Ō\u0003\u0002\u0002\u0002\u0018Ŭ\u0003\u0002\u0002\u0002\u001aƕ\u0003\u0002\u0002\u0002\u001cǚ\u0003\u0002\u0002\u0002\u001eǭ\u0003\u0002\u0002\u0002 ȋ\u0003\u0002\u0002\u0002\"ȕ\u0003\u0002\u0002\u0002$Ȳ\u0003\u0002\u0002\u0002&ȸ\u0003\u0002\u0002\u0002(Ʌ\u0003\u0002\u0002\u0002*ɒ\u0003\u0002\u0002\u0002,ɟ\u0003\u0002\u0002\u0002.ɭ\u0003\u0002\u0002\u00020ʍ\u0003\u0002\u0002\u00022˗\u0003\u0002\u0002\u00024˦\u0003\u0002\u0002\u00026˳\u0003\u0002\u0002\u00028˹\u0003\u0002\u0002\u0002:̇\u0003\u0002\u0002\u0002<̋\u0003\u0002\u0002\u0002>̣\u0003\u0002\u0002\u0002@Ό\u0003\u0002\u0002\u0002BΏ\u0003\u0002\u0002\u0002Dβ\u0003\u0002\u0002\u0002FϨ\u0003\u0002\u0002\u0002HϪ\u0003\u0002\u0002\u0002Jϵ\u0003\u0002\u0002\u0002LЇ\u0003\u0002\u0002\u0002NЯ\u0003\u0002\u0002\u0002Pѽ\u0003\u0002\u0002\u0002Rә\u0003\u0002\u0002\u0002Tԉ\u0003\u0002\u0002\u0002Vԓ\u0003\u0002\u0002\u0002Xԝ\u0003\u0002\u0002\u0002ZՅ\u0003\u0002\u0002\u0002\\Ք\u0003\u0002\u0002\u0002^՞\u0003\u0002\u0002\u0002`թ\u0003\u0002\u0002\u0002bի\u0003\u0002\u0002\u0002d֊\u0003\u0002\u0002\u0002f\u05ce\u0003\u0002\u0002\u0002hא\u0003\u0002\u0002\u0002jק\u0003\u0002\u0002\u0002l\u05f7\u0003\u0002\u0002\u0002nك\u0003\u0002\u0002\u0002pي\u0003\u0002\u0002\u0002rٍ\u0003\u0002\u0002\u0002tّ\u0003\u0002\u0002\u0002vٓ\u0003\u0002\u0002\u0002xٕ\u0003\u0002\u0002\u0002zٙ\u0003\u0002\u0002\u0002|ٛ\u0003\u0002\u0002\u0002~ٝ\u0003\u0002\u0002\u0002\u0080ٟ\u0003\u0002\u0002\u0002\u0082١\u0003\u0002\u0002\u0002\u0084٣\u0003\u0002\u0002\u0002\u0086٥\u0003\u0002\u0002\u0002\u0088٧\u0003\u0002\u0002\u0002\u008a٩\u0003\u0002\u0002\u0002\u008c٫\u0003\u0002\u0002\u0002\u008e٭\u0003\u0002\u0002\u0002\u0090ٯ\u0003\u0002\u0002\u0002\u0092ٱ\u0003\u0002\u0002\u0002\u0094ٳ\u0003\u0002\u0002\u0002\u0096ٵ\u0003\u0002\u0002\u0002\u0098ٷ\u0003\u0002\u0002\u0002\u009aٹ\u0003\u0002\u0002\u0002\u009cٻ\u0003\u0002\u0002\u0002\u009eٽ\u0003\u0002\u0002\u0002 ٿ\u0003\u0002\u0002\u0002¢ڇ\u0003\u0002\u0002\u0002¤ډ\u0003\u0002\u0002\u0002¦ڒ\u0003\u0002\u0002\u0002¨«\u0005\u0006\u0004\u0002©«\u0005\u0004\u0003\u0002ª¨\u0003\u0002\u0002\u0002ª©\u0003\u0002\u0002\u0002«®\u0003\u0002\u0002\u0002¬ª\u0003\u0002\u0002\u0002¬\u00ad\u0003\u0002\u0002\u0002\u00ad¯\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002¯°\u0007\u0002\u0002\u0003°\u0003\u0003\u0002\u0002\u0002±²\u0007\u009f\u0002\u0002²³\b\u0003\u0001\u0002³\u0005\u0003\u0002\u0002\u0002´¶\u0007\u0003\u0002\u0002µ´\u0003\u0002\u0002\u0002¶¹\u0003\u0002\u0002\u0002·µ\u0003\u0002\u0002\u0002·¸\u0003\u0002\u0002\u0002¸º\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002ºÃ\u0005\b\u0005\u0002»½\u0007\u0003\u0002\u0002¼»\u0003\u0002\u0002\u0002½¾\u0003\u0002\u0002\u0002¾¼\u0003\u0002\u0002\u0002¾¿\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002ÀÂ\u0005\b\u0005\u0002Á¼\u0003\u0002\u0002\u0002ÂÅ\u0003\u0002\u0002\u0002ÃÁ\u0003\u0002\u0002\u0002ÃÄ\u0003\u0002\u0002\u0002ÄÉ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÆÈ\u0007\u0003\u0002\u0002ÇÆ\u0003\u0002\u0002\u0002ÈË\u0003\u0002\u0002\u0002ÉÇ\u0003\u0002\u0002\u0002ÉÊ\u0003\u0002\u0002\u0002Ê\u0007\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ÌÏ\u0007I\u0002\u0002ÍÎ\u0007t\u0002\u0002ÎÐ\u0007q\u0002\u0002ÏÍ\u0003\u0002\u0002\u0002ÏÐ\u0003\u0002\u0002\u0002ÐÒ\u0003\u0002\u0002\u0002ÑÌ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002Òñ\u0003\u0002\u0002\u0002Óò\u0005\n\u0006\u0002Ôò\u0005\f\u0007\u0002Õò\u0005\u000e\b\u0002Öò\u0005\u0010\t\u0002×ò\u0005\u0012\n\u0002Øò\u0005\u0014\u000b\u0002Ùò\u0005\u0016\f\u0002Úò\u0005\u0018\r\u0002Ûò\u0005\u001a\u000e\u0002Üò\u0005\u001c\u000f\u0002Ýò\u0005\u001e\u0010\u0002Þò\u0005 \u0011\u0002ßò\u0005\"\u0012\u0002àò\u0005$\u0013\u0002áò\u0005&\u0014\u0002âò\u0005(\u0015\u0002ãò\u0005*\u0016\u0002äò\u0005,\u0017\u0002åò\u0005.\u0018\u0002æò\u00050\u0019\u0002çò\u00052\u001a\u0002èò\u00054\u001b\u0002éò\u00056\u001c\u0002êò\u00058\u001d\u0002ëò\u0005:\u001e\u0002ìò\u0005<\u001f\u0002íò\u0005> \u0002îò\u0005B\"\u0002ïò\u0005D#\u0002ðò\u0005F$\u0002ñÓ\u0003\u0002\u0002\u0002ñÔ\u0003\u0002\u0002\u0002ñÕ\u0003\u0002\u0002\u0002ñÖ\u0003\u0002\u0002\u0002ñ×\u0003\u0002\u0002\u0002ñØ\u0003\u0002\u0002\u0002ñÙ\u0003\u0002\u0002\u0002ñÚ\u0003\u0002\u0002\u0002ñÛ\u0003\u0002\u0002\u0002ñÜ\u0003\u0002\u0002\u0002ñÝ\u0003\u0002\u0002\u0002ñÞ\u0003\u0002\u0002\u0002ñß\u0003\u0002\u0002\u0002ñà\u0003\u0002\u0002\u0002ñá\u0003\u0002\u0002\u0002ñâ\u0003\u0002\u0002\u0002ñã\u0003\u0002\u0002\u0002ñä\u0003\u0002\u0002\u0002ñå\u0003\u0002\u0002\u0002ñæ\u0003\u0002\u0002\u0002ñç\u0003\u0002\u0002\u0002ñè\u0003\u0002\u0002\u0002ñé\u0003\u0002\u0002\u0002ñê\u0003\u0002\u0002\u0002ñë\u0003\u0002\u0002\u0002ñì\u0003\u0002\u0002\u0002ñí\u0003\u0002\u0002\u0002ñî\u0003\u0002\u0002\u0002ñï\u0003\u0002\u0002\u0002ñð\u0003\u0002\u0002\u0002ò\t\u0003\u0002\u0002\u0002óô\u0007 \u0002\u0002ôø\u0007\u0084\u0002\u0002õö\u0005\u0084C\u0002ö÷\u0007\u0004\u0002\u0002÷ù\u0003\u0002\u0002\u0002øõ\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùú\u0003\u0002\u0002\u0002úă\u0005\u008aF\u0002ûü\u0007{\u0002\u0002üý\u0007\u0088\u0002\u0002ýĄ\u0005\u008eH\u0002þĀ\u0007\u001d\u0002\u0002ÿā\u00070\u0002\u0002Āÿ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āĂ\u0003\u0002\u0002\u0002ĂĄ\u0005H%\u0002ăû\u0003\u0002\u0002\u0002ăþ\u0003\u0002\u0002\u0002Ą\u000b\u0003\u0002\u0002\u0002ąČ\u0007!\u0002\u0002Ćč\u0005\u0084C\u0002ćč\u0005\u008cG\u0002Ĉĉ\u0005\u0084C\u0002ĉĊ\u0007\u0004\u0002\u0002Ċċ\u0005\u008cG\u0002ċč\u0003\u0002\u0002\u0002ČĆ\u0003\u0002\u0002\u0002Čć\u0003\u0002\u0002\u0002ČĈ\u0003\u0002\u0002\u0002Čč\u0003\u0002\u0002\u0002č\r\u0003\u0002\u0002\u0002ĎĐ\u0007%\u0002\u0002ďđ\u00079\u0002\u0002Đď\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0003\u0002\u0002\u0002Ēē\u0005P)\u0002ēĔ\u0007#\u0002\u0002Ĕĕ\u0005\u0084C\u0002ĕ\u000f\u0003\u0002\u0002\u0002ĖĘ\u0007(\u0002\u0002ėę\t\u0002\u0002\u0002Ęė\u0003\u0002\u0002\u0002Ęę\u0003\u0002\u0002\u0002ęĞ\u0003\u0002\u0002\u0002ĚĜ\u0007\u0089\u0002\u0002ěĝ\u0005¤S\u0002Ĝě\u0003\u0002\u0002\u0002Ĝĝ\u0003\u0002\u0002\u0002ĝğ\u0003\u0002\u0002\u0002ĞĚ\u0003\u0002\u0002\u0002Ğğ\u0003\u0002\u0002\u0002ğ\u0011\u0003\u0002\u0002\u0002Ġĥ\t\u0003\u0002\u0002ġģ\u0007\u0089\u0002\u0002ĢĤ\u0005¤S\u0002ģĢ\u0003\u0002\u0002\u0002ģĤ\u0003\u0002\u0002\u0002ĤĦ\u0003\u0002\u0002\u0002ĥġ\u0003\u0002\u0002\u0002ĥĦ\u0003\u0002\u0002\u0002Ħ\u0013\u0003\u0002\u0002\u0002ħĩ\u0005Z.\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĴ\u0005n8\u0002īĭ\u0007\u008b\u0002\u0002ĬĮ\u0007\u001f\u0002\u0002ĭĬ\u0003\u0002\u0002\u0002ĭĮ\u0003\u0002\u0002\u0002ĮĲ\u0003\u0002\u0002\u0002įĲ\u0007\\\u0002\u0002İĲ\u0007F\u0002\u0002ıī\u0003\u0002\u0002\u0002ıį\u0003\u0002\u0002\u0002ıİ\u0003\u0002\u0002\u0002Ĳĳ\u0003\u0002\u0002\u0002ĳĵ\u0005n8\u0002Ĵı\u0003\u0002\u0002\u0002ĵĶ\u0003\u0002\u0002\u0002ĶĴ\u0003\u0002\u0002\u0002Ķķ\u0003\u0002\u0002\u0002ķł\u0003\u0002\u0002\u0002ĸĹ\u0007o\u0002\u0002Ĺĺ\u0007*\u0002\u0002ĺĿ\u0005^0\u0002Ļļ\u0007\u0007\u0002\u0002ļľ\u0005^0\u0002ĽĻ\u0003\u0002\u0002\u0002ľŁ\u0003\u0002\u0002\u0002ĿĽ\u0003\u0002\u0002\u0002Ŀŀ\u0003\u0002\u0002\u0002ŀŃ\u0003\u0002\u0002\u0002ŁĿ\u0003\u0002\u0002\u0002łĸ\u0003\u0002\u0002\u0002łŃ\u0003\u0002\u0002\u0002ŃŊ\u0003\u0002\u0002\u0002ńŅ\u0007d\u0002\u0002Ņň\u0005P)\u0002ņŇ\t\u0004\u0002\u0002Ňŉ\u0005P)\u0002ňņ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŋ\u0003\u0002\u0002\u0002Ŋń\u0003\u0002\u0002\u0002Ŋŋ\u0003\u0002\u0002\u0002ŋ\u0015\u0003\u0002\u0002\u0002ŌŎ\u00074\u0002\u0002ōŏ\u0007\u008c\u0002\u0002Ŏō\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏŐ\u0003\u0002\u0002\u0002ŐŔ\u0007V\u0002\u0002őŒ\u0007R\u0002\u0002Œœ\u0007h\u0002\u0002œŕ\u0007H\u0002\u0002Ŕő\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕř\u0003\u0002\u0002\u0002Ŗŗ\u0005\u0084C\u0002ŗŘ\u0007\u0004\u0002\u0002ŘŚ\u0003\u0002\u0002\u0002řŖ\u0003\u0002\u0002\u0002řŚ\u0003\u0002\u0002\u0002Śś\u0003\u0002\u0002\u0002śŜ\u0005\u0096L\u0002Ŝŝ\u0007m\u0002\u0002ŝŞ\u0005\u008aF\u0002Şş\u0007\u0005\u0002\u0002şŤ\u0005V,\u0002Šš\u0007\u0007\u0002\u0002šţ\u0005V,\u0002ŢŠ\u0003\u0002\u0002\u0002ţŦ\u0003\u0002\u0002\u0002ŤŢ\u0003\u0002\u0002\u0002Ťť\u0003\u0002\u0002\u0002ťŧ\u0003\u0002\u0002\u0002ŦŤ\u0003\u0002\u0002\u0002ŧŪ\u0007\u0006\u0002\u0002Ũũ\u0007\u0094\u0002\u0002ũū\u0005P)\u0002ŪŨ\u0003\u0002\u0002\u0002Ūū\u0003\u0002\u0002\u0002ū\u0017\u0003\u0002\u0002\u0002ŬŮ\u00074\u0002\u0002ŭů\t\u0005\u0002\u0002Ůŭ\u0003\u0002\u0002\u0002Ůů\u0003\u0002\u0002\u0002ůŰ\u0003\u0002\u0002\u0002ŰŴ\u0007\u0084\u0002\u0002űŲ\u0007R\u0002\u0002Ųų\u0007h\u0002\u0002ųŵ\u0007H\u0002\u0002Ŵű\u0003\u0002\u0002\u0002Ŵŵ\u0003\u0002\u0002\u0002ŵŹ\u0003\u0002\u0002\u0002Ŷŷ\u0005\u0084C\u0002ŷŸ\u0007\u0004\u0002\u0002Ÿź\u0003\u0002\u0002\u0002ŹŶ\u0003\u0002\u0002\u0002Źź\u0003\u0002\u0002\u0002źŻ\u0003\u0002\u0002\u0002ŻƓ\u0005\u008aF\u0002żŽ\u0007\u0005\u0002\u0002ŽƂ\u0005H%\u0002žſ\u0007\u0007\u0002\u0002ſƁ\u0005H%\u0002ƀž\u0003\u0002\u0002\u0002ƁƄ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002ƃƉ\u0003\u0002\u0002\u0002ƄƂ\u0003\u0002\u0002\u0002ƅƆ\u0007\u0007\u0002\u0002Ɔƈ\u0005X-\u0002Ƈƅ\u0003\u0002\u0002\u0002ƈƋ\u0003\u0002\u0002\u0002ƉƇ\u0003\u0002\u0002\u0002ƉƊ\u0003\u0002\u0002\u0002Ɗƌ\u0003\u0002\u0002\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0007\u0006\u0002\u0002ƍƎ\u0007\u0096\u0002\u0002ƎƐ\u0007\u0097\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƏƐ\u0003\u0002\u0002\u0002ƐƔ\u0003\u0002\u0002\u0002Ƒƒ\u0007#\u0002\u0002ƒƔ\u0005> \u0002Ɠż\u0003\u0002\u0002\u0002ƓƑ\u0003\u0002\u0002\u0002Ɣ\u0019\u0003\u0002\u0002\u0002ƕƗ\u00074\u0002\u0002ƖƘ\t\u0005\u0002\u0002ƗƖ\u0003\u0002\u0002\u0002ƗƘ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƝ\u0007\u008a\u0002\u0002ƚƛ\u0007R\u0002\u0002ƛƜ\u0007h\u0002\u0002Ɯƞ\u0007H\u0002\u0002Ɲƚ\u0003\u0002\u0002\u0002Ɲƞ\u0003\u0002\u0002\u0002ƞƢ\u0003\u0002\u0002\u0002ƟƠ\u0005\u0084C\u0002Ơơ\u0007\u0004\u0002\u0002ơƣ\u0003\u0002\u0002\u0002ƢƟ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002ƤƩ\u0005\u0098M\u0002ƥƪ\u0007'\u0002\u0002Ʀƪ\u0007\u001e\u0002\u0002Ƨƨ\u0007[\u0002\u0002ƨƪ\u0007k\u0002\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƪ\u0003\u0002\u0002\u0002ƪƹ\u0003\u0002\u0002\u0002ƫƺ\u0007=\u0002\u0002Ƭƺ\u0007Z\u0002\u0002ƭƷ\u0007\u008d\u0002\u0002ƮƯ\u0007k\u0002\u0002Ưƴ\u0005\u0090I\u0002ưƱ\u0007\u0007\u0002\u0002ƱƳ\u0005\u0090I\u0002Ʋư\u0003\u0002\u0002\u0002Ƴƶ\u0003\u0002\u0002\u0002ƴƲ\u0003\u0002\u0002\u0002ƴƵ\u0003\u0002\u0002\u0002ƵƸ\u0003\u0002\u0002\u0002ƶƴ\u0003\u0002\u0002\u0002ƷƮ\u0003\u0002\u0002\u0002ƷƸ\u0003\u0002\u0002\u0002Ƹƺ\u0003\u0002\u0002\u0002ƹƫ\u0003\u0002\u0002\u0002ƹƬ\u0003\u0002\u0002\u0002ƹƭ\u0003\u0002\u0002\u0002ƺƻ\u0003\u0002\u0002\u0002ƻƿ\u0007m\u0002\u0002Ƽƽ\u0005\u0084C\u0002ƽƾ\u0007\u0004\u0002\u0002ƾǀ\u0003\u0002\u0002\u0002ƿƼ\u0003\u0002\u0002\u0002ƿǀ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǅ\u0005\u008aF\u0002ǂǃ\u0007K\u0002\u0002ǃǄ\u0007B\u0002\u0002Ǆǆ\u0007\u0080\u0002\u0002ǅǂ\u0003\u0002\u0002\u0002ǅǆ\u0003\u0002\u0002\u0002ǆǉ\u0003\u0002\u0002\u0002Ǉǈ\u0007\u0093\u0002\u0002ǈǊ\u0005P)\u0002ǉǇ\u0003\u0002\u0002\u0002ǉǊ\u0003\u0002\u0002\u0002Ǌǋ\u0003\u0002\u0002\u0002ǋǔ\u0007(\u0002\u0002ǌǑ\u0005B\"\u0002ǍǑ\u00050\u0019\u0002ǎǑ\u0005 \u0011\u0002ǏǑ\u0005> \u0002ǐǌ\u0003\u0002\u0002\u0002ǐǍ\u0003\u0002\u0002\u0002ǐǎ\u0003\u0002\u0002\u0002ǐǏ\u0003\u0002\u0002\u0002Ǒǒ\u0003\u0002\u0002\u0002ǒǓ\u0007\u0003\u0002\u0002ǓǕ\u0003\u0002\u0002\u0002ǔǐ\u0003\u0002\u0002\u0002Ǖǖ\u0003\u0002\u0002\u0002ǖǔ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002Ǘǘ\u0003\u0002\u0002\u0002ǘǙ\u0007D\u0002\u0002Ǚ\u001b\u0003\u0002\u0002\u0002ǚǜ\u00074\u0002\u0002Ǜǝ\t\u0005\u0002\u0002ǜǛ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0003\u0002\u0002\u0002ǞǢ\u0007\u0091\u0002\u0002ǟǠ\u0007R\u0002\u0002Ǡǡ\u0007h\u0002\u0002ǡǣ\u0007H\u0002\u0002Ǣǟ\u0003\u0002\u0002\u0002Ǣǣ\u0003\u0002\u0002\u0002ǣǧ\u0003\u0002\u0002\u0002Ǥǥ\u0005\u0084C\u0002ǥǦ\u0007\u0004\u0002\u0002ǦǨ\u0003\u0002\u0002\u0002ǧǤ\u0003\u0002\u0002\u0002ǧǨ\u0003\u0002\u0002\u0002Ǩǩ\u0003\u0002\u0002\u0002ǩǪ\u0005\u009aN\u0002Ǫǫ\u0007#\u0002\u0002ǫǬ\u0005> \u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǮ\u00074\u0002\u0002Ǯǯ\u0007\u0092\u0002\u0002ǯǳ\u0007\u0084\u0002\u0002ǰǱ\u0007R\u0002\u0002Ǳǲ\u0007h\u0002\u0002ǲǴ\u0007H\u0002\u0002ǳǰ\u0003\u0002\u0002\u0002ǳǴ\u0003\u0002\u0002\u0002ǴǸ\u0003\u0002\u0002\u0002ǵǶ\u0005\u0084C\u0002ǶǷ\u0007\u0004\u0002\u0002Ƿǹ\u0003\u0002\u0002\u0002Ǹǵ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0005\u008aF\u0002ǻǼ\u0007\u008e\u0002\u0002ǼȈ\u0005\u009cO\u0002ǽǾ\u0007\u0005\u0002\u0002Ǿȃ\u0005z>\u0002ǿȀ\u0007\u0007\u0002\u0002ȀȂ\u0005z>\u0002ȁǿ\u0003\u0002\u0002\u0002Ȃȅ\u0003\u0002\u0002\u0002ȃȁ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0003\u0002\u0002\u0002ȅȃ\u0003\u0002\u0002\u0002Ȇȇ\u0007\u0006\u0002\u0002ȇȉ\u0003\u0002\u0002\u0002Ȉǽ\u0003\u0002\u0002\u0002Ȉȉ\u0003\u0002\u0002\u0002ȉ\u001f\u0003\u0002\u0002\u0002ȊȌ\u0005Z.\u0002ȋȊ\u0003\u0002\u0002\u0002ȋȌ\u0003\u0002\u0002\u0002Ȍȍ\u0003\u0002\u0002\u0002ȍȎ\u0007=\u0002\u0002Ȏȏ\u0007M\u0002\u0002ȏȒ\u0005\\/\u0002Ȑȑ\u0007\u0094\u0002\u0002ȑȓ\u0005P)\u0002ȒȐ\u0003\u0002\u0002\u0002Ȓȓ\u0003\u0002\u0002\u0002ȓ!\u0003\u0002\u0002\u0002ȔȖ\u0005Z.\u0002ȕȔ\u0003\u0002\u0002\u0002ȕȖ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0007=\u0002\u0002Șș\u0007M\u0002\u0002șȜ\u0005\\/\u0002Țț\u0007\u0094\u0002\u0002țȝ\u0005P)\u0002ȜȚ\u0003\u0002\u0002\u0002Ȝȝ\u0003\u0002\u0002\u0002ȝȰ\u0003\u0002\u0002\u0002Ȟȟ\u0007o\u0002\u0002ȟȠ\u0007*\u0002\u0002Ƞȥ\u0005^0\u0002ȡȢ\u0007\u0007\u0002\u0002ȢȤ\u0005^0\u0002ȣȡ\u0003\u0002\u0002\u0002Ȥȧ\u0003\u0002\u0002\u0002ȥȣ\u0003\u0002\u0002\u0002ȥȦ\u0003\u0002\u0002\u0002Ȧȩ\u0003\u0002\u0002\u0002ȧȥ\u0003\u0002\u0002\u0002ȨȞ\u0003\u0002\u0002\u0002Ȩȩ\u0003\u0002\u0002\u0002ȩȪ\u0003\u0002\u0002\u0002Ȫȫ\u0007d\u0002\u0002ȫȮ\u0005P)\u0002Ȭȭ\t\u0004\u0002\u0002ȭȯ\u0005P)\u0002ȮȬ\u0003\u0002\u0002\u0002Ȯȯ\u0003\u0002\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002ȰȨ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱ#\u0003\u0002\u0002\u0002Ȳȴ\u0007?\u0002\u0002ȳȵ\u00079\u0002\u0002ȴȳ\u0003\u0002\u0002\u0002ȴȵ\u0003\u0002\u0002\u0002ȵȶ\u0003\u0002\u0002\u0002ȶȷ\u0005\u0084C\u0002ȷ%\u0003\u0002\u0002\u0002ȸȹ\u0007A\u0002\u0002ȹȼ\u0007V\u0002\u0002ȺȻ\u0007R\u0002\u0002ȻȽ\u0007H\u0002\u0002ȼȺ\u0003\u0002\u0002\u0002ȼȽ\u0003\u0002\u0002\u0002ȽɁ\u0003\u0002\u0002\u0002Ⱦȿ\u0005\u0084C\u0002ȿɀ\u0007\u0004\u0002\u0002ɀɂ\u0003\u0002\u0002\u0002ɁȾ\u0003\u0002\u0002\u0002Ɂɂ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002ɃɄ\u0005\u0096L\u0002Ʉ'\u0003\u0002\u0002\u0002ɅɆ\u0007A\u0002\u0002Ɇɉ\u0007\u0084\u0002\u0002ɇɈ\u0007R\u0002\u0002ɈɊ\u0007H\u0002\u0002ɉɇ\u0003\u0002\u0002\u0002ɉɊ\u0003\u0002\u0002\u0002ɊɎ\u0003\u0002\u0002\u0002ɋɌ\u0005\u0084C\u0002Ɍɍ\u0007\u0004\u0002\u0002ɍɏ\u0003\u0002\u0002\u0002Ɏɋ\u0003\u0002\u0002\u0002Ɏɏ\u0003\u0002\u0002\u0002ɏɐ\u0003\u0002\u0002\u0002ɐɑ\u0005\u008aF\u0002ɑ)\u0003\u0002\u0002\u0002ɒɓ\u0007A\u0002\u0002ɓɖ\u0007\u008a\u0002\u0002ɔɕ\u0007R\u0002\u0002ɕɗ\u0007H\u0002\u0002ɖɔ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0003\u0002\u0002\u0002ɘə\u0005\u0084C\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɞ\u0005\u0098M\u0002ɞ+\u0003\u0002\u0002\u0002ɟɠ\u0007A\u0002\u0002ɠɣ\u0007\u0091\u0002\u0002ɡɢ\u0007R\u0002\u0002ɢɤ\u0007H\u0002\u0002ɣɡ\u0003\u0002\u0002\u0002ɣɤ\u0003\u0002\u0002\u0002ɤɨ\u0003\u0002\u0002\u0002ɥɦ\u0005\u0084C\u0002ɦɧ\u0007\u0004\u0002\u0002ɧɩ\u0003\u0002\u0002\u0002ɨɥ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɪ\u0003\u0002\u0002\u0002ɪɫ\u0005\u009aN\u0002ɫ-\u0003\u0002\u0002\u0002ɬɮ\u0005Z.\u0002ɭɬ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮɯ\u0003\u0002\u0002\u0002ɯɵ\u0005n8\u0002ɰɱ\u0005p9\u0002ɱɲ\u0005n8\u0002ɲɴ\u0003\u0002\u0002\u0002ɳɰ\u0003\u0002\u0002\u0002ɴɷ\u0003\u0002\u0002\u0002ɵɳ\u0003\u0002\u0002\u0002ɵɶ\u0003\u0002\u0002\u0002ɶʂ\u0003\u0002\u0002\u0002ɷɵ\u0003\u0002\u0002\u0002ɸɹ\u0007o\u0002\u0002ɹɺ\u0007*\u0002\u0002ɺɿ\u0005^0\u0002ɻɼ\u0007\u0007\u0002\u0002ɼɾ\u0005^0\u0002ɽɻ\u0003\u0002\u0002\u0002ɾʁ\u0003\u0002\u0002\u0002ɿɽ\u0003\u0002\u0002\u0002ɿʀ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʂɸ\u0003\u0002\u0002\u0002ʂʃ\u0003\u0002\u0002\u0002ʃʊ\u0003\u0002\u0002\u0002ʄʅ\u0007d\u0002\u0002ʅʈ\u0005P)\u0002ʆʇ\t\u0004\u0002\u0002ʇʉ\u0005P)\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʋ\u0003\u0002\u0002\u0002ʊʄ\u0003\u0002\u0002\u0002ʊʋ\u0003\u0002\u0002\u0002ʋ/\u0003\u0002\u0002\u0002ʌʎ\u0005Z.\u0002ʍʌ\u0003\u0002\u0002\u0002ʍʎ\u0003\u0002\u0002\u0002ʎʠ\u0003\u0002\u0002\u0002ʏʡ\u0007Z\u0002\u0002ʐʡ\u0007|\u0002\u0002ʑʒ\u0007Z\u0002\u0002ʒʓ\u0007n\u0002\u0002ʓʡ\u0007|\u0002\u0002ʔʕ\u0007Z\u0002\u0002ʕʖ\u0007n\u0002\u0002ʖʡ\u0007\u007f\u0002\u0002ʗʘ\u0007Z\u0002\u0002ʘʙ\u0007n\u0002\u0002ʙʡ\u0007\u001b\u0002\u0002ʚʛ\u0007Z\u0002\u0002ʛʜ\u0007n\u0002\u0002ʜʡ\u0007J\u0002\u0002ʝʞ\u0007Z\u0002\u0002ʞʟ\u0007n\u0002\u0002ʟʡ\u0007S\u0002\u0002ʠʏ\u0003\u0002\u0002\u0002ʠʐ\u0003\u0002\u0002\u0002ʠʑ\u0003\u0002\u0002\u0002ʠʔ\u0003\u0002\u0002\u0002ʠʗ\u0003\u0002\u0002\u0002ʠʚ\u0003\u0002\u0002\u0002ʠʝ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʦ\u0007]\u0002\u0002ʣʤ\u0005\u0084C\u0002ʤʥ\u0007\u0004\u0002\u0002ʥʧ\u0003\u0002\u0002\u0002ʦʣ\u0003\u0002\u0002\u0002ʦʧ\u0003\u0002\u0002\u0002ʧʨ\u0003\u0002\u0002\u0002ʨʴ\u0005\u008aF\u0002ʩʪ\u0007\u0005\u0002\u0002ʪʯ\u0005\u0090I\u0002ʫʬ\u0007\u0007\u0002\u0002ʬʮ\u0005\u0090I\u0002ʭʫ\u0003\u0002\u0002\u0002ʮʱ\u0003\u0002\u0002\u0002ʯʭ\u0003\u0002\u0002\u0002ʯʰ\u0003\u0002\u0002\u0002ʰʲ\u0003\u0002\u0002\u0002ʱʯ\u0003\u0002\u0002\u0002ʲʳ\u0007\u0006\u0002\u0002ʳʵ\u0003\u0002\u0002\u0002ʴʩ\u0003\u0002\u0002\u0002ʴʵ\u0003\u0002\u0002\u0002ʵ˕\u0003\u0002\u0002\u0002ʶʷ\u0007\u0090\u0002\u0002ʷʸ\u0007\u0005\u0002\u0002ʸʽ\u0005P)\u0002ʹʺ\u0007\u0007\u0002\u0002ʺʼ\u0005P)\u0002ʻʹ\u0003\u0002\u0002\u0002ʼʿ\u0003\u0002\u0002\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʽ\u0003\u0002\u0002\u0002ˀˏ\u0007\u0006\u0002\u0002ˁ˂\u0007\u0007\u0002\u0002˂˃\u0007\u0005\u0002\u0002˃ˈ\u0005P)\u0002˄˅\u0007\u0007\u0002\u0002˅ˇ\u0005P)\u0002ˆ˄\u0003\u0002\u0002\u0002ˇˊ\u0003\u0002\u0002\u0002ˈˆ\u0003\u0002\u0002\u0002ˈˉ\u0003\u0002\u0002\u0002ˉˋ\u0003\u0002\u0002\u0002ˊˈ\u0003\u0002\u0002\u0002ˋˌ\u0007\u0006\u0002\u0002ˌˎ\u0003\u0002\u0002\u0002ˍˁ\u0003\u0002\u0002\u0002ˎˑ\u0003\u0002\u0002\u0002ˏˍ\u0003\u0002\u0002\u0002ˏː\u0003\u0002\u0002\u0002ː˖\u0003\u0002\u0002\u0002ˑˏ\u0003\u0002\u0002\u0002˒˖\u0005> \u0002˓˔\u0007:\u0002\u0002˔˖\u0007\u0090\u0002\u0002˕ʶ\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˓\u0003\u0002\u0002\u0002˖1\u0003\u0002\u0002\u0002˗˛\u0007r\u0002\u0002˘˙\u0005\u0084C\u0002˙˚\u0007\u0004\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛˘\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜˝\u0003\u0002\u0002\u0002˝ˤ\u0005\u009eP\u0002˞˟\u0007\b\u0002\u0002˟˥\u0005`1\u0002ˠˡ\u0007\u0005\u0002\u0002ˡˢ\u0005`1\u0002ˢˣ\u0007\u0006\u0002\u0002ˣ˥\u0003\u0002\u0002\u0002ˤ˞\u0003\u0002\u0002\u0002ˤˠ\u0003\u0002\u0002\u0002ˤ˥\u0003\u0002\u0002\u0002˥3\u0003\u0002\u0002\u0002˦˱\u0007y\u0002\u0002˧˲\u0005\u0092J\u0002˨˩\u0005\u0084C\u0002˩˪\u0007\u0004\u0002\u0002˪ˬ\u0003\u0002\u0002\u0002˫˨\u0003\u0002\u0002\u0002˫ˬ\u0003\u0002\u0002\u0002ˬ˯\u0003\u0002\u0002\u0002˭˰\u0005\u008aF\u0002ˮ˰\u0005\u0096L\u0002˯˭\u0003\u0002\u0002\u0002˯ˮ\u0003\u0002\u0002\u0002˰˲\u0003\u0002\u0002\u0002˱˧\u0003\u0002\u0002\u0002˱˫\u0003\u0002\u0002\u0002˱˲\u0003\u0002\u0002\u0002˲5\u0003\u0002\u0002\u0002˳˵\u0007z\u0002\u0002˴˶\u0007\u0081\u0002\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˸\u0005 Q\u0002˸7\u0003\u0002\u0002\u0002˹˾\u0007\u007f\u0002\u0002˺˼\u0007\u0089\u0002\u0002˻˽\u0005¤S\u0002˼˻\u0003\u0002\u0002\u0002˼˽\u0003\u0002\u0002\u0002˽˿\u0003\u0002\u0002\u0002˾˺\u0003\u0002\u0002\u0002˾˿\u0003\u0002\u0002\u0002˿̅\u0003\u0002\u0002\u0002̀̂\u0007\u0088\u0002\u0002́̃\u0007\u0081\u0002\u0002̂́\u0003\u0002\u0002\u0002̂̃\u0003\u0002\u0002\u0002̃̄\u0003\u0002\u0002\u0002̄̆\u0005 Q\u0002̅̀\u0003\u0002\u0002\u0002̅̆\u0003\u0002\u0002\u0002̆9\u0003\u0002\u0002\u0002̇̈\u0007\u0081\u0002\u0002̈̉\u0005 Q\u0002̉;\u0003\u0002\u0002\u0002̊̌\u0005Z.\u0002̋̊\u0003\u0002\u0002\u0002̋̌\u0003\u0002\u0002\u0002̌̍\u0003\u0002\u0002\u0002̘̍\u0005n8\u0002̎̏\u0007o\u0002\u0002̏̐\u0007*\u0002\u0002̐̕\u0005^0\u0002̑̒\u0007\u0007\u0002\u0002̒̔\u0005^0\u0002̓̑\u0003\u0002\u0002\u0002̗̔\u0003\u0002\u0002\u0002̓̕\u0003\u0002\u0002\u0002̖̕\u0003\u0002\u0002\u0002̖̙\u0003\u0002\u0002\u0002̗̕\u0003\u0002\u0002\u0002̘̎\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̠\u0003\u0002\u0002\u0002̛̚\u0007d\u0002\u0002̛̞\u0005P)\u0002̜̝\t\u0004\u0002\u0002̝̟\u0005P)\u0002̞̜\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̡̟\u0003\u0002\u0002\u0002̠̚\u0003\u0002\u0002\u0002̡̠\u0003\u0002\u0002\u0002̡=\u0003\u0002\u0002\u0002̢̤\u0005Z.\u0002̢̣\u0003\u0002\u0002\u0002̣̤\u0003\u0002\u0002\u0002̤̥\u0003\u0002\u0002\u0002̥̫\u0005@!\u0002̧̦\u0005p9\u0002̧̨\u0005@!\u0002̨̪\u0003\u0002\u0002\u0002̩̦\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̸̬\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007o\u0002\u0002̯̰\u0007*\u0002\u0002̵̰\u0005^0\u0002̱̲\u0007\u0007\u0002\u0002̴̲\u0005^0\u0002̳̱\u0003\u0002\u0002\u0002̴̷\u0003\u0002\u0002\u0002̵̳\u0003\u0002\u0002\u0002̵̶\u0003\u0002\u0002\u0002̶̹\u0003\u0002\u0002\u0002̷̵\u0003\u0002\u0002\u0002̸̮\u0003\u0002\u0002\u0002̸̹\u0003\u0002\u0002\u0002̹̀\u0003\u0002\u0002\u0002̺̻\u0007d\u0002\u0002̻̾\u0005P)\u0002̼̽\t\u0004\u0002\u0002̽̿\u0005P)\u0002̼̾\u0003\u0002\u0002\u0002̾̿\u0003\u0002\u0002\u0002̿́\u0003\u0002\u0002\u0002̺̀\u0003\u0002\u0002\u0002̀́\u0003\u0002\u0002\u0002́?\u0003\u0002\u0002\u0002͂̈́\u0007\u0082\u0002\u0002̓ͅ\t\u0006\u0002\u0002̈́̓\u0003\u0002\u0002\u0002̈́ͅ\u0003\u0002\u0002\u0002͆ͅ\u0003\u0002\u0002\u0002͆͋\u0005d3\u0002͇͈\u0007\u0007\u0002\u0002͈͊\u0005d3\u0002͉͇\u0003\u0002\u0002\u0002͍͊\u0003\u0002\u0002\u0002͉͋\u0003\u0002\u0002\u0002͋͌\u0003\u0002\u0002\u0002͚͌\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͎͘\u0007M\u0002\u0002͏͔\u0005f4\u0002͐͑\u0007\u0007\u0002\u0002͓͑\u0005f4\u0002͒͐\u0003\u0002\u0002\u0002͓͖\u0003\u0002\u0002\u0002͔͒\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͙\u0003\u0002\u0002\u0002͖͔\u0003\u0002\u0002\u0002͙͗\u0005h5\u0002͘͏\u0003\u0002\u0002\u0002͗͘\u0003\u0002\u0002\u0002͙͛\u0003\u0002\u0002\u0002͚͎\u0003\u0002\u0002\u0002͚͛\u0003\u0002\u0002\u0002͛͞\u0003\u0002\u0002\u0002͜͝\u0007\u0094\u0002\u0002͟͝\u0005P)\u0002͜͞\u0003\u0002\u0002\u0002͟͞\u0003\u0002\u0002\u0002ͮ͟\u0003\u0002\u0002\u0002͠͡\u0007P\u0002\u0002͢͡\u0007*\u0002\u0002ͧ͢\u0005P)\u0002ͣͤ\u0007\u0007\u0002\u0002ͤͦ\u0005P)\u0002ͥͣ\u0003\u0002\u0002\u0002ͦͩ\u0003\u0002\u0002\u0002ͧͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͬ\u0003\u0002\u0002\u0002ͩͧ\u0003\u0002\u0002\u0002ͪͫ\u0007Q\u0002\u0002ͫͭ\u0005P)\u0002ͬͪ\u0003\u0002\u0002\u0002ͬͭ\u0003\u0002\u0002\u0002ͭͯ\u0003\u0002\u0002\u0002ͮ͠\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯ\u038d\u0003\u0002\u0002\u0002Ͱͱ\u0007\u0090\u0002\u0002ͱͲ\u0007\u0005\u0002\u0002Ͳͷ\u0005P)\u0002ͳʹ\u0007\u0007\u0002\u0002ʹͶ\u0005P)\u0002͵ͳ\u0003\u0002\u0002\u0002Ͷ\u0379\u0003\u0002\u0002\u0002ͷ͵\u0003\u0002\u0002\u0002ͷ\u0378\u0003\u0002\u0002\u0002\u0378ͺ\u0003\u0002\u0002\u0002\u0379ͷ\u0003\u0002\u0002\u0002ͺΉ\u0007\u0006\u0002\u0002ͻͼ\u0007\u0007\u0002\u0002ͼͽ\u0007\u0005\u0002\u0002ͽ\u0382\u0005P)\u0002;Ϳ\u0007\u0007\u0002\u0002Ϳ\u0381\u0005P)\u0002\u0380;\u0003\u0002\u0002\u0002\u0381΄\u0003\u0002\u0002\u0002\u0382\u0380\u0003\u0002\u0002\u0002\u0382\u0383\u0003\u0002\u0002\u0002\u0383΅\u0003\u0002\u0002\u0002΄\u0382\u0003\u0002\u0002\u0002΅Ά\u0007\u0006\u0002\u0002ΆΈ\u0003\u0002\u0002\u0002·ͻ\u0003\u0002\u0002\u0002Έ\u038b\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038d\u0003\u0002\u0002\u0002\u038bΉ\u0003\u0002\u0002\u0002Ό͂\u0003\u0002\u0002\u0002ΌͰ\u0003\u0002\u0002\u0002\u038dA\u0003\u0002\u0002\u0002Ύΐ\u0005Z.\u0002ΏΎ\u0003\u0002\u0002\u0002Ώΐ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΜ\u0007\u008d\u0002\u0002ΒΓ\u0007n\u0002\u0002ΓΝ\u0007\u007f\u0002\u0002ΔΕ\u0007n\u0002\u0002ΕΝ\u0007\u001b\u0002\u0002ΖΗ\u0007n\u0002\u0002ΗΝ\u0007|\u0002\u0002ΘΙ\u0007n\u0002\u0002ΙΝ\u0007J\u0002\u0002ΚΛ\u0007n\u0002\u0002ΛΝ\u0007S\u0002\u0002ΜΒ\u0003\u0002\u0002\u0002ΜΔ\u0003\u0002\u0002\u0002ΜΖ\u0003\u0002\u0002\u0002ΜΘ\u0003\u0002\u0002\u0002ΜΚ\u0003\u0002\u0002\u0002ΜΝ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002ΞΟ\u0005\\/\u0002ΟΠ\u0007\u0083\u0002\u0002ΠΡ\u0005\u0090I\u0002Ρ\u03a2\u0007\b\u0002\u0002\u03a2Ϊ\u0005P)\u0002ΣΤ\u0007\u0007\u0002\u0002ΤΥ\u0005\u0090I\u0002ΥΦ\u0007\b\u0002\u0002ΦΧ\u0005P)\u0002ΧΩ\u0003\u0002\u0002\u0002ΨΣ\u0003\u0002\u0002\u0002Ωά\u0003\u0002\u0002\u0002ΪΨ\u0003\u0002\u0002\u0002ΪΫ\u0003\u0002\u0002\u0002Ϋί\u0003\u0002\u0002\u0002άΪ\u0003\u0002\u0002\u0002έή\u0007\u0094\u0002\u0002ήΰ\u0005P)\u0002ίέ\u0003\u0002\u0002\u0002ίΰ\u0003\u0002\u0002\u0002ΰC\u0003\u0002\u0002\u0002αγ\u0005Z.\u0002βα\u0003\u0002\u0002\u0002βγ\u0003\u0002\u0002\u0002γδ\u0003\u0002\u0002\u0002δο\u0007\u008d\u0002\u0002εζ\u0007n\u0002\u0002ζπ\u0007\u007f\u0002\u0002ηθ\u0007n\u0002\u0002θπ\u0007\u001b\u0002\u0002ικ\u0007n\u0002\u0002κπ\u0007|\u0002\u0002λμ\u0007n\u0002\u0002μπ\u0007J\u0002\u0002νξ\u0007n\u0002\u0002ξπ\u0007S\u0002\u0002οε\u0003\u0002\u0002\u0002οη\u0003\u0002\u0002\u0002οι\u0003\u0002\u0002\u0002ολ\u0003\u0002\u0002\u0002ον\u0003\u0002\u0002\u0002οπ\u0003\u0002\u0002\u0002πρ\u0003\u0002\u0002\u0002ρς\u0005\\/\u0002ςσ\u0007\u0083\u0002\u0002στ\u0005\u0090I\u0002τυ\u0007\b\u0002\u0002υύ\u0005P)\u0002φχ\u0007\u0007\u0002\u0002χψ\u0005\u0090I\u0002ψω\u0007\b\u0002\u0002ωϊ\u0005P)\u0002ϊό\u0003\u0002\u0002\u0002ϋφ\u0003\u0002\u0002\u0002όϏ\u0003\u0002\u0002\u0002ύϋ\u0003\u0002\u0002\u0002ύώ\u0003\u0002\u0002\u0002ώϒ\u0003\u0002\u0002\u0002Ϗύ\u0003\u0002\u0002\u0002ϐϑ\u0007\u0094\u0002\u0002ϑϓ\u0005P)\u0002ϒϐ\u0003\u0002\u0002\u0002ϒϓ\u0003\u0002\u0002\u0002ϓϦ\u0003\u0002\u0002\u0002ϔϕ\u0007o\u0002\u0002ϕϖ\u0007*\u0002\u0002ϖϛ\u0005^0\u0002ϗϘ\u0007\u0007\u0002\u0002ϘϚ\u0005^0\u0002ϙϗ\u0003\u0002\u0002\u0002Ϛϝ\u0003\u0002\u0002\u0002ϛϙ\u0003\u0002\u0002\u0002ϛϜ\u0003\u0002\u0002\u0002Ϝϟ\u0003\u0002\u0002\u0002ϝϛ\u0003\u0002\u0002\u0002Ϟϔ\u0003\u0002\u0002\u0002Ϟϟ\u0003\u0002\u0002\u0002ϟϠ\u0003\u0002\u0002\u0002Ϡϡ\u0007d\u0002\u0002ϡϤ\u0005P)\u0002Ϣϣ\t\u0004\u0002\u0002ϣϥ\u0005P)\u0002ϤϢ\u0003\u0002\u0002\u0002Ϥϥ\u0003\u0002\u0002\u0002ϥϧ\u0003\u0002\u0002\u0002ϦϞ\u0003\u0002\u0002\u0002Ϧϧ\u0003\u0002\u0002\u0002ϧE\u0003\u0002\u0002\u0002Ϩϩ\u0007\u008f\u0002\u0002ϩG\u0003\u0002\u0002\u0002ϪϬ\u0005\u0090I\u0002ϫϭ\u0005J&\u0002Ϭϫ\u0003\u0002\u0002\u0002Ϭϭ\u0003\u0002\u0002\u0002ϭϱ\u0003\u0002\u0002\u0002Ϯϰ\u0005L'\u0002ϯϮ\u0003\u0002\u0002\u0002ϰϳ\u0003\u0002\u0002\u0002ϱϯ\u0003\u0002\u0002\u0002ϱϲ\u0003\u0002\u0002\u0002ϲI\u0003\u0002\u0002\u0002ϳϱ\u0003\u0002\u0002\u0002ϴ϶\u0005\u0080A\u0002ϵϴ\u0003\u0002\u0002\u0002϶Ϸ\u0003\u0002\u0002\u0002Ϸϸ\u0003\u0002\u0002\u0002Ϸϵ\u0003\u0002\u0002\u0002ϸЃ\u0003\u0002\u0002\u0002ϹϺ\u0007\u0005\u0002\u0002Ϻϻ\u0005r:\u0002ϻϼ\u0007\u0006\u0002\u0002ϼЄ\u0003\u0002\u0002\u0002ϽϾ\u0007\u0005\u0002\u0002ϾϿ\u0005r:\u0002ϿЀ\u0007\u0007\u0002\u0002ЀЁ\u0005r:\u0002ЁЂ\u0007\u0006\u0002\u0002ЂЄ\u0003\u0002\u0002\u0002ЃϹ\u0003\u0002\u0002\u0002ЃϽ\u0003\u0002\u0002\u0002ЃЄ\u0003\u0002\u0002\u0002ЄK\u0003\u0002\u0002\u0002ЅІ\u00073\u0002\u0002ІЈ\u0005\u0080A\u0002ЇЅ\u0003\u0002\u0002\u0002ЇЈ\u0003\u0002\u0002\u0002ЈЪ\u0003\u0002\u0002\u0002ЉЊ\u0007s\u0002\u0002ЊЌ\u0007a\u0002\u0002ЋЍ\t\u0007\u0002\u0002ЌЋ\u0003\u0002\u0002\u0002ЌЍ\u0003\u0002\u0002\u0002ЍЎ\u0003\u0002\u0002\u0002ЎА\u0005N(\u0002ЏБ\u0007&\u0002\u0002АЏ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЫ\u0003\u0002\u0002\u0002ВД\u0007h\u0002\u0002ГВ\u0003\u0002\u0002\u0002ГД\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0007j\u0002\u0002ЖЫ\u0005N(\u0002ЗИ\u0007\u008c\u0002\u0002ИЫ\u0005N(\u0002ЙК\u0007.\u0002\u0002КЛ\u0007\u0005\u0002\u0002ЛМ\u0005P)\u0002МН\u0007\u0006\u0002\u0002НЫ\u0003\u0002\u0002\u0002ОХ\u0007:\u0002\u0002ПЦ\u0005r:\u0002РЦ\u0005t;\u0002СТ\u0007\u0005\u0002\u0002ТУ\u0005P)\u0002УФ\u0007\u0006\u0002\u0002ФЦ\u0003\u0002\u0002\u0002ХП\u0003\u0002\u0002\u0002ХР\u0003\u0002\u0002\u0002ХС\u0003\u0002\u0002\u0002ЦЫ\u0003\u0002\u0002\u0002ЧШ\u0007/\u0002\u0002ШЫ\u0005\u0092J\u0002ЩЫ\u0005R*\u0002ЪЉ\u0003\u0002\u0002\u0002ЪГ\u0003\u0002\u0002\u0002ЪЗ\u0003\u0002\u0002\u0002ЪЙ\u0003\u0002\u0002\u0002ЪО\u0003\u0002\u0002\u0002ЪЧ\u0003\u0002\u0002\u0002ЪЩ\u0003\u0002\u0002\u0002ЫM\u0003\u0002\u0002\u0002ЬЭ\u0007m\u0002\u0002ЭЮ\u00072\u0002\u0002Юа\t\b\u0002\u0002ЯЬ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аO\u0003\u0002\u0002\u0002бв\b)\u0001\u0002вѾ\u0005t;\u0002гѾ\u0007\u0099\u0002\u0002де\u0005\u0084C\u0002еж\u0007\u0004\u0002\u0002жи\u0003\u0002\u0002\u0002зд\u0003\u0002\u0002\u0002зи\u0003\u0002\u0002\u0002ий\u0003\u0002\u0002\u0002йк\u0005\u008aF\u0002кл\u0007\u0004\u0002\u0002лн\u0003\u0002\u0002\u0002мз\u0003\u0002\u0002\u0002мн\u0003\u0002\u0002\u0002но\u0003\u0002\u0002\u0002оѾ\u0005\u0090I\u0002пр\u0005v<\u0002рс\u0005P)\u0017сѾ\u0003\u0002\u0002\u0002ту\u0005\u0082B\u0002уѐ\u0007\u0005\u0002\u0002фц\u0007@\u0002\u0002хф\u0003\u0002\u0002\u0002хц\u0003\u0002\u0002\u0002цч\u0003\u0002\u0002\u0002чь\u0005P)\u0002шщ\u0007\u0007\u0002\u0002щы\u0005P)\u0002ъш\u0003\u0002\u0002\u0002ыю\u0003\u0002\u0002\u0002ьъ\u0003\u0002\u0002\u0002ьэ\u0003\u0002\u0002\u0002эё\u0003\u0002\u0002\u0002юь\u0003\u0002\u0002\u0002яё\u0007\t\u0002\u0002ѐх\u0003\u0002\u0002\u0002ѐя\u0003\u0002\u0002\u0002ѐё\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0007\u0006\u0002\u0002ѓѾ\u0003\u0002\u0002\u0002єѕ\u0007\u0005\u0002\u0002ѕі\u0005P)\u0002ії\u0007\u0006\u0002\u0002їѾ\u0003\u0002\u0002\u0002јљ\u0007-\u0002\u0002љњ\u0007\u0005\u0002\u0002њћ\u0005P)\u0002ћќ\u0007#\u0002\u0002ќѝ\u0005J&\u0002ѝў\u0007\u0006\u0002\u0002ўѾ\u0003\u0002\u0002\u0002џѡ\u0007h\u0002\u0002Ѡџ\u0003\u0002\u0002\u0002Ѡѡ\u0003\u0002\u0002\u0002ѡѢ\u0003\u0002\u0002\u0002ѢѤ\u0007H\u0002\u0002ѣѠ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\u0003\u0002\u0002\u0002ѥѦ\u0007\u0005\u0002\u0002Ѧѧ\u0005> \u0002ѧѨ\u0007\u0006\u0002\u0002ѨѾ\u0003\u0002\u0002\u0002ѩѫ\u0007,\u0002\u0002ѪѬ\u0005P)\u0002ѫѪ\u0003\u0002\u0002\u0002ѫѬ\u0003\u0002\u0002\u0002ѬѲ\u0003\u0002\u0002\u0002ѭѮ\u0007\u0093\u0002\u0002Ѯѯ\u0005P)\u0002ѯѰ\u0007\u0087\u0002\u0002Ѱѱ\u0005P)\u0002ѱѳ\u0003\u0002\u0002\u0002Ѳѭ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002ѴѲ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵѸ\u0003\u0002\u0002\u0002Ѷѷ\u0007C\u0002\u0002ѷѹ\u0005P)\u0002ѸѶ\u0003\u0002\u0002\u0002Ѹѹ\u0003\u0002\u0002\u0002ѹѺ\u0003\u0002\u0002\u0002Ѻѻ\u0007D\u0002\u0002ѻѾ\u0003\u0002\u0002\u0002ѼѾ\u0005T+\u0002ѽб\u0003\u0002\u0002\u0002ѽг\u0003\u0002\u0002\u0002ѽм\u0003\u0002\u0002\u0002ѽп\u0003\u0002\u0002\u0002ѽт\u0003\u0002\u0002\u0002ѽє\u0003\u0002\u0002\u0002ѽј\u0003\u0002\u0002\u0002ѽѣ\u0003\u0002\u0002\u0002ѽѩ\u0003\u0002\u0002\u0002ѽѼ\u0003\u0002\u0002\u0002ѾӖ\u0003\u0002\u0002\u0002ѿҀ\f\u0016\u0002\u0002Ҁҁ\u0007\r\u0002\u0002ҁӕ\u0005P)\u0017҂҃\f\u0015\u0002\u0002҃҄\t\t\u0002\u0002҄ӕ\u0005P)\u0016҅҆\f\u0014\u0002\u0002҆҇\t\n\u0002\u0002҇ӕ\u0005P)\u0015҈҉\f\u0013\u0002\u0002҉Ҋ\t\u000b\u0002\u0002Ҋӕ\u0005P)\u0014ҋҌ\f\u0012\u0002\u0002Ҍҍ\t\f\u0002\u0002ҍӕ\u0005P)\u0013Ҏҏ\f\u0011\u0002\u0002ҏҐ\t\r\u0002\u0002Ґӕ\u0005P)\u0012ґҒ\f\u000f\u0002\u0002Ғғ\u0007\"\u0002\u0002ғӕ\u0005P)\u0010Ҕҕ\f\u000e\u0002\u0002ҕҖ\u0007n\u0002\u0002Җӕ\u0005P)\u000fҗҘ\f\u0007\u0002\u0002ҘҚ\u0007^\u0002\u0002ҙқ\u0007h\u0002\u0002Қҙ\u0003\u0002\u0002\u0002Ққ\u0003\u0002\u0002\u0002қҜ\u0003\u0002\u0002\u0002Ҝӕ\u0005P)\bҝҟ\f\u0006\u0002\u0002ҞҠ\u0007h\u0002\u0002ҟҞ\u0003\u0002\u0002\u0002ҟҠ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡҢ\u0007)\u0002\u0002Ңң\u0005P)\u0002ңҤ\u0007\"\u0002\u0002Ҥҥ\u0005P)\u0007ҥӕ\u0003\u0002\u0002\u0002ҦҨ\f\u0010\u0002\u0002ҧҩ\u0007h\u0002\u0002Ҩҧ\u0003\u0002\u0002\u0002Ҩҩ\u0003\u0002\u0002\u0002ҩҪ\u0003\u0002\u0002\u0002ҪҾ\u0007U\u0002\u0002ҫҵ\u0007\u0005\u0002\u0002ҬҶ\u0005> \u0002ҭҲ\u0005P)\u0002Үү\u0007\u0007\u0002\u0002үұ\u0005P)\u0002ҰҮ\u0003\u0002\u0002\u0002ұҴ\u0003\u0002\u0002\u0002ҲҰ\u0003\u0002\u0002\u0002Ҳҳ\u0003\u0002\u0002\u0002ҳҶ\u0003\u0002\u0002\u0002ҴҲ\u0003\u0002\u0002\u0002ҵҬ\u0003\u0002\u0002\u0002ҵҭ\u0003\u0002\u0002\u0002ҵҶ\u0003\u0002\u0002\u0002Ҷҷ\u0003\u0002\u0002\u0002ҷҿ\u0007\u0006\u0002\u0002Ҹҹ\u0005\u0084C\u0002ҹҺ\u0007\u0004\u0002\u0002ҺҼ\u0003\u0002\u0002\u0002һҸ\u0003\u0002\u0002\u0002һҼ\u0003\u0002\u0002\u0002Ҽҽ\u0003\u0002\u0002\u0002ҽҿ\u0005\u008aF\u0002Ҿҫ\u0003\u0002\u0002\u0002Ҿһ\u0003\u0002\u0002\u0002ҿӕ\u0003\u0002\u0002\u0002ӀӁ\f\n\u0002\u0002Ӂӂ\u0007/\u0002\u0002ӂӕ\u0005\u0092J\u0002ӃӅ\f\t\u0002\u0002ӄӆ\u0007h\u0002\u0002Ӆӄ\u0003\u0002\u0002\u0002Ӆӆ\u0003\u0002\u0002\u0002ӆӇ\u0003\u0002\u0002\u0002Ӈӈ\t\u000e\u0002\u0002ӈӋ\u0005P)\u0002Ӊӊ\u0007E\u0002\u0002ӊӌ\u0005P)\u0002ӋӉ\u0003\u0002\u0002\u0002Ӌӌ\u0003\u0002\u0002\u0002ӌӕ\u0003\u0002\u0002\u0002ӍӒ\f\b\u0002\u0002ӎӓ\u0007_\u0002\u0002ӏӓ\u0007i\u0002\u0002Ӑӑ\u0007h\u0002\u0002ӑӓ\u0007j\u0002\u0002Ӓӎ\u0003\u0002\u0002\u0002Ӓӏ\u0003\u0002\u0002\u0002ӒӐ\u0003\u0002\u0002\u0002ӓӕ\u0003\u0002\u0002\u0002Ӕѿ\u0003\u0002\u0002\u0002Ӕ҂\u0003\u0002\u0002\u0002Ӕ҅\u0003\u0002\u0002\u0002Ӕ҈\u0003\u0002\u0002\u0002Ӕҋ\u0003\u0002\u0002\u0002ӔҎ\u0003\u0002\u0002\u0002Ӕґ\u0003\u0002\u0002\u0002ӔҔ\u0003\u0002\u0002\u0002Ӕҗ\u0003\u0002\u0002\u0002Ӕҝ\u0003\u0002\u0002\u0002ӔҦ\u0003\u0002\u0002\u0002ӔӀ\u0003\u0002\u0002\u0002ӔӃ\u0003\u0002\u0002\u0002ӔӍ\u0003\u0002\u0002\u0002ӕӘ\u0003\u0002\u0002\u0002ӖӔ\u0003\u0002\u0002\u0002Ӗӗ\u0003\u0002\u0002\u0002ӗQ\u0003\u0002\u0002\u0002ӘӖ\u0003\u0002\u0002\u0002әӚ\u0007w\u0002\u0002ӚӦ\u0005\u0094K\u0002ӛӜ\u0007\u0005\u0002\u0002Ӝӡ\u0005\u0090I\u0002ӝӞ\u0007\u0007\u0002\u0002ӞӠ\u0005\u0090I\u0002ӟӝ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӟ\u0003\u0002\u0002\u0002ӡӢ\u0003\u0002\u0002\u0002ӢӤ\u0003\u0002\u0002\u0002ӣӡ\u0003\u0002\u0002\u0002Ӥӥ\u0007\u0006\u0002\u0002ӥӧ\u0003\u0002\u0002\u0002Ӧӛ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧӺ\u0003\u0002\u0002\u0002Өө\u0007m\u0002\u0002өӲ\t\u000f\u0002\u0002Ӫӫ\u0007\u0083\u0002\u0002ӫӳ\u0007j\u0002\u0002Ӭӭ\u0007\u0083\u0002\u0002ӭӳ\u0007:\u0002\u0002Ӯӳ\u0007+\u0002\u0002ӯӳ\u0007}\u0002\u0002Ӱӱ\u0007g\u0002\u0002ӱӳ\u0007\u001c\u0002\u0002ӲӪ\u0003\u0002\u0002\u0002ӲӬ\u0003\u0002\u0002\u0002ӲӮ\u0003\u0002\u0002\u0002Ӳӯ\u0003\u0002\u0002\u0002ӲӰ\u0003\u0002\u0002\u0002ӳӷ\u0003\u0002\u0002\u0002Ӵӵ\u0007e\u0002\u0002ӵӷ\u0005\u0080A\u0002ӶӨ\u0003\u0002\u0002\u0002ӶӴ\u0003\u0002\u0002\u0002ӷӹ\u0003\u0002\u0002\u0002ӸӶ\u0003\u0002\u0002\u0002ӹӼ\u0003\u0002\u0002\u0002ӺӸ\u0003\u0002\u0002\u0002Ӻӻ\u0003\u0002\u0002\u0002ӻԇ\u0003\u0002\u0002\u0002ӼӺ\u0003\u0002\u0002\u0002ӽӿ\u0007h\u0002\u0002Ӿӽ\u0003\u0002\u0002\u0002Ӿӿ\u0003\u0002\u0002\u0002ӿԀ\u0003\u0002\u0002\u0002Ԁԅ\u0007;\u0002\u0002ԁԂ\u0007X\u0002\u0002ԂԆ\u0007<\u0002\u0002ԃԄ\u0007X\u0002\u0002ԄԆ\u0007T\u0002\u0002ԅԁ\u0003\u0002\u0002\u0002ԅԃ\u0003\u0002\u0002\u0002ԅԆ\u0003\u0002\u0002\u0002ԆԈ\u0003\u0002\u0002\u0002ԇӾ\u0003\u0002\u0002\u0002ԇԈ\u0003\u0002\u0002\u0002ԈS\u0003\u0002\u0002\u0002ԉԊ\u0007u\u0002\u0002Ԋԏ\u0007\u0005\u0002\u0002ԋԐ\u0007S\u0002\u0002Ԍԍ\t\u0010\u0002\u0002ԍԎ\u0007\u0007\u0002\u0002ԎԐ\u0005x=\u0002ԏԋ\u0003\u0002\u0002\u0002ԏԌ\u0003\u0002\u0002\u0002Ԑԑ\u0003\u0002\u0002\u0002ԑԒ\u0007\u0006\u0002\u0002ԒU\u0003\u0002\u0002\u0002ԓԖ\u0005\u0090I\u0002Ԕԕ\u0007/\u0002\u0002ԕԗ\u0005\u0092J\u0002ԖԔ\u0003\u0002\u0002\u0002Ԗԗ\u0003\u0002\u0002\u0002ԗԙ\u0003\u0002\u0002\u0002ԘԚ\t\u0007\u0002\u0002ԙԘ\u0003\u0002\u0002\u0002ԙԚ\u0003\u0002\u0002\u0002ԚW\u0003\u0002\u0002\u0002ԛԜ\u00073\u0002\u0002ԜԞ\u0005\u0080A\u0002ԝԛ\u0003\u0002\u0002\u0002ԝԞ\u0003\u0002\u0002\u0002ԞՃ\u0003\u0002\u0002\u0002ԟԠ\u0007s\u0002\u0002Ԡԣ\u0007a\u0002\u0002ԡԣ\u0007\u008c\u0002\u0002Ԣԟ\u0003\u0002\u0002\u0002Ԣԡ\u0003\u0002\u0002\u0002ԣԤ\u0003\u0002\u0002\u0002Ԥԥ\u0007\u0005\u0002\u0002ԥԪ\u0005V,\u0002Ԧԧ\u0007\u0007\u0002\u0002ԧԩ\u0005V,\u0002ԨԦ\u0003\u0002\u0002\u0002ԩԬ\u0003\u0002\u0002\u0002ԪԨ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԭ\u0003\u0002\u0002\u0002ԬԪ\u0003\u0002\u0002\u0002ԭԮ\u0007\u0006\u0002\u0002Ԯԯ\u0005N(\u0002ԯՄ\u0003\u0002\u0002\u0002\u0530Ա\u0007.\u0002\u0002ԱԲ\u0007\u0005\u0002\u0002ԲԳ\u0005P)\u0002ԳԴ\u0007\u0006\u0002\u0002ԴՄ\u0003\u0002\u0002\u0002ԵԶ\u0007L\u0002\u0002ԶԷ\u0007a\u0002\u0002ԷԸ\u0007\u0005\u0002\u0002ԸԽ\u0005\u0090I\u0002ԹԺ\u0007\u0007\u0002\u0002ԺԼ\u0005\u0090I\u0002ԻԹ\u0003\u0002\u0002\u0002ԼԿ\u0003\u0002\u0002\u0002ԽԻ\u0003\u0002\u0002\u0002ԽԾ\u0003\u0002\u0002\u0002ԾՀ\u0003\u0002\u0002\u0002ԿԽ\u0003\u0002\u0002\u0002ՀՁ\u0007\u0006\u0002\u0002ՁՂ\u0005R*\u0002ՂՄ\u0003\u0002\u0002\u0002ՃԢ\u0003\u0002\u0002\u0002Ճ\u0530\u0003\u0002\u0002\u0002ՃԵ\u0003\u0002\u0002\u0002ՄY\u0003\u0002\u0002\u0002ՅՇ\u0007\u0095\u0002\u0002ՆՈ\u0007v\u0002\u0002ՇՆ\u0003\u0002\u0002\u0002ՇՈ\u0003\u0002\u0002\u0002ՈՉ\u0003\u0002\u0002\u0002ՉՎ\u0005b2\u0002ՊՋ\u0007\u0007\u0002\u0002ՋՍ\u0005b2\u0002ՌՊ\u0003\u0002\u0002\u0002ՍՐ\u0003\u0002\u0002\u0002ՎՌ\u0003\u0002\u0002\u0002ՎՏ\u0003\u0002\u0002\u0002Տ[\u0003\u0002\u0002\u0002ՐՎ\u0003\u0002\u0002\u0002ՑՒ\u0005\u0084C\u0002ՒՓ\u0007\u0004\u0002\u0002ՓՕ\u0003\u0002\u0002\u0002ՔՑ\u0003\u0002\u0002\u0002ՔՕ\u0003\u0002\u0002\u0002ՕՖ\u0003\u0002\u0002\u0002Ֆ՜\u0005\u008aF\u0002\u0557\u0558\u0007W\u0002\u0002\u0558ՙ\u0007*\u0002\u0002ՙ՝\u0005\u0096L\u0002՚՛\u0007h\u0002\u0002՛՝\u0007W\u0002\u0002՜\u0557\u0003\u0002\u0002\u0002՜՚\u0003\u0002\u0002\u0002՜՝\u0003\u0002\u0002\u0002՝]\u0003\u0002\u0002\u0002՞ա\u0005P)\u0002՟ՠ\u0007/\u0002\u0002ՠբ\u0005\u0092J\u0002ա՟\u0003\u0002\u0002\u0002աբ\u0003\u0002\u0002\u0002բդ\u0003\u0002\u0002\u0002գե\t\u0007\u0002\u0002դգ\u0003\u0002\u0002\u0002դե\u0003\u0002\u0002\u0002ե_\u0003\u0002\u0002\u0002զժ\u0005r:\u0002էժ\u0005\u0080A\u0002ըժ\u0007\u009a\u0002\u0002թզ\u0003\u0002\u0002\u0002թէ\u0003\u0002\u0002\u0002թը\u0003\u0002\u0002\u0002ժa\u0003\u0002\u0002\u0002իշ\u0005\u008aF\u0002լխ\u0007\u0005\u0002\u0002խղ\u0005\u0090I\u0002ծկ\u0007\u0007\u0002\u0002կձ\u0005\u0090I\u0002հծ\u0003\u0002\u0002\u0002ձմ\u0003\u0002\u0002\u0002ղհ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճյ\u0003\u0002\u0002\u0002մղ\u0003\u0002\u0002\u0002յն\u0007\u0006\u0002\u0002նո\u0003\u0002\u0002\u0002շլ\u0003\u0002\u0002\u0002շո\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չպ\u0007#\u0002\u0002պջ\u0007\u0005\u0002\u0002ջռ\u0005> \u0002ռս\u0007\u0006\u0002\u0002սc\u0003\u0002\u0002\u0002վ\u058b\u0007\t\u0002\u0002տր\u0005\u008aF\u0002րց\u0007\u0004\u0002\u0002ցւ\u0007\t\u0002\u0002ւ\u058b\u0003\u0002\u0002\u0002փֈ\u0005P)\u0002քֆ\u0007#\u0002\u0002օք\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0003\u0002\u0002\u0002և։\u0005|?\u0002ֈօ\u0003\u0002\u0002\u0002ֈ։\u0003\u0002\u0002\u0002։\u058b\u0003\u0002\u0002\u0002֊վ\u0003\u0002\u0002\u0002֊տ\u0003\u0002\u0002\u0002֊փ\u0003\u0002\u0002\u0002\u058be\u0003\u0002\u0002\u0002\u058c֍\u0005\u0086D\u0002֍֎\u0007\u0004\u0002\u0002֎\u0590\u0003\u0002\u0002\u0002֏\u058c\u0003\u0002\u0002\u0002֏\u0590\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֖\u0005\u008aF\u0002֒֔\u0007#\u0002\u0002֓֒\u0003\u0002\u0002\u0002֓֔\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֕֗\u0005¢R\u0002֖֓\u0003\u0002\u0002\u0002֖֗\u0003\u0002\u0002\u0002֗֝\u0003\u0002\u0002\u0002֘֙\u0007W\u0002\u0002֚֙\u0007*\u0002\u0002֚֞\u0005\u0096L\u0002֛֜\u0007h\u0002\u0002֜֞\u0007W\u0002\u0002֝֘\u0003\u0002\u0002\u0002֛֝\u0003\u0002\u0002\u0002֝֞\u0003\u0002\u0002\u0002֞\u05cf\u0003\u0002\u0002\u0002֟֠\u0005\u0086D\u0002֠֡\u0007\u0004\u0002\u0002֣֡\u0003\u0002\u0002\u0002֢֟\u0003\u0002\u0002\u0002֢֣\u0003\u0002\u0002\u0002֣֤\u0003\u0002\u0002\u0002֤֥\u0005\u0088E\u0002֥֮\u0007\u0005\u0002\u0002֦֫\u0005P)\u0002֧֨\u0007\u0007\u0002\u0002֪֨\u0005P)\u0002֧֩\u0003\u0002\u0002\u0002֪֭\u0003\u0002\u0002\u0002֫֩\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֬֯\u0003\u0002\u0002\u0002֭֫\u0003\u0002\u0002\u0002֦֮\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ְ֯\u0003\u0002\u0002\u0002ְֵ\u0007\u0006\u0002\u0002ֱֳ\u0007#\u0002\u0002ֱֲ\u0003\u0002\u0002\u0002ֲֳ\u0003\u0002\u0002\u0002ֳִ\u0003\u0002\u0002\u0002ִֶ\u0005¢R\u0002ֲֵ\u0003\u0002\u0002\u0002ֵֶ\u0003\u0002\u0002\u0002ֶ\u05cf\u0003\u0002\u0002\u0002ַׁ\u0007\u0005\u0002\u0002ָֽ\u0005f4\u0002ֹֺ\u0007\u0007\u0002\u0002ֺּ\u0005f4\u0002ֹֻ\u0003\u0002\u0002\u0002ּֿ\u0003\u0002\u0002\u0002ֻֽ\u0003\u0002\u0002\u0002ֽ־\u0003\u0002\u0002\u0002־ׂ\u0003\u0002\u0002\u0002ֽֿ\u0003\u0002\u0002\u0002׀ׂ\u0005h5\u0002ָׁ\u0003\u0002\u0002\u0002ׁ׀\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׄ\u0007\u0006\u0002\u0002ׄ\u05cf\u0003\u0002\u0002\u0002ׅ׆\u0007\u0005\u0002\u0002׆ׇ\u0005> \u0002ׇ\u05cc\u0007\u0006\u0002\u0002\u05c8\u05ca\u0007#\u0002\u0002\u05c9\u05c8\u0003\u0002\u0002\u0002\u05c9\u05ca\u0003\u0002\u0002\u0002\u05ca\u05cb\u0003\u0002\u0002\u0002\u05cb\u05cd\u0005¢R\u0002\u05cc\u05c9\u0003\u0002\u0002\u0002\u05cc\u05cd\u0003\u0002\u0002\u0002\u05cd\u05cf\u0003\u0002\u0002\u0002\u05ce֏\u0003\u0002\u0002\u0002\u05ce֢\u0003\u0002\u0002\u0002\u05ceַ\u0003\u0002\u0002\u0002\u05ceׅ\u0003\u0002\u0002\u0002\u05cfg\u0003\u0002\u0002\u0002אח\u0005f4\u0002בג\u0005j6\u0002גד\u0005f4\u0002דה\u0005l7\u0002הז\u0003\u0002\u0002\u0002וב\u0003\u0002\u0002\u0002זי\u0003\u0002\u0002\u0002חו\u0003\u0002\u0002\u0002חט\u0003\u0002\u0002\u0002טi\u0003\u0002\u0002\u0002יח\u0003\u0002\u0002\u0002ךר\u0007\u0007\u0002\u0002כם\u0007f\u0002\u0002לכ\u0003\u0002\u0002\u0002לם\u0003\u0002\u0002\u0002םפ\u0003\u0002\u0002\u0002מנ\u0007b\u0002\u0002ןס\u0007p\u0002\u0002נן\u0003\u0002\u0002\u0002נס\u0003\u0002\u0002\u0002סץ\u0003\u0002\u0002\u0002עץ\u0007Y\u0002\u0002ףץ\u00075\u0002\u0002פמ\u0003\u0002\u0002\u0002פע\u0003\u0002\u0002\u0002פף\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0003\u0002\u0002\u0002צר\u0007`\u0002\u0002קך\u0003\u0002\u0002\u0002קל\u0003\u0002\u0002\u0002רk\u0003\u0002\u0002\u0002שת\u0007m\u0002\u0002ת\u05f8\u0005P)\u0002\u05eb\u05ec\u0007\u008e\u0002\u0002\u05ec\u05ed\u0007\u0005\u0002\u0002\u05edײ\u0005\u0090I\u0002\u05eeׯ\u0007\u0007\u0002\u0002ׯױ\u0005\u0090I\u0002װ\u05ee\u0003\u0002\u0002\u0002ױ״\u0003\u0002\u0002\u0002ײװ\u0003\u0002\u0002\u0002ײ׳\u0003\u0002\u0002\u0002׳\u05f5\u0003\u0002\u0002\u0002״ײ\u0003\u0002\u0002\u0002\u05f5\u05f6\u0007\u0006\u0002\u0002\u05f6\u05f8\u0003\u0002\u0002\u0002\u05f7ש\u0003\u0002\u0002\u0002\u05f7\u05eb\u0003\u0002\u0002\u0002\u05f7\u05f8\u0003\u0002\u0002\u0002\u05f8m\u0003\u0002\u0002\u0002\u05f9\u05fb\u0007\u0082\u0002\u0002\u05fa\u05fc\t\u0006\u0002\u0002\u05fb\u05fa\u0003\u0002\u0002\u0002\u05fb\u05fc\u0003\u0002\u0002\u0002\u05fc\u05fd\u0003\u0002\u0002\u0002\u05fd\u0602\u0005d3\u0002\u05fe\u05ff\u0007\u0007\u0002\u0002\u05ff\u0601\u0005d3\u0002\u0600\u05fe\u0003\u0002\u0002\u0002\u0601\u0604\u0003\u0002\u0002\u0002\u0602\u0600\u0003\u0002\u0002\u0002\u0602\u0603\u0003\u0002\u0002\u0002\u0603ؑ\u0003\u0002\u0002\u0002\u0604\u0602\u0003\u0002\u0002\u0002\u0605؏\u0007M\u0002\u0002؆؋\u0005f4\u0002؇؈\u0007\u0007\u0002\u0002؈؊\u0005f4\u0002؉؇\u0003\u0002\u0002\u0002؊؍\u0003\u0002\u0002\u0002؋؉\u0003\u0002\u0002\u0002؋،\u0003\u0002\u0002\u0002،ؐ\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؎ؐ\u0005h5\u0002؏؆\u0003\u0002\u0002\u0002؏؎\u0003\u0002\u0002\u0002ؐؒ\u0003\u0002\u0002\u0002ؑ\u0605\u0003\u0002\u0002\u0002ؑؒ\u0003\u0002\u0002\u0002ؒؕ\u0003\u0002\u0002\u0002ؓؔ\u0007\u0094\u0002\u0002ؔؖ\u0005P)\u0002ؕؓ\u0003\u0002\u0002\u0002ؕؖ\u0003\u0002\u0002\u0002ؖإ\u0003\u0002\u0002\u0002ؘؗ\u0007P\u0002\u0002ؘؙ\u0007*\u0002\u0002ؙ؞\u0005P)\u0002ؚ؛\u0007\u0007\u0002\u0002؛\u061d\u0005P)\u0002\u061cؚ\u0003\u0002\u0002\u0002\u061dؠ\u0003\u0002\u0002\u0002؞\u061c\u0003\u0002\u0002\u0002؞؟\u0003\u0002\u0002\u0002؟أ\u0003\u0002\u0002\u0002ؠ؞\u0003\u0002\u0002\u0002ءآ\u0007Q\u0002\u0002آؤ\u0005P)\u0002أء\u0003\u0002\u0002\u0002أؤ\u0003\u0002\u0002\u0002ؤئ\u0003\u0002\u0002\u0002إؗ\u0003\u0002\u0002\u0002إئ\u0003\u0002\u0002\u0002ئل\u0003\u0002\u0002\u0002اب\u0007\u0090\u0002\u0002بة\u0007\u0005\u0002\u0002ةخ\u0005P)\u0002تث\u0007\u0007\u0002\u0002ثح\u0005P)\u0002جت\u0003\u0002\u0002\u0002حذ\u0003\u0002\u0002\u0002خج\u0003\u0002\u0002\u0002خد\u0003\u0002\u0002\u0002در\u0003\u0002\u0002\u0002ذخ\u0003\u0002\u0002\u0002رـ\u0007\u0006\u0002\u0002زس\u0007\u0007\u0002\u0002سش\u0007\u0005\u0002\u0002شع\u0005P)\u0002صض\u0007\u0007\u0002\u0002ضظ\u0005P)\u0002طص\u0003\u0002\u0002\u0002ظػ\u0003\u0002\u0002\u0002عط\u0003\u0002\u0002\u0002عغ\u0003\u0002\u0002\u0002غؼ\u0003\u0002\u0002\u0002ػع\u0003\u0002\u0002\u0002ؼؽ\u0007\u0006\u0002\u0002ؽؿ\u0003\u0002\u0002\u0002ؾز\u0003\u0002\u0002\u0002ؿق\u0003\u0002\u0002\u0002ـؾ\u0003\u0002\u0002\u0002ـف\u0003\u0002\u0002\u0002فل\u0003\u0002\u0002\u0002قـ\u0003\u0002\u0002\u0002ك\u05f9\u0003\u0002\u0002\u0002كا\u0003\u0002\u0002\u0002لo\u0003\u0002\u0002\u0002مً\u0007\u008b\u0002\u0002نه\u0007\u008b\u0002\u0002هً\u0007\u001f\u0002\u0002وً\u0007\\\u0002\u0002ىً\u0007F\u0002\u0002يم\u0003\u0002\u0002\u0002ين\u0003\u0002\u0002\u0002يو\u0003\u0002\u0002\u0002يى\u0003\u0002\u0002\u0002ًq\u0003\u0002\u0002\u0002ٌَ\t\n\u0002\u0002ٌٍ\u0003\u0002\u0002\u0002ٍَ\u0003\u0002\u0002\u0002َُ\u0003\u0002\u0002\u0002ُِ\u0007\u0098\u0002\u0002ِs\u0003\u0002\u0002\u0002ّْ\t\u0011\u0002\u0002ْu\u0003\u0002\u0002\u0002ٓٔ\t\u0012\u0002\u0002ٔw\u0003\u0002\u0002\u0002ٕٖ\u0007\u009a\u0002\u0002ٖy\u0003\u0002\u0002\u0002ٗٚ\u0005P)\u0002٘ٚ\u0005H%\u0002ٙٗ\u0003\u0002\u0002\u0002ٙ٘\u0003\u0002\u0002\u0002ٚ{\u0003\u0002\u0002\u0002ٜٛ\t\u0013\u0002\u0002ٜ}\u0003\u0002\u0002\u0002ٝٞ\t\u0014\u0002\u0002ٞ\u007f\u0003\u0002\u0002\u0002ٟ٠\u0005¦T\u0002٠\u0081\u0003\u0002\u0002\u0002١٢\u0005¦T\u0002٢\u0083\u0003\u0002\u0002\u0002٣٤\u0005¦T\u0002٤\u0085\u0003\u0002\u0002\u0002٥٦\u0005¦T\u0002٦\u0087\u0003\u0002\u0002\u0002٧٨\u0005¦T\u0002٨\u0089\u0003\u0002\u0002\u0002٩٪\u0005¦T\u0002٪\u008b\u0003\u0002\u0002\u0002٫٬\u0005¦T\u0002٬\u008d\u0003\u0002\u0002\u0002٭ٮ\u0005¦T\u0002ٮ\u008f\u0003\u0002\u0002\u0002ٯٰ\u0005¦T\u0002ٰ\u0091\u0003\u0002\u0002\u0002ٱٲ\u0005¦T\u0002ٲ\u0093\u0003\u0002\u0002\u0002ٳٴ\u0005¦T\u0002ٴ\u0095\u0003\u0002\u0002\u0002ٵٶ\u0005¦T\u0002ٶ\u0097\u0003\u0002\u0002\u0002ٷٸ\u0005¦T\u0002ٸ\u0099\u0003\u0002\u0002\u0002ٹٺ\u0005¦T\u0002ٺ\u009b\u0003\u0002\u0002\u0002ٻټ\u0005¦T\u0002ټ\u009d\u0003\u0002\u0002\u0002ٽپ\u0005¦T\u0002پ\u009f\u0003\u0002\u0002\u0002ٿڀ\u0005¦T\u0002ڀ¡\u0003\u0002\u0002\u0002ځڈ\u0007\u0097\u0002\u0002ڂڈ\u0007\u009a\u0002\u0002ڃڄ\u0007\u0005\u0002\u0002ڄڅ\u0005¢R\u0002څچ\u0007\u0006\u0002\u0002چڈ\u0003\u0002\u0002\u0002ڇځ\u0003\u0002\u0002\u0002ڇڂ\u0003\u0002\u0002\u0002ڇڃ\u0003\u0002\u0002\u0002ڈ£\u0003\u0002\u0002\u0002ډڊ\u0005¦T\u0002ڊ¥\u0003\u0002\u0002\u0002ڋړ\u0007\u0097\u0002\u0002ڌړ\u0005~@\u0002ڍړ\u0007\u009a\u0002\u0002ڎڏ\u0007\u0005\u0002\u0002ڏڐ\u0005¦T\u0002ڐڑ\u0007\u0006\u0002\u0002ڑړ\u0003\u0002\u0002\u0002ڒڋ\u0003\u0002\u0002\u0002ڒڌ\u0003\u0002\u0002\u0002ڒڍ\u0003\u0002\u0002\u0002ڒڎ\u0003\u0002\u0002\u0002ړ§\u0003\u0002\u0002\u0002ïª¬·¾ÃÉÏÑñøĀăČĐĘĜĞģĥĨĭıĶĿłňŊŎŔřŤŪŮŴŹƂƉƏƓƗƝƢƩƴƷƹƿǅǉǐǖǜǢǧǳǸȃȈȋȒȕȜȥȨȮȰȴȼɁɉɎɖɛɣɨɭɵɿʂʈʊʍʠʦʯʴʽˈˏ˕˛ˤ˫˯˱˵˼˾̵̸̘̞̠̣̫͔͚̂̅̋̾̀̈́͋ͧͬͮ̕͘͞ͷ\u0382ΉΌΏΜΪίβούϒϛϞϤϦϬϱϷЃЇЌАГХЪЯзмхьѐѠѣѫѴѸѽҚҟҨҲҵһҾӅӋӒӔӖӡӦӲӶӺӾԅԇԏԖԙԝԢԪԽՃՇՎՔ՜ադթղշօֈ֊֏ֲֵֽׁ֖֢֮֓֝֫\u05c9\u05cc\u05ceחלנפקײ\u05f7\u05fb\u0602؋؏ؑؕ؞أإخعـكيٍٙڇڒ".toCharArray());
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];

        for(i = 0; i < _ATN.getNumberOfDecisions(); ++i) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }

    }

    private static String[] makeRuleNames() {
        return new String[]{"parse", "error", "sql_stmt_list", "sql_stmt", "alter_table_stmt", "analyze_stmt", "attach_stmt", "begin_stmt", "commit_stmt", "compound_select_stmt", "create_index_stmt", "create_table_stmt", "create_trigger_stmt", "create_view_stmt", "create_virtual_table_stmt", "delete_stmt", "delete_stmt_limited", "detach_stmt", "drop_index_stmt", "drop_table_stmt", "drop_trigger_stmt", "drop_view_stmt", "factored_select_stmt", "insert_stmt", "pragma_stmt", "reindex_stmt", "release_stmt", "rollback_stmt", "savepoint_stmt", "simple_select_stmt", "select_stmt", "select_or_values", "update_stmt", "update_stmt_limited", "vacuum_stmt", "column_def", "type_name", "column_constraint", "conflict_clause", "expr", "foreign_key_clause", "raise_function", "indexed_column", "table_constraint", "with_clause", "qualified_table_name", "ordering_term", "pragma_value", "common_table_expression", "result_column", "table_or_subquery", "join_clause", "join_operator", "join_constraint", "select_core", "compound_operator", "signed_number", "literal_value", "unary_operator", "error_message", "module_argument", "column_alias", "keyword", "name", "function_name", "database_name", "schema_name", "table_function_name", "table_name", "table_or_index_name", "new_table_name", "column_name", "collation_name", "foreign_table", "index_name", "trigger_name", "view_name", "module_name", "pragma_name", "savepoint_name", "table_alias", "transaction_name", "any_name"};
    }

    private static String[] makeLiteralNames() {
        return new String[]{null, "';'", "'.'", "'('", "')'", "','", "'='", "'*'", "'+'", "'-'", "'~'", "'||'", "'/'", "'%'", "'<<'", "'>>'", "'&'", "'|'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<>'"};
    }

    private static String[] makeSymbolicNames() {
        return new String[]{null, "SCOL", "DOT", "OPEN_PAR", "CLOSE_PAR", "COMMA", "ASSIGN", "STAR", "PLUS", "MINUS", "TILDE", "PIPE2", "DIV", "MOD", "LT2", "GT2", "AMP", "PIPE", "LT", "LT_EQ", "GT", "GT_EQ", "EQ", "NOT_EQ1", "NOT_EQ2", "K_ABORT", "K_ACTION", "K_ADD", "K_AFTER", "K_ALL", "K_ALTER", "K_ANALYZE", "K_AND", "K_AS", "K_ASC", "K_ATTACH", "K_AUTOINCREMENT", "K_BEFORE", "K_BEGIN", "K_BETWEEN", "K_BY", "K_CASCADE", "K_CASE", "K_CAST", "K_CHECK", "K_COLLATE", "K_COLUMN", "K_COMMIT", "K_CONFLICT", "K_CONSTRAINT", "K_CREATE", "K_CROSS", "K_CURRENT_DATE", "K_CURRENT_TIME", "K_CURRENT_TIMESTAMP", "K_DATABASE", "K_DEFAULT", "K_DEFERRABLE", "K_DEFERRED", "K_DELETE", "K_DESC", "K_DETACH", "K_DISTINCT", "K_DROP", "K_EACH", "K_ELSE", "K_END", "K_ESCAPE", "K_EXCEPT", "K_EXCLUSIVE", "K_EXISTS", "K_EXPLAIN", "K_FAIL", "K_FOR", "K_FOREIGN", "K_FROM", "K_FULL", "K_GLOB", "K_GROUP", "K_HAVING", "K_IF", "K_IGNORE", "K_IMMEDIATE", "K_IN", "K_INDEX", "K_INDEXED", "K_INITIALLY", "K_INNER", "K_INSERT", "K_INSTEAD", "K_INTERSECT", "K_INTO", "K_IS", "K_ISNULL", "K_JOIN", "K_KEY", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", "K_NATURAL", "K_NO", "K_NOT", "K_NOTNULL", "K_NULL", "K_OF", "K_OFFSET", "K_ON", "K_OR", "K_ORDER", "K_OUTER", "K_PLAN", "K_PRAGMA", "K_PRIMARY", "K_QUERY", "K_RAISE", "K_RECURSIVE", "K_REFERENCES", "K_REGEXP", "K_REINDEX", "K_RELEASE", "K_RENAME", "K_REPLACE", "K_RESTRICT", "K_RIGHT", "K_ROLLBACK", "K_ROW", "K_SAVEPOINT", "K_SELECT", "K_SET", "K_TABLE", "K_TEMP", "K_TEMPORARY", "K_THEN", "K_TO", "K_TRANSACTION", "K_TRIGGER", "K_UNION", "K_UNIQUE", "K_UPDATE", "K_USING", "K_VACUUM", "K_VALUES", "K_VIEW", "K_VIRTUAL", "K_WHEN", "K_WHERE", "K_WITH", "K_WITHOUT", "IDENTIFIER", "NUMERIC_LITERAL", "BIND_PARAMETER", "STRING_LITERAL", "BLOB_LITERAL", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT", "SPACES", "UNEXPECTED_CHAR"};
    }

    /** @deprecated */
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    public String getGrammarFileName() {
        return "SQLite.g4";
    }

    public String[] getRuleNames() {
        return ruleNames;
    }

    public String getSerializedATN() {
        return "\u0003悋Ꜫ脳맭䅼㯧瞆奤\u0003\u009fڕ\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e\t\u001e\u0004\u001f\t\u001f\u0004 \t \u0004!\t!\u0004\"\t\"\u0004#\t#\u0004$\t$\u0004%\t%\u0004&\t&\u0004'\t'\u0004(\t(\u0004)\t)\u0004*\t*\u0004+\t+\u0004,\t,\u0004-\t-\u0004.\t.\u0004/\t/\u00040\t0\u00041\t1\u00042\t2\u00043\t3\u00044\t4\u00045\t5\u00046\t6\u00047\t7\u00048\t8\u00049\t9\u0004:\t:\u0004;\t;\u0004<\t<\u0004=\t=\u0004>\t>\u0004?\t?\u0004@\t@\u0004A\tA\u0004B\tB\u0004C\tC\u0004D\tD\u0004E\tE\u0004F\tF\u0004G\tG\u0004H\tH\u0004I\tI\u0004J\tJ\u0004K\tK\u0004L\tL\u0004M\tM\u0004N\tN\u0004O\tO\u0004P\tP\u0004Q\tQ\u0004R\tR\u0004S\tS\u0004T\tT\u0003\u0002\u0003\u0002\u0007\u0002«\n\u0002\f\u0002\u000e\u0002®\u000b\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0007\u0004¶\n\u0004\f\u0004\u000e\u0004¹\u000b\u0004\u0003\u0004\u0003\u0004\u0006\u0004½\n\u0004\r\u0004\u000e\u0004¾\u0003\u0004\u0007\u0004Â\n\u0004\f\u0004\u000e\u0004Å\u000b\u0004\u0003\u0004\u0007\u0004È\n\u0004\f\u0004\u000e\u0004Ë\u000b\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005Ð\n\u0005\u0005\u0005Ò\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005ò\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ù\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006ā\n\u0006\u0003\u0006\u0005\u0006Ą\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007č\n\u0007\u0003\b\u0003\b\u0005\bđ\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0005\tę\n\t\u0003\t\u0003\t\u0005\tĝ\n\t\u0005\tğ\n\t\u0003\n\u0003\n\u0003\n\u0005\nĤ\n\n\u0005\nĦ\n\n\u0003\u000b\u0005\u000bĩ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bĮ\n\u000b\u0003\u000b\u0003\u000b\u0005\u000bĲ\n\u000b\u0003\u000b\u0006\u000bĵ\n\u000b\r\u000b\u000e\u000bĶ\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0007\u000bľ\n\u000b\f\u000b\u000e\u000bŁ\u000b\u000b\u0005\u000bŃ\n\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000bŉ\n\u000b\u0005\u000bŋ\n\u000b\u0003\f\u0003\f\u0005\fŏ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fŕ\n\f\u0003\f\u0003\f\u0003\f\u0005\fŚ\n\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0007\fţ\n\f\f\f\u000e\fŦ\u000b\f\u0003\f\u0003\f\u0003\f\u0005\fū\n\f\u0003\r\u0003\r\u0005\rů\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rŵ\n\r\u0003\r\u0003\r\u0003\r\u0005\rź\n\r\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0007\rƁ\n\r\f\r\u000e\rƄ\u000b\r\u0003\r\u0003\r\u0007\rƈ\n\r\f\r\u000e\rƋ\u000b\r\u0003\r\u0003\r\u0003\r\u0005\rƐ\n\r\u0003\r\u0003\r\u0005\rƔ\n\r\u0003\u000e\u0003\u000e\u0005\u000eƘ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƞ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƣ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eƪ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0007\u000eƳ\n\u000e\f\u000e\u000e\u000eƶ\u000b\u000e\u0005\u000eƸ\n\u000e\u0005\u000eƺ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǀ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǆ\n\u000e\u0003\u000e\u0003\u000e\u0005\u000eǊ\n\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0005\u000eǑ\n\u000e\u0003\u000e\u0003\u000e\u0006\u000eǕ\n\u000e\r\u000e\u000e\u000eǖ\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0005\u000fǝ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǣ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fǨ\n\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010Ǵ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0005\u0010ǹ\n\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0010\u0007\u0010Ȃ\n\u0010\f\u0010\u000e\u0010ȅ\u000b\u0010\u0003\u0010\u0003\u0010\u0005\u0010ȉ\n\u0010\u0003\u0011\u0005\u0011Ȍ\n\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0003\u0011\u0005\u0011ȓ\n\u0011\u0003\u0012\u0005\u0012Ȗ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȝ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0007\u0012Ȥ\n\u0012\f\u0012\u000e\u0012ȧ\u000b\u0012\u0005\u0012ȩ\n\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0005\u0012ȯ\n\u0012\u0005\u0012ȱ\n\u0012\u0003\u0013\u0003\u0013\u0005\u0013ȵ\n\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014Ƚ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0005\u0014ɂ\n\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015Ɋ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015ɏ\n\u0015\u0003\u0015\u0003\u0015\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɗ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016ɜ\n\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɤ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0017\u0005\u0017ɩ\n\u0017\u0003\u0017\u0003\u0017\u0003\u0018\u0005\u0018ɮ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɴ\n\u0018\f\u0018\u000e\u0018ɷ\u000b\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0007\u0018ɾ\n\u0018\f\u0018\u000e\u0018ʁ\u000b\u0018\u0005\u0018ʃ\n\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0003\u0018\u0005\u0018ʉ\n\u0018\u0005\u0018ʋ\n\u0018\u0003\u0019\u0005\u0019ʎ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʡ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʧ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʮ\n\u0019\f\u0019\u000e\u0019ʱ\u000b\u0019\u0003\u0019\u0003\u0019\u0005\u0019ʵ\n\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ʼ\n\u0019\f\u0019\u000e\u0019ʿ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˇ\n\u0019\f\u0019\u000e\u0019ˊ\u000b\u0019\u0003\u0019\u0003\u0019\u0007\u0019ˎ\n\u0019\f\u0019\u000e\u0019ˑ\u000b\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0005\u0019˖\n\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˜\n\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a˥\n\u001a\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0003\u001b\u0005\u001bˬ\n\u001b\u0003\u001b\u0003\u001b\u0005\u001b˰\n\u001b\u0005\u001b˲\n\u001b\u0003\u001c\u0003\u001c\u0005\u001c˶\n\u001c\u0003\u001c\u0003\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0005\u001d˽\n\u001d\u0005\u001d˿\n\u001d\u0003\u001d\u0003\u001d\u0005\u001d̃\n\u001d\u0003\u001d\u0005\u001d̆\n\u001d\u0003\u001e\u0003\u001e\u0003\u001e\u0003\u001f\u0005\u001f̌\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0007\u001f̔\n\u001f\f\u001f\u000e\u001f̗\u000b\u001f\u0005\u001f̙\n\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0003\u001f\u0005\u001f̟\n\u001f\u0005\u001f̡\n\u001f\u0003 \u0005 ̤\n \u0003 \u0003 \u0003 \u0003 \u0007 ̪\n \f \u000e ̭\u000b \u0003 \u0003 \u0003 \u0003 \u0003 \u0007 ̴\n \f \u000e ̷\u000b \u0005 ̹\n \u0003 \u0003 \u0003 \u0003 \u0005 ̿\n \u0005 ́\n \u0003!\u0003!\u0005!ͅ\n!\u0003!\u0003!\u0003!\u0007!͊\n!\f!\u000e!͍\u000b!\u0003!\u0003!\u0003!\u0003!\u0007!͓\n!\f!\u000e!͖\u000b!\u0003!\u0005!͙\n!\u0005!͛\n!\u0003!\u0003!\u0005!͟\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!ͦ\n!\f!\u000e!ͩ\u000b!\u0003!\u0003!\u0005!ͭ\n!\u0005!ͯ\n!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!Ͷ\n!\f!\u000e!\u0379\u000b!\u0003!\u0003!\u0003!\u0003!\u0003!\u0003!\u0007!\u0381\n!\f!\u000e!΄\u000b!\u0003!\u0003!\u0007!Έ\n!\f!\u000e!\u038b\u000b!\u0005!\u038d\n!\u0003\"\u0005\"ΐ\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0005\"Ν\n\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0003\"\u0007\"Ω\n\"\f\"\u000e\"ά\u000b\"\u0003\"\u0003\"\u0005\"ΰ\n\"\u0003#\u0005#γ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0005#π\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#ό\n#\f#\u000e#Ϗ\u000b#\u0003#\u0003#\u0005#ϓ\n#\u0003#\u0003#\u0003#\u0003#\u0003#\u0007#Ϛ\n#\f#\u000e#ϝ\u000b#\u0005#ϟ\n#\u0003#\u0003#\u0003#\u0003#\u0005#ϥ\n#\u0005#ϧ\n#\u0003$\u0003$\u0003%\u0003%\u0005%ϭ\n%\u0003%\u0007%ϰ\n%\f%\u000e%ϳ\u000b%\u0003&\u0006&϶\n&\r&\u000e&Ϸ\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0003&\u0005&Є\n&\u0003'\u0003'\u0005'Ј\n'\u0003'\u0003'\u0003'\u0005'Ѝ\n'\u0003'\u0003'\u0005'Б\n'\u0003'\u0005'Д\n'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0003'\u0005'Ц\n'\u0003'\u0003'\u0003'\u0005'Ы\n'\u0003(\u0003(\u0003(\u0005(а\n(\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)и\n)\u0003)\u0003)\u0003)\u0005)н\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ц\n)\u0003)\u0003)\u0003)\u0007)ы\n)\f)\u000e)ю\u000b)\u0003)\u0005)ё\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ѡ\n)\u0003)\u0005)Ѥ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)Ѭ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0006)ѳ\n)\r)\u000e)Ѵ\u0003)\u0003)\u0005)ѹ\n)\u0003)\u0003)\u0003)\u0005)Ѿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)қ\n)\u0003)\u0003)\u0003)\u0005)Ҡ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ҩ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0003)\u0007)ұ\n)\f)\u000e)Ҵ\u000b)\u0005)Ҷ\n)\u0003)\u0003)\u0003)\u0003)\u0005)Ҽ\n)\u0003)\u0005)ҿ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӆ\n)\u0003)\u0003)\u0003)\u0003)\u0005)ӌ\n)\u0003)\u0003)\u0003)\u0003)\u0003)\u0005)ӓ\n)\u0007)ӕ\n)\f)\u000e)Ә\u000b)\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0007*Ӡ\n*\f*\u000e*ӣ\u000b*\u0003*\u0003*\u0005*ӧ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*ӳ\n*\u0003*\u0003*\u0005*ӷ\n*\u0007*ӹ\n*\f*\u000e*Ӽ\u000b*\u0003*\u0005*ӿ\n*\u0003*\u0003*\u0003*\u0003*\u0003*\u0005*Ԇ\n*\u0005*Ԉ\n*\u0003+\u0003+\u0003+\u0003+\u0003+\u0003+\u0005+Ԑ\n+\u0003+\u0003+\u0003,\u0003,\u0003,\u0005,ԗ\n,\u0003,\u0005,Ԛ\n,\u0003-\u0003-\u0005-Ԟ\n-\u0003-\u0003-\u0003-\u0005-ԣ\n-\u0003-\u0003-\u0003-\u0003-\u0007-ԩ\n-\f-\u000e-Ԭ\u000b-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0003-\u0007-Լ\n-\f-\u000e-Կ\u000b-\u0003-\u0003-\u0003-\u0005-Մ\n-\u0003.\u0003.\u0005.Ո\n.\u0003.\u0003.\u0003.\u0007.Ս\n.\f.\u000e.Ր\u000b.\u0003/\u0003/\u0003/\u0005/Օ\n/\u0003/\u0003/\u0003/\u0003/\u0003/\u0003/\u0005/՝\n/\u00030\u00030\u00030\u00050բ\n0\u00030\u00050ե\n0\u00031\u00031\u00031\u00051ժ\n1\u00032\u00032\u00032\u00032\u00032\u00072ձ\n2\f2\u000e2մ\u000b2\u00032\u00032\u00052ո\n2\u00032\u00032\u00032\u00032\u00032\u00033\u00033\u00033\u00033\u00033\u00033\u00033\u00053ֆ\n3\u00033\u00053։\n3\u00053\u058b\n3\u00034\u00034\u00034\u00054\u0590\n4\u00034\u00034\u00054֔\n4\u00034\u00054֗\n4\u00034\u00034\u00034\u00034\u00034\u00054֞\n4\u00034\u00034\u00034\u00054֣\n4\u00034\u00034\u00034\u00034\u00034\u00074֪\n4\f4\u000e4֭\u000b4\u00054֯\n4\u00034\u00034\u00054ֳ\n4\u00034\u00054ֶ\n4\u00034\u00034\u00034\u00034\u00074ּ\n4\f4\u000e4ֿ\u000b4\u00034\u00054ׂ\n4\u00034\u00034\u00034\u00034\u00034\u00034\u00054\u05ca\n4\u00034\u00054\u05cd\n4\u00054\u05cf\n4\u00035\u00035\u00035\u00035\u00035\u00075ז\n5\f5\u000e5י\u000b5\u00036\u00036\u00056ם\n6\u00036\u00036\u00056ס\n6\u00036\u00036\u00056ץ\n6\u00036\u00056ר\n6\u00037\u00037\u00037\u00037\u00037\u00037\u00037\u00077ױ\n7\f7\u000e7״\u000b7\u00037\u00037\u00057\u05f8\n7\u00038\u00038\u00058\u05fc\n8\u00038\u00038\u00038\u00078\u0601\n8\f8\u000e8\u0604\u000b8\u00038\u00038\u00038\u00038\u00078؊\n8\f8\u000e8؍\u000b8\u00038\u00058ؐ\n8\u00058ؒ\n8\u00038\u00038\u00058ؖ\n8\u00038\u00038\u00038\u00038\u00038\u00078\u061d\n8\f8\u000e8ؠ\u000b8\u00038\u00038\u00058ؤ\n8\u00058ئ\n8\u00038\u00038\u00038\u00038\u00038\u00078ح\n8\f8\u000e8ذ\u000b8\u00038\u00038\u00038\u00038\u00038\u00038\u00078ظ\n8\f8\u000e8ػ\u000b8\u00038\u00038\u00078ؿ\n8\f8\u000e8ق\u000b8\u00058ل\n8\u00039\u00039\u00039\u00039\u00039\u00059ً\n9\u0003:\u0005:َ\n:\u0003:\u0003:\u0003;\u0003;\u0003<\u0003<\u0003=\u0003=\u0003>\u0003>\u0005>ٚ\n>\u0003?\u0003?\u0003@\u0003@\u0003A\u0003A\u0003B\u0003B\u0003C\u0003C\u0003D\u0003D\u0003E\u0003E\u0003F\u0003F\u0003G\u0003G\u0003H\u0003H\u0003I\u0003I\u0003J\u0003J\u0003K\u0003K\u0003L\u0003L\u0003M\u0003M\u0003N\u0003N\u0003O\u0003O\u0003P\u0003P\u0003Q\u0003Q\u0003R\u0003R\u0003R\u0003R\u0003R\u0003R\u0005Rڈ\nR\u0003S\u0003S\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0003T\u0005Tړ\nT\u0003T\u0004ƂϷ\u0003PU\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e ¢¤¦\u0002\u0015\u0005\u0002<<GGTT\u0004\u000211DD\u0004\u0002\u0007\u0007ll\u0003\u0002\u0085\u0086\u0004\u0002\u001f\u001f@@\u0004\u0002$$>>\u0007\u0002\u001b\u001bJJSS||\u007f\u007f\u0004\u0002\t\t\u000e\u000f\u0003\u0002\n\u000b\u0003\u0002\u0010\u0013\u0003\u0002\u0014\u0017\u0004\u0002\b\b\u0018\u001a\u0006\u0002OOcceexx\u0004\u0002==\u008d\u008d\u0005\u0002\u001b\u001bJJ\u007f\u007f\u0006\u000268jj\u0098\u0098\u009a\u009b\u0004\u0002\n\fhh\u0004\u0002\u0097\u0097\u009a\u009a\u0003\u0002\u001b\u0096\u0002ޓ\u0002¬\u0003\u0002\u0002\u0002\u0004±\u0003\u0002\u0002\u0002\u0006·\u0003\u0002\u0002\u0002\bÑ\u0003\u0002\u0002\u0002\nó\u0003\u0002\u0002\u0002\fą\u0003\u0002\u0002\u0002\u000eĎ\u0003\u0002\u0002\u0002\u0010Ė\u0003\u0002\u0002\u0002\u0012Ġ\u0003\u0002\u0002\u0002\u0014Ĩ\u0003\u0002\u0002\u0002\u0016Ō\u0003\u0002\u0002\u0002\u0018Ŭ\u0003\u0002\u0002\u0002\u001aƕ\u0003\u0002\u0002\u0002\u001cǚ\u0003\u0002\u0002\u0002\u001eǭ\u0003\u0002\u0002\u0002 ȋ\u0003\u0002\u0002\u0002\"ȕ\u0003\u0002\u0002\u0002$Ȳ\u0003\u0002\u0002\u0002&ȸ\u0003\u0002\u0002\u0002(Ʌ\u0003\u0002\u0002\u0002*ɒ\u0003\u0002\u0002\u0002,ɟ\u0003\u0002\u0002\u0002.ɭ\u0003\u0002\u0002\u00020ʍ\u0003\u0002\u0002\u00022˗\u0003\u0002\u0002\u00024˦\u0003\u0002\u0002\u00026˳\u0003\u0002\u0002\u00028˹\u0003\u0002\u0002\u0002:̇\u0003\u0002\u0002\u0002<̋\u0003\u0002\u0002\u0002>̣\u0003\u0002\u0002\u0002@Ό\u0003\u0002\u0002\u0002BΏ\u0003\u0002\u0002\u0002Dβ\u0003\u0002\u0002\u0002FϨ\u0003\u0002\u0002\u0002HϪ\u0003\u0002\u0002\u0002Jϵ\u0003\u0002\u0002\u0002LЇ\u0003\u0002\u0002\u0002NЯ\u0003\u0002\u0002\u0002Pѽ\u0003\u0002\u0002\u0002Rә\u0003\u0002\u0002\u0002Tԉ\u0003\u0002\u0002\u0002Vԓ\u0003\u0002\u0002\u0002Xԝ\u0003\u0002\u0002\u0002ZՅ\u0003\u0002\u0002\u0002\\Ք\u0003\u0002\u0002\u0002^՞\u0003\u0002\u0002\u0002`թ\u0003\u0002\u0002\u0002bի\u0003\u0002\u0002\u0002d֊\u0003\u0002\u0002\u0002f\u05ce\u0003\u0002\u0002\u0002hא\u0003\u0002\u0002\u0002jק\u0003\u0002\u0002\u0002l\u05f7\u0003\u0002\u0002\u0002nك\u0003\u0002\u0002\u0002pي\u0003\u0002\u0002\u0002rٍ\u0003\u0002\u0002\u0002tّ\u0003\u0002\u0002\u0002vٓ\u0003\u0002\u0002\u0002xٕ\u0003\u0002\u0002\u0002zٙ\u0003\u0002\u0002\u0002|ٛ\u0003\u0002\u0002\u0002~ٝ\u0003\u0002\u0002\u0002\u0080ٟ\u0003\u0002\u0002\u0002\u0082١\u0003\u0002\u0002\u0002\u0084٣\u0003\u0002\u0002\u0002\u0086٥\u0003\u0002\u0002\u0002\u0088٧\u0003\u0002\u0002\u0002\u008a٩\u0003\u0002\u0002\u0002\u008c٫\u0003\u0002\u0002\u0002\u008e٭\u0003\u0002\u0002\u0002\u0090ٯ\u0003\u0002\u0002\u0002\u0092ٱ\u0003\u0002\u0002\u0002\u0094ٳ\u0003\u0002\u0002\u0002\u0096ٵ\u0003\u0002\u0002\u0002\u0098ٷ\u0003\u0002\u0002\u0002\u009aٹ\u0003\u0002\u0002\u0002\u009cٻ\u0003\u0002\u0002\u0002\u009eٽ\u0003\u0002\u0002\u0002 ٿ\u0003\u0002\u0002\u0002¢ڇ\u0003\u0002\u0002\u0002¤ډ\u0003\u0002\u0002\u0002¦ڒ\u0003\u0002\u0002\u0002¨«\u0005\u0006\u0004\u0002©«\u0005\u0004\u0003\u0002ª¨\u0003\u0002\u0002\u0002ª©\u0003\u0002\u0002\u0002«®\u0003\u0002\u0002\u0002¬ª\u0003\u0002\u0002\u0002¬\u00ad\u0003\u0002\u0002\u0002\u00ad¯\u0003\u0002\u0002\u0002®¬\u0003\u0002\u0002\u0002¯°\u0007\u0002\u0002\u0003°\u0003\u0003\u0002\u0002\u0002±²\u0007\u009f\u0002\u0002²³\b\u0003\u0001\u0002³\u0005\u0003\u0002\u0002\u0002´¶\u0007\u0003\u0002\u0002µ´\u0003\u0002\u0002\u0002¶¹\u0003\u0002\u0002\u0002·µ\u0003\u0002\u0002\u0002·¸\u0003\u0002\u0002\u0002¸º\u0003\u0002\u0002\u0002¹·\u0003\u0002\u0002\u0002ºÃ\u0005\b\u0005\u0002»½\u0007\u0003\u0002\u0002¼»\u0003\u0002\u0002\u0002½¾\u0003\u0002\u0002\u0002¾¼\u0003\u0002\u0002\u0002¾¿\u0003\u0002\u0002\u0002¿À\u0003\u0002\u0002\u0002ÀÂ\u0005\b\u0005\u0002Á¼\u0003\u0002\u0002\u0002ÂÅ\u0003\u0002\u0002\u0002ÃÁ\u0003\u0002\u0002\u0002ÃÄ\u0003\u0002\u0002\u0002ÄÉ\u0003\u0002\u0002\u0002ÅÃ\u0003\u0002\u0002\u0002ÆÈ\u0007\u0003\u0002\u0002ÇÆ\u0003\u0002\u0002\u0002ÈË\u0003\u0002\u0002\u0002ÉÇ\u0003\u0002\u0002\u0002ÉÊ\u0003\u0002\u0002\u0002Ê\u0007\u0003\u0002\u0002\u0002ËÉ\u0003\u0002\u0002\u0002ÌÏ\u0007I\u0002\u0002ÍÎ\u0007t\u0002\u0002ÎÐ\u0007q\u0002\u0002ÏÍ\u0003\u0002\u0002\u0002ÏÐ\u0003\u0002\u0002\u0002ÐÒ\u0003\u0002\u0002\u0002ÑÌ\u0003\u0002\u0002\u0002ÑÒ\u0003\u0002\u0002\u0002Òñ\u0003\u0002\u0002\u0002Óò\u0005\n\u0006\u0002Ôò\u0005\f\u0007\u0002Õò\u0005\u000e\b\u0002Öò\u0005\u0010\t\u0002×ò\u0005\u0012\n\u0002Øò\u0005\u0014\u000b\u0002Ùò\u0005\u0016\f\u0002Úò\u0005\u0018\r\u0002Ûò\u0005\u001a\u000e\u0002Üò\u0005\u001c\u000f\u0002Ýò\u0005\u001e\u0010\u0002Þò\u0005 \u0011\u0002ßò\u0005\"\u0012\u0002àò\u0005$\u0013\u0002áò\u0005&\u0014\u0002âò\u0005(\u0015\u0002ãò\u0005*\u0016\u0002äò\u0005,\u0017\u0002åò\u0005.\u0018\u0002æò\u00050\u0019\u0002çò\u00052\u001a\u0002èò\u00054\u001b\u0002éò\u00056\u001c\u0002êò\u00058\u001d\u0002ëò\u0005:\u001e\u0002ìò\u0005<\u001f\u0002íò\u0005> \u0002îò\u0005B\"\u0002ïò\u0005D#\u0002ðò\u0005F$\u0002ñÓ\u0003\u0002\u0002\u0002ñÔ\u0003\u0002\u0002\u0002ñÕ\u0003\u0002\u0002\u0002ñÖ\u0003\u0002\u0002\u0002ñ×\u0003\u0002\u0002\u0002ñØ\u0003\u0002\u0002\u0002ñÙ\u0003\u0002\u0002\u0002ñÚ\u0003\u0002\u0002\u0002ñÛ\u0003\u0002\u0002\u0002ñÜ\u0003\u0002\u0002\u0002ñÝ\u0003\u0002\u0002\u0002ñÞ\u0003\u0002\u0002\u0002ñß\u0003\u0002\u0002\u0002ñà\u0003\u0002\u0002\u0002ñá\u0003\u0002\u0002\u0002ñâ\u0003\u0002\u0002\u0002ñã\u0003\u0002\u0002\u0002ñä\u0003\u0002\u0002\u0002ñå\u0003\u0002\u0002\u0002ñæ\u0003\u0002\u0002\u0002ñç\u0003\u0002\u0002\u0002ñè\u0003\u0002\u0002\u0002ñé\u0003\u0002\u0002\u0002ñê\u0003\u0002\u0002\u0002ñë\u0003\u0002\u0002\u0002ñì\u0003\u0002\u0002\u0002ñí\u0003\u0002\u0002\u0002ñî\u0003\u0002\u0002\u0002ñï\u0003\u0002\u0002\u0002ñð\u0003\u0002\u0002\u0002ò\t\u0003\u0002\u0002\u0002óô\u0007 \u0002\u0002ôø\u0007\u0084\u0002\u0002õö\u0005\u0084C\u0002ö÷\u0007\u0004\u0002\u0002÷ù\u0003\u0002\u0002\u0002øõ\u0003\u0002\u0002\u0002øù\u0003\u0002\u0002\u0002ùú\u0003\u0002\u0002\u0002úă\u0005\u008aF\u0002ûü\u0007{\u0002\u0002üý\u0007\u0088\u0002\u0002ýĄ\u0005\u008eH\u0002þĀ\u0007\u001d\u0002\u0002ÿā\u00070\u0002\u0002Āÿ\u0003\u0002\u0002\u0002Āā\u0003\u0002\u0002\u0002āĂ\u0003\u0002\u0002\u0002ĂĄ\u0005H%\u0002ăû\u0003\u0002\u0002\u0002ăþ\u0003\u0002\u0002\u0002Ą\u000b\u0003\u0002\u0002\u0002ąČ\u0007!\u0002\u0002Ćč\u0005\u0084C\u0002ćč\u0005\u008cG\u0002Ĉĉ\u0005\u0084C\u0002ĉĊ\u0007\u0004\u0002\u0002Ċċ\u0005\u008cG\u0002ċč\u0003\u0002\u0002\u0002ČĆ\u0003\u0002\u0002\u0002Čć\u0003\u0002\u0002\u0002ČĈ\u0003\u0002\u0002\u0002Čč\u0003\u0002\u0002\u0002č\r\u0003\u0002\u0002\u0002ĎĐ\u0007%\u0002\u0002ďđ\u00079\u0002\u0002Đď\u0003\u0002\u0002\u0002Đđ\u0003\u0002\u0002\u0002đĒ\u0003\u0002\u0002\u0002Ēē\u0005P)\u0002ēĔ\u0007#\u0002\u0002Ĕĕ\u0005\u0084C\u0002ĕ\u000f\u0003\u0002\u0002\u0002ĖĘ\u0007(\u0002\u0002ėę\t\u0002\u0002\u0002Ęė\u0003\u0002\u0002\u0002Ęę\u0003\u0002\u0002\u0002ęĞ\u0003\u0002\u0002\u0002ĚĜ\u0007\u0089\u0002\u0002ěĝ\u0005¤S\u0002Ĝě\u0003\u0002\u0002\u0002Ĝĝ\u0003\u0002\u0002\u0002ĝğ\u0003\u0002\u0002\u0002ĞĚ\u0003\u0002\u0002\u0002Ğğ\u0003\u0002\u0002\u0002ğ\u0011\u0003\u0002\u0002\u0002Ġĥ\t\u0003\u0002\u0002ġģ\u0007\u0089\u0002\u0002ĢĤ\u0005¤S\u0002ģĢ\u0003\u0002\u0002\u0002ģĤ\u0003\u0002\u0002\u0002ĤĦ\u0003\u0002\u0002\u0002ĥġ\u0003\u0002\u0002\u0002ĥĦ\u0003\u0002\u0002\u0002Ħ\u0013\u0003\u0002\u0002\u0002ħĩ\u0005Z.\u0002Ĩħ\u0003\u0002\u0002\u0002Ĩĩ\u0003\u0002\u0002\u0002ĩĪ\u0003\u0002\u0002\u0002ĪĴ\u0005n8\u0002īĭ\u0007\u008b\u0002\u0002ĬĮ\u0007\u001f\u0002\u0002ĭĬ\u0003\u0002\u0002\u0002ĭĮ\u0003\u0002\u0002\u0002ĮĲ\u0003\u0002\u0002\u0002įĲ\u0007\\\u0002\u0002İĲ\u0007F\u0002\u0002ıī\u0003\u0002\u0002\u0002ıį\u0003\u0002\u0002\u0002ıİ\u0003\u0002\u0002\u0002Ĳĳ\u0003\u0002\u0002\u0002ĳĵ\u0005n8\u0002Ĵı\u0003\u0002\u0002\u0002ĵĶ\u0003\u0002\u0002\u0002ĶĴ\u0003\u0002\u0002\u0002Ķķ\u0003\u0002\u0002\u0002ķł\u0003\u0002\u0002\u0002ĸĹ\u0007o\u0002\u0002Ĺĺ\u0007*\u0002\u0002ĺĿ\u0005^0\u0002Ļļ\u0007\u0007\u0002\u0002ļľ\u0005^0\u0002ĽĻ\u0003\u0002\u0002\u0002ľŁ\u0003\u0002\u0002\u0002ĿĽ\u0003\u0002\u0002\u0002Ŀŀ\u0003\u0002\u0002\u0002ŀŃ\u0003\u0002\u0002\u0002ŁĿ\u0003\u0002\u0002\u0002łĸ\u0003\u0002\u0002\u0002łŃ\u0003\u0002\u0002\u0002ŃŊ\u0003\u0002\u0002\u0002ńŅ\u0007d\u0002\u0002Ņň\u0005P)\u0002ņŇ\t\u0004\u0002\u0002Ňŉ\u0005P)\u0002ňņ\u0003\u0002\u0002\u0002ňŉ\u0003\u0002\u0002\u0002ŉŋ\u0003\u0002\u0002\u0002Ŋń\u0003\u0002\u0002\u0002Ŋŋ\u0003\u0002\u0002\u0002ŋ\u0015\u0003\u0002\u0002\u0002ŌŎ\u00074\u0002\u0002ōŏ\u0007\u008c\u0002\u0002Ŏō\u0003\u0002\u0002\u0002Ŏŏ\u0003\u0002\u0002\u0002ŏŐ\u0003\u0002\u0002\u0002ŐŔ\u0007V\u0002\u0002őŒ\u0007R\u0002\u0002Œœ\u0007h\u0002\u0002œŕ\u0007H\u0002\u0002Ŕő\u0003\u0002\u0002\u0002Ŕŕ\u0003\u0002\u0002\u0002ŕř\u0003\u0002\u0002\u0002Ŗŗ\u0005\u0084C\u0002ŗŘ\u0007\u0004\u0002\u0002ŘŚ\u0003\u0002\u0002\u0002řŖ\u0003\u0002\u0002\u0002řŚ\u0003\u0002\u0002\u0002Śś\u0003\u0002\u0002\u0002śŜ\u0005\u0096L\u0002Ŝŝ\u0007m\u0002\u0002ŝŞ\u0005\u008aF\u0002Şş\u0007\u0005\u0002\u0002şŤ\u0005V,\u0002Šš\u0007\u0007\u0002\u0002šţ\u0005V,\u0002ŢŠ\u0003\u0002\u0002\u0002ţŦ\u0003\u0002\u0002\u0002ŤŢ\u0003\u0002\u0002\u0002Ťť\u0003\u0002\u0002\u0002ťŧ\u0003\u0002\u0002\u0002ŦŤ\u0003\u0002\u0002\u0002ŧŪ\u0007\u0006\u0002\u0002Ũũ\u0007\u0094\u0002\u0002ũū\u0005P)\u0002ŪŨ\u0003\u0002\u0002\u0002Ūū\u0003\u0002\u0002\u0002ū\u0017\u0003\u0002\u0002\u0002ŬŮ\u00074\u0002\u0002ŭů\t\u0005\u0002\u0002Ůŭ\u0003\u0002\u0002\u0002Ůů\u0003\u0002\u0002\u0002ůŰ\u0003\u0002\u0002\u0002ŰŴ\u0007\u0084\u0002\u0002űŲ\u0007R\u0002\u0002Ųų\u0007h\u0002\u0002ųŵ\u0007H\u0002\u0002Ŵű\u0003\u0002\u0002\u0002Ŵŵ\u0003\u0002\u0002\u0002ŵŹ\u0003\u0002\u0002\u0002Ŷŷ\u0005\u0084C\u0002ŷŸ\u0007\u0004\u0002\u0002Ÿź\u0003\u0002\u0002\u0002ŹŶ\u0003\u0002\u0002\u0002Źź\u0003\u0002\u0002\u0002źŻ\u0003\u0002\u0002\u0002ŻƓ\u0005\u008aF\u0002żŽ\u0007\u0005\u0002\u0002ŽƂ\u0005H%\u0002žſ\u0007\u0007\u0002\u0002ſƁ\u0005H%\u0002ƀž\u0003\u0002\u0002\u0002ƁƄ\u0003\u0002\u0002\u0002Ƃƃ\u0003\u0002\u0002\u0002Ƃƀ\u0003\u0002\u0002\u0002ƃƉ\u0003\u0002\u0002\u0002ƄƂ\u0003\u0002\u0002\u0002ƅƆ\u0007\u0007\u0002\u0002Ɔƈ\u0005X-\u0002Ƈƅ\u0003\u0002\u0002\u0002ƈƋ\u0003\u0002\u0002\u0002ƉƇ\u0003\u0002\u0002\u0002ƉƊ\u0003\u0002\u0002\u0002Ɗƌ\u0003\u0002\u0002\u0002ƋƉ\u0003\u0002\u0002\u0002ƌƏ\u0007\u0006\u0002\u0002ƍƎ\u0007\u0096\u0002\u0002ƎƐ\u0007\u0097\u0002\u0002Əƍ\u0003\u0002\u0002\u0002ƏƐ\u0003\u0002\u0002\u0002ƐƔ\u0003\u0002\u0002\u0002Ƒƒ\u0007#\u0002\u0002ƒƔ\u0005> \u0002Ɠż\u0003\u0002\u0002\u0002ƓƑ\u0003\u0002\u0002\u0002Ɣ\u0019\u0003\u0002\u0002\u0002ƕƗ\u00074\u0002\u0002ƖƘ\t\u0005\u0002\u0002ƗƖ\u0003\u0002\u0002\u0002ƗƘ\u0003\u0002\u0002\u0002Ƙƙ\u0003\u0002\u0002\u0002ƙƝ\u0007\u008a\u0002\u0002ƚƛ\u0007R\u0002\u0002ƛƜ\u0007h\u0002\u0002Ɯƞ\u0007H\u0002\u0002Ɲƚ\u0003\u0002\u0002\u0002Ɲƞ\u0003\u0002\u0002\u0002ƞƢ\u0003\u0002\u0002\u0002ƟƠ\u0005\u0084C\u0002Ơơ\u0007\u0004\u0002\u0002ơƣ\u0003\u0002\u0002\u0002ƢƟ\u0003\u0002\u0002\u0002Ƣƣ\u0003\u0002\u0002\u0002ƣƤ\u0003\u0002\u0002\u0002ƤƩ\u0005\u0098M\u0002ƥƪ\u0007'\u0002\u0002Ʀƪ\u0007\u001e\u0002\u0002Ƨƨ\u0007[\u0002\u0002ƨƪ\u0007k\u0002\u0002Ʃƥ\u0003\u0002\u0002\u0002ƩƦ\u0003\u0002\u0002\u0002ƩƧ\u0003\u0002\u0002\u0002Ʃƪ\u0003\u0002\u0002\u0002ƪƹ\u0003\u0002\u0002\u0002ƫƺ\u0007=\u0002\u0002Ƭƺ\u0007Z\u0002\u0002ƭƷ\u0007\u008d\u0002\u0002ƮƯ\u0007k\u0002\u0002Ưƴ\u0005\u0090I\u0002ưƱ\u0007\u0007\u0002\u0002ƱƳ\u0005\u0090I\u0002Ʋư\u0003\u0002\u0002\u0002Ƴƶ\u0003\u0002\u0002\u0002ƴƲ\u0003\u0002\u0002\u0002ƴƵ\u0003\u0002\u0002\u0002ƵƸ\u0003\u0002\u0002\u0002ƶƴ\u0003\u0002\u0002\u0002ƷƮ\u0003\u0002\u0002\u0002ƷƸ\u0003\u0002\u0002\u0002Ƹƺ\u0003\u0002\u0002\u0002ƹƫ\u0003\u0002\u0002\u0002ƹƬ\u0003\u0002\u0002\u0002ƹƭ\u0003\u0002\u0002\u0002ƺƻ\u0003\u0002\u0002\u0002ƻƿ\u0007m\u0002\u0002Ƽƽ\u0005\u0084C\u0002ƽƾ\u0007\u0004\u0002\u0002ƾǀ\u0003\u0002\u0002\u0002ƿƼ\u0003\u0002\u0002\u0002ƿǀ\u0003\u0002\u0002\u0002ǀǁ\u0003\u0002\u0002\u0002ǁǅ\u0005\u008aF\u0002ǂǃ\u0007K\u0002\u0002ǃǄ\u0007B\u0002\u0002Ǆǆ\u0007\u0080\u0002\u0002ǅǂ\u0003\u0002\u0002\u0002ǅǆ\u0003\u0002\u0002\u0002ǆǉ\u0003\u0002\u0002\u0002Ǉǈ\u0007\u0093\u0002\u0002ǈǊ\u0005P)\u0002ǉǇ\u0003\u0002\u0002\u0002ǉǊ\u0003\u0002\u0002\u0002Ǌǋ\u0003\u0002\u0002\u0002ǋǔ\u0007(\u0002\u0002ǌǑ\u0005B\"\u0002ǍǑ\u00050\u0019\u0002ǎǑ\u0005 \u0011\u0002ǏǑ\u0005> \u0002ǐǌ\u0003\u0002\u0002\u0002ǐǍ\u0003\u0002\u0002\u0002ǐǎ\u0003\u0002\u0002\u0002ǐǏ\u0003\u0002\u0002\u0002Ǒǒ\u0003\u0002\u0002\u0002ǒǓ\u0007\u0003\u0002\u0002ǓǕ\u0003\u0002\u0002\u0002ǔǐ\u0003\u0002\u0002\u0002Ǖǖ\u0003\u0002\u0002\u0002ǖǔ\u0003\u0002\u0002\u0002ǖǗ\u0003\u0002\u0002\u0002Ǘǘ\u0003\u0002\u0002\u0002ǘǙ\u0007D\u0002\u0002Ǚ\u001b\u0003\u0002\u0002\u0002ǚǜ\u00074\u0002\u0002Ǜǝ\t\u0005\u0002\u0002ǜǛ\u0003\u0002\u0002\u0002ǜǝ\u0003\u0002\u0002\u0002ǝǞ\u0003\u0002\u0002\u0002ǞǢ\u0007\u0091\u0002\u0002ǟǠ\u0007R\u0002\u0002Ǡǡ\u0007h\u0002\u0002ǡǣ\u0007H\u0002\u0002Ǣǟ\u0003\u0002\u0002\u0002Ǣǣ\u0003\u0002\u0002\u0002ǣǧ\u0003\u0002\u0002\u0002Ǥǥ\u0005\u0084C\u0002ǥǦ\u0007\u0004\u0002\u0002ǦǨ\u0003\u0002\u0002\u0002ǧǤ\u0003\u0002\u0002\u0002ǧǨ\u0003\u0002\u0002\u0002Ǩǩ\u0003\u0002\u0002\u0002ǩǪ\u0005\u009aN\u0002Ǫǫ\u0007#\u0002\u0002ǫǬ\u0005> \u0002Ǭ\u001d\u0003\u0002\u0002\u0002ǭǮ\u00074\u0002\u0002Ǯǯ\u0007\u0092\u0002\u0002ǯǳ\u0007\u0084\u0002\u0002ǰǱ\u0007R\u0002\u0002Ǳǲ\u0007h\u0002\u0002ǲǴ\u0007H\u0002\u0002ǳǰ\u0003\u0002\u0002\u0002ǳǴ\u0003\u0002\u0002\u0002ǴǸ\u0003\u0002\u0002\u0002ǵǶ\u0005\u0084C\u0002ǶǷ\u0007\u0004\u0002\u0002Ƿǹ\u0003\u0002\u0002\u0002Ǹǵ\u0003\u0002\u0002\u0002Ǹǹ\u0003\u0002\u0002\u0002ǹǺ\u0003\u0002\u0002\u0002Ǻǻ\u0005\u008aF\u0002ǻǼ\u0007\u008e\u0002\u0002ǼȈ\u0005\u009cO\u0002ǽǾ\u0007\u0005\u0002\u0002Ǿȃ\u0005z>\u0002ǿȀ\u0007\u0007\u0002\u0002ȀȂ\u0005z>\u0002ȁǿ\u0003\u0002\u0002\u0002Ȃȅ\u0003\u0002\u0002\u0002ȃȁ\u0003\u0002\u0002\u0002ȃȄ\u0003\u0002\u0002\u0002ȄȆ\u0003\u0002\u0002\u0002ȅȃ\u0003\u0002\u0002\u0002Ȇȇ\u0007\u0006\u0002\u0002ȇȉ\u0003\u0002\u0002\u0002Ȉǽ\u0003\u0002\u0002\u0002Ȉȉ\u0003\u0002\u0002\u0002ȉ\u001f\u0003\u0002\u0002\u0002ȊȌ\u0005Z.\u0002ȋȊ\u0003\u0002\u0002\u0002ȋȌ\u0003\u0002\u0002\u0002Ȍȍ\u0003\u0002\u0002\u0002ȍȎ\u0007=\u0002\u0002Ȏȏ\u0007M\u0002\u0002ȏȒ\u0005\\/\u0002Ȑȑ\u0007\u0094\u0002\u0002ȑȓ\u0005P)\u0002ȒȐ\u0003\u0002\u0002\u0002Ȓȓ\u0003\u0002\u0002\u0002ȓ!\u0003\u0002\u0002\u0002ȔȖ\u0005Z.\u0002ȕȔ\u0003\u0002\u0002\u0002ȕȖ\u0003\u0002\u0002\u0002Ȗȗ\u0003\u0002\u0002\u0002ȗȘ\u0007=\u0002\u0002Șș\u0007M\u0002\u0002șȜ\u0005\\/\u0002Țț\u0007\u0094\u0002\u0002țȝ\u0005P)\u0002ȜȚ\u0003\u0002\u0002\u0002Ȝȝ\u0003\u0002\u0002\u0002ȝȰ\u0003\u0002\u0002\u0002Ȟȟ\u0007o\u0002\u0002ȟȠ\u0007*\u0002\u0002Ƞȥ\u0005^0\u0002ȡȢ\u0007\u0007\u0002\u0002ȢȤ\u0005^0\u0002ȣȡ\u0003\u0002\u0002\u0002Ȥȧ\u0003\u0002\u0002\u0002ȥȣ\u0003\u0002\u0002\u0002ȥȦ\u0003\u0002\u0002\u0002Ȧȩ\u0003\u0002\u0002\u0002ȧȥ\u0003\u0002\u0002\u0002ȨȞ\u0003\u0002\u0002\u0002Ȩȩ\u0003\u0002\u0002\u0002ȩȪ\u0003\u0002\u0002\u0002Ȫȫ\u0007d\u0002\u0002ȫȮ\u0005P)\u0002Ȭȭ\t\u0004\u0002\u0002ȭȯ\u0005P)\u0002ȮȬ\u0003\u0002\u0002\u0002Ȯȯ\u0003\u0002\u0002\u0002ȯȱ\u0003\u0002\u0002\u0002ȰȨ\u0003\u0002\u0002\u0002Ȱȱ\u0003\u0002\u0002\u0002ȱ#\u0003\u0002\u0002\u0002Ȳȴ\u0007?\u0002\u0002ȳȵ\u00079\u0002\u0002ȴȳ\u0003\u0002\u0002\u0002ȴȵ\u0003\u0002\u0002\u0002ȵȶ\u0003\u0002\u0002\u0002ȶȷ\u0005\u0084C\u0002ȷ%\u0003\u0002\u0002\u0002ȸȹ\u0007A\u0002\u0002ȹȼ\u0007V\u0002\u0002ȺȻ\u0007R\u0002\u0002ȻȽ\u0007H\u0002\u0002ȼȺ\u0003\u0002\u0002\u0002ȼȽ\u0003\u0002\u0002\u0002ȽɁ\u0003\u0002\u0002\u0002Ⱦȿ\u0005\u0084C\u0002ȿɀ\u0007\u0004\u0002\u0002ɀɂ\u0003\u0002\u0002\u0002ɁȾ\u0003\u0002\u0002\u0002Ɂɂ\u0003\u0002\u0002\u0002ɂɃ\u0003\u0002\u0002\u0002ɃɄ\u0005\u0096L\u0002Ʉ'\u0003\u0002\u0002\u0002ɅɆ\u0007A\u0002\u0002Ɇɉ\u0007\u0084\u0002\u0002ɇɈ\u0007R\u0002\u0002ɈɊ\u0007H\u0002\u0002ɉɇ\u0003\u0002\u0002\u0002ɉɊ\u0003\u0002\u0002\u0002ɊɎ\u0003\u0002\u0002\u0002ɋɌ\u0005\u0084C\u0002Ɍɍ\u0007\u0004\u0002\u0002ɍɏ\u0003\u0002\u0002\u0002Ɏɋ\u0003\u0002\u0002\u0002Ɏɏ\u0003\u0002\u0002\u0002ɏɐ\u0003\u0002\u0002\u0002ɐɑ\u0005\u008aF\u0002ɑ)\u0003\u0002\u0002\u0002ɒɓ\u0007A\u0002\u0002ɓɖ\u0007\u008a\u0002\u0002ɔɕ\u0007R\u0002\u0002ɕɗ\u0007H\u0002\u0002ɖɔ\u0003\u0002\u0002\u0002ɖɗ\u0003\u0002\u0002\u0002ɗɛ\u0003\u0002\u0002\u0002ɘə\u0005\u0084C\u0002əɚ\u0007\u0004\u0002\u0002ɚɜ\u0003\u0002\u0002\u0002ɛɘ\u0003\u0002\u0002\u0002ɛɜ\u0003\u0002\u0002\u0002ɜɝ\u0003\u0002\u0002\u0002ɝɞ\u0005\u0098M\u0002ɞ+\u0003\u0002\u0002\u0002ɟɠ\u0007A\u0002\u0002ɠɣ\u0007\u0091\u0002\u0002ɡɢ\u0007R\u0002\u0002ɢɤ\u0007H\u0002\u0002ɣɡ\u0003\u0002\u0002\u0002ɣɤ\u0003\u0002\u0002\u0002ɤɨ\u0003\u0002\u0002\u0002ɥɦ\u0005\u0084C\u0002ɦɧ\u0007\u0004\u0002\u0002ɧɩ\u0003\u0002\u0002\u0002ɨɥ\u0003\u0002\u0002\u0002ɨɩ\u0003\u0002\u0002\u0002ɩɪ\u0003\u0002\u0002\u0002ɪɫ\u0005\u009aN\u0002ɫ-\u0003\u0002\u0002\u0002ɬɮ\u0005Z.\u0002ɭɬ\u0003\u0002\u0002\u0002ɭɮ\u0003\u0002\u0002\u0002ɮɯ\u0003\u0002\u0002\u0002ɯɵ\u0005n8\u0002ɰɱ\u0005p9\u0002ɱɲ\u0005n8\u0002ɲɴ\u0003\u0002\u0002\u0002ɳɰ\u0003\u0002\u0002\u0002ɴɷ\u0003\u0002\u0002\u0002ɵɳ\u0003\u0002\u0002\u0002ɵɶ\u0003\u0002\u0002\u0002ɶʂ\u0003\u0002\u0002\u0002ɷɵ\u0003\u0002\u0002\u0002ɸɹ\u0007o\u0002\u0002ɹɺ\u0007*\u0002\u0002ɺɿ\u0005^0\u0002ɻɼ\u0007\u0007\u0002\u0002ɼɾ\u0005^0\u0002ɽɻ\u0003\u0002\u0002\u0002ɾʁ\u0003\u0002\u0002\u0002ɿɽ\u0003\u0002\u0002\u0002ɿʀ\u0003\u0002\u0002\u0002ʀʃ\u0003\u0002\u0002\u0002ʁɿ\u0003\u0002\u0002\u0002ʂɸ\u0003\u0002\u0002\u0002ʂʃ\u0003\u0002\u0002\u0002ʃʊ\u0003\u0002\u0002\u0002ʄʅ\u0007d\u0002\u0002ʅʈ\u0005P)\u0002ʆʇ\t\u0004\u0002\u0002ʇʉ\u0005P)\u0002ʈʆ\u0003\u0002\u0002\u0002ʈʉ\u0003\u0002\u0002\u0002ʉʋ\u0003\u0002\u0002\u0002ʊʄ\u0003\u0002\u0002\u0002ʊʋ\u0003\u0002\u0002\u0002ʋ/\u0003\u0002\u0002\u0002ʌʎ\u0005Z.\u0002ʍʌ\u0003\u0002\u0002\u0002ʍʎ\u0003\u0002\u0002\u0002ʎʠ\u0003\u0002\u0002\u0002ʏʡ\u0007Z\u0002\u0002ʐʡ\u0007|\u0002\u0002ʑʒ\u0007Z\u0002\u0002ʒʓ\u0007n\u0002\u0002ʓʡ\u0007|\u0002\u0002ʔʕ\u0007Z\u0002\u0002ʕʖ\u0007n\u0002\u0002ʖʡ\u0007\u007f\u0002\u0002ʗʘ\u0007Z\u0002\u0002ʘʙ\u0007n\u0002\u0002ʙʡ\u0007\u001b\u0002\u0002ʚʛ\u0007Z\u0002\u0002ʛʜ\u0007n\u0002\u0002ʜʡ\u0007J\u0002\u0002ʝʞ\u0007Z\u0002\u0002ʞʟ\u0007n\u0002\u0002ʟʡ\u0007S\u0002\u0002ʠʏ\u0003\u0002\u0002\u0002ʠʐ\u0003\u0002\u0002\u0002ʠʑ\u0003\u0002\u0002\u0002ʠʔ\u0003\u0002\u0002\u0002ʠʗ\u0003\u0002\u0002\u0002ʠʚ\u0003\u0002\u0002\u0002ʠʝ\u0003\u0002\u0002\u0002ʡʢ\u0003\u0002\u0002\u0002ʢʦ\u0007]\u0002\u0002ʣʤ\u0005\u0084C\u0002ʤʥ\u0007\u0004\u0002\u0002ʥʧ\u0003\u0002\u0002\u0002ʦʣ\u0003\u0002\u0002\u0002ʦʧ\u0003\u0002\u0002\u0002ʧʨ\u0003\u0002\u0002\u0002ʨʴ\u0005\u008aF\u0002ʩʪ\u0007\u0005\u0002\u0002ʪʯ\u0005\u0090I\u0002ʫʬ\u0007\u0007\u0002\u0002ʬʮ\u0005\u0090I\u0002ʭʫ\u0003\u0002\u0002\u0002ʮʱ\u0003\u0002\u0002\u0002ʯʭ\u0003\u0002\u0002\u0002ʯʰ\u0003\u0002\u0002\u0002ʰʲ\u0003\u0002\u0002\u0002ʱʯ\u0003\u0002\u0002\u0002ʲʳ\u0007\u0006\u0002\u0002ʳʵ\u0003\u0002\u0002\u0002ʴʩ\u0003\u0002\u0002\u0002ʴʵ\u0003\u0002\u0002\u0002ʵ˕\u0003\u0002\u0002\u0002ʶʷ\u0007\u0090\u0002\u0002ʷʸ\u0007\u0005\u0002\u0002ʸʽ\u0005P)\u0002ʹʺ\u0007\u0007\u0002\u0002ʺʼ\u0005P)\u0002ʻʹ\u0003\u0002\u0002\u0002ʼʿ\u0003\u0002\u0002\u0002ʽʻ\u0003\u0002\u0002\u0002ʽʾ\u0003\u0002\u0002\u0002ʾˀ\u0003\u0002\u0002\u0002ʿʽ\u0003\u0002\u0002\u0002ˀˏ\u0007\u0006\u0002\u0002ˁ˂\u0007\u0007\u0002\u0002˂˃\u0007\u0005\u0002\u0002˃ˈ\u0005P)\u0002˄˅\u0007\u0007\u0002\u0002˅ˇ\u0005P)\u0002ˆ˄\u0003\u0002\u0002\u0002ˇˊ\u0003\u0002\u0002\u0002ˈˆ\u0003\u0002\u0002\u0002ˈˉ\u0003\u0002\u0002\u0002ˉˋ\u0003\u0002\u0002\u0002ˊˈ\u0003\u0002\u0002\u0002ˋˌ\u0007\u0006\u0002\u0002ˌˎ\u0003\u0002\u0002\u0002ˍˁ\u0003\u0002\u0002\u0002ˎˑ\u0003\u0002\u0002\u0002ˏˍ\u0003\u0002\u0002\u0002ˏː\u0003\u0002\u0002\u0002ː˖\u0003\u0002\u0002\u0002ˑˏ\u0003\u0002\u0002\u0002˒˖\u0005> \u0002˓˔\u0007:\u0002\u0002˔˖\u0007\u0090\u0002\u0002˕ʶ\u0003\u0002\u0002\u0002˕˒\u0003\u0002\u0002\u0002˕˓\u0003\u0002\u0002\u0002˖1\u0003\u0002\u0002\u0002˗˛\u0007r\u0002\u0002˘˙\u0005\u0084C\u0002˙˚\u0007\u0004\u0002\u0002˚˜\u0003\u0002\u0002\u0002˛˘\u0003\u0002\u0002\u0002˛˜\u0003\u0002\u0002\u0002˜˝\u0003\u0002\u0002\u0002˝ˤ\u0005\u009eP\u0002˞˟\u0007\b\u0002\u0002˟˥\u0005`1\u0002ˠˡ\u0007\u0005\u0002\u0002ˡˢ\u0005`1\u0002ˢˣ\u0007\u0006\u0002\u0002ˣ˥\u0003\u0002\u0002\u0002ˤ˞\u0003\u0002\u0002\u0002ˤˠ\u0003\u0002\u0002\u0002ˤ˥\u0003\u0002\u0002\u0002˥3\u0003\u0002\u0002\u0002˦˱\u0007y\u0002\u0002˧˲\u0005\u0092J\u0002˨˩\u0005\u0084C\u0002˩˪\u0007\u0004\u0002\u0002˪ˬ\u0003\u0002\u0002\u0002˫˨\u0003\u0002\u0002\u0002˫ˬ\u0003\u0002\u0002\u0002ˬ˯\u0003\u0002\u0002\u0002˭˰\u0005\u008aF\u0002ˮ˰\u0005\u0096L\u0002˯˭\u0003\u0002\u0002\u0002˯ˮ\u0003\u0002\u0002\u0002˰˲\u0003\u0002\u0002\u0002˱˧\u0003\u0002\u0002\u0002˱˫\u0003\u0002\u0002\u0002˱˲\u0003\u0002\u0002\u0002˲5\u0003\u0002\u0002\u0002˳˵\u0007z\u0002\u0002˴˶\u0007\u0081\u0002\u0002˵˴\u0003\u0002\u0002\u0002˵˶\u0003\u0002\u0002\u0002˶˷\u0003\u0002\u0002\u0002˷˸\u0005 Q\u0002˸7\u0003\u0002\u0002\u0002˹˾\u0007\u007f\u0002\u0002˺˼\u0007\u0089\u0002\u0002˻˽\u0005¤S\u0002˼˻\u0003\u0002\u0002\u0002˼˽\u0003\u0002\u0002\u0002˽˿\u0003\u0002\u0002\u0002˾˺\u0003\u0002\u0002\u0002˾˿\u0003\u0002\u0002\u0002˿̅\u0003\u0002\u0002\u0002̀̂\u0007\u0088\u0002\u0002́̃\u0007\u0081\u0002\u0002̂́\u0003\u0002\u0002\u0002̂̃\u0003\u0002\u0002\u0002̃̄\u0003\u0002\u0002\u0002̄̆\u0005 Q\u0002̅̀\u0003\u0002\u0002\u0002̅̆\u0003\u0002\u0002\u0002̆9\u0003\u0002\u0002\u0002̇̈\u0007\u0081\u0002\u0002̈̉\u0005 Q\u0002̉;\u0003\u0002\u0002\u0002̊̌\u0005Z.\u0002̋̊\u0003\u0002\u0002\u0002̋̌\u0003\u0002\u0002\u0002̌̍\u0003\u0002\u0002\u0002̘̍\u0005n8\u0002̎̏\u0007o\u0002\u0002̏̐\u0007*\u0002\u0002̐̕\u0005^0\u0002̑̒\u0007\u0007\u0002\u0002̒̔\u0005^0\u0002̓̑\u0003\u0002\u0002\u0002̗̔\u0003\u0002\u0002\u0002̓̕\u0003\u0002\u0002\u0002̖̕\u0003\u0002\u0002\u0002̖̙\u0003\u0002\u0002\u0002̗̕\u0003\u0002\u0002\u0002̘̎\u0003\u0002\u0002\u0002̘̙\u0003\u0002\u0002\u0002̙̠\u0003\u0002\u0002\u0002̛̚\u0007d\u0002\u0002̛̞\u0005P)\u0002̜̝\t\u0004\u0002\u0002̝̟\u0005P)\u0002̞̜\u0003\u0002\u0002\u0002̞̟\u0003\u0002\u0002\u0002̡̟\u0003\u0002\u0002\u0002̠̚\u0003\u0002\u0002\u0002̡̠\u0003\u0002\u0002\u0002̡=\u0003\u0002\u0002\u0002̢̤\u0005Z.\u0002̢̣\u0003\u0002\u0002\u0002̣̤\u0003\u0002\u0002\u0002̤̥\u0003\u0002\u0002\u0002̥̫\u0005@!\u0002̧̦\u0005p9\u0002̧̨\u0005@!\u0002̨̪\u0003\u0002\u0002\u0002̩̦\u0003\u0002\u0002\u0002̪̭\u0003\u0002\u0002\u0002̫̩\u0003\u0002\u0002\u0002̫̬\u0003\u0002\u0002\u0002̸̬\u0003\u0002\u0002\u0002̭̫\u0003\u0002\u0002\u0002̮̯\u0007o\u0002\u0002̯̰\u0007*\u0002\u0002̵̰\u0005^0\u0002̱̲\u0007\u0007\u0002\u0002̴̲\u0005^0\u0002̳̱\u0003\u0002\u0002\u0002̴̷\u0003\u0002\u0002\u0002̵̳\u0003\u0002\u0002\u0002̵̶\u0003\u0002\u0002\u0002̶̹\u0003\u0002\u0002\u0002̷̵\u0003\u0002\u0002\u0002̸̮\u0003\u0002\u0002\u0002̸̹\u0003\u0002\u0002\u0002̹̀\u0003\u0002\u0002\u0002̺̻\u0007d\u0002\u0002̻̾\u0005P)\u0002̼̽\t\u0004\u0002\u0002̽̿\u0005P)\u0002̼̾\u0003\u0002\u0002\u0002̾̿\u0003\u0002\u0002\u0002̿́\u0003\u0002\u0002\u0002̺̀\u0003\u0002\u0002\u0002̀́\u0003\u0002\u0002\u0002́?\u0003\u0002\u0002\u0002͂̈́\u0007\u0082\u0002\u0002̓ͅ\t\u0006\u0002\u0002̈́̓\u0003\u0002\u0002\u0002̈́ͅ\u0003\u0002\u0002\u0002͆ͅ\u0003\u0002\u0002\u0002͆͋\u0005d3\u0002͇͈\u0007\u0007\u0002\u0002͈͊\u0005d3\u0002͉͇\u0003\u0002\u0002\u0002͍͊\u0003\u0002\u0002\u0002͉͋\u0003\u0002\u0002\u0002͋͌\u0003\u0002\u0002\u0002͚͌\u0003\u0002\u0002\u0002͍͋\u0003\u0002\u0002\u0002͎͘\u0007M\u0002\u0002͏͔\u0005f4\u0002͐͑\u0007\u0007\u0002\u0002͓͑\u0005f4\u0002͒͐\u0003\u0002\u0002\u0002͓͖\u0003\u0002\u0002\u0002͔͒\u0003\u0002\u0002\u0002͔͕\u0003\u0002\u0002\u0002͕͙\u0003\u0002\u0002\u0002͖͔\u0003\u0002\u0002\u0002͙͗\u0005h5\u0002͘͏\u0003\u0002\u0002\u0002͗͘\u0003\u0002\u0002\u0002͙͛\u0003\u0002\u0002\u0002͚͎\u0003\u0002\u0002\u0002͚͛\u0003\u0002\u0002\u0002͛͞\u0003\u0002\u0002\u0002͜͝\u0007\u0094\u0002\u0002͟͝\u0005P)\u0002͜͞\u0003\u0002\u0002\u0002͟͞\u0003\u0002\u0002\u0002ͮ͟\u0003\u0002\u0002\u0002͠͡\u0007P\u0002\u0002͢͡\u0007*\u0002\u0002ͧ͢\u0005P)\u0002ͣͤ\u0007\u0007\u0002\u0002ͤͦ\u0005P)\u0002ͥͣ\u0003\u0002\u0002\u0002ͦͩ\u0003\u0002\u0002\u0002ͧͥ\u0003\u0002\u0002\u0002ͧͨ\u0003\u0002\u0002\u0002ͨͬ\u0003\u0002\u0002\u0002ͩͧ\u0003\u0002\u0002\u0002ͪͫ\u0007Q\u0002\u0002ͫͭ\u0005P)\u0002ͬͪ\u0003\u0002\u0002\u0002ͬͭ\u0003\u0002\u0002\u0002ͭͯ\u0003\u0002\u0002\u0002ͮ͠\u0003\u0002\u0002\u0002ͮͯ\u0003\u0002\u0002\u0002ͯ\u038d\u0003\u0002\u0002\u0002Ͱͱ\u0007\u0090\u0002\u0002ͱͲ\u0007\u0005\u0002\u0002Ͳͷ\u0005P)\u0002ͳʹ\u0007\u0007\u0002\u0002ʹͶ\u0005P)\u0002͵ͳ\u0003\u0002\u0002\u0002Ͷ\u0379\u0003\u0002\u0002\u0002ͷ͵\u0003\u0002\u0002\u0002ͷ\u0378\u0003\u0002\u0002\u0002\u0378ͺ\u0003\u0002\u0002\u0002\u0379ͷ\u0003\u0002\u0002\u0002ͺΉ\u0007\u0006\u0002\u0002ͻͼ\u0007\u0007\u0002\u0002ͼͽ\u0007\u0005\u0002\u0002ͽ\u0382\u0005P)\u0002;Ϳ\u0007\u0007\u0002\u0002Ϳ\u0381\u0005P)\u0002\u0380;\u0003\u0002\u0002\u0002\u0381΄\u0003\u0002\u0002\u0002\u0382\u0380\u0003\u0002\u0002\u0002\u0382\u0383\u0003\u0002\u0002\u0002\u0383΅\u0003\u0002\u0002\u0002΄\u0382\u0003\u0002\u0002\u0002΅Ά\u0007\u0006\u0002\u0002ΆΈ\u0003\u0002\u0002\u0002·ͻ\u0003\u0002\u0002\u0002Έ\u038b\u0003\u0002\u0002\u0002Ή·\u0003\u0002\u0002\u0002ΉΊ\u0003\u0002\u0002\u0002Ί\u038d\u0003\u0002\u0002\u0002\u038bΉ\u0003\u0002\u0002\u0002Ό͂\u0003\u0002\u0002\u0002ΌͰ\u0003\u0002\u0002\u0002\u038dA\u0003\u0002\u0002\u0002Ύΐ\u0005Z.\u0002ΏΎ\u0003\u0002\u0002\u0002Ώΐ\u0003\u0002\u0002\u0002ΐΑ\u0003\u0002\u0002\u0002ΑΜ\u0007\u008d\u0002\u0002ΒΓ\u0007n\u0002\u0002ΓΝ\u0007\u007f\u0002\u0002ΔΕ\u0007n\u0002\u0002ΕΝ\u0007\u001b\u0002\u0002ΖΗ\u0007n\u0002\u0002ΗΝ\u0007|\u0002\u0002ΘΙ\u0007n\u0002\u0002ΙΝ\u0007J\u0002\u0002ΚΛ\u0007n\u0002\u0002ΛΝ\u0007S\u0002\u0002ΜΒ\u0003\u0002\u0002\u0002ΜΔ\u0003\u0002\u0002\u0002ΜΖ\u0003\u0002\u0002\u0002ΜΘ\u0003\u0002\u0002\u0002ΜΚ\u0003\u0002\u0002\u0002ΜΝ\u0003\u0002\u0002\u0002ΝΞ\u0003\u0002\u0002\u0002ΞΟ\u0005\\/\u0002ΟΠ\u0007\u0083\u0002\u0002ΠΡ\u0005\u0090I\u0002Ρ\u03a2\u0007\b\u0002\u0002\u03a2Ϊ\u0005P)\u0002ΣΤ\u0007\u0007\u0002\u0002ΤΥ\u0005\u0090I\u0002ΥΦ\u0007\b\u0002\u0002ΦΧ\u0005P)\u0002ΧΩ\u0003\u0002\u0002\u0002ΨΣ\u0003\u0002\u0002\u0002Ωά\u0003\u0002\u0002\u0002ΪΨ\u0003\u0002\u0002\u0002ΪΫ\u0003\u0002\u0002\u0002Ϋί\u0003\u0002\u0002\u0002άΪ\u0003\u0002\u0002\u0002έή\u0007\u0094\u0002\u0002ήΰ\u0005P)\u0002ίέ\u0003\u0002\u0002\u0002ίΰ\u0003\u0002\u0002\u0002ΰC\u0003\u0002\u0002\u0002αγ\u0005Z.\u0002βα\u0003\u0002\u0002\u0002βγ\u0003\u0002\u0002\u0002γδ\u0003\u0002\u0002\u0002δο\u0007\u008d\u0002\u0002εζ\u0007n\u0002\u0002ζπ\u0007\u007f\u0002\u0002ηθ\u0007n\u0002\u0002θπ\u0007\u001b\u0002\u0002ικ\u0007n\u0002\u0002κπ\u0007|\u0002\u0002λμ\u0007n\u0002\u0002μπ\u0007J\u0002\u0002νξ\u0007n\u0002\u0002ξπ\u0007S\u0002\u0002οε\u0003\u0002\u0002\u0002οη\u0003\u0002\u0002\u0002οι\u0003\u0002\u0002\u0002ολ\u0003\u0002\u0002\u0002ον\u0003\u0002\u0002\u0002οπ\u0003\u0002\u0002\u0002πρ\u0003\u0002\u0002\u0002ρς\u0005\\/\u0002ςσ\u0007\u0083\u0002\u0002στ\u0005\u0090I\u0002τυ\u0007\b\u0002\u0002υύ\u0005P)\u0002φχ\u0007\u0007\u0002\u0002χψ\u0005\u0090I\u0002ψω\u0007\b\u0002\u0002ωϊ\u0005P)\u0002ϊό\u0003\u0002\u0002\u0002ϋφ\u0003\u0002\u0002\u0002όϏ\u0003\u0002\u0002\u0002ύϋ\u0003\u0002\u0002\u0002ύώ\u0003\u0002\u0002\u0002ώϒ\u0003\u0002\u0002\u0002Ϗύ\u0003\u0002\u0002\u0002ϐϑ\u0007\u0094\u0002\u0002ϑϓ\u0005P)\u0002ϒϐ\u0003\u0002\u0002\u0002ϒϓ\u0003\u0002\u0002\u0002ϓϦ\u0003\u0002\u0002\u0002ϔϕ\u0007o\u0002\u0002ϕϖ\u0007*\u0002\u0002ϖϛ\u0005^0\u0002ϗϘ\u0007\u0007\u0002\u0002ϘϚ\u0005^0\u0002ϙϗ\u0003\u0002\u0002\u0002Ϛϝ\u0003\u0002\u0002\u0002ϛϙ\u0003\u0002\u0002\u0002ϛϜ\u0003\u0002\u0002\u0002Ϝϟ\u0003\u0002\u0002\u0002ϝϛ\u0003\u0002\u0002\u0002Ϟϔ\u0003\u0002\u0002\u0002Ϟϟ\u0003\u0002\u0002\u0002ϟϠ\u0003\u0002\u0002\u0002Ϡϡ\u0007d\u0002\u0002ϡϤ\u0005P)\u0002Ϣϣ\t\u0004\u0002\u0002ϣϥ\u0005P)\u0002ϤϢ\u0003\u0002\u0002\u0002Ϥϥ\u0003\u0002\u0002\u0002ϥϧ\u0003\u0002\u0002\u0002ϦϞ\u0003\u0002\u0002\u0002Ϧϧ\u0003\u0002\u0002\u0002ϧE\u0003\u0002\u0002\u0002Ϩϩ\u0007\u008f\u0002\u0002ϩG\u0003\u0002\u0002\u0002ϪϬ\u0005\u0090I\u0002ϫϭ\u0005J&\u0002Ϭϫ\u0003\u0002\u0002\u0002Ϭϭ\u0003\u0002\u0002\u0002ϭϱ\u0003\u0002\u0002\u0002Ϯϰ\u0005L'\u0002ϯϮ\u0003\u0002\u0002\u0002ϰϳ\u0003\u0002\u0002\u0002ϱϯ\u0003\u0002\u0002\u0002ϱϲ\u0003\u0002\u0002\u0002ϲI\u0003\u0002\u0002\u0002ϳϱ\u0003\u0002\u0002\u0002ϴ϶\u0005\u0080A\u0002ϵϴ\u0003\u0002\u0002\u0002϶Ϸ\u0003\u0002\u0002\u0002Ϸϸ\u0003\u0002\u0002\u0002Ϸϵ\u0003\u0002\u0002\u0002ϸЃ\u0003\u0002\u0002\u0002ϹϺ\u0007\u0005\u0002\u0002Ϻϻ\u0005r:\u0002ϻϼ\u0007\u0006\u0002\u0002ϼЄ\u0003\u0002\u0002\u0002ϽϾ\u0007\u0005\u0002\u0002ϾϿ\u0005r:\u0002ϿЀ\u0007\u0007\u0002\u0002ЀЁ\u0005r:\u0002ЁЂ\u0007\u0006\u0002\u0002ЂЄ\u0003\u0002\u0002\u0002ЃϹ\u0003\u0002\u0002\u0002ЃϽ\u0003\u0002\u0002\u0002ЃЄ\u0003\u0002\u0002\u0002ЄK\u0003\u0002\u0002\u0002ЅІ\u00073\u0002\u0002ІЈ\u0005\u0080A\u0002ЇЅ\u0003\u0002\u0002\u0002ЇЈ\u0003\u0002\u0002\u0002ЈЪ\u0003\u0002\u0002\u0002ЉЊ\u0007s\u0002\u0002ЊЌ\u0007a\u0002\u0002ЋЍ\t\u0007\u0002\u0002ЌЋ\u0003\u0002\u0002\u0002ЌЍ\u0003\u0002\u0002\u0002ЍЎ\u0003\u0002\u0002\u0002ЎА\u0005N(\u0002ЏБ\u0007&\u0002\u0002АЏ\u0003\u0002\u0002\u0002АБ\u0003\u0002\u0002\u0002БЫ\u0003\u0002\u0002\u0002ВД\u0007h\u0002\u0002ГВ\u0003\u0002\u0002\u0002ГД\u0003\u0002\u0002\u0002ДЕ\u0003\u0002\u0002\u0002ЕЖ\u0007j\u0002\u0002ЖЫ\u0005N(\u0002ЗИ\u0007\u008c\u0002\u0002ИЫ\u0005N(\u0002ЙК\u0007.\u0002\u0002КЛ\u0007\u0005\u0002\u0002ЛМ\u0005P)\u0002МН\u0007\u0006\u0002\u0002НЫ\u0003\u0002\u0002\u0002ОХ\u0007:\u0002\u0002ПЦ\u0005r:\u0002РЦ\u0005t;\u0002СТ\u0007\u0005\u0002\u0002ТУ\u0005P)\u0002УФ\u0007\u0006\u0002\u0002ФЦ\u0003\u0002\u0002\u0002ХП\u0003\u0002\u0002\u0002ХР\u0003\u0002\u0002\u0002ХС\u0003\u0002\u0002\u0002ЦЫ\u0003\u0002\u0002\u0002ЧШ\u0007/\u0002\u0002ШЫ\u0005\u0092J\u0002ЩЫ\u0005R*\u0002ЪЉ\u0003\u0002\u0002\u0002ЪГ\u0003\u0002\u0002\u0002ЪЗ\u0003\u0002\u0002\u0002ЪЙ\u0003\u0002\u0002\u0002ЪО\u0003\u0002\u0002\u0002ЪЧ\u0003\u0002\u0002\u0002ЪЩ\u0003\u0002\u0002\u0002ЫM\u0003\u0002\u0002\u0002ЬЭ\u0007m\u0002\u0002ЭЮ\u00072\u0002\u0002Юа\t\b\u0002\u0002ЯЬ\u0003\u0002\u0002\u0002Яа\u0003\u0002\u0002\u0002аO\u0003\u0002\u0002\u0002бв\b)\u0001\u0002вѾ\u0005t;\u0002гѾ\u0007\u0099\u0002\u0002де\u0005\u0084C\u0002еж\u0007\u0004\u0002\u0002жи\u0003\u0002\u0002\u0002зд\u0003\u0002\u0002\u0002зи\u0003\u0002\u0002\u0002ий\u0003\u0002\u0002\u0002йк\u0005\u008aF\u0002кл\u0007\u0004\u0002\u0002лн\u0003\u0002\u0002\u0002мз\u0003\u0002\u0002\u0002мн\u0003\u0002\u0002\u0002но\u0003\u0002\u0002\u0002оѾ\u0005\u0090I\u0002пр\u0005v<\u0002рс\u0005P)\u0017сѾ\u0003\u0002\u0002\u0002ту\u0005\u0082B\u0002уѐ\u0007\u0005\u0002\u0002фц\u0007@\u0002\u0002хф\u0003\u0002\u0002\u0002хц\u0003\u0002\u0002\u0002цч\u0003\u0002\u0002\u0002чь\u0005P)\u0002шщ\u0007\u0007\u0002\u0002щы\u0005P)\u0002ъш\u0003\u0002\u0002\u0002ыю\u0003\u0002\u0002\u0002ьъ\u0003\u0002\u0002\u0002ьэ\u0003\u0002\u0002\u0002эё\u0003\u0002\u0002\u0002юь\u0003\u0002\u0002\u0002яё\u0007\t\u0002\u0002ѐх\u0003\u0002\u0002\u0002ѐя\u0003\u0002\u0002\u0002ѐё\u0003\u0002\u0002\u0002ёђ\u0003\u0002\u0002\u0002ђѓ\u0007\u0006\u0002\u0002ѓѾ\u0003\u0002\u0002\u0002єѕ\u0007\u0005\u0002\u0002ѕі\u0005P)\u0002ії\u0007\u0006\u0002\u0002їѾ\u0003\u0002\u0002\u0002јљ\u0007-\u0002\u0002љњ\u0007\u0005\u0002\u0002њћ\u0005P)\u0002ћќ\u0007#\u0002\u0002ќѝ\u0005J&\u0002ѝў\u0007\u0006\u0002\u0002ўѾ\u0003\u0002\u0002\u0002џѡ\u0007h\u0002\u0002Ѡџ\u0003\u0002\u0002\u0002Ѡѡ\u0003\u0002\u0002\u0002ѡѢ\u0003\u0002\u0002\u0002ѢѤ\u0007H\u0002\u0002ѣѠ\u0003\u0002\u0002\u0002ѣѤ\u0003\u0002\u0002\u0002Ѥѥ\u0003\u0002\u0002\u0002ѥѦ\u0007\u0005\u0002\u0002Ѧѧ\u0005> \u0002ѧѨ\u0007\u0006\u0002\u0002ѨѾ\u0003\u0002\u0002\u0002ѩѫ\u0007,\u0002\u0002ѪѬ\u0005P)\u0002ѫѪ\u0003\u0002\u0002\u0002ѫѬ\u0003\u0002\u0002\u0002ѬѲ\u0003\u0002\u0002\u0002ѭѮ\u0007\u0093\u0002\u0002Ѯѯ\u0005P)\u0002ѯѰ\u0007\u0087\u0002\u0002Ѱѱ\u0005P)\u0002ѱѳ\u0003\u0002\u0002\u0002Ѳѭ\u0003\u0002\u0002\u0002ѳѴ\u0003\u0002\u0002\u0002ѴѲ\u0003\u0002\u0002\u0002Ѵѵ\u0003\u0002\u0002\u0002ѵѸ\u0003\u0002\u0002\u0002Ѷѷ\u0007C\u0002\u0002ѷѹ\u0005P)\u0002ѸѶ\u0003\u0002\u0002\u0002Ѹѹ\u0003\u0002\u0002\u0002ѹѺ\u0003\u0002\u0002\u0002Ѻѻ\u0007D\u0002\u0002ѻѾ\u0003\u0002\u0002\u0002ѼѾ\u0005T+\u0002ѽб\u0003\u0002\u0002\u0002ѽг\u0003\u0002\u0002\u0002ѽм\u0003\u0002\u0002\u0002ѽп\u0003\u0002\u0002\u0002ѽт\u0003\u0002\u0002\u0002ѽє\u0003\u0002\u0002\u0002ѽј\u0003\u0002\u0002\u0002ѽѣ\u0003\u0002\u0002\u0002ѽѩ\u0003\u0002\u0002\u0002ѽѼ\u0003\u0002\u0002\u0002ѾӖ\u0003\u0002\u0002\u0002ѿҀ\f\u0016\u0002\u0002Ҁҁ\u0007\r\u0002\u0002ҁӕ\u0005P)\u0017҂҃\f\u0015\u0002\u0002҃҄\t\t\u0002\u0002҄ӕ\u0005P)\u0016҅҆\f\u0014\u0002\u0002҆҇\t\n\u0002\u0002҇ӕ\u0005P)\u0015҈҉\f\u0013\u0002\u0002҉Ҋ\t\u000b\u0002\u0002Ҋӕ\u0005P)\u0014ҋҌ\f\u0012\u0002\u0002Ҍҍ\t\f\u0002\u0002ҍӕ\u0005P)\u0013Ҏҏ\f\u0011\u0002\u0002ҏҐ\t\r\u0002\u0002Ґӕ\u0005P)\u0012ґҒ\f\u000f\u0002\u0002Ғғ\u0007\"\u0002\u0002ғӕ\u0005P)\u0010Ҕҕ\f\u000e\u0002\u0002ҕҖ\u0007n\u0002\u0002Җӕ\u0005P)\u000fҗҘ\f\u0007\u0002\u0002ҘҚ\u0007^\u0002\u0002ҙқ\u0007h\u0002\u0002Қҙ\u0003\u0002\u0002\u0002Ққ\u0003\u0002\u0002\u0002қҜ\u0003\u0002\u0002\u0002Ҝӕ\u0005P)\bҝҟ\f\u0006\u0002\u0002ҞҠ\u0007h\u0002\u0002ҟҞ\u0003\u0002\u0002\u0002ҟҠ\u0003\u0002\u0002\u0002Ҡҡ\u0003\u0002\u0002\u0002ҡҢ\u0007)\u0002\u0002Ңң\u0005P)\u0002ңҤ\u0007\"\u0002\u0002Ҥҥ\u0005P)\u0007ҥӕ\u0003\u0002\u0002\u0002ҦҨ\f\u0010\u0002\u0002ҧҩ\u0007h\u0002\u0002Ҩҧ\u0003\u0002\u0002\u0002Ҩҩ\u0003\u0002\u0002\u0002ҩҪ\u0003\u0002\u0002\u0002ҪҾ\u0007U\u0002\u0002ҫҵ\u0007\u0005\u0002\u0002ҬҶ\u0005> \u0002ҭҲ\u0005P)\u0002Үү\u0007\u0007\u0002\u0002үұ\u0005P)\u0002ҰҮ\u0003\u0002\u0002\u0002ұҴ\u0003\u0002\u0002\u0002ҲҰ\u0003\u0002\u0002\u0002Ҳҳ\u0003\u0002\u0002\u0002ҳҶ\u0003\u0002\u0002\u0002ҴҲ\u0003\u0002\u0002\u0002ҵҬ\u0003\u0002\u0002\u0002ҵҭ\u0003\u0002\u0002\u0002ҵҶ\u0003\u0002\u0002\u0002Ҷҷ\u0003\u0002\u0002\u0002ҷҿ\u0007\u0006\u0002\u0002Ҹҹ\u0005\u0084C\u0002ҹҺ\u0007\u0004\u0002\u0002ҺҼ\u0003\u0002\u0002\u0002һҸ\u0003\u0002\u0002\u0002һҼ\u0003\u0002\u0002\u0002Ҽҽ\u0003\u0002\u0002\u0002ҽҿ\u0005\u008aF\u0002Ҿҫ\u0003\u0002\u0002\u0002Ҿһ\u0003\u0002\u0002\u0002ҿӕ\u0003\u0002\u0002\u0002ӀӁ\f\n\u0002\u0002Ӂӂ\u0007/\u0002\u0002ӂӕ\u0005\u0092J\u0002ӃӅ\f\t\u0002\u0002ӄӆ\u0007h\u0002\u0002Ӆӄ\u0003\u0002\u0002\u0002Ӆӆ\u0003\u0002\u0002\u0002ӆӇ\u0003\u0002\u0002\u0002Ӈӈ\t\u000e\u0002\u0002ӈӋ\u0005P)\u0002Ӊӊ\u0007E\u0002\u0002ӊӌ\u0005P)\u0002ӋӉ\u0003\u0002\u0002\u0002Ӌӌ\u0003\u0002\u0002\u0002ӌӕ\u0003\u0002\u0002\u0002ӍӒ\f\b\u0002\u0002ӎӓ\u0007_\u0002\u0002ӏӓ\u0007i\u0002\u0002Ӑӑ\u0007h\u0002\u0002ӑӓ\u0007j\u0002\u0002Ӓӎ\u0003\u0002\u0002\u0002Ӓӏ\u0003\u0002\u0002\u0002ӒӐ\u0003\u0002\u0002\u0002ӓӕ\u0003\u0002\u0002\u0002Ӕѿ\u0003\u0002\u0002\u0002Ӕ҂\u0003\u0002\u0002\u0002Ӕ҅\u0003\u0002\u0002\u0002Ӕ҈\u0003\u0002\u0002\u0002Ӕҋ\u0003\u0002\u0002\u0002ӔҎ\u0003\u0002\u0002\u0002Ӕґ\u0003\u0002\u0002\u0002ӔҔ\u0003\u0002\u0002\u0002Ӕҗ\u0003\u0002\u0002\u0002Ӕҝ\u0003\u0002\u0002\u0002ӔҦ\u0003\u0002\u0002\u0002ӔӀ\u0003\u0002\u0002\u0002ӔӃ\u0003\u0002\u0002\u0002ӔӍ\u0003\u0002\u0002\u0002ӕӘ\u0003\u0002\u0002\u0002ӖӔ\u0003\u0002\u0002\u0002Ӗӗ\u0003\u0002\u0002\u0002ӗQ\u0003\u0002\u0002\u0002ӘӖ\u0003\u0002\u0002\u0002әӚ\u0007w\u0002\u0002ӚӦ\u0005\u0094K\u0002ӛӜ\u0007\u0005\u0002\u0002Ӝӡ\u0005\u0090I\u0002ӝӞ\u0007\u0007\u0002\u0002ӞӠ\u0005\u0090I\u0002ӟӝ\u0003\u0002\u0002\u0002Ӡӣ\u0003\u0002\u0002\u0002ӡӟ\u0003\u0002\u0002\u0002ӡӢ\u0003\u0002\u0002\u0002ӢӤ\u0003\u0002\u0002\u0002ӣӡ\u0003\u0002\u0002\u0002Ӥӥ\u0007\u0006\u0002\u0002ӥӧ\u0003\u0002\u0002\u0002Ӧӛ\u0003\u0002\u0002\u0002Ӧӧ\u0003\u0002\u0002\u0002ӧӺ\u0003\u0002\u0002\u0002Өө\u0007m\u0002\u0002өӲ\t\u000f\u0002\u0002Ӫӫ\u0007\u0083\u0002\u0002ӫӳ\u0007j\u0002\u0002Ӭӭ\u0007\u0083\u0002\u0002ӭӳ\u0007:\u0002\u0002Ӯӳ\u0007+\u0002\u0002ӯӳ\u0007}\u0002\u0002Ӱӱ\u0007g\u0002\u0002ӱӳ\u0007\u001c\u0002\u0002ӲӪ\u0003\u0002\u0002\u0002ӲӬ\u0003\u0002\u0002\u0002ӲӮ\u0003\u0002\u0002\u0002Ӳӯ\u0003\u0002\u0002\u0002ӲӰ\u0003\u0002\u0002\u0002ӳӷ\u0003\u0002\u0002\u0002Ӵӵ\u0007e\u0002\u0002ӵӷ\u0005\u0080A\u0002ӶӨ\u0003\u0002\u0002\u0002ӶӴ\u0003\u0002\u0002\u0002ӷӹ\u0003\u0002\u0002\u0002ӸӶ\u0003\u0002\u0002\u0002ӹӼ\u0003\u0002\u0002\u0002ӺӸ\u0003\u0002\u0002\u0002Ӻӻ\u0003\u0002\u0002\u0002ӻԇ\u0003\u0002\u0002\u0002ӼӺ\u0003\u0002\u0002\u0002ӽӿ\u0007h\u0002\u0002Ӿӽ\u0003\u0002\u0002\u0002Ӿӿ\u0003\u0002\u0002\u0002ӿԀ\u0003\u0002\u0002\u0002Ԁԅ\u0007;\u0002\u0002ԁԂ\u0007X\u0002\u0002ԂԆ\u0007<\u0002\u0002ԃԄ\u0007X\u0002\u0002ԄԆ\u0007T\u0002\u0002ԅԁ\u0003\u0002\u0002\u0002ԅԃ\u0003\u0002\u0002\u0002ԅԆ\u0003\u0002\u0002\u0002ԆԈ\u0003\u0002\u0002\u0002ԇӾ\u0003\u0002\u0002\u0002ԇԈ\u0003\u0002\u0002\u0002ԈS\u0003\u0002\u0002\u0002ԉԊ\u0007u\u0002\u0002Ԋԏ\u0007\u0005\u0002\u0002ԋԐ\u0007S\u0002\u0002Ԍԍ\t\u0010\u0002\u0002ԍԎ\u0007\u0007\u0002\u0002ԎԐ\u0005x=\u0002ԏԋ\u0003\u0002\u0002\u0002ԏԌ\u0003\u0002\u0002\u0002Ԑԑ\u0003\u0002\u0002\u0002ԑԒ\u0007\u0006\u0002\u0002ԒU\u0003\u0002\u0002\u0002ԓԖ\u0005\u0090I\u0002Ԕԕ\u0007/\u0002\u0002ԕԗ\u0005\u0092J\u0002ԖԔ\u0003\u0002\u0002\u0002Ԗԗ\u0003\u0002\u0002\u0002ԗԙ\u0003\u0002\u0002\u0002ԘԚ\t\u0007\u0002\u0002ԙԘ\u0003\u0002\u0002\u0002ԙԚ\u0003\u0002\u0002\u0002ԚW\u0003\u0002\u0002\u0002ԛԜ\u00073\u0002\u0002ԜԞ\u0005\u0080A\u0002ԝԛ\u0003\u0002\u0002\u0002ԝԞ\u0003\u0002\u0002\u0002ԞՃ\u0003\u0002\u0002\u0002ԟԠ\u0007s\u0002\u0002Ԡԣ\u0007a\u0002\u0002ԡԣ\u0007\u008c\u0002\u0002Ԣԟ\u0003\u0002\u0002\u0002Ԣԡ\u0003\u0002\u0002\u0002ԣԤ\u0003\u0002\u0002\u0002Ԥԥ\u0007\u0005\u0002\u0002ԥԪ\u0005V,\u0002Ԧԧ\u0007\u0007\u0002\u0002ԧԩ\u0005V,\u0002ԨԦ\u0003\u0002\u0002\u0002ԩԬ\u0003\u0002\u0002\u0002ԪԨ\u0003\u0002\u0002\u0002Ԫԫ\u0003\u0002\u0002\u0002ԫԭ\u0003\u0002\u0002\u0002ԬԪ\u0003\u0002\u0002\u0002ԭԮ\u0007\u0006\u0002\u0002Ԯԯ\u0005N(\u0002ԯՄ\u0003\u0002\u0002\u0002\u0530Ա\u0007.\u0002\u0002ԱԲ\u0007\u0005\u0002\u0002ԲԳ\u0005P)\u0002ԳԴ\u0007\u0006\u0002\u0002ԴՄ\u0003\u0002\u0002\u0002ԵԶ\u0007L\u0002\u0002ԶԷ\u0007a\u0002\u0002ԷԸ\u0007\u0005\u0002\u0002ԸԽ\u0005\u0090I\u0002ԹԺ\u0007\u0007\u0002\u0002ԺԼ\u0005\u0090I\u0002ԻԹ\u0003\u0002\u0002\u0002ԼԿ\u0003\u0002\u0002\u0002ԽԻ\u0003\u0002\u0002\u0002ԽԾ\u0003\u0002\u0002\u0002ԾՀ\u0003\u0002\u0002\u0002ԿԽ\u0003\u0002\u0002\u0002ՀՁ\u0007\u0006\u0002\u0002ՁՂ\u0005R*\u0002ՂՄ\u0003\u0002\u0002\u0002ՃԢ\u0003\u0002\u0002\u0002Ճ\u0530\u0003\u0002\u0002\u0002ՃԵ\u0003\u0002\u0002\u0002ՄY\u0003\u0002\u0002\u0002ՅՇ\u0007\u0095\u0002\u0002ՆՈ\u0007v\u0002\u0002ՇՆ\u0003\u0002\u0002\u0002ՇՈ\u0003\u0002\u0002\u0002ՈՉ\u0003\u0002\u0002\u0002ՉՎ\u0005b2\u0002ՊՋ\u0007\u0007\u0002\u0002ՋՍ\u0005b2\u0002ՌՊ\u0003\u0002\u0002\u0002ՍՐ\u0003\u0002\u0002\u0002ՎՌ\u0003\u0002\u0002\u0002ՎՏ\u0003\u0002\u0002\u0002Տ[\u0003\u0002\u0002\u0002ՐՎ\u0003\u0002\u0002\u0002ՑՒ\u0005\u0084C\u0002ՒՓ\u0007\u0004\u0002\u0002ՓՕ\u0003\u0002\u0002\u0002ՔՑ\u0003\u0002\u0002\u0002ՔՕ\u0003\u0002\u0002\u0002ՕՖ\u0003\u0002\u0002\u0002Ֆ՜\u0005\u008aF\u0002\u0557\u0558\u0007W\u0002\u0002\u0558ՙ\u0007*\u0002\u0002ՙ՝\u0005\u0096L\u0002՚՛\u0007h\u0002\u0002՛՝\u0007W\u0002\u0002՜\u0557\u0003\u0002\u0002\u0002՜՚\u0003\u0002\u0002\u0002՜՝\u0003\u0002\u0002\u0002՝]\u0003\u0002\u0002\u0002՞ա\u0005P)\u0002՟ՠ\u0007/\u0002\u0002ՠբ\u0005\u0092J\u0002ա՟\u0003\u0002\u0002\u0002աբ\u0003\u0002\u0002\u0002բդ\u0003\u0002\u0002\u0002գե\t\u0007\u0002\u0002դգ\u0003\u0002\u0002\u0002դե\u0003\u0002\u0002\u0002ե_\u0003\u0002\u0002\u0002զժ\u0005r:\u0002էժ\u0005\u0080A\u0002ըժ\u0007\u009a\u0002\u0002թզ\u0003\u0002\u0002\u0002թէ\u0003\u0002\u0002\u0002թը\u0003\u0002\u0002\u0002ժa\u0003\u0002\u0002\u0002իշ\u0005\u008aF\u0002լխ\u0007\u0005\u0002\u0002խղ\u0005\u0090I\u0002ծկ\u0007\u0007\u0002\u0002կձ\u0005\u0090I\u0002հծ\u0003\u0002\u0002\u0002ձմ\u0003\u0002\u0002\u0002ղհ\u0003\u0002\u0002\u0002ղճ\u0003\u0002\u0002\u0002ճյ\u0003\u0002\u0002\u0002մղ\u0003\u0002\u0002\u0002յն\u0007\u0006\u0002\u0002նո\u0003\u0002\u0002\u0002շլ\u0003\u0002\u0002\u0002շո\u0003\u0002\u0002\u0002ոչ\u0003\u0002\u0002\u0002չպ\u0007#\u0002\u0002պջ\u0007\u0005\u0002\u0002ջռ\u0005> \u0002ռս\u0007\u0006\u0002\u0002սc\u0003\u0002\u0002\u0002վ\u058b\u0007\t\u0002\u0002տր\u0005\u008aF\u0002րց\u0007\u0004\u0002\u0002ցւ\u0007\t\u0002\u0002ւ\u058b\u0003\u0002\u0002\u0002փֈ\u0005P)\u0002քֆ\u0007#\u0002\u0002օք\u0003\u0002\u0002\u0002օֆ\u0003\u0002\u0002\u0002ֆև\u0003\u0002\u0002\u0002և։\u0005|?\u0002ֈօ\u0003\u0002\u0002\u0002ֈ։\u0003\u0002\u0002\u0002։\u058b\u0003\u0002\u0002\u0002֊վ\u0003\u0002\u0002\u0002֊տ\u0003\u0002\u0002\u0002֊փ\u0003\u0002\u0002\u0002\u058be\u0003\u0002\u0002\u0002\u058c֍\u0005\u0086D\u0002֍֎\u0007\u0004\u0002\u0002֎\u0590\u0003\u0002\u0002\u0002֏\u058c\u0003\u0002\u0002\u0002֏\u0590\u0003\u0002\u0002\u0002\u0590֑\u0003\u0002\u0002\u0002֑֖\u0005\u008aF\u0002֒֔\u0007#\u0002\u0002֓֒\u0003\u0002\u0002\u0002֓֔\u0003\u0002\u0002\u0002֔֕\u0003\u0002\u0002\u0002֕֗\u0005¢R\u0002֖֓\u0003\u0002\u0002\u0002֖֗\u0003\u0002\u0002\u0002֗֝\u0003\u0002\u0002\u0002֘֙\u0007W\u0002\u0002֚֙\u0007*\u0002\u0002֚֞\u0005\u0096L\u0002֛֜\u0007h\u0002\u0002֜֞\u0007W\u0002\u0002֝֘\u0003\u0002\u0002\u0002֛֝\u0003\u0002\u0002\u0002֝֞\u0003\u0002\u0002\u0002֞\u05cf\u0003\u0002\u0002\u0002֟֠\u0005\u0086D\u0002֠֡\u0007\u0004\u0002\u0002֣֡\u0003\u0002\u0002\u0002֢֟\u0003\u0002\u0002\u0002֢֣\u0003\u0002\u0002\u0002֣֤\u0003\u0002\u0002\u0002֤֥\u0005\u0088E\u0002֥֮\u0007\u0005\u0002\u0002֦֫\u0005P)\u0002֧֨\u0007\u0007\u0002\u0002֪֨\u0005P)\u0002֧֩\u0003\u0002\u0002\u0002֪֭\u0003\u0002\u0002\u0002֫֩\u0003\u0002\u0002\u0002֫֬\u0003\u0002\u0002\u0002֬֯\u0003\u0002\u0002\u0002֭֫\u0003\u0002\u0002\u0002֦֮\u0003\u0002\u0002\u0002֮֯\u0003\u0002\u0002\u0002ְ֯\u0003\u0002\u0002\u0002ְֵ\u0007\u0006\u0002\u0002ֱֳ\u0007#\u0002\u0002ֱֲ\u0003\u0002\u0002\u0002ֲֳ\u0003\u0002\u0002\u0002ֳִ\u0003\u0002\u0002\u0002ִֶ\u0005¢R\u0002ֲֵ\u0003\u0002\u0002\u0002ֵֶ\u0003\u0002\u0002\u0002ֶ\u05cf\u0003\u0002\u0002\u0002ַׁ\u0007\u0005\u0002\u0002ָֽ\u0005f4\u0002ֹֺ\u0007\u0007\u0002\u0002ֺּ\u0005f4\u0002ֹֻ\u0003\u0002\u0002\u0002ּֿ\u0003\u0002\u0002\u0002ֻֽ\u0003\u0002\u0002\u0002ֽ־\u0003\u0002\u0002\u0002־ׂ\u0003\u0002\u0002\u0002ֽֿ\u0003\u0002\u0002\u0002׀ׂ\u0005h5\u0002ָׁ\u0003\u0002\u0002\u0002ׁ׀\u0003\u0002\u0002\u0002ׂ׃\u0003\u0002\u0002\u0002׃ׄ\u0007\u0006\u0002\u0002ׄ\u05cf\u0003\u0002\u0002\u0002ׅ׆\u0007\u0005\u0002\u0002׆ׇ\u0005> \u0002ׇ\u05cc\u0007\u0006\u0002\u0002\u05c8\u05ca\u0007#\u0002\u0002\u05c9\u05c8\u0003\u0002\u0002\u0002\u05c9\u05ca\u0003\u0002\u0002\u0002\u05ca\u05cb\u0003\u0002\u0002\u0002\u05cb\u05cd\u0005¢R\u0002\u05cc\u05c9\u0003\u0002\u0002\u0002\u05cc\u05cd\u0003\u0002\u0002\u0002\u05cd\u05cf\u0003\u0002\u0002\u0002\u05ce֏\u0003\u0002\u0002\u0002\u05ce֢\u0003\u0002\u0002\u0002\u05ceַ\u0003\u0002\u0002\u0002\u05ceׅ\u0003\u0002\u0002\u0002\u05cfg\u0003\u0002\u0002\u0002אח\u0005f4\u0002בג\u0005j6\u0002גד\u0005f4\u0002דה\u0005l7\u0002הז\u0003\u0002\u0002\u0002וב\u0003\u0002\u0002\u0002זי\u0003\u0002\u0002\u0002חו\u0003\u0002\u0002\u0002חט\u0003\u0002\u0002\u0002טi\u0003\u0002\u0002\u0002יח\u0003\u0002\u0002\u0002ךר\u0007\u0007\u0002\u0002כם\u0007f\u0002\u0002לכ\u0003\u0002\u0002\u0002לם\u0003\u0002\u0002\u0002םפ\u0003\u0002\u0002\u0002מנ\u0007b\u0002\u0002ןס\u0007p\u0002\u0002נן\u0003\u0002\u0002\u0002נס\u0003\u0002\u0002\u0002סץ\u0003\u0002\u0002\u0002עץ\u0007Y\u0002\u0002ףץ\u00075\u0002\u0002פמ\u0003\u0002\u0002\u0002פע\u0003\u0002\u0002\u0002פף\u0003\u0002\u0002\u0002פץ\u0003\u0002\u0002\u0002ץצ\u0003\u0002\u0002\u0002צר\u0007`\u0002\u0002קך\u0003\u0002\u0002\u0002קל\u0003\u0002\u0002\u0002רk\u0003\u0002\u0002\u0002שת\u0007m\u0002\u0002ת\u05f8\u0005P)\u0002\u05eb\u05ec\u0007\u008e\u0002\u0002\u05ec\u05ed\u0007\u0005\u0002\u0002\u05edײ\u0005\u0090I\u0002\u05eeׯ\u0007\u0007\u0002\u0002ׯױ\u0005\u0090I\u0002װ\u05ee\u0003\u0002\u0002\u0002ױ״\u0003\u0002\u0002\u0002ײװ\u0003\u0002\u0002\u0002ײ׳\u0003\u0002\u0002\u0002׳\u05f5\u0003\u0002\u0002\u0002״ײ\u0003\u0002\u0002\u0002\u05f5\u05f6\u0007\u0006\u0002\u0002\u05f6\u05f8\u0003\u0002\u0002\u0002\u05f7ש\u0003\u0002\u0002\u0002\u05f7\u05eb\u0003\u0002\u0002\u0002\u05f7\u05f8\u0003\u0002\u0002\u0002\u05f8m\u0003\u0002\u0002\u0002\u05f9\u05fb\u0007\u0082\u0002\u0002\u05fa\u05fc\t\u0006\u0002\u0002\u05fb\u05fa\u0003\u0002\u0002\u0002\u05fb\u05fc\u0003\u0002\u0002\u0002\u05fc\u05fd\u0003\u0002\u0002\u0002\u05fd\u0602\u0005d3\u0002\u05fe\u05ff\u0007\u0007\u0002\u0002\u05ff\u0601\u0005d3\u0002\u0600\u05fe\u0003\u0002\u0002\u0002\u0601\u0604\u0003\u0002\u0002\u0002\u0602\u0600\u0003\u0002\u0002\u0002\u0602\u0603\u0003\u0002\u0002\u0002\u0603ؑ\u0003\u0002\u0002\u0002\u0604\u0602\u0003\u0002\u0002\u0002\u0605؏\u0007M\u0002\u0002؆؋\u0005f4\u0002؇؈\u0007\u0007\u0002\u0002؈؊\u0005f4\u0002؉؇\u0003\u0002\u0002\u0002؊؍\u0003\u0002\u0002\u0002؋؉\u0003\u0002\u0002\u0002؋،\u0003\u0002\u0002\u0002،ؐ\u0003\u0002\u0002\u0002؍؋\u0003\u0002\u0002\u0002؎ؐ\u0005h5\u0002؏؆\u0003\u0002\u0002\u0002؏؎\u0003\u0002\u0002\u0002ؐؒ\u0003\u0002\u0002\u0002ؑ\u0605\u0003\u0002\u0002\u0002ؑؒ\u0003\u0002\u0002\u0002ؒؕ\u0003\u0002\u0002\u0002ؓؔ\u0007\u0094\u0002\u0002ؔؖ\u0005P)\u0002ؕؓ\u0003\u0002\u0002\u0002ؕؖ\u0003\u0002\u0002\u0002ؖإ\u0003\u0002\u0002\u0002ؘؗ\u0007P\u0002\u0002ؘؙ\u0007*\u0002\u0002ؙ؞\u0005P)\u0002ؚ؛\u0007\u0007\u0002\u0002؛\u061d\u0005P)\u0002\u061cؚ\u0003\u0002\u0002\u0002\u061dؠ\u0003\u0002\u0002\u0002؞\u061c\u0003\u0002\u0002\u0002؞؟\u0003\u0002\u0002\u0002؟أ\u0003\u0002\u0002\u0002ؠ؞\u0003\u0002\u0002\u0002ءآ\u0007Q\u0002\u0002آؤ\u0005P)\u0002أء\u0003\u0002\u0002\u0002أؤ\u0003\u0002\u0002\u0002ؤئ\u0003\u0002\u0002\u0002إؗ\u0003\u0002\u0002\u0002إئ\u0003\u0002\u0002\u0002ئل\u0003\u0002\u0002\u0002اب\u0007\u0090\u0002\u0002بة\u0007\u0005\u0002\u0002ةخ\u0005P)\u0002تث\u0007\u0007\u0002\u0002ثح\u0005P)\u0002جت\u0003\u0002\u0002\u0002حذ\u0003\u0002\u0002\u0002خج\u0003\u0002\u0002\u0002خد\u0003\u0002\u0002\u0002در\u0003\u0002\u0002\u0002ذخ\u0003\u0002\u0002\u0002رـ\u0007\u0006\u0002\u0002زس\u0007\u0007\u0002\u0002سش\u0007\u0005\u0002\u0002شع\u0005P)\u0002صض\u0007\u0007\u0002\u0002ضظ\u0005P)\u0002طص\u0003\u0002\u0002\u0002ظػ\u0003\u0002\u0002\u0002عط\u0003\u0002\u0002\u0002عغ\u0003\u0002\u0002\u0002غؼ\u0003\u0002\u0002\u0002ػع\u0003\u0002\u0002\u0002ؼؽ\u0007\u0006\u0002\u0002ؽؿ\u0003\u0002\u0002\u0002ؾز\u0003\u0002\u0002\u0002ؿق\u0003\u0002\u0002\u0002ـؾ\u0003\u0002\u0002\u0002ـف\u0003\u0002\u0002\u0002فل\u0003\u0002\u0002\u0002قـ\u0003\u0002\u0002\u0002ك\u05f9\u0003\u0002\u0002\u0002كا\u0003\u0002\u0002\u0002لo\u0003\u0002\u0002\u0002مً\u0007\u008b\u0002\u0002نه\u0007\u008b\u0002\u0002هً\u0007\u001f\u0002\u0002وً\u0007\\\u0002\u0002ىً\u0007F\u0002\u0002يم\u0003\u0002\u0002\u0002ين\u0003\u0002\u0002\u0002يو\u0003\u0002\u0002\u0002يى\u0003\u0002\u0002\u0002ًq\u0003\u0002\u0002\u0002ٌَ\t\n\u0002\u0002ٌٍ\u0003\u0002\u0002\u0002ٍَ\u0003\u0002\u0002\u0002َُ\u0003\u0002\u0002\u0002ُِ\u0007\u0098\u0002\u0002ِs\u0003\u0002\u0002\u0002ّْ\t\u0011\u0002\u0002ْu\u0003\u0002\u0002\u0002ٓٔ\t\u0012\u0002\u0002ٔw\u0003\u0002\u0002\u0002ٕٖ\u0007\u009a\u0002\u0002ٖy\u0003\u0002\u0002\u0002ٗٚ\u0005P)\u0002٘ٚ\u0005H%\u0002ٙٗ\u0003\u0002\u0002\u0002ٙ٘\u0003\u0002\u0002\u0002ٚ{\u0003\u0002\u0002\u0002ٜٛ\t\u0013\u0002\u0002ٜ}\u0003\u0002\u0002\u0002ٝٞ\t\u0014\u0002\u0002ٞ\u007f\u0003\u0002\u0002\u0002ٟ٠\u0005¦T\u0002٠\u0081\u0003\u0002\u0002\u0002١٢\u0005¦T\u0002٢\u0083\u0003\u0002\u0002\u0002٣٤\u0005¦T\u0002٤\u0085\u0003\u0002\u0002\u0002٥٦\u0005¦T\u0002٦\u0087\u0003\u0002\u0002\u0002٧٨\u0005¦T\u0002٨\u0089\u0003\u0002\u0002\u0002٩٪\u0005¦T\u0002٪\u008b\u0003\u0002\u0002\u0002٫٬\u0005¦T\u0002٬\u008d\u0003\u0002\u0002\u0002٭ٮ\u0005¦T\u0002ٮ\u008f\u0003\u0002\u0002\u0002ٯٰ\u0005¦T\u0002ٰ\u0091\u0003\u0002\u0002\u0002ٱٲ\u0005¦T\u0002ٲ\u0093\u0003\u0002\u0002\u0002ٳٴ\u0005¦T\u0002ٴ\u0095\u0003\u0002\u0002\u0002ٵٶ\u0005¦T\u0002ٶ\u0097\u0003\u0002\u0002\u0002ٷٸ\u0005¦T\u0002ٸ\u0099\u0003\u0002\u0002\u0002ٹٺ\u0005¦T\u0002ٺ\u009b\u0003\u0002\u0002\u0002ٻټ\u0005¦T\u0002ټ\u009d\u0003\u0002\u0002\u0002ٽپ\u0005¦T\u0002پ\u009f\u0003\u0002\u0002\u0002ٿڀ\u0005¦T\u0002ڀ¡\u0003\u0002\u0002\u0002ځڈ\u0007\u0097\u0002\u0002ڂڈ\u0007\u009a\u0002\u0002ڃڄ\u0007\u0005\u0002\u0002ڄڅ\u0005¢R\u0002څچ\u0007\u0006\u0002\u0002چڈ\u0003\u0002\u0002\u0002ڇځ\u0003\u0002\u0002\u0002ڇڂ\u0003\u0002\u0002\u0002ڇڃ\u0003\u0002\u0002\u0002ڈ£\u0003\u0002\u0002\u0002ډڊ\u0005¦T\u0002ڊ¥\u0003\u0002\u0002\u0002ڋړ\u0007\u0097\u0002\u0002ڌړ\u0005~@\u0002ڍړ\u0007\u009a\u0002\u0002ڎڏ\u0007\u0005\u0002\u0002ڏڐ\u0005¦T\u0002ڐڑ\u0007\u0006\u0002\u0002ڑړ\u0003\u0002\u0002\u0002ڒڋ\u0003\u0002\u0002\u0002ڒڌ\u0003\u0002\u0002\u0002ڒڍ\u0003\u0002\u0002\u0002ڒڎ\u0003\u0002\u0002\u0002ړ§\u0003\u0002\u0002\u0002ïª¬·¾ÃÉÏÑñøĀăČĐĘĜĞģĥĨĭıĶĿłňŊŎŔřŤŪŮŴŹƂƉƏƓƗƝƢƩƴƷƹƿǅǉǐǖǜǢǧǳǸȃȈȋȒȕȜȥȨȮȰȴȼɁɉɎɖɛɣɨɭɵɿʂʈʊʍʠʦʯʴʽˈˏ˕˛ˤ˫˯˱˵˼˾̵̸̘̞̠̣̫͔͚̂̅̋̾̀̈́͋ͧͬͮ̕͘͞ͷ\u0382ΉΌΏΜΪίβούϒϛϞϤϦϬϱϷЃЇЌАГХЪЯзмхьѐѠѣѫѴѸѽҚҟҨҲҵһҾӅӋӒӔӖӡӦӲӶӺӾԅԇԏԖԙԝԢԪԽՃՇՎՔ՜ադթղշօֈ֊֏ֲֵֽׁ֖֢֮֓֝֫\u05c9\u05cc\u05ceחלנפקײ\u05f7\u05fb\u0602؋؏ؑؕ؞أإخعـكيٍٙڇڒ";
    }

    public ATN getATN() {
        return _ATN;
    }

    public SQLiteParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public final ParseContext parse() throws RecognitionException {
        ParseContext _localctx = new ParseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 0, 0);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(170);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); (_la & -64) == 0 && (1L << _la & -6339801325483589630L) != 0L || (_la - 66 & -64) == 0 && (1L << _la - 66 & 7593139340495028257L) != 0L || (_la - 139 & -64) == 0 && (1L << _la - 139 & 262413L) != 0L; _la = this._input.LA(1)) {
                this.setState(168);
                this._errHandler.sync(this);
                switch (this._input.LA(1)) {
                    case 1:
                    case 30:
                    case 31:
                    case 35:
                    case 38:
                    case 47:
                    case 50:
                    case 59:
                    case 61:
                    case 63:
                    case 66:
                    case 71:
                    case 88:
                    case 112:
                    case 119:
                    case 120:
                    case 122:
                    case 125:
                    case 127:
                    case 128:
                    case 139:
                    case 141:
                    case 142:
                    case 147:
                        this.setState(166);
                        this.sql_stmt_list();
                        break;
                    case 157:
                        this.setState(167);
                        this.error();
                        break;
                    default:
                        throw new NoViableAltException(this);
                }

                this.setState(172);
                this._errHandler.sync(this);
            }

            this.setState(173);
            this.match(-1);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final ErrorContext error() throws RecognitionException {
        ErrorContext _localctx = new ErrorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 2, 1);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(175);
            _localctx.UNEXPECTED_CHAR = this.match(157);
            String var10002 = _localctx.UNEXPECTED_CHAR != null ? _localctx.UNEXPECTED_CHAR.getText() : null;
            throw new RuntimeException("UNEXPECTED_CHAR=" + var10002);
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
        Sql_stmt_listContext _localctx = new Sql_stmt_listContext(this._ctx, this.getState());
        this.enterRule(_localctx, 4, 2);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(181);
            this._errHandler.sync(this);

            int _la;
            for(_la = this._input.LA(1); _la == 1; _la = this._input.LA(1)) {
                this.setState(178);
                this.match(1);
                this.setState(183);
                this._errHandler.sync(this);
            }

            this.setState(184);
            this.sql_stmt();
            this.setState(193);
            this._errHandler.sync(this);

            int _alt;
            for(_alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 4, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 4, this._ctx)) {
                if (_alt == 1) {
                    this.setState(186);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);

                    do {
                        this.setState(185);
                        this.match(1);
                        this.setState(188);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    } while(_la == 1);

                    this.setState(190);
                    this.sql_stmt();
                }

                this.setState(195);
                this._errHandler.sync(this);
            }

            this.setState(199);
            this._errHandler.sync(this);

            for(_alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 5, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 5, this._ctx)) {
                if (_alt == 1) {
                    this.setState(196);
                    this.match(1);
                }

                this.setState(201);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Sql_stmtContext sql_stmt() throws RecognitionException {
        Sql_stmtContext _localctx = new Sql_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 6, 3);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(207);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 71) {
                this.setState(202);
                this.match(71);
                this.setState(205);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 114) {
                    this.setState(203);
                    this.match(114);
                    this.setState(204);
                    this.match(111);
                }
            }

            this.setState(239);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 8, this._ctx)) {
                case 1:
                    this.setState(209);
                    this.alter_table_stmt();
                    break;
                case 2:
                    this.setState(210);
                    this.analyze_stmt();
                    break;
                case 3:
                    this.setState(211);
                    this.attach_stmt();
                    break;
                case 4:
                    this.setState(212);
                    this.begin_stmt();
                    break;
                case 5:
                    this.setState(213);
                    this.commit_stmt();
                    break;
                case 6:
                    this.setState(214);
                    this.compound_select_stmt();
                    break;
                case 7:
                    this.setState(215);
                    this.create_index_stmt();
                    break;
                case 8:
                    this.setState(216);
                    this.create_table_stmt();
                    break;
                case 9:
                    this.setState(217);
                    this.create_trigger_stmt();
                    break;
                case 10:
                    this.setState(218);
                    this.create_view_stmt();
                    break;
                case 11:
                    this.setState(219);
                    this.create_virtual_table_stmt();
                    break;
                case 12:
                    this.setState(220);
                    this.delete_stmt();
                    break;
                case 13:
                    this.setState(221);
                    this.delete_stmt_limited();
                    break;
                case 14:
                    this.setState(222);
                    this.detach_stmt();
                    break;
                case 15:
                    this.setState(223);
                    this.drop_index_stmt();
                    break;
                case 16:
                    this.setState(224);
                    this.drop_table_stmt();
                    break;
                case 17:
                    this.setState(225);
                    this.drop_trigger_stmt();
                    break;
                case 18:
                    this.setState(226);
                    this.drop_view_stmt();
                    break;
                case 19:
                    this.setState(227);
                    this.factored_select_stmt();
                    break;
                case 20:
                    this.setState(228);
                    this.insert_stmt();
                    break;
                case 21:
                    this.setState(229);
                    this.pragma_stmt();
                    break;
                case 22:
                    this.setState(230);
                    this.reindex_stmt();
                    break;
                case 23:
                    this.setState(231);
                    this.release_stmt();
                    break;
                case 24:
                    this.setState(232);
                    this.rollback_stmt();
                    break;
                case 25:
                    this.setState(233);
                    this.savepoint_stmt();
                    break;
                case 26:
                    this.setState(234);
                    this.simple_select_stmt();
                    break;
                case 27:
                    this.setState(235);
                    this.select_stmt();
                    break;
                case 28:
                    this.setState(236);
                    this.update_stmt();
                    break;
                case 29:
                    this.setState(237);
                    this.update_stmt_limited();
                    break;
                case 30:
                    this.setState(238);
                    this.vacuum_stmt();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Alter_table_stmtContext alter_table_stmt() throws RecognitionException {
        Alter_table_stmtContext _localctx = new Alter_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 8, 4);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(241);
            this.match(30);
            this.setState(242);
            this.match(130);
            this.setState(246);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 9, this._ctx)) {
                case 1:
                    this.setState(243);
                    this.database_name();
                    this.setState(244);
                    this.match(2);
                default:
                    this.setState(248);
                    this.table_name();
                    this.setState(257);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case 27:
                            this.setState(252);
                            this.match(27);
                            this.setState(254);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 10, this._ctx)) {
                                case 1:
                                    this.setState(253);
                                    this.match(46);
                                default:
                                    this.setState(256);
                                    this.column_def();
                                    return _localctx;
                            }
                        case 121:
                            this.setState(249);
                            this.match(121);
                            this.setState(250);
                            this.match(134);
                            this.setState(251);
                            this.new_table_name();
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Analyze_stmtContext analyze_stmt() throws RecognitionException {
        Analyze_stmtContext _localctx = new Analyze_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 10, 5);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(259);
            this.match(31);
            this.setState(266);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 12, this._ctx)) {
                case 1:
                    this.setState(260);
                    this.database_name();
                    break;
                case 2:
                    this.setState(261);
                    this.table_or_index_name();
                    break;
                case 3:
                    this.setState(262);
                    this.database_name();
                    this.setState(263);
                    this.match(2);
                    this.setState(264);
                    this.table_or_index_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Attach_stmtContext attach_stmt() throws RecognitionException {
        Attach_stmtContext _localctx = new Attach_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 12, 6);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(268);
            this.match(35);
            this.setState(270);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 13, this._ctx)) {
                case 1:
                    this.setState(269);
                    this.match(55);
                default:
                    this.setState(272);
                    this.expr(0);
                    this.setState(273);
                    this.match(33);
                    this.setState(274);
                    this.database_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Begin_stmtContext begin_stmt() throws RecognitionException {
        Begin_stmtContext _localctx = new Begin_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 14, 7);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(276);
            this.match(38);
            this.setState(278);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if ((_la - 58 & -64) == 0 && (1L << _la - 58 & 16779265L) != 0L) {
                this.setState(277);
                _la = this._input.LA(1);
                if ((_la - 58 & -64) == 0 && (1L << _la - 58 & 16779265L) != 0L) {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                } else {
                    this._errHandler.recoverInline(this);
                }
            }

            this.setState(284);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 135) {
                this.setState(280);
                this.match(135);
                this.setState(282);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 15, this._ctx)) {
                    case 1:
                        this.setState(281);
                        this.transaction_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Commit_stmtContext commit_stmt() throws RecognitionException {
        Commit_stmtContext _localctx = new Commit_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 16, 8);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(286);
            int _la = this._input.LA(1);
            if (_la != 47 && _la != 66) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }

            this.setState(291);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 135) {
                this.setState(287);
                this.match(135);
                this.setState(289);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 17, this._ctx)) {
                    case 1:
                        this.setState(288);
                        this.transaction_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Compound_select_stmtContext compound_select_stmt() throws RecognitionException {
        Compound_select_stmtContext _localctx = new Compound_select_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 18, 9);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(294);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(293);
                this.with_clause();
            }

            this.setState(296);
            this.select_core();
            this.setState(306);
            this._errHandler.sync(this);
            _la = this._input.LA(1);

            do {
                do {
                    do {
                        this.setState(303);
                        this._errHandler.sync(this);
                        switch (this._input.LA(1)) {
                            case 68:
                                this.setState(302);
                                this.match(68);
                                break;
                            case 90:
                                this.setState(301);
                                this.match(90);
                                break;
                            case 137:
                                this.setState(297);
                                this.match(137);
                                this.setState(299);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);
                                if (_la == 29) {
                                    this.setState(298);
                                    this.match(29);
                                }
                                break;
                            default:
                                throw new NoViableAltException(this);
                        }

                        this.setState(305);
                        this.select_core();
                        this.setState(308);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    } while(_la == 68);
                } while(_la == 90);
            } while(_la == 137);

            this.setState(320);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 109) {
                this.setState(310);
                this.match(109);
                this.setState(311);
                this.match(40);
                this.setState(312);
                this.ordering_term();
                this.setState(317);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(313);
                    this.match(5);
                    this.setState(314);
                    this.ordering_term();
                    this.setState(319);
                    this._errHandler.sync(this);
                }
            }

            this.setState(328);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98) {
                this.setState(322);
                this.match(98);
                this.setState(323);
                this.expr(0);
                this.setState(326);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(324);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(325);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Create_index_stmtContext create_index_stmt() throws RecognitionException {
        Create_index_stmtContext _localctx = new Create_index_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 20, 10);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(330);
            this.match(50);
            this.setState(332);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 138) {
                this.setState(331);
                this.match(138);
            }

            this.setState(334);
            this.match(84);
            this.setState(338);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 28, this._ctx)) {
                case 1:
                    this.setState(335);
                    this.match(80);
                    this.setState(336);
                    this.match(102);
                    this.setState(337);
                    this.match(70);
                default:
                    this.setState(343);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 29, this._ctx)) {
                        case 1:
                            this.setState(340);
                            this.database_name();
                            this.setState(341);
                            this.match(2);
                        default:
                            this.setState(345);
                            this.index_name();
                            this.setState(346);
                            this.match(107);
                            this.setState(347);
                            this.table_name();
                            this.setState(348);
                            this.match(3);
                            this.setState(349);
                            this.indexed_column();
                            this.setState(354);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                    }
            }

            while(_la == 5) {
                this.setState(350);
                this.match(5);
                this.setState(351);
                this.indexed_column();
                this.setState(356);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }

            this.setState(357);
            this.match(4);
            this.setState(360);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 146) {
                this.setState(358);
                this.match(146);
                this.setState(359);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
        Create_table_stmtContext _localctx = new Create_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 22, 11);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(362);
            this.match(50);
            this.setState(364);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 131 || _la == 132) {
                this.setState(363);
                _la = this._input.LA(1);
                if (_la != 131 && _la != 132) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(366);
            this.match(130);
            this.setState(370);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 33, this._ctx)) {
                case 1:
                    this.setState(367);
                    this.match(80);
                    this.setState(368);
                    this.match(102);
                    this.setState(369);
                    this.match(70);
                default:
                    this.setState(375);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 34, this._ctx)) {
                        case 1:
                            this.setState(372);
                            this.database_name();
                            this.setState(373);
                            this.match(2);
                    }

                    this.setState(377);
                    this.table_name();
                    this.setState(401);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case 3:
                            this.setState(378);
                            this.match(3);
                            this.setState(379);
                            this.column_def();
                            this.setState(384);
                            this._errHandler.sync(this);

                            for(int _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 35, this._ctx); _alt != 1 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 35, this._ctx)) {
                                if (_alt == 2) {
                                    this.setState(380);
                                    this.match(5);
                                    this.setState(381);
                                    this.column_def();
                                }

                                this.setState(386);
                                this._errHandler.sync(this);
                            }

                            this.setState(391);
                            this._errHandler.sync(this);

                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                this.setState(387);
                                this.match(5);
                                this.setState(388);
                                this.table_constraint();
                                this.setState(393);
                                this._errHandler.sync(this);
                            }

                            this.setState(394);
                            this.match(4);
                            this.setState(397);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 148) {
                                this.setState(395);
                                this.match(148);
                                this.setState(396);
                                this.match(149);
                            }
                            break;
                        case 33:
                            this.setState(399);
                            this.match(33);
                            this.setState(400);
                            this.select_stmt();
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Create_trigger_stmtContext create_trigger_stmt() throws RecognitionException {
        Create_trigger_stmtContext _localctx = new Create_trigger_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 24, 12);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(403);
            this.match(50);
            this.setState(405);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 131 || _la == 132) {
                this.setState(404);
                _la = this._input.LA(1);
                if (_la != 131 && _la != 132) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(407);
            this.match(136);
            this.setState(411);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 40, this._ctx)) {
                case 1:
                    this.setState(408);
                    this.match(80);
                    this.setState(409);
                    this.match(102);
                    this.setState(410);
                    this.match(70);
            }

            this.setState(416);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 41, this._ctx)) {
                case 1:
                    this.setState(413);
                    this.database_name();
                    this.setState(414);
                    this.match(2);
            }

            this.setState(418);
            this.trigger_name();
            this.setState(423);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 28:
                    this.setState(420);
                    this.match(28);
                    break;
                case 37:
                    this.setState(419);
                    this.match(37);
                case 59:
                case 88:
                case 139:
                default:
                    break;
                case 89:
                    this.setState(421);
                    this.match(89);
                    this.setState(422);
                    this.match(105);
            }

            this.setState(439);
            this._errHandler.sync(this);
            label215:
            switch (this._input.LA(1)) {
                case 59:
                    this.setState(425);
                    this.match(59);
                    break;
                case 88:
                    this.setState(426);
                    this.match(88);
                    break;
                case 139:
                    this.setState(427);
                    this.match(139);
                    this.setState(437);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la != 105) {
                        break;
                    }

                    this.setState(428);
                    this.match(105);
                    this.setState(429);
                    this.column_name();
                    this.setState(434);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);

                    while(true) {
                        if (_la != 5) {
                            break label215;
                        }

                        this.setState(430);
                        this.match(5);
                        this.setState(431);
                        this.column_name();
                        this.setState(436);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    }
                default:
                    throw new NoViableAltException(this);
            }

            this.setState(441);
            this.match(107);
            this.setState(445);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 46, this._ctx)) {
                case 1:
                    this.setState(442);
                    this.database_name();
                    this.setState(443);
                    this.match(2);
                default:
                    this.setState(447);
                    this.table_name();
                    this.setState(451);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 73) {
                        this.setState(448);
                        this.match(73);
                        this.setState(449);
                        this.match(64);
                        this.setState(450);
                        this.match(126);
                    }

                    this.setState(455);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 145) {
                        this.setState(453);
                        this.match(145);
                        this.setState(454);
                        this.expr(0);
                    }

                    this.setState(457);
                    this.match(38);
                    this.setState(466);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            do {
                this.setState(462);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 49, this._ctx)) {
                    case 1:
                        this.setState(458);
                        this.update_stmt();
                        break;
                    case 2:
                        this.setState(459);
                        this.insert_stmt();
                        break;
                    case 3:
                        this.setState(460);
                        this.delete_stmt();
                        break;
                    case 4:
                        this.setState(461);
                        this.select_stmt();
                }

                this.setState(464);
                this.match(1);
                this.setState(468);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            } while(_la == 59 || (_la - 88 & -64) == 0 && (1L << _la - 88 & 596728067318087681L) != 0L);

            this.setState(470);
            this.match(66);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Create_view_stmtContext create_view_stmt() throws RecognitionException {
        Create_view_stmtContext _localctx = new Create_view_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 26, 13);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(472);
            this.match(50);
            this.setState(474);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 131 || _la == 132) {
                this.setState(473);
                _la = this._input.LA(1);
                if (_la != 131 && _la != 132) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(476);
            this.match(143);
            this.setState(480);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 52, this._ctx)) {
                case 1:
                    this.setState(477);
                    this.match(80);
                    this.setState(478);
                    this.match(102);
                    this.setState(479);
                    this.match(70);
                default:
                    this.setState(485);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 53, this._ctx)) {
                        case 1:
                            this.setState(482);
                            this.database_name();
                            this.setState(483);
                            this.match(2);
                        default:
                            this.setState(487);
                            this.view_name();
                            this.setState(488);
                            this.match(33);
                            this.setState(489);
                            this.select_stmt();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Create_virtual_table_stmtContext create_virtual_table_stmt() throws RecognitionException {
        Create_virtual_table_stmtContext _localctx = new Create_virtual_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 28, 14);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(491);
            this.match(50);
            this.setState(492);
            this.match(144);
            this.setState(493);
            this.match(130);
            this.setState(497);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 54, this._ctx)) {
                case 1:
                    this.setState(494);
                    this.match(80);
                    this.setState(495);
                    this.match(102);
                    this.setState(496);
                    this.match(70);
                default:
                    this.setState(502);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 55, this._ctx)) {
                        case 1:
                            this.setState(499);
                            this.database_name();
                            this.setState(500);
                            this.match(2);
                    }

                    this.setState(504);
                    this.table_name();
                    this.setState(505);
                    this.match(140);
                    this.setState(506);
                    this.module_name();
                    this.setState(518);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 3) {
                        this.setState(507);
                        this.match(3);
                        this.setState(508);
                        this.module_argument();
                        this.setState(513);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(509);
                            this.match(5);
                            this.setState(510);
                            this.module_argument();
                            this.setState(515);
                            this._errHandler.sync(this);
                        }

                        this.setState(516);
                        this.match(4);
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Delete_stmtContext delete_stmt() throws RecognitionException {
        Delete_stmtContext _localctx = new Delete_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 30, 15);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(521);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(520);
                this.with_clause();
            }

            this.setState(523);
            this.match(59);
            this.setState(524);
            this.match(75);
            this.setState(525);
            this.qualified_table_name();
            this.setState(528);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 146) {
                this.setState(526);
                this.match(146);
                this.setState(527);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Delete_stmt_limitedContext delete_stmt_limited() throws RecognitionException {
        Delete_stmt_limitedContext _localctx = new Delete_stmt_limitedContext(this._ctx, this.getState());
        this.enterRule(_localctx, 32, 16);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(531);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(530);
                this.with_clause();
            }

            this.setState(533);
            this.match(59);
            this.setState(534);
            this.match(75);
            this.setState(535);
            this.qualified_table_name();
            this.setState(538);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 146) {
                this.setState(536);
                this.match(146);
                this.setState(537);
                this.expr(0);
            }

            this.setState(558);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98 || _la == 109) {
                this.setState(550);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 109) {
                    this.setState(540);
                    this.match(109);
                    this.setState(541);
                    this.match(40);
                    this.setState(542);
                    this.ordering_term();
                    this.setState(547);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(543);
                        this.match(5);
                        this.setState(544);
                        this.ordering_term();
                        this.setState(549);
                        this._errHandler.sync(this);
                    }
                }

                this.setState(552);
                this.match(98);
                this.setState(553);
                this.expr(0);
                this.setState(556);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(554);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(555);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Detach_stmtContext detach_stmt() throws RecognitionException {
        Detach_stmtContext _localctx = new Detach_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 34, 17);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(560);
            this.match(61);
            this.setState(562);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 66, this._ctx)) {
                case 1:
                    this.setState(561);
                    this.match(55);
                default:
                    this.setState(564);
                    this.database_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Drop_index_stmtContext drop_index_stmt() throws RecognitionException {
        Drop_index_stmtContext _localctx = new Drop_index_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 36, 18);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(566);
            this.match(63);
            this.setState(567);
            this.match(84);
            this.setState(570);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 67, this._ctx)) {
                case 1:
                    this.setState(568);
                    this.match(80);
                    this.setState(569);
                    this.match(70);
                default:
                    this.setState(575);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 68, this._ctx)) {
                        case 1:
                            this.setState(572);
                            this.database_name();
                            this.setState(573);
                            this.match(2);
                        default:
                            this.setState(577);
                            this.index_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Drop_table_stmtContext drop_table_stmt() throws RecognitionException {
        Drop_table_stmtContext _localctx = new Drop_table_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 38, 19);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(579);
            this.match(63);
            this.setState(580);
            this.match(130);
            this.setState(583);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 69, this._ctx)) {
                case 1:
                    this.setState(581);
                    this.match(80);
                    this.setState(582);
                    this.match(70);
                default:
                    this.setState(588);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 70, this._ctx)) {
                        case 1:
                            this.setState(585);
                            this.database_name();
                            this.setState(586);
                            this.match(2);
                        default:
                            this.setState(590);
                            this.table_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Drop_trigger_stmtContext drop_trigger_stmt() throws RecognitionException {
        Drop_trigger_stmtContext _localctx = new Drop_trigger_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 40, 20);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(592);
            this.match(63);
            this.setState(593);
            this.match(136);
            this.setState(596);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 71, this._ctx)) {
                case 1:
                    this.setState(594);
                    this.match(80);
                    this.setState(595);
                    this.match(70);
                default:
                    this.setState(601);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 72, this._ctx)) {
                        case 1:
                            this.setState(598);
                            this.database_name();
                            this.setState(599);
                            this.match(2);
                        default:
                            this.setState(603);
                            this.trigger_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Drop_view_stmtContext drop_view_stmt() throws RecognitionException {
        Drop_view_stmtContext _localctx = new Drop_view_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 42, 21);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(605);
            this.match(63);
            this.setState(606);
            this.match(143);
            this.setState(609);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 73, this._ctx)) {
                case 1:
                    this.setState(607);
                    this.match(80);
                    this.setState(608);
                    this.match(70);
                default:
                    this.setState(614);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 74, this._ctx)) {
                        case 1:
                            this.setState(611);
                            this.database_name();
                            this.setState(612);
                            this.match(2);
                        default:
                            this.setState(616);
                            this.view_name();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Factored_select_stmtContext factored_select_stmt() throws RecognitionException {
        Factored_select_stmtContext _localctx = new Factored_select_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 44, 22);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(619);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(618);
                this.with_clause();
            }

            this.setState(621);
            this.select_core();
            this.setState(627);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 68 || _la == 90 || _la == 137; _la = this._input.LA(1)) {
                this.setState(622);
                this.compound_operator();
                this.setState(623);
                this.select_core();
                this.setState(629);
                this._errHandler.sync(this);
            }

            this.setState(640);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 109) {
                this.setState(630);
                this.match(109);
                this.setState(631);
                this.match(40);
                this.setState(632);
                this.ordering_term();
                this.setState(637);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(633);
                    this.match(5);
                    this.setState(634);
                    this.ordering_term();
                    this.setState(639);
                    this._errHandler.sync(this);
                }
            }

            this.setState(648);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98) {
                this.setState(642);
                this.match(98);
                this.setState(643);
                this.expr(0);
                this.setState(646);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(644);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(645);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Insert_stmtContext insert_stmt() throws RecognitionException {
        Insert_stmtContext _localctx = new Insert_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 46, 23);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(651);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(650);
                this.with_clause();
            }

            this.setState(670);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 82, this._ctx)) {
                case 1:
                    this.setState(653);
                    this.match(88);
                    break;
                case 2:
                    this.setState(654);
                    this.match(122);
                    break;
                case 3:
                    this.setState(655);
                    this.match(88);
                    this.setState(656);
                    this.match(108);
                    this.setState(657);
                    this.match(122);
                    break;
                case 4:
                    this.setState(658);
                    this.match(88);
                    this.setState(659);
                    this.match(108);
                    this.setState(660);
                    this.match(125);
                    break;
                case 5:
                    this.setState(661);
                    this.match(88);
                    this.setState(662);
                    this.match(108);
                    this.setState(663);
                    this.match(25);
                    break;
                case 6:
                    this.setState(664);
                    this.match(88);
                    this.setState(665);
                    this.match(108);
                    this.setState(666);
                    this.match(72);
                    break;
                case 7:
                    this.setState(667);
                    this.match(88);
                    this.setState(668);
                    this.match(108);
                    this.setState(669);
                    this.match(81);
            }

            this.setState(672);
            this.match(91);
            this.setState(676);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 83, this._ctx)) {
                case 1:
                    this.setState(673);
                    this.database_name();
                    this.setState(674);
                    this.match(2);
            }

            this.setState(678);
            this.table_name();
            this.setState(690);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(679);
                this.match(3);
                this.setState(680);
                this.column_name();
                this.setState(685);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(681);
                    this.match(5);
                    this.setState(682);
                    this.column_name();
                    this.setState(687);
                    this._errHandler.sync(this);
                }

                this.setState(688);
                this.match(4);
            }

            this.setState(723);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 89, this._ctx)) {
                case 1:
                    this.setState(692);
                    this.match(142);
                    this.setState(693);
                    this.match(3);
                    this.setState(694);
                    this.expr(0);
                    this.setState(699);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(695);
                        this.match(5);
                        this.setState(696);
                        this.expr(0);
                        this.setState(701);
                        this._errHandler.sync(this);
                    }

                    this.setState(702);
                    this.match(4);
                    this.setState(717);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(703);
                        this.match(5);
                        this.setState(704);
                        this.match(3);
                        this.setState(705);
                        this.expr(0);
                        this.setState(710);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(706);
                            this.match(5);
                            this.setState(707);
                            this.expr(0);
                            this.setState(712);
                            this._errHandler.sync(this);
                        }

                        this.setState(713);
                        this.match(4);
                        this.setState(719);
                        this._errHandler.sync(this);
                    }

                    return _localctx;
                case 2:
                    this.setState(720);
                    this.select_stmt();
                    break;
                case 3:
                    this.setState(721);
                    this.match(56);
                    this.setState(722);
                    this.match(142);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Pragma_stmtContext pragma_stmt() throws RecognitionException {
        Pragma_stmtContext _localctx = new Pragma_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 48, 24);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(725);
            this.match(112);
            this.setState(729);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 90, this._ctx)) {
                case 1:
                    this.setState(726);
                    this.database_name();
                    this.setState(727);
                    this.match(2);
                default:
                    this.setState(731);
                    this.pragma_name();
                    this.setState(738);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case -1:
                        case 1:
                        case 30:
                        case 31:
                        case 35:
                        case 38:
                        case 47:
                        case 50:
                        case 59:
                        case 61:
                        case 63:
                        case 66:
                        case 71:
                        case 88:
                        case 112:
                        case 119:
                        case 120:
                        case 122:
                        case 125:
                        case 127:
                        case 128:
                        case 139:
                        case 141:
                        case 142:
                        case 147:
                        case 157:
                        default:
                            break;
                        case 3:
                            this.setState(734);
                            this.match(3);
                            this.setState(735);
                            this.pragma_value();
                            this.setState(736);
                            this.match(4);
                            break;
                        case 6:
                            this.setState(732);
                            this.match(6);
                            this.setState(733);
                            this.pragma_value();
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Reindex_stmtContext reindex_stmt() throws RecognitionException {
        Reindex_stmtContext _localctx = new Reindex_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 50, 25);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(740);
            this.match(119);
            this.setState(751);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 94, this._ctx)) {
                case 1:
                    this.setState(741);
                    this.collation_name();
                    break;
                case 2:
                    this.setState(745);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 92, this._ctx)) {
                        case 1:
                            this.setState(742);
                            this.database_name();
                            this.setState(743);
                            this.match(2);
                        default:
                            this.setState(749);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 93, this._ctx)) {
                                case 1:
                                    this.setState(747);
                                    this.table_name();
                                    break;
                                case 2:
                                    this.setState(748);
                                    this.index_name();
                            }
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Release_stmtContext release_stmt() throws RecognitionException {
        Release_stmtContext _localctx = new Release_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 52, 26);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(753);
            this.match(120);
            this.setState(755);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 95, this._ctx)) {
                case 1:
                    this.setState(754);
                    this.match(127);
                default:
                    this.setState(757);
                    this.savepoint_name();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Rollback_stmtContext rollback_stmt() throws RecognitionException {
        Rollback_stmtContext _localctx = new Rollback_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 54, 27);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(759);
            this.match(125);
            this.setState(764);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 135) {
                this.setState(760);
                this.match(135);
                this.setState(762);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 96, this._ctx)) {
                    case 1:
                        this.setState(761);
                        this.transaction_name();
                }
            }

            this.setState(771);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 134) {
                this.setState(766);
                this.match(134);
                this.setState(768);
                this._errHandler.sync(this);
                switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 98, this._ctx)) {
                    case 1:
                        this.setState(767);
                        this.match(127);
                    default:
                        this.setState(770);
                        this.savepoint_name();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Savepoint_stmtContext savepoint_stmt() throws RecognitionException {
        Savepoint_stmtContext _localctx = new Savepoint_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 56, 28);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(773);
            this.match(127);
            this.setState(774);
            this.savepoint_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Simple_select_stmtContext simple_select_stmt() throws RecognitionException {
        Simple_select_stmtContext _localctx = new Simple_select_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 58, 29);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(777);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(776);
                this.with_clause();
            }

            this.setState(779);
            this.select_core();
            this.setState(790);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 109) {
                this.setState(780);
                this.match(109);
                this.setState(781);
                this.match(40);
                this.setState(782);
                this.ordering_term();
                this.setState(787);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(783);
                    this.match(5);
                    this.setState(784);
                    this.ordering_term();
                    this.setState(789);
                    this._errHandler.sync(this);
                }
            }

            this.setState(798);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98) {
                this.setState(792);
                this.match(98);
                this.setState(793);
                this.expr(0);
                this.setState(796);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(794);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(795);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Select_stmtContext select_stmt() throws RecognitionException {
        Select_stmtContext _localctx = new Select_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 60, 30);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(801);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(800);
                this.with_clause();
            }

            this.setState(803);
            this.select_or_values();
            this.setState(809);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 68 || _la == 90 || _la == 137; _la = this._input.LA(1)) {
                this.setState(804);
                this.compound_operator();
                this.setState(805);
                this.select_or_values();
                this.setState(811);
                this._errHandler.sync(this);
            }

            this.setState(822);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 109) {
                this.setState(812);
                this.match(109);
                this.setState(813);
                this.match(40);
                this.setState(814);
                this.ordering_term();
                this.setState(819);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(815);
                    this.match(5);
                    this.setState(816);
                    this.ordering_term();
                    this.setState(821);
                    this._errHandler.sync(this);
                }
            }

            this.setState(830);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98) {
                this.setState(824);
                this.match(98);
                this.setState(825);
                this.expr(0);
                this.setState(828);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(826);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(827);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Select_or_valuesContext select_or_values() throws RecognitionException {
        Select_or_valuesContext _localctx = new Select_or_valuesContext(this._ctx, this.getState());
        this.enterRule(_localctx, 62, 31);

        try {
            this.setState(906);
            this._errHandler.sync(this);
            int _la;
            switch (this._input.LA(1)) {
                case 128:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(832);
                    this.match(128);
                    this.setState(834);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 111, this._ctx)) {
                        case 1:
                            this.setState(833);
                            _la = this._input.LA(1);
                            if (_la != 29 && _la != 62) {
                                this._errHandler.recoverInline(this);
                            } else {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            }
                    }

                    this.setState(836);
                    this.result_column();
                    this.setState(841);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(837);
                        this.match(5);
                        this.setState(838);
                        this.result_column();
                        this.setState(843);
                        this._errHandler.sync(this);
                    }

                    this.setState(856);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 75) {
                        this.setState(844);
                        this.match(75);
                        this.setState(854);
                        this._errHandler.sync(this);
                        label178:
                        switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 114, this._ctx)) {
                            case 1:
                                this.setState(845);
                                this.table_or_subquery();
                                this.setState(850);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);

                                while(true) {
                                    if (_la != 5) {
                                        break label178;
                                    }

                                    this.setState(846);
                                    this.match(5);
                                    this.setState(847);
                                    this.table_or_subquery();
                                    this.setState(852);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                                }
                            case 2:
                                this.setState(853);
                                this.join_clause();
                        }
                    }

                    this.setState(860);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 146) {
                        this.setState(858);
                        this.match(146);
                        this.setState(859);
                        this.expr(0);
                    }

                    this.setState(876);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 78) {
                        this.setState(862);
                        this.match(78);
                        this.setState(863);
                        this.match(40);
                        this.setState(864);
                        this.expr(0);
                        this.setState(869);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(865);
                            this.match(5);
                            this.setState(866);
                            this.expr(0);
                            this.setState(871);
                            this._errHandler.sync(this);
                        }

                        this.setState(874);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 79) {
                            this.setState(872);
                            this.match(79);
                            this.setState(873);
                            this.expr(0);
                        }
                    }
                    break;
                case 142:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(878);
                    this.match(142);
                    this.setState(879);
                    this.match(3);
                    this.setState(880);
                    this.expr(0);
                    this.setState(885);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(881);
                        this.match(5);
                        this.setState(882);
                        this.expr(0);
                        this.setState(887);
                        this._errHandler.sync(this);
                    }

                    this.setState(888);
                    this.match(4);
                    this.setState(903);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(889);
                        this.match(5);
                        this.setState(890);
                        this.match(3);
                        this.setState(891);
                        this.expr(0);
                        this.setState(896);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(892);
                            this.match(5);
                            this.setState(893);
                            this.expr(0);
                            this.setState(898);
                            this._errHandler.sync(this);
                        }

                        this.setState(899);
                        this.match(4);
                        this.setState(905);
                        this._errHandler.sync(this);
                    }

                    return _localctx;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Update_stmtContext update_stmt() throws RecognitionException {
        Update_stmtContext _localctx = new Update_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 64, 32);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(909);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(908);
                this.with_clause();
            }

            this.setState(911);
            this.match(139);
            this.setState(922);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 125, this._ctx)) {
                case 1:
                    this.setState(912);
                    this.match(108);
                    this.setState(913);
                    this.match(125);
                    break;
                case 2:
                    this.setState(914);
                    this.match(108);
                    this.setState(915);
                    this.match(25);
                    break;
                case 3:
                    this.setState(916);
                    this.match(108);
                    this.setState(917);
                    this.match(122);
                    break;
                case 4:
                    this.setState(918);
                    this.match(108);
                    this.setState(919);
                    this.match(72);
                    break;
                case 5:
                    this.setState(920);
                    this.match(108);
                    this.setState(921);
                    this.match(81);
            }

            this.setState(924);
            this.qualified_table_name();
            this.setState(925);
            this.match(129);
            this.setState(926);
            this.column_name();
            this.setState(927);
            this.match(6);
            this.setState(928);
            this.expr(0);
            this.setState(936);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(929);
                this.match(5);
                this.setState(930);
                this.column_name();
                this.setState(931);
                this.match(6);
                this.setState(932);
                this.expr(0);
                this.setState(938);
                this._errHandler.sync(this);
            }

            this.setState(941);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 146) {
                this.setState(939);
                this.match(146);
                this.setState(940);
                this.expr(0);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Update_stmt_limitedContext update_stmt_limited() throws RecognitionException {
        Update_stmt_limitedContext _localctx = new Update_stmt_limitedContext(this._ctx, this.getState());
        this.enterRule(_localctx, 66, 33);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(944);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 147) {
                this.setState(943);
                this.with_clause();
            }

            this.setState(946);
            this.match(139);
            this.setState(957);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 129, this._ctx)) {
                case 1:
                    this.setState(947);
                    this.match(108);
                    this.setState(948);
                    this.match(125);
                    break;
                case 2:
                    this.setState(949);
                    this.match(108);
                    this.setState(950);
                    this.match(25);
                    break;
                case 3:
                    this.setState(951);
                    this.match(108);
                    this.setState(952);
                    this.match(122);
                    break;
                case 4:
                    this.setState(953);
                    this.match(108);
                    this.setState(954);
                    this.match(72);
                    break;
                case 5:
                    this.setState(955);
                    this.match(108);
                    this.setState(956);
                    this.match(81);
            }

            this.setState(959);
            this.qualified_table_name();
            this.setState(960);
            this.match(129);
            this.setState(961);
            this.column_name();
            this.setState(962);
            this.match(6);
            this.setState(963);
            this.expr(0);
            this.setState(971);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                this.setState(964);
                this.match(5);
                this.setState(965);
                this.column_name();
                this.setState(966);
                this.match(6);
                this.setState(967);
                this.expr(0);
                this.setState(973);
                this._errHandler.sync(this);
            }

            this.setState(976);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 146) {
                this.setState(974);
                this.match(146);
                this.setState(975);
                this.expr(0);
            }

            this.setState(996);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 98 || _la == 109) {
                this.setState(988);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 109) {
                    this.setState(978);
                    this.match(109);
                    this.setState(979);
                    this.match(40);
                    this.setState(980);
                    this.ordering_term();
                    this.setState(985);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(981);
                        this.match(5);
                        this.setState(982);
                        this.ordering_term();
                        this.setState(987);
                        this._errHandler.sync(this);
                    }
                }

                this.setState(990);
                this.match(98);
                this.setState(991);
                this.expr(0);
                this.setState(994);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
                if (_la == 5 || _la == 106) {
                    this.setState(992);
                    _la = this._input.LA(1);
                    if (_la != 5 && _la != 106) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(993);
                    this.expr(0);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Vacuum_stmtContext vacuum_stmt() throws RecognitionException {
        Vacuum_stmtContext _localctx = new Vacuum_stmtContext(this._ctx, this.getState());
        this.enterRule(_localctx, 68, 34);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(998);
            this.match(141);
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Column_defContext column_def() throws RecognitionException {
        Column_defContext _localctx = new Column_defContext(this._ctx, this.getState());
        this.enterRule(_localctx, 70, 35);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1000);
            this.column_name();
            this.setState(1002);
            this._errHandler.sync(this);
            int _la;
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 136, this._ctx)) {
                case 1:
                    this.setState(1001);
                    this.type_name();
                default:
                    this.setState(1007);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            while((_la & -64) == 0 && (1L << _la & 72673320549482496L) != 0L || (_la - 102 & -64) == 0 && (1L << _la - 102 & 68719511557L) != 0L) {
                this.setState(1004);
                this.column_constraint();
                this.setState(1009);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Type_nameContext type_name() throws RecognitionException {
        Type_nameContext _localctx = new Type_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 72, 36);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1011);
            this._errHandler.sync(this);
            int _alt = 2;

            do {
                switch (_alt) {
                    case 2:
                        this.setState(1010);
                        this.name();
                        this.setState(1013);
                        this._errHandler.sync(this);
                        _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 138, this._ctx);
                        break;
                    default:
                        throw new NoViableAltException(this);
                }
            } while(_alt != 1 && _alt != 0);

            this.setState(1025);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 139, this._ctx)) {
                case 1:
                    this.setState(1015);
                    this.match(3);
                    this.setState(1016);
                    this.signed_number();
                    this.setState(1017);
                    this.match(4);
                    break;
                case 2:
                    this.setState(1019);
                    this.match(3);
                    this.setState(1020);
                    this.signed_number();
                    this.setState(1021);
                    this.match(5);
                    this.setState(1022);
                    this.signed_number();
                    this.setState(1023);
                    this.match(4);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Column_constraintContext column_constraint() throws RecognitionException {
        Column_constraintContext _localctx = new Column_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 74, 37);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1029);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 49) {
                this.setState(1027);
                this.match(49);
                this.setState(1028);
                this.name();
            }

            this.setState(1064);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 44:
                    this.setState(1047);
                    this.match(44);
                    this.setState(1048);
                    this.match(3);
                    this.setState(1049);
                    this.expr(0);
                    this.setState(1050);
                    this.match(4);
                    break;
                case 45:
                    this.setState(1061);
                    this.match(45);
                    this.setState(1062);
                    this.collation_name();
                    break;
                case 56:
                    this.setState(1052);
                    this.match(56);
                    this.setState(1059);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 144, this._ctx)) {
                        case 1:
                            this.setState(1053);
                            this.signed_number();
                            return _localctx;
                        case 2:
                            this.setState(1054);
                            this.literal_value();
                            return _localctx;
                        case 3:
                            this.setState(1055);
                            this.match(3);
                            this.setState(1056);
                            this.expr(0);
                            this.setState(1057);
                            this.match(4);
                            return _localctx;
                        default:
                            return _localctx;
                    }
                case 102:
                case 104:
                    this.setState(1041);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 102) {
                        this.setState(1040);
                        this.match(102);
                    }

                    this.setState(1043);
                    this.match(104);
                    this.setState(1044);
                    this.conflict_clause();
                    break;
                case 113:
                    this.setState(1031);
                    this.match(113);
                    this.setState(1032);
                    this.match(95);
                    this.setState(1034);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 34 || _la == 60) {
                        this.setState(1033);
                        _la = this._input.LA(1);
                        if (_la != 34 && _la != 60) {
                            this._errHandler.recoverInline(this);
                        } else {
                            if (this._input.LA(1) == -1) {
                                this.matchedEOF = true;
                            }

                            this._errHandler.reportMatch(this);
                            this.consume();
                        }
                    }

                    this.setState(1036);
                    this.conflict_clause();
                    this.setState(1038);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 36) {
                        this.setState(1037);
                        this.match(36);
                    }
                    break;
                case 117:
                    this.setState(1063);
                    this.foreign_key_clause();
                    break;
                case 138:
                    this.setState(1045);
                    this.match(138);
                    this.setState(1046);
                    this.conflict_clause();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Conflict_clauseContext conflict_clause() throws RecognitionException {
        Conflict_clauseContext _localctx = new Conflict_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 76, 38);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1069);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 107) {
                this.setState(1066);
                this.match(107);
                this.setState(1067);
                this.match(48);
                this.setState(1068);
                _la = this._input.LA(1);
                if (_la == 25 || (_la - 72 & -64) == 0 && (1L << _la - 72 & 10133099161584129L) != 0L) {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                } else {
                    this._errHandler.recoverInline(this);
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final ExprContext expr() throws RecognitionException {
        return this.expr(0);
    }

    private ExprContext expr(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = this._ctx;
        int _parentState = this.getState();
        ExprContext _localctx = new ExprContext(this._ctx, _parentState);
        int _startState = 78;
        this.enterRecursionRule(_localctx, 78, 39, _p);

        try {
            int _la;
            this.enterOuterAlt(_localctx, 1);
            this.setState(1147);
            this._errHandler.sync(this);
            label536:
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 157, this._ctx)) {
                case 1:
                    this.setState(1072);
                    this.literal_value();
                    break;
                case 2:
                    this.setState(1073);
                    this.match(151);
                    break;
                case 3:
                    this.setState(1082);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 148, this._ctx)) {
                        case 1:
                            this.setState(1077);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 147, this._ctx)) {
                                case 1:
                                    this.setState(1074);
                                    this.database_name();
                                    this.setState(1075);
                                    this.match(2);
                                default:
                                    this.setState(1079);
                                    this.table_name();
                                    this.setState(1080);
                                    this.match(2);
                            }
                        default:
                            this.setState(1084);
                            this.column_name();
                            break label536;
                    }
                case 4:
                    this.setState(1085);
                    this.unary_operator();
                    this.setState(1086);
                    this.expr(21);
                    break;
                case 5:
                    this.setState(1088);
                    this.function_name();
                    this.setState(1089);
                    this.match(3);
                    this.setState(1102);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case 3:
                        case 8:
                        case 9:
                        case 10:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                        case 95:
                        case 96:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122:
                        case 123:
                        case 124:
                        case 125:
                        case 126:
                        case 127:
                        case 128:
                        case 129:
                        case 130:
                        case 131:
                        case 132:
                        case 133:
                        case 134:
                        case 135:
                        case 136:
                        case 137:
                        case 138:
                        case 139:
                        case 140:
                        case 141:
                        case 142:
                        case 143:
                        case 144:
                        case 145:
                        case 146:
                        case 147:
                        case 148:
                        case 149:
                        case 150:
                        case 151:
                        case 152:
                        case 153:
                            this.setState(1091);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 149, this._ctx)) {
                                case 1:
                                    this.setState(1090);
                                    this.match(62);
                                default:
                                    this.setState(1093);
                                    this.expr(0);
                                    this.setState(1098);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                            }

                            while(_la == 5) {
                                this.setState(1094);
                                this.match(5);
                                this.setState(1095);
                                this.expr(0);
                                this.setState(1100);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);
                            }
                        case 4:
                        case 5:
                        case 6:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        default:
                            break;
                        case 7:
                            this.setState(1101);
                            this.match(7);
                    }

                    this.setState(1104);
                    this.match(4);
                    break;
                case 6:
                    this.setState(1106);
                    this.match(3);
                    this.setState(1107);
                    this.expr(0);
                    this.setState(1108);
                    this.match(4);
                    break;
                case 7:
                    this.setState(1110);
                    this.match(43);
                    this.setState(1111);
                    this.match(3);
                    this.setState(1112);
                    this.expr(0);
                    this.setState(1113);
                    this.match(33);
                    this.setState(1114);
                    this.type_name();
                    this.setState(1115);
                    this.match(4);
                    break;
                case 8:
                    this.setState(1121);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 70 || _la == 102) {
                        this.setState(1118);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 102) {
                            this.setState(1117);
                            this.match(102);
                        }

                        this.setState(1120);
                        this.match(70);
                    }

                    this.setState(1123);
                    this.match(3);
                    this.setState(1124);
                    this.select_stmt();
                    this.setState(1125);
                    this.match(4);
                    break;
                case 9:
                    this.setState(1127);
                    this.match(42);
                    this.setState(1129);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 154, this._ctx)) {
                        case 1:
                            this.setState(1128);
                            this.expr(0);
                        default:
                            this.setState(1136);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                    }

                    do {
                        this.setState(1131);
                        this.match(145);
                        this.setState(1132);
                        this.expr(0);
                        this.setState(1133);
                        this.match(133);
                        this.setState(1134);
                        this.expr(0);
                        this.setState(1138);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                    } while(_la == 145);

                    this.setState(1142);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 65) {
                        this.setState(1140);
                        this.match(65);
                        this.setState(1141);
                        this.expr(0);
                    }

                    this.setState(1144);
                    this.match(66);
                    break;
                case 10:
                    this.setState(1146);
                    this.raise_function();
            }

            this._ctx.stop = this._input.LT(-1);
            this.setState(1236);
            this._errHandler.sync(this);

            for(int _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 169, this._ctx); _alt != 2 && _alt != 0; _alt = ((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 169, this._ctx)) {
                if (_alt == 1) {
                    if (this._parseListeners != null) {
                        this.triggerExitRuleEvent();
                    }

                    this.setState(1234);
                    this._errHandler.sync(this);
                    label611:
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 168, this._ctx)) {
                        case 1:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1149);
                            if (!this.precpred(this._ctx, 20)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 20)");
                            }

                            this.setState(1150);
                            this.match(11);
                            this.setState(1151);
                            this.expr(21);
                            break;
                        case 2:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1152);
                            if (!this.precpred(this._ctx, 19)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 19)");
                            }

                            this.setState(1153);
                            _la = this._input.LA(1);
                            if ((_la & -64) == 0 && (1L << _la & 12416L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1154);
                            this.expr(20);
                            break;
                        case 3:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1155);
                            if (!this.precpred(this._ctx, 18)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 18)");
                            }

                            this.setState(1156);
                            _la = this._input.LA(1);
                            if (_la != 8 && _la != 9) {
                                this._errHandler.recoverInline(this);
                            } else {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            }

                            this.setState(1157);
                            this.expr(19);
                            break;
                        case 4:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1158);
                            if (!this.precpred(this._ctx, 17)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                            }

                            this.setState(1159);
                            _la = this._input.LA(1);
                            if ((_la & -64) == 0 && (1L << _la & 245760L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1160);
                            this.expr(18);
                            break;
                        case 5:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1161);
                            if (!this.precpred(this._ctx, 16)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                            }

                            this.setState(1162);
                            _la = this._input.LA(1);
                            if ((_la & -64) == 0 && (1L << _la & 3932160L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1163);
                            this.expr(17);
                            break;
                        case 6:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1164);
                            if (!this.precpred(this._ctx, 15)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                            }

                            this.setState(1165);
                            _la = this._input.LA(1);
                            if ((_la & -64) == 0 && (1L << _la & 29360192L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1166);
                            this.expr(16);
                            break;
                        case 7:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1167);
                            if (!this.precpred(this._ctx, 13)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 13)");
                            }

                            this.setState(1168);
                            this.match(32);
                            this.setState(1169);
                            this.expr(14);
                            break;
                        case 8:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1170);
                            if (!this.precpred(this._ctx, 12)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 12)");
                            }

                            this.setState(1171);
                            this.match(108);
                            this.setState(1172);
                            this.expr(13);
                            break;
                        case 9:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1173);
                            if (!this.precpred(this._ctx, 5)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                            }

                            this.setState(1174);
                            this.match(92);
                            this.setState(1176);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 158, this._ctx)) {
                                case 1:
                                    this.setState(1175);
                                    this.match(102);
                                default:
                                    this.setState(1178);
                                    this.expr(6);
                                    break label611;
                            }
                        case 10:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1179);
                            if (!this.precpred(this._ctx, 4)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                            }

                            this.setState(1181);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 102) {
                                this.setState(1180);
                                this.match(102);
                            }

                            this.setState(1183);
                            this.match(39);
                            this.setState(1184);
                            this.expr(0);
                            this.setState(1185);
                            this.match(32);
                            this.setState(1186);
                            this.expr(5);
                            break;
                        case 11:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1188);
                            if (!this.precpred(this._ctx, 14)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 14)");
                            }

                            this.setState(1190);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 102) {
                                this.setState(1189);
                                this.match(102);
                            }

                            this.setState(1192);
                            this.match(83);
                            this.setState(1212);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 164, this._ctx)) {
                                case 1:
                                    this.setState(1193);
                                    this.match(3);
                                    this.setState(1203);
                                    this._errHandler.sync(this);
                                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 162, this._ctx)) {
                                        case 1:
                                            this.setState(1194);
                                            this.select_stmt();
                                            break;
                                        case 2:
                                            this.setState(1195);
                                            this.expr(0);
                                            this.setState(1200);
                                            this._errHandler.sync(this);

                                            for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                                                this.setState(1196);
                                                this.match(5);
                                                this.setState(1197);
                                                this.expr(0);
                                                this.setState(1202);
                                                this._errHandler.sync(this);
                                            }
                                    }

                                    this.setState(1205);
                                    this.match(4);
                                    break label611;
                                case 2:
                                    this.setState(1209);
                                    this._errHandler.sync(this);
                                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 163, this._ctx)) {
                                        case 1:
                                            this.setState(1206);
                                            this.database_name();
                                            this.setState(1207);
                                            this.match(2);
                                        default:
                                            this.setState(1211);
                                            this.table_name();
                                    }
                                default:
                                    break label611;
                            }
                        case 12:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1214);
                            if (!this.precpred(this._ctx, 8)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 8)");
                            }

                            this.setState(1215);
                            this.match(45);
                            this.setState(1216);
                            this.collation_name();
                            break;
                        case 13:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1217);
                            if (!this.precpred(this._ctx, 7)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 7)");
                            }

                            this.setState(1219);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 102) {
                                this.setState(1218);
                                this.match(102);
                            }

                            this.setState(1221);
                            _la = this._input.LA(1);
                            if ((_la - 77 & -64) == 0 && (1L << _la - 77 & 2199028498433L) != 0L) {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            } else {
                                this._errHandler.recoverInline(this);
                            }

                            this.setState(1222);
                            this.expr(0);
                            this.setState(1225);
                            this._errHandler.sync(this);
                            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 166, this._ctx)) {
                                case 1:
                                    this.setState(1223);
                                    this.match(67);
                                    this.setState(1224);
                                    this.expr(0);
                                default:
                                    break label611;
                            }
                        case 14:
                            _localctx = new ExprContext(_parentctx, _parentState);
                            this.pushNewRecursionContext(_localctx, _startState, 39);
                            this.setState(1227);
                            if (!this.precpred(this._ctx, 6)) {
                                throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                            }

                            this.setState(1232);
                            this._errHandler.sync(this);
                            switch (this._input.LA(1)) {
                                case 93:
                                    this.setState(1228);
                                    this.match(93);
                                    break;
                                case 102:
                                    this.setState(1230);
                                    this.match(102);
                                    this.setState(1231);
                                    this.match(104);
                                    break;
                                case 103:
                                    this.setState(1229);
                                    this.match(103);
                                    break;
                                default:
                                    throw new NoViableAltException(this);
                            }
                    }
                }

                this.setState(1238);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var12) {
            _localctx.exception = var12;
            this._errHandler.reportError(this, var12);
            this._errHandler.recover(this, var12);
        } finally {
            this.unrollRecursionContexts(_parentctx);
        }

        return _localctx;
    }

    public final Foreign_key_clauseContext foreign_key_clause() throws RecognitionException {
        Foreign_key_clauseContext _localctx = new Foreign_key_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 80, 40);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1239);
            this.match(117);
            this.setState(1240);
            this.foreign_table();
            this.setState(1252);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(1241);
                this.match(3);
                this.setState(1242);
                this.column_name();
                this.setState(1247);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(1243);
                    this.match(5);
                    this.setState(1244);
                    this.column_name();
                    this.setState(1249);
                    this._errHandler.sync(this);
                }

                this.setState(1250);
                this.match(4);
            }

            this.setState(1272);
            this._errHandler.sync(this);

            for(_la = this._input.LA(1); _la == 99 || _la == 107; _la = this._input.LA(1)) {
                this.setState(1268);
                this._errHandler.sync(this);
                label167:
                switch (this._input.LA(1)) {
                    case 99:
                        this.setState(1266);
                        this.match(99);
                        this.setState(1267);
                        this.name();
                        break;
                    case 107:
                        this.setState(1254);
                        this.match(107);
                        this.setState(1255);
                        _la = this._input.LA(1);
                        if (_la != 59 && _la != 139) {
                            this._errHandler.recoverInline(this);
                        } else {
                            if (this._input.LA(1) == -1) {
                                this.matchedEOF = true;
                            }

                            this._errHandler.reportMatch(this);
                            this.consume();
                        }

                        this.setState(1264);
                        this._errHandler.sync(this);
                        switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 172, this._ctx)) {
                            case 1:
                                this.setState(1256);
                                this.match(129);
                                this.setState(1257);
                                this.match(104);
                                break label167;
                            case 2:
                                this.setState(1258);
                                this.match(129);
                                this.setState(1259);
                                this.match(56);
                                break label167;
                            case 3:
                                this.setState(1260);
                                this.match(41);
                                break label167;
                            case 4:
                                this.setState(1261);
                                this.match(123);
                                break label167;
                            case 5:
                                this.setState(1262);
                                this.match(101);
                                this.setState(1263);
                                this.match(26);
                            default:
                                break label167;
                        }
                    default:
                        throw new NoViableAltException(this);
                }

                this.setState(1274);
                this._errHandler.sync(this);
            }

            this.setState(1285);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 177, this._ctx)) {
                case 1:
                    this.setState(1276);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 102) {
                        this.setState(1275);
                        this.match(102);
                    }

                    this.setState(1278);
                    this.match(57);
                    this.setState(1283);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 176, this._ctx)) {
                        case 1:
                            this.setState(1279);
                            this.match(86);
                            this.setState(1280);
                            this.match(58);
                            return _localctx;
                        case 2:
                            this.setState(1281);
                            this.match(86);
                            this.setState(1282);
                            this.match(82);
                            return _localctx;
                    }
                default:
                    return _localctx;
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Raise_functionContext raise_function() throws RecognitionException {
        Raise_functionContext _localctx = new Raise_functionContext(this._ctx, this.getState());
        this.enterRule(_localctx, 82, 41);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1287);
            this.match(115);
            this.setState(1288);
            this.match(3);
            this.setState(1293);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 25:
                case 72:
                case 125:
                    this.setState(1290);
                    int _la = this._input.LA(1);
                    if (_la != 25 && _la != 72 && _la != 125) {
                        this._errHandler.recoverInline(this);
                    } else {
                        if (this._input.LA(1) == -1) {
                            this.matchedEOF = true;
                        }

                        this._errHandler.reportMatch(this);
                        this.consume();
                    }

                    this.setState(1291);
                    this.match(5);
                    this.setState(1292);
                    this.error_message();
                    break;
                case 81:
                    this.setState(1289);
                    this.match(81);
                    break;
                default:
                    throw new NoViableAltException(this);
            }

            this.setState(1295);
            this.match(4);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Indexed_columnContext indexed_column() throws RecognitionException {
        Indexed_columnContext _localctx = new Indexed_columnContext(this._ctx, this.getState());
        this.enterRule(_localctx, 84, 42);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1297);
            this.column_name();
            this.setState(1300);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 45) {
                this.setState(1298);
                this.match(45);
                this.setState(1299);
                this.collation_name();
            }

            this.setState(1303);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 34 || _la == 60) {
                this.setState(1302);
                _la = this._input.LA(1);
                if (_la != 34 && _la != 60) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_constraintContext table_constraint() throws RecognitionException {
        Table_constraintContext _localctx = new Table_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 86, 43);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1307);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 49) {
                this.setState(1305);
                this.match(49);
                this.setState(1306);
                this.name();
            }

            this.setState(1345);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 44:
                    this.setState(1326);
                    this.match(44);
                    this.setState(1327);
                    this.match(3);
                    this.setState(1328);
                    this.expr(0);
                    this.setState(1329);
                    this.match(4);
                    break;
                case 74:
                    this.setState(1331);
                    this.match(74);
                    this.setState(1332);
                    this.match(95);
                    this.setState(1333);
                    this.match(3);
                    this.setState(1334);
                    this.column_name();
                    this.setState(1339);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1335);
                        this.match(5);
                        this.setState(1336);
                        this.column_name();
                        this.setState(1341);
                        this._errHandler.sync(this);
                    }

                    this.setState(1342);
                    this.match(4);
                    this.setState(1343);
                    this.foreign_key_clause();
                    break;
                case 113:
                case 138:
                    this.setState(1312);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case 113:
                            this.setState(1309);
                            this.match(113);
                            this.setState(1310);
                            this.match(95);
                            break;
                        case 138:
                            this.setState(1311);
                            this.match(138);
                            break;
                        default:
                            throw new NoViableAltException(this);
                    }

                    this.setState(1314);
                    this.match(3);
                    this.setState(1315);
                    this.indexed_column();
                    this.setState(1320);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1316);
                        this.match(5);
                        this.setState(1317);
                        this.indexed_column();
                        this.setState(1322);
                        this._errHandler.sync(this);
                    }

                    this.setState(1323);
                    this.match(4);
                    this.setState(1324);
                    this.conflict_clause();
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final With_clauseContext with_clause() throws RecognitionException {
        With_clauseContext _localctx = new With_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 88, 44);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1347);
            this.match(147);
            this.setState(1349);
            this._errHandler.sync(this);
            int _la;
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 186, this._ctx)) {
                case 1:
                    this.setState(1348);
                    this.match(116);
                default:
                    this.setState(1351);
                    this.common_table_expression();
                    this.setState(1356);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
            }

            while(_la == 5) {
                this.setState(1352);
                this.match(5);
                this.setState(1353);
                this.common_table_expression();
                this.setState(1358);
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Qualified_table_nameContext qualified_table_name() throws RecognitionException {
        Qualified_table_nameContext _localctx = new Qualified_table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 90, 45);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1362);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 188, this._ctx)) {
                case 1:
                    this.setState(1359);
                    this.database_name();
                    this.setState(1360);
                    this.match(2);
                default:
                    this.setState(1364);
                    this.table_name();
                    this.setState(1370);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case -1:
                        case 1:
                        case 30:
                        case 31:
                        case 35:
                        case 38:
                        case 47:
                        case 50:
                        case 59:
                        case 61:
                        case 63:
                        case 66:
                        case 71:
                        case 88:
                        case 98:
                        case 109:
                        case 112:
                        case 119:
                        case 120:
                        case 122:
                        case 125:
                        case 127:
                        case 128:
                        case 129:
                        case 139:
                        case 141:
                        case 142:
                        case 146:
                        case 147:
                        case 157:
                        default:
                            break;
                        case 85:
                            this.setState(1365);
                            this.match(85);
                            this.setState(1366);
                            this.match(40);
                            this.setState(1367);
                            this.index_name();
                            break;
                        case 102:
                            this.setState(1368);
                            this.match(102);
                            this.setState(1369);
                            this.match(85);
                    }
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Ordering_termContext ordering_term() throws RecognitionException {
        Ordering_termContext _localctx = new Ordering_termContext(this._ctx, this.getState());
        this.enterRule(_localctx, 92, 46);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1372);
            this.expr(0);
            this.setState(1375);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 45) {
                this.setState(1373);
                this.match(45);
                this.setState(1374);
                this.collation_name();
            }

            this.setState(1378);
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            if (_la == 34 || _la == 60) {
                this.setState(1377);
                _la = this._input.LA(1);
                if (_la != 34 && _la != 60) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Pragma_valueContext pragma_value() throws RecognitionException {
        Pragma_valueContext _localctx = new Pragma_valueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 94, 47);

        try {
            this.setState(1383);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 192, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1380);
                    this.signed_number();
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1381);
                    this.name();
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1382);
                    this.match(152);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Common_table_expressionContext common_table_expression() throws RecognitionException {
        Common_table_expressionContext _localctx = new Common_table_expressionContext(this._ctx, this.getState());
        this.enterRule(_localctx, 96, 48);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1385);
            this.table_name();
            this.setState(1397);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 3) {
                this.setState(1386);
                this.match(3);
                this.setState(1387);
                this.column_name();
                this.setState(1392);
                this._errHandler.sync(this);

                for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                    this.setState(1388);
                    this.match(5);
                    this.setState(1389);
                    this.column_name();
                    this.setState(1394);
                    this._errHandler.sync(this);
                }

                this.setState(1395);
                this.match(4);
            }

            this.setState(1399);
            this.match(33);
            this.setState(1400);
            this.match(3);
            this.setState(1401);
            this.select_stmt();
            this.setState(1402);
            this.match(4);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Result_columnContext result_column() throws RecognitionException {
        Result_columnContext _localctx = new Result_columnContext(this._ctx, this.getState());
        this.enterRule(_localctx, 98, 49);

        try {
            this.setState(1416);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 197, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1404);
                    this.match(7);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1405);
                    this.table_name();
                    this.setState(1406);
                    this.match(2);
                    this.setState(1407);
                    this.match(7);
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1409);
                    this.expr(0);
                    this.setState(1414);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 33 || _la == 149 || _la == 152) {
                        this.setState(1411);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 33) {
                            this.setState(1410);
                            this.match(33);
                        }

                        this.setState(1413);
                        this.column_alias();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_or_subqueryContext table_or_subquery() throws RecognitionException {
        Table_or_subqueryContext _localctx = new Table_or_subqueryContext(this._ctx, this.getState());
        this.enterRule(_localctx, 100, 50);

        try {
            this.setState(1484);
            this._errHandler.sync(this);
            int _la;
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 211, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1421);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 198, this._ctx)) {
                        case 1:
                            this.setState(1418);
                            this.schema_name();
                            this.setState(1419);
                            this.match(2);
                    }

                    this.setState(1423);
                    this.table_name();
                    this.setState(1428);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 33 || _la == 149 || _la == 152) {
                        this.setState(1425);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 33) {
                            this.setState(1424);
                            this.match(33);
                        }

                        this.setState(1427);
                        this.table_alias();
                    }

                    this.setState(1435);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case -1:
                        case 1:
                        case 4:
                        case 5:
                        case 30:
                        case 31:
                        case 35:
                        case 38:
                        case 47:
                        case 50:
                        case 51:
                        case 59:
                        case 61:
                        case 63:
                        case 66:
                        case 68:
                        case 71:
                        case 78:
                        case 87:
                        case 88:
                        case 90:
                        case 94:
                        case 96:
                        case 98:
                        case 100:
                        case 107:
                        case 109:
                        case 112:
                        case 119:
                        case 120:
                        case 122:
                        case 125:
                        case 127:
                        case 128:
                        case 137:
                        case 139:
                        case 140:
                        case 141:
                        case 142:
                        case 146:
                        case 147:
                        case 157:
                        default:
                            return _localctx;
                        case 85:
                            this.setState(1430);
                            this.match(85);
                            this.setState(1431);
                            this.match(40);
                            this.setState(1432);
                            this.index_name();
                            return _localctx;
                        case 102:
                            this.setState(1433);
                            this.match(102);
                            this.setState(1434);
                            this.match(85);
                            return _localctx;
                    }
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1440);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 202, this._ctx)) {
                        case 1:
                            this.setState(1437);
                            this.schema_name();
                            this.setState(1438);
                            this.match(2);
                    }

                    this.setState(1442);
                    this.table_function_name();
                    this.setState(1443);
                    this.match(3);
                    this.setState(1452);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if ((_la & -64) == 0 && (1L << _la & -33552632L) != 0L || (_la - 64 & -64) == 0 && (1L << _la - 64 & -1L) != 0L || (_la - 128 & -64) == 0 && (1L << _la - 128 & 67108863L) != 0L) {
                        this.setState(1444);
                        this.expr(0);
                        this.setState(1449);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(1445);
                            this.match(5);
                            this.setState(1446);
                            this.expr(0);
                            this.setState(1451);
                            this._errHandler.sync(this);
                        }
                    }

                    this.setState(1454);
                    this.match(4);
                    this.setState(1459);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 33 || _la == 149 || _la == 152) {
                        this.setState(1456);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 33) {
                            this.setState(1455);
                            this.match(33);
                        }

                        this.setState(1458);
                        this.table_alias();
                    }
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1461);
                    this.match(3);
                    this.setState(1471);
                    this._errHandler.sync(this);
                    label228:
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 208, this._ctx)) {
                        case 1:
                            this.setState(1462);
                            this.table_or_subquery();
                            this.setState(1467);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);

                            while(true) {
                                if (_la != 5) {
                                    break label228;
                                }

                                this.setState(1463);
                                this.match(5);
                                this.setState(1464);
                                this.table_or_subquery();
                                this.setState(1469);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);
                            }
                        case 2:
                            this.setState(1470);
                            this.join_clause();
                    }

                    this.setState(1473);
                    this.match(4);
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1475);
                    this.match(3);
                    this.setState(1476);
                    this.select_stmt();
                    this.setState(1477);
                    this.match(4);
                    this.setState(1482);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 3 || _la == 33 || _la == 149 || _la == 152) {
                        this.setState(1479);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 33) {
                            this.setState(1478);
                            this.match(33);
                        }

                        this.setState(1481);
                        this.table_alias();
                    }
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Join_clauseContext join_clause() throws RecognitionException {
        Join_clauseContext _localctx = new Join_clauseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 102, 51);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1486);
            this.table_or_subquery();
            this.setState(1493);
            this._errHandler.sync(this);

            for(int _la = this._input.LA(1); _la == 5 || _la == 51 || (_la - 87 & -64) == 0 && (1L << _la - 87 & 8833L) != 0L; _la = this._input.LA(1)) {
                this.setState(1487);
                this.join_operator();
                this.setState(1488);
                this.table_or_subquery();
                this.setState(1489);
                this.join_constraint();
                this.setState(1495);
                this._errHandler.sync(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Join_operatorContext join_operator() throws RecognitionException {
        Join_operatorContext _localctx = new Join_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 104, 52);

        try {
            this.setState(1509);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 5:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1496);
                    this.match(5);
                    break;
                case 51:
                case 87:
                case 94:
                case 96:
                case 100:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1498);
                    this._errHandler.sync(this);
                    int _la = this._input.LA(1);
                    if (_la == 100) {
                        this.setState(1497);
                        this.match(100);
                    }

                    this.setState(1506);
                    this._errHandler.sync(this);
                    switch (this._input.LA(1)) {
                        case 51:
                            this.setState(1505);
                            this.match(51);
                            break;
                        case 87:
                            this.setState(1504);
                            this.match(87);
                        case 94:
                        default:
                            break;
                        case 96:
                            this.setState(1500);
                            this.match(96);
                            this.setState(1502);
                            this._errHandler.sync(this);
                            _la = this._input.LA(1);
                            if (_la == 110) {
                                this.setState(1501);
                                this.match(110);
                            }
                    }

                    this.setState(1508);
                    this.match(94);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Join_constraintContext join_constraint() throws RecognitionException {
        Join_constraintContext _localctx = new Join_constraintContext(this._ctx, this.getState());
        this.enterRule(_localctx, 106, 53);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1525);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case -1:
                case 1:
                case 4:
                case 5:
                case 30:
                case 31:
                case 35:
                case 38:
                case 47:
                case 50:
                case 51:
                case 59:
                case 61:
                case 63:
                case 66:
                case 68:
                case 71:
                case 78:
                case 87:
                case 88:
                case 90:
                case 94:
                case 96:
                case 98:
                case 100:
                case 109:
                case 112:
                case 119:
                case 120:
                case 122:
                case 125:
                case 127:
                case 128:
                case 137:
                case 139:
                case 141:
                case 142:
                case 146:
                case 147:
                case 157:
                default:
                    break;
                case 107:
                    this.setState(1511);
                    this.match(107);
                    this.setState(1512);
                    this.expr(0);
                    break;
                case 140:
                    this.setState(1513);
                    this.match(140);
                    this.setState(1514);
                    this.match(3);
                    this.setState(1515);
                    this.column_name();
                    this.setState(1520);
                    this._errHandler.sync(this);

                    for(int _la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1516);
                        this.match(5);
                        this.setState(1517);
                        this.column_name();
                        this.setState(1522);
                        this._errHandler.sync(this);
                    }

                    this.setState(1523);
                    this.match(4);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Select_coreContext select_core() throws RecognitionException {
        Select_coreContext _localctx = new Select_coreContext(this._ctx, this.getState());
        this.enterRule(_localctx, 108, 54);

        try {
            this.setState(1601);
            this._errHandler.sync(this);
            int _la;
            switch (this._input.LA(1)) {
                case 128:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1527);
                    this.match(128);
                    this.setState(1529);
                    this._errHandler.sync(this);
                    switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 219, this._ctx)) {
                        case 1:
                            this.setState(1528);
                            _la = this._input.LA(1);
                            if (_la != 29 && _la != 62) {
                                this._errHandler.recoverInline(this);
                            } else {
                                if (this._input.LA(1) == -1) {
                                    this.matchedEOF = true;
                                }

                                this._errHandler.reportMatch(this);
                                this.consume();
                            }
                    }

                    this.setState(1531);
                    this.result_column();
                    this.setState(1536);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1532);
                        this.match(5);
                        this.setState(1533);
                        this.result_column();
                        this.setState(1538);
                        this._errHandler.sync(this);
                    }

                    this.setState(1551);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 75) {
                        this.setState(1539);
                        this.match(75);
                        this.setState(1549);
                        this._errHandler.sync(this);
                        label178:
                        switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 222, this._ctx)) {
                            case 1:
                                this.setState(1540);
                                this.table_or_subquery();
                                this.setState(1545);
                                this._errHandler.sync(this);
                                _la = this._input.LA(1);

                                while(true) {
                                    if (_la != 5) {
                                        break label178;
                                    }

                                    this.setState(1541);
                                    this.match(5);
                                    this.setState(1542);
                                    this.table_or_subquery();
                                    this.setState(1547);
                                    this._errHandler.sync(this);
                                    _la = this._input.LA(1);
                                }
                            case 2:
                                this.setState(1548);
                                this.join_clause();
                        }
                    }

                    this.setState(1555);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 146) {
                        this.setState(1553);
                        this.match(146);
                        this.setState(1554);
                        this.expr(0);
                    }

                    this.setState(1571);
                    this._errHandler.sync(this);
                    _la = this._input.LA(1);
                    if (_la == 78) {
                        this.setState(1557);
                        this.match(78);
                        this.setState(1558);
                        this.match(40);
                        this.setState(1559);
                        this.expr(0);
                        this.setState(1564);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(1560);
                            this.match(5);
                            this.setState(1561);
                            this.expr(0);
                            this.setState(1566);
                            this._errHandler.sync(this);
                        }

                        this.setState(1569);
                        this._errHandler.sync(this);
                        _la = this._input.LA(1);
                        if (_la == 79) {
                            this.setState(1567);
                            this.match(79);
                            this.setState(1568);
                            this.expr(0);
                        }
                    }
                    break;
                case 142:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1573);
                    this.match(142);
                    this.setState(1574);
                    this.match(3);
                    this.setState(1575);
                    this.expr(0);
                    this.setState(1580);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1576);
                        this.match(5);
                        this.setState(1577);
                        this.expr(0);
                        this.setState(1582);
                        this._errHandler.sync(this);
                    }

                    this.setState(1583);
                    this.match(4);
                    this.setState(1598);
                    this._errHandler.sync(this);

                    for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                        this.setState(1584);
                        this.match(5);
                        this.setState(1585);
                        this.match(3);
                        this.setState(1586);
                        this.expr(0);
                        this.setState(1591);
                        this._errHandler.sync(this);

                        for(_la = this._input.LA(1); _la == 5; _la = this._input.LA(1)) {
                            this.setState(1587);
                            this.match(5);
                            this.setState(1588);
                            this.expr(0);
                            this.setState(1593);
                            this._errHandler.sync(this);
                        }

                        this.setState(1594);
                        this.match(4);
                        this.setState(1600);
                        this._errHandler.sync(this);
                    }

                    return _localctx;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Compound_operatorContext compound_operator() throws RecognitionException {
        Compound_operatorContext _localctx = new Compound_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 110, 55);

        try {
            this.setState(1608);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 232, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1603);
                    this.match(137);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1604);
                    this.match(137);
                    this.setState(1605);
                    this.match(29);
                    break;
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1606);
                    this.match(90);
                    break;
                case 4:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1607);
                    this.match(68);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Signed_numberContext signed_number() throws RecognitionException {
        Signed_numberContext _localctx = new Signed_numberContext(this._ctx, this.getState());
        this.enterRule(_localctx, 112, 56);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1611);
            this._errHandler.sync(this);
            int _la = this._input.LA(1);
            if (_la == 8 || _la == 9) {
                this.setState(1610);
                _la = this._input.LA(1);
                if (_la != 8 && _la != 9) {
                    this._errHandler.recoverInline(this);
                } else {
                    if (this._input.LA(1) == -1) {
                        this.matchedEOF = true;
                    }

                    this._errHandler.reportMatch(this);
                    this.consume();
                }
            }

            this.setState(1613);
            this.match(150);
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Literal_valueContext literal_value() throws RecognitionException {
        Literal_valueContext _localctx = new Literal_valueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 114, 57);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1615);
            int _la = this._input.LA(1);
            if ((_la & -64) == 0 && (1L << _la & 31525197391593472L) != 0L || (_la - 104 & -64) == 0 && (1L << _la - 104 & 914793674309633L) != 0L) {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            } else {
                this._errHandler.recoverInline(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Unary_operatorContext unary_operator() throws RecognitionException {
        Unary_operatorContext _localctx = new Unary_operatorContext(this._ctx, this.getState());
        this.enterRule(_localctx, 116, 58);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1617);
            int _la = this._input.LA(1);
            if (((_la & -64) != 0 || (1L << _la & 1792L) == 0L) && _la != 102) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Error_messageContext error_message() throws RecognitionException {
        Error_messageContext _localctx = new Error_messageContext(this._ctx, this.getState());
        this.enterRule(_localctx, 118, 59);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1619);
            this.match(152);
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Module_argumentContext module_argument() throws RecognitionException {
        Module_argumentContext _localctx = new Module_argumentContext(this._ctx, this.getState());
        this.enterRule(_localctx, 120, 60);

        try {
            this.setState(1623);
            this._errHandler.sync(this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 234, this._ctx)) {
                case 1:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1621);
                    this.expr(0);
                    break;
                case 2:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1622);
                    this.column_def();
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Column_aliasContext column_alias() throws RecognitionException {
        Column_aliasContext _localctx = new Column_aliasContext(this._ctx, this.getState());
        this.enterRule(_localctx, 122, 61);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1625);
            int _la = this._input.LA(1);
            if (_la != 149 && _la != 152) {
                this._errHandler.recoverInline(this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final KeywordContext keyword() throws RecognitionException {
        KeywordContext _localctx = new KeywordContext(this._ctx, this.getState());
        this.enterRule(_localctx, 124, 62);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1627);
            int _la = this._input.LA(1);
            if ((_la - 25 & -64) == 0 && (1L << _la - 25 & -1L) != 0L || (_la - 89 & -64) == 0 && (1L << _la - 89 & 1152921504606846975L) != 0L) {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }

                this._errHandler.reportMatch(this);
                this.consume();
            } else {
                this._errHandler.recoverInline(this);
            }
        } catch (RecognitionException var7) {
            _localctx.exception = var7;
            this._errHandler.reportError(this, var7);
            this._errHandler.recover(this, var7);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final NameContext name() throws RecognitionException {
        NameContext _localctx = new NameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 126, 63);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1629);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Function_nameContext function_name() throws RecognitionException {
        Function_nameContext _localctx = new Function_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 128, 64);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1631);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Database_nameContext database_name() throws RecognitionException {
        Database_nameContext _localctx = new Database_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 130, 65);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1633);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Schema_nameContext schema_name() throws RecognitionException {
        Schema_nameContext _localctx = new Schema_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 132, 66);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1635);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_function_nameContext table_function_name() throws RecognitionException {
        Table_function_nameContext _localctx = new Table_function_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 134, 67);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1637);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_nameContext table_name() throws RecognitionException {
        Table_nameContext _localctx = new Table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 136, 68);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1639);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_or_index_nameContext table_or_index_name() throws RecognitionException {
        Table_or_index_nameContext _localctx = new Table_or_index_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 138, 69);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1641);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final New_table_nameContext new_table_name() throws RecognitionException {
        New_table_nameContext _localctx = new New_table_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 140, 70);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1643);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Column_nameContext column_name() throws RecognitionException {
        Column_nameContext _localctx = new Column_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 142, 71);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1645);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Collation_nameContext collation_name() throws RecognitionException {
        Collation_nameContext _localctx = new Collation_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 144, 72);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1647);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Foreign_tableContext foreign_table() throws RecognitionException {
        Foreign_tableContext _localctx = new Foreign_tableContext(this._ctx, this.getState());
        this.enterRule(_localctx, 146, 73);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1649);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Index_nameContext index_name() throws RecognitionException {
        Index_nameContext _localctx = new Index_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 148, 74);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1651);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Trigger_nameContext trigger_name() throws RecognitionException {
        Trigger_nameContext _localctx = new Trigger_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 150, 75);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1653);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final View_nameContext view_name() throws RecognitionException {
        View_nameContext _localctx = new View_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 152, 76);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1655);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Module_nameContext module_name() throws RecognitionException {
        Module_nameContext _localctx = new Module_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 154, 77);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1657);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Pragma_nameContext pragma_name() throws RecognitionException {
        Pragma_nameContext _localctx = new Pragma_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 156, 78);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1659);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Savepoint_nameContext savepoint_name() throws RecognitionException {
        Savepoint_nameContext _localctx = new Savepoint_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 158, 79);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1661);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Table_aliasContext table_alias() throws RecognitionException {
        Table_aliasContext _localctx = new Table_aliasContext(this._ctx, this.getState());
        this.enterRule(_localctx, 160, 80);

        try {
            this.setState(1669);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 3:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1665);
                    this.match(3);
                    this.setState(1666);
                    this.table_alias();
                    this.setState(1667);
                    this.match(4);
                    break;
                case 149:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1663);
                    this.match(149);
                    break;
                case 152:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1664);
                    this.match(152);
                    break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Transaction_nameContext transaction_name() throws RecognitionException {
        Transaction_nameContext _localctx = new Transaction_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 162, 81);

        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(1671);
            this.any_name();
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public final Any_nameContext any_name() throws RecognitionException {
        Any_nameContext _localctx = new Any_nameContext(this._ctx, this.getState());
        this.enterRule(_localctx, 164, 82);

        try {
            this.setState(1680);
            this._errHandler.sync(this);
            switch (this._input.LA(1)) {
                case 3:
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(1676);
                    this.match(3);
                    this.setState(1677);
                    this.any_name();
                    this.setState(1678);
                    this.match(4);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 150:
                case 151:
                default:
                    throw new NoViableAltException(this);
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 132:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(1674);
                    this.keyword();
                    break;
                case 149:
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(1673);
                    this.match(149);
                    break;
                case 152:
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(1675);
                    this.match(152);
            }
        } catch (RecognitionException var6) {
            _localctx.exception = var6;
            this._errHandler.reportError(this, var6);
            this._errHandler.recover(this, var6);
        } finally {
            this.exitRule();
        }

        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 39:
                return this.expr_sempred((ExprContext)_localctx, predIndex);
            default:
                return true;
        }
    }

    private boolean expr_sempred(ExprContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return this.precpred(this._ctx, 20);
            case 1:
                return this.precpred(this._ctx, 19);
            case 2:
                return this.precpred(this._ctx, 18);
            case 3:
                return this.precpred(this._ctx, 17);
            case 4:
                return this.precpred(this._ctx, 16);
            case 5:
                return this.precpred(this._ctx, 15);
            case 6:
                return this.precpred(this._ctx, 13);
            case 7:
                return this.precpred(this._ctx, 12);
            case 8:
                return this.precpred(this._ctx, 5);
            case 9:
                return this.precpred(this._ctx, 4);
            case 10:
                return this.precpred(this._ctx, 14);
            case 11:
                return this.precpred(this._ctx, 8);
            case 12:
                return this.precpred(this._ctx, 7);
            case 13:
                return this.precpred(this._ctx, 6);
            default:
                return true;
        }
    }

    public static class Alter_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_ALTER() {
            return this.getToken(30, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(130, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_RENAME() {
            return this.getToken(121, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(134, 0);
        }

        public New_table_nameContext new_table_name() {
            return (New_table_nameContext)this.getRuleContext(New_table_nameContext.class, 0);
        }

        public TerminalNode K_ADD() {
            return this.getToken(27, 0);
        }

        public Column_defContext column_def() {
            return (Column_defContext)this.getRuleContext(Column_defContext.class, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode K_COLUMN() {
            return this.getToken(46, 0);
        }

        public Alter_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 4;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAlter_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAlter_table_stmt(this);
            }

        }
    }

    public static class Analyze_stmtContext extends ParserRuleContext {
        public TerminalNode K_ANALYZE() {
            return this.getToken(31, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public Table_or_index_nameContext table_or_index_name() {
            return (Table_or_index_nameContext)this.getRuleContext(Table_or_index_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Analyze_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 5;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAnalyze_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAnalyze_stmt(this);
            }

        }
    }

    public static class Any_nameContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(149, 0);
        }

        public KeywordContext keyword() {
            return (KeywordContext)this.getRuleContext(KeywordContext.class, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public Any_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 82;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAny_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAny_name(this);
            }

        }
    }

    public static class Attach_stmtContext extends ParserRuleContext {
        public TerminalNode K_ATTACH() {
            return this.getToken(35, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(55, 0);
        }

        public Attach_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 6;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterAttach_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitAttach_stmt(this);
            }

        }
    }

    public static class Begin_stmtContext extends ParserRuleContext {
        public TerminalNode K_BEGIN() {
            return this.getToken(38, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(135, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(58, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_EXCLUSIVE() {
            return this.getToken(69, 0);
        }

        public Transaction_nameContext transaction_name() {
            return (Transaction_nameContext)this.getRuleContext(Transaction_nameContext.class, 0);
        }

        public Begin_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 7;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterBegin_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitBegin_stmt(this);
            }

        }
    }

    public static class Collation_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Collation_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 72;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCollation_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCollation_name(this);
            }

        }
    }

    public static class Column_aliasContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(149, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public Column_aliasContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 61;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_alias(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_alias(this);
            }

        }
    }

    public static class Column_constraintContext extends ParserRuleContext {
        public TerminalNode K_PRIMARY() {
            return this.getToken(113, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(95, 0);
        }

        public Conflict_clauseContext conflict_clause() {
            return (Conflict_clauseContext)this.getRuleContext(Conflict_clauseContext.class, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(138, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(44, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(56, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(45, 0);
        }

        public Collation_nameContext collation_name() {
            return (Collation_nameContext)this.getRuleContext(Collation_nameContext.class, 0);
        }

        public Foreign_key_clauseContext foreign_key_clause() {
            return (Foreign_key_clauseContext)this.getRuleContext(Foreign_key_clauseContext.class, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(49, 0);
        }

        public NameContext name() {
            return (NameContext)this.getRuleContext(NameContext.class, 0);
        }

        public Signed_numberContext signed_number() {
            return (Signed_numberContext)this.getRuleContext(Signed_numberContext.class, 0);
        }

        public Literal_valueContext literal_value() {
            return (Literal_valueContext)this.getRuleContext(Literal_valueContext.class, 0);
        }

        public TerminalNode K_AUTOINCREMENT() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(60, 0);
        }

        public Column_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 37;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_constraint(this);
            }

        }
    }

    public static class Column_defContext extends ParserRuleContext {
        public Column_nameContext column_name() {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, 0);
        }

        public Type_nameContext type_name() {
            return (Type_nameContext)this.getRuleContext(Type_nameContext.class, 0);
        }

        public List<Column_constraintContext> column_constraint() {
            return this.getRuleContexts(Column_constraintContext.class);
        }

        public Column_constraintContext column_constraint(int i) {
            return (Column_constraintContext)this.getRuleContext(Column_constraintContext.class, i);
        }

        public Column_defContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 35;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_def(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_def(this);
            }

        }
    }

    public static class Column_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Column_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 71;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterColumn_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitColumn_name(this);
            }

        }
    }

    public static class Commit_stmtContext extends ParserRuleContext {
        public TerminalNode K_COMMIT() {
            return this.getToken(47, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(66, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(135, 0);
        }

        public Transaction_nameContext transaction_name() {
            return (Transaction_nameContext)this.getRuleContext(Transaction_nameContext.class, 0);
        }

        public Commit_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 8;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCommit_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCommit_stmt(this);
            }

        }
    }

    public static class Common_table_expressionContext extends ParserRuleContext {
        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public List<TerminalNode> OPEN_PAR() {
            return this.getTokens(3);
        }

        public TerminalNode OPEN_PAR(int i) {
            return this.getToken(3, i);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public List<TerminalNode> CLOSE_PAR() {
            return this.getTokens(4);
        }

        public TerminalNode CLOSE_PAR(int i) {
            return this.getToken(4, i);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Common_table_expressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 48;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCommon_table_expression(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCommon_table_expression(this);
            }

        }
    }

    public static class Compound_operatorContext extends ParserRuleContext {
        public TerminalNode K_UNION() {
            return this.getToken(137, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(29, 0);
        }

        public TerminalNode K_INTERSECT() {
            return this.getToken(90, 0);
        }

        public TerminalNode K_EXCEPT() {
            return this.getToken(68, 0);
        }

        public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 55;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCompound_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCompound_operator(this);
            }

        }
    }

    public static class Compound_select_stmtContext extends ParserRuleContext {
        public List<Select_coreContext> select_core() {
            return this.getRuleContexts(Select_coreContext.class);
        }

        public Select_coreContext select_core(int i) {
            return (Select_coreContext)this.getRuleContext(Select_coreContext.class, i);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> K_UNION() {
            return this.getTokens(137);
        }

        public TerminalNode K_UNION(int i) {
            return this.getToken(137, i);
        }

        public List<TerminalNode> K_INTERSECT() {
            return this.getTokens(90);
        }

        public TerminalNode K_INTERSECT(int i) {
            return this.getToken(90, i);
        }

        public List<TerminalNode> K_EXCEPT() {
            return this.getTokens(68);
        }

        public TerminalNode K_EXCEPT(int i) {
            return this.getToken(68, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public List<TerminalNode> K_ALL() {
            return this.getTokens(29);
        }

        public TerminalNode K_ALL(int i) {
            return this.getToken(29, i);
        }

        public Compound_select_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 9;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCompound_select_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCompound_select_stmt(this);
            }

        }
    }

    public static class Conflict_clauseContext extends ParserRuleContext {
        public TerminalNode K_ON() {
            return this.getToken(107, 0);
        }

        public TerminalNode K_CONFLICT() {
            return this.getToken(48, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(122, 0);
        }

        public Conflict_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 38;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterConflict_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitConflict_clause(this);
            }

        }
    }

    public static class Create_index_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(84, 0);
        }

        public Index_nameContext index_name() {
            return (Index_nameContext)this.getRuleContext(Index_nameContext.class, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(107, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Indexed_columnContext> indexed_column() {
            return this.getRuleContexts(Indexed_columnContext.class);
        }

        public Indexed_columnContext indexed_column(int i) {
            return (Indexed_columnContext)this.getRuleContext(Indexed_columnContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(138, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public Create_index_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 10;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_index_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_index_stmt(this);
            }

        }
    }

    public static class Create_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(130, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Column_defContext> column_def() {
            return this.getRuleContexts(Column_defContext.class);
        }

        public Column_defContext column_def(int i) {
            return (Column_defContext)this.getRuleContext(Column_defContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(131, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(132, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public List<Table_constraintContext> table_constraint() {
            return this.getRuleContexts(Table_constraintContext.class);
        }

        public Table_constraintContext table_constraint(int i) {
            return (Table_constraintContext)this.getRuleContext(Table_constraintContext.class, i);
        }

        public TerminalNode K_WITHOUT() {
            return this.getToken(148, 0);
        }

        public TerminalNode IDENTIFIER() {
            return this.getToken(149, 0);
        }

        public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 11;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_table_stmt(this);
            }

        }
    }

    public static class Create_trigger_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(136, 0);
        }

        public Trigger_nameContext trigger_name() {
            return (Trigger_nameContext)this.getRuleContext(Trigger_nameContext.class, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(107, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_BEGIN() {
            return this.getToken(38, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(66, 0);
        }

        public TerminalNode K_DELETE() {
            return this.getToken(59, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(88, 0);
        }

        public TerminalNode K_UPDATE() {
            return this.getToken(139, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public List<Database_nameContext> database_name() {
            return this.getRuleContexts(Database_nameContext.class);
        }

        public Database_nameContext database_name(int i) {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, i);
        }

        public List<TerminalNode> DOT() {
            return this.getTokens(2);
        }

        public TerminalNode DOT(int i) {
            return this.getToken(2, i);
        }

        public TerminalNode K_BEFORE() {
            return this.getToken(37, 0);
        }

        public TerminalNode K_AFTER() {
            return this.getToken(28, 0);
        }

        public TerminalNode K_INSTEAD() {
            return this.getToken(89, 0);
        }

        public List<TerminalNode> K_OF() {
            return this.getTokens(105);
        }

        public TerminalNode K_OF(int i) {
            return this.getToken(105, i);
        }

        public TerminalNode K_FOR() {
            return this.getToken(73, 0);
        }

        public TerminalNode K_EACH() {
            return this.getToken(64, 0);
        }

        public TerminalNode K_ROW() {
            return this.getToken(126, 0);
        }

        public TerminalNode K_WHEN() {
            return this.getToken(145, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public List<TerminalNode> SCOL() {
            return this.getTokens(1);
        }

        public TerminalNode SCOL(int i) {
            return this.getToken(1, i);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(131, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(132, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public List<Update_stmtContext> update_stmt() {
            return this.getRuleContexts(Update_stmtContext.class);
        }

        public Update_stmtContext update_stmt(int i) {
            return (Update_stmtContext)this.getRuleContext(Update_stmtContext.class, i);
        }

        public List<Insert_stmtContext> insert_stmt() {
            return this.getRuleContexts(Insert_stmtContext.class);
        }

        public Insert_stmtContext insert_stmt(int i) {
            return (Insert_stmtContext)this.getRuleContext(Insert_stmtContext.class, i);
        }

        public List<Delete_stmtContext> delete_stmt() {
            return this.getRuleContexts(Delete_stmtContext.class);
        }

        public Delete_stmtContext delete_stmt(int i) {
            return (Delete_stmtContext)this.getRuleContext(Delete_stmtContext.class, i);
        }

        public List<Select_stmtContext> select_stmt() {
            return this.getRuleContexts(Select_stmtContext.class);
        }

        public Select_stmtContext select_stmt(int i) {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Create_trigger_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 12;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_trigger_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_trigger_stmt(this);
            }

        }
    }

    public static class Create_view_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(143, 0);
        }

        public View_nameContext view_name() {
            return (View_nameContext)this.getRuleContext(View_nameContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(131, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(132, 0);
        }

        public Create_view_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 13;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_view_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_view_stmt(this);
            }

        }
    }

    public static class Create_virtual_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_VIRTUAL() {
            return this.getToken(144, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(130, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(140, 0);
        }

        public Module_nameContext module_name() {
            return (Module_nameContext)this.getRuleContext(Module_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Module_argumentContext> module_argument() {
            return this.getRuleContexts(Module_argumentContext.class);
        }

        public Module_argumentContext module_argument(int i) {
            return (Module_argumentContext)this.getRuleContext(Module_argumentContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Create_virtual_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 14;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterCreate_virtual_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitCreate_virtual_table_stmt(this);
            }

        }
    }

    public static class Database_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Database_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 65;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDatabase_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDatabase_name(this);
            }

        }
    }

    public static class Delete_stmtContext extends ParserRuleContext {
        public TerminalNode K_DELETE() {
            return this.getToken(59, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(75, 0);
        }

        public Qualified_table_nameContext qualified_table_name() {
            return (Qualified_table_nameContext)this.getRuleContext(Qualified_table_nameContext.class, 0);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 15;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDelete_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDelete_stmt(this);
            }

        }
    }

    public static class Delete_stmt_limitedContext extends ParserRuleContext {
        public TerminalNode K_DELETE() {
            return this.getToken(59, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(75, 0);
        }

        public Qualified_table_nameContext qualified_table_name() {
            return (Qualified_table_nameContext)this.getRuleContext(Qualified_table_nameContext.class, 0);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Delete_stmt_limitedContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 16;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDelete_stmt_limited(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDelete_stmt_limited(this);
            }

        }
    }

    public static class Detach_stmtContext extends ParserRuleContext {
        public TerminalNode K_DETACH() {
            return this.getToken(61, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(55, 0);
        }

        public Detach_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 17;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDetach_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDetach_stmt(this);
            }

        }
    }

    public static class Drop_index_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(84, 0);
        }

        public Index_nameContext index_name() {
            return (Index_nameContext)this.getRuleContext(Index_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Drop_index_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 18;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_index_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_index_stmt(this);
            }

        }
    }

    public static class Drop_table_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(130, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Drop_table_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 19;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_table_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_table_stmt(this);
            }

        }
    }

    public static class Drop_trigger_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(136, 0);
        }

        public Trigger_nameContext trigger_name() {
            return (Trigger_nameContext)this.getRuleContext(Trigger_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Drop_trigger_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 20;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_trigger_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_trigger_stmt(this);
            }

        }
    }

    public static class Drop_view_stmtContext extends ParserRuleContext {
        public TerminalNode K_DROP() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(143, 0);
        }

        public View_nameContext view_name() {
            return (View_nameContext)this.getRuleContext(View_nameContext.class, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Drop_view_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 21;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterDrop_view_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitDrop_view_stmt(this);
            }

        }
    }

    public static class ErrorContext extends ParserRuleContext {
        public Token UNEXPECTED_CHAR;

        public TerminalNode UNEXPECTED_CHAR() {
            return this.getToken(157, 0);
        }

        public ErrorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 1;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterError(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitError(this);
            }

        }
    }

    public static class Error_messageContext extends ParserRuleContext {
        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public Error_messageContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 59;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterError_message(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitError_message(this);
            }

        }
    }

    public static class ExprContext extends ParserRuleContext {
        public Literal_valueContext literal_value() {
            return (Literal_valueContext)this.getRuleContext(Literal_valueContext.class, 0);
        }

        public TerminalNode BIND_PARAMETER() {
            return this.getToken(151, 0);
        }

        public Column_nameContext column_name() {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public List<TerminalNode> DOT() {
            return this.getTokens(2);
        }

        public TerminalNode DOT(int i) {
            return this.getToken(2, i);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public Unary_operatorContext unary_operator() {
            return (Unary_operatorContext)this.getRuleContext(Unary_operatorContext.class, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public Function_nameContext function_name() {
            return (Function_nameContext)this.getRuleContext(Function_nameContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode STAR() {
            return this.getToken(7, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(62, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_CAST() {
            return this.getToken(43, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Type_nameContext type_name() {
            return (Type_nameContext)this.getRuleContext(Type_nameContext.class, 0);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_CASE() {
            return this.getToken(42, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(66, 0);
        }

        public List<TerminalNode> K_WHEN() {
            return this.getTokens(145);
        }

        public TerminalNode K_WHEN(int i) {
            return this.getToken(145, i);
        }

        public List<TerminalNode> K_THEN() {
            return this.getTokens(133);
        }

        public TerminalNode K_THEN(int i) {
            return this.getToken(133, i);
        }

        public TerminalNode K_ELSE() {
            return this.getToken(65, 0);
        }

        public Raise_functionContext raise_function() {
            return (Raise_functionContext)this.getRuleContext(Raise_functionContext.class, 0);
        }

        public TerminalNode PIPE2() {
            return this.getToken(11, 0);
        }

        public TerminalNode DIV() {
            return this.getToken(12, 0);
        }

        public TerminalNode MOD() {
            return this.getToken(13, 0);
        }

        public TerminalNode PLUS() {
            return this.getToken(8, 0);
        }

        public TerminalNode MINUS() {
            return this.getToken(9, 0);
        }

        public TerminalNode LT2() {
            return this.getToken(14, 0);
        }

        public TerminalNode GT2() {
            return this.getToken(15, 0);
        }

        public TerminalNode AMP() {
            return this.getToken(16, 0);
        }

        public TerminalNode PIPE() {
            return this.getToken(17, 0);
        }

        public TerminalNode LT() {
            return this.getToken(18, 0);
        }

        public TerminalNode LT_EQ() {
            return this.getToken(19, 0);
        }

        public TerminalNode GT() {
            return this.getToken(20, 0);
        }

        public TerminalNode GT_EQ() {
            return this.getToken(21, 0);
        }

        public TerminalNode ASSIGN() {
            return this.getToken(6, 0);
        }

        public TerminalNode EQ() {
            return this.getToken(22, 0);
        }

        public TerminalNode NOT_EQ1() {
            return this.getToken(23, 0);
        }

        public TerminalNode NOT_EQ2() {
            return this.getToken(24, 0);
        }

        public TerminalNode K_AND() {
            return this.getToken(32, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_IS() {
            return this.getToken(92, 0);
        }

        public TerminalNode K_BETWEEN() {
            return this.getToken(39, 0);
        }

        public TerminalNode K_IN() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(45, 0);
        }

        public Collation_nameContext collation_name() {
            return (Collation_nameContext)this.getRuleContext(Collation_nameContext.class, 0);
        }

        public TerminalNode K_LIKE() {
            return this.getToken(97, 0);
        }

        public TerminalNode K_GLOB() {
            return this.getToken(77, 0);
        }

        public TerminalNode K_REGEXP() {
            return this.getToken(118, 0);
        }

        public TerminalNode K_MATCH() {
            return this.getToken(99, 0);
        }

        public TerminalNode K_ESCAPE() {
            return this.getToken(67, 0);
        }

        public TerminalNode K_ISNULL() {
            return this.getToken(93, 0);
        }

        public TerminalNode K_NOTNULL() {
            return this.getToken(103, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(104, 0);
        }

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 39;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterExpr(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitExpr(this);
            }

        }
    }

    public static class Factored_select_stmtContext extends ParserRuleContext {
        public List<Select_coreContext> select_core() {
            return this.getRuleContexts(Select_coreContext.class);
        }

        public Select_coreContext select_core(int i) {
            return (Select_coreContext)this.getRuleContext(Select_coreContext.class, i);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public List<Compound_operatorContext> compound_operator() {
            return this.getRuleContexts(Compound_operatorContext.class);
        }

        public Compound_operatorContext compound_operator(int i) {
            return (Compound_operatorContext)this.getRuleContext(Compound_operatorContext.class, i);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public Factored_select_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 22;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterFactored_select_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitFactored_select_stmt(this);
            }

        }
    }

    public static class Foreign_key_clauseContext extends ParserRuleContext {
        public TerminalNode K_REFERENCES() {
            return this.getToken(117, 0);
        }

        public Foreign_tableContext foreign_table() {
            return (Foreign_tableContext)this.getRuleContext(Foreign_tableContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode K_DEFERRABLE() {
            return this.getToken(57, 0);
        }

        public List<TerminalNode> K_ON() {
            return this.getTokens(107);
        }

        public TerminalNode K_ON(int i) {
            return this.getToken(107, i);
        }

        public List<TerminalNode> K_MATCH() {
            return this.getTokens(99);
        }

        public TerminalNode K_MATCH(int i) {
            return this.getToken(99, i);
        }

        public List<NameContext> name() {
            return this.getRuleContexts(NameContext.class);
        }

        public NameContext name(int i) {
            return (NameContext)this.getRuleContext(NameContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public List<TerminalNode> K_DELETE() {
            return this.getTokens(59);
        }

        public TerminalNode K_DELETE(int i) {
            return this.getToken(59, i);
        }

        public List<TerminalNode> K_UPDATE() {
            return this.getTokens(139);
        }

        public TerminalNode K_UPDATE(int i) {
            return this.getToken(139, i);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_INITIALLY() {
            return this.getToken(86, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(58, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(82, 0);
        }

        public List<TerminalNode> K_SET() {
            return this.getTokens(129);
        }

        public TerminalNode K_SET(int i) {
            return this.getToken(129, i);
        }

        public List<TerminalNode> K_NULL() {
            return this.getTokens(104);
        }

        public TerminalNode K_NULL(int i) {
            return this.getToken(104, i);
        }

        public List<TerminalNode> K_DEFAULT() {
            return this.getTokens(56);
        }

        public TerminalNode K_DEFAULT(int i) {
            return this.getToken(56, i);
        }

        public List<TerminalNode> K_CASCADE() {
            return this.getTokens(41);
        }

        public TerminalNode K_CASCADE(int i) {
            return this.getToken(41, i);
        }

        public List<TerminalNode> K_RESTRICT() {
            return this.getTokens(123);
        }

        public TerminalNode K_RESTRICT(int i) {
            return this.getToken(123, i);
        }

        public List<TerminalNode> K_NO() {
            return this.getTokens(101);
        }

        public TerminalNode K_NO(int i) {
            return this.getToken(101, i);
        }

        public List<TerminalNode> K_ACTION() {
            return this.getTokens(26);
        }

        public TerminalNode K_ACTION(int i) {
            return this.getToken(26, i);
        }

        public Foreign_key_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 40;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterForeign_key_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitForeign_key_clause(this);
            }

        }
    }

    public static class Foreign_tableContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Foreign_tableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 73;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterForeign_table(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitForeign_table(this);
            }

        }
    }

    public static class Function_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Function_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 64;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterFunction_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitFunction_name(this);
            }

        }
    }

    public static class Index_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Index_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 74;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterIndex_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitIndex_name(this);
            }

        }
    }

    public static class Indexed_columnContext extends ParserRuleContext {
        public Column_nameContext column_name() {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(45, 0);
        }

        public Collation_nameContext collation_name() {
            return (Collation_nameContext)this.getRuleContext(Collation_nameContext.class, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(60, 0);
        }

        public Indexed_columnContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 42;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterIndexed_column(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitIndexed_column(this);
            }

        }
    }

    public static class Insert_stmtContext extends ParserRuleContext {
        public TerminalNode K_INTO() {
            return this.getToken(91, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(88, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(122, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(142, 0);
        }

        public List<TerminalNode> OPEN_PAR() {
            return this.getTokens(3);
        }

        public TerminalNode OPEN_PAR(int i) {
            return this.getToken(3, i);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> CLOSE_PAR() {
            return this.getTokens(4);
        }

        public TerminalNode CLOSE_PAR(int i) {
            return this.getToken(4, i);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(56, 0);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 23;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterInsert_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitInsert_stmt(this);
            }

        }
    }

    public static class Join_clauseContext extends ParserRuleContext {
        public List<Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(Table_or_subqueryContext.class);
        }

        public Table_or_subqueryContext table_or_subquery(int i) {
            return (Table_or_subqueryContext)this.getRuleContext(Table_or_subqueryContext.class, i);
        }

        public List<Join_operatorContext> join_operator() {
            return this.getRuleContexts(Join_operatorContext.class);
        }

        public Join_operatorContext join_operator(int i) {
            return (Join_operatorContext)this.getRuleContext(Join_operatorContext.class, i);
        }

        public List<Join_constraintContext> join_constraint() {
            return this.getRuleContexts(Join_constraintContext.class);
        }

        public Join_constraintContext join_constraint(int i) {
            return (Join_constraintContext)this.getRuleContext(Join_constraintContext.class, i);
        }

        public Join_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 51;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_clause(this);
            }

        }
    }

    public static class Join_constraintContext extends ParserRuleContext {
        public TerminalNode K_ON() {
            return this.getToken(107, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(140, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Join_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 53;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_constraint(this);
            }

        }
    }

    public static class Join_operatorContext extends ParserRuleContext {
        public TerminalNode COMMA() {
            return this.getToken(5, 0);
        }

        public TerminalNode K_JOIN() {
            return this.getToken(94, 0);
        }

        public TerminalNode K_NATURAL() {
            return this.getToken(100, 0);
        }

        public TerminalNode K_LEFT() {
            return this.getToken(96, 0);
        }

        public TerminalNode K_INNER() {
            return this.getToken(87, 0);
        }

        public TerminalNode K_CROSS() {
            return this.getToken(51, 0);
        }

        public TerminalNode K_OUTER() {
            return this.getToken(110, 0);
        }

        public Join_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 52;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterJoin_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitJoin_operator(this);
            }

        }
    }

    public static class KeywordContext extends ParserRuleContext {
        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_ACTION() {
            return this.getToken(26, 0);
        }

        public TerminalNode K_ADD() {
            return this.getToken(27, 0);
        }

        public TerminalNode K_AFTER() {
            return this.getToken(28, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(29, 0);
        }

        public TerminalNode K_ALTER() {
            return this.getToken(30, 0);
        }

        public TerminalNode K_ANALYZE() {
            return this.getToken(31, 0);
        }

        public TerminalNode K_AND() {
            return this.getToken(32, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_ATTACH() {
            return this.getToken(35, 0);
        }

        public TerminalNode K_AUTOINCREMENT() {
            return this.getToken(36, 0);
        }

        public TerminalNode K_BEFORE() {
            return this.getToken(37, 0);
        }

        public TerminalNode K_BEGIN() {
            return this.getToken(38, 0);
        }

        public TerminalNode K_BETWEEN() {
            return this.getToken(39, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_CASCADE() {
            return this.getToken(41, 0);
        }

        public TerminalNode K_CASE() {
            return this.getToken(42, 0);
        }

        public TerminalNode K_CAST() {
            return this.getToken(43, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(44, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(45, 0);
        }

        public TerminalNode K_COLUMN() {
            return this.getToken(46, 0);
        }

        public TerminalNode K_COMMIT() {
            return this.getToken(47, 0);
        }

        public TerminalNode K_CONFLICT() {
            return this.getToken(48, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(49, 0);
        }

        public TerminalNode K_CREATE() {
            return this.getToken(50, 0);
        }

        public TerminalNode K_CROSS() {
            return this.getToken(51, 0);
        }

        public TerminalNode K_CURRENT_DATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_CURRENT_TIME() {
            return this.getToken(53, 0);
        }

        public TerminalNode K_CURRENT_TIMESTAMP() {
            return this.getToken(54, 0);
        }

        public TerminalNode K_DATABASE() {
            return this.getToken(55, 0);
        }

        public TerminalNode K_DEFAULT() {
            return this.getToken(56, 0);
        }

        public TerminalNode K_DEFERRABLE() {
            return this.getToken(57, 0);
        }

        public TerminalNode K_DEFERRED() {
            return this.getToken(58, 0);
        }

        public TerminalNode K_DELETE() {
            return this.getToken(59, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(60, 0);
        }

        public TerminalNode K_DETACH() {
            return this.getToken(61, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(62, 0);
        }

        public TerminalNode K_DROP() {
            return this.getToken(63, 0);
        }

        public TerminalNode K_EACH() {
            return this.getToken(64, 0);
        }

        public TerminalNode K_ELSE() {
            return this.getToken(65, 0);
        }

        public TerminalNode K_END() {
            return this.getToken(66, 0);
        }

        public TerminalNode K_ESCAPE() {
            return this.getToken(67, 0);
        }

        public TerminalNode K_EXCEPT() {
            return this.getToken(68, 0);
        }

        public TerminalNode K_EXCLUSIVE() {
            return this.getToken(69, 0);
        }

        public TerminalNode K_EXISTS() {
            return this.getToken(70, 0);
        }

        public TerminalNode K_EXPLAIN() {
            return this.getToken(71, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_FOR() {
            return this.getToken(73, 0);
        }

        public TerminalNode K_FOREIGN() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_FROM() {
            return this.getToken(75, 0);
        }

        public TerminalNode K_FULL() {
            return this.getToken(76, 0);
        }

        public TerminalNode K_GLOB() {
            return this.getToken(77, 0);
        }

        public TerminalNode K_GROUP() {
            return this.getToken(78, 0);
        }

        public TerminalNode K_HAVING() {
            return this.getToken(79, 0);
        }

        public TerminalNode K_IF() {
            return this.getToken(80, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public TerminalNode K_IMMEDIATE() {
            return this.getToken(82, 0);
        }

        public TerminalNode K_IN() {
            return this.getToken(83, 0);
        }

        public TerminalNode K_INDEX() {
            return this.getToken(84, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(85, 0);
        }

        public TerminalNode K_INITIALLY() {
            return this.getToken(86, 0);
        }

        public TerminalNode K_INNER() {
            return this.getToken(87, 0);
        }

        public TerminalNode K_INSERT() {
            return this.getToken(88, 0);
        }

        public TerminalNode K_INSTEAD() {
            return this.getToken(89, 0);
        }

        public TerminalNode K_INTERSECT() {
            return this.getToken(90, 0);
        }

        public TerminalNode K_INTO() {
            return this.getToken(91, 0);
        }

        public TerminalNode K_IS() {
            return this.getToken(92, 0);
        }

        public TerminalNode K_ISNULL() {
            return this.getToken(93, 0);
        }

        public TerminalNode K_JOIN() {
            return this.getToken(94, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(95, 0);
        }

        public TerminalNode K_LEFT() {
            return this.getToken(96, 0);
        }

        public TerminalNode K_LIKE() {
            return this.getToken(97, 0);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public TerminalNode K_MATCH() {
            return this.getToken(99, 0);
        }

        public TerminalNode K_NATURAL() {
            return this.getToken(100, 0);
        }

        public TerminalNode K_NO() {
            return this.getToken(101, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_NOTNULL() {
            return this.getToken(103, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_OF() {
            return this.getToken(105, 0);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public TerminalNode K_ON() {
            return this.getToken(107, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_OUTER() {
            return this.getToken(110, 0);
        }

        public TerminalNode K_PLAN() {
            return this.getToken(111, 0);
        }

        public TerminalNode K_PRAGMA() {
            return this.getToken(112, 0);
        }

        public TerminalNode K_PRIMARY() {
            return this.getToken(113, 0);
        }

        public TerminalNode K_QUERY() {
            return this.getToken(114, 0);
        }

        public TerminalNode K_RAISE() {
            return this.getToken(115, 0);
        }

        public TerminalNode K_RECURSIVE() {
            return this.getToken(116, 0);
        }

        public TerminalNode K_REFERENCES() {
            return this.getToken(117, 0);
        }

        public TerminalNode K_REGEXP() {
            return this.getToken(118, 0);
        }

        public TerminalNode K_REINDEX() {
            return this.getToken(119, 0);
        }

        public TerminalNode K_RELEASE() {
            return this.getToken(120, 0);
        }

        public TerminalNode K_RENAME() {
            return this.getToken(121, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(122, 0);
        }

        public TerminalNode K_RESTRICT() {
            return this.getToken(123, 0);
        }

        public TerminalNode K_RIGHT() {
            return this.getToken(124, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ROW() {
            return this.getToken(126, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(127, 0);
        }

        public TerminalNode K_SELECT() {
            return this.getToken(128, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(129, 0);
        }

        public TerminalNode K_TABLE() {
            return this.getToken(130, 0);
        }

        public TerminalNode K_TEMP() {
            return this.getToken(131, 0);
        }

        public TerminalNode K_TEMPORARY() {
            return this.getToken(132, 0);
        }

        public TerminalNode K_THEN() {
            return this.getToken(133, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(134, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(135, 0);
        }

        public TerminalNode K_TRIGGER() {
            return this.getToken(136, 0);
        }

        public TerminalNode K_UNION() {
            return this.getToken(137, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(138, 0);
        }

        public TerminalNode K_UPDATE() {
            return this.getToken(139, 0);
        }

        public TerminalNode K_USING() {
            return this.getToken(140, 0);
        }

        public TerminalNode K_VACUUM() {
            return this.getToken(141, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(142, 0);
        }

        public TerminalNode K_VIEW() {
            return this.getToken(143, 0);
        }

        public TerminalNode K_VIRTUAL() {
            return this.getToken(144, 0);
        }

        public TerminalNode K_WHEN() {
            return this.getToken(145, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public TerminalNode K_WITH() {
            return this.getToken(147, 0);
        }

        public TerminalNode K_WITHOUT() {
            return this.getToken(148, 0);
        }

        public KeywordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 62;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterKeyword(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitKeyword(this);
            }

        }
    }

    public static class Literal_valueContext extends ParserRuleContext {
        public TerminalNode NUMERIC_LITERAL() {
            return this.getToken(150, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public TerminalNode BLOB_LITERAL() {
            return this.getToken(153, 0);
        }

        public TerminalNode K_NULL() {
            return this.getToken(104, 0);
        }

        public TerminalNode K_CURRENT_TIME() {
            return this.getToken(53, 0);
        }

        public TerminalNode K_CURRENT_DATE() {
            return this.getToken(52, 0);
        }

        public TerminalNode K_CURRENT_TIMESTAMP() {
            return this.getToken(54, 0);
        }

        public Literal_valueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 57;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterLiteral_value(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitLiteral_value(this);
            }

        }
    }

    public static class Module_argumentContext extends ParserRuleContext {
        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public Column_defContext column_def() {
            return (Column_defContext)this.getRuleContext(Column_defContext.class, 0);
        }

        public Module_argumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 60;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterModule_argument(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitModule_argument(this);
            }

        }
    }

    public static class Module_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Module_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 77;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterModule_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitModule_name(this);
            }

        }
    }

    public static class NameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public NameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 63;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterName(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitName(this);
            }

        }
    }

    public static class New_table_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public New_table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 70;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterNew_table_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitNew_table_name(this);
            }

        }
    }

    public static class Ordering_termContext extends ParserRuleContext {
        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode K_COLLATE() {
            return this.getToken(45, 0);
        }

        public Collation_nameContext collation_name() {
            return (Collation_nameContext)this.getRuleContext(Collation_nameContext.class, 0);
        }

        public TerminalNode K_ASC() {
            return this.getToken(34, 0);
        }

        public TerminalNode K_DESC() {
            return this.getToken(60, 0);
        }

        public Ordering_termContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 46;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterOrdering_term(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitOrdering_term(this);
            }

        }
    }

    public static class ParseContext extends ParserRuleContext {
        public TerminalNode EOF() {
            return this.getToken(-1, 0);
        }

        public List<Sql_stmt_listContext> sql_stmt_list() {
            return this.getRuleContexts(Sql_stmt_listContext.class);
        }

        public Sql_stmt_listContext sql_stmt_list(int i) {
            return (Sql_stmt_listContext)this.getRuleContext(Sql_stmt_listContext.class, i);
        }

        public List<ErrorContext> error() {
            return this.getRuleContexts(ErrorContext.class);
        }

        public ErrorContext error(int i) {
            return (ErrorContext)this.getRuleContext(ErrorContext.class, i);
        }

        public ParseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 0;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterParse(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitParse(this);
            }

        }
    }

    public static class Pragma_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Pragma_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 78;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_name(this);
            }

        }
    }

    public static class Pragma_stmtContext extends ParserRuleContext {
        public TerminalNode K_PRAGMA() {
            return this.getToken(112, 0);
        }

        public Pragma_nameContext pragma_name() {
            return (Pragma_nameContext)this.getRuleContext(Pragma_nameContext.class, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode ASSIGN() {
            return this.getToken(6, 0);
        }

        public Pragma_valueContext pragma_value() {
            return (Pragma_valueContext)this.getRuleContext(Pragma_valueContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public Pragma_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 24;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_stmt(this);
            }

        }
    }

    public static class Pragma_valueContext extends ParserRuleContext {
        public Signed_numberContext signed_number() {
            return (Signed_numberContext)this.getRuleContext(Signed_numberContext.class, 0);
        }

        public NameContext name() {
            return (NameContext)this.getRuleContext(NameContext.class, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public Pragma_valueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 47;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterPragma_value(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitPragma_value(this);
            }

        }
    }

    public static class Qualified_table_nameContext extends ParserRuleContext {
        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(85, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public Index_nameContext index_name() {
            return (Index_nameContext)this.getRuleContext(Index_nameContext.class, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public Qualified_table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 45;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterQualified_table_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitQualified_table_name(this);
            }

        }
    }

    public static class Raise_functionContext extends ParserRuleContext {
        public TerminalNode K_RAISE() {
            return this.getToken(115, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public TerminalNode COMMA() {
            return this.getToken(5, 0);
        }

        public Error_messageContext error_message() {
            return (Error_messageContext)this.getRuleContext(Error_messageContext.class, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public Raise_functionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 41;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRaise_function(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRaise_function(this);
            }

        }
    }

    public static class Reindex_stmtContext extends ParserRuleContext {
        public TerminalNode K_REINDEX() {
            return this.getToken(119, 0);
        }

        public Collation_nameContext collation_name() {
            return (Collation_nameContext)this.getRuleContext(Collation_nameContext.class, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public Index_nameContext index_name() {
            return (Index_nameContext)this.getRuleContext(Index_nameContext.class, 0);
        }

        public Database_nameContext database_name() {
            return (Database_nameContext)this.getRuleContext(Database_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Reindex_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 25;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterReindex_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitReindex_stmt(this);
            }

        }
    }

    public static class Release_stmtContext extends ParserRuleContext {
        public TerminalNode K_RELEASE() {
            return this.getToken(120, 0);
        }

        public Savepoint_nameContext savepoint_name() {
            return (Savepoint_nameContext)this.getRuleContext(Savepoint_nameContext.class, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(127, 0);
        }

        public Release_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 26;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRelease_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRelease_stmt(this);
            }

        }
    }

    public static class Result_columnContext extends ParserRuleContext {
        public TerminalNode STAR() {
            return this.getToken(7, 0);
        }

        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public Column_aliasContext column_alias() {
            return (Column_aliasContext)this.getRuleContext(Column_aliasContext.class, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Result_columnContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 49;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterResult_column(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitResult_column(this);
            }

        }
    }

    public static class Rollback_stmtContext extends ParserRuleContext {
        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_TRANSACTION() {
            return this.getToken(135, 0);
        }

        public TerminalNode K_TO() {
            return this.getToken(134, 0);
        }

        public Savepoint_nameContext savepoint_name() {
            return (Savepoint_nameContext)this.getRuleContext(Savepoint_nameContext.class, 0);
        }

        public Transaction_nameContext transaction_name() {
            return (Transaction_nameContext)this.getRuleContext(Transaction_nameContext.class, 0);
        }

        public TerminalNode K_SAVEPOINT() {
            return this.getToken(127, 0);
        }

        public Rollback_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 27;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterRollback_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitRollback_stmt(this);
            }

        }
    }

    public static class Savepoint_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Savepoint_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 79;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSavepoint_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSavepoint_name(this);
            }

        }
    }

    public static class Savepoint_stmtContext extends ParserRuleContext {
        public TerminalNode K_SAVEPOINT() {
            return this.getToken(127, 0);
        }

        public Savepoint_nameContext savepoint_name() {
            return (Savepoint_nameContext)this.getRuleContext(Savepoint_nameContext.class, 0);
        }

        public Savepoint_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 28;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSavepoint_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSavepoint_stmt(this);
            }

        }
    }

    public static class Schema_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Schema_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 66;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSchema_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSchema_name(this);
            }

        }
    }

    public static class Select_coreContext extends ParserRuleContext {
        public TerminalNode K_SELECT() {
            return this.getToken(128, 0);
        }

        public List<Result_columnContext> result_column() {
            return this.getRuleContexts(Result_columnContext.class);
        }

        public Result_columnContext result_column(int i) {
            return (Result_columnContext)this.getRuleContext(Result_columnContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_FROM() {
            return this.getToken(75, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public TerminalNode K_GROUP() {
            return this.getToken(78, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(62, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(29, 0);
        }

        public List<Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(Table_or_subqueryContext.class);
        }

        public Table_or_subqueryContext table_or_subquery(int i) {
            return (Table_or_subqueryContext)this.getRuleContext(Table_or_subqueryContext.class, i);
        }

        public Join_clauseContext join_clause() {
            return (Join_clauseContext)this.getRuleContext(Join_clauseContext.class, 0);
        }

        public TerminalNode K_HAVING() {
            return this.getToken(79, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(142, 0);
        }

        public List<TerminalNode> OPEN_PAR() {
            return this.getTokens(3);
        }

        public TerminalNode OPEN_PAR(int i) {
            return this.getToken(3, i);
        }

        public List<TerminalNode> CLOSE_PAR() {
            return this.getTokens(4);
        }

        public TerminalNode CLOSE_PAR(int i) {
            return this.getToken(4, i);
        }

        public Select_coreContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 54;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSelect_core(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSelect_core(this);
            }

        }
    }

    public static class Select_or_valuesContext extends ParserRuleContext {
        public TerminalNode K_SELECT() {
            return this.getToken(128, 0);
        }

        public List<Result_columnContext> result_column() {
            return this.getRuleContexts(Result_columnContext.class);
        }

        public Result_columnContext result_column(int i) {
            return (Result_columnContext)this.getRuleContext(Result_columnContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_FROM() {
            return this.getToken(75, 0);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public TerminalNode K_GROUP() {
            return this.getToken(78, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public TerminalNode K_DISTINCT() {
            return this.getToken(62, 0);
        }

        public TerminalNode K_ALL() {
            return this.getToken(29, 0);
        }

        public List<Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(Table_or_subqueryContext.class);
        }

        public Table_or_subqueryContext table_or_subquery(int i) {
            return (Table_or_subqueryContext)this.getRuleContext(Table_or_subqueryContext.class, i);
        }

        public Join_clauseContext join_clause() {
            return (Join_clauseContext)this.getRuleContext(Join_clauseContext.class, 0);
        }

        public TerminalNode K_HAVING() {
            return this.getToken(79, 0);
        }

        public TerminalNode K_VALUES() {
            return this.getToken(142, 0);
        }

        public List<TerminalNode> OPEN_PAR() {
            return this.getTokens(3);
        }

        public TerminalNode OPEN_PAR(int i) {
            return this.getToken(3, i);
        }

        public List<TerminalNode> CLOSE_PAR() {
            return this.getTokens(4);
        }

        public TerminalNode CLOSE_PAR(int i) {
            return this.getToken(4, i);
        }

        public Select_or_valuesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 31;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSelect_or_values(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSelect_or_values(this);
            }

        }
    }

    public static class Select_stmtContext extends ParserRuleContext {
        public List<Select_or_valuesContext> select_or_values() {
            return this.getRuleContexts(Select_or_valuesContext.class);
        }

        public Select_or_valuesContext select_or_values(int i) {
            return (Select_or_valuesContext)this.getRuleContext(Select_or_valuesContext.class, i);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public List<Compound_operatorContext> compound_operator() {
            return this.getRuleContexts(Compound_operatorContext.class);
        }

        public Compound_operatorContext compound_operator(int i) {
            return (Compound_operatorContext)this.getRuleContext(Compound_operatorContext.class, i);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public Select_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 30;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSelect_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSelect_stmt(this);
            }

        }
    }

    public static class Signed_numberContext extends ParserRuleContext {
        public TerminalNode NUMERIC_LITERAL() {
            return this.getToken(150, 0);
        }

        public TerminalNode PLUS() {
            return this.getToken(8, 0);
        }

        public TerminalNode MINUS() {
            return this.getToken(9, 0);
        }

        public Signed_numberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 56;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSigned_number(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSigned_number(this);
            }

        }
    }

    public static class Simple_select_stmtContext extends ParserRuleContext {
        public Select_coreContext select_core() {
            return (Select_coreContext)this.getRuleContext(Select_coreContext.class, 0);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public Simple_select_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 29;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSimple_select_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSimple_select_stmt(this);
            }

        }
    }

    public static class Sql_stmtContext extends ParserRuleContext {
        public Alter_table_stmtContext alter_table_stmt() {
            return (Alter_table_stmtContext)this.getRuleContext(Alter_table_stmtContext.class, 0);
        }

        public Analyze_stmtContext analyze_stmt() {
            return (Analyze_stmtContext)this.getRuleContext(Analyze_stmtContext.class, 0);
        }

        public Attach_stmtContext attach_stmt() {
            return (Attach_stmtContext)this.getRuleContext(Attach_stmtContext.class, 0);
        }

        public Begin_stmtContext begin_stmt() {
            return (Begin_stmtContext)this.getRuleContext(Begin_stmtContext.class, 0);
        }

        public Commit_stmtContext commit_stmt() {
            return (Commit_stmtContext)this.getRuleContext(Commit_stmtContext.class, 0);
        }

        public Compound_select_stmtContext compound_select_stmt() {
            return (Compound_select_stmtContext)this.getRuleContext(Compound_select_stmtContext.class, 0);
        }

        public Create_index_stmtContext create_index_stmt() {
            return (Create_index_stmtContext)this.getRuleContext(Create_index_stmtContext.class, 0);
        }

        public Create_table_stmtContext create_table_stmt() {
            return (Create_table_stmtContext)this.getRuleContext(Create_table_stmtContext.class, 0);
        }

        public Create_trigger_stmtContext create_trigger_stmt() {
            return (Create_trigger_stmtContext)this.getRuleContext(Create_trigger_stmtContext.class, 0);
        }

        public Create_view_stmtContext create_view_stmt() {
            return (Create_view_stmtContext)this.getRuleContext(Create_view_stmtContext.class, 0);
        }

        public Create_virtual_table_stmtContext create_virtual_table_stmt() {
            return (Create_virtual_table_stmtContext)this.getRuleContext(Create_virtual_table_stmtContext.class, 0);
        }

        public Delete_stmtContext delete_stmt() {
            return (Delete_stmtContext)this.getRuleContext(Delete_stmtContext.class, 0);
        }

        public Delete_stmt_limitedContext delete_stmt_limited() {
            return (Delete_stmt_limitedContext)this.getRuleContext(Delete_stmt_limitedContext.class, 0);
        }

        public Detach_stmtContext detach_stmt() {
            return (Detach_stmtContext)this.getRuleContext(Detach_stmtContext.class, 0);
        }

        public Drop_index_stmtContext drop_index_stmt() {
            return (Drop_index_stmtContext)this.getRuleContext(Drop_index_stmtContext.class, 0);
        }

        public Drop_table_stmtContext drop_table_stmt() {
            return (Drop_table_stmtContext)this.getRuleContext(Drop_table_stmtContext.class, 0);
        }

        public Drop_trigger_stmtContext drop_trigger_stmt() {
            return (Drop_trigger_stmtContext)this.getRuleContext(Drop_trigger_stmtContext.class, 0);
        }

        public Drop_view_stmtContext drop_view_stmt() {
            return (Drop_view_stmtContext)this.getRuleContext(Drop_view_stmtContext.class, 0);
        }

        public Factored_select_stmtContext factored_select_stmt() {
            return (Factored_select_stmtContext)this.getRuleContext(Factored_select_stmtContext.class, 0);
        }

        public Insert_stmtContext insert_stmt() {
            return (Insert_stmtContext)this.getRuleContext(Insert_stmtContext.class, 0);
        }

        public Pragma_stmtContext pragma_stmt() {
            return (Pragma_stmtContext)this.getRuleContext(Pragma_stmtContext.class, 0);
        }

        public Reindex_stmtContext reindex_stmt() {
            return (Reindex_stmtContext)this.getRuleContext(Reindex_stmtContext.class, 0);
        }

        public Release_stmtContext release_stmt() {
            return (Release_stmtContext)this.getRuleContext(Release_stmtContext.class, 0);
        }

        public Rollback_stmtContext rollback_stmt() {
            return (Rollback_stmtContext)this.getRuleContext(Rollback_stmtContext.class, 0);
        }

        public Savepoint_stmtContext savepoint_stmt() {
            return (Savepoint_stmtContext)this.getRuleContext(Savepoint_stmtContext.class, 0);
        }

        public Simple_select_stmtContext simple_select_stmt() {
            return (Simple_select_stmtContext)this.getRuleContext(Simple_select_stmtContext.class, 0);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public Update_stmtContext update_stmt() {
            return (Update_stmtContext)this.getRuleContext(Update_stmtContext.class, 0);
        }

        public Update_stmt_limitedContext update_stmt_limited() {
            return (Update_stmt_limitedContext)this.getRuleContext(Update_stmt_limitedContext.class, 0);
        }

        public Vacuum_stmtContext vacuum_stmt() {
            return (Vacuum_stmtContext)this.getRuleContext(Vacuum_stmtContext.class, 0);
        }

        public TerminalNode K_EXPLAIN() {
            return this.getToken(71, 0);
        }

        public TerminalNode K_QUERY() {
            return this.getToken(114, 0);
        }

        public TerminalNode K_PLAN() {
            return this.getToken(111, 0);
        }

        public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 3;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSql_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSql_stmt(this);
            }

        }
    }

    public static class Sql_stmt_listContext extends ParserRuleContext {
        public List<Sql_stmtContext> sql_stmt() {
            return this.getRuleContexts(Sql_stmtContext.class);
        }

        public Sql_stmtContext sql_stmt(int i) {
            return (Sql_stmtContext)this.getRuleContext(Sql_stmtContext.class, i);
        }

        public List<TerminalNode> SCOL() {
            return this.getTokens(1);
        }

        public TerminalNode SCOL(int i) {
            return this.getToken(1, i);
        }

        public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 2;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterSql_stmt_list(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitSql_stmt_list(this);
            }

        }
    }

    public static class Table_aliasContext extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(149, 0);
        }

        public TerminalNode STRING_LITERAL() {
            return this.getToken(152, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public Table_aliasContext table_alias() {
            return (Table_aliasContext)this.getRuleContext(Table_aliasContext.class, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public Table_aliasContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 80;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_alias(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_alias(this);
            }

        }
    }

    public static class Table_constraintContext extends ParserRuleContext {
        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Indexed_columnContext> indexed_column() {
            return this.getRuleContexts(Indexed_columnContext.class);
        }

        public Indexed_columnContext indexed_column(int i) {
            return (Indexed_columnContext)this.getRuleContext(Indexed_columnContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public Conflict_clauseContext conflict_clause() {
            return (Conflict_clauseContext)this.getRuleContext(Conflict_clauseContext.class, 0);
        }

        public TerminalNode K_CHECK() {
            return this.getToken(44, 0);
        }

        public ExprContext expr() {
            return (ExprContext)this.getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode K_FOREIGN() {
            return this.getToken(74, 0);
        }

        public TerminalNode K_KEY() {
            return this.getToken(95, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public Foreign_key_clauseContext foreign_key_clause() {
            return (Foreign_key_clauseContext)this.getRuleContext(Foreign_key_clauseContext.class, 0);
        }

        public TerminalNode K_CONSTRAINT() {
            return this.getToken(49, 0);
        }

        public NameContext name() {
            return (NameContext)this.getRuleContext(NameContext.class, 0);
        }

        public TerminalNode K_PRIMARY() {
            return this.getToken(113, 0);
        }

        public TerminalNode K_UNIQUE() {
            return this.getToken(138, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public Table_constraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 43;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_constraint(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_constraint(this);
            }

        }
    }

    public static class Table_function_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Table_function_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 67;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_function_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_function_name(this);
            }

        }
    }

    public static class Table_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Table_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 68;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_name(this);
            }

        }
    }

    public static class Table_or_index_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Table_or_index_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 69;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_or_index_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_or_index_name(this);
            }

        }
    }

    public static class Table_or_subqueryContext extends ParserRuleContext {
        public Table_nameContext table_name() {
            return (Table_nameContext)this.getRuleContext(Table_nameContext.class, 0);
        }

        public Schema_nameContext schema_name() {
            return (Schema_nameContext)this.getRuleContext(Schema_nameContext.class, 0);
        }

        public TerminalNode DOT() {
            return this.getToken(2, 0);
        }

        public Table_aliasContext table_alias() {
            return (Table_aliasContext)this.getRuleContext(Table_aliasContext.class, 0);
        }

        public TerminalNode K_INDEXED() {
            return this.getToken(85, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public Index_nameContext index_name() {
            return (Index_nameContext)this.getRuleContext(Index_nameContext.class, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public TerminalNode K_AS() {
            return this.getToken(33, 0);
        }

        public Table_function_nameContext table_function_name() {
            return (Table_function_nameContext)this.getRuleContext(Table_function_nameContext.class, 0);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public List<Table_or_subqueryContext> table_or_subquery() {
            return this.getRuleContexts(Table_or_subqueryContext.class);
        }

        public Table_or_subqueryContext table_or_subquery(int i) {
            return (Table_or_subqueryContext)this.getRuleContext(Table_or_subqueryContext.class, i);
        }

        public Join_clauseContext join_clause() {
            return (Join_clauseContext)this.getRuleContext(Join_clauseContext.class, 0);
        }

        public Select_stmtContext select_stmt() {
            return (Select_stmtContext)this.getRuleContext(Select_stmtContext.class, 0);
        }

        public Table_or_subqueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 50;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTable_or_subquery(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTable_or_subquery(this);
            }

        }
    }

    public static class Transaction_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Transaction_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 81;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTransaction_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTransaction_name(this);
            }

        }
    }

    public static class Trigger_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public Trigger_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 75;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterTrigger_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitTrigger_name(this);
            }

        }
    }

    public static class Type_nameContext extends ParserRuleContext {
        public List<NameContext> name() {
            return this.getRuleContexts(NameContext.class);
        }

        public NameContext name(int i) {
            return (NameContext)this.getRuleContext(NameContext.class, i);
        }

        public TerminalNode OPEN_PAR() {
            return this.getToken(3, 0);
        }

        public List<Signed_numberContext> signed_number() {
            return this.getRuleContexts(Signed_numberContext.class);
        }

        public Signed_numberContext signed_number(int i) {
            return (Signed_numberContext)this.getRuleContext(Signed_numberContext.class, i);
        }

        public TerminalNode CLOSE_PAR() {
            return this.getToken(4, 0);
        }

        public TerminalNode COMMA() {
            return this.getToken(5, 0);
        }

        public Type_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 36;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterType_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitType_name(this);
            }

        }
    }

    public static class Unary_operatorContext extends ParserRuleContext {
        public TerminalNode MINUS() {
            return this.getToken(9, 0);
        }

        public TerminalNode PLUS() {
            return this.getToken(8, 0);
        }

        public TerminalNode TILDE() {
            return this.getToken(10, 0);
        }

        public TerminalNode K_NOT() {
            return this.getToken(102, 0);
        }

        public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 58;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUnary_operator(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUnary_operator(this);
            }

        }
    }

    public static class Update_stmtContext extends ParserRuleContext {
        public TerminalNode K_UPDATE() {
            return this.getToken(139, 0);
        }

        public Qualified_table_nameContext qualified_table_name() {
            return (Qualified_table_nameContext)this.getRuleContext(Qualified_table_nameContext.class, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(129, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public List<TerminalNode> ASSIGN() {
            return this.getTokens(6);
        }

        public TerminalNode ASSIGN(int i) {
            return this.getToken(6, i);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(122, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public Update_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 32;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUpdate_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUpdate_stmt(this);
            }

        }
    }

    public static class Update_stmt_limitedContext extends ParserRuleContext {
        public TerminalNode K_UPDATE() {
            return this.getToken(139, 0);
        }

        public Qualified_table_nameContext qualified_table_name() {
            return (Qualified_table_nameContext)this.getRuleContext(Qualified_table_nameContext.class, 0);
        }

        public TerminalNode K_SET() {
            return this.getToken(129, 0);
        }

        public List<Column_nameContext> column_name() {
            return this.getRuleContexts(Column_nameContext.class);
        }

        public Column_nameContext column_name(int i) {
            return (Column_nameContext)this.getRuleContext(Column_nameContext.class, i);
        }

        public List<TerminalNode> ASSIGN() {
            return this.getTokens(6);
        }

        public TerminalNode ASSIGN(int i) {
            return this.getToken(6, i);
        }

        public List<ExprContext> expr() {
            return this.getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return (ExprContext)this.getRuleContext(ExprContext.class, i);
        }

        public With_clauseContext with_clause() {
            return (With_clauseContext)this.getRuleContext(With_clauseContext.class, 0);
        }

        public TerminalNode K_OR() {
            return this.getToken(108, 0);
        }

        public TerminalNode K_ROLLBACK() {
            return this.getToken(125, 0);
        }

        public TerminalNode K_ABORT() {
            return this.getToken(25, 0);
        }

        public TerminalNode K_REPLACE() {
            return this.getToken(122, 0);
        }

        public TerminalNode K_FAIL() {
            return this.getToken(72, 0);
        }

        public TerminalNode K_IGNORE() {
            return this.getToken(81, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public TerminalNode K_WHERE() {
            return this.getToken(146, 0);
        }

        public TerminalNode K_LIMIT() {
            return this.getToken(98, 0);
        }

        public TerminalNode K_ORDER() {
            return this.getToken(109, 0);
        }

        public TerminalNode K_BY() {
            return this.getToken(40, 0);
        }

        public List<Ordering_termContext> ordering_term() {
            return this.getRuleContexts(Ordering_termContext.class);
        }

        public Ordering_termContext ordering_term(int i) {
            return (Ordering_termContext)this.getRuleContext(Ordering_termContext.class, i);
        }

        public TerminalNode K_OFFSET() {
            return this.getToken(106, 0);
        }

        public Update_stmt_limitedContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 33;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterUpdate_stmt_limited(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitUpdate_stmt_limited(this);
            }

        }
    }

    public static class Vacuum_stmtContext extends ParserRuleContext {
        public TerminalNode K_VACUUM() {
            return this.getToken(141, 0);
        }

        public Vacuum_stmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 34;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterVacuum_stmt(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitVacuum_stmt(this);
            }

        }
    }

    public static class View_nameContext extends ParserRuleContext {
        public Any_nameContext any_name() {
            return (Any_nameContext)this.getRuleContext(Any_nameContext.class, 0);
        }

        public View_nameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 76;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterView_name(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitView_name(this);
            }

        }
    }

    public static class With_clauseContext extends ParserRuleContext {
        public TerminalNode K_WITH() {
            return this.getToken(147, 0);
        }

        public List<Common_table_expressionContext> common_table_expression() {
            return this.getRuleContexts(Common_table_expressionContext.class);
        }

        public Common_table_expressionContext common_table_expression(int i) {
            return (Common_table_expressionContext)this.getRuleContext(Common_table_expressionContext.class, i);
        }

        public TerminalNode K_RECURSIVE() {
            return this.getToken(116, 0);
        }

        public List<TerminalNode> COMMA() {
            return this.getTokens(5);
        }

        public TerminalNode COMMA(int i) {
            return this.getToken(5, i);
        }

        public With_clauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 44;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).enterWith_clause(this);
            }

        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SQLiteListener) {
                ((SQLiteListener)listener).exitWith_clause(this);
            }

        }
    }
}

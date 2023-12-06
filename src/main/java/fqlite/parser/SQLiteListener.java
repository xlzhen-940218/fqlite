//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

public interface SQLiteListener extends ParseTreeListener {
    void enterParse(SQLiteParser.ParseContext var1);

    void exitParse(SQLiteParser.ParseContext var1);

    void enterError(SQLiteParser.ErrorContext var1);

    void exitError(SQLiteParser.ErrorContext var1);

    void enterSql_stmt_list(SQLiteParser.Sql_stmt_listContext var1);

    void exitSql_stmt_list(SQLiteParser.Sql_stmt_listContext var1);

    void enterSql_stmt(SQLiteParser.Sql_stmtContext var1);

    void exitSql_stmt(SQLiteParser.Sql_stmtContext var1);

    void enterAlter_table_stmt(SQLiteParser.Alter_table_stmtContext var1);

    void exitAlter_table_stmt(SQLiteParser.Alter_table_stmtContext var1);

    void enterAnalyze_stmt(SQLiteParser.Analyze_stmtContext var1);

    void exitAnalyze_stmt(SQLiteParser.Analyze_stmtContext var1);

    void enterAttach_stmt(SQLiteParser.Attach_stmtContext var1);

    void exitAttach_stmt(SQLiteParser.Attach_stmtContext var1);

    void enterBegin_stmt(SQLiteParser.Begin_stmtContext var1);

    void exitBegin_stmt(SQLiteParser.Begin_stmtContext var1);

    void enterCommit_stmt(SQLiteParser.Commit_stmtContext var1);

    void exitCommit_stmt(SQLiteParser.Commit_stmtContext var1);

    void enterCompound_select_stmt(SQLiteParser.Compound_select_stmtContext var1);

    void exitCompound_select_stmt(SQLiteParser.Compound_select_stmtContext var1);

    void enterCreate_index_stmt(SQLiteParser.Create_index_stmtContext var1);

    void exitCreate_index_stmt(SQLiteParser.Create_index_stmtContext var1);

    void enterCreate_table_stmt(SQLiteParser.Create_table_stmtContext var1);

    void exitCreate_table_stmt(SQLiteParser.Create_table_stmtContext var1);

    void enterCreate_trigger_stmt(SQLiteParser.Create_trigger_stmtContext var1);

    void exitCreate_trigger_stmt(SQLiteParser.Create_trigger_stmtContext var1);

    void enterCreate_view_stmt(SQLiteParser.Create_view_stmtContext var1);

    void exitCreate_view_stmt(SQLiteParser.Create_view_stmtContext var1);

    void enterCreate_virtual_table_stmt(SQLiteParser.Create_virtual_table_stmtContext var1);

    void exitCreate_virtual_table_stmt(SQLiteParser.Create_virtual_table_stmtContext var1);

    void enterDelete_stmt(SQLiteParser.Delete_stmtContext var1);

    void exitDelete_stmt(SQLiteParser.Delete_stmtContext var1);

    void enterDelete_stmt_limited(SQLiteParser.Delete_stmt_limitedContext var1);

    void exitDelete_stmt_limited(SQLiteParser.Delete_stmt_limitedContext var1);

    void enterDetach_stmt(SQLiteParser.Detach_stmtContext var1);

    void exitDetach_stmt(SQLiteParser.Detach_stmtContext var1);

    void enterDrop_index_stmt(SQLiteParser.Drop_index_stmtContext var1);

    void exitDrop_index_stmt(SQLiteParser.Drop_index_stmtContext var1);

    void enterDrop_table_stmt(SQLiteParser.Drop_table_stmtContext var1);

    void exitDrop_table_stmt(SQLiteParser.Drop_table_stmtContext var1);

    void enterDrop_trigger_stmt(SQLiteParser.Drop_trigger_stmtContext var1);

    void exitDrop_trigger_stmt(SQLiteParser.Drop_trigger_stmtContext var1);

    void enterDrop_view_stmt(SQLiteParser.Drop_view_stmtContext var1);

    void exitDrop_view_stmt(SQLiteParser.Drop_view_stmtContext var1);

    void enterFactored_select_stmt(SQLiteParser.Factored_select_stmtContext var1);

    void exitFactored_select_stmt(SQLiteParser.Factored_select_stmtContext var1);

    void enterInsert_stmt(SQLiteParser.Insert_stmtContext var1);

    void exitInsert_stmt(SQLiteParser.Insert_stmtContext var1);

    void enterPragma_stmt(SQLiteParser.Pragma_stmtContext var1);

    void exitPragma_stmt(SQLiteParser.Pragma_stmtContext var1);

    void enterReindex_stmt(SQLiteParser.Reindex_stmtContext var1);

    void exitReindex_stmt(SQLiteParser.Reindex_stmtContext var1);

    void enterRelease_stmt(SQLiteParser.Release_stmtContext var1);

    void exitRelease_stmt(SQLiteParser.Release_stmtContext var1);

    void enterRollback_stmt(SQLiteParser.Rollback_stmtContext var1);

    void exitRollback_stmt(SQLiteParser.Rollback_stmtContext var1);

    void enterSavepoint_stmt(SQLiteParser.Savepoint_stmtContext var1);

    void exitSavepoint_stmt(SQLiteParser.Savepoint_stmtContext var1);

    void enterSimple_select_stmt(SQLiteParser.Simple_select_stmtContext var1);

    void exitSimple_select_stmt(SQLiteParser.Simple_select_stmtContext var1);

    void enterSelect_stmt(SQLiteParser.Select_stmtContext var1);

    void exitSelect_stmt(SQLiteParser.Select_stmtContext var1);

    void enterSelect_or_values(SQLiteParser.Select_or_valuesContext var1);

    void exitSelect_or_values(SQLiteParser.Select_or_valuesContext var1);

    void enterUpdate_stmt(SQLiteParser.Update_stmtContext var1);

    void exitUpdate_stmt(SQLiteParser.Update_stmtContext var1);

    void enterUpdate_stmt_limited(SQLiteParser.Update_stmt_limitedContext var1);

    void exitUpdate_stmt_limited(SQLiteParser.Update_stmt_limitedContext var1);

    void enterVacuum_stmt(SQLiteParser.Vacuum_stmtContext var1);

    void exitVacuum_stmt(SQLiteParser.Vacuum_stmtContext var1);

    void enterColumn_def(SQLiteParser.Column_defContext var1);

    void exitColumn_def(SQLiteParser.Column_defContext var1);

    void enterType_name(SQLiteParser.Type_nameContext var1);

    void exitType_name(SQLiteParser.Type_nameContext var1);

    void enterColumn_constraint(SQLiteParser.Column_constraintContext var1);

    void exitColumn_constraint(SQLiteParser.Column_constraintContext var1);

    void enterConflict_clause(SQLiteParser.Conflict_clauseContext var1);

    void exitConflict_clause(SQLiteParser.Conflict_clauseContext var1);

    void enterExpr(SQLiteParser.ExprContext var1);

    void exitExpr(SQLiteParser.ExprContext var1);

    void enterForeign_key_clause(SQLiteParser.Foreign_key_clauseContext var1);

    void exitForeign_key_clause(SQLiteParser.Foreign_key_clauseContext var1);

    void enterRaise_function(SQLiteParser.Raise_functionContext var1);

    void exitRaise_function(SQLiteParser.Raise_functionContext var1);

    void enterIndexed_column(SQLiteParser.Indexed_columnContext var1);

    void exitIndexed_column(SQLiteParser.Indexed_columnContext var1);

    void enterTable_constraint(SQLiteParser.Table_constraintContext var1);

    void exitTable_constraint(SQLiteParser.Table_constraintContext var1);

    void enterWith_clause(SQLiteParser.With_clauseContext var1);

    void exitWith_clause(SQLiteParser.With_clauseContext var1);

    void enterQualified_table_name(SQLiteParser.Qualified_table_nameContext var1);

    void exitQualified_table_name(SQLiteParser.Qualified_table_nameContext var1);

    void enterOrdering_term(SQLiteParser.Ordering_termContext var1);

    void exitOrdering_term(SQLiteParser.Ordering_termContext var1);

    void enterPragma_value(SQLiteParser.Pragma_valueContext var1);

    void exitPragma_value(SQLiteParser.Pragma_valueContext var1);

    void enterCommon_table_expression(SQLiteParser.Common_table_expressionContext var1);

    void exitCommon_table_expression(SQLiteParser.Common_table_expressionContext var1);

    void enterResult_column(SQLiteParser.Result_columnContext var1);

    void exitResult_column(SQLiteParser.Result_columnContext var1);

    void enterTable_or_subquery(SQLiteParser.Table_or_subqueryContext var1);

    void exitTable_or_subquery(SQLiteParser.Table_or_subqueryContext var1);

    void enterJoin_clause(SQLiteParser.Join_clauseContext var1);

    void exitJoin_clause(SQLiteParser.Join_clauseContext var1);

    void enterJoin_operator(SQLiteParser.Join_operatorContext var1);

    void exitJoin_operator(SQLiteParser.Join_operatorContext var1);

    void enterJoin_constraint(SQLiteParser.Join_constraintContext var1);

    void exitJoin_constraint(SQLiteParser.Join_constraintContext var1);

    void enterSelect_core(SQLiteParser.Select_coreContext var1);

    void exitSelect_core(SQLiteParser.Select_coreContext var1);

    void enterCompound_operator(SQLiteParser.Compound_operatorContext var1);

    void exitCompound_operator(SQLiteParser.Compound_operatorContext var1);

    void enterSigned_number(SQLiteParser.Signed_numberContext var1);

    void exitSigned_number(SQLiteParser.Signed_numberContext var1);

    void enterLiteral_value(SQLiteParser.Literal_valueContext var1);

    void exitLiteral_value(SQLiteParser.Literal_valueContext var1);

    void enterUnary_operator(SQLiteParser.Unary_operatorContext var1);

    void exitUnary_operator(SQLiteParser.Unary_operatorContext var1);

    void enterError_message(SQLiteParser.Error_messageContext var1);

    void exitError_message(SQLiteParser.Error_messageContext var1);

    void enterModule_argument(SQLiteParser.Module_argumentContext var1);

    void exitModule_argument(SQLiteParser.Module_argumentContext var1);

    void enterColumn_alias(SQLiteParser.Column_aliasContext var1);

    void exitColumn_alias(SQLiteParser.Column_aliasContext var1);

    void enterKeyword(SQLiteParser.KeywordContext var1);

    void exitKeyword(SQLiteParser.KeywordContext var1);

    void enterName(SQLiteParser.NameContext var1);

    void exitName(SQLiteParser.NameContext var1);

    void enterFunction_name(SQLiteParser.Function_nameContext var1);

    void exitFunction_name(SQLiteParser.Function_nameContext var1);

    void enterDatabase_name(SQLiteParser.Database_nameContext var1);

    void exitDatabase_name(SQLiteParser.Database_nameContext var1);

    void enterSchema_name(SQLiteParser.Schema_nameContext var1);

    void exitSchema_name(SQLiteParser.Schema_nameContext var1);

    void enterTable_function_name(SQLiteParser.Table_function_nameContext var1);

    void exitTable_function_name(SQLiteParser.Table_function_nameContext var1);

    void enterTable_name(SQLiteParser.Table_nameContext var1);

    void exitTable_name(SQLiteParser.Table_nameContext var1);

    void enterTable_or_index_name(SQLiteParser.Table_or_index_nameContext var1);

    void exitTable_or_index_name(SQLiteParser.Table_or_index_nameContext var1);

    void enterNew_table_name(SQLiteParser.New_table_nameContext var1);

    void exitNew_table_name(SQLiteParser.New_table_nameContext var1);

    void enterColumn_name(SQLiteParser.Column_nameContext var1);

    void exitColumn_name(SQLiteParser.Column_nameContext var1);

    void enterCollation_name(SQLiteParser.Collation_nameContext var1);

    void exitCollation_name(SQLiteParser.Collation_nameContext var1);

    void enterForeign_table(SQLiteParser.Foreign_tableContext var1);

    void exitForeign_table(SQLiteParser.Foreign_tableContext var1);

    void enterIndex_name(SQLiteParser.Index_nameContext var1);

    void exitIndex_name(SQLiteParser.Index_nameContext var1);

    void enterTrigger_name(SQLiteParser.Trigger_nameContext var1);

    void exitTrigger_name(SQLiteParser.Trigger_nameContext var1);

    void enterView_name(SQLiteParser.View_nameContext var1);

    void exitView_name(SQLiteParser.View_nameContext var1);

    void enterModule_name(SQLiteParser.Module_nameContext var1);

    void exitModule_name(SQLiteParser.Module_nameContext var1);

    void enterPragma_name(SQLiteParser.Pragma_nameContext var1);

    void exitPragma_name(SQLiteParser.Pragma_nameContext var1);

    void enterSavepoint_name(SQLiteParser.Savepoint_nameContext var1);

    void exitSavepoint_name(SQLiteParser.Savepoint_nameContext var1);

    void enterTable_alias(SQLiteParser.Table_aliasContext var1);

    void exitTable_alias(SQLiteParser.Table_aliasContext var1);

    void enterTransaction_name(SQLiteParser.Transaction_nameContext var1);

    void exitTransaction_name(SQLiteParser.Transaction_nameContext var1);

    void enterAny_name(SQLiteParser.Any_nameContext var1);

    void exitAny_name(SQLiteParser.Any_nameContext var1);
}

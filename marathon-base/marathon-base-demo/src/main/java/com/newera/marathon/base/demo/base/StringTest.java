package com.newera.marathon.base.demo.base;

public class StringTest {
    public static void main(String[] args) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("t2.*");
        sql.append(",t3.SCORE");
        sql.append(",t3.COMMENTS ");
        sql.append("from ");
        sql.append("(");
        sql.append("select ");
        sql.append("edi.CCL_LOG_ID");
        sql.append(",edi.CCL_EVAL_ID");
        sql.append(",ei.DIMENSION");
        sql.append(",ei.DIMENSION_NAME");
        sql.append(",ei.SECONDARY_DIMENSION_NAME");
        sql.append(",ei.TYPE");
        sql.append(",ei.NAME ");
        sql.append("from ");
        sql.append("M_CCL_EVALUATION_DIMENSION_ITEM edi,M_CCL_EVALUATION_ITEM ei ");
        sql.append("where ");
        sql.append("edi.CCL_EVALUATION_ITEM_ID=ei.SRC_ID and edi.DELETED=0 and edi.CCL_LOG_ID in (:logIds) ");
        sql.append(") t2 ");
        sql.append("left join ");
        sql.append("M_CCL_EVALUATION_DIMENSION t3 ");
        sql.append("on t3.CCL_LOG_ID= t2.CCL_LOG_ID and t3.CCL_EVAL_ID=t2.CCL_EVAL_ID and t3.DIMENSION_ID=t2.DIMENSION and t3.DELETED=0");
        System.out.println(sql.toString());
    }
}

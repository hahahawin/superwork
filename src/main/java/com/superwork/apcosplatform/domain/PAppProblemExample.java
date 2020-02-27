package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PAppProblemExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PAppProblemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProblemDescIsNull() {
            addCriterion("PROBLEM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andProblemDescIsNotNull() {
            addCriterion("PROBLEM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andProblemDescEqualTo(String value) {
            addCriterion("PROBLEM_DESC =", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescNotEqualTo(String value) {
            addCriterion("PROBLEM_DESC <>", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescGreaterThan(String value) {
            addCriterion("PROBLEM_DESC >", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROBLEM_DESC >=", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescLessThan(String value) {
            addCriterion("PROBLEM_DESC <", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescLessThanOrEqualTo(String value) {
            addCriterion("PROBLEM_DESC <=", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescLike(String value) {
            addCriterion("PROBLEM_DESC like", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescNotLike(String value) {
            addCriterion("PROBLEM_DESC not like", value, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescIn(List<String> values) {
            addCriterion("PROBLEM_DESC in", values, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescNotIn(List<String> values) {
            addCriterion("PROBLEM_DESC not in", values, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescBetween(String value1, String value2) {
            addCriterion("PROBLEM_DESC between", value1, value2, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andProblemDescNotBetween(String value1, String value2) {
            addCriterion("PROBLEM_DESC not between", value1, value2, "problemDesc");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeIsNull() {
            addCriterion("OCCURRENCE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeIsNotNull() {
            addCriterion("OCCURRENCE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeEqualTo(String value) {
            addCriterion("OCCURRENCE_TIME =", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeNotEqualTo(String value) {
            addCriterion("OCCURRENCE_TIME <>", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeGreaterThan(String value) {
            addCriterion("OCCURRENCE_TIME >", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeGreaterThanOrEqualTo(String value) {
            addCriterion("OCCURRENCE_TIME >=", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeLessThan(String value) {
            addCriterion("OCCURRENCE_TIME <", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeLessThanOrEqualTo(String value) {
            addCriterion("OCCURRENCE_TIME <=", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeLike(String value) {
            addCriterion("OCCURRENCE_TIME like", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeNotLike(String value) {
            addCriterion("OCCURRENCE_TIME not like", value, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeIn(List<String> values) {
            addCriterion("OCCURRENCE_TIME in", values, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeNotIn(List<String> values) {
            addCriterion("OCCURRENCE_TIME not in", values, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeBetween(String value1, String value2) {
            addCriterion("OCCURRENCE_TIME between", value1, value2, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andOccurrenceTimeNotBetween(String value1, String value2) {
            addCriterion("OCCURRENCE_TIME not between", value1, value2, "occurrenceTime");
            return (Criteria) this;
        }

        public Criteria andFielPathIsNull() {
            addCriterion("FIEL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFielPathIsNotNull() {
            addCriterion("FIEL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFielPathEqualTo(String value) {
            addCriterion("FIEL_PATH =", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathNotEqualTo(String value) {
            addCriterion("FIEL_PATH <>", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathGreaterThan(String value) {
            addCriterion("FIEL_PATH >", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathGreaterThanOrEqualTo(String value) {
            addCriterion("FIEL_PATH >=", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathLessThan(String value) {
            addCriterion("FIEL_PATH <", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathLessThanOrEqualTo(String value) {
            addCriterion("FIEL_PATH <=", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathLike(String value) {
            addCriterion("FIEL_PATH like", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathNotLike(String value) {
            addCriterion("FIEL_PATH not like", value, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathIn(List<String> values) {
            addCriterion("FIEL_PATH in", values, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathNotIn(List<String> values) {
            addCriterion("FIEL_PATH not in", values, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathBetween(String value1, String value2) {
            addCriterion("FIEL_PATH between", value1, value2, "fielPath");
            return (Criteria) this;
        }

        public Criteria andFielPathNotBetween(String value1, String value2) {
            addCriterion("FIEL_PATH not between", value1, value2, "fielPath");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNull() {
            addCriterion("CREATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateIdIsNotNull() {
            addCriterion("CREATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateIdEqualTo(String value) {
            addCriterion("CREATE_ID =", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotEqualTo(String value) {
            addCriterion("CREATE_ID <>", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThan(String value) {
            addCriterion("CREATE_ID >", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_ID >=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThan(String value) {
            addCriterion("CREATE_ID <", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_ID <=", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdLike(String value) {
            addCriterion("CREATE_ID like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotLike(String value) {
            addCriterion("CREATE_ID not like", value, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdIn(List<String> values) {
            addCriterion("CREATE_ID in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotIn(List<String> values) {
            addCriterion("CREATE_ID not in", values, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdBetween(String value1, String value2) {
            addCriterion("CREATE_ID between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_ID not between", value1, value2, "createId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNull() {
            addCriterion("REPLY_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNotNull() {
            addCriterion("REPLY_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentEqualTo(String value) {
            addCriterion("REPLY_CONTENT =", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotEqualTo(String value) {
            addCriterion("REPLY_CONTENT <>", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThan(String value) {
            addCriterion("REPLY_CONTENT >", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_CONTENT >=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThan(String value) {
            addCriterion("REPLY_CONTENT <", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThanOrEqualTo(String value) {
            addCriterion("REPLY_CONTENT <=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLike(String value) {
            addCriterion("REPLY_CONTENT like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotLike(String value) {
            addCriterion("REPLY_CONTENT not like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentIn(List<String> values) {
            addCriterion("REPLY_CONTENT in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotIn(List<String> values) {
            addCriterion("REPLY_CONTENT not in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentBetween(String value1, String value2) {
            addCriterion("REPLY_CONTENT between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotBetween(String value1, String value2) {
            addCriterion("REPLY_CONTENT not between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyIdIsNull() {
            addCriterion("REPLY_ID is null");
            return (Criteria) this;
        }

        public Criteria andReplyIdIsNotNull() {
            addCriterion("REPLY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReplyIdEqualTo(String value) {
            addCriterion("REPLY_ID =", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdNotEqualTo(String value) {
            addCriterion("REPLY_ID <>", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdGreaterThan(String value) {
            addCriterion("REPLY_ID >", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_ID >=", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdLessThan(String value) {
            addCriterion("REPLY_ID <", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdLessThanOrEqualTo(String value) {
            addCriterion("REPLY_ID <=", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdLike(String value) {
            addCriterion("REPLY_ID like", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdNotLike(String value) {
            addCriterion("REPLY_ID not like", value, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdIn(List<String> values) {
            addCriterion("REPLY_ID in", values, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdNotIn(List<String> values) {
            addCriterion("REPLY_ID not in", values, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdBetween(String value1, String value2) {
            addCriterion("REPLY_ID between", value1, value2, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyIdNotBetween(String value1, String value2) {
            addCriterion("REPLY_ID not between", value1, value2, "replyId");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("REPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("REPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(Date value) {
            addCriterionForJDBCDate("REPLY_TIME =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("REPLY_TIME <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("REPLY_TIME >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPLY_TIME >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(Date value) {
            addCriterionForJDBCDate("REPLY_TIME <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REPLY_TIME <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<Date> values) {
            addCriterionForJDBCDate("REPLY_TIME in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("REPLY_TIME not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPLY_TIME between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REPLY_TIME not between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andArrt1IsNull() {
            addCriterion("ARRT1 is null");
            return (Criteria) this;
        }

        public Criteria andArrt1IsNotNull() {
            addCriterion("ARRT1 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt1EqualTo(String value) {
            addCriterion("ARRT1 =", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotEqualTo(String value) {
            addCriterion("ARRT1 <>", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1GreaterThan(String value) {
            addCriterion("ARRT1 >", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT1 >=", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1LessThan(String value) {
            addCriterion("ARRT1 <", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1LessThanOrEqualTo(String value) {
            addCriterion("ARRT1 <=", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1Like(String value) {
            addCriterion("ARRT1 like", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotLike(String value) {
            addCriterion("ARRT1 not like", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1In(List<String> values) {
            addCriterion("ARRT1 in", values, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotIn(List<String> values) {
            addCriterion("ARRT1 not in", values, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1Between(String value1, String value2) {
            addCriterion("ARRT1 between", value1, value2, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotBetween(String value1, String value2) {
            addCriterion("ARRT1 not between", value1, value2, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt2IsNull() {
            addCriterion("ARRT2 is null");
            return (Criteria) this;
        }

        public Criteria andArrt2IsNotNull() {
            addCriterion("ARRT2 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt2EqualTo(String value) {
            addCriterion("ARRT2 =", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotEqualTo(String value) {
            addCriterion("ARRT2 <>", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2GreaterThan(String value) {
            addCriterion("ARRT2 >", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT2 >=", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2LessThan(String value) {
            addCriterion("ARRT2 <", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2LessThanOrEqualTo(String value) {
            addCriterion("ARRT2 <=", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2Like(String value) {
            addCriterion("ARRT2 like", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotLike(String value) {
            addCriterion("ARRT2 not like", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2In(List<String> values) {
            addCriterion("ARRT2 in", values, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotIn(List<String> values) {
            addCriterion("ARRT2 not in", values, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2Between(String value1, String value2) {
            addCriterion("ARRT2 between", value1, value2, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotBetween(String value1, String value2) {
            addCriterion("ARRT2 not between", value1, value2, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt3IsNull() {
            addCriterion("ARRT3 is null");
            return (Criteria) this;
        }

        public Criteria andArrt3IsNotNull() {
            addCriterion("ARRT3 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt3EqualTo(String value) {
            addCriterion("ARRT3 =", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotEqualTo(String value) {
            addCriterion("ARRT3 <>", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3GreaterThan(String value) {
            addCriterion("ARRT3 >", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT3 >=", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3LessThan(String value) {
            addCriterion("ARRT3 <", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3LessThanOrEqualTo(String value) {
            addCriterion("ARRT3 <=", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3Like(String value) {
            addCriterion("ARRT3 like", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotLike(String value) {
            addCriterion("ARRT3 not like", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3In(List<String> values) {
            addCriterion("ARRT3 in", values, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotIn(List<String> values) {
            addCriterion("ARRT3 not in", values, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3Between(String value1, String value2) {
            addCriterion("ARRT3 between", value1, value2, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotBetween(String value1, String value2) {
            addCriterion("ARRT3 not between", value1, value2, "arrt3");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
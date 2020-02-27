package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PAppCommonproExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PAppCommonproExample() {
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

        public Criteria andProTitleIsNull() {
            addCriterion("PRO_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andProTitleIsNotNull() {
            addCriterion("PRO_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andProTitleEqualTo(String value) {
            addCriterion("PRO_TITLE =", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleNotEqualTo(String value) {
            addCriterion("PRO_TITLE <>", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleGreaterThan(String value) {
            addCriterion("PRO_TITLE >", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_TITLE >=", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleLessThan(String value) {
            addCriterion("PRO_TITLE <", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleLessThanOrEqualTo(String value) {
            addCriterion("PRO_TITLE <=", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleLike(String value) {
            addCriterion("PRO_TITLE like", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleNotLike(String value) {
            addCriterion("PRO_TITLE not like", value, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleIn(List<String> values) {
            addCriterion("PRO_TITLE in", values, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleNotIn(List<String> values) {
            addCriterion("PRO_TITLE not in", values, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleBetween(String value1, String value2) {
            addCriterion("PRO_TITLE between", value1, value2, "proTitle");
            return (Criteria) this;
        }

        public Criteria andProTitleNotBetween(String value1, String value2) {
            addCriterion("PRO_TITLE not between", value1, value2, "proTitle");
            return (Criteria) this;
        }

        public Criteria andSolutionIsNull() {
            addCriterion("SOLUTION is null");
            return (Criteria) this;
        }

        public Criteria andSolutionIsNotNull() {
            addCriterion("SOLUTION is not null");
            return (Criteria) this;
        }

        public Criteria andSolutionEqualTo(String value) {
            addCriterion("SOLUTION =", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotEqualTo(String value) {
            addCriterion("SOLUTION <>", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionGreaterThan(String value) {
            addCriterion("SOLUTION >", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionGreaterThanOrEqualTo(String value) {
            addCriterion("SOLUTION >=", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLessThan(String value) {
            addCriterion("SOLUTION <", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLessThanOrEqualTo(String value) {
            addCriterion("SOLUTION <=", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionLike(String value) {
            addCriterion("SOLUTION like", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotLike(String value) {
            addCriterion("SOLUTION not like", value, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionIn(List<String> values) {
            addCriterion("SOLUTION in", values, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotIn(List<String> values) {
            addCriterion("SOLUTION not in", values, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionBetween(String value1, String value2) {
            addCriterion("SOLUTION between", value1, value2, "solution");
            return (Criteria) this;
        }

        public Criteria andSolutionNotBetween(String value1, String value2) {
            addCriterion("SOLUTION not between", value1, value2, "solution");
            return (Criteria) this;
        }

        public Criteria andColumuIdIsNull() {
            addCriterion("COLUMU_ID is null");
            return (Criteria) this;
        }

        public Criteria andColumuIdIsNotNull() {
            addCriterion("COLUMU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andColumuIdEqualTo(String value) {
            addCriterion("COLUMU_ID =", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdNotEqualTo(String value) {
            addCriterion("COLUMU_ID <>", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdGreaterThan(String value) {
            addCriterion("COLUMU_ID >", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMU_ID >=", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdLessThan(String value) {
            addCriterion("COLUMU_ID <", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdLessThanOrEqualTo(String value) {
            addCriterion("COLUMU_ID <=", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdLike(String value) {
            addCriterion("COLUMU_ID like", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdNotLike(String value) {
            addCriterion("COLUMU_ID not like", value, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdIn(List<String> values) {
            addCriterion("COLUMU_ID in", values, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdNotIn(List<String> values) {
            addCriterion("COLUMU_ID not in", values, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdBetween(String value1, String value2) {
            addCriterion("COLUMU_ID between", value1, value2, "columuId");
            return (Criteria) this;
        }

        public Criteria andColumuIdNotBetween(String value1, String value2) {
            addCriterion("COLUMU_ID not between", value1, value2, "columuId");
            return (Criteria) this;
        }

        public Criteria andFavorNumIsNull() {
            addCriterion("FAVOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFavorNumIsNotNull() {
            addCriterion("FAVOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFavorNumEqualTo(String value) {
            addCriterion("FAVOR_NUM =", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotEqualTo(String value) {
            addCriterion("FAVOR_NUM <>", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumGreaterThan(String value) {
            addCriterion("FAVOR_NUM >", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumGreaterThanOrEqualTo(String value) {
            addCriterion("FAVOR_NUM >=", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumLessThan(String value) {
            addCriterion("FAVOR_NUM <", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumLessThanOrEqualTo(String value) {
            addCriterion("FAVOR_NUM <=", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumLike(String value) {
            addCriterion("FAVOR_NUM like", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotLike(String value) {
            addCriterion("FAVOR_NUM not like", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumIn(List<String> values) {
            addCriterion("FAVOR_NUM in", values, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotIn(List<String> values) {
            addCriterion("FAVOR_NUM not in", values, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumBetween(String value1, String value2) {
            addCriterion("FAVOR_NUM between", value1, value2, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotBetween(String value1, String value2) {
            addCriterion("FAVOR_NUM not between", value1, value2, "favorNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumIsNull() {
            addCriterion("OPPOSITION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andOppositionNumIsNotNull() {
            addCriterion("OPPOSITION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andOppositionNumEqualTo(String value) {
            addCriterion("OPPOSITION_NUM =", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumNotEqualTo(String value) {
            addCriterion("OPPOSITION_NUM <>", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumGreaterThan(String value) {
            addCriterion("OPPOSITION_NUM >", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumGreaterThanOrEqualTo(String value) {
            addCriterion("OPPOSITION_NUM >=", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumLessThan(String value) {
            addCriterion("OPPOSITION_NUM <", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumLessThanOrEqualTo(String value) {
            addCriterion("OPPOSITION_NUM <=", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumLike(String value) {
            addCriterion("OPPOSITION_NUM like", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumNotLike(String value) {
            addCriterion("OPPOSITION_NUM not like", value, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumIn(List<String> values) {
            addCriterion("OPPOSITION_NUM in", values, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumNotIn(List<String> values) {
            addCriterion("OPPOSITION_NUM not in", values, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumBetween(String value1, String value2) {
            addCriterion("OPPOSITION_NUM between", value1, value2, "oppositionNum");
            return (Criteria) this;
        }

        public Criteria andOppositionNumNotBetween(String value1, String value2) {
            addCriterion("OPPOSITION_NUM not between", value1, value2, "oppositionNum");
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

        public Criteria andEditIdIsNull() {
            addCriterion("EDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andEditIdIsNotNull() {
            addCriterion("EDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEditIdEqualTo(String value) {
            addCriterion("EDIT_ID =", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotEqualTo(String value) {
            addCriterion("EDIT_ID <>", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdGreaterThan(String value) {
            addCriterion("EDIT_ID >", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDIT_ID >=", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdLessThan(String value) {
            addCriterion("EDIT_ID <", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdLessThanOrEqualTo(String value) {
            addCriterion("EDIT_ID <=", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdLike(String value) {
            addCriterion("EDIT_ID like", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotLike(String value) {
            addCriterion("EDIT_ID not like", value, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdIn(List<String> values) {
            addCriterion("EDIT_ID in", values, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotIn(List<String> values) {
            addCriterion("EDIT_ID not in", values, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdBetween(String value1, String value2) {
            addCriterion("EDIT_ID between", value1, value2, "editId");
            return (Criteria) this;
        }

        public Criteria andEditIdNotBetween(String value1, String value2) {
            addCriterion("EDIT_ID not between", value1, value2, "editId");
            return (Criteria) this;
        }

        public Criteria andEditTimeIsNull() {
            addCriterion("EDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEditTimeIsNotNull() {
            addCriterion("EDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEditTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_TIME =", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_TIME <>", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EDIT_TIME >", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_TIME >=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThan(Date value) {
            addCriterionForJDBCDate("EDIT_TIME <", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_TIME <=", value, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeIn(List<Date> values) {
            addCriterionForJDBCDate("EDIT_TIME in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EDIT_TIME not in", values, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDIT_TIME between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andEditTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDIT_TIME not between", value1, value2, "editTime");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNull() {
            addCriterion("ATTR1 is null");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNotNull() {
            addCriterion("ATTR1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr1EqualTo(String value) {
            addCriterion("ATTR1 =", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotEqualTo(String value) {
            addCriterion("ATTR1 <>", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThan(String value) {
            addCriterion("ATTR1 >", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR1 >=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThan(String value) {
            addCriterion("ATTR1 <", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThanOrEqualTo(String value) {
            addCriterion("ATTR1 <=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Like(String value) {
            addCriterion("ATTR1 like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotLike(String value) {
            addCriterion("ATTR1 not like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1In(List<String> values) {
            addCriterion("ATTR1 in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotIn(List<String> values) {
            addCriterion("ATTR1 not in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Between(String value1, String value2) {
            addCriterion("ATTR1 between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotBetween(String value1, String value2) {
            addCriterion("ATTR1 not between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNull() {
            addCriterion("ATTR2 is null");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNotNull() {
            addCriterion("ATTR2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr2EqualTo(String value) {
            addCriterion("ATTR2 =", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotEqualTo(String value) {
            addCriterion("ATTR2 <>", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThan(String value) {
            addCriterion("ATTR2 >", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR2 >=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThan(String value) {
            addCriterion("ATTR2 <", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThanOrEqualTo(String value) {
            addCriterion("ATTR2 <=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Like(String value) {
            addCriterion("ATTR2 like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotLike(String value) {
            addCriterion("ATTR2 not like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2In(List<String> values) {
            addCriterion("ATTR2 in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotIn(List<String> values) {
            addCriterion("ATTR2 not in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Between(String value1, String value2) {
            addCriterion("ATTR2 between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotBetween(String value1, String value2) {
            addCriterion("ATTR2 not between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNull() {
            addCriterion("ATTR3 is null");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNotNull() {
            addCriterion("ATTR3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr3EqualTo(String value) {
            addCriterion("ATTR3 =", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotEqualTo(String value) {
            addCriterion("ATTR3 <>", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThan(String value) {
            addCriterion("ATTR3 >", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR3 >=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThan(String value) {
            addCriterion("ATTR3 <", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThanOrEqualTo(String value) {
            addCriterion("ATTR3 <=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Like(String value) {
            addCriterion("ATTR3 like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotLike(String value) {
            addCriterion("ATTR3 not like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3In(List<String> values) {
            addCriterion("ATTR3 in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotIn(List<String> values) {
            addCriterion("ATTR3 not in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Between(String value1, String value2) {
            addCriterion("ATTR3 between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotBetween(String value1, String value2) {
            addCriterion("ATTR3 not between", value1, value2, "attr3");
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
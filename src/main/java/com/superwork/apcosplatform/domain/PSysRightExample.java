package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PSysRightExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSysRightExample() {
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

        public Criteria andRightIdIsNull() {
            addCriterion("RIGHT_ID is null");
            return (Criteria) this;
        }

        public Criteria andRightIdIsNotNull() {
            addCriterion("RIGHT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRightIdEqualTo(String value) {
            addCriterion("RIGHT_ID =", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotEqualTo(String value) {
            addCriterion("RIGHT_ID <>", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThan(String value) {
            addCriterion("RIGHT_ID >", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdGreaterThanOrEqualTo(String value) {
            addCriterion("RIGHT_ID >=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThan(String value) {
            addCriterion("RIGHT_ID <", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLessThanOrEqualTo(String value) {
            addCriterion("RIGHT_ID <=", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdLike(String value) {
            addCriterion("RIGHT_ID like", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotLike(String value) {
            addCriterion("RIGHT_ID not like", value, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdIn(List<String> values) {
            addCriterion("RIGHT_ID in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotIn(List<String> values) {
            addCriterion("RIGHT_ID not in", values, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdBetween(String value1, String value2) {
            addCriterion("RIGHT_ID between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightIdNotBetween(String value1, String value2) {
            addCriterion("RIGHT_ID not between", value1, value2, "rightId");
            return (Criteria) this;
        }

        public Criteria andRightNameIsNull() {
            addCriterion("RIGHT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRightNameIsNotNull() {
            addCriterion("RIGHT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRightNameEqualTo(String value) {
            addCriterion("RIGHT_NAME =", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotEqualTo(String value) {
            addCriterion("RIGHT_NAME <>", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameGreaterThan(String value) {
            addCriterion("RIGHT_NAME >", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameGreaterThanOrEqualTo(String value) {
            addCriterion("RIGHT_NAME >=", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLessThan(String value) {
            addCriterion("RIGHT_NAME <", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLessThanOrEqualTo(String value) {
            addCriterion("RIGHT_NAME <=", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameLike(String value) {
            addCriterion("RIGHT_NAME like", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotLike(String value) {
            addCriterion("RIGHT_NAME not like", value, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameIn(List<String> values) {
            addCriterion("RIGHT_NAME in", values, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotIn(List<String> values) {
            addCriterion("RIGHT_NAME not in", values, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameBetween(String value1, String value2) {
            addCriterion("RIGHT_NAME between", value1, value2, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightNameNotBetween(String value1, String value2) {
            addCriterion("RIGHT_NAME not between", value1, value2, "rightName");
            return (Criteria) this;
        }

        public Criteria andRightTypeIsNull() {
            addCriterion("RIGHT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRightTypeIsNotNull() {
            addCriterion("RIGHT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRightTypeEqualTo(BigDecimal value) {
            addCriterion("RIGHT_TYPE =", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotEqualTo(BigDecimal value) {
            addCriterion("RIGHT_TYPE <>", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeGreaterThan(BigDecimal value) {
            addCriterion("RIGHT_TYPE >", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("RIGHT_TYPE >=", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeLessThan(BigDecimal value) {
            addCriterion("RIGHT_TYPE <", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("RIGHT_TYPE <=", value, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeIn(List<BigDecimal> values) {
            addCriterion("RIGHT_TYPE in", values, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotIn(List<BigDecimal> values) {
            addCriterion("RIGHT_TYPE not in", values, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RIGHT_TYPE between", value1, value2, "rightType");
            return (Criteria) this;
        }

        public Criteria andRightTypeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("RIGHT_TYPE not between", value1, value2, "rightType");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andIsMeuaIsNull() {
            addCriterion("IS_MEUA is null");
            return (Criteria) this;
        }

        public Criteria andIsMeuaIsNotNull() {
            addCriterion("IS_MEUA is not null");
            return (Criteria) this;
        }

        public Criteria andIsMeuaEqualTo(BigDecimal value) {
            addCriterion("IS_MEUA =", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaNotEqualTo(BigDecimal value) {
            addCriterion("IS_MEUA <>", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaGreaterThan(BigDecimal value) {
            addCriterion("IS_MEUA >", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("IS_MEUA >=", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaLessThan(BigDecimal value) {
            addCriterion("IS_MEUA <", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("IS_MEUA <=", value, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaIn(List<BigDecimal> values) {
            addCriterion("IS_MEUA in", values, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaNotIn(List<BigDecimal> values) {
            addCriterion("IS_MEUA not in", values, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IS_MEUA between", value1, value2, "isMeua");
            return (Criteria) this;
        }

        public Criteria andIsMeuaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("IS_MEUA not between", value1, value2, "isMeua");
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

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andEditedIdIsNull() {
            addCriterion("EDITED_ID is null");
            return (Criteria) this;
        }

        public Criteria andEditedIdIsNotNull() {
            addCriterion("EDITED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEditedIdEqualTo(String value) {
            addCriterion("EDITED_ID =", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdNotEqualTo(String value) {
            addCriterion("EDITED_ID <>", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdGreaterThan(String value) {
            addCriterion("EDITED_ID >", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDITED_ID >=", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdLessThan(String value) {
            addCriterion("EDITED_ID <", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdLessThanOrEqualTo(String value) {
            addCriterion("EDITED_ID <=", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdLike(String value) {
            addCriterion("EDITED_ID like", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdNotLike(String value) {
            addCriterion("EDITED_ID not like", value, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdIn(List<String> values) {
            addCriterion("EDITED_ID in", values, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdNotIn(List<String> values) {
            addCriterion("EDITED_ID not in", values, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdBetween(String value1, String value2) {
            addCriterion("EDITED_ID between", value1, value2, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedIdNotBetween(String value1, String value2) {
            addCriterion("EDITED_ID not between", value1, value2, "editedId");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIsNull() {
            addCriterion("EDITED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIsNotNull() {
            addCriterion("EDITED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEditedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME =", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <>", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EDITED_TIME >", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME >=", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeLessThan(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <=", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("EDITED_TIME in", values, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EDITED_TIME not in", values, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDITED_TIME between", value1, value2, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDITED_TIME not between", value1, value2, "editedTime");
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
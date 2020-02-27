package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PSmhDeviceExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSmhDeviceExample() {
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

        public Criteria andServiceIdIsNull() {
            addCriterion("SERVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("SERVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("SERVICE_ID =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("SERVICE_ID <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("SERVICE_ID >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("SERVICE_ID <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("SERVICE_ID like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("SERVICE_ID not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("SERVICE_ID in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("SERVICE_ID not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("SERVICE_ID between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("SERVICE_ID not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIsNull() {
            addCriterion("SERVICE_KEY is null");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIsNotNull() {
            addCriterion("SERVICE_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andServiceKeyEqualTo(String value) {
            addCriterion("SERVICE_KEY =", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotEqualTo(String value) {
            addCriterion("SERVICE_KEY <>", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyGreaterThan(String value) {
            addCriterion("SERVICE_KEY >", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_KEY >=", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLessThan(String value) {
            addCriterion("SERVICE_KEY <", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_KEY <=", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyLike(String value) {
            addCriterion("SERVICE_KEY like", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotLike(String value) {
            addCriterion("SERVICE_KEY not like", value, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyIn(List<String> values) {
            addCriterion("SERVICE_KEY in", values, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotIn(List<String> values) {
            addCriterion("SERVICE_KEY not in", values, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyBetween(String value1, String value2) {
            addCriterion("SERVICE_KEY between", value1, value2, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceKeyNotBetween(String value1, String value2) {
            addCriterion("SERVICE_KEY not between", value1, value2, "serviceKey");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlIsNull() {
            addCriterion("SERVICE_BACK_URL is null");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlIsNotNull() {
            addCriterion("SERVICE_BACK_URL is not null");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlEqualTo(String value) {
            addCriterion("SERVICE_BACK_URL =", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlNotEqualTo(String value) {
            addCriterion("SERVICE_BACK_URL <>", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlGreaterThan(String value) {
            addCriterion("SERVICE_BACK_URL >", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_BACK_URL >=", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlLessThan(String value) {
            addCriterion("SERVICE_BACK_URL <", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_BACK_URL <=", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlLike(String value) {
            addCriterion("SERVICE_BACK_URL like", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlNotLike(String value) {
            addCriterion("SERVICE_BACK_URL not like", value, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlIn(List<String> values) {
            addCriterion("SERVICE_BACK_URL in", values, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlNotIn(List<String> values) {
            addCriterion("SERVICE_BACK_URL not in", values, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlBetween(String value1, String value2) {
            addCriterion("SERVICE_BACK_URL between", value1, value2, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andServiceBackUrlNotBetween(String value1, String value2) {
            addCriterion("SERVICE_BACK_URL not between", value1, value2, "serviceBackUrl");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNull() {
            addCriterion("ATTR_1 is null");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNotNull() {
            addCriterion("ATTR_1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr1EqualTo(String value) {
            addCriterion("ATTR_1 =", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotEqualTo(String value) {
            addCriterion("ATTR_1 <>", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThan(String value) {
            addCriterion("ATTR_1 >", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_1 >=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThan(String value) {
            addCriterion("ATTR_1 <", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThanOrEqualTo(String value) {
            addCriterion("ATTR_1 <=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Like(String value) {
            addCriterion("ATTR_1 like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotLike(String value) {
            addCriterion("ATTR_1 not like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1In(List<String> values) {
            addCriterion("ATTR_1 in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotIn(List<String> values) {
            addCriterion("ATTR_1 not in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Between(String value1, String value2) {
            addCriterion("ATTR_1 between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotBetween(String value1, String value2) {
            addCriterion("ATTR_1 not between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNull() {
            addCriterion("ATTR_2 is null");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNotNull() {
            addCriterion("ATTR_2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr2EqualTo(String value) {
            addCriterion("ATTR_2 =", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotEqualTo(String value) {
            addCriterion("ATTR_2 <>", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThan(String value) {
            addCriterion("ATTR_2 >", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_2 >=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThan(String value) {
            addCriterion("ATTR_2 <", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThanOrEqualTo(String value) {
            addCriterion("ATTR_2 <=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Like(String value) {
            addCriterion("ATTR_2 like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotLike(String value) {
            addCriterion("ATTR_2 not like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2In(List<String> values) {
            addCriterion("ATTR_2 in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotIn(List<String> values) {
            addCriterion("ATTR_2 not in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Between(String value1, String value2) {
            addCriterion("ATTR_2 between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotBetween(String value1, String value2) {
            addCriterion("ATTR_2 not between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("CREATOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("CREATOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("CREATOR_ID =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("CREATOR_ID <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("CREATOR_ID >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("CREATOR_ID <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("CREATOR_ID like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("CREATOR_ID not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("CREATOR_ID in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("CREATOR_ID not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("CREATOR_ID between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ID not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andEditorIdIsNull() {
            addCriterion("EDITOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andEditorIdIsNotNull() {
            addCriterion("EDITOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEditorIdEqualTo(String value) {
            addCriterion("EDITOR_ID =", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotEqualTo(String value) {
            addCriterion("EDITOR_ID <>", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdGreaterThan(String value) {
            addCriterion("EDITOR_ID >", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDITOR_ID >=", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLessThan(String value) {
            addCriterion("EDITOR_ID <", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLessThanOrEqualTo(String value) {
            addCriterion("EDITOR_ID <=", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLike(String value) {
            addCriterion("EDITOR_ID like", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotLike(String value) {
            addCriterion("EDITOR_ID not like", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdIn(List<String> values) {
            addCriterion("EDITOR_ID in", values, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotIn(List<String> values) {
            addCriterion("EDITOR_ID not in", values, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdBetween(String value1, String value2) {
            addCriterion("EDITOR_ID between", value1, value2, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotBetween(String value1, String value2) {
            addCriterion("EDITOR_ID not between", value1, value2, "editorId");
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

        public Criteria andBelongOrgIdIsNull() {
            addCriterion("BELONG_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIsNotNull() {
            addCriterion("BELONG_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdEqualTo(String value) {
            addCriterion("BELONG_ORG_ID =", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <>", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThan(String value) {
            addCriterion("BELONG_ORG_ID >", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID >=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThan(String value) {
            addCriterion("BELONG_ORG_ID <", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLike(String value) {
            addCriterion("BELONG_ORG_ID like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotLike(String value) {
            addCriterion("BELONG_ORG_ID not like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIn(List<String> values) {
            addCriterion("BELONG_ORG_ID in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotIn(List<String> values) {
            addCriterion("BELONG_ORG_ID not in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID not between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBccaUrlIsNull() {
            addCriterion("BCCA_URL is null");
            return (Criteria) this;
        }

        public Criteria andBccaUrlIsNotNull() {
            addCriterion("BCCA_URL is not null");
            return (Criteria) this;
        }

        public Criteria andBccaUrlEqualTo(String value) {
            addCriterion("BCCA_URL =", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlNotEqualTo(String value) {
            addCriterion("BCCA_URL <>", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlGreaterThan(String value) {
            addCriterion("BCCA_URL >", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlGreaterThanOrEqualTo(String value) {
            addCriterion("BCCA_URL >=", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlLessThan(String value) {
            addCriterion("BCCA_URL <", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlLessThanOrEqualTo(String value) {
            addCriterion("BCCA_URL <=", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlLike(String value) {
            addCriterion("BCCA_URL like", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlNotLike(String value) {
            addCriterion("BCCA_URL not like", value, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlIn(List<String> values) {
            addCriterion("BCCA_URL in", values, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlNotIn(List<String> values) {
            addCriterion("BCCA_URL not in", values, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlBetween(String value1, String value2) {
            addCriterion("BCCA_URL between", value1, value2, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andBccaUrlNotBetween(String value1, String value2) {
            addCriterion("BCCA_URL not between", value1, value2, "bccaUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlIsNull() {
            addCriterion("DGJ_URL is null");
            return (Criteria) this;
        }

        public Criteria andDgjUrlIsNotNull() {
            addCriterion("DGJ_URL is not null");
            return (Criteria) this;
        }

        public Criteria andDgjUrlEqualTo(String value) {
            addCriterion("DGJ_URL =", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlNotEqualTo(String value) {
            addCriterion("DGJ_URL <>", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlGreaterThan(String value) {
            addCriterion("DGJ_URL >", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlGreaterThanOrEqualTo(String value) {
            addCriterion("DGJ_URL >=", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlLessThan(String value) {
            addCriterion("DGJ_URL <", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlLessThanOrEqualTo(String value) {
            addCriterion("DGJ_URL <=", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlLike(String value) {
            addCriterion("DGJ_URL like", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlNotLike(String value) {
            addCriterion("DGJ_URL not like", value, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlIn(List<String> values) {
            addCriterion("DGJ_URL in", values, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlNotIn(List<String> values) {
            addCriterion("DGJ_URL not in", values, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlBetween(String value1, String value2) {
            addCriterion("DGJ_URL between", value1, value2, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andDgjUrlNotBetween(String value1, String value2) {
            addCriterion("DGJ_URL not between", value1, value2, "dgjUrl");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("APPID is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("APPID is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("APPID =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("APPID <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("APPID >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("APPID >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("APPID <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("APPID <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("APPID like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("APPID not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("APPID in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("APPID not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("APPID between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("APPID not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNull() {
            addCriterion("APPKEY is null");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNotNull() {
            addCriterion("APPKEY is not null");
            return (Criteria) this;
        }

        public Criteria andAppkeyEqualTo(String value) {
            addCriterion("APPKEY =", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotEqualTo(String value) {
            addCriterion("APPKEY <>", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThan(String value) {
            addCriterion("APPKEY >", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThanOrEqualTo(String value) {
            addCriterion("APPKEY >=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThan(String value) {
            addCriterion("APPKEY <", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThanOrEqualTo(String value) {
            addCriterion("APPKEY <=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLike(String value) {
            addCriterion("APPKEY like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotLike(String value) {
            addCriterion("APPKEY not like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyIn(List<String> values) {
            addCriterion("APPKEY in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotIn(List<String> values) {
            addCriterion("APPKEY not in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyBetween(String value1, String value2) {
            addCriterion("APPKEY between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotBetween(String value1, String value2) {
            addCriterion("APPKEY not between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andApcosUrlIsNull() {
            addCriterion("APCOS_URL is null");
            return (Criteria) this;
        }

        public Criteria andApcosUrlIsNotNull() {
            addCriterion("APCOS_URL is not null");
            return (Criteria) this;
        }

        public Criteria andApcosUrlEqualTo(String value) {
            addCriterion("APCOS_URL =", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlNotEqualTo(String value) {
            addCriterion("APCOS_URL <>", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlGreaterThan(String value) {
            addCriterion("APCOS_URL >", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlGreaterThanOrEqualTo(String value) {
            addCriterion("APCOS_URL >=", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlLessThan(String value) {
            addCriterion("APCOS_URL <", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlLessThanOrEqualTo(String value) {
            addCriterion("APCOS_URL <=", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlLike(String value) {
            addCriterion("APCOS_URL like", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlNotLike(String value) {
            addCriterion("APCOS_URL not like", value, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlIn(List<String> values) {
            addCriterion("APCOS_URL in", values, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlNotIn(List<String> values) {
            addCriterion("APCOS_URL not in", values, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlBetween(String value1, String value2) {
            addCriterion("APCOS_URL between", value1, value2, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosUrlNotBetween(String value1, String value2) {
            addCriterion("APCOS_URL not between", value1, value2, "apcosUrl");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdIsNull() {
            addCriterion("APCOS_SERVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdIsNotNull() {
            addCriterion("APCOS_SERVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdEqualTo(String value) {
            addCriterion("APCOS_SERVICE_ID =", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdNotEqualTo(String value) {
            addCriterion("APCOS_SERVICE_ID <>", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdGreaterThan(String value) {
            addCriterion("APCOS_SERVICE_ID >", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("APCOS_SERVICE_ID >=", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdLessThan(String value) {
            addCriterion("APCOS_SERVICE_ID <", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdLessThanOrEqualTo(String value) {
            addCriterion("APCOS_SERVICE_ID <=", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdLike(String value) {
            addCriterion("APCOS_SERVICE_ID like", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdNotLike(String value) {
            addCriterion("APCOS_SERVICE_ID not like", value, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdIn(List<String> values) {
            addCriterion("APCOS_SERVICE_ID in", values, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdNotIn(List<String> values) {
            addCriterion("APCOS_SERVICE_ID not in", values, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdBetween(String value1, String value2) {
            addCriterion("APCOS_SERVICE_ID between", value1, value2, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceIdNotBetween(String value1, String value2) {
            addCriterion("APCOS_SERVICE_ID not between", value1, value2, "apcosServiceId");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyIsNull() {
            addCriterion("APCOS_SERVICE_KEY is null");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyIsNotNull() {
            addCriterion("APCOS_SERVICE_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyEqualTo(String value) {
            addCriterion("APCOS_SERVICE_KEY =", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyNotEqualTo(String value) {
            addCriterion("APCOS_SERVICE_KEY <>", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyGreaterThan(String value) {
            addCriterion("APCOS_SERVICE_KEY >", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyGreaterThanOrEqualTo(String value) {
            addCriterion("APCOS_SERVICE_KEY >=", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyLessThan(String value) {
            addCriterion("APCOS_SERVICE_KEY <", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyLessThanOrEqualTo(String value) {
            addCriterion("APCOS_SERVICE_KEY <=", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyLike(String value) {
            addCriterion("APCOS_SERVICE_KEY like", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyNotLike(String value) {
            addCriterion("APCOS_SERVICE_KEY not like", value, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyIn(List<String> values) {
            addCriterion("APCOS_SERVICE_KEY in", values, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyNotIn(List<String> values) {
            addCriterion("APCOS_SERVICE_KEY not in", values, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyBetween(String value1, String value2) {
            addCriterion("APCOS_SERVICE_KEY between", value1, value2, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andApcosServiceKeyNotBetween(String value1, String value2) {
            addCriterion("APCOS_SERVICE_KEY not between", value1, value2, "apcosServiceKey");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNull() {
            addCriterion("ATTR_3 is null");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNotNull() {
            addCriterion("ATTR_3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr3EqualTo(String value) {
            addCriterion("ATTR_3 =", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotEqualTo(String value) {
            addCriterion("ATTR_3 <>", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThan(String value) {
            addCriterion("ATTR_3 >", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_3 >=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThan(String value) {
            addCriterion("ATTR_3 <", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThanOrEqualTo(String value) {
            addCriterion("ATTR_3 <=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Like(String value) {
            addCriterion("ATTR_3 like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotLike(String value) {
            addCriterion("ATTR_3 not like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3In(List<String> values) {
            addCriterion("ATTR_3 in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotIn(List<String> values) {
            addCriterion("ATTR_3 not in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Between(String value1, String value2) {
            addCriterion("ATTR_3 between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotBetween(String value1, String value2) {
            addCriterion("ATTR_3 not between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr4IsNull() {
            addCriterion("ATTR_4 is null");
            return (Criteria) this;
        }

        public Criteria andAttr4IsNotNull() {
            addCriterion("ATTR_4 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr4EqualTo(String value) {
            addCriterion("ATTR_4 =", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotEqualTo(String value) {
            addCriterion("ATTR_4 <>", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThan(String value) {
            addCriterion("ATTR_4 >", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_4 >=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThan(String value) {
            addCriterion("ATTR_4 <", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThanOrEqualTo(String value) {
            addCriterion("ATTR_4 <=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Like(String value) {
            addCriterion("ATTR_4 like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotLike(String value) {
            addCriterion("ATTR_4 not like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4In(List<String> values) {
            addCriterion("ATTR_4 in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotIn(List<String> values) {
            addCriterion("ATTR_4 not in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Between(String value1, String value2) {
            addCriterion("ATTR_4 between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotBetween(String value1, String value2) {
            addCriterion("ATTR_4 not between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNull() {
            addCriterion("ATTR_5 is null");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNotNull() {
            addCriterion("ATTR_5 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr5EqualTo(String value) {
            addCriterion("ATTR_5 =", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotEqualTo(String value) {
            addCriterion("ATTR_5 <>", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThan(String value) {
            addCriterion("ATTR_5 >", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_5 >=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThan(String value) {
            addCriterion("ATTR_5 <", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThanOrEqualTo(String value) {
            addCriterion("ATTR_5 <=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Like(String value) {
            addCriterion("ATTR_5 like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotLike(String value) {
            addCriterion("ATTR_5 not like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5In(List<String> values) {
            addCriterion("ATTR_5 in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotIn(List<String> values) {
            addCriterion("ATTR_5 not in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Between(String value1, String value2) {
            addCriterion("ATTR_5 between", value1, value2, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotBetween(String value1, String value2) {
            addCriterion("ATTR_5 not between", value1, value2, "attr5");
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
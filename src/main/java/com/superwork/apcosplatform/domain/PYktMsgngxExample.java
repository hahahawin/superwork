package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PYktMsgngxExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PYktMsgngxExample() {
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

        public Criteria andMsgngxIdIsNull() {
            addCriterion("MSGNGX_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdIsNotNull() {
            addCriterion("MSGNGX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdEqualTo(String value) {
            addCriterion("MSGNGX_ID =", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdNotEqualTo(String value) {
            addCriterion("MSGNGX_ID <>", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdGreaterThan(String value) {
            addCriterion("MSGNGX_ID >", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdGreaterThanOrEqualTo(String value) {
            addCriterion("MSGNGX_ID >=", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdLessThan(String value) {
            addCriterion("MSGNGX_ID <", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdLessThanOrEqualTo(String value) {
            addCriterion("MSGNGX_ID <=", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdLike(String value) {
            addCriterion("MSGNGX_ID like", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdNotLike(String value) {
            addCriterion("MSGNGX_ID not like", value, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdIn(List<String> values) {
            addCriterion("MSGNGX_ID in", values, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdNotIn(List<String> values) {
            addCriterion("MSGNGX_ID not in", values, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdBetween(String value1, String value2) {
            addCriterion("MSGNGX_ID between", value1, value2, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsgngxIdNotBetween(String value1, String value2) {
            addCriterion("MSGNGX_ID not between", value1, value2, "msgngxId");
            return (Criteria) this;
        }

        public Criteria andMsidIsNull() {
            addCriterion("MSID is null");
            return (Criteria) this;
        }

        public Criteria andMsidIsNotNull() {
            addCriterion("MSID is not null");
            return (Criteria) this;
        }

        public Criteria andMsidEqualTo(String value) {
            addCriterion("MSID =", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotEqualTo(String value) {
            addCriterion("MSID <>", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidGreaterThan(String value) {
            addCriterion("MSID >", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidGreaterThanOrEqualTo(String value) {
            addCriterion("MSID >=", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidLessThan(String value) {
            addCriterion("MSID <", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidLessThanOrEqualTo(String value) {
            addCriterion("MSID <=", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidLike(String value) {
            addCriterion("MSID like", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotLike(String value) {
            addCriterion("MSID not like", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidIn(List<String> values) {
            addCriterion("MSID in", values, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotIn(List<String> values) {
            addCriterion("MSID not in", values, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidBetween(String value1, String value2) {
            addCriterion("MSID between", value1, value2, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotBetween(String value1, String value2) {
            addCriterion("MSID not between", value1, value2, "msid");
            return (Criteria) this;
        }

        public Criteria andWhidIsNull() {
            addCriterion("WHID is null");
            return (Criteria) this;
        }

        public Criteria andWhidIsNotNull() {
            addCriterion("WHID is not null");
            return (Criteria) this;
        }

        public Criteria andWhidEqualTo(String value) {
            addCriterion("WHID =", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidNotEqualTo(String value) {
            addCriterion("WHID <>", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidGreaterThan(String value) {
            addCriterion("WHID >", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidGreaterThanOrEqualTo(String value) {
            addCriterion("WHID >=", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidLessThan(String value) {
            addCriterion("WHID <", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidLessThanOrEqualTo(String value) {
            addCriterion("WHID <=", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidLike(String value) {
            addCriterion("WHID like", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidNotLike(String value) {
            addCriterion("WHID not like", value, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidIn(List<String> values) {
            addCriterion("WHID in", values, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidNotIn(List<String> values) {
            addCriterion("WHID not in", values, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidBetween(String value1, String value2) {
            addCriterion("WHID between", value1, value2, "whid");
            return (Criteria) this;
        }

        public Criteria andWhidNotBetween(String value1, String value2) {
            addCriterion("WHID not between", value1, value2, "whid");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("PARAMS =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("PARAMS <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("PARAMS >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMS >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("PARAMS <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("PARAMS <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("PARAMS like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("PARAMS not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("PARAMS in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("PARAMS not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("PARAMS between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("PARAMS not between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemo1IsNull() {
            addCriterion("MEMO1 is null");
            return (Criteria) this;
        }

        public Criteria andMemo1IsNotNull() {
            addCriterion("MEMO1 is not null");
            return (Criteria) this;
        }

        public Criteria andMemo1EqualTo(String value) {
            addCriterion("MEMO1 =", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1NotEqualTo(String value) {
            addCriterion("MEMO1 <>", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1GreaterThan(String value) {
            addCriterion("MEMO1 >", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1GreaterThanOrEqualTo(String value) {
            addCriterion("MEMO1 >=", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1LessThan(String value) {
            addCriterion("MEMO1 <", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1LessThanOrEqualTo(String value) {
            addCriterion("MEMO1 <=", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1Like(String value) {
            addCriterion("MEMO1 like", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1NotLike(String value) {
            addCriterion("MEMO1 not like", value, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1In(List<String> values) {
            addCriterion("MEMO1 in", values, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1NotIn(List<String> values) {
            addCriterion("MEMO1 not in", values, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1Between(String value1, String value2) {
            addCriterion("MEMO1 between", value1, value2, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo1NotBetween(String value1, String value2) {
            addCriterion("MEMO1 not between", value1, value2, "memo1");
            return (Criteria) this;
        }

        public Criteria andMemo2IsNull() {
            addCriterion("MEMO2 is null");
            return (Criteria) this;
        }

        public Criteria andMemo2IsNotNull() {
            addCriterion("MEMO2 is not null");
            return (Criteria) this;
        }

        public Criteria andMemo2EqualTo(String value) {
            addCriterion("MEMO2 =", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2NotEqualTo(String value) {
            addCriterion("MEMO2 <>", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2GreaterThan(String value) {
            addCriterion("MEMO2 >", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2GreaterThanOrEqualTo(String value) {
            addCriterion("MEMO2 >=", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2LessThan(String value) {
            addCriterion("MEMO2 <", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2LessThanOrEqualTo(String value) {
            addCriterion("MEMO2 <=", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2Like(String value) {
            addCriterion("MEMO2 like", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2NotLike(String value) {
            addCriterion("MEMO2 not like", value, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2In(List<String> values) {
            addCriterion("MEMO2 in", values, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2NotIn(List<String> values) {
            addCriterion("MEMO2 not in", values, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2Between(String value1, String value2) {
            addCriterion("MEMO2 between", value1, value2, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo2NotBetween(String value1, String value2) {
            addCriterion("MEMO2 not between", value1, value2, "memo2");
            return (Criteria) this;
        }

        public Criteria andMemo3IsNull() {
            addCriterion("MEMO3 is null");
            return (Criteria) this;
        }

        public Criteria andMemo3IsNotNull() {
            addCriterion("MEMO3 is not null");
            return (Criteria) this;
        }

        public Criteria andMemo3EqualTo(String value) {
            addCriterion("MEMO3 =", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3NotEqualTo(String value) {
            addCriterion("MEMO3 <>", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3GreaterThan(String value) {
            addCriterion("MEMO3 >", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3GreaterThanOrEqualTo(String value) {
            addCriterion("MEMO3 >=", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3LessThan(String value) {
            addCriterion("MEMO3 <", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3LessThanOrEqualTo(String value) {
            addCriterion("MEMO3 <=", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3Like(String value) {
            addCriterion("MEMO3 like", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3NotLike(String value) {
            addCriterion("MEMO3 not like", value, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3In(List<String> values) {
            addCriterion("MEMO3 in", values, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3NotIn(List<String> values) {
            addCriterion("MEMO3 not in", values, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3Between(String value1, String value2) {
            addCriterion("MEMO3 between", value1, value2, "memo3");
            return (Criteria) this;
        }

        public Criteria andMemo3NotBetween(String value1, String value2) {
            addCriterion("MEMO3 not between", value1, value2, "memo3");
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

        public Criteria andProductCodeIsNull() {
            addCriterion("PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("PRODUCT_CODE =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("PRODUCT_CODE <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("PRODUCT_CODE >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("PRODUCT_CODE <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("PRODUCT_CODE like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("PRODUCT_CODE not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("PRODUCT_CODE in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("PRODUCT_CODE not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE not between", value1, value2, "productCode");
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
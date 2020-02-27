package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PRoleExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PRoleExample() {
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

        public Criteria andRoleNameIsNull() {
            addCriterion("ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("ROLE_NAME =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("ROLE_NAME <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("ROLE_NAME >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("ROLE_NAME <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("ROLE_NAME like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("ROLE_NAME not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("ROLE_NAME in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("ROLE_NAME not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("ROLE_NAME between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("ROLE_NAME not between", value1, value2, "roleName");
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

        public Criteria andEditDateIsNull() {
            addCriterion("EDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEditDateIsNotNull() {
            addCriterion("EDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEditDateEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_DATE =", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_DATE <>", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThan(Date value) {
            addCriterionForJDBCDate("EDIT_DATE >", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_DATE >=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThan(Date value) {
            addCriterionForJDBCDate("EDIT_DATE <", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDIT_DATE <=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateIn(List<Date> values) {
            addCriterionForJDBCDate("EDIT_DATE in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("EDIT_DATE not in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDIT_DATE between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDIT_DATE not between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
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
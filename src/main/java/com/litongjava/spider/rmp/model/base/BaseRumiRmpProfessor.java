package com.litongjava.spider.rmp.model.base;

import com.litongjava.db.activerecord.Model;
import com.litongjava.model.db.IBean;

/**
 * Generated by java-db, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRumiRmpProfessor<M extends BaseRumiRmpProfessor<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}
	
	public M setFirstName(java.lang.String firstName) {
		set("first_name", firstName);
		return (M)this;
	}
	
	public java.lang.String getFirstName() {
		return getStr("first_name");
	}
	
	public M setLastName(java.lang.String lastName) {
		set("last_name", lastName);
		return (M)this;
	}
	
	public java.lang.String getLastName() {
		return getStr("last_name");
	}
	
	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}
	
	public M setSchoolId(java.lang.Long schoolId) {
		set("school_id", schoolId);
		return (M)this;
	}
	
	public java.lang.Long getSchoolId() {
		return getLong("school_id");
	}
	
	public M setDepartment(java.lang.String department) {
		set("department", department);
		return (M)this;
	}
	
	public java.lang.String getDepartment() {
		return getStr("department");
	}
	
	public M setDepartmentId(java.lang.Long departmentId) {
		set("department_id", departmentId);
		return (M)this;
	}
	
	public java.lang.Long getDepartmentId() {
		return getLong("department_id");
	}
	
	public M setAvgDifficultyRounded(java.math.BigDecimal avgDifficultyRounded) {
		set("avg_difficulty_rounded", avgDifficultyRounded);
		return (M)this;
	}
	
	public java.math.BigDecimal getAvgDifficultyRounded() {
		return getBigDecimal("avg_difficulty_rounded");
	}
	
	public M setAvgRatingRounded(java.math.BigDecimal avgRatingRounded) {
		set("avg_rating_rounded", avgRatingRounded);
		return (M)this;
	}
	
	public java.math.BigDecimal getAvgRatingRounded() {
		return getBigDecimal("avg_rating_rounded");
	}
	
	public M setMostUsefulRatingId(java.lang.Long mostUsefulRatingId) {
		set("most_useful_rating_id", mostUsefulRatingId);
		return (M)this;
	}
	
	public java.lang.Long getMostUsefulRatingId() {
		return getLong("most_useful_rating_id");
	}
	
	public M setNumRatings(java.lang.Integer numRatings) {
		set("num_ratings", numRatings);
		return (M)this;
	}
	
	public java.lang.Integer getNumRatings() {
		return getInt("num_ratings");
	}
	
	public M setWouldTakeAgainCount(java.lang.Integer wouldTakeAgainCount) {
		set("would_take_again_count", wouldTakeAgainCount);
		return (M)this;
	}
	
	public java.lang.Integer getWouldTakeAgainCount() {
		return getInt("would_take_again_count");
	}
	
	public M setWouldTakeAgainPercentRounded(java.math.BigDecimal wouldTakeAgainPercentRounded) {
		set("would_take_again_percent_rounded", wouldTakeAgainPercentRounded);
		return (M)this;
	}
	
	public java.math.BigDecimal getWouldTakeAgainPercentRounded() {
		return getBigDecimal("would_take_again_percent_rounded");
	}
	
	public M setSourceUrl(java.lang.String sourceUrl) {
		set("source_url", sourceUrl);
		return (M)this;
	}
	
	public java.lang.String getSourceUrl() {
		return getStr("source_url");
	}
	
	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}
	
	public M setCreator(java.lang.String creator) {
		set("creator", creator);
		return (M)this;
	}
	
	public java.lang.String getCreator() {
		return getStr("creator");
	}
	
	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return getDate("create_time");
	}
	
	public M setUpdater(java.lang.String updater) {
		set("updater", updater);
		return (M)this;
	}
	
	public java.lang.String getUpdater() {
		return getStr("updater");
	}
	
	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return getDate("update_time");
	}
	
	public M setDeleted(java.lang.Integer deleted) {
		set("deleted", deleted);
		return (M)this;
	}
	
	public java.lang.Integer getDeleted() {
		return getInt("deleted");
	}
	
	public M setTenantId(java.lang.Long tenantId) {
		set("tenant_id", tenantId);
		return (M)this;
	}
	
	public java.lang.Long getTenantId() {
		return getLong("tenant_id");
	}
	
	public M setFirstNameVector(java.lang.String firstNameVector) {
		set("first_name_vector", firstNameVector);
		return (M)this;
	}
	
	public java.lang.String getFirstNameVector() {
		return getStr("first_name_vector");
	}
	
	public M setLastNameVector(java.lang.String lastNameVector) {
		set("last_name_vector", lastNameVector);
		return (M)this;
	}
	
	public java.lang.String getLastNameVector() {
		return getStr("last_name_vector");
	}
	
	public M setNameVector(java.lang.String nameVector) {
		set("name_vector", nameVector);
		return (M)this;
	}
	
	public java.lang.String getNameVector() {
		return getStr("name_vector");
	}
	
}


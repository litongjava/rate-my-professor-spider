package com.litongjava.spider.rmp.model.base;

import com.litongjava.db.activerecord.Model;
import com.litongjava.model.db.IBean;

/**
 * Generated by java-db, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRumiRmpSchoolRating<M extends BaseRumiRmpSchoolRating<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}
	
	public M setClubsRating(java.lang.Integer clubsRating) {
		set("clubs_rating", clubsRating);
		return (M)this;
	}
	
	public java.lang.Integer getClubsRating() {
		return getInt("clubs_rating");
	}
	
	public M setComment(java.lang.String comment) {
		set("comment", comment);
		return (M)this;
	}
	
	public java.lang.String getComment() {
		return getStr("comment");
	}
	
	public M setCreatedByUser(java.lang.Boolean createdByUser) {
		set("created_by_user", createdByUser);
		return (M)this;
	}
	
	public java.lang.Boolean getCreatedByUser() {
		return getBoolean("created_by_user");
	}
	
	public M setDate(java.util.Date date) {
		set("date", date);
		return (M)this;
	}
	
	public java.util.Date getDate() {
		return getDate("date");
	}
	
	public M setFacilitiesRating(java.lang.String facilitiesRating) {
		set("facilities_rating", facilitiesRating);
		return (M)this;
	}
	
	public java.lang.String getFacilitiesRating() {
		return getStr("facilities_rating");
	}
	
	public M setFlagStatus(java.lang.String flagStatus) {
		set("flag_status", flagStatus);
		return (M)this;
	}
	
	public java.lang.String getFlagStatus() {
		return getStr("flag_status");
	}
	
	public M setFoodRating(java.lang.Integer foodRating) {
		set("food_rating", foodRating);
		return (M)this;
	}
	
	public java.lang.Integer getFoodRating() {
		return getInt("food_rating");
	}
	
	public M setHappinessRating(java.lang.Integer happinessRating) {
		set("happiness_rating", happinessRating);
		return (M)this;
	}
	
	public java.lang.Integer getHappinessRating() {
		return getInt("happiness_rating");
	}
	
	public M setInternetRating(java.lang.Integer internetRating) {
		set("internet_rating", internetRating);
		return (M)this;
	}
	
	public java.lang.Integer getInternetRating() {
		return getInt("internet_rating");
	}
	
	public M setLegacyId(java.lang.Integer legacyId) {
		set("legacy_id", legacyId);
		return (M)this;
	}
	
	public java.lang.Integer getLegacyId() {
		return getInt("legacy_id");
	}
	
	public M setLocationRating(java.lang.Integer locationRating) {
		set("location_rating", locationRating);
		return (M)this;
	}
	
	public java.lang.Integer getLocationRating() {
		return getInt("location_rating");
	}
	
	public M setOpportunitiesRating(java.lang.Integer opportunitiesRating) {
		set("opportunities_rating", opportunitiesRating);
		return (M)this;
	}
	
	public java.lang.Integer getOpportunitiesRating() {
		return getInt("opportunities_rating");
	}
	
	public M setReputationRating(java.lang.Integer reputationRating) {
		set("reputation_rating", reputationRating);
		return (M)this;
	}
	
	public java.lang.Integer getReputationRating() {
		return getInt("reputation_rating");
	}
	
	public M setSafetyRating(java.lang.Integer safetyRating) {
		set("safety_rating", safetyRating);
		return (M)this;
	}
	
	public java.lang.Integer getSafetyRating() {
		return getInt("safety_rating");
	}
	
	public M setSocialRating(java.lang.Integer socialRating) {
		set("social_rating", socialRating);
		return (M)this;
	}
	
	public java.lang.Integer getSocialRating() {
		return getInt("social_rating");
	}
	
	public M setThumbsDownTotal(java.lang.Integer thumbsDownTotal) {
		set("thumbs_down_total", thumbsDownTotal);
		return (M)this;
	}
	
	public java.lang.Integer getThumbsDownTotal() {
		return getInt("thumbs_down_total");
	}
	
	public M setThumbsUpTotal(java.lang.Integer thumbsUpTotal) {
		set("thumbs_up_total", thumbsUpTotal);
		return (M)this;
	}
	
	public java.lang.Integer getThumbsUpTotal() {
		return getInt("thumbs_up_total");
	}
	
	public M setSchoolId(java.lang.Long schoolId) {
		set("school_id", schoolId);
		return (M)this;
	}
	
	public java.lang.Long getSchoolId() {
		return getLong("school_id");
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
	
}


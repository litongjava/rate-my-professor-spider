package com.litongjava.spider.rmp.model.base;

import com.litongjava.db.activerecord.Model;
import com.litongjava.model.db.IBean;

/**
 * Generated by java-db, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseCourseChats<M extends BaseCourseChats<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Object id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Object getId() {
		return get("id");
	}
	
	public M setUserId(java.lang.String userId) {
		set("user_id", userId);
		return (M)this;
	}
	
	public java.lang.String getUserId() {
		return getStr("user_id");
	}
	
	public M setCreatedAt(java.util.Date createdAt) {
		set("created_at", createdAt);
		return (M)this;
	}
	
	public java.util.Date getCreatedAt() {
		return getDate("created_at");
	}
	
	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}
	
	public M setDeleted(java.lang.Boolean deleted) {
		set("deleted", deleted);
		return (M)this;
	}
	
	public java.lang.Boolean getDeleted() {
		return getBoolean("deleted");
	}
	
	public M setUsedDoc(java.lang.String usedDoc) {
		set("used_doc", usedDoc);
		return (M)this;
	}
	
	public java.lang.String getUsedDoc() {
		return getStr("used_doc");
	}
	
	public M setMetadata(java.lang.String metadata) {
		set("metadata", metadata);
		return (M)this;
	}
	
	public java.lang.String getMetadata() {
		return getStr("metadata");
	}
	
	public M setSchoolId(java.lang.Long schoolId) {
		set("school_id", schoolId);
		return (M)this;
	}
	
	public java.lang.Long getSchoolId() {
		return getLong("school_id");
	}
	
	public M setAppId(java.lang.Long appId) {
		set("app_id", appId);
		return (M)this;
	}
	
	public java.lang.Long getAppId() {
		return getLong("app_id");
	}
	
	public M setType(java.lang.String type) {
		set("type", type);
		return (M)this;
	}
	
	public java.lang.String getType() {
		return getStr("type");
	}
	
	public M setChatType(java.lang.Integer chatType) {
		set("chat_type", chatType);
		return (M)this;
	}
	
	public java.lang.Integer getChatType() {
		return getInt("chat_type");
	}
	
}


package com.litongjava.spider.rmp.model.base;

import com.litongjava.db.activerecord.Model;
import com.litongjava.model.db.IBean;

/**
 * Generated by java-db, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRumiMessage<M extends BaseRumiMessage<M>> extends Model<M> implements IBean {

	public M setUuid(java.lang.Object uuid) {
		set("uuid", uuid);
		return (M)this;
	}
	
	public java.lang.Object getUuid() {
		return get("uuid");
	}
	
	public M setCreatedAt(java.util.Date createdAt) {
		set("created_at", createdAt);
		return (M)this;
	}
	
	public java.util.Date getCreatedAt() {
		return getDate("created_at");
	}
	
	public M setUpdatedAt(java.util.Date updatedAt) {
		set("updated_at", updatedAt);
		return (M)this;
	}
	
	public java.util.Date getUpdatedAt() {
		return getDate("updated_at");
	}
	
	public M setDeletedAt(java.util.Date deletedAt) {
		set("deleted_at", deletedAt);
		return (M)this;
	}
	
	public java.util.Date getDeletedAt() {
		return getDate("deleted_at");
	}
	
	public M setSessionId(java.lang.String sessionId) {
		set("session_id", sessionId);
		return (M)this;
	}
	
	public java.lang.String getSessionId() {
		return getStr("session_id");
	}
	
	public M setRole(java.lang.String role) {
		set("role", role);
		return (M)this;
	}
	
	public java.lang.String getRole() {
		return getStr("role");
	}
	
	public M setContent(java.lang.String content) {
		set("content", content);
		return (M)this;
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}
	
	public M setMetadata(java.lang.Object metadata) {
		set("metadata", metadata);
		return (M)this;
	}
	
	public java.lang.Object getMetadata() {
		return get("metadata");
	}
	
	public M setHiddenFromUser(java.lang.Boolean hiddenFromUser) {
		set("hidden_from_user", hiddenFromUser);
		return (M)this;
	}
	
	public java.lang.Boolean getHiddenFromUser() {
		return getBoolean("hidden_from_user");
	}
	
	public M setLiked(java.lang.Boolean liked) {
		set("liked", liked);
		return (M)this;
	}
	
	public java.lang.Boolean getLiked() {
		return getBoolean("liked");
	}
	
}


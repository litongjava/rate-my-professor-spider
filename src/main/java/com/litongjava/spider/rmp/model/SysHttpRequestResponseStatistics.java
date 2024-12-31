package com.litongjava.spider.rmp.model;

import com.litongjava.spider.rmp.model.base.BaseSysHttpRequestResponseStatistics;

/**
 * Generated by java-db.
 */
public class SysHttpRequestResponseStatistics extends BaseSysHttpRequestResponseStatistics<SysHttpRequestResponseStatistics> {
  private static final long serialVersionUID = 1L;
	public static final SysHttpRequestResponseStatistics dao = new SysHttpRequestResponseStatistics().dao();
	/**
	 * 
	 */
  public static final String tableName = "sys_http_request_response_statistics";
  public static final String primaryKey = "id";
  // private java.lang.Long id
  // private java.lang.String requestIp
  // private java.lang.String requestUri
  // private java.lang.String requestHeader
  // private java.lang.String requestContentType
  // private java.lang.String requestBody
  // private java.lang.Integer responseStatusCode
  // private java.lang.String responseBody
  // private java.lang.Long elapsed
  // private java.util.Date createTime
  // private java.lang.Integer deleted
  // private java.lang.Long tenantId

  @Override
  protected String _getPrimaryKey() {
    return primaryKey;
  }

  @Override
  protected String _getTableName() {
    return tableName;
  }
}

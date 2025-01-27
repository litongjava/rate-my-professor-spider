package com.litongjava.spider.rmp.model;

import com.litongjava.spider.rmp.model.base.BaseRumiRmpRating;

/**
 * Generated by java-db.
 */
public class RumiRmpRating extends BaseRumiRmpRating<RumiRmpRating> {
  private static final long serialVersionUID = 1L;
	public static final RumiRmpRating dao = new RumiRmpRating().dao();
	/**
	 * 
	 */
  public static final String tableName = "rumi_rmp_rating";
  public static final String primaryKey = "id";
  // private java.lang.Long id
  // private java.lang.Long teacherId
  // private java.lang.Long schoolId
  // private java.util.Date adminReviewedAt
  // private java.lang.String attendanceMandatory
  // private java.lang.Integer clarityRatingRounded
  // private java.lang.String className
  // private java.lang.String comment
  // private java.lang.Integer courseType
  // private java.lang.Boolean createdByUser
  // private java.util.Date date
  // private java.lang.Integer difficultyRatingRounded
  // private java.lang.String flagStatus
  // private java.lang.String grade
  // private java.lang.Integer helpfulRatingRounded
  // private java.lang.Integer wouldLikeTakeAgain
  // private java.lang.Boolean isForCredit
  // private java.lang.Boolean isForOnlineClass
  // private java.lang.Integer maskCount
  // private java.lang.Integer qualityRating
  // private java.lang.String ratingTags
  // private java.lang.Boolean textbookIsUsed
  // private java.lang.Integer thumbsDownTotal
  // private java.lang.Integer thumbsUpTotal
  // private java.lang.String sourceUrl
  // private java.lang.String remark
  // private java.lang.String creator
  // private java.util.Date createTime
  // private java.lang.String updater
  // private java.util.Date updateTime
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


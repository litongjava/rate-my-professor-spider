package com.litongjava.spider.rmp.model;

import com.litongjava.db.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("course_chats", "id", CourseChats.class);
		arp.addMapping("rumi_embedding", "id", RumiEmbedding.class);
		arp.addMapping("rumi_message", "uuid", RumiMessage.class);
		arp.addMapping("rumi_rmp_professor", "id", RumiRmpProfessor.class);
		arp.addMapping("rumi_rmp_professor_rating_tag", "id", RumiRmpProfessorRatingTag.class);
		arp.addMapping("rumi_rmp_professor_rating_tag_mapping", "id", RumiRmpProfessorRatingTagMapping.class);
		arp.addMapping("rumi_rmp_professor_rating_thumb", "id", RumiRmpProfessorRatingThumb.class);
		arp.addMapping("rumi_rmp_rating", "id", RumiRmpRating.class);
		arp.addMapping("rumi_rmp_school", "id", RumiRmpSchool.class);
		arp.addMapping("rumi_rmp_school_departments", "id", RumiRmpSchoolDepartments.class);
		arp.addMapping("rumi_rmp_school_rating", "id", RumiRmpSchoolRating.class);
		arp.addMapping("rumi_rmp_school_rating_thumb", "id", RumiRmpSchoolRatingThumb.class);
		arp.addMapping("rumi_rmp_school_summary", "id", RumiRmpSchoolSummary.class);
		arp.addMapping("rumi_school_dict", "id", RumiSchoolDict.class);
		arp.addMapping("sys_http_request_response_statistics", "id", SysHttpRequestResponseStatistics.class);
	}
}



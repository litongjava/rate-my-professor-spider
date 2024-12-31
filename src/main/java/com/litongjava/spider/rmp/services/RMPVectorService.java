package com.litongjava.spider.rmp.services;

import java.util.List;

import org.postgresql.util.PGobject;

import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.db.utils.PgVectorUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.template.SqlTemplates;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RMPVectorService {

  public void embeddingRating() {
    List<Row> records = Db.find("select id,class_name from " + TableNames.rumi_rmp_rating);

    String updateSql = "update " + TableNames.rumi_rmp_rating + " set class_name_vector= ? where id=?";

    for (Row record : records) {
      String text = record.getStr("class_name");
      Object id = record.get("id");

      String vector = Aop.get(VectorService.class).getVector(text);

      int update = Db.updateBySql(updateSql, vector, id);
      if (update < 1) {
        log.error("update fail:{},{}", id, text);
      }

    }
  }

  public void embeddingProfessor() {
    List<Row> records = Db.find("select id,first_name,last_name,name from " + TableNames.rumi_rmp_professor);

    String updateSql = "update " + TableNames.rumi_rmp_professor + " set first_name_vector= ?,last_name_vector=?,name_vector=? where id=?";

    for (Row record : records) {
      String firstName = record.getStr("first_name");
      String lastName = record.getStr("last_name");
      String name = record.getStr("name");
      Object id = record.get("id");

      PGobject firstNameVector = PgVectorUtils.getPgVector(Aop.get(VectorService.class).getVector(firstName));
      PGobject lastNameVector = PgVectorUtils.getPgVector(Aop.get(VectorService.class).getVector(lastName));
      PGobject nameVector = PgVectorUtils.getPgVector(Aop.get(VectorService.class).getVector(name));

      int update = Db.updateBySql(updateSql, firstNameVector, lastNameVector, nameVector, id);
      if (update < 1) {
        log.error("update fail:{},{}", id, firstName, lastName);
      }

    }
  }

  public Row searchNameVector(String name) {
    String string = Aop.get(VectorService.class).getVector(name);
    System.out.println(string);
    String professorSql = SqlTemplates.get("rumi_rmp_professor.name_vector_search");
    Row professor = Db.findFirst(professorSql, string);
    return professor;
  }
}

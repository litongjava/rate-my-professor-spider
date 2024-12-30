drop table if exists rumi_rmp_school;
CREATE TABLE rumi_rmp_school (
    id BIGINT PRIMARY KEY,
    avg_rating_rounded DECIMAL(3,2),
    city VARCHAR(255),
    country VARCHAR(255),
    legacy_id INT,
    name VARCHAR(255),
    num_ratings INT,
    state VARCHAR(255),
    
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0
);

drop table if exists rumi_rmp_school_departments;
CREATE TABLE rumi_rmp_school_departments (
    id BIGINT PRIMARY KEY,
  d_id int NOT NULL,
    name VARCHAR(255) NOT NULL,
    school_id BIGINT NOT NULL,
    
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE rumi_rmp_school_rating (
    id BIGINT PRIMARY KEY,
    clubs_rating INT,
    comment TEXT,
    created_by_user BOOLEAN,
    date TIMESTAMP WITH TIME ZONE,
    facilities_rating VARCHAR(255),
    flag_status VARCHAR(255),
    food_rating INT,
    happiness_rating INT,
    internet_rating INT,
    legacy_id INT,
    location_rating INT,
    opportunities_rating INT,
    reputation_rating INT,
    safety_rating INT,
    social_rating INT,
    thumbs_down_total INT,
    thumbs_up_total INT,
    
    -- 外键
    school_id BIGINT NOT NULL,
    
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE rumi_rmp_school_rating_thumb (
    id BIGINT PRIMARY KEY,
    campus_rating_id BIGINT NOT NULL,
    computer_id VARCHAR(255),
    legacy_id INT,
    thumbs_down INT,
    thumbs_up INT,
    user_id INT,
    school_id bigint,
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0   
);

CREATE TABLE rumi_rmp_school_summary (
    id BIGINT PRIMARY KEY,
    campus_condition FLOAT,
    campus_location FLOAT,
    career_opportunities FLOAT,
    club_and_event_activities FLOAT,
    food_quality FLOAT,
    internet_speed FLOAT,
    library_condition FLOAT,
    school_reputation FLOAT,
    school_safety FLOAT,
    school_satisfaction FLOAT,
    social_activities FLOAT,
    
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0
);

drop table if exists rumi_rmp_professor_rating_thumb;
CREATE TABLE rumi_rmp_professor_rating_thumb (
    id BIGINT PRIMARY KEY,
    rating_id BIGINT NOT NULL,
    computer_id VARCHAR(255),
    thumbs_down INT,
    thumbs_up INT,
    user_id INT,
    teacher_id bigint,
    -- 通用字段
    remark VARCHAR(256),
    creator VARCHAR(64) DEFAULT '',
    create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater VARCHAR(64) DEFAULT '',
    update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted SMALLINT NOT NULL DEFAULT 0,
    tenant_id BIGINT NOT NULL DEFAULT 0   
);

DROP TABLE IF EXISTS rumi_rmp_rating;
CREATE TABLE rumi_rmp_rating (
  id bigint PRIMARY KEY,
  teacher_id BIGINT,
  school_id BIGINT,
  admin_reviewed_at TIMESTAMP WITH TIME ZONE,
  attendance_mandatory VARCHAR,
  clarity_rating_rounded INTEGER,
  class_name VARCHAR,
  comment VARCHAR,
  course_type INTEGER,
  created_by_user BOOLEAN,
  date TIMESTAMP WITH TIME ZONE,
  difficulty_rating_rounded INTEGER,
  flag_status VARCHAR,
  grade VARCHAR,
  helpful_rating_rounded INTEGER,
  would_like_take_again INTEGER,
  is_for_credit BOOLEAN,
  is_for_online_class BOOLEAN,
  mask_count integer,
  quality_rating INTEGER,
  rating_tags VARCHAR,
  textbook_is_used BOOLEAN,
  thumbs_down_total INTEGER,
  thumbs_up_total INTEGER,
  source_url VARCHAR,
  remark VARCHAR(256),
  creator VARCHAR(64) DEFAULT '',
  create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updater VARCHAR(64) DEFAULT '',
  update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted SMALLINT NOT NULL DEFAULT 0,
  tenant_id BIGINT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS rumi_rmp_professor_rating_tag_mapping;
CREATE TABLE rumi_rmp_professor_rating_tag_mapping (
  id bigint PRIMARY KEY,
  teacher_id bigint,
  tag_id bigint,
  tag_count integer,
  remark VARCHAR(256),
  creator VARCHAR(64) DEFAULT '',
  create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updater VARCHAR(64) DEFAULT '',
  update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted SMALLINT NOT NULL DEFAULT 0,
  tenant_id BIGINT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS rumi_rmp_professor_rating_tag;
CREATE TABLE rumi_rmp_professor_rating_tag (
  id bigint PRIMARY KEY,
  tag_name VARCHAR,
  remark VARCHAR(256),
  creator VARCHAR(64) DEFAULT '',
  create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updater VARCHAR(64) DEFAULT '',
  update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted SMALLINT NOT NULL DEFAULT 0,
  tenant_id BIGINT NOT NULL DEFAULT 0
);

drop table if exists rumi_rmp_professor;
CREATE TABLE rumi_rmp_professor (
  id BIGINT NOT NULL PRIMARY KEY,
  first_name VARCHAR(64),
  last_name VARCHAR(64),
  name VARCHAR(128),
  school_id BIGINT,
  department VARCHAR(128),
  department_id BIGINT,
  avg_difficulty_rounded DECIMAL(3, 2),
  avg_rating_rounded DECIMAL(3, 2),
  most_useful_rating_id bigint,
  num_ratings INT,
  "would_take_again_count" "int4",
  "would_take_again_percent_rounded" "numeric",
  source_url VARCHAR(256),
  remark VARCHAR(256),
  creator VARCHAR(64) DEFAULT '',
  create_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updater VARCHAR(64) DEFAULT '',
  update_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted SMALLINT NOT NULL DEFAULT 0,
  tenant_id BIGINT NOT NULL DEFAULT 0,
  first_name_vector vector,
  last_name_vector vector,
  name_vector vector
);

CREATE TABLE "public"."rumi_embedding" (
  "id" "pg_catalog"."int8" NOT NULL,
  "t" "pg_catalog"."text" COLLATE "pg_catalog"."default",
  "v" "public"."vector",
  "m" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  "md5" "pg_catalog"."varchar" COLLATE "pg_catalog"."default",
  CONSTRAINT "rumi_embedding_pkey" PRIMARY KEY ("id")
);

CREATE INDEX "idx_rumi_embedding_md5" ON "public"."rumi_embedding" USING btree (
  "md5" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

CREATE INDEX "idx_rumi_embedding_md5_m" ON "public"."rumi_embedding" USING btree (
  "md5" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST,
  "m" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
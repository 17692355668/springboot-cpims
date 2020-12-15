-- 这里是定义数据结构的SQL，每次运行都会执行，此文件不能为空，必须至少写一句Sql语句
DROP TABLE IF EXISTS cpims_prm_project_registration;
CREATE TABLE cpims_prm_project_registration(
                                               id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
                                               project_name VARCHAR(128)    COMMENT '项目名称' ,
                                               owner VARCHAR(32)    COMMENT '业主' ,
                                               revision INT    COMMENT '乐观锁' ,
                                               created_by VARCHAR(32)    COMMENT '创建人' ,
                                               created_time DATETIME    COMMENT '创建时间' ,
                                               updated_by VARCHAR(32)    COMMENT '更新人' ,
                                               updated_time DATETIME    COMMENT '更新时间' ,
                                               PRIMARY KEY (id)
) COMMENT = '项目登记 ';

ALTER TABLE cpims_prm_project_registration COMMENT '项目登记';
DROP TABLE IF EXISTS cpims_prm_project_review;
CREATE TABLE cpims_prm_project_review(
                                         id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
                                         registration_id INT NOT NULL   COMMENT '项目登记表外键' ,
                                         revision INT    COMMENT '乐观锁' ,
                                         created_by VARCHAR(32)    COMMENT '创建人' ,
                                         created_time DATETIME    COMMENT '创建时间' ,
                                         updated_by VARCHAR(32)    COMMENT '更新人' ,
                                         updated_time DATETIME    COMMENT '更新时间' ,
                                         PRIMARY KEY (id)
) COMMENT = '项目审核 ';

ALTER TABLE cpims_prm_project_review COMMENT '项目审核';
DROP TABLE IF EXISTS cpims_prm_project_history;
CREATE TABLE cpims_prm_project_history(
                                          id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
                                          registration_id INT NOT NULL   COMMENT '项目登记表外键' ,
                                          project_name VARCHAR(128)    COMMENT '项目名称' ,
                                          owner VARCHAR(32)    COMMENT '业主' ,
                                          revision INT    COMMENT '乐观锁' ,
                                          created_by VARCHAR(32)    COMMENT '创建人' ,
                                          created_time DATETIME    COMMENT '创建时间' ,
                                          updated_by VARCHAR(32)    COMMENT '更新人' ,
                                          updated_time DATETIME    COMMENT '更新时间' ,
                                          PRIMARY KEY (id)
) COMMENT = '历史项目库 ';

ALTER TABLE cpims_prm_project_history COMMENT '历史项目库';
DROP TABLE IF EXISTS cpims_basic_data_dictionary;
CREATE TABLE cpims_basic_data_dictionary(
                                            id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
                                            system_name VARCHAR(32)    COMMENT '子系统' ,
                                            group_name VARCHAR(32)    COMMENT '分组' ,
                                            real_value INT    COMMENT '真实值' ,
                                            show_value VARCHAR(32)    COMMENT '表现值' ,
                                            sort_value INT    COMMENT '排序值' ,
                                            remarks VARCHAR(128)    COMMENT '备注描述' ,
                                            revision INT    COMMENT '乐观锁' ,
                                            created_by VARCHAR(32)    COMMENT '创建人' ,
                                            created_time DATETIME    COMMENT '创建时间' ,
                                            updated_by VARCHAR(32)    COMMENT '更新人' ,
                                            updated_time DATETIME    COMMENT '更新时间' ,
                                            PRIMARY KEY (id)
) COMMENT = '数据字典 ';

ALTER TABLE cpims_basic_data_dictionary COMMENT '数据字典';


-- 该角本是同步服务系统每次启动会自动执行的角本。编写时，请注意确保角本中不会变更现有记录
-- 数据字典初始化数据
insert into cpims_basic_data_dictionary(id, system_name, group_name, real_value, show_value, sort_value, remarks) values
    (1, '基础服务', 'dictionary', 1, '固定资产', 1, ''),
    (2, '基础服务', 'dictionary', 2, '对外投资', 2, ''),
    (3, '基础服务', 'dictionary', 3, '金融投资', 3, '');
# 建立数据库
CREATE DATABASE myreader;
USE myreader;
#用户
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `phone` varchar(12) NOT NULL unique COMMENT '使用手机号登陆',
  `name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `code` int(11) DEFAULT NULL COMMENT '验证码，相当于密码',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARSET = utf8;

#类别
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '类别名',
  `parent_id` bigint DEFAULT NULL COMMENT '父类别',
  PRIMARY KEY (`id`),
    KEY `k_category` (`parent_id`),
  CONSTRAINT `k_category` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
)ENGINE = InnoDB CHARSET = utf8;
#书籍
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '书名',
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `picture` varchar(255) DEFAULT NULL COMMENT '书籍封面',
  `introduction` text DEFAULT NULL COMMENT '书籍简介',
  `length` int(11) DEFAULT 0 COMMENT '字数（万字）',
  `charSet` varchar(20) DEFAULT NULL COMMENT '字符集编码',
  `size` bigint DEFAULT 0 COMMENT '文本大小（字节）',
  `path` varchar(255) NOT NULL COMMENT '文件存放位置',
  `up_date` date DEFAULT NULL COMMENT '上传时间',
  `download_count` bigint DEFAULT 0 COMMENT '下载量',
  `star_avg` double DEFAULT 0 COMMENT '评分',
  `category_id` bigint not null COMMENT '分类',
  PRIMARY KEY (`id`),
  KEY `fk_book_category` (`category_id`),
  CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
)ENGINE = InnoDB CHARSET = utf8;

#章节
CREATE TABLE `catalogue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '章节名',
  `start` bigint DEFAULT NULL COMMENT '在此书的字节位置',
  `book_id` bigint NOT NULL COMMENT '书',
  PRIMARY KEY (`id`),
  KEY `fk_catalogue_book` (`book_id`),
  CONSTRAINT `fk_catalogue_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
)ENGINE = InnoDB CHARSET = utf8;

#缓存字节数
CREATE TABLE `cache` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_index` int DEFAULT 0 COMMENT '第几块缓存',
  `data_size` int DEFAULT 0 COMMENT '缓存大小 char',
  `book_data` text DEFAULT NULL COMMENT '数据 char[]',
  `book_id` bigint NOT NULL COMMENT '书',
  PRIMARY KEY (`id`),
  KEY `fk_cache_book` (`book_id`),
  CONSTRAINT `fk_cache_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
)ENGINE = InnoDB CHARSET = utf8;

#阅读关系
CREATE TABLE `read_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `start` bigint DEFAULT 0 COMMENT '在此书的字节位置',
  `duration` bigint DEFAULT 0 COMMENT '阅读时长',
  `is_download` bit DEFAULT 0 COMMENT '是否下载0-否 1-是',
  `book_id` bigint NOT NULL COMMENT '书',
  `user_id` bigint NOT NULL COMMENT '用户',
  PRIMARY KEY (`id`),
  KEY `fk_read_book` (`book_id`),
  KEY `fk_read_user` (`user_id`),
  CONSTRAINT `fk_read_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_read_user` FOREIGN KEY (`user_id`) REFERENCES phone_user (`id`)
)ENGINE = InnoDB CHARSET = utf8;

#评论
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT null COMMENT '评论内容',
  `star` double DEFAULT null COMMENT '评分',
  `book_id` bigint NOT NULL COMMENT '书',
  `user_id` bigint NOT NULL COMMENT '用户',
  PRIMARY KEY (`id`),
  KEY `fk_comment_book` (`book_id`),
  KEY `fk_comment_user` (`user_id`),
  CONSTRAINT `fk_comment_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES phone_user (`id`)
)ENGINE = InnoDB CHARSET = utf8;

#排行榜
CREATE TABLE `ranking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `order_by` varchar(32) DEFAULT NULL COMMENT '排序规则（length、up_date、download_count、star_avg）',
  `category_id` bigint not NULL COMMENT '分类',
  PRIMARY KEY (`id`),
  KEY `fk_range_category` (`category_id`),
  CONSTRAINT `fk_range_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE = InnoDB CHARSET = utf8;
#广告轮播图
CREATE TABLE `mybanner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `picture` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `category_id` bigint not NULL COMMENT '分类',
  PRIMARY KEY (`id`),
    KEY `fk_banner_category` (`category_id`),
  CONSTRAINT `fk_banner_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE = InnoDB CHARSET = utf8;


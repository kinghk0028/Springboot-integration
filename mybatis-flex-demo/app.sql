CREATE TABLE `tb_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `tb_account` (`user_name`, `age`, `birthday`)
VALUES
    ('Michael Yang', 18, '2000-01-01 00:00:00'),
    ('Jean', 17, '2001-01-01 00:00:00');

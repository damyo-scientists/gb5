# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.5.5-MariaDB-1:10.5.5+maria~focal)
# Database: gb5
# Generation Time: 2020-10-26 11:11:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table batting_result
# ------------------------------------------------------------

CREATE TABLE `batting_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `batting_type` int(11) DEFAULT NULL,
  `hit_random_result` double DEFAULT NULL,
  `out_count` int(11) DEFAULT NULL,
  `scoring_count` int(11) DEFAULT NULL,
  `deck_character_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ovovs5alm5sdy9kbd5aelxtj` (`code`),
  KEY `FK7jxcpv158niq6565x2mwh09c` (`deck_character_id`),
  CONSTRAINT `FK7jxcpv158niq6565x2mwh09c` FOREIGN KEY (`deck_character_id`) REFERENCES `deck_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table batting_result_running_result_list
# ------------------------------------------------------------

CREATE TABLE `batting_result_running_result_list` (
  `batting_result_id` bigint(20) NOT NULL,
  `running_result_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_j4hge03ks39rdcdcfx205abcq` (`running_result_list_id`),
  KEY `FKeoq626u122dj4uyremvc8x4qs` (`batting_result_id`),
  CONSTRAINT `FKeoq626u122dj4uyremvc8x4qs` FOREIGN KEY (`batting_result_id`) REFERENCES `batting_result` (`id`),
  CONSTRAINT `FKsoskw74wwbfg0x5m47fx7furv` FOREIGN KEY (`running_result_list_id`) REFERENCES `running_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table character_piece
# ------------------------------------------------------------

CREATE TABLE `character_piece` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `target_character_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mnnsxiguogu4d1egg4jhhrd3k` (`code`),
  KEY `FK37942ev498uk9ma9u6ascfu7o` (`target_character_id`),
  CONSTRAINT `FK37942ev498uk9ma9u6ascfu7o` FOREIGN KEY (`target_character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table character_set
# ------------------------------------------------------------

CREATE TABLE `character_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `cost` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gdn6mx6dkdorw175sd4r2ww2t` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table character_set_relation
# ------------------------------------------------------------

CREATE TABLE `character_set_relation` (
  `character_set_id` bigint(20) NOT NULL,
  `character_id` bigint(20) NOT NULL,
  KEY `FKt3f1tl7696oviw655qm9j4hug` (`character_id`),
  KEY `FK7iibbla5g1ac5n40ampu5ulxl` (`character_set_id`),
  CONSTRAINT `FK7iibbla5g1ac5n40ampu5ulxl` FOREIGN KEY (`character_set_id`) REFERENCES `character_set` (`id`),
  CONSTRAINT `FKt3f1tl7696oviw655qm9j4hug` FOREIGN KEY (`character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table character_status
# ------------------------------------------------------------

CREATE TABLE `character_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `batting_accuracy` int(11) DEFAULT NULL,
  `batting_power` int(11) DEFAULT NULL,
  `concentration_point` int(11) DEFAULT NULL,
  `defending` int(11) DEFAULT NULL,
  `health_point` int(11) DEFAULT NULL,
  `leadership` int(11) DEFAULT NULL,
  `reflexes` int(11) DEFAULT NULL,
  `running_speed` int(11) DEFAULT NULL,
  `sense` int(11) DEFAULT NULL,
  `throwing_accuracy` int(11) DEFAULT NULL,
  `throwing_speed` int(11) DEFAULT NULL,
  `character_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dgs382nuq50j3e5gt277nxqh6` (`code`),
  KEY `FKab47w0e8ifqhwocj1o576sjw5` (`character_id`),
  CONSTRAINT `FKab47w0e8ifqhwocj1o576sjw5` FOREIGN KEY (`character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table deck
# ------------------------------------------------------------

CREATE TABLE `deck` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6v2y22iqq85euujnncd4ssj20` (`code`),
  KEY `FKceph6k63k3j52jdb1qdc8imkc` (`user_id`),
  CONSTRAINT `FKceph6k63k3j52jdb1qdc8imkc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table deck_character
# ------------------------------------------------------------

CREATE TABLE `deck_character` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `character_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r1g60lgn0mysxmiov8xdgir6` (`code`),
  KEY `FKgwko68qhgbqn6uwi0tkef50nn` (`character_id`),
  CONSTRAINT `FKgwko68qhgbqn6uwi0tkef50nn` FOREIGN KEY (`character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table deck_deck_characters
# ------------------------------------------------------------

CREATE TABLE `deck_deck_characters` (
  `deck_id` bigint(20) NOT NULL,
  `deck_characters_id` bigint(20) NOT NULL,
  `deck_characters_key` varchar(255) NOT NULL,
  PRIMARY KEY (`deck_id`,`deck_characters_key`),
  KEY `FKog4ek9nwd8b5n27ljqmvgn5dx` (`deck_characters_id`),
  CONSTRAINT `FKavhaeec1w74arpxhcwh7a3wpq` FOREIGN KEY (`deck_id`) REFERENCES `deck` (`id`),
  CONSTRAINT `FKog4ek9nwd8b5n27ljqmvgn5dx` FOREIGN KEY (`deck_characters_id`) REFERENCES `deck_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game
# ------------------------------------------------------------

CREATE TABLE `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `game_state` int(11) DEFAULT NULL,
  `game_type` int(11) DEFAULT NULL,
  `matching_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q3tu80gjneqixlguktt38fd93` (`code`),
  KEY `FKdjiob96l5q05u8k1i65863oar` (`matching_id`),
  CONSTRAINT `FKdjiob96l5q05u8k1i65863oar` FOREIGN KEY (`matching_id`) REFERENCES `matching` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game_character
# ------------------------------------------------------------

CREATE TABLE `game_character` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `acquisition_coefficient` int(11) DEFAULT NULL,
  `acquisition_number` int(11) DEFAULT NULL,
  `back_number` int(11) DEFAULT NULL,
  `character_unlock_date_time` datetime(6) DEFAULT NULL,
  `cumulative_acquisition_coefficient` int(11) DEFAULT NULL,
  `exp` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `is_character_locked` bit(1) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mj1lkugim0chp87rof71tdwiu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game_character_hitting_inclination
# ------------------------------------------------------------

CREATE TABLE `game_character_hitting_inclination` (
  `game_character_id` bigint(20) NOT NULL,
  `hitting_inclination` double DEFAULT NULL,
  KEY `FKe6hnuqdmdac9slvjlrnuppj9e` (`game_character_id`),
  CONSTRAINT `FKe6hnuqdmdac9slvjlrnuppj9e` FOREIGN KEY (`game_character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game_options
# ------------------------------------------------------------

CREATE TABLE `game_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `inning` int(11) DEFAULT NULL,
  `game_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ikxmeo1teu0k3m52rhe4f33sy` (`code`),
  KEY `FKpr6ko2pk5c9md3jbbwb3al26n` (`game_id`),
  CONSTRAINT `FKpr6ko2pk5c9md3jbbwb3al26n` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game_result
# ------------------------------------------------------------

CREATE TABLE `game_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `deck1score` int(11) DEFAULT NULL,
  `deck2score` int(11) DEFAULT NULL,
  `game_id` bigint(20) DEFAULT NULL,
  `winning_deck_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iskypjydxtyj5ukndutth06s5` (`code`),
  KEY `FKg7hnlts78dmtl1nqjleq4v1ku` (`game_id`),
  KEY `FKgoktnpi53hhyp2pkhme3w8epl` (`winning_deck_id`),
  CONSTRAINT `FKg7hnlts78dmtl1nqjleq4v1ku` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `FKgoktnpi53hhyp2pkhme3w8epl` FOREIGN KEY (`winning_deck_id`) REFERENCES `deck` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table game_result_inning_results
# ------------------------------------------------------------

CREATE TABLE `game_result_inning_results` (
  `game_result_id` bigint(20) NOT NULL,
  `inning_results_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9eqfebmkssva4lnqtvg3gcocw` (`inning_results_id`),
  KEY `FKlubqg8fpelcnckalwlycfgx50` (`game_result_id`),
  CONSTRAINT `FKlubqg8fpelcnckalwlycfgx50` FOREIGN KEY (`game_result_id`) REFERENCES `game_result` (`id`),
  CONSTRAINT `FKqm2ri1vny4nieo3q5csfi3ldn` FOREIGN KEY (`inning_results_id`) REFERENCES `inning_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table hit_result
# ------------------------------------------------------------

CREATE TABLE `hit_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `final_hitting_probability` double DEFAULT NULL,
  `hit_check_random_value` double DEFAULT NULL,
  `is_hit` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwsxqis501a71tyjfgukd1n46` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inning_result
# ------------------------------------------------------------

CREATE TABLE `inning_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `inning` int(11) DEFAULT NULL,
  `inning_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fw7o4wvwgv1bohpahh3rbfaly` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inning_result_batting_result_list
# ------------------------------------------------------------

CREATE TABLE `inning_result_batting_result_list` (
  `inning_result_id` bigint(20) NOT NULL,
  `batting_result_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_30vmwpqgicaclj8r87hdcqnky` (`batting_result_list_id`),
  KEY `FK3p6xqia8u4hg3y6jhsrbr9gqc` (`inning_result_id`),
  CONSTRAINT `FK2xh7fk7jdu4hbj1ka3jmuebno` FOREIGN KEY (`batting_result_list_id`) REFERENCES `batting_result` (`id`),
  CONSTRAINT `FK3p6xqia8u4hg3y6jhsrbr9gqc` FOREIGN KEY (`inning_result_id`) REFERENCES `inning_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inventory
# ------------------------------------------------------------

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `money` int(11) DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r16gsk43x9ty7eai47qjl1it` (`code`),
  KEY `FK86u2qtuaxn5uph2u9olsxk2ic` (`user_id`),
  CONSTRAINT `FK86u2qtuaxn5uph2u9olsxk2ic` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inventory_character_pieces
# ------------------------------------------------------------

CREATE TABLE `inventory_character_pieces` (
  `inventory_id` bigint(20) NOT NULL,
  `character_pieces` int(11) DEFAULT NULL,
  `character_pieces_key` bigint(20) NOT NULL,
  PRIMARY KEY (`inventory_id`,`character_pieces_key`),
  KEY `FKsjqlufw62qband03ap9i9rbxu` (`character_pieces_key`),
  CONSTRAINT `FKdutikaxvv9rh1a4kexlwtve4e` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FKsjqlufw62qband03ap9i9rbxu` FOREIGN KEY (`character_pieces_key`) REFERENCES `character_piece` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inventory_passes
# ------------------------------------------------------------

CREATE TABLE `inventory_passes` (
  `inventory_id` bigint(20) NOT NULL,
  `passes` int(11) DEFAULT NULL,
  `pass_type` int(11) NOT NULL,
  PRIMARY KEY (`inventory_id`,`pass_type`),
  CONSTRAINT `FKhguyssgnsghvy1ybnewon0flb` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table inventory_tickets
# ------------------------------------------------------------

CREATE TABLE `inventory_tickets` (
  `inventory_id` bigint(20) NOT NULL,
  `tickets` int(11) DEFAULT NULL,
  `tickets_key` varchar(255) NOT NULL,
  PRIMARY KEY (`inventory_id`,`tickets_key`),
  CONSTRAINT `FK8v4yp0cuxdvq9728j3yl5cirs` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table matching
# ------------------------------------------------------------

CREATE TABLE `matching` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `is_opened` bit(1) NOT NULL,
  `deck1_id` bigint(20) DEFAULT NULL,
  `deck2_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nt5jk2w1fhfe2pfmg5dt283yc` (`code`),
  KEY `FK5qsxcw3e2bkuxde486xpaj4rk` (`deck1_id`),
  KEY `FKc9vgdxsukbo81yd5a9imos8c` (`deck2_id`),
  CONSTRAINT `FK5qsxcw3e2bkuxde486xpaj4rk` FOREIGN KEY (`deck1_id`) REFERENCES `deck` (`id`),
  CONSTRAINT `FKc9vgdxsukbo81yd5a9imos8c` FOREIGN KEY (`deck2_id`) REFERENCES `deck` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table pass
# ------------------------------------------------------------

CREATE TABLE `pass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fofrt6t7m5ev4xbshmaexv6x9` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table report_character
# ------------------------------------------------------------

CREATE TABLE `report_character` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `scouting_report_id` bigint(20) DEFAULT NULL,
  `target_character_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pm9modl4twniqtmoka0omu2fc` (`code`),
  KEY `FKio0k2ifwfxi40weiwrdjlmi6u` (`scouting_report_id`),
  KEY `FKplexv1k989n1qa4liaose5vdc` (`target_character_id`),
  CONSTRAINT `FKio0k2ifwfxi40weiwrdjlmi6u` FOREIGN KEY (`scouting_report_id`) REFERENCES `scouting_report` (`id`),
  CONSTRAINT `FKplexv1k989n1qa4liaose5vdc` FOREIGN KEY (`target_character_id`) REFERENCES `game_character` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table running_result
# ------------------------------------------------------------

CREATE TABLE `running_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `base_result_type` int(11) DEFAULT NULL,
  `out_count` int(11) DEFAULT NULL,
  `runner` tinyblob DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l5atv5as6iw4is98q4kpare3p` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouter
# ------------------------------------------------------------

CREATE TABLE `scouter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `is_hold` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `report_regen_time` datetime(6) DEFAULT NULL,
  `seed` bigint(20) DEFAULT NULL,
  `character_set_id` bigint(20) DEFAULT NULL,
  `scouter_status_id` bigint(20) DEFAULT NULL,
  `scouting_report_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9vy0yuhnqim9rhc97psmu0k3c` (`code`),
  KEY `FKryknbjjef2vbx9700l7491yn6` (`character_set_id`),
  KEY `FKsn935l7gsqsi7396n7985su3h` (`scouter_status_id`),
  KEY `FK8b628n2ha0ba8s9lm030pu7k3` (`scouting_report_id`),
  CONSTRAINT `FK8b628n2ha0ba8s9lm030pu7k3` FOREIGN KEY (`scouting_report_id`) REFERENCES `scouting_report` (`id`),
  CONSTRAINT `FKryknbjjef2vbx9700l7491yn6` FOREIGN KEY (`character_set_id`) REFERENCES `character_set` (`id`),
  CONSTRAINT `FKsn935l7gsqsi7396n7985su3h` FOREIGN KEY (`scouter_status_id`) REFERENCES `scouter_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouter_status
# ------------------------------------------------------------

CREATE TABLE `scouter_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `deflection` int(11) DEFAULT NULL,
  `deflection_randomize_value` int(11) DEFAULT NULL,
  `is_give_minimum_grade_assured` bit(1) DEFAULT NULL,
  `report_chracter_size` int(11) DEFAULT NULL,
  `report_random_count` varchar(255) DEFAULT NULL,
  `report_reset_time` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qvag5c0eeyhjqsn1ioou4g04k` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouter_status_grade_acquisition_rates
# ------------------------------------------------------------

CREATE TABLE `scouter_status_grade_acquisition_rates` (
  `scouter_status_id` bigint(20) NOT NULL,
  `grade_acquisition_rates` int(11) DEFAULT NULL,
  KEY `FKhvw4oy0wenm0ged5n3vqnusrc` (`scouter_status_id`),
  CONSTRAINT `FKhvw4oy0wenm0ged5n3vqnusrc` FOREIGN KEY (`scouter_status_id`) REFERENCES `scouter_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouter_status_report_hide_status_columns
# ------------------------------------------------------------

CREATE TABLE `scouter_status_report_hide_status_columns` (
  `scouter_status_id` bigint(20) NOT NULL,
  `report_hide_status_columns` int(11) DEFAULT NULL,
  KEY `FKgo5gd51wo8515srvjotrja6op` (`scouter_status_id`),
  CONSTRAINT `FKgo5gd51wo8515srvjotrja6op` FOREIGN KEY (`scouter_status_id`) REFERENCES `scouter_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouting_report
# ------------------------------------------------------------

CREATE TABLE `scouting_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hnls5iuffef3cxokog0sussdj` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table scouting_report_report_character_list
# ------------------------------------------------------------

CREATE TABLE `scouting_report_report_character_list` (
  `scouting_report_id` bigint(20) NOT NULL,
  `report_character_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_tlls40kq8t7n850oclqamu959` (`report_character_list_id`),
  KEY `FKnssy71f8af8318qfxw40lays9` (`scouting_report_id`),
  CONSTRAINT `FK7txg7qob3p2iiipkasstsk8b2` FOREIGN KEY (`report_character_list_id`) REFERENCES `report_character` (`id`),
  CONSTRAINT `FKnssy71f8af8318qfxw40lays9` FOREIGN KEY (`scouting_report_id`) REFERENCES `scouting_report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table ticket
# ------------------------------------------------------------

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bxs74dmpyjw6rsd4xec3pfr0c` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user
# ------------------------------------------------------------

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `report_acquisition_reset_time` datetime(6) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h1vneshxbwkd1ailk02vdy2qu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table user_character_relation
# ------------------------------------------------------------

CREATE TABLE `user_character_relation` (
  `users_id` bigint(20) NOT NULL,
  `characters_id` bigint(20) NOT NULL,
  KEY `FK1urq8g8rh5aoqv6uh5smk5tvi` (`characters_id`),
  KEY `FKa1ui7rtgeacdht35ull9wbrll` (`users_id`),
  CONSTRAINT `FK1urq8g8rh5aoqv6uh5smk5tvi` FOREIGN KEY (`characters_id`) REFERENCES `game_character` (`id`),
  CONSTRAINT `FKa1ui7rtgeacdht35ull9wbrll` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

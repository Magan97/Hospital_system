-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-11-26 05:02:39
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hospital_system`
--

-- --------------------------------------------------------

--
-- 表的结构 `admission_details`
--

CREATE TABLE IF NOT EXISTS `admission_details` (
  `admission_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `guardian_id` int(11) DEFAULT NULL,
  `room_ward_id` int(11) DEFAULT NULL,
  `bed_id` int(11) DEFAULT NULL,
  `refer_doctor` int(11) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `admission_time` date DEFAULT NULL,
  `emergency_contact` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `appointment_bill`
--

CREATE TABLE IF NOT EXISTS `appointment_bill` (
  `appointment_bill_id` varchar(10) NOT NULL,
  `appointment_id` varchar(8) NOT NULL,
  `patient_id` varchar(8) NOT NULL,
  `bill_date` date NOT NULL,
  `appointment_charge` int(5) NOT NULL,
  `hospital_charge` int(5) NOT NULL,
  `grand_total` int(5) NOT NULL,
  `discount` int(5) NOT NULL,
  `net_value` int(5) NOT NULL,
  PRIMARY KEY (`appointment_bill_id`),
  KEY `fk` (`appointment_id`),
  KEY `fk1` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `appointment_bill_payment`
--

CREATE TABLE IF NOT EXISTS `appointment_bill_payment` (
  `bill_payment_id` varchar(10) NOT NULL,
  `appointment_bill_id` varchar(10) NOT NULL,
  `amount_paid` int(5) NOT NULL,
  `paid_date` date NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `cheque_no` int(15) NOT NULL,
  `cheque_date` date NOT NULL,
  `bank` varchar(20) NOT NULL,
  PRIMARY KEY (`bill_payment_id`),
  KEY `fk2` (`appointment_bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `bed_details`
--

CREATE TABLE IF NOT EXISTS `bed_details` (
  `bed_id` int(11) DEFAULT NULL,
  `room_ward_id` int(11) DEFAULT NULL,
  `available` varchar(20) DEFAULT NULL,
  `admission_id` int(11) DEFAULT NULL,
  `bed_desc` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `doctor_appointment`
--

CREATE TABLE IF NOT EXISTS `doctor_appointment` (
  `appointment_id` varchar(8) NOT NULL,
  `patient_id` varchar(8) NOT NULL,
  `doctor_id` varchar(8) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` varchar(10) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `fk_doctor_id` (`patient_id`),
  KEY `fk_doctor` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `doctor_appointment`
--

INSERT INTO `doctor_appointment` (`appointment_id`, `patient_id`, `doctor_id`, `appointment_date`, `appointment_time`) VALUES
('APID_1', 'PAID_1', 'DCID_2', '2017-11-24', '5:0');

-- --------------------------------------------------------

--
-- 表的结构 `doctor_details`
--

CREATE TABLE IF NOT EXISTS `doctor_details` (
  `doctor_id` varchar(8) NOT NULL,
  `doctor_fname` varchar(10) NOT NULL,
  `doctor_lname` varchar(10) NOT NULL,
  `doctor_sex` varchar(6) NOT NULL,
  `doctor_NID` int(12) NOT NULL,
  `doctor_hphone` int(15) NOT NULL,
  `doctor_mphone` int(15) NOT NULL,
  `doctor_address` varchar(20) NOT NULL,
  `doctor_qualification` varchar(20) NOT NULL,
  `doctor_specialization` varchar(20) NOT NULL,
  `doctor_type` varchar(20) NOT NULL,
  `doctor_vcharge` int(5) NOT NULL,
  `doctor_ccharge` int(5) NOT NULL,
  `doctor_notes` varchar(20) NOT NULL,
  `doctor_basic_sal` int(5) NOT NULL,
  `doctor_status` varchar(5) NOT NULL,
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `doctor_details`
--

INSERT INTO `doctor_details` (`doctor_id`, `doctor_fname`, `doctor_lname`, `doctor_sex`, `doctor_NID`, `doctor_hphone`, `doctor_mphone`, `doctor_address`, `doctor_qualification`, `doctor_specialization`, `doctor_type`, `doctor_vcharge`, `doctor_ccharge`, `doctor_notes`, `doctor_basic_sal`, `doctor_status`) VALUES
('DCID_1', 'Shuo', 'FNEG', 'Male', 123456, 6168888, 1393058, 'hebut', 'qu', 'Cardiologist', 'Permanent', 100, 50, 'no', 3000, 'Y'),
('DCID_2', 'Ha', 'Ha', 'Male', 123456, 123456, 123456, 'hebut', 'qu', 'Dermatologist', 'Permanent', 100, 80, 'no', 2800, 'Y');

-- --------------------------------------------------------

--
-- 表的结构 `doctor_schedule_details`
--

CREATE TABLE IF NOT EXISTS `doctor_schedule_details` (
  `schedule_id` varchar(10) NOT NULL,
  `doctor_id` varchar(10) NOT NULL,
  `doctor_in` varchar(20) NOT NULL,
  `doctor_out` varchar(20) NOT NULL,
  `doctor_avaldate` varchar(100) NOT NULL,
  `schedual_note` varchar(20) NOT NULL,
  `schedule_status` varchar(5) NOT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `fk3` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `doctor_schedule_details`
--

INSERT INTO `doctor_schedule_details` (`schedule_id`, `doctor_id`, `doctor_in`, `doctor_out`, `doctor_avaldate`, `schedual_note`, `schedule_status`) VALUES
('DSID_1', 'DCID_1', '8:15', '12:30', 'Monday Tuesday Thursday ', 'no', 'Y'),
('DSID_2', 'DCID_2', '14:20', '18:0', 'Thursday Friday ', 'no', 'Y');

-- --------------------------------------------------------

--
-- 表的结构 `guardian_details`
--

CREATE TABLE IF NOT EXISTS `guardian_details` (
  `guardian_id` int(11) DEFAULT NULL,
  `guardian_fname` varchar(20) DEFAULT NULL,
  `guardian_lname` varchar(20) DEFAULT NULL,
  `guardian_NIC` varchar(20) DEFAULT NULL,
  `guardian_address` varchar(100) DEFAULT NULL,
  `guardian_phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `hospital_charges`
--

CREATE TABLE IF NOT EXISTS `hospital_charges` (
  `hospital_id` int(11) DEFAULT NULL,
  `hospital_charge` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `discount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `hospital_details`
--

CREATE TABLE IF NOT EXISTS `hospital_details` (
  `hospital_id` int(11) DEFAULT NULL,
  `hospital_name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `talephone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `registraion_number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `inpatients_order_details`
--

CREATE TABLE IF NOT EXISTS `inpatients_order_details` (
  `order_detail_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `date_sold` date DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `inpatient_discharge`
--

CREATE TABLE IF NOT EXISTS `inpatient_discharge` (
  `discharge_id` int(11) DEFAULT NULL,
  `admission_id` int(11) DEFAULT NULL,
  `discharge_date` date DEFAULT NULL,
  `discharge_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `inpatient_services`
--

CREATE TABLE IF NOT EXISTS `inpatient_services` (
  `inpatient_service_id` int(11) DEFAULT NULL,
  `inpatient_id` int(11) DEFAULT NULL,
  `admission_id` int(11) DEFAULT NULL,
  `hospital_service_id` int(11) DEFAULT NULL,
  `bill_date` date DEFAULT NULL,
  `service_date` date DEFAULT NULL,
  `service_time` date DEFAULT NULL,
  `service_charge` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `in_patient_details`
--

CREATE TABLE IF NOT EXISTS `in_patient_details` (
  `patient_id` int(11) DEFAULT NULL,
  `patient_fname` varchar(20) DEFAULT NULL,
  `patient_iname` varchar(20) DEFAULT NULL,
  `patient_dob` date DEFAULT NULL,
  `patient_sex` varchar(20) DEFAULT NULL,
  `patient_NID` varchar(20) DEFAULT NULL,
  `patient_hphone` varchar(20) DEFAULT NULL,
  `patient_mphone` varchar(20) DEFAULT NULL,
  `patient_address` varchar(100) DEFAULT NULL,
  `patient_height` int(11) DEFAULT NULL,
  `patient_weight` int(11) DEFAULT NULL,
  `patient_blood_group` varchar(20) DEFAULT NULL,
  `patient_notes` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `in_petients_order`
--

CREATE TABLE IF NOT EXISTS `in_petients_order` (
  `order_id` int(11) DEFAULT NULL,
  `admission_id` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `login_details`
--

CREATE TABLE IF NOT EXISTS `login_details` (
  `login_id` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `login_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `medicine_category`
--

CREATE TABLE IF NOT EXISTS `medicine_category` (
  `category_id` int(11) DEFAULT NULL,
  `category_name` varchar(20) DEFAULT NULL,
  `category_desc` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `medicine_details`
--

CREATE TABLE IF NOT EXISTS `medicine_details` (
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `units_in_stock` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `recorder_level` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `outpatient_treatment`
--

CREATE TABLE IF NOT EXISTS `outpatient_treatment` (
  `opHistory_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `prescription` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `patient_bill_inpatient`
--

CREATE TABLE IF NOT EXISTS `patient_bill_inpatient` (
  `patient_bill_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `admission_id` int(11) DEFAULT NULL,
  `discharge_date` date DEFAULT NULL,
  `doctor_charges` int(11) DEFAULT NULL,
  `medicine_charges` int(11) DEFAULT NULL,
  `service_charges` int(11) DEFAULT NULL,
  `room_charges` int(11) DEFAULT NULL,
  `hospital_charges` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `net_value` varchar(100) DEFAULT NULL,
  `other_bill_detail` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `patient_bill_payment`
--

CREATE TABLE IF NOT EXISTS `patient_bill_payment` (
  `bill_payment_id` int(11) DEFAULT NULL,
  `patient_bill_id` int(11) DEFAULT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `paid_date` date DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `cash_cheque` varchar(20) DEFAULT NULL,
  `cheque_number` varchar(20) DEFAULT NULL,
  `cheque_date` date DEFAULT NULL,
  `bank` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `patient_details`
--

CREATE TABLE IF NOT EXISTS `patient_details` (
  `patient_id` varchar(8) NOT NULL,
  `firstname` varchar(10) NOT NULL,
  `lastname` varchar(10) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `address` varchar(20) NOT NULL,
  `telephone` int(15) NOT NULL,
  `status` varchar(10) NOT NULL,
  `notes` varchar(20) NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `patient_details`
--

INSERT INTO `patient_details` (`patient_id`, `firstname`, `lastname`, `gender`, `address`, `telephone`, `status`, `notes`) VALUES
('PAID_1', 'ZHANG', 'San', 'male', 'hebut', 133123456, 'Y', 'no');

-- --------------------------------------------------------

--
-- 表的结构 `prescription_details`
--

CREATE TABLE IF NOT EXISTS `prescription_details` (
  `prescription_id` int(11) DEFAULT NULL,
  `medicine_service_id` int(11) DEFAULT NULL,
  `frequency` int(11) DEFAULT NULL,
  `no_of_days` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `purchase_order`
--

CREATE TABLE IF NOT EXISTS `purchase_order` (
  `purchase_order_id` int(11) DEFAULT NULL,
  `purchase_supplier_id` int(11) DEFAULT NULL,
  `purchase_order_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `purchase_order_detail`
--

CREATE TABLE IF NOT EXISTS `purchase_order_detail` (
  `purchase_order_detail_id` int(11) DEFAULT NULL,
  `purchase_order_id` int(11) DEFAULT NULL,
  `purchase_product_id` int(11) DEFAULT NULL,
  `purchase_quantity` varchar(100) DEFAULT NULL,
  `purchase_unit_price` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `net_value` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `room_details`
--

CREATE TABLE IF NOT EXISTS `room_details` (
  `room_id` varchar(11) DEFAULT NULL,
  `room_rtype` varchar(20) DEFAULT NULL,
  `room_desc` varchar(100) DEFAULT NULL,
  `room_status` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `room_details`
--

INSERT INTO `room_details` (`room_id`, `room_rtype`, `room_desc`, `room_status`) VALUES
('RMID_1', 'Single room', 'no..', 'Y'),
('RMID_2', 'Double room', 'double', 'Y'),
('RMID_3', 'Luxurious room', 'luxurious', 'Y'),
('RMID_4', 'triple room', 'triple room', 'Y'),
('RMID_5', 'Double room', 'asd', 'N');

-- --------------------------------------------------------

--
-- 表的结构 `room_type`
--

CREATE TABLE IF NOT EXISTS `room_type` (
  `room_type` varchar(20) DEFAULT NULL,
  `room_rate` int(11) DEFAULT NULL,
  `room_notes` varchar(100) DEFAULT NULL,
  `rtype_status` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `room_type`
--

INSERT INTO `room_type` (`room_type`, `room_rate`, `room_notes`, `rtype_status`) VALUES
('Single room', 150, 'no', 'Y'),
('Double room', 90, 'no', 'N'),
('Luxurious room', 300, 'no', 'Y'),
('triple room', 50, 'no', 'Y'),
('asdd', 120, 'asdffg', 'Y');

-- --------------------------------------------------------

--
-- 表的结构 `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `service_id` varchar(8) NOT NULL,
  `service_name` varchar(20) NOT NULL,
  `duration_of_service` int(5) NOT NULL,
  `charge_for_service` int(5) NOT NULL,
  `service_notes` varchar(20) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `services`
--

INSERT INTO `services` (`service_id`, `service_name`, `duration_of_service`, `charge_for_service`, `service_notes`) VALUES
('SVID_1', 'colonoscopy', 30, 120, 'no');

-- --------------------------------------------------------

--
-- 表的结构 `service_appointment`
--

CREATE TABLE IF NOT EXISTS `service_appointment` (
  `appointment_id` varchar(10) NOT NULL,
  `patient_id` varchar(8) NOT NULL,
  `hospital_service_id` varchar(8) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` varchar(20) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `fk5` (`patient_id`),
  KEY `fk6` (`hospital_service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `service_appointment`
--

INSERT INTO `service_appointment` (`appointment_id`, `patient_id`, `hospital_service_id`, `appointment_date`, `appointment_time`) VALUES
('APID_1', 'PAID_1', 'SVID_1', '2017-11-20', '12:0');

-- --------------------------------------------------------

--
-- 表的结构 `service_appointment_bill`
--

CREATE TABLE IF NOT EXISTS `service_appointment_bill` (
  `appointment_bill_id` varchar(10) NOT NULL,
  `appointment_id` varchar(10) NOT NULL,
  `patient_id` varchar(10) NOT NULL,
  `bill_date` date NOT NULL,
  `appointment_charge` int(5) NOT NULL,
  `hospital_charge` int(5) NOT NULL,
  `grand_total` int(5) NOT NULL,
  `discount` int(5) NOT NULL,
  `net_value` int(5) NOT NULL,
  PRIMARY KEY (`appointment_bill_id`),
  KEY `fk7` (`appointment_id`),
  KEY `fk8` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `service_appointment_bill_payment`
--

CREATE TABLE IF NOT EXISTS `service_appointment_bill_payment` (
  `bill_payment_id` int(11) DEFAULT NULL,
  `appointment_bill_id` int(11) DEFAULT NULL,
  `amount_paid` int(11) DEFAULT NULL,
  `paid_date` date DEFAULT NULL,
  `payment_type` varchar(20) DEFAULT NULL,
  `cheque_no` int(11) DEFAULT NULL,
  `cheque_date` date DEFAULT NULL,
  `bank` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `service_schedule_details`
--

CREATE TABLE IF NOT EXISTS `service_schedule_details` (
  `service_schedule_id` varchar(10) NOT NULL,
  `service_id` varchar(8) NOT NULL,
  `service_start` varchar(20) NOT NULL,
  `service_end` varchar(20) NOT NULL,
  `service_avaldate` varchar(100) NOT NULL,
  `schedual_notes` varchar(20) NOT NULL,
  PRIMARY KEY (`service_schedule_id`),
  KEY `fk4` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `service_schedule_details`
--

INSERT INTO `service_schedule_details` (`service_schedule_id`, `service_id`, `service_start`, `service_end`, `service_avaldate`, `schedual_notes`) VALUES
('SSID_1', 'SVID_1', '8:00', '17:00', 'Monday Tuesday Wednesday', 'no');

-- --------------------------------------------------------

--
-- 表的结构 `suppliers`
--

CREATE TABLE IF NOT EXISTS `suppliers` (
  `supplier_id` int(11) DEFAULT NULL,
  `company_name` varchar(20) DEFAULT NULL,
  `contact_name` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone_no` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `other_details` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `ward_details`
--

CREATE TABLE IF NOT EXISTS `ward_details` (
  `ward_id` int(11) DEFAULT NULL,
  `ward_name` varchar(20) DEFAULT NULL,
  `ward_rate` int(11) DEFAULT NULL,
  `ward_desc` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 限制导出的表
--

--
-- 限制表 `appointment_bill`
--
ALTER TABLE `appointment_bill`
  ADD CONSTRAINT `fk` FOREIGN KEY (`appointment_id`) REFERENCES `doctor_appointment` (`appointment_id`),
  ADD CONSTRAINT `fk1` FOREIGN KEY (`patient_id`) REFERENCES `patient_details` (`patient_id`);

--
-- 限制表 `appointment_bill_payment`
--
ALTER TABLE `appointment_bill_payment`
  ADD CONSTRAINT `fk2` FOREIGN KEY (`appointment_bill_id`) REFERENCES `appointment_bill` (`appointment_bill_id`);

--
-- 限制表 `doctor_appointment`
--
ALTER TABLE `doctor_appointment`
  ADD CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_details` (`doctor_id`),
  ADD CONSTRAINT `fk_doctor_id` FOREIGN KEY (`patient_id`) REFERENCES `patient_details` (`patient_id`);

--
-- 限制表 `doctor_schedule_details`
--
ALTER TABLE `doctor_schedule_details`
  ADD CONSTRAINT `fk3` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_details` (`doctor_id`);

--
-- 限制表 `service_appointment`
--
ALTER TABLE `service_appointment`
  ADD CONSTRAINT `fk5` FOREIGN KEY (`patient_id`) REFERENCES `patient_details` (`patient_id`),
  ADD CONSTRAINT `fk6` FOREIGN KEY (`hospital_service_id`) REFERENCES `services` (`service_id`);

--
-- 限制表 `service_appointment_bill`
--
ALTER TABLE `service_appointment_bill`
  ADD CONSTRAINT `fk7` FOREIGN KEY (`appointment_id`) REFERENCES `service_appointment` (`appointment_id`),
  ADD CONSTRAINT `fk8` FOREIGN KEY (`patient_id`) REFERENCES `patient_details` (`patient_id`);

--
-- 限制表 `service_schedule_details`
--
ALTER TABLE `service_schedule_details`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

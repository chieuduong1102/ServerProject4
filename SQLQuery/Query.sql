-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.20-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for project4
DROP DATABASE IF EXISTS `project4`;
CREATE DATABASE IF NOT EXISTS `project4` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `project4`;

-- Dumping structure for table project4.admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(100) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phonenumber` varchar(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.admin: ~1 rows (approximately)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
REPLACE INTO `admin` (`username`, `fullname`, `email`, `phonenumber`, `password`) VALUES
	('duongph', 'Duong', 'duong@gmail.com', '1234567890', '6116afedcb0bc31083935c1c262ff4c9'),
	('oanhnt', 'Pham Huu Duong', 'chieuduong1102@gmail.com', '0865765102', '6116afedcb0bc31083935c1c262ff4c9');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table project4.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `titleBook` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `manufacture` varchar(255) NOT NULL,
  `publishingCompany` varchar(255) NOT NULL,
  `yearPublish` int(11) DEFAULT NULL,
  `dateSale` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` text DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.book: ~80 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
REPLACE INTO `book` (`bid`, `titleBook`, `author`, `manufacture`, `publishingCompany`, `yearPublish`, `dateSale`, `price`, `description`, `status`) VALUES
	(81, 'Basic Ielts Listening', 'Li Ya Bin', 'NXB Tổng hợp TPHCM', 'NXB Tổng hợp TPHCM', 2018, '2021-09-11', 168000, '0 Đánh giá', 2),
	(82, 'Cẩm Nang Cấu Trúc Tiếng Anh', 'Trang Anh', 'Huy Hoàng  Bookstore', 'NXB Sư Phạm', 2019, '2021-09-11', 98000, '0 Đánh giá', 2),
	(83, 'Động Từ Bất Quy Tắc Và Ngữ Pháp Tiếng Anh Căn Bản', 'Mai Lan Hương', 'Zen Books', 'NXB Đà Nẵng', 2019, '2021-09-11', 15000, '0 đánh giá', 2),
	(84, 'Essential Words For The Toeic 6th', 'Lougheed', 'First News', 'NXB Tổng hơph TPHCM', 2019, '2021-09-11', 238000, '0 Đánh giá', 2),
	(85, 'Little Stories To Get More Knowledge', 'Claire Lương', 'Zen books', 'NXB Đà Nẵng', 2020, '2021-09-10', 65000, '0 Đánh giá', 2),
	(86, 'Little Stories To Make You Smile', 'Claire Lương', 'Zen books', 'NXB Đà Nẵng', 2020, '2021-09-10', 65000, '0 Đánh giá', 2),
	(87, 'Ngữ Pháp Tiếng Anh Dành Cho Học Sinh Vở Bài Tập Và Đáp Án', 'Mai Lan Hương', 'Zen books', 'NXB Đà Nẵng', 2020, '2021-09-10', 90000, '0 danh gia', 2),
	(88, 'Tập Viết Tiếng Hàn Dành Cho Người Mới Bắt Đầu', 'the changmi', 'MCbooks', 'NXB Hồng Đức', 2020, '2021-09-10', 89000, '0 Đánh giá', 2),
	(89, 'Tiếng Nhật Sơ Cấp Cho Mọi Người', 'Minna No Nihongo', 'NXB Trẻ', 'NXB Trẻ', 2021, '2021-09-10', 45000, '0 Đánh giá', 2),
	(90, 'Tự Học 2000 Từ Vựng Tiếng Anh Theo Chủ Đề', 'The Windy', 'MCbooks', 'NXB Đại học Quốc gia Hà Nội', 2021, '2021-09-10', 65000, '0 Đánh giá', 2),
	(91, '22 Quy Luật Bất Biến Trong Marketing', 'Al Ries', 'First News', 'NXB Tổng hợp TPHCM', 2021, '2021-09-09', 78000, '0 Đánh giá', 2),
	(92, '101 Ý Tưởng Marketing Trong Bán Hàng', 'Nguyễn Anh Dũng', 'Marvel', 'NXB Thế Giới', 2021, '2021-09-09', 138000, '0 Đánh giá', 2),
	(93, 'Chiến Lược Influencer Marketing', 'Neal Schaffer', '1980 books', 'NXB Thanh Niên', 2021, '2021-09-09', 149000, '0 Đánh giá', 2),
	(94, 'Hiệu Ứng Chim Mồi', 'hạo nhiên', 'Ecoblader', 'NXB Kinh Tế TPHCM', 2021, '2021-09-09', 75000, '0 Đánh giá', 2),
	(95, 'Marketing Đáng Kinh Ngạc', 'Schaefer', 'Bách Việt', 'NXB Tài Chính', 2020, '2021-09-09', 143000, '0 Đánh giá', 2),
	(96, 'MBA Marketing', 'Yukihiro Makita', '1980 books', 'NXB Dân trí', 2021, '2021-09-09', 169000, '0 Đánh giá', 2),
	(97, 'Nhân Viên Bán Hàng Thành Công', 'Akira Kagata', 'Bách Việt', 'NXB Thanh Niên', 2021, '2021-09-09', 80000, '0 Đánh giá', 2),
	(98, 'Những Đòn Tâm Lý Trong Bán Hàng', 'Brian Tracy', 'Alpha books', 'NXB Lao Động', 2021, '2021-09-09', 129000, '0 Đánh giá', 2),
	(99, 'Phải Yêu Bán Hàng Thì Bạn Mới Có Tiền', 'TS Lê Văn Tư', '1980 books', 'NXB Tổng hợp TPHCM', 2021, '2021-09-09', 129000, '0 danh gia', 2),
	(100, 'Thấy Trước Doanh Thu', 'Aaron Ross', '1980 books', 'NXB Thế Giới', 2021, '2021-09-09', 238000, '0 Đánh giá', 2),
	(101, 'Các Triều Đại Việt Nam', 'Quỳnh Cư', 'Huy Hoàng bookstore', 'NXB Thanh Niên', 2019, '2021-09-09', 98000, '0 Đánh giá', 2),
	(102, 'Đại Việt Sử Ký Toàn Thư Trọn Bộ', 'Lịch sử', 'Nhà sách Minh Thắng', 'NXB Hồng Đức', 2020, '2021-09-09', 235000, '0 Đánh giá', 2),
	(103, 'Lịch Sử Việt Nam Từ Nguồn Gốc Đến Giữa Thế Kỷ XX', 'Lê Thanh Khôi', 'Nhã Nam', 'NXB Thế Giới', 2018, '2021-09-09', 200000, '0 Đánh giá', 2),
	(104, 'Lĩnh Nam Chích Quái', 'Trần Thế Pháp', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 350000, '0 Đánh giá', 2),
	(105, 'Nam Phương Hoàng Hậu Cuối Cùng', 'Lý Nhân Phan', 'Saigon book', 'NXB Thế Giới', 2020, '2021-09-09', 100000, '0 Đánh giá', 2),
	(106, 'Người Bị CIA Cưa Chân Sáu Lần', 'Ma Thiên Đông', 'Saigon book', 'NXB Tổng hợp TPHCM', 2018, '2021-09-09', 95000, '0 Đánh giá', 2),
	(107, 'Người Việt Cao Quý', 'Pazzi', '1980 books', 'NXB Khoa học', 2020, '2021-09-09', 65000, '0 Đánh giá', 2),
	(108, 'Sử Việt 12 Khúc Tráng Ca', 'Dung phan', 'ZGroup', 'NXB Hà Nội', 2019, '2021-09-09', 99000, '0 Đánh giá', 2),
	(109, 'Truyền Kỳ Mạn Lục', 'Nguyễn Dữ', 'Nhã Nam', 'NXB Hội Nhà Văn', 2018, '2021-09-09', 65000, '0 Đánh giá', 2),
	(110, 'Việt Nam Sử Lược', 'Trần Trọng Kim', 'Nhà sách Minh Thắng ', 'NXB Văn học', 2020, '2021-09-09', 175000, '0 Đánh giá', 2),
	(111, '30 Thực Đơn Bữa Ăn Hàng Ngày', 'Nguyễn Cẩm Vân', 'Công ty văn hoá Việt Thư', 'NXB Hồng Đức', 2021, '2021-09-09', 58000, '0 Đánh giá', 2),
	(112, '160 Món Rán Ngon Miệng', 'Nguyễn Viên Chi', 'Tân Việt', 'NXB Hà Nội', 2021, '2021-09-09', 38000, '0 Đánh giá', 2),
	(113, 'Các Món Chay Bổ Dưỡng', 'Thanh Nguyên', 'Phụ Nữ', 'NXB Phụ Nữ', 2020, '2021-09-09', 52000, '0 Đánh giá', 2),
	(114, 'Chế Độ Ăn Bổ Trí Não Giảm Stress', 'Nguyễn Thị Minh Kiều', 'Công ty văn hoá Việt Thư', 'NXB Hồng Đức', 2021, '2021-09-09', 58000, '0 Đánh giá', 2),
	(115, 'Kỹ Thuật Chế Biến Các Món Rau', 'Thanh Huyền', 'Công ty Văn hoá Minh Lâm', 'NXB Hồng Đức', 2021, '2021-09-09', 45000, '0 Đánh giá', 2),
	(116, 'Món Ăn Vị Thuốc', 'Hải An', 'Phụ Nữ', 'NXB Đà Nẵng', 2021, '2021-09-09', 58000, '0 Đánh giá', 2),
	(117, 'Mứt Quả Chữa Bệnh', 'Thuỳ Anh', 'Phụ Nữ', 'NXB Phụ Nữ', 2020, '2021-09-09', 88000, '0 Đánh giá', 2),
	(118, 'Phương Pháp Ăn Uống Cải Thiện Lưu Thông Máu', 'Akiyoshi Horie', 'Thái Hà', 'NXB Công Thương', 2020, '2021-09-09', 89000, '0 Đánh giá', 2),
	(119, 'Siêu Đầu Bếp Nhí', 'Charity Mathews', 'AZ Việt Nam', 'NXB Thế Giới', 2020, '2021-09-09', 155000, '0 Đánh giá', 2),
	(120, 'Trà Sữa Và Hơn Thế Nữa', 'Bin Chen', 'AZ Việt Nam', 'NXB Thế Giới', 2021, '2021-09-09', 179000, '0 Đánh giá', 2),
	(121, 'Ba Người Thầy Vĩ Đại', 'Robin Sharma', 'Thái Hà', 'NXB Lao Động', 2019, '2021-09-09', 95000, '0 Đánh giá', 2),
	(122, 'Đắc Nhân Tâm', 'Dale Carnegie', 'First News', 'NXB Tổng hợp TPHCM', 2021, '2021-09-09', 86000, '0 Đánh giá', 2),
	(123, 'Đọc Vị Bất Kỳ Ai', 'David Lieberman', 'Thái Hà', 'NXB Lao Động', 2019, '2021-09-09', 79000, '0 Đánh giá', 2),
	(124, 'Hiểu Về Trái Tim', 'Minh Niêm', 'First News', 'NXB Tổng Hợp TPHCM', 2019, '2021-09-09', 138000, '0 Đánh giá', 2),
	(125, 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ', 'Trác Nhã', 'Ming Long', 'NXB Văn Học', 2021, '2021-09-09', 110000, '0 Đánh giá', 2),
	(126, 'Người Giàu Có Nhất Thành Babylon', 'Clason', 'First News', 'NXB Tổng Hợp TPHCM', 2020, '2021-09-09', 98000, '0 Đánh giá', 2),
	(127, 'Người Nam Châm', 'Jack Canfield', 'Thái Hà', 'NXB Công Thương', 2019, '2021-09-09', 65000, '0 Đánh giá', 2),
	(128, 'Rèn Luyện Tư Duy Phản Biện', 'Albert', '1980 books', 'NXB Phụ Nữ', 2020, '2021-09-09', 99000, '0 Đánh giá', 2),
	(129, 'Thay Đổi Cuộc Sống Với Nhân Số Học', 'Lê Đỗ Quỳnh Hương', 'First News', 'NXB Tổng Hợp TPHCM', 2021, '2021-09-09', 248000, '0 Đánh giá', 2),
	(130, 'Trên Đường Băng', 'Tony', 'NXB Trẻ', 'NXB Trẻ', 2017, '2021-09-09', 80000, '0 Đánh giá', 2),
	(131, 'Arduino Và Lập Trình lot', 'Nguyễn Tất Bảo Thiên', 'NXB Thanh Niên', 'NXB Thanh Niên', 2020, '2021-09-09', 162000, '0 Đánh giá', 2),
	(132, 'Giáo Trình Lập Trình C Căn Bản và Nâng Cao', 'Phạm Văn Ất', 'Huy Hoàng bookstore', 'NXB Thông Tin', 2020, '2021-09-09', 135000, '0 Đánh giá', 2),
	(133, 'Hacker Lược Sử', 'Steven Levy', 'Alpha books', 'NXB Công Thương', 2019, '2021-09-09', 299000, '0 Đánh giá', 2),
	(134, 'Hành Trang Lập Trình Những Kỹ Năng Lập Trình Viên Chuyên Nghiệp Cần Có', 'Vũ Công Tấn Tài', 'NXB Thanh Niên', 'NXB Thanh Niên', 2020, '2021-09-09', 179000, '0 Đánh giá', 2),
	(135, 'Kiểm Thử Phần Mềm Từng Bước Trở Thành Tester Chuyên Nghiệp', 'Nguyễn Thị Kiêm Ái', 'NXB Thanh Niên', 'NXB Thanh Niên', 2021, '2021-09-09', 65000, '0 Đánh giá', 2),
	(136, 'Lập Trình Windows Form Và Web Form Với C#', 'Nguyễn Tất Bảo Thiên', 'NXB Thanh Niên', 'NXB Thanh Niên', 2021, '2021-09-09', 150000, '0 Đánh giá', 2),
	(137, 'Microsoft Office Excel 2013', 'IIG Việt Nam', 'NXB Tổng Hợp TPHCM', 'NXB Tổng Hợp TPHCM', 2017, '2021-09-09', 89000, '0 Đánh giá', 2),
	(138, 'Microsoft Office Excel 2016', 'IIG Việt Nam', 'NXB Tổng Hợp TPHCM', 'NXB Tổng Hợp TPHCM', 2017, '2021-09-09', 89000, '0 Đánh giá', 2),
	(139, 'Microsoft Office Powerpoint 2013', 'IIG Việt Nam', 'NXB Tổng Hợp TPHCM', 'NXB Tổng Hợp TPHCM', 2017, '2021-09-09', 55000, '0 Đánh giá', 2),
	(140, 'Thiết Kế Slide Theo Phong Cách Thiền', 'Garr Reynolds', 'Alpha books', 'NXB Thế Giới', 2019, '2021-09-09', 239000, '0 Đánh giá', 2),
	(141, '10 Vạn Câu Hỏi Vì Sao Các Hiện Tượng Thiên Nhiên Kỳ Thú', 'Tôn Nguyên Vĩ', 'Đinh Ti', 'NXB Thanh Niên', 2018, '2021-09-09', 55000, '0 Đánh giá', 2),
	(142, 'Danh Nhân Thế Giới Anhxtanh', 'Han Kiên', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 30000, '0 Đánh giá', 2),
	(143, 'Danh Nhân Thế Giới Êđixơn', 'Han Kiên', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 30000, '0 Đánh giá', 2),
	(144, 'Danh Nhân Thế Giới LinCôn', 'Han Kiên', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 30000, '0 Đánh giá', 2),
	(145, 'Danh Nhân Thế Giới NiuTơn', 'Han Kiên', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 30000, '0 Đánh giá', 2),
	(146, 'Danh Nhân Thế Giới SuTơ', 'Han Kiên', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 30000, '0 Đánh giá', 2),
	(147, 'Mười Vạn Câu Hỏi Vì Sao', 'Nhiều Tác Giả', 'Đông Á', 'NXB Dân Trí', 2020, '2021-09-09', 280000, '0 Đánh giá', 2),
	(148, 'Vòng Quanh Thế Giới Hà Lan', 'Nguyễn Hào', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 12000, '0 Đánh giá', 2),
	(149, 'Vòng Quanh Thế Giới Hàn Quốc', 'Nguyễn Hào', 'NXB Kim Đồng', 'NXB Kim Đồng', 2019, '2021-09-09', 12000, '0 Đánh giá', 2),
	(150, 'Who Truyện Kể Về Danh Nhân Thế Giới Henry David Thoreau', 'Chungbe studio', 'NXB Kim Đồng ', 'NXB Kim Đồng', 2020, '2021-09-09', 55000, '0 Đánh Giá', 2),
	(151, 'Bước Chậm Lại Giữa Thế Gian Vội Vã', 'Hae Min', 'Nhã Nam', 'NXB Hội Nhà Văn', 2018, '2021-09-09', 85000, 'Sách quá hay và ý nghĩa', 2),
	(152, 'Chiến Binh Cầu Vồng', 'Andrea Hirata', 'Nhã Nam', 'NXB Hội Nhà Văn', 2020, '2021-09-09', 109000, '1 cuốn sách tuyệt vời', 2),
	(153, 'Cô Gái Đến Từ Hôm Qua', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'NXB Trẻ', 2019, '2021-09-09', 80000, '0 Đánh giá', 2),
	(154, 'Dấu Chân Trên Cát', 'Nguyên Phong', 'First News', 'NXB Tổng Hợp TPHCM', 2020, '2021-09-09', 148000, '0 Đánh giá', 2),
	(155, 'Đứa Con Gái Hoang Đàng', 'Jeffrey Archer', 'Bách Việt', 'NXB Lao Động', 2020, '2021-09-09', 185000, '0 Đánh giá', 2),
	(156, 'Mắt Biếc', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'NXB Trẻ', 2019, '2021-09-09', 110000, '0 Đánh giá', 2),
	(157, 'Ngàn Mặt Trời Rực Rỡ', 'Khaled Hosseini', 'Nhã Nam', 'NXB Văn Học', 2019, '2021-09-09', 130000, '0 Đánh giá', 2),
	(158, 'Ngày Xưa Có Một Chuyện Tình ', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'NXB Trẻ', 2019, '2021-09-09', 125000, '0 Đánh giá', 2),
	(159, 'Nhà Giả Kim', 'Paulo Coelho', 'Nhã Nam', 'NXB Hội Nhà Văn', 2020, '2021-09-09', 79000, '0 Đánh giá', 2),
	(160, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'NXB Trẻ', 2018, '2021-09-09', 125000, '0 Đánh giá', 2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table project4.bookcategory
DROP TABLE IF EXISTS `bookcategory`;
CREATE TABLE IF NOT EXISTS `bookcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `bid` (`bid`),
  CONSTRAINT `bookcategory_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`),
  CONSTRAINT `bookcategory_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.bookcategory: ~80 rows (approximately)
/*!40000 ALTER TABLE `bookcategory` DISABLE KEYS */;
REPLACE INTO `bookcategory` (`id`, `cid`, `bid`) VALUES
	(81, 1, 91),
	(82, 1, 92),
	(83, 1, 93),
	(84, 1, 94),
	(85, 1, 95),
	(86, 1, 96),
	(87, 1, 97),
	(88, 1, 98),
	(89, 1, 99),
	(90, 1, 100),
	(91, 2, 101),
	(92, 2, 102),
	(93, 2, 103),
	(94, 2, 104),
	(95, 2, 105),
	(96, 2, 106),
	(97, 2, 107),
	(98, 2, 108),
	(99, 2, 109),
	(100, 2, 110),
	(101, 3, 111),
	(102, 3, 112),
	(103, 3, 113),
	(104, 3, 114),
	(105, 3, 115),
	(106, 3, 116),
	(107, 3, 117),
	(108, 3, 118),
	(109, 3, 119),
	(110, 3, 120),
	(111, 4, 81),
	(112, 4, 82),
	(113, 4, 83),
	(114, 4, 84),
	(115, 4, 85),
	(116, 4, 86),
	(117, 4, 87),
	(118, 4, 88),
	(119, 4, 89),
	(120, 4, 90),
	(121, 5, 121),
	(122, 5, 122),
	(123, 5, 123),
	(124, 5, 124),
	(125, 5, 125),
	(126, 5, 126),
	(127, 5, 127),
	(128, 5, 128),
	(129, 5, 129),
	(130, 5, 130),
	(131, 6, 151),
	(132, 6, 152),
	(133, 6, 153),
	(134, 6, 154),
	(135, 6, 155),
	(136, 6, 156),
	(137, 6, 157),
	(138, 6, 158),
	(139, 6, 159),
	(140, 6, 160),
	(141, 7, 131),
	(142, 7, 132),
	(143, 7, 133),
	(144, 7, 134),
	(145, 7, 135),
	(146, 7, 136),
	(147, 7, 137),
	(148, 7, 138),
	(149, 7, 139),
	(150, 7, 140),
	(151, 8, 141),
	(152, 8, 142),
	(153, 8, 143),
	(154, 8, 144),
	(155, 8, 145),
	(156, 8, 146),
	(157, 8, 147),
	(158, 8, 148),
	(159, 8, 149),
	(160, 8, 150);
/*!40000 ALTER TABLE `bookcategory` ENABLE KEYS */;

-- Dumping structure for table project4.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(100) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.category: ~8 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`cid`, `categoryName`) VALUES
	(1, 'Sách kinh tế'),
	(2, 'Sách lịch sử xã hội'),
	(3, 'Sách chăm sóc gia đình'),
	(4, 'Sách ngoại ngữ'),
	(5, 'Sách kỹ năng sống'),
	(6, 'Tiểu thuyệt'),
	(7, 'Sách công nghệ'),
	(8, 'Truyện thiếu nhi');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table project4.image
DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) NOT NULL,
  `nameFile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iid`),
  KEY `bid` (`bid`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=484 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.image: ~239 rows (approximately)
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
REPLACE INTO `image` (`iid`, `bid`, `nameFile`) VALUES
	(245, 81, 'bil1.jpg'),
	(246, 81, 'bil2.jpg'),
	(247, 81, 'bil3.jpg'),
	(248, 82, 'cnctta1.jpg'),
	(249, 82, 'cnctta2.jpg'),
	(250, 82, 'cnctta3.jpg'),
	(251, 83, 'dtbqt&nptacb1.jpg'),
	(252, 83, 'dtbqt&nptacb2.jpg'),
	(253, 83, 'dtbqt&nptacb3.jpg'),
	(254, 84, 'ewftt5.jpg'),
	(255, 84, 'ewftt4.jpg'),
	(256, 84, 'ewftt3.jpg'),
	(257, 85, 'lstgmk1.jpg'),
	(258, 85, 'lstgmk2.jpg'),
	(259, 85, 'lstgmk3.jpg'),
	(260, 86, 'lstmys1.jpg'),
	(261, 86, 'lstmys2.jpg'),
	(262, 86, 'lstmys3.jpg'),
	(263, 87, 'nptadchs1.jpg'),
	(264, 87, 'nptadchs2.jpg'),
	(265, 87, 'nptadchs3.jpg'),
	(266, 88, 'tvthdcnmbd1.jpg'),
	(267, 88, 'tvthdcnmbd2.jpg'),
	(268, 88, 'tvthdcnmbd3.jpg'),
	(269, 89, 'tncmn1.jpg'),
	(270, 89, 'tncmn2.jpg'),
	(271, 89, 'tncmn3.jpg'),
	(272, 90, 'th2000tvtatcd1.jpg'),
	(273, 90, 'th2000tvtatcd2.jpg'),
	(274, 90, 'th2000tvtatcd3.jpg'),
	(275, 91, '22qlbbtm1.jpg'),
	(276, 91, '22qlbbtm2.jpg'),
	(277, 91, '22qlbbtm3.jpg'),
	(278, 92, '101ytmtbh1.jpg'),
	(279, 92, '101ytmtbh2.jpg'),
	(280, 92, '101ytmtbh3.jpg'),
	(281, 93, 'clim1.jpg'),
	(282, 93, 'clim2.jpg'),
	(283, 93, 'clim3.jpg'),
	(284, 94, 'hucm1.jpg'),
	(285, 94, 'hucm2.jpg'),
	(286, 94, 'hucm3.jpg'),
	(287, 95, 'mdkn1.jpg'),
	(288, 95, 'mdkn2.jpg'),
	(289, 95, 'mdkn3.jpg'),
	(290, 96, 'mbam1.jpg'),
	(291, 96, 'mbam2.jpg'),
	(292, 96, 'mbam3.jpg'),
	(293, 97, 'nvbhtc1.jpg'),
	(294, 97, 'nvbhtc2.jpg'),
	(295, 97, 'nvbhtc3.jpg'),
	(296, 98, 'ndtltbh1.jpg'),
	(297, 98, 'ndtltbh2.jpg'),
	(298, 98, 'ndtltbh3.jpg'),
	(299, 99, 'pybhtbmct1.jpg'),
	(300, 99, 'pybhtbmct2.jpg'),
	(301, 99, 'pybhtbmct3.jpg'),
	(302, 100, 'ttdt1.jpg'),
	(303, 100, 'ttdt2.jpg'),
	(304, 100, 'ttdt3.jpg'),
	(305, 101, 'ctdvn1.jpg'),
	(306, 101, 'ctdvn2.jpg'),
	(307, 101, 'ctdvn3.jpg'),
	(308, 102, 'dvsktttt1.jpg'),
	(309, 102, 'dvsktttt2.jpg'),
	(310, 102, 'dvsktttt3.jpg'),
	(311, 103, 'lsvntngdgtkxx1.jpg'),
	(312, 103, 'lsvntngdgtkxx2.jpg'),
	(313, 103, 'lsvntngdgtkxx3.jpg'),
	(314, 104, 'lncq1.jpg'),
	(315, 104, 'lncq2.jpg'),
	(316, 104, 'lncq3.jpg'),
	(317, 105, 'nphhcc1.jpg'),
	(318, 105, 'nphhcc2.jpg'),
	(319, 105, 'nphhcc3.jpg'),
	(320, 106, 'nbciaccsl1.jpg'),
	(321, 106, 'nbciaccsl2.jpg'),
	(322, 106, 'nbciaccsl3.jpg'),
	(323, 107, 'nvcq1.jpg'),
	(324, 107, 'nvcq2.jpg'),
	(325, 107, 'nvcq3.jpg'),
	(326, 108, 'sv12ktc1.jpg'),
	(327, 108, 'sv12ktc2.jpg'),
	(328, 108, 'sv12ktc3.jpg'),
	(329, 109, 'tkml1.jpg'),
	(330, 109, 'tkml2.jpg'),
	(331, 109, 'tkml3.jpg'),
	(332, 110, 'vnsl1.jpg'),
	(333, 110, 'vnsl2.jpg'),
	(334, 110, 'vnsl3.jpg'),
	(335, 111, '30tdbahn1.jpg'),
	(336, 111, '30tdbahn2.jpg'),
	(337, 111, '30tdbahn3.jpg'),
	(338, 112, '160mrnm1.jpg'),
	(339, 112, '160mrnm2.jpg'),
	(340, 112, '160mrnm3.jpg'),
	(341, 113, 'cmcbd1.jpg'),
	(342, 113, 'cmcbd2.jpg'),
	(343, 113, 'cmcbd3.jpg'),
	(344, 114, 'cdabtngs1.jpg'),
	(345, 114, 'cdabtngs2.jpg'),
	(346, 114, 'cdabtngs3.jpg'),
	(347, 115, 'ktcbcmr1.jpg'),
	(348, 115, 'ktcbcmr2.jpg'),
	(349, 115, 'ktcbcmr3.jpg'),
	(350, 116, 'mavt1.jpg'),
	(351, 116, 'mavt2.jpg'),
	(352, 116, 'mavt3.jpg'),
	(353, 117, 'mqcb1.jpg'),
	(354, 117, 'mqcb2.jpg'),
	(355, 117, 'mqcb3.jpg'),
	(356, 118, 'ppauctltm1.jpg'),
	(357, 118, 'ppauctltm2.jpg'),
	(358, 118, 'ppauctltm3.jpg'),
	(359, 119, 'sdbn1.jpg'),
	(360, 119, 'sdbn2.jpg'),
	(361, 119, 'sdbn3.jpg'),
	(362, 120, 'tsvhtn1.jpg'),
	(363, 120, 'tsvhtn2.jpg'),
	(364, 120, 'tsvhtn3.jpg'),
	(365, 121, 'bntvd4.jpg'),
	(366, 121, 'bntvd1.jpg'),
	(367, 121, 'bntvd2.jpg'),
	(368, 122, 'dnt1.jpg'),
	(369, 122, 'dnt2.jpg'),
	(370, 122, 'dnt3.jpg'),
	(371, 123, 'dvbka1.jpg'),
	(372, 123, 'dvbka2.jpg'),
	(373, 123, 'dvbka3.jpg'),
	(374, 124, 'hvtt1.jpg'),
	(375, 124, 'hvtt2.jpg'),
	(376, 124, 'hvtt3.jpg'),
	(377, 125, 'kanscdth1.jpg'),
	(378, 125, 'kanscdth2.jpg'),
	(379, 125, 'kanscdth3.jpg'),
	(380, 126, 'ngcntb1.jpg'),
	(381, 126, 'ngcntb2.jpg'),
	(382, 126, 'ngcntb3.jpg'),
	(383, 127, 'nnc1.jpg'),
	(384, 127, 'nnc2.jpg'),
	(385, 127, 'nnc3.jpg'),
	(386, 128, 'rltdpb1.jpg'),
	(387, 128, 'rltdpb2.jpg'),
	(388, 128, 'rltdpb3.jpg'),
	(389, 129, 'tdcsvnsh2.jpg'),
	(390, 129, 'tdcsvnsh1.jpg'),
	(391, 129, 'tdcsvnsh3.jpg'),
	(392, 130, 'tdb1.jpg'),
	(393, 130, 'tdb2.jpg'),
	(394, 130, 'tdb3.jpg'),
	(395, 131, 'avlti1.jpg'),
	(396, 131, 'avlti2.jpg'),
	(397, 131, 'avlti3.jpg'),
	(398, 132, 'gtktltccbnc1.jpg'),
	(399, 132, 'gtktltccbnc2.jpg'),
	(400, 132, 'gtktltccbnc3.jpg'),
	(401, 133, 'hls1.jpg'),
	(402, 133, 'hls2.jpg'),
	(403, 133, 'hls3.jpg'),
	(404, 134, 'htltnknltvcncc1.jpg'),
	(405, 134, 'htltnknltvcncc2.jpg'),
	(406, 134, 'htltnknltvcncc3.jpg'),
	(407, 135, 'ktpmtbtttcn1.jpg'),
	(408, 135, 'ktpmtbtttcn2.jpg'),
	(409, 135, 'ktpmtbtttcn3.jpg'),
	(410, 136, 'ltwfvwfvc#1.jpg'),
	(411, 136, 'ltwfvwfvc#2.jpg'),
	(412, 136, 'ltwfvwfvc#3.jpg'),
	(413, 137, 'moe2013-1.jpg'),
	(414, 137, 'moe2013-2.jpg'),
	(415, 137, 'moe2013-3.jpg'),
	(416, 138, 'moe2016-1.jpg'),
	(417, 138, 'moe2016-2.jpg'),
	(418, 138, 'moe2016-3.jpg'),
	(419, 139, 'mop2013-1.jpg'),
	(420, 139, 'mop2013-2.jpg'),
	(421, 139, 'mop2013-3.jpg'),
	(422, 140, 'tkstpct1.jpg'),
	(423, 140, 'tkstpct2.jpg'),
	(424, 140, 'tkstpct3.jpg'),
	(425, 141, '10vchvschttnkt1.jpg'),
	(426, 141, '10vchvschttnkt2.jpg'),
	(427, 141, '10vchvschttnkt3.jpg'),
	(428, 142, 'dntga1.jpg'),
	(429, 142, 'dntga2.jpg'),
	(430, 142, 'dntga3.jpg'),
	(431, 143, 'dntge1.jpg'),
	(432, 143, 'dntge2.jpg'),
	(433, 143, 'dntge3.jpg'),
	(434, 144, 'dntgl1.jpg'),
	(435, 144, 'dntgl2.jpg'),
	(436, 144, 'dntgl3.jpg'),
	(437, 145, 'dntgn1.jpg'),
	(438, 145, 'dntgn2.jpg'),
	(439, 145, 'dntgn3.jpg'),
	(440, 146, 'dntgs1.jpg'),
	(441, 146, 'dntgs2.jpg'),
	(442, 146, 'dntgs3.jpg'),
	(443, 147, 'mvchvs1.jpg'),
	(444, 147, 'mvchvs2.jpg'),
	(445, 147, 'mvchvs3.jpg'),
	(446, 148, 'vqtghl1.jpg'),
	(447, 148, 'vqtghl2.jpg'),
	(448, 148, 'vqtghl3.jpg'),
	(449, 149, 'vqtghq1.jpg'),
	(450, 149, 'vqtghq2.jpg'),
	(451, 149, 'vqtghq3.jpg'),
	(452, 150, 'wckvdntghdt1.jpg'),
	(453, 150, 'wckvdntghdt2.jpg'),
	(454, 150, 'wckvdntghdt3.jpg'),
	(455, 151, 'bclgtgvv1.jpg'),
	(456, 151, 'bclgtgvv2.jpg'),
	(457, 151, 'bclgtgvv3.jpg'),
	(458, 152, 'cbcv1.jpg'),
	(459, 152, 'cbcv2.jpg'),
	(460, 152, 'cbcv3.jpg'),
	(461, 153, 'cgdthq1.jpg'),
	(462, 153, 'cgdthq2.jpg'),
	(463, 153, 'cgdthq3.jpg'),
	(464, 154, 'dctc1.jpg'),
	(465, 154, 'dctc2.jpg'),
	(466, 154, 'dctc3.jpg'),
	(467, 155, 'dcghd1.jpg'),
	(468, 155, 'dcghd2.jpg'),
	(469, 155, 'dcghd3.jpg'),
	(470, 156, 'mb1.jpg'),
	(471, 156, 'mb2.jpg'),
	(472, 156, 'mb3.jpg'),
	(473, 157, 'nmtrr1.jpg'),
	(474, 157, 'nmtrr2.jpg'),
	(475, 157, 'nmtrr3.jpg'),
	(476, 158, 'nxcmct1.jpg'),
	(477, 158, 'nxcmct2.jpg'),
	(478, 158, 'nxcmct3.jpg'),
	(479, 159, 'ngk1.jpg'),
	(480, 159, 'ngk2.jpg'),
	(481, 160, 'tthvtcx1.jpg'),
	(482, 160, 'tthvtcx2.jpg'),
	(483, 160, 'tthvtcx3.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;

-- Dumping structure for table project4.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `timeOrder` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `deliveryAddress` varchar(500) NOT NULL,
  `totalPrice` double NOT NULL,
  `note` text DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `username` (`username`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.order: ~10 rows (approximately)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
REPLACE INTO `order` (`oid`, `timeOrder`, `username`, `deliveryAddress`, `totalPrice`, `note`, `status`) VALUES
	(1, '2021-09-11', 'duongph1', 'HN', 1008000, 'ABC', 0),
	(2, '2021-09-12', 'duongph1', 'HN', 264000, 'ABC', -1),
	(3, '2021-09-12', 'duongph123', 'HN', 220000, NULL, 0),
	(4, '2021-9-19', 'duongph123', 'Ha Noi', 281000, '123', 0),
	(5, '2021-9-19', 'duongph123', 'Thai Binh', 260000, '11111111', 0),
	(6, '2021-9-19', 'duongph123', 'Ha Noi', 445000, '123', 0),
	(7, '2021-9-19', 'duongph123', 'Ha Noi', 135000, '123', 0),
	(8, '2021-9-19', 'duongph123', 'HN', 135000, '123', 0),
	(9, '19', 'duongph123', 'HN0', 390000, '123', 0),
	(10, '19-09-2021 18:04', 'duongph123', 'HN1', 260000, '123', 0),
	(11, '2021-09-20 18:54', 'duongph123', 'Đống Đa, Hà Nội', 356000, 'AAA', 1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for table project4.orderdetail
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE IF NOT EXISTS `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `oid` (`oid`),
  KEY `bid` (`bid`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.orderdetail: ~14 rows (approximately)
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
REPLACE INTO `orderdetail` (`id`, `oid`, `bid`, `amount`) VALUES
	(1, 1, 92, 1),
	(2, 1, 81, 1),
	(9, 2, 81, 2),
	(14, 1, 82, 1),
	(16, 2, 83, 1),
	(17, 2, 83, 1),
	(18, 2, 91, 3),
	(19, 3, 125, 2),
	(20, 8, 84, 1),
	(21, 8, 88, 1),
	(22, 9, 106, 1),
	(23, 9, 105, 1),
	(24, 10, 86, 1),
	(25, 10, 85, 1),
	(26, 11, 88, 2);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;

-- Dumping structure for table project4.ratingfeedback
DROP TABLE IF EXISTS `ratingfeedback`;
CREATE TABLE IF NOT EXISTS `ratingfeedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `bid` int(11) NOT NULL,
  `scoreRate` int(11) DEFAULT NULL,
  `feedback` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `bid` (`bid`),
  CONSTRAINT `ratingfeedback_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `ratingfeedback_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.ratingfeedback: ~8 rows (approximately)
/*!40000 ALTER TABLE `ratingfeedback` DISABLE KEYS */;
REPLACE INTO `ratingfeedback` (`id`, `username`, `bid`, `scoreRate`, `feedback`) VALUES
	(1, 'duongph123', 82, 4, 'A book review is a form of literary criticism in which a book is merely described or analyzed based on content, style, and merit. A book review may be a primary source, opinion piece, summary review or scholarly review.'),
	(2, 'duongph1', 82, 4, 'Sách OK'),
	(3, 'duongph111', 82, 3, 'Sách OK'),
	(4, 'duongph111', 84, 3, 'Sách OK'),
	(5, 'duongph1', 84, 4, 'Sách OK'),
	(6, 'duongph123', 84, 4, 'Sách OK'),
	(7, 'duongph111', 82, 2, 'Sách OK'),
	(8, 'duongph111', 82, 5, 'Sách OK'),
	(9, 'duongph111', 82, 4, 'Sách hay');
/*!40000 ALTER TABLE `ratingfeedback` ENABLE KEYS */;

-- Dumping structure for table project4.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(100) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phonenumber` varchar(11) NOT NULL,
  `address` varchar(500) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table project4.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`username`, `fullname`, `email`, `phonenumber`, `address`, `password`) VALUES
	('duongph1', 'Pham Huu Duong', 'chieuduong1102@gmail.com', '0865765102', 'ngach 80C, ngo Van Huong,phuong Hang  Bot,quan Dong Da, Ha Noi', '6116afedcb0bc31083935c1c262ff4c9'),
	('duongph111', 'Pham Huu Duong', 'chieuduong1102@gmail.com', '0865765102', 'ngach 80C, ngo Van Huong,phuong Hang  Bot,quan Dong Da, Ha Noi', 'b3bf6901b91d01ec65eb3848bef053d0'),
	('duongph123', 'Pham Huu Duong', 'chieuduong1102@gmail.com', '0865765102', 'ngach 80C, ngo Van Huong,phuong Hang  Bot,quan Dong Da, Ha Noi', '6116afedcb0bc31083935c1c262ff4c9');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for trigger project4.orderdetail_after_insert
DROP TRIGGER IF EXISTS `orderdetail_after_insert`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `orderdetail_after_insert` AFTER INSERT ON `orderdetail` FOR EACH ROW BEGIN
	DECLARE order_id INTEGER;
-- 	DECLARE total DOUBLE;
	DECLARE product_price double;
	SET order_id = NEW.oid;
-- 	SELECT totalPrice FROM order INTO total;
	SELECT NEW.amount * price from orderdetail JOIN book on orderdetail.bid = book.bid WHERE book.bid = NEW.bid LIMIT 1 INTO product_price;
	UPDATE `order` SET totalPrice = IFNULL(totalPrice,0) + product_price WHERE oid = order_id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

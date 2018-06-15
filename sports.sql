SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `sports` (
  `pname` text NOT NULL,
  `runs` text NOT NULL,
  `wickets` text NOT NULL,
  `catches` text NOT NULL,
  `tstamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `sports` (`pname`, `runs`, `wickets`, `catches`, `tstamp`) VALUES
('Rahul Dravid', '25000', '27', '324', '2014-04-16 14:21:39'),
('pasha', '5000', '50', '27', '2014-04-16 14:21:39'),
('zimmy', '2500', '20', '14', '2014-04-16 14:21:39'),
('mamba', '500', '69', '3', '2014-04-16 14:21:39'),
('SRT', '20000', '200', '200', '2014-04-16 14:21:39');


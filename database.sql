-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 05 Gru 2016, 08:23
-- Wersja serwera: 10.1.19-MariaDB
-- Wersja PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `database`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `project`
--

CREATE TABLE `project` (
  `id_project` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `time_from` date DEFAULT NULL,
  `time_to` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `project`
--

INSERT INTO `project` (`id_project`, `name`, `time_from`, `time_to`) VALUES
(1, 'Modelowanie dyskretne', '2016-11-09', '2016-11-24'),
(3, 'Modelowanie dyskretne3', '2016-11-09', '2016-11-24');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `data_create_account` datetime DEFAULT NULL,
  `data_modification_account` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id_user`, `firstname`, `surname`, `email`, `data_create_account`, `data_modification_account`, `status`) VALUES
(3, '"Jacek"', '"Placek"', '"jacek@placek.com"', '2016-11-30 08:57:11', NULL, 1),
(4, 'Maciek', NULL, NULL, '2016-11-30 10:09:57', NULL, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_project`
--

CREATE TABLE `user_project` (
  `projects_id_project` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `user_project`
--

INSERT INTO `user_project` (`projects_id_project`, `users_id_user`, `date_from`, `date_to`) VALUES
(1, 4, '2016-11-01', '2016-11-23'),
(3, 4, '2016-11-01', '2016-11-18');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id_project`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `user_project`
--
ALTER TABLE `user_project`
  ADD PRIMARY KEY (`projects_id_project`,`users_id_user`),
  ADD KEY `fk_projects_has_users_users1_idx` (`users_id_user`),
  ADD KEY `fk_projects_has_users_projects_idx` (`projects_id_project`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `project`
--
ALTER TABLE `project`
  MODIFY `id_project` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `user_project`
--
ALTER TABLE `user_project`
  ADD CONSTRAINT `fk_projects_has_users_projects` FOREIGN KEY (`projects_id_project`) REFERENCES `project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_projects_has_users_users1` FOREIGN KEY (`users_id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

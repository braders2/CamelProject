--
-- Baza danych: `database`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contact_person` varchar(50) NOT NULL,
  `contact_email` varchar(40) NOT NULL,
  `id_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `customer`
--

INSERT INTO `customer` (`id`, `name`, `date_created`, `contact_person`, `contact_email`, `id_status`) VALUES
(1, 'Korporacja', '2016-12-05 08:59:35', 'Jan Kowalski', 'jan.kowalski@korporacja.com.pl', 1),
(2, 'Wielka Korporacja', '2016-12-05 09:00:49', 'Andrzej Nowak', 'andrzej.nowak@wielkakorporacja.com', 1),
(3, 'Jakas Firma', '2016-12-05 09:06:53', 'Jan Janusz Janowski', 'janek@firma.com.pl', 3),
(4, 'Inna Firma Sp. z o.o.', '2016-12-05 09:07:03', 'Mariusz Mariuszowski', 'mariusz.mariuszowski@mail.com.pl', 3),
(5, 'Upadla Firma', '2016-12-05 09:14:05', 'Henryk Upadly', 'henryk@upadly.com.pl', 4),
(6, 'Firma Zwykla', '2016-12-05 09:14:18', 'Bronislaw Zwykly', 'bronek.zwykly@bronek.com.pl', 4),
(7, 'Planowana Firma', NULL, 'Marek Planowany', 'marek.plan@nowafirma.com.pl', 2),
(8, 'Firma Przyszlego Klienta', NULL, 'Jozef Przyszly', 'jozefprzyszly@przyszly.com', 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `customer_status`
--

CREATE TABLE `customer_status` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sort_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `customer_status`
--

INSERT INTO `customer_status` (`id`, `name`, `sort_order`) VALUES
(1, 'important', 0),
(2, 'planned', 1),
(3, 'new', 2),
(4, 'ended', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `project`
--

CREATE TABLE `project` (
  `id_project` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `time_from` date DEFAULT NULL,
  `time_to` date DEFAULT NULL,
  `id_customer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `project`
--

INSERT INTO `project` (`id_project`, `name`, `time_from`, `time_to`, `id_customer`) VALUES
(1, 'Modelowanie dyskretne', '2016-11-09', '2016-11-24', 0),
(3, 'Modelowanie dyskretne3', '2016-11-09', '2016-11-24', 0);

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
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_status` (`id_status`);

--
-- Indexes for table `customer_status`
--
ALTER TABLE `customer_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`id_project`),
  ADD KEY `id_customer` (`id_customer`);

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
-- AUTO_INCREMENT dla tabeli `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT dla tabeli `customer_status`
--
ALTER TABLE `customer_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
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

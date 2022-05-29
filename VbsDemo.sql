-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:8889
-- Létrehozás ideje: 2022. Máj 29. 19:42
-- Kiszolgáló verziója: 5.7.34
-- PHP verzió: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `VbsDemo`
--
CREATE DATABASE IF NOT EXISTS `VbsDemo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `VbsDemo`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `Book`
--

CREATE TABLE `Book` (
  `id` int(8) NOT NULL,
  `isbn` int(15) NOT NULL,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `img` text NOT NULL,
  `short_review` text NOT NULL,
  `main_category` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` text NOT NULL,
  `Status` enum('pending','registered','archived') NOT NULL,
  `Firstname` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `regtime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `User`
--

INSERT INTO `User` (`id`, `Email`, `Password`, `Status`, `Firstname`, `Lastname`, `regtime`) VALUES
(1, 'norbi1119@gmail.com', 'akshgflas', 'registered', 'Paksi', 'Norbert', '2022-05-24'),
(2, 'a', '', 'pending', 'a', 'a', '2022-05-24'),
(3, 'b', '', 'pending', 'b', 'b', '2022-05-24'),
(4, 'sad', '', 'pending', 'asd', 'asd', '2022-05-24'),
(6, 'sadasd', '', 'pending', 'asd', 'asd', '2022-05-24'),
(8, 'string', '', 'pending', 'string', 'string', '2022-05-25'),
(10, 'stringsd', '', 'pending', 'string', 'string', '2022-05-25');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `Book`
--
ALTER TABLE `Book`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `Book`
--
ALTER TABLE `Book`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

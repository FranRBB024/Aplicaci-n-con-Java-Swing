-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 07-01-2020 a las 16:58:46
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `JUGADORES`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `dni` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(40) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fechaNac` date NOT NULL,
  `posicion` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `foto` varchar(250) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`dni`, `nombre`, `fechaNac`, `posicion`, `foto`) VALUES
('11111111A', 'HUGO SERRANO REYES', '2009-03-10', 'EXTREMO IZQUIERDO', '/home/francisco/Escritorio/hugo.jpeg'),
('22222222C', 'JEMUEL GEDALANGA', '1997-01-22', 'DELANTERO', '/home/francisco/Escritorio/Francisco/C-Fotos/jemuel.PNG');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

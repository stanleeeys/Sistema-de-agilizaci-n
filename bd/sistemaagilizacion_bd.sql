-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2024 a las 07:00:02
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaagilizacion_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cotizacion`
--

CREATE TABLE `tb_cotizacion` (
  `id_cotizacion` int(11) NOT NULL,
  `cantidad` int(100) NOT NULL,
  `unidad_de_medida` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `precio_unitario` decimal(5,2) NOT NULL,
  `precio_total` decimal(5,2) NOT NULL,
  `fecha_de_compra` datetime NOT NULL DEFAULT current_timestamp(),
  `area_de_inversion_y_rubros_especificos` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_cotizacion`
--

INSERT INTO `tb_cotizacion` (`id_cotizacion`, `cantidad`, `unidad_de_medida`, `descripcion`, `precio_unitario`, `precio_total`, `fecha_de_compra`, `area_de_inversion_y_rubros_especificos`) VALUES
(9, 2, 'C/U', 'GALONES DE AGUA', 12.00, 12.00, '2024-04-27 22:36:43', 'S'),
(10, 2, 'C/U', 'CUBETAS DE PINTURA', 12.00, 12.00, '2024-04-27 22:37:26', 'S'),
(11, 3, 'c/u', 'kit para inodoro', 15.00, 15.00, '2024-04-27 22:43:34', ''),
(12, 6, 'c/u', 'spray de diferente color', 4.00, 21.00, '2024-04-27 22:49:06', ''),
(14, 234, 'awd', 'awd', 14.00, 14.00, '2024-04-27 22:51:24', ''),
(15, 13, 'cu', 'lo que sea', 12.50, 13.80, '2024-04-27 22:58:21', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_cotizacion`
--
ALTER TABLE `tb_cotizacion`
  ADD PRIMARY KEY (`id_cotizacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_cotizacion`
--
ALTER TABLE `tb_cotizacion`
  MODIFY `id_cotizacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

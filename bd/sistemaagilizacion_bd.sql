create database sistemaagilizacion_bd;
use sistemaagilizacion_bd;

create table cliente(
	id_cliente int not null auto_increment,
	encargado_compra varchar(255),
	nombre_institucion varchar(512) not null,
	municipio varchar(255) not null,
	codigo_escuela varchar(255) not null,
	primary key(id_cliente)
);


create table proveedor(
	id_proveedor int not null auto_increment,
	nombre_proveedor varchar(512) not null,
	primary key(id_proveedor)

);

create table fechaser(
	id_fechas int not null auto_increment,
	fecha_solicitud datetime not null,
	fecha_cotizacion datetime not null,
	fecha_orden datetime not null,
	fecha_recepcion datetime not null,
	fecha_plan_compras datetime not null,
	primary key(id_fechas)
);


create table orden(
	id_orden int not null auto_increment,
	cod_orden varchar(100) not null,
	encargado_orden varchar(255) not null,
	totales decimal(8,2),
	limite_cotizacion datetime not null,
	fecha_de_entrega datetime not null,
	hora_entrega_desde varchar(50),
	hora_entrega_hasta varchar(50),
	tiempo_entrega varchar(100),
	plazo_entrega varchar(100),
	lugar_entrega varchar(100),
	vigencia_de_la_cotizacion varchar(50),
	tiempo_de_garantia varchar(50),
	cliente_id int not null,
	proveedor_id int not null,
	fechas_er_id int not null,
	primary key(id_orden),
	foreign key(cliente_id) references cliente(id_cliente),
	foreign key (proveedor_id) references proveedor(id_proveedor),
	foreign key (fechas_er_id) references fechaser(id_fechas)
);

create table detallesorden(
	id_detalle_orden int not null auto_increment,
	num_articulo int not null,
	cantidad int not null,
	unidad_medida varchar(50),
	descripcion_articulo varchar(512) not null,
	precio_unitario decimal(8,2) not null,
	precio_total decimal(8,2),
	orden_id int not null,
	primary key(id_detalle_orden),
	foreign key(orden_id) references orden(id_orden)
);

INSERT INTO proveedor (nombre_proveedor) VALUES ('LIBRERÍA, PAPELERIA Y ACCESORIOS DANY '), ('EL RINCONSITO FERRETERO');



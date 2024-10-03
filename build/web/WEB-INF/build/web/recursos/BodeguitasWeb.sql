create database BodeguitasWeb;
use BodeguitasWeb;

create table persona(
identificacion int primary key not null,
tipoDocumento char not null,
primerNombre varchar(50) not null,
segundoNombre varchar(50),
primerApellido varchar(50) not null,
segundoApellido varchar(50), 
celular int,
email varchar(100),
direccion varchar(100),
genero char,
rol char not null,
clave varchar(32) not null,
codigoRecuperacion int not null,
fechaExpedicion date,
fechaNacimiento date,
foto varchar(100)
);

create table categoria(
id int auto_increment primary key not null,
nombre varchar(50 )not null,
referecia char not null,
descripcion text
);

create table departamento(
id int auto_increment primary key not null,
nombre varchar(50) not null
);

create table municipio(
id int auto_increment primary key not null,
nombre varchar(50) not null,
cardinalidadGeografica char not null,
idDepartamento int not null,
foreign key (idDepartamento) references departamento(id) on update cascade on delete restrict
);

create table unidadDeMedida(
id int auto_increment primary key not null,
formato varchar(30) not null,
nomenclatura varchar(10) not null,
idUnidadPadre int not null,
factorDeConversion varchar(30),
foreign key (idUnidadPadre) references unidadDeMedida(id) on update cascade on delete restrict
);

create table inventario(
id int auto_increment primary key not null,
nombreProducto varchar(50) not null unique,
stock int,
idUnidadMedida int not null,
idCategoria int not null, 
observaciones text,
foto varchar(100),
foreign key (idUnidadMedida) references unidadDeMedida(id) on update cascade on delete restrict,
foreign key (idCategoria) references categoria(id) on update cascade on delete restrict
);

create table entrada(
id int auto_increment primary key not null,
fecha datetime not null,
numeroDocumento int not null, -- documento proovedor
foreign key (numeroDocumento) references persona(identificacion) on update cascade on delete restrict
);

create table detalleEntrada(
id int auto_increment primary key not null,
idEntrada int not null,
idInventario int not null,
cantidad int not null,
foreign key (idEntrada) references entrada(id) on update cascade on delete restrict,
foreign key (idInventario) references inventario(id) on update cascade on delete restrict
);

create table ruta(
id int auto_increment primary key not null,
nombre varchar(50) not null,
distancia int not null,
peajes int
);

create table vehiculo(
placaVehiculo varchar(10) primary key not null,
modelo varchar(30) not null,
marca varchar(40) not null,
capacidadDeCarga int not null default 0,
descripcion text
);

create table institucion(
id int auto_increment primary key not null,
nombreSede varchar(100) not null unique,
cupo int not null,
idRuta int not null,
idMunicipio int not null,
identificacionEncargado int not null,-- nombre del encardo de la institucion
orden int not null,
direccion varchar(50) not null,
direccionComplementaria text,
telefono int not null,
foreign key (idRuta) references ruta(id) on update cascade on delete restrict,
foreign key (idMunicipio) references municipio(id) on update cascade on delete restrict,
foreign key (identificacionEncargado) references persona(identificacion) on update cascade on delete restrict
);

create table planRuta(
id int auto_increment primary key not null,
canastillas int not null default 1,
fecha datetime, -- fecha en el plan de ruta
idRuta int not null,
idPlacaVehiculo varchar(10) not null,
identificacionConductor int not null, -- conductor empleado de la bodega
foreign key (idRuta) references ruta(id) on update cascade on delete restrict,
foreign key (idPlacaVehiculo) references vehiculo(placaVehiculo) on update cascade on delete restrict,
foreign key (identificacionConductor) references persona(identificacion) on update cascade on delete restrict
);

create table despacho(
id int auto_increment primary key not null,
fechaSalida datetime not null,
cantidadCanastillas int not null default 1, -- canastillas que alista el despachador
idPlanDeRuta int not null,
observacion text,
identificacionDespachador int not null,
foreign key (idPlanDeRuta) references planRuta(id) on update cascade on delete restrict,
foreign key (identificacionDespachador) references persona(identificacion) on update cascade on delete restrict
);

create table despachoCanastilla(
idDespachoCanastilla int primary key not null,
idProducto int not null,
cantidad int not null, -- cantidad de canastillas
idDespacho int not null,
idInstitucion int not null,
foreign key (idProducto) references inventario(id) on update cascade on delete restrict,
foreign key (idDespacho) references despacho(id) on update cascade on delete restrict,
foreign key (idInstitucion) references institucion(id) on update cascade on delete restrict
);

create table devolucion(
id int auto_increment primary key not null,
idInstitucion int not null,
encargandoInstitucion int not null,
encargadoRecibir int not null,
motivoDevolucion text not null,
fechaDevolucion datetime not null,
foreign key (idInstitucion) references institucion(id) on update cascade on delete restrict,
foreign key (encargandoInstitucion) references persona(identificacion) on update cascade on delete restrict,
foreign key (encargadoRecibir) references persona(identificacion) on update cascade on delete restrict
);

create table detalleDevolucion(
id int auto_increment primary key not null,
idProducto int not null,
cantidad int not null default 1,
idUnidadMedida int not null,
codigoDevolucion int not null,
codigoDespacho int not null,
foreign key (idProducto) references inventario(id) on update cascade on delete restrict,
foreign key (idUnidadMedida) references unidadDeMedida(id) on update cascade on delete restrict,
foreign key (codigoDevolucion) references devolucion(id) on update cascade on delete restrict,
foreign key (codigoDespacho) references despachoCanastilla(idDespachoCanastilla) on update cascade on delete restrict
);



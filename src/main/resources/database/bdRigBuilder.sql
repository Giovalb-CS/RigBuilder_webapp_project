DROP DATABASE IF EXISTS rigbuilder;
CREATE DATABASE rigbuilder;
USE rigbuilder;

#Admin(email, password, id)
create table Administrator(
	id int NOT NULL AUTO_INCREMENT primary key,
    email char(30) not null,
    password char(30) not null
);

#CPU(id, name, rating, price, shop_URL, image_URL, TDP, socket, RAM_type, core, thread, clock_base, clock_boost, cache, scale, generation)
create table Processor(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    socket varchar(30) not null,
    ram_type varchar(15) not null,
    core int not null,
    thread int not null,
    clock_base double not null,
    clock_boost double not null,
    cache int not null,
    scale int not null,
    generation varchar(20) not null
);

#GPU(id, name, rating, price, shop_URL, image_URL, TDP, memory, memory_clock, core_clock, boost_clock, lenght, slot_width, power_cable)
create table GPU(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    memory varchar(15) not null,
    memory_clock int not null,
    core_clock int not null,
    boost_clock int not null,
    lenght int not null,
    slot_width int not null,
    power_cable varchar(15) not null
);

#RAM(id, name, rating, price, shop_URL, image_URL, TDP, type, clock)
create table RAM(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    type varchar(8) not null,
    clock int not null
);

#SSD(id, name, rating, price, shop_URL, image_URL, TDP, PCIe_gen, capacity, speed_read, speed_write)
create table SSD(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    pcie_gen varchar(8) not null,
    capacity varchar(6) not null,
    speed_read int not null,
    speed_write int not null
);

#Motherboard(id, name, rating, price, shop_URL, image_URL, TDP, socket, chipset, RAM_type, RAM_max_speed, RAM_slot, RAM_max, PCIe_x16_slot, PCIe_x1_slot, M2_slot, SATA_slot, LAN, WIFI, form_factor)
create table Motherboard(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    socket varchar(8) not null,
    chipset varchar(10) not null,
    ram_type varchar(8) not null,
    ram_max_speed int not null,
    ram_slot int not null,
    ram_max int not null,
    pcie_x16_slot int not null,
    pcie_x1_slot int not null,
    m2_slot int not null,
    sata_slot int not null,
    lan varchar(15) not null,
    wifi varchar(8) not null,
    form_factor varchar(20) not null
);

#Cooler(id, name, rating, price, shop_URL, image_URL, TDP, socket, rpm, noise_level, radiator_size, cooler_height)
create table Cooler(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    socket varchar(40) not null,
    rpm int not null,
    noise_level int not null,
    radiator_size int,
    cooler_height int
);

#PSU(id, name, rating, price, shop_URL, image_URL, TDP, type, efficiency, wattage, lenght)
create table PSU(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    type varchar(20) not null,
    efficiency varchar(20) not null,
    wattage int not null,
    lenght int not null
);

#Case(id, name, rating, price, shop_URL, image_URL, max_cooler_height, radiator_size, gpu_lenght, form_factor, psu_lenght, PCIe_slots)
create table CaseBox(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    max_cooler_height int not null,
    radiator_size int not null,
    gpu_lenght int not null,
    form_factor varchar(20) not null,
    psu_lenght int not null,
    pcie_slots int not null
);

#GestireProcessor(idAdmin, idCPU)
create table GestireProcessor(
	idAdmin int not null,
    idProcessor int not null,
    primary key(idAdmin, idProcessor),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idProcessor) references Processor(id)
);

#GestireGPU(idAdmin, idGPU)
create table GestireGPU(
	idAdmin int not null,
    idGPU int not null,
    primary key(idAdmin, idGPU),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idGPU) references GPU(id)
);

#GestireRAM(idAdmin, idRAM)
create table GestireRAM(
	idAdmin int not null,
    idRAM int not null,
    primary key(idAdmin, idRAM),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idRAM) references RAM(id)
);

#GestireSSD(idAdmin, idSSD)
create table GestireSSD(
	idAdmin int not null,
    idSSD int not null,
    primary key(idAdmin, idSSD),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idSSD) references SSD(id)
);

#GestireMOBO(idAdmin, idMOBO)
create table GestireMOBO(
	idAdmin int not null,
    idMOBO int not null,
    primary key(idAdmin, idMOBO),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idMOBO) references Motherboard(id)
);

#GestireCooler(idAdmin, idCooler)
create table GestireCooler(
	idAdmin int not null,
    idCooler int not null,
    primary key(idAdmin, idCooler),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idCooler) references Cooler(id)
);

#GestirePSU(idAdmin, idPSU)
create table GestirePSU(
	idAdmin int not null,
    idPSU int not null,
    primary key(idAdmin, idPSU),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idPSU) references PSU(id)
);

#GestireCaseBox(idAdmin, idCase)
create table GestireCaseBox(
	idAdmin int not null,
    idCaseBox int not null,
    primary key(idAdmin, idCaseBox),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idCaseBox) references CaseBox(id)
);
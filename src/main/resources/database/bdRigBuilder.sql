DROP DATABASE IF EXISTS rigbuilder;
CREATE DATABASE rigbuilder;
USE rigbuilder;

#Admin(email, password, id)
create table Administrator(
	id int NOT NULL AUTO_INCREMENT primary key,
    email char(30) not null,
    pwd char(40) not null
);

insert into Administrator values
(1, "giovannibonal@gmail.com", "9af535ac528d26f4cb8e8429b00652e18c7b8c1d");

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
    generation varchar(40) not null
);
insert into processor values
(1,
"AMD Processore Ryzen 7 7800X3D, Tecnologia 3D VCache, 8 Core/16 Thread senza Limiti, Architettura Zen 4, 104 M di Cache, 120 W di TDP, AMD Socket 5, DDR5 e PCIe 5.0", 4.8, 427,
"https://www.amazon.it/AMD-Processore-Ryzen-7800X3D-Architettura/dp/B0BTZB7F88/ref=sr_1_1?crid=M28COSZK1X13&dib=eyJ2IjoiMSJ9.eMhG2ZqLZV2WsnDgng90ytFZEGEz2o8uSzvHjBTQnMGDF63W74rahFsHvBp7qiNAX98_wa45oxHkVuxLtiiMePTtV-dM-GJj6lilrTFng8UaDylRTJN6XK80rce2iX1okU0XTYpIy7NNXsdnj7HrzMnp4bGPdeeCUt61sVh6Ilnrv2HqwTm8qK6Fn99IwRgNWIbmpLZ8Ddv31R08r87hA1ZCs-kyLVw5FdXkRV42ieF1fblGipI-aC41gIzAm1HZ03snhdFq96JTzNFMNu9GKBYaPGh0kw7HEr1JQOf147U.LteEJpRb6dSTwxOZ4wYYZ4hMOZYFI-1dhD3vEhynYp0&dib_tag=se&keywords=amd+ryzen+7+7800x3d&qid=1727721110&sprefix=amd%2Caps%2C140&sr=8-1&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/51HqC0rU9HL._AC_SX355_.jpg",
120, "AM5", "DDR5", 8, 16, 4.2, 5, 104, 5, "AMD Zen 4"),
(2,
"Intel® Core™ i9 Processore per sistemi desktop 14900K 24 core (8 P-core + 16 E-core) fino a 6,0 GHz", 4.4, 506,
"https://www.amazon.it/Intel%C2%AE-CoreTM-Processore-sistemi-desktop/dp/B0CHBJGFBC/ref=sr_1_1?crid=20H37T7YTJ8S9&dib=eyJ2IjoiMSJ9.BYciJEW51u9vhyxR4cuYPRce7cic_fccJ3IhcKTVNlDEeB6MuX_hYXxHRhYBKoRtnX2Sk35azwLdNbb8-WlXNINBxmED62nWvh8du3ZkDhurasoPiR9tKB9N13ec2QF5eG_bh3WtWJLG6XEI2NMn6VwgeQEH-f9ysZh_GEmyV3H5PKu15aGiXfewT6kzmjG3r1RHMM2Xva4x8m4VsqXU3FffH7mG3SfLIfP4_4WZv4g5oFgJT_C5HUrHGZA5k0E8FtrDbFo7XTqFW5F9iprIairoKDkA4WrxqbSMYJISBSg.ZAscuzbeiHeP22Oa0uvS8_aFxCpWsJWL3u2jcLOOxkM&dib_tag=se&keywords=intel+i9+14900k%2B&qid=1727721120&sprefix=intel+i9%2Caps%2C154&sr=8-1&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/31AnDDm99fL._AC_.jpg",
125, "LGA 1700", "DDR5", 24, 32, 3.2, 5.8, 36, 10, "Intel Raptor Lake Refresh");

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
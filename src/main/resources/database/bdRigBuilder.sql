# DROP DATABASE IF EXISTS rigbuilder;
# CREATE DATABASE rigbuilder;
# USE rigbuilder;
USE p5uucxui7h661tgv;

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
    power_cable varchar(30) not null
);
insert into GPU values
(1,
"ASUS ROG STRIX NVIDIA GeForce RTX4090 O24G GAMING, Scheda Grafica Gaming, OpenGL 4.6, 24 GB GDDR6X, PCIe 4.0, HDMI 2.1a, DisplayPort 1.4a, GPU Tweak III, Nero",
4.4,
2438.88,
"https://www.amazon.it/ASUS-GeForce-RTX4090-Grafica-DisplayPort/dp/B0BHD6N2CK/ref=sr_1_2?crid=3TH91YUMPUQU0&dib=eyJ2IjoiMSJ9.wZXSihhC9cQQVorzrry8JGK-76S0nREsxhgU0pCHopChHWqN2jGvxc0vWMobhg44AifvQRhEVfjz1OxcfRFQUz5Er2qSLuxtTkYvn9-gs1iV-VHRMtz7SKsYMynzPqtcarsajXDGlWncWOCi3frv1s3xPFKx1YB12iqdSym3nZwsS48L75Ldd_JRDpill6LUyHmZkIGuz-O2W-5xfGA2y54CjKLSEEdSN8BenR4IVlwZ880SDL8NqsHShMtIoWAHqr8FRIx9mrn0gZ3aKZirAi3IN0Cc44snBwSJXBc-iN0.ViRpTe4Ge1QIyvGkgCTo5kP58ZGIp1fs3aKGC63YpB8&dib_tag=se&keywords=nvidia+rtx+4090+ti+super&qid=1728662746&sprefix=nvidia+rtx+4090+ti%2Caps%2C145&sr=8-2&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/81VLjsBvSjL._AC_SX522_.jpg",
1000,
"24GB GDDR6X",
21000,
2610,
2640,
357,
2,
"1x 16pin"),
(2,
"Asus Dual NVIDIA GeForce RTX 3060 V2 OC Edition Scheda Grafica, 12GB GDDR6 192-bit 15 Gbps PCIE 4.0, GPU NVIDIA Ampere, DUAL-RTX3060-O12G-V2",
4.7,
295.0,
"https://www.amazon.it/ASUS-GeForce-Grafica-192-bit-DUAL-RTX3060-O12G-V2/dp/B096658ZWP/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=NVK7JUMK643Q&dib=eyJ2IjoiMSJ9.SywKHJKEd_dhD7em5BvUMEkxDjqkHQRRldUT7XqSlO_Gch3hwafvtsIwOMGM4fvuWAgwKPDXo3UFBC_y2LVKhP8Rjb57NWRdrBGotbaGHCLReEp6HADiodzkrEdnSQry5cfOmkV3MI2xNuSgy9gcE7EgpbadTEHE75jA1ISXt4utThTSM3hLVNvyt3fG963iiEPOGOA2bd2t8MywFftnrFWDqj89sjpgkqsOCDC_-d3mMCJ1Ddh5bcstOySnSm72wsDlN5VwbQ0QPv3RwSsBTvcmgRmhbk4fqNVPDiuWKSc.HyCOxgxYlOYSWzFbhdL608lEtUBOjZwb7fPDhnEK8po&dib_tag=se&keywords=nvidia+rtx+2070+gigabyte&qid=1728662704&sprefix=nvidia+rtx+2070+gigabyte%2Caps%2C149&sr=8-1&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/81cy1Ep7vFL._AC_SY355_.jpg",
650,
"12GB GDDR6",
15000,
1837,
1867,
200,
2,
"1x 8pin");

#RAM(id, name, rating, price, shop_URL, image_URL, TDP, type, clock)
create table RAM(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    type varchar(10) not null,
    clock int not null
);
insert into RAM values
(1,
"CORSAIR VENGEANCE RGB DDR5 32GB (2x16GB) DDR5 6000MHz CL30 AMD EXPO Intel XMP iCUE Memoria Compatibile per Computer - Grigio (CMH32GX5M2B6000Z30K)",
4.8,
124.9,
"https://www.amazon.it/CORSAIR-VENGEANCE-6000MHz-Compatibile-Computer/dp/B0BPTKD797/ref=sr_1_6?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=3ST29QIKQBBIC&dib=eyJ2IjoiMSJ9.ZN1k5yscRwMZ_P9FLJtaFxPyPLMoS4EEUHzwoK86teZ1LvFc3TuSeOttJURYb-EiNdUDYJDsOxrfmz1wBmqpnUxkJAU6Jn2wLzI6HpE7SLieMdofJO_Ac4d6uu6B96zMTRYgAZ6z8vYqbZ0vy-W6fBa6rv8MBJ6PPoKOQs4vpPnHwUg9pPHCww3qU8nKUUIc2B-aWc3Xzt2M9JdTjTtNyuiAaFqrEaO1jHOcaMaJNRFMZOwRoi2DpknctYKoY55lk6iAp74wn_Bhjre_lPtfCdnLtdW66sSuxqrDfg-VvEI.vMVT64JenRT-QTnTSw7gRmYT2eC1JLmf8q4Dv0UvIA0&dib_tag=se&keywords=ram%2Bddr5%2B8400&qid=1729095252&sprefix=ram%2Bddr5%2B8400%2Caps%2C182&sr=8-6&ufe=INHOUSE_INSTALLMENTS%3AIT_IHI_5M&th=1",
"https://m.media-amazon.com/images/I/61EVf-QxpvL._AC_SX679_.jpg",
2,
"DDR5",
6000),
(2,
"G.Skill Trident Z RGB 16GB DDR4 memory module 2 x 8 GB 3200 MHz",
4.7,
50,
"https://www.amazon.it/G-Skill-Trident-16GB-3200MHz-memoria/dp/B01MTDEYHU/ref=sr_1_2_mod_primary_new?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1GZ8PVS91BDW9&dib=eyJ2IjoiMSJ9.Uws9ruUjhUuDDPf-AUJmPjBwUHLV0_tYUwVZKS-1ShUPEiGoOMSfmUN3iRwcR3ZRP_vz3XpA-WzqUJ_IHV5_bUKvMc-haODUTd8Gn8cjuM3RMScnCgLtp2ilXbDRTKc_nX38PlSQsQmPBM6EcZnznu-MQ4jDzA6ZI8VmJAPl1IuB7mkP6iIq1tLrmvi1tEoSgCpAQ_8SCKunVqpMQnz2HtP0rC11By-h8gRjgK_2-KDZGD5roo_p4syHEJFaBwz-516dBF4iVqu15u4M_H4xB0_KRJ0gg7KArg39gFRbKf8.3LwCs0-JA4SaawaKxE0XSMa9erMARexf0i0qQWeABms&dib_tag=se&keywords=gskill+trident+z+ddr4+3200mhz&qid=1729095035&sbo=RZvfv%2F%2FHxDF%2BO5021pAnSA%3D%3D&sprefix=gskill+trident+z+ddr4+3200mhz%2Caps%2C117&sr=8-2",
"https://m.media-amazon.com/images/I/61l4EStxhnL._AC_SX679_.jpg",
2,
"DDR4",
3200);

#SSD(id, name, rating, price, shop_URL, image_URL, TDP, PCIe_gen, capacity, speed_read, speed_write)
create table SSD(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    pcie_gen varchar(10) not null,
    capacity varchar(10) not null,
    speed_read int not null,
    speed_write int not null
);
insert into SSD values
(1,
"Crucial P3 Plus SSD 2TB PCIe Gen4 NVMe M.2 SSD Interno, Fino a 5.000 MB/s, Compatibile con Notebook e PC Desktop, Hard Disk SSD - CT2000P3PSSD801",
4.7,
124.9,
"https://www.amazon.it/Crucial-Plus-PCIe-Gen4-interno/dp/B0BYW8FLKN/ref=sr_1_5?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=5LCN59F5OI9T&dib=eyJ2IjoiMSJ9.8TVbqvYvb-epgdJGz_nqAkc3hajW95n3wPRAb3iQQVMcKM_afZ_5qSZp1E4tkPSMiARBg-x8Jog-ExT1a4IAQX53hZ8rCOiU5FyzzeCXWFNywxDgSopXCvJ_ZTwoHV7LMn3fTHubCUCA-LcDv71IecAqsju7FYLL1lrM1bXbrQZBJkPjHinWJMrCfPBq3kvXwBspuACJoA3lTQeGyM5PHDe8S4jE-OIZZ69RygJi75gmBl3ao5FrEDsrgPDQPzZ5aU8pEb56ZjdHJhVmZ9HmPohqR2j6YKDi637d7yHxvQM.XxbemlSQn4KqdR7h-xz1rLS7vjx632ze6QrYnlb0du8&dib_tag=se&keywords=ssd+nvme+2tb&qid=1729155013&sprefix=ssd+nvme2tb%2Caps%2C178&sr=8-5&ufe=app_do%3Aamzn1.fos.d4b79b69-7fa3-49d4-9d2a-f8ac4bab3f93",
"https://m.media-amazon.com/images/I/51xZaoS+Q1L._AC_SX522_.jpg",
10,
"4x4",
"2TB",
5000,
4200),
(2,
"Lexar NQ790 2TB SSD Interno PCIe 4.0, M.2 2280 PCIe Gen4x4 NVMe 1.4, Lettura fino a 7000 MB/s, Unità a Stato Solido Interna ad Alte Prestazioni per Carichi di Lavoro Intensi, PS5 SSD",
4.8,
129.9,
"https://www.amazon.it/Lexar-Interno-Lettura-Interna-Prestazioni/dp/B0CHS1SN9K/ref=sr_1_9?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=5LCN59F5OI9T&dib=eyJ2IjoiMSJ9.8TVbqvYvb-epgdJGz_nqAkc3hajW95n3wPRAb3iQQVMcKM_afZ_5qSZp1E4tkPSMiARBg-x8Jog-ExT1a4IAQX53hZ8rCOiU5FyzzeCXWFNywxDgSopXCvJ_ZTwoHV7LMn3fTHubCUCA-LcDv71IecAqsju7FYLL1lrM1bXbrQZBJkPjHinWJMrCfPBq3kvXwBspuACJoA3lTQeGyM5PHDe8S4jE-OIZZ69RygJi75gmBl3ao5FrEDsrgPDQPzZ5aU8pEb56ZjdHJhVmZ9HmPohqR2j6YKDi637d7yHxvQM.XxbemlSQn4KqdR7h-xz1rLS7vjx632ze6QrYnlb0du8&dib_tag=se&keywords=ssd+nvme+2tb&qid=1729155013&sprefix=ssd+nvme2tb%2Caps%2C178&sr=8-9&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/61CEtN360EL._AC_SX522_.jpg",
10,
"4x4",
"2TB",
7000,
6000);

#Motherboard(id, name, rating, price, shop_URL, image_URL, TDP, socket, chipset, RAM_type, RAM_max_speed, RAM_slot, RAM_max, PCIe_x16_slot, PCIe_x1_slot, M2_slot, SATA_slot, LAN, WIFI, form_factor)
create table Motherboard(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    socket varchar(30) not null,
    chipset varchar(30) not null,
    ram_type varchar(15) not null,
    ram_max_speed int not null,
    ram_slot int not null,
    ram_max int not null,
    pcie_x16_slot int not null,
    pcie_x1_slot int not null,
    m2_slot int not null,
    sata_slot int not null,
    lan varchar(28) not null,
    wifi varchar(10) not null,
    form_factor varchar(20) not null
);
insert into Motherboard values
(1,
"Gigabyte B760M DS3H DDR4 motherboard Intel B760 LGA 1700 micro ATX",
4.2,
97.1,
"https://www.amazon.it/Gigabyte-B760M-DS3H-DDR4-motherboard/dp/B0BPMH3N7H/ref=sr_1_1?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1FV8OHT4ES845&dib=eyJ2IjoiMSJ9.PYbRdXgR_6XVVEcDKF1mqMc9qL4WeIridkikrDX5sHprq7ypkLENJrcWvgdZhm9PU1KogDzaLeI0q0mCjpeE6szuVr5QdUQmJzFPoWxiauiQxpAQCWa3e-7dWG8k5ZCzpIz2Bbxf7TCg2gNwx-n2OE2ghD2xVx5LOF1UiDJpV1zg430e_9dOMxrXmks9_0rUQk_9wFr_xeMjrz-r0fovQYcKnOFnqdEG1LBHsR1hHJDLTMsCDmbb49ekM-NIACNhnPsYM8228tvaNwbiVFp7-qcoQWk1ZvmvyUEH1cniglw.O5MjsMmeZLcF1OlZP-LkdV8rnRKJHa6-IgCduiyW-2A&dib_tag=se&keywords=lga+1170+motherboard&qid=1729612667&sprefix=lga+1170+motherboard%2Caps%2C126&sr=8-1",
"https://m.media-amazon.com/images/I/61EOMUZ6ACL._AC_SX679_.jpg",
80,
"LGA 1700",
"Intel B760",
"DDR4",
5333,
4,
128,
1,
2,
2,
4,
"1x 1GB/s",
"No",
"Micro-ATX"),
(2,
"ASUS ROG STRIX B650-A GAMING WIFI Scheda Madre Gaming ATX, AMD B650, AM5, DDR5, 4xPCI 4.0, WiFi 6E (802.11ax), Intel 2.5Gb Ethernet, ROG SupremeFX 7.1, 3xM.2, 4xSATA 6GB/s, Aura Sync RGB, Nero",
4.5,
221.9,
"https://www.amazon.it/ASUS-GAMING-802-11ax-Ethernet-SupremeFX/dp/B0BHHVHQXZ/ref=sr_1_5?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=2XT6TVE23SLAU&dib=eyJ2IjoiMSJ9.NUy1PJT05NYyQ9iWyW31F0zhZtLeKh19Eb98mfil463XzD9j4mrclvFLdJOE4q06JGYoFRHNMnzxufi2OJCCjN2MMmQtlD_Lb26_PtgPCZ0ysT_OMNBQFhvNfJbrFIxhYhvYQWR7npXOB4zfWFatOx3TOLR4cDjhJ1woawCfqxsxvRIe975Juk3cEng10hwaqyz8adzKQS2IBKbJVzVaWeZ0LVGLlAWQD_4zFrJk2-h10Gjz_RdcBOPf9rrnQa5cbG1Fn8DuPRInhYRRBRLXoA5mSFARE6Fzhmhd603kxXk.CPhYBJJFnFtAkVmWQZMZwKSnDT3LRh41XR_OGo8zPZw&dib_tag=se&keywords=am5+mobo&qid=1729612683&sprefix=am5+mob%2Caps%2C126&sr=8-5&ufe=app_do%3Aamzn1.fos.9d4f9b77-768c-4a4e-94ad-33674c20ab35",
"https://m.media-amazon.com/images/I/81MH+nx+shL._AC_SX522_.jpg",
80,
"AM5",
"AMD B650",
"DDR5",
6400,
4,
128,
2,
2,
3,
4,
"1x 2.5GB/s",
"Wi-Fi 6E",
"ATX");

#Cooler(id, name, rating, price, shop_URL, image_URL, TDP, socket, rpm, noise_level, radiator_size, cooler_height)
create table Cooler(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    tdp int not null,
    socket varchar(300) not null,
    rpm int not null,
    noise_level int not null,
    radiator_size int,
    cooler_height int
);
insert into Cooler values
(1,
"NZXT Kraken 360 RGB Processeur Refroidisseur de liquide tout-en-un 12 cm Blanc 1 pièce(s)",
4.4,
222.1,
"https://www.amazon.it/NZXT-Processeur-Refroidisseur-liquide-tout-en/dp/B0BNYQC95P/ref=sr_1_1_sspa?crid=1J7D5XDILGYPE&dib=eyJ2IjoiMSJ9.Z-HIiXlF0ElHpGCnCvMPNwm8UWMm1jQS5LXlruCGsA5RmtsAc_xlgRY8xd99Xx8Eac7UmDmwiajLz9QHi8iIYlCsTCRPsI4eiQ9uhoVKELPWfF_xPh3X4xf_5qcaFBgaIsa2fiAArpOMjm5yvOKFKKMIKHQbuEalYL46N0SPphftdgtXYMa7dQhz8Jm1eKYraHksNhoSn-KdgDwzOFHkfKsecoEZT-vk-vzw1oO3-d5sjIyIVNidfeGh6y_BfVr8.gU7qI_nYzG-Mp6e-AHcebS21l3Y1mhAVTVjXDgETJQU&dib_tag=se&keywords=nzxt+kraken+x73&qid=1729688907&sprefix=nzxt+kraken+x%2Caps%2C272&sr=8-1-spons&ufe=INHOUSE_INSTALLMENTS%3AIT_IHI_5M_VF&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1",
"https://m.media-amazon.com/images/I/41K2nMA4QZL._AC_SX522_.jpg",
25,
"LGA 1851/LGA 1700/LGA 1200/LGA 1150/LGA 1151/LGA 1155/LGA 1156/AM5/AM4/sTR4/sTR5/sTRX4/TR4/sWRX8",
2800,
34,
360,
null),
(2,
"be quiet! Pure Rock 2 Black, CPU Cooler Raffreddatore, 150W TDP, Pure Wings 2 120mm PWM,4 6mm Heatpipes, design asimmetrico, torre singola, BK007",
4.7,
42,
"https://www.amazon.it/quiet-Raffreddatore-Heatpipes-asimmetrico-BK007/dp/B087VL2Z21/ref=sr_1_12?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1NWNWPFZ2RVKV&dib=eyJ2IjoiMSJ9.KsiLMzTdQCvX4en6Ntq4Mo5eSrP0QsWQE9lVy2ElxslK3Se5qKrjBOIsTJpfiBVTohmjgotpW2-2LplchBXDKCmhYIhyPmqznhGlA95mfv7fQq79l7WvYL_i2YtzAS2AdJoL4gUJbd0_16LqNCwBacaORtS8uNC_qICEhZt7oqCcyHSOyWAcyXzj7sMkiXjLUy14WET3DEa1OnrvGcq9z_TsBQ4KYb5JiAl-PvJWfi581jaez65T1FwLexi1tfkE4NfAmoQOT8DGPG-mzItfP7Q8u49s0xJEis-t9oqVP5I.6uWnkDIp_NaBKmTHDErKKLOYZx7j9crfiCF9xN2ie5E&dib_tag=se&keywords=cpu%2Bair%2Bfractal%2Bcooler&qid=1729688931&sprefix=cpu%2Bair%2Bfractal%2Bcooler%2Caps%2C133&sr=8-12&th=1",
"https://m.media-amazon.com/images/I/71Vdg3j2o1L._AC_SX679_.jpg",
15,
"LGA 1700/LGA 1200/LGA 2066/LGA 1150/LGA 1151/LGA 1155/LGA 1156/AM5/AM4",
1500,
23.8,
null,
155);

#PSU(id, name, rating, price, shop_URL, image_URL, TDP, type, efficiency, wattage, lenght)
create table PSU(
	id int NOT NULL AUTO_INCREMENT primary key,
    name char(255) not null,
    rating double,
    price double not null,
    shop_url varchar(2048) not null,
    image_url varchar(2048) not null,
    type varchar(20) not null,
    efficiency varchar(20) not null,
    wattage int not null,
    lenght int not null
);
insert into PSU values
(1,
"Cooler Master MWE 850 Gold V2 Alimentatore Completamente Modulare (Spina UE) - Alimentatore 80 PLUS Gold da 850W, Cavi Piatti Neri, Ventola HDB da 120mm, Soglia Alta Temperatura",
4.7,
115.9,
"https://www.amazon.it/Cooler-Master-Gold-Completamente-modulare/dp/B08BKPG3BH/ref=sr_1_3_sspa?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1MMCLPC1LD5O4&dib=eyJ2IjoiMSJ9.Ok5b3ZSRhfT8RxtuzdA8bgeoHdxzxl3CqWvZ05Dp-Wgv2_5i5fMBJsOcAaL1tEzBX3YuUNeyEHqNdMkIAJ8sj10GzIGyFHFpVPc61RgOBOQDqp5iEcVsP_CMrTqQUSAZb368MwcYg5lb0QMgUCE0hv3YgvRx3PlqS_6vFZGOZQxeDT-pvVPGvY15VnmUWJIPz8MVBGXieADDz5yQdmgI5DrjaUJBa4LQF1WEOUDENDAC-_lLhSVHXllVQj3oyOR0K_i9nkm09nWEFHQ_6x12YXI2d-ilarmHFuuQ6M0unJ0.XxipnzgEhUhoagpsaU4k07kQPi7IZrSGcMEoRSNkXPg&dib_tag=se&keywords=alimentatore+pc&qid=1729858813&sprefix=alimentatore+p%2Caps%2C182&sr=8-3-spons&ufe=INHOUSE_INSTALLMENTS%3AIT_IHI_5M_AUTOMATED&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1",
"https://m.media-amazon.com/images/I/914bcic6EkL._AC_SX522_.jpg",
"Fully Modular",
"80+ Gold",
850,
14),
(2,
"Corsair HX1000i Alimentatore ATX Completamente Modulare ed Estremamente Silenzioso - Compatibile con ATX 3.0 e PCIe 5.0 - Compatibile con il Software Corsair iCUE - Efficienza 80 Plus Platinum - Nero",
4.6,
238.5,
"https://www.amazon.it/Corsair-Alimentatore-Completamente-Estremamente-Silenzioso/dp/B0C4B8BWM3/ref=sr_1_44?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1MMCLPC1LD5O4&dib=eyJ2IjoiMSJ9.Ok5b3ZSRhfT8RxtuzdA8bgeoHdxzxl3CqWvZ05Dp-Wgv2_5i5fMBJsOcAaL1tEzBX3YuUNeyEHqNdMkIAJ8sj10GzIGyFHFpVPc61RgOBOQDqp5iEcVsP_CMrTqQUSAZb368MwcYg5lb0QMgUCE0hv3YgvRx3PlqS_6vFZGOZQxeDT-pvVPGvY15VnmUWJIPz8MVBGXieADDz5yQdmgI5DrjaUJBa4LQF1WEOUDENDAC-_lLhSVHXllVQj3oyOR0K_i9nkm09nWEFHQ_6x12YXI2d-ilarmHFuuQ6M0unJ0.XxipnzgEhUhoagpsaU4k07kQPi7IZrSGcMEoRSNkXPg&dib_tag=se&keywords=alimentatore+pc&qid=1729858813&sprefix=alimentatore+p%2Caps%2C182&sr=8-44&ufe=INHOUSE_INSTALLMENTS%3AIT_IHI_5M_AUTOMATED",
"https://m.media-amazon.com/images/I/81oi663aTXL._AC_SX679_.jpg",
"Fully Modular",
"80+ Platinum",
1000,
18);

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
    form_factor varchar(50) not null,
    psu_lenght int not null,
    pcie_slots int not null
);
insert into CaseBox values
(1,
"Cooler Master TD300 Case PC Mesh - Mini-Tower,Elevato Flusso d'Aria, 2 x 120mm Ventole ARGB,Supporto MB Micro-ATX e Mini-ITX,Pannello Laterale in Vetro Temperato,Coperchio Rimovibile,Hub ARGB/PWM",
4.6,
86.1,
"https://www.amazon.it/Cooler-Master-TD300-Case-Mesh/dp/B09J4WN98H/ref=sr_1_5?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=1BO7CPXEBST7Z&dib=eyJ2IjoiMSJ9.sjO6UHFdnbXtn79YHCnQkNnqErtoMCmUd9KVII8mCWQUCAVoxH2uMzbNyJD0HZUX9JKxU3WXD5vketRlOdcebziwnUOayzO6OFmd0czjNvTq8mvfOMpCBDHfJ-y2EfGwTLQmDgF4rHOxSyWz2uU-piw-2G0FxxjF30gFcC_RUBSlDe08KK5Hcs_ZiDjhgvnrL73O4AHA3dLp8OndSpH6M5yDzknFSPoGLhF_MXCWeu6FI9zzoCOPhLHAt9LkizHYbVyiV2rW5S9HmmyZ80HxDUotANQouHXd3KADZPckOuo.A2Z-R4uGO8pDE9R_Pjz0yZQIqgGjqcB76bsxl7xe_wk&dib_tag=se&keywords=cooler+master+td300&qid=1729949816&sprefix=cooler+master+td30%2Caps%2C145&sr=8-5",
"https://m.media-amazon.com/images/I/81jk11GK7EL._AC_SX522_.jpg",
166,
280,
344,
"Micro-ATX/Mini-ITX",
325,
4),
(2,
"Lian Li O11 Vision Midi-Tower, Tempered Glass - weiß",
4.6,
183.7,
"https://www.amazon.it/Lian-Vision-Midi-Tower-Tempered-Glass/dp/B0CNKXH73Q/ref=sr_1_2_mod_primary_new?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=VHEORZDNWUVV&dib=eyJ2IjoiMSJ9.zYXukYpN6KfppbB9Z9JfN8Z47NFPoRRO8Jsa6jUBWzUCUslFHnrx0tsQ0Vcri5-Jnpf1HPfYuhyUBweblrzAFr9P6rRrOTUf6JwU9RbYC4Y7ro-1XWre4jcU6bh4pjcOfoy05u7heLPRpMZbnxbLq3-2R16ryHznGnHP_sOCZW-NbrlNS0_h0PFzucflNciM0euMuiUWtbDbjhul59JNj3sJ__I9RAyF_3vq1SHsbFCP8V6vY1OhWNItAaGzl0FIn58RGAOy7KzCJ2oqBBFjnAo71Ut-_GvoQNEcyXi-XYE.nZ8s79RTHJ2PACxGJvVivGR8JAfzMqFR30y_nAGN_8k&dib_tag=se&keywords=pc+case+lian+li&qid=1729949830&sbo=RZvfv%2F%2FHxDF%2BO5021pAnSA%3D%3D&sprefix=pc+case+lian%2Caps%2C325&sr=8-2",
"https://m.media-amazon.com/images/I/81WPPF07bEL._AC_SX522_.jpg",
174,
360,
430,
"EATX/ATX/Micro-ATX/Mini-ITX",
232,
7);

#GestireProcessor(idAdmin, idCPU)
create table GestireProcessor(
	idAdmin int not null,
    idProcessor int not null,
    primary key(idAdmin, idProcessor),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idProcessor) references Processor(id) on delete cascade
);

#GestireGPU(idAdmin, idGPU)
create table GestireGPU(
	idAdmin int not null,
    idGPU int not null,
    primary key(idAdmin, idGPU),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idGPU) references GPU(id) on delete cascade
);

#GestireRAM(idAdmin, idRAM)
create table GestireRAM(
	idAdmin int not null,
    idRAM int not null,
    primary key(idAdmin, idRAM),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idRAM) references RAM(id) on delete cascade
);

#GestireSSD(idAdmin, idSSD)
create table GestireSSD(
	idAdmin int not null,
    idSSD int not null,
    primary key(idAdmin, idSSD),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idSSD) references SSD(id) on delete cascade
);

#GestireMOBO(idAdmin, idMOBO)
create table GestireMOBO(
	idAdmin int not null,
    idMOBO int not null,
    primary key(idAdmin, idMOBO),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idMOBO) references Motherboard(id) on delete cascade
);

#GestireCooler(idAdmin, idCooler)
create table GestireCooler(
	idAdmin int not null,
    idCooler int not null,
    primary key(idAdmin, idCooler),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idCooler) references Cooler(id) on delete cascade
);

#GestirePSU(idAdmin, idPSU)
create table GestirePSU(
	idAdmin int not null,
    idPSU int not null,
    primary key(idAdmin, idPSU),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idPSU) references PSU(id) on delete cascade
);

#GestireCaseBox(idAdmin, idCase)
create table GestireCaseBox(
	idAdmin int not null,
    idCaseBox int not null,
    primary key(idAdmin, idCaseBox),
    foreign key(idAdmin) references Administrator(id),
    foreign key(idCaseBox) references CaseBox(id) on delete cascade
);
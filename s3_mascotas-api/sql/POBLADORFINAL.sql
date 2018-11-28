/*APP.ESPECIEENTITY*/

/* INSERT QUERY */
INSERT INTO APP.ESPECIEENTITY(id, deleted, nombre, imagen) 
VALUES 
  (
    201, FALSE, 'Perro', 'https://static.diariosur.es/www/multimedia/201707/04/media/cortadas/chihuahua-U30430842503s7C-U40257429333JnE-624x385@Diario%20Sur-DiarioSur.jpg'
  );
/* INSERT QUERY */
INSERT INTO APP.ESPECIEENTITY(id, deleted, nombre, imagen) 
VALUES 
  (
    202, FALSE, 'Gato', 'https://fotografias.lasexta.com/clipping/cmsimages02/2016/03/13/9A1C357F-2FDD-4DD0-BFE5-5AAA2C81F6FB/58.jpg'
  );
/* INSERT QUERY */
INSERT INTO APP.ESPECIEENTITY(id, deleted, nombre, imagen) 
VALUES 
  (
    203, FALSE, 'Hamster', 'https://www.anipedia.net/imagenes/taxonomia-hamsters.jpg'
  );
  
  
  
  
  
 
 /*APP.RAZAENTITY*/
 
 /* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    201, FALSE, 'https://misanimales.com/wp-content/uploads/2017/07/gato-azul-ruso.jpg', 
    'Azul Ruso', 202
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    202, FALSE, 'https://estaticos.muymascotas.es/media/cache/1000x_thumb/uploads/images/gallery/591ec9b95bafe8a5c53c986a/siames-thai.jpg', 
    'Gato Siames', 202
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    203, FALSE, 'https://shop-cdn-m.shpp.ext.zooplus.io/bilder/royal/canin/maine/coon/adult/1/400/9804_3_1.jpg', 
    'Maine Coon', 202
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    204, FALSE, 'https://www.dogalize.com/wp-content/uploads/2017/04/persiano-bianco-705x470.jpg', 
    'Gato Persa', 202
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    205, FALSE, 'https://t1.ea.ltmcdn.com/es/razas/1/0/5/img_501_hamster-dorado_0_600.jpg', 
    'Hamster Dorado', 203
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    206, FALSE, 'http://www.mundoroedor.com/images/hamsters/campbellnominal.jpg', 
    'Hamster Campbell', 203
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    209, FALSE, 'https://ichef.bbci.co.uk/news/660/cpsprodpb/B22F/production/_90551654_gettyimages-511711526.jpg', 
    'Bulldog', 201
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    210, FALSE, 'https://t1.ea.ltmcdn.com/es/razas/0/4/3/img_340_beagle_0_600.jpg', 
    'Beagle', 201
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    211, FALSE, 'https://www.vitake.net/wp-content/uploads/2017/06/pastor-aleman-raza-perro-mascota-3-1280x720x80xX.jpg', 
    'Pastor Aleman', 201
  );
/* INSERT QUERY */
INSERT INTO APP.RAZAENTITY(
  id, deleted, imagen, nombre, especie_id
) 
VALUES 
  (
    212, FALSE, 'https://www.purina.es/sites/g/files/mcldtz1656/files/2018-01/Chihuahua%20%28de%20pelo%20suave%29%20_400x378.jpg', 
    'Chihuahua', 201
  );
  
  
  
  
  
/*APP.MASCOTAENTITY*/

/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    201, 'Violet', FALSE, 2, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Pronghorn', 78.44, 204
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    202, 'Orange', FALSE, 3, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Yellow mongoose', 228.00, 201
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    203, 'Blue', FALSE, 1, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Bat-eared fox', 998.95, 202
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    204, 'Orange', FALSE, 10, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Blue-tongued lizard', 511.11, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    205, 'Red', FALSE, 9, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Duck, white-faced whistling', 
    929.62, 212
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    206, 'Purple', FALSE, 1, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Possum, western pygmy', 993.85, 
    210
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    207, 'Yellow', FALSE, 10, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Common rhea', 426.39, 212
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    208, 'Green', FALSE, 9, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'King vulture', 17.47, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    209, 'Aquamarine', FALSE, 4, 'M', 
    'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Great egret', 110.16, 211
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    210, 'Pink', FALSE, 9, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Wapiti, elk,', 184.85, 201
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    211, 'Blue', FALSE, 2, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Oryx, fringe-eared', 911.73, 201
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    212, 'Mauv', FALSE, 2, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'South African hedgehog', 523.31, 
    205
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    213, 'Goldenrod', FALSE, 3, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Little grebe', 311.00, 210
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    214, 'Orange', FALSE, 4, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Bahama pintail', 620.64, 207
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    215, 'Orange', FALSE, 9, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Andean goose', 894.22, 208
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    216, 'Yellow', FALSE, 7, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Waxbill, black-cheeked', 791.83, 
    212
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    217, 'Goldenrod', FALSE, 6, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Dusky gull', 251.84, 201
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    218, 'Orange', FALSE, 6, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'North American river otter', 512.58, 
    209
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    219, 'Yellow', FALSE, 9, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Horned puffin', 213.54, 212
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    220, 'Teal', FALSE, 6, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Turtle (unidentified)', 212.61, 
    205
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    221, 'Yellow', FALSE, 5, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Avocet, pied', 934.78, 208
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    222, 'Indigo', FALSE, 4, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Snake, tiger', 782.16, 207
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    223, 'Fuscia', FALSE, 3, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Woylie', 541.36, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    224, 'Pink', FALSE, 8, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Squirrel, red', 32.73, 209
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    225, 'Yellow', FALSE, 4, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'American bison', 173.97, 207
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    226, 'Green', FALSE, 2, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Red phalarope', 315.18, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    227, 'Mauv', FALSE, 2, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Pintail, white-cheeked', 59.78, 
    206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    228, 'Blue', FALSE, 5, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'White-fronted bee-eater', 797.76, 
    211
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    229, 'Blue', FALSE, 4, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Magpie, australian', 170.36, 204
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    230, 'Crimson', FALSE, 3, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Rhea, common', 291.42, 209
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    231, 'Mauv', FALSE, 9, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Hyena, spotted', 491.93, 207
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    232, 'Blue', FALSE, 3, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Stork, greater adjutant', 864.95, 
    209
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    233, 'Yellow', FALSE, 2, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Owl, great horned', 450.25, 202
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    234, 'Indigo', FALSE, 10, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Nilgai', 101.76, 204
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    235, 'Puce', FALSE, 6, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Chilean flamingo', 923.27, 209
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    236, 'Puce', FALSE, 3, 'F', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Falcon, peregrine', 509.44, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    237, 'Goldenrod', FALSE, 1, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'White-winged dove', 530.70, 210
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    238, 'Purple', FALSE, 8, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'American badger', 127.93, 206
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    239, 'Blue', FALSE, 1, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Black-tailed tree creeper', 838.71, 
    208
  );
/* INSERT QUERY */
INSERT INTO APP.MASCOTAENTITY(
  id, color, deleted, edad, genero, imagen, 
  nombre, precio, raza_id
) 
VALUES 
  (
    240, 'Yellow', FALSE, 6, 'M', 'http://thefamilysupportcenter.org/wp-content/uploads/2018/04/kisspng-dog-paw-cougar-drawing-clip-art-paw-prints-5ad0eba3679bd0.8414908415236412514244.png', 
    'Purple moorhen', 947.12, 202
  );
  
  
  
  
  
/*APP.CLIENTEENTITY*/

/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    201, 'ecobain0@weather.com', 'FALSE', 
    '4694 Clyde Gallagher Street', 
    ' 3589118285137736 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    202, 'lprium1@dell.com', 'FALSE', 
    '69 Crescent Oaks Place', ' 4905332611488508341 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    203, 'aruffell2@yellowpages.com', 
    'FALSE', '9357 Prentice Junction', 
    ' 3585983669980892 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    204, 'cpiche3@nature.com', 'FALSE', 
    '670 Bultman Court', ' 30540545514142 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    205, 'haurelius4@baidu.com', 'FALSE', 
    '27 Quincy Park', ' 3574991158992768 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    206, 'drossbrook5@photobucket.com', 
    'FALSE', '0473 Kropf Road', ' 3540335691429121 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    207, 'araye6@google.de', 'FALSE', 
    '82 Vidon Alley', ' 3565619323526457 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    208, 'hast7@oaic.gov.au', 'FALSE', 
    '03 Messerschmidt Alley', ' 5100140075661597 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    209, 'ntasseler8@photobucket.com', 
    'FALSE', '8 Rowland Terrace', ' 3558468973844375 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    210, 'wstrivens9@bravesites.com', 
    'FALSE', '01172 Judy Circle', ' 3530529869389164 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    211, 'kmaciunasa@foxnews.com', 'FALSE', 
    '78 Southridge Avenue', ' 3529554923256939 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    212, 'dhubballb@wix.com', 'FALSE', 
    '67780 Farmco Plaza', ' 5100137514052198 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    213, 'sshippc@va.gov', 'FALSE', '404 Schiller Center', 
    ' 3575110278381609 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    214, 'eschoulard@gravatar.com', 'FALSE', 
    '46458 Warbler Terrace', ' 5641822196672831920 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    215, 'posbaldestone@qq.com', 'FALSE', 
    '654 Oxford Street', ' 3565963830194460 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    216, 'sbatcockf@jugem.jp', 'FALSE', 
    '8171 Maryland Park', ' 3536078767407672 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    217, 'growcliffeg@naver.com', 'FALSE', 
    '511 Lyons Pass', ' 4026536619140580 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    218, 'pcaulketth@archive.org', 'FALSE', 
    '74019 Talmadge Point', ' 4026849825811542 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    219, 'mvanyarkini@themeforest.net', 
    'FALSE', '3 Dottie Lane', ' 5010122595211144 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    220, 'blokierj@mtv.com', 'FALSE', 
    '374 Transport Circle', ' 3588629403712425 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    221, 'mbusek@liveinternet.ru', 'FALSE', 
    '542 Mcguire Plaza', ' 5002350326717499 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    222, 'pwaterhousl@ebay.co.uk', 'FALSE', 
    '56 Lakewood Place', ' 4026825152540698 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    223, 'morrillm@slate.com', 'FALSE', 
    '6516 Bashford Parkway', ' 3582629787511274 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    224, 'bcoddingtonn@patch.com', 'FALSE', 
    '911 Mallory Street', ' 5018095342784555863 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    225, 'pspaldingo@usnews.com', 'FALSE', 
    '776 Doe Crossing Drive', ' 3532444878876482 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    226, 'bruoffp@about.com', 'FALSE', 
    '565 Buell Avenue', ' 3589893962018447 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    227, 'gmilsteadq@independent.co.uk', 
    'FALSE', '8142 Stuart Parkway', 
    ' 3538635624522601 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    228, 'mheggier@mlb.com', 'FALSE', 
    '2 Waubesa Circle', ' 3589841470580309 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    229, 'isteepless@dedecms.com', 'FALSE', 
    '1464 Northwestern Alley', ' 060464385047696800 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    230, 'kshacklet@bing.com', 'FALSE', 
    '8 David Alley', ' 3528120828051816 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    231, 'sroadknightu@nhs.uk', 'FALSE', 
    '20 Utah Court', ' 4221210289763 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    232, 'dajeanv@bandcamp.com', 'FALSE', 
    '78044 Nevada Hill', ' 5108757872406736 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    233, 'wdefeww@booking.com', 'FALSE', 
    '211 Waywood Street', ' 5407842239484697 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    234, 'nhabblex@berkeley.edu', 'FALSE', 
    '098 Granby Plaza', ' 491182169883574489 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    235, 'bcrombleholmey@e-recht24.de', 
    'FALSE', '7818 Fuller Crossing', 
    ' 3532889961994339 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    236, 'vhubbucksz@vkontakte.ru', 'FALSE', 
    '3387 Bluestem Junction', ' 3549742496387191 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    237, 'smaclaren10@cargocollective.com', 
    'FALSE', '3 Luster Court', ' 3549155606766016 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    238, 'lguilloton11@bing.com', 'FALSE', 
    '055 Mesta Hill', ' 670669000573627025 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    239, 'vcuniffe12@home.pl', 'FALSE', 
    '321 Granby Center', ' 5602211237630136 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    240, 'nbrideoke13@sakura.ne.jp', 
    'FALSE', '97 Hollow Ridge Street', 
    ' 3565669331994999 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    241, 'csimpkiss14@guardian.co.uk', 
    'FALSE', '81 Chive Pass', ' 30217617833573 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    242, 'upennoni15@virginia.edu', 'FALSE', 
    '72 Michigan Terrace', ' 3555769978451220 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    243, 'ksteeples16@cdbaby.com', 'FALSE', 
    '87 Vidon Court', ' 5010122413370288 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    244, 'bvlasenko17@photobucket.com', 
    'FALSE', '4245 Messerschmidt Avenue', 
    ' 3582244507320129 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    245, 'vgellibrand18@toplist.cz', 
    'FALSE', '819 Ridgeview Court', 
    ' 3578694943281069 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    246, 'ctuer19@nyu.edu', 'FALSE', '5 Canary Lane', 
    ' 3537715433784120 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    247, 'zspandley1a@weebly.com', 'FALSE', 
    '0153 Mesta Hill', ' 3569140384217098 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    248, 'washard1b@cbslocal.com', 'FALSE', 
    '0 5th Trail', ' 3535029475535313 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    249, 'rcristoforetti1c@flavors.me', 
    'FALSE', '935 Fallview Drive', ' 36000079265052 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    250, 'mrollings1d@redcross.org', 
    'FALSE', '85050 Eastwood Alley', 
    ' 6399110257981004 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    251, 'ggrinyov1e@edublogs.org', 'FALSE', 
    '0 Del Mar Way', ' 3575325312770266 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    252, 'promke1f@ftc.gov', 'FALSE', 
    '311 Di Loreto Road', ' 6759735343861382886 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    253, 'cscoates1g@salon.com', 'FALSE', 
    '02 Clove Lane', ' 6706126036652284 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    254, 'apilmer1h@angelfire.com', 'FALSE', 
    '65744 East Place', ' 3562043515505901 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    255, 'sbraunston1i@indiatimes.com', 
    'FALSE', '3 Ohio Drive', ' 374288856377410 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    256, 'parenson1j@mail.ru', 'FALSE', 
    '70646 Cherokee Alley', ' 3532701736958202 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    257, 'dsunock1k@webeden.co.uk', 'FALSE', 
    '37 Kim Point', ' 4844936584809906 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    258, 'kholdall1l@slideshare.net', 
    'FALSE', '68 Florence Crossing', 
    ' 3586329790362015 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    259, 'mrenols1m@google.fr', 'FALSE', 
    '3 Thackeray Junction', ' 589347694014394336 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    260, 'kdrewet1n@e-recht24.de', 'FALSE', 
    '77401 Daystar Junction', ' 4917002729128926 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    261, 'oblundan1o@creativecommons.org', 
    'FALSE', '8462 Havey Place', ' 3547476505928263 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    262, 'ljonczyk1p@sciencedaily.com', 
    'FALSE', '403 Basil Parkway', ' 5602234383126220 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    263, 'uaston1q@sphinn.com', 'FALSE', 
    '4637 Oakridge Center', ' 5100147168345830 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    264, 'dheakey1r@omniture.com', 'FALSE', 
    '0 Westridge Pass', ' 56022138647785124 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    265, 'twittrington1s@nbcnews.com', 
    'FALSE', '6 Utah Hill', ' 6381973592610954 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    266, 'maxon1t@europa.eu', 'FALSE', 
    '69063 Sutteridge Avenue', ' 30538926456602 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    267, 'bbryce1u@indiegogo.com', 'FALSE', 
    '86542 Paget Parkway', ' 3553515488032352 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    268, 'slamberth1v@reference.com', 
    'FALSE', '96131 Gina Street', ' 3547691389927831 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    269, 'rgehrels1w@linkedin.com', 'FALSE', 
    '73729 Washington Drive', ' 3567482755487070 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    270, 'astatersfield1x@taobao.com', 
    'FALSE', '972 Redwing Avenue', ' 5048373750976718 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    271, 'hmillichap1y@hexun.com', 'FALSE', 
    '45998 North Junction', ' 675967042084188947 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    272, 'lsommerly1z@google.cn', 'FALSE', 
    '83 Dahle Court', ' 6767153390087983290 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    273, 'llawrenceson20@blogtalkradio.com', 
    'FALSE', '06 Meadow Ridge Place', 
    ' 6334953568737898437 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    274, 'ppavy21@canalblog.com', 'FALSE', 
    '77 Sundown Parkway', ' 675910363555675677 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    275, 'rhalloway22@msn.com', 'FALSE', 
    '39 Dennis Plaza', ' 374283338222599 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    276, 'dhurd23@technorati.com', 'FALSE', 
    '624 Oak Hill', ' 676728080783251275 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    277, 'cmcnally24@webmd.com', 'FALSE', 
    '89 Mockingbird Center', ' 56022348427098537 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    278, 'aclift25@ebay.co.uk', 'FALSE', 
    '538 Pankratz Crossing', ' 4913653600395549 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    279, 'pschuricht26@t-online.de', 
    'FALSE', '1942 Oak Crossing', ' 3552548194099630 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    280, 'hfryers27@last.fm', 'FALSE', 
    '9 Holmberg Point', ' 6386547056616072 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    281, 'sbrasseur28@china.com.cn', 
    'FALSE', '8621 Memorial Road', ' 5610687988417692 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    282, 'gvallery29@tamu.edu', 'FALSE', 
    '4736 Kensington Pass', ' 5528697599399602 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    283, 'gmunson2a@opera.com', 'FALSE', 
    '8005 Eliot Junction', ' 5602230877395264576 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    284, 'sbygreaves2b@pagesperso-orange.fr', 
    'FALSE', '70 Fairfield Parkway', 
    ' 5602211369462217 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    285, 'klevison2c@cnbc.com', 'FALSE', 
    '1531 Lake View Alley', ' 374283063810527 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    286, 'wlister2d@china.com.cn', 'FALSE', 
    '3997 Independence Crossing', ' 3533601346466209 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    287, 'ccoste2e@elegantthemes.com', 
    'FALSE', '01 Knutson Trail', ' 5010126855724837 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    288, 'orubrow2f@telegraph.co.uk', 
    'FALSE', '2 Sauthoff Lane', ' 5451224070965578 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    289, 'agatch2g@jiathis.com', 'FALSE', 
    '267 Hanover Hill', ' 3581861296790617 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    290, 'sdougliss2h@time.com', 'FALSE', 
    '2 Northwestern Hill', ' 3559646557378857 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    291, 'wmcleod2i@smh.com.au', 'FALSE', 
    '6562 Nevada Avenue', ' 56022107564962214 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    292, 'iverne2j@cloudflare.com', 'FALSE', 
    '85250 Namekagon Way', ' 3541862217443780 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    293, 'cscotting2k@europa.eu', 'FALSE', 
    '2 Heffernan Pass', ' 5100145512392185 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    294, 'uabbes2l@apache.org', 'FALSE', 
    '257 Cottonwood Street', ' 493633811569748629 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    295, 'amawford2m@cbslocal.com', 'FALSE', 
    '761 Golf Way', ' 3528617675943714 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    296, 'edubois2n@elegantthemes.com', 
    'FALSE', '217 Melrose Road', ' 67062297050682372 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    297, 'amace2o@dedecms.com', 'FALSE', 
    '873 Mandrake Parkway', ' 201763357921790 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    298, 'ajuris2p@hexun.com', 'FALSE', 
    '85 Towne Way', ' 3555025008581712 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    299, 'dlatty2q@techcrunch.com', 'FALSE', 
    '38 Pleasure Crossing', ' 5602259312218673 '
  );
/* INSERT QUERY */
INSERT INTO APP.CLIENTEENTITY(
  id, correo, deleted, direccion, tarjetacreditoregistrada
) 
VALUES 
  (
    300, 'mbeckingham2r@infoseek.co.jp', 
    'FALSE', '91615 Stuart Way', ' 3575794910901781 '
  );
  

  
  
  
  
/*HISTORIAENTITY*/


/* INSERT QUERY */
INSERT INTO APP.HISTORIAENTITY(
  id, deleted, foto, texto, mascota_id
) 
VALUES 
  (
    201, FALSE, 'https://cdngeneral.rentcafe.com/dmslivecafe/UploadedImages/6dceca2c-c9a8-4963-abfc-7d9ea9663c7e.jpg', 
    'Guaifenesin', 201
  );
/* INSERT QUERY */
INSERT INTO APP.HISTORIAENTITY(
  id, deleted, foto, texto, mascota_id
) 
VALUES 
  (
    202, FALSE, 'https://cdngeneral.rentcafe.com/dmslivecafe/UploadedImages/6dceca2c-c9a8-4963-abfc-7d9ea9663c7e.jpg', 
    'BENZOYL PEROXIDE', 202
  );
/* INSERT QUERY */
INSERT INTO APP.HISTORIAENTITY(
  id, deleted, foto, texto, mascota_id
) 
VALUES 
  (
    203, FALSE, 'https://cdngeneral.rentcafe.com/dmslivecafe/UploadedImages/6dceca2c-c9a8-4963-abfc-7d9ea9663c7e.jpg', 
    'lidocaine hydrochloride anhydrous and epinephrine', 
    203
  );
/* INSERT QUERY */
INSERT INTO APP.HISTORIAENTITY(
  id, deleted, foto, texto, mascota_id
) 
VALUES 
  (
    204, FALSE, 'https://cdngeneral.rentcafe.com/dmslivecafe/UploadedImages/6dceca2c-c9a8-4963-abfc-7d9ea9663c7e.jpg', 
    'Pioglitazone', 204
  );
/* INSERT QUERY */
INSERT INTO APP.HISTORIAENTITY(
  id, deleted, foto, texto, mascota_id
) 
VALUES 
  (
    205, FALSE, 'https://cdngeneral.rentcafe.com/dmslivecafe/UploadedImages/6dceca2c-c9a8-4963-abfc-7d9ea9663c7e.jpg', 
    'Acetaminophen', 205
  );
-- Tabla de categorías
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Guitarras", "Guitarras", "https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Clasica.jpg");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Bajos", "Bajos", "https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Clasica.jpg");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Baterías y Percusion", "Baterías y Percusion");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Piano y Teclado", "Piano y Teclado", "https://img-c9-g2-bucket.s3.amazonaws.com/Teclado-Casio4.webp");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Trompetas", "Baterías y Percusion", "https://img-c9-g2-bucket.s3.amazonaws.com/Bateria.jpg");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Accesorios", "Accesorios", "https://img-c9-g2-bucket.s3.amazonaws.com/tester.jpg");
INSERT INTO CATEGORIAS (titulo, descripcion, url_imagen) VALUES ("Violines", "Violines", "https://img-c9-g2-bucket.s3.amazonaws.com/Violin-Cremona.jpg");

-- Tabla de características
INSERT INTO CARACTERISTICAS (nombre, url_icono) VALUES ("Nuevo", "https://img-c9-g2-bucket.s3.amazonaws.com/caracteristicas-nuevo.jpeg");
INSERT INTO CARACTERISTICAS (nombre, url_icono) VALUES ("Oferta", "https://img-c9-g2-bucket.s3.amazonaws.com/caracteristica-oferta.jpeg");
INSERT INTO CARACTERISTICAS (nombre, url_icono) VALUES ("Recomendado para principiantes", "https://img-c9-g2-bucket.s3.amazonaws.com/caracteristica-principiantes.jpeg");
INSERT INTO CARACTERISTICAS (nombre, url_icono) VALUES ("Recibe hoy", "https://img-c9-g2-bucket.s3.amazonaws.com/caracteristicas-recibe-hoy.jpeg");
INSERT INTO CARACTERISTICAS (nombre, url_icono) VALUES ("Usado", "https://img-c9-g2-bucket.s3.amazonaws.com/caracteristicas-usuado.jpeg")

INSERT INTO productos (nombre, descripcion, categoria_id)
VALUES ("Guitarra Clasica 1/4 natural", "Guitarra clásica tamaño 1/4, recomendada especialmente para niños de (aprox.) entre 4 a 6 años.", 1);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Micrófono Condensador Behringer Bv44 Usb", "El micrófono BV44 es el sueño de un transmisor en vivo hecho realidad! Este micrófono de condensador de calidad profesional tiene un diseño de ruido ultra bajo.", 6);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Bateria Black Galaxy Sparkle C/hardware", "Batería Mapex Venus VE5044FTCVH 5 cuerpos.", 3);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Guitarra Electrica Translucent Blue", "Perfecta como primera guitarra o como la última guitarra que necesitarás, la SE Standard 24 recrea fielmente el diseño fundacional de la Custom 24.", 1);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Guitarra Electrica Epiphone 1959", "Fabricada en asociación con Gibson™ Custom Shop, la edición limitada de 1959 Les Paul™ Standard es la impresionante recreación de Epiphone del raro clásico vintage.", 1);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Amplificador Guitarra Memphis Ak15 15w Acústica", "Amplificador Guitarra Memphis Ak15 15W para guitarra electroacústica.", 6);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Tester Behringer Ct200", "Asegúrese de que todos los cables de su arsenal funcionen correctamente con el invaluable comprobador de cables CT200.", 6);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Piano Digital Yamaha Csp170b C/banqueta", "Clavinova es una innovadora gama de pianos digitales en continua evolución, siempre buscando proporcionar el tacto y el sonido de un piano de cola, que es la máxima expresión de la excelencia en piano.", 4);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Piano Digital Casio Cdps100 Bk", "La nueva serie CDP-S de teclados Casio inspira con características destacadas: nueva acción de martillo, nueva generación de sonido.", 4);

INSERT INTO productos (nombre, descripcion, categoria_id) VALUES ("Violin Cremona Sv130 4/4", "Saga siempre ha sido pionera en lo que respecta a la fabricación de violines de calidad para satisfacer las demandas del creciente mercado estudiantil.", 7);

--Imagen Producto 1
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Clasica.jpg", 1);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Clasica2.jpg", 1);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Clasica3.jpg", 1);

--Imagen Producto 2
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Microfono.jpg", 2);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Microfono2.webp", 2);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Microfono3.webp", 2);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Microfono4.webp", 2);

--Imagen Producto 3
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Bateria.jpg", 3);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Bateria2.webp", 3);

--Imagen Producto 4
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica.jpg", 4);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica2.webp", 4);

INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica3.webp", 4);


--Imagen Producto 5
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/guitarra-electrica-epiphone.jpg", 5);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica-Epiphone.webp", 5);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica-Epiphone2.webp", 5);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Guitarra-Electrica-Epiphone3.webp", 5);

--Imagen Producto 6
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Amplificador.jpg", 6);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Amplificador.webp", 6);

--Imagen Producto 7
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/tester.jpg", 7);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Tester2.webp", 7);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Tester3.webp", 7);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Tester4.webp", 7);

--Imagen Producto 8
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Piano.jpg", 8);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Piano2.webp", 8);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Piano3.webp", 8);

--Imagen Producto 9
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Teclado-Casio1.webp", 9);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Teclado-Casio2.webp", 9);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Teclado-Casio3.webp", 9);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Teclado-Casio4.webp", 9);

--Imagen Producto 10
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Violin-Cremona.jpg", 10);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Violin1.webp", 10);
INSERT INTO imagenes (url, producto_id) VALUES ("https://img-c9-g2-bucket.s3.amazonaws.com/Violin2.webp", 10);






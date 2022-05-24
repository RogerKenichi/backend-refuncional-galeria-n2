
DROP DATABASE galeria_n2;
CREATE DATABASE galeria_n2;
USE galeria_n2;


CREATE TABLE tb_autor (
    SK_autor_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    autor_nome VARCHAR(512) NOT NULL,
    autor_descricao VARCHAR(1024),
    cod_autor_img_preview INT
);

CREATE TABLE tb_midia_social_tipo (
    SK_midia_social_tipo_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    midia_social VARCHAR(128),
    logo_path VARCHAR(1024)
);

CREATE TABLE tb_autor_midia_social (
    FK_autor_id INT NOT NULL,
    FK_midia_social_tipo_id INT NOT NULL,
    midia_social_link VARCHAR(512),
    midia_social_tag VARCHAR(128),
    FOREIGN KEY (FK_autor_id) REFERENCES tb_autor(SK_autor_id) ON DELETE CASCADE,
    FOREIGN KEY (FK_midia_social_tipo_id) REFERENCES tb_midia_social_tipo(SK_midia_social_tipo_id) ON DELETE CASCADE
);

INSERT INTO tb_autor (autor_nome, autor_descricao) VALUES ('Autor Desconhecido', 'Autor desconhecido.');

INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Facebook');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Twitter');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Instagram');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Pixiv');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Deviantart');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Flickr');
INSERT INTO tb_midia_social_tipo (midia_social) VALUES ('Reddit');



CREATE TABLE tb_imagem (
    SK_imagem_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FK_autor_id INT NOT NULL,
    img_path VARCHAR(1024) NOT NULL,
    img_descricao VARCHAR(1024),
    data_envio DATETIME, 
    FOREIGN KEY (FK_autor_id) REFERENCES tb_autor(SK_autor_id)
);

CREATE TABLE tb_tag  (
    SK_tag_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FK_tag_id INT,
    tag VARCHAR(128) NOT NULL,
    cod_tag_img_preview INT
);

CREATE TABLE tb_imagemxtags (
    FK_imagem_id INT NOT NULL,
    FK_tag_id INT NOT NULL,
    FOREIGN KEY (FK_imagem_id) REFERENCES tb_imagem(SK_imagem_id) ON DELETE CASCADE,
    FOREIGN KEY (FK_tag_id) REFERENCES tb_tag(SK_tag_id) ON DELETE CASCADE
);



-- STORED PROCEDURES
DELIMITER //

CREATE PROCEDURE sp_insert_tag(IN p_tag varchar(128), IN p_cod_img_preview int)
BEGIN
    INSERT INTO tb_tag (tag, cod_tag_img_preview) VALUES (p_tag, p_cod_img_preview);
END //

CREATE PROCEDURE sp_insert_subtag(IN p_tag varchar(128), IN p_tagpai_id int, IN p_cod_img_preview int)
BEGIN
    INSERT INTO tb_tag (tag, FK_tag_id, cod_tag_img_preview) VALUES (p_tagpai_id, p_cod_img_preview);
END //

CREATE PROCEDURE sp_insert_autor(IN p_autor_nome varchar(512), IN p_autor_descricao varchar(1024), IN p_cod_img_preview int)
BEGIN
    INSERT INTO tb_autor (autor_nome, autor_descricao, cod_autor_img_preview) VALUES (p_autor_nome, p_autor_descricao, p_cod_img_preview);
END //

CREATE PROCEDURE sp_insert_midia_social_tipo(IN p_midia_social varchar(128), IN p_logo_path varchar(1024))
BEGIN
    INSERT INTO tb_midia_social_tipo (midia_social, logo_path) VALUES (p_midia_social, p_logo_path);
END //

CREATE PROCEDURE sp_insert_autor_midia_social (IN p_FK_autor_id int, IN p_FK_midia_social_tipo_id int, IN p_midia_social_link varchar(512), IN p_midia_social_tag varchar(128))
BEGIN
    INSERT INTO tb_autor_midia_social (FK_autor_id, FK_midia_social_tipo_id, midia_social_link, midia_social_tag) VALUES (p_FK_autor_id, p_FK_midia_social_tipo_id, p_midia_social_link, p_midia_social_tag);
END //

CREATE PROCEDURE sp_insert_imagem(IN p_FK_autor_id int, IN p_img_path varchar(1024), IN p_img_descricao varchar(1024), IN p_data_envio datetime)
BEGIN
    INSERT INTO tb_imagem (FK_autor_id, img_path, img_descricao, data_envio) VALUES (p_FK_autor_id, p_img_path, p_img_descricao, p_data_envio);
END //

CREATE PROCEDURE sp_insert_imagemxtags(IN p_imagem_id int, IN p_tag_id int)
BEGIN
    INSERT INTO tb_imagemxtags (FK_imagem_id, FK_tag_id) VALUES (p_imagem_id, p_tag_id);
END //




CREATE PROCEDURE sp_delete_tag(IN p_tag_id int)
BEGIN
    DELETE FROM tb_tag WHERE SK_tag_id = p_tag_id;
END //

CREATE PROCEDURE sp_delete_autor(IN p_autor_id int)
BEGIN
    DELETE FROM tb_autor WHERE SK_autor_id = p_autor_id;
END //

CREATE PROCEDURE sp_delete_imagem(IN p_imagem_id int)
BEGIN
    DELETE FROM tb_imagem WHERE SK_imagem_id = p_imagem_id;
END //

CREATE PROCEDURE sp_delete_imagemxtags_imagem(IN p_imagem_id int, IN p_tag_id int)
BEGIN
    DELETE FROM tb_imagemxtags WHERE FK_imagem_id = p_imagem_id AND FK_tag_id = p_tag_id;
END //




CREATE PROCEDURE sp_select_tag(IN p_tag_id int)
BEGIN
    SELECT * FROM tb_tag WHERE SK_tag_id = p_tag_id;
END //

CREATE PROCEDURE sp_select_tags()
BEGIN
    SELECT * FROM tb_tag;
END //

CREATE PROCEDURE sp_select_autor(IN p_autor_id int)
BEGIN
    SELECT * FROM tb_autor WHERE SK_autor_id = p_autor_id;
END //

CREATE PROCEDURE sp_select_autores()
BEGIN
    SELECT * FROM tb_autor;
END //

CREATE PROCEDURE sp_select_imagem(IN p_imagem_id int)
BEGIN
    SELECT * FROM tb_imagem WHERE SK_imagem_id = p_imagem_id;
END //

CREATE PROCEDURE sp_select_imagens()
BEGIN
    SELECT * FROM tb_imagem;
END //

CREATE PROCEDURE sp_select_imagemxtags(IN p_imagem_id int)
BEGIN
    SELECT * FROM tb_imagemxtags WHERE FK_imagem_id = p_imagem_id;
END //

CREATE PROCEDURE sp_select_imagemxtags_especifico(IN p_imagem_id int, IN p_tag_id int)
BEGIN
    SELECT * FROM tb_imagemxtags WHERE FK_imagem_id = p_imagem_id AND FK_tag_id = p_tag_id;
END //



CREATE PROCEDURE sp_update_autor(IN p_autor_id int, p_autor_nome_novo varchar(512), IN p_autor_descricao_novo varchar(1024), IN p_cod_img_preview int)
BEGIN
    UPDATE tb_autor SET autor_nome = p_autor_nome_novo, autor_descricao = p_autor_descricao_novo, cod_autor_img_preview = p_cod_img_preview WHERE SK_autor_id = p_autor_id;
END //

CREATE PROCEDURE sp_update_tag(IN p_tag_id int, p_fk_id_novo int, IN p_tag_descricao_novo varchar(128), IN p_cod_tag_img_preview int)
BEGIN
    UPDATE tb_tag SET FK_tag_id = p_fk_id_novo, tag = p_tag_descricao_novo, cod_tag_img_preview = p_cod_tag_img_preview WHERE SK_tag_id = p_tag_id;
END //



DELIMITER ;
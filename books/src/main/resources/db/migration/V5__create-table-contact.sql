
CREATE TABLE `tb_contact`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `phone_number` VARCHAR(30) NOT NULL,
    `editora_id` BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY (editora_id) REFERENCES tb_publisher(id)
);
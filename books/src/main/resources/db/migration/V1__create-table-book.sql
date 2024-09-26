

CREATE TABLE `tb_book`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `book_year` INT NOT NULL,
    `author` VARCHAR(100) NOT NULL,
    `book_value` DECIMAL(10,2) NOT NULL,
    `num_pages` VARCHAR(100) NOT NULL,
    `genre` VARCHAR(100) NOT NULL,
    `rating` DOUBLE NOT NULL,
    `book_active` BOOLEAN NOT NULL DEFAULT TRUE,
    `publishing_company` VARCHAR(100) NOT NULL,
    `description` TEXT NOT NULL

);

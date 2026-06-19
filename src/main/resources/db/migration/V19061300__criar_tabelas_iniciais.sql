create table tb_usuario (
    id int auto_increment primary key,
    nome varchar(100) not null,
    email varchar(100) not null
);

create table tb_jogo (
    id int auto_increment primary key,
    titulo varchar(150) not null,
    preco_sugerido decimal(10, 2) not null, -- mapear com o BigDecimal
    status varchar(50) not null
)
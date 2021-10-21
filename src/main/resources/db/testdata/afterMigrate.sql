set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha(id, nome)values(1, 'Tailandesa');
insert into cozinha(id, nome)values(2, 'Indiana');
insert into cozinha(id, nome)values(3, 'Argentina');
insert into cozinha(id, nome)values(4, 'Brasileira');


insert into estado(id, nome)values(1, 'Goiás');
insert into estado(id, nome)values(2, 'Minas Gerais');
insert into estado(id, nome)values(3, 'São Paulo');
insert into estado(id, nome)values(4, 'Ceará');

insert into cidade(id, nome, estado_id)values(1, 'Rio Verde', 1);
insert into cidade(id, nome, estado_id)values(2, 'Uberlandia', 2);
insert into cidade(id, nome, estado_id)values(3, 'Belo Horizonte', 2);
insert into cidade(id, nome, estado_id)values(4, 'São Paulo', 3);
insert into cidade(id, nome, estado_id)values(5, 'Fortaleza', 4);

insert into restaurante(id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, data_cadastro, data_atualizacao, ativo)values(1, 'Comida Caseira', 5.5, 1, 1, '75800-000', 'Rua Santos Dumont', '1657', 'Próximo a clinica Plena', 'Samuel Ganham', utc_timestamp, utc_timestamp, true);
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo)values(2, 'Itadakimazu', 9.8, 2, utc_timestamp, utc_timestamp, true);
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo)values(3, 'Mae Joana', 13.5, 2, utc_timestamp, utc_timestamp, true);

insert into forma_Pagamento(descricao)values('Dinheiro');
insert into forma_Pagamento(descricao)values('Cartao de Débito A vista');
insert into forma_Pagamento(descricao)values('Cartao de Crédito 12x');

insert into permissao(nome, descricao)values('buscar restaurantes', 'permite consultar restaurantes');
insert into permissao(nome, descricao)values('alterar restaurantes', 'permite alterar restaurantes');
insert into permissao(nome, descricao)values('exclui restaurantes', 'permite exclui restaurantes');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,	1),(1,	2),(1,	3),(2,	1),(3,	2),(3,	3);

insert into produto(nome, descricao, preco, ativo, restaurante_id)values('Isca de peixe', 'filézinhos de peixe fritos', 33, 1, 1);

insert into grupo (nome) values ('administrador'), ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into grupo_permissao(grupo_id, permissao_id)values(1, 1), (1, 2), (1, 3), (2, 1), (2, 2);


insert into usuario (nome, email, senha, data_cadastro) values
    ('Ricardo', 'ricardolima.ti@gmail.com', 'Ricardo0511', utc_timestamp),
    ('Juliano', 'juliano@gmail.com', '12356', utc_timestamp),
    ('João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
    ('Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
    ('José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
    ('Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);

insert into usuario_grupo(usuario_id, grupo_id)values(1, 1), (2, 2);
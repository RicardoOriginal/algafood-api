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
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

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
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

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

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into forma_pagamento(descricao)values('Dinheiro');
insert into forma_pagamento(descricao)values('Cartao de Débito A vista');
insert into forma_pagamento(descricao)values('Cartao de Crédito 12x');

insert into permissao(nome, descricao)values('buscar restaurantes', 'permite consultar restaurantes');
insert into permissao(nome, descricao)values('alterar restaurantes', 'permite alterar restaurantes');
insert into permissao(nome, descricao)values('exclui restaurantes', 'permite exclui restaurantes');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,	1),(1,	2),(1,	3),(2,	1),(3,	2),(3,	3);

insert into produto(nome, descricao, preco, ativo, restaurante_id)values('Isca de peixe', 'filézinhos de peixe fritos', 33, 1, 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id)values('File de merlusa', 'filézinhos de merlusa fritos', 33, 1, 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id)values('File de tilapia', 'filézinhos de tilapia fritos', 35, 1, 2);
insert into produto(nome, descricao, preco, ativo, restaurante_id)values('Cerveja', 'Cerveja', 10, 1, 2);
insert into produto(nome, descricao, preco, ativo, restaurante_id)values('refrigerante', 'Coca Cola', 11, 1, 2);
insert into produto(nome, descricao, preco, ativo, restaurante_id)values('Acaí', 'Acaí na tigela', 18, 1, 2);

insert into grupo (nome) values ('administrador'), ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

insert into grupo_permissao(grupo_id, permissao_id)values(1, 1), (1, 2), (1, 3), (2, 1), (2, 2);

insert into usuario (nome, email, senha, data_cadastro) values
                                                            ('Ricardo', 'ricardolima.ti@gmail.com', 'Ricardo0511', utc_timestamp),
                                                            ('Juliano', 'juliano@gmail.com', '12356', utc_timestamp),
                                                            ('João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
                                                            ('Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
                                                            ('José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
                                                            ('Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1,	1),(1, 2),(1, 3),(2, 1),(3,	2),(3, 3);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, sub_total, taxa_frete, valor_total)
values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
        'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, sub_total, taxa_frete, valor_total)
values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');
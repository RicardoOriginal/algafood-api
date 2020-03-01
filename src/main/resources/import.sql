insert into cozinha(id, nome)values(1, 'Tailandesa')
insert into cozinha(id, nome)values(2, 'Indiana')

insert into restaurante(nome, taxa_frete, cozinha_id)values('Comida Caseira', 5.5, 1)
insert into restaurante(nome, taxa_frete, cozinha_id)values('Itadakimazu', 9.8, 2)
insert into restaurante(nome, taxa_frete, cozinha_id)values('Mae Joana', 13.5, 2)

insert into forma_Pagamento(descricao)values('Dinheiro')
insert into forma_Pagamento(descricao)values('Cartao de Débito A vista')
insert into forma_Pagamento(descricao)values('Cartao de Crédito 12x')

insert into permissao(nome, descricao)values('buscar restaurantes', 'permite consultar restaurantes')
insert into permissao(nome, descricao)values('alterar restaurantes', 'permite alterar restaurantes')
insert into permissao(nome, descricao)values('exclui restaurantes', 'permite exclui restaurantes')

insert into estado(nome)values('Goiás')
insert into estado(nome)values('Minas Gerais')

insert into cidade(nome, estado_id)values('Rio Verde', 1)
insert into cidade(nome, estado_id)values('Uberlandia', 2)
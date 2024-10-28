
-- Inserts  TB_TIPO_USUARIO
INSERT INTO tb_tipo_usuario(tipo_usuario) VALUES (0)
INSERT INTO tb_tipo_usuario(tipo_usuario) VALUES (1)
INSERT INTO tb_tipo_usuario(tipo_usuario) VALUES (2)
INSERT INTO tb_tipo_usuario(tipo_usuario) VALUES (3)

-- Inserts  TB_USUARIO   
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Gustavo Guanabara', 'gustavo.guanabara@gmail.com', '(21) 2572-0268', 'AlfredoELindo', 2)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Fernanda Kipper', 'fernanda.kipper@hotmail.com', '(51) 9 8645-9635', 'EuAmooItau95', 2)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Nelio Alves', 'nelio.alves@gmail.com', '(34) 9 8948-1670', 'NelinhoDelas69', 2)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Edemilton Galindo', 'edemilton.galindo@hotmail.com', '(75) 9 9974-5974', 'Edemiltonefera85', 2)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('André Rafael', 'andre.origamid@gmail.com', '(21) 2584-7664', 'OrigamidEOFront', 2)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Soraya Thronicke', 'sen.sorayathronicke@senado.leg.br', '(61) 3303-1775', 'DeusdeteELindaEPantaneira', 1)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Nicole Bahls', 'producao.nicolebahls@hotmail.com', '(43) 8409-3939', 'C0rag3mN3?', 1)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Narcisa Tamborindeguy', 'narcisat@uol.com.br', '(21) 9 9678-8480', 'AiQueTudo#', 1)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Hebe Maria Monteiro de Camargo Ravagnani', 'hebe.camargo@gmail.com', '(11) 9 9846-5940', 'UmBeijoQuerida', 1)
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Nikolas Ferreira de Oliveira', 'dep.nikolasferreira@camara.leg.br', ' (61) 3215-5743', 'Beijei0B0lsonaro', 1)

-- Inserts  TB_DISCPROFTURMA
INSERT INTO tb_discprofturma(usuario_id) VALUES (1)
INSERT INTO tb_discprofturma(usuario_id) VALUES (2)
INSERT INTO tb_discprofturma(usuario_id) VALUES (2)

-- Inserts  TB_ATIVIDADE
INSERT INTO tb_atividade(titulo, data_inicio, data_entrega, descricao, discprofturma_id) VALUES ('Trabalho de campo em Boipeba', '2007-12-03T10:15:30', '2007-12-10T15:40:00', 'Trabalho de campo voltado a disciplina de Geografia', 2)

--INSERT INTO tb_atividade(titulo, dataEntrega, descricao, comentarios, discprofturma_id) VALUES ()

--INSERT INTO tb_comentario(conteudo, dataHora, usuario, atividade) VALUES ('Odiei essa atividade! Trabalhosa demais. Coisa de professor que não tem o que fazer! Vá caçar um serviço e pare de passar Para casa infeliz!', '2024-09-12T10:15:30', 10)
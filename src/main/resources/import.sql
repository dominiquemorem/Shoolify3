
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
INSERT INTO tb_usuario(nome, email, telefone, senha, tipousuario_id) VALUES ('Camila Antonia Amaranta Vallejo Dowling', 'camila.vallejo@congreso.cl', ' +56 2 2690 4000', 'UniversisDeChileResiste', 1)

-- Inserts  TB_DISCPROFTURMA
INSERT INTO tb_discprofturma(usuario_id) VALUES (1)
INSERT INTO tb_discprofturma(usuario_id) VALUES (2)
INSERT INTO tb_discprofturma(usuario_id) VALUES (3)
INSERT INTO tb_discprofturma(usuario_id) VALUES (4)
INSERT INTO tb_discprofturma(usuario_id) VALUES (5)
INSERT INTO tb_discprofturma(usuario_id) VALUES (6)
INSERT INTO tb_discprofturma(usuario_id) VALUES (7)

-- Inserts  TB_ATIVIDADE
INSERT INTO tb_atividade(titulo, data_inicio, data_entrega, descricao, discprofturma_id) VALUES ('Trabalho de campo em Boipeba', '2007-12-03T10:15:30', '2007-12-10T15:40:00', 'Trabalho de campo voltado a disciplina de Geografia', 2)
INSERT INTO tb_atividade(titulo, data_inicio, data_entrega, descricao, discprofturma_id) VALUES ('Visita aos Canyons do São Francisco', '2024-11-24T10:15:30', '2024-11-27T15:40:00', 'Construir um relatório sobre a visita aos canions com textos e fotos', 7)

-- Inserts  TB_DISCIPLINA
INSERT INTO tb_disciplina(nome, img_url) VALUES ('Matemática', 'https://get.pxhere.com/photo/distance-measure-ruler-label-protractor-mathematics-count-exactly-inch-measuring-instrument-729445.jpg')
INSERT INTO tb_disciplina(nome, img_url) VALUES ('Geografia', 'https://get.pxhere.com/photo/ocean-land-europe-journey-color-africa-map-globe-world-geography-egypt-world-map-art-background-earth-morocco-algeria-758013.jpg')

-- Inserts  TB_COMENTARIO
INSERT INTO tb_comentario(conteudo, data_hora, usuario_id, atividade_id) VALUES ('Eu amei essa atividade!', '2023-08-05T10:15:30', 5, 1)
INSERT INTO tb_comentario(conteudo, data_hora, usuario_id, atividade_id) VALUES ('Infelizmente eu e meu filho não pudemos comparecer!', '2022-07-07T15:15:00', 2, 2)

-- Inserts  TB_EVENTO
INSERT INTO tb_evento(nome, data_inicio, hora_inicio, descricao) VALUES ('Arraiá da Escolinha Criança Feliz', '2024-12-07', '09:30:00', 'Canjica, pamonha, brincadeiras e muita diversão na creche criança feliz!')

-- Inserts  TB_SUGESTAOLIVRO
INSERT INTO tb_sugestaolivro(nome, capa, link_livros, discprofturma_id) VALUES ('O País dos Dedos Gordos', 'https://checkout.loyola.com.br/resizer/view/false/false/true/false/3704.jpg', 'https://www.livrarialoyola.com.br/produto/o-pais-dos-dedos-gordos-218265', 1)
INSERT INTO tb_sugestaolivro(nome, capa, link_livros, discprofturma_id) VALUES ('O Menino Maluquinho', 'https://s2-g1.glbimg.com/YGeT7EO7yAH4yYc9BqwR7xfrQqk=/0x0:300x400/1000x0/smart/filters:strip_icc()/s.glbimg.com/jo/g1/f/original/2010/08/20/meninomaluquinho300x400.jpg', 'https://www.amazon.com.br/Menino-Maluquinho-Ziraldo-Alves-Pinto/dp/8506055105?source=ps-sl-shoppingads-lpcontext&ref_=fplfs&psc=1&smid=A373VKZRHHBSYL', 3)



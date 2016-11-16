CREATE DATABASE BreakingBlocks

create table jogador(
	id int primary key NOT NULL AUTO_INCREMENT,
	nome varchar(30),
    senha varchar(30)
)

create table pontuacao(
	id int primary key NOT NULL AUTO_INCREMENT,
    jogadorId int,
    pontosObtidos int,
    data_gameover timestamp ,
    foreign key (jogadorId) references jogador(id)
)


select * from jogador
select * from pontuacao
delete from pontuacao where jogadorNome = 'thiago'
INSERT INTO pontuacao (jogadorNome, pontosObtidos) values(thiago, 10)

SELECT jogador.nome, pontuacao.pontosobtidos FROM pontuacao  INNER JOIN jogador ON pontuacao.jogadorId = jogador.id ORDER BY pontosObtidos DESC
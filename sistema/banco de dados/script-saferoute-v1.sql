CREATE DATABASE SAFEROUTE;

USE SAFEROUTE;

CREATE TABLE usuaria(
	id INT PRIMARY KEY IDENTITY(1,1)
	, foto VARCHAR(MAX)
	, selfie VARCHAR(MAX)
	, nome VARCHAR(255)
	, dataNascimento DATE
	, numeroTelefone VARCHAR(14)
	, email VARCHAR(100)
	, senha VARCHAR(50)
	, receberDicas BIT NOT NULL
	, isValida BIT NOT NULL
);

CREATE TABLE documento(
	id INT PRIMARY KEY IDENTITY(1,1)
	, tipo VARCHAR(3)
	, numeracao VARCHAR(50)
	, fotoFrente VARCHAR(MAX)
	, fotoVerso VARCHAR(MAX)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE endereco(
	id INT PRIMARY KEY IDENTITY(1,1)
	, descricao VARCHAR(100)
	, logradouro VARCHAR(50)
	, cep VARCHAR(9)
	, numero VARCHAR(30)
	, estado VARCHAR(50)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE contatoConfianca(
	id INT PRIMARY KEY IDENTITY(1,1)
	, nome VARCHAR(45)
	, email VARCHAR(45)
	, numeroTelefone VARCHAR(14)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE viagem(
	id INT PRIMARY KEY IDENTITY(1,1)
	, origem VARCHAR(150)
	, destino VARCHAR(150)
);

CREATE TABLE dataViagem(
	id INT PRIMARY KEY IDENTITY(1,1)
	, diaSemana VARCHAR(3)
	, horario TIME
	, idViagem INT FOREIGN KEY REFERENCES viagem(id)
);

CREATE TABLE grupoLocomocao(
	id INT PRIMARY KEY IDENTITY(1,1)
	, nome VARCHAR(50)
	, dataCriacaoGrupo DATETIME
	, isPrivado BIT NOT NULL
    , idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
    , idViagem INT FOREIGN KEY REFERENCES viagem(id)
);

CREATE TABLE mensagem(
	id INT PRIMARY KEY IDENTITY(1,1)
	, conteudo VARCHAR(300)
	, dataEnvio DATETIME
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
	, idGrupoLocomocao INT FOREIGN KEY REFERENCES grupoLocomocao(id)
);

CREATE TABLE grupoLocomocao_usuaria(
	idGrupoLocomocao INT FOREIGN KEY REFERENCES grupoLocomocao(id)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
	, dataEntradaUsuaria DATETIME
);

CREATE TABLE areaRisco(
	id INT PRIMARY KEY IDENTITY(1,1)
	, latitude DECIMAL(12, 9)
	, longitude DECIMAL(12, 9)
);

CREATE TABLE viagem_areaRisco(
	idViagem INT FOREIGN KEY REFERENCES viagem(id)
	,idAreaRisco INT FOREIGN KEY REFERENCES areaRisco(id)
);

CREATE TABLE publicacao(
	id INT PRIMARY KEY IDENTITY(1,1)
	, descricao VARCHAR(255)
	, foto VARCHAR(MAX)
	, curtidas INT
);

CREATE TABLE publicacao_usuaria(
	idPublicacao INT FOREIGN KEY REFERENCES publicacao(id)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE comentario(
	id INT PRIMARY KEY IDENTITY(1,1)
	, descricao VARCHAR(100)
	, idPublicacao INT FOREIGN KEY REFERENCES publicacao(id)
    , idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE denunciaLocal(
	id INT PRIMARY KEY IDENTITY(1,1)
	, descricao VARCHAR(255)
	, local VARCHAR(100)
	, idUsuaria INT FOREIGN KEY REFERENCES usuaria(id)
);

CREATE TABLE administrador(
	id INT PRIMARY KEY IDENTITY(1,1)
	, nome VARCHAR(50)
	, senha VARCHAR(45)
	, email VARCHAR(50)
    , foto VARCHAR(MAX)
);

CREATE TABLE denunciaLocal_administrador(
	idAdministrador INT FOREIGN KEY REFERENCES administrador(id)
	, idDenunciaLocal INT FOREIGN KEY REFERENCES denunciaLocal(id)
);
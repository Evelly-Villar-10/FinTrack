create table usuario(
	id integer primary key autoincrement,
	nome text not null,
	email text not null unique,
	cpf text not null,
	telefone text not null,
	senha text not null
);

CREATE TABLE transacao (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eh_receita INTEGER NOT NULL,
    valor REAL NOT NULL,
    descricao TEXT NOT NULL,
    data DATE NOT NULL,
    usuario_id INTEGER NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
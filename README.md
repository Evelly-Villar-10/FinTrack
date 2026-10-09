# 💰 FinTrack

Aplicação desktop de **controle financeiro pessoal** feita em Java com JavaFX. Cada usuário se cadastra, faz login e gerencia suas receitas e despesas, com um relatório de saldo na tela.

## Funcionalidades

- **Cadastro e login de usuários**, com validação de nome, e-mail (formato e duplicidade), CPF, telefone e senha (mínimo de 6 caracteres)
- **Transações**: adicionar, editar e excluir receitas e despesas
- **Tabela de transações** com data, descrição, valor e tipo
- **Relatório** com saldo total, total de receitas, total de despesas e quantidade de transações
- Dados salvos em um banco **SQLite** local, separados por usuário

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| JavaFX (controls + FXML) | 21.0.2 |
| SQLite JDBC (`sqlite-jdbc`) | 3.46.1.0 |
| JUnit Jupiter | 5.10.3 |
| Maven | - |

## Como rodar

### Pré-requisitos

- JDK 21
- Maven
- SQLite (`sqlite3`) para criar o banco a partir do script

### Passo a passo

```bash
# 1. clonar o repositório
git clone https://github.com/Evelly-Villar-10/FinTrack.git
cd FinTrack

# 2. criar o banco de dados a partir do script
sqlite3 database/fintrack.db < database/database.sql

# 3. rodar a aplicação (sempre a partir da raiz do projeto)
mvn javafx:run
```

> O arquivo `database/fintrack.db` **não é versionado** (está no `.gitignore`). Cada pessoa cria o seu a partir do `database/database.sql`.

Também é possível abrir o projeto no **NetBeans** e executar normalmente (o `nbactions.xml` já está configurado para usar `javafx:run`).

### Rodar os testes

```bash
mvn test
```

## Estrutura do projeto

```
FinTrack/
├── database/
│   └── database.sql          # script de criação das tabelas
├── src/
│   ├── main/java/com/bianca/fintrack/
│   │   ├── FinApp.java       # classe principal (JavaFX) e troca de telas
│   │   ├── controller/       # FinTracker: regras de negócio das transações
│   │   ├── dao/              # acesso ao banco (UsuarioDAO, TransacaoDAO)
│   │   ├── database/         # Conexao: conexão com o SQLite
│   │   ├── exceptions/       # EntradaInvalidaException
│   │   ├── model/            # Usuario, Transacao, RepositorioGenerico...
│   │   ├── service/          # UsuarioService: validações e login
│   │   └── view/             # telas (.fxml), controllers das telas e style.css
│   └── test/java/            # testes unitários
└── pom.xml
```

## Banco de dados

Duas tabelas:

- **`usuario`**: `id`, `nome`, `email` (único), `cpf`, `telefone`, `senha`
- **`transacao`**: `id`, `eh_receita`, `valor`, `descricao`, `data`, `usuario_id` (chave estrangeira para `usuario`)

## Testes

Os testes cobrem o modelo (`Transacao`), o `RepositorioGenerico`, o `FinTracker` (como o cálculo de saldo) e o `TransacaoDAO`, este último usando um banco SQLite **em memória** para não mexer nos dados reais.

## Melhorias futuras

- Armazenar as senhas com hash (por exemplo, BCrypt) em vez de texto puro
- Filtros por período e categoria no relatório
- Gráficos de receitas e despesas

## Autora

Desenvolvido por **Bianca** como projeto de estudo.

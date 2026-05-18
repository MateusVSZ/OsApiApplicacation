# Ordem de Serviço API

Você foi recrutado pela equipe de TI para desenvolver o sistema de Ordem de Serviço para uma Assistência Técnica de Notebooks e equipamentos de informática em geral.

A sua responsabilidade é preparar o Back-End utilizando frameworks de mercado e as boas práticas de programação, incluindo Clean Code.

## Regras de Negócio

Em entrevista com o Product Owner (P.O.), foi explicado o funcionamento esperado do sistema.

Todo equipamento que chega à assistência deve receber uma Ordem de Serviço (OS).

Essa OS deve conter os seguintes dados do cliente:

* Nome
* Telefone
* Email

Além disso, a OS deve possuir:

* Descrição do serviço
* Valor do serviço
* Status da OS
* Data e hora de cadastro

Ao ser criada, a OS deve iniciar com o status:

* `ABERTA`

Também deve ser registrada automaticamente a data e hora da criação da OS.

---

## Comentários da OS

Quando existir alguma anotação referente à OS, como por exemplo:

> "Solicitada compra da tela para substituição"

essa mensagem deverá ser vinculada à OS, contendo:

* Mensagem
* Data e hora do comentário

---

## Finalização da OS

Quando o equipamento estiver pronto, a OS deverá receber o status:

* `FINALIZADA`

Também deverá ser registrada a data e hora do fechamento.

---

## Cancelamento da OS

Caso o cliente opte por não realizar o serviço, a OS deverá receber o status:

* `CANCELADA`

Também deverá ser registrada a data e hora do cancelamento.

---

# Funcionalidades da API

## Clientes

A API deverá permitir:

* CRUD de clientes
* Listar todos os clientes
* Listar cliente por ID
* Listar cliente por email
* Listar cliente por telefone

---

## Ordem de Serviço

A API deverá permitir:

* CRUD de Ordem de Serviço
* Listar uma OS por ID
* Listar todas as OS por ID do cliente

---

## Comentários

A API deverá permitir:

* CRUD de comentários
* Listar comentário por ID
* Listar comentários por OS

---

# Desafio

Implemente as seguintes funcionalidades:

* Listar todas as OS abertas por ID do cliente
* Listar todas as OS fechadas por ID do cliente
* Listar todas as OS com e sem comentários
* Listar todas as OS abertas com e sem comentários
* Listar todas as OS fechadas com e sem comentários

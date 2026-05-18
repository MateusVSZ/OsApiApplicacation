# Ordem de Serviço API

VocÃª foi recrutado pela equipe de TI para desenvolver o sistema de Ordem de ServiÃ§o para uma AssistÃªncia TÃ©cnica de Notebooks e equipamentos de InformÃ¡tica em geral. A sua responsabilidade Ã© preparar o BackEnd utilizando framework de mercado e as boas prÃ¡ticas de programaÃ§Ã£o, inclusive clean code.

Em entrevista com o P.O., foi explicado o funcionamento esperado do sistema. Todo equipamento que chega deve receber uma Ordem de ServiÃ§o (chamada a partir daqui de OS - "Ã³-esse"). Essa OS deve ter os dados do cliente (Nome, Telefone e Email), a descriÃ§Ã£o do serviÃ§o e o valor. A OS Ã© cadastrada no sistema com o status de aberta e deve ser marcado a data e hora que foi cadastrada.

Quando existir alguma anotaÃ§Ã£o referente a OS, como p.exemplo "Solicitado compra da tela para substituiÃ§Ã£o" deve-se anexar essa mensagem a OS, identificando a mensagem e a data e hora.

Quando o equipamento ficar pronto a OS receberÃ¡ o status de FINALIZADA. DeverÃ¡ ser marcado a data e hora do fechamento.

Caso o cliente opte por nÃ£o realizar o serviÃ§o, a OS deve ser marcada como CANCELADA e tambÃ©m deverÃ¡ ser registrado a data e hora do cancelamento.

A AAPI deve ser capaz de:

Clientes
-CRUD
-Listar todos clientes
-Listar cliente por ID
-Listar cliente por email
-Listar cliente por telefone


OrdemServico
-CRUD
-Listar uma OS por ID
-Listar todas OS por ID cliente


Comentarios
-CRUD
-Listar comentÃ¡rio por ID
-Listar comentÃ¡rios por OS


## Desafio

Implemente as seguintes funcionalidades
-Listar todas OS aberta por ID cliente
-Listar todas OS fechada por ID cliente
-Listar todas OS Com e Sem ComentÃ¡rios
-Listar todas OS abertas Com e Sem ComentÃ¡rios
-Listar todas OS fechadas Com e Sem ComentÃ¡rios

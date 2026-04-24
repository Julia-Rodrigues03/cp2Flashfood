# [FlashFood] - [DELIVERY]
 
## 👥 Integrantes do Grupo
- Gustavo de Faria - 41328779
- Julia Rodriguês de Lima - 45574332
- Arthur Rodrigues dos Santos Vilas Boas - 45968993
- João Victor de Lemos Monteiro - 44896140
- Lucas Matteus Baptista de Godoy - 45133239
- Bruno Santos de Godoy - 45737719
 
## 📋 Tema Escolhido
[Delivery]
 
## 🎯 Objetivo do Sistema
[O sistema de gerenciamento de delivery de comida tem como objetivo centralizar e organizar o fluxo de pedidos realizados por clientes em restaurantes parceiros, garantindo que todas as etapas — desde o cadastro de produtos até a entrega final — sejam executadas de forma eficiente. Ele busca oferecer uma solução prática para operadores da plataforma, permitindo o controle de restaurantes, clientes, entregadores e pedidos em um único ambiente.

Além de facilitar a experiência do cliente ao realizar pedidos, o sistema também apoia os restaurantes no gerenciamento de seus cardápios e vendas, e auxilia os entregadores na atribuição de entregas de forma automatizada. Dessa forma, cada participante do processo tem acesso às informações relevantes em tempo real, reduzindo falhas de comunicação e aumentando a confiabilidade do serviço.

Com isso, o sistema pretende otimizar o tempo de resposta, reduzir erros manuais e melhorar a satisfação dos clientes, ao mesmo tempo em que proporciona maior controle operacional para os restaurantes e melhor aproveitamento da disponibilidade dos entregadores. O resultado esperado é um ecossistema de delivery transparente, ágil e eficiente.]
 
## 📦 Funcionalidades Principais
1. [Criação e gerenciamento de pedidos de delivery]
2. [Adição de produtos aos pedidos com controle de quantidade]
3. [Cadastro de clientes durante o fluxo de pedido]
4. [Atribuição de entregadores disponíveis aos pedidos]
5. [Gerenciamento de Pedidos]
6. [Cálculo de Valores]
7. [Exibição de resumo detalhado do pedido]

 
## 🏗️ Estrutura de Classes (Planejada)
- **Classe 1:** [Pessoa] - [Superclasse genérica que concentra atributos comuns a qualquer pessoa no sistema (id, nome, email, telefone).]
- **Classe 2:** [Cliente] - [Subclasse de Pessoa. Representa quem faz pedidos no sistema.]
- **Classe 3:** [Entregador] - [Subclasse de Pessoa. Representa quem realiza as entregas.]
- **Classe 4:** [Produto] - [Representa um item do cardápio de um restaurante.]
- **Classe 5:** [Restaurante] - [Representa um restaurante parceiro.]
- **Classe 6:** [Pedido] - [Representa um pedido feito por um cliente em um restaurante.]
- **Classe 7:** [ItemPedido] - [Representa cada produto dentro de um pedido.]
 
## 🔄 Regra de Negócio Complexa

Cálculo de Valor Total do Pedido  
O sistema deve calcular o valor final de cada pedido considerando:

Subtotal dos produtos (preço × quantidade)

Descontos progressivos:

5% acima de R$ 100,00

10% acima de R$ 200,00

15% acima de R$ 300,00

Taxa fixa de entrega de R$ 8,00

Exibição do resumo detalhado (subtotal, desconto, taxa e valor final)

Atribuição automática de entregador disponível
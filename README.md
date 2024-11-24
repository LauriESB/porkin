# <img src="misc/banner.png">


<br>

🌟 Nos dê uma estrela!
<img align="right" src="https://img.shields.io/badge/status%20do%20projeto:-em%20desenvolvimento-purple">


## Tabela de conteúdos

- [Sobre](#-sobre)
- [Tecnologias utilizadas](#-tecnologias-utilizadas)
- [Como usar](#-como-usar)
- [Screenshots do projeto](#-visão-geral)
- [Licença](#-licença)
- [Contato](#-contato)

## 🐷 Sobre

**porkin!** é um PWA desenvolvido para quem precisa organizar as despesas de um evento com os amigos de maneira simples, rápida e sem complicações. Sabemos que, na correria, é fácil perder o controle de quem pagou o quê e quem deve para quem, especialmente quando as dívidas são quitadas após algum tempo. **porkin!** facilita esse processo, permitindo que todos acompanhem as despesas em tempo real, garantindo transparência e evitando mal-entendidos. Com uma interface intuitiva e funcionalidades pensadas para o dia a dia, ele é a solução perfeita para manter as contas em ordem e focar no que realmente importa: aproveitar os momentos com os amigos.

<details open>
<summary>
 Screenshots
</summary> <br />
    
<p align="center">
    <img width="32%" src="misc/1.png"/>
&nbsp;
    <img width="32%" src="misc/2.png"/>
&nbsp;
    <img width="32%" src="misc/3.png"/>
    <img width="32%" src="misc/4.png"/>
&nbsp;
    <img width="32%" src="misc/5 .png"/>
&nbsp;
    <img width="32%" src="misc/6.png"/>

</details>

## 💻 Tecnologias utilizadas

- **JDK 21 (Amazon Corretto)**: Java é a linguagem de programação utilizada no back-end deste projeto.
- **Spring Boot**: Framework java.
  - **Spring Data JPA**: Para gerenciar a persistência de dados e as interações com o banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenamento de dados.
- **Maven**: Gerenciamento de dependências.
- **Javascript**: Javascript é a linguagem de programação utilizada no front-end deste projeto.
- **HTML**: Linguagem de marcação usada para estruturar o conteúdo das páginas web.
- **CSS**: Linguagem de estilo usada para definir a aparência visual das páginas web.
- **Webpack**: Ferramenta para empacotamento de módulos JavaScript, otimizando recursos para o frontend.  
- **GSAP**: Biblioteca JavaScript para animações. 

## 📱 Como usar


#### Criação da rede de contatos

- Permite a criação de uma rede de contatos.
- Caso o usuário deseje compartilhar um valor com outras pessoas, os contatos devem ser adicionados antes da inserção de uma nova despesa.

#### Inserção e divisão

- Na página inicial, o valor a ser dividido pode ser definido e vinculado a uma ou mais pessoas da rede.
- O custo total é inicialmente dividido de forma igual entre todos os participantes.
- Os valores podem ser ajustados posteriormente, de acordo com as necessidades do grupo.
- O usuário criador tem a opção de adicionar seu Pix ou PayPal à despesa criada.

#### Gestão de cobranças

- A cobrança permanece pendente, exibindo o valor que cada pessoa deve pagar e a data limite para pagamento.
- No dia do vencimento, uma notificação é enviada como lembrete para aqueles que ainda não quitaram suas partes.

#### Pagamento

- Usuários podem marcar suas partes individuais como pagas a qualquer momento.

#### Exclusão de despesas

- A exclusão permanente da despesa só pode ser realizada pela pessoa que a criou.


## 📖 Licença

Este projeto está sob a licença GNU GENERAL PUBLIC LICENSE Version 3 (GPLv3) - veja o arquivo [LICENSE.md](https://github.com/LauriESB/porkin/blob/main/LICENSE) para mais detalhes.

[De volta ao topo do README](#top)

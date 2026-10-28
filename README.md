
# Sistema de Restaurante (Java)

> Projeto desenvolvido para praticar Java, programa orientada a objeto, padrões de projeto e testes automatizados.

## Funcionalidades

- [x] cadastro de pedidos
- [x] Criação de lanches pelo cardapio
- [x] Personalização de ingredientes
- [x] Adicção de cheddar e bacon extra
- [x] Calculo do total vendido
- [x] Idenificação de combinações diferentes


## Tecnologias utilizadas

- Java 17
- Maven
- -Junit 5
- Git e GitHub

## Padrões de projeto

- **Factory:** criação dos lanches disponíveis no cardapio
- **Decorator:** adição de ingredientes extras, como cheddar e bacon.

## Estrutura do projeto

```text
restaurante/
├── pom.xml
├── README.md
└── scr/
    ├── main/
    |  └──  java/
    └── test/
        └── java/
```
## Testes automatizados

Os testes são desenvolvidos com JUnit 5 para verificar regras de negócio, com validação de preços e cálculo dos adicionais.

Para executar os testes, utilize o Itellij IDEA ou execute:
```bash
mvn test
```

## Como executar o projeto

1. Clone este repositório.
2. Abra o prpjeto no intellij IDEA.
3. Aguarde o Maven carregar as dependencias.
4. Execute a classe Main.

## Autora
- Desenvolvido como projeto de estudo em Java com IA da SoulCode.

- Rebeca Miguel
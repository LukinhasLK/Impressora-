# Impressora-Elgin

Este projeto é um sistema PDV simples focado na comunicação com impressoras de caixa da marca Elgin, utilizando uma DLL para controle da impressora via Java (JNA). O objetivo principal é realizar impressões diversas, como texto, códigos QR, código de barras, XML SAT, além de funcionalidades adicionais como abrir gaveta, sinal sonoro e avançar papel.

---

## Funcionalidades principais

### 1. Sistema de Login e Cadastro
- **Cadastro:** Se o usuário não possuir login, ele pode criar um cadastro.
- **Login:** Se já existir cadastro, o usuário entra com login e senha.
- Os dados de login e senha são armazenados em um `HashMap<String, String>`, onde a chave é o usuário e o valor a senha.
- O sistema valida se o login já existe antes de permitir novo cadastro.

### 2. Configuração e controle da impressora via DLL
- Configuração da conexão (tipo, modelo, porta, parâmetro).
- Abertura e fechamento da conexão com a impressora.
- Impressão de texto simples.
- Impressão de QR Code.
- Impressão de código de barras.
- Impressão de XML SAT e cancelamento.
- Comandos de corte de papel, avanço de papel, abertura de gaveta (Elgin e genérica).
- Emissão de sinal sonoro.

---

## Ferramentas e tecnologias utilizadas
- Linguagem Java.
- JNA (Java Native Access) para comunicação com a DLL da impressora.
- DLL proprietária da impressora Elgin para controle via funções nativas.
- Estrutura simples para interface de usuário via console.

---

## Como usar o sistema

1. Ao iniciar o programa, o usuário é apresentado ao menu de login:
    - Pode optar por se cadastrar ou fazer login.
    - Após login bem-sucedido, o menu principal da impressora é exibido.

2. Menu principal com opções para:
    - Configurar a conexão da impressora.
    - Abrir conexão.
    - Enviar comandos de impressão (texto, QR Code, código de barras).
    - Imprimir XML SAT e cancelamentos.
    - Abrir gaveta e emitir sinal sonoro.
    - Fechar conexão e sair.

---

## Estrutura de código e funções principais

### Interface `ImpressoraDLL`
- Mapeia os métodos da DLL para uso em Java via JNA.

### Métodos chave
- `AbreConexaoImpressora(...)` — abre a conexão.
- `FechaConexaoImpressora()` — fecha a conexão.
- `ImpressaoTexto(...)` — imprime texto.
- `ImpressaoQRCode(...)` — imprime QR Code.
- `ImpressaoCodigoBarras(...)` — imprime código de barras.
- `ImprimeXMLSAT(...)` — imprime XML SAT.
- `ImprimeXMLCancelamentoSAT(...)` — imprime cancelamento.
- `AvancaPapel(...)` — avança papel.
- `Corte(...)` — corta papel.
- `AbreGavetaElgin()` e `AbreGaveta(...)` — abrem gavetas.
- `SinalSonoro(...)` — emite sinal sonoro.

---

## Observações

- O programa utiliza menus no console para navegação.
- A conexão precisa ser configurada antes de abrir.
- As funções de impressão só funcionam após a conexão estar aberta.
- Erros e mensagens são exibidos no console para feedback ao usuário.

---

Obrigado por usar o sistema Impressora-Elgin!  
Qualquer dúvida ou sugestão, fique à vontade para contribuir.

Densenvolvido por Renato, Lucas Rodrigues, Giovanny, Henrique e Pedro ADS/2SEM



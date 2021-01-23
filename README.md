# Store-Microservice-System-2

Anotações de aula

---> CURSO 1 <---

Microservices com Spring Cloud: Registry, Config Server e Distributed Tracing

#MÓDULO 1 <br>
    1. Qual a solução a ser implementada no curso<br>
        - Teremos três microsserviços: Fornecedor, Loja e Transportador<br>
    2. Uma apresentação da modelagem focado em DDD (Domain Driven Design)<br>
    3. Quebraremos o domínio em contextos menores (bounded context)<br>
        - Um microsserviço é a implementação de um contexto<br>
    4. O uso do Postman como cliente HTTP<br>
    5. A criação do microsserviço Loja usando Spring Boot<br>
<br>
#MÓDULO 2<br>
    1. A integração entre microsserviços com RestTemplate<br>
        - O RestTemplate do Spring permite chamadas HTTP de alto nível<br>
    2. Um introdução ao Service discovery e Service registry<br>
        - Service registry é um servidor central, onde todos os microsserviços ficam cadastrados (nome e IP/porta)<br>
        - Service discovery é um mecanismo de descoberta do IP do microsserviço pelo nome<br>
        - Dessa forma, nenhum microsserviço fica acoplado ao outro pelo IP/porta<br>
    3. A implementação do service registry através do Eureka Server<br>
    4. Como registrar da Loja e do Fornecedor no Eureka Server<br>
    5. A resolução do IP/porta através do nome do microsserviço nas requisições<br>
<br>
#MÓDULO 3<br>
    1. Que um servidor de configuração é o lugar central para definir as configurações dos serviços<br>
    2. Que todas as configurações dos microsserviços devem ficar externalizadas e centralizadas<br>
    3. Que o Spring Config Server é uma implementação do servidor do projeto Spring Cloud<br>
    4. Sobre a integração dos microsserviços com o servidor de configuração<br>
        - Para tal, devemos configurar o nome do microsserviço, profile e URL do Config Server<br>
    5. Que existem várias formas de definir um repositório de configurações, entre elas o GitHub e o local<br>
<br>
#MÓDULO 4<br>
    1. Que o Client Side Load Balancing (CSLB) é o cliente HTTP que decide qual microsserviço recebe a requisição<br>
        - Isto é importante, pois pode ter várias instâncias do mesmo serviço no ar<br>
        - A configuração do CSLB é feita a partir da anotação @LoadBalanced, na criação do RestTemplate<br>
        - Como implementação do CSLB, usamos um projeto chamado Ribbon, que faz parte do Spring Cloud Netflix<br>
    2. Para descobrir quais clientes existem, podemos injetar a interface DiscoveryClient<br>
    3. Como alternativa ao RestTemplate, podemos usar o Spring Feign, que já usa o Ribbon para CSLB<br>
    4. O Spring Feign exige apenas uma interface, com a definição e mapeamento dos métodos que executarão a requisição<br>
        - Toda a implementação da interface é gerada pelo Spring Feign<br>
<br>
#MÓDULO 5<br>
    1. Como se trata de uma arquitetura distribuída, temos logs distribuídos<br>
        - Ou seja, cada microsserviço (e instância dele) possui o seu log<br>
        - Isso dificulta o acompanhamento e rastreabilidade das requisições<br>
    2. Para unificar os logs, precisamos de agregadores de log<br>
        - Como implementação de um agregador, usamos o Papertrail, um agregador como serviço<br>
    3. Usamos a biblioteca Logback para gerar e enviar os logs ao agregador<br>
        - O Logback possui um appender, que possibilita o envio dos logs<br>
    4. Para acompanhar uma transação nos logs, usamos uma correlation-id<br>
        - A correltation-id é um identificador da transação, que é passada de requisição pra requisição<br>
        - Dessa forma, podemos entender quais requisições fazem parte da mesma transação<br>
    5. A biblioteca Spring Sleuth, que é usada para gerar a correlation-id<br>
<br>

---> CURSO 2 <---
<br>
#MÓDULO 1 <br>
    1. O Circuit Breaker tem como funcionalidade principal a análise das requisições anteriores, para decidir<br>
    se deve parar de repassar as requisições vindas do cliente para um microsserviço com problemas de performance.<br>
    Enquanto o circuito está fechado, o Hystrix continua tentando a cada 5 segundos, com o objetivo de verificar<br>
    se o servidor voltou a funcionar normalmente.<br>
<br>
    2. O Circuit Breaker implementado pelo Hystrix executa o processamento em uma thread separada.<br> 
    Quando o tempo limite é excedido, o Hystrix mata a execução da thread e, caso configurado, repassa a execução<br> 
    para o método de Fallback, de forma que este possa implementar livremente um tratamento de erro.<br>

Bulkhead: Poder agrupar e alocar grupos de threads para processamentos diferentes. Dessa forma, uma das chamadas de um microsserviço, que sofre lentidão por causa de uma integração com problema de performance, não indisponibiliza todas as outras chamadas do mesmo microsserviço

#MÓDULO 2 <br>
Que o Hystrix executa uma gerência de um pool de threads
A combinação de um volume alto de requisições para um único serviço de um microsserviço pode indisponibilizar as outras requisições

    @HystrixCommand(threadPoolKey = "{nomeDesejadoParaALinhaDeExecucao}ThreadPool") //criando o bulkhead dentro do hystrix

A importação e integração com o microsserviço Transportador
Como realizar a compra com os dados do pedido e do voucher do transporte, utilizando os id's fornecidos pelos microsserviços
O entendimento de que, com microsserviços, uma transação do usuário passa por diversos microsserviços e que a gestão dessa transação fica sob a responsabilidade do programador
Como tratar os erros na transação, marcando o que foi processado com o uso de estados

Como disponibilizar a nossa aplicação para serem acessadas por um cliente
O uso do Api Gateway, para criar um único ponto de acesso à nossa aplicação
O uso da implementação Zuul como Api Gateway do nosso sistema
A integração do Zuul com o Eureka, para o descobrimento automático das instâncias disponíveis da nossa aplicação
A alteração das requisições para os nossos microsserviços, com o uso do Zuul

Como funciona a autenticação e autorização com microsserviços
Como criar um servidor de autenticação com Spring Security e Spring Cloud OAuth2
A implementação da integração entre o Spring Security e o Spring Cloud OAuth2
Como gerar e validar tokens no servidor de autenticação
A integração dos microsserviços com o servidor de autenticação
Como repassar o token de autenticação através do Zuul
A implementar um filtro do Feign para repassar o token do usuário nas chamadas para os microsserviços Fornecedor e Transportador

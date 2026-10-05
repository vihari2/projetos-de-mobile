# Plano de Implementação: Calculadora de IMC com Duas Activities

Este plano descreve a criação de uma aplicação Android para cálculo de IMC, onde os dados são inseridos na `MainActivity` e o resultado é exibido na `ResultActivity`. O cálculo do IMC será realizado na `MainActivity` e passado dentro de um objeto `Person` para a segunda tela.

## Mudanças Propostas

### Modelo de Dados

#### [NEW] [Person.kt](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/java/com/example/imc/Person.kt)
Criação de uma data class `Person` que implementa `Serializable` para facilitar o transporte de dados entre Activities.

### Atividade Principal (Entrada de Dados)

#### [MODIFY] [activity_main.xml](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/res/layout/activity_main.xml)
Atualização do layout para incluir campos de texto para Nome, Peso e Altura, além de um botão para calcular.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/java/com/example/imc/MainActivity.kt)
Implementação da lógica para ler os inputs, realizar o cálculo do IMC e iniciar a `ResultActivity` passando o objeto `Person`.

### Atividade de Resultado

#### [NEW] [activity_result.xml](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/res/layout/activity_result.xml)
Layout organizado para exibir Nome, Peso, Altura, o valor do IMC calculado e a mensagem de classificação (ex: "Peso Ideal").

#### [NEW] [ResultActivity.kt](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/java/com/example/imc/ResultActivity.kt)
Activity que recebe o objeto `Person` e preenche os componentes da interface.

### Configuração do Projeto

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/victo/AndroidStudioProjects/IMC/app/src/main/xml/AndroidManifest.xml)
Registro da nova `ResultActivity`.

---

## Plano de Verificação

### Testes Manuais
1. Abrir o app na `MainActivity`.
2. Inserir Nome, Peso e Altura.
3. Clicar no botão de calcular.
4. Verificar se a `ResultActivity` abre com todos os campos preenchidos corretamente e o IMC calculado corretamente.
5. Verificar se a mensagem de classificação condiz com o IMC (ex: IMC 22 -> "Normal").

# APP CheckVeículos

# Pré-requisitos:

Backend disponível em:
https://github.com/dylan402/projeto-check-veiculos

No ambiente de teste foram utilizadas as versões dos softwares:

- Android Studio 4.2.1 <br>
Build #AI-202.7660.26.42.7351085, built on May 10, 2021 <br>
Runtime version: 11.0.8+10-b944.6842174 amd64 <br>
VM: OpenJDK 64-Bit Server VM by N/A <br>
<br>

# Importação de arquivos

Realize o download do projeto e abertura no Android Studio.
<br>

# Preparando ambiente

No Android Studio, **sincronize o Gradle** clicando em File em seguida sobre **Sync Project With Gradle Files**.

É possível realizar a alteração da API na aba Project:
 - Selecionar **APP > Java > br.checkveiculos.appcheckveiculos > api**
 - **Clicar duas vezes** sobre o arquivo **RestServiceGenerator** para abertura
 - **Nas linhas 10 e 11 estão configuradas para acesso localhost**
 - **Nas linhas 12 e 13 pode-se configurar para ambiente de produção** 

Para execução do AVD - Android Virtual Device, selecione o menu Tools e a opção AVD Mananger:
- Utilizamos o **smartphone Pixel_3a**
- **API level 30**
- Arquitetura **32 bits**
- **Target google API Android 11.0(Google APIs)** 
- Clique em executar através do botão **play**
<br>

# Executando a aplicação

Deve-se inicar os serviços referente aos clientes e aos veículos. Este processo esta detalhado em: **https://github.com/dylan402/projeto-check-veiculos**
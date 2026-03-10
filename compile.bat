@echo off
echo Compilando Conversor de Moedas...
if not exist out mkdir out
javac -cp "lib\gson-2.10.1.jar" -d out -sourcepath src src\com\currencyconverter\Main.java src\com\currencyconverter\model\ExchangeRateResponse.java src\com\currencyconverter\api\ApiClient.java src\com\currencyconverter\service\CurrencyConverter.java src\com\currencyconverter\ui\ConsoleMenu.java
if %ERRORLEVEL% == 0 (
    echo.
    echo Compilacao concluida com sucesso! Execute run.bat para iniciar o programa.
) else (
    echo.
    echo ERRO: Falha na compilacao. Verifique as mensagens acima.
)

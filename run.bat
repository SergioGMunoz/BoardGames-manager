@echo off
setlocal

set "JAR_PATH=release\boardgame-manager-3.0.0-jar-with-dependencies.jar"

echo ================================
echo   Launching Boardgame Manager
echo ================================
echo.

echo.
set /p runSQL=Have you executed the script database.sql in your local MySQL server? (Y/N): 
if /I not "%runSQL%"=="Y" (
    echo [INFO] Please, do it before launch the program.
    goto end
)

REM 3. Check if the JAR exists
if not exist "%JAR_PATH%" (
    echo [ERROR] JAR file not found: %JAR_PATH%
    pause
    exit /b
)

REM 4. Run the application
java -jar "%JAR_PATH%"

echo.

:end


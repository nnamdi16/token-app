@echo off
REM Define ports for services
set FRONTEND_PORT=8080
set GENERATOR_SERVICE_PORT=8081
set VALIDATOR_SERVICE_PORT=8082

REM Define an array of port numbers you want to stop
REM Add your port numbers here
set ports=%FRONTEND_PORT% %GENERATOR_SERVICE_PORT% %VALIDATOR_SERVICE_PORT%

REM Loop through the array and attempt to stop processes on each port
for %%port in (%ports%) do (
    echo Stopping processes on port %%port...
    for /f "tokens=2" %%pid in ('netstat -aon ^| findstr /r "%%port"') do (
        taskkill /f /pid %%pid
    )
)

echo All specified ports have been stopped.

REM Start the frontend on port 8080
cd frontend || exit
npm install
start npm start -- --port %FRONTEND_PORT%
cd ..

REM Start Generator Service on port 8081
cd generator || exit
call mvnw spring-boot:run -Dserver.port=%GENERATOR_SERVICE_PORT%
cd ..

REM Start Validator Service on port 8082
cd validator || exit
call mvnw spring-boot:run -Dserver.port=%VALIDATOR_SERVICE_PORT%
cd ..

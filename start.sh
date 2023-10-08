#!/bin/bash
# Define ports for services
FRONTEND_PORT=8080
GENERATOR_SERVICE_PORT=8081
VALIDATOR_SERVICE_PORT=8082

# Define an array of port numbers you want to stop
ports=("$FRONTEND_PORT" "$GENERATOR_SERVICE_PORT" "$VALIDATOR_SERVICE_PORT")  # Add your port numbers here

# Loop through the array and attempt to stop processes on each port
for port in "${ports[@]}"; do
    echo "Stopping processes on port $port..."
    lsof -t -i :"$port" | xargs kill -9
done

echo "All specified ports have been stopped."

# Start the frontend on port 8080
cd frontend || exit
npm install
npm start -- --port 8080 &
cd ..

# Start Generator Service on port 8081
cd generator || exit
./mvnw spring-boot:run -Dserver.port=$GENERATOR_SERVICE_PORT &
cd ..

# Start Validator Service on port 8082
cd validator || exit
./mvnw spring-boot:run -Dserver.port=$VALIDATOR_SERVICE_PORT &
cd ..




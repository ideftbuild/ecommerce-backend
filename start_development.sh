#!/usr/bin/bash

# Check if the .env file exists
if [ -f .env ]; then
	export $(cat .env | grep -v '#')
	echo "Loaded environment variables from .env file..."

	echo "Starting Development Server: "
	# start dev server
	./gradlew bootRun
else
	echo "No .env file found. Please create one"
fi

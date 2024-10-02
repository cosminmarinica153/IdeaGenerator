# Idea Generator Groq Cloud Integration

This project is a Java SpringBoot web application that randomly selects 5-10 APIs from a collection of over 700 APIs. These APIs are added to a pre-made prompt, which is then sent to the **Groq Cloud API**. The application receives the response from Groq Cloud, parses the output, and displays the results to the user on the web interface.

## Features
- **Random API Selection**: Randomly selects 5-10 APIs from a pool of over 700 APIs.
- **Groq Cloud Integration**: Sends the randomly selected APIs with a prompt to the Groq Cloud API for processing.
- **Response Parsing**: Parses the response received from Groq Cloud and displays the processed data on a web page.
- **User-Friendly UI**: Provides a web interface for users to see the selected APIs and Groq Cloud responses.

## Technologies Used
- **Java**: Core backend language.
- **SpringBoot**: Framework for building the REST API and managing backend services.
- **Thymeleaf**: Template engine for rendering dynamic HTML pages.
- **JavaScript**: Used for client-side interactions and fetching data asynchronously.
- **HTML/CSS**: Frontend web technologies for structuring and styling the UI.

## Installation & Setup

### Prerequisites
- **Java 17+**: You need to have Java Development Kit (JDK) installed.
- **Maven**: Ensure you have Apache Maven installed for dependency management.
- **Groq Cloud API Key**: Obtain an API key from [Groq Cloud](https://groq.com) to access their API.

### Clone the Repository
```bash
git clone https://github.com/yourusername/random-api-selector.git
cd random-api-selector

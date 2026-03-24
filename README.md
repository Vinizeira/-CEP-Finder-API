📮 CEP Finder API

A Java application that fetches Brazilian address data using a public API and stores results locally in JSON format.

📌 About the Project

This project is a Java-based application that allows users to retrieve address information by entering a CEP (Brazilian postal code) using the ViaCEP API.

It performs HTTP requests, processes JSON responses, and stores queried addresses locally.

The project was built to practice:

REST API consumption
JSON parsing
Object-Oriented Programming (OOP)
Input validation and data handling
🧱 Project Structure
📊 Class Diagram
+----------------+
|    Request     |
+----------------+
|                |
+----------------+
| + getJson()    |
+----------------+

        ↓ (extends)

+----------------+
|     Scep       |
+----------------+
| - addresses    |
| - gson         |
+----------------+
| + main()       |
+----------------+
📋 Class Responsibilities
Class	Description
Request	პასუხისმგ Responsible for making HTTP requests to the API
Scep	Main class handling user interaction and application logic
⚙️ How to Use
🔹 Running the program

The user interacts through the terminal.

🔹 Commands
Enter a CEP (8 digits) → Fetch address
list → Show previously searched CEPs
Close → Exit program and save data
🔹 Example usage
String response = search.getJson("https://viacep.com.br/ws/" + cep + "/json/");
🧠 How the System Works

The application follows this flow:

User Input → Validation → HTTP Request → JSON Parsing → Storage
📐 CEP Validation Rule

The CEP must match the pattern:

\d{8}
Rule	Description
8 digits	Required
Numbers only	No letters
No hyphen	Automatically removed
📊 Examples
Input	Output
01001000	Valid address returned
123	Invalid CEP
99999999	CEP not found
▶️ How to Run
🔹 Clone repository
git clone https://github.com/your-username/cep-finder-api.git
🔹 Navigate
cd cep-finder-api
🔹 Compile
javac com/vn/challenge3/*.java
🔹 Run
java com.vn.challenge3.Scep
💻 Expected Output
**** Ceps Consultation ****
Enter your cep:

01001000

{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  ...
}

list
01001-000 - Praça da Sé

Close
Running Shutdown Hook
File updated!
✅ Coding Checklist
 HTTP request implementation
 API integration
 JSON parsing with Gson
 Input validation
 Duplicate prevention
 Local JSON file storage
 Shutdown Hook implementation
 Object-Oriented structure
🛠️ Technologies
Java
Gson
REST API (ViaCEP API)
IDE: IntelliJ IDEA
👨‍💻 Author
Vinicius Pereira

This project was developed as part of my studies to practice API consumption and backend fundamentals using Java.

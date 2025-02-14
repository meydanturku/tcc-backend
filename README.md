# tcc-backend
Full-Stack Online Library with AI Insights Challenge


# 📚 Book Library Backend

This is the **Spring Boot** backend for the Book Library application.  
It provides **REST API endpoints** to manage books and AI-powered book insights.

✅ Set Environment Variables on Windows
To configure the API key, follow these steps:

On Windows (Using System Environment Variables)
1️⃣ Open Run (Win + R) and type sysdm.cpl, then press Enter.
2️⃣ Go to the Advanced tab and click Environment Variables.
3️⃣ Under User variables, click New.
4️⃣ Set the Variable name as:

nginx
Copy
Edit
OPENAI_API_KEY
5️⃣ Set the Variable value as your OpenAI key:

Copy
Edit
The OpenAI API key (sk-xxxxxxxxxxxxxxxxxxxxxxxxxxxxx)  #has been shared with you via email or will be shared shortly.
6️⃣ Click OK, then Apply, and close all windows.
7️⃣ Restart your command prompt (cmd) or terminal for changes to take effect.


📌 API Endpoints
📖 Books API
Method	Endpoint	Description
GET	/books	Get all books
GET	/books/get-book/{id}	Get book by ID
POST	/books/create-book	Create a new book
PUT	/books/update-book/{id}	Update book by ID
DELETE	/books/delete-book/{id}	Delete book by ID

💡 AI Insights API
Method	Endpoint	Description
GET	/books/{id}/ai-insights	Get AI insights for a book

📌 Technology Stack
Java 17
Spring Boot
Spring Web
Spring Data JPA
H2 Database
Lombok
OpenAI API

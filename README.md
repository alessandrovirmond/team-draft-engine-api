# ⚽ Team Draft Engine API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📖 About the Project
The **Team Draft Engine** is a robust backend API designed to solve a classic weekend problem: balancing amateur football (soccer) teams fairly and quickly. 

Instead of wasting time before the match arguing over team selections, this engine uses a smart algorithm to automatically draft players into balanced squads based on their preferred positions (Goalkeeper, Defender, Midfielder, Attacker) and age groups (e.g., distributing U-40 and O-40 players evenly). 

Built with scalability in mind, the API uses a "dormant multi-tenant" architecture, allowing multiple independent football groups to manage their own weekly rosters securely.

## ✨ Features
* **Smart Team Generation:** Balances teams using a Round-Robin and Bucket-based algorithm (Strategy Pattern).
* **Player Management:** CRUD operations for the global roster of a specific group.
* **Weekly Presence Tracking:** Toggle which players are attending the upcoming match.
* **Position & Age Distribution:** Ensures no team is left without a goalkeeper or overloaded with players of the same age bracket.
* **Stateless & Scalable:** Ready to be consumed by Web (React) and Mobile (React Native) clients.

## 🏗️ Architecture & Patterns
* **Layered Architecture:** Controllers, Services, Repositories, and Models.
* **Strategy Pattern:** The drafting logic is abstracted into a `TeamGeneratorStrategy` interface, allowing easy swapping between "Age & Position Based" drafting (MVP) and "Skill Overall Based" drafting (Future).
* **Global Exception Handling:** Clean and standardized JSON error responses using `@ControllerAdvice`.
* **UUID Primary Keys:** Enhanced security for ayer and group identification.
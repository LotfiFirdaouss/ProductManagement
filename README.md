# ☕ Java SE 21 — Programming Complete Course Practice

This repository is part of my journey to master **Java SE 21**, the latest Long-Term Support (LTS) release of Java.  
It contains all my practice exercises, notes, and projects built while following the **Java SE 21: Programming Complete Course**.

---

## 📘 About the Course

The **Java SE 21 Complete Course** equips developers with the latest tools and knowledge to build **secure, efficient, and maintainable applications**.  
It provides in-depth coverage of all **core Java topics** and frequently used **APIs**, and guides you through building a fully functioning Java application from scratch.

---

## 🧠 Learning Goals

Through this repository, I aim to:
- Strengthen my understanding of **Object-Oriented Programming (OOP)** concepts.
- Explore modern **Java features** introduced up to version 21 (Records, Pattern Matching, Sealed Classes, etc.).
- Practice writing **clean, maintainable, and modular code**.
- Prepare for the **Java SE 21 Developer Certification (1Z0-830)**.
- Build hands-on experience by developing a **Product Management System** and other mini-projects.

---

## 💻 Current Project: Product Management App

### 🧩 Overview
The current project simulates a simple **Product Management System** that manages product data, ratings, and discounts.

### 🗂️ Example Code Snippet

```java
Product p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
Product p2 = new Product(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
Product p3 = new Product(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR);

System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " +
                   p1.getDiscount() + " " + p1.getRating().getStars());
🧱 Structure
labs.pm.data.Product → defines product properties and behaviors

labs.pm.data.Rating → enum representing product ratings

labs.pm.app.Shop → main application for managing and displaying products

🧰 Tools & Environment
JDK: Java SE 21

IDE: IntelliJ IDEA

Version Control: Git & GitHub

Build Tool: (manual for now, may add Maven/Gradle later)

🚀 Next Steps
Add more product operations (sorting, filtering, persistence)

Practice Streams, Records, and Sealed Classes

Add JUnit tests

Build a small CLI interface

✨ Author
Firdaouss Lotfi
📍 Morocco
💼 Software Engineer | Java & Web Development Enthusiast
📚 Currently preparing for Oracle Java SE 21 Certification

# Yassir REST Assured API Automation Framework

This project is a RESTful API automation framework built using **Java**, **TestNG**, and **Rest Assured**. It's a simple, usable, and scalable solution for testing RESTful web services. The framework demonstrates key software engineering concepts such as modularity, externalized configuration, and clean test design.

The framework automates test cases for the [DummyJSON API](https://dummyjson.com/docs), covering both public and authorized endpoints.

-----

## ✨ Solution Overview

This solution addresses the API automation challenge by using a robust and scalable framework built with **Java**, **TestNG**, and **Rest Assured**. The framework automates test cases for the **DummyJSON API**, showcasing capabilities for both public and protected endpoints. The design prioritizes modularity and maintainability, with a clear separation of concerns for tests, utilities, and data objects.

### Framework Choice

* **Rest Assured**: A powerful, open-source Java library specifically designed for testing REST APIs. It provides a simple, domain-specific language (DSL) that makes writing and reading API tests highly intuitive.
* **TestNG**: Used for test management, providing powerful features like test suites (`testng.xml`), test method grouping, and reporting.
* **Java**: A widely-used, stable language with a strong ecosystem of tools and libraries, making it a reliable choice for a professional framework.

### Key Design Principles

* **Modularity**: The project separates different functionalities into dedicated packages (`tests`, `util`, `pojo`). This ensures each class has a single responsibility, making the code easier to maintain and understand.
* **Externalized Configuration**: Using `config.properties` for test data and URLs keeps the codebase clean and secure. This allows you to change credentials or environments without modifying the test code.
* **Reusability**: The `BaseTest` class centralizes the setup logic, such as setting the base URI and content type, ensuring every test starts with the same configuration. Similarly, the `AuthUtil` class centralizes the logic for obtaining a token, so any test that needs one can simply call a single method.
* **Object-Oriented Payloads**: Instead of hardcoding JSON strings, the framework uses Java POJOs (like the `Product` class) for request and response payloads. This provides type safety, improves readability, and makes it easy to modify payloads.

-----

## 📦 Project Structure

The project follows a test-only structure where all source code, including helper classes and data objects, is located within the `src/test/java` directory.

```
├── pom.xml
├── src
│   └── test
│       ├── java
│       │   ├── pojo          # Java objects for request/response bodies
│       │   │   └── Product.java
│       │   ├── tests         # Test suites organized by API resource
│       │   │   ├── AuthenticationTests.java
│       │   │   └── ProductTests.java
│       │   └── util          # Helper and utility classes
│       │       ├── AuthUtil.java
│       │       ├── BaseTest.java
│       │       └── ConfigReader.java
│       │
│       └── resources
│           ├── config.properties
│           └── testng.xml
├── .github
│   └── workflows
│       └── maven.yml
└── README.md
```

-----

## 🛠️ Prerequisites

* **Java Development Kit (JDK) 17 or higher**
* **Maven**
* **An IDE** (e.g., IntelliJ IDEA, Eclipse)

-----

## ⚙️ Setup and Installation

1.  **Clone the Repository**:

    ```bash
    git clone https://github.com/mostafa97-debug/YassirAPI.git
    cd YassirAPI
    ```

2.  **Configure Credentials**:
    Update the `src/test/resources/config.properties` file with your API credentials and base URL.

    ```properties
    api.base.uri=https://dummyjson.com
    auth.username=emilys
    auth.password=emilyspass
    auth.email=emily.johnson@x.dummyjson.com
    ```

3.  **Build the Project**:
    Use Maven to download dependencies and build the project.

    ```bash
    mvn clean install
    ```

-----

## ▶️ How to Run Tests

### From the Command Line

Run all tests using the Maven command:

```bash
mvn test
```

### From an IDE

You can run the tests by right-clicking on the `testng.xml` file and selecting "Run 'testng.xml'". This will execute the entire test suite.

-----

## ✅ Test Case List

### `/products` Endpoint

* **Test Case 1: Retrieve All Products**

    * **Action:** Send a `GET` request to `/products`.
    * **Validation:** Assert the response status code is `200`, the content type is `JSON`, and the response body contains a `products` array with the correct `total` count.

* **Test Case 2: Search for a Product**

    * **Action:** Send a `GET` request to `/products/search?q=phone`.
    * **Validation:** Assert the response status code is `200`, and the `products` array contains only items relevant to the search query.

* **Test Case 3: Add a New Product (Protected Endpoint)**

    * **Action:** Send a `POST` request to `/products/add` with a product payload.
    * **Validation:** Assert the response status code is `200` and the response body contains the title of the newly created product.

### `/auth/me` Endpoint

* **Test Case 4: Successful Authentication**

    * **Action:** Send a `POST` request to `/auth/login` with valid credentials to obtain an authentication token.
    * **Validation:** Assert the status code is `200`, and the response body contains the `token`.

* **Test Case 5: Authorized Access to User Profile**

    * **Action:** Use the obtained authentication token in a `GET` request to `/auth/me`.
    * **Validation:** Assert the status code is `200` and the response body contains the correct user details (`username`, `email`).

* **Test Case 6: Unauthorized Access (Invalid Token)**

    * **Action:** Send a `GET` request to `/auth/me` with an invalid token.
    * **Validation:** Assert the response status code is `401` and the body contains an appropriate error message.

-----

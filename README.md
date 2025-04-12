# 🧪 QA Testing for RobotController – Task 3 & Task 4

## 📌 Overview

This repository documents the **Quality Assurance (QA)** activities performed on another team’s **RobotController application**:

- **Task 3**: Black-box and White-box Testing
- **Task 4**: Dev Team's Response and Regression Testing

As the QA team, we tested another group's implementation using comprehensive code coverage techniques. The **Dev team’s fixes and regression tests** are maintained in a **separate repository**, which is linked below.

---

## 🧪 QA Testing Objectives (Task 3)

We performed both **black-box** and **white-box** testing techniques, targeting critical methods within the codebase using the following coverage strategies:

- ✅ **Statement Coverage**
- ✅ **Decision Coverage**
- ✅ **Condition Coverage**
- ✅ **Multiple Condition Coverage**
- ✅ **Mutation Testing** (on the `move()` method)
- ✅ **Data Flow Testing** (on the `drawFloor()` function)

Our goal was to achieve **>50% coverage** in each metric and provide detailed feedback for developer improvements.

---

## 📊 Code Coverage Thresholds

| Metric                      | Target  | Achieved |
|----------------------------|---------|----------|
| Statement Coverage          | > 50%   | ✅        |
| Decision Coverage           | > 50%   | ✅        |
| Condition Coverage          | > 50%   | ✅        |
| Multiple Condition Coverage | > 50%   | ✅        |

---

## ⚙️ Tools Used

- **JUnit 5.3.1**
- **JaCoCo 0.8.8** – for code coverage
- **PIT** – for mutation testing
- **Maven** – as build tool

---

## 🧪 How to Run the Tests

Run all unit tests:
mvn test
Generate JaCoCo coverage report:

mvn jacoco:prepare-agent test jacoco:report
Run PIT mutation tests:


mvn org.pitest:pitest-maven:mutationCoverage
🔄 Task 4: Dev Team’s Response & Regression Testing
The Dev team reviewed our QA report and implemented the suggested fixes.

They ran regression tests to ensure the system still functioned correctly after changes.

Their updated codebase and regression test results are available in a separate repository.

🔗 Dev Repo with Task 4 Fixes: GitHub – Task 4 Repo: https://github.com/Vraj-2011/COEN-6761/tree/main


📜 License
This project is licensed under the MIT License.

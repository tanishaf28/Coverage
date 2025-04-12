QA Testing: RobotController Application
Task 3: Black-box and White-box Testing
Task 4: Dev Team's Response and Regression Testing


- **QA test report**
- The **Dev response and regression testing** is handled in a **separate repository**, and that repo is linked in this one (for Task 4).

---

```markdown
# QA Testing for Robot Controller – Task 3 & Task 4
📌 Overview
This repository documents the Quality Assurance (QA) activities performed on another team’s RobotController application (Task 3), as well as the follow-up regression testing and response from the original Dev team (Task 4, in a different repository).

As a QA team, we conducted black-box and white-box testing using comprehensive coverage criteria. The developer team's changes and regression testing were tracked separately and are referenced below.

**##🧪 QA Testing Objectives (Task 3)**
We tested another team’s application using the following white-box techniques:

✅ Statement Coverage

✅ Decision Coverage

✅ Condition Coverage

✅ Multiple Condition Coverage

✅ Mutation Testing (on the move method)

✅ Data Flow Testing (on the drawFloor function)

Our goal was to achieve over 50% coverage in all metrics and provide feedback to the developer team for improvement and regression testing.

## 📁 Repository Contents

```
robotcontroller-qa/
│
├── src/
  └── main/java/
  ├── source.java
│   └── test/java/
│           ├── SourceTests.java
├── Target (Consists of test resullts)
├── pom.xml
├── README.md
└── reports/
```


### 📌 Code Coverage Thresholds

| Metric                     | Target  | Achieved |
|---------------------------|---------|----------|
| Statement Coverage         | > 50%   | ✅       |
| Decision Coverage          | > 50%   | ✅       |
| Condition Coverage         | > 50%   | ✅       |
| Multiple Condition Coverage| > 50%   | ✅       |

### Tools Used

- **JUnit 5.3.1**
- **JaCoCo 0.8.8** for code coverage analysis
- **PIT** for mutation testing
- **Maven** as build tool

---

### 📖 How to Run the Tests

```bash
mvn test
```

To view test coverage reports:

```bash
mvn jacoco:prepare-agent test jacoco:report
```

To run mutation testing:

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

---

## 🔄 Task 4: Dev Team’s Response

- The **Dev team reviewed our QA report** and implemented the necessary changes.
- They **performed regression testing** and validated improvements.
- Regression testing and updated code are available in their repository.

---
## 📎 Resources
- 🧪 Dev Repo with Task 4 Fixes: [GitHub]((https://github.com/Vraj-2011/COEN-6761/tree/main))


## 📜 License

This project is licensed under the MIT License.

```

Let me know if you want to include coverage screenshots inline or link to them in the report. I can also generate a Markdown table mapping test cases to coverage metrics if you'd like to add more detail.

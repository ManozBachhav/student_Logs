# Student_Logs (Console Based Hibernate Project)

StudentApp is a simple **console-based CRUD application** built using  
**Java, Hibernate ORM, and MySQL**.  
The application manages **Students** and their **Subjects** using a One-to-Many / Many-to-Many relationship.

---

##  Features

The app supports the following operations:

- Add Student with Subjects  
- Get Student by Student ID  
- Get Subject by Subject ID  
- Get all Students with their Subjects  
- Update Student details using Student ID  
- Update specific Subject using Student ID  
- Delete Student using Student ID  
- Delete specific Subject from a Student  

---

##  Project Structure

```
src/
├── com.Entity
│   ├── Student.java
│   └── Subject.java
│
├── com.Service
│   ├── StudentServices.java     (interface / abstract)
│   └── StudentServiceIMPL.java
│
├── com.Controller
│   └── HomeController.java
│
└── resources
    └── hibernate.cfg.xml
```

---

## Relationship Meaning

- Adding Student ➝ their Subjects are also saved (Cascade)  
- Updating Student ➝ only Student updated  
- Deleting Student ➝ their Subjects also deleted (If Cascade = ALL)  
- Deleting a Subject ➝ Student remains  
- A Student can have multiple Subjects  

---

## ▶ How to Run

### 1. Create MySQL database:
```sql
CREATE DATABASE mydb;
```

### 2. Update database credentials in `hibernate.cfg.xml`.

### 3. Run the application:
```bash
java com.Controller.HomeController
```

### 4. Use any method in `HomeController`:
```java
ss.addStudentWithSubjects();
ss.getStudentOnly();
ss.getAllStudents();
ss.updateSpecificSubjectUsingStudentID();
ss.deleteSpecificSubjectFromSpecificStudentUsingSID();
```

---

## 🧪 Example Output

### Adding Student
```
Enter the student name : Rohan
Enter how many subjects you want to add : 2
Enter subject name : Java
Enter subject name : DBMS

Student Added!
```

---

## 🔧 Technologies Used

- Java 17+  
- Hibernate ORM 6.x  
- MySQL / MariaDB  
- Maven  
- IntelliJ IDEA  

---

## 📌 Concepts Demonstrated

- Hibernate Session & Transaction Handling  
- One-to-Many / Many-to-Many Mapping  
- Cascade Operations  
- CRUD Operations  
- HQL (`from Student`)  
- Dirty Checking  
- Console Input Handling  
- Entity Relationship Mapping  

---

## 📎 Future Enhancements

- Add menu-driven console UI  
- Convert to Spring Boot  
- Add exception handling  
- Add logging with SLF4J  
- Add bidirectional mapping (Subjects ↔ Students)  

---

## 📄 License

This project is for educational and learning purposes.  
Feel free to modify or improve.

---

## 👨‍💻 Author

**Manoj Bachhav**  
GitHub: [ManozBachhav](https://github.com/ManozBachhav)

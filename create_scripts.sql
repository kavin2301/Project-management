create table Project (
    id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    status ENUM('started', 'dev', 'build', 'test', 'deployed') NOT NULL
);
create table Employee (
    id INT primary key auto_increment,
    name VARCHAR(255) not null,
    designation VARCHAR(100) not null,
    gender ENUM('Male', 'Female', 'Other') not null,
    salary DECIMAL(10,2) not null,
    project_id INT,
    FOREIGN KEY (project_id) references Project(id) on delete set null);

create table Task (
    task_id INT primary key auto_increment,
    task_name VARCHAR(255) not null,
    project_id INT,
    employee_id INT,
    status ENUM('Assigned', 'Started', 'Completed') not null,
    FOREIGN KEY (project_id) REFERENCES Project(id) on delete cascade,
    FOREIGN KEY (employee_id) REFERENCES Employee(id) on delete set null);
use casestudy_schema;
select * from employee;
select * from project;
select * from task;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE employee;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO Project (project_name, description, start_date, status) VALUES
('Website Redesign', 'Revamping the corporate website with a modern design.', '2024-01-15', 'started'),
('Mobile App Development', 'Developing a mobile app for e-commerce features.', '2024-02-01', 'dev'),
('Inventory System', 'Building an internal inventory management system.', '2024-03-10', 'build'),
('Customer Feedback Portal', 'Platform for collecting and analyzing customer feedback.', '2024-01-25', 'test'),
('AI Chatbot Integration', 'Integrating AI chatbot into the customer support system.', '2024-04-01', 'deployed');

    
INSERT INTO Employee (name, designation, gender, salary, project_id) VALUES
('Kavin Kaarthik', 'Software Engineer', 'Male', 65000.00, 1),
('Sneha', 'Frontend Developer', 'Female', 60000.00, 1),
('Arjun', 'Backend Developer', 'Male', 70000.00, 2),
('Priya Menon', 'Project Manager', 'Female', 90000.00, 3),
('Rahul Das', 'QA Engineer', 'Male', 55000.00, 4),
('Anjali Rao', 'Data Scientist', 'Female', 85000.00, 5),
('Sam Wilson', 'DevOps Engineer', 'Other', 75000.00, 3);

INSERT INTO Task (task_name, project_id, employee_id, status) VALUES
('Create wireframes', 1, 2, 'Completed'),
('Implement backend APIs', 2, 3, 'Started'),
('Database schema design', 3, 4, 'Assigned'),
('Write test cases', 4, 5, 'Started'),
('Deploy application', 5, 6, 'Completed'),
('CI/CD setup', 3, 7, 'Started'),
('UI implementation', 1, 1, 'Assigned'),
('Bug fixing', 2, 3, 'Assigned');

ALTER TABLE Employee AUTO_INCREMENT = 8;
ALTER TABLE Task AUTO_INCREMENT = 9;

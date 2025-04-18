USE speed_monitoring_db;


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    branch VARCHAR(255),
    enrollment_number VARCHAR(255)
);
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    profession VARCHAR(255),
    college_id VARCHAR(255)
);
CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT, -- References 'users' table
    timestamp DATETIME NOT NULL,
    speed FLOAT NOT NULL,
    status VARCHAR(255), -- e.g., "Below Threshold", "Normal"
    FOREIGN KEY (user_id) REFERENCES users(id)
);

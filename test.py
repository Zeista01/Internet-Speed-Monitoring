import mysql.connector

# Connect to the MySQL database
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Aniket@166165",  # <-- Replace this with your actual password
    database="speed_monitoring_db"
)

cursor = conn.cursor()

# Insert sample data
insert_user = """
INSERT INTO users (username, password, name, branch, enrollment_number)
VALUES ('testuser', 'testpass', 'Test User', 'CSE', 'VNIT2025001');
"""

insert_admin = """
INSERT INTO admins (username, password, name, profession, college_id)
VALUES ('adminuser', 'adminpass', 'Admin User', 'Network Engineer', 'VNITADMIN01');
"""

try:
    cursor.execute(insert_user)
    cursor.execute(insert_admin)
    conn.commit()
    success_message = "✅ Sample user and admin inserted successfully."
except Exception as e:
    success_message = f"❌ Failed to insert sample data: {e}"
finally:
    cursor.close()
    conn.close()

success_message

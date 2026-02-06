#!/bin/bash
# Populate database with test data

echo "Adding test user..."
docker exec espotify-mysql mysql -uespotify_user -pespotify_pass espotify << 'EOF'
INSERT INTO User (username, email, password) VALUES 
('testuser', 'test@espotify.com', 'password123')
ON DUPLICATE KEY UPDATE username=username;

SELECT 'Users in database:' as '';
SELECT username, email FROM User;
EOF

echo -e "\n✓ Test user created: testuser / password123"
echo "You can now log in and add songs!"

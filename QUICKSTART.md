# Espotify - Quick Start Guide

## ✅ Fixed Issues

### 1. **Text Visibility Fixed**
   - Changed text color from BLACK to WHITE in input fields
   - You can now see what you're typing!

### 2. **Database Setup Complete**
   - MySQL running in Docker on localhost:3306
   - All tables created automatically

### 3. **Better Error Messages**
   - Added clear error messages for database operations
   - Console now shows success/failure for user and song creation

## 🚀 How to Use the Application

### First Time Setup

1. **Start the database** (if not already running):
   ```bash
   docker compose up -d
   ```

2. **Check database status**:
   ```bash
   ./check_db.sh
   ```

3. **Create a test user** (optional):
   ```bash
   ./create_test_user.sh
   ```
   This creates: username=`testuser`, password=`password123`

### Using the Application

1. **Run the application** through VS Code or:
   ```bash
   # Your IDE should have a run button
   ```

2. **First, create an account or use test account**:
   - Click "Sign Up" 
   - Enter username, email, and password
   - Or use test credentials: `testuser` / `password123`

3. **Log in with your account**

4. **⚠️ IMPORTANT: You MUST be logged in to add songs!**
   - The app requires a logged-in user to add songs
   - If you try to add a song without logging in, you'll get an error

### Adding Songs

1. Make sure you're logged in
2. Click "Add Songs"
3. Fill in all fields (Title, Author, Genre, Album, Duration as `min:sec`)
4. Click "import file..." to select an MP3 file
5. Click "ADD SONG"

### Checking What's in the Database

Run the helper script anytime:
```bash
./check_db.sh
```

This shows:
- All users
- All songs
- All playlists
- Count summary

## 🐛 Troubleshooting

### "Cannot connect to database"
- Make sure Docker Desktop is running
- Run: `docker compose up -d`
- Wait 10-15 seconds for MySQL to initialize

### "Song was not added"
Check the console output for details. Common causes:
- Not logged in (you'll see an error dialog now)
- Database connection issue
- Duplicate song (same title + author)

### "User not created"
- Check console for error details
- Username might already exist
- Email format might be invalid

### Can't read text in UI
- This should be fixed now (white text on dark background)
- If still having issues, restart the application

## 📝 Database Credentials

- **Host**: localhost
- **Port**: 3306
- **Database**: espotify
- **Username**: espotify_user
- **Password**: espotify_pass

## 🛠️ Useful Commands

```bash
# Start database
docker compose up -d

# Stop database
docker compose down

# Check database contents
./check_db.sh

# Create test user
./create_test_user.sh

# View database logs
docker compose logs -f mysql

# Access MySQL directly
docker exec -it espotify-mysql mysql -uespotify_user -pespotify_pass espotify
```

## 💡 Tips

1. Always log in before trying to add songs
2. Check console output for debugging info
3. Use the check_db.sh script to verify data was saved
4. Duration format is `minutes:seconds` (e.g., `3:45`)
5. Test user credentials: `testuser` / `password123`

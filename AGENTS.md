# Espotify - Agent Guide

## Project Overview

Espotify is a Java desktop music streaming application modeled after Spotify. It provides a Swing-based GUI for users to upload and manage MP3 music files, create playlists, play music with controls, and view song statistics by genre. The application also fetches song lyrics via an external API (lyrics.ovh).

**Authors**: Alan Beltrán, Alvaro Feher, Marc Barberà, Youssef Bat, Albert Gomez  
**Version**: 1.0  
**Since**: 29/03/2022

## Technology Stack

- **Language**: Java 17 (Temurin JDK)
- **GUI Framework**: Swing with custom components
- **Database**: MySQL 8.0 (containerized via Docker)
- **Database Connectivity**: JDBC (MySQL Connector/J 8.0.23)
- **JSON Processing**: Gson 2.10.1
- **Audio Playback**: JLayer 1.0.1 (MP3 decoding)
- **External API**: Song Lyrics API (lyrics.ovh)

## Project Structure

```
/Users/alan/JavaEspotify/
├── src/                              # Main source directory
│   ├── Main.java                     # Application entry point
│   ├── business/                     # Business logic layer
│   │   ├── BusinessFacade.java       # Interface for business operations
│   │   ├── ModelFacade.java          # Implementation of business facade
│   │   ├── SongLyricsAPI.java        # External API client for lyrics
│   │   ├── entities/                 # Domain models (User, Song, Playlist, Player)
│   │   └── managers/                 # Business logic managers
│   ├── persistence/                  # Data access layer (DAO interfaces)
│   │   └── SQL/                      # SQL implementations using JDBC
│   └── presentation/                 # UI layer (MVC pattern)
│       ├── MainView.java             # Main JFrame with CardLayout
│       ├── controllers/              # MVC Controllers
│       └── views/                    # Swing-based UI components
├── libs/                             # External JAR dependencies
├── assets/                           # Image resources (icons, logos)
├── songs/                            # Sample MP3 files
├── config/                           # Configuration files
│   └── config.json                   # Database connection config
├── docker-compose.yml                # MySQL container setup
├── init.sql                          # Database schema initialization
├── check_db.sh                       # Helper script to check DB contents
└── create_test_user.sh               # Helper script to create test user
```

## Architecture

The application follows a **layered architecture with MVC pattern**:

1. **Presentation Layer**: Swing-based GUI using CardLayout for view switching
   - Controllers handle user interactions and coordinate between view and business layer
   - Views are pure UI components extending Swing panels

2. **Business Layer**: Facade pattern + Manager classes
   - `BusinessFacade` interface simplifies access to business operations
   - Managers handle specific domains: UserManager, SongManager, PlaylistManager, SongPlayerManager
   - Entities are POJOs: User, Song, Playlist, Player

3. **Persistence Layer**: DAO pattern with SQL implementations
   - Abstract data access through interfaces (UserDAO, SongDAO, PlaylistDAO)
   - `SQLConnector` singleton manages database connections
   - JDBC-based implementations in `persistence.SQL` package

## Build and Run

### Prerequisites
- Java 17 (Temurin JDK recommended)
- Docker Desktop
- VS Code or IntelliJ IDEA

### Dependencies
No formal build system (Maven/Gradle). Dependencies are manually managed in `/libs/`:
- `mysql-connector-java-8.0.23.jar`
- `gson-2.10.1.jar`
- `jl1.0.1.jar`

### Running the Application

1. **Start the database**:
   ```bash
   docker compose up -d
   ```

2. **Run the application**:
   - Use your IDE's run button, or
   - Compile and run `Main.java` manually

3. **Create test user** (optional):
   ```bash
   ./create_test_user.sh
   ```
   Credentials: `testuser` / `password123`

### Useful Commands

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

## Database Configuration

**Connection Details** (from `config/config.json`):
- Host: localhost
- Port: 3306
- Database: espotify
- Username: espotify_user
- Password: espotify_pass

**Schema**:
- `User` - User accounts (username PK, email, password)
- `Song` - Music tracks (title+author PK, genre, album, filepath, duration, owner FK)
- `Playlist` - User playlists (name+author PK, owner FK)
- `SongPlaylistLink` - Many-to-many relationship between songs and playlists
- `Genre` - Music genres

## Code Style Guidelines

- **Comments**: Use Javadoc-style comments for classes and methods
- **Naming**: Follow Java conventions (PascalCase for classes, camelCase for methods/variables)
- **Organization**: Group imports by package; separate standard library from project imports
- **Thread Safety**: Audio playback runs in a separate Thread for non-blocking operation
- **Error Handling**: Use console output for debugging; show user-friendly error dialogs

## Testing

**No formal testing framework** is present. Manual testing approach:
- `check_db.sh` - Queries database to verify users, songs, playlists
- `create_test_user.sh` - Creates a test user for manual testing
- Sample MP3 files in `/songs` directory for testing uploads

## Security Considerations

- **Password Storage**: MD5 hashing is used (Note: MD5 is cryptographically weak for production use)
- **Email Validation**: Regex-based format validation
- **Password Policy**: Minimum 8 characters, requires 2 of: numbers, capitals, lowercase, special characters
- **Database Credentials**: Stored in plain text in `config/config.json` (development only)

## Development Notes

- **UI Text**: White text on dark background for visibility
- **Duration Format**: Use `minutes:seconds` format (e.g., `3:45`)
- **Login Required**: User must be logged in to add songs
- **Duplicate Prevention**: Songs are unique by title + author combination
- **Custom Components**: Hint text fields, custom scrollbar UI in `presentation.views.GUIassets`

## Entry Point

**Main Class**: `src/Main.java`

The application initializes on the AWT Event Dispatch Thread:
1. Creates `BusinessFacade` (ModelFacade implementation)
2. Creates `MainView` (JFrame with CardLayout)
3. Instantiates all controllers
4. Registers controllers with the main view
5. Starts the application

# Espotify Database Setup

This document explains how to set up and use the MySQL database for the JavaEspotify application using Docker.

## Prerequisites

- Docker Desktop installed and running on your Mac
- Docker Compose (included with Docker Desktop)

## Quick Start

1. **Start Docker Desktop** - Make sure Docker Desktop is running

2. **Start the database:**
   ```bash
   docker compose up -d
   ```

3. **Verify the database is running:**
   ```bash
   docker compose ps
   ```

4. **Run your Java application** - The config is already updated to connect to localhost

## Database Configuration

The database is configured with:
- **Host**: localhost
- **Port**: 3306
- **Database**: espotify
- **Username**: espotify_user
- **Password**: espotify_pass

These credentials are already configured in `config/config.json`.

## Database Schema

The database includes the following tables:
- `User` - User accounts (username, email, password)
- `Song` - Music tracks (title, author, genre, album, filepath, duration, owner)
- `Playlist` - User playlists (name, author)
- `SongPlaylistLink` - Links songs to playlists
- `Genre` - Music genres

## Useful Commands

### Stop the database:
```bash
docker compose down
```

### Stop and delete all data:
```bash
docker compose down -v
```

### View database logs:
```bash
docker compose logs -f mysql
```

### Access MySQL CLI:
```bash
docker exec -it espotify-mysql mysql -u espotify_user -pespotify_pass espotify
```

### Restart the database:
```bash
docker compose restart
```

## Troubleshooting

**Connection refused or cannot connect:**
- Ensure Docker Desktop is running
- Check if the container is running: `docker compose ps`
- Check logs: `docker compose logs mysql`

**Port 3306 already in use:**
- You may have another MySQL instance running locally
- Either stop the local MySQL or change the port in `docker-compose.yml`

**Database schema not initialized:**
- The schema is automatically created from `init.sql` on first startup
- To reinitialize: `docker compose down -v && docker compose up -d`

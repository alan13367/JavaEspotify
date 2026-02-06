-- Espotify Database Schema
-- Initialize database for JavaEspotify application

USE espotify;

-- User table
CREATE TABLE IF NOT EXISTS User (
    username VARCHAR(50) PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Song table
CREATE TABLE IF NOT EXISTS Song (
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    album VARCHAR(200),
    filepath VARCHAR(500) NOT NULL,
    duration BIGINT NOT NULL,
    owner VARCHAR(50),
    PRIMARY KEY (title, author),
    FOREIGN KEY (owner) REFERENCES User(username) ON DELETE CASCADE
);

-- Playlist table
CREATE TABLE IF NOT EXISTS Playlist (
    name VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    PRIMARY KEY (name, author),
    FOREIGN KEY (author) REFERENCES User(username) ON DELETE CASCADE
);

-- SongPlaylistLink table
CREATE TABLE IF NOT EXISTS SongPlaylistLink (
    playlistName VARCHAR(100) NOT NULL,
    playlistAuthor VARCHAR(50) NOT NULL,
    songArtist VARCHAR(100) NOT NULL,
    songTitle VARCHAR(200) NOT NULL,
    PRIMARY KEY (playlistName, playlistAuthor, songArtist, songTitle),
    FOREIGN KEY (playlistName, playlistAuthor) REFERENCES Playlist(name, author) ON DELETE CASCADE,
    FOREIGN KEY (songTitle, songArtist) REFERENCES Song(title, author) ON DELETE CASCADE
);

-- Genre table (optional - appears to be used minimally)
CREATE TABLE IF NOT EXISTS Genre (
    name VARCHAR(50) PRIMARY KEY
);

-- Sample data (optional - uncomment to add test data)
-- INSERT INTO User (username, email, password) VALUES 
-- ('testuser', 'test@example.com', 'password123');

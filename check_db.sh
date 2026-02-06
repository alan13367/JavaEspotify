#!/bin/bash
# Helper script to check database contents

echo "=== USERS ==="
docker exec espotify-mysql mysql -uespotify_user -pespotify_pass -e "SELECT username, email FROM espotify.User;" 2>/dev/null

echo -e "\n=== SONGS ==="
docker exec espotify-mysql mysql -uespotify_user -pespotify_pass -e "SELECT title, author, genre, album FROM espotify.Song;" 2>/dev/null

echo -e "\n=== PLAYLISTS ==="
docker exec espotify-mysql mysql -uespotify_user -pespotify_pass -e "SELECT name, author FROM espotify.Playlist;" 2>/dev/null

echo -e "\n=== DATABASE STATUS ==="
docker exec espotify-mysql mysql -uespotify_user -pespotify_pass -e "
    SELECT 
        (SELECT COUNT(*) FROM espotify.User) as users,
        (SELECT COUNT(*) FROM espotify.Song) as songs,
        (SELECT COUNT(*) FROM espotify.Playlist) as playlists;
" 2>/dev/null

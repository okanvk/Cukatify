export const authEndpoint = 'https://accounts.spotify.com/authorize';
const clientId = "844bc5b0b0f84f83bd226642b3108896";
const redirectUri = "http://localhost:3000";
const scopes = [
  "user-read-currently-playing",
  "user-read-playback-state",
];
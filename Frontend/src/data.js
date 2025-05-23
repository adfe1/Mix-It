import fs from 'fs';
import fetch from 'node-fetch'; // Bei Node 18+ ist fetch eingebaut, sonst musst du node-fetch installieren

const API_URL = 'http://localhost:8080/api/items'; // Deine API-URL
const OUTPUT_FILE = '../data.json';     // Zielpfad (z. B. für Astro)

async function fetchAndSaveJSON() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error(`HTTP-Fehler: ${response.status}`);

        const data = await response.json();

        fs.writeFileSync(OUTPUT_FILE, JSON.stringify(data, null, 2), 'utf8');
        console.log('Datei gespeichert:', OUTPUT_FILE);
    } catch (error) {
        console.error('Fehler beim Abrufen oder Speichern:', error);
    }
}

fetchAndSaveJSON();

// Daten holen (GET)
fetch('http://localhost:8080/api/items')
    .then(res => res.json())
    .then(data => {

    });

// Daten senden (POST)
fetch('http://localhost:8080/api/items', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name: 'Neues Item' })
})
    .then(res => res.json())
    .then(data => {
        // Antwort verarbeiten
    });

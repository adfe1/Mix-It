import type { APIRoute } from "astro";

export const POST: APIRoute = async ({ request }) => {
    const body = await request.json();
    const { name } = body;

    // Hier würdest du deine Spring Boot API aufrufen oder eine DB verwenden
    // Beispiel-Response:
    return new Response(
        JSON.stringify({ message: `Kunde ${name} empfangen` }),
        { status: 200, headers: { 'Content-Type': 'application/json' } }
    );

};
await fetch('http://localhost:8080/api/register', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({  })
});


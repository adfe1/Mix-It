export async function POST() {
    return new Response(null, {
        status: 200,
        headers: {
            // Delete the session cookie
            'Set-Cookie': 'session=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 GMT; HttpOnly; Secure; SameSite=Strict',
        },
    });
}

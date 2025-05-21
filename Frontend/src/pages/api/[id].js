export async function post({ params, request }) {
    const { id } = params;
    await fetch(`http://localhost:8080/api/favorites/${id}`, { method: "POST" });
    return new Response(null, { status: 200 });
}

export async function del({ params, request }) {
    const { id } = params;
    await fetch(`http://localhost:8080/api/favorites/${id}`, { method: "DELETE" });
    return new Response(null, { status: 200 });
}

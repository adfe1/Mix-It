// @ts-ignore
export async function GET({ request }) {
    const cookie = request.headers.get("cookie") || "";

    const res = await fetch("http://localhost:8080/api/auth/userinfo", {
        headers: {
            cookie
        },
        credentials: "include"
    });

    const body = await res.text();

    return new Response(body, {
        status: res.status,
        headers: {
            "Content-Type": "application/json"
        }
    });
}

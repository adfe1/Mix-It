import React, { useEffect, useState } from 'react';

export default function SavedDrinks() {
    const [savedDrinks, setSavedDrinks] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Read JWT token from cookie
        function getCookie(name) {
            const value = `; ${document.cookie}`;
            const parts = value.split(`; ${name}=`);
            if (parts.length === 2) return parts.pop().split(';').shift();
        }
        const token = getCookie('session'); // adjust name as needed

        if (!token) {
            setError('No session token found, please log in.');
            setLoading(false);
            return;
        }

        fetch('http://localhost:8080/api/save/my', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
            },
            credentials: 'include',
        })
            .then(res => {
                if (!res.ok) throw new Error(`Fetch failed: ${res.status}`);
                return res.json();
            })
            .then(async (saves) => {
                const drinks = await Promise.all(
                    saves.map(async save => {
                        const res = await fetch(`https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=${save.idDrink}`);
                        const data = await res.json();
                        return data.drinks?.[0] ?? null;
                    })
                );
                setSavedDrinks(drinks.filter(d => d !== null));
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading saved drinks...</p>;
    if (error) return <p>Error: {error}</p>;
    if (!savedDrinks.length) return <p>You haven't saved any drinks yet.</p>;

    return (
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill,minmax(300px,1fr))', gap: '2rem' }}>
            {savedDrinks.map(drink => (
                <div key={drink.idDrink} style={{ border: '1px solid #ccc', padding: '1rem', borderRadius: '0.5rem', background: '#fff' }}>
                    <h3>{drink.strDrink}</h3>
                    <img src={drink.strDrinkThumb} alt={drink.strDrink} style={{ width: '100%', borderRadius: '0.5rem' }} />
                    <p><strong>Glass:</strong> {drink.strGlass}</p>
                    <p><strong>Instructions:</strong> {drink.strInstructions}</p>
                    {[...Array(15)].map((_, i) => {
                        const ingredient = drink[`strIngredient${i + 1}`];
                        const measure = drink[`strMeasure${i + 1}`];
                        return ingredient ? <p key={i}>{ingredient} {measure || ''}</p> : null;
                    })}
                </div>
            ))}
        </div>
    );
}

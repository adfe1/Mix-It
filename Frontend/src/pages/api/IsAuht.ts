let Auth = false;

export function setState(state: boolean) {
    Auth = state;
}

export function getState() {
    return Auth;
}
async function apiFetch(url, options = {}) {

    const response = await fetch(url, {
        credentials: "include",
        ...options
    });

    // 401 = NOT LOGGED IN
    if (response.status === 401) {

        window.location.href = "login.html";

        return null;
    }

    // 403 = NO PERMISSION
    if (response.status === 403) {

        alert("Access Denied");

        return null;
    }

    return response;
}
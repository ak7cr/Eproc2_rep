// Base API URL
const apiUrl = "http://localhost:8085/registration/home/vendors";

// Function to fetch vendors
async function fetchVendors() {
    const search = document.getElementById("search").value;
    const sortBy = document.getElementById("sortBy").value;

    // Construct query parameters
    const params = new URLSearchParams();
    if (search) params.append("search", search);
    if (sortBy) params.append("sortBy", sortBy);

    // Fetch data from the backend
    try {
        const response = await fetch(`${apiUrl}?${params.toString()}`);
        if (!response.ok) throw new Error("Failed to fetch vendors");
        const vendors = await response.json();
        renderTable(vendors);
    } catch (error) {
        console.error("Error:", error);
        renderError("Error loading vendors. Please try again later.");
    }
}

// Function to render the table
function renderTable(vendors) {
    const tbody = document.getElementById("vendorTable");
    tbody.innerHTML = ""; // Clear existing rows

    if (vendors.length === 0) {
        tbody.innerHTML = `<tr><td colspan="4">No vendors found</td></tr>`;
        return;
    }

    vendors.forEach(vendor => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${vendor.name}</td>
            <td>${vendor.email}</td>
            <td>${vendor.phone}</td>
            <td>${vendor.address}</td>
        `;
        tbody.appendChild(row);
    });
}

// Function to render an error message
function renderError(message) {
    const tbody = document.getElementById("vendorTable");
    tbody.innerHTML = `<tr><td colspan="4">${message}</td></tr>`;
}

// Function to update the search input's placeholder
function updatePlaceholder() {
    const sortBy = document.getElementById("sortBy").value;
    const searchInput = document.getElementById("search");

    switch (sortBy) {
        case "name":
            searchInput.placeholder = "Search by name...";
            break;
        case "email":
            searchInput.placeholder = "Search by email...";
            break;
        case "phone":
            searchInput.placeholder = "Search by phone...";
            break;
        case "address":
            searchInput.placeholder = "Search by address...";
            break;
        default:
            searchInput.placeholder = "Search...";
            break;
    }
}

// Initialize the page
document.addEventListener("DOMContentLoaded", () => {
    updatePlaceholder(); // Update placeholder on page load
    fetchVendors(); // Fetch vendors on page load
});

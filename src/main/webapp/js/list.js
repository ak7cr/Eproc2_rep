// Base API URL
const apiUrl = "http://localhost:8085/list/list_items";

// Function to fetch list data
async function fetchListData() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) throw new Error("Failed to fetch list data");
        const listData = await response.json();
        renderTable(listData);
    } catch (error) {
        console.error("Error:", error);
        renderError("Error loading list data. Please try again later.");
    }
}

// Function to render the table
function renderTable(listData) {
    const tbody = document.getElementById("listTable");
    tbody.innerHTML = ""; // Clear existing rows

    if (listData.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6">No data found</td></tr>`;
        return;
    }

    listData.forEach(item => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${item.slNo}</td>
            <td>${item.tenderDescription}</td>
            <td>${item.referenceNo}</td>
            <td>${item.department}</td>
            <td>${item.endDate}</td>
            <td>${item.timeLeft}</td>
        `;
        tbody.appendChild(row);
    });
}

// Function to render an error message
function renderError(message) {
    const tbody = document.getElementById("listTable");
    tbody.innerHTML = `<tr><td colspan="6">${message}</td></tr>`;
}

// Initialize the page
document.addEventListener("DOMContentLoaded", () => {
    fetchListData(); // Fetch list data on page load
});
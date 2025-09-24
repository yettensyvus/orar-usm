// DOM Elements
const select = document.getElementById("dynamicSelect");
const profesoriBtn = document.getElementById("profesori");
const blocBtn = document.getElementById("bloc");
const blockSelection = document.getElementById("blockSelection");

// Block toggle elements
const bloc1Btn = document.getElementById("bloc1");
const bloc2Btn = document.getElementById("bloc2");
const bloc3Btn = document.getElementById("bloc3");
const bloc4Btn = document.getElementById("bloc4");

// Sample data for professors
const profesoriData = [
    "Prof. Ion Popescu",
    "Prof. Maria Ionescu",
    "Dr. Alexandru Mihai",
    "Prof. Elena Georgescu",
    "Dr. Vasile Dumitru",
    "Prof. Ana-Maria Stoica",
    "Dr. Constantin Radu",
    "Prof. Ioana Petre"
];

// Block-specific room data
const blocData = {
    "bloc1": [
        "Sala 101",
        "Sala 102",
        "Sala 103",
        "Laborator 1A",
        "Laborator 1B"
    ],
    "bloc2": [
        "Sala 201",
        "Sala 202",
        "Sala 203",
        "Laborator 2A",
        "Amfiteatru 2"
    ],
    "bloc3": [
        "Sala 301",
        "Sala 302",
        "Laborator Informatică",
        "Laborator Fizică",
        "Sala de conferințe A"
    ],
    "bloc4": [
        "Sala 401",
        "Sala 402",
        "Laborator Chimie",
        "Amfiteatrul Mare",
        "Sala de seminar"
    ]
};

// Function to populate dropdown with data
function populateDropdown(data, placeholder) {
    select.innerHTML = `<option value="">${placeholder}</option>`;

    data.forEach(item => {
        const option = document.createElement("option");
        option.value = item;
        option.textContent = item;
        select.appendChild(option);
    });
}

// Function to get selected block
function getSelectedBlock() {
    if (bloc1Btn.checked) return "bloc1";
    if (bloc2Btn.checked) return "bloc2";
    if (bloc3Btn.checked) return "bloc3";
    if (bloc4Btn.checked) return "bloc4";
    return "bloc1"; // default
}

// Event listeners for main toggle buttons (updated for swapped buttons)
blocBtn.addEventListener("change", () => {
    if (blocBtn.checked) {
        blockSelection.classList.add("active");
        const selectedBlock = getSelectedBlock();
        populateDropdown(blocData[selectedBlock], "Selectează sala din bloc");
    }
});

profesoriBtn.addEventListener("change", () => {
    if (profesoriBtn.checked) {
        populateDropdown(profesoriData, "Selectează profesorul");
        blockSelection.classList.remove("active");
    }
});

// Event listeners for block toggle buttons
function updateBlockRooms() {
    if (blocBtn.checked) {
        const selectedBlock = getSelectedBlock();
        populateDropdown(blocData[selectedBlock], "Selectează sala din bloc");
    }
}

bloc1Btn.addEventListener("change", updateBlockRooms);
bloc2Btn.addEventListener("change", updateBlockRooms);
bloc3Btn.addEventListener("change", updateBlockRooms);
bloc4Btn.addEventListener("change", updateBlockRooms);

// Faculty card click handlers
function setupFacultyCards() {
    const facultyCards = document.querySelectorAll('.faculty-card');

    facultyCards.forEach(card => {
        card.addEventListener('click', function () {
            const facultyName = this.querySelector('.faculty-text').textContent;
            console.log(`Clicked on: ${facultyName}`);

            // Add visual feedback
            this.style.transform = 'scale(0.98)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);

            alert(`Navigating to ${facultyName}`);
        });

        // Add keyboard accessibility
        card.addEventListener('keypress', function (e) {
            if (e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                this.click();
            }
        });

        // Make cards focusable for keyboard navigation
        card.setAttribute('tabindex', '0');
        card.setAttribute('role', 'button');
        card.setAttribute('aria-label', `Navigate to ${card.querySelector('.faculty-text').textContent}`);
    });
}

// Login button functionality
function setupLoginButton() {
    const loginBtn = document.querySelector('.btn-login');

    loginBtn.addEventListener('click', function () {
        console.log('Login button clicked');
        alert('Login functionality would be implemented here');
    });
}

// Select dropdown change handler
function setupSelectHandler() {
    select.addEventListener('change', function () {
        if (this.value) {
            let selectedType;
            if (profesoriBtn.checked) selectedType = 'profesor';
            else if (blocBtn.checked) {
                const block = getSelectedBlock();
                selectedType = `sala din ${block}`;
            }

            console.log(`Selected ${selectedType}: ${this.value}`);
            alert(`Loading schedule for ${this.value}`);
        }
    });
}

// Initialize all functionality when DOM is loaded
document.addEventListener('DOMContentLoaded', function () {
    // Initialize with default state (now Bloc is default)
    const selectedBlock = getSelectedBlock();
    populateDropdown(blocData[selectedBlock], "Selectează sala din bloc");

    // Setup all event handlers
    setupFacultyCards();
    setupLoginButton();
    setupSelectHandler();

    console.log('USM Schedule app initialized successfully');
});
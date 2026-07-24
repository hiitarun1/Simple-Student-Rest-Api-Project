// app.js – Handles live search and filtering for the Student Management Dashboard

const API_BASE = '/api/students';

// ==== DOM Elements ==== //
const studentTableBody = document.getElementById('student-table-body');
const searchInput = document.getElementById('searchInput');
const emptyState = document.getElementById('emptyState');
const loadingSpinner = document.getElementById('loadingSpinner');
const toast = document.getElementById('toast');

let debounceTimeout = null;

// ==== Helper Functions ==== //
function showToast(message, type = 'info') {
  if (!toast) return;
  toast.textContent = message;
  toast.style.background = type === 'error' ? '#dc2626' : '#2563eb';
  toast.classList.add('show');
  setTimeout(() => toast.classList.remove('show'), 3000);
}

// Ensure toast is hidden on load
if (toast) {
  toast.classList.remove('show');
}

function toggleSpinner(show) {
  if (!loadingSpinner) return;
  loadingSpinner.style.display = show ? 'flex' : 'none';
}

function renderTable(students) {
  if (!studentTableBody) return;
  
  studentTableBody.innerHTML = '';
  
  if (students.length === 0) {
    if (emptyState) {
      emptyState.textContent = 'No students found.';
      emptyState.style.display = 'block';
    }
    return;
  }
  
  if (emptyState) {
    emptyState.style.display = 'none';
  }
  
  students.forEach(student => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${student.roll}</td>
      <td>${student.name}</td>
      <td>${student.email}</td>
      <td>${student.marks}</td>
      <td>
        <a class="btn btn-edit" href="/students-view/edit/${student.roll}">Edit</a>
        <a class="btn btn-delete" href="/students-view/delete/${student.roll}" onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
      </td>
    `;
    studentTableBody.appendChild(tr);
  });
}

// ==== Live Search Flow ==== //
async function performSearch() {
  const query = searchInput ? searchInput.value.trim() : '';
  toggleSpinner(true);
  try {
    let url = API_BASE;
    if (query) {
      url = `${API_BASE}/search?query=${encodeURIComponent(query)}`;
    }
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Search failed on server');
    }
    const students = await response.json();
    renderTable(students);
  } catch (err) {
    showToast(err.message, 'error');
  } finally {
    toggleSpinner(false);
  }
}

// ==== Event Listener with Debounce ==== //
if (searchInput) {
  searchInput.addEventListener('input', () => {
    clearTimeout(debounceTimeout);
    debounceTimeout = setTimeout(performSearch, 200);
  });
}

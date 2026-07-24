// app.js – Handles all client‑side interactions for the Student Management Dashboard

// ==== Configuration ==== //
const API_BASE = '/students'; // Spring Boot base path (relative to context)

// ==== DOM Elements ==== //
const studentTableBody = document.getElementById('student-table-body');
const toast = document.getElementById('toast');
const loadingSpinner = document.getElementById('loadingSpinner');
const emptyState = document.getElementById('emptyState');

// Form fields
const rollInput = document.getElementById('rollNumber');
const nameInput = document.getElementById('studentName');
const emailInput = document.getElementById('email');
const marksInput = document.getElementById('marks');
const addBtn = document.getElementById('addBtn');
const updateBtn = document.getElementById('updateBtn');
const clearBtn = document.getElementById('clearBtn');
const searchInput = document.getElementById('searchInput');

let editingStudentId = null; // holds the id of the student being edited

// ==== Helper Functions ==== //
function showToast(message, type = 'info') {
  toast.textContent = message;
  toast.style.background = type === 'error' ? '#dc2626' : '#2563eb';
  toast.classList.add('show');
  setTimeout(() => toast.classList.remove('show'), 3000);
}

function toggleSpinner(show) {
  loadingSpinner.style.display = show ? 'flex' : 'none';
}

function clearForm() {
  document.getElementById('student-form').reset();
  editingStudentId = null;
  updateBtn.disabled = true;
  addBtn.disabled = false;
}

function validateForm() {
  const roll = rollInput.value.trim();
  const name = nameInput.value.trim();
  const email = emailInput.value.trim();
  const marks = marksInput.value.trim();

  if (!roll || !name || !email || marks === '') {
    showToast('All fields are required.', 'error');
    return false;
  }
  const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
  if (!emailRegex.test(email)) {
    showToast('Invalid email address.', 'error');
    return false;
  }
  const marksNum = Number(marks);
  if (isNaN(marksNum) || marksNum < 0 || marksNum > 100) {
    showToast('Marks must be between 0 and 100.', 'error');
    return false;
  }
  return true;
}

function renderStats(students) {
  const total = students.length;
  const avg = total ? (students.reduce((a, s) => a + s.marks, 0) / total).toFixed(2) : 0;
  const highest = total ? Math.max(...students.map(s => s.marks)) : 0;
  const lowest = total ? Math.min(...students.map(s => s.marks)) : 0;

  document.querySelector('[data-key="totalStudents"]').textContent = total;
  document.querySelector('[data-key="averageMarks"]').textContent = avg;
  document.querySelector('[data-key="highestMarks"]').textContent = highest;
  document.querySelector('[data-key="lowestMarks"]').textContent = lowest;
}

function renderTable(students) {
  // Apply search filter
  const query = searchInput.value.toLowerCase();
  const filtered = students.filter(s =>
    s.rollNumber.toString().includes(query) ||
    s.name.toLowerCase().includes(query) ||
    s.email.toLowerCase().includes(query)
  );

  studentTableBody.innerHTML = '';
  if (filtered.length === 0) {
    emptyState.style.display = 'block';
  } else {
    emptyState.style.display = 'none';
    filtered.forEach(student => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${student.rollNumber}</td>
        <td>${student.name}</td>
        <td>${student.email}</td>
        <td>${student.marks}</td>
        <td>
          <i class="fa-solid fa-pen-to-square action-btn" data-id="${student.id}" title="Edit"></i>
          <i class="fa-solid fa-trash action-btn" data-id="${student.id}" title="Delete"></i>
        </td>`;
      studentTableBody.appendChild(tr);
    });
  }
  renderStats(students);
}

async function fetchStudents() {
  toggleSpinner(true);
  try {
    const response = await fetch(API_BASE);
    if (!response.ok) throw new Error('Failed to fetch students');
    const students = await response.json();
    renderTable(students);
  } catch (err) {
    showToast(err.message, 'error');
  } finally {
    toggleSpinner(false);
  }
}

async function addStudent() {
  if (!validateForm()) return;
  const payload = {
    rollNumber: rollInput.value.trim(),
    name: nameInput.value.trim(),
    email: emailInput.value.trim(),
    marks: Number(marksInput.value.trim())
  };
  toggleSpinner(true);
  try {
    const response = await fetch(API_BASE, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error('Unable to add student');
    showToast('Student added successfully');
    clearForm();
    await fetchStudents();
  } catch (err) {
    showToast(err.message, 'error');
  } finally {
    toggleSpinner(false);
  }
}

async function updateStudent() {
  if (!editingStudentId) return;
  if (!validateForm()) return;
  const payload = {
    id: editingStudentId,
    rollNumber: rollInput.value.trim(),
    name: nameInput.value.trim(),
    email: emailInput.value.trim(),
    marks: Number(marksInput.value.trim())
  };
  toggleSpinner(true);
  try {
    const response = await fetch(API_BASE, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!response.ok) throw new Error('Unable to update student');
    showToast('Student updated successfully');
    clearForm();
    await fetchStudents();
  } catch (err) {
    showToast(err.message, 'error');
  } finally {
    toggleSpinner(false);
  }
}

async function deleteStudent(id) {
  if (!confirm('Are you sure you want to delete this student?')) return;
  toggleSpinner(true);
  try {
    const response = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
    if (!response.ok) throw new Error('Delete failed');
    showToast('Student deleted');
    await fetchStudents();
  } catch (err) {
    showToast(err.message, 'error');
  } finally {
    toggleSpinner(false);
  }
}

function populateForm(student) {
  rollInput.value = student.rollNumber;
  nameInput.value = student.name;
  emailInput.value = student.email;
  marksInput.value = student.marks;
  editingStudentId = student.id;
  addBtn.disabled = true;
  updateBtn.disabled = false;
}

// ==== Event Listeners ==== //
addBtn.addEventListener('click', addStudent);
updateBtn.addEventListener('click', updateStudent);
clearBtn.addEventListener('click', clearForm);
searchInput.addEventListener('input', async () => await fetchStudents());

// Delegate click events for edit / delete icons (event delegation)
studentTableBody.addEventListener('click', async e => {
  const target = e.target;
  if (target.classList.contains('action-btn')) {
    const id = target.getAttribute('data-id');
    if (target.title === 'Edit') {
      // Fetch single student for edit to avoid stale data
      toggleSpinner(true);
      try {
        const res = await fetch(`${API_BASE}/${id}`);
        if (!res.ok) throw new Error('Failed to load student');
        const student = await res.json();
        populateForm(student);
      } catch (err) {
        showToast(err.message, 'error');
      } finally {
        toggleSpinner(false);
      }
    } else if (target.title === 'Delete') {
      await deleteStudent(id);
    }
  }
});

// Initial load
fetchStudents();

// ==== Export (for testing) ==== //
export { fetchStudents, addStudent, updateStudent, deleteStudent };

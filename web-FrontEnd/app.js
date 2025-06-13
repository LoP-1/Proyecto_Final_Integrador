// Simple SPA view switch
function showView(viewId) {
  document.querySelectorAll('.view').forEach(div => div.style.display = 'none');
  document.getElementById(viewId).style.display = 'block';
}

// Guardar usuario logueado
let currentUser = null;

// --- LOGIN ---
document.getElementById('login-form').onsubmit = async function(e) {
  e.preventDefault();
  const usuario = e.target.usuario.value;
  const contraseña = e.target.contraseña.value;
  try {
    const res = await fetch('http://localhost:8080/api/usuarios/login', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({usuario, contraseña})
    });
    if (res.ok) {
      const data = await res.json();
      currentUser = data;
      document.getElementById('login-error').textContent = '';
      showView('libros-view');
      cargarLibros();
    } else {
      const error = await res.json();
      document.getElementById('login-error').textContent = error.mensaje || 'Usuario o contraseña incorrectos';
    }
  } catch (err) {
    document.getElementById('login-error').textContent = 'Error de conexión con el servidor';
  }
};

// --- REGISTRO ---
document.getElementById('register-form').onsubmit = async function(e) {
  e.preventDefault();
  const form = e.target;
  const body = {
    nombre: form.nombre.value,
    apellido: form.apellido.value,
    correo: form.correo.value,
    dni: form.dni.value,
    contraseña: form.contraseña.value,
    rol: form.rol.value
  };
  try {
    const res = await fetch('http://localhost:8080/api/usuarios', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(body)
    });
    if (!res.ok) throw new Error('Error al registrar usuario');
    document.getElementById('register-error').textContent = '';
    alert('¡Registro exitoso! Ahora inicia sesión');
    showView('login-view');
  } catch (err) {
    document.getElementById('register-error').textContent = err.message;
  }
};

// --- LIBROS ---
async function cargarLibros() {
  const cont = document.getElementById('libros-list');
  cont.innerHTML = 'Cargando...';
  try {
    const res = await fetch('http://localhost:8080/api/libros');
    const libros = await res.json();
    cont.innerHTML = '';
    libros.forEach(libro => {
      cont.innerHTML += `
        <div class="libro-card">
          <strong>${libro.titulo}</strong><br>
          <img src="${libro.portada}" alt="portada" style="max-width:100px"><br>
          ${libro.descripcion}<br>
          <small>ID: ${libro.id}</small>
        </div>
      `;
    });
  } catch {
    cont.innerHTML = 'Error al cargar libros';
  }
}

// --- CREAR LIBRO ---
document.getElementById('crear-libro-form').onsubmit = async function(e) {
  e.preventDefault();
  const f = e.target;
  const body = {
    titulo: f.titulo.value,
    descripcion: f.descripcion.value,
    portada: f.portada.value,
    disponibilidad: f.disponibilidad.value === 'true',
    tipo: f.tipo.value,
    categoriaId: parseInt(f.categoriaId.value),
    editorialId: parseInt(f.editorialId.value),
    autoresIds: f.autoresIds.value.split(',').map(id => parseInt(id.trim()))
  };
  try {
    const res = await fetch('http://localhost:8080/api/libros', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    });
    if (!res.ok) throw new Error('Error al crear libro');
    document.getElementById('crear-libro-error').textContent = '';
    alert('Libro creado');
    showView('libros-view');
    cargarLibros();
  } catch (err) {
    document.getElementById('crear-libro-error').textContent = err.message;
  }
};

// --- RESERVAR LIBRO ---
document.getElementById('reservar-libro-form').onsubmit = async function(e) {
  e.preventDefault();
  const f = e.target;
  const body = {
    usuarioId: parseInt(f.usuarioId.value),
    fechaDevolucion: f.fechaDevolucion.value,
    librosIds: f.librosIds.value.split(',').map(id => parseInt(id.trim()))
  };
  try {
    const res = await fetch('http://localhost:8080/api/reservas', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(body)
    });
    if (!res.ok) throw new Error('Error al reservar libro');
    document.getElementById('reservar-libro-error').textContent = '';
    alert('Reserva exitosa');
    showView('libros-view');
  } catch (err) {
    document.getElementById('reservar-libro-error').textContent = err.message;
  }
};

// --- LOGOUT ---
function logout() {
  currentUser = null;
  showView('login-view');
}

// Mostrar login al iniciar
showView('login-view');
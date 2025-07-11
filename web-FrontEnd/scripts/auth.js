    // Cambiar entre login y registro con animación
function switchView(view) {
      document.getElementById('login-view').style.display = 'none';
      document.getElementById('register-view').style.display = 'none';
      if (view === 'login-view') {
        document.getElementById('login-view').classList.add('fade-in');
        document.getElementById('login-view').style.display = 'block';
      } else {
        document.getElementById('register-view').classList.add('fade-in');
        document.getElementById('register-view').style.display = 'block';
      }
      setTimeout(() => {
        document.getElementById('login-view').classList.remove('fade-in');
        document.getElementById('register-view').classList.remove('fade-in');
      }, 500);
    }

    // LOGIN
    document.getElementById('login-form').onsubmit = async function(e) {
      e.preventDefault();
      const usuario = e.target.usuario.value;
      const contraseña = e.target.contraseña.value;
      const errorDiv = document.getElementById('login-error');
      errorDiv.textContent = "";
      try {
        const res = await fetch('http://localhost:8080/api/usuarios/login', {
          method: 'POST',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({usuario, contraseña})
        });
        if (res.ok) {
          const data = await res.json();
          // Guarda el id en localStorage
          localStorage.setItem('usuarioId', data.id);
          localStorage.setItem('usuarioNombre', data.nombre);
          // Pon el validador en positivo
          localStorage.setItem('validador', 'positivo');
          // Redirige o muestra mensaje
          window.location.href = "index.html"; // Cambia por la página principal real
        } else {
          const error = await res.json();
          errorDiv.textContent = error.mensaje || "Usuario o contraseña incorrectos";
        }
      } catch {
        errorDiv.textContent = "No se pudo conectar con el servidor";
      }
    };

    // REGISTRO
    document.getElementById('register-form').onsubmit = async function(e) {
      e.preventDefault();
      const form = e.target;
      const errorDiv = document.getElementById('register-error');
      const successDiv = document.getElementById('register-success');
      errorDiv.textContent = "";
      successDiv.textContent = "";
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
        if (res.ok) {
          successDiv.textContent = "¡Registro exitoso! Ahora puedes iniciar sesión.";
          setTimeout(() => {
            switchView('login-view');
            successDiv.textContent = "";
            form.reset();
          }, 1100);
        } else {
          const error = await res.json();
          errorDiv.textContent = error.mensaje || "No se pudo registrar el usuario";
        }
      } catch {
        errorDiv.textContent = "No se pudo conectar con el servidor";
      }
    };

    // Al cargar, limpiar localStorage y mostrar login
    window.onload = () => {
      // Si quieres mantener la sesión, puedes omitir esto
      localStorage.removeItem('usuarioId');
      localStorage.removeItem('usuarioNombre');
      localStorage.setItem('validador', 'negativo'); // Siempre empieza en negativo
      switchView('login-view');
    }
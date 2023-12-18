function checkWindowSize() {
  var toggleButton = document.getElementById("toggleButton");
  var windowWidth = window.innerWidth;
  var sidebar = document.getElementById("sidebar");

  if (windowWidth > 1000) {
    // Si el ancho de la ventana es mayor a 1000px, mostrar la barra lateral
    sidebar.style.display = "block";
    toggleButton.style.display = "none"; // Ocultar el botón para abrir la barra lateral
  } else {
    // Si el ancho de la ventana es menor o igual a 1000px, ocultar la barra lateral
    sidebar.style.display = "none";
    toggleButton.style.display = "block"; // Mostrar el botón para abrir la barra lateral
  }
}

// Verificar el tamaño de la ventana al cargar la página
window.onload = function () {
  checkWindowSize();

  // Volver a verificar el tamaño de la ventana cuando cambie su tamaño
  window.addEventListener("resize", checkWindowSize);
};


document.addEventListener('DOMContentLoaded', function () {
  var perfilButton = document.getElementById('perfilButton');
  var perfilInfo = document.getElementById('perfilInfo');
  var editarButton = document.getElementById('editarButton');
  var editarForm = document.getElementById('editarForm');
  var sacarTurnoButton = document.getElementById('sacarTurnoButton');
  var divEspecializaciones = document.getElementById('especializaciones');
  var mostrarDoctoresPorEspecializacion = document.getElementById('doctoresPorEspecializacion');
  var turnosButton = document.getElementById('turnos');
  var tarjetasContainer = document.getElementById('tarjetasContainer');

// Función para mostrar u ocultar las tarjetas

if (turnosButton && tarjetasContainer) {
  turnosButton.addEventListener("click", function () {
    if (tarjetasContainer.style.display === "block") {
      tarjetasContainer.style.display = "none";
      // Aquí puedes agregar la lógica para mostrar lo que necesites cuando las tarjetas se ocultan.
      
    } else {
      tarjetasContainer.style.display = "block";
      // Aquí ocultas los otros elementos si las tarjetas se muestran
      editarForm.style.display = "none";
      divEspecializaciones.style.display = "none";
      perfilInfo.style.display = "none";
    }
  });
}


  // Manejador de evento clic para el botón de perfil
  perfilButton.addEventListener("click", function () {
    if (perfilInfo.style.display === "block") {
      perfilInfo.style.display = "none";
    } else {
      perfilInfo.style.display = "block";

      // Ocultar el formulario de edición si está visible

      tarjetasContainer.style.display = "none";
      
      editarForm.style.display = "none";

      divEspecializaciones.style.display = "none";

    }
  });

    
  

  // Manejador de evento clic para el botón de editar
  editarButton.addEventListener("click", function (event) {
    event.preventDefault();

    perfilInfo.style.display = "none";
    // Mostrar el formulario de edición
    editarForm.style.display = "block";
  });

  sacarTurnoButton.addEventListener("click", function () {
    if (divEspecializaciones.style.display === "block") {
      divEspecializaciones.style.display = "none";
    } else {
      divEspecializaciones.style.display = "block";

      tarjetasContainer.style.display = "none";
      // Ocultar el perfil si está visible
      perfilInfo.style.display = "none";

      // Ocultar el formulario de edición si está visible
      editarForm.style.display = "none";
    }
  });
}); 
document.getElementById('especializacionesForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Evita que el formulario se envíe por defecto

  var form = event.target;
  var especializacionId = form.elements['especializacionId'].value;

  // Redirige a la página de lista de doctores con el ID de la especialización
  window.location.href = '/doctor/especializacion/' + especializacionId;
});
function togglePassword() {
  var passwordFields = document.getElementById("passwordFields");
  if (passwordFields.style.display === "none") {
      passwordFields.style.display = "block";
  } else {
      passwordFields.style.display = "none";
  }
}

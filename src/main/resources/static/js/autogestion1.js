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

document.addEventListener("DOMContentLoaded", function () {
  var perfilButton = document.getElementById("perfilButton");
  var perfilInfo = document.getElementById("perfilInfo");
  var editarButton = document.getElementById("editarButton");
  var editarForm = document.getElementById("editarForm");
  var mostrarEspecializacionesButton = document.getElementById(
    "mostrarEspecializaciones"
  );
  var divEspecializaciones = document.getElementById("especializaciones");
  var mostrarDoctoresPorEspecializacion = document.getElementById(
    "doctoresPorEspecializacion"
  );

  // Manejador de evento clic para el botón de perfil
  perfilButton.addEventListener("click", function (event) {
    event.preventDefault();

    if (editarForm.style.display === "block") {
      editarForm.style.display = "none";
    }

    perfilInfo.style.display = "block";
    divEspecializaciones.style.display = "none";
  });

  // Manejador de evento clic para el botón de editar
  editarButton.addEventListener("click", function (event) {
    event.preventDefault();

    perfilInfo.style.display = "none";
    // Mostrar el formulario de edición
    editarForm.style.display = "block";
    divEspecializaciones.style.display = "none"; // Ocultar especializaciones si están visibles
  });

  mostrarEspecializacionesButton.addEventListener("click", function () {
    if (divEspecializaciones.style.display === "block") {
      divEspecializaciones.style.display = "none";
    } else {
      divEspecializaciones.style.display = "block";

      // Ocultar el perfil si está visible
      perfilInfo.style.display = "none";

      // Ocultar el formulario de edición si está visible
      editarForm.style.display = "none";
    }
  });
  mostrarDoctoresPorEspecializacion.addEventListener("click", function () {
    if (mostrarDoctoresPorEspecializacion.style.display == "block") {
      mostrarDoctoresPorEspecializacion.style.display = "none";
    } else {
      divEspecializaciones.style.display = "none";

      // Ocultar el perfil si está visible
      perfilInfo.style.display = "none";

      // Ocultar el formulario de edición si está visible
      editarForm.style.display = "none";

      mostrarDoctoresPorEspecializacion.style.display = "block";
    }
  });
  // Manejo de los campos de contraseña (mostrar/ocultar)
  $("#togglePasswordFields").click(function () {
    $("#passwordFields").toggle();
  });
});

////////////////////////////////////////////////////////////////////////////
//intento de que ande el boton de las especializaciones para q me muestre
 /*function mostrarDoctoresPorEspecializacion(event, form) {
    event.preventDefault();
  
    const especializacionId = form.especializacionId.value;
  
    fetch(`/doctor/especializacion/${especializacionId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then(doctores => {
        const doctoresDiv = document.getElementById("doctoresPorEspecializacion");
        doctoresDiv.innerHTML = "";
  
        // Mostrar la lista de doctores por especialización
        doctores.forEach(doctor => {
          const doctorElement = document.createElement("p");
          doctorElement.textContent = doctor.nombre; // Ajusta esto según tus datos reales
  
          // Agrega más información del doctor si es necesario
          // Por ejemplo: doctorElement.textContent += ` - ${doctor.especialidad}`;
  
          doctoresDiv.appendChild(doctorElement);
        });
  
        // Mostrar el div con la lista de doctores por especialización
        doctoresDiv.style.display = "block";
      })
      .catch(error => {
        console.error('Error al obtener la lista de doctores:', error);
      });
    }*/

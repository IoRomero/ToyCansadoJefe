// autogestion.js

function toggleSidebar() {
    var sidebar = document.getElementById("sidebar");
    sidebar.style.display =
      sidebar.style.display === "none" || sidebar.style.display === ""
        ? "block"
        : "none";
  }
  
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
  }
  
  document.addEventListener("DOMContentLoaded", function () {
    // Obtener referencia al botón de perfil y al div del perfil
    var perfilButton = document.getElementById("perfilButton");
    var perfilInfo = document.getElementById("perfilInfo");
  
    var editarForm = document.getElementById("editarForm");
    // Manejador de evento clic para el botón de perfil
    perfilButton.addEventListener("click", function (event) {
      event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace

  // Si el formulario de edición está visible, ocultarlo
  if (editarForm.style.display === "block") {
    editarForm.style.display = "none";
  }
      // Aquí podrías hacer una solicitud AJAX para obtener los datos del paciente
      // Suponiendo que ya tienes los datos, puedes simular su visualización
      // Cambiar el estilo de visualización del div del perfil al hacer clic en el botón
      perfilInfo.style.display = "block";
  
      // Simulando datos del paciente (sustituye estos datos con los reales)
      document.getElementById("nombreUsuario").innerText = "Nombre del paciente";
      document.getElementById("correoElectronico").innerText = "correo@example.com";
      document.getElementById("edad").innerText = "30 años";
      
    })
  })

  document.addEventListener("DOMContentLoaded", function () {
    // Obtener referencia al botón de editar y al formulario de edición
    var editarButton = document.getElementById("editarButton");
    var editarForm = document.getElementById("editarForm");

    // Manejador de evento clic para el botón de editar
    editarButton.addEventListener("click", function (event) {
        event.preventDefault(); // Prevenir el comportamiento predeterminado del botón

        // Aquí podrías obtener los datos del paciente para prellenar el formulario de edición
        // Supongamos que ya tienes los datos en variables como nombre, email, etc.

        // Llenar los campos del formulario con los datos actuales del paciente
        document.getElementById("nombre").value = "Nombre del paciente"; // Reemplazar con el valor real
        // Otros campos del formulario

        // Mostrar el formulario de edición y ocultar el div del perfil
        editarForm.style.display = "block";
        perfilInfo.style.display = "none";

      
    })
})
;

  
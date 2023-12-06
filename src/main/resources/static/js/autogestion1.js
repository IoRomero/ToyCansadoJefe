<<<<<<< HEAD
function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("closed");
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
};
=======
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
  
    // Manejador de evento clic para el botón de perfil
    perfilButton.addEventListener("click", function (event) {
      event.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
  
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
  ;
>>>>>>> c08482c86cdbff624e01fa74309e7688b7a87dc0

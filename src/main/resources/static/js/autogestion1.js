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
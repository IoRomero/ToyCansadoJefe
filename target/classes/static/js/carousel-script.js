
document.addEventListener("DOMContentLoaded", function () {
  var carousel = document.getElementById("carouselNista");

  // Inicia el carrusel
  var carouselInstance = new bootstrap.Carousel(carousel, {
    interval: 3000,
  });

  // Agrega un evento para detectar cuando se ha completado la transición de la diapositiva
  carousel.addEventListener("slid.bs.carousel", function (event) {
    // Obtiene el índice de la diapositiva actual
    var currentIndex = event.to;

    // Obtiene la cantidad total de diapositivas
    var totalSlides = carouselInstance._items.length;

    // Si es la última diapositiva, vuelve a la primera
    if (currentIndex === totalSlides - 1) {
      carouselInstance.to(0); // Vuelve a la primera diapositiva
    }
  });
});



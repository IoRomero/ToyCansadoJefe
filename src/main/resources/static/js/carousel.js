const carouselWrapper = document.getElementById("carousel-wrapper");
let currentIndex = 0;

function nextSlide() {
  currentIndex = (currentIndex + 1) % 3; // Adjust the number based on the number of images
  updateCarousel();
}
function prevSlide() {
  currentIndex = (currentIndex - 1) % 3; // Adjust the number based on the number of images
  updateCarousel();
}

function updateCarousel() {
  const translateValue = -currentIndex * 100 + "%";
  carouselWrapper.style.transform = "translateX(" + translateValue + ")";
}

setInterval(nextSlide, 3000); // Change slide every 3 seconds, adjust as needed

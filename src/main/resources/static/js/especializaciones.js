$(document).ready(function () {
    $('.tabla-desplegable').addClass('mostrar');

    $('.tabla-desplegable').click(function () {
        $(this).toggleClass('mostrar');
    });
});
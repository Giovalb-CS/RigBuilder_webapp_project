$(document).ready(function() {
    // Nascondi il contenitore dei filtri all'inizio e imposta il testo del pulsante su "Show Filters"
    $(".filters-container").hide();
    $("#toggle-filters").text("Show Filters");

    // Aggiungi l'evento click al pulsante
    $("#toggle-filters").click(function() {
        // Esegui il toggle con animazione
        $(".filters-container").slideToggle("slow", function() {
            // Cambia il testo del pulsante in base alla visibilità
            if ($(".filters-container").is(":visible")) {
                $("#toggle-filters").text("Hide Filters");
            } else {
                $("#toggle-filters").text("Show Filters");
            }
        });
    });
});
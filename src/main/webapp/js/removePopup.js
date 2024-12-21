$(document).ready(function() {
    // Associa l'evento click a tutti i pulsanti "Remove"
    $('.remove-button').on('click', function() {
        const processorId = $(this).data('id'); // Ottieni l'ID dal pulsante
        const fileName = $(this).data('filename'); // Ottieni il nome del file

        // Crea il messaggio per il popup
        const message = `Are you sure you want to delete the ${fileName.substring(0, fileName.length - 1)} n.${processorId}?`;
        $('#remove-popup h2').text(message); // Imposta il testo del popup

        $('#remove-id').val(processorId); // Imposta l'ID nel campo hidden del form
        $('#remove-popup').fadeIn(); // Mostra il popup con effetto fade-in
    });

    // Quando viene premuto "Annulla" o si clicca fuori dal popup, il popup si chiude
    $('.cancel-remove').on('click', function() {
        $('#remove-popup').fadeOut(); // Nascondi il popup con effetto fade-out
    });

    // Chiudi il popup cliccando fuori dal contenuto
    $(window).on('click', function(event) {
        if ($(event.target).is('#remove-popup')) {
            $('#remove-popup').fadeOut(); // Nascondi il popup
        }
    });
});

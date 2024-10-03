$(document).ready(function() {
    $('#image_URL').on('input', function() {
        var imageUrl = $(this).val(); // Prende l'URL inserito nella textarea

        // Controlla se il campo URL non è vuoto
        if (imageUrl.trim() !== "") {
            // Crea un nuovo oggetto immagine per il caricamento asincrono
            var img = new Image();
            img.src = imageUrl;

            // Gestione del caricamento dell'immagine
            img.onload = function() {
                // Se l'immagine è valida, cambia l'attributo src dell'immagine nel form
                $('#preview-image').attr('src', imageUrl);
            };

            // Gestione dell'errore (se l'URL non è valido o l'immagine non esiste)
            img.onerror = function() {
                // Imposta un'immagine di fallback o lascia vuoto
                $('#preview-image').attr('src', '');
                alert("Impossibile caricare l'immagine. Verifica l'URL inserito.");
            };
        } else {
            // Se il campo è vuoto, rimuove l'immagine dal preview
            $('#preview-image').attr('src', '');
        }
    });
});
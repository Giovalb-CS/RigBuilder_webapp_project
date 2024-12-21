window.onload = function() {
    function updateImagePreview() {
        var imageUrl = document.getElementById('image_URL').value;

        if (imageUrl.trim() !== "") {
            var img = new Image();
            img.src = imageUrl;

            img.onload = function() {
                document.getElementById('preview-image').src = imageUrl;
                document.getElementById('preview-image').style.display = "block";
            };

            img.onerror = function() {
                document.getElementById('preview-image').src = '';
                alert("Impossibile caricare l'immagine. Verifica l'URL inserito.");
            };
        } else {
            // Se il campo è vuoto, rimuove l'immagine dal preview
            document.getElementById('preview-image').src = '';
            document.getElementById('preview-image').style.display = "none";
        }
    }

    updateImagePreview();

    document.getElementById('image_URL').addEventListener('input', updateImagePreview);
};

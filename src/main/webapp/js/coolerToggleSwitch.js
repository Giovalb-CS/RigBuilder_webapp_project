document.addEventListener("DOMContentLoaded", function () {
    const radiatorSizeInput = document.getElementById('radiatorSize');
    const coolerHeightInput = document.getElementById('coolerHeight');
    const coolerTypeDisplay = document.getElementById('coolerType');

    // Funzione per aggiornare il tipo di raffreddamento
    function updateCoolerType() {
        const isRadiatorSizeRequired = radiatorSizeInput.hasAttribute('required');
        const isCoolerHeightRequired = coolerHeightInput.hasAttribute('required');

        console.log("Checking cooler type..."); // Debug
        console.log("Radiator size required:", isRadiatorSizeRequired); // Debug
        console.log("Cooler height required:", isCoolerHeightRequired); // Debug

        if (isRadiatorSizeRequired && isCoolerHeightRequired) {
            coolerTypeDisplay.textContent = "Please fill one input between Radiator Size and Cooler Height";
        } else if (isRadiatorSizeRequired) {
            coolerTypeDisplay.textContent = "Water";
        } else if (isCoolerHeightRequired) {
            coolerTypeDisplay.textContent = "Air";
        } else {
            coolerTypeDisplay.textContent = "Error";
        }
    }

    // Funzione per aggiornare lo stato dei campi in base alla modifica
    function updateRequiredFields(changedInput, otherInput) {
        if (changedInput.value.trim() !== "") {
            // Se il campo modificato ha un valore, rimuovi il required dall'altro e svuotalo
            otherInput.required = false;
            otherInput.value = "";
            // Assegna il required al campo che è stato modificato
            changedInput.required = true;
        } else {
            // Se il campo modificato è vuoto, riapplica il required all'altro
            otherInput.required = true;
        }
    }

    // Controllo iniziale al caricamento della pagina
    updateRequiredFields(radiatorSizeInput, coolerHeightInput); // Assicura i required
    updateCoolerType(); // Aggiorna il tipo di raffreddamento al caricamento

    // Event listener per quando si modifica il campo radiatorSize
    radiatorSizeInput.addEventListener('input', function () {
        updateRequiredFields(radiatorSizeInput, coolerHeightInput);
        updateCoolerType(); // Aggiorna il tipo di raffreddamento
    });

    // Event listener per quando si modifica il campo coolerHeight
    coolerHeightInput.addEventListener('input', function () {
        updateRequiredFields(coolerHeightInput, radiatorSizeInput);
        updateCoolerType(); // Aggiorna il tipo di raffreddamento
    });
});

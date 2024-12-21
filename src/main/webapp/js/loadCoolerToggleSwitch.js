document.addEventListener("DOMContentLoaded", function () {
    const radiatorSizeInput = document.getElementById('radiatorSize');
    const coolerHeightInput = document.getElementById('coolerHeight');
    const coolerTypeDisplay = document.getElementById('coolerType');

    // Funzione per aggiornare il tipo di raffreddamento
    function updateCoolerType() {
        const isRadiatorSizeFilled = radiatorSizeInput.value.trim() !== "";
        const isCoolerHeightFilled = coolerHeightInput.value.trim() !== "";

        if (!isRadiatorSizeFilled && !isCoolerHeightFilled) {
            coolerTypeDisplay.textContent = "Please fill one input between Radiator Size and Cooler Height";
        } else if (isRadiatorSizeFilled) {
            coolerTypeDisplay.textContent = "Water";
            coolerHeightInput.required = false;  // Se radiator size è popolato, cooler height non è required
        } else if (isCoolerHeightFilled) {
            coolerTypeDisplay.textContent = "Air";
            radiatorSizeInput.required = false;  // Se cooler height è popolato, radiator size non è required
        } else {
            coolerTypeDisplay.textContent = "Error";
        }
    }

    // Funzione per aggiornare lo stato dei campi in base alla modifica
    function updateRequiredFields(changedInput, otherInput) {
        if (changedInput.value.trim() !== "") {
            otherInput.required = false;
            otherInput.value = "";
            changedInput.required = true;
        } else {
            otherInput.required = true;
        }
    }

    // Inizializza i campi con i valori esistenti
    console.log(existingRadiatorSize, existingCoolerHeight)
    if (existingRadiatorSize) {
        radiatorSizeInput.value = existingRadiatorSize;
        radiatorSizeInput.required = true;
        coolerHeightInput.required = false;
    } else if (existingCoolerHeight) {
        coolerHeightInput.value = existingCoolerHeight;
        coolerHeightInput.required = true;
        radiatorSizeInput.required = false;
    }

    // Aggiorna il tipo di raffreddamento in base ai valori iniziali
    updateCoolerType();

    // Event listener per quando si modifica il campo radiatorSize
    radiatorSizeInput.addEventListener('input', function () {
        updateRequiredFields(radiatorSizeInput, coolerHeightInput);
        updateCoolerType();
    });

    // Event listener per quando si modifica il campo coolerHeight
    coolerHeightInput.addEventListener('input', function () {
        updateRequiredFields(coolerHeightInput, radiatorSizeInput);
        updateCoolerType();
    });
});
